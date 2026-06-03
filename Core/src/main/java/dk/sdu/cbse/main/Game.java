package dk.sdu.cbse.main;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.cbse.scoringclient.spi.IScoringSPI;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Game {
    private final World world = new World();
    private final Pane worldWindow = new Pane();
    private final GameData gameData = new GameData();
    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();
    private final Text scoreText = new Text(10, 20, "Score: 0");
    private long lastFrameTime = -1;

    private final ModuleLayer pluginLayer;
    private final List<IGamePluginService> gamePluginServices;
    private final List<IEntityProcessingService> entityProcessingServices;
    private final List<IPostEntityProcessingService> postEntityProcessingServices;
    private final List<IScoringSPI> scoringSPIs;

    @Autowired
    Game(List<IGamePluginService> gamePluginServices, List<IEntityProcessingService> entityProccessingServices,
         List<IPostEntityProcessingService> postEntityProcessingServices, ModuleLayer pluginLayer,
         List<IScoringSPI> scoringSPIs){
        this.gamePluginServices = gamePluginServices;
        this.entityProcessingServices = entityProccessingServices;
        this.postEntityProcessingServices = postEntityProcessingServices;
        this.pluginLayer = pluginLayer;
        this.scoringSPIs = scoringSPIs;
    }

    public void start(Stage window) {
        gameData.setPluginLayer(pluginLayer);

        scoreText.setStroke(Color.WHITE);

        worldWindow.setPrefSize(gameData.getWidth(), gameData.getHeight());
        worldWindow.getChildren().add(scoreText);

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
        for(IEntityProcessingService service : entityProcessingServices){
            service.proccess(gameData, world);
        }

        for(IPostEntityProcessingService service : postEntityProcessingServices){
            service.process(gameData, world);
        }
    }

    private void draw(){
        // Update score text
        try {
            IScoringSPI scoringSPI = scoringSPIs.getFirst();
            int newScore = scoringSPI.getScore();
            scoreText.setText("Score: " + newScore);
        } catch (NullPointerException ignored) { }
        catch (Exception e) {
            System.out.println("Exception: " + e);
        }

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
