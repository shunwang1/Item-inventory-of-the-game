package main.java.game.dal;

import main.java.game.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;




public class Job_of_CharacterDao {
	protected ConnectionManager connectionManager;

	private static Job_of_CharacterDao instance = null;
	protected Job_of_CharacterDao() {
		connectionManager = new ConnectionManager();
	}
	public static Job_of_CharacterDao getInstance() {
		if(instance == null) {
			instance = new Job_of_CharacterDao();
		}
		return instance;
	}

	public Job_of_Character create(Job_of_Character job_of_Character) throws SQLException {
		String insertJob_of_Character =
			"INSERT IGNORE INTO Job_of_Character(job_id, character_id, unlocked, current_level, EXP_earned) " +
			"VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertJob_of_Character,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, job_of_Character.getJob_id());
			insertStmt.setInt(2, job_of_Character.getCharacter_id());
			insertStmt.setBoolean(3, job_of_Character.getUnlocked());
			insertStmt.setInt(4, job_of_Character.getCurrent_level());
			insertStmt.setInt(5, job_of_Character.getEXP_earned());
			insertStmt.executeUpdate();
			return job_of_Character;
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

	public Job_of_Character getJob_of_CharacterByJobAndCharacter(int job, int character) throws SQLException {
		String selectJob_of_Character =
			"SELECT job_id, character_id, unlocked, current_level, EXP_earned " +
			"FROM Job_of_Character " +
			"WHERE job_id=? AND character_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectJob_of_Character);
			selectStmt.setInt(1, job);
			selectStmt.setInt(2, character);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int job_id = job;
				boolean unlocked = results.getBoolean("unlocked");
				int current_level = results.getInt("current_level");
				int EXP_earned = results.getInt("EXP_earned");
				Job_of_Character job_of_Character = new Job_of_Character(job_id, character, unlocked, current_level, EXP_earned);
				return job_of_Character;
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
	public List<Job_of_Character> getJobs_of_CharacterForCharacter(Each_Character character) throws SQLException {
		List<Job_of_Character> jobs_of_Character = new ArrayList<Job_of_Character>();
		String selectJob_of_Character =
				"SELECT job_id, character_id, unlocked, current_level, EXP_earned " +
				"FROM Job_of_Character " +
				"WHERE character_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectJob_of_Character);
			selectStmt.setInt(1, character.getCharacterId());
			results = selectStmt.executeQuery();
			while(results.next()) {
				int job_id = results.getInt("job_id");
				int character_id = character.getCharacterId();
				boolean unlocked = results.getBoolean("unlocked");
				int current_level = results.getInt("current_level");
				int EXP_earned = results.getInt("EXP_earned");
				Job_of_Character job_of_Character = new Job_of_Character(job_id, character_id, unlocked, current_level, EXP_earned);
				jobs_of_Character.add(job_of_Character);
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
		return jobs_of_Character;
	}
	
	// updated 12.10
		public List<Job_of_Character> getJobs_of_CharacterForJob(Job job) throws SQLException {
			List<Job_of_Character> jobs_of_Character = new ArrayList<Job_of_Character>();
			String selectJob_of_Character =
					"SELECT job_id, character_id, unlocked, current_level, EXP_earned " +
					"FROM Job_of_Character " +
					"WHERE job_id=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectJob_of_Character);
				selectStmt.setInt(1, job.getJob_id());
				results = selectStmt.executeQuery();
				while(results.next()) {
					int job_id = results.getInt("job_id");
					int character_id = results.getInt("character_id");
					boolean unlocked = results.getBoolean("unlocked");
					int current_level = results.getInt("current_level");
					int EXP_earned = results.getInt("EXP_earned");
					Job_of_Character job_of_Character = new Job_of_Character(job_id, character_id, unlocked, current_level, EXP_earned);
					jobs_of_Character.add(job_of_Character);
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
			return jobs_of_Character;
		}

	public Job_of_Character updateJob_of_CharacterLevel(Job_of_Character job_of_Character, int newLevel) throws SQLException {
		String updateJob_of_Character = "UPDATE Job_of_Character SET current_level=? "
				+ "WHERE job_id=? AND character_id=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateJob_of_Character);
			updateStmt.setInt(1, newLevel);
			updateStmt.setInt(2, job_of_Character.getJob_id());
			updateStmt.setInt(3, job_of_Character.getCharacter_id());
			updateStmt.executeUpdate();
			
			job_of_Character.setCurrent_level(newLevel);
			return job_of_Character;
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
	
	public Job_of_Character updateJob_of_CharacterEXP(Job_of_Character job_of_Character, int newEXP_earned) throws SQLException {
		String updateJob_of_Character = "UPDATE Job_of_Character SET EXP_earned=? "
				+ "WHERE job_id=? AND character_id=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateJob_of_Character);
			updateStmt.setInt(1, newEXP_earned);
			updateStmt.setInt(2, job_of_Character.getJob_id());
			updateStmt.setInt(3, job_of_Character.getCharacter_id());
			updateStmt.executeUpdate();
			
			job_of_Character.setEXP_earned(newEXP_earned);
			return job_of_Character;
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
