package com.java.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.components.User;
import com.java.exception.GeneralException;
import com.java.services.AdminService;
import com.java.services.CustomerService;

/**
 * Servlet implementation class CustomerLoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CustomerService customerservice = new CustomerService();
	private AdminService adminservice = new AdminService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("userName");
		String password = request.getParameter("password");
		
		System.out.println(username+" "+password);
		
		boolean isAdmin;
		if (request.getParameter("isAdmin").equalsIgnoreCase("true")) {
			isAdmin = true;
		} else {
			isAdmin = false;
		}

		request.setAttribute("isAdmin", isAdmin);
		
		User user = null;

		try {


			HttpSession session = request.getSession(true);
			session.setMaxInactiveInterval(0);
			session.setAttribute("username", username);
			session.setAttribute("isAdmin", isAdmin);
			
			if (isAdmin) {
				user = adminservice.getUser(username);
				System.out.println(user+" admin");
				if (user != null && user.getId() >0) {
					if (user.getAccount().getPassword().equalsIgnoreCase(password)) {
						request.getRequestDispatcher("./displayhome").include(request, response);
						request.getRequestDispatcher("AdminHome.jsp").forward(request, response);
					} else {
						request.setAttribute("errorMsg", "Invalid username or password!");
						request.getRequestDispatcher("./AdminLogin.jsp").forward(request, response);
					}
				} else {
					request.setAttribute("errorMsg", "Invalid username or password!");
					request.getRequestDispatcher("./AdminLogin.jsp").forward(request, response);
				}
			} else {
				user = customerservice.getUser(username);
				System.out.println(user+" user");
				if (user != null && user.getId() >0) {
					if (user.getAccount().getPassword().equalsIgnoreCase(password)) {
						request.getRequestDispatcher("./displayhome").include(request, response);
						request.getRequestDispatcher("CustomerHome.jsp").forward(request, response);
					} else {
						request.setAttribute("errorMsg", "Invalid username or password!");
						request.getRequestDispatcher("./CustomerLogin.jsp").forward(request, response);
					}
				} else {
					request.setAttribute("errorMsg", "Invalid username or password!");
					request.getRequestDispatcher("./CustomerLogin.jsp").forward(request, response);
				}
			}
			session.setAttribute("userdetails", user); // change other servlets using username then remove "username"
														// and "isAdmin"
		} catch (GeneralException e) {
			request.setAttribute("exceptionMsg", "Something went wrong: " + e.getMessage());
			request.getRequestDispatcher("./naverror").forward(request, response);
		}

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
