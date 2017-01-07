package com.lynuc.bean;

import com.lynuc.def.Constant;

public class SearchModel {
	private String keyStr;
	private String state;
	private String is_pay;
	private String machorcontrl;
	private String expireState;
	private String orderingWord;
	private int pageSize;
	private String company_type;
	private String company_gguid;
	public SearchModel() {
		super();
		// TODO Auto-generated constructor stub
		this.keyStr=Constant.KEYSTR_SEARCH;
		this.state=Constant.AUTHORIZED_STATE;
		this.is_pay=Constant.IS_PAY;
		this.machorcontrl=Constant.TYPE_DEFAULT;
		this.expireState=Constant.ENDDATE_DEFAULT;
		this.orderingWord=Constant.ORDERING_DEFAULT;
		this.pageSize = Constant.DEFAULT_PAGE_SIZE;
		this.company_type = Constant.DEFAULT_COMPANY_TYPE;
	}

	public SearchModel(String keyStr, String state, String is_pay, String machorcontrl, String expireState,
			String orderingWord, int pageSize) {
		super();
		this.keyStr = keyStr;
		this.state = state;
		this.is_pay = is_pay;
		this.machorcontrl = machorcontrl;
		this.expireState = expireState;
		this.orderingWord = orderingWord;
		this.pageSize = pageSize;
	}

	public String getKeyStr() {
		return keyStr;
	}

	public String getState() {
		return state;
	}

	public String getIs_pay() {
		return is_pay;
	}

	public String getMachorcontrl() {
		return machorcontrl;
	}

	public String getExpireState() {
		return expireState;
	}

	public String getOrderingWord() {
		return orderingWord;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setKeyStr(String keyStr) {
		this.keyStr = keyStr;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setIs_pay(String is_pay) {
		this.is_pay = is_pay;
	}

	public void setMachorcontrl(String machorcontrl) {
		this.machorcontrl = machorcontrl;
	}

	public void setExpireState(String expireState) {
		this.expireState = expireState;
	}

	public void setOrderingWord(String orderingWord) {
		this.orderingWord = orderingWord;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getCompany_type() {
		return company_type;
	}

	public void setCompany_type(String company_type) {
		this.company_type = company_type;
	}

	public String getCompany_gguid() {
		return company_gguid;
	}

	public void setCompany_gguid(String company_gguid) {
		this.company_gguid = company_gguid;
	}

	@Override
	public String toString() {
		return "{\"keyStr\":\"" + keyStr + "\",\"state\":\"" + state + "\",\"is_pay\":\"" + is_pay
				+ "\",\"machorcontrl\":\"" + machorcontrl + "\",\"expireState\":\"" + expireState
				+ "\",\"orderingWord\":\"" + orderingWord + "\",\"pageSize\":\"" + pageSize + "\",\"company_type\":\""
				+ company_type + "\"}  ";
	}

}
