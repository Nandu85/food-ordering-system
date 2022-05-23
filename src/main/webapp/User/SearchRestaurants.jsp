<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.narola.fooddelivery.utility.URLConstantUser"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.narola.fooddelivery.restaurants.model.Restaurant"%>
<%@page import="java.util.Iterator"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>

<%@include file="MetaTag.jsp"%>

<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->


</head>
<body class="main-layout">

	<!-- loader  -->
	<div class="loader_bg">
		<div class="loader">
			<img src="images/loading.gif" alt="" />
		</div>
	</div>

	<div class="wrapper">
		<!-- end loader -->



		<div id="content">
			<!-- header From index.jsp -->
			<%@include file="Navigation.jsp"%>
			<!-- end header -->
			<br><br><br><br>
			
			<div align="center"><h2>
				<strong> Find Restaurants Near You</strong>
			</h2></div>
			<br>
			<div class="container">
				<div class="row">

					<form
						action="<%=request.getContextPath() + URLConstantOfServlet.SEARCHRESTAURANT%>"
						method="POST" style="background-color: transparent;">
						Restaurant Name: <input type="text" name="RestaurantName">
						&nbsp;&nbsp;&nbsp; Area: <select name="Area">
						<option value="">Select
							</option>
							<%
							ArrayList<String> area = (ArrayList) request.getAttribute("Areas");

							Iterator itr = area.iterator();
							while (itr.hasNext()) {
								String x = (String) itr.next();
							%>
							<option value="<%=x%>"><%=x%>
							</option>

							<%
							}
							%>
						</select> &nbsp;&nbsp;&nbsp; <input type="submit" value="Find">
					</form>
				</div>
				<br>
				<br>

				<div class="row">
					<%
					int count = 0;
					ArrayList<Restaurant> RestList = (ArrayList) request.getAttribute("Restaurants");
					itr = RestList.iterator();
					while (itr.hasNext()) {
						Restaurant rest = (Restaurant) itr.next();
						if(rest.getDisableFlag() == 0){
						count++;
					%>

					<%
					if (count % 3 == 1) {
					%>
				</div>
				<br />
				<br />
				<div class="row">

					<%
					}
					%>
					
					<div class="col-12 col-sm-4">
						<div class="card border-secondary">
								<!--  class="card-header" -->
							
							<div class="card-body">
							<div align="center">
								<label><h2>
								<strong><%=rest.getRestName()%></strong>
									</h2> </label>
							</div>
							
								<img class="img-thumbnail"
									src="data:image/png;base64,<%=rest.getRestphotoAsBase64()%>"
									alt="No image found" style="width: 100%; height: 250px;"><br>
									
								<br>
								<%-- <c:forEach items="${rest.getCategories()}" var="cat"> --%>
								<%List<String> cat=rest.getCategories();
									Iterator itr1=cat.iterator();
									while(itr1.hasNext())
									{
								%>
								<span class="badge badge-primary"><%=itr1.next() %></span>
								<%} %>
								<%-- </c:forEach> --%>
								
 								<br><br>
 								<%-- <%=rest.getCategories() %> --%>
								 <label><i class="fa-solid fa-location-dot" style="font-size: 24px; color: black;"></i>&nbsp;&nbsp;<%=rest.getLocation().getArea()%></label>

								&nbsp;&nbsp;&nbsp;
								
								<a type="button" class="btn btn-info"
									href="<%=request.getContextPath() + URLConstantOfServlet.RESTDETAIL%>?RestaurantId=<%=rest.getRestId()%>">Menu</a>
								
							</div>
						</div>
					</div>
					<%
					} }
					%>
					<%
					if (count == 0) {
					%>
					<b>No Record</b>
					<%
					}
					%>
				</div>
				<br><br>
			</div>




		</div>

	</div>



<%@include file="Scripts.jsp"%>


</body>

<script>
	const collection = document.getElementsByClassName("example");
	for (let i = 0; i < collection.length; i++) {
		collection[i].style.backgroundColor = "silver";
	}
</script>
</html>