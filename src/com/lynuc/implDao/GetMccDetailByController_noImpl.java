package com.lynuc.implDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import com.lynuc.bean.Mcc;
import com.lynuc.dao.MccDetailByControllerDao;
import com.lynuc.def.Constant;
import com.lynuc.util.JdbcUtil;

public class GetMccDetailByController_noImpl implements MccDetailByControllerDao{

	@Override
	public void getMccDetailByController_no(String controller_no,Map<String,Object> mccMap) throws Exception
	{
		StringBuilder sqlMcc2=new StringBuilder("select the_password,enddate,is_forever from mcc_password where apply_no in");
		sqlMcc2.append(" (select MAX(apply_no) from mcc_password where state='Y' and controller_no='");
		sqlMcc2.append(controller_no+"')");
		try {
			Connection conn=JdbcUtil.getConnection(); // 获取数据库链接
			Statement state=conn.createStatement();
			PreparedStatement pstmt=conn.prepareStatement("{call dbo.mccsel_byctrlno(?)}");
			pstmt.setString(1, controller_no);
			ResultSet rs = pstmt.executeQuery();
			Mcc mcc=new Mcc();
			if(rs.next()){
				mcc.getEntry(rs);
				mcc.convertMccToMap(mccMap);
				mccMap.put("input_user_name", rs.getString("input_user_name"));
			}
			rs = state.executeQuery(sqlMcc2.toString());
			if(rs.next()){
				mccMap.put("the_password", rs.getString("the_password"));
				mccMap.put("enddate", rs.getString("enddate"));
				mccMap.put("is_forever", rs.getString("is_forever"));
			}
			int module_set=(int) mccMap.get("module_set");
			mccMap.remove("module_set");
			mccMap.put("module_set", moduleset_compose(module_set));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//module_set数字转化成对应的字符串
	private String moduleset_compose(int module_set){
		StringBuilder modusetStr=new StringBuilder(String.format("%08d",module_set));
		int moduleset_len=Constant.module_set.length;
		if(module_set>0)  modusetStr.append(" [ ");
		for(int i=0;i<moduleset_len;i++){
			if((module_set&(1<<i))!=0){
	               modusetStr.append(Constant.module_set[i]+"  ");
	         }
		}
		if(module_set>0) modusetStr.append(" ]");
		return modusetStr.toString();
	}
}
