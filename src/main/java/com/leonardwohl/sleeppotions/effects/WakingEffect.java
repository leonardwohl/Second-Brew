package com.leonardwohl.sleeppotions.effects;

import com.leonardwohl.sleeppotions.RegistryItems;
import com.leonardwohl.sleeppotions.effects.SleepingEffect;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class WakingEffect extends InstantenousMobEffect {
    public WakingEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xffb300);
    }
    @Override
    public void applyInstantenousEffect(@Nullable Entity pSource, @Nullable Entity pIndirectSource, LivingEntity pLivingEntity, int pAmplifier, double pHealth) {
        if(pLivingEntity.getLevel().isClientSide)
            return;
        if(pLivingEntity.hasEffect(EffectsRegistry.SLEEPING_EFFECT.get()))
            SleepingEffect.removeEffect(pLivingEntity);
        if(pLivingEntity.isSleeping())
            pLivingEntity.stopSleeping();
        if(pLivingEntity instanceof ServerPlayer player){
            Optional<Vec3> respawnPosition = Player.findRespawnPositionAndUseSpawnBlock(player.getLevel(), player.getRespawnPosition(), player.getRespawnAngle(), player.isRespawnForced(), true);
            respawnPosition.ifPresent(v3->player.teleportTo(v3.x, v3.y, v3.z));
        }
    }
    @SubscribeEvent
    public static void onPotionAdded(PotionEvent.PotionAddedEvent potionAddedEvent){
        if(EffectsRegistry.WAKING_EFFECT.get() != potionAddedEvent.getPotionEffect().getEffect()
                || potionAddedEvent.getEntityLiving().getLevel().isClientSide){
            return;
        }
        LivingEntity entity = potionAddedEvent.getEntityLiving();
        if(entity.getLevel() instanceof ServerLevel && entity instanceof ServerPlayer serverPlayer){
            entity.getServer().getPlayerList().respawn(serverPlayer, true);
        }
    }
}
