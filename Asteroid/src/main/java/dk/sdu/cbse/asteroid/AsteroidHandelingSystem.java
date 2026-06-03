package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.asteroid.Asteroid;
import dk.sdu.cbse.common.asteroid.IAsteroidSPI;
import dk.sdu.cbse.common.asteroid.IAsteroidSplitter;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.Vector;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ServiceLoader;

public class AsteroidHandelingSystem implements IEntityProcessingService {
    private static final double ASTEROID_SPAWN_TIME = 2.5d;

    private double asteroidSpawnCooldown = 0;
    private final IAsteroidSplitter asteroidSplitter = new AsteroidSplitterImpl();

    @Override
    public void proccess(GameData gameData, World world) {
        double deltaT = gameData.getDeltaT();
        for (Entity entity : world.getEntities(Asteroid.class)){
            if(!(entity instanceof Asteroid asteroid)){
                continue;
            }

            // Process hit
            if (asteroid.isHit()){
                if (asteroid.getLife() > 0){
                    asteroidSplitter.createSplitAsteroid(asteroid, world, gameData);
                }
                world.removeEntity(asteroid);
                continue;
            }

            // Remove if outside window
            if (asteroid.getTimeout() <= 0 && isOutsideWindow(asteroid, gameData.getWidth(), gameData.getHeight())) {
                world.removeEntity(entity);
                return;
            }

            // Move asteroid
            Vector vel = asteroid.getVelocity();
            asteroid.setX(asteroid.getX() + vel.getX() * deltaT);
            asteroid.setY(asteroid.getY() + vel.getY() * deltaT);

            // Rotate asteroid
            asteroid.setAngle(asteroid.getAngle() + asteroid.getRotationSpeed());

            // Lower timeout
            asteroid.subtractFromTimeout(deltaT);
        }

        // Spawn new asteroids
        if (asteroidSpawnCooldown <= 0){
            List<IAsteroidSPI> spis = getAsteroidSPIs(gameData);
            if (!spis.isEmpty()){
                spawnAsteroids(gameData, world, spis);
            }

            asteroidSpawnCooldown = ASTEROID_SPAWN_TIME;
        }
        asteroidSpawnCooldown -= deltaT;
    }

    private void spawnAsteroids(GameData gameData, World world, List<IAsteroidSPI> asteroidSPIS){
        Random rng = new Random();
        IAsteroidSPI spi = asteroidSPIS.get(rng.nextInt(asteroidSPIS.size()));

        int spawnSide = rng.nextInt(4);
        Vector startPos = new Vector();
        double angle = 0;
        if (spawnSide == 0){
            // Left side
            startPos.setX(-50);
            startPos.setY(rng.nextInt((int)(gameData.getHeight()/2 - 100), (int)(gameData.getHeight()/2 + 101)));

            angle = 90;
        } else if (spawnSide == 1) {
            // Top side
            startPos.setY(-50);
            startPos.setX(rng.nextInt((int)(gameData.getWidth()/2 - 100), (int)(gameData.getWidth()/2 + 101)));

            angle = 180;
        } else if (spawnSide == 2) {
            // Right side
            startPos.setX(gameData.getWidth() + 50);
            startPos.setY(rng.nextInt((int)(gameData.getHeight()/2 - 100), (int)(gameData.getHeight()/2 + 101)));

            angle = 270;
        } else {
            // Bottom side
            startPos.setY(gameData.getHeight() + 50);
            startPos.setX(rng.nextInt((int)(gameData.getWidth()/2 - 100), (int)(gameData.getWidth()/2 + 100)));

            angle = 0;
        }

        angle = rng.nextDouble(angle-60, angle + 61);

        double magnitude = 1;
        double x =  magnitude * Math.sin(Math.toRadians(angle));
        double y = -magnitude * Math.cos(Math.toRadians(angle));

        world.addEntity(spi.createAsteroid(startPos, new Vector(x, y)));
    }

    private boolean isOutsideWindow(Asteroid asteroid, double width, double height){
        if (asteroid.getX() > width + 50 || asteroid.getX() < -50){
            return true;
        }
        return (asteroid.getY() > height + 50) && !(asteroid.getY() < -50);
    }

    public List<IAsteroidSPI> getAsteroidSPIs(GameData gameData){
        List<IAsteroidSPI> spis = new ArrayList<>();
        ServiceLoader.load(gameData.getPluginLayer(), IAsteroidSPI.class).forEach(spis::add);
        return spis;
    }
}
