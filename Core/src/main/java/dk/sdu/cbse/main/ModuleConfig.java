package dk.sdu.cbse.main;

import dk.sdu.cbse.common.asteroid.IAsteroidSPI;
import dk.sdu.cbse.common.enemy.IEnemySPI;
import dk.sdu.cbse.common.services.IEntityProccessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

@Configuration
public class ModuleConfig {
    public ModuleConfig() {

    }

    @Bean
    public Game game(){
        return new Game(getGamePlugins(), getProcessingServices(), getPostProcessingService(), getEnemySPIs(), getAsteroidSPIs());
    }

    @Bean
    public List<IGamePluginService> getGamePlugins(){
        List<IGamePluginService> services = new ArrayList<>();
        ServiceLoader.load(IGamePluginService.class).forEach(services::add);
        return services;
    }

    @Bean
    public List<IEntityProccessingService> getProcessingServices(){
        List<IEntityProccessingService> services = new ArrayList<>();
        ServiceLoader.load(IEntityProccessingService.class).forEach(services::add);
        return services;
    }

    @Bean
    public List<IPostEntityProcessingService> getPostProcessingService(){
        List<IPostEntityProcessingService> services = new ArrayList<>();
        ServiceLoader.load(IPostEntityProcessingService.class).forEach(services::add);
        return services;
    }

    @Bean
    public List<IEnemySPI> getEnemySPIs(){
        List<IEnemySPI> spis = new ArrayList<>();
        ServiceLoader.load(IEnemySPI.class).forEach(spis::add);
        return spis;
    }

    @Bean
    public List<IAsteroidSPI> getAsteroidSPIs(){
        List<IAsteroidSPI> spis = new ArrayList<>();
        ServiceLoader.load(IAsteroidSPI.class).forEach(spis::add);
        return spis;
    }
}
