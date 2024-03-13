package main.java.game.dal;

import main.java.game.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class EquippedGearDao{
	
	protected ConnectionManager connectionManager;
	
	private static EquippedGearDao instance = null;
	protected EquippedGearDao() {
		connectionManager= new ConnectionManager();
	}
	public static EquippedGearDao getInstance() {
		if(instance == null) {
			instance = new EquippedGearDao();
		}
		return instance;
	}

	public EquippedGear create(EquippedGear equippedGear) throws SQLException {
		String insertEquippedGear = "INSERT IGNORE INTO equippedGear(equippedGear_id,mainHand,other_slot)"
				+ " VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertEquippedGear,Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, equippedGear.getEquippedGear_id());
			insertStmt.setInt(2, equippedGear.getMainHand());

			insertStmt.setInt(3, equippedGear.getOther_slot());

			insertStmt.executeUpdate();
			
			/**resultKey = insertStmt.getGeneratedKeys();
			int equippedGearID = -1;
			if(resultKey.next()) {
				equippedGearID = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			equippedGear.setEquippedGear_id(equippedGearID);**/
			
			return equippedGear;
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


	public EquippedGear getEquippedGearByEquippedGear_id(int equippedGear_id)throws SQLException {
		String selectEquippedGear= "SELECT mainHand, other_slot FROM EquippedGear WHERE equippedGear_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectEquippedGear);
			selectStmt.setInt(1, equippedGear_id);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int mainHand = results.getInt("mainHand");
				int other_slot = results.getInt("other_slot");
				EquippedGear equippedGear = new EquippedGear(equippedGear_id, mainHand, other_slot);
				return equippedGear;
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

	public EquippedGear delete(EquippedGear equippedGear) throws SQLException {
		String deleteEquippedGear = "DELETE FROM EquippedGear WHERE equippedGear_id=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteEquippedGear);
			deleteStmt.setInt(1, equippedGear.getEquippedGear_id());
			deleteStmt.executeUpdate();
			
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
	
	public EquippedGear updateMainHand(EquippedGear equippedGear, int newMainHand) throws SQLException {
		String updateEquippedGear = "UPDATE EquippedGear SET mainHand=? WHERE equippedGear_id=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateEquippedGear);
			updateStmt.setInt(1, newMainHand);
			updateStmt.setInt(2, equippedGear.getEquippedGear_id());
			updateStmt.executeUpdate();
			equippedGear.setMainHand(newMainHand);
			return equippedGear;
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
	
	public EquippedGear updateOther_slot(EquippedGear equippedGear, int newOther_slot) throws SQLException {
		String updateEquippedGear = "UPDATE EquippedGear SET other_slot=? WHERE equippedGear_id=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateEquippedGear);
			updateStmt.setInt(1, newOther_slot);
			updateStmt.setInt(2, equippedGear.getEquippedGear_id());
			updateStmt.executeUpdate();
			equippedGear.setOther_slot(newOther_slot);
			return equippedGear;
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
