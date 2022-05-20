<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.narola.fooddelivery.utility.URLConstantAdmin"%>
<%@page import="com.narola.fooddelivery.utility.URLConstantOfServlet"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="MetaTag.jsp" %>

<title>Insert Restaurant here</title>

</head>
<body>
	<div class="container-scroller">
		<%@include file="Navigation.jsp"%>
		<div class="container-fluid page-body-wrapper">
			<%@include file="Sidebar.jsp"%>
			<div class="main-panel">
				<div class="content-wrapper">
					<h2>
						<strong>Add Your Restaurant </strong>
					</h2>
					<br />
					<%
					if (request.getAttribute("ErrMsg") != null) {
					%>
					<style>
p.error {
	color: Red;
	text-align: center;
}
</style>
					<p class="error">
						<%=request.getAttribute("ErrMsg")%>
					</p>
					<%
					}
					%>
					<form
						action="<%=request.getContextPath() + URLConstantOfServlet.ADDRESTAURANT%>"
						method="post" enctype="multipart/form-data">


						<input type="hidden" name="RestaurantId">
						<div class="row">
							<div class="col-12 col-sm-2">Name:</div>
							<div class="col-12 col-sm-5">
								<input class="form-control" type="text" name="RestName">
							</div>
						</div>
						<br />
						<div class="row">
							<div class="col-12 col-sm-2">Email:</div>
							<div class="col-12 col-sm-5">
								<input class="form-control" type="text" name="email">

							</div>
						</div>
						<br />
						<div class="row">
							<div class="col-12 col-sm-2">Picture:</div>
							<div class="col-auto">

								<input type="file" name="RestPic" />
							</div>
						</div>
						<br />
						<div class="row">
							<div class="col-12 col-sm-2">AddressLine:</div>
							<div class="col-auto">
								<textarea class="form-control" rows="3" cols="50"
									name="addressline" width="60%"></textarea>
							</div>

						</div>
						<br />



						<div class="row">
							<div class="col-12 col-sm-2">Area:</div>
							<div class="col-auto">
								<input class="form-control" type="text" name="area">
							</div>
						</div>
						<br />
						<div class="row">
							<div class="col-12 col-sm-2">City:</div>
							<div class="col-auto">
								<input class="form-control" type="text" name="city">
							</div>

						</div>
						<br />
						<div class="row">
							<div class="col-12 col-sm-2">State:</div>
							<div class="col-auto">
								<input class="form-control" type="text" name="state">
							</div>
						</div>
						<br />
						<div class="row">
							<div class="col-12 col-sm-2">Pincode:</div>
							<div class="col-auto">
								<input class="form-control" type="text" name="pincode">
							</div>
						</div>
						<br /> <br> <input class="btn btn-secondary" type="reset">
						<input class="btn btn-primary" type="submit"> <br />
					</form>
					<br>
					<br>
				</div>
			</div>

		</div>
	</div>





	<%@include file="Scripts.jsp" %>


</body>
</html>