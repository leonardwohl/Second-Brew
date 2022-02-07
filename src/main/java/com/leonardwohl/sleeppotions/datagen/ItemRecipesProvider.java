package com.leonardwohl.sleeppotions.datagen;

import com.leonardwohl.sleeppotions.RegistryItems;
import mezz.jei.plugins.vanilla.brewing.BrewingRecipeMaker;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

import java.util.function.Consumer;


public class ItemRecipesProvider extends RecipeProvider {
    public ItemRecipesProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        ShapedRecipeBuilder.shaped(RegistryItems.GOLDEN_FLEECE_ITEM.get())
                .pattern("ggg")
                .pattern("gwg")
                .pattern("ggg")
                .define('g', Tags.Items.NUGGETS_GOLD)
                .define('w', ItemTags.WOOL)
                .group("golden_fleece")
                .unlockedBy("has_gold_nugget", has(Items.GOLD_NUGGET))
                .save(pFinishedRecipeConsumer);
//        ShapelessRecipeBuilder.shapeless(RegistryItems.ROSE_PETALS.get(), 5).group("rose_petals")
//                .requires(Items.ROSE_BUSH).unlockedBy("has_rose_bush", has(Items.ROSE_BUSH))
//                .save(pFinishedRecipeConsumer);
        ShapelessRecipeBuilder.shapeless(Items.NETHER_WART, 1).group("nether_wart")
                .requires(Items.NETHER_WART_BLOCK).unlockedBy("has_wart_block", has(Items.NETHER_WART_BLOCK))
                .save(pFinishedRecipeConsumer);
    }
}
