package com.lynuc.action;

import java.util.HashMap;
import java.util.Map;

import com.lynuc.bean.BaseAction;
import com.lynuc.dao.PasswordDetailDao;
import com.lynuc.implDao.GetPasswordDetailImpl;
/*
 * 此action可又 1 点击申请号链接跳转过来，用来显示所点击申请号的申请信息，2 点击撤销申请密码跳转过来
 * 对应页面是passwordDetail.jsp，
 * 根据申请号检索数据库mcc_password得到页面数据
 */
public class PasswordDetailAction extends BaseAction {

	private static final long serialVersionUID = 2480117000385901050L;
	
	public String execute()throws Exception{
		PasswordDetailDao passwordDetailDao = new GetPasswordDetailImpl();
		Map<String,Object>  map = new HashMap<String,Object>();
		String apply_Id=request.getParameter("apply_no");
		
		//loc字符串区分是从首页进入密码详情还是从控制器申请密码列表进入密码详情
		String loc=request.getParameter("loc");
		passwordDetailDao.getPasswordDetailByApplyID(Integer.parseInt(apply_Id),map);
		request.setAttribute("mccpassData", map);
		//是从控制器申请密码列表进入密码详情
		if(loc != null)
		{
			request.setAttribute("loc", loc);
		}
		//is_revert用来区分是密码撤销功能还是密码详情功能，如果是撤销loc必定是null
		String is_revert=request.getParameter("is_revert");
		request.setAttribute("is_revert", is_revert);
		return SUCCESS;
	}

}
