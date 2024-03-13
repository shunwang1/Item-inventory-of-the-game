package main.java.game.dal;

import main.java.game.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class AttributeDao {
    private static AttributeDao instance = null;
    protected ConnectionManager connectionManager;
    protected AttributeDao(){connectionManager = new ConnectionManager();}
    public static AttributeDao getInstance(){
        if(instance==null){
            instance = new AttributeDao();
        }
        return instance;
    }
    public Attribute create(Attribute att) throws SQLException{
        String insertAtt="INSERT IGNORE INTO attribute(attribute_ID,strength,intelligence,mind,dexterity) VALUES(?,?,?,?,?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try{
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertAtt);
            insertStmt.setInt(1,att.getAttributeId());
            insertStmt.setInt(2,att.getStrength());
            insertStmt.setInt(3,att.getIntelligence());
            insertStmt.setInt(4,att.getMind());
            insertStmt.setInt(5,att.getDexterity());
            insertStmt.executeUpdate();
            return att;
        }
        catch (SQLException e){
            e.printStackTrace();
            throw e;
        }
        finally {
            if(connection!=null){
                connection=null;
            }
            if(insertStmt!=null){
                insertStmt=null;
            }
        }
    }

    public Attribute getAttributeByCharacterId(int characterId) throws SQLException {
        String query = "SELECT a.attribute_id, a.strength, a.intelligence, a.mind, a.dexterity " +
                       "FROM attribute a " +
                       "JOIN each_character ca ON a.attribute_id = ca.attribute_id " +
                       "WHERE ca.character_id = ?";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        Attribute attribute = null;
        
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(query);
            selectStmt.setInt(1, characterId);
            
            results = selectStmt.executeQuery();
            if (results.next()) {
                int attributeId = results.getInt("attribute_ID");
                int strength = results.getInt("strength");
                int intelligence = results.getInt("intelligence");
                int mind = results.getInt("mind");
                int dexterity = results.getInt("dexterity");
                
                // Create the attribute object with retrieved values
                attribute = new Attribute(attributeId, strength, intelligence, mind, dexterity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            // Properly closing resources
            if (results != null) {
                results.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return attribute;
    }
}
