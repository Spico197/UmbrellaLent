package dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import entity.Umbrella;
import tools.JDBCCRUD;
import tools.PreparedStatementSetter;
import tools.ResultSetHandler;

public class UmbrellaDao extends JDBCCRUD {
	/**
	 * 查询单伞
	 * @param locName
	 * @param schoolName
	 * @return
	 */
	public Umbrella select(String locName, String schoolName) {
		String sql = "select b.* " + 
				"from T_LOCATION a, t_umbrella b, t_shelf c, t_school d " + 
				"where a.LOC_NAME=? and a.LOC_SCHOOL_ID=d.SCHOOL_ID " + 
				"and d.SCHOOL_NAME=? and c.SHELF_LOC_ID=a.LOC_ID " + 
				"and b.UBL_SHELF_ID=c.SHELF_ID";
		Umbrella u = null;
		query(sql, new PreparedStatementSetter() {
			@Override
			public void setholder(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, locName);
				pstmt.setString(2, schoolName);
			}
		}, new ResultSetHandler() {
			@Override
			public void setResult(ResultSet rs) throws SQLException {
				if (rs.next()) {
					u.setUbl_id(rs.getBigDecimal(1));
					u.setUbl_uid(rs.getString(2));
					u.setUbl_type(rs.getString(3));
					u.setUbl_attr(rs.getString(4));
					u.setUbl_color(rs.getString(5));
					u.setUbl_shelf_id(rs.getBigDecimal(6));
					u.setUbl_price_per_day(rs.getBigDecimal(7));
					u.setUbl_create_time(rs.getTimestamp(8));
					u.setLent_situation(rs.getBigDecimal(9));
					u.setLent_user_id(rs.getBigDecimal(10));
					u.setLent_start_time(rs.getTimestamp(11));
					u.setLent_end_time(rs.getTimestamp(12));
				}
			}
		});
		return u;
	}
	/**
	 * 查询单伞
	 * @param ublId: 伞的id
	 * @return: 返回查询到的Umbrella对象
	 */
	public Umbrella select(BigDecimal bigDecimal) {
		String sql = "select * from t_umbrella where ubl_id=?";
		Umbrella u = new Umbrella();
		query(sql, new PreparedStatementSetter() {
			@Override
			public void setholder(PreparedStatement pstmt) throws SQLException {
				pstmt.setBigDecimal(1, bigDecimal);
			}
		}, new ResultSetHandler() {
			@Override
			public void setResult(ResultSet rs) throws SQLException {
				if (rs.next()) {
					u.setUbl_id(rs.getBigDecimal(1));
					u.setUbl_uid(rs.getString(2));
					u.setUbl_type(rs.getString(3));
					u.setUbl_attr(rs.getString(4));
					u.setUbl_color(rs.getString(5));
					u.setUbl_shelf_id(rs.getBigDecimal(6));
					u.setUbl_price_per_day(rs.getBigDecimal(7));
					u.setUbl_create_time(rs.getTimestamp(8));
					u.setLent_situation(rs.getBigDecimal(9));
					u.setLent_user_id(rs.getBigDecimal(10));
					u.setLent_start_time(rs.getTimestamp(11));
					u.setLent_end_time(rs.getTimestamp(12));
				}
			}
		});
		return u;
	}
	/**
	 * 查询多把伞
	 * @param locName <String>: 位置名
	 * @param schoolName <String>: 学校名
	 * @return List<Umbrella>: Umbrella列表
	 */
	public List<Umbrella> selectList(String locName, String schoolName) {
		String sql = "select b.* " + 
				"from T_LOCATION a, t_umbrella b, t_shelf c, t_school d " + 
				"where a.LOC_NAME=? and a.LOC_SCHOOL_ID=d.SCHOOL_ID " + 
				"and d.SCHOOL_NAME=? and c.SHELF_LOC_ID=a.LOC_ID " + 
				"and b.UBL_SHELF_ID=c.SHELF_ID";
		return query(sql, Umbrella.class, new Object[] {locName, schoolName});
	}
	/**
	 * 根据username查找当前username所租的伞
	 * @param username: 用户名
	 * @return List<Umbrella>
	 */
	public List<Umbrella> orderByUsername(String username) {
		String sql = "select um.* from t_umbrella um, t_user u where um.lent_user_id=u.user_id and u.user_name=?";
		return query(sql, Umbrella.class, new Object[] {username});
	}
	/**
	 * 插入新伞
	 * @param u: 待插入的伞对象
	 * @return int: 受影响的行数
	 */
	public int insert(Umbrella u) {
		String sql = "insert into t_umbrella values"
				+ "(seq_ubl_id.nextval,?,?,?,?,?,?,?,1,null,null,null)";
		return update(sql, new PreparedStatementSetter() {
			@Override
			public void setholder(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, u.getUbl_uid());
				pstmt.setString(2, u.getUbl_type());
				pstmt.setString(3, u.getUbl_attr());
				pstmt.setString(4, u.getUbl_color());
				pstmt.setBigDecimal(5, u.getUbl_shelf_id());
				pstmt.setBigDecimal(6, u.getUbl_price_per_day());
				pstmt.setTimestamp(7, u.getUbl_create_time());
			}
		});
	}
	/**
	 * 更新User
	 * @param u: User 对象
	 * @return: 返回影响的行数
	 */
	public int update(Umbrella u) {
		String sql = "update t_umbrella " + 
				"set ubl_uid=?,ubl_type=?,ubl_attr=?,ubl_color=?,ubl_shelf_id=?, " + 
				"ubl_price_per_day=?,ubl_create_time=?,lent_situation=?,lent_user_id=?, " + 
				"lent_start_time=?,lent_end_time=? " + 
				"where ubl_id=?";
		return update(sql, new PreparedStatementSetter() {
			@Override
			public void setholder(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, u.getUbl_uid());
				pstmt.setString(2, u.getUbl_type());
				pstmt.setString(3, u.getUbl_attr());
				pstmt.setString(4, u.getUbl_color());
				pstmt.setBigDecimal(5, u.getUbl_shelf_id());
				pstmt.setBigDecimal(6, u.getUbl_price_per_day());
				pstmt.setTimestamp(7, u.getUbl_create_time());
				pstmt.setBigDecimal(8, u.getLent_situation());
				pstmt.setBigDecimal(9, u.getLent_user_id());
				pstmt.setTimestamp(10, u.getLent_start_time());
				pstmt.setTimestamp(11, u.getLent_end_time());
				pstmt.setBigDecimal(12, u.getUbl_id());
			}
		});
	}
	public int updateLent(Umbrella um) {
		String sql = "update t_umbrella " + 
				"set lent_situation=?,lent_user_id=?, " + 
				"lent_start_time=?,lent_end_time=? " + 
				"where ubl_id=?";
		return update(sql, new PreparedStatementSetter() {
			@Override
			public void setholder(PreparedStatement pstmt) throws SQLException {
				pstmt.setBigDecimal(1, um.getLent_situation());
				pstmt.setBigDecimal(2, um.getLent_user_id());
				pstmt.setTimestamp(3, um.getLent_start_time());
				pstmt.setTimestamp(4, um.getLent_end_time());
				pstmt.setBigDecimal(5, um.getUbl_id());
			}
		});
	}
	public int delete(Umbrella um) {
		String sql = "delete from t_umbrella " + 
				"where ubl_id=?";
		return update(sql, new PreparedStatementSetter() {
			@Override
			public void setholder(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, um.getUbl_uid());
			}
		});
	}
}
