import dk.sdu.cbse.common.asteroid.IAsteroidSPI;
import dk.sdu.cbse.common.enemy.IEnemySPI;
import dk.sdu.cbse.common.services.IEntityProccessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Core {
    requires javafx.graphics;
    requires Common;
    requires CommonEnemy;
    requires CommonAsteroid;

    uses IGamePluginService;
    uses IEntityProccessingService;
    uses IEnemySPI;
    uses IAsteroidSPI;
    uses dk.sdu.cbse.common.services.IPostEntityProcessingService;

    exports dk.sdu.cbse;
}