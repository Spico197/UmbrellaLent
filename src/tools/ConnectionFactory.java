package tools;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory {
	private static String DRIVER;
	private static String URL;
	private static String USER;
	private static String PASSWORD;

	static {
		Properties pops = new Properties();
		try {
			/**
			 * 读取跟ConnectionFactory.java这个类所在同一个
			 * 包下面的jdbcinfo.properties文件里面的内容，
			 * 并且解析出来
			 */
			pops.load(ConnectionFactory.class.
					getResourceAsStream("jdbcinfo.properties"));
			DRIVER = pops.getProperty("driver");
			URL = pops.getProperty("url");
			USER = pops.getProperty("user");
			PASSWORD = pops.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		Connection conn = null ;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(
					URL,USER,PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
