package com.lynuc.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Map;

public class MccFromDB extends MccOri implements Serializable 
{
	private static final long serialVersionUID = 4321780112670911746L;

	protected String machine_no;
	protected String machine_type;
	protected String is_pay;
	protected String office;
	protected String cust_name;
	protected String cust_duty;
	protected String cust_tel;
	protected String mac_no;
	protected String ver;
	protected String setup_date;
	protected String hard_ver;
	protected String memo;
	protected int module_set;
	protected int filecount;
	protected String file1path;
	protected String file1desc;
	protected String file2path;
	protected String file2desc;
	protected String file3path;
	protected String file3desc;
	protected String sale_company_gguid;
	protected String cust_company_gguid;
	protected String total_type;
	protected String mac_option;
	protected String lynuc_option;
	protected String machine_dat;
	protected int input_user_id;
	protected String input_time;
	
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
	
	public MccFromDB() {}

	public MccFromDB(String machine_no, String machine_type, String is_pay, String office, String cust_name,
			String cust_duty, String cust_tel, String mac_no, String ver, String setup_date, String hard_ver,
			String memo, int module_set, int filecount, String file1path, String file1desc, String file2path,
			String file2desc, String file3path, String file3desc, String sale_company_gguid, String cust_company_gguid,
			String total_type, String mac_option, String lynuc_option, String machine_dat, int input_user_id,
			String input_time) {
		super();
		this.machine_no = machine_no;
		this.machine_type = machine_type;
		this.is_pay = is_pay;
		this.office = office;
		this.cust_name = cust_name;
		this.cust_duty = cust_duty;
		this.cust_tel = cust_tel;
		this.mac_no = mac_no;
		this.ver = ver;
		this.setup_date = setup_date;
		this.hard_ver = hard_ver;
		this.memo = memo;
		this.module_set = module_set;
		this.filecount = filecount;
		this.file1path = file1path;
		this.file1desc = file1desc;
		this.file2path = file2path;
		this.file2desc = file2desc;
		this.file3path = file3path;
		this.file3desc = file3desc;
		this.sale_company_gguid = sale_company_gguid;
		this.cust_company_gguid = cust_company_gguid;
		this.total_type = total_type;
		this.mac_option = mac_option;
		this.lynuc_option = lynuc_option;
		this.machine_dat = machine_dat;
		this.input_user_id = input_user_id;
		this.input_time = input_time;
	}

	public String getMachine_no() {
		return machine_no;
	}

	public void setMachine_no(String machine_no) {
		this.machine_no = machine_no;
	}

	public String getMachine_type() {
		return machine_type;
	}

	public void setMachine_type(String machine_type) {
		this.machine_type = machine_type;
	}

	public String getIs_pay() {
		return is_pay;
	}

	public void setIs_pay(String is_pay) {
		this.is_pay = is_pay;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getCust_duty() {
		return cust_duty;
	}

	public void setCust_duty(String cust_duty) {
		this.cust_duty = cust_duty;
	}

	public String getCust_tel() {
		return cust_tel;
	}

	public void setCust_tel(String cust_tel) {
		this.cust_tel = cust_tel;
	}

	public String getMac_no() {
		return mac_no;
	}

	public void setMac_no(String mac_no) {
		this.mac_no = mac_no;
	}

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public String getSetup_date() {
		return setup_date;
	}

	public void setSetup_date(String setup_date) {
		this.setup_date = setup_date;
	}

	public String getHard_ver() {
		return hard_ver;
	}

	public void setHard_ver(String hard_ver) {
		this.hard_ver = hard_ver;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getModule_set() {
		return module_set;
	}

	public void setModule_set(int module_set) {
		this.module_set = module_set;
	}

	public int getFilecount() {
		return filecount;
	}

	public void setFilecount(int filecount) {
		this.filecount = filecount;
	}

	public String getFile1path() {
		return file1path;
	}

	public void setFile1path(String file1path) {
		this.file1path = file1path;
	}

	public String getFile1desc() {
		return file1desc;
	}

	public void setFile1desc(String file1desc) {
		this.file1desc = file1desc;
	}

	public String getFile2path() {
		return file2path;
	}

	public void setFile2path(String file2path) {
		this.file2path = file2path;
	}

	public String getFile2desc() {
		return file2desc;
	}

	public void setFile2desc(String file2desc) {
		this.file2desc = file2desc;
	}

	public String getFile3path() {
		return file3path;
	}

	public void setFile3path(String file3path) {
		this.file3path = file3path;
	}

	public String getFile3desc() {
		return file3desc;
	}

	public void setFile3desc(String file3desc) {
		this.file3desc = file3desc;
	}

	public String getSale_company_gguid() {
		return sale_company_gguid;
	}

	public void setSale_company_gguid(String sale_company_gguid) {
		this.sale_company_gguid = sale_company_gguid;
	}

	public String getCust_company_gguid() {
		return cust_company_gguid;
	}

	public void setCust_company_gguid(String cust_company_gguid) {
		this.cust_company_gguid = cust_company_gguid;
	}

	public String getTotal_type() {
		return total_type;
	}

	public void setTotal_type(String total_type) {
		this.total_type = total_type;
	}

	public String getMac_option() {
		return mac_option;
	}

	public void setMac_option(String mac_option) {
		this.mac_option = mac_option;
	}

	public String getLynuc_option() {
		return lynuc_option;
	}

	public void setLynuc_option(String lynuc_option) {
		this.lynuc_option = lynuc_option;
	}

	public String getMachine_dat() {
		return machine_dat;
	}

	public void setMachine_dat(String machine_dat) {
		this.machine_dat = machine_dat;
	}

	public int getInput_user_id() {
		return input_user_id;
	}

	public void setInput_user_id(int input_user_id) {
		this.input_user_id = input_user_id;
	}

	public String getInput_time() {
		return input_time;
	}

	public void setInput_time(String input_time) {
		this.input_time = input_time;
	}

	public MccFromDB getEntry(ResultSet rs) throws Exception {
		super.getEntry(rs);
		this.setMachine_no(rs.getString("machine_no"));
		this.setMachine_type(rs.getString("machine_type"));
		this.setIs_pay(rs.getString("is_pay"));
		this.setOffice(rs.getString("office"));
		this.setCust_name(rs.getString("cust_name"));
		this.setMac_no(rs.getString("mac_no"));
		this.setVer(rs.getString("ver"));
		//this.setSetup_date(rs.getDate("setup_date").toString());
		this.setSetup_date(rs.getString("setup_date"));
		this.setHard_ver(rs.getString("hard_ver"));
		this.setMemo(rs.getString("memo"));
		this.setModule_set(rs.getInt("module_set"));
		this.setFilecount(rs.getInt("filecount"));
		this.setFile1path(rs.getString("file1path"));
		this.setFile1desc(rs.getString("file1desc"));
		this.setFile2path(rs.getString("file2path"));
		this.setFile2desc(rs.getString("file2desc"));
		this.setFile3path(rs.getString("file3path"));
		this.setFile3desc(rs.getString("file3path"));
		this.setSale_company_gguid(rs.getString("sale_company_gguid"));
		this.setCust_company_gguid(rs.getString("cust_company_gguid"));
		this.setTotal_type(rs.getString("total_type"));
		this.setMac_option(rs.getString("mac_option"));
		this.setLynuc_option(rs.getString("lynuc_option"));		
		this.setMachine_dat(rs.getString("machine_dat"));
		this.setEzdoctor_serial(rs.getString("ezdoctor_serial"));
		if(null !=rs.getObject("input_user_id")){
			this.setInput_user_id(rs.getInt("input_user_id"));
		}else{
			this.setInput_user_id(0);
		}
		if(null !=rs.getDate("input_time")){
			this.setInput_time(rs.getDate("input_time").toString());
		}else{
			this.setInput_time("");
		}
		
		return this;
	}

	@Override
	public String toString() {
		return "{\"gguid\":\"" + this.getGguid() + "\",\"machine_no\":\"" + machine_no + "\",\"machine_type\":\"" + machine_type
				+ "\",\"controller_no\":\"" + this.getController_no() + "\",\"card_no\":\"" + this.getCard_no() + "\",\"card_content\":\""
				+ this.getCard_content() + "\",\"ipc_sn\":\"" + this.getIpc_sn() + "\",\"is_old\":\"" + this.getIs_old() + "\",\"is_pay\":\"" + is_pay
				+ "\",\"office\":\"" + office + "\",\"cust_name\":\"" + cust_name + "\",\"cust_duty\":\"" + cust_duty
				+ "\",\"cust_tel\":\"" + cust_tel + "\",\"mac_no\":\"" + mac_no + "\",\"ver\":\"" + ver
				+ "\",\"setup_date\":\"" + setup_date + "\",\"hard_ver\":\"" + hard_ver + "\",\"memo\":\"" + memo
				+ "\",\"module_set\":\"" + module_set + "\",\"filecount\":\"" + filecount + "\",\"file1path\":\""
				+ file1path + "\",\"file1desc\":\"" + file1desc + "\",\"file2path\":\"" + file2path
				+ "\",\"file2desc\":\"" + file2desc + "\",\"file3path\":\"" + file3path + "\",\"file3desc\":\""
				+ file3desc + "\",\"sale_company_gguid\":\""+ sale_company_gguid + "\",\"cust_company_gguid\":\""
				+ cust_company_gguid + "\",\"total_type\":\"" + total_type + "\",\"mac_option\":\"" + mac_option
				+ "\",\"lynuc_option\":\"" + lynuc_option + "\",\"password_ver\":\"" + this.getPassword_ver()
				+ "\",\"controller_ver\":\"" + this.getController_ver() + "\",\"machine_dat\":\"" + machine_dat
				+ "\",\"ezdoctor_serial\":\"" + this.getEzdoctor_serial() + "\",\"input_user_id\":\"" + input_user_id + "\",\"input_time\":\"" + input_time + "\"}  ";
	}

}
