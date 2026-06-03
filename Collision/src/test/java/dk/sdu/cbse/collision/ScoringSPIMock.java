package dk.sdu.cbse.collision;

import dk.sdu.cbse.scoringclient.spi.IScoringSPI;

public class ScoringSPIMock implements IScoringSPI {
    public int score = 0;

    @Override
    public Integer addScore(int score) {
        this.score += score;
        return this.score;
    }

    @Override
    public Integer resetScore() {
        score = 0;
        return score;
    }

    @Override
    public Integer getScore() {
        return score;
    }
}
