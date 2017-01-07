package com.lynuc.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.lynuc.bean.BaseAction;
import com.lynuc.bean.User;
import com.lynuc.util.CryptoTools;
import com.lynuc.util.JdbcUtil;

public class ResetPasswordAction extends BaseAction {

	private static final long serialVersionUID = -9093315483050615001L;
	
	public void checkOldPassword() throws Exception{
		response.setCharacterEncoding("UTF-8");
		Connection conn=JdbcUtil.getConnection();
		String sql="select * from sys_user where usercode=? and password=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		String password=request.getParameter("oldpassword");
		CryptoTools ct=new CryptoTools();
		String password_des=ct.encode(password);
		User user=(User) sessionMap.get("user");
		pstmt.setString(1, user.getUsercode());
		pstmt.setString(2, password_des);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			response.getWriter().write("1"); //"{\"code\":\"1\"}"
		}else{
			response.getWriter().write("0"); 
		}
	}
	public void resetPasswordIntoDB() throws Exception
	{
		response.setCharacterEncoding("UTF-8");
		Connection conn=JdbcUtil.getConnection();
		User user=(User) sessionMap.get("user");
		String sql="update sys_user set password=? where usercode=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		String password=request.getParameter("newpass");
		CryptoTools ct=new CryptoTools();
		String password_des=ct.encode(password);
		pstmt.setString(1, password_des);
		pstmt.setString(2, user.getUsercode());
		pstmt.executeUpdate();
	}
}
