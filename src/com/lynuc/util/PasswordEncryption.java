package com.lynuc.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lynuc.bean.Mcc;
import com.lynuc.bean.MccFromDB;
import com.lynuc.bean.MccPassword;

public class PasswordEncryption {
	
	private static String seed;
	static{
		try {
			Connection conn=JdbcUtil.getConnection(); // 获取数据库链接
			String sqlMcc="select * from table_sys_config where configcode =?";
			PreparedStatement pstmt=conn.prepareStatement(sqlMcc);
			pstmt.setString(1, "seed_password");
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				seed=rs.getString("configvalue");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private native static String make_encrypt(String seed,String key,int nlen,String message);
	static{
		System.loadLibrary("passProductionDll");	
	}
	public String make_encrypt_interface(String key,int nlen,String message){
		
		return make_encrypt(this.seed,key,nlen,message);
	}
	public String generatePassword(MccFromDB m,MccPassword mp)
	{
		String controller_ver_key=m.getController_ver();
		if(null==controller_ver_key) return null;
		ParseConfiguration eg=new ParseConfiguration("/controlnoList.properties");
		String controller_ver_value=eg.readValueByKey(controller_ver_key);
		if(controller_ver_value == null) return null;
		String key=m.getCard_content()+m.getIpc_sn()+m.getController_no()+controller_ver_value.trim();
		int nlen=32;
		String moduleset_str=String.format("%08d", m.getModule_set());
		String message="";
		if(mp.getIs_forever().equals("Y")){
			message="19770223203707131000"+moduleset_str;
		}else{
			String enddate_str1=mp.getEnddate().split(" ")[0].replace("-", "");
			message="20080101"+enddate_str1+"1000"+moduleset_str;
		}
		String licensestr=make_encrypt_interface(key,nlen,message);
		return licensestr;
	}
	public String generatePassword(int apply_no) throws SQLException
	{
		String licenseStr="";
		String sqlMccPassword="select is_forever,enddate,controller_no from mcc_password where apply_no="+apply_no;
		try(
			Connection conn = JdbcUtil.getConnection();	
			java.sql.Statement stateMccPassword=conn.createStatement();
			java.sql.Statement stateCompanyid=conn.createStatement();
			ResultSet rsMccPassword=stateMccPassword.executeQuery(sqlMccPassword);
		)
		{
			MccPassword mp=new MccPassword();
			if(rsMccPassword.next()){
				mp.setIs_forever(rsMccPassword.getString("is_forever"));
				mp.setEnddate(rsMccPassword.getString("enddate").toString());
				mp.setController_no(rsMccPassword.getString("controller_no"));
				String sqlMcc="select * from mcc where controller_no='"+mp.getController_no()+"'";
				try(
					ResultSet rsMcc=stateMccPassword.executeQuery(sqlMcc);
				)
				{
					Mcc m=new Mcc();
					if(rsMcc.next()){
						m.setController_ver(rsMcc.getString("controller_ver"));
						m.setCard_content(rsMcc.getString("card_content"));
						m.setIpc_sn(rsMcc.getString("ipc_sn"));
						m.setController_no(rsMcc.getString("controller_no"));
						int module_set=rsMcc.getInt("module_set");
						m.setModule_set(module_set);
						licenseStr=this.generatePassword(m,mp); 
					}	
				}
			}			
		}
		return licenseStr;
	}
	public void updateModuleSetFromMcc(String controller_no, int new_module_set) throws SQLException
	{
		String sqlUpdateModuleSet="update mcc set module_set="+new_module_set+" where controller_no='"+controller_no+"'";
		try(
			Connection conn = JdbcUtil.getConnection();	
			java.sql.Statement stateUpdateModuleSet=conn.createStatement();
		)
		{
			stateUpdateModuleSet.executeUpdate(sqlUpdateModuleSet);
		}
	}
}