package com.leonardwohl.sleeppotions.potions;

import com.leonardwohl.sleeppotions.RegistryItems;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;

public class SplashModPotionItem extends ThrowableModPotionItem {
    public SplashModPotionItem(Properties p_42979_) {
        super(p_42979_);
    }

    @Override
    public ItemStack getDefaultInstance() {
        return ModPotionItem.setPotion(new ItemStack(this), RegistryItems.EMPTY_MOD_POTION.get());
    }
}
