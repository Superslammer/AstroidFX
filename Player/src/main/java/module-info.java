import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.player.PlayerHandelingSystem;
import dk.sdu.cbse.player.PlayerPlugin;

module Player {
    requires Common;
    requires CommonBullet;
    requires javafx.graphics;

    uses IEntityProcessingService;
    uses dk.sdu.cbse.common.bullet.IBulletSPI;

    provides IEntityProcessingService with PlayerHandelingSystem;
    provides IGamePluginService with PlayerPlugin;
}