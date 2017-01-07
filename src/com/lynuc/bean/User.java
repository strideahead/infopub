package com.lynuc.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Map;

/*
 * 用户信息Bean，比数据库多了个company_name，关联外键表company
 */
public class User implements Serializable {

	private static final long serialVersionUID = -3034282640724717666L;
	private int id;
	private String usercode;
	private String username;
	private String password;
	private String permission;
	private String isvalid;
	private String complaint_permission;
	private String email;
	private String mobile;
	private String password_type;
	private Date password_time;
	private String user_type;
	private String belong_company_gguid;
	private String company_name;
	private String company_type;
	private String company_gguid;
	public User() {}

	public User(int id, String usercode, String username, String password, String permission, String isvalid,
			String complaint_permission, String email, String mobile, String password_type, Date password_time,
			String user_type, String belong_company_gguid, String company_name, String company_type,
			String company_gguid) {
		super();
		this.id = id;
		this.usercode = usercode;
		this.username = username;
		this.password = password;
		this.permission = permission;
		this.isvalid = isvalid;
		this.complaint_permission = complaint_permission;
		this.email = email;
		this.mobile = mobile;
		this.password_type = password_type;
		this.password_time = password_time;
		this.user_type = user_type;
		this.belong_company_gguid = belong_company_gguid;
		this.company_name = company_name;
		this.company_type = company_type;
		this.company_gguid = company_gguid;
	}

	public User(Map<String, Object> map){
		this.id = (int)map.get("id");
		this.usercode = (String)map.get("usercode");;
		this.username = (String)map.get("username");
		this.password = (String)map.get("password");
		this.permission = (String)map.get("permission");
		this.isvalid =  (String)map.get("isvalid");
		this.complaint_permission = (String)map.get("complaint_permission");
		this.email = (String)map.get("email");
		this.mobile = (String)map.get("mobile");
		this.password_type = (String)map.get("password_type");
		this.password_time = (Date)map.get("apply_no");
		this.user_type = (String)map.get("apply_no");
		this.belong_company_gguid = (String)map.get("apply_no");
		this.company_name = (String)map.get("company_name");
		this.company_type = (String)map.get("company_type");
		this.company_gguid = (String)map.get("company_gguid");
	}
	public void convertUserToMap(Map<String,Object>  map)
	{
		map.put("id",this.getId());
		map.put("usercode",this.getUsercode());
		map.put("username",this.getUsername());
		map.put("permisson",this.getPermission());
		map.put("isvalid",this.getIsvalid());
		map.put("complaint_permission",this.getComplaint_permission());
		map.put("email",this.getEmail());
		map.put("mobile",this.getMobile());
		map.put("password_type",this.getPassword_type());
		map.put("password_time",this.getPassword_time());
		map.put("user_type",this.getUser_type());
		map.put("belong_company_gguid",this.getBelong_company_gguid());
		map.put("company_name",this.getCompany_name());
		map.put("company_type",this.getCompany_type());
		map.put("company_gguid",this.getCompany_gguid());
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public String isIsvalid() {
		return isvalid;
	}
	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}
	public String getComplaint_permission() {
		return complaint_permission;
	}
	public void setComplaint_permission(String complaint_permission) {
		this.complaint_permission = complaint_permission;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword_type() {
		return password_type;
	}
	public void setPassword_type(String password_type) {
		this.password_type = password_type;
	}
	public Date getPassword_time() {
		return password_time;
	}
	public void setPassword_time(Date password_time) {
		this.password_time = password_time;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	public String getBelong_company_gguid() {
		return belong_company_gguid;
	}
	public void setBelong_company_gguid(String belong_company_gguid) {
		this.belong_company_gguid = belong_company_gguid;
	}
	
	public String getIsvalid() {
		return isvalid;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	
	public String getCompany_type() {
		return company_type;
	}

	public void setCompany_type(String company_type) {
		this.company_type = company_type;
	}

	public String getCompany_gguid() {
		return company_gguid;
	}

	public void setCompany_gguid(String company_gguid) {
		this.company_gguid = company_gguid;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\",\"usercode\":\"" + usercode + "\",\"username\":\"" + username
				+ "\",\"password\":\"" + password + "\",\"permission\":\"" + permission + "\",\"isvalid\":\"" + isvalid
				+ "\",\"complaint_permission\":\"" + complaint_permission + "\",\"email\":\"" + email
				+ "\",\"mobile\":\"" + mobile + "\",\"password_type\":\"" + password_type + "\",\"password_time\":\""
				+ password_time + "\",\"user_type\":\"" + user_type + "\",\"belong_company_gguid\":\""
				+ belong_company_gguid + "\",\"company_name\":\"" + company_name + "\",\"company_type\":\""
				+ company_type + "\",\"company_gguid\":\"" + company_gguid + "\"}  ";
	}

	public User getEntry(ResultSet rs) throws Exception {
		this.setId(rs.getInt("id"));
		this.setUsercode(rs.getString("usercode"));
		this.setUsername(rs.getString("username"));
		this.setPassword(rs.getString("password"));
		this.setPermission(rs.getString("permission"));
		this.setIsvalid(rs.getString("isvalid"));
		this.setComplaint_permission(rs.getString("complaint_permission"));

		this.setEmail(rs.getString("email"));
		this.setMobile(rs.getString("mobile"));
		this.setPassword_type(rs.getString("password_type"));
		this.setPassword_time(rs.getDate("password_time"));
		this.setUser_type(rs.getString("user_type"));
		this.setBelong_company_gguid(rs.getString("belong_company_gguid"));
		this.setCompany_name(rs.getString("company_name"));
		this.setCompany_type(rs.getString("company_type"));
		this.setCompany_gguid(rs.getString("belong_company_gguid"));
		return this;
	}
	
}