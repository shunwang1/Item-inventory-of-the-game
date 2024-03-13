package main.java.game.dal;

import main.java.game.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Exp_NeededDao {
	protected ConnectionManager connectionManager;

	private static Exp_NeededDao instance = null;
	protected Exp_NeededDao() {
		connectionManager = new ConnectionManager();
	}
	public static Exp_NeededDao getInstance() {
		if(instance == null) {
			instance = new Exp_NeededDao();
		}
		return instance;
	}

	public Exp_Needed create(Exp_Needed exp_needed) throws SQLException {
		String insertExp_Needed =
			"INSERT IGNORE INTO Exp_Needed(job_id, current_level, EXP_needed_for_current_level) " +
			"VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertExp_Needed,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, exp_needed.getJob_id());
			insertStmt.setInt(2, exp_needed.getCurrent_level());
			insertStmt.setInt(3, exp_needed.getEXP_needed_for_current_level());
			insertStmt.executeUpdate();
			return exp_needed;
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


	public Exp_Needed getExp_NeededByJobOfCharacter(Job_of_Character job_of_Character) throws SQLException {
		String selectExp_Needed =
			"SELECT job_id, current_level, EXP_needed_for_current_level " +
			"FROM Exp_Needed " +
			"WHERE job_id=? AND current_level=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectExp_Needed);
			selectStmt.setInt(1, job_of_Character.getJob_id());
			selectStmt.setInt(2, job_of_Character.getCurrent_level());
			results = selectStmt.executeQuery();
			if(results.next()) {
				int job_id = job_of_Character.getJob_id();
				int current_level = job_of_Character.getCurrent_level();
				int EXP_needed_for_current_level = results.getInt("EXP_needed_for_current_level");
				Exp_Needed exp_needed = new Exp_Needed(job_id, current_level, EXP_needed_for_current_level);
				return exp_needed;
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
	public List<Exp_Needed> getExps_NeededForJob(Job job) throws SQLException {
		List<Exp_Needed> exps_Needed = new ArrayList<Exp_Needed>();
		String selectExp_Needed =
			"SELECT job_id, current_level, EXP_needed_for_current_level " +
			"FROM Exp_Needed " +
			"WHERE job_id=;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectExp_Needed);
			selectStmt.setInt(1, job.getJob_id());
			results = selectStmt.executeQuery();
			if(results.next()) {
				int job_id = job.getJob_id();
				int current_level = results.getInt("current_level");
				int EXP_needed_for_current_level = results.getInt("EXP_needed_for_current_level");
				Exp_Needed exp_needed = new Exp_Needed(job_id, current_level, EXP_needed_for_current_level);
				exps_Needed.add(exp_needed);
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
		return exps_Needed;
	}

	
}
