package view;

import entity.User;

public class ReturnUser {
	public User user = new User();
	public String status = "";
	public String mod = "";
	
	public String getMod() {
		return mod;
	}
	public void setMod(String mod) {
		this.mod = mod;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ReturnUser(User user, String status, String mod) {
		super();
		this.user = user;
		this.status = status;
		this.mod = mod;
	}
	public ReturnUser() {
		super();
	}
	@Override
	public String toString() {
		return "ReturnUser [user=" + user.getUser_name() + ", status=" + status + ", mod=" + mod + "]";
	}
	
}
