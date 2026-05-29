package dk.sdu.cbse.common.bullet;


import dk.sdu.cbse.common.data.Entity;

public interface IBulletSPI {
    Bullet createBullet(Entity shooter);
}
