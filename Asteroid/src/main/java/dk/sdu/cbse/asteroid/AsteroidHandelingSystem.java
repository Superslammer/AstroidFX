package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.asteroid.Asteroid;
import dk.sdu.cbse.common.asteroid.IAsteroidSPI;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.Vector;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProccessingService;

public class AsteroidHandelingSystem implements IEntityProccessingService, IAsteroidSPI {
    @Override
    public Asteroid createAsteroid(Vector spawnPosition, double angle) {
        return null;
    }

    @Override
    public void proccess(GameData gameData, World world) {
    }

}
