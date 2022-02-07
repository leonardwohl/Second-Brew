package com.leonardwohl.sleeppotions.effects;

import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

public class MilkEffect extends InstantenousMobEffect {
    public MilkEffect() {
        super(MobEffectCategory.NEUTRAL, 0xffffff);
    }
    @Override
    public void applyInstantenousEffect(@Nullable Entity pSource, @Nullable Entity pIndirectSource, LivingEntity pLivingEntity, int pAmplifier, double pHealth) {
        if (!pLivingEntity.getLevel().isClientSide) {
            pLivingEntity.removeEffect(MobEffects.POISON);
        }
    }
}
