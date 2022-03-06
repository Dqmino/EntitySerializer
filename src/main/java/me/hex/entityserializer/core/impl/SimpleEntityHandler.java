package me.hex.entityserializer.core.impl;

import com.google.common.base.Preconditions;
import me.hex.entityserializer.api.EntityHandler;
import me.hex.entityserializer.api.EntityHolder;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.structure.Structure;
import org.bukkit.structure.StructureManager;

import java.util.concurrent.CompletableFuture;

public class SimpleEntityHandler implements EntityHandler {

    private final StructureManager manager;
    private final StructureFactory factory;

    public SimpleEntityHandler(StructureManager manager, StructureFactory factory) {
        this.manager = manager;
        this.factory = factory;
    }

    /**
     * Serializes an entity to Name-spaced Key specified.
     * Note that this method uses 1.17.1 Structures API.
     *
     * @param toSerialize Key to use for later deserializing.
     * @param removeAfter Whether to remove after serializing.
     * @return Name-spaced Key used to serialize.
     */
    @Override
    public NamespacedKey serialize(Entity toSerialize, NamespacedKey serialKey, boolean removeAfter) {

        Preconditions.checkArgument(toSerialize != null && serialKey != null);

        factory.create(toSerialize, serialKey, removeAfter);

        return serialKey;
    }

    /**
     * Deserializes an entity from its corresponding Name-spaced Key specified.
     * Note that this method uses 1.17.1 Structures API.
     *
     * @param toDeserialize Key corresponding to the entity serialized.
     * @return deserialized SimpleEntityHolder from Name-spaced Key.
     */

    @Override
    public EntityHolder deserialize(NamespacedKey toDeserialize) {

        Preconditions.checkArgument(toDeserialize != null);

        Structure structure = manager.getStructure(toDeserialize);

        Preconditions.checkArgument(structure != null);

        return new SimpleEntityHolder(structure);
    }

    /**
     * Note that the entity will not exist, and you cant use this to spawn the entity.
     * Reads an entity from its corresponding Name-spaced Key specified.
     * Note that this method uses 1.17.1 Structures API.
     *
     * @param toDeserialize Key corresponding to the entity serialized.
     * @return deserialized entity from Name-spaced Key.
     * @deprecated Might not work, use deserialize(NamespacedKey)
     */

    public Entity read(NamespacedKey toDeserialize) {

        Preconditions.checkArgument(toDeserialize != null);

        Structure structure = manager.getStructure(toDeserialize);

        Preconditions.checkArgument(structure != null);

        Entity entityExtracted = structure.getEntities().get(0);

        Preconditions.checkArgument(entityExtracted != null
                && structure.getEntities().size() == 1);

        return entityExtracted;
    }

    /**
     * Destroys a serialization
     *
     * @param toDestroy Key representing the serialization to destroy
     * @return true if successful false otherwise.
     */
    @Override
    public CompletableFuture<Boolean> destroy(NamespacedKey toDestroy) {
        return factory.destroy(toDestroy);
    }
}
