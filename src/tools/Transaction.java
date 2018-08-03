package tools;

import java.sql.Connection;
import java.sql.SQLException;

public class Transaction {
	//���Զ��ύ�����Ϊ�ֶ��ύ����
	public static void beginOne(Connection conn) {
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//���ֶ��ύ�����Ϊ�Զ��ύ����
	public static void beginTwo(Connection conn) {
		try {
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//�ύ����
	public static void commit(Connection conn) {
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//�ع�����
	public static void rollback(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
