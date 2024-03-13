package main.java.game.model;


public class Weapon extends Item {
	protected int item_level;
	protected int required_level;
	protected double attribute_bonuses;
	protected double damage_done;
	protected boolean auto_attack;
	protected int attack_delay;
	
	public Weapon(int item_id, String item_name, int item_max_size, boolean could_sold_vendor, double vendor_price, int item_level, int required_level, double attribute_bonuses, double damage_done, boolean auto_attack,
			int attack_delay) {
		super(item_id, item_name, item_max_size, could_sold_vendor, vendor_price);
		this.item_level = item_level;
		this.required_level = required_level;
		this.attribute_bonuses = attribute_bonuses;
		this.damage_done = damage_done;
		this.auto_attack = auto_attack;
		this.attack_delay = attack_delay;
	}
	
	
	public Weapon(int item_id) {
		super(item_id);
	}


	
	/** Getters and setters. */

	public int getItem_level() {
		return item_level;
	}


	public void setItem_level(int item_level) {
		this.item_level = item_level;
	}


	public int getRequired_level() {
		return required_level;
	}


	public void setRequired_level(int required_level) {
		this.required_level = required_level;
	}


	public double getAttribute_bonuses() {
		return attribute_bonuses;
	}


	public void setAttribute_bonuses(float attribute_bonuses) {
		this.attribute_bonuses = attribute_bonuses;
	}



	public boolean isAuto_attack() {
		return auto_attack;
	}


	public void setAuto_attack(boolean auto_attack) {
		this.auto_attack = auto_attack;
	}


	public double getDamage_done() {
		return damage_done;
	}


	public void setDamage_done(float damage_done) {
		this.damage_done = damage_done;
	}


	public int getAttack_delay() {
		return attack_delay;
	}


	public void setAttack_delay(int attack_delay) {
		this.attack_delay = attack_delay;
	}
	
	
}

