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
	 * 查询单学生
	 * @param ublId: 伞的id
	 * @return: 返回查询到的Umbrella对象
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
	//	 * 查询多把伞
	//	 * @param locName <String>: 位置名
	//	 * @param schoolName <String>: 学校名
	//	 * @return List<Umbrella>: Umbrella列表
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
	 * 插入学生
	 * @param s: 待插入的学生对象
	 * @return int: 受影响的行数
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
	 * 更新Student
	 * @param s: Student 对象
	 * @return: 返回影响的行数
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
