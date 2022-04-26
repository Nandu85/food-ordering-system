package com.narola.fooddelivery.dishes;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.fooddelivery.URLConstantAdmin;
import com.narola.fooddelivery.category.CategoryDAO;
import com.narola.fooddelivery.user.User;

/**
 * Servlet implementation class SearchingDish
 */
public class SearchingDishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String isFilter = request.getParameter("isfilter"); 
		String dname = request.getParameter("DishName");
		String category = request.getParameter("category");
		String dtype = request.getParameter("DishType");
		try {
			if (isFilter != null && Boolean.parseBoolean(isFilter)) {
				searchDish(request, response, dname, category, dtype);
			} else {
				searchDish(request, response);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void searchDish(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		searchDish(request, response, null, null, null);
	}

	public void searchDish(HttpServletRequest request, HttpServletResponse response, String dname, String categoryId,
			String dtype1) throws ServletException, IOException {
		List<Dish> dl = null;
		if (dname == null && categoryId == null && dtype1 == null) {
			dl = DishDAO.getAllDish();
		} else {
			dl = DishDAO.getAllDish(dname, categoryId, dtype1);
		}
		request.setAttribute("dishList", dl);
		request.setAttribute("categories", CategoryDAO.getAllCategories());
		
		User sessionUser=(User)request.getSession().getAttribute("user");
		if(sessionUser.getAdmin()==3 && sessionUser.getRestaurantId()!=0) {
			request.setAttribute("dishList", sessionUser.getRestaurant().getMenu());
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(URLConstantAdmin.SEARCHDISH_JSP);
		dispatcher.forward(request, response);
	}

}
