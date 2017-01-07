package com.lynuc.implDao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lynuc.bean.VersionPassword;
import com.lynuc.dao.Pager;
import com.lynuc.dao.VerUpdatePwdDao;
import com.lynuc.util.JdbcUtil;

public class JdbcSqlVerUpdateListImpl implements VerUpdatePwdDao {
	@SuppressWarnings("unused")
	public Pager<VersionPassword> findVerUpdatePwdList(VersionPassword searchModel, int pageNum,
			int pageSize) throws ParseException {
		JdbcUtil jdbcUtil = null;
		jdbcUtil=new JdbcUtil();
		//MccPassword mccData=new MccPassword();
		Pager<VersionPassword> result = null;
		// 存放查询参数
		List<Object> paramList = new ArrayList<Object>();
		String cardNo = searchModel.getCard_no();
		String state = searchModel.getState();
		String hardVer = searchModel.getHard_ver();
		String type = searchModel.getState();
		int constnum=1;
		String orderby="vp.apply_no";
		StringBuilder sqlVerUpdatePwd=new StringBuilder("select vp.*,m.controller_ver,m.machine_no,m.machine_type,co.company_name,cu.customer_name from version_password as vp ");
		sqlVerUpdatePwd.append(" inner join mcc as m on vp.mcc_gguid=m.gguid" ); 
		sqlVerUpdatePwd.append(" inner join company as co on co.gguid=m.sale_company_gguid" );
		sqlVerUpdatePwd.append(" inner join customer as cu on cu.company_gguid=co.gguid" );
		sqlVerUpdatePwd.append(" where 1=? ");
		sqlVerUpdatePwd.append(" order by ");
		sqlVerUpdatePwd.append(orderby);
		StringBuilder sqlVerUpdatePwdcount=new StringBuilder("select count(*) as totalRecord from version_password");
		sqlVerUpdatePwdcount.append(" where 1=? ");
		
		paramList.add(constnum);
		// 起始索引
		int fromIndex	= pageSize * (pageNum -1)+1;
		
		// 存放所有查询出的mcc对象
		List<VersionPassword> veruptpwd = new ArrayList<VersionPassword>();
		System.out.println(sqlVerUpdatePwd.toString());
		int totalRecord = 0;
		totalRecord = jdbcUtil.totalDataAmount(sqlVerUpdatePwdcount.toString(), paramList);
		System.out.println(totalRecord);
		// 获取查询的学生记录
		List<Map<String, Object>> vresult = null;
		System.out.println("fromIndex="+fromIndex+",pagesize="+pageSize);
		vresult = jdbcUtil.findResult(sqlVerUpdatePwd.toString(), paramList,fromIndex,pageSize);
		if (vresult != null) {
			System.out.println("verResult is not null");
			try {
				for (Map<String, Object> map : vresult) {
					VersionPassword s = new VersionPassword(map);
					veruptpwd.add(s);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//获取总页数
		int totalPage = totalRecord / pageSize;
		if(totalRecord % pageSize !=0){
			totalPage++;
		}
		// 组装pager对象
		result = new Pager<VersionPassword>(pageSize, pageNum, 
									totalRecord, totalPage, veruptpwd);
		return result;
	}
}
