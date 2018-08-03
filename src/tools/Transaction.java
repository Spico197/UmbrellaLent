package tools;

import java.sql.Connection;
import java.sql.SQLException;

public class Transaction {
	//将自动提交事务改为手动提交事务
	public static void beginOne(Connection conn) {
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//将手动提交事务改为自动提交事务
	public static void beginTwo(Connection conn) {
		try {
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//提交事务
	public static void commit(Connection conn) {
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//回滚事务
	public static void rollback(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
