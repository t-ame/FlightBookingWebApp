package com.java.controllers;

import java.io.IOException;
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
 * Servlet implementation class EditProfileServlet
 */
@WebServlet(value = { "/editprofile", "/saveprofile" })
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	final CustomerService cust_service;
	final AdminService admin_service;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditProfileServlet() {
		super();
		cust_service = new CustomerService();
		admin_service = new AdminService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		HttpSession session = request.getSession();
		if (session == null || session.getAttribute("userdetails") == null) {
			request.getRequestDispatcher("AdminLogin.jsp").forward(request, response);
			return;
		}
		User user = (User) session.getAttribute("userdetails");

		if (request.getRequestURI().contains("editprofile")) {

			Account account = user.getAccount();
			
			session.setAttribute("account", account);
			request.getRequestDispatcher("EditProfile.jsp").forward(request, response);

		} else if (request.getRequestURI().contains("saveprofile")) {

			try {
				if (user.isAdmin()) {
					admin_service.updateUser(user);
				} else {
					cust_service.updateUser(user);
				}
			} catch (GeneralException e) {
				request.setAttribute("exceptionMsg", "Something went wrong: " + e.getMessage());
				request.getRequestDispatcher("ErrorPage.jsp").forward(request, response);
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
