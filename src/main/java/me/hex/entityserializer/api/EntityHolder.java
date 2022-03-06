package me.hex.entityserializer.api;

import me.hex.entityserializer.core.exceptions.EntityNotFoundException;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public interface EntityHolder {

    Entity spawnAndGet(Location location) throws EntityNotFoundException;

    void spawn(Location location);
}
