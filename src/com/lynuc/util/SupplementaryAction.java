package com.lynuc.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lynuc.bean.BaseAction;

/*
 * ��action��supplementary.jspҳ�������޸�Mcc�б�module_set�ֶΡ���ť���
 * infopub���ݿ���mcc��module_setֵ�������������controller_no ��Ӧ��
 * cust_company_gguid��Ӧ��company_id��Ϊ0����������Ӧmcc���е�module_setֵ
 */
public class SupplementaryAction extends BaseAction{

	public String execute() throws SQLException
	{
		String checkoutCompanyid = "select company_id from company where gguid in " 
				+"(select cust_company_gguid from mcc where controller_no=?) ";
		String checkoutcontrollerno="select cust_company_gguid,controller_no,module_set from mcc";
		String modify_moduleset="update mcc set module_set=? where controller_no=?";
		try(
			Connection conn=JdbcUtil.getConnection();
			java.sql.Statement state=conn.createStatement();
			ResultSet rscontrollerno=state.executeQuery(checkoutcontrollerno);
		){
			while(rscontrollerno.next()){
				if(null != rscontrollerno.getObject("controller_no") 
						&& null != rscontrollerno.getObject("cust_company_gguid")
						&& null != rscontrollerno.getObject("module_set")){
					String controller_no = rscontrollerno.getString("controller_no");
					int module_set=rscontrollerno.getInt("module_set");
					int companyid_tmp=(module_set&(-131072))>>17;
					if(companyid_tmp!=0) continue;
					PreparedStatement pstmt=conn.prepareStatement(checkoutCompanyid);
					try {
						pstmt.setString(1, controller_no);
						ResultSet rscompanyid=pstmt.executeQuery();
						if(rscompanyid.next()){
							if(null!=rscompanyid.getObject("company_id") && 0!=rscompanyid.getInt("company_id")){
								int companyid=rscompanyid.getInt("company_id");
								module_set=(companyid<<17)|module_set;
								PreparedStatement pstmtupdate=conn.prepareStatement(modify_moduleset);
								System.out.println("company_id=="+companyid+",module_set="+module_set+",controller_no="+controller_no);
								try {
									pstmtupdate.setInt(1, module_set);
									pstmtupdate.setString(2, controller_no);
									pstmtupdate.executeUpdate();
								} catch (Exception e) {
									// TODO: handle exception
								}finally{
									if(pstmtupdate != null){
										pstmtupdate.close();
									}
									if(null != rscompanyid){
										rscompanyid.close();
									}
								}
							}
						}
					} catch (Exception e) {
						// TODO: handle exception
					}finally{
						if(null != pstmt) pstmt.close();
					}
				}
			}
		}
		request.setAttribute("success", "���������");
		return SUCCESS;
		
	}
}
