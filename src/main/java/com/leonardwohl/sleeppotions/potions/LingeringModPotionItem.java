package com.leonardwohl.sleeppotions.potions;

import com.leonardwohl.sleeppotions.RegistryItems;
import net.minecraft.world.item.ItemStack;

public class LingeringModPotionItem extends ThrowableModPotionItem {
    public LingeringModPotionItem(Properties p_42979_) {
        super(p_42979_);
    }

    @Override
    public ItemStack getDefaultInstance() {
        return ModPotionItem.setPotion(new ItemStack(this), RegistryItems.EMPTY_MOD_POTION.get());
    }
}
