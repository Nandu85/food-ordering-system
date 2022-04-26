<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Stellar Admin</title>
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
<!-- End layout styles -->
<link rel="shortcut icon" href="./images/favicon.png" />

<title>Insert Restaurant here</title>

</head>
<body>
	<div class="container-scroller">
		<%@include file="Navigation.jsp"%>
		<div class="container-fluid page-body-wrapper">
			<%@include file="Sidebar.jsp"%>
			<div class="main-panel">
				<div class="content-wrapper">
					<br> <br>
					<form method="post"
						action="<%=request.getContextPath() + URLConstantAdmin.ADDCATEGORY%>">

						<div class="row">
							<div class="col-12 col-sm-3">
								<input type="text" class="form-control" name="categoryName"
									placeholder="Name of Category" required="required">
							</div>
							<div class="col-12 col-sm-2">
								<!-- <select class="form-control" name="popularity">
									<option value="0" hidden>Select Popularity</option>
									<option value="1">1.Very High</option>
									<option value="2">2.High</option>
									<option value="3">3.Medium</option>
									<option value="4">4.Low</option>
									<option value="5">5.Very Low</option>
								</select> -->
								<label>Is Popular?</label>&nbsp;&nbsp;&nbsp;
								<input type="checkbox" name="popularity">
							</div>
							<div class="col-12 col-sm-3">
								<input type="submit" class="btn btn-inverse-info btn-rounded"
									name="Submit" value="Add">
							</div>
						</div>
					</form>
					<br> <br>


					<div class="card">
						<div class="card-body">
							<h4 class="card-title">
								<strong>Find Categories here </strong>
							</h4>
							<table class="table table-striped">
								<thead>
									<tr>
										<th><b>CategoryId</b></th>
										<th><b>Category Name</b></th>
										<th><b> Popularity </b></th>

									</tr>
								</thead>
								<tbody>

									<c:forEach items="${categories}" var="cat">
										<tr>
											<td><c:out value="${cat.getCategoryId()}"></c:out></td>
											<td>${cat.getCategoryName()}</td>

											<td>${cat.getPopularity()}</td>

											<td><button type="button" class="btn btn-primary btn-rounded btn-inverse" data-toggle="modal" data-target=".bd-example-modal-md${cat.getCategoryId()}"><i class="icon-pencil"></i></button>
											&nbsp;&nbsp;&nbsp;
											<a class="btn btn-secondary btn-rounded btn-inverse" href="<%=request.getContextPath()+URLConstant_Admin.DELETECATEGORY%>?Id=${cat.getCategoryId()}"><i class="icon-trash"></i></a>
												
												<div class="modal fade bd-example-modal-md${cat.getCategoryId()}" tabindex="-1" 
					role="dialog" aria-labelledby="mySmallModalLabel"
					aria-hidden="true">
					<div class="modal-dialog modal-md">
						<div class="modal-content">
							<form method="post" action="<%=request.getContextPath()+URLConstantAdmin.UPDATECATEGORY%>" style="padding: 30px;">
					
					
							<input type="hidden" name="categoryId" value="${cat.getCategoryId()}">
							<input type="text" class="form-control" name="categoryName" placeholder="Name of Category" value="${cat.getCategoryName()}" required="required">	
							<br><br>
						
							<label>Is Popular?</label>&nbsp;&nbsp;&nbsp;
								<input type="checkbox" name="popularity" ${cat.getPopularity()>0?"checked":"" }>			
							<br>
							<input type="submit" class="btn btn-inverse-info btn-rounded" name="Submit" value="Edit">	
						
					</form>
						
						
						</div>
					</div>
				</div>
												
												
												</td>
										
										</tr>

									</c:forEach>




								</tbody>
							</table>
						</div>
					</div>



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
	
	
<!-- <script type="text/javascript">
$('#exampleModal').on('show.bs.modal', function (event) {
	  var button = $(event.relatedTarget) // Button that triggered the modal
	  var recipient = button.data('whatever') // Extract info from data-* attributes
	  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
	  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
	  var modal = $(this)
	  modal.find('.modal-title').text('New message to ' + recipient)
	  modal.find('.modal-body input').val(recipient)
	})

</script> -->

</body>
</html>