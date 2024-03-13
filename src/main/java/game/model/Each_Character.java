package main.java.game.model;

public class Each_Character {

    private int characterId;
    private String userName;
    private String firstName;
    private String lastName;
    private int maxHP;
    private int maxMP = 10000;
    private int currencyId;
    private int jobId;


    private int attributeId;
    private int equippedGearId;

    public Each_Character(int characterId, String userName, String firstName, String lastName, int maxHP, int currencyId, int jobId, int attributeId, int equippedGearId) {
        this.characterId = characterId;
        this.userName=userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.maxHP = maxHP;
        this.currencyId = currencyId;
        this.jobId = jobId;
        this.attributeId = attributeId;
        this.equippedGearId = equippedGearId;
    }

    public Each_Character(int characterId, String firstName, String lastName) {
        this.characterId = characterId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public void setMaxMP(int maxMP) {
        this.maxMP = maxMP;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public void setAttributeId(int attributeId) {
        this.attributeId = attributeId;
    }

    public void setEquippedGearId(int equippedGearId) {
        this.equippedGearId = equippedGearId;
    }

    public int getCharacterId() {
        return characterId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getMaxMP() {
        return maxMP;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public int getJobId() {
        return jobId;
    }

    public int getAttributeId() {
        return attributeId;
    }

    public int getEquippedGearId() {
        return equippedGearId;
    }
}
