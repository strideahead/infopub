package com.lynuc.dao;

import java.text.ParseException;
import com.lynuc.bean.VersionPassword;

public interface VerUpdatePwdDao {
	/**
	 * ���ݲ�ѯ��������ѯVersionUpdatePassword��ҳ��Ϣ
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
	public Pager<VersionPassword> findVerUpdatePwdList(VersionPassword searchModel, int pageNum,
			int pageSize) throws ParseException;
}
