package com.leonardwohl.sleeppotions.effects;

import net.minecraft.world.effect.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

public class PanaceaEffect extends InstantenousMobEffect {
    protected PanaceaEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xffffff);
    }

    @Override
    public void applyInstantenousEffect(@Nullable Entity pSource, @Nullable Entity pIndirectSource, LivingEntity pLivingEntity, int pAmplifier, double pHealth) {
        if (!pLivingEntity.getLevel().isClientSide) {
            pLivingEntity.getActiveEffects().stream().map(MobEffectInstance::getEffect).filter(e->!e.isBeneficial()).forEach(pLivingEntity::removeEffect);
        }
    }
}
