package com.lynuc.PO;

public class VersionPO {
	private String gguid;
	private String version_name;
	private String is_soft;
	private String is_publish;
	public VersionPO() {}
	public VersionPO(String gguid, String version_name, String is_soft, String is_publish) {
		super();
		this.gguid = gguid;
		this.version_name = version_name;
		this.is_soft = is_soft;
		this.is_publish = is_publish;
	}
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
	@Override
	public String toString() {
		return "{\"gguid\":\"" + gguid + "\",\"version_name\":\"" + version_name + "\",\"is_soft\":\"" + is_soft
				+ "\",\"is_publish\":\"" + is_publish + "\"}  ";
	}
	
}