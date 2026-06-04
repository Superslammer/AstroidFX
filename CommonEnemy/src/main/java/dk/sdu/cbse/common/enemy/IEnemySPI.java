package dk.sdu.cbse.common.enemy;

import dk.sdu.cbse.common.data.Vector;

public interface IEnemySPI {
    /**
     * Creates an enemy with it’s coordinates set to spawnPosition and it’s angle set to angle.
     * <p><b>Pre-conditions:</b></p>
     * <ul>
     *     <li>{@code spawnPosition} and {@code angle} are not null.</li>
     *     <li>{@code angle} is between 0 and 360</li>
     * </ul>
     *
     * <p><b>Post-conditions:</b></p>
     * <ul>
     *     <li>Returns a new {@code Enemy} object.</li>
     * </ul>
     *
     * @param spawnPosition Is the position the enemy should spawn.
     * @param angle Is the angle the enemy should point.
     */
    Enemy createEnemy(Vector spawnPosition, double angle);
}
