package me.hex.entityserializer.api;

import me.hex.entityserializer.EntitySerializer;
import me.hex.entityserializer.core.exceptions.EntityNotFoundException;
import org.bukkit.Location;
import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.entity.Entity;
import org.bukkit.structure.Structure;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

public class EntityResult {

    private final Structure structure;

    public EntityResult(Structure structure) {
        this.structure = structure;
    }

    /**
     *  Spawns the entity, and returns it.
     * @param location Location to spawn entity at.
     * @return Entity spawned.
     * @throws EntityNotFoundException if entity was not found. (Should not happen)
     */
    public Entity spawnAndGet(Location location) throws EntityNotFoundException {

        spawn(location);

        Optional<Entity> closest = Arrays.stream(location.getChunk().getEntities())
                .min((e1, e2) -> (int) (e2.getLocation().distanceSquared(location)
                        - e1.getLocation().distanceSquared(location)));

        return closest.orElseThrow(() -> new EntityNotFoundException
                ("Cannot find entity at location"));
    }

    /**
     * Spawns the Entity at the given Location.
     * @param loc Location to spawn entity at.
     */
    public void spawn(Location loc) {
        structure.place(loc, true, StructureRotation.NONE, Mirror.NONE,
                0, 0f, new Random());
    }

}
