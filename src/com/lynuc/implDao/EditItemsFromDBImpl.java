
package com.lynuc.implDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lynuc.PO.CompanyPO;
import com.lynuc.PO.VersionPO;
import com.lynuc.addHibernateFile.HibernateSessionFactory;
import com.lynuc.bean.User;
import com.lynuc.util.JdbcUtil;
public class EditItemsFromDBImpl {
	private static Session session=HibernateSessionFactory.getSession();
    public EditItemsFromDBImpl(){}
    
    public void updateUserInfo(User user) throws SQLException
    {
    	StringBuilder newUserInfo=new StringBuilder("update sys_user set ");
    	newUserInfo.append("usercode=?,username=?,password=?,permission=?,isvalid=?,complaint_permission=?,");
    	newUserInfo.append("email=?,mobile=?,password_type=?,password_time=?,user_type=?,belong_company_gguid=? ");
    	newUserInfo.append("where id=?");
		
		try(
			Connection conn = JdbcUtil.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(newUserInfo.toString());
		){
			int index=1;
			pstmt.setString(index++, user.getUsercode());
			pstmt.setString(index++, user.getUsername());
			pstmt.setString(index++, user.getPassword());
			pstmt.setString(index++, user.getPermission());
			pstmt.setString(index++, user.getIsvalid());
			pstmt.setString(index++, user.getComplaint_permission());
			pstmt.setString(index++, user.getEmail());
			pstmt.setString(index++, user.getMobile());
			pstmt.setString(index++, user.getPassword_type());
			pstmt.setObject(index++, user.getPassword_time());
			pstmt.setString(index++, user.getUser_type());
			pstmt.setString(index++, user.getBelong_company_gguid());
			pstmt.setObject(index++, user.getId());
			
			pstmt.executeUpdate();
		}
    }
    public void insertUserInfo(User user) throws SQLException
    {
    	//String maxIdUser="select max(id) as maxid from sys_user";
    	StringBuilder newUserInfo=new StringBuilder("insert into sys_user(usercode,username,password,permission,"
    			+ "isvalid,complaint_permission,email,mobile,password_type,password_time,user_type,"
    			+ "belong_company_gguid) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
		try(
			Connection conn = JdbcUtil.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(newUserInfo.toString());
			Statement statement=conn.createStatement();
		){
			int index=1;
			pstmt.setString(index++, user.getUsercode());
			pstmt.setString(index++, user.getUsername());
			pstmt.setString(index++, user.getPassword());
			pstmt.setString(index++, user.getPermission());
			pstmt.setString(index++, user.getIsvalid());
			pstmt.setString(index++, user.getComplaint_permission());
			pstmt.setString(index++, user.getEmail());
			pstmt.setString(index++, user.getMobile());
			pstmt.setString(index++, user.getPassword_type());
			pstmt.setObject(index++, user.getPassword_time());
			pstmt.setString(index++, user.getUser_type());
			pstmt.setString(index++, user.getBelong_company_gguid());
			
			pstmt.executeUpdate();
			
		}
    }
    public void updateVersionInfo(VersionPO cp) throws SQLException
    {
    	//Session session=HibernateSessionFactory.getSession();
    	Transaction transaction=session.beginTransaction();
    	try{
        	session.update(cp);
        	transaction.commit();
        	session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void insertVersionInfo(VersionPO cp)
    {    	
    	Session session=HibernateSessionFactory.getSession();
    	Transaction transaction=session.beginTransaction();
        try{
        	session.save(cp);
        	transaction.commit();
        	session.clear();
        	//session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void updateCompanyInfo(CompanyPO cp) throws SQLException
    {
    	System.out.println(cp.toString());
    	//Session session=HibernateSessionFactory.getSession();
    	Transaction transaction=session.beginTransaction();
    	try{
        	//CompanyPO cpori=(CompanyPO)session.get(CompanyPO.class, cp.getGguid());
        	//cpori = cpori.copyAnother(cp);
        	//session.saveOrUpdate(cpori);
    		session.update(cp);
        	transaction.commit();
        	session.flush();
        	session.clear();
        }catch(Exception e){
        	transaction.rollback();
            e.printStackTrace();
        }
    }
    public void insertCompanyInfo(CompanyPO cp)
    {    	
    	//Session session=HibernateSessionFactory.getSession();
    	Transaction transaction=session.beginTransaction();
        try{
        	session.save(cp);
        	transaction.commit();
        	session.clear();
        	//session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}