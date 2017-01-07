package com.lynuc.action;

import java.sql.SQLException;

import com.lynuc.bean.BaseAction;
import com.lynuc.dao.MccListDao;
import com.lynuc.implDao.JdbcSqlMccDaoImpl;

public class DelPasswordItemAction extends BaseAction  {
	private static final long serialVersionUID = -1818351093026877469L;

	private String controller_no;
	private String card_no;
	
	public DelPasswordItemAction() {}

	public DelPasswordItemAction(String controller_no, String card_no) {
		super();
		this.controller_no = controller_no;
		this.card_no = card_no;
	}

	public String getController_no() {
		return controller_no;
	}

	public void setController_no(String controller_no) {
		this.controller_no = controller_no;
	}

	public String getCard_no() {
		return card_no;
	}

	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	
	@Override
	public String toString() {
		return "{\"controller_no\":\"" + controller_no + "\",\"card_no\":\"" + card_no + "\"}  ";
	}

	//É¾³ý¿ØÖÆÆ÷
	public String execute() throws SQLException{
		MccListDao mccListdao=new JdbcSqlMccDaoImpl();
		mccListdao.delPasswordByApplyno(controller_no, card_no);
		return SUCCESS;
	}

}
