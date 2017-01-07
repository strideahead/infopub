package com.lynuc.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.lynuc.bean.MccPassword;

public interface PasswordDetailDao {

	public void getPasswordDetailByApplyID(int apply_Id, Map<String,Object>  map)throws SQLException;
	
	public void getOldpasswordDetailByApplyID(int apply_Id, Map<String,Object>  mapOld)throws SQLException;
	
	public void getPasswordDetailWithNoApplyID(String controller_no, Map<String, Object> map) throws Exception;
	
	public int getMaxApply_no() throws SQLException;

	//获取相同control_no的控制器申请密码历史列表，从mcc_password  mcc company等表联合查询，查询条目放到mccMap里
	public List<MccPassword> RetrievePasswordByMcc_ControllerNo(String controller_no, Map<String, Object> mccMap)
			throws Exception;
}
