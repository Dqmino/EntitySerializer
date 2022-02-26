package me.hex.entityserializer.core.interfaces;

public interface Factory<T, Y, Z> {

    Z create(T identifier, Y key);

}
