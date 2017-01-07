package com.lynuc.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class MccShowOnPage  implements Serializable {

	private static final long serialVersionUID = 5168166010355739045L;
	private static final  DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private String the_password;
	private Integer apply_no;
	private String controller_ver;
	private String machine_no;
	private String machine_type;
	private String controller_no;
	private String card_no;
	private String is_pay;
	private String company_name;
	private String cust_company_name;
	private String state;
	private String is_forever;
	private String enddate;
	private String expireState;
	private int input_user_id;
	private int request_user_id;
	
	public MccShowOnPage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MccShowOnPage(String the_password, Integer apply_no, String controller_ver, String machine_no,
			String machine_type, String controller_no, String card_no, String is_pay, String company_name,
			String cust_company_name, String state, String is_forever, String enddate, String expireState,
			int input_user_id,int request_user_id) {
		super();
		this.the_password = the_password;
		this.apply_no = apply_no;
		this.controller_ver = controller_ver;
		this.machine_no = machine_no;
		this.machine_type = machine_type;
		this.controller_no = controller_no;
		this.card_no = card_no;
		this.is_pay = is_pay;
		this.company_name = company_name;
		this.cust_company_name = cust_company_name;
		this.state = state;
		this.is_forever = is_forever;
		this.enddate = enddate;
		this.expireState = expireState;
		this.input_user_id = input_user_id;
		this.request_user_id = request_user_id;
	}

	public MccShowOnPage(Map<String, Object> map) throws ParseException{
		this.the_password = (String)map.get("the_password");
		//if("".equals((String)map.get("apply_no"))){
			this.apply_no=(Integer)map.get("apply_no");;
		//}else{
			///this.apply_no = Integer.parseInt(map.get("apply_no").toString().trim());
		//}

//		String apply_no_str=(String) map.get("apply_no");
//		if(apply_no_str==null||"".equals(apply_no_str)) 
//			this.apply_no=0;
//		else
			
		 
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
			this.state="拒绝";
		}else{
			this.state="";
		}
		String forever = (String)map.get("is_forever");
		if(!"".equals(forever)){
			if(forever.equals("Y")){
				this.enddate="永久密码";
			}else{
				String response=(map.get("enddate").toString().split(" "))[0];
				this.enddate=sdf.format(sdf.parse(response));
			}
		}
		
		this.is_forever=forever;
		
		if("".equals(map.get("enddate").toString())){
			this.expireState="#FFFFFF";
		}else{
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
						this.expireState="RGB(176,196,222)";
					}
				}
			}else{
				this.expireState="RGB(176,196,222)";
			}
		}
		String input_user_id_str=(String)(map.get("input_user_id")+"");
		if(input_user_id_str!=null && !"".equals(input_user_id_str)){
			this.input_user_id=Integer.parseInt(input_user_id_str);
		}else{
			this.input_user_id=0;
		}
		
		String request_user_id_str=(String)(map.get("request_user_id")+"");
		if(request_user_id_str != null && !"".equals(request_user_id_str)){
			this.request_user_id=Integer.parseInt(request_user_id_str);
		}else{
			this.request_user_id=0;
		}
	}
	
	public String getThe_password() {
		return the_password;
	}

	public void setThe_password(String the_password) {
		this.the_password = the_password;
	}

	public int getApply_no() {
		return apply_no;
	}

	public void setApply_no(int apply_no) {
		this.apply_no = apply_no;
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

	public String getIs_pay() {
		return is_pay;
	}

	public void setIs_pay(String is_pay) {
		this.is_pay = is_pay;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getCust_company_name() {
		return cust_company_name;
	}

	public void setCust_company_name(String cust_company_name) {
		this.cust_company_name = cust_company_name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIs_forever() {
		return is_forever;
	}

	public void setIs_forever(String is_forever) {
		this.is_forever = is_forever;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getExpireState() {
		return expireState;
	}

	public void setExpireState(String expireState) {
		this.expireState = expireState;
	}

	public void setApply_no(Integer apply_no) {
		this.apply_no = apply_no;
	}

	public int getInput_user_id() {
		return input_user_id;
	}

	public void setInput_user_id(int input_user_id) {
		this.input_user_id = input_user_id;
	}
	
	public int getRequest_user_id() {
		return request_user_id;
	}

	public void setRequest_user_id(int request_user_id) {
		this.request_user_id = request_user_id;
	}

	/*@Override
	public String toString() {
		return "{\"the_password\":\"" + the_password + "\",\"apply_no\":\"" + apply_no + "\",\"controller_ver\":\""
				+ controller_ver + "\",\"machine_no\":\"" + machine_no + "\",\"machine_type\":\"" + machine_type
				+ "\",\"controller_no\":\"" + controller_no + "\",\"card_no\":\"" + card_no + "\",\"is_pay\":\""
				+ is_pay + "\",\"company_name\":\"" + company_name + "\",\"cust_company_name\":\"" + cust_company_name
				+ "\",\"state\":\"" + state + "\",\"is_forever\":\"" + is_forever + "\",\"enddate\":\"" + enddate
				+ "\",\"expireState\":\"" + expireState + "\",\"input_user_id\":\"" + input_user_id + "\"}";
	}*/

	@Override
	public String toString() {
		return "{\"the_password\":\"" + the_password + "\",\"apply_no\":\"" + apply_no + "\",\"controller_ver\":\""
				+ controller_ver + "\",\"machine_no\":\"" + machine_no + "\",\"machine_type\":\"" + machine_type
				+ "\",\"controller_no\":\"" + controller_no + "\",\"card_no\":\"" + card_no + "\",\"is_pay\":\""
				+ is_pay + "\",\"company_name\":\"" + company_name + "\",\"cust_company_name\":\"" + cust_company_name
				+ "\",\"state\":\"" + state + "\",\"is_forever\":\"" + is_forever + "\",\"enddate\":\"" + enddate
				+ "\",\"expireState\":\"" + expireState + "\",\"input_user_id\":\"" + input_user_id
				+ "\",\"request_user_id\":\"" + request_user_id + "\"}  ";
	}
	public MccShowOnPage getEntry(ResultSet rs) throws Exception {
		this.setThe_password(rs.getString("the_password"));
		this.setIs_forever(rs.getString("is_forever"));
		this.setApply_no(rs.getInt("apply_no"));
		this.setController_ver(rs.getString("controller_ver"));
		this.setController_no(rs.getString("controller_no"));
		this.setMachine_no(rs.getString("machine_no"));
		this.setMachine_type(rs.getString("machine_type"));
		this.setCard_no(rs.getString("card_no"));
		this.setCompany_name(rs.getString("company_name"));
		this.setCust_company_name(rs.getString("cust_company_name"));
		this.setState(rs.getString("state"));
		this.setEnddate(rs.getString("enddate"));
		this.setInput_user_id(rs.getInt("input_user_id"));
		return this;
	}

	
}
