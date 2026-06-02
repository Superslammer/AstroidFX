package dk.sdu.cbse.scoringclient.spi;

public interface IScoringSPI {
    Integer addScore(int score);
    Integer resetScore();
    Integer getScore();
}
