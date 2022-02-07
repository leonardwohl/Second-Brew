package com.leonardwohl.sleeppotions.potions;

import com.leonardwohl.sleeppotions.RegistryItems;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;

public class HoneyPotionItem extends ModPotionItem {
    public HoneyPotionItem(Properties p_42979_) {
        super(p_42979_);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        Potion potion = ModPotionItem.getPotion(pStack).getPotion();
        if(potion == null || potion == Potions.EMPTY || potion == Potions.WATER)
            return Items.HONEY_BOTTLE.finishUsingItem(pStack, pLevel, pEntityLiving);
        else
            return super.finishUsingItem(pStack, pLevel, pEntityLiving);
    }

    @Override
    public ItemStack getDefaultInstance() {
        return ModPotionItem.setPotion(new ItemStack(this), RegistryItems.EMPTY_MOD_POTION.get());
    }

    @Override
    public void fillItemCategory(CreativeModeTab pGroup, NonNullList<ItemStack> pItems) {
        pItems.add(ModPotionItem.setPotion(new ItemStack(this), RegistryItems.GOOEY_POTION.get()));
        pItems.add(ModPotionItem.setPotion(new ItemStack(this), RegistryItems.LOVE_POTION.get()));
        pItems.add(ModPotionItem.setPotion(new ItemStack(this), RegistryItems.PEACE_POTION.get()));

    }
}
