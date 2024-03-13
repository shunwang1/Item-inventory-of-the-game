package main.java.game.model;

/**
 * Item is a simple, plain old java objects (POJO).
 * 
 * Item/ItemDao is the superclass for Gear/GearDao and Weapon/WeaponDao.
 */
public class Item{
	protected int item_id;
	protected String item_name;
	protected int item_max_size;
	protected boolean could_sold_vendor;
	protected double vendor_price;
	
	
	public Item(int item_id, String item_name, int item_max_size, boolean could_sold_vendor, double vendor_price) {
		this.item_id = item_id;
		this.item_name = item_name;
		this.item_max_size = item_max_size;
		this.could_sold_vendor = could_sold_vendor;
		this.vendor_price = vendor_price;
	}

	
	public Item(int item_id) {
		this.item_id = item_id;
	}
	

	public Item(String item_name, int item_max_size, boolean could_sold_vendor, double vendor_price) {
		this.item_name = item_name;
		this.item_max_size = item_max_size;
		this.could_sold_vendor = could_sold_vendor;
		this.vendor_price = vendor_price;
	}
	
	
	/** Getters and setters. */

	public int getItem_id() {
		return item_id;
	}


	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}


	public String getItem_name() {
		return item_name;
	}


	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}


	public int getItem_max_size() {
		return item_max_size;
	}


	public void setItem_max_size(int item_max_size) {
		this.item_max_size = item_max_size;
	}


	public boolean isCould_sold_vendor() {
		return could_sold_vendor;
	}


	public void setCould_sold_vendor(boolean could_sold_vendor) {
		this.could_sold_vendor = could_sold_vendor;
	}


	public double getVendor_price() {
		return vendor_price;
	}


	public void setVendor_price(double vendor_price) {
		this.vendor_price = vendor_price;
	}
	
}
