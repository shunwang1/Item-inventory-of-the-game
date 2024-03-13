package main.java.game.model;


public class Gear extends Item{
	protected Integer gear_level;
	protected Integer required_level;
	protected Double attribute_bonuses;
	protected Integer defense_rating;
	protected Integer customerID;
	protected Integer magic_defense_rating;
	
	public Gear(int item_id, String item_name, int item_max_size, boolean could_sold_vendor, double vendor_price,
		 Integer gear_level, Integer required_level, Double attribute_bonuses,
			Integer defense_rating, Integer customerID, Integer magic_defense_rating) {
		super(item_id, item_name, item_max_size, could_sold_vendor, vendor_price);
		this.gear_level = gear_level;
		this.required_level = required_level;
		this.attribute_bonuses = attribute_bonuses;
		this.defense_rating = defense_rating;
		this.customerID = customerID;
		this.magic_defense_rating = magic_defense_rating;
	}

	public Gear(int item_id) {
		super(item_id);
	}
	
	
	public Integer getGear_level() {
		return gear_level;
	}
	public void setGear_level(Integer gear_level) {
		this.gear_level = gear_level;
	}
	public Integer getRequired_level() {
		return required_level;
	}
	public void setRequired_level(Integer required_level) {
		this.required_level = required_level;
	}
	public Double getAttribute_bonuses() {
		return attribute_bonuses;
	}
	public void setAttribute_bonuses(Double attribute_bonuses) {
		this.attribute_bonuses = attribute_bonuses;
	}
	public Integer getDefense_rating() {
		return defense_rating;
	}
	public void setDefense_rating(Integer defense_rating) {
		this.defense_rating = defense_rating;
	}
	public Integer getCustomerID() {
		return customerID;
	}
	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
	}
	public Integer getMagic_defense_rating() {
		return magic_defense_rating;
	}
	public void setMagic_defense_rating(Integer magic_defense_rating) {
		this.magic_defense_rating = magic_defense_rating;
	}
	
}

