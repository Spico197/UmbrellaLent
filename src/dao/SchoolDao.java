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
	 * ��ѯ��ɡ
	 * @param ublId: ɡ��id
	 * @return: ���ز�ѯ����Umbrella����
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
//	 * ��ѯ���ɡ
//	 * @param locName <String>: λ����
//	 * @param schoolName <String>: ѧУ��
//	 * @return List<Umbrella>: Umbrella�б�
//	 */
	public List<School> selectList() {
		String sql = "select * from t_school";
		return query(sql, School.class, new Object[] {});
	}
	/**
	 * ����ѧУ
	 * @param s: �������ѧУ����
	 * @return int: ��Ӱ�������
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
	 * ����School
	 * @param s: School ����
	 * @return: ����Ӱ�������
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
