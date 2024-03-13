package main.java.game.dal;
import main.java.game.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class GearDao extends ItemDao{
	

	
	private static GearDao instance = null;
	protected GearDao() {
		super();
	}
	public static GearDao getInstance() {
		if(instance == null) {
			instance = new GearDao();
		}
		return instance;
	}
	public Gear create(Gear gear) throws SQLException {
		create(new Item(gear.getItem_name(),
				gear.getItem_max_size(), gear.isCould_sold_vendor(), gear.getVendor_price()));
		
		String insertGear = "INSERT IGNORE INTO Gear(item_id,gear_level, required_level, attribute_bonuses, defense_rating, customerID, magic_defense_rating) "
				+ " VALUES(?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertGear,Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, gear.getItem_id());
			insertStmt.setInt(2, gear.getGear_level());
			insertStmt.setInt(3, gear.getRequired_level());
			insertStmt.setDouble(4, gear.getAttribute_bonuses());
			insertStmt.setInt(5, gear.getDefense_rating());
			insertStmt.setInt(6, gear.getCustomerID());
			insertStmt.setInt(7, gear.getMagic_defense_rating());

			insertStmt.executeUpdate();
			
			return gear;
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


	public Gear getGearByItem_id(int item_id)throws SQLException {
		String selectGear= 
				"SELECT Item.item_id AS item_id, item_name, item_max_size,"
				+ "could_sold_vendor, vendor_price"
				+ "gear_level, required_level, attribute_bonuses,"
				+ "defense_rating, customerID, magic_defense_rating " +
						"FROM Gear INNER JOIN Item " +
						"  ON Gear.item_id = Item.item_id " +
						"WHERE Item.item_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectGear);
			selectStmt.setInt(1, item_id);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String item_name = results.getString("item_name");
				int item_max_size = results.getInt("item_max_size");
				boolean could_sold_vendor = results.getBoolean("could_sold_vendor");
				double vendor_price = results.getDouble("vendor_price");
				
				int gear_level = results.getInt("gear_level");
				int required_level = results.getInt("required_level");
				double attribute_bonuses = results.getDouble("attribute_bonuses");
				int defense_rating = results.getInt("defense_rating");
				int customerID = results.getInt("customerID");
				int magic_defense_rating = results.getInt("magic_defense_rating");
				
				Gear gear = new Gear(item_id, item_name,item_max_size,could_sold_vendor, vendor_price, gear_level, required_level,
						attribute_bonuses, defense_rating, customerID, magic_defense_rating);
				return gear;
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
	
	// updated 12.10
	public Gear getGearByItem_name(String item_name)throws SQLException {
		String selectGear= 
				"SELECT Item.item_id AS item_id, item_name, item_max_size,"
				+ "could_sold_vendor, vendor_price"
				+ "gear_level, required_level, attribute_bonuses,"
				+ "defense_rating, customerID, magic_defense_rating " +
						"FROM Gear INNER JOIN Item " +
						"  ON Gear.item_id = Item.item_id " +
						"WHERE Item.item_name=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectGear);
			selectStmt.setString(1, item_name);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int item_id = results.getInt("item_id");
				int item_max_size = results.getInt("item_max_size");
				boolean could_sold_vendor = results.getBoolean("could_sold_vendor");
				double vendor_price = results.getDouble("vendor_price");
				
				int gear_level = results.getInt("gear_level");
				int required_level = results.getInt("required_level");
				double attribute_bonuses = results.getDouble("attribute_bonuses");
				int defense_rating = results.getInt("defense_rating");
				int customerID = results.getInt("customerID");
				int magic_defense_rating = results.getInt("magic_defense_rating");
				
				Gear gear = new Gear(item_id, item_name,item_max_size,could_sold_vendor, vendor_price, gear_level, required_level,
						attribute_bonuses, defense_rating, customerID, magic_defense_rating);
				return gear;
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
	
	// updated 12.10
		public Gear getGearByCustomerID(int customerID)throws SQLException {
			String selectGear= 
					"SELECT Item.item_id AS item_id, item_name, item_max_size,"
					+ "could_sold_vendor, vendor_price"
					+ "gear_level, required_level, attribute_bonuses,"
					+ "defense_rating, customerID, magic_defense_rating " +
							"FROM Gear INNER JOIN Item " +
							"  ON Gear.item_id = Item.item_id " +
							"WHERE Gear.customerID=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectGear);
				selectStmt.setInt(1, customerID);
				results = selectStmt.executeQuery();
				if(results.next()) {
					int item_id = results.getInt("item_id");
					String item_name = results.getString("item_name");
					int item_max_size = results.getInt("item_max_size");
					boolean could_sold_vendor = results.getBoolean("could_sold_vendor");
					double vendor_price = results.getDouble("vendor_price");
					
					int gear_level = results.getInt("gear_level");
					int required_level = results.getInt("required_level");
					double attribute_bonuses = results.getDouble("attribute_bonuses");
					int defense_rating = results.getInt("defense_rating");
					int magic_defense_rating = results.getInt("magic_defense_rating");
					
					Gear gear = new Gear(item_id, item_name,item_max_size,could_sold_vendor, vendor_price, gear_level, required_level,
							attribute_bonuses, defense_rating, customerID, magic_defense_rating);
					return gear;
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
	
	
	public Gear updateGear_level(Gear gear, int newGear_level) throws SQLException {
		String updateGear = "UPDATE Gear SET gear_level=? WHERE item_id=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateGear);
			updateStmt.setInt(1, newGear_level);
			updateStmt.setInt(2, gear.getItem_id());
			updateStmt.executeUpdate();
			gear.setGear_level(newGear_level);
			return gear;
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
	
	public Gear updateCustomerID(Gear gear, int newCustomerID) throws SQLException {
		String updateGear = "UPDATE Gear SET customerID=? WHERE item_id=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateGear);
			updateStmt.setInt(1, newCustomerID);
			updateStmt.setInt(2, gear.getItem_id());
			updateStmt.executeUpdate();
			gear.setCustomerID(newCustomerID);
			return gear;
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
}
