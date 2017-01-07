package com.lynuc.implDao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.lynuc.bean.TableProducts;
import com.lynuc.bean.User;
import com.lynuc.dao.UserLoginCheck;
import com.lynuc.util.CryptoTools;
import com.lynuc.util.JdbcUtil;
import com.lynuc.util.JdbcUtilInfoPubConfig;
import com.opensymphony.xwork2.ActionContext;

public class JdbcsqlUserLogImpl implements UserLoginCheck{

	@Override
	public boolean login(String loginAccount, String password) throws SQLException{
		//连接infopub数据库sys_user表
		Connection conn=JdbcUtil.getConnection(); // 获取数据库链接
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			//获取用户信息sql
			String checkoutuser="select su.*,co.company_name,co.company_type,co.gguid as company_gguid from sys_user as su "
					+ "left join company as co on co.gguid=su.belong_company_gguid "
					+" where (usercode=? or mobile=?) and password=?";
			
			//CallableStatement c=conn.prepareCall("{call sp_checkoutUser(?,?)}");
			CallableStatement c=conn.prepareCall("{call sp_UserExist(?,?)}");
			//ECS三次加密后与数据库密码字符串对比
			CryptoTools ct=new CryptoTools();
			String password_ecs=ct.encode(password);
			pstmt=conn.prepareStatement(checkoutuser);
			pstmt.setString(1, loginAccount);
			pstmt.setString(2, loginAccount);
			pstmt.setString(3, password_ecs);
			rs = pstmt.executeQuery();
			User user=new User();
			if(rs.next()){
				Map<String, Object> session=ActionContext.getContext().getSession();
				user.getEntry(rs);
				//用户信息保存到session
				session.put("user", user);
				return true;
			}			 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
				JdbcUtil.free(rs,pstmt,conn); // 一定要释放资源
		}
		return false;
	}

	@Override
	public TableProducts checkout_TableProducts() throws Exception {
		String tableproducts_sql="select * from table_products";
		TableProducts tp=new TableProducts();
		try(
			Connection conn=JdbcUtilInfoPubConfig.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(tableproducts_sql);	
			ResultSet rs=pstmt.executeQuery();
		){
			if(rs.next()){
				Map<String, Object> session=ActionContext.getContext().getSession();
				tp=tp.getEntry(rs);
				session.put("tableproducts", tp);
			}
		}
		
		return tp;
	}
}
