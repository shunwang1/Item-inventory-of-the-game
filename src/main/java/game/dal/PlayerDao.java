package main.java.game.dal;

import main.java.game.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;





public class PlayerDao {
	protected ConnectionManager connectionManager;
    private static PlayerDao instance = null;

    protected PlayerDao() {
        connectionManager = new ConnectionManager();
    }

    public static PlayerDao getInstance() {
        if (instance == null) {
            instance = new PlayerDao();
        }
        return instance;
    }
    public Player create(Player player) throws SQLException {
        String insertQuery = "INSERT IGNORE INTO player (UserName, player_name, email, address, phone, is_active) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, player.getUserName());
            statement.setString(2, player.getPlayerName());
            statement.setString(3, player.getEmail());
            statement.setString(4, player.getAddress());
            statement.setString(5, player.getPhone());
            statement.setBoolean(6, player.isActive());
			statement.executeUpdate();
            //int affectedRows = statement.executeUpdate();
            /**
            if (affectedRows == 0) {
                throw new SQLException("Creating player failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int playerId = generatedKeys.getInt(1);
                    player.setCharacterId(playerId);
                } else {
                    throw new SQLException("Creating player failed, no ID obtained.");
                }
            }**/
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return player;
    }

    public Player getPlayerByUserName(String UserName) throws SQLException {
        String selectQuery = "SELECT * FROM Player WHERE UserName = ?";
        Player player = null;

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {

            statement.setString(1, UserName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    player = extractPlayerFromResultSet(resultSet);
					return player;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
		return player;
    }
    
    
    public Player updatePlayer(Player player, String newPlayerName,String email,String address, String phone,boolean active) throws SQLException {
		String updatePlayer = "UPDATE Player SET player_name=?,email = ?,address = ?, phone = ?, is_active = ? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePlayer);
			updateStmt.setString(1, newPlayerName);
			updateStmt.setString(2, email);
			updateStmt.setString(3, address);
			updateStmt.setString(4, phone);
			updateStmt.setBoolean(5, active);
			updateStmt.setString(6, player.getUserName());
			updateStmt.executeUpdate();
			
			
			player.setPlayerName(newPlayerName);
			return player;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
    


    public void delete(Player player) throws SQLException {
        String deleteQuery = "DELETE IGNORE FROM Player WHERE UserName = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setString(1, player.getUserName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    private Player extractPlayerFromResultSet(ResultSet resultSet) throws SQLException {
        String userName = resultSet.getString("UserName");
        String playerName = resultSet.getString("player_name");
        String email = resultSet.getString("email");
        String address = resultSet.getString("address");
        String phone = resultSet.getString("phone");
        boolean isActive = resultSet.getBoolean("is_active");

        return new Player(userName, playerName, email, address, phone, isActive);
    }
    
    /**
	 * Get the matching Player records by fetching from your MySQL instance.
	 * This runs a SELECT statement and returns a list of matching Player.
	 */
	public List<Player> getPlayerFromPlayerName(String playerName) throws SQLException {
		List<Player> players = new ArrayList<Player>();
		String selectPlayer =
			"SELECT UserName,player_name,email,address,phone,is_active FROM Player WHERE player_name=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPlayer);
			selectStmt.setString(1, playerName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String userName = results.getString("UserName");
				String resultPlayerName = results.getString("player_name");
				String email = results.getString("email");
				String address = results.getString("address");
				String phone = results.getString("phone");
				boolean isActive = results.getBoolean("is_active");
				Player player = new Player(
						userName, 
						resultPlayerName, 
						email,
						address,
						phone,
						isActive
						);
				players.add(player);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return players;
	}
}

