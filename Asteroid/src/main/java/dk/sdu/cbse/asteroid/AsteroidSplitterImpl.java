package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.asteroid.Asteroid;
import dk.sdu.cbse.common.asteroid.IAsteroidSplitter;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.Vector;
import dk.sdu.cbse.common.data.World;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class AsteroidSplitterImpl implements IAsteroidSplitter {
    @Override
    public void createSplitAsteroid(Asteroid asteroid, World world, GameData gameData) {
        Polygon originalSprite = asteroid.getSprite();
        double newScale = originalSprite.getScaleX()/2;

        // Create split up
        Asteroid astUp = new Asteroid();
        astUp.setPosition(new Vector(asteroid.getPosition()));

        Vector velUp = new Vector(asteroid.getVelocity());
        velUp.rotate(-30);
        astUp.setVelocity(velUp);

        astUp.setHitBox(asteroid.getHitBox()/2);

        Polygon spriteUp = createPolygon(newScale, copyPolygon(originalSprite));
        astUp.setSprite(spriteUp);

        astUp.setRotationSpeed(asteroid.getRotationSpeed()*2);

        world.addEntity(astUp);

        // Create split down
        Asteroid astDown = new Asteroid();
        astDown.setPosition(new Vector(asteroid.getPosition()));

        Vector velDown = new Vector(asteroid.getVelocity());
        velDown.rotate(30);
        astDown.setVelocity(velDown);

        astDown.setHitBox(asteroid.getHitBox()/2);

        Polygon spriteDown = createPolygon(newScale, copyPolygon(originalSprite));
        astDown.setSprite(spriteDown);

        astDown.setRotationSpeed(asteroid.getRotationSpeed()*2);

        world.addEntity(astDown);

        // Move couple pixels forward
        Vector vel = astUp.getVelocity();
        astUp.setX(astUp.getX() + vel.getX() * gameData.getDeltaT() * 10);
        astUp.setY(astUp.getY() + vel.getY() * gameData.getDeltaT() * 10);

        vel = astDown.getVelocity();
        astDown.setX(astDown.getX() + vel.getX() * gameData.getDeltaT() * 10);
        astDown.setY(astDown.getY() + vel.getY() * gameData.getDeltaT() * 10);

    }

    private Polygon createPolygon(double scale, double[] points){
        Polygon spriteUp = new Polygon(points);
        spriteUp.setScaleX(scale);
        spriteUp.setScaleY(scale);
        spriteUp.setFill(Color.TRANSPARENT);
        spriteUp.setStroke(Color.WHITE);
        spriteUp.setStrokeWidth(2d / scale);
        return spriteUp;
    }

    private double[] copyPolygon(Polygon original){
        return original.getPoints().stream().mapToDouble(Double::doubleValue).toArray();
    }
}
