package com.lynuc.implDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import com.lynuc.bean.BaseAction;
import com.lynuc.bean.SmsTodo;
import com.lynuc.bean.User;
import com.lynuc.util.JdbcUtil;
/*
 * 插入短信记录到数据库 sms_todo
 */
public class AddSmstodoActionImpl{

	public void insertSmstodoToDB(SmsTodo st) throws SQLException
	{
		User user=(User)BaseAction.sessionMap.get("user");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		
		Calendar calendar=Calendar.getInstance();
        Date date=calendar.getTime();
        st.setBtime(sdf.format(date));
        
        UUID uuid=UUID.randomUUID();
		String gguid=uuid.toString().toLowerCase();
		st.setGguid(gguid);
		if(st.getMobile()==null && "".equals(st.getMobile())){
			st.setMobile(user.getMobile());
		}
		
		StringBuilder sqlMp=new StringBuilder("insert into sms_todo VALUES");
		sqlMp.append("(?,?,?,?,?)");

		try(
			Connection conn = JdbcUtil.getConnection();	
			PreparedStatement pstmt=conn.prepareStatement(sqlMp.toString())
		){
			conn.setAutoCommit(false);
			int index=1;
			pstmt.setString(index++, st.getGguid());
			pstmt.setString(index++, st.getMobile());
			pstmt.setString(index++, st.getContent());
			pstmt.setObject(index++, st.getIs_sent());
			pstmt.setString(index++, st.getBtime());
			pstmt.executeUpdate();
			conn.commit();
		}
		
	}
}
