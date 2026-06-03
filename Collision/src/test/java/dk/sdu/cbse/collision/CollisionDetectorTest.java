package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.asteroid.Asteroid;
import dk.sdu.cbse.common.bullet.Bullet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.enemy.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CollisionDetectorTest {
    CollisionDetector detector;
    GameData gameData;
    World world;
    ScoringSPIMock scoringSPIMock;

    @BeforeEach
    void setUp(){
        detector = new CollisionDetector();
        gameData = new GameData();
        world = new World();
        scoringSPIMock = new ScoringSPIMock();

        detector.scoringSPIs = List.of(scoringSPIMock);
    }

    @Test
    @DisplayName("Same objects doesn't collide")
    void testSameObjectCollision(){
        // Arrange
        Entity entity = new Entity();
        entity.setHitBox(10);

        world.addEntity(entity);

        // Act
        detector.process(gameData, world);

        // Assert
        assertFalse(entity.isHit());
    }

    @Test
    @DisplayName("Same class types doesn't collide")
    void testSameClassTypeCollision(){
        // Arrange
        Entity entity1 = new Entity();
        entity1.setHitBox(10);

        Entity entity2 = new Entity();
        entity2.setHitBox(10);

        world.addEntity(entity1);
        world.addEntity(entity2);

        // Act
        detector.process(gameData, world);

        // Assert
        assertFalse(entity1.isHit());
        assertFalse(entity2.isHit());
    }

    @Test
    @DisplayName("Player doesn't collide with bullet")
    void testPlayerBulletCollision(){
        // Arrange
        Entity player = new Entity();
        player.setHitBox(10);

        Bullet bullet = new Bullet();
        bullet.setHitBox(3);

        world.addEntity(player);
        world.addEntity(bullet);

        gameData.setPlayerID(player.getID());

        // Act
        detector.process(gameData, world);

        // Assert
        assertFalse(player.isHit());
        assertFalse(bullet.isHit());
    }

    @Test
    @DisplayName("Destroying asteroid adds 1 score")
    void testScoreAddForAsteroid(){
        // Arrange
        Asteroid asteroid = new Asteroid();
        asteroid.setHitBox(10);

        Bullet bullet = new Bullet();
        bullet.setHitBox(3);

        world.addEntity(asteroid);
        world.addEntity(bullet);

        // Act
        detector.process(gameData, world);

        // Assert
        assertTrue(asteroid.isHit());
        assertTrue(bullet.isHit());
        assertEquals(1, scoringSPIMock.score);
    }

    @Test
    @DisplayName("Destroying enemy adds 5 score")
    void testScoreAddForEnemy(){
        // Arrange
        Enemy enemy = new Enemy();
        enemy.setHitBox(10);

        Bullet bullet = new Bullet();
        bullet.setHitBox(3);

        world.addEntity(enemy);
        world.addEntity(bullet);

        // Act
        detector.process(gameData, world);

        // Assert
        assertTrue(enemy.isHit());
        assertTrue(bullet.isHit());
        assertEquals(5, scoringSPIMock.score);
    }
}
