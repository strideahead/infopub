package com.lynuc.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class MccPassword extends MccOri implements Serializable {

	private static final long serialVersionUID = -6084879654801233749L;
	private static final  DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private String mcc_gguid;
	private int apply_no;
	private String startdate;
	private String enddate;
	private int request_user_id;
	private String request_user_name;
	private String request_time;
	private String request_memo;
	private String state;
	private String check_time;
	private int check_user_id;
	private String check_user_name;
	private String check_memo;
	private String the_password;
	private String package_gguid;
	private String is_forever;
	private String is_batch;
	private String batch_startdate;
	private String batch_enddate;
	private int batch_period;
	private String btime;
	private String card_change_gguid;
	private String user_number;
	private int prompt;
	private String expirestate;
	public MccPassword() {}
	
	public MccPassword(String mcc_gguid, int apply_no, String startdate, String enddate, int request_user_id,
			String request_user_name, String request_time, String request_memo, String state, String check_time,
			int check_user_id, String check_user_name, String check_memo, String the_password, String package_gguid,
			String is_forever, String is_batch, String batch_startdate, String batch_enddate, int batch_period,
			String btime, String card_change_gguid, String user_number, int prompt, String expirestate) {
		super();
		this.mcc_gguid = mcc_gguid;
		this.apply_no = apply_no;
		this.startdate = startdate;
		this.enddate = enddate;
		this.request_user_id = request_user_id;
		this.request_user_name = request_user_name;
		this.request_time = request_time;
		this.request_memo = request_memo;
		this.state = state;
		this.check_time = check_time;
		this.check_user_id = check_user_id;
		this.check_user_name = check_user_name;
		this.check_memo = check_memo;
		this.the_password = the_password;
		this.package_gguid = package_gguid;
		this.is_forever = is_forever;
		this.is_batch = is_batch;
		this.batch_startdate = batch_startdate;
		this.batch_enddate = batch_enddate;
		this.batch_period = batch_period;
		this.btime = btime;
		this.card_change_gguid = card_change_gguid;
		this.user_number = user_number;
		this.prompt = prompt;
		this.expirestate = expirestate;
	}

	public String getMcc_gguid() {
		return mcc_gguid;
	}

	public void setMcc_gguid(String mcc_gguid) {
		this.mcc_gguid = mcc_gguid;
	}

	public int getApply_no() {
		return apply_no;
	}

	public void setApply_no(int apply_no) {
		this.apply_no = apply_no;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
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

	public String getRequest_user_name() {
		return request_user_name;
	}

	public void setRequest_user_name(String request_user_name) {
		this.request_user_name = request_user_name;
	}

	public String getRequest_time() {
		return request_time;
	}

	public void setRequest_time(String request_time) {
		this.request_time = request_time;
	}

	public String getRequest_memo() {
		return request_memo;
	}

	public void setRequest_memo(String request_memo) {
		this.request_memo = request_memo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCheck_time() {
		return check_time;
	}

	public void setCheck_time(String check_time) {
		this.check_time = check_time;
	}

	public int getCheck_user_id() {
		return check_user_id;
	}

	public void setCheck_user_id(int check_user_id) {
		this.check_user_id = check_user_id;
	}

	public String getCheck_user_name() {
		return check_user_name;
	}

	public void setCheck_user_name(String check_user_name) {
		this.check_user_name = check_user_name;
	}

	public String getCheck_memo() {
		return check_memo;
	}

	public void setCheck_memo(String check_memo) {
		this.check_memo = check_memo;
	}

	public String getThe_password() {
		return the_password;
	}

	public void setThe_password(String the_password) {
		this.the_password = the_password;
	}

	public String getPackage_gguid() {
		return package_gguid;
	}

	public void setPackage_gguid(String package_gguid) {
		this.package_gguid = package_gguid;
	}

	public String getIs_forever() {
		return is_forever;
	}

	public void setIs_forever(String is_forever) {
		this.is_forever = is_forever;
	}

	public String getIs_batch() {
		return is_batch;
	}

	public void setIs_batch(String is_batch) {
		this.is_batch = is_batch;
	}

	public String getBatch_startdate() {
		return batch_startdate;
	}

	public void setBatch_startdate(String batch_startdate) {
		this.batch_startdate = batch_startdate;
	}

	public String getBatch_enddate() {
		return batch_enddate;
	}

	public void setBatch_enddate(String batch_enddate) {
		this.batch_enddate = batch_enddate;
	}

	public int getBatch_period() {
		return batch_period;
	}

	public void setBatch_period(int batch_period) {
		this.batch_period = batch_period;
	}

	public String getBtime() {
		return btime;
	}

	public void setBtime(String btime) {
		this.btime = btime;
	}

	public String getCard_change_gguid() {
		return card_change_gguid;
	}

	public void setCard_change_gguid(String card_change_gguid) {
		this.card_change_gguid = card_change_gguid;
	}

	public String getUser_number() {
		return user_number;
	}

	public void setUser_number(String user_number) {
		this.user_number = user_number;
	}

	public int getPrompt() {
		return prompt;
	}

	public void setPrompt(int prompt) {
		this.prompt = prompt;
	}

	public String getExpirestate() {
		return expirestate;
	}

	public void setExpirestate(String expirestate) {
		this.expirestate = expirestate;
	}

	public MccPassword(Map<String, Object> map) throws ParseException {
		this.setGguid((String)map.get("the_password"));
		this.mcc_gguid = (String)map.get("mcc_gguid");
		this.setController_no((String)map.get("controller_no"));
		this.setCard_no((String)map.get("card_no"));
		
		this.setCard_content((String)map.get("card_content"));
		this.setIpc_sn((String)map.get("ipc_sn"));
		this.apply_no = (int)map.get("apply_no");
		this.setIs_old((String)map.get("is_old"));
		this.startdate = map.get("startdate").toString();
		this.request_user_id = (int)map.get("request_user_id");
		this.request_user_name=(String)map.get("request_name");
		this.request_time =  map.get("request_time").toString();
		this.request_memo = (String)map.get("request_memo");
		
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
		
		String checktime=(map.get("check_time").toString().split(" "))[0];
		this.check_time=sdf.format(sdf.parse(checktime));
		this.check_time = map.get("check_time").toString();
		
		this.check_user_id = (int)map.get("check_user_id");
		this.check_user_name = (String)map.get("check_name");
		this.check_memo = (String)map.get("check_memo");
		this.the_password = (String)map.get("the_password");
		this.package_gguid = (String)map.get("package_gguid");

		String forever = (String)map.get("is_forever");
		if(forever.equals("Y")){
			this.enddate="永久密码";
		}else{
			String response=(map.get("enddate").toString().split(" "))[0];
			this.enddate=sdf.format(sdf.parse(response));
		}
		this.is_forever=forever;
		this.is_batch = (String)map.get("is_batch");
		this.batch_startdate = map.get("batch_startdate").toString();
		this.batch_enddate = map.get("batch_enddate").toString();
		this.batch_period = (int)map.get("batch_period");
		this.btime = map.get("btime").toString();
		this.card_change_gguid = (String)map.get("card_change_gguid");
		this.setPassword_ver((String)map.get("password_ver"));
		this.setController_ver((String)map.get("controller_ver"));
		this.user_number = (String)map.get("user_number");
		this.setEzdoctor_serial((String)map.get("ezdoctor_serial"));
		this.prompt = (int)map.get("prompt");
		
	}
	
	public MccPassword getEntry(ResultSet rs) throws Exception {
		this.setGguid(rs.getString("gguid"));
		this.setMcc_gguid(rs.getString("mcc_gguid"));
		this.setController_no(rs.getString("controller_no"));
		this.setCard_no(rs.getString("card_no"));
		this.setCard_content(rs.getString("card_content"));
		this.setIpc_sn(rs.getString("ipc_sn"));
		
		this.setIs_old(rs.getString("is_old"));
		this.setApply_no(rs.getInt("apply_no"));
		this.setStartdate(rs.getString("batch_startdate"));
		this.setEnddate(rs.getString("enddate").toString());
		this.setRequest_user_id(rs.getInt("request_user_id"));
		
		if(rs.getObject("request_name")!=null){
			this.setRequest_user_name((String) rs.getObject("request_name"));
		}
		
		this.setRequest_time(rs.getString("request_time"));
		
		this.setRequest_memo(rs.getString("request_memo"));
		this.setState(rs.getString("state"));
		//this.setCheck_time(rs.getString("check_time").toString());
		if(rs.getString("check_time")==null) this.setCheck_time("");
		else this.setCheck_time(rs.getString("check_time").toString());
		this.setCheck_user_id(rs.getInt("check_user_id"));
		this.setCheck_user_name(rs.getString("check_name"));
		this.setCheck_memo(rs.getString("check_memo"));
		this.setThe_password(rs.getString("the_password"));
		this.setPackage_gguid(rs.getString("package_gguid"));
		this.setIs_forever(rs.getString("is_forever"));
		this.setIs_batch(rs.getString("is_batch"));
		this.setBatch_startdate(rs.getString("batch_startdate"));
		
		this.setBatch_enddate(rs.getString("batch_enddate"));
		this.setBatch_period(rs.getInt("batch_period"));
		this.setBtime(rs.getString("btime"));
		this.setCard_change_gguid(rs.getString("card_change_gguid"));
		this.setPassword_ver(rs.getString("password_ver"));
		this.setController_ver(rs.getString("controller_ver"));
		
		this.setUser_number(rs.getString("user_number"));
		this.setEzdoctor_serial(rs.getString("ezdoctor_serial"));
		this.setPrompt(rs.getInt("prompt"));
		
		if(!"".equals(this.is_forever)){
			if(this.getIs_forever().equals("Y")){
				this.enddate="永久密码";
			}/*else{
				String response=(map.get("enddate").toString().split(" "))[0];
				this.enddate=sdf.format(sdf.parse(response));
			}*/
		}
				
		if("".equals(this.getEnddate())){
			this.expirestate="#FFFFFF";
		}else{
			Date datetmp=rs.getDate("enddate");
			Date now=new Date();
			if(!is_forever.equals("Y")){
				long days=(datetmp.getTime()-now.getTime())/(1000*60*60*24);
				if(days<0){
					this.expirestate="#D6D3CE";
				}else{
					if(days<=30){
						this.expirestate="#FCF7BB";
					}else if(days<=90){
						this.expirestate="#CAEAFC";
					}else if(days>90){
						this.expirestate="RGB(176,196,222)";
					}
				}
			}else{
				this.expirestate="RGB(176,196,222)";
			}
		}
		return this;
	}
	public void generateMccPasswordFromMcc(MccFromDB m)
	{
		this.setMcc_gguid(m.getGguid());
		this.setController_no(m.getController_no());
		this.setController_ver(m.getController_ver());
		this.setCard_no(m.getCard_no());
		this.setCard_content(m.getCard_content());
		this.setIpc_sn(m.getIpc_sn());
		this.setIs_old(m.getIs_old());
		this.setEzdoctor_serial(m.getEzdoctor_serial());
	}
}
