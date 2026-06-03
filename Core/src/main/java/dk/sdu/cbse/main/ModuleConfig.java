package dk.sdu.cbse.main;

import dk.sdu.cbse.common.services.IEntityProccessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.cbse.main.util.PluginLayerFactory;
import dk.sdu.cbse.main.util.PluginServices;
import dk.sdu.cbse.scoringclient.spi.IScoringSPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Paths;
import java.util.List;

@Configuration
public class ModuleConfig {
    @Bean
    public Game game(List<IGamePluginService> gamePlugins, List<IEntityProccessingService> processingServices,
                     List<IPostEntityProcessingService> postProcessingServices, ModuleLayer pluginLayer,
                     List<IScoringSPI> scoringSPIs) {

        return new Game(gamePlugins, processingServices, postProcessingServices, pluginLayer, scoringSPIs);
    }

    @Bean
    public ModuleLayer pluginLayer() {
        return PluginLayerFactory.createPluginLayer(Paths.get("plugins"));
    }

    @Bean
    public List<IGamePluginService> gamePlugins(ModuleLayer pluginLayer){
        return PluginServices.load(IGamePluginService.class, pluginLayer);
    }

    @Bean
    public List<IEntityProccessingService> processingServices(ModuleLayer pluginLayer){
        return PluginServices.load( IEntityProccessingService.class, pluginLayer);
    }

    @Bean
    public List<IPostEntityProcessingService> postProcessingService(ModuleLayer pluginLayer){
        return PluginServices.load(IPostEntityProcessingService.class, pluginLayer);
    }

    @Bean
    public List<IScoringSPI> scoringSPIs(ModuleLayer pluginLayer){
        return PluginServices.load(IScoringSPI.class, pluginLayer);
    }
}
