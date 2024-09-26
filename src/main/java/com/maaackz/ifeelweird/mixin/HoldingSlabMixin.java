package com.maaackz.ifeelweird.mixin;

import com.maaackz.ifeelweird.sound.SoundPacketSender;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class HoldingSlabMixin {

	private ItemStack previousMainHandStack = ItemStack.EMPTY;
	private boolean soundPlaying = false;  // Flag to track if the sound is playing

	@Inject(at = @At("HEAD"), method = "tick")
	private void onTick(CallbackInfo info) {
		ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;

		int currentSlot = player.getInventory().selectedSlot;
		ItemStack currentMainHandStack = player.getInventory().getMainHandStack();

		// Check if the main hand stack has changed
		if (!ItemStack.areEqual(previousMainHandStack, currentMainHandStack)) {
			previousMainHandStack = currentMainHandStack; // Update previous stack

			// Check if the item stack name contains "sand" and "slab"
			if (currentMainHandStack.getName().getString().toLowerCase().contains("sand") &&
					currentMainHandStack.getName().getString().toLowerCase().contains("slab")) {

				// If sound is not playing, send the play sound packet
				if (!soundPlaying) {
					System.out.println("RETURN THE SLAB");
					SoundPacketSender.sendToAllPlayers(player.server, "play", new Identifier("ifeelweird", "pharaohs_curse"), 1.0F, 1.0F, 0);
					soundPlaying = true; // Set the flag
				}
			} else {
				// If the stack doesn't match and sound is playing, send the stop sound packet
				if (soundPlaying) {
					SoundPacketSender.sendToAllPlayers(player.server, "stop", new Identifier("ifeelweird", "pharaohs_curse"));
					soundPlaying = false; // Reset the flag
				}
			}
		}
	}
}
