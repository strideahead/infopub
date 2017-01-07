package com.lynuc.bean;

public class SmsTodo {
	private String gguid;
	private String mobile;
	private String content;
	private String is_sent;
	private String btime;
	public SmsTodo() {}
	public SmsTodo(String gguid, String mobile, String content, String is_sent, String btime) {
		super();
		this.gguid = gguid;
		this.mobile = mobile;
		this.content = content;
		this.is_sent = is_sent;
		this.btime = btime;
	}
	public String getGguid() {
		return gguid;
	}
	public void setGguid(String gguid) {
		this.gguid = gguid;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIs_sent() {
		return is_sent;
	}
	public void setIs_sent(String is_sent) {
		this.is_sent = is_sent;
	}
	public String getBtime() {
		return btime;
	}
	public void setBtime(String btime) {
		this.btime = btime;
	}
	@Override
	public String toString() {
		return "{\"gguid\":\"" + gguid + "\",\"mobile\":\"" + mobile + "\",\"content\":\"" + content
				+ "\",\"is_sent\":\"" + is_sent + "\",\"btime\":\"" + btime + "\"}  ";
	}
	
	
}
