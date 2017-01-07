package com.lynuc.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lynuc.bean.User;
/*@WebFilter(filterName="loginFilter",
	urlPatterns={"/*"},
	initParams={
			@WebInitParam(name="encoding",value="GBK"),
			@WebInitParam(name="loginPage",value="/login.jsp"),
			@WebInitParam(name="loginServlet",value="/LoginServlet")
			}
)
	*/
public class LoginFilter extends HttpServlet implements Filter {
	private static final long serialVersionUID = -3443700200252301075L;
	@Override
	public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) sRequest;        
        HttpServletResponse response = (HttpServletResponse) sResponse;        
        HttpSession session = request.getSession();        
        String url=request.getServletPath();    
        String contextPath=request.getContextPath();   
        //System.out.println("url========="+url);
        if(url.equals("")) url+="/index.jsp";  
        //若访问后台资源 过滤到login   
        /*if((url.startsWith("/")&&!url.startsWith("/login.jsp")&&!url.startsWith("/indexAction")
        		&&!url.startsWith("/loginm.jsp")&&!url.startsWith("/LoginServlet")&& !url.startsWith("/fonts")
        		&& !url.startsWith("/bootstrap")&& !url.startsWith("/index.jsp")
        		&& !url.startsWith("/js") && !url.startsWith("/css") && !url.startsWith("/images"))){ 
        	User user=(User)session.getAttribute("user");    
	        if(user==null){//转入管理员登陆页面    
	             response.sendRedirect(contextPath+"/indexAction");   
	             return;    
	        }    
        }  */ 
        
        if((!url.startsWith("/indexAction")&&!url.startsWith("/login.jsp")
        		&&!url.startsWith("/loginm.jsp")&&!url.startsWith("/LoginServlet")&& !url.startsWith("/fonts")
        		&& !url.startsWith("/bootstrap")&& !url.startsWith("/index.jsp")
        		&& !url.startsWith("/js") && !url.startsWith("/css") && !url.startsWith("/images"))){ 
        	User user=(User)session.getAttribute("user");    
	        if(user==null){//转入管理员登陆页面    
	             response.sendRedirect(contextPath+"/index.jsp");   
	             return;    
	        }    
        }  
        filterChain.doFilter(sRequest, sResponse);  
          
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
