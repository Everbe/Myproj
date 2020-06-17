package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginSql {

	public boolean Select(AddUser user) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean loginCheck = false;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test_db?useUnicode = true£¦characterEncoding = utf-8£¦useSSL = false&serverTimezone = GMT",
					"root", "000000");
			String mysql = "select name,mobile from users;";
			ps = conn.prepareStatement(mysql);
			rs = ps.executeQuery();
			while (rs.next()) {
				if ((user.getUser().equals(rs.getString("name")) && user.getMobile().equals(rs.getString("mobile")))) {
					loginCheck = true;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return loginCheck;

	}

}