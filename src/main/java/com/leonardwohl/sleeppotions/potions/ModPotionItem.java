package com.leonardwohl.sleeppotions.potions;

import com.google.common.collect.Lists;
import com.leonardwohl.sleeppotions.RegistryItems;
import com.leonardwohl.sleeppotions.effects.EffectsRegistry;
import com.mojang.datafixers.util.Pair;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ModPotionItem extends PotionItem {
    public ModPotionItem(Properties p_42979_) {
        super(p_42979_);
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return pStack.isEnchanted() || getPotion(pStack).isFoil();
    }


    @Override
    public ItemStack getDefaultInstance(){
        return setPotion(new ItemStack(RegistryItems.MOD_POTION_ITEM.get()), RegistryItems.EMPTY_MOD_POTION.get());
    }

    @Override
    public void fillItemCategory(CreativeModeTab pGroup, NonNullList<ItemStack> pItems){
        for(ModPotion potion : RegistryItems.MOD_POTIONS_REGISTRY.get()) {
            if (potion.getPotion() != Potions.EMPTY) {
                pItems.add(setPotion(new ItemStack(this), potion));
            }
        }
    }

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        if(!(event.getTarget() instanceof Cow target))
            return;
        Player player = event.getPlayer();
        ItemStack itemstack = player.getItemInHand(event.getHand());
        if (itemstack.is(Items.GLASS_BOTTLE) && !target.isBaby()) {
            player.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
            ItemStack result = RegistryItems.MOD_POTION_ITEM.get().getDefaultInstance();
            player.setItemInHand(event.getHand(), result);
            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.sidedSuccess(event.getWorld().isClientSide));
        }
    }

//    protected NonNullList<ItemStack> getCreativeModeItemVarients(List<Potion> potions){
//        NonNullList<ItemStack> result = NonNullList.create();
//        for(Potion potion : potions){
//            result.add(setPotion(new ItemStack()))
//        }
//    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        Player player = pEntityLiving instanceof Player ? (Player)pEntityLiving : null;
        if (player instanceof ServerPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer)player, pStack);
        }

        if (!pLevel.isClientSide) {
            for(MobEffectInstance mobeffectinstance : getMobEffects(pStack)) {
                if (mobeffectinstance.getEffect().isInstantenous()) {
                    mobeffectinstance.getEffect().applyInstantenousEffect(player, player, pEntityLiving, mobeffectinstance.getAmplifier(), 1.0D);
                } else {
                    pEntityLiving.addEffect(new MobEffectInstance(mobeffectinstance));
                }
            }
        }

        if (player != null) {
            player.awardStat(Stats.ITEM_USED.get(this));
            if (!player.getAbilities().instabuild) {
                pStack.shrink(1);
            }
        }

        if (player == null || !player.getAbilities().instabuild) {
            if (pStack.isEmpty()) {
                return new ItemStack(Items.GLASS_BOTTLE);
            }

            if (player != null) {
                player.getInventory().add(new ItemStack(Items.GLASS_BOTTLE));
            }
        }

        pLevel.gameEvent(pEntityLiving, GameEvent.DRINKING_FINISH, pEntityLiving.eyeBlockPosition());
        return pStack;
    }

    @Override
    public String getDescriptionId(ItemStack pStack) {
        return getPotion(pStack).getName(this.getDescriptionId() + ".effect.");
    }

    public static ModPotion getPotion(ItemStack pStack) {
        return getPotion(pStack.getTag());
    }

    public static ModPotion getPotion(@Nullable CompoundTag pCompoundTag) {
        return pCompoundTag == null ? RegistryItems.EMPTY_MOD_POTION.get() : byName(pCompoundTag.getString("Potion"));
    }

    public static ModPotion byName(String pName) {
        return RegistryItems.MOD_POTIONS_REGISTRY.get().getValue((ResourceLocation.tryParse(pName)));
    }

    public static ItemStack setPotion(ItemStack pStack, ModPotion pPotion) {
        ResourceLocation resourcelocation = RegistryItems.MOD_POTIONS_REGISTRY.get().getKey(pPotion);
        pStack.getOrCreateTag().putString("Potion", resourcelocation.toString());
        return pStack;
    }

    public static List<MobEffectInstance> getMobEffects(ItemStack pStack) {
        return getAllEffects(pStack.getTag());
    }

    public static List<MobEffectInstance> getAllEffects(@Nullable CompoundTag pCompoundTag) {
        List<MobEffectInstance> list = Lists.newArrayList();
        list.addAll(getPotion(pCompoundTag).getPotion().getEffects());
        getCustomEffects(pCompoundTag, list);
        return list;
    }

    public static void getCustomEffects(@Nullable CompoundTag pCompoundTag, List<MobEffectInstance> pEffectList) {
        if (pCompoundTag != null && pCompoundTag.contains("CustomPotionEffects", 9)) {
            ListTag listtag = pCompoundTag.getList("CustomPotionEffects", 10);

            for(int i = 0; i < listtag.size(); ++i) {
                CompoundTag compoundtag = listtag.getCompound(i);
                MobEffectInstance mobeffectinstance = MobEffectInstance.load(compoundtag);
                if (mobeffectinstance != null) {
                    pEffectList.add(mobeffectinstance);
                }
            }
        }

    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        addPotionTooltip(pStack, pTooltip, 1.0F);
    }

    private static final Component NO_EFFECT = (new TranslatableComponent("effect.none")).withStyle(ChatFormatting.GRAY);


    public static void addPotionTooltip(ItemStack pStack, List<Component> pTooltips, float pDurationFactor) {
        List<MobEffectInstance> list = getMobEffects(pStack);
        List<Pair<Attribute, AttributeModifier>> list1 = Lists.newArrayList();
        if (list.isEmpty()) {
            pTooltips.add(NO_EFFECT);
        } else {
            for(MobEffectInstance mobeffectinstance : list) {
                MutableComponent mutablecomponent = new TranslatableComponent(mobeffectinstance.getDescriptionId());
                MobEffect mobeffect = mobeffectinstance.getEffect();
                Map<Attribute, AttributeModifier> map = mobeffect.getAttributeModifiers();
                if (!map.isEmpty()) {
                    for(Map.Entry<Attribute, AttributeModifier> entry : map.entrySet()) {
                        AttributeModifier attributemodifier = entry.getValue();
                        AttributeModifier attributemodifier1 = new AttributeModifier(attributemodifier.getName(), mobeffect.getAttributeModifierValue(mobeffectinstance.getAmplifier(), attributemodifier), attributemodifier.getOperation());
                        list1.add(new Pair<>(entry.getKey(), attributemodifier1));
                    }
                }

                if (mobeffectinstance.getAmplifier() > 0) {
                    mutablecomponent = new TranslatableComponent("potion.withAmplifier", mutablecomponent, new TranslatableComponent("potion.potency." + mobeffectinstance.getAmplifier()));
                }

                if (mobeffectinstance.getDuration() > 20) {
                    mutablecomponent = new TranslatableComponent("potion.withDuration", mutablecomponent, MobEffectUtil.formatDuration(mobeffectinstance, pDurationFactor));
                }

                pTooltips.add(mutablecomponent.withStyle(mobeffect.getCategory().getTooltipFormatting()));
            }
        }

        if (!list1.isEmpty()) {
            pTooltips.add(TextComponent.EMPTY);
            pTooltips.add((new TranslatableComponent("potion.whenDrank")).withStyle(ChatFormatting.DARK_PURPLE));

            for(Pair<Attribute, AttributeModifier> pair : list1) {
                AttributeModifier attributemodifier2 = pair.getSecond();
                double d0 = attributemodifier2.getAmount();
                double d1;
                if (attributemodifier2.getOperation() != AttributeModifier.Operation.MULTIPLY_BASE && attributemodifier2.getOperation() != AttributeModifier.Operation.MULTIPLY_TOTAL) {
                    d1 = attributemodifier2.getAmount();
                } else {
                    d1 = attributemodifier2.getAmount() * 100.0D;
                }

                if (d0 > 0.0D) {
                    pTooltips.add((new TranslatableComponent("attribute.modifier.plus." + attributemodifier2.getOperation().toValue(), ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(d1), new TranslatableComponent(pair.getFirst().getDescriptionId()))).withStyle(ChatFormatting.BLUE));
                } else if (d0 < 0.0D) {
                    d1 *= -1.0D;
                    pTooltips.add((new TranslatableComponent("attribute.modifier.take." + attributemodifier2.getOperation().toValue(), ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(d1), new TranslatableComponent(pair.getFirst().getDescriptionId()))).withStyle(ChatFormatting.RED));
                }
            }
        }

    }

    public static int getColor(ItemStack pStack) {
        CompoundTag compoundtag = pStack.getTag();
        if (compoundtag != null && compoundtag.contains("CustomPotionColor", 99)) {
            return compoundtag.getInt("CustomPotionColor");
        } else {
            Potion potion = getPotion(pStack).getPotion();
            if(RegistryItems.MILK_POTION.get().getPotion() == potion){
                return EffectsRegistry.PANACEA_EFFECT.get().getColor();
            }
//            if(RegistryItems.GOOEY_POTION.get().getPotion() == potion){
//                return Ef
//            }
            return potion == Potions.EMPTY ? 16253176 : getColor(getMobEffects(pStack));
        }
    }

    public static int getColor(Collection<MobEffectInstance> pEffects) {
        int i = 3694022;
        if (pEffects.isEmpty()) {
            return 3694022;
        } else {
            float f = 0.0F;
            float f1 = 0.0F;
            float f2 = 0.0F;
            int j = 0;

            for(MobEffectInstance mobeffectinstance : pEffects) {
                if (mobeffectinstance.isVisible()) {
                    int k = mobeffectinstance.getEffect().getColor();
                    int l = mobeffectinstance.getAmplifier() + 1;
                    f += (float)(l * (k >> 16 & 255)) / 255.0F;
                    f1 += (float)(l * (k >> 8 & 255)) / 255.0F;
                    f2 += (float)(l * (k >> 0 & 255)) / 255.0F;
                    j += l;
                }
            }

            if (j == 0) {
                return 0;
            } else {
                f = f / (float)j * 255.0F;
                f1 = f1 / (float)j * 255.0F;
                f2 = f2 / (float)j * 255.0F;
                return (int)f << 16 | (int)f1 << 8 | (int)f2;
            }
        }
    }
}
