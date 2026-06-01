package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.asteroid.Asteroid;
import dk.sdu.cbse.common.asteroid.IAsteroidSPI;
import dk.sdu.cbse.common.asteroid.IAsteroidSplitter;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.Vector;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProccessingService;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.Random;

public class AsteroidHandelingSystem implements IEntityProccessingService, IAsteroidSPI {
    private static final double MAX_MOVEMENT_SPEED = 200d;
    private static final double MIN_MOVEMENT_SPEED = 100d;
    private static final double MAX_SPIN_SPEED = 4d;
    private static final double MIN_SPIN_SPEED = -4d;

    private final IAsteroidSplitter asteroidSplitter = new AsteroidSplitterImpl();

    @Override
    public Asteroid createAsteroid(Vector spawnPosition, Vector velocity) {
        Asteroid ast = new Asteroid();
        ast.setPosition(spawnPosition);

        Random rng = new Random();
        velocity.normalize().scale(rng.nextDouble(MIN_MOVEMENT_SPEED, MAX_MOVEMENT_SPEED));
        ast.setVelocity(velocity);

        ast.setRotationSpeed(rng.nextDouble(MIN_SPIN_SPEED, MAX_SPIN_SPEED));

        Polygon sprite = getSprite();
        ast.setSprite(sprite);

        ast.setHitBox(15);

        return ast;
    }

    private static Polygon getSprite() {
        double scale = 0.3d;
        Polygon sprite = new Polygon(
                0.0, -50.0,
                30.0, -35.0,
                50.0, -10.0,
                40.0,  25.0,
                20.0,  50.0,
                -15.0,  45.0,
                -45.0,  25.0,
                -50.0, -10.0,
                -30.0, -40.0
        );
        sprite.setScaleX(scale);
        sprite.setScaleY(scale);
        sprite.setFill(Color.TRANSPARENT);
        sprite.setStroke(Color.WHITE);
        sprite.setStrokeWidth(2d / scale);
        return sprite;
    }

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
    }

    private boolean isOutsideWindow(Asteroid asteroid, double width, double height){
        if (asteroid.getX() > width + 50 || asteroid.getX() < -50){
            return true;
        }
        return (asteroid.getY() > height + 50) && !(asteroid.getY() < -50);
    }

}
