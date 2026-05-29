package dk.sdu.cbse.common.enemy;

import dk.sdu.cbse.common.data.Vector;

public interface IEnemySPI {
    Enemy createEnemy(Vector spawnPosition, double angle);
}
