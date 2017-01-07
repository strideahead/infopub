package com.lynuc.bean;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

public class VersionPassword implements Serializable{

	private static final long serialVersionUID = -580121890235933438L;
	private String controller_no;
	private String card_no;
	private String soft_ver;
	private String hard_ver;
	private int apply_no;
	private String state;
	
	private String controller_ver;
	private String machine_no;
	private String machine_type;
	private String company_name;
	private String customer_name;
	
	private String state_color;
	public VersionPassword() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public VersionPassword(Map<String, Object> map) throws ParseException{
		this.controller_no = (String)map.get("controller_no");
		this.card_no = (String)map.get("card_no");
		this.soft_ver = (String)map.get("soft_ver");
		this.hard_ver = (String)map.get("hard_ver");
		this.apply_no = (int)map.get("apply_no");
		this.state = (String)map.get("state");String st=(String) map.get("state");
		if(st.equals("Y")){
			this.state="已批准";
			this.state_color="#00CC00";
		}else if(st.equals("P")){
			this.state="待批准";
			this.state_color="#FFCCCC";
		}else if(st.equals("N")){
			this.state="拒绝";
			this.state_color="#FF0033";
		}else{
			this.state="";
			this.state_color="#0099CC";
		}
		
		this.controller_ver = (String)map.get("controller_ver");
		this.machine_no = (String)map.get("machine_no");
		this.machine_type = (String)map.get("machine_type");
		this.company_name = (String)map.get("company_name");
		this.customer_name = (String)map.get("customer_name");
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
	public String getSoft_ver() {
		return soft_ver;
	}
	public void setSoft_ver(String soft_ver) {
		this.soft_ver = soft_ver;
	}
	public String getHard_ver() {
		return hard_ver;
	}
	public void setHard_ver(String hard_ver) {
		this.hard_ver = hard_ver;
	}
	public int getApply_no() {
		return apply_no;
	}
	public void setApply_no(int apply_no) {
		this.apply_no = apply_no;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getController_ver() {
		return controller_ver;
	}
	public void setController_ver(String controller_ver) {
		this.controller_ver = controller_ver;
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

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getState_color() {
		return state_color;
	}

	public void setState_color(String state_color) {
		this.state_color = state_color;
	}

	@Override
	public String toString() {
		return "VersionPassword [controller_no=" + controller_no + ", card_no=" + card_no + ", soft_ver=" + soft_ver
				+ ", hard_ver=" + hard_ver + ", apply_no=" + apply_no + ", state=" + state + ", controller_ver="
				+ controller_ver + ", machine_no=" + machine_no + ", machine_type=" + machine_type + ", company_name="
				+ company_name + ", customer_name=" + customer_name + ", state_color=" + state_color + "]";
	}
	
	
	
	
}
