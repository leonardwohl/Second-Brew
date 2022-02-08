package com.leonardwohl.sleeppotions;

import com.leonardwohl.sleeppotions.effects.EffectsRegistry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class PotionRegistryBuilder {

    private DeferredRegister<Potion> potionDeferredRegister;
    private String potionName;
    private Supplier<MobEffect> mobEffectSupplier;
    private RegistryObject<Potion> basePotion;
    private RegistryObject<Potion> longPotion;
    private RegistryObject<Potion> strongPotion;
    private RegistryObject<Potion> extraLongPotion;


    public PotionRegistryBuilder(DeferredRegister<Potion> potionDeferredRegister, String potionName, Supplier<MobEffect> mobEffectSupplier) {
        this.potionDeferredRegister = potionDeferredRegister;
        this.potionName = potionName;
        this.mobEffectSupplier = mobEffectSupplier;
    }

    public PotionRegistryBuilder withBasePotion(){
        return withBasePotion(EffectsRegistry.POTION_TICKS, 0);
    }
    public PotionRegistryBuilder withBasePotion(int ticks, int amplifier){
        basePotion = ticks > 0 ?
                potionDeferredRegister.register(potionName, () -> new Potion(new MobEffectInstance(mobEffectSupplier.get(), ticks, amplifier))) :
                potionDeferredRegister.register(potionName, () -> new Potion(new MobEffectInstance(mobEffectSupplier.get(), amplifier)));
        return this;
    }

    public PotionRegistryBuilder withLongPotion(){
        return withLongPotion(EffectsRegistry.LONG_POTION_TICKS, 0);
    }
    public PotionRegistryBuilder withLongPotion(int ticks, int amplifier){
        longPotion = potionDeferredRegister.register("long_"+potionName, () -> new Potion(new MobEffectInstance(mobEffectSupplier.get(), ticks, amplifier)));
        return this;
    }

    public PotionRegistryBuilder withStrongPotion(){
        return withStrongPotion(EffectsRegistry.STRONG_POTION_TICKS, 1);
    }
    public PotionRegistryBuilder withStrongPotion(int ticks, int amplifier){
        strongPotion = ticks > 0 ?
                potionDeferredRegister.register("strong_"+potionName, () -> new Potion(new MobEffectInstance(mobEffectSupplier.get(), ticks, amplifier))):
                potionDeferredRegister.register("strong_"+potionName, () -> new Potion(new MobEffectInstance(mobEffectSupplier.get(), amplifier)));
        return this;
    }
    public PotionRegistryBuilder withExtraLongPotion(){
        return withExtraLongPotion(20*60*20, 0);
    }
    public PotionRegistryBuilder withExtraLongPotion(int ticks, int amplifier){
        extraLongPotion = potionDeferredRegister.register("extra_long_"+potionName, () -> new Potion(new MobEffectInstance(mobEffectSupplier.get(), ticks, amplifier)));
        return this;
    }

    public PotionRegistryBuilder registerRecipes(ItemLike baseIngredient){
        return registerRecipes(Potions.AWKWARD, baseIngredient);
    }
    public PotionRegistryBuilder registerRecipes(Potion solvent, ItemLike baseIngredient){
        return registerRecipes(PotionUtils.setPotion(Items.POTION.getDefaultInstance(), solvent), baseIngredient);
    }
    public PotionRegistryBuilder registerRecipes(ItemStack solvent, ItemLike baseIngredient){
        addBaseRecipe(solvent, baseIngredient);
        addStrongRecipe();
        addLongRecipe();
        addExtraLongRecipe();
        return this;
    }

    public Potion getBasePotion() {
        return basePotion != null ? basePotion.get() : null;
    }

    public Potion getLongPotion() {
        return longPotion != null ? longPotion.get() : null;
    }

    public Potion getStrongPotion() {
        return strongPotion != null ? strongPotion.get() : null;
    }

    public Potion getExtraLongPotion() {
        return extraLongPotion !=null ? extraLongPotion.get() : null;
    }

    public void addBaseRecipe(ItemStack solvent, ItemLike item){
        Ingredient awkwardPotion = Ingredient.of(solvent);
        Ingredient ingredient = Ingredient.of(new ItemStack(item));
        ItemStack resultStack = PotionUtils.setPotion(new ItemStack(Items.POTION), basePotion.get());
        BrewingRecipeRegistry.addRecipe(awkwardPotion, ingredient, resultStack);
    }

    public void addLongRecipe(){
        if(this.longPotion == null)
            return;
        Ingredient ingredient = Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), basePotion.get()));
        Ingredient redStone = Ingredient.of(new ItemStack(Items.REDSTONE));
        ItemStack resultPotion = PotionUtils.setPotion(new ItemStack(Items.POTION), longPotion.get());
        BrewingRecipeRegistry.addRecipe(ingredient, redStone, resultPotion);
    }
    public void addStrongRecipe(){
        if(this.strongPotion == null)
            return;
        Ingredient ingredient = Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), basePotion.get()));
        Ingredient glowStone = Ingredient.of(new ItemStack(Items.GLOWSTONE_DUST));
        ItemStack resultPotion = PotionUtils.setPotion(new ItemStack(Items.POTION), strongPotion.get());
        BrewingRecipeRegistry.addRecipe(ingredient, glowStone, resultPotion);
    }
    public void addExtraLongRecipe(){
        if(this.extraLongPotion == null)
            return;
        Ingredient ingredient = Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), longPotion.get()));
        Ingredient amethyst = Ingredient.of(new ItemStack(Items.AMETHYST_SHARD));
        ItemStack resultPotion = PotionUtils.setPotion(new ItemStack(Items.POTION), extraLongPotion.get());
        BrewingRecipeRegistry.addRecipe(ingredient, amethyst, resultPotion);
    }
    public void addCorruptedRecipes(Potion baseCorrupted, Potion longCorrupted, Potion strongCorrupted, Potion extraLongCorrupted){
        addCorruptedRecipe(getBasePotion(), baseCorrupted);
        addCorruptedRecipe(getLongPotion(), longCorrupted);
        addCorruptedRecipe(getStrongPotion(), strongCorrupted);
        addCorruptedRecipe(getExtraLongPotion(), strongCorrupted);
    }
    public static void addCorruptedRecipe(Potion potion, Potion corrupted){
        if(potion == null || corrupted == null)
            return;
        Ingredient ingredient = Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), potion));
        Ingredient spiderEye = Ingredient.of(new ItemStack(Items.FERMENTED_SPIDER_EYE));
        ItemStack resultPotion = PotionUtils.setPotion(new ItemStack(Items.POTION), corrupted);
        BrewingRecipeRegistry.addRecipe(ingredient, spiderEye, resultPotion);
    }

    public static void addBaseRecipe(ItemLike item, Potion result){
        Ingredient awkwardPotion = Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD));
        Ingredient ingredient = Ingredient.of(new ItemStack(item));
        ItemStack resultStack = PotionUtils.setPotion(new ItemStack(Items.POTION), result);
        BrewingRecipeRegistry.addRecipe(awkwardPotion, ingredient, resultStack);
    }

    public static void addLongRecipe(Potion shortPotion, Potion longPotion){
        Ingredient ingredient = Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), shortPotion));
        Ingredient redstone = Ingredient.of(new ItemStack(Items.REDSTONE));
        ItemStack resultPotion = PotionUtils.setPotion(new ItemStack(Items.POTION), longPotion);
        BrewingRecipeRegistry.addRecipe(ingredient, redstone, resultPotion);
    }
    public static void addStrongRecipe(Potion weakPotion, Potion strongPotion){
        Ingredient ingredient = Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), weakPotion));
        Ingredient glowstone = Ingredient.of(new ItemStack(Items.GLOWSTONE_DUST));
        ItemStack resultPotion = PotionUtils.setPotion(new ItemStack(Items.POTION), strongPotion);
        BrewingRecipeRegistry.addRecipe(ingredient, glowstone, resultPotion);
    }

    public static void addExtraLongRecipe(Potion longPotion, Potion extraLongPotion){
        Ingredient ingredient = Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), longPotion));
        Ingredient amethyst = Ingredient.of(new ItemStack(Items.AMETHYST_SHARD));
        ItemStack resultPotion = PotionUtils.setPotion(new ItemStack(Items.POTION), extraLongPotion);
        BrewingRecipeRegistry.addRecipe(ingredient, amethyst, resultPotion);
    }
}
