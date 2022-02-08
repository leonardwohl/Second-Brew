package com.leonardwohl.sleeppotions;

import com.leonardwohl.sleeppotions.ItemRegistry;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MilkBottleItem extends Item {
    public MilkBottleItem(Properties pProperties) {
        super(pProperties);
    }
    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        if(!(event.getTarget() instanceof Cow target))
            return;
        Player player = event.getPlayer();
        ItemStack itemstack = player.getItemInHand(event.getHand());
        if (itemstack.is(Items.GLASS_BOTTLE) && !target.isBaby()) {
            player.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
            //ItemStack result = PotionUtils.setPotion(new ItemStack(Items.POTION), RegistryItems.MILK_POTION.get());//RegistryItems.MOD_POTION_ITEM.get().getDefaultInstance();
            ItemStack result = ItemRegistry.MILK_BOTTLE_ITEM.get().getDefaultInstance();
            player.setItemInHand(event.getHand(), result);
            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.sidedSuccess(event.getWorld().isClientSide));
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if (!pLevel.isClientSide)
            pLivingEntity.curePotionEffects(Items.MILK_BUCKET.getDefaultInstance());
        if (pLivingEntity instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, pStack);
            serverPlayer.awardStat(Stats.ITEM_USED.get(this));
        }
        if (pLivingEntity instanceof Player player && !(player).getAbilities().instabuild) {
            pStack.shrink(1);
        }
        return pStack.isEmpty() ? new ItemStack(Items.GLASS_BOTTLE) : pStack;
    }
    @Override
    public int getUseDuration(ItemStack pStack) {
        return 32;
    }
    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.DRINK;
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        return ItemUtils.startUsingInstantly(pLevel, pPlayer, pHand);
    }
}
