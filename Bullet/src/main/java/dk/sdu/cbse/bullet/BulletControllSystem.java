package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.bullet.Bullet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.Vector;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProccessingService;
import dk.sdu.cbse.common.bullet.IBulletSPI;

public class BulletControllSystem implements IEntityProccessingService, IBulletSPI {

    @Override
    public Bullet createBullet(Entity shooter, GameData gameData) {
        Bullet newBullet = new Bullet();
        newBullet.setAngle(shooter.getAngle());
        newBullet.setX(shooter.getX());
        newBullet.setY(shooter.getY());

        // Create the initial velocity vector
        Vector vel = new Vector();
        double magnitude = 1d;
        vel.setX(magnitude * Math.cos(Math.toRadians(newBullet.getAngle())));
        vel.setY(magnitude * Math.sin(Math.toRadians(newBullet.getAngle())));

        newBullet.setVelocity(vel);
        newBullet.setBoundingBox(new double[][]{new double[]{1, -1}, new double[]{1, 1}, new double[]{-1, 1}, new double[]{-1, -1}});
        return newBullet;
    }

    @Override
    public void proccess(GameData gameData, World world) {
        // Get bullet entities
        // Move them according to angle and velocity
    }
}
