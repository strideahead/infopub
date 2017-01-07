package com.lynuc.implDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lynuc.bean.Mcc;
import com.lynuc.bean.MccPassword;
import com.lynuc.dao.PasswordDetailDao;
import com.lynuc.def.Constant;
import com.lynuc.util.JdbcUtil;
import com.lynuc.util.JdbcUtilInfoPubConfig;

public class GetPasswordDetailImpl implements PasswordDetailDao{
	
	@Override
	public void getPasswordDetailByApplyID(int apply_Id, Map<String,Object>  map)throws SQLException{
		JdbcUtil jdbcUtil = null;
		try {
			Connection conn=JdbcUtil.getConnection(); // 获取数据库链接
			
			PreparedStatement pstmt=conn.prepareStatement("{call dbo.mccpassword_detailByApplyno(?)}");
			pstmt.setInt(1, apply_Id);
			ResultSet rs=pstmt.executeQuery();
			System.out.println("getPasswordDetailByApplyID");
			if(rs.next()){
				try {
					map.put("controller_ver", rs.getString("controller_ver"));
					map.put("machine_no", rs.getString("machine_no"));
					map.put("total_type", rs.getString("total_type"));
					map.put("machine_type", rs.getString("machine_type"));
					map.put("controller_no", rs.getString("controller_no"));
					map.put("card_no", rs.getString("card_no"));
					map.put("ipc_sn", rs.getString("ipc_sn"));
					map.put("password_ver", rs.getString("password_ver"));
					map.put("office_name", rs.getString("office_name"));
					map.put("customer_name", rs.getString("customer_name"));
					map.put("is_pay", rs.getString("is_pay"));
					map.put("setup_date", rs.getString("setup_date"));
					map.put("soft_ver", rs.getString("soft_ver"));
					map.put("hard_ver", rs.getString("hard_ver"));
					map.put("mac_option", rs.getString("mac_option"));
					map.put("lynuc_option", rs.getString("lynuc_option"));
					map.put("the_password_new", rs.getString("the_password"));
					map.put("enddate_new", rs.getString("enddate"));
					int module_set=rs.getInt("module_set");
					
					map.put("module_set", module_set);
					map.put("module_set_num", this.moduleset_compose(module_set));
					map.put("startdate", rs.getString("startdate"));
					map.put("enddate", rs.getString("enddate"));
					map.put("is_forever", rs.getString("is_forever"));
					map.put("is_batch", rs.getString("is_batch"));
					map.put("batch_startdate", rs.getString("batch_startdate"));
					map.put("batch_enddate", rs.getString("batch_enddate"));
					map.put("batch_period", rs.getString("batch_period"));
					map.put("state", rs.getString("state"));
					
					map.put("request_user_id", rs.getInt("request_user_id"));
					map.put("request_name", rs.getString("request_name"));
					map.put("request_time", rs.getString("request_time"));
					map.put("request_memo", rs.getString("request_memo"));
					map.put("apply_no", rs.getString("apply_no"));
					map.put("check_time", rs.getString("check_time"));
					map.put("check_name", rs.getString("check_name"));
					map.put("check_memo", rs.getString("check_memo"));
					map.put("input_user_name", rs.getString("input_user_name"));
					map.put("input_time", rs.getString("input_time"));
					
					StringBuilder selectCurrPass=new StringBuilder("select is_forever,the_password,enddate from mcc_password where apply_no in");
					selectCurrPass.append("(select max(apply_no) from mcc_password where controller_no=? and state='Y')");
					pstmt=conn.prepareStatement(selectCurrPass.toString());
					pstmt.setString(1, (String) map.get("controller_no"));
					rs=pstmt.executeQuery();
					if(rs.next()){
						map.put("is_forever_old", rs.getString("is_forever"));
						map.put("the_password_old", rs.getString("the_password"));
						map.put("enddate_old", rs.getString("enddate"));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				map = null;
			}
		} finally {
			if (jdbcUtil != null) {
				jdbcUtil.releaseConn(); // 一定要释放资源
			}
		}
	}
	@Override
	public void getOldpasswordDetailByApplyID(int apply_Id, Map<String,Object>  mapOld)
	{
		try {
			Connection conn=JdbcUtil.getConnection(); // 获取数据库链接
			StringBuilder sqlOldpassword=new StringBuilder("select top 1 * from mcc_password where controller_no in");
			sqlOldpassword.append("(select controller_no from mcc_password where apply_no=?) and the_password is not null order by apply_no desc");
			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(sqlOldpassword.toString());
			pstmt.setInt(1, apply_Id);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				mapOld.put("is_forever_oldpass", rs.getString("is_forever"));
				mapOld.put("the_old_password", rs.getString("the_password"));
				mapOld.put("old_password_enddate", rs.getString("enddate"));
			}
			conn.close();
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void getPasswordDetailWithNoApplyID(String controller_no, Map<String,Object> map)
	{
		Connection conn;
		try {
			conn = JdbcUtil.getConnection();
			PreparedStatement pstmt=conn.prepareStatement("{call dbo.mccsel_byctrlno(?)}");
			System.out.println("getPasswordDetailWithNoApplyID");
			pstmt.setString(1, controller_no);
			ResultSet rs=pstmt.executeQuery();
			Mcc m=new Mcc();
			if(rs.next()){
				try {
					m.getEntry(rs);
					m.convertMccToMap(map);
					map.put("office_name", rs.getString("office_name"));
					map.put("customer_name", rs.getString("customer_name"));
					map.put("enddate", "");
					map.put("is_forever", "N");
					map.put("the_password","");
					int module_set=(int) map.get("module_set");
					map.remove("module_set");
					map.put("module_set", this.moduleset_compose(module_set));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			conn.close();
			pstmt.close();
			rs.close(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public int getMaxApply_no() throws SQLException
	{
		int apply_no=1;
		String maxapply_nosql="select top 1 max_apply_no from table_applyno order by max_apply_no desc";
		try(
			Connection conn = JdbcUtilInfoPubConfig.getConnection();	
			java.sql.Statement statement=conn.createStatement();
			ResultSet rs=statement.executeQuery(maxapply_nosql);
		)
		{
			if(rs.next()){
				apply_no=rs.getInt("max_apply_no");
			}
		}
		return apply_no;
	}
	@Override
	public List<MccPassword> RetrievePasswordByMcc_ControllerNo(String controller_no,Map<String,Object> mccMap)throws Exception{
		List<MccPassword> mpList = new ArrayList<MccPassword>();
		try {
			Connection conn=JdbcUtil.getConnection(); // 获取数据库链接
			PreparedStatement pstmt = conn.prepareStatement("{call dbo.mccsel_byctrlno(?)}");
			pstmt.setString(1, controller_no);
			ResultSet rs=pstmt.executeQuery();
			Mcc mcc=new Mcc();
			if(rs.next()){
				mcc.getEntry(rs);
				mcc.convertMccToMap(mccMap);
				mccMap.put("office_name", rs.getString("office_name"));
				mccMap.put("customer_name", rs.getString("customer_name"));
			}
			pstmt = conn.prepareStatement("{call dbo.mccpasswordsel_byctrlno(?)}");
			pstmt.setString(1, controller_no);
			rs=pstmt.executeQuery();
			while(rs.next()){
				MccPassword mp=new MccPassword();
				mp.getEntry(rs);
				mpList.add(mp);
			}
			conn.close();
			pstmt.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mpList;
	}
	
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
