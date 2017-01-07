package com.lynuc.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import com.lynuc.bean.BaseAction;
import com.lynuc.bean.Mcc;
import com.lynuc.bean.ProductCard;
import com.lynuc.implDao.AddControlCardItemImpl;
import com.lynuc.util.JdbcUtil;

public class CreateNewPasswordAjaxAction extends BaseAction 
{
	private static final long serialVersionUID = 8690725759379700763L;
	private String card_no;
	private String controller_no;
	
	public String getCard_no() {
		return card_no;
	}
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	public String getController_no() {
		return controller_no;
	}
	public void setController_no(String controller_no) {
		this.controller_no = controller_no;
	}
	public String execute() throws Exception{
		AddControlCardItemImpl daoImpl=new AddControlCardItemImpl();
		String card_no=request.getParameter("card_no");
		ProductCard productcard=daoImpl.getProduCardEntryByCardNo(this.getCard_no());
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(productcard.toString());
		return null;
	}
	public String getCtrlNoListFromMcc() throws Exception{
		Connection conn=JdbcUtil.getConnection();
		Statement stmt=conn.createStatement();
		String contrlNoListFromMcc="select distinct controller_no from mcc where controller_no is not null";
		ResultSet rs=stmt.executeQuery(contrlNoListFromMcc);
//		List contrlnoListFromMcc=new ArrayList();
//		while(rs.next()){
//			contrlnoListFromMcc.add(rs.getString("controller_no"));
//		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("{'helloworld':'1234'}");  
		return null;
	}
	//添加新控制器时拷贝controller_no信息到表格
	public void retrieveMccByCtrlno() throws Exception
	{
		AddControlCardItemImpl daoImpl=new AddControlCardItemImpl();
		Mcc mcc=daoImpl.getMccEntryByControllerNo(this.getController_no());
		if(mcc!=null){
			response.setCharacterEncoding("UTF-8");
			  //对象response
			  //写中文
			  //response.setHeader("Content-Type", "text/html;charset=UTF-8");
			response.getWriter().write(mcc.toString());
		}else{
			response.getWriter().write("{\"code\":\"FAIL\",\"err_message\":\"Error: The controller is  NOT exist.\"}");
		}
		
	}
}










