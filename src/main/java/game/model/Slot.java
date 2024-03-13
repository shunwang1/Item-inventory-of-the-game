package main.java.game.model;


public class Slot {
	protected int slot_id;
	protected int item_id;
	protected int item_amount;
	protected int slot_position;
	protected int inventory_id;
	
	public Slot(int slot_id, int item_id, int item_amount, int slot_position, int inventory_id) {
		this.slot_id = slot_id;
		this.item_id = item_id;
		this.item_amount = item_amount;
		this.slot_position = slot_position;
		this.inventory_id = inventory_id;
	}

	public Slot(int slot_id) {
		this.slot_id = slot_id;
	}
	
	public Slot(int item_id, int item_amount, int slot_position, int inventory_id) {
		this.item_id = item_id;
		this.item_amount = item_amount;
		this.slot_position = slot_position;
		this.inventory_id = inventory_id;
	}

	// getters and setters
	public int getSlot_id() {
		return slot_id;
	}

	public void setSlot_id(int slot_id) {
		this.slot_id = slot_id;
	}



	public int getItem_amount() {
		return item_amount;
	}

	public void setItem_amount(int item_amount) {
		this.item_amount = item_amount;
	}
	
	public int getSlot_position() {
		return slot_position;
	}

	public void setSlot_position(int slot_position) {
		this.slot_position = slot_position;
	}

	public int getItem_id() {
		return item_id;
	}

	public int getInventory_id() {
		return inventory_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public void setInventory_id(int inventory_id) {
		this.inventory_id = inventory_id;
	}
}