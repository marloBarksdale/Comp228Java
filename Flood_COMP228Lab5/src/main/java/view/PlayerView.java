package view;

import dao.PlayerDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import manager.SceneManager;
import model.Player;

public class PlayerView {

    private final PlayerDAO dao = new PlayerDAO();
    private final ObservableList<Player> players = FXCollections.observableArrayList();

    public void show() {
        TableView<Player> table = new TableView<>();
        TextField firstNameInput = new TextField();
        TextField lastNameInput = new TextField();
        Button addBtn = new Button("Add");
        Button updateBtn = new Button("Update");
        Button deleteBtn = new Button("Delete");
        Button backBtn = new Button("Back to Menu");

        TableColumn<Player, Number> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getPlayerId()));

        TableColumn<Player, String> fnCol = new TableColumn<>("First Name");
        fnCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getFirstName()));

        TableColumn<Player, String> lnCol = new TableColumn<>("Last Name");
        lnCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getLastName()));

        table.getColumns().addAll(idCol, fnCol, lnCol);
        table.setItems(players);
        refreshData();

        addBtn.setOnAction(e -> {
            String fn = firstNameInput.getText().trim();
            String ln = lastNameInput.getText().trim();

            if (fn.isEmpty() || ln.isEmpty()) {
                showWarning("First name and last name cannot be blank.");
                return;
            }

            dao.insertPlayer(fn, ln);
            refreshData();
            firstNameInput.clear();
            lastNameInput.clear();
        });





        updateBtn.setOnAction(e -> {
            Player selected = table.getSelectionModel().getSelectedItem();
            String fn = firstNameInput.getText().trim();
            String ln = lastNameInput.getText().trim();

            if (selected == null || fn.isEmpty() || ln.isEmpty()) {
                showWarning("Select a player and ensure all fields are filled.");
                return;
            }

            dao.updatePlayer(selected.getPlayerId(), fn, ln);
            refreshData();
            firstNameInput.clear();
            lastNameInput.clear();
        });
        deleteBtn.setOnAction(e -> {
            Player selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                dao.deletePlayer(selected.getPlayerId());
                refreshData();
            }
        });

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                firstNameInput.setText(newSel.getFirstName());
                lastNameInput.setText(newSel.getLastName());
            }
        });

        backBtn.setOnAction(e -> new MainMenuView().show());

        VBox inputs = new VBox(5,
                new Label("First Name:"), firstNameInput,
                new Label("Last Name:"), lastNameInput);

        HBox controls = new HBox(10, addBtn, updateBtn, deleteBtn, backBtn);
        VBox layout = new VBox(10, table, inputs, controls);
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout, 600, 500);
        SceneManager.switchScene(scene);
    }

    private void refreshData() {
        players.setAll(dao.getAllPlayers());
    }

    private void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}