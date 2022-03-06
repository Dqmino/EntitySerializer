package me.hex.entityserializer.api;

import com.google.common.base.Preconditions;
import me.hex.entityserializer.core.impl.SimpleEntityHandler;
import me.hex.entityserializer.core.impl.SimpleEntitySerializer;
import me.hex.entityserializer.core.impl.StructureFactory;
import me.hex.entityserializer.core.interfaces.Serializer;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.structure.StructureManager;

public interface EntitySerializer {

    /**
     * Creates an instance of EntitySerializer.
     * @param plugin Your main class.
     * @return the instance of EntitySerializer created.
     */
    static EntitySerializer create(JavaPlugin plugin) {

        Preconditions.checkArgument(plugin != null);

        StructureManager manager = plugin.getServer().getStructureManager();
        SimpleEntityHandler simpleEntityHandler = new SimpleEntityHandler(manager, new StructureFactory(manager));

        return new SimpleEntitySerializer(plugin, simpleEntityHandler);
    }

    /**
     * @return User's plugin
     */
    JavaPlugin getPlugin();

    /**
     * Use this to de/serialize, destroy a serialization
     * @return The Serializer
     */
    Serializer<Entity, NamespacedKey, EntityHolder> getEntityHandler();

}
