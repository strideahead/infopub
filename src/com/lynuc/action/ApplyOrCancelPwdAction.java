package com.lynuc.action;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.lynuc.bean.BaseAction;
import com.lynuc.dao.PasswordDetailDao;
import com.lynuc.implDao.GetPasswordDetailImpl;
/*
 * ��action�Ǵӵ�������������������������ӿ�������������ȷ������������'��ת����
 * ��ȡ��Ҫ���������mcc_password���ݣ����������
 * 1 �������룬get��ʽ����controller_no������
 * 2 �������룬���ݹ���apply_no����
 * 3 ����ӿ�������������ȷ������������'��ת��session����controller_no����
 */
public class ApplyOrCancelPwdAction extends BaseAction implements Serializable{
	private static final long serialVersionUID = 3301680299006584349L;

	public String execute() throws Exception{
		PasswordDetailDao mccpwdDetailDao = new GetPasswordDetailImpl();
		Map<String,Object>  map = new HashMap<String,Object>();
		Map<String,Object>  mapOldpassword=null;
		//3 ����ӿ�������������ȷ������������'��ת��session����controller_no����
		String controller_no=(String) sessionMap.get("controller_no_applypass");
		if(controller_no != null){
			sessionMap.remove("controller_no_applypass");
		}else{
			//1 �������룬get��ʽ����controller_no������
			controller_no=request.getParameter("txt_controller_no");
		}
		String apply_Id=request.getParameter("apply_no");
		String is_revert=request.getParameter("is_revert");
		//��������������
		if(controller_no!=null){
			mccpwdDetailDao.getPasswordDetailWithNoApplyID(controller_no, map);
		//2 �������룬���ݹ���apply_no����
		}else{
			//��ȡ�������������������
			mccpwdDetailDao.getPasswordDetailByApplyID(Integer.parseInt(apply_Id),map);
			mapOldpassword=new HashMap<String,Object>();
			//��ȡ����һ��controller_no�������������
			mccpwdDetailDao.getOldpasswordDetailByApplyID(Integer.parseInt(apply_Id), mapOldpassword);
			
			if("true".equals(is_revert)){
				map.remove("apply_no");
				map.put("apply_no", apply_Id);
				request.setAttribute("max_apply_no", Integer.parseInt(apply_Id));
			}else{
				int max_apply_no=mccpwdDetailDao.getMaxApply_no();
				request.setAttribute("max_apply_no", max_apply_no);
			}
		}
		request.setAttribute("is_revert", is_revert);
		request.setAttribute("mccpassData", map);
		request.setAttribute("mcc_oldpassData", mapOldpassword);
		
		/*
		 * ��ȡ����������ʼ���ڵ��߼��������Լ�����
		 */
		String is_forever=(String) map.get("is_forever");
		String endDate=(String) map.get("enddate");
		boolean check=false;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(endDate!=null && !"".equals(endDate)){
			Calendar c2=Calendar.getInstance();
			Calendar c=Calendar.getInstance();
		    Date d = sdf.parse(endDate);  
		    c.setTime(d);  
		    c.add(Calendar.MONTH, 1);
		    if(c.getTimeInMillis() < c2.getTimeInMillis()) {
		    	check = true;
		    }
		}
		if((is_forever==null || is_forever.equals("Y")) || (endDate==null || "".equals(endDate)) || check){
			Calendar calendar=Calendar.getInstance();
	        calendar.add(Calendar.MONTH, +1);
	        Date date=calendar.getTime();
	        request.setAttribute("enddatebat", sdf.format(date));
	        calendar.add(Calendar.MONTH, -1);
	        calendar.add(Calendar.DATE, -1);
	        date=calendar.getTime();
	        request.setAttribute("fromdate", sdf.format(date));
		}else{
			Calendar calendar=Calendar.getInstance();
		    Date date = sdf.parse(endDate);  
		    calendar.setTime(date);  
		    calendar.add(Calendar.DATE, -1);
	        date=calendar.getTime();
	        request.setAttribute("fromdate", sdf.format(date));
	        calendar.add(Calendar.MONTH, 1);
	        calendar.add(Calendar.DATE, 1);
	        date=calendar.getTime();
	        request.setAttribute("enddatebat", sdf.format(date));
		}
		return SUCCESS;
	}

}
