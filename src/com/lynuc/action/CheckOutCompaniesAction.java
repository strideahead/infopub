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

import com.lynuc.PO.CompanyPO;
import com.lynuc.bean.BaseAction;
import com.lynuc.dao.Pager;
import com.lynuc.def.Constant;
import com.lynuc.implDao.CheckOutItemsFromDBImpl;
import com.lynuc.util.JdbcUtil;

public class CheckOutCompaniesAction extends BaseAction implements Serializable
{
	private static final long serialVersionUID = 4057490182203918711L;
	public String execute()
	{
		String pageNumStr = request.getParameter("pageNum"); 
		Cookie[] cookies = request.getCookies();
		String pageSize_company="";
		Cookie cookie_pagesize=null;
		if(cookies!=null && cookies.length>0){
			for(int i=0;i<cookies.length;i++){
				Cookie c=cookies[i];
				if("pageSize_company".equals(c.getName())){
					cookie_pagesize=c;
					pageSize_company=cookie_pagesize.getValue();
				}
			}
		}
		int pageNum = Constant.DEFAULT_PAGE_NUM; //显示第几页数据
		if(pageNumStr!=null && !"".equals(pageNumStr.trim())){
			pageNum = Integer.parseInt(pageNumStr);
		}
		
		int pageSize = Constant.DEFAULT_PAGE_SIZE;  // 每页显示多少条记录
		String pageSizestr = request.getParameter("pageSize");
		if(pageSizestr!=null && !("".equals(pageSizestr.trim()))&&!"-1".equals(pageSizestr)){
			pageSize = Integer.parseInt(pageSizestr);
			if("".equals(pageSize_company)){
				Cookie cookie_pageSize_company=new Cookie("pageSize_company", pageSize+"");
				cookie_pageSize_company.setMaxAge(3600);
				response.addCookie(cookie_pageSize_company);
			}else{
				if(!pageSize_company.equals(pageSize+"")){
					cookie_pagesize.setMaxAge(0);
					response.addCookie(cookie_pagesize);
					Cookie cookie_pageSize_company=new Cookie("pageSize_company", pageSize+"");
					cookie_pageSize_company.setMaxAge(3600);
					response.addCookie(cookie_pageSize_company);
				}
			}
		}else{
			if(!"".equals(pageSize_company)){
				pageSize=Integer.parseInt(pageSize_company);
			}
		}
		
		CompanyPO searchModel=new CompanyPO();
		String searchKeyStr=request.getParameter("companyKeystr");

		if(searchKeyStr!=null){
			searchModel.setAddress(searchKeyStr);
			sessionMap.put("keystr_company", searchKeyStr);
		}else{
			searchKeyStr=(String) sessionMap.get("keystr_company");
			if(searchKeyStr!=null) searchModel.setAddress(searchKeyStr);
		}
		String companytype=request.getParameter("company_type");
		if(companytype!=null&&!companytype.equals("-1")){
			searchModel.setCompany_type(companytype);
			sessionMap.put("company_type", companytype);
		}else{
			companytype=(String) sessionMap.get("company_type");
			if(companytype!=null) searchModel.setAddress(companytype);
		}
		CheckOutItemsFromDBImpl companyhql=new CheckOutItemsFromDBImpl();
		
		Pager<CompanyPO> result=companyhql.checkoutCompanies(searchModel, pageNum ,pageSize);
		for(int i=0;i<result.getDataList().size();i++){
			CompanyPO cp=(CompanyPO)result.getDataList().get(i);
			String company_type=cp.getCompany_type();
			if(company_type.equals("1")){
				cp.setCompany_type("客户");
			}else if(company_type.equals("2")){
				cp.setCompany_type("事务所");
			}else if(company_type.equals("3")){
				cp.setCompany_type("管理公司");
			}else if(company_type.equals("4")){
				cp.setCompany_type("代理商");
			}
		}
		request.setAttribute("keyStr", searchKeyStr);
		request.setAttribute("company_type_str", companytype);
		request.setAttribute("pagesize_str", pageSize);
		request.setAttribute("totalRecord", result.getTotalRecord());
		request.setAttribute("result", result);
		request.setAttribute("fromIndex", (result.getCurrentPage()-1)*pageSize+1);
		request.setAttribute("currentpage", result.getCurrentPage());
		request.setAttribute("totalpage", result.getTotalPage());
		return SUCCESS;
	}
	public String companyDel()
	{
		String companygguid=request.getParameter("gguid");
		CheckOutItemsFromDBImpl companyhql=new CheckOutItemsFromDBImpl();
		companyhql.delCompanyByGguid(companygguid);
		
		return SUCCESS;
	}
	public String companyEdit() throws SQLException
	{
		String companygguid=request.getParameter("company_gguid");
		CheckOutItemsFromDBImpl companyhql=new CheckOutItemsFromDBImpl();
		CompanyPO cp=companyhql.getCompanyByGguid(companygguid);
		List<Map<String,String>> version_checkNameList = getVersion_checkNameList();
		if(cp==null){
			return ERROR;
		}else{
			request.setAttribute("company_get", cp);
			request.setAttribute("version_checkNameList", version_checkNameList);
			return SUCCESS;
		}
	}
	public String addCompanyInfo() throws SQLException
	{
		List<Map<String,String>> version_checkNameList = getVersion_checkNameList();
		request.setAttribute("version_checkNameList", version_checkNameList);
		return SUCCESS;
	}
	private List<Map<String,String>> getVersion_checkNameList() throws SQLException
	{
		String sqlUserList="select id, usercode, username from sys_user order by id";
		try(
			Connection conn = JdbcUtil.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sqlUserList);
			ResultSet rs=pstmt.executeQuery();	
		)
		{
			List<Map<String,String>> version_checkNameList = new ArrayList<Map<String,String>>(); 
			while(rs.next()){
				Map<String,String> map=new HashMap<String,String>();
				map.put("version_check_id",rs.getInt("id")+"");
				map.put("version_check__userinfo", rs.getString("username")+"("+rs.getString("usercode")+")");
				version_checkNameList.add(map);
			}
			return version_checkNameList;
		}
		
	}
}
