package com.java.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.components.BookedFlight;
import com.java.exception.GeneralException;
import com.java.services.FlightService;

/**
 * Servlet implementation class ViewAllBookedFlights
 */
@WebServlet("/viewflights")
public class AdminViewBookedFlightsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	final FlightService flight_service;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminViewBookedFlightsServlet() {
        super();
        flight_service = new FlightService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		List<BookedFlight> flights = null;
		
		try {
			flights = flight_service.getAllBookings();
			HttpSession session = request.getSession();
			if (session != null)
				session.setAttribute("flights", flights);
		} catch (GeneralException e) {
			request.setAttribute("exceptionMsg", "Something went wrong: " + e.getMessage());
			request.getRequestDispatcher("ErrorPage.jsp").forward(request, response);
		}
		
		if(flights == null) {
			request.setAttribute("errorMsg", "No Booked flights.");
		}
		request.getRequestDispatcher("AdminDisplayBookedFlights.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
