package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.asteroid.Asteroid;
import dk.sdu.cbse.common.bullet.Bullet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.enemy.Enemy;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.cbse.scoringclient.spi.IScoringSPI;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class CollisionDetector implements IPostEntityProcessingService {
    List<IScoringSPI> scoringSPIs = null;

    @Override
    public void process(GameData gameData, World world) {
        if (scoringSPIs == null){
            scoringSPIs = getScoringSPIs(gameData);
        }

        for (Entity collider1 : world.getEntities()){
            for (Entity collider2 : world.getEntities()){
                if (collider1.getClass().equals(collider2.getClass())){
                     continue;
                }

                if (collider1.getID().equals(collider2.getID())){
                    continue;
                }

                if (collides(collider1, collider2) && !isPlayerBulletCol(collider1, collider2, gameData.getPlayerID())){
                    collider1.setHit(true);
                    collider2.setHit(true);

                    if (collider1 instanceof Asteroid && !scoringSPIs.isEmpty()){
                        IScoringSPI spi = scoringSPIs.getFirst();
                        if (spi != null){
                            spi.addScore(1);
                        }
                    }

                    if (collider1 instanceof Enemy && !scoringSPIs.isEmpty()){
                        IScoringSPI spi = scoringSPIs.getFirst();
                        if (spi != null){
                            spi.addScore(5);
                        }
                    }
                }
            }
        }
    }

    private boolean isPlayerBulletCol(Entity c1, Entity c2, String playerID){
        if (c1 instanceof Bullet && c2.getID().equals(playerID)){
            return true;
        }
        if (c2 instanceof Bullet && c1.getID().equals(playerID)){
            return true;
        }

        return false;
    }

    private boolean collides(Entity c1, Entity c2) {
        double dx = c1.getX() - c2.getX();
        double dy = c1.getY() - c2.getY();
        double radius = c1.getHitBox() + c2.getHitBox();
        return (dx * dx + dy * dy) <= (radius * radius);
    }

    private List<IScoringSPI> getScoringSPIs(GameData gameData){
        List<IScoringSPI> spis = new ArrayList<>();
        ServiceLoader.load(gameData.getPluginLayer(), IScoringSPI.class).forEach(spis::add);
        return spis;
    }
}
