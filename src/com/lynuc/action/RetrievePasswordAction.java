package com.lynuc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.lynuc.bean.BaseAction;
import com.lynuc.bean.MccPassword;
import com.lynuc.dao.PasswordDetailDao;
import com.lynuc.implDao.GetPasswordDetailImpl;
/*
 * 此action是从点击某控制器申请密码历史列表跳转过来，对应的页面是passwordQuery.jsp
 */
public class RetrievePasswordAction extends BaseAction implements ServletRequestAware {

	private static final long serialVersionUID = -82837608937926003L;
	public String execute() throws Exception{
		//从mcc_pasword获取相同controller_no的密码申请列表
		String controller_no=request.getParameter("controller_no");
		PasswordDetailDao passwordDetailDao=new GetPasswordDetailImpl();
		Map<String,Object> mccMap=new HashMap<String,Object>();
		List<MccPassword>  smccList = passwordDetailDao.RetrievePasswordByMcc_ControllerNo(controller_no,mccMap);
		for(int i=0;i<smccList.size();i++){
			MccPassword mp=smccList.get(i);
			String state=mp.getState();
			if(state.equals("Y")) mp.setState("已批准");
			else if(state.equals("N")) mp.setState("拒绝");
			else mp.setState("未批准");
			String check_time=mp.getCheck_time();
			if(check_time!=null && !check_time.equals("")){
				check_time=check_time.split(" ")[0];
			}
			mp.setCheck_time(check_time);
			String enddate=mp.getEnddate();
			if(enddate!=null && !enddate.equals("")){
				enddate=enddate.split(" ")[0];
			}
			mp.setEnddate(enddate);
		}
		request.setAttribute("mcc", mccMap);
		request.setAttribute("mccpassAmount", smccList.size());
		request.setAttribute("mccpassList", smccList);
		request.setAttribute("controller_no", controller_no);
		return SUCCESS;
		
	}
}
