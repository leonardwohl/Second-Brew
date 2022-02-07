package com.leonardwohl.sleeppotions.effects;

import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

public class LoveEffect extends InstantenousMobEffect {

    public LoveEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xffb3d7);
    }

    @Override
    public void applyInstantenousEffect(@Nullable Entity pSource, @Nullable Entity pIndirectSource, LivingEntity pLivingEntity, int pAmplifier, double pHealth) {
        if(!pLivingEntity.getLevel().isClientSide() && pLivingEntity.isAlive() && pLivingEntity instanceof Animal animal){
            animal.setInLove(pIndirectSource instanceof Player player ? player : null);
        }
    }
}
