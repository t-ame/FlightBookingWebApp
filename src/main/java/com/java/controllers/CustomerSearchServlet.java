package com.java.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.components.FlightTemplate;
//import com.java.components.ScheduledFlight;
import com.java.exception.GeneralException;
import com.java.services.FlightService;

/**
 * Servlet implementation class CustomerSearchServlet
 */
@WebServlet("/flightsearch")
public class CustomerSearchServlet extends HttpServlet {

	FlightService service = new FlightService();

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerSearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		String departure = request.getParameter("departure");
		String arrival = request.getParameter("destination");
		LocalDate date = LocalDate.parse(request.getParameter("departure_date"),
				DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		System.out.println(date.toString());
		if (date.isBefore(LocalDate.now())) {
			request.setAttribute("errorMsg", "Departure date must be a future date!");
		}
		List<FlightTemplate> flightList = null;
		
		
		try {
			

			
			flightList = service.getAllFlightsBetween(departure, arrival, date);
//			System.out.println(flightList);
		} catch (DateTimeParseException | GeneralException e) {
			request.setAttribute("exceptionMsg", "Something went wrong: " + e.getMessage());
			request.getRequestDispatcher("ErrorPage.jsp").forward(request, response);
		}
		
		
		if (flightList != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("flights", flightList);
			request.setAttribute("flights", flightList);
		} else {
			request.setAttribute("errorMsg", "No flights found!!!");
		}
		request.getRequestDispatcher("CustomerFlightSearch.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
