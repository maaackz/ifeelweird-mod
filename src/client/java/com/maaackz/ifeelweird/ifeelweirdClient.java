package com.maaackz.ifeelweird;

import com.maaackz.ifeelweird.sound.SoundPacketReceiver;
import net.fabricmc.api.ClientModInitializer;

public class ifeelweirdClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		System.out.println("Client initialized.");
		SoundPacketReceiver.registerPackets();
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
	}

}