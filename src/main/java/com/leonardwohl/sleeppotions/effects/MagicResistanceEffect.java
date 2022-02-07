package com.leonardwohl.sleeppotions.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
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
        if(event.getSource().isMagic()){
            event.setAmount(.5F);
        }
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
