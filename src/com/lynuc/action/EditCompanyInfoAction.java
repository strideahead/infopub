package com.lynuc.action;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.UUID;

import com.lynuc.bean.BaseAction;
import com.lynuc.PO.CompanyPO;
import com.lynuc.implDao.EditItemsFromDBImpl;

public class EditCompanyInfoAction extends BaseAction implements Serializable{

	private static final long serialVersionUID = -5402725740337944693L;
	private String txtCompanyName;
	private int ddl_version_checker;
	private String txtAddress;
	private String txtZip;
	private String txtContacter;
	private String txtTel;
	private String txtFax;
	private String ddlCompanyType;
	private String txtMemo;
	private String txtCompanyId;
	
	public EditCompanyInfoAction() {}

	public EditCompanyInfoAction(String txtCompanyName, Integer ddl_version_checker, String txtAddress, String txtZip,
			String txtContacter, String txtTel, String txtFax, String ddlCompanyType, String txtMemo,
			String txtCompanyId) {
		super();
		this.txtCompanyName = txtCompanyName;
		this.ddl_version_checker = ddl_version_checker;
		this.txtAddress = txtAddress;
		this.txtZip = txtZip;
		this.txtContacter = txtContacter;
		this.txtTel = txtTel;
		this.txtFax = txtFax;
		this.ddlCompanyType = ddlCompanyType;
		this.txtMemo = txtMemo;
		this.txtCompanyId = txtCompanyId;
	}

	public String getTxtCompanyName() {
		return txtCompanyName;
	}

	public void setTxtCompanyName(String txtCompanyName) {
		this.txtCompanyName = txtCompanyName;
	}

	public Integer getDdl_version_checker() {
		return ddl_version_checker;
	}

	public void setDdl_version_checker(Integer ddl_version_checker) {
		this.ddl_version_checker = ddl_version_checker;
	}

	public String getTxtAddress() {
		return txtAddress;
	}

	public void setTxtAddress(String txtAddress) {
		this.txtAddress = txtAddress;
	}

	public String getTxtZip() {
		return txtZip;
	}

	public void setTxtZip(String txtZip) {
		this.txtZip = txtZip;
	}

	public String getTxtContacter() {
		return txtContacter;
	}

	public void setTxtContacter(String txtContacter) {
		this.txtContacter = txtContacter;
	}

	public String getTxtTel() {
		return txtTel;
	}

	public void setTxtTel(String txtTel) {
		this.txtTel = txtTel;
	}

	public String getTxtFax() {
		return txtFax;
	}

	public void setTxtFax(String txtFax) {
		this.txtFax = txtFax;
	}

	public String getDdlCompanyType() {
		return ddlCompanyType;
	}

	public void setDdlCompanyType(String ddlCompanyType) {
		this.ddlCompanyType = ddlCompanyType;
	}
	
	public String getTxtMemo() {
		return txtMemo;
	}

	public void setTxtMemo(String txtMemo) {
		this.txtMemo = txtMemo;
	}

	public String getTxtCompanyId() {
		return txtCompanyId;
	}

	public void setTxtCompanyId(String txtCompanyId) {
		this.txtCompanyId = txtCompanyId;
	}

	public String execute() throws ParseException, SQLException
	{
		String copanygguid=request.getParameter("hidden_gguid");
		CompanyPO cp=new CompanyPO();
		cp.setGguid(copanygguid.trim());
		cp.setCompany_name(txtCompanyName);
		cp.setAddress(txtAddress);
		cp.setZip(txtZip);
		cp.setContacter(txtContacter);
		cp.setTel(txtTel);
		cp.setFax(txtFax);
		cp.setCompany_type(ddlCompanyType);
		cp.setMemo(txtMemo);
		cp.setVersion_checker_id(ddl_version_checker);
		cp.setCompany_id(null);
		EditItemsFromDBImpl eifdb = new EditItemsFromDBImpl();
		eifdb.updateCompanyInfo(cp);
		return SUCCESS;
		
	}
	public String insertCompanyInfoIntoDB() throws ParseException, SQLException
	{
		CompanyPO cp=new CompanyPO();
		cp.setCompany_name(txtCompanyName);
		cp.setAddress(txtAddress);
		cp.setZip(txtZip);
		cp.setContacter(txtContacter);
		cp.setTel(txtTel);
		cp.setFax(txtFax);
		cp.setCompany_type(ddlCompanyType);
		cp.setMemo(txtMemo);
		cp.setVersion_checker_id(ddl_version_checker);
		cp.setCompany_id(null);
		
		UUID uuid=UUID.randomUUID();
		String gguid=uuid.toString().toLowerCase();
		
		cp.setGguid(gguid);
		EditItemsFromDBImpl eifdb=new EditItemsFromDBImpl();
		eifdb.insertCompanyInfo(cp);
		return SUCCESS;
	}
	
}
