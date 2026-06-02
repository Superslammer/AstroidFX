package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.asteroid.Asteroid;
import dk.sdu.cbse.common.asteroid.IAsteroidSPI;
import dk.sdu.cbse.common.data.Vector;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.Random;

public class AsteroidCreator implements IAsteroidSPI {
    private static final double MAX_MOVEMENT_SPEED = 200d;
    private static final double MIN_MOVEMENT_SPEED = 100d;
    private static final double MAX_SPIN_SPEED = 4d;
    private static final double MIN_SPIN_SPEED = -4d;

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
}
