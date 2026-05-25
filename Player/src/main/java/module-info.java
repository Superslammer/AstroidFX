import dk.sdu.cbse.common.services.IEntityProccessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.player.PlayerHandelingSystem;
import dk.sdu.cbse.player.PlayerPlugin;

module Player {
    requires Common;
    requires CommonBullet;
    requires javafx.graphics;

    uses dk.sdu.cbse.common.services.IEntityProccessingService;
    uses dk.sdu.cbse.common.bullet.IBulletSPI;

    provides IEntityProccessingService with PlayerHandelingSystem;
    provides IGamePluginService with PlayerPlugin;
}