package com.foodwagonradar.controllers;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodwagonradar.entities.FoodWagon;
import com.foodwagonradar.managers.FoodWagonManager;

/**
 * Servlet implementation class FoodWagon
 */
@WebServlet(urlPatterns = { "/food-wagons", "/food-wagons/favorites" })
public class FoodWagonsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public FoodWagonsController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
		int limit = 10;
		String serachValue = request.getParameter("serachValue");
		String sortBy = request.getParameter("sortBy");
		Collection<FoodWagon> list = null;
		String userId = request.getSession().getAttribute("userId") == null ? null
				: String.valueOf(request.getSession().getAttribute("userId"));
		
		if (request.getServletPath().contains("favorites")) {
			list = FoodWagonManager.getInstance().getFoodWagons(page, limit, serachValue, sortBy, userId);
		} else {
			list = FoodWagonManager.getInstance().getFoodWagons(page, limit, serachValue, sortBy, null);
		}

		int count = FoodWagonManager.getInstance().getRecordsCount();
		int pageCount = (int) Math.ceil(count * 1.0 / limit);
		System.out.printf("Count: %d PageCount: %d", count, pageCount);
		request.setAttribute("foodWagons", list);
		request.setAttribute("page", page);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("serachValue", serachValue);
		request.getRequestDispatcher("/foodWagons.jsp").forward(request, response);
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
