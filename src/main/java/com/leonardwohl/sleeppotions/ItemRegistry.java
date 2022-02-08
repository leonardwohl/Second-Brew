package com.leonardwohl.sleeppotions;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.leonardwohl.sleeppotions.effects.EffectsRegistry.*;

public class ItemRegistry {

    private static final Logger log = LogManager.getLogger();

    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, SecondBrewMod.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SecondBrewMod.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SecondBrewMod.MOD_ID);

    //Items
    public static final RegistryObject<Block> GOLDEN_FLEECE = BLOCKS.register("golden_fleece",
            ()-> new Block(BlockBehaviour.Properties.of(Material.WOOL, MaterialColor.GOLD).strength(.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Item> GOLDEN_FLEECE_ITEM = ITEMS.register("golden_fleece",
            ()-> new BlockItem(GOLDEN_FLEECE.get(), new Item.Properties().tab(CreativeModeTab.TAB_BREWING)));
    public static final RegistryObject<Item> MILK_BOTTLE_ITEM = ITEMS.register("milk_bottle", ()-> new MilkBottleItem(new Item.Properties().tab(CreativeModeTab.TAB_BREWING).stacksTo(1)) {});

    //Potions
    public static final PotionRegistryBuilder HUNGER_REGISTRY = new PotionRegistryBuilder(POTIONS, "hunger", ()->MobEffects.HUNGER)
            .withBasePotion().withLongPotion();
    public static final PotionRegistryBuilder SATURATION_REGISTRY = new PotionRegistryBuilder(POTIONS, "saturation", ()->MobEffects.SATURATION)
            .withBasePotion(8, 0).withLongPotion(10, 0).withExtraLongPotion(12, 0);
    public static final PotionRegistryBuilder MINING_FATIGUE_REGISTRY = new PotionRegistryBuilder(POTIONS, "fatigue", ()->MobEffects.DIG_SLOWDOWN)
            .withBasePotion().withLongPotion().withStrongPotion();
    public static final PotionRegistryBuilder HASTE_REGISTRY = new PotionRegistryBuilder(POTIONS, "haste", ()->MobEffects.DIG_SPEED)
            .withBasePotion().withLongPotion().withStrongPotion().withExtraLongPotion();
    public static final PotionRegistryBuilder RESISTANCE_REGISTRY = new PotionRegistryBuilder(POTIONS, "resistance", ()->MobEffects.DAMAGE_RESISTANCE)
            .withBasePotion().withLongPotion().withStrongPotion().withExtraLongPotion();
    public static final PotionRegistryBuilder WITHER_POTION_REGISTRY = new PotionRegistryBuilder(POTIONS, "wither", ()->MobEffects.WITHER)
            .withBasePotion(POISON_TICKS, 0).withStrongPotion(STRONG_POISON_TICKS, 1).withLongPotion(LONG_POISON_TICKS, 0);
    public static final PotionRegistryBuilder LEVITATION_REGISTRY = new PotionRegistryBuilder(POTIONS, "levitation", ()->MobEffects.LEVITATION)
        .withBasePotion(200, 0).withLongPotion(300, 0).withStrongPotion(20*5, 5);
    public static final PotionRegistryBuilder GLOWING_REGISTRY = new PotionRegistryBuilder(POTIONS, "glowing", ()->MobEffects.GLOWING)
            .withBasePotion().withLongPotion().withExtraLongPotion();
    public static final PotionRegistryBuilder BLINDNESS_REGISTRY = new PotionRegistryBuilder(POTIONS, "blindness", ()->MobEffects.BLINDNESS)
            .withBasePotion().withLongPotion();
    public static final PotionRegistryBuilder SLOW_FALLING_REGISTRY = new PotionRegistryBuilder(POTIONS, "slow_falling", ()->MobEffects.SLOW_FALLING)
            .withExtraLongPotion(LONG_POTION_TICKS, 0);
    public static final PotionRegistryBuilder SWIFTNESS_REGISTRY = new PotionRegistryBuilder(POTIONS, "swiftness", ()->MobEffects.MOVEMENT_SPEED)
            .withExtraLongPotion();
    public static final PotionRegistryBuilder NIGHT_VISION_REGISTRY = new PotionRegistryBuilder(POTIONS, "night_vision", ()->MobEffects.NIGHT_VISION)
            .withExtraLongPotion();
    public static final PotionRegistryBuilder INVISIBILITY_REGISTRY = new PotionRegistryBuilder(POTIONS, "invisibility", ()->MobEffects.INVISIBILITY)
            .withExtraLongPotion();
    public static final PotionRegistryBuilder LEAPING_REGISTRY = new PotionRegistryBuilder(POTIONS, "leaping", ()->MobEffects.JUMP)
            .withExtraLongPotion();
    public static final PotionRegistryBuilder FIRE_RESISTANCE_REGISTRY = new PotionRegistryBuilder(POTIONS, "fire_resistance", ()->MobEffects.FIRE_RESISTANCE)
            .withExtraLongPotion();
    public static final PotionRegistryBuilder REGENERATION_REGISTRY = new PotionRegistryBuilder(POTIONS, "regeneration", ()->MobEffects.REGENERATION)
            .withExtraLongPotion(POTION_TICKS, 0);
    public static final PotionRegistryBuilder WATER_BREATHING_REGISTRY = new PotionRegistryBuilder(POTIONS, "water_breathing", ()->MobEffects.WATER_BREATHING)
            .withExtraLongPotion();
    public static final PotionRegistryBuilder STRENGTH_REGISTRY = new PotionRegistryBuilder(POTIONS, "strength", ()->MobEffects.DAMAGE_BOOST)
            .withExtraLongPotion();


    public static final PotionRegistryBuilder SLEEPING_REGISTRY = new PotionRegistryBuilder(POTIONS, "sleep", SLEEPING_EFFECT::get)
            .withBasePotion(POISON_TICKS, 0).withLongPotion(LONG_POISON_TICKS, 0).withExtraLongPotion(POTION_TICKS, 0);
    public static final PotionRegistryBuilder WAKING_REGISTRY = new PotionRegistryBuilder(POTIONS, "waking", WAKING_EFFECT::get)
            .withBasePotion(-1, 0);
    public static final PotionRegistryBuilder LOVE_REGISTRY = new PotionRegistryBuilder(POTIONS, "love", LOVE_EFFECT::get)
            .withBasePotion(-1, 0);
    public static final PotionRegistryBuilder GOOEY_REGISTRY = new PotionRegistryBuilder(POTIONS, "gooey", GOOEY_EFFECT::get)
            .withBasePotion(-1, 0);
    public static final PotionRegistryBuilder PANACEA_REGISTRY = new PotionRegistryBuilder(POTIONS, "panacea", PANACEA_EFFECT::get)
            .withBasePotion(-1, 0);
    public static final PotionRegistryBuilder MAGIC_RESISTANCE_REGISTRY = new PotionRegistryBuilder(POTIONS, "magic_resistance", MAGIC_RESISTANCE_EFFECT::get)
            .withBasePotion().withLongPotion().withExtraLongPotion();
    public static final PotionRegistryBuilder PEACE_REGISTRY = new PotionRegistryBuilder(POTIONS, "peace", PEACE_EFFECT::get)
            .withBasePotion().withLongPotion().withExtraLongPotion();

    public static void register(){
        ItemRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ItemRegistry.POTIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    @SubscribeEvent
    public static void setUpBrewingRecipes(FMLCommonSetupEvent event) {
        log.info("Brewing Recipes setup");
        event.enqueueWork( () -> {
            HUNGER_REGISTRY.registerRecipes(Items.ROTTEN_FLESH)
                    .addCorruptedRecipes(SATURATION_REGISTRY.getBasePotion(),
                            SATURATION_REGISTRY.getLongPotion(),
                            null, null);
            SATURATION_REGISTRY.registerRecipes(null);
            MINING_FATIGUE_REGISTRY.registerRecipes(Items.PRISMARINE_CRYSTALS)
                    .addCorruptedRecipes(HASTE_REGISTRY.getBasePotion(),
                            HASTE_REGISTRY.getLongPotion(),
                            HASTE_REGISTRY.getStrongPotion(),
                            null);
            HASTE_REGISTRY.registerRecipes(null);
            GLOWING_REGISTRY.registerRecipes(Items.GLOW_INK_SAC)
                    .addCorruptedRecipes(BLINDNESS_REGISTRY.getBasePotion(),
                            BLINDNESS_REGISTRY.getLongPotion(),
                            null,
                            BLINDNESS_REGISTRY.getExtraLongPotion());
            BLINDNESS_REGISTRY.registerRecipes(Items.INK_SAC);
            WITHER_POTION_REGISTRY.registerRecipes(Items.WITHER_ROSE);

            PotionRegistryBuilder.addCorruptedRecipe(Potions.SLOW_FALLING, LEVITATION_REGISTRY.getBasePotion());
            PotionRegistryBuilder.addCorruptedRecipe(Potions.LONG_SLOW_FALLING, LEVITATION_REGISTRY.getLongPotion());
            PotionRegistryBuilder.addCorruptedRecipe(SLOW_FALLING_REGISTRY.getExtraLongPotion(), LEVITATION_REGISTRY.getExtraLongPotion());
            LEVITATION_REGISTRY.registerRecipes(null);

            PotionRegistryBuilder.addExtraLongRecipe(Potions.LONG_SLOW_FALLING, SLOW_FALLING_REGISTRY.getExtraLongPotion());
            PotionRegistryBuilder.addExtraLongRecipe(Potions.LONG_SWIFTNESS, SWIFTNESS_REGISTRY.getExtraLongPotion());
            PotionRegistryBuilder.addExtraLongRecipe(Potions.LONG_NIGHT_VISION, NIGHT_VISION_REGISTRY.getExtraLongPotion());
            PotionRegistryBuilder.addExtraLongRecipe(Potions.LONG_LEAPING, LEAPING_REGISTRY.getExtraLongPotion());
            PotionRegistryBuilder.addExtraLongRecipe(Potions.LONG_INVISIBILITY, INVISIBILITY_REGISTRY.getExtraLongPotion());
            PotionRegistryBuilder.addExtraLongRecipe(Potions.LONG_FIRE_RESISTANCE, FIRE_RESISTANCE_REGISTRY.getExtraLongPotion());
            PotionRegistryBuilder.addExtraLongRecipe(Potions.LONG_WATER_BREATHING, WATER_BREATHING_REGISTRY.getExtraLongPotion());
            PotionRegistryBuilder.addExtraLongRecipe(Potions.LONG_REGENERATION, REGENERATION_REGISTRY.getExtraLongPotion());
            PotionRegistryBuilder.addExtraLongRecipe(Potions.LONG_STRENGTH, STRENGTH_REGISTRY.getExtraLongPotion());

            SLEEPING_REGISTRY.registerRecipes(GOLDEN_FLEECE_ITEM.get());
            WAKING_REGISTRY.registerRecipes(SLEEPING_REGISTRY.getBasePotion(), Items.FERMENTED_SPIDER_EYE);

            GOOEY_REGISTRY.registerRecipes(Items.HONEY_BOTTLE);
            LOVE_REGISTRY.registerRecipes(GOOEY_REGISTRY.getBasePotion(), Items.MILK_BUCKET);
            PEACE_REGISTRY.registerRecipes(GOOEY_REGISTRY.getBasePotion(), Items.DANDELION);
            PANACEA_REGISTRY.registerRecipes(MILK_BOTTLE_ITEM.get().getDefaultInstance(), Items.NETHER_WART);
            MAGIC_RESISTANCE_REGISTRY.registerRecipes(PANACEA_REGISTRY.getBasePotion(), Items.AXOLOTL_BUCKET);
        } );
    }

}
