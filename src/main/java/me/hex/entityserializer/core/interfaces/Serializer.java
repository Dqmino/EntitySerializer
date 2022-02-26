package me.hex.entityserializer.core.interfaces;

/**
 *
 * @param <T> Info needed to serialize
 * @param <Y> Result of serializing.
 */
public interface Serializer<T, Y> {

    /**
     * Serializes T to Y.
     * @param toSerialize Info to serialize from.
     * @param serialKey Serial Key to set for later deserializing.
     * @return Serialized form (Y)
     */
    Y serialize(T toSerialize, Y serialKey);

    /**
     * Deserializes Y To T.
     * @param toDeserialize Info to Deserialize from (Serial key)
     * @return Deserialized form. (T)
     */
    T deserialize(Y toDeserialize);
}
