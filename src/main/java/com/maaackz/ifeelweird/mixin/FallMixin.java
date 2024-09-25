package com.maaackz.ifeelweird.mixin;

import com.maaackz.ifeelweird.sound.SoundPacketSender;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class FallMixin {

	private boolean soundPlaying = false;  // Flag to track if the sound is playing

	@Inject(at = @At("HEAD"), method = "tick")
	private void onTick(CallbackInfo info) {
		ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;

		if (player.getWorld() == null) return; // Check if the world is valid

		// Check if the player is falling
		if (player.getVelocity().y < -0.5) {  // Adjust this threshold as needed
			// Perform a raycast straight downwards to determine the distance to the ground
			Vec3d startPos = player.getPos();
			Vec3d endPos = startPos.add(0, -5, 0);  // Raycast 5 blocks downwards

			BlockHitResult hitResult = player.getWorld().raycast(new RaycastContext(
					startPos,
					endPos,
					RaycastContext.ShapeType.COLLIDER,
					RaycastContext.FluidHandling.ANY,
					player
			));

			if (hitResult.getType() == HitResult.Type.MISS) {
				// If the distance to the ground is greater than a certain amount and the sound is not already playing, send the sound packet
				if (!soundPlaying) {
					System.out.println("Playing fall sound AAAAA");
					SoundPacketSender.sendToAllPlayers(player.server,"play", new Identifier("ifeelweird", "aaaaa"), 0.5F, 1.0F, 0); // You can modify volume/pitch or add fade duration if needed
					soundPlaying = true;  // Set the flag to indicate the sound is playing
				}
			}
		} else {
			// Reset the flag when the player is no longer falling
			if (soundPlaying) {
				soundPlaying = false;

				SoundPacketSender.sendToAllPlayers(player.server,"stop", new Identifier("ifeelweird", "aaaaa"));
				// Optionally: Send a stop sound packet here if needed
			}
		}
	}

	private void sendSoundPacket(PlayerEntity player, Identifier soundId) {
		// Send a packet to all clients to play the sound

	}
}
