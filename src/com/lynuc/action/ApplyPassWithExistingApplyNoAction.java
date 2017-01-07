package com.lynuc.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import com.lynuc.bean.BaseAction;
import com.lynuc.bean.Mcc;
import com.lynuc.bean.MccPassword;
import com.lynuc.bean.SmsTodo;
import com.lynuc.bean.SysLog;
import com.lynuc.bean.TableProducts;
import com.lynuc.bean.User;
import com.lynuc.dao.MccListDao;
import com.lynuc.dao.PasswordDetailDao;
import com.lynuc.def.Constant;
import com.lynuc.implDao.AddLogRecordActionImpl;
import com.lynuc.implDao.AddSmstodoActionImpl;
import com.lynuc.implDao.GetPasswordDetailImpl;
import com.lynuc.implDao.JdbcSqlMccDaoImpl;
import com.lynuc.util.JdbcUtil;
import com.lynuc.util.PasswordEncryption;
public class ApplyPassWithExistingApplyNoAction extends BaseAction {
	private static final long serialVersionUID = -2204596919422933708L;
	private static PasswordEncryption generatePass=new PasswordEncryption();
	
	public String execute() throws Exception 
	{
		Connection conn = JdbcUtil.getConnection();	
		String is_revert=request.getParameter("hidden_revert");
		String apply_no_str=request.getParameter("txt_apply_no");
		int apply_no=0;
		if(null != apply_no_str && !"".equals(apply_no_str.trim())){
			apply_no=Integer.parseInt(apply_no_str);
		}
		if(is_revert != null){
			String delete_mccpassword="delete from mcc_password where apply_no=?";
			try (
				PreparedStatement pstmt=conn.prepareStatement(delete_mccpassword);	
			){
				pstmt.setInt(1, apply_no);
				pstmt.executeUpdate();
			} 
			return SUCCESS;
		}
		String controller_no=request.getParameter("txt_controller_no");
		
		Mcc m=new Mcc();
		SmsTodo st=null;
		SysLog sl=null;
		try(
			PreparedStatement pstmt=conn.prepareStatement("{call dbo.mccsel_byctrlno(?)}");	
		)
		{
			pstmt.setString(1, controller_no);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				m.getEntry(rs);
			}
			if(rs!=null){
				rs.close();
			}
		}
		PasswordDetailDao passdetailDao = new GetPasswordDetailImpl();
		int apply_no_max=passdetailDao.getMaxApply_no();
		String cust_name=request.getParameter("txt_cust_name");
		String is_pay_str=request.getParameter("txt_is_pay");
		String is_pay="N";
		if(is_pay_str.equals("Î´¸¶")) is_pay="N";
		if(is_pay_str.equals("ÒÑ¸¶")) is_pay="Y";
		String is_forever_str=request.getParameter("chkForever");
		String is_forever=(is_forever_str==null)?"N":"Y";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf4 = new SimpleDateFormat("MM/dd/yyyy");
		String startdate_str=(is_forever=="Y")?"1977-02-23 00:00:00.000":"2008-01-01 00:00:00.000";
		String enddate_str_tmp=request.getParameter("enddate_set");
		Date enddate_tmp = null;
		String enddate_str = null;
		if(enddate_str_tmp.contains("/")){
			try {
				enddate_tmp=sdf4.parse(enddate_str_tmp);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			enddate_str_tmp=sdf.format(enddate_tmp); 
			enddate_str=(is_forever=="Y")?"2037-07-13 00:00:00.000":enddate_str_tmp;
		}else if(enddate_str_tmp.contains("-")){
			try {
				enddate_tmp=sdf2.parse(enddate_str_tmp);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			enddate_str_tmp=sdf.format(enddate_tmp); 
			enddate_str=(is_forever=="Y")?"2037-07-13 00:00:00.000":enddate_str_tmp;
		}
		//enddate_str = this.returnFixedDate(enddate_str);
		String is_batch_str=request.getParameter("chkBatch");
		String is_batch=(is_batch_str==null)?"N":"Y";
		StringBuilder batchstartdate_str=new StringBuilder();
		StringBuilder batchenddate_str=new StringBuilder("");
		int batchperiod=0;
		if(is_batch=="N"){
			batchstartdate_str.append(startdate_str);
			batchenddate_str.append(enddate_str);
			batchperiod=1;
		}else{
			batchstartdate_str.append(request.getParameter("dp_startdate_batch_apply"));
			//batchenddate_str = new StringBuilder(returnFixedDate(batchenddate_str.toString()));
			batchenddate_str.append(request.getParameter("dp_enddate_batch_apply"));
			
			batchperiod=Integer.parseInt(request.getParameter("rdl_period_apply"));
		}
		User user=(User) sessionMap.get("user"); 
		TableProducts tp=(TableProducts)sessionMap.get("tableproducts");
		int request_user_id=user.getId();
		String request_time_str=request.getParameter("txt_request_time");
		String request_memo=request.getParameter("ftb_memo_apply");

		MccPassword mp=new MccPassword();
		UUID uuid=UUID.randomUUID();
		String gguid=uuid.toString().toLowerCase();
		mp.setGguid(gguid);
		mp.setApply_no(apply_no_max+1);				
		mp.setMcc_gguid(m.getGguid());
		mp.setController_no(controller_no);
		mp.setCard_no(m.getCard_no());
		mp.setCard_content(m.getCard_content());
		mp.setIpc_sn(m.getIpc_sn());
		mp.setIs_old(mp.getIs_old());
		mp.setStartdate(startdate_str);
		mp.setEnddate(enddate_str);
		mp.setRequest_user_id(request_user_id);
		mp.setRequest_time(request_time_str);
		mp.setRequest_memo(request_memo);
		mp.setCheck_time(null);
		mp.setCheck_user_id(0);
		mp.setCheck_memo(null);
		mp.setPackage_gguid(null);
		mp.setIs_forever(is_forever);
		mp.setIs_batch(is_batch);
		mp.setBatch_startdate(batchstartdate_str.toString());
		mp.setBatch_enddate(batchenddate_str.toString());
		mp.setBatch_period(batchperiod);
		mp.setCard_change_gguid(null);
		mp.setPassword_ver(m.getPassword_ver());
		mp.setController_ver(m.getController_ver());
		mp.setUser_number(null);
		mp.setEzdoctor_serial(null);
		mp.setPrompt(5);
		
		String state="P";
		String btime_str="";
		String check_time=null;
		String checker_account_sql="select su.*,co.company_name,co.company_type,co.gguid as company_gguid from sys_user as su "
				+ "left join company as co on co.gguid=su.belong_company_gguid "
				+"  where usercode=?";
		/*String checker_account_sql="select su.*,co.company_name from sys_user as su left join company as co"
				+ " on co.gguid=su.belong_company_gguid where usercode=?";*/
		User checkeruser=new User();
		try(
			PreparedStatement pstmt = conn.prepareStatement(checker_account_sql);
		)
		{
			pstmt.setString(1, tp.getPwd_checker_accounts());
			ResultSet rs=pstmt.executeQuery();	
			if(rs.next()){
					checkeruser.getEntry(rs);
			}
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		boolean is_needapprove = check_NeedApprove(controller_no, enddate_str);
		if(is_pay.equals("Y") || is_needapprove) {
			String the_password=generatePass.generatePassword(m, mp);
			state="Y";
			Calendar calendar=Calendar.getInstance();
	        Date date=calendar.getTime();
	        btime_str=sdf.format(date);
	        check_time=sdf.format(date);
	        mp.setCheck_time(check_time);
	        mp.setCheck_user_id(checkeruser.getId());
	        mp.setThe_password(the_password);
	        sl=new SysLog();
	        StringBuilder logstr=new StringBuilder("ÃÜÂëÉêÇë[ÄúÉêÇëµÄÃÜÂëÒÑ¾­±»Åú×¼¡£[ÉêÇëºÅ:"+mp.getApply_no());
	        logstr.append(";¿Í»§Ãû:"+cust_name+";»ú´²±àºÅ:"+mp.getController_no()+";ÃÜÂëÓÐÐ§ÆÚ:"+enddate_str.substring(0,11)+"]]");
	        sl.setLog_memo(logstr.toString());
	        sl.setLog_type("6");
		}else{
			state="P";
			st=new SmsTodo();
			sl=new SysLog();
			StringBuilder smscontent=new StringBuilder("ÃÜÂëÉêÇë£¬ÉêÇëºÅÎª"+mp.getApply_no()+"¡£Î´¸¶;");
			smscontent.append(";¿Í»§Ãû:"+cust_name+";±àºÅ:"+mp.getController_no());
			smscontent.append(";ÃÜÂëÓÐÐ§ÆÚ:"+enddate_str.substring(0,11)+"("+checkeruser.getUsername()+")");
			
			st.setContent(smscontent.toString());
			st.setIs_sent("N");
			st.setMobile(checkeruser.getMobile());
			
			sl.setLog_memo(smscontent.toString());
			sl.setLog_user(user.getUsername());
			sl.setLog_type("6");
			mp.setThe_password(null);
		}
		mp.setState(state);
		mp.setBtime(btime_str);
		MccListDao jsmdi=new JdbcSqlMccDaoImpl();
		jsmdi.insertMcc_password_item(mp);
		
		if(st!=null){
			AddSmstodoActionImpl  addsms=new AddSmstodoActionImpl();
			try {
				addsms.insertSmstodoToDB(st);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(sl!=null){
			AddLogRecordActionImpl addlog=new AddLogRecordActionImpl();
			try {
				addlog.insertSysLogToDB(sl);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return SUCCESS;
	}
	private boolean check_NeedApprove(String controller_no, String enddate) throws ParseException 
	{
		String applytimessql="select count(*) as applytimes from mcc_password where controller_no=?";
		Calendar c=Calendar.getInstance();
		c.add(Calendar.MONTH, 2);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		try {
			Connection conn=JdbcUtil.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(applytimessql);
			pstmt.setString(1, controller_no);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				int times=rs.getInt("applytimes");
				if(times >= 3){
					return false;
				}
				Date date=sdf.parse(enddate);
				
				if(date.getTime()>c.getTimeInMillis()){
					return false;
				}
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			
		}
		return false;
	}
}















