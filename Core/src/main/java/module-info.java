import dk.sdu.cbse.common.enemy.IEnemySPI;
import dk.sdu.cbse.common.services.IEntityProccessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Core {
    requires javafx.graphics;
    requires Common;
    requires CommonEnemy;

    uses IGamePluginService;
    uses IEntityProccessingService;
    uses IEnemySPI;

    exports dk.sdu.cbse;
}