package main.java.game.dal;

import main.java.game.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CustomizedItemDao {
	
	protected ConnectionManager connectionManager;
    private static CustomizedItemDao instance = null;

    protected CustomizedItemDao() {
        connectionManager = new ConnectionManager();
    }

    public static CustomizedItemDao getInstance() {
        if (instance == null) {
            instance = new CustomizedItemDao();
        }
        return instance;
    }

    public CustomizedItem getCustomizedItemById(int customizedItemId) throws SQLException {
        CustomizedItem customizedItem = null;
        String query = "SELECT * FROM customized_item WHERE Customized_item_id = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, customizedItemId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int itemId = resultSet.getInt("item_id");
                int characterId = resultSet.getInt("character_id");
                String dyeColor = resultSet.getString("dye_color");
                boolean highQuality = resultSet.getBoolean("high_quality");
                boolean normalQuality = resultSet.getBoolean("normal_quality");
                double conditionValue = resultSet.getDouble("condition_value");

                customizedItem = new CustomizedItem(customizedItemId, itemId, characterId, dyeColor, highQuality, normalQuality, conditionValue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return customizedItem;
    }

    public CustomizedItem create(CustomizedItem customizedItem) throws SQLException {
        String insertQuery = "INSERT IGNORE INTO customized_item (Customized_item_id,item_id, character_id, dye_color, high_quality, normal_quality, condition_value) VALUES (?, ?, ?, ?, ?, ?,?)";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {

            statement.setInt(1,customizedItem.getCustomizedItemId());
            statement.setInt(2, customizedItem.getItemId());
            statement.setInt(3, customizedItem.getCharacterId());
            statement.setString(4, customizedItem.getDyeColor());
            statement.setBoolean(5, customizedItem.isHighQuality());
            statement.setBoolean(6, customizedItem.isNormalQuality());
            statement.setDouble(7, customizedItem.getConditionValue());
            statement.executeUpdate();
            //int affectedRows = statement.executeUpdate();
            /**
            if (affectedRows == 0) {
                throw new SQLException("Creating customized item failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    customizedItem.setCustomizedItemId(generatedId);
                } else {
                    throw new SQLException("Creating customized item failed, no ID obtained.");
                }
            }**/
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return customizedItem;
    }

    public CustomizedItem update(CustomizedItem customizedItem, String newDyeColor) throws SQLException {
        String updateQuery = "UPDATE customized_item SET dye_color = ? WHERE Customized_item_id = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateQuery)) {

            statement.setString(1, newDyeColor);
            statement.setInt(2, customizedItem.getCustomizedItemId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                customizedItem.setDyeColor(newDyeColor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return customizedItem;
    }

    public void delete(CustomizedItem customizedItem) throws SQLException {
        String deleteQuery = "DELETE FROM customized_item WHERE Customized_item_id = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {

            statement.setInt(1, customizedItem.getCustomizedItemId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
