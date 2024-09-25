package com.maaackz.ifeelweird;

import com.maaackz.ifeelweird.item.CustomItemGroups;
import com.maaackz.ifeelweird.item.CustomItems;
import com.maaackz.ifeelweird.sound.CustomSounds;
import com.maaackz.ifeelweird.sound.SoundPacketSender;
import com.maaackz.ifeelweird.util.CustomLootTableModifiers;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ifeelweird implements ModInitializer {

	public static final String MOD_ID = "ifeelweird";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("ifeelweird mod initializing...");

		CustomItemGroups.registerItemGroups();
		CustomItemGroups.registerToVanillaItemGroups();

		CustomItems.registerItems();
		CustomSounds.registerSounds();

		CustomLootTableModifiers.modifyLootTables();

		SoundPacketSender.registerPackets();
		LOGGER.info("ifeelweird mod initialized!");
	}

}