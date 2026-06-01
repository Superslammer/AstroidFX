package dk.sdu.cbse;

import dk.sdu.cbse.common.asteroid.IAsteroidSPI;
import dk.sdu.cbse.common.enemy.IEnemySPI;
import dk.sdu.cbse.common.services.IEntityProccessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
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
        Game game = new Game(getGamePlugins(), getProcessingServices(), getPostProcessingService(), getEnemySPIs(), getAsteroidSPIs());
        game.start(window);
        game.render();
    }

    private List<IGamePluginService> getGamePlugins(){
        List<IGamePluginService> services = new ArrayList<>();
        ServiceLoader.load(IGamePluginService.class).forEach(services::add);
        return services;
    }

    private List<IEntityProccessingService> getProcessingServices(){
        List<IEntityProccessingService> services = new ArrayList<>();
        ServiceLoader.load(IEntityProccessingService.class).forEach(services::add);
        return services;
    }

    private List<IPostEntityProcessingService> getPostProcessingService(){
        List<IPostEntityProcessingService> services = new ArrayList<>();
        ServiceLoader.load(IPostEntityProcessingService.class).forEach(services::add);
        return services;
    }

    private List<IEnemySPI> getEnemySPIs(){
        List<IEnemySPI> spis = new ArrayList<>();
        ServiceLoader.load(IEnemySPI.class).forEach(spis::add);
        return spis;
    }

    private List<IAsteroidSPI> getAsteroidSPIs(){
        List<IAsteroidSPI> spis = new ArrayList<>();
        ServiceLoader.load(IAsteroidSPI.class).forEach(spis::add);
        return spis;
    }
}
