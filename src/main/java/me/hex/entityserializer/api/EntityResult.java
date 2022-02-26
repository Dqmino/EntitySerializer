package me.hex.entityserializer.api;

import me.hex.entityserializer.EntitySerializer;
import org.bukkit.Location;
import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.entity.Entity;
import org.bukkit.structure.Structure;

import java.util.Arrays;
import java.util.Optional;

public class EntityResult {

    private final Structure structure;

    public EntityResult(Structure structure) {
        this.structure = structure;
    }

    /**
     *  Spawns the entity, and returns it.
     * @param location Location to spawn entity at.
     * @return Entity spawned.
     */
    public Entity spawnAndGet(Location location) {

        spawn(location);

        Optional<Entity> closest = Arrays.stream(location.getChunk().getEntities())
                .min((e1, e2) -> (int) (e2.getLocation().distanceSquared(location)
                        - e1.getLocation().distanceSquared(location)));

        return closest.orElseThrow(() -> new RuntimeException("Can't find entity to return."));
    }

    /**
     * Spawns the Entity at the given Location.
     * @param loc Location to spawn entity at.
     */
    public void spawn(Location loc) {
        structure.place(loc, true, StructureRotation.NONE, Mirror.NONE,
                0, 0f, EntitySerializer.getRandom());
    }

}
