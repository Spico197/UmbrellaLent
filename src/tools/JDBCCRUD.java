package tools;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCCRUD {
	static List list = new ArrayList<>();
	//通用的增删改方法
	public int update(String sql,
			PreparedStatementSetter setter) {
		Connection conn = null ;
		PreparedStatement pstmt = null ;
		int rows = 0 ;
		//1、2、
		conn = ConnectionFactory.getConnection();
		Transaction.beginOne(conn);
		try {
			//3、
			pstmt = conn.prepareStatement(sql);
			/**
			 * 做一个判断，判断你的SQL语句里有没有占位符？，
			 * 如果setter为NULL时代表没有占位符，反之有
			 * 占位符就必须对占位符进行处理
			 */
			if(setter!=null) {
				setter.setholder(pstmt);
			}
			//4、
			rows = pstmt.executeUpdate();
			Transaction.commit(conn);
			Transaction.beginTwo(conn);
		} catch (SQLException e) {
			Transaction.rollback(conn);
			e.printStackTrace();
		}finally {
			DButils.close(conn, pstmt, null);
		}
		return rows;
		
	}
	//通用的查询方法
	public void query(String sql,
			PreparedStatementSetter setter,
			ResultSetHandler handler) {
		Connection conn = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		//1、2
		conn = ConnectionFactory.getConnection();
		//3
		try {
			pstmt = conn.prepareStatement(sql);
			if(setter!=null) {
				setter.setholder(pstmt);
			}
			//4、
			rs = pstmt.executeQuery();
			/**
			 * 做一个判断，判断结果集里面是否为空，如果不为空，
			 * 必须要进行结果集的处理
			 */
			//5、处理结果集
			if(handler!=null) {
				handler.setResult(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DButils.close(conn, pstmt, rs);
		}
	}
	public static List query(String sql, Class c, Object wenhao[]) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			list.clear();
			pstmt = conn.prepareStatement(sql);
			if (wenhao != null) {
				for (int i = 0; i < wenhao.length; i++) {
				pstmt.setObject(i + 1, wenhao[i]);
				}
			}
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData(); // 为了获取更多的结果集数据
			while (rs.next()) {
				Object obj = c.newInstance();// 通过类型实例化对象
				int colCount = rsmd.getColumnCount();// ColumnCount数据库字段数量
				for (int i = 0; i < colCount; i++) {
					String columnName = rsmd.getColumnName(i + 1);// 从1开始
					Field[] fs = c.getDeclaredFields();// 获取类的所有属性名
					AccessibleObject.setAccessible(fs, true);// 获取私有权限
					for (Field f : fs) {
						if (f.getName().equals(columnName.toLowerCase())) {
							f.set(obj, rs.getObject(columnName));
						}
					}
				}
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DButils.close(conn, pstmt, rs);
		return list;
	}
}
