package sample;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;

public class restful {
//	private String name;
//	private String mobile;
	List<AddUser> list = new ArrayList<AddUser>();
	private Map<String,Object> jsonResult;
    
	public Map<String, Object> getJsonResult() {
	        return jsonResult;
	}
	
	public void setJsonResult(Map<String, Object> jsonResult) {
		this.jsonResult = jsonResult;
	}
	
	public String  execute() throws Exception{
		jsonResult = new HashMap<String, Object>();
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String ageFrom = request.getParameter("ageFrom");
		String ageTo = request.getParameter("ageTo");
		int iCnt = 1;
		String strRt="";
 		new DBCPUtil();
		Connection con = DBCPUtil.getConn();
		Statement ps = con.createStatement();
		
		String sql = "select * from users";
//		 && ageFrom.equals("") && ageTo.equals("")
		if(id == null && name == null && mobile == null && ageFrom == null && ageTo == null){ 
			ResultSet rs = ps.executeQuery(sql);
			while (rs.next()) {
			AddUser adduser = new AddUser();
			adduser.setId(rs.getInt("id"));
			adduser.setUser(rs.getString("name"));
			adduser.setMobile(rs.getString("mobile"));
			adduser.setAge(rs.getInt("age"));
			list.add(adduser);
			jsonResult.put(String.valueOf(iCnt), adduser);				
			iCnt ++ ;
		}
			strRt="success";
		}else {
			sql += " where ";
			boolean bAddAnd = false;
			if(id != null && !id.equals("")) {
				sql += "id=" + id ;
				bAddAnd = true;
			}
			if(name !=null && !name.equals("")) {
				if (bAddAnd) {
					sql += " and ";
				}
				sql += "name like '%" + name + "%'";
				bAddAnd = true;
				}
			if ( mobile != null && !mobile.equals("")) {
				if (bAddAnd) {
					sql += " and ";
				}
				sql += "mobile like '%" + mobile + "%'";
				bAddAnd = true;
			}
			if (ageFrom != null && !ageFrom.equals("")) {
					if (bAddAnd) {
						sql += " and ";
					}
					sql += "age > " + ageFrom;
					bAddAnd = true;
				}
			if (ageTo != null && !ageTo.equals("")) {
					if (bAddAnd) {
						sql += " and ";
						}
					sql += " age < " + ageTo;	
				}
			sql += ";";
			ResultSet rs = ps.executeQuery(sql);
			
			
			while (rs.next()) {
				AddUser user = new AddUser();
				user.setId(rs.getInt("id"));
				user.setUser(rs.getString("name"));
				user.setMobile(rs.getString("mobile"));
				user.setAge(rs.getInt("age"));
				list.add(user);
				jsonResult.put(String.valueOf(iCnt), user);				
				iCnt ++ ;
				}
			System.out.println(jsonResult);
//			JSONObject json = new JSONObject();
//			json.put("params",jsonResult);
//			String tojsonstring= json.toString();//将json格式的数据转换为字符格式
//			tojsonstring=URLEncoder.encode(tojsonstring,"utf-8");//将数据进行编码
//
//			String url ="http://test.dian111.com/ems/callout?content="+tojsonstring;

			}

			strRt="one";
			return strRt;
		}
	
	public List<AddUser> getList() {
		return list;
	}

	public void setList(List<AddUser> list) {
		this.list = list;
	}

}
