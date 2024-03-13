package main.java.game.model;


public class otherSlots {

    public enum NAME{
        HEAD,BODY,HANDS,LEGS,OFFHAND,EARRING,NECK,WRIST,LEFTRING,RIGHTRING;
    }
    private int otherSlorsId;
    private NAME slotName;
    private int gearId;

    public otherSlots(int otherSlorsId, NAME slotName, int gearId) {
        this.otherSlorsId = otherSlorsId;
        this.slotName = slotName;
        this.gearId = gearId;
    }

    public otherSlots(int otherSlorsId) {
        this.otherSlorsId = otherSlorsId;
    }

    public int getOtherSlorsId() {
        return otherSlorsId;
    }

    public NAME getSlotName() {
        return slotName;
    }

    public int getGearId() {
        return gearId;
    }

    public void setOtherSlorsId(int otherSlorsId) {
        this.otherSlorsId = otherSlorsId;
    }

    public void setSlotName(NAME slotName) {
        this.slotName = slotName;
    }

    public void setGearId(int gearId) {
        this.gearId = gearId;
    }
}
