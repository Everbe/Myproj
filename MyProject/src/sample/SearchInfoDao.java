package sample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

public class SearchInfoDao {
//	private List<AddUser> list;
	// private List<AddUser> list3;
	List<AddUser> list = new ArrayList<AddUser>();
	// List<AddUser> list = new ArrayList<AddUser>();
	List<ArrayList<AddUser>> list2 = new ArrayList<>();
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpSession session = ServletActionContext.getRequest().getSession();
	private int pageNow = 1;
	private int totalPage;
	private final int pageSize = 5;

	
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

	/// 第一次 把list取出来 然后放进去session
	public List<AddUser> getUserList(int pageNow) {
		try {
			new DBCPUtil();
			Connection con = DBCPUtil.getConn();
			Statement ps = con.createStatement();
			String sql = "select * from users where ";
			boolean bSkip = false;
			if (uName.equals("") && uMobile.equals("") && uAge1.equals("") && uAge2.equals("")) {
				bSkip = true;
			} else {
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
						sql += "age >= " + uAge1;
					}
					if (uAge1.equals("") && !uAge2.equals("")) {
						if (uAge1.equals("")) {
							if (bAddAnd) {
								sql += " and ";
							}
							sql += "age <= " + uAge2;
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
				int iCnt = 0;
				ArrayList<AddUser> list_ = new ArrayList<AddUser>();
				while (rs.next()) {
					// pageSize
					AddUser adduser = new AddUser();
					adduser.setUser(rs.getString("name"));
					adduser.setMobile(rs.getString("mobile"));
					adduser.setAge(rs.getInt("age"));
					list_.add(adduser);
					iCnt++;
					if (iCnt % pageSize == 0) {
						list2.add(list_);
						list_ = new ArrayList<AddUser>();
					}

				}
				if (iCnt % pageSize != 0) {
					list2.add(list_);
				}
				session.setAttribute("AllList", list2);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
//		list = (List<AddUser>) session.getAttribute("list");
		totalPage = list2.size();
		return list2.get(pageNow);
	}

	/// 每一次分页把session里面的list返回去
	public List<AddUser> getEachUserList(int pageNow) {
		List<ArrayList<AddUser>> list_ = new ArrayList<>();
		list_ = (List<ArrayList<AddUser>>) session.getAttribute("AllList");
		
		totalPage = list_.size();


		if (pageNow >= totalPage) {
			pageNow = totalPage-1;
		}
		return list_.get(pageNow);
	}

	/// 这里是按搜索才跑的action

	/// 这里是每次下一页跟上一页跑的action
//	public String PageAction() {
//		list = (List<AddUser>)getEachUserList(pageNow-1);
//		if(pageNow <=0) {
//			pageNow = 1;
//		}
//		else if(pageNow > totalPage) {
//			pageNow = totalPage;
//		}
//		return "success";
//	}

	public String execute() {


		if ((List<ArrayList<AddUser>>) session.getAttribute("AllList") == null) {

			list = (List<AddUser>) getUserList(pageNow-1);
		}

		else {
			if (pageNow <= 0) {
				pageNow = 1;
			}		
			list = (List<AddUser>) getEachUserList(pageNow-1);
			if (pageNow >= totalPage) {
				pageNow = totalPage;
			}
		}

		return "success";
	}

	public List<AddUser> getList() {
		return list;
	}

	public void setList(List<AddUser> list) {
		this.list = list;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}
}
