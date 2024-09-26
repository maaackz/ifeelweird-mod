package com.maaackz.ifeelweird.sound;


import com.maaackz.ifeelweird.ifeelweird;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class CustomSounds {
    public static final SoundEvent PHARAOHS_CURSE_SOUND = registerSoundEvent("pharaohs_curse");
    public static final SoundEvent AAAAA_SOUND = registerSoundEvent("aaaaa");
    public static final SoundEvent SAND_OCEAN = registerSoundEvent("sand_ocean");
    public static final SoundEvent I_LUV = registerSoundEvent("iluv");
    public static final SoundEvent I_HATE = registerSoundEvent("ihate");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(ifeelweird.MOD_ID, name);
        ifeelweird.LOGGER.info("Registered sound: " + id);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() { ifeelweird.LOGGER.info("Registering sounds for " + ifeelweird.MOD_ID); }
}