package me.hex.entityserializer;

import me.hex.entityserializer.api.EntityHolder;
import me.hex.entityserializer.core.StructureFactory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.structure.StructureManager;

import java.util.Random;

public final class EntitySerializer {

    private static JavaPlugin plugin;
    private static EntityHolder entityHolder;
    private static StructureFactory factory;
    private static Random random;

    /**
     * Gets the Main instance.
     *
     * @return Main instance of User's plugin if set, otherwise null
     */
    public static JavaPlugin getPlugin() {
        return plugin;
    }

    /**
     * Initializes the API, With the User's Main Instance.
     * REQUIRED TO USE THE API.
     *
     * @param plugin User's Main Instance.
     */
    public static void setPlugin(JavaPlugin plugin) {
        EntitySerializer.plugin = plugin;
        initialize();
    }

    /**
     * Checks if the main class instance was set.
     *
     * @return true if set, otherwise false.
     */
    public static boolean isPluginSet() {
        return plugin != null;
    }

    /**
     * IMPORTANT NOTE: THIS SHOULD NOT BE USED OUTSIDE OF API INTERNALS. THIS IS NOT FOR YOU.
     *
     * @return StructureManager Object
     */
    public static StructureManager getManager() {
        return plugin.getServer().getStructureManager();
    }

    /**
     * you should use this to serialize, and to deserialize.
     *
     * @return EntityHolder Object.
     */
    public static EntityHolder getEntityHolder() {
        return entityHolder;
    }

    /**
     * Initializes variables.
     * IMPORTANT NOTE: THIS SHOULD NOT BE USED OUTSIDE OF API INTERNALS. THIS IS NOT FOR YOU.
     */
    private static void initialize() {
        entityHolder = new EntityHolder();
        factory = new StructureFactory();
        random = new Random();
    }

    /**
     * IMPORTANT NOTE: THIS SHOULD NOT BE USED OUTSIDE OF API INTERNALS. THIS IS NOT FOR YOU.
     *
     * @return StructureFactory Instance
     */
    public static StructureFactory getFactory() {
        return factory;
    }

    /**
     * Gets the random object used for Structure API methods.
     *
     * @return Random Object.
     */
    public static Random getRandom() {
        return random;
    }

}
