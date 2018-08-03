package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import entity.Location;
import entity.School;
import entity.Umbrella;
import tools.JDBCCRUD;
import tools.PreparedStatementSetter;
import tools.ResultSetHandler;

public class SchoolDao extends JDBCCRUD {
	/**
	 * 查询单伞
	 * @param ublId: 伞的id
	 * @return: 返回查询到的Umbrella对象
	 */
	public School select(String schoolName) {
		String sql = "select * from t_school where school_name=?";
		School s = null;
		query(sql, new PreparedStatementSetter() {
			@Override
			public void setholder(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, schoolName);
			}
		}, new ResultSetHandler() {
			@Override
			public void setResult(ResultSet rs) throws SQLException {
				if (rs.next()) {
					s.setSchool_id(rs.getBigDecimal(1));
					s.setSchool_name(rs.getString(2));
					s.setSchool_province(rs.getString(3));
					s.setSchool_city(rs.getString(4));
					s.setSchool_create_time(rs.getTimestamp(5));
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
	public List<School> selectList() {
		String sql = "select * from t_school";
		return query(sql, School.class, new Object[] {});
	}
	/**
	 * 插入学校
	 * @param s: 待插入的学校对象
	 * @return int: 受影响的行数
	 */
	public int insert(School s) {
		String sql = "insert into t_school values"
				+ "(seq_school_id.nextval,?,?,?,?)";
		return update(sql, new PreparedStatementSetter() {
			@Override
			public void setholder(PreparedStatement pstmt) throws SQLException {
				pstmt.setBigDecimal(1, s.getSchool_id());
				pstmt.setString(2, s.getSchool_name());
				pstmt.setString(3, s.getSchool_province());
				pstmt.setString(4, s.getSchool_city());
				pstmt.setTimestamp(5, s.getSchool_create_time());
			}
		});
	}
	/**
	 * 更新School
	 * @param s: School 对象
	 * @return: 返回影响的行数
	 */
	public int update(School s) {
		String sql = "update t_school " + 
				"set school_name=?, school_province=?, school_city=?,school_create_time=? " + 
				"where school_id=?";
		return update(sql, new PreparedStatementSetter() {
			@Override
			public void setholder(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, s.getSchool_name());
				pstmt.setString(2, s.getSchool_province());
				pstmt.setString(3, s.getSchool_city());
				pstmt.setTimestamp(4, s.getSchool_create_time());
				pstmt.setBigDecimal(5, s.getSchool_id());
			}
		});
	}
}
