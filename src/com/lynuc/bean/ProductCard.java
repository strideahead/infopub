package com.lynuc.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.Map;

public class ProductCard implements Serializable{
	private static final long serialVersionUID = -8525519381197298196L;
	private String card_no;
	private String card_type;
	private String card_content;
	private String card_memo;
	private String ctime;
	private String utime;
	private String gguid;
	private String controller_no;
	private String ipc_sn;
	private String is_old;
	private String is_pay;
	private String inticket_code;
	private String outticket_code;
	private String is_reselect;
	private String card_ver;
	
	public ProductCard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductCard(String card_no, String card_type, String card_content, String card_memo, String ctime,
			String utime, String gguid, String controller_no, String ipc_sn, String is_old, String is_pay,
			String inticket_code, String outticket_code, String is_reselect, String card_ver) {
		super();
		this.card_no = card_no;
		this.card_type = card_type;
		this.card_content = card_content;
		this.card_memo = card_memo;
		this.ctime = ctime;
		this.utime = utime;
		this.gguid = gguid;
		this.controller_no = controller_no;
		this.ipc_sn = ipc_sn;
		this.is_old = is_old;
		this.is_pay = is_pay;
		this.inticket_code = inticket_code;
		this.outticket_code = outticket_code;
		this.is_reselect = is_reselect;
		this.card_ver = card_ver;
	}
	public ProductCard(Map<String, Object> map) throws ParseException{
		this.card_no = (String)map.get("card_no");
		this.card_type = (String)map.get("card_type");;
		this.card_content = (String)map.get("card_content");;
		this.card_memo = (String)map.get("card_memo");;
		this.ctime = (String)map.get("ctime");;
		this.utime = (String)map.get("utime");;
		this.gguid = (String)map.get("gguid");;
		this.controller_no = (String)map.get("controller_no");;
		this.ipc_sn = (String)map.get("ipc_sn");;
		this.is_old = (String)map.get("is_old");;
		this.is_pay = (String)map.get("is_pay");;
		this.inticket_code = (String)map.get("inticket_code");;
		this.outticket_code = (String)map.get("outticket_code");;
		this.is_reselect = (String)map.get("is_reselect");;
		this.card_ver = (String)map.get("card_ver");;
	}
	public String getCard_no() {
		return card_no;
	}
	public String getCard_type() {
		return card_type;
	}
	public String getCard_content() {
		return card_content;
	}
	public String getCard_memo() {
		return card_memo;
	}
	public String getCtime() {
		return ctime;
	}
	public String getUtime() {
		return utime;
	}
	public String getGguid() {
		return gguid;
	}
	public String getController_no() {
		return controller_no;
	}
	public String getIpc_sn() {
		return ipc_sn;
	}
	public String getIs_old() {
		return is_old;
	}
	public String getIs_pay() {
		return is_pay;
	}
	public String getInticket_code() {
		return inticket_code;
	}
	public String getOutticket_code() {
		return outticket_code;
	}
	public String getIs_reselect() {
		return is_reselect;
	}
	public String getCard_ver() {
		return card_ver;
	}
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}
	public void setCard_content(String card_content) {
		this.card_content = card_content;
	}
	public void setCard_memo(String card_memo) {
		this.card_memo = card_memo;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	public void setUtime(String utime) {
		this.utime = utime;
	}
	public void setGguid(String gguid) {
		this.gguid = gguid;
	}
	public void setController_no(String controller_no) {
		this.controller_no = controller_no;
	}
	public void setIpc_sn(String ipc_sn) {
		this.ipc_sn = ipc_sn;
	}
	public void setIs_old(String is_old) {
		this.is_old = is_old;
	}
	public void setIs_pay(String is_pay) {
		this.is_pay = is_pay;
	}
	public void setInticket_code(String inticket_code) {
		this.inticket_code = inticket_code;
	}
	public void setOutticket_code(String outticket_code) {
		this.outticket_code = outticket_code;
	}
	public void setIs_reselect(String is_reselect) {
		this.is_reselect = is_reselect;
	}
	public void setCard_ver(String card_ver) {
		this.card_ver = card_ver;
	}
	@Override
	public String toString() {
		return "{\"card_no\":\"" + card_no + "\",\"card_type\":\"" + card_type + "\",\"card_content\":\"" + card_content
				+ "\",\"card_memo\":\"" + card_memo + "\",\"ctime\":\"" + ctime + "\",\"utime\":\"" + utime
				+ "\",\"gguid\":\"" + gguid + "\",\"controller_no\":\"" + controller_no + "\",\"ipc_sn\":\"" + ipc_sn
				+ "\",\"is_old\":\"" + is_old + "\",\"is_pay\":\"" + is_pay + "\",\"inticket_code\":\"" + inticket_code
				+ "\",\"outticket_code\":\"" + outticket_code + "\",\"is_reselect\":\"" + is_reselect
				+ "\",\"card_ver\":\"" + card_ver + "\"} ";
	}
	public ProductCard getEntry(ResultSet rs) throws Exception {
		this.setCard_no(rs.getString("card_no"));
		this.setCard_type(rs.getString("card_type"));
		this.setCard_content(rs.getString("card_content"));
		this.setCard_memo(rs.getString("card_memo"));
		this.setCtime(rs.getString("ctime"));
		this.setUtime(rs.getString("utime"));
		this.setGguid(rs.getString("gguid"));
		this.setController_no(rs.getString("controller_no"));
		this.setIpc_sn(rs.getString("ipc_sn"));
		this.setIs_pay(rs.getString("is_pay"));
		this.setIs_old(rs.getString("is_old"));
		this.setInticket_code(rs.getString("inticket_code"));
		this.setOutticket_code(rs.getString("outticket_code"));
		this.setIs_reselect(rs.getString("is_reselect"));
		this.setCard_ver(rs.getString("card_ver"));
		return this;
	}
	
}
