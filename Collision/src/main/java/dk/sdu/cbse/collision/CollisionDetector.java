package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.bullet.Bullet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

public class CollisionDetector implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity collider1 : world.getEntities()){
            for (Entity collider2 : world.getEntities()){
                if (collider1.getID().equals(collider2.getID())){
                    continue;
                }

                if (collides(collider1, collider2) && !isPlayerBulletCol(collider1, collider2, gameData.getPlayerID())){
                    collider1.setHit(true);
                    collider2.setHit(true);
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
        double radius = c1.getHitBox()/2 + c2.getHitBox()/2;
        return (dx * dx + dy * dy) <= (radius * radius);
    }
}
