package com.lynuc.dao;

import com.lynuc.bean.TableProducts;

/*
 * ��֤��¼����Dao��
 */
public interface UserLoginCheck {
	
	//�ж��û�������˺������Ƿ���ȷ
	public boolean login(String loginAccount, String password) throws Exception;
	
	//��������׼�ˡ�����Ա����������˵�ȫ�ֱ����ŵ�session����
	public TableProducts checkout_TableProducts() throws Exception;
	
}
