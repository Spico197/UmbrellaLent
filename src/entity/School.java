package entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * ѧУ�࣬ ���Դ���ѧУ���������ݿ��Ӧ
 * @author Spico
 *
 */
public class School {
	private BigDecimal school_id;		// ѧУ���
	private String school_name;	// ѧУ����
	private String school_province; 	// ѧУ����ʡ��
	private String school_city;		// ѧУ���ڳ���
	private Timestamp school_create_time;	// ѧУ����ϵͳ������
	
	
	public BigDecimal getSchool_id() {
		return school_id;
	}
	public void setSchool_id(BigDecimal school_id) {
		this.school_id = school_id;
	}
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	public String getSchool_province() {
		return school_province;
	}
	public void setSchool_province(String school_province) {
		this.school_province = school_province;
	}
	public String getSchool_city() {
		return school_city;
	}
	public void setSchool_city(String school_city) {
		this.school_city = school_city;
	}
	public Timestamp getSchool_create_time() {
		return school_create_time;
	}
	public void setSchool_create_time(Timestamp school_create_time) {
		this.school_create_time = school_create_time;
	}
	public School(BigDecimal school_id, String school_name, String school_province, String school_city,
			Timestamp school_create_time) {
		super();
		this.school_id = school_id;
		this.school_name = school_name;
		this.school_province = school_province;
		this.school_city = school_city;
		this.school_create_time = school_create_time;
	}
	public School(String school_name, String school_province, String school_city, Timestamp school_create_time) {
		super();
		this.school_name = school_name;
		this.school_province = school_province;
		this.school_city = school_city;
		this.school_create_time = school_create_time;
	}
	public School() {
		super();
	}
	@Override
	public String toString() {
		return "School [school_id=" + school_id + ", school_name=" + school_name + ", school_province="
				+ school_province + ", school_city=" + school_city + ", school_create_time=" + school_create_time + "]";
	}
	
}
