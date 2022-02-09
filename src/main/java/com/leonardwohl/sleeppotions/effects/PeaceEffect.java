package com.leonardwohl.sleeppotions.effects;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.client.EffectRenderer;
import net.minecraftforge.client.RenderProperties;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.function.Consumer;

public class PeaceEffect extends MobEffect {
    protected PeaceEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xffff00);
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
                event.setResult(Event.Result.DENY);
            }
        }
    }

}
