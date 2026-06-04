package dk.sdu.cbse.scoringclient.spi;

public interface IScoringSPI {
    /**
     * Adds score to the players current score.
     * <p><b>Pre-conditions:</b></p>
     * <ul>
     *     <li>{@code score} is positive.</li>
     * </ul>
     *
     * <p><b>Post-conditions:</b></p>
     * <ul>
     *     <li>Returns the players new {@code score}.</li>
     * </ul>
     *
     * @param score Is the amount to add to the players score.
     */
    Integer addScore(int score);
    /**
     * Sets the players score to 0.
     * <p><b>Pre-conditions:</b></p>
     *
     * <p><b>Post-conditions:</b></p>
     * <ul>
     *     <li>The players {@code score} is 0.</li>
     *     <li>Returns the players current {@code score}.</li>
     * </ul>
     */
    Integer resetScore();
    /**
     * Gets the players current score.
     * <p><b>Pre-conditions:</b></p>
     *
     * <p><b>Post-conditions:</b></p>
     * <ul>
     *     <li>Returns the players current {@code score}.</li>
     * </ul>
     */
    Integer getScore();
}
