<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*"%>
<%@page import="com.narola.fooddelivery.utility.URLConstantAdmin"%>
<%@page import="com.narola.fooddelivery.restaurants.model.Restaurant"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Required meta tags -->
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

<title>Insert User here</title>

</head>
<body>
	<div class="container-scroller">
		<%@include file="Navigation.jsp"%>
		<div class="container-fluid page-body-wrapper">
			<%@include file="Sidebar.jsp"%>
			<div class="main-panel">
				<div class="content-wrapper">
					<h2>
						<strong>Add User </strong>
					</h2>
					<br />
					<%-- <%
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
					%> --%>
					
					<form class="forms-sample" method="post" action="<%=request.getContextPath() + URLConstantOfServlet.ADDUSER%>">
                      <div class="form-group row">
                        <label for="exampleInputUsername2" class="col-sm-3 col-form-label">UserName</label>
                        <div class="col-sm-6">
                          <input type="text" class="form-control" id="exampleInputUsername2" placeholder="Username" name="username" required="required">
                        </div>
                      </div>
                      <div class="form-group row">
                        <label for="exampleInputEmail2" class="col-sm-3 col-form-label">Email</label>
                        <div class="col-sm-6">
                          <input type="email" class="form-control" id="exampleInputEmail2" placeholder="Email" name="email" required="required">
                        </div>
                      </div>
                      
                      <div class="form-group row">
                        <label for="exampleInputPassword2" class="col-sm-3 col-form-label">Password</label>
                        <div class="col-sm-6">
                          <input type="password" class="form-control" id="exampleInputPassword2" placeholder="Password" name="password" required="required">
                        </div>
                      </div>
                      <div class="form-group row">
                        <label for="exampleInputConfirmPassword2" class="col-sm-3 col-form-label">User type</label>
                        <div class="col-sm-6">
                        <select class="form-control" name="usertype">
                        <%if(user.getAdmin()==1){ %>
                        	<option value=2>Admin</option>
                        	<%} %>
                        	<option value=3>Restaurant Admin</option>
                        </select>
                          
                        </div>
                      </div>
                      <!-- <div class="form-check form-check-flat form-check-primary">
                        <label class="form-check-label">
                          <input type="checkbox" class="form-check-input" name="admin"> Is Admin? </label>
                      </div> -->
                      <button type="submit" class="btn btn-primary mr-2">Submit</button>
                      <button type="reset" class="btn btn-light">Reset</button>
                    </form>
					
					
					
					<br>
					<br>
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