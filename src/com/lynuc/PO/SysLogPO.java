package com.lynuc.PO;

import java.util.Date;

public class SysLogPO implements java.io.Serializable{

	private static final long serialVersionUID = 225865017284588271L;
	private String gguid;
	private String log_user;
	private String log_memo;
	private Date log_time;
	private String log_type;
	private String uploadpag_gguid;
	public SysLogPO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SysLogPO(String gguid, String log_user, String log_memo, Date log_time, String log_type,
			String uploadpag_gguid) {
		super();
		this.gguid = gguid;
		this.log_user = log_user;
		this.log_memo = log_memo;
		this.log_time = log_time;
		this.log_type = log_type;
		this.uploadpag_gguid = uploadpag_gguid;
	}
	public String getGguid() {
		return gguid;
	}
	public void setGguid(String gguid) {
		this.gguid = gguid;
	}
	public String getLog_user() {
		return log_user;
	}
	public void setLog_user(String log_user) {
		this.log_user = log_user;
	}
	public String getLog_memo() {
		return log_memo;
	}
	public void setLog_memo(String log_memo) {
		this.log_memo = log_memo;
	}
	public Date getLog_time() {
		return log_time;
	}
	public void setLog_time(Date log_time) {
		this.log_time = log_time;
	}
	public String getLog_type() {
		return log_type;
	}
	public void setLog_type(String log_type) {
		this.log_type = log_type;
	}
	public String getUploadpag_gguid() {
		return uploadpag_gguid;
	}
	public void setUploadpag_gguid(String uploadpag_gguid) {
		this.uploadpag_gguid = uploadpag_gguid;
	}
	@Override
	public String toString() {
		return "{\"gguid\":\"" + gguid + "\",\"log_user\":\"" + log_user + "\",\"log_memo\":\"" + log_memo
				+ "\",\"log_time\":\"" + log_time + "\",\"log_type\":\"" + log_type + "\",\"uploadpag_gguid\":\""
				+ uploadpag_gguid + "\"}  ";
	}
	
	
}
