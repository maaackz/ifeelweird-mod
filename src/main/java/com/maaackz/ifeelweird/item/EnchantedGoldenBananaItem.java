package com.maaackz.ifeelweird.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EnchantedGoldenBananaItem extends Item {
    public EnchantedGoldenBananaItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
