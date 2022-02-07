package com.leonardwohl.sleeppotions.effects;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.client.EffectRenderer;
import net.minecraftforge.client.RenderProperties;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class GooeyEffect extends InstantenousMobEffect {

    protected GooeyEffect() {
        super(MobEffectCategory.NEUTRAL, 0xffa200);
    }

    @Override
    public void applyInstantenousEffect(@Nullable Entity pSource, @Nullable Entity pIndirectSource, LivingEntity pLivingEntity, int pAmplifier, double pHealth) {
        if (!pLivingEntity.getLevel().isClientSide) {
            pLivingEntity.removeEffect(MobEffects.POISON);
        }
    }
}
