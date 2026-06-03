import dk.sdu.cbse.scoringclient.spi.IScoringSPI;
import dk.sdu.cbse.scoringclient.ScoringClient;

module ScoringClient {
    requires spring.web;
    requires com.fasterxml.jackson.databind;

    provides IScoringSPI with ScoringClient;

    exports dk.sdu.cbse.scoringclient.spi;
}