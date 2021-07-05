package com.foodwagonradar.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foodwagonradar.managers.UserManager;

/**
 * Servlet implementation class SignIn
 */
@WebServlet(urlPatterns = { "/sign-in", "/logout" })
public class SignInController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignInController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getServletPath().contains("logout")) {
			request.getSession().invalidate();
		}

		request.getRequestDispatcher("/signIn.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!request.getServletPath().contains("logout")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			long userId = UserManager.getInstance().authenticate(email, password);
			if (userId != -1) {
				HttpSession session = request.getSession();
				session.setAttribute("userId", userId);
				System.out.println("User ID:" + session.getAttribute("userId"));
				response.sendRedirect("food-wagons");
			} else {
				request.getRequestDispatcher("/signIn.jsp").forward(request, response);
			}
		}
	}

}
