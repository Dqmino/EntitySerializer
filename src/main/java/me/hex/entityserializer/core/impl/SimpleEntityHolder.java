package me.hex.entityserializer.core.impl;

import me.hex.entityserializer.api.EntityHolder;
import me.hex.entityserializer.core.exceptions.EntityNotFoundException;
import org.bukkit.Location;
import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.entity.Entity;
import org.bukkit.structure.Structure;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SimpleEntityHolder implements EntityHolder {

    private final Structure structure;

    public SimpleEntityHolder(Structure structure) {
        this.structure = structure;
    }

    /**
     * Spawns the entity, and returns it.
     *
     * @param location Location to spawn entity at.
     * @return Entity spawned.
     * @throws EntityNotFoundException if entity was not found. (Should not happen)
     */

    @Override
    public Entity spawnAndGet(Location location) throws EntityNotFoundException {

        Location uppedLoc = location.add(0, ThreadLocalRandom.current()
                .nextInt(750, 4000), 0);

        spawn(uppedLoc);

        Optional<Entity> closest = Arrays.stream(uppedLoc.getChunk().getEntities())
                .min((e1, e2) -> (int) (e2.getLocation().distanceSquared(location)
                        - e1.getLocation().distanceSquared(location)));

        closest.ifPresent((e) -> handleTeleportation(e, location));
        return closest.orElseThrow(() -> new EntityNotFoundException
                ("Cannot find entity at location"));
    }

    /**
     * Spawns the Entity at the given Location.
     *
     * @param loc Location to spawn entity at.
     */
    @Override
    public void spawn(Location loc) {
        structure.place(loc, true, StructureRotation.NONE, Mirror.NONE,
                0, 0f, new Random());
    }

    /**
     * Handles teleportation to the location wanted.
     *
     * @param closest  Entity to handle
     * @param location Location to teleport to
     */
    private void handleTeleportation(Entity closest, Location location) {
        if (closest == null) return;
        if (!closest.isInvulnerable()) {
            closest.setInvulnerable(true);
            closest.teleport(location);
            closest.setInvulnerable(false);
        } else {
            closest.teleport(location);
        }
    }

}
