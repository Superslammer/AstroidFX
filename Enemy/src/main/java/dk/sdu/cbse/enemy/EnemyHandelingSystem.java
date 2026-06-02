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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ServiceLoader;

public class EnemyHandelingSystem implements IEntityProccessingService, IEnemySPI {
    private static final double ENEMY_SPAWN_TIME = 10d;
    private double enemySpawnCooldown = 0;


    @Override
    public void proccess(GameData gameData, World world) {
        double deltaT = gameData.getDeltaT();
        for (Entity entity : world.getEntities(Enemy.class)){
            if(!(entity instanceof Enemy enemy)){
                continue;
            }

            // Remove if hit
            if (enemy.isHit()){
                world.removeEntity(enemy);
                continue;
            }

            // Remove if outside window
            if (enemy.getTimeout() <= 0 && isOutsideWindow(enemy, gameData.getWidth(), gameData.getHeight())) {
                world.removeEntity(entity);
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

            // Lower timeout
            enemy.subtractFromTimeout(deltaT);
        }

        // Spawn new enemies
        if (enemySpawnCooldown <= 0){
            List<IEnemySPI> spis = getEnemySPIs();
            if(!spis.isEmpty()){
                spawnEnemies(gameData, world, spis);
            }

            enemySpawnCooldown = ENEMY_SPAWN_TIME;
        }
    }

    private void spawnEnemies(GameData gameData, World world, List<IEnemySPI> enemySPIS){
        Random rng = new Random();
        IEnemySPI spi = enemySPIS.get(rng.nextInt(enemySPIS.size()));

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

        angle = rng.nextDouble(angle-30, angle + 31);
        world.addEntity(spi.createEnemy(startPos, angle));
        enemySpawnCooldown = ENEMY_SPAWN_TIME;
    }

    public List<IEnemySPI> getEnemySPIs(){
        List<IEnemySPI> spis = new ArrayList<>();
        ServiceLoader.load(IEnemySPI.class).forEach(spis::add);
        return spis;
    }

    private boolean isOutsideWindow(Enemy enemy, double width, double height){
        if (enemy.getX() > width + 50 || enemy.getX() < -50){
            return true;
        }
        return (enemy.getY() > height + 50) && !(enemy.getY() < -50);
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

        enemy.setHitBox(9);

        return enemy;
    }
}
