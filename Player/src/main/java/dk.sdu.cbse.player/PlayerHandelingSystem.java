package dk.sdu.cbse.player;

import dk.sdu.cbse.common.bullet.IBulletSPI;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.Vector;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProccessingService;

import java.util.ServiceLoader;
import java.util.stream.Stream;

public class PlayerHandelingSystem implements IEntityProccessingService {
    private static final double BULLET_COOLDOWN_SECONDS = 2d;
    private static final double PLAYER_DRAG = 2d;
    private double currentBulletCooldown = 0;

    @Override
    public void proccess(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Player.class)){
            // Make sure it's a player entity
            if(!(entity instanceof Player player)){
                continue;
            }

            // Handle player input
            if(gameData.isLeft()){
                player.setAngle(player.getAngle() - player.getRotationAngle());
            }
            if(gameData.isRight()){
                player.setAngle(player.getAngle() + player.getRotationAngle());
            }
            if(gameData.isForwards()){
                Vector vel = new Vector();
                double magnitude = player.getMovementSpeed();
                vel.setX(magnitude * Math.cos(Math.toRadians(player.getAngle())));
                vel.setY(magnitude * Math.sin(Math.toRadians(player.getAngle())));

                player.setVelocity(vel.add(player.getVelocity()));
            }
            if(gameData.isBackwards()){
                Vector vel = new Vector();
                double magnitude = player.getMovementSpeed();
                vel.setX(-(magnitude * Math.cos(Math.toRadians(player.getAngle()))));
                vel.setY(-(magnitude * Math.sin(Math.toRadians(player.getAngle()))));

                player.setVelocity(vel.add(player.getVelocity()));
            }
            if(gameData.isShoot() && currentBulletCooldown <= 0){
                System.out.println("Shoot!");
                getBulletSPIs().findFirst().ifPresent(
                        spi -> world.addEntity(spi.createBullet(player, gameData))
                );
                currentBulletCooldown = BULLET_COOLDOWN_SECONDS;
            }

            // Move the player
            Vector vel = player.getVelocity();
            player.setX(player.getX() + vel.getX());
            player.setY(player.getY() + vel.getY());
        }

        // Decrease cooldowns
        currentBulletCooldown -= deltaT;
    }

    private Stream<IBulletSPI> getBulletSPIs(){
        return ServiceLoader.load(IBulletSPI.class).stream().map(ServiceLoader.Provider::get);
    }
}
