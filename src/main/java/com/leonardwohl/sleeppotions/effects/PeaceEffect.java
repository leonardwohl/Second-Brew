package com.leonardwohl.sleeppotions.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PeaceEffect extends MobEffect {
    protected PeaceEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xffffff);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        //NO-OP
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return false;
    }

    @SubscribeEvent
    public static void checkSpawn(LivingSpawnEvent.CheckSpawn event){
        Entity entity = event.getEntity();
        if (!(entity instanceof Enemy))
            return;

        AABB aabb = new AABB(entity.getX() - 128, entity.getY() - 128, entity.getZ() - 128,
                entity.getX() + 128, entity.getY() + 128, entity.getZ() + 128);
        for (Player player : entity.level.players()) {
            if (player.hasEffect(EffectsRegistry.PEACE_EFFECT.get()) && player.getBoundingBox().intersects(aabb)) {
                event.setCanceled(true);
            }
        }
    }
}
