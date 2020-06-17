package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

public class FinderService {
	
	ArrayList<AddUser> list = new ArrayList<AddUser>();
	private PreparedStatement ps;
	private Connection con;
	private ResultSet rs;
	
	public String getBestTutorialSite(String ageFrom, String ageTo) throws Exception{
		if(ageFrom.equals("")&&ageTo.equals("")){ 
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test_db?useUnicode = true£¦characterEncoding = utf-8£¦useSSL = false&serverTimezone = GMT",
					"root", "000000");
			ps = con.prepareStatement("select * from users ");
			rs = ps.executeQuery();
			while (rs.next()) {
				AddUser user = new AddUser();
				user.setUser(rs.getString("name"));
				user.setMobile(rs.getString("mobile"));
				user.setAge(rs.getInt("age"));
				list.add(user);
			}
			return "success";
		}
		else{
			return "not java";
		}
	}

	
}
