package com.leonardwohl.sleeppotions.datagen;

import com.leonardwohl.sleeppotions.PotionRegistryBuilder;
import com.leonardwohl.sleeppotions.SecondBrewMod;
import com.leonardwohl.sleeppotions.effects.EffectsRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.registries.RegistryObject;

import static com.leonardwohl.sleeppotions.ItemRegistry.*;

public class LanguageProvider extends net.minecraftforge.common.data.LanguageProvider {
    public LanguageProvider(DataGenerator gen, String locale) {
        super(gen, SecondBrewMod.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        add(GOLDEN_FLEECE_ITEM.get(), "Golden Fleece");
        add(MILK_BOTTLE_ITEM.get(), "Milk Bottle");

        add(EffectsRegistry.SLEEPING_EFFECT.get(), "Sleeping");
        add(EffectsRegistry.WAKING_EFFECT.get(), "Waking");
        add(EffectsRegistry.LOVE_EFFECT.get(), "Love");
        add(EffectsRegistry.PEACE_EFFECT.get(), "Peace");
        add(EffectsRegistry.PANACEA_EFFECT.get(), "Panacea");
        add(EffectsRegistry.MAGIC_RESISTANCE_EFFECT.get(), "Magic Resistance");
        add(EffectsRegistry.GOOEY_EFFECT.get(), "Poison Cure");

        registerPotionsLanguage(WITHER_POTION_REGISTRY, "Wither");
        registerPotionsLanguage(HUNGER_REGISTRY, "Hunger");
        registerPotionsLanguage(SATURATION_REGISTRY, "Filling");
        registerPotionsLanguage(MINING_FATIGUE_REGISTRY, "Fatigue");
        registerPotionsLanguage(HASTE_REGISTRY, "Haste");
        registerPotionsLanguage(RESISTANCE_REGISTRY, "Resistance");
        registerPotionsLanguage(LEVITATION_REGISTRY, "Levitation");
        registerPotionsLanguage(GLOWING_REGISTRY, "Glowing");
        registerPotionsLanguage(BLINDNESS_REGISTRY, "Blindness");
        registerPotionsLanguage( SLOW_FALLING_REGISTRY, "Slow Falling");
        registerPotionsLanguage(SWIFTNESS_REGISTRY, "Swiftness");
        registerPotionsLanguage(NIGHT_VISION_REGISTRY, "Night Vision");
        registerPotionsLanguage(LEAPING_REGISTRY, "Leaping");
        registerPotionsLanguage(INVISIBILITY_REGISTRY, "Invisibility");
        registerPotionsLanguage(FIRE_RESISTANCE_REGISTRY, "Fire Resistance");
        registerPotionsLanguage(WATER_BREATHING_REGISTRY, "Water Breathing");
        registerPotionsLanguage(REGENERATION_REGISTRY, "Regeneration");
        registerPotionsLanguage(STRENGTH_REGISTRY, "Strength");

        add(PotionUtils.setPotion(new ItemStack(Items.POTION), GOOEY_REGISTRY.getBasePotion()), "Gooey Potion");
        add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), GOOEY_REGISTRY.getBasePotion()), "Gooey Splash Potion");
        add(PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), GOOEY_REGISTRY.getBasePotion()), "Gooey Lingering Potion");
        add(PotionUtils.setPotion(new ItemStack(Items.TIPPED_ARROW), GOOEY_REGISTRY.getBasePotion()), "Gooey Arrow");


        registerPotionsLanguage(SLEEPING_REGISTRY, "Sleeping");
        registerPotionsLanguage(WAKING_REGISTRY, "Waking");
        registerPotionsLanguage(LOVE_REGISTRY, "Love");
        registerPotionsLanguage(PEACE_REGISTRY, "Peace");
        registerPotionsLanguage(PANACEA_REGISTRY, "Panacea");
        registerPotionsLanguage(MAGIC_RESISTANCE_REGISTRY, "Magic Resistance");

    }


    private void registerPotionsLanguage(PotionRegistryBuilder potionRegistry, String displayKeyword){
        addPotionVariants(potionRegistry.getBasePotion(), displayKeyword);
        addPotionVariants(potionRegistry.getLongPotion(), displayKeyword);
        addPotionVariants(potionRegistry.getExtraLongPotion(), displayKeyword);
        addPotionVariants(potionRegistry.getStrongPotion(), displayKeyword);
    }

    private void addPotionVariants(Potion potion, String displayKeyword){
        if (potion == null)
            return;
        add(PotionUtils.setPotion(new ItemStack(Items.POTION), potion), "Potion of "+displayKeyword);
        add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), potion), "Splash Potion of "+displayKeyword);
        add(PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), potion), "Lingering Potion of "+displayKeyword);
        add(PotionUtils.setPotion(new ItemStack(Items.TIPPED_ARROW), potion), "Arrow of "+displayKeyword);
    }

    private void addPotionVariants(RegistryObject<Potion> potion, String displayKeyword){
        add(PotionUtils.setPotion(new ItemStack(Items.POTION), potion.get()), "Potion of "+displayKeyword);
        add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), potion.get()), "Splash Potion of "+displayKeyword);
        add(PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), potion.get()), "Lingering Potion of "+displayKeyword);
        add(PotionUtils.setPotion(new ItemStack(Items.TIPPED_ARROW), potion.get()), "Arrow of "+displayKeyword);
    }
}
