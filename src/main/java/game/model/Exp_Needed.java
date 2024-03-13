package main.java.game.model;

public class Exp_Needed {
	
	protected int job_id;
	protected int current_level;
	protected int EXP_needed_for_current_level;
	
	
	public Exp_Needed(int job_id, int current_level, int eXP_needed_for_current_level) {
		super();
		this.job_id = job_id;
		this.current_level = current_level;
		EXP_needed_for_current_level = eXP_needed_for_current_level;
	}


	public int getJob_id() {
		return job_id;
	}


	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}


	public int getCurrent_level() {
		return current_level;
	}


	public void setCurrent_level(int current_level) {
		this.current_level = current_level;
	}


	public int getEXP_needed_for_current_level() {
		return EXP_needed_for_current_level;
	}


	public void setEXP_needed_for_current_level(int eXP_needed_for_current_level) {
		EXP_needed_for_current_level = eXP_needed_for_current_level;
	}	
	
	
}
