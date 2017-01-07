package com.lynuc.dao;

import java.util.Map;

public interface MccDetailByControllerDao {

	public void getMccDetailByController_no(String controller_no,Map<String,Object> mccMap) throws Exception;
}
