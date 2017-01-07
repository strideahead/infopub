package com.lynuc.action;

import java.util.HashMap;
import java.util.Map;

import com.lynuc.bean.BaseAction;
import com.lynuc.dao.MccDetailByControllerDao;
import com.lynuc.implDao.GetMccDetailByController_noImpl;

/*
 * 权限小于’A'用户点击获取控制器信息按钮，跳转到checkOrEditPwd.jsp
 */
public class CheckOrEditPwdAction extends BaseAction {
	private static final long serialVersionUID = 3258568508516406417L;
	
	public String execute() throws Exception{
		MccDetailByControllerDao passdetail = new GetMccDetailByController_noImpl();
		Map<String,Object>  map = new HashMap<String,Object>();
		String controller_no=request.getParameter("controller_no");
		passdetail.getMccDetailByController_no(controller_no,map);
		request.setAttribute("mccpassData", map);
		return SUCCESS;
		
	}
}
