package net.feyhoan.the_resurrection_talisman.sounds;

import net.feyhoan.the_resurrection_talisman.TheResurrectionTalisman;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final RegistryEntry<SoundEvent> LIMBO_AMBIENT = registerSoundEventRegistry("ambient.overworld_limbo");

    public static final SoundEvent LOST_SPIRIT_1 = registerSoundEvent("entity.lost_spirit_1");
    public static final SoundEvent LOST_SPIRIT_2 = registerSoundEvent("entity.lost_spirit_2");
    public static final SoundEvent LOST_SPIRIT_3 = registerSoundEvent("entity.lost_spirit_3");

    private static RegistryEntry<SoundEvent> registerSoundEventRegistry(String name) {
        Identifier id = new Identifier(TheResurrectionTalisman.MOD_ID, name);
        SoundEvent soundEvent = SoundEvent.of(id);
        return Registry.registerReference(Registries.SOUND_EVENT, id, soundEvent);
    }

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(TheResurrectionTalisman.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        TheResurrectionTalisman.LOGGER.info("Registering Sounds for " + TheResurrectionTalisman.MOD_ID);
    }
}