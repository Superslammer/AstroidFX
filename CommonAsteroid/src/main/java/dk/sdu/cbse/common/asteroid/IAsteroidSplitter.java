package dk.sdu.cbse.common.asteroid;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public interface IAsteroidSplitter {
    /**
     * Removes the given asteroid and spawns two new smaller asteroids in its place.
     * <p><b>Pre-conditions:</b></p>
     * <ul>
     *     <li>{@code entity}, {@code world} and {@code gameData} are not null.</li>
     *     <li>{@code entity} has a sprite set.</li>
     *     <li>{@code entity} has more than 0 life.</li>
     * </ul>
     *
     * <p><b>Post-conditions:</b></p>
     * <ul>
     *     <li>{@code entity} is remove from {@code world}.</li>
     *     <li>Two new smaller asteroids are added to the {@code world}.</li>
     *     <li>The new asteroids have one less life then {@code entity}.</li>
     * </ul>
     *
     * @param entity Is the asteroid that needs to be removed
     * @param world Holds all instantiated entities.
     * @param gameData Holds the game state.
     */
    void createSplitAsteroid(Asteroid entity, World world, GameData gameData);
}
