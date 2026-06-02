package dk.sdu.cbse.main;

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
        return new Game(gamePlugins(), processingServices(), postProcessingService());
    }

    @Bean
    public List<IGamePluginService> gamePlugins(){
        List<IGamePluginService> services = new ArrayList<>();
        ServiceLoader.load(IGamePluginService.class).forEach(services::add);
        return services;
    }

    @Bean
    public List<IEntityProccessingService> processingServices(){
        List<IEntityProccessingService> services = new ArrayList<>();
        ServiceLoader.load(IEntityProccessingService.class).forEach(services::add);
        return services;
    }

    @Bean
    public List<IPostEntityProcessingService> postProcessingService(){
        List<IPostEntityProcessingService> services = new ArrayList<>();
        ServiceLoader.load(IPostEntityProcessingService.class).forEach(services::add);
        return services;
    }
}
