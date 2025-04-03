package model;

public class PlayerGameDTO {
    private int playerId;
    private String firstName;
    private String lastName;
    private String gameTitle;

    public PlayerGameDTO(int playerId, String firstName, String lastName, String gameTitle) {
        this.playerId = playerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gameTitle = gameTitle;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGameTitle() {
        return gameTitle;
    }
}