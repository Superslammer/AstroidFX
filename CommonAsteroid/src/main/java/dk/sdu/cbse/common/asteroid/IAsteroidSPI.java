package dk.sdu.cbse.common.asteroid;

import dk.sdu.cbse.common.data.Vector;

public interface IAsteroidSPI {
    /**
     * Creates an asteroid with it’s coordinates set to spawnPosition and it’s velocity set to velocity.
     * <p><b>Pre-conditions:</b></p>
     * <ul>
     *     <li>{@code spawnPosition} and velocity are not null.</li>
     * </ul>
     *
     * <p><b>Post-conditions:</b></p>
     * <ul>
     *     <li>Returns a new {@code Asteroid} object.</li>
     * </ul>
     *
     * @param spawnPosition Holds the coordinates the for the asteroid.
     * @param velocity Holds the velocity for the asteroid.
     */
    Asteroid createAsteroid(Vector spawnPosition, Vector velocity);
}
