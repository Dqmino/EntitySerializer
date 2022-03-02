package me.hex.entityserializer.core.interfaces;

import java.util.concurrent.CompletableFuture;

/**
 * @param <T> Info needed to serialize
 * @param <Y> Result of serializing.
 */
public interface Serializer<T, Y, K> {

    /**
     * Serializes T to Y.
     *
     * @param toSerialize Info to serialize from.
     * @param serialKey   Serial Key to set for later deserializing.
     * @return Serialized form (Y)
     */
    Y serialize(T toSerialize, Y serialKey, boolean option);

    /**
     * Deserializes Y To K.
     *
     * @param toDeserialize Info to Deserialize from (Serial key)
     * @return Deserialized form. (T)
     */
    K deserialize(Y toDeserialize);

    /**
     * Destroys the serialization
     *
     * @param toDestroy Serial Key to destroy
     * @return true if successful, otherwise false
     */
    CompletableFuture<Boolean> destroy(Y toDestroy);
}
