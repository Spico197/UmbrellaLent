package entity;

import java.math.BigDecimal;

public class Location {
	private BigDecimal loc_id;
	private BigDecimal loc_school_id;
	private String loc_name;
	public BigDecimal getLoc_id() {
		return loc_id;
	}
	public void setLoc_id(BigDecimal loc_id) {
		this.loc_id = loc_id;
	}
	public BigDecimal getLoc_school_id() {
		return loc_school_id;
	}
	public void setLoc_school_id(BigDecimal loc_school_id) {
		this.loc_school_id = loc_school_id;
	}
	public String getLoc_name() {
		return loc_name;
	}
	public void setLoc_name(String loc_name) {
		this.loc_name = loc_name;
	}
	public Location(BigDecimal loc_id, BigDecimal loc_school_id, String loc_name) {
		super();
		this.loc_id = loc_id;
		this.loc_school_id = loc_school_id;
		this.loc_name = loc_name;
	}
	public Location(BigDecimal loc_school_id, String loc_name) {
		super();
		this.loc_school_id = loc_school_id;
		this.loc_name = loc_name;
	}
	public Location() {
		super();
	}
	@Override
	public String toString() {
		return "Location [loc_id=" + loc_id + ", loc_school_id=" + loc_school_id + ", loc_name=" + loc_name + "]";
	}
}
