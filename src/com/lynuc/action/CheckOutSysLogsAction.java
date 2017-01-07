package com.lynuc.action;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import com.lynuc.PO.SysLogPO;
import com.lynuc.bean.BaseAction;
import com.lynuc.dao.Pager;
import com.lynuc.def.Constant;
import com.lynuc.implDao.CheckOutItemsFromDBImpl;
import com.lynuc.util.JdbcUtil;

public class CheckOutSysLogsAction extends BaseAction implements Serializable{

	private static final long serialVersionUID = -8026677267791468700L;

	@SuppressWarnings("unchecked")
	public String execute() throws SQLException
	{
		String pageNumStr = request.getParameter("pageNum"); 
		Cookie[] cookies = request.getCookies();
		String pageSize_syslog="";
		Cookie cookie_pagesize=null;
		if(cookies!=null && cookies.length>0){
			for(int i=0;i<cookies.length;i++){
				Cookie c=cookies[i];
				if("pageSize_syslog".equals(c.getName())){
					cookie_pagesize=c;
					pageSize_syslog=cookie_pagesize.getValue();
				}
			}
		}
		int pageNum = Constant.DEFAULT_PAGE_NUM; //显示第几页数据
		if(pageNumStr!=null && !"".equals(pageNumStr.trim())){
			pageNum = Integer.parseInt(pageNumStr);
		}
		
		int pageSize = Constant.DEFAULT_PAGE_SIZE;  // 每页显示多少条记录
		String pageSizeStr = request.getParameter("pageSize");
		if(pageSizeStr!=null && !("".equals(pageSizeStr.trim()))&&!"-1".equals(pageSizeStr)){
			pageSize = Integer.parseInt(pageSizeStr);
			if("".equals(pageSize_syslog)){
				Cookie cookie_pageSize_syslog=new Cookie("pageSize_syslog", pageSize+"");
				cookie_pageSize_syslog.setMaxAge(3600);
				response.addCookie(cookie_pageSize_syslog);
			}else{
				if(!pageSize_syslog.equals(pageSize+"")){
					cookie_pagesize.setMaxAge(0);
					response.addCookie(cookie_pagesize);
					Cookie cookie_pageSize_syslog=new Cookie("pageSize_syslog", pageSize+"");
					cookie_pageSize_syslog.setMaxAge(3600);
					response.addCookie(cookie_pageSize_syslog);
				}
			}
		}else{
			if(!"".equals(pageSize_syslog)){
				pageSize=Integer.parseInt(pageSize_syslog);
			}
		}
		
		SysLogPO searchModel=new SysLogPO();
		String loguser_search=request.getParameter("ddl_log_user");

		if(loguser_search!=null &&!loguser_search.equals("")&& !loguser_search.equals("-1")){
			searchModel.setLog_user(loguser_search);
			sessionMap.put("loguserr_syslog", loguser_search);
		}else{
			loguser_search=(String) sessionMap.get("loguserr_syslog");
			if(loguser_search!=null) searchModel.setLog_user(loguser_search);
		}
		String logtype_search=request.getParameter("ddl_loguser_type");
		if(logtype_search!=null&&!logtype_search.equals("")&&!logtype_search.equals("-1")){
			searchModel.setLog_type(logtype_search);
			sessionMap.put("logtype_syslog", loguser_search);
		}else{
			logtype_search=(String) sessionMap.get("logtype_syslog");
			if(logtype_search!=null) searchModel.setLog_type(logtype_search);
		}

		CheckOutItemsFromDBImpl sysloghql=new CheckOutItemsFromDBImpl();
		Pager<SysLogPO> result=sysloghql.checkoutSyslogs(searchModel, pageNum ,pageSize);
		
		List<Map<String,String>> loguser_list = new ArrayList<Map<String,String>>(); 
		List<Map<String,String>> logtype_list = new ArrayList<Map<String,String>>(); 
		
		String logusersql="select distinct log_user from sys_log ";
    	String logtypesql="select distinct log_type from sys_log ";
		try(
			Connection conn = JdbcUtil.getConnection();
			Statement stmt=conn.createStatement();	
			ResultSet loguser_rs=stmt.executeQuery(logusersql);		
		)
		{
			while(loguser_rs.next()){
				Map<String,String> map=new HashMap<String,String>();
				map.put("logusername1", loguser_rs.getString("log_user"));
				map.put("logusername2", loguser_rs.getString("log_user"));
				loguser_list.add(map);
			}
		}
		try(
				Connection conn = JdbcUtil.getConnection();
				Statement stmt=conn.createStatement();	
				ResultSet logtype_rs=stmt.executeQuery(logtypesql);			
			)
			{
		    	while(logtype_rs.next()){
					Map<String,String> map=new HashMap<String,String>();
					String log_type_str=logtype_rs.getString("log_type");
					map.put("log_type", log_type_str);
					if(log_type_str!=null&&log_type_str.equals("1")){
						map.put("log_type_name", "登录系统");
					}else if(log_type_str!=null&&log_type_str.equals("2")){
						map.put("log_type_name", "资料");
					}else if(log_type_str!=null&&log_type_str.equals("3")){
						map.put("log_type_name", "投诉单");
					}else if(log_type_str!=null&&log_type_str.equals("4")){
						map.put("log_type_name", "论坛");
					}else if(log_type_str!=null&&log_type_str.equals("5")){
						map.put("log_type_name", "升级包");
					}else if(log_type_str!=null&&log_type_str.equals("6")){
						map.put("log_type_name", "机床/控制器/控制卡/密码");
					}else if(log_type_str!=null&&log_type_str.equals("1")){
						map.put("log_type_name", "登录系统");
					}
					
					logtype_list.add(map);
				}
			}
		request.setAttribute("loguser_search", loguser_search);
		request.setAttribute("logtype_search", logtype_search);
		request.setAttribute("totalRecord", result.getTotalRecord());
		request.setAttribute("result", result);
		request.setAttribute("fromIndex", (pageNum-1)*pageSize+1);
		request.setAttribute("currentpage", result.getCurrentPage());
		request.setAttribute("totalpage", result.getTotalPage());
		request.setAttribute("loguserList", loguser_list);
		request.setAttribute("logtypeList", logtype_list);
		request.setAttribute("pagesize_str", pageSize);
		
		return SUCCESS;
	}

}
