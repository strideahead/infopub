package com.lynuc.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.core.Filter.Result;

public class JdbcUtilInfoPubConfig {
	private static String USERNAME;
	private static String PASSWORD;
	private static String DRIVER;
	private static String URL;

	static{
		loadConfig();
		try  
        {  
            Class.forName(DRIVER);  
        }  
        catch(ClassNotFoundException e)  
        {  
            throw new ExceptionInInitializerError(e);  
        }  
	}
	public static void loadConfig() {
		try {
			InputStream inStream = JdbcUtilInfoPubConfig.class
					.getResourceAsStream("/jdbcInfoPubConfig.properties");
			Properties prop = new Properties();
			prop.load(inStream);
			USERNAME = prop.getProperty("jdbc.username");
			PASSWORD = prop.getProperty("jdbc.password");
			DRIVER= prop.getProperty("jdbc.driver");
			URL = prop.getProperty("jdbc.url");
		} catch (Exception e) {
			throw new RuntimeException("读取数据库配置文件异常！", e);
		}
	}
	public JdbcUtilInfoPubConfig() {
		
	}
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
	
	public static void free(ResultSet rs,Statement st,Connection conn){
		try{  
	        if(rs!=null)  
	            rs.close();  
	        } catch (SQLException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	        finally  
	        {  
	            try  
	            {  
	                if(st!=null)  
	                	st.close();
	                     
	            } catch (SQLException e) {  
	                // TODO Auto-generated catch block  
	                e.printStackTrace();  
	            }  
	            finally  
	            {  
	                if(conn!=null)  
	                    try {  
	                        conn.close();  
	                    } catch (SQLException e) {  
	                        // TODO Auto-generated catch block  
	                        e.printStackTrace();  
	                    }  
	            }  
	        }  
		
	}
}















