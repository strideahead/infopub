package com.lynuc.action;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lynuc.bean.BaseAction;
import com.lynuc.bean.MccFromDB;
import com.lynuc.bean.MccOri;
import com.lynuc.def.Constant;
import com.lynuc.util.JdbcUtil;
import com.sun.net.httpserver.Authenticator.Success;

public class BatchEditControllerAction extends BaseAction implements Serializable{
/*
	private String txt_controller_no;
	private String chk_is_pay;
	private String chk_sale_cust;
	private String chk_module_set;
	private String ddl_is_pay;
	private String ddl_is_pay;
	private String ddl_is_pay;
	private String ddl_is_pay;*/
	
	public String execute()
	{
		
		Connection conn;
		try {
			conn = JdbcUtil.getConnection();
			Map<String,String> mapdefault_custname=new HashMap<String,String>();	
			Map<String,String> mapdefault_office=new HashMap<String,String>();	
			getCustSaleCompanyList(conn,mapdefault_custname,mapdefault_office);
			
	        String minlen_sql="select min(len(controller_no)) as minlen from mcc";
	        PreparedStatement pstmt=conn.prepareStatement(minlen_sql);
	        ResultSet rs=pstmt.executeQuery();
	        if(rs.next()){
	        	request.setAttribute("minlen_controllerno", rs.getObject("minlen"));
	        }
	        
			//默认事务所下拉列表值为lynuc
			request.setAttribute("mapdefault_office", mapdefault_office);
			//默认客户名下拉列表值为lynuc
			request.setAttribute("mapdefault_custname", mapdefault_custname);
			conn.close();
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return SUCCESS;
		
	}
	public String submitEditAction() throws SQLException
	{
		Connection conn = JdbcUtil.getConnection();
		String txt_controller_no=request.getParameter("txt_controller_no");
		String txt_controller_notmp=txt_controller_no.replaceAll("\\s{1,}", "");
		String [] ctrlno_list=txt_controller_notmp.split(",");
		String chk_ispay=request.getParameter("chk_is_pay");
		String ddl_is_pay=request.getParameter("ddl_is_pay");
		String chk_salecust=request.getParameter("chk_sale_cust");
		String ddl_office=request.getParameter("ddl_office");
		String ddl_cust=request.getParameter("ddl_cust");
		String chk_moduleset=request.getParameter("chk_module_set");
		StringBuilder batUpdateSql=new StringBuilder("update mcc set ");
		if(chk_ispay != null ){
			batUpdateSql.append(" is_pay='"+ddl_is_pay+"',");
		}
		if(chk_salecust != null ){			
			batUpdateSql.append("cust_company_gguid='"+ddl_cust+"',");
			batUpdateSql.append("sale_company_gguid='"+ddl_office+"',");
		}
		int companyid=0;
		if(chk_moduleset != null ){
			try {
				java.sql.Statement stateCompanyid=conn.createStatement();
				String sqlcompanyid="select company_id from company where gguid='"+ddl_cust+"'";
				ResultSet rscompanyid=stateCompanyid.executeQuery(sqlcompanyid);
				
				//若传递过来的客户名的company_id 则修正module_set值
				if(rscompanyid.next()){
					if(null != rscompanyid.getObject("company_id")){
						companyid=rscompanyid.getInt("company_id");
					}
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/*
			 * 根据要求，个别模块作修正
			 */
			int mudule[]=new int[Constant.module_set.length];
			for(int i=0;i<Constant.module_set.length;i++){
				String name_str="module"+i;
				String mudule_selt=request.getParameter(name_str);	
				
				if(mudule_selt==null){
					if(i==0 || i==1 || i==2 || i==3 || i==5 || i==8){
						mudule[i]=1;
					}else{
						mudule[i]=0;
					}
				}
				else if(mudule_selt.equals("on")) {
					if( i==0 || i==1 || i==2 || i==3 || i==5 || i==8){
						mudule[i]=0;
					}else{
						mudule[i]=1;
					}
				}
			}
			int module_set=0;
			for(int i=0;i<Constant.module_set.length;i++){
				if(mudule[i]!=0) module_set+=1<<i;
			}
			if(companyid != 0){
				module_set = (companyid << 17) | module_set;
			}
			System.out.println("company_id"+companyid);
			batUpdateSql.append("module_set="+module_set+",");
		}
		System.out.println("batUpdateSql====="+batUpdateSql.toString());
		if(batUpdateSql.charAt(batUpdateSql.length()-1)!=','){
			return SUCCESS;
		}else{
			batUpdateSql.deleteCharAt(batUpdateSql.length()-1);
		}
		Statement stateCompanyid=conn.createStatement();
		
		for(String ctrl_noItem:ctrlno_list){
			String ctrlItemSql=batUpdateSql+" where controller_no='"+ctrl_noItem+"'";
			//batUpdateSql.append(" where controller_no='"+ctrl_noItem+"'");
			System.out.println("ctrl_noItem=========="+ctrlItemSql.toString());
			stateCompanyid.executeUpdate(ctrlItemSql);
		}
		request.setAttribute("txt_controller", txt_controller_no);
		return SUCCESS;
	}
	/*
	 * 获取客户下拉别表，即company表中所有的公司信息
	 * 获取事务所下拉列表
	 */
	private void getCustSaleCompanyList(Connection conn,Map<String,String> mapdefault_custname,Map<String,String> mapdefault_office)
	{
		try {
			String sqlCustnameList="select gguid, company_name from company order by company_name";
			Statement stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlCustnameList);
			List<Map<String,String>> custnameList = new ArrayList<Map<String,String>>(); 
			
			while(rs.next()){
				Map<String,String> map=new HashMap<String,String>();
				map.put("gguid", rs.getString("gguid"));
				map.put("company_name", rs.getString("company_name"));
				if(rs.getString("company_name").equals("Lynuc")){
					mapdefault_office.put("gguid", rs.getString("gguid"));
				}
				custnameList.add(map);
			}
			//获取事务所列表。界面上的事务所一栏中的公司type必须大于等于2
			String sqlOfficeList="select gguid, company_name from company where company_type>=2 order by company_name";
			rs=stmt.executeQuery(sqlOfficeList);
			List<Map<String,String>> officeList = new ArrayList<Map<String,String>>(); 
				
			while(rs.next()){
				Map<String,String> map=new HashMap<String,String>();
				map.put("gguid", rs.getString("gguid"));
				map.put("company_name", rs.getString("company_name"));
				if(rs.getString("company_name").equals("Lynuc")){
					mapdefault_custname.put("gguid", rs.getString("gguid"));
				}
				officeList.add(map);
			}
			//客户下拉列表
			request.setAttribute("custnameList", custnameList);
			//事务所下拉列表
			request.setAttribute("officeList", officeList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
