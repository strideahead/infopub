package com.lynuc.implDao;

import com.lynuc.addHibernateFile.HibernateSessionFactory;
import com.lynuc.bean.User;
import com.lynuc.dao.Pager;
import com.lynuc.util.JdbcUtil;
import com.lynuc.PO.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.*;
public class CheckOutItemsFromDBImpl {
    private Session session;
    private Transaction transaction;
    public CheckOutItemsFromDBImpl(){}
    
	public Pager<CompanyPO> checkoutCompanies(CompanyPO searchModel, int pageNum,int pageSize)
    {
		session=HibernateSessionFactory.getSession();
    	Pager<CompanyPO> result = null;
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
    	String searchKeyStr = searchModel.getAddress();
		String company_typeStr = searchModel.getCompany_type();
		
    	StringBuilder hqlsql=new StringBuilder("from CompanyPO where 1=1 ");
    	StringBuilder countHql = new StringBuilder(
				"select count(id)  from CompanyPO where 1=1 ");
    	if (searchKeyStr != null && !searchKeyStr.equals("")) {
    		hqlsql.append(" and( company_name like :searchStr ");
    		hqlsql.append(" or address like :searchStr ");
    		hqlsql.append(" or contacter like :searchStr ");
    		hqlsql.append(" or tel like :searchStr ");
    		hqlsql.append(" or fax like :searchStr )");
    		
    		countHql.append(" and( company_name like :searchStr ");
    		countHql.append(" or address like :searchStr ");
    		countHql.append(" or contacter like :searchStr ");
    		countHql.append(" or tel like :searchStr ");
    		countHql.append(" or fax like :searchStr )");
			paramMap.put("searchStr", "%" + searchKeyStr + "%");
		}
    	
    	if (company_typeStr != null && !company_typeStr.equals("") && !"-1".equals(company_typeStr)) {
    		hqlsql.append(" and company_type= :company_type ");
    		countHql.append(" and company_type= :company_type ");
			paramMap.put("company_type", company_typeStr);
		}
		int fromIndex	= pageSize * (pageNum -1);
		
		List<CompanyPO> companyList = new ArrayList<CompanyPO>();
		try {			
			Query hqlQuery = session.createQuery(hqlsql.toString());
			Query countHqlQuery = session.createQuery(countHql.toString());
			setQueryParams(hqlQuery, paramMap);
			setQueryParams(countHqlQuery, paramMap);
			hqlQuery.setFirstResult(fromIndex);
			hqlQuery.setMaxResults(pageSize);
			
			companyList = hqlQuery.list();			
			List<?> countResult = countHqlQuery.list();
			int totalRecord = ((Number)countResult.get(0)).intValue();
			
			int totalPage = totalRecord / pageSize;
			if(totalRecord % pageSize !=0){
				totalPage++;
			}
			
			result = new Pager<CompanyPO>(pageSize, pageNum, 
							totalRecord, totalPage, companyList);
			session.clear();
		} catch (Exception e) {
		} 
    	return result;
    }
    public void delCompanyByGguid(String gguid)
    {
    	session=HibernateSessionFactory.getSession();
    	CompanyPO cp=new CompanyPO();
    	transaction=session.beginTransaction();
    	try {	    
	    	cp=(CompanyPO)session.get(CompanyPO.class, gguid);
	    	session.delete(cp);
	    	transaction.commit();
    	}catch (Exception e) {
			throw new RuntimeException("查询所有数据异常！", e);
		} 
    }
    public CompanyPO getCompanyByGguid(String gguid)
    {
    	session=HibernateSessionFactory.getSession();
    	transaction=session.beginTransaction();
    	CompanyPO cp=null;
    	try {
	    	cp=(CompanyPO)session.get(CompanyPO.class, gguid);
	    	transaction.commit();
	    	session.clear();
    	}catch (Exception e) {
		} 
    	return cp;
    }
    public Pager<User> checkoutUsers(User searchModel, int pageNum,int pageSize)
    {
    	session=HibernateSessionFactory.getSession();
    	Pager<User> result = null;
		// 存放查询参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
    	String searchKeyStr = searchModel.getUsername();
		
    	StringBuilder hqlsql=new StringBuilder("select su.id id,su.usercode usercode,su.username username,"
    			+ "su.password password ,su.permission permission,su.isvalid isvalid,su.complaint_permission complaint_permission,"
    			+ "su.email email,su.mobile mobile,su.password_type password_type,su.password_time password_time,su.user_type user_type,"
    			+ "co.company_name company_name from sys_user as su left join company as co ");
    	hqlsql.append(" on co.gguid=su.belong_company_gguid where 1=1 ");
    	
		StringBuilder countHql=new StringBuilder("select count(*) from sys_user as su left join company as co ");
		countHql.append("on co.gguid=su.belong_company_gguid where 1=1 ");

    	if (searchKeyStr != null && !searchKeyStr.trim().equals("")) {
    		hqlsql.append(" and( su.usercode like :searchStr ");
    		hqlsql.append(" or su.username like :searchStr ");
    		hqlsql.append(" or su.mobile like :searchStr ");
    		hqlsql.append(" or su.email like :searchStr ");
    		hqlsql.append(" or co.company_name like :searchStr  )");
    		hqlsql.append(" order by id asc");
    		
    		countHql.append(" and( su.usercode like :searchStr ");
    		countHql.append(" or su.username like :searchStr ");
    		countHql.append(" or su.mobile like :searchStr ");
    		countHql.append(" or su.email like :searchStr ");
    		countHql.append(" or co.company_name like :searchStr  )");
    		
			paramMap.put("searchStr", "%" + searchKeyStr + "%");
		}
    	
		int fromIndex	= pageSize * (pageNum -1);
		try {	    	
	    	SQLQuery hqlQuery=session.createSQLQuery(hqlsql.toString());
	    	hqlQuery.addScalar("id",IntegerType.INSTANCE);
	    	hqlQuery.addScalar("usercode",StringType.INSTANCE);
	    	hqlQuery.addScalar("username",StringType.INSTANCE);
	    	hqlQuery.addScalar("password",StringType.INSTANCE);
	    	hqlQuery.addScalar("permission",StringType.INSTANCE);
	    	hqlQuery.addScalar("isvalid",StringType.INSTANCE);
	    	hqlQuery.addScalar("complaint_permission",StringType.INSTANCE);
	    	hqlQuery.addScalar("email",StringType.INSTANCE);
	    	hqlQuery.addScalar("mobile",StringType.INSTANCE);
	    	hqlQuery.addScalar("password_type",StringType.INSTANCE);
	    	hqlQuery.addScalar("password_time",DateType.INSTANCE);
	    	hqlQuery.addScalar("user_type",StringType.INSTANCE);
	    	hqlQuery.addScalar("company_name",StringType.INSTANCE);
			Query countHqlQuery = session.createSQLQuery(countHql.toString());
			setQueryParams(hqlQuery, paramMap);
			setQueryParams(countHqlQuery, paramMap);
			hqlQuery.setFirstResult(fromIndex);
			
			hqlQuery.setMaxResults(pageSize);
			
			List<Object[]> list =hqlQuery.list();
			List<User> userList = new ArrayList<>();
			
			for(Object[] ele:list){
				User user=new User();
				user.setId((Integer)ele[0]);
				user.setUsercode((String) ele[1]);
				user.setUsername((String) ele[2]);
				user.setPassword((String) ele[3]);
				user.setPermission((String) ele[4]);
				user.setIsvalid((String) ele[5]);
				user.setComplaint_permission((String) ele[6]);
				user.setEmail((String) ele[7]);
				user.setMobile((String) ele[8]);
				user.setPassword_type((String) ele[9]);
				//user.setPassword_time((Date)ele[10]);
				user.setUser_type((String) ele[11]);
				user.setCompany_name((String) ele[12]);
				userList.add(user);
			}
			// 获取总计条数
			List<?> countResult = countHqlQuery.list();
			int totalRecord = ((Number)countResult.get(0)).intValue();
			
			//获取总页数
			int totalPage = totalRecord / pageSize;
			if(totalRecord % pageSize !=0){
				totalPage++;
			}
			
			// 组装pager对象
			result = new Pager<User>(pageSize, pageNum, 
							totalRecord, totalPage, userList);
		} catch (Exception e) {
		} 
    	return result;
    }
    public void deleteUserInfo(int id) throws SQLException
    {
    	String deleteUser="delete from sys_user where id="+id;
    	try(
    		Connection conn=JdbcUtil.getConnection();
    		Statement state=conn.createStatement();	
    	)
    	{
    		state.executeUpdate(deleteUser);
    	}
    }
    public Pager<SysLogPO> checkoutSyslogs(SysLogPO searchModel, int pageNum,int pageSize)
    {
    	session=HibernateSessionFactory.getSession();
    	Pager<SysLogPO> result = null;
		
		// 存放查询参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
    	StringBuilder hqlsql=new StringBuilder("from SysLogPO where 1=1 ");
    	StringBuilder countHql = new StringBuilder(
				"select count(gguid)  from SysLogPO where 1=1 ");
    	String loguser_search=searchModel.getLog_user();
    	if (loguser_search != null && !loguser_search.equals("") && !loguser_search.equals("-1")) {
    		hqlsql.append(" and log_user = :loguser_search ");
    		countHql.append(" and log_user = :loguser_search ");
			paramMap.put("loguser_search", loguser_search);
		}
    	
    	String logtype_search=searchModel.getLog_type();
    	if (logtype_search != null && !logtype_search.equals("") && !logtype_search.equals("-1")) {
    		hqlsql.append(" and log_type = :logtype_search ");
    		countHql.append(" and log_type = :logtype_search ");
			paramMap.put("logtype_search", logtype_search);
		}
    	hqlsql.append(" order by log_time desc");
		int fromIndex	= pageSize * (pageNum -1);
		
		List<SysLogPO> syslogList = new ArrayList<>();
		try {			
			Query hqlQuery = session.createQuery(hqlsql.toString());
			Query countHqlQuery = session.createQuery(countHql.toString());
			setQueryParams(hqlQuery, paramMap);
			setQueryParams(countHqlQuery, paramMap);
			hqlQuery.setFirstResult(fromIndex);
			
			hqlQuery.setMaxResults(pageSize);
			
			syslogList = hqlQuery.list();
			
			List<?> countResult = countHqlQuery.list();
			int totalRecord = ((Number)countResult.get(0)).intValue();
			
			//获取总页数
			int totalPage = totalRecord / pageSize;
			if(totalRecord % pageSize !=0){
				totalPage++;
			}
			
			// 组装pager对象
			result = new Pager<SysLogPO>(pageSize, pageNum, 
							totalRecord, totalPage, syslogList);
		} catch (Exception e) {
		} 
    	return result;
    }
    public Pager<VersionPO> checkoutVersions(VersionPO searchModel, int pageNum,int pageSize)
    {
    	session=HibernateSessionFactory.getSession();
    	Pager<VersionPO> result = null;
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
    	StringBuilder hqlsql=new StringBuilder("from VersionPO where 1=1 ");
    	StringBuilder countHql = new StringBuilder(
				"select count(gguid)  from VersionPO where 1=1 ");
    	String versionname_search=searchModel.getVersion_name();
    	if (versionname_search != null && !versionname_search.equals("") && !versionname_search.equals("-1")) {
    		hqlsql.append(" and version_name like :versionname_search ");
    		countHql.append(" and version_name like :versionname_search ");
			paramMap.put("versionname_search", '%'+versionname_search+'%');
		}
    	
    	String issoft_search=searchModel.getIs_soft();
    	if (issoft_search != null && !issoft_search.equals("") && !issoft_search.equals("-1")) {
    		hqlsql.append(" and is_soft = :issoft_search ");
    		countHql.append(" and is_soft = :issoft_search ");
			paramMap.put("issoft_search", issoft_search);
		}
    	
    	String ispublish_search=searchModel.getIs_publish();
    	if (ispublish_search != null && !ispublish_search.equals("") && !ispublish_search.equals("-1")) {
    		hqlsql.append(" and is_publish = :ispublish_search ");
    		countHql.append(" and is_publish = :ispublish_search ");
			paramMap.put("ispublish_search", ispublish_search);
		}
    	
    	hqlsql.append(" order by version_name desc");
		int fromIndex	= pageSize * (pageNum -1);
		
		List<VersionPO> versionList = new ArrayList<>();
		try {
			Query hqlQuery = session.createQuery(hqlsql.toString());
			Query countHqlQuery = session.createQuery(countHql.toString());
			setQueryParams(hqlQuery, paramMap);
			setQueryParams(countHqlQuery, paramMap);
			hqlQuery.setFirstResult(fromIndex);
			
			hqlQuery.setMaxResults(pageSize);
			
			versionList = hqlQuery.list();
			
			List<?> countResult = countHqlQuery.list();
			int totalRecord = ((Number)countResult.get(0)).intValue();
			
			int totalPage = totalRecord / pageSize;
			if(totalRecord % pageSize !=0){
				totalPage++;
			}
			
			result = new Pager<VersionPO>(pageSize, pageNum, 
							totalRecord, totalPage, versionList);
		} catch (Exception e) {
		} 
    	return result;
    }
    public VersionPO getVersionByGguid(String gguid)
    {
    	session=HibernateSessionFactory.getSession();
    	transaction=session.beginTransaction();
    	try {	    	
	    	VersionPO cp=(VersionPO)session.get(VersionPO.class, gguid);
	    	transaction.commit();
	    	return cp;
    	}catch (Exception e) {
		}
		return null; 
    }
    public void delVersionByGguid(String gguid)
    {
    	session=HibernateSessionFactory.getSession();
    	VersionPO vp=new VersionPO();
    	transaction=session.beginTransaction();
    	try {
	    	vp=(VersionPO)session.get(VersionPO.class, gguid);
	    	session.delete(vp);
	    	transaction.commit();
    	}catch (Exception e) {
		} 
    }
	private Query setQueryParams(Query query, Map<String, Object> paramMap){
		if(paramMap != null && !paramMap.isEmpty()){
			for(Map.Entry<String, Object> param : paramMap.entrySet()){
				query.setParameter(param.getKey(), param.getValue());
			}
		}
		return query;
	}
}