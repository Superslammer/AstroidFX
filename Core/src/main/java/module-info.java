import dk.sdu.cbse.common.asteroid.IAsteroidSPI;
import dk.sdu.cbse.common.enemy.IEnemySPI;
import dk.sdu.cbse.common.services.IEntityProccessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Core {
    requires javafx.graphics;
    requires Common;
    requires CommonEnemy;
    requires CommonAsteroid;
    requires spring.context;
    requires spring.beans;

    uses IGamePluginService;
    uses IEntityProccessingService;
    uses IEnemySPI;
    uses IAsteroidSPI;
    uses dk.sdu.cbse.common.services.IPostEntityProcessingService;

    opens dk.sdu.cbse.main to spring.core, spring.beans, spring.context, javafx.graphics;

    exports dk.sdu.cbse.main;
}