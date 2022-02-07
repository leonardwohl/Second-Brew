package com.leonardwohl.sleeppotions.datagen;

import com.leonardwohl.sleeppotions.SleepPotionsMod;
import com.leonardwohl.sleeppotions.effects.EffectsRegistry;
import com.leonardwohl.sleeppotions.potions.ModPotionItem;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.registries.RegistryObject;

import static com.leonardwohl.sleeppotions.RegistryItems.*;

public class LanguageProvider extends net.minecraftforge.common.data.LanguageProvider {
    public LanguageProvider(DataGenerator gen, String locale) {
        super(gen, SleepPotionsMod.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        add(GOLDEN_FLEECE_ITEM.get(), "Golden Fleece");

        add(EffectsRegistry.SLEEPING_EFFECT.get(), "Sleeping");
        add(EffectsRegistry.WAKING_EFFECT.get(), "Waking");
        add(EffectsRegistry.LOVE_EFFECT.get(), "Love");
        add(EffectsRegistry.GOOEY_EFFECT.get(), "No Effects");

        add(ModPotionItem.setPotion(new ItemStack(Items.POTION), GOOEY_POTION.get()), "Gooey Potion");
        add(ModPotionItem.setPotion(new ItemStack(Items.SPLASH_POTION), GOOEY_POTION.get()), "Gooey Splash Potion");
        add(ModPotionItem.setPotion(new ItemStack(Items.LINGERING_POTION), GOOEY_POTION.get()), "Gooey Lingering Potion");
        add(ModPotionItem.setPotion(new ItemStack(Items.TIPPED_ARROW), GOOEY_POTION.get()), "Gooey Arrow");


        addPotionVariants(SLEEPING_POTION, "Sleeping");
        addPotionVariants(SLEEPING_POTION_LONG, "Sleeping");
        addPotionVariants(WAKING_POTION, "Waking");
        //addPotionVariants(LOVE_POTION, "Love");

        addPotionVariants(HUNGER_POTION, "Hunger");
        addPotionVariants(HUNGER_POTION_LONG, "Hunger");
        addPotionVariants(SATURATION_POTION, "Filling");
        addPotionVariants(SATURATION_POTION_LONG, "Filling");
        addPotionVariants(MINING_FATIGUE_POTION, "Fatigue");
        addPotionVariants(MINING_FATIGUE_POTION_LONG, "Fatigue");
        addPotionVariants(MINING_FATIGUE_POTION_STRONG, "Fatigue");
        addPotionVariants(HASTE_POTION, "Haste");
        addPotionVariants(HASTE_POTION_LONG, "Haste");
        addPotionVariants(HASTE_POTION_STRONG, "Haste");
        addPotionVariants(RESISTANCE_POTION, "Resistance");
        addPotionVariants(RESISTANCE_POTION_LONG, "Resistance");
        addPotionVariants(RESISTANCE_POTION_STRONG, "Resistance");
        addPotionVariants(LEVITATION_POTION, "Levitation");
        addPotionVariants(LEVITATION_POTION_LONG, "Levitation");
        addPotionVariants(LEVITATION_POTION_STRONG, "Levitation");
        addPotionVariants(GLOWING_POTION, "Glowing");
        addPotionVariants(GLOWING_POTION_LONG, "Glowing");
        addPotionVariants(BLINDNESS_POTION, "Blindness");
        addPotionVariants(BLINDNESS_POTION_LONG, "Blindness");
    }


    private void addPotionVariants(RegistryObject<Potion> potion, String displayKeyword){
        add(PotionUtils.setPotion(new ItemStack(Items.POTION), potion.get()), "Potion of "+displayKeyword);
        add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), potion.get()), "Splash Potion of "+displayKeyword);
        add(PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), potion.get()), "Lingering Potion of "+displayKeyword);
        add(PotionUtils.setPotion(new ItemStack(Items.TIPPED_ARROW), potion.get()), "Arrow of "+displayKeyword);
    }
}
