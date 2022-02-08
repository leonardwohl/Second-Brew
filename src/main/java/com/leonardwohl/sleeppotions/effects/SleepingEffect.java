package com.leonardwohl.sleeppotions.effects;

import com.google.common.collect.Multimaps;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.entity.player.SleepingLocationCheckEvent;
import net.minecraftforge.event.entity.player.SleepingTimeCheckEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

public class SleepingEffect extends MobEffect {

    private static final Logger log = LogManager.getLogger();

    protected SleepingEffect() {
        super(MobEffectCategory.NEUTRAL, 0x0c0063);
        super.addAttributeModifier(Attributes.MOVEMENT_SPEED, UUID.randomUUID().toString(), -1000, AttributeModifier.Operation.ADDITION);
    }

    @Override
    public void applyEffectTick(LivingEntity livingTarget, int p_76394_2_) {
        if(livingTarget.getLevel().isClientSide() || livingTarget.isSleeping() || !livingTarget.hasEffect(this))
            return;
        if(livingTarget instanceof Villager && livingTarget.getLevel().isDay())
            return;

        livingTarget.startSleeping(livingTarget.blockPosition());
        log.info("putting entity to sleep: {} {}", livingTarget.getName(), livingTarget.isSleeping());
        if(livingTarget instanceof Player)
            ObfuscationReflectionHelper.setPrivateValue(Player.class, (Player) livingTarget, 0, "sleepCounter");
        if(livingTarget instanceof ServerPlayer)
            ((ServerPlayer) livingTarget).getLevel().updateSleepingPlayerList();
    }

    @Override
    public boolean isDurationEffectTick(int p_76397_1_, int p_76397_2_) {
        int j = 20 >> p_76397_2_;
        if (j > 0) {
            return p_76397_1_ % j == 0;
        } else {
            return true;
        }
    }

//    @SubscribeEvent
//    public static void onLivingTick(LivingEvent.LivingUpdateEvent event){
//        if(event.getEntityLiving().hasEffect(RegistryItems.SLEEPING_EFFECT.get()) && event.getEntityLiving().isSleeping()) {
//            event.getEntityLiving().setDeltaMovement(event.getEntityLiving().getDeltaMovement().with(Direction.Axis.X, 0).with(Direction.Axis.Z, 0));
//        }
//    }

    @SubscribeEvent
    public static void onSleepingLocationCheckEvent(SleepingLocationCheckEvent event){
        boolean hasEffect = event.getEntityLiving().hasEffect(EffectsRegistry.SLEEPING_EFFECT.get());
        if(event.getEntityLiving() instanceof Player)
            log.info("player sleeping with effects : {}", event.getEntityLiving().getActiveEffects().toString());
        if(hasEffect)
            event.setResult(Event.Result.ALLOW);
    }
    @SubscribeEvent
    public static void onSleepingTimeCheckEvent(SleepingTimeCheckEvent event){
        boolean hasEffect = event.getEntityLiving().hasEffect(EffectsRegistry.SLEEPING_EFFECT.get());
        if(event.getEntityLiving() instanceof Player)
            log.info("player sleeping with effects : {}", event.getEntityLiving().getActiveEffects().toString());
        if(hasEffect)
            event.setResult(Event.Result.ALLOW);
    }
    @SubscribeEvent
    public static void onPlayerWakeUp(PlayerWakeUpEvent event){
        if(!event.getEntityLiving().hasEffect(EffectsRegistry.SLEEPING_EFFECT.get()))
            return;
        removeEffect(event.getEntityLiving());
    }
    @SubscribeEvent
    public static void onDamage(LivingDamageEvent event){
        if(event.getEntityLiving().hasEffect(EffectsRegistry.SLEEPING_EFFECT.get()))
            removeEffect(event.getEntityLiving());
    }

    public static void removeEffect(LivingEntity entity){
        MobEffectInstance effectInstance = entity.getEffect(EffectsRegistry.SLEEPING_EFFECT.get());
        entity.getActiveEffects().remove(effectInstance);
        entity.getAttributes().removeAttributeModifiers(Multimaps.forMap(EffectsRegistry.SLEEPING_EFFECT.get().getAttributeModifiers()));
    }
}
