package com.maaackz.ifeelweird.item;

import com.maaackz.ifeelweird.ifeelweird;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CustomItemGroups {
    public static final ItemGroup IFEELWEIRD_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(ifeelweird.MOD_ID, "ifeelweird"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.ifeelweird"))
                    .icon(() -> new ItemStack(CustomItems.SAND_OCEAN_MUSIC_DISC)).entries((displayContext, entries) -> {

                        entries.add(CustomItems.SAND_OCEAN_MUSIC_DISC);

                    }).build());

    public static void registerToVanillaItemGroups() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {

            content.addAfter(Items.MUSIC_DISC_RELIC, CustomItems.SAND_OCEAN_MUSIC_DISC);

        });
    }

    public static void registerItemGroups() {
        ifeelweird.LOGGER.info("Registering item groups for " + ifeelweird.MOD_ID);
    }
}