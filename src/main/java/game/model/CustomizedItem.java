package main.java.game.model;

public class CustomizedItem {
	private int customizedItemId;
    private int itemId;
    private int characterId;
    private String dyeColor;
    private boolean highQuality;
    private boolean normalQuality;
    private double conditionValue;

    public CustomizedItem(int customizedItemId, int itemId, int characterId, String dyeColor, boolean highQuality, boolean normalQuality, double conditionValue) {
        this.customizedItemId = customizedItemId;
        this.itemId = itemId;
        this.characterId = characterId;
        this.dyeColor = dyeColor;
        this.highQuality = highQuality;
        this.normalQuality = normalQuality;
        this.conditionValue = conditionValue;
    }

    // Getters and Setters
    public int getCustomizedItemId() {
        return customizedItemId;
    }

    public void setCustomizedItemId(int customizedItemId) {
        this.customizedItemId = customizedItemId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public String getDyeColor() {
        return dyeColor;
    }

    public void setDyeColor(String dyeColor) {
        this.dyeColor = dyeColor;
    }

    public boolean isHighQuality() {
        return highQuality;
    }

    public void setHighQuality(boolean highQuality) {
        this.highQuality = highQuality;
    }

    public boolean isNormalQuality() {
        return normalQuality;
    }

    public void setNormalQuality(boolean normalQuality) {
        this.normalQuality = normalQuality;
    }

    public double getConditionValue() {
        return conditionValue;
    }

    public void setConditionValue(double conditionValue) {
        this.conditionValue = conditionValue;
    }

}
