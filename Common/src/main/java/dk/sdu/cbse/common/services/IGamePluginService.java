package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public interface IGamePluginService {
    void init(GameData gameData, World world);
    void destroy(GameData gameData, World world);
}
