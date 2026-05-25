package dk.sdu.cbse.common.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class World {
    private final Map<String, Entity> entities = new ConcurrentHashMap<>();

    public void addEntity(Entity e) {
        entities.put(e.getID(), e);
    }

    public Collection<Entity> getEntities(){
        return entities.values();
    }

    @SafeVarargs
    public final <E extends Entity> List<Entity> getEntities(Class<E>... types){
        List<Entity> toReturn = new ArrayList<>();
        for (Entity entity : getEntities()){
            for (Class<E> type : types){
                if (type.equals(entity.getClass())) {
                    toReturn.add(entity);
                }
            }
        }

        return toReturn;
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity.getID());
    }
}
