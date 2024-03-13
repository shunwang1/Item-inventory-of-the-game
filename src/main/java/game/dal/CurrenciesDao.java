package main.java.game.dal;

import main.java.game.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Data access object (DAO) class to interact with the underlying Persons table in your MySQL
 * instance. This is used to store {@link Currencies} into your MySQL instance and retrieve 
 * {@link Currencies} from MySQL instance.
 */
public class CurrenciesDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static CurrenciesDao instance = null;
	protected CurrenciesDao() {
		connectionManager = new ConnectionManager();
	}
	public static CurrenciesDao getInstance() {
		if(instance == null) {
			instance = new CurrenciesDao();
		}
		return instance;
	}

	/**
	 * Save the Currencies instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Currencies create(Currencies currencies) throws SQLException {
		String insertCurrencies = "INSERT IGNORE INTO currencies(currency_id,currency_name,max_cap,weekly_cap,"
				+ "amount,amount_weekly) VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCurrencies,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1,currencies.getCurrency_id());
			insertStmt.setString(2, currencies.getCurrency_name());
			insertStmt.setDouble(3, currencies.getMax_cap());
			insertStmt.setDouble(4, currencies.getWeekly_cap());
			insertStmt.setDouble(5, currencies.getAmount());
			insertStmt.setDouble(6, currencies.getAmount_weekly());
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			/**int currency_id = -1;
			if(resultKey.next()) {
				currency_id = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			currencies.setCurrency_id(currency_id);
			 **/
			return currencies;
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
	 * Get the Currencies record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Currencies instance.
	 */
	public Currencies getCurrenciesById(int currency_id) throws SQLException {
		String selectCurrencies =
			"SELECT currency_id,currency_name,max_cap,weekly_cap,amount,amount_weekly " +
			"FROM currencies " +
			"WHERE currency_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCurrencies);
			selectStmt.setInt(1, currency_id);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultCurrency_id = results.getInt("currency_id");
				String currency_name = results.getString("currency_name");
				double max_cap = results.getDouble("max_cap");
				double weekly_cap = results.getDouble("weekly_cap");
				double amount = results.getDouble("amount");
				double amount_weekly = results.getDouble("amount_weekly");
				Currencies currencies = new Currencies(resultCurrency_id, currency_name, max_cap,
						weekly_cap, amount, amount_weekly);
				return currencies;
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
