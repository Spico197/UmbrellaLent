package entity;

import java.math.BigDecimal;

/**
 * 学生类，用以和数据库中的t_student表对应
 * @author Spico
 *
 */
public class Student {
	private String stu_name;
	private String stu_iden; // 用户身份证号
	private BigDecimal stu_school_id; // 学校编号
	private String stu_id; // 学号
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	public String getStu_iden() {
		return stu_iden;
	}
	public void setStu_iden(String stu_iden) {
		this.stu_iden = stu_iden;
	}
	public BigDecimal getStu_school_id() {
		return stu_school_id;
	}
	public void setStu_school_id(BigDecimal stu_school_id) {
		this.stu_school_id = stu_school_id;
	}
	public String getStu_id() {
		return stu_id;
	}
	public void setStu_id(String stu_id) {
		this.stu_id = stu_id;
	}
	public Student(String stu_name, String stu_iden, BigDecimal stu_school_id, String stu_id) {
		super();
		this.stu_name = stu_name;
		this.stu_iden = stu_iden;
		this.stu_school_id = stu_school_id;
		this.stu_id = stu_id;
	}
	public Student(String stu_iden, BigDecimal stu_school_id, String stu_id) {
		super();
		this.stu_iden = stu_iden;
		this.stu_school_id = stu_school_id;
		this.stu_id = stu_id;
	}
	public Student() {
		super();
	}
	@Override
	public String toString() {
		return "Student [stu_name=" + stu_name + ", stu_iden=" + stu_iden + ", stu_school_id=" + stu_school_id
				+ ", stu_id=" + stu_id + "]";
	}

	
}
