package com.leonardwohl.sleeppotions.datagen;

import com.leonardwohl.sleeppotions.RegistryItems;
import com.leonardwohl.sleeppotions.SleepPotionsMod;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class ItemTagsProvider extends net.minecraft.data.tags.ItemTagsProvider {

    public ItemTagsProvider(DataGenerator generator, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, blockTagsProvider, SleepPotionsMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        super.addTags();
        tag(ItemTags.WOOL).add(RegistryItems.GOLDEN_FLEECE_ITEM.get());
        tag(ItemTags.PIGLIN_LOVED).add(RegistryItems.GOLDEN_FLEECE_ITEM.get());
    }
}