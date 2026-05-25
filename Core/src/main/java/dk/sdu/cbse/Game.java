package dk.sdu.cbse;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

public class Game {
    private final World world = new World();
    private final Pane worldWindow = new Pane();
    private final GameData gameData = new GameData();
    private final List<IGamePluginService> gamePluginServices;

    Game(List<IGamePluginService> gamePluginServices){
        this.gamePluginServices = gamePluginServices;
    }

    public void start(Stage window) {
        Text text = new Text(10, 20, "Destroyed asteroids: 0");
        worldWindow.setPrefSize(gameData.getWidth(), gameData.getHeight());
        worldWindow.getChildren().add(text);

        Scene gameScene = new Scene(worldWindow);
        // Handle key presses
        gameScene.setOnKeyPressed(keyEvent -> gameData.pressKey(keyEvent.getCode()));
        gameScene.setOnKeyReleased(keyEvent -> gameData.releaseKey(keyEvent.getCode()));

        // Run init functions
        for(IGamePluginService gService : gamePluginServices){
            gService.init(gameData, world);
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
                update();
                draw();
            }
        }.start();
    }

    private void update(){

    }

    private void draw(){

    }

}
