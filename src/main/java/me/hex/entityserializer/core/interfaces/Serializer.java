package me.hex.entityserializer.core.interfaces;

public interface Serializer<T, Y> {

    // y = the result of serializing
    // t = the info needed to serialize

    Y serialize(T toSerialize, Y serialKey);

    T deserialize(Y toDeserialize);
}
