package com.leonardwohl.sleeppotions;

import com.leonardwohl.sleeppotions.potions.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

import static com.leonardwohl.sleeppotions.effects.EffectsRegistry.*;

public class RegistryItems {

    private static final Logger log = LogManager.getLogger();

    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, SleepPotionsMod.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SleepPotionsMod.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SleepPotionsMod.MOD_ID);

    public static final DeferredRegister<ModPotion> MOD_POTIONS = DeferredRegister.create(ModPotion.class, SleepPotionsMod.MOD_ID);
    public static final Supplier<IForgeRegistry<ModPotion>> MOD_POTIONS_REGISTRY = MOD_POTIONS.makeRegistry("mod_potions", RegistryBuilder::new);

    //Items
    public static final RegistryObject<Block> GOLDEN_FLEECE = BLOCKS.register("golden_fleece",
            ()-> new Block(BlockBehaviour.Properties.of(Material.WOOL, MaterialColor.GOLD).strength(.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Item> GOLDEN_FLEECE_ITEM = ITEMS.register("golden_fleece",
            ()-> new BlockItem(GOLDEN_FLEECE.get(), new Item.Properties().tab(CreativeModeTab.TAB_BREWING)));
    public static final RegistryObject<Item> MOD_POTION_ITEM = ITEMS.register("mod_potion", ()-> new ModPotionItem(new Item.Properties().tab(CreativeModeTab.TAB_BREWING).stacksTo(1)) {
    });
    public static final RegistryObject<Item> MOD_POTION_SPLASH = ITEMS.register("mod_potion_splash", () -> new SplashModPotionItem(new Item.Properties().tab(CreativeModeTab.TAB_BREWING).stacksTo(1)));
    public static final RegistryObject<Item> MOD_POTION_LINGERING = ITEMS.register("mod_potion_lingering", ()->new LingeringModPotionItem(new Item.Properties().tab(CreativeModeTab.TAB_BREWING).stacksTo(1)));

    //Potions
    public static final RegistryObject<Potion> SLEEPING_POTION = POTIONS.register("sleep", () -> new Potion(new MobEffectInstance(SLEEPING_EFFECT.get(), 20*30)));
    public static final RegistryObject<Potion> SLEEPING_POTION_LONG = POTIONS.register("long_sleep", () -> new Potion(new MobEffectInstance(SLEEPING_EFFECT.get(), 20*60)));

    public static final RegistryObject<Potion> WAKING_POTION = POTIONS.register("waking", () -> new Potion(new MobEffectInstance(WAKING_EFFECT.get())));

    public static final RegistryObject<Potion> HUNGER_POTION = POTIONS.register("hunger", () -> new Potion(new MobEffectInstance(MobEffects.HUNGER, 20*60*3)));
    public static final RegistryObject<Potion> HUNGER_POTION_LONG = POTIONS.register("long_hunger", () -> new Potion(new MobEffectInstance(MobEffects.HUNGER, 20*60*8)));

    public static final RegistryObject<Potion> SATURATION_POTION = POTIONS.register("saturation", () -> new Potion(new MobEffectInstance(MobEffects.SATURATION, 8)));
    public static final RegistryObject<Potion> SATURATION_POTION_LONG = POTIONS.register("long_saturation", () -> new Potion(new MobEffectInstance(MobEffects.SATURATION, 10)));

    public static final RegistryObject<Potion> MINING_FATIGUE_POTION = POTIONS.register("fatigue", () -> new Potion(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 20*60*3)));
    public static final RegistryObject<Potion> MINING_FATIGUE_POTION_LONG = POTIONS.register("long_fatigue", () -> new Potion(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 20*60*8)));
    public static final RegistryObject<Potion> MINING_FATIGUE_POTION_STRONG = POTIONS.register("strong_fatigue", () -> new Potion(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 20*30*3, 1)));

    public static final RegistryObject<Potion> HASTE_POTION = POTIONS.register("haste", () -> new Potion(new MobEffectInstance(MobEffects.DIG_SPEED, 20*60*3)));
    public static final RegistryObject<Potion> HASTE_POTION_LONG = POTIONS.register("long_haste", () -> new Potion(new MobEffectInstance(MobEffects.DIG_SPEED, 20*60*8)));
    public static final RegistryObject<Potion> HASTE_POTION_STRONG = POTIONS.register("strong_haste", () -> new Potion(new MobEffectInstance(MobEffects.DIG_SPEED, 20*30*3, 1)));

    public static final RegistryObject<Potion> RESISTANCE_POTION = POTIONS.register("resistance", () -> new Potion(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20*60*3)));
    public static final RegistryObject<Potion> RESISTANCE_POTION_LONG = POTIONS.register("long_resistance", () -> new Potion(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20*60*8)));
    public static final RegistryObject<Potion> RESISTANCE_POTION_STRONG = POTIONS.register("strong_resistance", () -> new Potion(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20*30*3, 1)));

    public static final RegistryObject<Potion> LEVITATION_POTION = POTIONS.register("levitation", () -> new Potion(new MobEffectInstance(MobEffects.LEVITATION, 20*10)));
    public static final RegistryObject<Potion> LEVITATION_POTION_LONG = POTIONS.register("long_levitation", () -> new Potion(new MobEffectInstance(MobEffects.LEVITATION, 20*20)));
    public static final RegistryObject<Potion> LEVITATION_POTION_STRONG = POTIONS.register("strong_levitation", () -> new Potion(new MobEffectInstance(MobEffects.LEVITATION, 20*5, 5)));

    public static final RegistryObject<Potion> GLOWING_POTION = POTIONS.register("glowing", () -> new Potion(new MobEffectInstance(MobEffects.GLOWING, 20*60*3)));
    public static final RegistryObject<Potion> GLOWING_POTION_LONG = POTIONS.register("long_glowing", () -> new Potion(new MobEffectInstance(MobEffects.GLOWING, 20*60*8)));

    public static final RegistryObject<Potion> BLINDNESS_POTION = POTIONS.register("blindness", () -> new Potion(new MobEffectInstance(MobEffects.BLINDNESS, 20*45)));
    public static final RegistryObject<Potion> BLINDNESS_POTION_LONG = POTIONS.register("long_blindness", () -> new Potion(new MobEffectInstance(MobEffects.BLINDNESS, 20*90)));

    public static final RegistryObject<ModPotion> EMPTY_MOD_POTION = MOD_POTIONS.register("empty", ()->new ModPotion(Potions.EMPTY));
    public static final RegistryObject<ModPotion> LOVE_POTION = MOD_POTIONS.register("love_potion", ()->new ModPotion(new Potion(new MobEffectInstance(LOVE_EFFECT.get()))).foil(true));
    public static final RegistryObject<ModPotion> GOOEY_POTION = MOD_POTIONS.register("gooey_potion", ()->new ModPotion(new Potion(new MobEffectInstance(GOOEY_EFFECT.get()))));
    public static final RegistryObject<ModPotion> MILK_POTION = MOD_POTIONS.register("milk_potion", ()->new ModPotion(new Potion(new MobEffectInstance(MILK_EFFECT.get()))));
    public static final RegistryObject<ModPotion> PANACEA_POTION = MOD_POTIONS.register("panacea_potion", ()->new ModPotion(new Potion(new MobEffectInstance(PANACEA_EFFECT.get()))).foil(true));
    public static final RegistryObject<ModPotion> MAGIC_RESISTANCE_POTION = MOD_POTIONS.register("magic_resistance_potion", ()->new ModPotion(new Potion(new MobEffectInstance(MAGIC_RESISTANCE_EFFECT.get(), 20*180))).foil(true));
    public static final RegistryObject<ModPotion> PEACE_POTION = MOD_POTIONS.register("peace_potion", ()->new ModPotion(new Potion(new MobEffectInstance(PEACE_EFFECT.get(), 20*180))).foil(true));


    public static void register(){
        RegistryItems.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegistryItems.POTIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegistryItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegistryItems.MOD_POTIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    @SubscribeEvent
    public static void setUpBrewingRecipes(FMLCommonSetupEvent event) {
        log.info("Brewing Recipes setup");
        event.enqueueWork( () -> {

            Ingredient honeyBottle = Ingredient.of(Items.HONEY_BOTTLE);
            Ingredient wart = Ingredient.of(new ItemStack(Items.NETHER_WART));
            ItemStack gooeyResult = ModPotionItem.setPotion(new ItemStack(MOD_POTION_ITEM.get()), GOOEY_POTION.get());
            BrewingRecipeRegistry.addRecipe(honeyBottle, wart, gooeyResult);

            Ingredient gooeyPotion = Ingredient.of(ModPotionItem.setPotion(new ItemStack(MOD_POTION_ITEM.get()), GOOEY_POTION.get()));
            Ingredient rose = Ingredient.of(new ItemStack(Items.MILK_BUCKET));
            ItemStack loveResult = ModPotionItem.setPotion(new ItemStack(MOD_POTION_ITEM.get()), LOVE_POTION.get());
            BrewingRecipeRegistry.addRecipe(gooeyPotion, rose, loveResult);

            Ingredient milkBottle = Ingredient.of(MOD_POTION_ITEM.get());
            ItemStack panaceaResult = ModPotionItem.setPotion(new ItemStack(MOD_POTION_ITEM.get()), PANACEA_POTION.get());
            BrewingRecipeRegistry.addRecipe(milkBottle, wart, panaceaResult);

            addBaseRecipe(GOLDEN_FLEECE_ITEM.get(), SLEEPING_POTION.get());
            addLongRecipe(SLEEPING_POTION.get(), SLEEPING_POTION_LONG.get());
            addCorruptedRecipe(SLEEPING_POTION.get(), WAKING_POTION.get());

            addBaseRecipe(Items.ROTTEN_FLESH, HUNGER_POTION.get());
            addLongRecipe(HUNGER_POTION.get(), HUNGER_POTION_LONG.get());
            addCorruptedRecipe(HUNGER_POTION.get(), SATURATION_POTION.get());
            addCorruptedRecipe(HUNGER_POTION_LONG.get(), SATURATION_POTION_LONG.get());

            addBaseRecipe(Items.PRISMARINE_CRYSTALS, MINING_FATIGUE_POTION.get());
            addLongRecipe(MINING_FATIGUE_POTION.get(), MINING_FATIGUE_POTION_LONG.get());
            addStrongRecipe(MINING_FATIGUE_POTION.get(), MINING_FATIGUE_POTION_STRONG.get());
            addCorruptedRecipe(MINING_FATIGUE_POTION.get(), HASTE_POTION.get());
            addCorruptedRecipe(MINING_FATIGUE_POTION_LONG.get(), HASTE_POTION_LONG.get());
            addCorruptedRecipe(MINING_FATIGUE_POTION_STRONG.get(), HASTE_POTION_STRONG.get());

            addCorruptedRecipe(Potions.SLOW_FALLING, LEVITATION_POTION.get());
            addCorruptedRecipe(Potions.LONG_SLOW_FALLING, LEVITATION_POTION_LONG.get());
            addStrongRecipe(LEVITATION_POTION.get(), LEVITATION_POTION_STRONG.get());

            addBaseRecipe(Items.GLOW_INK_SAC, GLOWING_POTION.get());
            addLongRecipe(GLOWING_POTION.get(), GLOWING_POTION_LONG.get());
            addCorruptedRecipe(GLOWING_POTION.get(), BLINDNESS_POTION.get());
            addCorruptedRecipe(GLOWING_POTION_LONG.get(), BLINDNESS_POTION_LONG.get());

            addBaseRecipe(Items.INK_SAC, BLINDNESS_POTION.get());
            addLongRecipe(BLINDNESS_POTION.get(), BLINDNESS_POTION_LONG.get());

        } );
    }

    //other potions
    @SubscribeEvent
    public static void registerColors(ColorHandlerEvent.Item event){
        event.getItemColors()
                .register((stack, tintIndex) ->
                        tintIndex > 0 ?
                                -1 :
                                ModPotionItem.getColor(stack), MOD_POTION_ITEM.get(), MOD_POTION_SPLASH.get(), MOD_POTION_LINGERING.get());
    }


    private static void addBaseRecipe(ItemLike item, Potion result){
        Ingredient awkwardPotion = Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD));
        Ingredient ingredient = Ingredient.of(new ItemStack(item));
        ItemStack resultStack = PotionUtils.setPotion(new ItemStack(Items.POTION), result);
        BrewingRecipeRegistry.addRecipe(awkwardPotion, ingredient, resultStack);
    }

    private static void addLongRecipe(Potion shortPotion, Potion longPotion){
        Ingredient ingredient = Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), shortPotion));
        Ingredient redstone = Ingredient.of(new ItemStack(Items.REDSTONE));
        ItemStack resultPotion = PotionUtils.setPotion(new ItemStack(Items.POTION), longPotion);
        BrewingRecipeRegistry.addRecipe(ingredient, redstone, resultPotion);
    }
    private static void addStrongRecipe(Potion weakPotion, Potion strongPotion){
        Ingredient ingredient = Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), weakPotion));
        Ingredient glowstone = Ingredient.of(new ItemStack(Items.GLOWSTONE_DUST));
        ItemStack resultPotion = PotionUtils.setPotion(new ItemStack(Items.POTION), strongPotion);
        BrewingRecipeRegistry.addRecipe(ingredient, glowstone, resultPotion);
    }
    private static void addCorruptedRecipe(Potion potion, Potion corrupted){
        Ingredient ingredient = Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), potion));
        Ingredient spiderEye = Ingredient.of(new ItemStack(Items.FERMENTED_SPIDER_EYE));
        ItemStack resultPotion = PotionUtils.setPotion(new ItemStack(Items.POTION), corrupted);
        BrewingRecipeRegistry.addRecipe(ingredient, spiderEye, resultPotion);
    }

}
