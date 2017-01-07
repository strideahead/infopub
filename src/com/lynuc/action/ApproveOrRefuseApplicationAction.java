package com.lynuc.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.lynuc.bean.BaseAction;
import com.lynuc.bean.User;
import com.lynuc.dao.PasswordDetailDao;
import com.lynuc.implDao.GetPasswordDetailImpl;
import com.lynuc.util.JdbcUtil;
import com.lynuc.util.PasswordEncryption;

/*
 * ������׼�˶�δ��׼����Ĳ������ܾ�����׼
 */
public class ApproveOrRefuseApplicationAction extends BaseAction{
	private static final long serialVersionUID = 2518505572956891034L;
	private static PasswordEncryption generatePass=new PasswordEncryption();
	private int apply_no;
	
	public int getApply_no() {
		return apply_no;
	}
	public void setApply_no(int apply_no) {
		this.apply_no = apply_no;
	}
	//��� '��׼|�ܾ��� ��ť�������׼��ܾ�����ǰ����approveApplication.jspҳ�������
	public String execute() throws Exception {
		PasswordDetailDao pwdDetailDao = new GetPasswordDetailImpl();
		Map<String,Object>  map = new HashMap<String,Object>();
		Map<String,Object>  mapOldpassword = new HashMap<String,Object>();
		String apply_Id=request.getParameter("apply_no");
		//��ȡ�����Ϊapply_Id��mcc_password��¼����
		pwdDetailDao.getPasswordDetailByApplyID(Integer.parseInt(apply_Id),map);
		//��ȡ��ͬcontroller_no�����һ����������mcc_password����
		pwdDetailDao.getOldpasswordDetailByApplyID(Integer.parseInt(apply_Id),mapOldpassword);
		request.setAttribute("mccpassData", map);
		request.setAttribute("latestMccPassword", mapOldpassword);
		return SUCCESS;
	}
	/*
	 * ��׼�ܾ�����ҳ������׼��ܾ�������action
	 */
	public String decideApplication() throws SQLException
	{
		
		String which_button_click=request.getParameter("which_btn_click");
		String apply_Id_str=request.getParameter("txt_apply_no");
		int apply_no=Integer.parseInt(apply_Id_str);
		//��׼��ע����׼ʱ���form��ȡ
		String check_memo=request.getParameter("ftb_check_memeo");
		String check_time=request.getParameter("txt_check_time");
		//��׼��Ϊ�û�
		User user=(User) sessionMap.get("user");
		int check_user_id=user.getId();
		StringBuilder sqlMp=new StringBuilder("update mcc_password set");
		sqlMp.append(" check_user_id=?,check_time=?,check_memo=?,state=?,the_password=? where apply_no=? ");
		try(
			Connection conn = JdbcUtil.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sqlMp.toString());	
		)
		{
			if(which_button_click.equals("0")){
				String password=generatePass.generatePassword(apply_no);
				pstmt.setInt(1, check_user_id);
				pstmt.setObject(2, check_time);
				pstmt.setObject(3, check_memo);
				pstmt.setObject(4, "Y");
				pstmt.setObject(5, password);
				pstmt.setObject(6, apply_no);
				pstmt.executeUpdate();
			}else{
				pstmt.setObject(1, check_user_id);
				pstmt.setObject(2, check_time);
				pstmt.setObject(3, check_memo);
				pstmt.setObject(4, "N");
				pstmt.setObject(5, null);
				pstmt.setObject(6, apply_no);
				
				pstmt.executeUpdate();
			}
		}
		
		return SUCCESS;
		
	}
}
