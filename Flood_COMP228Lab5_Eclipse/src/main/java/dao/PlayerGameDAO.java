package dao;

import database.DatabaseConnection;
import model.PlayerGameDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerGameDAO {

    public List<PlayerGameDTO> getAllPlayerGameInfo() {
        List<PlayerGameDTO> playerGameList = new ArrayList<>();

        String query = """
            SELECT 
                p.player_id,
                p.first_name,
                p.last_name,
                g.game_title
            FROM 
                Players p
            JOIN 
                PlayerGame pg ON p.player_id = pg.player_id
            JOIN 
                Games g ON g.game_id = pg.game_id
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PlayerGameDTO dto = new PlayerGameDTO(
                        rs.getInt("player_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("game_title")
                );
                playerGameList.add(dto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return playerGameList;
    }


    public boolean insertPlayerGame(int playerId, int gameId) {
        String sql = "INSERT INTO PlayerGame (player_game_id, player_id, game_id) VALUES (PLAYER_GAME_SEQ.NEXTVAL, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, playerId);
            ps.setInt(2, gameId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean linkExists(int playerId, int gameId) {
        String sql = "SELECT COUNT(*) FROM PlayerGame WHERE player_id = ? AND game_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, playerId);
            ps.setInt(2, gameId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    public boolean deletePlayerGameLink(int playerId, int gameId) {
        String sql = "DELETE FROM PlayerGame WHERE player_id = ? AND game_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, playerId);
            ps.setInt(2, gameId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



}