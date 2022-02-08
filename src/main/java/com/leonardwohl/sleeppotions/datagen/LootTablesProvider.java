package com.leonardwohl.sleeppotions.datagen;

import com.leonardwohl.sleeppotions.ItemRegistry;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class LootTablesProvider extends LootTableProvider {
    //ForgeLootTableProvider forgeLoot;
    public LootTablesProvider(DataGenerator pGenerator) {
        super(pGenerator);
        //forgeLoot = new ForgeLootTableProvider(pGenerator);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        //return super.getTables();
        BlockLoot blockLoot = new BlockLoot();
        blockLoot.dropSelf(ItemRegistry.GOLDEN_FLEECE.get());
        //LootTable.lootTable().withPool(LootPool.lootPool().add(LootPoolEntryContainer.))
        List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> tables = super.getTables();
        tables.add(Pair.of(()->blockLoot, LootContextParamSets.BLOCK));
        return tables;
    }

}
