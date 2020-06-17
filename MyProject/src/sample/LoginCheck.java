package sample;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class LoginCheck extends ActionSupport{
	ArrayList<AddUser> list = new ArrayList<AddUser>();

	public ArrayList<AddUser> getlist() {
		return this.list;
	}

	public void setlist(ArrayList<AddUser> list) {
		this.list = list;
	}

//	private boolean CheckAge(String strAge1, String strAge2) {
//		boolean bRet = false;
//		if strAge1.equals("")
//		if (Integer.parseInt(strAge1) > Integer.parseInt(strAge2)) {
//			bRet = false;
//		} else {
//			bRet = true;
//		}
//		return bRet;
//	}

	public String uName;

	public String getUname() {
		return uName;
	}

	public String uMobile;

	public String getUmoblie() {
		return uMobile;
	}

	public String uAge1;

	public String getUage1() {
		return uAge1;
	}

	public String uAge2;

	public String getUage2() {
		return uAge2;
	}

	public String excute() throws Exception {

		new DBCPUtil();
		Connection con = DBCPUtil.getConn();
		Statement ps = con.createStatement();
		String sql = "select * from users";
		String strReturn = "";

		boolean bSkip = false;

//		CheckAge(uAge1,uAge2);
//		&& (Integer.parseInt(uAge1) > Integer.parseInt(uAge2))
		if (uName.equals("") && uMobile.equals("") && uAge1.equals("") && uAge2.equals("")) {
			bSkip = true;
			sql += ";";
			ResultSet rs = ps.executeQuery(sql);
			ArrayList<AddUser> listCheck = new ArrayList<AddUser>();
			while (rs.next()) {
				//pageSize
				AddUser adduser = new AddUser();
				adduser.setUser(rs.getString("name"));
				adduser.setMobile(rs.getString("mobile"));
				adduser.setAge(rs.getInt("age"));
				listCheck.add(adduser);
			}
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("check", listCheck);
			strReturn = "error";
		} else {
			// todo
			sql += " where ";
			boolean bAddAnd = false;
			if (!uName.equals("")) {
				sql += "name like '%" + uName + "%'";
				bAddAnd = true;
			}
			if (!uMobile.equals("")) {
				if (bAddAnd) {
					sql += " and ";
				}
				sql += "mobile like '%" + uMobile + "%'";
				bAddAnd = true;
			}
			if (!uAge1.equals("") || !uAge2.equals("")) {
				if (!uAge1.equals("") && uAge2.equals("")) {
					if (bAddAnd) {
						sql += " and ";
					}
					sql += "age > " + uAge1;
				}
				if (uAge1.equals("") && !uAge2.equals("")) {
					if (uAge1.equals("")) {
						if (bAddAnd) {
							sql += " and ";
							}
						sql += "age < " + uAge2;
					}
				}
				if (!uAge1.equals("") && !uAge2.equals("")) {
						if (bAddAnd) {
							sql += " and ";
						}
						sql += "age >= " + uAge1 + " and age <= " + uAge2;
				}
			}

			sql += ";";
		}


		if (!bSkip) {
			ResultSet rs = ps.executeQuery(sql);
			while (rs.next()) {
				AddUser adduser = new AddUser();
				adduser.setUser(rs.getString("name"));
				adduser.setMobile(rs.getString("mobile"));
				adduser.setAge(rs.getInt("age"));
				list.add(adduser);
			}
			if (list.size() > 0) {
				HttpServletRequest request = ServletActionContext.getRequest();
				request.setAttribute("check", this.getlist());
//				HttpSession session = request.getSession();
//				session.setAttribute("list1", this.getlist());
//				session.getAttribute("list1");
				strReturn = "success";
			} else {
				addActionMessage("»Î¡¶§∑§ø•«©`•ø§œ¥Ê‘⁄§∑§ﬁ§ª§Û");
				strReturn = "fail";
			}
		}

		// response.sendRedirect("test.jsp");
		return strReturn;
	}
	

	
	
}
