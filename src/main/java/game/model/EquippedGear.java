package main.java.game.model;

public class EquippedGear {
	
	protected Integer equippedGear_id;
	protected Integer mainHand;
	protected Integer other_slot;
	
	public EquippedGear(int equippedGear_id, int mainHand, int other_slot) {
		this.equippedGear_id = equippedGear_id;
		this.mainHand = mainHand;
		this.other_slot = other_slot;
	}
	
	public EquippedGear(int equippedGear_id) {
		this.equippedGear_id = equippedGear_id;
	}
	
	public EquippedGear(int mainHand, int other_slot) {
		this.mainHand = mainHand;
		this.other_slot = other_slot;
	}

	public Integer getEquippedGear_id() {
		return equippedGear_id;
	}

	public void setEquippedGear_id(Integer equippedGear_id) {
		this.equippedGear_id = equippedGear_id;
	}

	public Integer getMainHand() {
		return mainHand;
	}

	public void setMainHand(Integer mainHand) {
		this.mainHand = mainHand;
	}

	public Integer getOther_slot() {
		return other_slot;
	}

	public void setOther_slot(Integer other_slot) {
		this.other_slot = other_slot;
	}

}
