package com.lynuc.bean;

import java.io.Serializable;
import java.sql.ResultSet;

public class TableProducts implements Serializable{
	
	private static final long serialVersionUID = 5697628542281388036L;
	private String gguid;
	private String product_name;
	private String website_name;
	private String make_company;
	private String master_contract;
	private String master_email;
	private String mail_server;
	private String SMTPServerUser;
	private String SMTPServerPassword;
	private String if_has_sms;
	private String ftp_server_name;
	private String ftp_port_number;
	private String ftp_user_name;
	private String ftp_user_password;
	private String menuid;
	private String seed_password_set_user;
	private String pwd_checker_accounts;
	private String admin_accounts;
	private String admin_name;
	private String remark;
	private String pay_edit_accounts;
	private String version_checker_accounts;
	
	public TableProducts() {}
	
	public TableProducts(String gguid, String product_name, String website_name, String make_company,
			String master_contract, String master_email, String mail_server, String sMTPServerUser,
			String sMTPServerPassword, String if_has_sms, String ftp_server_name, String ftp_port_number,
			String ftp_user_name, String ftp_user_password, String menuid, String seed_password_set_user,
			String pwd_checker_accounts, String admin_accounts, String admin_name, String remark,
			String pay_edit_accounts, String version_checker_accounts) {
		super();
		this.gguid = gguid;
		this.product_name = product_name;
		this.website_name = website_name;
		this.make_company = make_company;
		this.master_contract = master_contract;
		this.master_email = master_email;
		this.mail_server = mail_server;
		SMTPServerUser = sMTPServerUser;
		SMTPServerPassword = sMTPServerPassword;
		this.if_has_sms = if_has_sms;
		this.ftp_server_name = ftp_server_name;
		this.ftp_port_number = ftp_port_number;
		this.ftp_user_name = ftp_user_name;
		this.ftp_user_password = ftp_user_password;
		this.menuid = menuid;
		this.seed_password_set_user = seed_password_set_user;
		this.pwd_checker_accounts = pwd_checker_accounts;
		this.admin_accounts = admin_accounts;
		this.admin_name = admin_name;
		this.remark = remark;
		this.pay_edit_accounts = pay_edit_accounts;
		this.version_checker_accounts = version_checker_accounts;
	}

	public String getGguid() {
		return gguid;
	}

	public void setGguid(String gguid) {
		this.gguid = gguid;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getWebsite_name() {
		return website_name;
	}

	public void setWebsite_name(String website_name) {
		this.website_name = website_name;
	}

	public String getMake_company() {
		return make_company;
	}

	public void setMake_company(String make_company) {
		this.make_company = make_company;
	}

	public String getMaster_contract() {
		return master_contract;
	}

	public void setMaster_contract(String master_contract) {
		this.master_contract = master_contract;
	}

	public String getMaster_email() {
		return master_email;
	}

	public void setMaster_email(String master_email) {
		this.master_email = master_email;
	}

	public String getMail_server() {
		return mail_server;
	}

	public void setMail_server(String mail_server) {
		this.mail_server = mail_server;
	}

	public String getSMTPServerUser() {
		return SMTPServerUser;
	}

	public void setSMTPServerUser(String sMTPServerUser) {
		SMTPServerUser = sMTPServerUser;
	}

	public String getSMTPServerPassword() {
		return SMTPServerPassword;
	}

	public void setSMTPServerPassword(String sMTPServerPassword) {
		SMTPServerPassword = sMTPServerPassword;
	}

	public String getIf_has_sms() {
		return if_has_sms;
	}

	public void setIf_has_sms(String if_has_sms) {
		this.if_has_sms = if_has_sms;
	}

	public String getFtp_server_name() {
		return ftp_server_name;
	}

	public void setFtp_server_name(String ftp_server_name) {
		this.ftp_server_name = ftp_server_name;
	}

	public String getFtp_port_number() {
		return ftp_port_number;
	}

	public void setFtp_port_number(String ftp_port_number) {
		this.ftp_port_number = ftp_port_number;
	}

	public String getFtp_user_name() {
		return ftp_user_name;
	}

	public void setFtp_user_name(String ftp_user_name) {
		this.ftp_user_name = ftp_user_name;
	}

	public String getFtp_user_password() {
		return ftp_user_password;
	}

	public void setFtp_user_password(String ftp_user_password) {
		this.ftp_user_password = ftp_user_password;
	}

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getSeed_password_set_user() {
		return seed_password_set_user;
	}

	public void setSeed_password_set_user(String seed_password_set_user) {
		this.seed_password_set_user = seed_password_set_user;
	}

	public String getPwd_checker_accounts() {
		return pwd_checker_accounts;
	}

	public void setPwd_checker_accounts(String pwd_checker_accounts) {
		this.pwd_checker_accounts = pwd_checker_accounts;
	}

	public String getAdmin_accounts() {
		return admin_accounts;
	}

	public void setAdmin_accounts(String admin_accounts) {
		this.admin_accounts = admin_accounts;
	}

	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPay_edit_accounts() {
		return pay_edit_accounts;
	}

	public void setPay_edit_accounts(String pay_edit_accounts) {
		this.pay_edit_accounts = pay_edit_accounts;
	}

	public String getVersion_checker_accounts() {
		return version_checker_accounts;
	}

	public void setVersion_checker_accounts(String version_checker_accounts) {
		this.version_checker_accounts = version_checker_accounts;
	}

	public TableProducts getEntry(ResultSet rs) throws Exception {
		this.setGguid(rs.getString("gguid"));
		this.setProduct_name(rs.getString("product_name"));
		this.setWebsite_name(rs.getString("website_name"));
		this.setMake_company(rs.getString("make_company"));
		this.setMaster_contract(rs.getString("master_contract"));
		this.setMaster_email(rs.getString("master_email"));
		this.setMail_server(rs.getString("mail_server"));
		this.setSMTPServerUser(rs.getString("SMTPServerUser"));
		this.setSMTPServerPassword(rs.getString("SMTPServerPassword"));
		this.setIf_has_sms(rs.getString("if_has_sms"));
		this.setFtp_server_name(rs.getString("ftp_server_name"));
		this.setFtp_port_number(rs.getString("ftp_port_number"));
		this.setFtp_user_name(rs.getString("ftp_user_name"));
		this.setFtp_user_password(rs.getString("ftp_user_password"));
		this.setMenuid(rs.getString("menuid"));
		this.setSeed_password_set_user(rs.getString("seed_password_set_user"));
		this.setPwd_checker_accounts(rs.getString("pwd_checker_accounts"));
		this.setAdmin_accounts(rs.getString("admin_accounts"));
		this.setAdmin_name(rs.getString("admin_name"));
		this.setRemark(rs.getString("remark"));
		this.setPay_edit_accounts(rs.getString("pay_edit_accounts"));
		this.setVersion_checker_accounts(rs.getString("version_checker_accounts"));
		return this;
	}
}
