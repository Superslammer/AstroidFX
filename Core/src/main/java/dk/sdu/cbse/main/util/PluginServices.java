package dk.sdu.cbse.main.util;

import java.util.*;

public final class PluginServices {

    private PluginServices() {}

    public static <T> List<T> load(Class<T> service, ModuleLayer pluginLayer) {
        Map<Class<?>, T> unique = new LinkedHashMap<>();
        ServiceLoader.load(service)
                .forEach(s ->
                        unique.put(
                                s.getClass(),
                                s));

        if (!pluginLayer.modules().isEmpty()) {

            ServiceLoader.load(pluginLayer, service)
                    .forEach(s ->
                            unique.put(
                                    s.getClass(),
                                    s));
        }

        return new ArrayList<>(unique.values());
    }
}