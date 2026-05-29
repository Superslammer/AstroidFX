package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.Vector;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.enemy.Enemy;
import dk.sdu.cbse.common.enemy.IEnemySPI;
import dk.sdu.cbse.common.services.IEntityProccessingService;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class EnemyHandelingSystem implements IEntityProccessingService, IEnemySPI {
    @Override
    public void proccess(GameData gameData, World world) {
        double deltaT = gameData.getDeltaT();
        for (Entity entity : world.getEntities(Enemy.class)){
            if(!(entity instanceof Enemy enemy)){
                continue;
            }

            // Change flight angle
            enemy.processBounce(deltaT);

            // Get velocity
            double angle = enemy.getAngle();
            double magnitude = enemy.getMovementSpeed();
            double x =  magnitude * Math.sin(Math.toRadians(angle));
            double y = -magnitude * Math.cos(Math.toRadians(angle));
            Vector vel = new Vector(x, y);
            enemy.setVelocity(vel);

            // Move enemy
            vel = enemy.getVelocity();
            enemy.setX(enemy.getX() + vel.getX() * deltaT);
            enemy.setY(enemy.getY() + vel.getY() * deltaT);

        }
    }

    @Override
    public Enemy createEnemy(Vector spawnPosition, double angle) {
        Enemy enemy = new Enemy();
        enemy.setPosition(spawnPosition);
        enemy.setAngle(Math.abs(angle)+enemy.getBounceAngle());

        Polygon sprite =new Polygon(
                0.0, -10.0,
                6.0, -4.0,
                12.0,  0.0,
                8.0,  6.0,
                -8.0,  6.0,
                -12.0,  0.0,
                -6.0, -4.0
        );
        sprite.setFill(Color.TRANSPARENT);
        sprite.setStroke(Color.RED);
        sprite.setStrokeWidth(2d);
        enemy.setSprite(sprite);

        return enemy;
    }
}
