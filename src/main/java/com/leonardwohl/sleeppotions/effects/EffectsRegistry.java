package com.leonardwohl.sleeppotions.effects;

import com.leonardwohl.sleeppotions.SecondBrewMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.block.BeaconBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EffectsRegistry {

    public static final int POTION_TICKS = 3600;
    public static final int LONG_POTION_TICKS = 9600;
    public static final int STRONG_POTION_TICKS = 1800;
    public static final int SLOWNESS_TICKS = 1800;
    public static final int LONG_SLOWNESS_TICKS = 4800;
    public static final int STRONG_SLOWNESS_TICKS = 400;
    public static final int POISON_TICKS = 900;
    public static final int LONG_POISON_TICKS = 1800;
    public static final int STRONG_POISON_TICKS = 432;


    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, SecondBrewMod.MOD_ID);
    public static final RegistryObject<MobEffect> SLEEPING_EFFECT = EFFECTS.register("sleeping", SleepingEffect::new);
    public static final RegistryObject<MobEffect> WAKING_EFFECT = EFFECTS.register("waking", WakingEffect::new);
    public static final RegistryObject<MobEffect> LOVE_EFFECT = EFFECTS.register("love", LoveEffect::new);
    public static final RegistryObject<MobEffect> GOOEY_EFFECT = EFFECTS.register("gooey", GooeyEffect::new);
    public static final RegistryObject<MobEffect> PANACEA_EFFECT = EFFECTS.register("panacea", PanaceaEffect::new);
    public static final RegistryObject<MobEffect> MAGIC_RESISTANCE_EFFECT = EFFECTS.register("magic_resistance", MagicResistanceEffect::new);
    public static final RegistryObject<MobEffect> PEACE_EFFECT = EFFECTS.register("peace", PeaceEffect::new);


    public static void register() {
        EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
