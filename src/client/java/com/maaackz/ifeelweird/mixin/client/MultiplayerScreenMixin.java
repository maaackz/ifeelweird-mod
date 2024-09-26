package com.maaackz.ifeelweird.mixin.client;

import com.maaackz.ifeelweird.sound.CustomSounds;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.sound.PositionedSoundInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MultiplayerScreen.class)
public class MultiplayerScreenMixin {
	MinecraftClient client = MinecraftClient.getInstance();
	@Inject(at = @At("HEAD"), method = "init")
	private void init(CallbackInfo info) {
		// Get the client instance

		// Generate a random number between 0 and 1
		double randomChance = Math.random();
		System.out.println(randomChance);
		// 10% chance to play I_HATE sound
		if (randomChance < 0.1) {
			System.out.println("hate");
			this.client.getSoundManager().play(PositionedSoundInstance.master(CustomSounds.I_HATE, 1.0F, 0.1F));
		} else {
			System.out.println("luv");
			// 90% chance to play I_LUV sound
			this.client.getSoundManager().play(PositionedSoundInstance.master(CustomSounds.I_LUV, 1.0F, 0.1F));
		}
	}

}