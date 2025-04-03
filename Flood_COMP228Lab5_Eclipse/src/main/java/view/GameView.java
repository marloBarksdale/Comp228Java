package view;

import dao.GameDAO;
import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import manager.SceneManager;
import model.Game;

public class GameView {

    private final GameDAO dao = new GameDAO();
    private final ObservableList<Game> games = FXCollections.observableArrayList();

    public void show() {
        TableView<Game> table = new TableView<>();
        TextField titleInput = new TextField();
        Button addBtn = new Button("Add");
        Button updateBtn = new Button("Update");
        Button deleteBtn = new Button("Delete");
        Button backBtn = new Button("Back to Menu");

        TableColumn<Game, Number> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getGameId()));

        TableColumn<Game, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getGameTitle()));

        table.getColumns().addAll(idCol, titleCol);
        table.setItems(games);
        refreshData();

        addBtn.setOnAction(e -> {
            String title = titleInput.getText().trim();
            if (title.isEmpty()) {
                showWarning("Game title cannot be blank.");
                return;
            }
            dao.insertGame(title);
            refreshData();
            titleInput.clear();
        });

        updateBtn.setOnAction(e -> {
            Game selected = table.getSelectionModel().getSelectedItem();
            String title = titleInput.getText().trim();
            if (selected == null || title.isEmpty()) {
                showWarning("Select a game and enter a non-empty title.");
                return;
            }
            dao.updateGame(selected.getGameId(), title);
            refreshData();
            titleInput.clear();
        });

        deleteBtn.setOnAction(e -> {
            Game selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                dao.deleteGame(selected.getGameId());
                refreshData();
            }
        });

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                titleInput.setText(newSel.getGameTitle());
            }
        });

        backBtn.setOnAction(e -> new MainMenuView().show());

        VBox inputBox = new VBox(5, new Label("Game Title:"), titleInput);
        HBox controls = new HBox(10, addBtn, updateBtn, deleteBtn, backBtn);
        VBox layout = new VBox(10, table, inputBox, controls);
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout, 500, 400);
        SceneManager.switchScene(scene);
    }

    private void refreshData() {
        games.setAll(dao.getAllGames());
    }


    private void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
