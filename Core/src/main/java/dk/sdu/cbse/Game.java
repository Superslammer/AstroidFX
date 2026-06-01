package dk.sdu.cbse;

import dk.sdu.cbse.common.asteroid.IAsteroidSPI;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.Vector;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.enemy.IEnemySPI;
import dk.sdu.cbse.common.services.IEntityProccessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Game {
    private final World world = new World();
    private final Pane worldWindow = new Pane();
    private final GameData gameData = new GameData();
    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();

    private final List<IGamePluginService> gamePluginServices;
    private final List<IEntityProccessingService> entityProcessingServices;
    private final List<IPostEntityProcessingService> postEntityProcessingServices;
    private final List<IEnemySPI> enemySPIS;
    private final List<IAsteroidSPI> asteroidSPIS;

    private long lastFrameTime = -1;

    Game(List<IGamePluginService> gamePluginServices, List<IEntityProccessingService> entityProccessingServices,
         List<IPostEntityProcessingService> postEntityProcessingServices ,List<IEnemySPI> enemySPIS,
         List<IAsteroidSPI> asteroidSPIS){
        this.gamePluginServices = gamePluginServices;
        this.entityProcessingServices = entityProccessingServices;
        this.postEntityProcessingServices = postEntityProcessingServices;
        this.enemySPIS = enemySPIS;
        this.asteroidSPIS = asteroidSPIS;
    }

    public void start(Stage window) {
        Text text = new Text(10, 20, "Destroyed asteroids: 0");
        worldWindow.setPrefSize(gameData.getWidth(), gameData.getHeight());
        worldWindow.getChildren().add(text);

        Scene gameScene = new Scene(worldWindow);
        gameScene.setFill(Color.BLACK);

        // Handle key presses
        gameScene.setOnKeyPressed(keyEvent -> gameData.pressKey(keyEvent.getCode()));
        gameScene.setOnKeyReleased(keyEvent -> gameData.releaseKey(keyEvent.getCode()));

        // Run init functions
        for(IGamePluginService pluginService : gamePluginServices){
            pluginService.init(gameData, world);
        }

        // Render initial polygons
        draw();

        window.setScene(gameScene);
        window.setTitle("Asteroids");
        window.show();
    }

    public void render() {
        new AnimationTimer() {
            @Override
            public void handle (long now){
                if (lastFrameTime == -1){
                    lastFrameTime = now;
                }
                gameData.setDeltaT((now - lastFrameTime)/1.0E9);
                lastFrameTime = now;

                update();
                draw();
            }
        }.start();
    }

    private void update(){
        spawnEnemies();
        spawnAsteroids();

        for(IEntityProccessingService service : entityProcessingServices){
            service.proccess(gameData, world);
        }

        for(IPostEntityProcessingService service : postEntityProcessingServices){
            service.process(gameData, world);
        }
    }

    private static final double ENEMY_SPAWN_TIME = 10d;
    private double enemySpawnCooldown = 0;

    private void spawnEnemies(){
        enemySpawnCooldown -= gameData.getDeltaT();

        if (enemySpawnCooldown <= 0 && !enemySPIS.isEmpty()){
            Random rng = new Random();
            IEnemySPI spi = enemySPIS.get(rng.nextInt(enemySPIS.size()));

            int spawnSide = rng.nextInt(4);
            Vector startPos = new Vector();
            double angle = 0;
            if (spawnSide == 0){
                // Left side
                startPos.setX(-50);
                startPos.setY(rng.nextInt((int)(gameData.getHeight()/2 - 100), (int)(gameData.getHeight()/2 + 101)));

                angle = 90;
            } else if (spawnSide == 1) {
                // Top side
                startPos.setY(-50);
                startPos.setX(rng.nextInt((int)(gameData.getWidth()/2 - 100), (int)(gameData.getWidth()/2 + 101)));

                angle = 180;
            } else if (spawnSide == 2) {
                // Right side
                startPos.setX(gameData.getWidth() + 50);
                startPos.setY(rng.nextInt((int)(gameData.getHeight()/2 - 100), (int)(gameData.getHeight()/2 + 101)));

                angle = 270;
            } else {
                // Bottom side
                startPos.setY(gameData.getHeight() + 50);
                startPos.setX(rng.nextInt((int)(gameData.getWidth()/2 - 100), (int)(gameData.getWidth()/2 + 100)));

                angle = 0;
            }

            angle = rng.nextDouble(angle-30, angle + 31);
            world.addEntity(spi.createEnemy(startPos, angle));
            enemySpawnCooldown = ENEMY_SPAWN_TIME;
        }
    }

    private static final double ASTEROID_SPAWN_TIME = 2.5d;
    private double asteroidSpawnCooldown = 0;
    private void spawnAsteroids(){
        asteroidSpawnCooldown -= gameData.getDeltaT();

        if (asteroidSpawnCooldown <= 0 && !asteroidSPIS.isEmpty()){
            Random rng = new Random();
            IAsteroidSPI spi = asteroidSPIS.get(rng.nextInt(asteroidSPIS.size()));

            int spawnSide = rng.nextInt(4);
            Vector startPos = new Vector();
            double angle = 0;
            if (spawnSide == 0){
                // Left side
                startPos.setX(-50);
                startPos.setY(rng.nextInt((int)(gameData.getHeight()/2 - 100), (int)(gameData.getHeight()/2 + 101)));

                angle = 90;
            } else if (spawnSide == 1) {
                // Top side
                startPos.setY(-50);
                startPos.setX(rng.nextInt((int)(gameData.getWidth()/2 - 100), (int)(gameData.getWidth()/2 + 101)));

                angle = 180;
            } else if (spawnSide == 2) {
                // Right side
                startPos.setX(gameData.getWidth() + 50);
                startPos.setY(rng.nextInt((int)(gameData.getHeight()/2 - 100), (int)(gameData.getHeight()/2 + 101)));

                angle = 270;
            } else {
                // Bottom side
                startPos.setY(gameData.getHeight() + 50);
                startPos.setX(rng.nextInt((int)(gameData.getWidth()/2 - 100), (int)(gameData.getWidth()/2 + 100)));

                angle = 0;
            }

            angle = rng.nextDouble(angle-60, angle + 61);

            double magnitude = 1;
            double x =  magnitude * Math.sin(Math.toRadians(angle));
            double y = -magnitude * Math.cos(Math.toRadians(angle));

            world.addEntity(spi.createAsteroid(startPos, new Vector(x, y)));
            asteroidSpawnCooldown = ASTEROID_SPAWN_TIME;
        }
    }

    private void draw(){
        // Remove polygons of non-existant entities
        Set<Entity> drawnEntities = new HashSet<>(polygons.keySet());
        Set<Entity> worldEntities = new HashSet<>(world.getEntities());
        drawnEntities.removeAll(worldEntities);

        for (Entity entityToRemove : drawnEntities){
            Polygon polygonToRemove = polygons.remove(entityToRemove);
            worldWindow.getChildren().remove(polygonToRemove);
        }

        for (Entity entityToAdd : worldEntities){
            // Add new polygons
            Polygon polygon = polygons.get(entityToAdd);
            if (polygon == null) {
                polygon = entityToAdd.getSprite();
                polygons.put(entityToAdd, polygon);
                worldWindow.getChildren().add(polygon);
            }

            // Move all polygons to entity pos
            polygon.setTranslateX(entityToAdd.getX());
            polygon.setTranslateY(entityToAdd.getY());
            polygon.setRotate(entityToAdd.getAngle());
        }

    }
}
