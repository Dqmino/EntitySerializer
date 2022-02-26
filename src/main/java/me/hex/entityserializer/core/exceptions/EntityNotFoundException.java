package me.hex.entityserializer.core.exceptions;

/**
 * Thrown if entity was not found somehow.
 */
public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
