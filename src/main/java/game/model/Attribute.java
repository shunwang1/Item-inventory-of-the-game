package main.java.game.model;

public class Attribute {
    private int attributeId;
    private int strength;
    private int intelligence;
    private int mind;
    private int dexterity;

    public Attribute(int attributeId, int strength, int intelligence, int mind, int dexterity) {
        this.attributeId = attributeId;
        this.strength = strength;
        this.intelligence = intelligence;
        this.mind = mind;
        this.dexterity = dexterity;
    }

    public Attribute(int attributeId) {
        this.attributeId = attributeId;
    }

    public void setAttributeId(int attributeId) {
        this.attributeId = attributeId;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setMind(int mind) {
        this.mind = mind;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getAttributeId() {
        return attributeId;
    }

    public int getStrength() {
        return strength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getMind() {
        return mind;
    }

    public int getDexterity() {
        return dexterity;
    }
}
