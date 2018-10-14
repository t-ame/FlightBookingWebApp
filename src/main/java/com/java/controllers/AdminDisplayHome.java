package com.java.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.components.FlightTemplate;
import com.java.components.User;
import com.java.exception.GeneralException;
import com.java.services.FlightService;

/**
 * Servlet implementation class AdminDisplayHome
 */
@WebServlet("/displayhome")
public class AdminDisplayHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	final FlightService flight_service;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDisplayHome() {
        super();
        flight_service = new FlightService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		boolean isAdmin = (boolean)request.getAttribute("isAdmin");
		HttpSession session = request.getSession();
		User user=null;
		if (session != null)
			user = (User)session.getAttribute("userdetails"); // change other servlets using username then remove "username"
		
		
		List flights = null;
		
		try {
			
			if(isAdmin) {
				flights = flight_service.getAllFlightsToday();

				if(flights == null) {
					request.setAttribute("errorMsg", "No flights scheduled today.");
				}
			} else {
				flights = flight_service.getUserHistory(user);
				
				if(flights == null) {
					request.setAttribute("errorMsg", "You have no booked flights.");
				}
			}
			if (session != null)
				session.setAttribute("flights", flights);
		} catch (GeneralException e) {
			request.setAttribute("exceptionMsg", "Something went wrong: " + 
					getServletConfig().getServletName() + "//"+e.getMessage());
			request.getRequestDispatcher("ErrorPage.jsp").forward(request, response);
		}
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
