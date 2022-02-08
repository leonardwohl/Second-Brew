package com.leonardwohl.sleeppotions.datagen;

import com.leonardwohl.sleeppotions.SecondBrewMod;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = SecondBrewMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CustomDataGenerator {
    @SubscribeEvent
    public static void gatherServerData(GatherDataEvent event){
        if(!event.includeServer())
            return;
        DataGenerator generator = event.getGenerator();
        generator.addProvider(new ItemRecipesProvider(generator));
        BlockTagsProvider blockTagsProvider = new BlockTagsProvider(generator, event.getExistingFileHelper());
        generator.addProvider(blockTagsProvider);
        generator.addProvider(new ItemTagsProvider(generator,blockTagsProvider, event.getExistingFileHelper()));


    }
    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        if(!event.includeClient())
            return;
        generator.addProvider(new LanguageProvider(generator,"en_us"));
        generator.addProvider(new BlockStatesProvider(generator, event.getExistingFileHelper()));
        generator.addProvider(new ItemModelsProvider(generator, event.getExistingFileHelper()));
        //generator.addProvider(new LootTablesProvider(generator));
    }
}
