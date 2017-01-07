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
	 * ���ݲ�ѯ��������ѯMCC��ҳ��Ϣ
	 * 
	 * @param searchModel
	 *            ��װ��ѯ����
	 * @param pageNum
	 *            ��ѯ�ڼ�ҳ����
	 * @param pageSize
	 *            ÿҳ��ʾ��������¼
	 * @return ��ѯ���
	 * @throws ParseException 
	 */
	public Pager<MccShowOnPage> findMccList(SearchModel searchModel, int pageNum,
			int pageSize) throws ParseException;
	
	/*
	 * �ֻ�ҳ���ѯmcc��ҳ��Ϣ
	 */
	public Pager<MccPass_m> findMccList_m(String keystrm,int pageNum, int pageSize) throws SQLException, Exception;
	
	//������������ݵ�mcc��
	public boolean insertMcc_item(Mcc m) throws SQLException;

	//ɾ����������ɾ��mcc��mcc_password��controller_no��ͬ�ļ�¼
	void delPasswordByApplyno(String controller_no, String card_no) throws SQLException;

	//����mcc_passwordһ����¼
	public void insertMcc_password_item(MccPassword mp) throws SQLException;
}

