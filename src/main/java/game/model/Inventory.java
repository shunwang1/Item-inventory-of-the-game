package main.java.game.model;


public class Inventory {
	protected int inventory_id;
	protected Each_Character each_character;
	protected int number_of_slots;
	

	public Inventory(int inventory_id, Each_Character each_character, int number_of_slots) {
		this.inventory_id = inventory_id;
		this.each_character = each_character;
		this.number_of_slots = number_of_slots;
	}

	public Inventory(int inventory_id) {
		this.inventory_id = inventory_id;
	}

	public Inventory(Each_Character each_character, int number_of_slots) {
		this.each_character = each_character;
		this.number_of_slots = number_of_slots;
	}
	

	/** Getters and setters. */

	public int getInventory_id() {
		return inventory_id;
	}

	public void setInventory_id(int inventory_id) {
		this.inventory_id = inventory_id;
	}

	public Each_Character getEach_character() {
		return each_character;
	}

	public void setEach_character(Each_Character each_character) {
		this.each_character = each_character;
	}

	public int getNumber_of_slots() {
		return number_of_slots;
	}

	public void setNumber_of_slots(int number_of_slots) {
		this.number_of_slots = number_of_slots;
	}
		
}

