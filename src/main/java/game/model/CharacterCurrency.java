package main.java.game.model;


public class CharacterCurrency {
	protected Each_Character each_character;
	protected Currencies currencies;
	protected double amount_weekly;
	protected double amount_total;
	
	public CharacterCurrency(Each_Character each_character, Currencies currencies, double amount_weekly,
			double amount_total) {
		this.each_character = each_character;
		this.currencies = currencies;
		this.amount_weekly = amount_weekly;
		this.amount_total = amount_total;
	}

	public CharacterCurrency(Each_Character each_character, Currencies currencies) {
		this.each_character = each_character;
		this.currencies = currencies;
	}
	
	
	/** Getters and setters. */

	public Each_Character getEach_character() {
		return each_character;
	}

	public void setEach_character(Each_Character each_character) {
		this.each_character = each_character;
	}

	public Currencies getCurrencies() {
		return currencies;
	}

	public void setCurrencies(Currencies currencies) {
		this.currencies = currencies;
	}

	public double getAmount_weekly() {
		return amount_weekly;
	}

	public void setAmount_weekly(double amount_weekly) {
		this.amount_weekly = amount_weekly;
	}

	public double getAmount_total() {
		return amount_total;
	}

	public void setAmount_total(double amount_total) {
		this.amount_total = amount_total;
	}
	


}
