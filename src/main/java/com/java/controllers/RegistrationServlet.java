package com.java.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.components.Account;
import com.java.components.User;
import com.java.exception.GeneralException;
import com.java.services.AdminService;
import com.java.services.CustomerService;

/**
 * Servlet implementation class CustomerRegistrationServlet
 */
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CustomerService customerservice = new CustomerService();
	private AdminService adminservice = new AdminService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		To convert java.util.Date to java.sql.Date, just use its constructor.
//		preparedStatement.setDate(0, new java.sql.Date(date.getTime()));
		
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String username = request.getParameter("userName");
		String password = request.getParameter("password");
		boolean isAdmin;
		if (request.getParameter("isAdmin").equalsIgnoreCase("true")) {
			isAdmin = true;
		} else {
			isAdmin = false;
		}

		Account account = new Account();

		System.out.println(LocalDate.parse(request.getParameter("date_of_birth"),
				DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		
		account.setDateOfBirth(LocalDate.parse(request.getParameter("date_of_birth"),
				DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		account.setFirstName(firstName);
		account.setLastName(lastName);
		account.setPassword(password);
		account.setUserName(username);
		account.setConcatAddress(request.getParameter("street_address") +"!"+request.getParameter("address_line_2")+"!"+
					request.getParameter("state")+" "+request.getParameter("country")+" "+request.getParameter("zip_code"));
		if (request.getParameter("gender").equalsIgnoreCase("female")) {
			account.setGender(Account.Gender.FEMALE);
		} else {
			account.setGender(Account.Gender.MALE);
		}

		User user = new User(isAdmin, account);

		try {
			if (isAdmin) {
				adminservice.addUser(user);
				request.getRequestDispatcher("AdminLogin.jsp").forward(request, response);
			} else {
				customerservice.addUser(user);
				request.getRequestDispatcher("CustomerLogin.jsp").forward(request, response);
			}
		} catch (GeneralException e) {
			request.setAttribute("exceptionMsg", "Something went wrong: " + e.getMessage());
			if (isAdmin)
				request.getRequestDispatcher("AdminRegistration.jsp").forward(request, response);
			else
				request.getRequestDispatcher("CustomerRegistration.jsp").forward(request, response);
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
