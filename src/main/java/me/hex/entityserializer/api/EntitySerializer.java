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

    static EntitySerializer create(JavaPlugin plugin) {

        Preconditions.checkArgument(plugin != null);

        StructureManager manager = plugin.getServer().getStructureManager();
        SimpleEntityHandler simpleEntityHandler = new SimpleEntityHandler(manager, new StructureFactory(manager));

        return new SimpleEntitySerializer(plugin, simpleEntityHandler);
    }

    JavaPlugin getPlugin();

    Serializer<Entity, NamespacedKey, EntityHolder> getEntityHandler();

}
