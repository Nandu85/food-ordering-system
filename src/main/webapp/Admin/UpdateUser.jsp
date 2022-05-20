<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.narola.fooddelivery.utility.URLConstantAdmin"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

<title>Update User here</title>

</head>
<body>
	<div class="container-scroller">
		<%@include file="Navigation.jsp"%>
		<div class="container-fluid page-body-wrapper">
			<%@include file="Sidebar.jsp"%>
			<div class="main-panel">
				<div class="content-wrapper">
					<h2>
						<strong>Update User </strong>
					</h2>
					<br />
					
<style>
					p.error {
						color: Red;
						text-align: center;
					}
					</style>
					<p class="error">
						<c:out value="${ErrMsg}"></c:out>
					</p>
					
					
					<form class="forms-sample" method="post" action="<%=request.getContextPath() + URLConstantOfServlet.UPDATEUSER%>">
					<input type="hidden" name=UserId value="${user.getUserId()}">
                      <div class="form-group row">
                        <label for="exampleInputUsername2" class="col-sm-3 col-form-label">UserName</label>
                        <div class="col-sm-6">
                          <input type="text" class="form-control" id="exampleInputUsername2" placeholder="Username" name="username" required="required" value="${user.getUsername()}">
                        </div>
                      </div>
                      <div class="form-group row">
                        <label for="exampleInputEmail2" class="col-sm-3 col-form-label">Email</label>
                        <div class="col-sm-6">
                          <input type="email" class="form-control" id="exampleInputEmail2" placeholder="Email" name="email" required="required" value="${user.getEmail()}">
                        </div>
                      </div>
                      
                      <div class="form-group row">
                        <label for="exampleInputPassword2" class="col-sm-3 col-form-label">Password</label>
                        <div class="col-sm-6">
                        <div class="input-group">
                        <input type="password" class="form-control" placeholder="password" name="password" value="${user.getPassword()}" id="myInput">
                        <div class="input-group-append">
                          <button class="btn btn-sm btn-primary" type="button" onclick="myFunction()">
                            <i class="icon-eye"></i>
                          </button>
                        </div>
                      </div>
                        	
                        </div>
                      </div>
                      
                      <div class="form-group row">
							<c:if test="${user.getAdmin()==3}">
								<label for="exampleInputPassword2" class="col-sm-3 col-form-label">Restaurant</label>		
								<div class="col-sm-6">
									<select name="Restaurant">
										<option value="${user.getRestaurantId()}" hidden="true"><c:if test="${user.getRestaurantId()!=0}">${user.getRestaurant().getRestName()}</c:if><c:if test="${user.getRestaurantId()==0}">Select</c:if> </option> 
										<c:forEach items="${Restaurants}" var="Rest">
											<option value="${Rest.getRestId()}">${Rest.getRestName()}</option>
										
										</c:forEach>
									
									</select>
								</div>
							</c:if>                        
                    
                      </div>
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

	<script>
function myFunction() {
  var x = document.getElementById("myInput");
  if (x.type === "password") {
    x.type = "text";
  } else {
    x.type = "password";
  }
}
</script>

</body>
</html>