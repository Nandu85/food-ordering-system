<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Stellar Admin</title>
    <!-- plugins:css -->
    <link rel="stylesheet" href="vendors/simple-line-icons/css/simple-line-icons.css">
    <link rel="stylesheet" href="vendors/flag-icon-css/css/flag-icon.min.css">
    <link rel="stylesheet" href="vendors/css/vendor.bundle.base.css">
    <!-- endinject -->
    <!-- Plugin css for this page -->
    <link rel="stylesheet" href="./vendors/daterangepicker/daterangepicker.css">
    <link rel="stylesheet" href="./vendors/chartist/chartist.min.css">
    <!-- End plugin css for this page -->
    <!-- inject:css -->
    <!-- endinject -->
    <!-- Layout styles -->
    <link rel="stylesheet" href="./css/style.css">
    <!-- End layout styles -->
    <link rel="shortcut icon" href="./images/favicon.png" />
</head>
<body>

	<div class="container-scroller">
      <!-- partial:partials/_navbar.html from jsp -->
      <%@include file="Navigation.jsp"%>
      
      <!-- partial from jsp -->
      <div class="container-fluid page-body-wrapper">
        <!-- partial:partials/_sidebar.html -->
        <%@include file="Sidebar.jsp"%>
        
        <!-- partial -->
        <div class="main-panel">
          <div class="content-wrapper">
          
          	<div class="col-sm-12 grid-margin streach-card">
          		<div class="card">
          			<div class="card-body">
          				<h1 class="card-title" align="center">Admin Profile</h1>
          				<div class="row">
          					<div class="col-12 col-lg-3">
          						<img class="img-md rounded-circle" src="images/faces/face1.png" alt="Profile image">	
          					
          					</div>
          					<div class="col-12 col-lg-6">
          						<h5 align="left"><strong><%=user.getUsername() %></strong></h5>
          						<h6 align="left"><%=user.getEmail()%></h6>
          						<div class="input-group">
                        <input type="password" style="border: 0px;" placeholder="password" name="password" value="<%=user.getPassword()%>" id="myInput">
                        <div class="input-group-append">
                          <button class="btn btn-sm btn-primary" type="button" onclick="myFunction()">
                            <i class="icon-eye"></i>
                          </button>
                        </div>
                      </div>
          					</div>
          				
          				</div>
          				
          				
          					
          					
          			
          			
          			
          			</div>
          		</div>
          		
          	
          	</div>
          
          
          
          </div>
          
         </div>
        </div>
       </div>


	 <!-- plugins:js -->
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