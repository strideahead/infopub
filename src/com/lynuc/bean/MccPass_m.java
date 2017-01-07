package com.lynuc.bean;

import java.io.Serializable;
import java.sql.ResultSet;

public class MccPass_m implements Serializable {
	
	private int apply_no;
	private String controller_no;
	private String customer_name;
	private String state;
	private String enddate;
	private int request_user_id;
	public MccPass_m() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MccPass_m(int apply_no, String controller_no, String customer_name, String state, String enddate) {
		super();
		this.apply_no = apply_no;
		this.controller_no = controller_no;
		this.customer_name = customer_name;
		this.state = state;
		this.enddate = enddate;
	}
	public int getApply_no() {
		return apply_no;
	}
	public void setApply_no(int apply_no) {
		this.apply_no = apply_no;
	}
	public String getController_no() {
		return controller_no;
	}
	public void setController_no(String controller_no) {
		this.controller_no = controller_no;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public int getRequest_user_id() {
		return request_user_id;
	}
	public void setRequest_user_id(int request_user_id) {
		this.request_user_id = request_user_id;
	}
	@Override
	public String toString() {
		return "{\"apply_no\":\"" + apply_no + "\",\"controller_no\":\"" + controller_no + "\",\"customer_name\":\""
				+ customer_name + "\",\"state\":\"" + state + "\",\"enddate\":\"" + enddate
				+ "\",\"request_user_id\":\"" + request_user_id + "\"}  ";
	}
	public MccPass_m getEntry(ResultSet rs) throws Exception
	{
		this.setApply_no(rs.getInt("apply_no"));
		this.setController_no(rs.getString("controller_no"));
		this.setCustomer_name(rs.getString("cust_company_name"));
		this.setState(rs.getString("state"));
		this.setEnddate(rs.getString("enddate"));
		this.setRequest_user_id(rs.getInt("request_user_id"));
		return this;
	}
	
}
