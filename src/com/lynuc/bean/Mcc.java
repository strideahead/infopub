package com.lynuc.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.Map;

public class Mcc extends MccFromDB implements Serializable {

	private static final long serialVersionUID = 4321780112670911746L;
	
	
	private String office_name;
	private String customer_name;
	
	private String input_user_name;
	
	public void convertMccToMap(Map<String,Object>  map)
	{
		map.put("gguid",this.getGguid());
		map.put("machine_no",this.getMachine_no());
		map.put("machine_type",this.getMachine_type());
		map.put("controller_no",this.getController_no());
		map.put("card_no",this.getCard_no());
		map.put("card_content",this.getCard_content());
		map.put("ipc_sn",this.getIpc_sn());
		map.put("is_old",this.getIs_old());
		map.put("is_pay",this.getIs_pay());
		map.put("office",this.getOffice());
		map.put("cust_name",this.getCust_name());
		map.put("cust_duty",this.getCust_duty());
		map.put("cust_tel",this.getCust_tel());
		map.put("mac_no",this.getMac_no());
		map.put("ver",this.getVer());
		map.put("setup_date",this.getSetup_date());
		map.put("hard_ver",this.getHard_ver());
		map.put("memo",this.getMemo());
		map.put("module_set",this.getModule_set());
		map.put("filecount",this.getFilecount());
		map.put("file1path",this.getFile1path());
		map.put("file1desc",this.getFile1desc());
		map.put("file2path",this.getFile2path());
		map.put("file2desc",this.getFile2desc());
		map.put("file3path",this.getFile3path());
		map.put("file3desc",this.getFile3desc());
		map.put("office_name",this.getOffice_name());
		map.put("sale_company_gguid",this.getSale_company_gguid());
		map.put("customer_name",this.getCustomer_name());
		map.put("cust_company_gguid",this.getCust_company_gguid());
		map.put("total_type",this.getTotal_type());
		map.put("mac_option",this.getMac_option());
		map.put("lynuc_option",this.getLynuc_option());
		map.put("password_ver",this.getPassword_ver());
		map.put("controller_ver",this.getController_ver());
		map.put("machine_dat",this.getMachine_dat());
		map.put("ezdoctor_serial",this.getEzdoctor_serial());
		map.put("input_user_name",this.getInput_user_name());
		map.put("input_user_id",this.getInput_user_id());
		map.put("input_time",this.getInput_time());	
	}
	
	public void MccShowOnPage(Map<String, Object> map) throws ParseException{
		/*this.the_password = (String)map.get("the_password");
		this.apply_no = Integer.parseInt(map.get("apply_no").toString().trim());
		this.controller_ver = (String)map.get("controller_ver");
		this.machine_no = (String)map.get("machine_no");
		this.machine_type = (String)map.get("machine_type");
		this.controller_no = (String)map.get("controller_no");
		this.card_no = (String)map.get("card_no");
		this.company_name = (String)map.get("company_name");
		this.cust_company_name = (String)map.get("cust_company_name");
		String st=(String) map.get("state");
		if(st.equals("Y")){
			this.state="已批准";
		}else if(st.equals("P")){
			this.state="待批准";
		}else if(st.equals("N")){
			this.state="不批准";
		}else{
			this.state="";
		}
		String forever = (String)map.get("is_forever");
		if(forever.equals("Y")){
			this.enddate="永久密码";
		}else{
			String response=(map.get("enddate").toString().split(" "))[0];
			this.enddate=sdf.format(sdf.parse(response));
		}
		this.is_forever=forever;
		
		Date datetmp=sdf.parse(map.get("enddate").toString());
		Date now=new Date();
		if(!is_forever.equals("Y")){
			long days=(datetmp.getTime()-now.getTime())/(1000*60*60*24);
			if(days<0){
				this.expireState="#D6D3CE";
			}else{
				if(days<=30){
					this.expireState="#FCF7BB";
				}else if(days<=90){
					this.expireState="#CAEAFC";
				}else if(days>90){
					this.expireState="#CEFFCE";
				}
			}
		}else{
			this.expireState="#CEFFCE";
		}*/
	}
	public Mcc() {}

	public Mcc(String machine_no, String machine_type, String is_pay, String office, String cust_name, String cust_duty,
			String cust_tel, String mac_no, String ver, String setup_date, String hard_ver, String memo, int module_set,
			int filecount, String file1path, String file1desc, String file2path, String file2desc, String file3path,
			String file3desc, String office_name, String sale_company_gguid, String customer_name,
			String cust_company_gguid, String total_type, String mac_option, String lynuc_option, String machine_dat,
			String input_user_name, int input_user_id, String input_time) {
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
		this.office_name = office_name;
		this.sale_company_gguid = sale_company_gguid;
		this.customer_name = customer_name;
		this.cust_company_gguid = cust_company_gguid;
		this.total_type = total_type;
		this.mac_option = mac_option;
		this.lynuc_option = lynuc_option;
		this.machine_dat = machine_dat;
		this.input_user_name = input_user_name;
		this.input_user_id = input_user_id;
		this.input_time = input_time;
	}

	public String getOffice_name() {
		return office_name;
	}

	public void setOffice_name(String office_name) {
		this.office_name = office_name;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getInput_user_name() {
		return input_user_name;
	}

	public void setInput_user_name(String input_user_name) {
		this.input_user_name = input_user_name;
	}
	public Mcc getEntry(ResultSet rs) throws Exception {
		super.getEntry(rs);
		this.setOffice_name(rs.getString("office_name"));
		this.setCustomer_name(rs.getString("customer_name"));	
		if(null != rs.getString("input_user_name")){
			this.setInput_user_name(rs.getString("input_user_name"));
		}else{
			this.setInput_user_name("");
		}
		return this;
	}

	@Override
	public String toString() {
		return "{\"office_name\":\"" + office_name + "\",\"customer_name\":\"" + customer_name
				+ "\",\"input_user_name\":\"" + input_user_name + "\",\"machine_no\":\"" + machine_no
				+ "\",\"machine_type\":\"" + machine_type + "\",\"is_pay\":\"" + is_pay + "\",\"office\":\"" + office
				+ "\",\"cust_name\":\"" + cust_name + "\",\"cust_duty\":\"" + cust_duty + "\",\"cust_tel\":\""
				+ cust_tel + "\",\"mac_no\":\"" + mac_no + "\",\"ver\":\"" + ver + "\",\"setup_date\":\"" + setup_date
				+ "\",\"hard_ver\":\"" + hard_ver + "\",\"memo\":\"" + memo + "\",\"module_set\":\"" + module_set
				+ "\",\"filecount\":\"" + filecount + "\",\"file1path\":\"" + file1path + "\",\"file1desc\":\""
				+ file1desc + "\",\"file2path\":\"" + file2path + "\",\"file2desc\":\"" + file2desc
				+ "\",\"file3path\":\"" + file3path + "\",\"file3desc\":\"" + file3desc + "\",\"sale_company_gguid\":\""
				+ sale_company_gguid + "\",\"cust_company_gguid\":\"" + cust_company_gguid + "\",\"total_type\":\""
				+ total_type + "\",\"mac_option\":\"" + mac_option + "\",\"lynuc_option\":\"" + lynuc_option
				+ "\",\"machine_dat\":\"" + machine_dat + "\",\"input_user_id\":\"" + input_user_id
				+ "\",\"input_time\":\"" + input_time + "\",\"gguid\":\"" + gguid + "\",\"controller_no\":\""
				+ controller_no + "\",\"card_no\":\"" + card_no + "\",\"card_content\":\"" + card_content
				+ "\",\"ipc_sn\":\"" + ipc_sn + "\",\"is_old\":\"" + is_old + "\",\"password_ver\":\"" + password_ver
				+ "\",\"controller_ver\":\"" + controller_ver + "\",\"ezdoctor_serial\":\"" + ezdoctor_serial + "\"}  ";
	}

}
