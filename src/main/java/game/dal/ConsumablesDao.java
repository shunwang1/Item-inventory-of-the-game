package main.java.game.dal;

import main.java.game.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConsumablesDao extends ItemDao {
	// Single pattern: instantiation is limited to one object.
	private static ConsumablesDao instance = null;
	protected ConsumablesDao() {
		super();
	}
	public static ConsumablesDao getInstance() {
		if(instance == null) {
			instance = new ConsumablesDao();
		}
		return instance;
	}


	
	public Consumables create(Consumables consumables) throws SQLException {
		String insertConsumables =
			"INSERT IGNORE INTO Consumables(consumables_id,item_level, descriptions, attribute_bonuses) VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertConsumables,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1,consumables.getItem_id());
			insertStmt.setInt(2, consumables.getItem_level());
			insertStmt.setString(3, consumables.getDescriptions());
			insertStmt.setDouble(4, consumables.getAttribute_bonuses());
			insertStmt.executeUpdate();

			/**
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int consumables_id = -1;
			if(resultKey.next()) {
				consumables_id = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			consumables.setItem_id(consumables_id);
			 **/
			return consumables;
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


	
	
	public Consumables delete(Consumables consumables) throws SQLException {
		String deleteConsumables = "DELETE FROM Weapon WHERE weapon_id=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteConsumables);
			deleteStmt.setInt(1, consumables.getItem_id());
			deleteStmt.executeUpdate();

			super.delete(consumables);
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
	
	public Consumables getConsumablesByID(int item_id) throws SQLException {
		String selectConsumables =
			"SELECT Consumables.consumables_id AS item_id, descriptions " +
			"FROM Consumables INNER JOIN Item " +
			"  ON item_id = Item.item_id " +
			"WHERE item_id = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectConsumables);
			selectStmt.setInt(1, item_id);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int resultItem_Id = results.getInt("miscellaneous_id");
				String item_name = results.getString("item_name");
				int item_max_size = results.getInt("item_max_size");
				boolean could_sold_vendor = results.getBoolean("could_sold_vendor");
				Double vendor_price = results.getDouble("vendor_price");
				int item_level = results.getInt("item_level");
				String descriptions = results.getString("descriptions");
				Double attribute_bonuses = results.getDouble("attribute_bonuses");
				
				Consumables consumables = new Consumables(resultItem_Id, item_name, item_max_size, could_sold_vendor, vendor_price, item_level, descriptions, attribute_bonuses);
			
				return consumables;
				
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
	
}