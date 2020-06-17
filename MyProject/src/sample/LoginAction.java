package sample;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends ActionSupport implements ModelDriven<AddUser>,ServletRequestAware{
	private HttpServletRequest request;
	private AddUser user = new AddUser();
	
	public AddUser getModel() {
		return user;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String execute() throws Exception{
//		HttpServletRequest request = ServletActionContext.getRequest();
//		HttpServletResponse response = ServletActionContext.getResponse();
//		response.setContentType("text/html;charset=utf-8");
//		String sendString = "";
//		boolean flag = false;
		String strReturn = "";
		LoginSql conn = new LoginSql();
		if(conn.Select(user)) {
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);
			strReturn = "success";
		}else {
			addActionMessage("交奈扒奈反湔婓仄引六氏" +"\n" + "扔奶件失永皿仄化仁分今中");
			strReturn = "false";
		}
		return strReturn;
	}

}
