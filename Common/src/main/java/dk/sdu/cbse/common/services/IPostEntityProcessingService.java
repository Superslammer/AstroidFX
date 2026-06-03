package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

/**
 * Service interface for managing entity postprocessing.
 */
public interface IPostEntityProcessingService {
    /**
     * Does all the postprocessing an entity need after the current update cycle.
     * <p><b>Pre-conditions:</b></p>
     * <ul>
     *     <li>{@code gameData} and {@code world} are not null.</li>
     *     <li>All {@code IEntityProccessingService} implementations have been called.</li>
     *     <li>All entities in the {@code world} are not null.</li>
     * </ul>
     *
     * <p><b>Post-conditions:</b></p>
     * <ul>
     *     <li>All entity states have been updated for this postprocessing cycle.</li>
     * </ul>
     *
     * @param gameData Contains the game state.
     * @param world Contains all instantiated entities in the game.
     */
    void process(GameData gameData, World world);
}
