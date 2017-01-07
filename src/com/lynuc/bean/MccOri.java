package com.lynuc.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Map;

public class MccOri implements Serializable 
{
	private static final long serialVersionUID = 4321780112670911746L;
	protected String gguid;
	protected String controller_no;
	protected String card_no;
	protected String card_content;
	protected String ipc_sn;
	protected String is_old;
	protected String password_ver;
	protected String controller_ver;
	protected String ezdoctor_serial;
	
	public void convertMccOriToMap(Map<String,Object>  map)
	{
		map.put("gguid",this.getGguid());
		map.put("controller_no",this.getController_no());
		map.put("card_no",this.getCard_no());
		map.put("card_content",this.getCard_content());
		map.put("ipc_sn",this.getIpc_sn());
		map.put("is_old",this.getIs_old());
		map.put("password_ver",this.getPassword_ver());
		map.put("controller_ver",this.getController_ver());
		map.put("ezdoctor_serial",this.getEzdoctor_serial());
	}
	
	public MccOri() {}

	public MccOri(String gguid, String controller_no, String card_no, String card_content, String ipc_sn, String is_old,
			String password_ver, String controller_ver, String ezdoctor_serial) {
		super();
		this.gguid = gguid;
		this.controller_no = controller_no;
		this.card_no = card_no;
		this.card_content = card_content;
		this.ipc_sn = ipc_sn;
		this.is_old = is_old;
		this.password_ver = password_ver;
		this.controller_ver = controller_ver;
		this.ezdoctor_serial = ezdoctor_serial;
	}

	public String getGguid() {
		return gguid;
	}

	public void setGguid(String gguid) {
		this.gguid = gguid;
	}

	public String getController_no() {
		return controller_no;
	}

	public void setController_no(String controller_no) {
		this.controller_no = controller_no;
	}

	public String getCard_no() {
		return card_no;
	}

	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}

	public String getCard_content() {
		return card_content;
	}

	public void setCard_content(String card_content) {
		this.card_content = card_content;
	}

	public String getIpc_sn() {
		return ipc_sn;
	}

	public void setIpc_sn(String ipc_sn) {
		this.ipc_sn = ipc_sn;
	}

	public String getIs_old() {
		return is_old;
	}

	public void setIs_old(String is_old) {
		this.is_old = is_old;
	}

	public String getPassword_ver() {
		return password_ver;
	}

	public void setPassword_ver(String password_ver) {
		this.password_ver = password_ver;
	}

	public String getController_ver() {
		return controller_ver;
	}

	public void setController_ver(String controller_ver) {
		this.controller_ver = controller_ver;
	}

	public String getEzdoctor_serial() {
		return ezdoctor_serial;
	}

	public void setEzdoctor_serial(String ezdoctor_serial) {
		this.ezdoctor_serial = ezdoctor_serial;
	}

	@Override
	public String toString() {
		return "{\"gguid\":\"" + gguid + "\",\"controller_no\":\"" + controller_no + "\",\"card_no\":\"" + card_no
				+ "\",\"card_content\":\"" + card_content + "\",\"ipc_sn\":\"" + ipc_sn + "\",\"is_old\":\"" + is_old
				+ "\",\"password_ver\":\"" + password_ver + "\",\"controller_ver\":\"" + controller_ver
				+ "\",\"ezdoctor_serial\":\"" + ezdoctor_serial + "\"}  ";
	}

	public MccOri getEntry(ResultSet rs) throws Exception {
		this.setGguid(rs.getString("gguid"));
		this.setController_no(rs.getString("controller_no"));
		this.setCard_no(rs.getString("card_no"));
		this.setCard_content(rs.getString("card_content"));
		this.setIpc_sn(rs.getString("ipc_sn"));
		this.setIs_old(rs.getString("is_old"));
		this.setPassword_ver(rs.getString("password_ver"));
		this.setController_ver(rs.getString("controller_ver"));
		this.setEzdoctor_serial(rs.getString("ezdoctor_serial"));
		return this;
	}

}
