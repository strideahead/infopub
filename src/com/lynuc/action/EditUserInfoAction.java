package com.lynuc.action;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.lynuc.bean.BaseAction;
import com.lynuc.bean.User;
import com.lynuc.implDao.EditItemsFromDBImpl;
import com.lynuc.util.CryptoTools;
import com.lynuc.util.JdbcUtil;

public class EditUserInfoAction extends BaseAction implements Serializable{

	private static final long serialVersionUID = -7875306694719447383L;
	private String txtUserCode;
	private String txtUserName;
	private String txtPassword;
	private String chk_resetPassword;
	private String ddl_user_belong_company;
	private String dpdPermission;
	private String ddl_complaint_permission;
	private String txt_email;
	private String txt_mobile;
	private String ddl_password_type;
	private String txt_password_time;
	private String chkIsValid;
	
	public EditUserInfoAction() {}

	public EditUserInfoAction(String txtUserCode, String txtUserName, String txtPassword, String chk_resetPassword,
			String ddl_user_belong_company, String dpdPermission, String ddl_complaint_permission, String txt_email,
			String txt_mobile, String ddl_password_type, String txt_password_time, String chkIsValid) {
		super();
		this.txtUserCode = txtUserCode;
		this.txtUserName = txtUserName;
		this.txtPassword = txtPassword;
		this.chk_resetPassword = chk_resetPassword;
		this.ddl_user_belong_company = ddl_user_belong_company;
		this.dpdPermission = dpdPermission;
		this.ddl_complaint_permission = ddl_complaint_permission;
		this.txt_email = txt_email;
		this.txt_mobile = txt_mobile;
		this.ddl_password_type = ddl_password_type;
		this.txt_password_time = txt_password_time;
		this.chkIsValid = chkIsValid;
	}

	public String getTxtUserCode() {
		return txtUserCode;
	}
	public void setTxtUserCode(String txtUserCode) {
		this.txtUserCode = txtUserCode;
	}
	public String getTxtUserName() {
		return txtUserName;
	}
	public void setTxtUserName(String txtUserName) {
		this.txtUserName = txtUserName;
	}
	public String getTxtPassword() {
		return txtPassword;
	}
	public void setTxtPassword(String txtPassword) {
		this.txtPassword = txtPassword;
	}
	public String getChk_resetPassword() {
		return chk_resetPassword;
	}
	public void setChk_resetPassword(String chk_resetPassword) {
		this.chk_resetPassword = chk_resetPassword;
	}

	public String getDdl_user_belong_company() {
		return ddl_user_belong_company;
	}
	public void setDdl_user_belong_company(String ddl_user_belong_company) {
		this.ddl_user_belong_company = ddl_user_belong_company;
	}
	public String getDpdPermission() {
		return dpdPermission;
	}

	public void setDpdPermission(String dpdPermission) {
		this.dpdPermission = dpdPermission;
	}
	public String getDdl_complaint_permission() {
		return ddl_complaint_permission;
	}
	public void setDdl_complaint_permission(String ddl_complaint_permission) {
		this.ddl_complaint_permission = ddl_complaint_permission;
	}
	public String getTxt_email() {
		return txt_email;
	}
	public void setTxt_email(String txt_email) {
		this.txt_email = txt_email;
	}
	public String getTxt_mobile() {
		return txt_mobile;
	}
	public void setTxt_mobile(String txt_mobile) {
		this.txt_mobile = txt_mobile;
	}
	public String getDdl_password_type() {
		return ddl_password_type;
	}
	public void setDdl_password_type(String ddl_password_type) {
		this.ddl_password_type = ddl_password_type;
	}
	public String getTxt_password_time() {
		return txt_password_time;
	}
	public void setTxt_password_time(String txt_password_time) {
		this.txt_password_time = txt_password_time;
	}
	public String getChkIsValid() {
		return chkIsValid;
	}
	public void setChkIsValid(String chkIsValid) {
		this.chkIsValid = chkIsValid;
	}
	
	@Override
	public String toString() {
		return "{\"txtUserCode\":\"" + txtUserCode + "\",\"txtUserName\":\"" + txtUserName + "\",\"txtPassword\":\""
				+ txtPassword + "\",\"chk_resetPassword\":\"" + chk_resetPassword + "\",\"ddl_user_belong_company\":\""
				+ ddl_user_belong_company + "\",\"dpdPermission\":\"" + dpdPermission
				+ "\",\"ddl_complaint_permission\":\"" + ddl_complaint_permission + "\",\"txt_email\":\"" + txt_email
				+ "\",\"txt_mobile\":\"" + txt_mobile + "\",\"ddl_password_type\":\"" + ddl_password_type
				+ "\",\"txt_password_time\":\"" + txt_password_time + "\",\"chkIsValid\":\"" + chkIsValid + "\"}  ";
	}

	public String execute() throws Exception
	{
		String useridstr=request.getParameter("hidden_id");
		String checkoutuser="select * from sys_user as su left join company as co on su.belong_company_gguid=co.gguid where id=";
		User user=new User();
		int userid=0;
		if(useridstr!=null){
			userid=Integer.parseInt(useridstr);
			checkoutuser+=userid;
		}else{
			return SUCCESS;
		}
		try(
			Connection con=JdbcUtil.getConnection();
			Statement pstmt=con.createStatement();
			ResultSet rs=pstmt.executeQuery(checkoutuser);
		){
			if(rs.next()){
				user=user.getEntry(rs);
			}
		}
		user.setUsercode(txtUserCode);
		user.setUsername(txtUserName);
		
		if("on".equals(chk_resetPassword)){
			CryptoTools ct=new CryptoTools();
			String password_des=ct.encode(txtPassword);
			user.setPassword(password_des);
		}
		user.setBelong_company_gguid(ddl_user_belong_company);
		user.setPermission(dpdPermission);
		user.setComplaint_permission(ddl_complaint_permission);
		user.setEmail(txt_email);
		user.setMobile(txt_mobile);
		user.setPassword_type(ddl_password_type);
		if(txt_password_time!=null&&!"".equals(txt_password_time)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			Date date=sdf.parse(txt_password_time);
			user.setPassword_time(date);
		}
		if("on".equals(chkIsValid)){
			user.setIsvalid("Y");
		}else{
			user.setIsvalid("N");
		}
		EditItemsFromDBImpl eifdb = new EditItemsFromDBImpl();
		eifdb.updateUserInfo(user);
		return SUCCESS;
	}
	public String insertUserInfoIntoDB() throws Exception
	{
		User user=new User();
		user.setUsercode(txtUserCode);
		user.setUsername(txtUserName);
		CryptoTools ct=new CryptoTools();
		String password_des=ct.encode(txtPassword);
		user.setPassword(password_des);
		user.setBelong_company_gguid(ddl_user_belong_company);
		user.setPermission(dpdPermission);
		user.setComplaint_permission(ddl_complaint_permission);
		user.setEmail(txt_email);
		user.setMobile(txt_mobile);
		user.setPassword_type(ddl_password_type);
		if(txt_password_time!=null&&!"".equals(txt_password_time)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			Date date=sdf.parse(txt_password_time);
			user.setPassword_time(date);
		}else{
			user.setPassword_time(null);
		}
		if("on".equals(chkIsValid)){
			user.setIsvalid("Y");
		}else{
			user.setIsvalid("N");
		}
		
		user.setUser_type(null);
		EditItemsFromDBImpl eifdb = new EditItemsFromDBImpl();
		eifdb.insertUserInfo(user);
		return SUCCESS;
	}
	
}
