package com.java.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class NavServlet
 */
@WebServlet(value = { "/navaccount", "/navhome", "/naverror", "/navregistration", "/navlogin" })
public class NavServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NavServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		HttpSession session = request.getSession();
		boolean isAdmin = false;
		if (request.getAttribute("isAdmin") != null || request.getParameter("isAdmin") != null) {
			if (((boolean) request.getAttribute("isAdmin"))
					|| request.getParameter("isAdmin").equalsIgnoreCase("true")) {
				isAdmin = true;
			} else {
				isAdmin = false;
			}
		}

		if (uri.contains("account")) {
			if (session == null || session.getAttribute("userdetails") == null) {
				if (isAdmin) {
					request.getRequestDispatcher("AdminLogin.jsp").forward(request, response);
					return;
				} else {
					request.getRequestDispatcher("CustomerLogin.jsp").forward(request, response);
					return;
				}
			}
			
			request.getRequestDispatcher("EditProfile.jsp").forward(request, response);

		} else if (uri.contains("home")) {
			if (session == null || session.getAttribute("userdetails") == null) {

				request.getRequestDispatcher("CustomerLogin.jsp").forward(request, response);

			}
			if (isAdmin) {
				request.getRequestDispatcher("AdminHome.jsp").forward(request, response);
				return;
			} else {
				request.getRequestDispatcher("CustomerHome.jsp").forward(request, response);
				return;
			}

		} else if (uri.contains("error")) {

			request.getRequestDispatcher("ErrorPage.jsp").forward(request, response);

		} else if (uri.contains("registration")) {
			if (isAdmin) {
				request.getRequestDispatcher("AdminRegistration.jsp").forward(request, response);
				return;
			} else {
				request.getRequestDispatcher("CustomerRegistration.jsp").forward(request, response);
				return;
			}
		} else if (uri.contains("login")) {

			System.out.println("Here");
			request.getRequestDispatcher("CustomerLogin.jsp").forward(request, response);

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
