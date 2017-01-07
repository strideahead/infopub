package com.lynuc.dao;

import com.lynuc.bean.TableProducts;

/*
 * 验证登录条件Dao层
 */
public interface UserLoginCheck {
	
	//判断用户输入的账号密码是否正确
	public boolean login(String loginAccount, String password) throws Exception;
	
	//将密码批准人、管理员、密码审核人等全局变量放到session里面
	public TableProducts checkout_TableProducts() throws Exception;
	
}
