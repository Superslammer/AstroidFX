import dk.sdu.cbse.asteroid.AsteroidHandelingSystem;
import dk.sdu.cbse.asteroid.AsteroidSplitterImpl;
import dk.sdu.cbse.common.asteroid.IAsteroidSPI;
import dk.sdu.cbse.common.asteroid.IAsteroidSplitter;
import dk.sdu.cbse.common.services.IEntityProccessingService;

module Asteroid {
    requires Common;
    requires CommonAsteroid;
    requires javafx.graphics;

    provides IEntityProccessingService with AsteroidHandelingSystem;
    provides IAsteroidSPI with AsteroidHandelingSystem;
    provides IAsteroidSplitter with AsteroidSplitterImpl;
}