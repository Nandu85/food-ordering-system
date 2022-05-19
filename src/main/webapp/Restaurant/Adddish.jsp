<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*"%>
<%@page import="com.narola.fooddelivery.URLConstantAdmin"%>
<%@page import="com.narola.fooddelivery.restaurants.model.Restaurant"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>

<!DOCTYPE html>
<html>
<head>
<title>Add Dishes</title>
<%@ include file="MetaTag.jsp" %>

</head>
<body>

<div class="container-scroller">
		<%@include file="Navigation.jsp"%>
		<div class="container-fluid page-body-wrapper">
			<%@include file="Sidebar.jsp"%>
			<div class="main-panel">
				<div class="content-wrapper">
					<h2>
						<strong>Add Dish </strong>
					</h2>
					<br />
			
					<form action="<%=request.getContextPath() + URLConstantAdmin.ADDDISH%>"
			method="post" enctype="multipart/form-data">
			
				
			<%
		if (request.getAttribute("errMsg") != null) {
		%>
		<style>
		p.error
		{
		color: Red;
		text-align: center;
		}
		</style>
		<p class="error">
		<%=request.getAttribute("errMsg")%>
		</p>
		<%
		}
		%>



			<div class="row">
				<div class="col-12 col-sm-2">Dish Name:</div>
				<div class="col-12 col-sm-5">
					<input class="form-control" type="text" name="DishName">
				</div>
			</div>
			<br />

			<div class="row">
				<div class="col-12 col-sm-2">Price:</div>
				<div class="col-12 col-sm-5">
					<input class="form-control" type="text" name="Price">
				</div>
			</div>
			<br />

			<div class="row">
				<div class="col-12 col-sm-2">Ingrediants:</div>
				<div class="col-12 col-sm-5">
					<input class="form-control" type="text" name="ingrediant">
				</div>
			</div>
			<br />


			<div class="row">
				<div class="col-12 col-sm-2">Picture:</div>
				<div class="col-auto">
					<input class="form-control" type="file" name="DishPic" />
				</div>
			</div>
			<br />

			<div class="row">
				<div class="col-12 col-sm-2">Category:</div>
				<div class="col-auto">
					
					<select name="category">
			<%
			ArrayList<String> category=(ArrayList)request.getAttribute("categories");
			Iterator itr=category.iterator();
			int i=1;
			while (itr.hasNext()) {
			%>
			<option value=<%=i%>><%=itr.next()%></option>
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
					
					<select class="form-control" name="restaurant">
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




	
</body>
</html>