import dk.sdu.cbse.scoringclient.spi.IScoringSPI;
import dk.sdu.cbse.scoringclient.ScoringClient;

module ScoringClient {
    requires spring.web;

    provides IScoringSPI with ScoringClient;

    exports dk.sdu.cbse.scoringclient.spi;
}