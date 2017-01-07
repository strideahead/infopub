package com.lynuc.action;

import com.lynuc.bean.BaseAction;
import com.lynuc.bean.Mcc;
import com.lynuc.bean.VersionPassword;
import com.lynuc.dao.Pager;
import com.lynuc.implDao.JdbcSqlVerUpdateListImpl;

public class VersionUpdatePwdAction extends BaseAction {

	private static final long serialVersionUID = 211081632531886473L;
	public String execute() throws Exception{
		String keyStr=request.getParameter("keyWord");
		String state=request.getParameter("state");
		String endDateState=request.getParameter("endDateState");
		
		VersionPassword searchModel=new VersionPassword();
		searchModel.setCard_no(keyStr);
		searchModel.setState(state);
		searchModel.setHard_ver(endDateState);
		JdbcSqlVerUpdateListImpl verUpdatePwd=new JdbcSqlVerUpdateListImpl();
		Mcc mcc=new Mcc();
		Pager<VersionPassword> verList = verUpdatePwd.findVerUpdatePwdList(searchModel,1,140);
		request.setAttribute("mcc", mcc);
		request.setAttribute("verUpAmount", verList.getDataList().size());
		request.setAttribute("verUpdateList", verList);
		return SUCCESS;
	}
}
