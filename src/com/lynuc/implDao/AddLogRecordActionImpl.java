package com.lynuc.implDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import com.lynuc.bean.BaseAction;
import com.lynuc.bean.SysLog;
import com.lynuc.bean.User;
import com.lynuc.util.JdbcUtil;

public class AddLogRecordActionImpl{

	public void insertSysLogToDB(SysLog sl) throws SQLException
	{
		User user=(User)BaseAction.sessionMap.get("user");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		
		Calendar calendar=Calendar.getInstance();
        Date date=calendar.getTime();
        sl.setLog_time(sdf.format(date));
        
        UUID uuid=UUID.randomUUID();
		String gguid=uuid.toString().toLowerCase();
		sl.setGguid(gguid);
		sl.setLog_user(user.getUsername());
		StringBuilder sqlMp=new StringBuilder("insert into sys_log VALUES");
		sqlMp.append("(?,?,?,?,?,?)");
		try(
				Connection conn = JdbcUtil.getConnection();	
				PreparedStatement pstmt=conn.prepareStatement(sqlMp.toString())
		){
			conn.setAutoCommit(false);
			int index=1;
			pstmt.setString(index++, sl.getGguid());
			pstmt.setString(index++, sl.getLog_user());
			pstmt.setString(index++, sl.getLog_memo());
			pstmt.setObject(index++, sl.getLog_time());
			pstmt.setString(index++, sl.getLog_type());
			pstmt.setString(index++, sl.getUploadpag_gguid());
			pstmt.executeUpdate();
			conn.commit();
		}

	}
}
