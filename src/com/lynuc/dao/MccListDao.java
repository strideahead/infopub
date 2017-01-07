package com.lynuc.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import com.lynuc.bean.Mcc;
import com.lynuc.bean.MccPass_m;
import com.lynuc.bean.MccPassword;
import com.lynuc.bean.MccShowOnPage;
import com.lynuc.bean.SearchModel;
public interface MccListDao {
	/**
	 * 根据查询条件，查询MCC分页信息
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
	public Pager<MccShowOnPage> findMccList(SearchModel searchModel, int pageNum,
			int pageSize) throws ParseException;
	
	/*
	 * 手机页面查询mcc分页信息
	 */
	public Pager<MccPass_m> findMccList_m(String keystrm,int pageNum, int pageSize) throws SQLException, Exception;
	
	//插入控制器数据到mcc表
	public boolean insertMcc_item(Mcc m) throws SQLException;

	//删除控制器，删除mcc和mcc_password里controller_no相同的记录
	void delPasswordByApplyno(String controller_no, String card_no) throws SQLException;

	//插入mcc_password一条记录
	public void insertMcc_password_item(MccPassword mp) throws SQLException;
}

