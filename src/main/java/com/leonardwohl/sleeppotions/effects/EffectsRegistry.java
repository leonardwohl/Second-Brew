package com.leonardwohl.sleeppotions.effects;

import com.leonardwohl.sleeppotions.RegistryItems;
import com.leonardwohl.sleeppotions.SleepPotionsMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EffectsRegistry {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, SleepPotionsMod.MOD_ID);
    public static final RegistryObject<MobEffect> SLEEPING_EFFECT = EFFECTS.register("sleeping", SleepingEffect::new);
    public static final RegistryObject<MobEffect> WAKING_EFFECT = EFFECTS.register("waking", WakingEffect::new);
    public static final RegistryObject<MobEffect> LOVE_EFFECT = EFFECTS.register("love", LoveEffect::new);
    public static final RegistryObject<MobEffect> GOOEY_EFFECT = EFFECTS.register("gooey", GooeyEffect::new);
    public static final RegistryObject<MobEffect> MILK_EFFECT = EFFECTS.register("milk", MilkEffect::new);
    public static final RegistryObject<MobEffect> PANACEA_EFFECT = EFFECTS.register("panacea", PanaceaEffect::new);
    public static final RegistryObject<MobEffect> MAGIC_RESISTANCE_EFFECT = EFFECTS.register("magic_resistance", MagicResistanceEffect::new);
    public static final RegistryObject<MobEffect> PEACE_EFFECT = EFFECTS.register("peace", PeaceEffect::new);


    public static void register() {
        EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
