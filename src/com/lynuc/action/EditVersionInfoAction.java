package com.lynuc.action;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.UUID;

import com.lynuc.PO.VersionPO;
import com.lynuc.bean.BaseAction;
import com.lynuc.implDao.EditItemsFromDBImpl;

public class EditVersionInfoAction extends BaseAction implements Serializable{

	private static final long serialVersionUID = -7875306694719447383L;
	private String txtVersionName;
	private String chkIssoft;
	private String chkIspublish;
	
	public EditVersionInfoAction() {}
	
	public EditVersionInfoAction(String txtVersionName, String chkIssoft, String chkIspublish) {
		super();
		this.txtVersionName = txtVersionName;
		this.chkIssoft = chkIssoft;
		this.chkIspublish = chkIspublish;
	}

	public String getTxtVersionName() {
		return txtVersionName;
	}

	public void setTxtVersionName(String txtVersionName) {
		this.txtVersionName = txtVersionName;
	}

	public String getChkIssoft() {
		return chkIssoft;
	}

	public void setChkIssoft(String chkIssoft) {
		this.chkIssoft = chkIssoft;
	}

	public String getChkIspublish() {
		return chkIspublish;
	}

	public void setChkIspublish(String chkIspublish) {
		this.chkIspublish = chkIspublish;
	}

	@Override
	public String toString() {
		return "{\"txtVersionName\":\"" + txtVersionName + "\",\"chkIssoft\":\"" + chkIssoft + "\",\"chkIspublish\":\""
				+ chkIspublish + "\"}  ";
	}

	public String execute() throws ParseException, SQLException
	{
		String versiongguid=request.getParameter("hidden_gguid");
		VersionPO version=new VersionPO();
		version.setVersion_name(txtVersionName);
		if("on".equals(chkIssoft)){
			version.setIs_soft("Soft");
		}else{
			version.setIs_soft("Hard");
		}
		if("on".equals(chkIspublish)){
			version.setIs_publish("Yes");
		}else{
			version.setIs_publish("No");
		}
		version.setGguid(versiongguid);
		EditItemsFromDBImpl eifdb = new EditItemsFromDBImpl();
		eifdb.updateVersionInfo(version);
		return SUCCESS;
	}
	public String insertVersionInfoIntoDB() throws ParseException, SQLException
	{
		VersionPO version=new VersionPO();
		version.setVersion_name(txtVersionName);
		
		if("on".equals(chkIssoft)){
			version.setIs_soft("Soft");
		}else{
			version.setIs_soft("Hard");
		}
		if("on".equals(chkIspublish)){
			version.setIs_publish("Yes");
		}else{
			version.setIs_publish("No");
		}
		//version.setIs_soft(chkIssoft);
		//version.setIs_publish(chkIspublish);
		
		UUID uuid=UUID.randomUUID();
		String gguid=uuid.toString().toLowerCase();
		
		version.setGguid(gguid);
		EditItemsFromDBImpl eifdb = new EditItemsFromDBImpl();
		eifdb.insertVersionInfo(version);
		return SUCCESS;
	}
	
}
