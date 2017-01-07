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
 * 此action是从点击进入申请密码或撤销密码或从添加控制器界面点击’确定并申请密码'跳转过来
 * 获取所要操作申请的mcc_password数据，分三种情况
 * 1 申请密码，get方式传递controller_no参数，
 * 2 撤销密码，传递过来apply_no参数
 * 3 从添加控制器界面点击’确定并申请密码'跳转，session传递controller_no参数
 */
public class ApplyOrCancelPwdAction extends BaseAction implements Serializable{
	private static final long serialVersionUID = 3301680299006584349L;

	public String execute() throws Exception{
		PasswordDetailDao mccpwdDetailDao = new GetPasswordDetailImpl();
		Map<String,Object>  map = new HashMap<String,Object>();
		Map<String,Object>  mapOldpassword=null;
		//3 从添加控制器界面点击’确定并申请密码'跳转，session传递controller_no参数
		String controller_no=(String) sessionMap.get("controller_no_applypass");
		if(controller_no != null){
			sessionMap.remove("controller_no_applypass");
		}else{
			//1 申请密码，get方式传递controller_no参数，
			controller_no=request.getParameter("txt_controller_no");
		}
		String apply_Id=request.getParameter("apply_no");
		String is_revert=request.getParameter("is_revert");
		//控制器申请密码
		if(controller_no!=null){
			mccpwdDetailDao.getPasswordDetailWithNoApplyID(controller_no, map);
		//2 撤销密码，传递过来apply_no参数
		}else{
			//获取申请密码控制器的数据
			mccpwdDetailDao.getPasswordDetailByApplyID(Integer.parseInt(apply_Id),map);
			mapOldpassword=new HashMap<String,Object>();
			//获取最新一次controller_no申请密码的数据
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
		 * 获取申请密码起始日期的逻辑，具体自己分析
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
