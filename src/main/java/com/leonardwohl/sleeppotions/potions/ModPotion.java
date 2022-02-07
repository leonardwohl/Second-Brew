package com.leonardwohl.sleeppotions.potions;

import com.leonardwohl.sleeppotions.RegistryItems;
import net.minecraft.world.item.alchemy.Potion;

public class ModPotion extends net.minecraftforge.registries.ForgeRegistryEntry<ModPotion> {

    private Potion potion;
    private boolean isFoil;

    public ModPotion(Potion potion) {
        this.potion = potion;
    }

    public Potion getPotion() {
        return potion;
    }

    public void setPotion(Potion potion) {
        this.potion = potion;
    }

    public String getName(String pPrefix) {
        return pPrefix + RegistryItems.MOD_POTIONS_REGISTRY.get().getKey(this).getPath();
    }

    public ModPotion foil(boolean isFoil){
        this.isFoil = isFoil;
        return this;
    }
    public boolean isFoil(){
        return isFoil;
    }
    //private final ImmutableList<MobEffectInstance> otherEffects;

//    public OtherPotion(MobEffectInstance... pEffects) {
//        this(null, pEffects);
//    }
//
//    public OtherPotion(@Nullable String pName, MobEffectInstance... pEffects) {
//        otherEffects = ImmutableList.copyOf(pEffects);
//    }
//
//    @Override
//    public List<MobEffectInstance> getEffects() {
//        return super.getEffects();
//    }
//
//    @Override
//    public boolean hasInstantEffects() {
//        return super.hasInstantEffects();
//    }
}
