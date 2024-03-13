package main.java.game.model;


public class Consumables extends Item {
	protected int item_level;
	protected String descriptions;
	protected Double attribute_bonuses;
	
	public Consumables(int item_id, String item_name, int item_max_size, boolean could_sold_vendor, Double vendor_price, int item_level, String descriptions, Double attribute_bonuses) {
		super(item_id, item_name, item_max_size, could_sold_vendor, vendor_price);
		this.item_level = item_level;
		this.descriptions = descriptions;
		this.attribute_bonuses = attribute_bonuses;
	}
	
	
	public Consumables(int item_id) {
		super(item_id);
	}
	
	public Consumables(String item_name, int item_max_size, boolean could_sold_vendor, Double vendor_price, int item_level, String descriptions, Double attribute_bonuses) {
		super(item_name, item_max_size, could_sold_vendor, vendor_price);
		this.item_level = item_level;
		this.descriptions = descriptions;
		this.attribute_bonuses = attribute_bonuses;
	}

	
	/** Getters and setters. */


	public int getItem_level() {
		return item_level;
	}


	public void setItem_level(int item_level) {
		this.item_level = item_level;
	}

	public String getDescriptions() {
		return descriptions;
	}


	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	

	public Double getAttribute_bonuses() {
		return attribute_bonuses;
	}


	public void setAttribute_bonuses(Double attribute_bonuses) {
		this.attribute_bonuses = attribute_bonuses;
	}

	
}

