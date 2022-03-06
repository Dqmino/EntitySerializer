package me.hex.entityserializer.api;

import me.hex.entityserializer.core.interfaces.Serializer;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;

public interface EntityHandler extends Serializer<Entity, NamespacedKey, EntityHolder> {

}
