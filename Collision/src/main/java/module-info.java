import dk.sdu.cbse.collision.CollisionDetector;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

module Collision{
    uses dk.sdu.cbse.scoringclient.spi.IScoringSPI;
    requires Common;
    requires CommonBullet;
    requires CommonAsteroid;
    requires ScoringClient;
    requires CommonEnemy;

    provides IPostEntityProcessingService with CollisionDetector;
}
