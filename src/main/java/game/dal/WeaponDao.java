package main.java.game.dal;

import main.java.game.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class WeaponDao extends ItemDao {
	
	private static WeaponDao instance = null;
	protected WeaponDao() {
		super();
	}
	public static WeaponDao getInstance() {
		if(instance == null) {
			instance = new WeaponDao();
		}
		return instance;
	}


	
	public Weapon create(Weapon weapon) throws SQLException {
		String insertWeapon =
			"INSERT IGNORE INTO weapon(weapon_id,item_level, required_level, attribute_bonuses, damage_done, auto_attack, attack_delay )" +
		" VALUES(?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertWeapon,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1,weapon.getItem_id());
			insertStmt.setInt(2, weapon.getItem_level());
			insertStmt.setInt(3, weapon.getRequired_level());
			insertStmt.setDouble(4, weapon.getAttribute_bonuses());
			insertStmt.setDouble(5, weapon.getDamage_done());
			insertStmt.setBoolean(6, weapon.isAuto_attack());
			insertStmt.setInt(7, weapon.getAttack_delay());
			
			insertStmt.executeUpdate();

			/**
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int weapon_id = -1;
			if(resultKey.next()) {
				weapon_id = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			weapon.setItem_id(weapon_id);
			 **/
			return weapon;
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

	public Weapon delete(Weapon weapon) throws SQLException {
		String deleteWeapon = "DELETE FROM Weapon WHERE weapon_id=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteWeapon);
			deleteStmt.setInt(1, weapon.getItem_id());
			deleteStmt.executeUpdate();


			super.delete(weapon);

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
	
	public Weapon getWeaponByID(int item_id) throws SQLException {
		String selectWeapon =
			"SELECT Weapon.weapon_id AS item_id, item_level, required_level, attribute_bonuses, damage_done, auto_attack, attack_delay " +
			"FROM Weapon INNER JOIN Item " +
			"  ON item_id = Item.item_id " +
			"WHERE item_id =?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectWeapon);
			selectStmt.setInt(1, item_id);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int resultItem_Id = results.getInt("weapon_id");
				String item_name = results.getString("item_name");
				int item_max_size = results.getInt("item_max_size");
				boolean could_sold_vendor = results.getBoolean("could_sold_vendor");
				Double vendor_price = results.getDouble("vendor_price");
				int item_level = results.getInt("item_level");
				int required_level = results.getInt("required_level");
				Double attribute_bonuses = results.getDouble("attribute_bonuses");
				Double damage_done = results.getDouble("damage_done");
				boolean auto_attack = results.getBoolean("auto_attack");
				int attack_delay = results.getInt("attack_delay");
				
				Weapon weapon = new Weapon(resultItem_Id, item_name, item_max_size, could_sold_vendor, vendor_price, item_level, required_level, attribute_bonuses, damage_done, auto_attack, attack_delay);
				return weapon;
				
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