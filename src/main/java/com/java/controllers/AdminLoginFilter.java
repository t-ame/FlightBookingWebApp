package com.java.controllers;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.components.User;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(value= {"/*"})
public class AdminLoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AdminLoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String uri = req.getServletPath().toLowerCase();
		
		System.out.println(uri + " "+ req.getServletPath());
		
		if(!(uri.contains("index") || uri.contains("login") || uri.contains("registration") || 
				uri.contains("error") || uri.contains("success")|| uri.contains(".jpg"))) {
			HttpSession session = req.getSession();
			User user = (User)session.getAttribute("userdetails");
			if(user != null) {
				boolean isAdmin = user.isAdmin();
				if(uri.contains("admin") && !isAdmin)
					req.getRequestDispatcher("AdminLogin.jsp").forward(req, resp);
			}
			if((session == null) || user == null) {
				
				req.getRequestDispatcher("CustomerLogin.jsp").forward(req, resp);
			}
		}
		
		
		chain.doFilter(request, response);
	}
	

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
