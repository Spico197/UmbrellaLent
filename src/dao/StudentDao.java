package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Student;
import tools.JDBCCRUD;
import tools.PreparedStatementSetter;
import tools.ResultSetHandler;

public class StudentDao extends JDBCCRUD{
	/**
	 * ��ѯ��ѧ��
	 * @param ublId: ɡ��id
	 * @return: ���ز�ѯ����Umbrella����
	 */
	public Student select(String stuIden) {
		String sql = "select * from t_student where stu_iden=?";
		Student s = new Student();
		query(sql, new PreparedStatementSetter() {
			@Override
			public void setholder(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, stuIden);
			}
		}, new ResultSetHandler() {
			@Override
			public void setResult(ResultSet rs) throws SQLException {
				if (rs.next()) {
					s.setStu_name(rs.getString(1));
					s.setStu_iden(rs.getString(2));
					s.setStu_school_id(rs.getBigDecimal(3));
					s.setStu_id(rs.getString(4));
				}
			}
		});
		return s;
	}
	//	/**
	//	 * ��ѯ���ɡ
	//	 * @param locName <String>: λ����
	//	 * @param schoolName <String>: ѧУ��
	//	 * @return List<Umbrella>: Umbrella�б�
	//	 */
	//	public List<Umbrella> selectList(String locName, String schoolName) {
	//		String sql = "select b.* \r\n" + 
	//				"from T_LOCATION a, t_umbrella b, t_shelf c, t_school d\r\n" + 
	//				"where a.LOC_NAME=? and a.LOC_SCHOOL_ID=d.SCHOOL_ID\r\n" + 
	//				"    and d.SCHOOL_NAME=? and c.SHELF_LOC_ID=a.LOC_ID \r\n" + 
	//				"    and b.UBL_SHELF_ID=c.SHELF_ID;";
	//		return query(sql, Umbrella.class, new Object[] {locName, schoolName});
	//	}
	/**
	 * ����ѧ��
	 * @param s: �������ѧ������
	 * @return int: ��Ӱ�������
	 */
	public int insert(Student s) {
		String sql = "insert into t_student values (?,?,?,?)";
		return update(sql, new PreparedStatementSetter() {
			@Override
			public void setholder(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, s.getStu_name());
				pstmt.setString(2, s.getStu_iden());
				pstmt.setBigDecimal(3, s.getStu_school_id());
				pstmt.setString(4, s.getStu_id());
			}
		});
	}
	/**
	 * ����Student
	 * @param s: Student ����
	 * @return: ����Ӱ�������
	 */
	public int update(Student s) {
		String sql = "update t_student " + 
				"set stu_name=?, stu_school_id=?, stu_id=? " + 
				"where stu_iden=?";
		return update(sql, new PreparedStatementSetter() {
			@Override
			public void setholder(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, s.getStu_name());
				pstmt.setBigDecimal(2, s.getStu_school_id());
				pstmt.setString(3, s.getStu_id());
				pstmt.setString(4, s.getStu_iden());
			}
		});
	}
}
