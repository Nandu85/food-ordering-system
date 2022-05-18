<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*"%>
<%@page import="com.narola.fooddelivery.dishes.model.Dish"%>
<%@page import="com.narola.fooddelivery.restaurants.Restaurant"%>
<%@page import="com.narola.fooddelivery.URLConstantAdmin"%>
<%@page import="java.util.Base64"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.narola.fooddelivery.category.Category"%>

<!DOCTYPE html>
<html>
<head>

<title>Update Dish</title>
<%@ include file="MetaTag.jsp" %>
</head>
<body>
	<div class="container-scroller">
		<%@include file="Navigation.jsp"%>
		<div class="container-fluid page-body-wrapper">
			<%@include file="Sidebar.jsp"%>
			<div class="main-panel">
				<div class="content-wrapper">
	<%
	Dish d = (Dish) request.getAttribute("Dish");
	%>
	<div class="container">
			<h2 align="center">
				<strong>Update Dish </strong>
			</h2>
		<br />
		<form action="<%=request.getContextPath() + URLConstantAdmin.UPDATEDISH%>"
			method="post" enctype="multipart/form-data">
			<%
			if (request.getAttribute("errMsg") != null) {
			%>
			<style>
p {
	color: Red;
	text-align: center;
}
</style>
			<p>
				<%=request.getAttribute("errMsg")%>
			</p>
			<%
			}
			%>

			
 
 			<input type="hidden" name="DishId" value="<%=request.getParameter("DishId")%>"> <br />
 
 <div class="row">
				<div class="col-12 col-sm-2">Dish Name:</div>
				<div class="col-12 col-sm-5">
					<input class="form-control" type="text" name="DishName" value="<%=d.getDishName()%>">
				</div>
			</div>
			<br />

			<div class="row">
				<div class="col-12 col-sm-2">Price:</div>
				<div class="col-12 col-sm-5">
					<input class="form-control" type="text" name="Price" value="<%=d.getPrice()%>">
				</div>
			</div>
			<br />

			<div class="row">
				<div class="col-12 col-sm-2">Ingrediants:</div>
				<div class="col-12 col-sm-5">
					<input class="form-control" type="text" name="ingrediant" value="<%=d.getIngrident()%>">
				</div>
			</div>
			<br />


			<div class="row">
				<div class="col-12 col-sm-2">Picture:</div>
				<div class="col-12 col-sm-4">
				<img src="data:image/png;base64,<%=d.getPhotoAsBase64()%>" alt="No image found" width="350px" name="DishPic1"><br><br>
				<input class="form-control" type="file" name="DishPic" />
				</div>
				<div class="col-12 col-sm-5"></div>
			</div>
					
				
			<br />

			<div class="row">
				<div class="col-12 col-sm-2">Category:</div>
				<div class="col-auto">
					
					<select class="form-control" name="category"
				value="<%=d.getCategoryId()%>">
				<option value="<%=d.getCategoryId()%>" selected hidden><%=d.getCategory()%></option>
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
					</select>
				</div>
			</div>
			<br />
			
			<div class="row">
				<div class="col-12 col-sm-2">Restaurant:</div>
				<div class="col-auto">
					
					<select class="form-control" name="restaurant" value="<%=d.getRestId()%>">
						<option value="<%=d.getRestId()%>" selected hidden><%=d.getRestaurant().getRestName()%></option>
					<%
						ArrayList<Restaurant> rests = (ArrayList)request.getAttribute("Restaurants");
						itr = rests.iterator();
						while(itr.hasNext()){
							Restaurant rest=(Restaurant)itr.next();
					%>
					<option value="<%=rest.getRestId()%>"><%=rest.getRestName()%></option>
					<%} %> 
						
						<%-- <c:forEach items="${Restaurants}" var="rest">
						
							<option value="${rest.getRestId()}"><c:out value="${rest.getRestName()}" /></option>
						
						</c:forEach> --%>
					</select>
				</div>
			</div>
			<br />

			<div class="row">
				<div class="col-12 col-sm-2">Type:</div>
				<div class="col-auto">
					<select class="form-control" name="DishType">
					<option value="<%=d.getDishtype()%>" selected hidden><%=d.getDishtype() == 0 ? "Veg" : "NonVeg"%></option>
						<option value="0">Veg</option>
						<option value="1">NonVeg</option>
					</select>
				</div>
			</div>
			<br /> <input class="btn btn-secondary" type="reset"> <input
				class="btn btn-primary" type="submit"> <br />
 
		</form>
	</div>
	
	</div>
</div>
</div>
</div>
	
<%@ include file="Scripts.jsp" %>

</body>
</html>