package me.hex.entityserializer.api;

import com.google.common.base.Preconditions;
import me.hex.entityserializer.core.StructureFactory;
import me.hex.entityserializer.core.interfaces.Serializer;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.structure.Structure;
import org.bukkit.structure.StructureManager;

import java.util.concurrent.CompletableFuture;

public class EntityHolder implements Serializer<Entity, NamespacedKey, EntityResult> {

    private final StructureManager manager;
    private final StructureFactory factory;

    public EntityHolder(StructureManager manager, StructureFactory factory) {
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
     * @return deserialized EntityResult from Name-spaced Key.
     */
    @Override
    public EntityResult deserialize(NamespacedKey toDeserialize) {

        Preconditions.checkArgument(toDeserialize != null);

        Structure structure = manager.getStructure(toDeserialize);

        Preconditions.checkArgument(structure != null);

        return new EntityResult(structure);
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
