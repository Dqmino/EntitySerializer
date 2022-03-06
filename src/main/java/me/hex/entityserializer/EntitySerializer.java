package me.hex.entityserializer;

import me.hex.entityserializer.api.EntityHolder;
import me.hex.entityserializer.core.StructureFactory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.structure.StructureManager;

/**
 * API's Main class, holding the core and essentials of the library.
 */
public final class EntitySerializer {

    private final JavaPlugin plugin;
    private final EntityHolder entityHolder;

    /**
     * @param plugin Main class of your plugin.
     */
    public EntitySerializer(JavaPlugin plugin) {
        this.plugin = plugin;
        StructureManager manager = plugin.getServer().getStructureManager();
        this.entityHolder = new EntityHolder(manager, new StructureFactory(manager));
    }

    /**
     * Gets the Main instance.
     *
     * @return Main instance of User's plugin if set, otherwise null
     */
    public JavaPlugin getPlugin() {
        return plugin;
    }

    /**
     * you should use this to serialize, and to deserialize.
     *
     * @return EntityHolder Object.
     */
    public EntityHolder getEntityHolder() {
        return entityHolder;
    }

}
