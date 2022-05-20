<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.narola.fooddelivery.utility.URLConstantAdmin"%>
<%@page import="com.narola.fooddelivery.utility.URLConstantOfServlet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.narola.fooddelivery.restaurants.model.Restaurant"%>
<%@page import="java.util.Iterator"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>

<title>Restaurants</title>
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
			<strong> Available Restaurants </strong>
		</h2>
	</header>
	
		<div class="row">
		
			<form action="<%=request.getContextPath() + URLConstantOfServlet.SEARCHRESTAURANT%>"
				method="POST" style="background-color: transparent;">
				Restaurant Name: <input type="text" name="RestaurantName">
				&nbsp;&nbsp;&nbsp;
				Area: <select name="Area">
				<%
				ArrayList<String> area = (ArrayList) request.getAttribute("Areas");
							
							Iterator itr = area.iterator();
							while (itr.hasNext()) {
								String x=(String)itr.next();
				%>
				<option value="<%=x%>"><%=x%> </option>
				
				<%
								}
								%>
				</select>
				&nbsp;&nbsp;&nbsp; <input type="submit" value="Find">
			</form>
		</div><br><br>
				
				
					<div class="col-lg-12 grid-margin stretch-card">
          		<div class="card">
          			<div class="card-body">

          				<table class="table table-hover table-bordered">
          					<thead>
          						<tr>
          							<th><strong>RestaurantId</strong></th>
          							<th><strong>Name</strong></th>
          							<th><strong>Email</strong></th>

          							<th><strong>Admin</strong></th>
          							
          							<th><strong>Disabled</strong></th>
          							
          							<th><strong>Joined At</strong></th>
          						</tr>
          						
          					</thead>
          					
          					<tbody>
          					<c:forEach items="${Restaurants}" var="rest">
          					
          					<tr>
          					<td><c:out value="${rest.getRestId()}"></c:out></td>
          					<td><c:out value="${rest.getRestName()}"></c:out>&nbsp;
          					<c:if test="${rest.getDisableFlag()==1}"><strong>(Disabled)</strong></c:if>
          					
          					</td>
          					
          					<td><c:out value="${rest.getEmail()}"></c:out> </td>
          					
          					<%-- <td><c:out value="${rest.getLocation().toString()}"></c:out> </td> --%>
          					
          					<td><c:out value="${rest.getUser().getUsername()}"></c:out> </td>
          					<td><c:out value="${rest.getDisableFlag()}"></c:out></td>
          					
          					<td><fmt:formatDate type = "date" value = "${rest.getTimestamp()}"	/></td> 
          					
          					<td><a href="<%=request.getContextPath() + URLConstantOfServlet.UPDATERESTAURANT+"?RestaurantId="%>${rest.getRestId()}">Update</a></td>	
          					
          					</tr>
          					
          					
          					
          					</c:forEach>
          					
          					
          					
          					
          					
          					
          					
          					
          					</tbody>
          				
          				</table>
          			
          			</div>
          		
          		</div>
          	
          	
          	
          	</div>
				
				
				
				
				
				
				
				
				
				
				
				
				
	
		<%-- <div class="row">
		<%
		int count = 0;
				ArrayList<Restaurant> RestList= (ArrayList)request.getAttribute("Restaurants");
				itr = RestList.iterator();
				while (itr.hasNext()) {
					Restaurant rest = (Restaurant) itr.next();
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
				<%
 				String str=rest.getDisableFlag()==0?"card-header":"card-header example";
 				%>
			<div class="col-12 col-sm-4">
				<div class="card">
				
					<div class="<%=str%>">
						<label><h4><%=rest.getRestId()%>. <strong><%=rest.getRestName()%><%=rest.getDisableFlag()==0?"":"(Disabled)"%></strong>
							</h4> </label>
					</div>
					<div class="card-body">
					<h6><i class="fa-solid fa-user"></i>&nbsp;&nbsp;<%=rest.getUserId()!=0?rest.getUser().getUsername():"Not Assigned"%></h6><br>
						<img class="img-thumbnail" src="data:image/png;base64,<%=rest.getRestphotoAsBase64()%>"
							alt="No image found" style="width: 100%;height:250px;"><br><br> <label><i class="fa fa-map-marker-alt" aria-hidden="true">&nbsp;&nbsp;</i><%=rest.getLocation().getArea()%></label>
							
							&nbsp;&nbsp;&nbsp; 
							<a href="<%=request.getContextPath() + URLConstantOfServlet.UPDATERESTAURANT%>?RestaurantId=<%=rest.getRestId()%>">
								<i class="fa-solid fa-pen-to-square"
								style="font-size: 24px; color: black; text-align: right"></i>
							</a>&nbsp;&nbsp;&nbsp;
							<%
							if(rest.getDisableFlag()==0){
							%>
							<a type="button" class="btn btn-info" href="<%=request.getContextPath()+URLConstantOfServlet.RESTDETAIL%>?RestaurantId=<%=rest.getRestId()%>">Menu</a>
							<%} %>
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
	</div> --%>
	</div>
			</div>

		</div>
	</div>
	
	
	
	<%@ include file="Scripts.jsp" %>

</html>