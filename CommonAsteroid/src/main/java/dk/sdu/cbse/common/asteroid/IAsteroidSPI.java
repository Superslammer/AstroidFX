package dk.sdu.cbse.common.asteroid;

import dk.sdu.cbse.common.data.Vector;

public interface IAsteroidSPI {
    Asteroid createAsteroid(Vector spawnPosition, double angle);
}
