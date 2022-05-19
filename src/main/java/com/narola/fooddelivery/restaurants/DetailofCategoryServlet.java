package com.narola.fooddelivery.restaurants;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.fooddelivery.DAOFactory;
import com.narola.fooddelivery.URLConstantUser;
import com.narola.fooddelivery.category.SubCategoryDAO;
import com.narola.fooddelivery.user.User;

/**
 * Servlet implementation class DetailofDish
 */
public class DetailofCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user=(User) req.getSession().getAttribute("user");
		String str;
		String id=req.getParameter("id");
		if(id!=null) {
			req.setAttribute("SubCategory", SubCategoryDAO.getSubCategoryById(Integer.parseInt(id)));
			req.setAttribute("Restaurants", DAOFactory.getInstance().getRestDAO().getRestaurantsFromSubCategory(Integer.parseInt(id)));
			getServletContext().getRequestDispatcher(URLConstantUser.CATEGORYDETAIL_JSP).forward(req, resp);
		}
		else
			System.out.println("Error in dish Detail Servlet");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
