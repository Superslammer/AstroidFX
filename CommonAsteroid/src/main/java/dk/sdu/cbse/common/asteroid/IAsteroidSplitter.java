package dk.sdu.cbse.common.asteroid;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public interface IAsteroidSplitter {
    void createSplitAsteroid(Asteroid entity, World world, GameData gameData);
}
