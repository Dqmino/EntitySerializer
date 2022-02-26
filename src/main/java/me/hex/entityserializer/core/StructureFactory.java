package me.hex.entityserializer.core;

import me.hex.entityserializer.EntitySerializer;
import me.hex.entityserializer.core.interfaces.Factory;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.structure.Structure;
import org.bukkit.structure.StructureManager;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class StructureFactory implements Factory<Entity, NamespacedKey, Structure> {

    /**
     * IMPORTANT NOTE: DO NOT USE THIS METHOD, IT'S FOR THE API INTERNALS.
     * Creates a structure around the entity.
     * Note that this method uses 1.17.1 Structures API.
     *
     * @param entity Entity to create a structure around.
     * @return Structure created..
     */
    @Override
    public Structure create(Entity entity, NamespacedKey keyToStruct) {

        StructureManager manager = EntitySerializer.getManager();
        Structure struct = manager.createStructure();


        struct.fill(entity.getLocation(), entity.getLocation()
                .add(0, 2, 0), true);

        manager.registerStructure(keyToStruct, struct);

        CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> {
            try {
                EntitySerializer.getManager().saveStructure(keyToStruct, struct);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }).toCompletableFuture();

        try {
            if (future.get()) {
                future.thenApply((e) -> {
                    entity.remove();
                    return null;
                });
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return struct;
    }
}
