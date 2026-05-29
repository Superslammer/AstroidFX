package dk.sdu.cbse.player;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class PlayerPlugin implements IGamePluginService {
    private Player player;

    @Override
    public void init(GameData gameData, World world) {
        player = new Player();

        Polygon sprite = new Polygon(
                0, -5,
                5, 5,
                0, 2,
                -5, 5
        );
        sprite.setFill(Color.TRANSPARENT);
        sprite.setStroke(Color.BLACK);
        sprite.setStrokeWidth(2d);
        player.setBoundingBox(sprite);

        player.setX(gameData.getWidth()/2);
        player.setY(gameData.getHeight()/2);

        world.addEntity(player);
    }

    @Override
    public void destroy(GameData gameData, World world) {
        world.removeEntity(player);
    }
}
