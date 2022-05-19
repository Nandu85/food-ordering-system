<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Stellar Admin</title>
<%@ include file="MetaTag.jsp" %>
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
          					<form action="<%=request.getContextPath()+URLConstantOfServlet.PROFILE%>" method="post">
          						<h5 align="left"><strong><input class="form-control" name="username" value="<%=user.getUsername() %>"> </strong></h5>
          						
          						<input type="hidden" name="userId" value="<%=user.getUserId()%>">
          						
          						
          						<input class="form-control" name="email" value="<%=user.getEmail()%>">
          						<br>
          						<div class="input-group">
                        <input class="form-control" type="password" placeholder="password" name="password" value="<%=user.getPassword()%>" id="myInput">
                        <div class="input-group-append">
                          <button class="btn btn-sm btn-primary" type="button" onclick="myFunction()">
                            <i class="icon-eye"></i>
                          </button>
                        </div>
                      </div>
                      <br><br>
                      <button class="btn btn-info" type="submit">Save</button>
                      <button class="btn btn-secondary" type="reset">Cancel</button>
                      
                      	</form>
                      
          					</div>
          				
          				</div>
          				
          				<%if(user.getAdmin()==3 && user.getRestaurant()!=null){ %>
          				<br><hr>
          				<br>
						<h1 class="card-title" align="center">Restaurant Profile</h1>
						
						
						 <br>
						<div class="row">
							<div class="col-12 col-lg-3">
          						<img class="img-md" src="data:image/png;base64,<%=user.getRestaurant().getRestphotoAsBase64()%>" alt="Profile image" style="width: 100%;">	
          					
          					</div>        
          				
          					<div class="col-12 col-lg-6">
          					<h2 align="right"><a href="<%=request.getContextPath() + URLConstantOfServlet.UPDATERESTAURANT%>?RestaurantId=<%=user.getRestaurantId()%>">
								<i class="fa-solid fa-pen-to-square"
								style="font-size: 24px; color: black; text-align: right"></i>
							</a></h2>
          						<h5 align="left"><strong><%=user.getRestaurant().getRestName() %></strong></h5>
          						<h6 align="left"><i class="fas fa-envelope"></i>&nbsp;&nbsp;<%=user.getRestaurant().getEmail()%></h6>
          						<h6 align="left">
          						<c:forEach items="${sessionScope.user. getRestaurant().getCategories()}" var="cat">
          						<span class="badge badge-pill badge-primary"><c:out value="${cat}"></c:out></span>
          						</c:forEach>
          						</h6>
          						<h6 align="left">
          						<i class="fa fa-map-marker-alt" aria-hidden="true"></i>&nbsp;&nbsp;
          							<%=user.getRestaurant().getLocation().getAddressLine()%>,&nbsp;
          							<%=user.getRestaurant().getLocation().getArea()%>,&nbsp;
          							<%=user.getRestaurant().getLocation().getCity()%>,&nbsp;
          							<%=user.getRestaurant().getLocation().getPincode()%>
          						</h6>          					
          						
          					</div>
          						 		
          				</div>		
          				<%} %>
          					
          					
          			
          			
          			
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