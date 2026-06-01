import dk.sdu.cbse.asteroid.AsteroidHandelingSystem;
import dk.sdu.cbse.common.asteroid.IAsteroidSPI;
import dk.sdu.cbse.common.services.IEntityProccessingService;

module Asteroid {
    requires Common;
    requires CommonAsteroid;

    provides IEntityProccessingService with AsteroidHandelingSystem;
    provides IAsteroidSPI with AsteroidHandelingSystem;
}