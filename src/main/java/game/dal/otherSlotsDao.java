package main.java.game.dal;

import main.java.game.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class otherSlotsDao {
    private static otherSlotsDao instance = null;
    protected ConnectionManager connectionManager;
    protected otherSlotsDao(){connectionManager=new ConnectionManager();}
    public static otherSlotsDao getInstance(){
        if(instance==null){
            instance=new otherSlotsDao();
        }
        return instance;
    }
    public otherSlots create(otherSlots slot)throws SQLException{
        String insertSlots = "INSERT IGNORE INTO other_slots(other_slot_id,slot_category,item_id) VALUES(?,?,?)";
        Connection connection=null;
        PreparedStatement insertStmt = null;
        try{

            connection=connectionManager.getConnection();
            insertStmt=connection.prepareStatement(insertSlots);
            insertStmt.setInt(1,slot.getOtherSlorsId());
            insertStmt.setString(2,slot.getSlotName().toString());
            insertStmt.setInt(3,slot.getGearId());
            insertStmt.executeUpdate();
            return slot;
        }catch (SQLException e) {
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
    public otherSlots getSlotFromSlotId(int ID)throws SQLException{
        String selectSlot="SELECT other_slots_id,slot_category,item_id FROM otherSlots WHERE other_slots_id = ?";
        Connection connection=null;
        PreparedStatement selectStmt = null;
        ResultSet result = null;
        try{
            connection=connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectSlot);
            selectStmt.setInt(1,ID);
            result=selectStmt.executeQuery();
            if(result.next()){
                int slotId = result.getInt("other_slots_id");
                String name = result.getString("slot_category");
                int gearId = result.getInt("item_id");
                otherSlots.NAME toName = otherSlots.NAME.valueOf(name);
                return new otherSlots(slotId,toName,gearId);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (result != null) {
                result.close();
            }
        }return null;
    }
}
