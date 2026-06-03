package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.bullet.Bullet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.Vector;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.bullet.IBulletSPI;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class BulletHandelingSystem implements IEntityProcessingService, IBulletSPI {
    private static final double BULLET_SPEED = 2d;

    @Override
    public Bullet createBullet(Entity shooter) {
        Bullet newBullet = new Bullet();
        newBullet.setAngle(shooter.getAngle());
        newBullet.setX(shooter.getX());
        newBullet.setY(shooter.getY());

        // Create the initial velocity vector
        Vector vel = new Vector();
        double magnitude = BULLET_SPEED;
        vel.setX(magnitude * Math.sin(Math.toRadians(newBullet.getAngle())));
        vel.setY(magnitude * -Math.cos(Math.toRadians(newBullet.getAngle())));
        newBullet.setVelocity(vel);

        // Create bullet polygon
        Polygon sprite = new Polygon(
                1, 1,
                1, -1,
                -1, -1,
                -1, 1
        );
        sprite.setFill(Color.TRANSPARENT);
        sprite.setStroke(Color.WHITE);
        sprite.setStrokeWidth(2d);
        newBullet.setSprite(sprite);

        newBullet.setHitBox(1);

        return newBullet;
    }

    @Override
    public void proccess(GameData gameData, World world) {
        // Get bullet entities
        for (Entity entity : world.getEntities(Bullet.class)){
            if (!(entity instanceof Bullet bullet)){
                continue;
            }

            // Remove bullet if hit
            if (bullet.isHit()){
                world.removeEntity(bullet);
                continue;
            }

            // Remove bullet if outside window
            if (bullet.getX() > gameData.getWidth() || bullet.getX() < 0){
                world.removeEntity(bullet);
                return;
            }
            if (bullet.getY() > gameData.getHeight() || bullet.getY() < 0){
                world.removeEntity(bullet);
                return;
            }

            // Move them according to velocity
            Vector vel = bullet.getVelocity();
            bullet.setX(bullet.getX() + vel.getX());
            bullet.setY(bullet.getY() + vel.getY());

            bullet.subITime(gameData.getDeltaT());
        }
    }
}
