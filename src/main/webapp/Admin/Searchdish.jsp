<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@page import="com.narola.fooddelivery.dishes.model.Dish"%>
<%@page import="com.narola.fooddelivery.category.Category"%>
<%@page import="com.narola.fooddelivery.utility.URLConstantOfServlet"%>

<%@page import="com.narola.fooddelivery.utility.URLConstantAdmin"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.*"%>

<!DOCTYPE html>
<html>
<head>

<title>Insert title here</title>
 <%@ include file="MetaTag.jsp" %>
</head>



<body>
<div class="container-scroller">
		<%@include file="Navigation.jsp"%>
		<div class="container-fluid page-body-wrapper">
			<%@include file="Sidebar.jsp"%>
			<div class="main-panel">
				<div class="content-wrapper">


	<header class="jumbotron" align="center">
		<h2>
			<strong> Popular Dishes </strong>
		</h2>
	</header>
	<div class="container">
		<div class="row">
			<form action="<%=request.getContextPath() + URLConstantOfServlet.SEARCHDISH%>"
				method="GET">
				<input type="hidden" name="isfilter" value="true">
				Dish Name: <input type="text" name="DishName"> &nbsp;&nbsp;&nbsp;
				Category: <select name="category">
					<option selected hidden value="0">Select</option>

					<%
					ArrayList<Category> category = (ArrayList<Category>) request.getAttribute("categories");
								Iterator itr = category.iterator();
								int i = 1;
								while (itr.hasNext()) {
									Category cat = (Category)itr.next();
					%>
					<option value=<%=cat.getCategoryId()%>><%=cat.getCategoryName()%></option>
					
					<%
										i++;
													}
										%>
				</select>&nbsp;&nbsp;&nbsp; Type: <select name="DishType">
					<option selected hidden value="2">Select</option>
					<option value="0">Veg</option>
					<option value="1">NonVeg</option>
				</select>&nbsp;&nbsp;&nbsp; <input type="submit" value="Find">
			</form>




		</div>
		<br> <br> <%-- Haven't find your favorite, <a
			href="<%=request.getContextPath() + URLConstantOfServlet.ADDDISH%>"> Add
			it here... </a> <br> <br> --%>
		<div class="row">

			<%
			int count = 0;
				List<Dish> al = null;

				al = (ArrayList) request.getAttribute("dishList");

				itr = al.iterator();
				while (itr.hasNext()) {
					Dish d = (Dish) itr.next();
					count++;
			%>

			 <%
			 if(count%3==1){
			 %>
			 </div><br /><br />
			 <div class="row">
				
				<%
								}
								%> 


			<div class="col-sm-4">
				<div class="card">
					<div class="card-header">
						<label><h3><%=d.getDishId()%>. <strong><%=d.getDishName()%></strong>
							</h3> </label>
					</div>
					<div class="card-body">
						<%-- <p>
						<i class="fas fa-burger" style="font-size: 24px; color: black;"></i>
						<strong><%=d.getRestaurant().getRestName()%></strong></p> --%>
						<img src="data:image/png;base64,<%=d.getPhotoAsBase64()%>"
							alt="No image found" style="width: 100%;height: 250px;"><br> <label>-/<%=d.getPrice()%></label><br>
						
							<span class="badge badge-primary"><%=d.getCategory()%></span>
							&nbsp;
							<%
							if (d.getDishtype() == 0) {
							%>
							<span class="badge badge-success">Veg</span>
							<%
							} else {
							%>
							<span class="badge badge-danger">NonVeg</span>
							<%
							}
							%>
							&nbsp;&nbsp;&nbsp; 
							<div align="right">
								<a
								href="<%=request.getContextPath() + URLConstantOfServlet.UPDATEDISH%>?DishId=<%=d.getDishId()%>">
								<i class="fa-solid fa-pen-to-square"
								style="font-size: 24px; color: black; text-align: right"></i>
								</a>&nbsp; <a
								href="<%=request.getContextPath() + URLConstantOfServlet.DELETEDISH%>?DishId=<%=d.getDishId()%>"
								align="right"> <i class="fas fa-trash-alt"
								style="font-size: 24px; color: black; text-align: right"></i></a>&nbsp;&nbsp;&nbsp;
							</div>
							
							<%-- <a type="button" class="btn btn-warning"
								href="<%=request.getContextPath() + URLConstantOfServlet.VIEWDISH%>?DishId=<%=d.getDishId()%>">
								Order </a> --%>

						



					</div>

				</div>

			</div>



			<%
			}
			%>
			<%
			if (count == 0) {
			%>
			<b>No Record</b>
			<%
			}
			%>


		</div>

	</div>


</div>
</div>
</div>
</div>

<%@ include file="Scripts.jsp" %>

</body>
</html>