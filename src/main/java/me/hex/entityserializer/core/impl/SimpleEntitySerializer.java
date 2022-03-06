package me.hex.entityserializer.core.impl;

import me.hex.entityserializer.api.EntityHandler;
import me.hex.entityserializer.api.EntitySerializer;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * API's Main class, holding the core and essentials of the library.
 */
public final class SimpleEntitySerializer implements EntitySerializer {

    private final JavaPlugin plugin;
    private final EntityHandler entityHandler;

    /**
     * @param plugin Main class of your plugin.
     */
    public SimpleEntitySerializer(JavaPlugin plugin, EntityHandler handler) {
        this.plugin = plugin;
        this.entityHandler = handler;
    }

    /**
     * Gets the Main instance.
     *
     * @return Main instance of User's plugin if set, otherwise null
     */
    @Override
    public JavaPlugin getPlugin() {
        return plugin;
    }

    /**
     * you should use this to serialize, and to deserialize.
     *
     * @return EntityHandler Object.
     */
    @Override
    public EntityHandler getEntityHandler() {
        return entityHandler;
    }

}
