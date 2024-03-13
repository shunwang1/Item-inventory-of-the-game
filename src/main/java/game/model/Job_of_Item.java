package main.java.game.model;


public class Job_of_Item {
    private int jobId;
    private int customizedItemId;

    public Job_of_Item(int jobId, int customizedItemId) {
        this.jobId = jobId;
        this.customizedItemId = customizedItemId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public void setCustomizedItemId(int customizedItemId) {
        this.customizedItemId = customizedItemId;
    }

    public int getJobId() {
        return jobId;
    }

    public int getCustomizedItemId() {
        return customizedItemId;
    }
}
