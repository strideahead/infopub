package com.lynuc.action;
import java.io.Serializable;

import javax.servlet.http.Cookie;

import com.lynuc.bean.BaseAction;
import com.lynuc.bean.SysLog;
import com.lynuc.bean.TableProducts;
import com.lynuc.bean.User;
import com.lynuc.dao.UserLoginCheck;
import com.lynuc.implDao.AddLogRecordActionImpl;
import com.lynuc.implDao.JdbcsqlUserLogImpl;

public class LoginAction extends BaseAction implements Serializable{

	private static final long serialVersionUID = 8092243976111868563L;
	public static TableProducts tableproduct;
	private String logAccount;
	private String password;
	public String getLogAccount() {
		return logAccount;
	}
	public void setLogAccount(String logAccount) {
		this.logAccount = logAccount;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
		
	}
	public String execute() throws Exception
	{
		//JdbcsqlUserLogImpl:ÅÐ¶ÏµÇÂ¼Ìõ¼þµÄÂß¼­²ã
		UserLoginCheck userLoginDao=new JdbcsqlUserLogImpl();
		
		if(userLoginDao.login(logAccount, password)){
			Cookie username=new Cookie("username", logAccount);
			username.setMaxAge(3600);
			response.addCookie(username);
			tableproduct=userLoginDao.checkout_TableProducts();
			SysLog sl=new SysLog();
			sl.setLog_memo("µÇÂ¼ÏµÍ³");
			sl.setLog_type("1");
			AddLogRecordActionImpl addlog=new AddLogRecordActionImpl();
			addlog.insertSysLogToDB(sl);
			//sessionMap.put("tableproducts", tableproduct);
			return SUCCESS;
		}
		request.setAttribute("error", "true");
		return ERROR;
	}
	public String logout()
	{
		User user=(User) sessionMap.get("user");
		if(user != null)
		{
			sessionMap.remove("user");
		}
		return SUCCESS;
	}
}
