package com.lynuc.bean;

public class Version {
	private String gguid;
	private String version_name;
	private String is_soft;
	public String getGguid() {
		return gguid;
	}
	public void setGguid(String gguid) {
		this.gguid = gguid;
	}
	public String getVersion_name() {
		return version_name;
	}
	public void setVersion_name(String version_name) {
		this.version_name = version_name;
	}
	public String getIs_soft() {
		return is_soft;
	}
	public void setIs_soft(String is_soft) {
		this.is_soft = is_soft;
	}
	public String getIs_publish() {
		return is_publish;
	}
	public void setIs_publish(String is_publish) {
		this.is_publish = is_publish;
	}
	private String is_publish;
}
