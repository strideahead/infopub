package com.lynuc.implDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.dispatcher.SessionMap;

import com.lynuc.dao.MccListDao;
import com.lynuc.bean.Mcc;
import com.lynuc.bean.MccPass_m;
import com.lynuc.bean.MccPassword;
import com.lynuc.bean.MccShowOnPage;
import com.lynuc.bean.SearchModel;
import com.lynuc.bean.SysLog;
import com.lynuc.dao.Pager;
import com.lynuc.util.JdbcUtil;
import com.lynuc.util.JdbcUtilInfoPubConfig;

public class JdbcSqlMccDaoImpl implements MccListDao {
	
	private static final  DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Override
	public Pager<MccShowOnPage> findMccList(SearchModel searchModel, int pageNum,int pageSize
			) throws ParseException {
		JdbcUtil jdbcUtil = new JdbcUtil();
		Pager<MccShowOnPage> result = null;
		
		// 存放查询参数
		List<Object> paramList = new ArrayList<Object>();
		//获取查询关键词
		String mccKeystr = searchModel.getKeyStr();
		//获取查询密码批准状态
		String state = searchModel.getState();
		//获取查询付款状态
		String isPay = searchModel.getIs_pay();
		//获取查询是控制器还是机床
		String machorcontrl = searchModel.getMachorcontrl();
		//获取查询过期状态
		String endDate = searchModel.getExpireState();
		//获取查询排序状态
		String orderingStr = searchModel.getOrderingWord();
		
		String company_type=searchModel.getCompany_type();
		
		String company_gguid=searchModel.getCompany_gguid();
		String[] array= new String[2];
	    array=orderingStr.split("__");
	    String ordering=array[0];
	    String ascordesc=array[1];
		int constnum=1;

		StringBuilder sqlMcc=new StringBuilder("select mp.the_password,ISNULL(mp.apply_no,0) as apply_no,m.controller_ver,m.machine_no,m.machine_type,"
				+ "m.controller_no,m.card_no,m.is_pay,co.company_name,cu.company_name as cust_company_name,mp.state,mp.is_forever,mp.enddate,"
				+ "m.input_user_id, mp.request_user_id from mcc as m left join  ");
		sqlMcc.append("(select * from mcc_password where apply_no in"
				+ "(select MAX(apply_no) from mcc_password group by controller_no)) as mp ");
		sqlMcc.append("on m.controller_no=mp.controller_no ");
		sqlMcc.append("left join company as co on co.gguid=m.sale_company_gguid ");
		sqlMcc.append("left join company as cu on cu.gguid=m.cust_company_gguid ");
		sqlMcc.append("where 1=? and m.controller_no is not null ");
		if(mccKeystr!="" && mccKeystr!=null) sqlMcc.append(" and (m.controller_no like '%"+mccKeystr+"%' or cu.company_name like '%"+mccKeystr+"%') ");
		if(!isPay.equals("-1")&& isPay!=null) sqlMcc.append(" and m.is_pay='"+isPay+"'"); 
		if(!state.equals("-1") && state!=null) sqlMcc.append(" and mp.state='"+state+"'"); 
		
		if(!"3".equals(company_type)){
			sqlMcc.append(" and (m.sale_company_gguid='"+company_gguid+"' or m.cust_company_gguid='"+company_gguid+"') "); 
		}
		if(!machorcontrl.equals("-1") && machorcontrl!=null){
			if(machorcontrl.equals("mach")){
				sqlMcc.append(" and machine_no!='' ");
			}else{
				sqlMcc.append(" and machine_no='' ");
			}
		}
		
		if(endDate!="-1" && endDate!=null) endDateFilter(endDate,sqlMcc);
		sqlMcc.append(" order by "+ordering+" "+ascordesc);
		System.out.println(sqlMcc.toString());
		paramList.add(constnum);
		// 起始索引
		int fromIndex	= pageSize * (pageNum -1)+1;

		// 存放所有查询出的mcc对象
		List<MccShowOnPage> smccList = new ArrayList<MccShowOnPage>();
		List<Map<String, Object>> mccResult = null;
		mccResult = jdbcUtil.findResult(sqlMcc.toString(), paramList,fromIndex,pageSize);
		//将单条记录Map转化成Bean对象
		if (mccResult != null) {
			for (Map<String, Object> map : mccResult) {
				MccShowOnPage s = new MccShowOnPage(map);
				smccList.add(s);
			}
		}
		int totalRecord=jdbcUtil.getTotalRecord();
		//获取总页数
		int totalPage = totalRecord / pageSize;
		if(totalRecord % pageSize !=0){
			totalPage++;
		}
		// 组装pager对象
		result = new Pager<MccShowOnPage>(pageSize, pageNum, totalRecord, totalPage, smccList);
		return result;
	}

	/*
	 * 这个函数作用是将注册码首页搜索过期字符串转化成能被数据库识别的查询条件
	 */
	private void endDateFilter(String endDateStr,StringBuilder sqlMcc){
		Calendar calendar=Calendar.getInstance();
		if("expired".equals(endDateStr)){
			Date date=calendar.getTime();
			sqlMcc.append(" and enddate<'"+sdf.format(date)+"' ");	
		}else if("in1mon".equals(endDateStr)){
			Date date=calendar.getTime();
			String back=sdf.format(date);
			calendar.add(Calendar.MONTH, 1);
			date=calendar.getTime();
			String front=sdf.format(date);
			sqlMcc.append(" and enddate between '"+back+"' and '"+front+"' ");		
		}else if("in3mon".equals(endDateStr)){
			calendar.add(Calendar.MONTH, 1);
			Date date=calendar.getTime();
			String back=sdf.format(date);
			
			calendar.add(Calendar.MONTH, 2);
			date=calendar.getTime();
			String front=sdf.format(date);
			sqlMcc.append(" and enddate between '"+back+"' and '"+front+"' ");	
		}else if("after3mon".equals(endDateStr)){
			calendar.add(Calendar.MONTH, 3);
			Date date=calendar.getTime();
			sqlMcc.append(" and enddate>'"+sdf.format(date)+"' ");	
		}
		else{
			
		}		
	}
	//密码信息插入到表mcc_password
	public void insertMcc_password_item(MccPassword mp) throws SQLException
	{
		StringBuilder sqlMp=new StringBuilder("insert into mcc_password VALUES");
		sqlMp.append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,?,?)");
		String updateMax="update table_applyno set max_apply_no="+(mp.getApply_no());
		try(
			Connection conn = JdbcUtil.getConnection();	
			Connection conn_maxno=JdbcUtilInfoPubConfig.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sqlMp.toString());
			java.sql.Statement statemax=conn_maxno.createStatement();
		){
			int index=1;
			pstmt.setObject(index++, mp.getGguid());
			pstmt.setObject(index++, mp.getMcc_gguid());
			pstmt.setObject(index++, mp.getController_no());
			pstmt.setObject(index++, mp.getCard_no());
			pstmt.setObject(index++, mp.getCard_content());
			pstmt.setObject(index++, mp.getIpc_sn());
			pstmt.setObject(index++, mp.getIs_old());
			pstmt.setObject(index++, mp.getApply_no());
			pstmt.setObject(index++, mp.getStartdate());
			pstmt.setObject(index++, mp.getEnddate());
			pstmt.setObject(index++, mp.getRequest_user_id());
			pstmt.setObject(index++, mp.getRequest_time());
			pstmt.setObject(index++, mp.getRequest_memo());
			pstmt.setObject(index++, mp.getState());
			pstmt.setObject(index++, mp.getCheck_time());
			pstmt.setObject(index++, mp.getCheck_user_id());
			pstmt.setObject(index++, mp.getCheck_memo());
			pstmt.setObject(index++, mp.getThe_password());
			pstmt.setObject(index++, mp.getPackage_gguid());
			pstmt.setObject(index++, mp.getIs_forever());
			pstmt.setObject(index++, mp.getIs_batch());
			pstmt.setObject(index++, mp.getBatch_startdate());
			pstmt.setObject(index++, mp.getBatch_enddate());
			pstmt.setObject(index++, mp.getBatch_period());
			pstmt.setObject(index++, mp.getBtime());
			pstmt.setObject(index++, mp.getCard_change_gguid());
			pstmt.setObject(index++, mp.getPassword_ver());
			pstmt.setObject(index++, mp.getController_ver());
			pstmt.setObject(index++, mp.getUser_number());
			pstmt.setObject(index++, mp.getEzdoctor_serial());
			pstmt.setObject(index++, mp.getPrompt());
			pstmt.executeUpdate();
			
			statemax.executeUpdate(updateMax);
			
		}
	}
	@Override
	public boolean insertMcc_item(Mcc m) throws SQLException
	{
		String sqlMp_check="select * from mcc where controller_no=?";
		StringBuilder sqlMp=new StringBuilder("insert into mcc VALUES(");
		sqlMp.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		try(
			Connection conn=JdbcUtil.getConnection(); // 获取数据库链接	
			PreparedStatement pstmt=conn.prepareStatement(sqlMp.toString());
			PreparedStatement pstmt_check=conn.prepareStatement(sqlMp_check);
		){
			pstmt_check.setString(1, m.getController_no());
			ResultSet rs=pstmt_check.executeQuery();	
			if(rs.next()){
				return false;
			}else{			
				int index = 1;
				pstmt.setObject(index++, m.getGguid());
				pstmt.setObject(index++, m.getMachine_no());
				pstmt.setObject(index++, m.getMachine_type());
				pstmt.setObject(index++, m.getController_no());
				pstmt.setObject(index++, m.getCard_no());
				pstmt.setObject(index++, m.getCard_content());
				pstmt.setObject(index++, m.getIpc_sn());
				pstmt.setObject(index++, m.getIs_old());
				pstmt.setObject(index++, m.getIs_pay());
				pstmt.setObject(index++, m.getOffice());
				pstmt.setObject(index++, m.getCust_name());
				pstmt.setObject(index++, m.getCust_duty());
				pstmt.setObject(index++, m.getCust_tel());
				pstmt.setObject(index++, m.getMac_no());
				pstmt.setObject(index++, m.getVer());
				pstmt.setObject(index++, m.getSetup_date());
				pstmt.setObject(index++, m.getHard_ver());
				pstmt.setObject(index++, m.getMemo());
				pstmt.setObject(index++, m.getModule_set());
				pstmt.setObject(index++, m.getFilecount());
				pstmt.setObject(index++, m.getFile1path());
				pstmt.setObject(index++, m.getFile1desc());
				pstmt.setObject(index++, m.getFile2path());
				pstmt.setObject(index++, m.getFile2desc());
				pstmt.setObject(index++, m.getFile3path());
				pstmt.setObject(index++, m.getFile3desc());
				pstmt.setObject(index++, m.getSale_company_gguid());
				pstmt.setObject(index++, m.getCust_company_gguid());
				pstmt.setObject(index++, m.getTotal_type());
				pstmt.setObject(index++, m.getMac_option());
				pstmt.setObject(index++, m.getLynuc_option());
				pstmt.setObject(index++, m.getPassword_ver());
				pstmt.setObject(index++, m.getController_ver());
				pstmt.setObject(index++, m.getMachine_dat());
				pstmt.setObject(index++, m.getEzdoctor_serial());
				pstmt.setObject(index++, m.getInput_user_id());
				pstmt.setObject(index++, m.getInput_time());
				pstmt.executeUpdate();
				return true;
			}
		}
	}
	/*
	 * 
	 */
	public void updateMyAddedMcc(Mcc m,String mcc_gguid) throws SQLException
	{
		StringBuilder sqlMcc=new StringBuilder("update mcc set ");
		sqlMcc.append("machine_no=?,total_type=?,machine_type=?,");
		sqlMcc.append("is_pay=?,sale_company_gguid=?,cust_company_gguid=?,ver=?,hard_ver=?,");
		sqlMcc.append("mac_option=?,lynuc_option=?,memo=?,module_set=? ");
		sqlMcc.append("where gguid=?");
		try(
			Connection conn=JdbcUtil.getConnection(); // 获取数据库链接
			PreparedStatement pstmt=conn.prepareStatement(sqlMcc.toString());	
		){
			int index=1;
			pstmt.setObject(index++, m.getMachine_no());
			pstmt.setObject(index++, m.getTotal_type());
			pstmt.setObject(index++, m.getMachine_type());
			pstmt.setObject(index++, m.getIs_pay());
			pstmt.setObject(index++, m.getSale_company_gguid());
			pstmt.setObject(index++, m.getCust_company_gguid());
			pstmt.setObject(index++, m.getVer());
			pstmt.setObject(index++, m.getHard_ver());
			pstmt.setObject(index++, m.getMac_option());
			pstmt.setObject(index++, m.getLynuc_option());
			pstmt.setObject(index++, m.getMemo());
			pstmt.setObject(index++, m.getModule_set());
			pstmt.setObject(index++, mcc_gguid);
			pstmt.executeUpdate();
		}
	}
	//删除controller_no记录，包括mcc和mcc_password记录
	@Override
	public void delPasswordByApplyno(String controller_no,String card_no) throws SQLException
	{
		StringBuilder sql=new StringBuilder("delete from mcc_password where controller_no=? and card_no=?");
		StringBuilder sqlMcc=new StringBuilder("delete from mcc where controller_no=? and card_no=?");
		System.out.println(controller_no+","+card_no);
		try(
			Connection conn = JdbcUtil.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql.toString());	
			//PreparedStatement pstmt2=conn.prepareStatement(sqlMcc.toString());		
		)
		{
			pstmt.setString(1, controller_no);
			pstmt.setString(2, card_no);
			pstmt.executeUpdate();	
			
			pstmt.setString(1, controller_no);
			pstmt.setString(2, card_no);
			pstmt.executeUpdate();	
			SysLog sl=new SysLog();
			sl.setLog_memo("删除[/"+controller_no+"/"+card_no+"]");
			sl.setLog_type("6");
			AddLogRecordActionImpl logins=new AddLogRecordActionImpl();
			logins.insertSysLogToDB(sl);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.lynuc.dao.MccListDao#findMccList_m(java.lang.String, int, int)
	 * 手机端注册码列表检索数据库生成手机端页面
	 * keystrm 搜索关键字，pageNum页数，pageSize每页显示多少记录
	 */
	public Pager<MccPass_m> findMccList_m(String keystrm,int pageNum,int pageSize) throws Exception 
	{
		String sqlMcc_m= "select mp.the_password,ISNULL(mp.apply_no,0) as apply_no,m.controller_ver,"
				 +"m.machine_no,m.machine_type,m.controller_no,m.card_no,m.is_pay,co.company_name,"
				  +" cu.company_name as cust_company_name,mp.state,mp.is_forever,mp.enddate,"
				+" m.input_user_id, mp.request_user_id from mcc as m left join  "
				+" (select * from mcc_password where apply_no in"
				+" (select MAX(apply_no) from mcc_password group by controller_no)) as mp "
				+" on m.controller_no=mp.controller_no "
				+" left join company as co on co.gguid=m.sale_company_gguid "
				+" left join company as cu on cu.gguid=m.cust_company_gguid ";
		if(keystrm!=null && !"".equals(keystrm.trim())){
			sqlMcc_m+=" where m.controller_no like '%"+keystrm+"%'";
		}
		sqlMcc_m+=" order by mp.apply_no desc";
		
		List<MccPass_m> mmList=new ArrayList<>();
		Pager<MccPass_m> result=new Pager<>();
		result.setCurrentPage(pageNum);
		result.setPageSize(pageSize);
		
		try (
			Connection conn=JdbcUtil.getConnection(); // 获取数据库链接
			//ResultSet.TYPE_SCROLL_SENSITIVE，结果集的记录可以上下滚动，数据库变化时，当前结果集随之变动，
			//ResultSet.CONCUR_READ_ONLY不能用结果集更新数据库中的表
			PreparedStatement pstmt=conn.prepareStatement(sqlMcc_m.toString(),ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs=pstmt.executeQuery();
		)
		{
			if(rs.next()){
				rs.last();
				int totalRecord=rs.getRow();
				result.setTotalRecord(rs.getRow());
				rs.absolute(pageSize * (pageNum -1)+1);
				
				int totalPage = totalRecord / pageSize;
				if(totalRecord % pageSize !=0){
					totalPage++;
				}
				result.setTotalPage(totalPage);
				int index=0;
				while(index<pageSize && !rs.isAfterLast()){
					MccPass_m  mm = new MccPass_m();
					mm.getEntry(rs);
					mmList.add(mm);
					
					rs.next();
					index++;
				}
				result.setDataList(mmList);
			}
			
		}
		return result;
	}
}














