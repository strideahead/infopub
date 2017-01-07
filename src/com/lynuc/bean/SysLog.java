package com.lynuc.bean;

public class SysLog {
	private String gguid;
	private String log_user;
	private String log_memo;
	private String log_time;
	private String log_type;
	private String uploadpag_gguid;
	
	public SysLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SysLog(String gguid, String log_user, String log_memo, String log_time, String log_type,
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
	public String getLog_time() {
		return log_time;
	}
	public void setLog_time(String log_time) {
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
