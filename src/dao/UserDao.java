package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import entity.User;
import tools.JDBCCRUD;
import tools.PreparedStatementSetter;
import tools.ResultSetHandler;

/**
 * �û���ΪDAO����
 * @author Spico
 *
 */
public class UserDao extends JDBCCRUD {
	/**
	 * ��ѯ���û�����¼��
	 * @param username: �û���
	 * @param password: ����
	 * @return: ���ز�ѯ����User����
	 */
	public User select(String username, String password) {
		String sql = "select * from t_user where user_name=? and user_pswd=?";
		User u = new User();
		query(sql, new PreparedStatementSetter() {
			@Override
			public void setholder(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, username);
				pstmt.setString(2, password);
			}
		}, new ResultSetHandler() {
			@Override
			public void setResult(ResultSet rs) throws SQLException {
				if (rs.next()) {
					u.setUser_id(rs.getBigDecimal(1));
					u.setUser_name(rs.getString(2));
					u.setUser_pswd(rs.getString(3));
					u.setUser_department_id(rs.getBigDecimal(4));
					u.setUser_stu_iden(rs.getString(5));
					u.setUser_wallet(rs.getBigDecimal(6));
					u.setUser_create_time(rs.getTimestamp(7));
				}
			}
		});
		return u;
	}
	/**
	 * ����username���û�
	 * @param username
	 * @param password
	 * @return User
	 */
	public User select(String username) {
		String sql = "select * from t_user where user_name=?";
		User u = new User();
		query(sql, new PreparedStatementSetter() {
			@Override
			public void setholder(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, username);
			}
		}, new ResultSetHandler() {
			@Override
			public void setResult(ResultSet rs) throws SQLException {
				if (rs.next()) {
					u.setUser_id(rs.getBigDecimal(1));
					u.setUser_name(rs.getString(2));
					u.setUser_pswd(rs.getString(3));
					u.setUser_department_id(rs.getBigDecimal(4));
					u.setUser_stu_iden(rs.getString(5));
					u.setUser_wallet(rs.getBigDecimal(6));
					u.setUser_create_time(rs.getTimestamp(7));
				}
			}
		});
		return u;
	}

	/**
	 * ��ѯ���û�
	 * @param username: �û���
	 * @param password: ����
	 * @return List<User>: ����User�б�
	 */
	public List<User> selectList(String username, String password) {
		String sql = "select * from t_user where user_name=? and user_pswd=?";
		return query(sql, User.class, new Object[] {username, password});
	}
	/**
	 * �������û�
	 * @param u: ��������û�����
	 * @return int: ��Ӱ�������
	 */
	public int insert(User u) {
		String sql = "insert into "
				+ "t_user(user_id,user_name,user_pswd,user_department_id,user_stu_iden,user_wallet,user_create_time) "
				+ "values(seq_user_id.nextval,?,?,?,?,?,sysdate)";
		return update(sql, new PreparedStatementSetter() {
			@Override
			public void setholder(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, u.getUser_name());
				pstmt.setString(2, u.getUser_pswd());
				pstmt.setBigDecimal(3, u.getUser_department_id());
				pstmt.setString(4, u.getUser_stu_iden());
				pstmt.setBigDecimal(5, u.getUser_wallet());
			}
		});
	}
	/**
	 * ����User
	 * @param u: User ����
	 * @return: ����Ӱ�������
	 */
	public int update(User u) {
		String sql = "update t_user set user_name=?,user_pswd=?,"
				+ "user_department_id=?,user_stu_iden=?,user_wallet=? where user_id=?";
		return update(sql, new PreparedStatementSetter() {
			@Override
			public void setholder(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, u.getUser_name());
				pstmt.setString(2, u.getUser_pswd());
				pstmt.setBigDecimal(3, u.getUser_department_id());
				pstmt.setString(4, u.getUser_stu_iden());
				pstmt.setBigDecimal(5, u.getUser_wallet());
				pstmt.setBigDecimal(6, u.getUser_id());
			}
		});
	}
}
