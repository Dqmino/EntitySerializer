package me.hex.entityserializer.api;

import me.hex.entityserializer.core.exceptions.EntityNotFoundException;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public interface EntityHolder {

    /**
     * Spawns and gets the entity at the given location.
     * @param location Location to spawn the entity at.
     * @return the entity spawned
     * @throws EntityNotFoundException if entity was not found, [Should not happen]
     */
    Entity spawnAndGet(Location location) throws EntityNotFoundException;

    /**
     * Spawns the entity at the given location
     * @param location Location to spawn the entity at.
     */
    void spawn(Location location);
}
