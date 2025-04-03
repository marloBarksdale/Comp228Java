package dao;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import database.DatabaseConnection;
import model.Game;

import java.sql.*;
import java.util.*;

public class GameDAO {
    public List<Game> getAllGames() {
        List<Game> list = new ArrayList<>();
        String sql = "SELECT * FROM Games";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Game(rs.getInt("game_id"), rs.getString("game_title")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insertGame(String title) {
        if (gameExists(title)) {
            // Show popup instead of printing to console
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Duplicate Game");
            alert.setHeaderText(null);
            alert.setContentText("A game with this title already exists!");
            alert.showAndWait();
            return false;
        }

        String sql = "INSERT INTO Games (game_id, game_title) VALUES (games_seq.NEXTVAL, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, title);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateGame(int id, String title) {
        String sql = "UPDATE Games SET game_title = ? WHERE game_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteGame(int id) {
        String sql = "DELETE FROM Games WHERE game_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean gameExists(String title) {
        String sql = "SELECT COUNT(*) FROM Games WHERE LOWER(game_title) = LOWER(?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }





}
