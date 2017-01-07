package com.lynuc.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.lynuc.bean.BaseAction;
import com.lynuc.bean.MccFromDB;
import com.lynuc.bean.MccPassword;
import com.lynuc.bean.SmsTodo;
import com.lynuc.bean.User;
import com.lynuc.implDao.AddSmstodoActionImpl;
import com.lynuc.implDao.JdbcSqlMccPasswordDaoImpl;
import com.lynuc.util.JdbcUtil;

public class BatchApplyPassAction extends BaseAction {

	private static final long serialVersionUID = 677217398508356869L;
	private String txt_controller_no;
	private String chkForever;
	private String enddate_set;
	private String chkBatch;
	private String dp_startdate_batch_apply;
	private String dp_enddate_batch_apply;
	private String rdl_period_apply;
	private String ftb_memo_apply;
	private String chkSendMessage;
	private String txt_sms_content;
	private String txt_request_user;
	private String txt_request_time;
	
	
	public BatchApplyPassAction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BatchApplyPassAction(String txt_controller_no, String chkForever, String enddate_set, String chkBatch,
			String dp_startdate_batch_apply, String dp_enddate_batch_apply, String rdl_period_apply,
			String ftb_memo_apply, String chkSendMessage, String txt_sms_content, String txt_request_user,
			String txt_request_time) {
		super();
		this.txt_controller_no = txt_controller_no;
		this.chkForever = chkForever;
		this.enddate_set = enddate_set;
		this.chkBatch = chkBatch;
		this.dp_startdate_batch_apply = dp_startdate_batch_apply;
		this.dp_enddate_batch_apply = dp_enddate_batch_apply;
		this.rdl_period_apply = rdl_period_apply;
		this.ftb_memo_apply = ftb_memo_apply;
		this.chkSendMessage = chkSendMessage;
		this.txt_sms_content = txt_sms_content;
		this.txt_request_user = txt_request_user;
		this.txt_request_time = txt_request_time;
	}
	
	public String getTxt_controller_no() {
		return txt_controller_no;
	}
	public void setTxt_controller_no(String txt_controller_no) {
		this.txt_controller_no = txt_controller_no;
	}
	public String isChkForever() {
		return chkForever;
	}
	public void setChkForever(String chkForever) {
		this.chkForever = chkForever;
	}
	public String getEnddate_set() {
		return enddate_set;
	}
	public void setEnddate_set(String enddate_set) {
		this.enddate_set = enddate_set;
	}
	public String isChkBatch() {
		return chkBatch;
	}
	public void setChkBatch(String chkBatch) {
		this.chkBatch = chkBatch;
	}
	public String getDp_startdate_batch_apply() {
		return dp_startdate_batch_apply;
	}
	public void setDp_startdate_batch_apply(String dp_startdate_batch_apply) {
		this.dp_startdate_batch_apply = dp_startdate_batch_apply;
	}
	public String getDp_enddate_batch_apply() {
		return dp_enddate_batch_apply;
	}
	public void setDp_enddate_batch_apply(String dp_enddate_batch_apply) {
		this.dp_enddate_batch_apply = dp_enddate_batch_apply;
	}
	public String getRdl_period_apply() {
		return rdl_period_apply;
	}
	public void setRdl_period_apply(String rdl_period_apply) {
		this.rdl_period_apply = rdl_period_apply;
	}
	public String getFtb_memo_apply() {
		return ftb_memo_apply;
	}
	public void setFtb_memo_apply(String ftb_memo_apply) {
		this.ftb_memo_apply = ftb_memo_apply;
	}
	public String isChkSendMessage() {
		return chkSendMessage;
	}
	public void setChkSendMessage(String chkSendMessage) {
		this.chkSendMessage = chkSendMessage;
	}
	public String getTxt_sms_content() {
		return txt_sms_content;
	}
	public void setTxt_sms_content(String txt_sms_content) {
		this.txt_sms_content = txt_sms_content;
	}
	public String getTxt_request_user() {
		return txt_request_user;
	}
	public void setTxt_request_user(String txt_request_user) {
		this.txt_request_user = txt_request_user;
	}
	public String getTxt_request_time() {
		return txt_request_time;
	}
	public void setTxt_request_time(String txt_request_time) {
		this.txt_request_time = txt_request_time;
	}
	
	@Override
	public String toString() {
		return "{\"txt_controller_no\":\"" + txt_controller_no + "\",\"chkForever\":\"" + chkForever
				+ "\",\"enddate_set\":\"" + enddate_set + "\",\"chkBatch\":\"" + chkBatch
				+ "\",\"dp_startdate_batch_apply\":\"" + dp_startdate_batch_apply + "\",\"dp_enddate_batch_apply\":\""
				+ dp_enddate_batch_apply + "\",\"rdl_period_apply\":\"" + rdl_period_apply + "\",\"ftb_memo_apply\":\""
				+ ftb_memo_apply + "\",\"chkSendMessage\":\"" + chkSendMessage + "\",\"txt_sms_content\":\""
				+ txt_sms_content + "\",\"txt_request_user\":\"" + txt_request_user + "\",\"txt_request_time\":\""
				+ txt_request_time + "\"}  ";
	}
	/*public void validate()
	{
		if(txt_controller_no==null) addFieldError("txt_controller_no", "控制器项不能为空");
	}*/
	public String execute() throws Exception
	{
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        Date date=calendar.getTime();
        request.setAttribute("enddate", sdf.format(date));
        calendar.add(Calendar.MONTH, -1);
        calendar.add(Calendar.DATE, -1);
        date=calendar.getTime();
        request.setAttribute("fromdate", sdf.format(date));  
        Connection conn=JdbcUtil.getConnection();
        String minlen_sql="select min(len(controller_no)) as minlen from mcc";
        PreparedStatement pstmt=conn.prepareStatement(minlen_sql);
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
        	request.setAttribute("minlen_controllerno", rs.getObject("minlen"));
        }
        conn.close();
        pstmt.close();
        rs.close();
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String batchApplyPassword() throws Exception
	{
		//enddate_set = this.returnFixedDate(enddate_set);
		dp_enddate_batch_apply = this.returnFixedDate(dp_enddate_batch_apply);
		MccPassword mp=new MccPassword();
		
		User u=(User) sessionMap.get("user");
		mp.setRequest_user_id(u.getId());
		
		mp.setRequest_memo(ftb_memo_apply);
		mp.setRequest_time(txt_request_time);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Calendar calendar=Calendar.getInstance();
        Date date=calendar.getTime();
        mp.setBtime(sdf.format(date));
        
        if(chkForever!=null){
			mp.setIs_forever("Y");
			mp.setStartdate("1977-02-23 00:00:00.000");
			mp.setEnddate("2037-07-13 00:00:00.000");
			mp.setIs_batch("N");
			mp.setBatch_startdate("1977-02-23 00:00:00.000");
			mp.setBatch_enddate("2037-07-13 00:00:00.000");
			mp.setBatch_period(1);
		}else if(chkForever==null&&chkBatch==null){
			mp.setIs_forever("N");
			mp.setStartdate("2008-01-01 00:00:00.000");
			mp.setEnddate(enddate_set);
			mp.setIs_batch("N");
			mp.setBatch_startdate(mp.getStartdate());
			mp.setBatch_enddate(mp.getEnddate());
			mp.setBatch_period(1);
		}
		if(chkBatch!=null){
			mp.setIs_forever("N");
			mp.setStartdate(dp_startdate_batch_apply);
			mp.setEnddate(dp_enddate_batch_apply);
			mp.setBatch_startdate(dp_startdate_batch_apply);
			mp.setBatch_enddate(dp_enddate_batch_apply);
			mp.setBatch_period(Integer.parseInt(rdl_period_apply));
			mp.setIs_batch("Y");
		}
		mp.setPassword_ver("3.0");
		String txt_controller_notmp=txt_controller_no.replaceAll("\\s{1,}", "");
		String [] ctrlno_list=txt_controller_notmp.split(",");
		List<String> failedList=new ArrayList<>(); 
		List<MccFromDB> succeedList=new ArrayList<>();
		JdbcSqlMccPasswordDaoImpl jsmpdi=new JdbcSqlMccPasswordDaoImpl();
		jsmpdi.batchApplyPasswordToDB(ctrlno_list, failedList, succeedList, mp);
		if(succeedList.size()>0){
			if("on".equals(this.chkSendMessage)){
				SmsTodo st=new SmsTodo();
				st.setContent(txt_sms_content);
				AddSmstodoActionImpl asai=new AddSmstodoActionImpl();
				asai.insertSmstodoToDB(st);
			}
		}
		sessionMap.put("succeedList", succeedList);
		sessionMap.put("failedList", failedList);
		return "submitbatapp";
	}
	public String showBatchResults()
	{
		request.setAttribute("succeedList", sessionMap.get("succeedList"));
		request.setAttribute("failedList", sessionMap.get("failedList"));
		return "showResults";
	}
	private String returnFixedDate(String endDate) throws ParseException
	{
		if(null == endDate || "".equals(endDate)){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date=sdf.parse(endDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w == 0 ){
        	cal.add(Calendar.DATE, 2);
        }else if(w == 5){
        	cal.add(Calendar.DATE, 4);
        }else if(w == 6){
        	cal.add(Calendar.DATE, 3);
        }
        return sdf.format(cal.getTime());
	}
}
