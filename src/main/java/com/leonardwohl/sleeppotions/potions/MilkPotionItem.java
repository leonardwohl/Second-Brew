package com.leonardwohl.sleeppotions.potions;

import com.leonardwohl.sleeppotions.RegistryItems;
import net.minecraft.core.NonNullList;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MilkPotionItem extends ModPotionItem {
    public MilkPotionItem(Properties p_42979_) {
        super(p_42979_);
    }

    @Override
    public ItemStack getDefaultInstance() {
        return ModPotionItem.setPotion(new ItemStack(this), RegistryItems.MILK_POTION.get());
    }

    @Override
    public void fillItemCategory(CreativeModeTab pGroup, NonNullList<ItemStack> pItems) {
        pItems.add(ModPotionItem.setPotion(new ItemStack(this), RegistryItems.MILK_POTION.get()));
        pItems.add(ModPotionItem.setPotion(new ItemStack(this), RegistryItems.PANACEA_POTION.get()));
        pItems.add(ModPotionItem.setPotion(new ItemStack(this), RegistryItems.MAGIC_RESISTANCE_POTION.get()));
        //pItems.add(ModPotionItem.setPotion(new ItemStack(RegistryItems.MILK_POTION_SPLASH.get()), RegistryItems.MILK_POTION.get()));

    }
}
