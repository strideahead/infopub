package com.lynuc.action;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import com.lynuc.bean.BaseAction;
import com.lynuc.bean.User;
import com.lynuc.dao.Pager;
import com.lynuc.def.Constant;
import com.lynuc.implDao.CheckOutItemsFromDBImpl;
import com.lynuc.util.JdbcUtil;

public class CheckOutUsersAction extends BaseAction implements Serializable{

	private static final long serialVersionUID = -3624775131225776767L;
	public String execute()
	{
		// 校验pageNum参数输入合法性
		String pageNumStr = request.getParameter("pageNum"); 
		Cookie[] cookies = request.getCookies();
		String pageSize_user="";
		Cookie cookie_pagesize=null;
		if(cookies!=null && cookies.length>0){
			for(int i=0;i<cookies.length;i++){
				Cookie c=cookies[i];
				if("pageSize_user".equals(c.getName())){
					cookie_pagesize=c;
					pageSize_user=cookie_pagesize.getValue();
				}
			}
		}
		int pageNum = Constant.DEFAULT_PAGE_NUM; //显示第几页数据
		if(pageNumStr!=null && !"".equals(pageNumStr.trim())){
			pageNum = Integer.parseInt(pageNumStr);
		}
		
		int pageSize = Constant.DEFAULT_USER_PAGE_SIZE;  // 每页显示多少条记录
		String pageSizeStr = request.getParameter("pageSize");
		if(pageSizeStr!=null && !"".equals(pageSizeStr.trim())&&!"-1".equals(pageSizeStr)){
			pageSize = Integer.parseInt(pageSizeStr);
			if("".equals(pageSize_user)){
				Cookie cookie_pageSize_user=new Cookie("pageSize_user", pageSize+"");
				cookie_pageSize_user.setMaxAge(3600);
				response.addCookie(cookie_pageSize_user);
			}else{
				if(!pageSize_user.equals(pageSize+"")){
					cookie_pagesize.setMaxAge(0);
					response.addCookie(cookie_pagesize);
					Cookie cookie_pageSize_user=new Cookie("pageSize_user", pageSize+"");
					cookie_pageSize_user.setMaxAge(3600);
					response.addCookie(cookie_pageSize_user);
				}
			}
		}else{
			if(!"".equals(pageSize_user)){
				pageSize=Integer.parseInt(pageSize_user);
			}
		}
		User searchModel=new User();
		String searchKeyStr=request.getParameter("txtKeyword");

		if(searchKeyStr!=null){
			searchModel.setUsername(searchKeyStr);
			sessionMap.put("keystr_user", searchKeyStr);
		}else{
			searchKeyStr=(String) sessionMap.get("keystr_user");
			if(searchKeyStr!=null) searchModel.setUsername(searchKeyStr);
		}
		CheckOutItemsFromDBImpl companyhql=new CheckOutItemsFromDBImpl();
		
		Pager<User> result=companyhql.checkoutUsers(searchModel, pageNum ,pageSize);
		request.setAttribute("keyStr", searchKeyStr);
		request.setAttribute("totalRecord", result.getTotalRecord());
		request.setAttribute("result", result);
		request.setAttribute("fromIndex", (pageNum-1)*pageSize+1);
		request.setAttribute("currentpage", result.getCurrentPage());
		request.setAttribute("totalpage", result.getTotalPage());
		request.setAttribute("currentPageSize", pageSize);
		return SUCCESS;
	}

	@SuppressWarnings("resource")
	public String editUserInfo() throws Exception 
	{
		String useridObj=request.getParameter("userid");
		if(useridObj==null){
			return ERROR;
		}
		int userid=Integer.parseInt(useridObj);
		String usercode=request.getParameter("usercode");
		
		String sqlCompanyList="select gguid, company_name from company order by company_name";
		String checkoutUserSql="select su.*,co.company_name from sys_user as su left join company as co "
				+ "on co.gguid=su.belong_company_gguid where id=? and usercode=?";
		User user=new User();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn = JdbcUtil.getConnection();
			pstmt=conn.prepareStatement(checkoutUserSql);
			pstmt.setObject(1, userid);
			pstmt.setObject(2, usercode);
			rs=pstmt.executeQuery();
			if(rs.next()){
				user.getEntry(rs);
			}
			pstmt=conn.prepareStatement(sqlCompanyList);
			rs=pstmt.executeQuery();
			List<Map<String,String>> companyNameList = new ArrayList<Map<String,String>>(); 
			while(rs.next()){
				Map<String,String> map=new HashMap<String,String>();
				map.put("gguid", rs.getString("gguid"));
				map.put("company_name", rs.getString("company_name"));
				companyNameList.add(map);
			}
			request.setAttribute("select_user", user);
			request.setAttribute("companyNameList", companyNameList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtil.free(rs, pstmt, conn);
		}
		
		
		return SUCCESS;
	}
	public String addUserInfo() throws SQLException
	{
		String sqlCompanyList="select gguid, company_name from company order by company_name";
		try(
			Connection conn = JdbcUtil.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sqlCompanyList);
			ResultSet rs=pstmt.executeQuery();
		)
		{
			List<Map<String,String>> companyNameList = new ArrayList<Map<String,String>>(); 
			while(rs.next()){
				Map<String,String> map=new HashMap<String,String>();
				map.put("gguid", rs.getString("gguid"));
				map.put("company_name", rs.getString("company_name"));
				companyNameList.add(map);
			}
			request.setAttribute("companyNameList", companyNameList);
			request.setAttribute("type", "edit");
		}
		return SUCCESS;
	}
	public String deleteUserInfo() throws SQLException
	{
		String idstr=request.getParameter("userid");
		if(idstr!=null){
			int id = Integer.parseInt(idstr);
			CheckOutItemsFromDBImpl userhql=new CheckOutItemsFromDBImpl();
			userhql.deleteUserInfo(id);
		}
		return SUCCESS;
	}
}
