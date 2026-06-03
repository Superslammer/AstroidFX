import dk.sdu.cbse.bullet.BulletHandelingSystem;
import dk.sdu.cbse.common.bullet.IBulletSPI;
import dk.sdu.cbse.common.services.IEntityProcessingService;

module Bullet{
    requires Common;
    requires CommonBullet;
    requires javafx.graphics;

    uses IEntityProcessingService;
    uses dk.sdu.cbse.common.bullet.IBulletSPI;

    provides IBulletSPI with BulletHandelingSystem;
    provides IEntityProcessingService with BulletHandelingSystem;
}