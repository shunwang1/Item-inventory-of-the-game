package main.java.game.dal;

import main.java.game.model.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SlotDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static SlotDao instance = null;
	protected SlotDao() {
		connectionManager = new ConnectionManager();
	}
	public static SlotDao getInstance() {
		if(instance == null) {
			instance = new SlotDao();
		}
		return instance;
	}

	/**
	 * Save the Persons instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Slot create(Slot slot) throws SQLException {
		String insertSlot = "INSERT IGNORE INTO Slot(item_id,item_amount,slot_position,inventory_id) VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertSlot, 
					Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setInt(1, slot.getItem_id());
			insertStmt.setInt(2, slot.getItem_amount());
			insertStmt.setInt(3, slot.getSlot_position());
			insertStmt.setInt(4, slot.getInventory_id());
		
			insertStmt.executeUpdate();

			/**
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int slot_id = -1;
			if(resultKey.next()) {
				slot_id = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			slot.setSlot_id(slot_id);
			 **/
			return slot;
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
	 * Delete the Slot instance.
	 * This runs a DELETE statement.
	 */
	public Slot delete(Slot slot) throws SQLException {
		String deleteSlot = "DELETE FROM Persons WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteSlot);
			deleteStmt.setInt(1, slot.getSlot_id());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Slot instance.
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

	/**
	 * Get the Persons record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Persons instance.
	 */
	public Slot getSlotByID(int slot_id) throws SQLException {
		String selectSlot = "SELECT slot_id, item_id, item_amount, slot_position, inventory_id FROM Slot WHERE slot_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSlot);
			selectStmt.setInt(1, slot_id);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultSlot_id = results.getInt("slot_id");
				int item = results.getInt("item_id");
				int item_amount = results.getInt("item_amount");
				int slot_position = results.getInt("slot_position");
				int inventory = results.getInt("inventory_id");
				Slot slot = new Slot(resultSlot_id, item, item_amount, slot_position, inventory);
				return slot;
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
	

	
	public List<Slot> getSlotFromItem(Item item) throws SQLException {
		List<Slot> slots = new ArrayList<Slot>();
		String selectSlot =
			"SELECT slot_id, item_id, item_amount, slot_position, inventory_id FROM Slot WHERE item_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSlot);
			selectStmt.setInt(1, item.getItem_id());
			results = selectStmt.executeQuery();
			while(results.next()) {
				int slot_id = results.getInt("slot_id");
				int resultItem_id = results.getInt("item_id");
				int item_amount = results.getInt("item_amount");
				int slot_position = results.getInt("slot_position");
				int inventory_id = results.getInt("inventory_id");
				Slot slot = new Slot(slot_id, resultItem_id, item_amount, slot_position, inventory_id);
				slots.add(slot);
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
		return slots;
	}
	

	public List<Slot> getSlotFromItem(int inventory) throws SQLException {
		List<Slot> slots = new ArrayList<Slot>();
		String selectSlot =
			"SELECT slot_id, item_id, item_amount, slot_position, inventory_id FROM Slot WHERE inventory_id =?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSlot);
			selectStmt.setInt(1, inventory);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int slot_id = results.getInt("slot_id");
				int item_id = results.getInt("item_id");
				int item_amount = results.getInt("item_amount");
				int slot_position = results.getInt("slot_position");
				int resultInventory_id = results.getInt("inventory_id");
				Slot slot = new Slot(slot_id, item_id, item_amount, slot_position, resultInventory_id);
				slots.add(slot);
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
		return slots;
	}
	
	public List<Slot> getSlotsByInventoryId(int inventoryId) throws SQLException {
	    List<Slot> slots = new ArrayList<>();
	    String selectSlot =
	        "SELECT slot_id, item_id, item_amount, slot_position, inventory_id FROM Slot WHERE inventory_id = ?;";
	    Connection connection = null;
	    PreparedStatement selectStmt = null;
	    ResultSet results = null;
	    try {
	        connection = connectionManager.getConnection();
	        selectStmt = connection.prepareStatement(selectSlot);
	        selectStmt.setInt(1, inventoryId);
	        results = selectStmt.executeQuery();
	        while (results.next()) {
	            int slotId = results.getInt("slot_id");
	            int itemId = results.getInt("item_id");
	            int itemAmount = results.getInt("item_amount");
	            int slotPosition = results.getInt("slot_position");
	            int resultInventoryId = results.getInt("inventory_id");
	            Slot slot = new Slot(slotId, itemId, itemAmount, slotPosition, resultInventoryId);
	            slots.add(slot);
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
	    return slots;
	}
}
