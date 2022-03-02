package me.hex.entityserializer.core.interfaces;

import java.util.concurrent.CompletableFuture;

/**
 * @param <T> Identifier
 * @param <Y> Key
 * @param <Z> Result
 */
public interface Factory<T, Y, Z> {

    /**
     * Does what a factory would do, create.
     *
     * @param identifier identifier for the creation.
     * @param key        Key for the creation
     * @return Result of the creation
     */
    Z create(T identifier, Y key, boolean option);

    CompletableFuture<Boolean> destroy(Y key);

}
