import dk.sdu.cbse.common.services.IEntityProccessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.cbse.scoringclient.spi.IScoringSPI;

module Core {
    requires javafx.graphics;
    requires Common;
    requires CommonEnemy;
    requires CommonAsteroid;
    requires spring.context;
    requires spring.beans;
    requires ScoringClient;

    uses IGamePluginService;
    uses IEntityProccessingService;
    uses IPostEntityProcessingService;
    uses IScoringSPI;

    opens dk.sdu.cbse.main to spring.core, spring.beans, spring.context, javafx.graphics;

    exports dk.sdu.cbse.main;
}