package main.java.game.model;


public class Job {
	
	protected int job_id;
	protected boolean avaliability;
	protected String category;
	protected int maximum_level;
	
	public Job(int job_id, boolean avaliability, String category, int maximum_level) {
		super();
		this.job_id = job_id;
		this.avaliability = avaliability;
		this.category = category;
		this.maximum_level = maximum_level;
	}

	public Job(int job_id) {
		super();
		this.job_id = job_id;
	}

	public Job(boolean avaliability, String category, int maximum_level) {
		super();
		this.avaliability = avaliability;
		this.category = category;
		this.maximum_level = maximum_level;
	}

	public int getJob_id() {
		return job_id;
	}

	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}

	public boolean getAvaliability() {
		return avaliability;
	}

	public void setAvaliability(boolean avaliability) {
		this.avaliability = avaliability;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getMaximum_level() {
		return maximum_level;
	}

	public void setMaximum_level(int maximum_level) {
		this.maximum_level = maximum_level;
	}
	
	
}