package com.lynuc.PO;

import java.sql.ResultSet;

public class CompanyPO{
	private String gguid;
	private String company_name;
	private String address;
	private String zip;
	private String contacter;
	private String tel;
	private String fax;
	private String company_type;
	private String memo;
	private Integer version_checker_id;
	private Integer company_id;
	public CompanyPO() {}
	public CompanyPO(String gguid, String company_name, String address, String zip, String contacter, String tel,
		String fax, String company_type, String memo, Integer version_checker_id, Integer company_id) {
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

	public Integer getVersion_checker_id() {
		return version_checker_id;
	}

	public void setVersion_checker_id(Integer version_checker_id) {
		this.version_checker_id = version_checker_id;
	}

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	@Override
	public String toString() {
		return "{\"gguid\":\"" + gguid + "\",\"company_name\":\"" + company_name + "\",\"address\":\"" + address
				+ "\",\"zip\":\"" + zip + "\",\"contacter\":\"" + contacter + "\",\"tel\":\"" + tel + "\",\"fax\":\""
				+ fax + "\",\"company_type\":\"" + company_type + "\",\"memo\":\"" + memo
				+ "\",\"version_checker_id\":\"" + version_checker_id + "\",\"company_id\":\"" + company_id + "\"}  ";
	}

	public CompanyPO copyAnother(CompanyPO cpnew)
	{
		this.setCompany_name(cpnew.getCompany_name());
		this.setAddress(cpnew.getAddress());
		this.setZip(cpnew.getZip());
		this.setContacter(cpnew.getContacter());
		this.setTel(cpnew.getTel());
		this.setFax(cpnew.getFax());
		this.setCompany_type(cpnew.getCompany_name());
		this.setMemo(cpnew.getMemo());
		this.setVersion_checker_id(cpnew.getVersion_checker_id());
		this.setCompany_id(cpnew.getCompany_id());
		return this;
	}
	public CompanyPO getEntry(ResultSet rs) throws Exception {
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
