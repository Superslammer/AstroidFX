import dk.sdu.cbse.common.services.IEntityProccessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Core {
    requires javafx.graphics;
    requires Common;

    uses IGamePluginService;
    uses IEntityProccessingService;

    exports dk.sdu.cbse;
}