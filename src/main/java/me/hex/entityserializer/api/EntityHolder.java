package me.hex.entityserializer.api;

import com.google.common.base.Preconditions;
import me.hex.entityserializer.EntitySerializer;
import me.hex.entityserializer.core.interfaces.Serializer;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.structure.Structure;

public class EntityHolder implements Serializer<Entity, NamespacedKey> {

    /**
     * Serializes an entity to Name-spaced Key specified.
     * Note that this method uses 1.17.1 Structures API.
     *
     * @param toSerialize Key to use for later deserializing.
     * @return Name-spaced Key used to serialize.
     */
    @Override
    public NamespacedKey serialize(Entity toSerialize, NamespacedKey serialKey) {

        Preconditions.checkArgument(toSerialize != null && serialKey != null);

        EntitySerializer.getFactory().create(toSerialize, serialKey);

        return serialKey;
    }

    /**
     * [MIGHT NOT WORK] Use EntityResult deserialize(NamespacedKey toDeserialize) if it doesn't.
     * Deserializes an entity from its corresponding Name-spaced Key specified.
     * Note that this method uses 1.17.1 Structures API.
     *
     * @param toDeserialize Key corresponding to the entity serialized.
     * @return deserialized entity from Name-spaced Key.
     */
    @Override
    public Entity deserialize(NamespacedKey toDeserialize) {

        Preconditions.checkArgument(toDeserialize != null);

        Structure structure = EntitySerializer.getManager().getStructure(toDeserialize);

        Preconditions.checkArgument(structure != null);

        Entity entityExtracted = structure.getEntities().get(0);

        Preconditions.checkArgument(entityExtracted != null
                && structure.getEntities().size() == 1);

        return entityExtracted;
    }

    /**
     * [MIGHT NOT WORK] Use EntityResult deserialize(NamespacedKey toDeserialize, true)
     * if it doesn't.
     * Deserializes an entity from its corresponding Name-spaced Key specified.
     * Note that this method uses 1.17.1 Structures API.
     *
     * @param toDeserialize Key corresponding to the entity serialized.
     * @param alpha         Doesn't matter what this is set to, this is just to overload methods.
     * @return deserialized entity from Name-spaced Key.
     */
    public EntityResult deserialize(NamespacedKey toDeserialize, boolean alpha) {

        Preconditions.checkArgument(toDeserialize != null);

        Structure structure = EntitySerializer.getManager().getStructure(toDeserialize);

        Preconditions.checkArgument(structure != null);

        return new EntityResult(structure);
    }

}
