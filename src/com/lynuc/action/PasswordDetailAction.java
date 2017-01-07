package com.lynuc.action;

import java.util.HashMap;
import java.util.Map;

import com.lynuc.bean.BaseAction;
import com.lynuc.dao.PasswordDetailDao;
import com.lynuc.implDao.GetPasswordDetailImpl;
/*
 * ��action���� 1 ��������������ת������������ʾ���������ŵ�������Ϣ��2 �����������������ת����
 * ��Ӧҳ����passwordDetail.jsp��
 * ��������ż������ݿ�mcc_password�õ�ҳ������
 */
public class PasswordDetailAction extends BaseAction {

	private static final long serialVersionUID = 2480117000385901050L;
	
	public String execute()throws Exception{
		PasswordDetailDao passwordDetailDao = new GetPasswordDetailImpl();
		Map<String,Object>  map = new HashMap<String,Object>();
		String apply_Id=request.getParameter("apply_no");
		
		//loc�ַ��������Ǵ���ҳ�����������黹�Ǵӿ��������������б������������
		String loc=request.getParameter("loc");
		passwordDetailDao.getPasswordDetailByApplyID(Integer.parseInt(apply_Id),map);
		request.setAttribute("mccpassData", map);
		//�Ǵӿ��������������б������������
		if(loc != null)
		{
			request.setAttribute("loc", loc);
		}
		//is_revert�������������볷�����ܻ����������鹦�ܣ�����ǳ���loc�ض���null
		String is_revert=request.getParameter("is_revert");
		request.setAttribute("is_revert", is_revert);
		return SUCCESS;
	}

}
