package com.leonardwohl.sleeppotions.datagen;

import com.leonardwohl.sleeppotions.ItemRegistry;
import com.leonardwohl.sleeppotions.SecondBrewMod;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStatesProvider extends BlockStateProvider {
    public BlockStatesProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, SecondBrewMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ItemRegistry.GOLDEN_FLEECE.get());
    }
}
