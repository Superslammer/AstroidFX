import dk.sdu.cbse.asteroid.AsteroidCreator;
import dk.sdu.cbse.asteroid.AsteroidHandelingSystem;
import dk.sdu.cbse.asteroid.AsteroidSplitterImpl;
import dk.sdu.cbse.common.asteroid.IAsteroidSPI;
import dk.sdu.cbse.common.asteroid.IAsteroidSplitter;
import dk.sdu.cbse.common.services.IEntityProcessingService;

module Asteroid {
    requires Common;
    requires CommonAsteroid;
    requires javafx.graphics;

    uses IAsteroidSPI;

    provides IEntityProcessingService with AsteroidHandelingSystem;
    provides IAsteroidSPI with AsteroidCreator;
    provides IAsteroidSplitter with AsteroidSplitterImpl;
}