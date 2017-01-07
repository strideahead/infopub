package com.lynuc.implDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lynuc.bean.Mcc;
import com.lynuc.bean.ProductCard;
import com.lynuc.util.JdbcUtil;
import com.lynuc.util.JdbcUtilJstore;

public class AddControlCardItemImpl {
	
	public ProductCard getProduCardEntryByCardNo(String card_no) throws Exception{
		String sqlcard_no_product_card="select * from product_card where card_no='"+card_no+"' ";
		try(
			Connection conn=JdbcUtilJstore.getConnection(); // 获取数据库链接	
			java.sql.Statement state=conn.createStatement();	
			ResultSet rs=state.executeQuery(sqlcard_no_product_card);	
		)
		{
			ProductCard productcard=new ProductCard();
			if(rs.next()){
				productcard.getEntry(rs);
				return productcard;
			}
		}
		return null;
	}
	public ProductCard retrieveMccByCtrlno(String controller_no) throws Exception{
		String sqlController_no="select * from mcc where "+controller_no+" in(select"
				+ " distinct controller_no from product_card";
		try(
			Connection conn=JdbcUtil.getConnection(); // 获取数据库链接
			java.sql.Statement stmt=conn.prepareStatement(sqlController_no);
			ResultSet rs=stmt.executeQuery(sqlController_no);	
		)
		{
			ProductCard productcard=new ProductCard();
			if(rs.next()){
				productcard.getEntry(rs);
				return productcard;
			}	
		}
		return null;
	}
	//函数用来从mcc表中获取controller控制器的信息
	public Mcc getMccEntryByControllerNo(String controller_no) throws SQLException
	{
		if(controller_no==null || controller_no.trim().isEmpty()){
			return null;
		}
		try(
			Connection conn = JdbcUtil.getConnection();	
			PreparedStatement pstmt=conn.prepareStatement("{call dbo.mccsel_byctrlno(?)}");	
		)
		{
			pstmt.setString(1, controller_no);
			ResultSet rs=pstmt.executeQuery();
			try {
				if(rs.next()){
					Mcc mcc=new Mcc();
					mcc.getEntry(rs);
					return mcc;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				if(rs!=null){
					rs.close();
				}
			}
			
		}
		return null;
	}
	
}
