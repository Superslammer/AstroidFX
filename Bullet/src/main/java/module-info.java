import dk.sdu.cbse.bullet.BulletHandelingSystem;
import dk.sdu.cbse.common.bullet.IBulletSPI;
import dk.sdu.cbse.common.services.IEntityProccessingService;

module Bullet{
    requires Common;
    requires CommonBullet;
    requires javafx.graphics;

    uses dk.sdu.cbse.common.services.IEntityProccessingService;
    uses dk.sdu.cbse.common.bullet.IBulletSPI;

    provides IBulletSPI with BulletHandelingSystem;
    provides IEntityProccessingService with BulletHandelingSystem;
}