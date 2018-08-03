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
	//ͨ�õ���ɾ�ķ���
	public int update(String sql,
			PreparedStatementSetter setter) {
		Connection conn = null ;
		PreparedStatement pstmt = null ;
		int rows = 0 ;
		//1��2��
		conn = ConnectionFactory.getConnection();
		Transaction.beginOne(conn);
		try {
			//3��
			pstmt = conn.prepareStatement(sql);
			/**
			 * ��һ���жϣ��ж����SQL�������û��ռλ������
			 * ���setterΪNULLʱ����û��ռλ������֮��
			 * ռλ���ͱ����ռλ�����д���
			 */
			if(setter!=null) {
				setter.setholder(pstmt);
			}
			//4��
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
	//ͨ�õĲ�ѯ����
	public void query(String sql,
			PreparedStatementSetter setter,
			ResultSetHandler handler) {
		Connection conn = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		//1��2
		conn = ConnectionFactory.getConnection();
		//3
		try {
			pstmt = conn.prepareStatement(sql);
			if(setter!=null) {
				setter.setholder(pstmt);
			}
			//4��
			rs = pstmt.executeQuery();
			/**
			 * ��һ���жϣ��жϽ���������Ƿ�Ϊ�գ������Ϊ�գ�
			 * ����Ҫ���н�����Ĵ���
			 */
			//5����������
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
			ResultSetMetaData rsmd = rs.getMetaData(); // Ϊ�˻�ȡ����Ľ��������
			while (rs.next()) {
				Object obj = c.newInstance();// ͨ������ʵ��������
				int colCount = rsmd.getColumnCount();// ColumnCount���ݿ��ֶ�����
				for (int i = 0; i < colCount; i++) {
					String columnName = rsmd.getColumnName(i + 1);// ��1��ʼ
					Field[] fs = c.getDeclaredFields();// ��ȡ�������������
					AccessibleObject.setAccessible(fs, true);// ��ȡ˽��Ȩ��
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
