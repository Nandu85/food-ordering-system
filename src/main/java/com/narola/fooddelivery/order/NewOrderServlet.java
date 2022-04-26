package com.narola.fooddelivery.order;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.fooddelivery.URLConstantAdmin;
import com.narola.fooddelivery.URLConstantOfServlet;
import com.narola.fooddelivery.user.User;

/**
 * Servlet implementation class NewOrderServlet
 */
public class NewOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static List<Order> orders=null;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		System.out.println(orders);
		
		String sort=request.getParameter("sort");
		if(sort!=null && !sort.trim().isEmpty() && orders!=null)
			orders=OrderDAO.getAllOrdersSorted(orders,sort);
		
		if(orders==null || request.getQueryString()==null)
			orders=OrderDAO.getAllOrders();
		
		
		String id=request.getParameter("id");
		if(id!=null && !id.trim().isEmpty()) {
			orders=new ArrayList<>();
			orders.add(OrderDAO.getOrderFromId(Integer.parseInt(id)));
			
		}
		
		
		
		
		String stdate = request.getParameter("startDate");
		String endate = request.getParameter("endDate");
		String status = request.getParameter("status");
//		System.out.println(stdate+"	 "+endate);
		Timestamp st=null;
		Timestamp en=null;
		if((stdate!=null && !stdate.trim().isEmpty()) || (endate!=null && !endate.trim().isEmpty()) || status!=null && !status.trim().isEmpty()) {
			if(stdate!=null && !stdate.trim().isEmpty()) {
				stdate+=" 00:00:00";
				st= Timestamp.valueOf(stdate);
			}
			if(endate!=null && !endate.trim().isEmpty()) {
				endate+=" 23:59:59";
				en= Timestamp.valueOf(endate);
			}
			
			
			orders= OrderDAO.getOrdersDatewise(st,en,status);
		}
		request.setAttribute("Orders", orders);
		
		int i=((User) request.getSession().getAttribute("user")).getAdmin();
		if(i!=3)
			getServletContext().getRequestDispatcher(URLConstantAdmin.NEW_ORDER_JSP).forward(request, response);
		else {
//			if(request.getRequestURI().contains(URLConstantOfServlet.NEW_ORDER))
				getServletContext().getRequestDispatcher(URLConstantAdmin.NEWORDERRESTAURANT_JSP).forward(request, response);
			
		}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			String orderId=request.getParameter("orderId");
			String status=request.getParameter("status");
			if(orderId!=null && !orderId.trim().isEmpty() && status!=null && !status.trim().isEmpty())
				OrderDAO.changeOrderStatus(Integer.parseInt(orderId), Integer.parseInt(status));
			
			response.sendRedirect(request.getContextPath()+URLConstantOfServlet.NEW_ORDER);
			
//		doGet(request, response);
	}

}
