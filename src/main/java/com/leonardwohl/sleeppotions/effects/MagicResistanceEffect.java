package com.leonardwohl.sleeppotions.effects;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.damagesource.CombatRules;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.Nullable;

public class MagicResistanceEffect extends MobEffect {
    protected MagicResistanceEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xe5c1ff);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (!pLivingEntity.getLevel().isClientSide) {
            pLivingEntity.getActiveEffects().stream().map(MobEffectInstance::getEffect)
                    .filter(e->!e.isBeneficial()).forEach(pLivingEntity::removeEffect);
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

    @SubscribeEvent
    public static void onLivingHurtEvent(LivingHurtEvent event){
        if (!event.getSource().isMagic() ||
                !event.getEntityLiving().hasEffect(EffectsRegistry.MAGIC_RESISTANCE_EFFECT.get())) {
            return;
        }
        float damage = event.getAmount();
        if (event.getEntityLiving() instanceof ServerPlayer serverPlayer) {
            serverPlayer.awardStat(Stats.CUSTOM.get(Stats.DAMAGE_RESISTED), Math.round(damage * 10.0F));
        } else if (event.getSource().getEntity() instanceof ServerPlayer serverPlayer) {
            serverPlayer.awardStat(Stats.CUSTOM.get(Stats.DAMAGE_DEALT_RESISTED), Math.round(damage * 10.0F));
        }
        event.setAmount(0);
        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void canBeAffected(PotionEvent.PotionApplicableEvent event){
        boolean hasEffect = event.getEntity() instanceof LivingEntity livingEntity
                && livingEntity.hasEffect(EffectsRegistry.MAGIC_RESISTANCE_EFFECT.get());
        boolean isBeneficial = event.getPotionEffect().getEffect().isBeneficial();
        if(hasEffect && !isBeneficial)
            event.setResult(Event.Result.DENY);
    }
}
