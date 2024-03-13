package main.java.game.model;



public class Miscellaneous extends Item {
	protected String description;
	
	public Miscellaneous(int item_id, String item_name, int item_max_size, boolean could_sold_vendor, Double vendor_price, String description) {
		super(item_id, item_name, item_max_size, could_sold_vendor, vendor_price);
		this.description = description;
	}
	
	
	public Miscellaneous(int item_id) {
		super(item_id);
	}
	
	public Miscellaneous(String item_name, int item_max_size, boolean could_sold_vendor, Double vendor_price, String description) {
		super(item_name, item_max_size, could_sold_vendor, vendor_price);
		this.description = description;
	}
	
	/** Getters and setters. */
	

	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
		
}

