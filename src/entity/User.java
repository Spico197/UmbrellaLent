package entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class User {
	private BigDecimal user_id;
	private String user_name;
	private String user_pswd;
	private BigDecimal user_department_id;
	private String user_stu_iden;
	private BigDecimal user_wallet;
	private Timestamp user_create_time;
	public BigDecimal getUser_id() {
		return user_id;
	}
	public void setUser_id(BigDecimal user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_pswd() {
		return user_pswd;
	}
	public void setUser_pswd(String user_pswd) {
		this.user_pswd = user_pswd;
	}
	public BigDecimal getUser_department_id() {
		return user_department_id;
	}
	public void setUser_department_id(BigDecimal user_department_id) {
		this.user_department_id = user_department_id;
	}
	public String getUser_stu_iden() {
		return user_stu_iden;
	}
	public void setUser_stu_iden(String user_stu_iden) {
		this.user_stu_iden = user_stu_iden;
	}
	public BigDecimal getUser_wallet() {
		return user_wallet;
	}
	public void setUser_wallet(BigDecimal user_wallet) {
		this.user_wallet = user_wallet;
	}
	public Timestamp getUser_create_time() {
		return user_create_time;
	}
	public void setUser_create_time(Timestamp user_create_time) {
		this.user_create_time = user_create_time;
	}
	public User(BigDecimal user_id, String user_name, String user_pswd, BigDecimal user_department_id, String user_stu_iden,
			BigDecimal user_wallet, Timestamp user_create_time) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_pswd = user_pswd;
		this.user_department_id = user_department_id;
		this.user_stu_iden = user_stu_iden;
		this.user_wallet = user_wallet;
		this.user_create_time = user_create_time;
	}
	public User(String user_name, String user_pswd, BigDecimal user_department_id, String user_stu_iden, BigDecimal user_wallet,
			Timestamp user_create_time) {
		super();
		this.user_name = user_name;
		this.user_pswd = user_pswd;
		this.user_department_id = user_department_id;
		this.user_stu_iden = user_stu_iden;
		this.user_wallet = user_wallet;
		this.user_create_time = user_create_time;
	}
	public User() {
		super();
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_name=" + user_name + ", user_pswd=" + user_pswd
				+ ", user_department_id=" + user_department_id + ", user_stu_iden=" + user_stu_iden + ", user_wallet="
				+ user_wallet + ", user_create_time=" + user_create_time + "]";
	}
	
}
