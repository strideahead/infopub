package com.lynuc.action;

import java.io.Serializable;

import javax.servlet.http.Cookie;

import com.lynuc.bean.BaseAction;
import com.lynuc.bean.MccPass_m;
import com.lynuc.dao.MccListDao;
import com.lynuc.dao.Pager;
import com.lynuc.def.Constant;
import com.lynuc.implDao.JdbcSqlMccDaoImpl;

public class MccListAction_m extends BaseAction implements Serializable
{
	private static final long serialVersionUID = 8194114403268586022L;

	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		MccListDao mccDao = new JdbcSqlMccDaoImpl();
		String pageNumstr=request.getParameter("pageNum");
		String pageSizestr=request.getParameter("pageSize");
		Cookie[] cookies = request.getCookies();
		String pageSize_m="";
		Cookie cookie_pagesize=null;
		if(cookies!=null && cookies.length>0){
			for(int i=0;i<cookies.length;i++){
				Cookie c=cookies[i];
				if("pageSize_m".equals(c.getName())){
					cookie_pagesize=c;
					pageSize_m=cookie_pagesize.getValue();
				}
			}
		}
		String searchKeyStr="";
		String isSearchBtn=request.getParameter("isSearchBtn");
		if(isSearchBtn!=null&&isSearchBtn.equals("true")){
			String keystr=request.getParameter("keyStrm");
			if(null!=keystr && !"".equals(keystr.trim())){
				searchKeyStr=keystr;
				sessionMap.put("mccKeystrm", searchKeyStr);
			}
		}else{
			String keyStr=(String) sessionMap.get("mccKeystrm");
			if(keyStr!=null&&!keyStr.equals(""))
				searchKeyStr=keyStr;
		}
		int pageNum=1;
		
		int pageSize=Constant.DEFAULT_PAGE_SIZE;
		if(null!=pageNumstr && !"".equals(pageNumstr.trim())){
			pageNum = Integer.parseInt(pageNumstr);
		}
		if(null!=pageSizestr && !"".equals(pageSizestr.trim())){
			pageSize = Integer.parseInt(pageSizestr);
			if("".equals(pageSize_m)){
				Cookie cookie_pageSize_m=new Cookie("pageSize_m", pageSize+"");
				cookie_pageSize_m.setMaxAge(3600);
				response.addCookie(cookie_pageSize_m);
			}else{
				if(!pageSize_m.equals(pageSize+"")){
					cookie_pagesize.setMaxAge(0);
					response.addCookie(cookie_pagesize);
					Cookie cookie_pageSize_m=new Cookie("pageSize_m", pageSize+"");
					cookie_pageSize_m.setMaxAge(3600);
					response.addCookie(cookie_pageSize_m);
				}
			}
		}else{
			if(!"".equals(pageSize_m)){
				pageSize=Integer.parseInt(pageSize_m);
			}
		}
		Pager<MccPass_m> result = mccDao.findMccList_m(searchKeyStr,pageNum,pageSize);
		request.setAttribute("mmList", result);
		request.setAttribute("currentpage", result.getCurrentPage());
		request.setAttribute("totalpage", result.getTotalPage());
		request.setAttribute("pageSize_m", pageSize);
		request.setAttribute("totalRecord", result.getTotalRecord());
		return SUCCESS;
	}
}
