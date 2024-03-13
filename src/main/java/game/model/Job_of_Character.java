package main.java.game.model;



public class Job_of_Character {
	
	protected int job_id; 
	protected int character_id;
	protected boolean unlocked = false;
	protected int current_level;
	protected int EXP_earned;
	
	public Job_of_Character(int job_id, int character_id, boolean unlocked, int current_level, int EXP_earned) {
		super();
		this.job_id = job_id;
		this.character_id = character_id;
		this.unlocked = unlocked;
		this.current_level = current_level;
		this.EXP_earned = EXP_earned;
	}

	public int getJob_id() {
		return job_id;
	}

	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}

	public int getCharacter_id() {
		return character_id;
	}

	public void setCharacter_id(int character_id) {
		this.character_id = character_id;
	}

	public boolean getUnlocked() {
		return unlocked;
	}

	public void setUnlocked(boolean unlocked) {
		this.unlocked = unlocked;
	}

	public int getCurrent_level() {
		return current_level;
	}

	public void setCurrent_level(int current_level) {
		this.current_level = current_level;
	}

	public int getEXP_earned() {
		return EXP_earned;
	}

	public void setEXP_earned(int eXP_earned) {
		this.EXP_earned = eXP_earned;
	}
	
	
	
	
}
