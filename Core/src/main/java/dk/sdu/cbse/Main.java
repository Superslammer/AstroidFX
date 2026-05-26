package dk.sdu.cbse;

import dk.sdu.cbse.common.services.IEntityProccessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class Main extends Application{
    public static void main(String[] args) {
        launch(Main.class);
    }

    @Override
    public void start(Stage window) throws Exception {
        Game game = new Game(getGamePlugins(), getProccessingServices());
        game.start(window);
        game.render();
    }

    private List<IGamePluginService> getGamePlugins(){
        List<IGamePluginService> services = new ArrayList<>();
        ServiceLoader.load(IGamePluginService.class).forEach(services::add);
        return services;
    }

    private List<IEntityProccessingService> getProccessingServices(){
        List<IEntityProccessingService> services = new ArrayList<>();
        ServiceLoader.load(IEntityProccessingService.class).forEach(services::add);
        return services;
    }
}
