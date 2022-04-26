<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="MetaTag.jsp"%>
</head>
<body class="main-layout" style="background: #e6e6e6;">
	<!-- loader  -->
	<!-- <div class="loader_bg">
		<div class="loader">
			<img src="images/loading.gif" alt="" />
		</div>
	</div> -->
<!-- end loader -->
	<div class="wrapper">
		
		<div id="content">
			
			<%@include file="Navigation.jsp"%>
			<br><br><br><br><br>
			<div class="container">
			<h2><strong>Enter Location</strong></h2><br><br>
			
				<form
						action="<%=request.getContextPath() + URLConstantOfServlet.ADDLOCATION%>"
						method="post">


						<input type="hidden" name="LocId">
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
					</form><br><br>
			</div>
			
		</div>
	</div>

<%@include file="Scripts.jsp" %>
</body>
</html>