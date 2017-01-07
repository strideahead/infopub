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
 * ��action�Ǵӵ��ĳ����������������ʷ�б���ת��������Ӧ��ҳ����passwordQuery.jsp
 */
public class RetrievePasswordAction extends BaseAction implements ServletRequestAware {

	private static final long serialVersionUID = -82837608937926003L;
	public String execute() throws Exception{
		//��mcc_pasword��ȡ��ͬcontroller_no�����������б�
		String controller_no=request.getParameter("controller_no");
		PasswordDetailDao passwordDetailDao=new GetPasswordDetailImpl();
		Map<String,Object> mccMap=new HashMap<String,Object>();
		List<MccPassword>  smccList = passwordDetailDao.RetrievePasswordByMcc_ControllerNo(controller_no,mccMap);
		for(int i=0;i<smccList.size();i++){
			MccPassword mp=smccList.get(i);
			String state=mp.getState();
			if(state.equals("Y")) mp.setState("����׼");
			else if(state.equals("N")) mp.setState("�ܾ�");
			else mp.setState("δ��׼");
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
