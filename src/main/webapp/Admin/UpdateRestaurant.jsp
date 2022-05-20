<%@page import="com.narola.fooddelivery.restaurants.model.Restaurant"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.narola.fooddelivery.utility.URLConstantAdmin"%>
<%@page import="com.narola.fooddelivery.utility.URLConstantOfServlet"%>

<!DOCTYPE html>
<html>
<head>


<title>Update Restaurant</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- plugins:css -->
<link rel="stylesheet"
	href="vendors/simple-line-icons/css/simple-line-icons.css">
<link rel="stylesheet"
	href="vendors/flag-icon-css/css/flag-icon.min.css">
<link rel="stylesheet" href="vendors/css/vendor.bundle.base.css">
<!-- endinject -->
<!-- Plugin css for this page -->
<link rel="stylesheet"
	href="./vendors/daterangepicker/daterangepicker.css">
<link rel="stylesheet" href="./vendors/chartist/chartist.min.css">
<!-- End plugin css for this page -->
<!-- inject:css -->
<!-- endinject -->
<!-- Layout styles -->
<link rel="stylesheet" href="./css/style.css">

</head>

<body>
	<div class="container-scroller">
		<%@include file="Navigation.jsp"%>
		<div class="container-fluid page-body-wrapper">
			<%@include file="Sidebar.jsp"%>
			<div class="main-panel">
				<div class="content-wrapper">




					<h2>
						<strong>Update Your Restaurant Data </strong>
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
						action="<%=request.getContextPath() + URLConstantOfServlet.UPDATERESTAURANT%>"
						method="post" enctype="multipart/form-data">
						<%
						Restaurant restaurant = (Restaurant) request.getAttribute("Restaurant");
						%>
						<input type="hidden" name="RestaurantId"
							value="<%=restaurant.getRestId()%>">
						<div class="row">
							<div class="col-12 col-sm-2">Name:</div>
							<div class="col-12 col-sm-5">
								<input class="form-control" type="text" name="RestName"
									value="<%=restaurant.getRestName()%>">
							</div>
						</div>
						<br />
						<div class="row">
							<div class="col-12 col-sm-2">Email:</div>
							<div class="col-12 col-sm-5">
								<input class="form-control" type="text" name="email"
									value="<%=restaurant.getEmail()%>">

							</div>
						</div>
						<br />
						<div class="row">
							<div class="col-12 col-sm-2">Picture:</div>
							<div class="col-auto">
								<img class="img-thumbnail"
									src="data:image/png;base64,<%=restaurant.getRestphotoAsBase64()%>"
									alt="No image found" style="width: 300px;"><br> <input
									type="file" name="RestPic" />
							</div>
						</div>
						<br />
						<div class="row">
							<div class="col-12 col-sm-2">AddressLine:</div>
							<div class="col-auto">
								<textarea class="form-control" rows="3" cols="50"
									name="addressline" width="60%"><%=restaurant.getLocation().getAddressLine()%></textarea>
							</div>

						</div>
						<br />



						<div class="row">
							<div class="col-12 col-sm-2">Area:</div>
							<div class="col-auto">
								<input class="form-control" type="text" name="area"
									value="<%=restaurant.getLocation().getArea()%>">
							</div>
						</div>
						<br />
						<div class="row">
							<div class="col-12 col-sm-2">City:</div>
							<div class="col-auto">
								<input class="form-control" type="text" name="city"
									value="<%=restaurant.getLocation().getCity()%>">
							</div>

						</div>
						<br />
						<div class="row">
							<div class="col-12 col-sm-2">State:</div>
							<div class="col-auto">
								<input class="form-control" type="text" name="state"
									value="<%=restaurant.getLocation().getState()%>">
							</div>
						</div>
						<br />
						<div class="row">
							<div class="col-12 col-sm-2">Pincode:</div>
							<div class="col-auto">
								<input class="form-control" type="text" name="pincode"
									value="<%=restaurant.getLocation().getPincode()%>">
							</div>
						</div>
						<br />
						<div class="row">
							<div class="col-12 col-sm-2"></div>
							<div class="col-auto">

								<input type="checkbox" name="Disable"
									<%=restaurant.getDisableFlag() == 0 ? "" : " checked"%>> <b><%=restaurant.getDisableFlag() == 0 ? "Check to disable Restaurant" : "Uncheck to enable restaurant"%></b>
							</div>
						</div>
						<br /> <input class="btn btn-secondary" type="reset"> <input
							class="btn btn-primary" type="submit"> <br />




					</form>

				</div>
			</div>

		</div>
	</div>



	<script src="vendors/js/vendor.bundle.base.js"></script>
	<!-- endinject -->
	<!-- Plugin js for this page -->
	<script src="./vendors/chart.js/Chart.min.js"></script>
	<script src="./vendors/moment/moment.min.js"></script>
	<script src="./vendors/daterangepicker/daterangepicker.js"></script>
	<script src="./vendors/chartist/chartist.min.js"></script>
	<!-- End plugin js for this page -->
	<!-- inject:js -->
	<script src="js/off-canvas.js"></script>
	<script src="js/misc.js"></script>
	<!-- endinject -->
	<!-- Custom js for this page -->
	<script src="./js/dashboard.js"></script>
	<!-- End custom js for this page -->



</body>

</html>