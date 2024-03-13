package main.java.game.dal;

import main.java.game.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class InventoryDao {
	protected ConnectionManager connectionManager;

	private static InventoryDao instance = null;
	protected InventoryDao() {
		connectionManager = new ConnectionManager();
	}
	public static InventoryDao getInstance() {
		if(instance == null) {
			instance = new InventoryDao();
		}
		return instance;
	}

	public Inventory create(Inventory inventory) throws SQLException {
		String insertInventory =
			"INSERT INTO inventory(character_id,number_of_slots) " +
			"VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			// Inventory has an auto-generated key. So we want to retrieve that key.
			insertStmt = connection.prepareStatement(insertInventory,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, inventory.getEach_character().getCharacterId());
			insertStmt.setInt(2, inventory.getNumber_of_slots());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int inventory_id = -1;
			if(resultKey.next()) {
				inventory_id = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			inventory.setInventory_id(inventory_id);
			return inventory;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}
	
	/**
	 * Get the Inventory record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Inventory instance.
	 */
	public Inventory getInventoryById(int inventory_id) throws SQLException {
		String selectInventory =
			"SELECT inventory_id,character_id,number_of_slots " +
			"FROM inventory " +
			"WHERE inventory_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectInventory);
			selectStmt.setInt(1, inventory_id);
			results = selectStmt.executeQuery();
			Each_CharacterDao each_characterDao = Each_CharacterDao.getInstance();
			if(results.next()) {
				int resultInventory_id = results.getInt("inventory_id");
				int character_id = results.getInt("character_id");
				int number_of_slots = results.getInt("number_of_slots");
				Each_Character each_character = each_characterDao.getCharacterFromCharacterId(character_id);
				Inventory inventory = new Inventory(resultInventory_id, each_character, number_of_slots);
				return inventory;
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
		return null;
	}
	public Inventory getInventoryByCharacterId(int characterId) throws SQLException {
	    String selectInventory =
	        "SELECT inventory_id, number_of_slots " +
	        "FROM inventory " +
	        "WHERE character_id = ?;";
	    Connection connection = null;
	    PreparedStatement selectStmt = null;
	    ResultSet results = null;
	    try {
	        connection = connectionManager.getConnection();
	        selectStmt = connection.prepareStatement(selectInventory);
	        selectStmt.setInt(1, characterId);
	        results = selectStmt.executeQuery();
	        Each_CharacterDao each_characterDao = Each_CharacterDao.getInstance();
	        if (results.next()) {
	            int inventory_id = results.getInt("inventory_id");
	            int number_of_slots = results.getInt("number_of_slots");
	            // Assuming you can get character from character_id
	            Each_Character each_character = each_characterDao.getCharacterFromCharacterId(characterId);
	            Inventory inventory = new Inventory(inventory_id, each_character, number_of_slots);
	            return inventory;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        if (connection != null) {
	            connection.close();
	        }
	        if (selectStmt != null) {
	            selectStmt.close();
	        }
	        if (results != null) {
	            results.close();
	        }
	    }
	    return null;
	}

}
