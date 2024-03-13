package main.java.game.model;


public class Player {
	private String userName;
    private String playerName;
    private String email;
    private String address;
    private String phone;
    private boolean isActive;

    public Player(String userName, String playerName, String email, String address, String phone, boolean isActive) {
        this.userName = userName;

        this.playerName = playerName;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}
