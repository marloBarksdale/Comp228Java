package model;

public class Game {
    private int gameId;
    private String gameTitle;

    public Game(int gameId, String gameTitle) {
        this.gameId = gameId;
        this.gameTitle = gameTitle;
    }

    public int getGameId() { return gameId; }
    public String getGameTitle() { return gameTitle; }

    public void setGameTitle(String title) { this.gameTitle = title; }
    public String toString() {
        return gameTitle;
    }
}
