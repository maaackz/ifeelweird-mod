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

public class CustomItems {
    public static final Item SAND_OCEAN_MUSIC_DISC = registerItem("sand_ocean_music_disc",
        new MusicDiscItem(7, CustomSounds.SAND_OCEAN, new FabricItemSettings().maxCount(1), 79));

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
