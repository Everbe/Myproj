package sample;

import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONObject;

public class SqlConnection extends ActionSupport{
//	private AddUser user = new AddUser();
	private boolean NowIsAddAction = false;
//	private boolean lostItemFlag = false;
	
	public boolean isNowIsAddAction() {
		return NowIsAddAction;
	}

	public void setNowIsAddAction(boolean nowIsAddAction) {
		NowIsAddAction = nowIsAddAction;
	}
	
//	public boolean isLostItemFlag() {
//		return lostItemFlag;
//	}
//
//	public void setLostItemFlag(boolean lostItemFlag) {
//		this.lostItemFlag = lostItemFlag;
//	}

//	public AddUser getModel() {
//		// TODO Auto-generated method stub
//		return user;
//	}
	
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
		//boolean isValid = false;
		String sendString = "����ʹ��";
		String strReturn = "";
		boolean isTrue = false;
		PreparedStatement ps = null;
		ResultSet result = null;
//		String isTrue;
//		JSONObject jsonObject = new JSONObject();


		try {
			new DBCPUtil();
			Connection conn = DBCPUtil.getConn();
//			String mysql = "select mobile from users ;";
			String mysql = "select * from users where mobile=? ;";
			ps = conn.prepareStatement(mysql);
//			ps.setString(1, uName);
			ps.setString(1, uMobile);
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			
//			uName.equals(result.getString("name")) ||
			result = ps.executeQuery();
			while(result.next()){
				if (uMobile.equals(result.getString("mobile"))) {
					sendString = "�ֻ����Ѵ���";
					isTrue = true;
					
//					addActionMessage("�Ԓ���ŤϤ��Ǥ˴��ڤ��ޤ�");
				}
				
			}
			response.getWriter().write(sendString);
//			if(isTrue) {
//			}
//			if (NowIsAddAction){
//				if (sendString != "�ֻ����Ѵ���"){						
//					mysql = "insert into test_db.users(name,mobile,age) values(?,?,?)";
//					ps = conn.prepareStatement(mysql);
//					ps.setString(1, uName);
//					ps.setString(2, uMobile);
//					ps.setString(3, uAge);
////					strReturn = "success";
//					isTrue = false;
//					ps.executeUpdate();
////					lostItemFlag = true;
//					NowIsAddAction = false;
//			}
//			}
			 
		} catch (Exception e) {
			DBCPUtil.closeConn();
		}
//		if(isTrue) {
////		if (strReturn.equals("")) {
//			return null;
//		}else {
			return null;
//		}
	
	
//	public String excute() throws SQLException{
//		String strReturn = "";
//		PreparedStatement ps = null;// ����Ԥ����������һ�㶼�������������Statement
//		ResultSet result = null;// ����һ�����������
//	
//		try {
//			new DBCPUtil();
//			Connection conn = DBCPUtil.getConn();
////			String mysql = "select mobile from users ;";// Ԥ������䣬�������������
//			String mysql = "select * from users where mobile=?";// Ԥ������䣬�������������
//			ps = conn.prepareStatement(mysql);// ʵ����Ԥ�������
//			ps.setString(1, uMobile);// ���ò�����ǰ���1��ʾ�����������������Ǳ�������������
//			HttpServletRequest request = ServletActionContext.getRequest();
//			HttpServletResponse response = ServletActionContext.getResponse();
//			response.setContentType("text/html;charset=utf-8");
//			String sendString = "����ʹ��";
//			result = ps.executeQuery();
//			while(result.next()){
//				if (uMobile.equals(result.getString("mobile"))) {
//					sendString = "�ֻ����Ѵ���";
//					strReturn="faile";
//					break;
//				}
//			}
//			if (NowIsAddAction){
//				if (sendString != "�ֻ����Ѵ���"){						
//					mysql = "insert into test_db.users(name,mobile,age) values(?,?,?)";
//					ps = conn.prepareStatement(mysql);
//					ps.setString(1, uName);
//					ps.setString(2, uMobile);
//					ps.setString(3, uAge);
//					ps.executeUpdate();
//					strReturn = "success";
//					NowIsAddAction = false;
//			}
//		
//	}
//
//}catch (Exception e) {
//	DBCPUtil.closeConn();
//}
//
//	return strReturn;

//}
}


		
}
