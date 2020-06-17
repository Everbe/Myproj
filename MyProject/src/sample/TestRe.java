package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class TestRe extends ActionSupport {

	public String uName;

	public String getUname() {
		return uName;
	}

	public String uMobile;

	public String getUmobile() {
		return uMobile;
	}

	public String uAge;

	public String getUage() {
		return uAge;
	}

	public String execute() throws SQLException {
		String strReturn = "";
		boolean isTrue = false;
		PreparedStatement ps = null;
		ResultSet result = null;



		try {
			new DBCPUtil();
			Connection conn = DBCPUtil.getConn();

			String mysql = "select * from users where mobile=? ;";
			ps = conn.prepareStatement(mysql);
//			ps.setString(1, uName);
			ps.setString(1, uMobile);
			boolean bSkip =false;
			boolean isValid = false;
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			if(uName.equals("") && uMobile.equals("") && uAge.equals("")) {
				bSkip = true;
				addActionMessage("»Î¡¶§∑§∆§Ø§¿§µ§§");
				strReturn = "faile";
			}
			if(!bSkip) {
				result = ps.executeQuery();
				if(!result.next()) {
					mysql = "insert into test_db.users(name,mobile,age) values(?,?,?)";
					ps = conn.prepareStatement(mysql);
					ps.setString(1, uName);
					ps.setString(2, uMobile);
					ps.setString(3, uAge);
					strReturn="success";
					ps.executeUpdate();
					isValid = true;
					}
				if(isValid) {

					strReturn = "success";
				}else {
					addActionMessage("Îä‘í∑¨∫≈§œ§π§«§À¥Ê‘⁄§∑§ﬁ§π");
					strReturn = "faile";

			}
		}
		}
			catch (Exception e) {
			DBCPUtil.closeConn();
		}

			return strReturn;
	}
}

