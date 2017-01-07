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
		//����infopub���ݿ�sys_user��
		Connection conn=JdbcUtil.getConnection(); // ��ȡ���ݿ�����
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			//��ȡ�û���Ϣsql
			String checkoutuser="select su.*,co.company_name,co.company_type,co.gguid as company_gguid from sys_user as su "
					+ "left join company as co on co.gguid=su.belong_company_gguid "
					+" where (usercode=? or mobile=?) and password=?";
			
			//CallableStatement c=conn.prepareCall("{call sp_checkoutUser(?,?)}");
			CallableStatement c=conn.prepareCall("{call sp_UserExist(?,?)}");
			//ECS���μ��ܺ������ݿ������ַ����Ա�
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
				//�û���Ϣ���浽session
				session.put("user", user);
				return true;
			}			 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
				JdbcUtil.free(rs,pstmt,conn); // һ��Ҫ�ͷ���Դ
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
