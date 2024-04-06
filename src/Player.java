/**
 * This class represents a player. Each player has a specific name, a high score, and a rank.
 *
 * @author Sehee Ryoo
 */
public class Player {
    protected String name;
    protected int highScore = 0;
    protected int rank = 0;

    /**
     * Constructor creates a player with the given name, and high score
     *
     * @param name name of the player
     *
     * @param highScore high score of the player
     *
     * @param rank rank of the player
     */
    public Player(String name, int highScore, int rank) {
        this.name = name;
        this.highScore = highScore;
        this.rank = rank;
    }

    /**
     * Accessor method to get the name of player
     *
     * @return name of the player
     */
    public String getName() {
        // Get the player's name
        return name;
    }

    /**
     * Accessor method to get the high score of player
     *
     * @return high score of the player
     */
    public int getHighScore() {
        return highScore;
    }

    /**
     * Accessor method to get the high score of player
     *
     * @return rank of the user
     */
    public int getRank() {
        return rank;
    }

    /**
     * setName method sets the player's name
     *
     * @param newName input name of the player
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * setHighScore method sets the player's high score
     *
     * @param highScore current high score of the player
     */
    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    /**
     * setRank method sets the player's rank
     *
     * @param curRank current rank of the player
     */
    public void setRank(int curRank) {
        this.rank = curRank;
    }

}
