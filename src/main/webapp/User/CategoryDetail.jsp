<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="MetaTag.jsp"%>
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
						<br><br><br><br>

		<div class="container">
		<div align="center"><h1>
				<strong> Restaurants having <strong><c:out value="${SubCategory.getCategoryName()}"></c:out> </strong></strong>
			</h1></div>
		
						<div class="row">
						<c:set var="i" value="0"></c:set>
					<c:forEach items="${Restaurants}" var="rest">
						<c:set var="i" value="${i+1}"></c:set>
						 <c:if test="${i % 3 == 1}">
							</div>
								<br /><br />
							<div class="row">
						</c:if>
					
						<div class="col-12 col-sm-4">
						<div class="card border-secondary">
								<!--  class="card-header" -->
							
							<div class="card-body">
							<div align="center">
								<label><h2>
								<strong><c:out value="${rest.getRestName()}"></c:out>   </strong>
									</h2> </label>
							</div>
							
								<img class="img-thumbnail"
									src="data:image/png;base64,${rest.getRestphotoAsBase64()}"
									alt="No image found" style="width: 100%; height: 250px;"><br>
									
								<br>
								 <c:forEach items="${rest.getCategories()}" var="cat"> 
								<%-- <%ArrayList<String> cat=rest.getCategories();
									Iterator itr1=cat.iterator();
									while(itr1.hasNext())
									{
								%> --%>
								<span class="badge badge-primary"><c:out value="${cat}"></c:out> </span>
								<%-- <%} %> --%>
								</c:forEach>
								
 								<br><br>
 								<%-- <%=rest.getCategories() %> --%>
								 <label><i class="fa-solid fa-location-dot" style="font-size: 24px; color: black;"></i>&nbsp;&nbsp;${rest.getLocation().getArea()}</label>

								&nbsp;&nbsp;&nbsp;
								
								<a type="button" class="btn btn-info"
									href="<%=request.getContextPath() + URLConstantOfServlet.RESTDETAIL%>?RestaurantId=${rest.getRestId()}">Menu</a>
								
							</div>
						</div>
					</div>
					
					<c:if test="${i==0}">
					<b>No Record</b></c:if>
					</c:forEach>
					
				</div>

			</div>
						
					
					
					

					
					
					



	
		</div>
	</div>

<%@include file="Scripts.jsp"%>
</body>
</html>