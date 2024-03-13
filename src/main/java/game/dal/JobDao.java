package main.java.game.dal;

import main.java.game.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class JobDao {
	protected ConnectionManager connectionManager;

	private static JobDao instance = null;
	protected JobDao() {
		connectionManager = new ConnectionManager();
	}
	public static JobDao getInstance() {
		if(instance == null) {
			instance = new JobDao();
		}
		return instance;
	}

	public Job create(Job job) throws SQLException {
		String insertJob =
			"INSERT IGNORE INTO job(job_id,avaliability,category,maximum_level)" +
			"VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			// Job has an auto-generated key. So we want to retrieve that key.
			insertStmt = connection.prepareStatement(insertJob,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, job.getJob_id());
			insertStmt.setBoolean(2, job.getAvaliability());
			insertStmt.setString(3, job.getCategory());
			insertStmt.setInt(4, job.getMaximum_level());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			// For more details, see:
			// http://dev.mysql.com/doc/connector-j/en/connector-j-usagenotes-last-insert-id.html
			/**resultKey = insertStmt.getGeneratedKeys();
			int jobId = -1;
			if(resultKey.next()) {
				jobId = resultKey.getInt("job_id");
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			job.setJob_id(jobId);
			 **/
			return job;
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
	 * Delete the Job instance.
	 * This runs a DELETE statement.
	 */
	public Job delete(Job job) throws SQLException {
		String deleteJob = "DELETE FROM job WHERE job_id=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteJob);
			deleteStmt.setInt(1, job.getJob_id());
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
	
	public Job getJobByJob_id(int job_id)throws SQLException {
		String selectJob= "SELECT avaliability, category, maximum_level FROM Job WHERE job_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectJob);
			selectStmt.setInt(1, job_id);
			results = selectStmt.executeQuery();
			if(results.next()) {
				boolean avaliability = results.getBoolean("avaliability");
				String category = results.getString("category");
				int maximum_level = results.getInt("maximum_level");
				Job job = new Job(job_id, avaliability, category, maximum_level);
				return job;
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
	 * Get the all the Jobs in a category.
	 */
	public List<Job> getJobsByCategory(String category) throws SQLException {
		List<Job> jobs = new ArrayList<Job>();
		String selectJob =
			"SELECT job_id,avaliability, category, maximum_level " +
			"FROM Job " +
			"WHERE category=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectJob);
			selectStmt.setString(1, category);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int job_id = results.getInt("job_id");
				boolean avaliability = results.getBoolean("avaliability");
				int maximum_level = results.getInt("maximum_level");
				Job job = new Job(job_id,avaliability, category, maximum_level);
				jobs.add(job);
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
		return jobs;
	}
}
