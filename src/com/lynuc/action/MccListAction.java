package com.lynuc.action;

import com.lynuc.dao.Pager;
import com.lynuc.def.Constant;

import javax.servlet.http.Cookie;

import com.lynuc.bean.BaseAction;
import com.lynuc.bean.MccShowOnPage;
import com.lynuc.bean.SearchModel;
import com.lynuc.bean.User;
import com.lynuc.dao.MccListDao;
import com.lynuc.implDao.JdbcSqlMccDaoImpl;
/*
 * PC端打开注册码列表首页从数据库获取数据
 */
public class MccListAction extends BaseAction{
	private static final long serialVersionUID = 7531388076316945762L;
	//搜索关键字
	private  SearchModel searchmodel;

	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		//当添加控制器失败返回主页时，将添加控制器失败信息告知input_user, 
		//为true则说明AddControlCardItemAction添加控制器失败，跳转到此action
		String is_fail=(String) sessionMap.get("fail");
		if(is_fail!=null){
			sessionMap.remove("fail");
			request.setAttribute("fail", "true");
		}else{
			request.setAttribute("fail", "false");
		}
		//从本地cookie中获取每页条目数
		Cookie[] cookies = request.getCookies();
		String pageSize_mccpc="";
		Cookie cookie_pagesize=null;
		if(cookies!=null && cookies.length>0){
			for(int i=0;i<cookies.length;i++){
				Cookie c=cookies[i];
				if("pageSize_mccpc".equals(c.getName())){
					cookie_pagesize=c;
					pageSize_mccpc=cookie_pagesize.getValue();
				}
			}
		}
		
		MccListDao mccDao = new JdbcSqlMccDaoImpl();
		
		if(searchmodel==null) searchmodel=new SearchModel();
		String keyStr="",is_pay="-1",state="-1",machorcontrl="-1",expireState="-1";		
		//判断是否点击了搜索按钮，若不是，则是点击了翻页或从其他页面跳转过来，
		//区别开来是要不同渠道获取搜索关键词
		String isSearchBtn=request.getParameter("isSearchBtn");
		//若点击了
		if(isSearchBtn!=null&&isSearchBtn.equals("true")){
			keyStr=request.getParameter("keyStr");
			if(null!=keyStr){
				sessionMap.put("keyStr", keyStr);
				searchmodel.setKeyStr(keyStr);
			}
			is_pay=request.getParameter("is_pay");
			if(null!=is_pay){
				sessionMap.put("is_pay", is_pay);
				searchmodel.setIs_pay(is_pay);
			}
			state=request.getParameter("state");
			if(null!=state){
				sessionMap.put("state", state);
				searchmodel.setState(state);
			}
			machorcontrl=request.getParameter("machorcontrl");
			if(null!=machorcontrl){
				sessionMap.put("machorcontrl", machorcontrl);
				searchmodel.setMachorcontrl(machorcontrl);
			}
			expireState=request.getParameter("expireState");
			if(null!=expireState){
				sessionMap.put("expireState", expireState);
				searchmodel.setExpireState(expireState);
			}
		}else if(isSearchBtn!=null&&isSearchBtn.equals("false")){
			keyStr=(String) sessionMap.get("keyStr");
			if(keyStr!=null){
				searchmodel.setKeyStr(keyStr);
			}
			
			is_pay=(String) sessionMap.get("is_pay");
			if(is_pay!=null) {
				searchmodel.setIs_pay(is_pay);
			}
			
			state=(String) sessionMap.get("state");
			if(state!=null){
				searchmodel.setState(state);
			}
			
			machorcontrl=(String) sessionMap.get("machorcontrl");
			if(machorcontrl!=null){
				searchmodel.setMachorcontrl(machorcontrl);
			}
			
			expireState=(String) sessionMap.get("expireState");
			if(expireState!=null) {
				searchmodel.setExpireState(expireState);
			}
		}else{
			keyStr=(String) sessionMap.get("keyStr");
			if(keyStr!=null&&!keyStr.equals(""))
				searchmodel.setKeyStr(keyStr);
			
			state=(String) sessionMap.get("state");
			if(state!=null&&!state.equals(""))
				searchmodel.setState(state);
			
			is_pay=(String) sessionMap.get("is_pay");
			if(is_pay!=null&&!is_pay.equals(""))
				searchmodel.setIs_pay(is_pay);
			
			machorcontrl=(String) sessionMap.get("machorcontrl");
			if(machorcontrl!=null&&!machorcontrl.equals(""))
				searchmodel.setMachorcontrl(machorcontrl);
			
			expireState=(String) sessionMap.get("expireState");
			if(expireState!=null&&!expireState.equals(""))
				searchmodel.setExpireState(expireState);
		}
		
		String pageNumStr=request.getParameter("pageNum");
		int pageNum=1;
		if(pageNumStr!=null && !pageNumStr.equals("")){
			pageNum=Integer.parseInt(pageNumStr);
		}
		int pageSize = Constant.DEFAULT_PAGE_SIZE;  // 每页显示多少条记录
		String pageSizeStr = request.getParameter("pageSize");
		if(pageSizeStr!=null && !("".equals(pageSizeStr.trim()))||"-1".equals(pageSizeStr)){
			pageSize = Integer.parseInt(pageSizeStr);
			if("".equals(pageSize_mccpc)){
				Cookie cookie_pageSize_mccpc=new Cookie("pageSize_mccpc", pageSize+"");
				cookie_pageSize_mccpc.setMaxAge(3600);
				response.addCookie(cookie_pageSize_mccpc);
			}else{
				if(!pageSize_mccpc.equals(pageSize+"")){
					cookie_pagesize.setMaxAge(0);
					response.addCookie(cookie_pagesize);
					Cookie cookie_pageSize_mccpc=new Cookie("pageSize_mccpc", pageSize+"");
					cookie_pageSize_mccpc.setMaxAge(3600);
					response.addCookie(cookie_pageSize_mccpc);
				}
			}
		}else{
			if(!"".equals(pageSize_mccpc)){
				pageSize=Integer.parseInt(pageSize_mccpc);
			}
		}
		String orderingStr=request.getParameter("orderingWord");
		if(orderingStr!=null && orderingStr!=""){
			searchmodel.setOrderingWord(orderingStr);
		}else{
			if(sessionMap.get("orderingSqu") != null)
				searchmodel.setOrderingWord((String) sessionMap.get("orderingSqu"));
		}
		searchmodel.setPageSize(pageSize);
		sessionMap.put("orderingSqu", searchmodel.getOrderingWord());
		User user=(User) sessionMap.get("user");
		searchmodel.setCompany_type(user.getCompany_type());
		searchmodel.setCompany_gguid(user.getCompany_gguid());
		Pager<MccShowOnPage> result = mccDao.findMccList(searchmodel, pageNum, searchmodel.getPageSize());
		int totalRecord=result.getTotalRecord();

		request.setAttribute("keyStrstr", keyStr);
		request.setAttribute("ispaystr", is_pay);
		request.setAttribute("statestr", state);
		request.setAttribute("machorcontrlstr", machorcontrl);
		request.setAttribute("expireStatestr", expireState);
		
		request.setAttribute("pageSizeStr", pageSize);
		request.setAttribute("result", result);
		request.setAttribute("currentpage", result.getCurrentPage());
		request.setAttribute("totalpage", result.getTotalPage());
		request.setAttribute("orderingSqu", searchmodel.getOrderingWord());
		request.setAttribute("totalRecord", totalRecord);
		
		return SUCCESS;
	}
}
