package com.lynuc.implDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.lynuc.bean.MccFromDB;
import com.lynuc.bean.MccPassword;
import com.lynuc.bean.SmsTodo;
import com.lynuc.bean.SysLog;
import com.lynuc.bean.User;
import com.lynuc.util.JdbcUtil;
import com.lynuc.util.PasswordEncryption;

public class JdbcSqlMccPasswordDaoImpl 
{
	
	private static PasswordEncryption generatePass=new PasswordEncryption();

	private static final  DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public void batchApplyPasswordToDB(String[] ctrlno_list,List<String> failedList,List<MccFromDB> succeedList,MccPassword mp)
			throws Exception
	{
		String check_ctrlno_exist="select * from mcc where controller_no=?";
		String if_can_apply="select top 1 * from mcc_password where controller_no=? order by apply_no desc";
		StringBuilder check_office_cust_name=new StringBuilder("select co.company_name as office_name,cm.company_name as cust_name from mcc as m ");
		check_office_cust_name.append("left join company as co on co.gguid=m.sale_company_gguid ");
		check_office_cust_name.append("left join company as cm on cm.gguid=m.cust_company_gguid ");
		check_office_cust_name.append("where m.controller_no=?");
		User user= (User) com.lynuc.bean.BaseAction.sessionMap.get("user");
		JdbcSqlMccDaoImpl jsmdi=new JdbcSqlMccDaoImpl();
		AddLogRecordActionImpl alrci=new AddLogRecordActionImpl();
		AddSmstodoActionImpl asai=new AddSmstodoActionImpl();
		SysLog sl=new SysLog();
		SmsTodo st=new SmsTodo();
		GetPasswordDetailImpl passdetail = new GetPasswordDetailImpl();
		try(
			Connection conn = JdbcUtil.getConnection();
			PreparedStatement pstmt_check=conn.prepareStatement(check_ctrlno_exist);
			PreparedStatement ifcanapply_check=conn.prepareStatement(if_can_apply);
	        PreparedStatement pstmt_checkoutnames=conn.prepareStatement(check_office_cust_name.toString());
		)
		{
			for(String ctrl_noItem:ctrlno_list){
				MccFromDB mDB=new MccFromDB();
	        	pstmt_check.setString(1, ctrl_noItem);
	        	ResultSet rs_check=pstmt_check.executeQuery();
	        	ifcanapply_check.setString(1, ctrl_noItem);
        		ResultSet rs_ifcanapply=ifcanapply_check.executeQuery();
        		if(!rs_check.next()) {
        			failedList.add(ctrl_noItem);
        			continue;
        		}
        		mDB.getEntry(rs_check);
        		if(null == mDB.getController_ver()){
	        		failedList.add(ctrl_noItem);
        			continue;
	        	}
        		if(rs_ifcanapply.next() && ("P".equals(rs_ifcanapply.getString("state")))){
        			failedList.add(ctrl_noItem);
        			continue;
        		}
        		pstmt_checkoutnames.setString(1, ctrl_noItem);
        		ResultSet rs_checkoutnames=null;
	        	rs_checkoutnames=pstmt_checkoutnames.executeQuery();
        		if(rs_checkoutnames.next()){
		        	mDB.setSale_company_gguid(rs_checkoutnames.getString("office_name"));
		        	mDB.setCust_company_gguid(rs_checkoutnames.getString("cust_name"));
	        	}
    			mp.generateMccPasswordFromMcc(mDB);
    			UUID uuid=UUID.randomUUID();
        		String gguid=uuid.toString().toLowerCase();
        		mp.setGguid(gguid);
        		int insertapplyno=passdetail.getMaxApply_no();
        		mp.setApply_no(insertapplyno+1);
        		boolean is_needapprove=false;
        		if("N".equals(mp.getIs_batch())){
        			is_needapprove = check_NeedApprove(ctrl_noItem, mp.getEnddate());
        		}else{
        			is_needapprove = this.check_NeedApprove(ctrl_noItem, mp.getBatch_enddate());
        		}
	        	if(mDB.getIs_pay().equals("Y") || is_needapprove) {
	    			String the_password=generatePass.generatePassword(mDB, mp);
	    			if(null==the_password){
	    				failedList.add(ctrl_noItem);
	        			continue;
	    			}
	    			mp.setState("Y");
	    			Calendar calendar1=Calendar.getInstance();
	    	        Date date_checktime=calendar1.getTime();
	    	        mp.setCheck_time(sdf.format(date_checktime));
	    	        mp.setCheck_user_id(user.getId());
	    	        mp.setThe_password(the_password);
	    	        mDB.setFile1desc(the_password);
	    	        jsmdi.insertMcc_password_item(mp);
	    	        
	    	        StringBuilder logstr=new StringBuilder("[您申请的密码已经被批准。[申请号:"+mp.getApply_no());
	    	        logstr.append("客户名:"+rs_checkoutnames.getString("cust_name"));
	    	        logstr.append("机床编号:"+mDB.getMachine_no());	
	    	        logstr.append(";批量;"+mp.getBatch_startdate()+"~"+mp.getBatch_enddate());
	    	        logstr.append(";间隔:"+mp.getBatch_period()+"月]]");
	    	        sl.setLog_memo(logstr.toString());
	    	        sl.setLog_type("6");
	    	        alrci.insertSysLogToDB(sl);
	    		}else{
	    			mp.setState("P");
	    			mp.setThe_password(null);
	    			mDB.setFile1desc(null);
	    			jsmdi.insertMcc_password_item(mp);
	    			StringBuilder smsstr=new StringBuilder(user.getUsername()+"申请了密码,请尽快批准。"+mp.getRequest_memo());
	    			st.setContent(smsstr.toString());
	    			st.setIs_sent("N");
	    			asai.insertSmstodoToDB(st);
	    		}
	        	mDB.setInput_user_id(mp.getApply_no());
	        	succeedList.add(mDB);
			}
		}
	}
	private boolean check_NeedApprove(String controller_no, String enddate) throws ParseException 
	{
		String applytimessql="select count(*) as applytimes from mcc_password where controller_no=?";
		Calendar c=Calendar.getInstance();
		c.add(Calendar.MONTH, 2);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
			
			
			//rs.absolute(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		
		return false;
	}
}














