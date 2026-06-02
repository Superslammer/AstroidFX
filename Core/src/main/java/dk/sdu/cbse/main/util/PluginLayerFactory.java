package dk.sdu.cbse.main.util;

import java.lang.module.Configuration;
import java.lang.module.ModuleFinder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

public class PluginLayerFactory {
    public static ModuleLayer createPluginLayer(Path pluginDir) {
        try {
            if (!Files.exists(pluginDir)) {
                System.out.println("Plugin directory not found: " + pluginDir);
                return ModuleLayer.boot();
            }

            ModuleFinder finder = ModuleFinder.of(pluginDir);

            Set<String> pluginModules = finder.findAll()
                            .stream()
                            .map(ref -> ref.descriptor().name())
                            .collect(Collectors.toSet());

            if (pluginModules.isEmpty()) {
                return ModuleLayer.boot();
            }

            Configuration configuration = ModuleLayer.boot()
                            .configuration()
                            .resolve(
                                    finder,
                                    ModuleFinder.of(),
                                    pluginModules
                            );

            return ModuleLayer.boot()
                    .defineModulesWithOneLoader(
                            configuration,
                            ClassLoader.getSystemClassLoader()
                    );
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to create plugin layer", e);
        }
    }

}
