package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

public class ListRead {
	ArrayList<AddUser> list = new ArrayList<AddUser>();
	private PreparedStatement ps;
	private Connection con;
	private ResultSet rs;



	public ArrayList<AddUser> getlist() {
		return this.list;
	}

	public void setlist(ArrayList<AddUser> list) {
		this.list = list;
	}

	public String excute() throws Exception {
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

		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list_data", this.getlist());
		return "success";
	}
	


}
