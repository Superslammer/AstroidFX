package dk.sdu.cbse;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.Vector;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.enemy.IEnemySPI;
import dk.sdu.cbse.common.services.IEntityProccessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class Game {
    private final World world = new World();
    private final Pane worldWindow = new Pane();
    private final GameData gameData = new GameData();
    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();

    private final List<IGamePluginService> gamePluginServices;
    private final List<IEntityProccessingService> entityProccessingServices;

    private long lastFrameTime = -1;

    Game(List<IGamePluginService> gamePluginServices, List<IEntityProccessingService> entityProccessingServices){
        this.gamePluginServices = gamePluginServices;
        this.entityProccessingServices = entityProccessingServices;
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


        Stream<IEnemySPI> tmp = ServiceLoader.load(IEnemySPI.class).stream().map(ServiceLoader.Provider::get);
        tmp.findFirst().ifPresent(
                spi -> world.addEntity(spi.createEnemy(new Vector(0, gameData.getHeight()/2), 90-20))
        );

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
        for(IEntityProccessingService service : entityProccessingServices){
            service.proccess(gameData, world);
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
                // Color the polygons
                //polygon.setFill(Color.TRANSPARENT);
                //polygon.setStroke(Color.BLACK);
                //polygon.setStrokeWidth(2d);
            }

            // Move all polygons to entity pos
            polygon.setTranslateX(entityToAdd.getX());
            polygon.setTranslateY(entityToAdd.getY());
            polygon.setRotate(entityToAdd.getAngle());
        }

    }

}
