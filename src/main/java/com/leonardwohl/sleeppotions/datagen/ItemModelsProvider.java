package com.leonardwohl.sleeppotions.datagen;

import com.leonardwohl.sleeppotions.ItemRegistry;
import com.leonardwohl.sleeppotions.SecondBrewMod;
import com.leonardwohl.sleeppotions.effects.EffectsRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModelsProvider extends ItemModelProvider {

    public ItemModelsProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, SecondBrewMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(ItemRegistry.GOLDEN_FLEECE_ITEM.get().getRegistryName().getPath(),
                modLoc("block/golden_fleece"));

//        super.getBuilder(RegistryItems.MOD_POTION_ITEM.get().getRegistryName().getPath())
//        .parent(new ModelFile.ExistingModelFile(mcLoc("item/generated"),existingFileHelper))
//        .texture("layer0", mcLoc("item/potion_overlay"))
//        .texture("layer1", mcLoc("item/potion"));
//
//        super.getBuilder(RegistryItems.MOD_POTION_SPLASH.get().getRegistryName().getPath())
//                .parent(new ModelFile.ExistingModelFile(mcLoc("item/generated"),existingFileHelper))
//                .texture("layer0", mcLoc("item/potion_overlay"))
//                .texture("layer1", mcLoc("item/splash_potion"));
//        super.getBuilder(RegistryItems.MOD_POTION_LINGERING.get().getRegistryName().getPath())
//                .parent(new ModelFile.ExistingModelFile(mcLoc("item/generated"),existingFileHelper))
//                .texture("layer0", mcLoc("item/potion_overlay"))
//                .texture("layer1", mcLoc("item/lingering_potion"));
//        singleTexture(RegistryItems.HONEY_POTION_ITEM.get().getRegistryName().getPath(),
//                mcLoc("item/generated"),
//                "layer0", mcLoc("item/honey_bottle"));
//        super.getBuilder(RegistryItems.HONEY_POTION_ITEM.get().getRegistryName().getPath())
//                .parent(new ModelFile.ExistingModelFile(mcLoc("item/generated"),existingFileHelper))
//                .texture("layer0", mcLoc("item/potion_overlay"))
//                .texture("layer1", mcLoc("item/potion"));
//
        super.getBuilder(EffectsRegistry.PEACE_EFFECT.get().getRegistryName().getPath())
                .texture("layer0", mcLoc("mob_effect/regeneration"));

        super.getBuilder(ItemRegistry.MILK_BOTTLE_ITEM.get().getRegistryName().getPath())
                .parent(new ModelFile.ExistingModelFile(mcLoc("item/generated"),existingFileHelper))
                .texture("layer0", mcLoc("item/potion_overlay"))
                .texture("layer1", mcLoc("item/potion"));
//        singleTexture(RegistryItems.MILK_POTION_ITEM.get().getRegistryName().getPath(),
//                mcLoc("item/generated"),
//                "layer0", modLoc("item/milk_bottle"));
    }
}
