package main.java.game.dal;

import main.java.game.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Each_CharacterDao {
    private static Each_CharacterDao instance = null;
    protected ConnectionManager connectionManager;
    protected  Each_CharacterDao(){connectionManager=new ConnectionManager();}
    public static Each_CharacterDao getInstance(){
        if(instance==null){
            instance = new Each_CharacterDao();
        }
        return instance;
    }

    public Each_Character create(Each_Character eachCharacter) throws SQLException{
        String insertCharacter = "INSERT IGNORE INTO each_character(character_id,player_name,first_name,last_name,maxHP,maxMP,currency_id,job_id,attribute_id,equippedGear_id) VALUES(?,?,?,?,?,?,?,?,?,?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try{
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertCharacter);
            insertStmt.setInt(1,eachCharacter.getCharacterId());
            insertStmt.setString(2,eachCharacter.getUserName());
            insertStmt.setString(3,eachCharacter.getFirstName());
            insertStmt.setString(4,eachCharacter.getLastName());
            insertStmt.setInt(5,eachCharacter.getMaxHP());
            insertStmt.setInt(6,10000);
            insertStmt.setInt(7,eachCharacter.getCurrencyId());
            insertStmt.setInt(8,eachCharacter.getJobId());
            insertStmt.setInt(9,eachCharacter.getAttributeId());
            insertStmt.setInt(10,eachCharacter.getEquippedGearId());
            insertStmt.executeUpdate();
            return eachCharacter;
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally {
            if(connection!=null){
                connection=null;
            }
            if(insertStmt!=null){
                insertStmt=null;
            }
        }
    }
    public Each_Character getCharacterFromCharacterId(int ID) throws SQLException{
        String selectCharacter = "SELECT character_id,player_name,first_name,last_name,maxHP,currency_id,job_Id,attribute_Id,equippedGear_id FROM each_character where character_id = ?";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try{
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCharacter);
            selectStmt.setInt(1,ID);
            results = selectStmt.executeQuery();
            if(results.next()){
                String play_name = results.getString("player_name");
                String first = results.getString("first_name");
                String last = results.getString("last_name");
                int HP = results.getInt("maxHP");
                int curId = results.getInt("currency_id");
                int job = results.getInt("job_id");
                int attId = results.getInt("attribute_id");
                int equipId = results.getInt("equippedGear_id");
                Each_Character chara = new Each_Character(ID,play_name,first,last,HP,curId,job,attId,equipId);
                return chara;
            }
        }catch (SQLException e){
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
            }
        }
        return null;
    }
    public List<Each_Character> getCharacterFromPlayerId(String id)throws SQLException{
        List<Each_Character> characterList = new ArrayList<>();
        String selectCharacter = "SELECT character_id,player_name,first_name,last_name,maxHP,currency_id,job_id,attribute_id,equippedGear_id FROM each_character where player_name = ?";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try{
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCharacter);
            selectStmt.setString(1,id);
            results = selectStmt.executeQuery();
            while(results.next()){
                int chaId = results.getInt("character_id");
                String play_name = results.getString("player_name");
                String first = results.getString("first_name");
                String last = results.getString("last_name");
                int HP = results.getInt("maxHP");
                int curId = results.getInt("currency_id");
                int job = results.getInt("job_id");
                int attId = results.getInt("attribute_id");
                int equipId = results.getInt("equippedGear_id");
                Each_Character chara = new Each_Character(chaId,play_name,first,last,HP,curId,job,attId,equipId);
                characterList.add(chara);
            }
            return characterList;
        }catch (SQLException e){
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
            }
        }
    }
}
