package entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Shelf {
	private BigDecimal shelf_id;
	private BigDecimal shelf_loc_id;
	private Timestamp shelf_create_time;
	public BigDecimal getShelf_id() {
		return shelf_id;
	}
	public void setShelf_id(BigDecimal shelf_id) {
		this.shelf_id = shelf_id;
	}
	public BigDecimal getShelf_loc_id() {
		return shelf_loc_id;
	}
	public void setShelf_loc_id(BigDecimal shelf_loc_id) {
		this.shelf_loc_id = shelf_loc_id;
	}
	public Timestamp getShelf_create_time() {
		return shelf_create_time;
	}
	public void setShelf_create_time(Timestamp shelf_create_time) {
		this.shelf_create_time = shelf_create_time;
	}
	public Shelf(BigDecimal shelf_id, BigDecimal shelf_loc_id, Timestamp shelf_create_time) {
		super();
		this.shelf_id = shelf_id;
		this.shelf_loc_id = shelf_loc_id;
		this.shelf_create_time = shelf_create_time;
	}
	public Shelf(BigDecimal shelf_loc_id, Timestamp shelf_create_time) {
		super();
		this.shelf_loc_id = shelf_loc_id;
		this.shelf_create_time = shelf_create_time;
	}
	public Shelf() {
		super();
	}
	@Override
	public String toString() {
		return "Shelf [shelf_id=" + shelf_id + ", shelf_loc_id=" + shelf_loc_id + ", shelf_create_time="
				+ shelf_create_time + "]";
	}
}
