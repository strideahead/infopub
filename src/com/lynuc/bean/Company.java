package com.lynuc.bean;

import java.sql.ResultSet;
import java.util.Map;

/*
 * ¹«Ë¾Bean
 */
public class Company implements java.io.Serializable{

	private static final long serialVersionUID = -5888615597660044607L;
	private String gguid;
	private String company_name;
	private String address;
	private String zip;
	private String contacter;
	private String tel;
	private String fax;
	private String company_type;
	private String memo;
	private int version_checker_id;
	private int company_id;
	
	public Company() {}

	public Company(String gguid, String company_name, String address, String zip, String contacter, String tel,
			String fax, String company_type, String memo, int version_checker_id, int company_id) {
		super();
		this.gguid = gguid;
		this.company_name = company_name;
		this.address = address;
		this.zip = zip;
		this.contacter = contacter;
		this.tel = tel;
		this.fax = fax;
		this.company_type = company_type;
		this.memo = memo;
		this.version_checker_id = version_checker_id;
		this.company_id = company_id;
	}

	public String getGguid() {
		return gguid;
	}

	public void setGguid(String gguid) {
		this.gguid = gguid;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getContacter() {
		return contacter;
	}

	public void setContacter(String contacter) {
		this.contacter = contacter;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCompany_type() {
		return company_type;
	}

	public void setCompany_type(String company_type) {
		this.company_type = company_type;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getVersion_checker_id() {
		return version_checker_id;
	}

	public void setVersion_checker_id(int version_checker_id) {
		this.version_checker_id = version_checker_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public Company getEntry(ResultSet rs) throws Exception {
		this.setGguid(rs.getString("gguid"));
		this.setCompany_name(rs.getString("company_name"));
		this.setAddress(rs.getString("address"));
		this.setZip(rs.getString("zip"));
		this.setContacter(rs.getString("contacter"));
		this.setTel(rs.getString("tel"));
		this.setFax(rs.getString("fax"));
		this.setCompany_type(rs.getString("company_type"));
		this.setMemo(rs.getString("memo"));
		this.setVersion_checker_id(rs.getInt("version_checker_id"));
		this.setCompany_id(rs.getInt("company_id"));
		return this;
	}
	
}
