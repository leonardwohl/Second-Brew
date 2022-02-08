package com.leonardwohl.sleeppotions.datagen;

import com.leonardwohl.sleeppotions.ItemRegistry;
import com.leonardwohl.sleeppotions.SleepPotionsMod;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class BlockTagsProvider extends net.minecraft.data.tags.BlockTagsProvider {
    public BlockTagsProvider(DataGenerator pGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, SleepPotionsMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(BlockTags.WOOL).add(ItemRegistry.GOLDEN_FLEECE.get());
    }
}
