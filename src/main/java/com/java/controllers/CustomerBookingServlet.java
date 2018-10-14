package com.java.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.components.BookedFlight;
import com.java.components.FlightTemplate;
import com.java.components.User;
import com.java.exception.GeneralException;
import com.java.services.CustomerService;
import com.java.services.FlightService;

/**
 * Servlet implementation class CustomerBookingServlet
 */
@WebServlet(value = { "/booking/*", "/flightbook" })
public class CustomerBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	final FlightService flight_service;
	final CustomerService cust_service;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerBookingServlet() {
		super();
		flight_service = new FlightService();
		cust_service = new CustomerService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (request.getRequestURI().contains("booking")) {
			String[] pathInfo = request.getRequestURI().split("/");

			System.out.println(pathInfo[pathInfo.length - 1]); // should give the ID of the flight selected for
																// booing...
			Integer flightId = -1;
			try {
				flightId = Integer.parseInt(pathInfo[pathInfo.length - 1]);
			} catch (NumberFormatException e) {
				response.sendError(404);
				return;
			}
			FlightTemplate flight;
			try {
				flight = flight_service.getFlightById(flightId);
				if (flight != null) {
					if (flight.hasRoom()) {
						if (session != null)
							session.setAttribute("flight", flight);
						request.getRequestDispatcher("CustomerFlightBooking.jsp").forward(request, response);
					} else {
						request.setAttribute("errorMsg", "Selected flight is full.");
						request.getRequestDispatcher("CustomerFlightSearch.jsp").forward(request, response);
					}
				} else {
					response.sendError(404);
					return;
				}
			} catch (GeneralException e) {
				request.setAttribute("exceptionMsg", "Something went wrong: " + e.getMessage());
				request.getRequestDispatcher("ErrorPage.jsp").forward(request, response);
			}
		} else if (request.getRequestURI().contains("flightbook")) {
			if (session != null) {
				try {
					User user = (User) session.getAttribute("userdetails");

					if (user == null) {
						request.setAttribute("errorMsg", "You need to be logged in to book a flight.");
						request.getRequestDispatcher("CustomerLogin.jsp").forward(request, response);
					}

					FlightTemplate flight = (FlightTemplate) session.getAttribute("flight");
					if (flight == null) {
						request.setAttribute("errorMsg", "You need to pick a flight from the flight list.");
						request.getRequestDispatcher("CustomerFlightSearch.jsp").forward(request, response);

					}

					BookedFlight flightTemp = new BookedFlight();

					flightTemp.setUserId(user.getId());
					flightTemp.setFlightId(flight.getId());
					flightTemp.setPassengerName((String) request.getAttribute("full_name"));
					flightTemp.setAirline(flight.getAirline());
					flightTemp.setArrivalTime(flight.getArrivalTime());
					flightTemp.setDepartureTime(flight.getDepartureTime());
					flightTemp.setFrom(flight.getFrom());
					flightTemp.setTo(flight.getTo());
					flight_service.addBooking(flightTemp);

					request.setAttribute("successMsg", "You have successfully booked your flight");
					request.getRequestDispatcher("CustomerHome.jsp").forward(request, response);

				} catch (GeneralException e) {
					request.setAttribute("exceptionMsg", "Something went wrong: " + e.getMessage());
					request.getRequestDispatcher("ErrorPage.jsp").forward(request, response);
				}

			} else {
				request.getRequestDispatcher("CustomerLogin.jsp").forward(request, response);
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
