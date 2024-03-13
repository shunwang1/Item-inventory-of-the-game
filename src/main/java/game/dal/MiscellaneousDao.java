package main.java.game.dal;

import main.java.game.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MiscellaneousDao extends ItemDao {

	private static MiscellaneousDao instance = null;
	protected MiscellaneousDao() {
		super();
	}
	public static MiscellaneousDao getInstance() {
		if(instance == null) {
			instance = new MiscellaneousDao();
		}
		return instance;
	}


	
	public Miscellaneous create(Miscellaneous miscellaneous) throws SQLException {
		String insertMiscellaneous =
			"INSERT IGNORE INTO Miscellaneous(descriptions) VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertMiscellaneous,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, miscellaneous.getDescription());

			
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			/**resultKey = insertStmt.getGeneratedKeys();
			int miscellaneous_id = -1;
			if(resultKey.next()) {
				miscellaneous_id = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			miscellaneous.setItem_id(miscellaneous_id);
			 **/
			return miscellaneous;
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


	public Miscellaneous delete(Miscellaneous miscellaneous) throws SQLException {
		String deleteMiscellaneous = "DELETE FROM Weapon WHERE weapon_id=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteMiscellaneous);
			deleteStmt.setInt(1, miscellaneous.getItem_id());
			deleteStmt.executeUpdate();

			super.delete(miscellaneous);
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
	
	public Miscellaneous getMiscellaneousByID(int item_id) throws SQLException {
		String selectMiscellaneous =
			"SELECT Miscellaneous.miscellaneous_id AS item_id, descriptions " +
			"FROM Miscellaneous INNER JOIN Item " +
			"  ON item_id = Item.item_id " +
			"WHERE item_id = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMiscellaneous);
			selectStmt.setInt(1, item_id);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int resultItem_Id = results.getInt("miscellaneous_id");
				String item_name = results.getString("item_name");
				int item_max_size = results.getInt("item_max_size");
				boolean could_sold_vendor = results.getBoolean("could_sold_vendor");
				Double vendor_price = results.getDouble("vendor_price");
				String descriptions = results.getString("descriptions");
				
				Miscellaneous miscellaneous = new Miscellaneous(resultItem_Id, item_name, item_max_size, could_sold_vendor, vendor_price, descriptions);
			
				return miscellaneous;
				
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