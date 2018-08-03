package entity;

import java.math.BigDecimal;
import java.sql.Timestamp;;

public class Umbrella {
	private BigDecimal ubl_id;
	private String ubl_uid;
	private String ubl_type;
	private String ubl_attr;
	private String ubl_color;
	private BigDecimal ubl_shelf_id;
	private BigDecimal ubl_price_per_day;
	private Timestamp ubl_create_time;
	private BigDecimal lent_situation;
	private BigDecimal lent_user_id;
	private Timestamp lent_start_time;
	private Timestamp lent_end_time;
	public BigDecimal getUbl_id() {
		return ubl_id;
	}
	public void setUbl_id(BigDecimal ubl_id) {
		this.ubl_id = ubl_id;
	}
	public String getUbl_uid() {
		return ubl_uid;
	}
	public void setUbl_uid(String ubl_uid) {
		this.ubl_uid = ubl_uid;
	}
	public String getUbl_type() {
		return ubl_type;
	}
	public void setUbl_type(String ubl_type) {
		this.ubl_type = ubl_type;
	}
	public String getUbl_attr() {
		return ubl_attr;
	}
	public void setUbl_attr(String ubl_attr) {
		this.ubl_attr = ubl_attr;
	}
	public String getUbl_color() {
		return ubl_color;
	}
	public void setUbl_color(String ubl_color) {
		this.ubl_color = ubl_color;
	}
	public BigDecimal getUbl_shelf_id() {
		return ubl_shelf_id;
	}
	public void setUbl_shelf_id(BigDecimal ubl_shelf_id) {
		this.ubl_shelf_id = ubl_shelf_id;
	}
	public BigDecimal getUbl_price_per_day() {
		return ubl_price_per_day;
	}
	public void setUbl_price_per_day(BigDecimal ubl_price_per_day) {
		this.ubl_price_per_day = ubl_price_per_day;
	}
	public Timestamp getUbl_create_time() {
		return ubl_create_time;
	}
	public void setUbl_create_time(Timestamp ubl_create_time) {
		this.ubl_create_time = ubl_create_time;
	}
	public BigDecimal getLent_situation() {
		return lent_situation;
	}
	public void setLent_situation(BigDecimal lent_situation) {
		this.lent_situation = lent_situation;
	}
	public BigDecimal getLent_user_id() {
		return lent_user_id;
	}
	public void setLent_user_id(BigDecimal bigDecimal) {
		this.lent_user_id = bigDecimal;
	}
	public Timestamp getLent_start_time() {
		return lent_start_time;
	}
	public void setLent_start_time(Timestamp lent_start_time) {
		this.lent_start_time = lent_start_time;
	}
	public Timestamp getLent_end_time() {
		return lent_end_time;
	}
	public void setLent_end_time(Timestamp lent_end_time) {
		this.lent_end_time = lent_end_time;
	}
	public Umbrella(BigDecimal ubl_id, String ubl_uid, String ubl_type, String ubl_attr, String ubl_color, BigDecimal ubl_shelf_id,
			BigDecimal ubl_price_per_day, Timestamp ubl_create_time, BigDecimal lent_situation, BigDecimal lent_user_id, Timestamp lent_start_time,
			Timestamp lent_end_time) {
		super();
		this.ubl_id = ubl_id;
		this.ubl_uid = ubl_uid;
		this.ubl_type = ubl_type;
		this.ubl_attr = ubl_attr;
		this.ubl_color = ubl_color;
		this.ubl_shelf_id = ubl_shelf_id;
		this.ubl_price_per_day = ubl_price_per_day;
		this.ubl_create_time = ubl_create_time;
		this.lent_situation = lent_situation;
		this.lent_user_id = lent_user_id;
		this.lent_start_time = lent_start_time;
		this.lent_end_time = lent_end_time;
	}
	public Umbrella(String ubl_uid, String ubl_type, String ubl_attr, String ubl_color, BigDecimal ubl_shelf_id,
			BigDecimal ubl_price_per_day, Timestamp ubl_create_time, BigDecimal lent_situation, BigDecimal lent_user_id, Timestamp lent_start_time,
			Timestamp lent_end_time) {
		super();
		this.ubl_uid = ubl_uid;
		this.ubl_type = ubl_type;
		this.ubl_attr = ubl_attr;
		this.ubl_color = ubl_color;
		this.ubl_shelf_id = ubl_shelf_id;
		this.ubl_price_per_day = ubl_price_per_day;
		this.ubl_create_time = ubl_create_time;
		this.lent_situation = lent_situation;
		this.lent_user_id = lent_user_id;
		this.lent_start_time = lent_start_time;
		this.lent_end_time = lent_end_time;
	}
	public Umbrella() {
		super();
	}
	@Override
	public String toString() {
		return "Umbrella [ubl_id=" + ubl_id + ", ubl_uid=" + ubl_uid + ", ubl_type=" + ubl_type + ", ubl_attr="
				+ ubl_attr + ", ubl_color=" + ubl_color + ", ubl_shelf_id=" + ubl_shelf_id + ", ubl_price_per_day="
				+ ubl_price_per_day + ", ubl_create_time=" + ubl_create_time + ", lent_situation=" + lent_situation
				+ ", lent_user_id=" + lent_user_id + ", lent_start_time=" + lent_start_time + ", lent_end_time="
				+ lent_end_time + "]";
	}
	
}
