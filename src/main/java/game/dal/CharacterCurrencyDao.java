package main.java.game.dal;

import main.java.game.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class CharacterCurrencyDao {
	protected ConnectionManager connectionManager;

	private static CharacterCurrencyDao instance = null;
	protected CharacterCurrencyDao() {
		connectionManager = new ConnectionManager();
	}
	public static CharacterCurrencyDao getInstance() {
		if(instance == null) {
			instance = new CharacterCurrencyDao();
		}
		return instance;
	}

	public CharacterCurrency create(CharacterCurrency characterCurrency) throws SQLException {
		String insertCharacterCurrency =
			"INSERT IGNORE INTO charactercurrency(character_id,currency_id,amount_weekly,amount_total) " +
			"VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCharacterCurrency);
			insertStmt.setInt(1, characterCurrency.getEach_character().getCharacterId());
			insertStmt.setInt(2, characterCurrency.getCurrencies().getCurrency_id());
			insertStmt.setDouble(3, characterCurrency.getAmount_weekly());
			insertStmt.setDouble(4, characterCurrency.getAmount_total());
			insertStmt.executeUpdate();
			
			return characterCurrency;
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
		}
	}



	/**
	 * Get the BlogComments record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single BlogComments instance.
	 * Note that we use BlogPostsDao and BlogUsersDao to retrieve the referenced
	 * BlogPosts and BlogUsers instances.
	 * One alternative (possibly more efficient) is using a single SELECT statement
	 * to join the BlogComments, BlogPosts, BlogUsers tables and then build each object.
	 */
	public Currencies getCharacterCurrencyByCharacter_idAndCurrency_id(int each_character, int currencies) throws SQLException {
		String selectCharacterCurrency =
				"SELECT character_id,currency_id,amount_weekly,amount_total " +
						"FROM charactercurrency " +
						"WHERE character_id=? AND currency_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCharacterCurrency);
			selectStmt.setInt(1, each_character);
			selectStmt.setInt(2, currencies);
			results = selectStmt.executeQuery();
			if(results.next()) {
				double amount_weekly =  results.getDouble("amount_weekly");
				double amount_total = results.getDouble("amount_total");

				Currencies characterCurrency = new Currencies(currencies, amount_total, amount_weekly);
				return characterCurrency;
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
	

