package com.java.controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


/**
 * Servlet Filter implementation class LoggingFilter
 */
@WebFilter("/*")
public class LoggingFilter implements Filter {

	private File file;
	private BufferedWriter writer;
	
    /**
     * Default constructor. 
     */
    public LoggingFilter() {
        super();
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		try {
			writer.write("Application shutdown: "+LocalDateTime.now());
			writer.close();
		} catch (IOException e) {
			System.out.println("Logging file could not be closed: "+e.getMessage());
		}
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		writer.write(LocalDateTime.now() + ": "+ request.getRemoteAddr()+" - "+request.getParameterMap()+"\n");
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
//	@Test
	public void init(FilterConfig fConfig) throws ServletException {	 

		LocalDate date = LocalDate.now();
		file = new File("./Flight_booking_system_"+date.getDayOfMonth()+"_"+date.getMonthValue()+"_"+date.getYear()+".log");
		try {
			if(!file.exists())
				file.createNewFile();
			writer = new BufferedWriter(new FileWriter(file,false));
			writer.write("Application: "+fConfig.getServletContext().getContextPath()+" -- Deployed: "+LocalDateTime.now());
			
		} catch (IOException e) {
			System.out.println("Logging file could not be opened: "+e.getMessage());
		}
		
	}

}
