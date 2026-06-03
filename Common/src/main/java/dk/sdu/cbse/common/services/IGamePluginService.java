package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

/**
 * Service interface for managing plugins.
 * Plugins are responsible creating and removing entities tied to it.
 */
public interface IGamePluginService {
    /**
     * Initialized the plugin and all it's entities.
     * <p><b>Pre-conditions:</b></p>
     * <ul>
     *     <li>{@code gameData} and {@code world} are not null.</li>
     *     <li>The plugin has not already been initialised</li>
     * </ul>
     *
     * <p><b>Post-conditions:</b></p>
     * <ul>
     *     <li>One or more entities have been added to the world</li>
     *     <li>{@code gameData} modified to accommodate entities</li>
     * </ul>
     *
     * @param gameData Contains the game state.
     * @param world Contains all instantiated entities in the game.
     */
    void init(GameData gameData, World world);

    /**
     * Destroys the plugin and all it's entities.
     * <p><b>Pre-conditions:</b></p>
     * <ul>
     *     <li>{@code gameData} and {@code world} are not null.</li>
     *     <li>The plugin has already been initialised</li>
     *     <li>The plugin has not already been destroyed</li>
     * </ul>
     *
     * <p><b>Post-conditions:</b></p>
     * <ul>
     *     <li>All entities added by the plugin have been removed from {@code world}</li>
     * </ul>
     *
     * @param gameData Contains the game state.
     * @param world Contains all instantiated entities in the game.
     */
    void destroy(GameData gameData, World world);
}
