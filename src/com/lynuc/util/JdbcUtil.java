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

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class JdbcUtil {
	private static String USERNAME;
	private static String PASSWORD;
	private static String DRIVER;
	private static String URL;

	private static Connection connection;
	private PreparedStatement pstmt;
	private ResultSet resultSet;
	private int totalRecord;
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
			InputStream inStream = JdbcUtil.class
					.getResourceAsStream("/jdbc.properties");
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
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
	public boolean updateByPreparedStatement(String sql, List<?> params) {
		boolean flag = false;
		int result = -1;
		Connection conn;
		try {
			conn = getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt = connection.prepareStatement(sql);
			int index = 1;
			if (params != null && !params.isEmpty()) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(index++, params.get(i));
				}
			}
			result = pstmt.executeUpdate();
			flag = result > 0 ? true : false;
			return flag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			releaseConn(); // 一定要释放资源		
		} 
		return flag;
	}
	public void releaseConn() {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public int totalDataAmount(String sql, List<?> params){
		try {
			connection=getConnection(); 
			pstmt=connection.prepareStatement(sql);
			int index = 1;
			pstmt = connection.prepareStatement(sql);
			if (params != null && !params.isEmpty()) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(index++, params.get(i));
				}
			}
			resultSet= pstmt.executeQuery();
			if(resultSet.next()){
				return resultSet.getInt("totalRecord");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.releaseConn(); // 一定要释放资源		
		}
		return 0;
	}
	public List<Map<String, Object>> findResult(String sql, List<?> params, int fromIndex, int pageSize
			) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		int index = 1;
		try {
			connection=getConnection(); // 获取数据库链接
			pstmt=connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			if (params != null && !params.isEmpty()) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(index++, params.get(i));
				}
			}
			resultSet = pstmt.executeQuery();
			RowSetFactory factory=RowSetProvider.newFactory();
			CachedRowSet cachedRs=factory.createCachedRowSet();
			cachedRs.setPageSize(pageSize);
			cachedRs.populate(resultSet,fromIndex);
			resultSet.last();
			this.setTotalRecord(resultSet.getRow());
			ResultSetMetaData metaData = resultSet.getMetaData();
			int cols_len = metaData.getColumnCount();
			/*resultSet.last();
			this.setTotalRecord(resultSet.getRow());
			ResultSetMetaData metaData = resultSet.getMetaData();
			int cols_len = metaData.getColumnCount();
			resultSet.absolute(fromIndex);
			int record=0;
			while (record<pageSize && !resultSet.isAfterLast()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 0; i < cols_len; i++) {
					String cols_name = metaData.getColumnName(i + 1);
					Object cols_value = resultSet.getObject(cols_name);
					if (cols_value == null && !cols_name.equals("apply_no")) {
						cols_value = "";
					}
					if (cols_value == null && cols_name.equals("controller_no")) {
						break;
					}
					map.put(cols_name, cols_value);
				}
				list.add(map);
				resultSet.next();
				record++;
			}*/
			while(cachedRs.next()){
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 0; i < cols_len; i++) {
					String cols_name = metaData.getColumnName(i + 1);
					Object cols_value = cachedRs.getObject(cols_name);
					if (cols_value == null && !cols_name.equals("apply_no")) {
						cols_value = "";
					}
					/*if (cols_value == null && cols_name.equals("controller_no")) {
						break;
					}*/
					map.put(cols_name, cols_value);
				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.releaseConn(); // 一定要释放资源
		}
		
		return list;
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
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	
}















