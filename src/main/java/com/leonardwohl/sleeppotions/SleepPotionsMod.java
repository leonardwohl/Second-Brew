package com.leonardwohl.sleeppotions;

import com.leonardwohl.sleeppotions.datagen.CustomDataGenerator;
import com.leonardwohl.sleeppotions.effects.EffectsRegistry;
import com.leonardwohl.sleeppotions.effects.SleepingEffect;
import com.leonardwohl.sleeppotions.effects.WakingEffect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("sleep_potions")
public class SleepPotionsMod
{
    public static final String MOD_ID = "sleep_potions";
    // Directly reference a log4j logger.
    private static final Logger log = LogManager.getLogger();

    public SleepPotionsMod() {
        MinecraftForge.EVENT_BUS.register(this);
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
        FMLJavaModLoadingContext.get().getModEventBus().register(CustomDataGenerator.class);
        FMLJavaModLoadingContext.get().getModEventBus().register(ItemRegistry.class);
        MinecraftForge.EVENT_BUS.register(SleepingEffect.class);
        MinecraftForge.EVENT_BUS.register(WakingEffect.class);
        MinecraftForge.EVENT_BUS.register(MilkBottleItem.class);
        MinecraftForge.EVENT_BUS.register(ItemRegistry.class);
        ItemRegistry.register();
        EffectsRegistry.register();
    }

    @SubscribeEvent
    public void setUpBrewingRecipes(FMLCommonSetupEvent event) {
        ItemRegistry.setUpBrewingRecipes(event);
    }

}
