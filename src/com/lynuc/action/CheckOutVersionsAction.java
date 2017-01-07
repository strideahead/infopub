package com.lynuc.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.Cookie;

import com.lynuc.PO.VersionPO;
import com.lynuc.bean.BaseAction;
import com.lynuc.dao.Pager;
import com.lynuc.def.Constant;
import com.lynuc.implDao.CheckOutItemsFromDBImpl;
import com.lynuc.util.JdbcUtil;

public class CheckOutVersionsAction extends BaseAction{

	private static final long serialVersionUID = -7875306694719447383L;
	
	public String execute()
	{
		String pageNumStr = request.getParameter("pageNum"); 
		Cookie[] cookies = request.getCookies();
		if(cookies!=null && cookies.length>0){
			for(int i=0;i<cookies.length;i++){
				Cookie c=cookies[i];
				if("pageSize_version".equals(c.getName())){
				}
			}
		}
		int pageNum = Constant.DEFAULT_PAGE_NUM; 
		if(pageNumStr!=null && !"".equals(pageNumStr.trim())){
			pageNum = Integer.parseInt(pageNumStr);
		}
		
		int pageSize = Constant.DEFAULT_PAGE_SIZE;
		String pageSizeStr = request.getParameter("pageSize");
		if(pageSizeStr!=null && !("".equals(pageSizeStr.trim()))&&!"-1".equals(pageSizeStr)){
			pageSize = Integer.parseInt(pageSizeStr);
		}
		
		VersionPO searchModel=new VersionPO();
		String searchKeyStr=request.getParameter("versionKeystr");

		if(searchKeyStr!=null && !searchKeyStr.equals("")){
			searchModel.setVersion_name(searchKeyStr);
			sessionMap.put("keyStr_version", searchKeyStr);
		}else{
			searchKeyStr=(String) sessionMap.get("keyStr_version");
			if(searchKeyStr!=null) searchModel.setVersion_name(searchKeyStr);
		}
		String issofttype=request.getParameter("issoft_type");
		if(issofttype!=null&&!issofttype.equals("-1")){
			searchModel.setIs_soft(issofttype);
			sessionMap.put("issofttype_version", issofttype);
		}else{
			issofttype=(String) sessionMap.get("issofttype_version");
			if(issofttype!=null) searchModel.setIs_soft(issofttype);
		}
		String ispublishtype=request.getParameter("ispublish_type");
		if(ispublishtype!=null&&!ispublishtype.equals("-1")){
			searchModel.setIs_publish(ispublishtype);
			sessionMap.put("ispublishtype_version", ispublishtype);
		}else{
			ispublishtype=(String) sessionMap.get("issofttype_version");
			if(issofttype!=null) searchModel.setIs_soft(ispublishtype);
		}

		CheckOutItemsFromDBImpl companyhql=new CheckOutItemsFromDBImpl();
		Pager<VersionPO> result=companyhql.checkoutVersions(searchModel, pageNum ,pageSize);
		request.setAttribute("versionkeyStr", searchKeyStr);
		request.setAttribute("issoft_type", issofttype);
		request.setAttribute("ispublish_type", ispublishtype);
		request.setAttribute("pagesize_str", pageSize);
		request.setAttribute("totalRecord", result.getTotalRecord());
		request.setAttribute("result", result);
		request.setAttribute("fromIndex", (result.getCurrentPage()-1)*pageSize+1);
		request.setAttribute("currentpage", result.getCurrentPage());
		request.setAttribute("totalpage", result.getTotalPage());
		
		return SUCCESS;
	}
	public String deleteVersionInfo()
	{
		String versiongguid=request.getParameter("versiongguid");
		CheckOutItemsFromDBImpl companyhql=new CheckOutItemsFromDBImpl();
		companyhql.delVersionByGguid(versiongguid);
		
		return SUCCESS;
	}
	public String versionEdit() throws SQLException
	{
		String versiongguid=request.getParameter("version_gguid");
		CheckOutItemsFromDBImpl coifdbi=new CheckOutItemsFromDBImpl();
		VersionPO vp=coifdbi.getVersionByGguid(versiongguid);
		request.setAttribute("version_get", vp);
		request.setAttribute("versiongguid", versiongguid);
		return SUCCESS;
	}
	public void checkVersionName() throws IOException, SQLException
	{
		String version_name=request.getParameter("version_name");
		response.setCharacterEncoding("UTF-8");
		String sql="select * from version where version_name='"+version_name+"'";
		try(
			Connection conn=JdbcUtil.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql);	
			ResultSet rs=pstmt.executeQuery();	
		)
		{
			if(rs.next()){
				response.getWriter().write("1"); 
			}else{
				response.getWriter().write("0"); 
			}
		}
		
	}
}
