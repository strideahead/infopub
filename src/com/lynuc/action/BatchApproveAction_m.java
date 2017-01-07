package com.lynuc.action;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.lynuc.bean.BaseAction;
import com.lynuc.bean.MccShowOnPage;
import com.lynuc.bean.User;
import com.lynuc.util.JdbcUtil;
import com.lynuc.util.PasswordEncryption;

public class BatchApproveAction_m extends BaseAction implements Serializable {

	private static final long serialVersionUID = 650914662190693219L;
	private static PasswordEncryption generatePass=new PasswordEncryption();
	public String execute() throws Exception
	{
		String pendingApplysql = "select mp.the_password,ISNULL(mp.apply_no,0) as apply_no,m.controller_ver,"
				+"m.machine_no,m.machine_type,m.controller_no,m.card_no,m.is_pay,co.company_name,"
				+" cu.company_name as cust_company_name,mp.state,mp.is_forever,mp.enddate,"
				+" m.input_user_id,mp.request_user_id from mcc as m left join mcc_password as mp "
				+" on m.controller_no=mp.controller_no "
				+" left join company as co on co.gguid=m.sale_company_gguid "
				+" left join company as cu on cu.gguid=m.cust_company_gguid "
				+" where mp.state='P' and mp.apply_no > 0 order by mp.apply_no desc";
		
		List<MccShowOnPage> mmList=new ArrayList<>();
		try (
			Connection conn=JdbcUtil.getConnection(); // 获取数据库链接
			PreparedStatement pstmt=conn.prepareStatement(pendingApplysql.toString());
			ResultSet rs=pstmt.executeQuery();
		)
		{
			while(rs.next()){
				MccShowOnPage  mm = new MccShowOnPage();
				mm.getEntry(rs);
				mmList.add(mm);
			}	
		}
		request.setAttribute("mmList", mmList);
		request.setAttribute("totalRecord", mmList.size());
		return SUCCESS;
	}
	public String batchApprove() throws SQLException
	{
		String batAppro_applynos=request.getParameter("hidden_selectValue");
		if(batAppro_applynos==null || "".equals(batAppro_applynos)) return SUCCESS;
		String approve_memo=request.getParameter("ftxt_memo");
		if(approve_memo==null) approve_memo="";
		batAppro_applynos=batAppro_applynos.replaceAll("\\s{1,}", "");
		//if(batAppro_applynos.startsWith(",")) batAppro_applynos.replaceFirst(",", "");
		String[] applyno_list=batAppro_applynos.split(",");
		
		User user=(User) sessionMap.get("user");
		int check_user_id=user.getId();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		
		Calendar calendar=Calendar.getInstance();
        Date date=calendar.getTime();
        
        StringBuilder sqlMp=new StringBuilder("update mcc_password set");
		sqlMp.append(" check_user_id=?,check_time=?,state=?,the_password=?,check_memo=? where apply_no=? ");
		String regex = "^\\d+$";
		try(
			Connection conn=JdbcUtil.getConnection(); // 获取数据库链接
			PreparedStatement pstmt=conn.prepareStatement(sqlMp.toString());	
		)
		{
			for(String apply_nostr:applyno_list){
				if(null!=apply_nostr && !"".equals(apply_nostr)){
					if(!apply_nostr.matches(regex)) continue;
					int apply_no=Integer.parseInt(apply_nostr);
					String password=generatePass.generatePassword(apply_no);
					pstmt.setInt(1, check_user_id);
					pstmt.setObject(2, sdf.format(date));
					pstmt.setObject(3, "Y");
					pstmt.setObject(4, password);
					pstmt.setObject(5, approve_memo);
					pstmt.setObject(6, apply_no);
					pstmt.executeUpdate();
				}
			}
		}
		return SUCCESS;
	}
	
}
