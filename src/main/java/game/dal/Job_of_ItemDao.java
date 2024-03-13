package main.java.game.dal;

import main.java.game.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Job_of_ItemDao {
    private static Job_of_ItemDao instance = null;
    protected ConnectionManager connectionManager;
    protected Job_of_ItemDao(){connectionManager=new ConnectionManager();}
    public static Job_of_ItemDao getInstance(){
        if(instance==null){
            instance=new Job_of_ItemDao();
        }
        return instance;
    }
    public Job_of_Item creat(Job_of_Item job) throws SQLException{
        String insertJob = "INSERT IGNORE INTO job_of_item(Job_ID,Customized_item_id) VALUES(?,?)";
        Connection connection=null;
        PreparedStatement insertStmt = null;
        try{
            connection=connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertJob);
            insertStmt.setInt(1,job.getJobId());
            insertStmt.setInt(2,job.getCustomizedItemId());
            insertStmt.executeUpdate();
            return job;
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally {
            if(connection!=null){
                connection.close();
            }
            if(insertStmt!=null){
                insertStmt.close();
            }
        }
    }
    public Job_of_Item getJobOfItemFromID(int ID)throws SQLException{
        String selectJob = "SELECT Job_ID,Customized_item_id FROM job_of_item where Job_ID=?";
        Connection connection=null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try{
            connection=connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectJob);
            selectStmt.setInt(1,ID);
            results=selectStmt.executeQuery();
            if(results.next()){
                int first = results.getInt("jobId");
                int second = results.getInt("customizedItemId");
                return new Job_of_Item(first,second);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }if(results != null) {
                results.close();
            }
        }
        return null;
    }
}
