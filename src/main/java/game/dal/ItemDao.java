package main.java.game.dal;

import main.java.game.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



/**
 * Data access object (DAO) class to interact with the underlying Item table in your MySQL
 * instance. This is used to store {@link Item} into your MySQL instance and retrieve 
 * {@link Item} from MySQL instance.
 */
public class ItemDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static ItemDao instance = null;
	protected ItemDao() {
		connectionManager = new ConnectionManager();
	}
	public static ItemDao getInstance() {
		if(instance == null) {
			instance = new ItemDao();
		}
		return instance;
	}

	/**
	 * Save the Item instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Item create(Item item) throws SQLException {
		String insertItem = "INSERT IGNORE INTO item(item_id,item_name,item_max_size,could_sold_vendor, vendor_price) VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			// Item has an auto-generated key. So we want to retrieve that key.
			insertStmt = connection.prepareStatement(insertItem,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1,item.getItem_id());
			insertStmt.setString(2, item.getItem_name());
			insertStmt.setInt(3, item.getItem_max_size());
			insertStmt.setBoolean(4, item.isCould_sold_vendor());
			insertStmt.setDouble(5, item.getVendor_price());

			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			/**int item_id = -1;
			if(resultKey.next()) {
				item_id = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			item.setItem_id(item_id);
			 **/
			return item;
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
	 * Delete the Item instance.
	 * This runs a DELETE statement.
	 */
	public Item delete(Item item) throws SQLException {
		String deleteItem = "DELETE FROM Item WHERE item_id=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteItem);
			deleteStmt.setInt(1, item.getItem_id());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Item instance.
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
	 * Get the Item record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Item instance.
	 */
	public Item getItemById(int item_id) throws SQLException {
		String selectItem = "SELECT item_id,item_name,item_max_size,could_sold_vendor,vendor_price"
				+ " FROM Item WHERE item_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectItem);
			selectStmt.setInt(1, item_id);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultItemId = results.getInt("item_id");
				String item_name = results.getString("item_name");
				int item_max_size = results.getInt("item_max_size");
				boolean could_sold_vendor = results.getBoolean("could_sold_vendor");
				double vendor_price = results.getDouble("vendor_price");
				Item item = new Item(resultItemId, item_name, item_max_size,
						could_sold_vendor, vendor_price);
				return item;
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
	
	
	/**
	 * Get the matching Item records by fetching from your MySQL instance.
	 * This runs a SELECT statement and returns a list of matching Item.
	 */
	public List<Item> getItemFromItemName(String item_name) throws SQLException {
		List<Item> items = new ArrayList<Item>();
		String selectPersons =
			"SELECT item_id,item_name,item_max_size,could_sold_vendor,vendor_price FROM item WHERE item_name=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPersons);
			selectStmt.setString(1, item_name);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int item_id = results.getInt("item_id");
				String resultItem_name = results.getString("item_name");
				int item_max_size = results.getInt("item_max_size");
				Boolean could_sold_vendor = results.getBoolean("could_sold_vendor");
				double vendor_price = results.getDouble("vendor_price");
				Item item = new Item(item_id, resultItem_name, item_max_size, could_sold_vendor, vendor_price);
				items.add(item);
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
		return items;
	}

}
