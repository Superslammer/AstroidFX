import dk.sdu.cbse.common.enemy.IEnemySPI;
import dk.sdu.cbse.common.services.IEntityProccessingService;
import dk.sdu.cbse.enemy.EnemyHandelingSystem;

module Enemy{
    requires Common;
    requires CommonEnemy;
    requires javafx.graphics;

    uses IEnemySPI;

    provides IEnemySPI with EnemyHandelingSystem;
    provides IEntityProccessingService with EnemyHandelingSystem;
}