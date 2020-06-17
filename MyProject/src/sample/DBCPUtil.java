package sample;

import java.sql.*;

public class DBCPUtil {

	public static Connection conn = null;
	String url = "jdbc:mysql://localhost:3306/test_db?useUnicode = true£¦characterEncoding = utf-8£¦useSSL = false&serverTimezone = GMT";
	String root = "root";
	String pass = "000000";
	String driver = "com.mysql.cj.jdbc.Driver";

	public DBCPUtil() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		conn = DriverManager.getConnection(url, root, pass);
	}

	public static Connection getConn() {
		return conn;
	}

	public static void closeConn() throws SQLException {
		
	}

}
