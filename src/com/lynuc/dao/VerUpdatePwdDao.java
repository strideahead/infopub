package com.lynuc.dao;

import java.text.ParseException;
import com.lynuc.bean.VersionPassword;

public interface VerUpdatePwdDao {
	/**
	 * 根据查询条件，查询VersionUpdatePassword分页信息
	 * 
	 * @param searchModel
	 *            封装查询条件
	 * @param pageNum
	 *            查询第几页数据
	 * @param pageSize
	 *            每页显示多少条记录
	 * @return 查询结果
	 * @throws ParseException 
	 */
	public Pager<VersionPassword> findVerUpdatePwdList(VersionPassword searchModel, int pageNum,
			int pageSize) throws ParseException;
}
