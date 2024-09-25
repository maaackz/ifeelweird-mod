package com.maaackz.ifeelweird.mixin;

import com.maaackz.ifeelweird.sound.CustomSounds;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class HoldingSlabMixin {

	private ItemStack previousMainHandStack = ItemStack.EMPTY;

	ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
	World world = this.player.getWorld();

//	SoundManager soundManager = server.getSoundManager();

	@Inject(at = @At("HEAD"), method = "playerTick")
	private void onTick(CallbackInfo info) {

		int currentSlot = player.getInventory().selectedSlot;
		ItemStack currentMainHandStack = player.getInventory().getMainHandStack();
		ItemStack currentOffStack = player.getInventory().getStack(PlayerInventory.OFF_HAND_SLOT);

		// Check if the main hand stack has changed
		if (!ItemStack.areEqual(previousMainHandStack, currentMainHandStack)) {
			previousMainHandStack = currentMainHandStack; // Update previous stack

			// Check if the item stack name contains "sand" and "slab"
			if (currentMainHandStack.getName().getString().toLowerCase().contains("sand") &&
					currentMainHandStack.getName().getString().toLowerCase().contains("slab")) {
				System.out.println("RETURN THE SLAB");
				playSoundForAllPlayers(player, CustomSounds.PHARAOHS_CURSE_SOUND);
			}
			else {

//				soundManager.stopSounds(new Identifier("ifeelweird:pharaohs_curse"), SoundCategory.AMBIENT);
			}
		}

	}

	private void playSoundForAllPlayers(PlayerEntity player, SoundEvent soundEvent) {
		MinecraftServer server = player.getServer();

		if (server != null && server.getWorld(player.getEntityWorld().getRegistryKey()) != null) {
			// Play sound for all players in the world
			server.getWorld(player.getEntityWorld().getRegistryKey()).playSoundFromEntity(
					null, // Target player for the sound, or null for all
					player, // The entity where the sound originates
					soundEvent, // The sound event to play
					SoundCategory.AMBIENT, // Sound category
					30.0F, // Volume
					1.0F   // Pitch
			);


		}
	}
}
