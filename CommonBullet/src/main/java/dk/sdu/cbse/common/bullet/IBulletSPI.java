package dk.sdu.cbse.common.bullet;


import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;

public interface IBulletSPI {
    Bullet createBullet(Entity shooter, GameData gameData);
}
