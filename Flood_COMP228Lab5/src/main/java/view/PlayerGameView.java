package view;

import dao.PlayerDAO;
import dao.GameDAO;
import dao.PlayerGameDAO;
import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import manager.SceneManager;
import model.Game;
import model.Player;
import model.PlayerGameDTO;

import java.util.List;

public class PlayerGameView {

    private final PlayerGameDAO pgDao = new PlayerGameDAO();
    private final PlayerDAO playerDao = new PlayerDAO();
    private final GameDAO gameDao = new GameDAO();

    private final ObservableList<PlayerGameDTO> links = FXCollections.observableArrayList();

    public void show() {
        ComboBox<Player> playerCombo = new ComboBox<>();
        ComboBox<Game> gameCombo = new ComboBox<>();
        Button linkBtn = new Button("Link Player to Game");
        Button backBtn = new Button("Back to Menu");
        Button deleteBtn = new Button("Delete Link");

        TableView<PlayerGameDTO> table = new TableView<>();

        // Columns
        TableColumn<PlayerGameDTO, Number> idCol = new TableColumn<>("Player ID");
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getPlayerId()));

        TableColumn<PlayerGameDTO, String> fnCol = new TableColumn<>("First Name");
        fnCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getFirstName()));

        TableColumn<PlayerGameDTO, String> lnCol = new TableColumn<>("Last Name");
        lnCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getLastName()));

        TableColumn<PlayerGameDTO, String> gameCol = new TableColumn<>("Game");
        gameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getGameTitle()));

        table.getColumns().addAll(idCol, fnCol, lnCol, gameCol);
        table.setItems(links);

        // Populate dropdowns
        playerCombo.setItems(FXCollections.observableArrayList(playerDao.getAllPlayers()));
        playerCombo.setPromptText("Select Player");

        gameCombo.setItems(FXCollections.observableArrayList(gameDao.getAllGames()));
        gameCombo.setPromptText("Select Game");

        // Button actions
        linkBtn.setOnAction(e -> {
            Player selectedPlayer = playerCombo.getValue();
            Game selectedGame = gameCombo.getValue();

            if (selectedPlayer == null || selectedGame == null) {
                showWarning("Please select both a player and a game.");
                return;
            }

            // 🔍 Check for duplicates
            if (pgDao.linkExists(selectedPlayer.getPlayerId(), selectedGame.getGameId())) {
                showWarning("This player is already linked to the selected game.");
                return;
            }

            boolean success = pgDao.insertPlayerGame(selectedPlayer.getPlayerId(), selectedGame.getGameId());
            if (success) {
                refreshData();
                showInfo("Link created successfully.");
            } else {
                showWarning("Failed to create link.");
            }
        });


        deleteBtn.setOnAction(e -> {
            PlayerGameDTO selected = table.getSelectionModel().getSelectedItem();

            if (selected == null) {
                showWarning("Please select a link to delete.");
                return;
            }

            boolean success = pgDao.deletePlayerGameLink(
                    selected.getPlayerId(),
                    gameDao.getAllGames().stream()
                            .filter(g -> g.getGameTitle().equals(selected.getGameTitle()))
                            .findFirst()
                            .map(Game::getGameId)
                            .orElse(-1)
            );

            if (success) {
                refreshData();
                showInfo("Link deleted successfully.");
            } else {
                showWarning("Failed to delete link.");
            }
        });


        backBtn.setOnAction(e -> new MainMenuView().show());

        VBox controls = new VBox(10,
                new Label("Player:"), playerCombo,
                new Label("Game:"), gameCombo,
                linkBtn, deleteBtn,backBtn
        );
        controls.setPadding(new Insets(10));

        HBox layout = new HBox(20, controls, table);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 800, 400);
        SceneManager.switchScene(scene);

        refreshData();
    }

    private void refreshData() {
        links.setAll(pgDao.getAllPlayerGameInfo());
    }

    private void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validation");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
