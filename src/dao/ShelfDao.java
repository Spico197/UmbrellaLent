package dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import entity.Location;
import entity.Shelf;
import tools.JDBCCRUD;
import tools.PreparedStatementSetter;

public class ShelfDao extends JDBCCRUD {
	/**
	 * 查询某一学校内的全部Shelf
	 * @param schoolName <String>: 学校名
	 * @return List<Shelf>: Shelf列表
	 */
	public List<Shelf> selectList(String schoolName) {
		String sql = "select s.* from t_shelf s, t_location l, t_school t where s.SHELF_LOC_ID=l.loc_id and l.loc_school_id=t.school_id and school_name=?";
		return query(sql, Shelf.class, new Object[] {schoolName});
	}
	/**
	 * 插入新的伞架
	 * @param s: 待插入的伞架对象
	 * @return int: 受影响的行数
	 */
	public int insert(BigDecimal loc_id) {
		String sql = "insert into t_shelf values (seq_shelf_id.nextval,?,sysdate)";
		return update(sql, new PreparedStatementSetter() {
			@Override
			public void setholder(PreparedStatement pstmt) throws SQLException {
				pstmt.setBigDecimal(1, loc_id);
			}
		});
	}
	/**
	 * 更新Shelf
	 * @param s: Shelf 对象
	 * @return: 返回影响的行数
	 */
	public int update(Shelf s) {
		String sql = "update t_shelf " + 
				"set shelf_loc_id=? " + 
				"where shelf_id=?";
		return update(sql, new PreparedStatementSetter() {
			@Override
			public void setholder(PreparedStatement pstmt) throws SQLException {
				pstmt.setBigDecimal(1, s.getShelf_loc_id());
				pstmt.setBigDecimal(2, s.getShelf_id());
			}
		});
	}
}
