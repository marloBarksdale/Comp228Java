package view;

import dao.PlayerGameDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import manager.SceneManager;
import model.PlayerGameDTO;

import java.util.List;

public class MainMenuView {

    public void show() {
        // Buttons
        Button btnPlayers = new Button("Manage Players");
        Button btnGames = new Button("Manage Games");

        btnPlayers.setOnAction(e -> new PlayerView().show());
        btnGames.setOnAction(e -> new GameView().show());


        Button btnLink = new Button("Manage Player-Game Links");
        btnLink.setOnAction(e -> new PlayerGameView().show());

        HBox buttonBox = new HBox(15, btnPlayers, btnGames, btnLink);



        // TableView
        TableView<PlayerGameDTO> table = new TableView<>();

        TableColumn<PlayerGameDTO, Number> colId = new TableColumn<>("Player ID");
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getPlayerId()));

        TableColumn<PlayerGameDTO, String> colFirst = new TableColumn<>("First Name");
        colFirst.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getFirstName()));

        TableColumn<PlayerGameDTO, String> colLast = new TableColumn<>("Last Name");
        colLast.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getLastName()));

        TableColumn<PlayerGameDTO, String> colGame = new TableColumn<>("Game Title");
        colGame.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getGameTitle()));

        table.getColumns().addAll(colId, colFirst, colLast, colGame);
        table.setItems(loadPlayerGameData());

        VBox layout = new VBox(20, new Label("Player-Game Report:"), table, buttonBox);
        layout.setPadding(new Insets(20));

        SceneManager.switchScene(new Scene(layout, 700, 500));
    }

    private ObservableList<PlayerGameDTO> loadPlayerGameData() {
        PlayerGameDAO dao = new PlayerGameDAO();
        List<PlayerGameDTO> list = dao.getAllPlayerGameInfo();
        return FXCollections.observableArrayList(list);
    }



}
