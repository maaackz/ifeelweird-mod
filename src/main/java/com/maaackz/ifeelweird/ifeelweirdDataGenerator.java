package com.maaackz.ifeelweird;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import com.maaackz.ifeelweird.datagen.*;

public class ifeelweirdDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

//		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(CustomItemTagProvider::new);
//		pack.addProvider(CustomLootTableProvider::new);
		pack.addProvider(CustomModelProvider::new);
		pack.addProvider(CustomAdvancementProvider::new);
//		pack.addProvider(ModRecipeProvider::new);
//		pack.addProvider(ModPoiTagProvider::new);
	}
}
