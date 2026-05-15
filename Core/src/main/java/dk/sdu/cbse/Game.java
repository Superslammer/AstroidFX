package dk.sdu.cbse;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Game {
    private final World world = new World();
    private final Pane worldWindow = new Pane();
    private final GameData gameData = new GameData();

    public void start(Stage window) {
        Text text = new Text(10, 20, "Destroyed asteroids: 0");
        worldWindow.setPrefSize(gameData.getWidth(), gameData.getHeight());
        worldWindow.getChildren().add(text);

        Scene gameScene = new Scene(worldWindow);

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
