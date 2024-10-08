package com.maaackz.ifeelweird.util;

import com.maaackz.ifeelweird.item.CustomItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.condition.RandomChanceWithLootingLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class CustomLootTableModifiers {
    private static final Identifier DESERT_TEMPLE_ID =
            new Identifier("minecraft", "chests/desert_pyramid");

    private static final Identifier JUNGLE_LEAVES_ID =
            new Identifier("minecraft", "blocks/jungle_leaves");

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if(DESERT_TEMPLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.1f)) // Drops 100% of the time
                        .with(ItemEntry.builder(CustomItems.SAND_OCEAN_MUSIC_DISC))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (JUNGLE_LEAVES_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        // Set drop chance to the same as apples (0.5%)
                        .conditionally(RandomChanceWithLootingLootCondition.builder(0.005f, 0.02f)) // 0.5% base chance, with Fortune adjustment
                        .with(ItemEntry.builder(CustomItems.BANANA))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            //            if(CREEPER_ID.equals(id)) {
            //                LootPool.Builder poolBuilder = LootPool.builder()
            //                        .rolls(ConstantLootNumberProvider.create(1))
            //                        .conditionally(RandomChanceLootCondition.builder(1f))
            //                        .with(ItemEntry.builder(ModItems.COAL_BRIQUETTE))
            //                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
            //
            //                tableBuilder.pool(poolBuilder.build());
            //            }
        });
    }
}