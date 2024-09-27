package com.maaackz.ifeelweird.item;

import com.maaackz.ifeelweird.ifeelweird;
import com.maaackz.ifeelweird.sound.CustomSounds;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class CustomItems {
    public static final Item SAND_OCEAN_MUSIC_DISC = registerItem("sand_ocean_music_disc",
        new MusicDiscItem(7, CustomSounds.SAND_OCEAN, new FabricItemSettings().maxCount(1), 79));

//    public static final Item POOP = registerItem("poop",
//            new Item(7, CustomSounds.poop, new FabricItemSettings().maxCount(1), 79));

    public static final Item POOP = registerItem("poop",
            new Item(new FabricItemSettings().food(CustomFoodComponents.POOP)));

    public static final Item BANANA = registerItem("banana",
            new Item(new FabricItemSettings().food(CustomFoodComponents.BANANA)));

    public static final Item GOLDEN_BANANA = registerItem("golden_banana",
            new Item(new FabricItemSettings()
                    .food(CustomFoodComponents.GOLDEN_BANANA)
                    .rarity(Rarity.RARE)
            )
    );

    public static final Item ENCHANTED_GOLDEN_BANANA = registerItem("enchanted_golden_banana",
            new EnchantedGoldenBananaItem(new FabricItemSettings()
                    .food(CustomFoodComponents.ENCHANTED_GOLDEN_BANANA)
                    .rarity(Rarity.EPIC)
            )
    );

    private static void addItemsToIngredientItemsGroup(FabricItemGroupEntries entries) {

    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(ifeelweird.MOD_ID, name), item);
    }

    public static void registerItems() {
        ifeelweird.LOGGER.info(("Registering mod items for " + ifeelweird.MOD_ID));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(CustomItems::addItemsToIngredientItemsGroup);
    }
}
