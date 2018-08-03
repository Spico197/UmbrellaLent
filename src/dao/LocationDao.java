package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import entity.Location;
import tools.JDBCCRUD;
import tools.PreparedStatementSetter;

public class LocationDao extends JDBCCRUD {
	/**
	 * 查询某一学校内的全部location
	 * @param locName <String>: 位置名
	 * @param schoolName <String>: 学校名
	 * @return List<Umbrella>: Umbrella列表
	 */
	public List<Location> selectList(int locSchoolId) {
		String sql = "select * from t_location where loc_school_id=?";
		return query(sql, Location.class, new Object[] {locSchoolId});
	}
	public List<Location> selectListBySchoolName(String locSchoolName) {
		String sql = "select * from t_location l, t_school s where l.loc_school_id=s.school_id and s.school_name=?";
		return query(sql, Location.class, new Object[] {locSchoolName});
	}
	/**
	 * 插入新的位置
	 * @param loc: 待插入的位置对象
	 * @return int: 受影响的行数
	 */
	public int insert(Location loc) {
		String sql = "insert into t_location values (seq_loc_id.nextval,?,?)";
		return update(sql, new PreparedStatementSetter() {
			@Override
			public void setholder(PreparedStatement pstmt) throws SQLException {
				pstmt.setBigDecimal(1, loc.getLoc_school_id());
				pstmt.setString(2, loc.getLoc_name());
			}
		});
	}
	/**
	 * 更新Location
	 * @param s: Location 对象
	 * @return: 返回影响的行数
	 */
	public int update(Location loc) {
		String sql = "update t_location " + 
				"set loc_school_id=?, loc_name=? " + 
				"where loc_id=?;";
		return update(sql, new PreparedStatementSetter() {
			@Override
			public void setholder(PreparedStatement pstmt) throws SQLException {
				pstmt.setBigDecimal(1, loc.getLoc_school_id());
				pstmt.setString(2, loc.getLoc_name());
				pstmt.setBigDecimal(3, loc.getLoc_id());
			}
		});
	}
}
