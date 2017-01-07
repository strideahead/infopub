package com.lynuc.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.Iterator;

import org.apache.struts2.ServletActionContext;

public class ParseConfiguration {
	private String url;
	private List<String> lines = new ArrayList<String>();
	private List<String> strings ;
	
	public ParseConfiguration(String url) {
		super();
		this.url = url;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<String> getLines() {
		return lines;
	}
	public void setLines(List<String> lines) {
		this.lines = lines;
	}
	public List<String> getStrings() {
		return strings;
	}
	public void setStrings(List<String> strings) {
		this.strings = strings;
	}
	public void read()
	{
		String realPath=ServletActionContext.getServletContext().getRealPath("/");
		File fileB = new File( this .getClass().getResource( "" ).getPath());
		System.out.println("realpath"+realPath);
		System.out.println("fileB"+fileB);
		String path=System.getProperty("user.dir")+"\\config\\controllerVerList.properties";
		File file=new File(path);
		try {
			BufferedReader br=new BufferedReader(new FileReader(file));
			for(String line=br.readLine();line!=null&&line!="";line=br.readLine()){
				lines.add(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<lines.size();i++){
			String[] strs=lines.get(i).split("=");
			strings.add(strs[0]);
			System.out.println("strs["+i+"]="+strs[0]);
		}
	}
	public void parseProperties(String kind)
	{
		try {
			InputStream inStream = ParseConfiguration.class
					.getResourceAsStream(this.url);
			Properties prop = new Properties();
			prop.load(inStream);
			inStream.close();
			Set keys=prop.keySet();
			if(kind.trim().equals("key")) strings=new ArrayList<String>(keys);
			if(kind.trim().equals("value")){
				for (Iterator it = keys.iterator(); it.hasNext();){
			        String k = (String) it.next();
			        strings.add(prop.getProperty(k));
			    }
			}
		} catch (Exception e) {
			throw new RuntimeException("读取数据库配置文件异常！", e);
		}
	}
	public String readValueByKey(String key)
	{
		try {
			InputStream inStream = ParseConfiguration.class
					.getResourceAsStream(this.url);
			Properties prop = new Properties();
			prop.load(inStream);
			inStream.close();
			String value = prop.getProperty(key);
			return value;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}


























