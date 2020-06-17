package sample;


import java.sql.*;


public class SqlLogin {
	public boolean Select(AddUser user) {
   	 PreparedStatement ps = null;
   	 ResultSet rs = null;
 	try {
		new DBCPUtil();
		Connection conn = DBCPUtil.getConn();
		String sql = "select * from users";
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()) {
			if(user.getUser().equals(rs.getString("name"))&&user.getMobile().equals(rs.getString("mobile"))){
				return true;
			}
		}
	}catch(Exception e){
		e.printStackTrace();
	}finally {
		try {
			rs.close();
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
 	return false;	
	}

}
