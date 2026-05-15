package dk.sdu.cbse;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Game {
    public void start(Stage window) {
        Pane gameArea = new Pane();

        Text text = new Text(10, 20, "Destroyed asteroids: 0");
        gameArea.setPrefSize(800, 800);
        gameArea.getChildren().add(text);

        Scene gameScene = new Scene(gameArea);

        window.setScene(gameScene);
        window.setTitle("Asteroids");
        window.show();
    }

    public void render() {

    }
}
