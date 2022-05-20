<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.narola.fooddelivery.utility.Constant" %>

<!DOCTYPE html>
<html>
<head>
<%@include file="MetaTag.jsp"%>
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
          
          	<div class="col-lg-12 grid-margin stretch-card">
          		<div class="card">
          			<div class="card-body">
          				<h4 class="card-title">Orders Received</h4>
          				
          				<div align="left">
          				
          					<form action="<%=request.getContextPath()+URLConstantOfServlet.NEW_ORDER%>" method="get" id="dates">
          					Enter Id: <input name="id" type="text"/>&nbsp;&nbsp;
          					<button type="submit" name="submit">Search</button>
          				</form><br>
          				</div>
          				<div align="center">
          <form action="<%=request.getContextPath()+URLConstantOfServlet.NEW_ORDER%>" method="get" id="dates">
          Start Date: <input name="startDate" type="date" value="go"/>&nbsp;&nbsp; 
          End Date: <input id="datepicker" name="endDate" type="date" value="go"/>
          &nbsp;&nbsp;
          <select id='filterText' name="status">
								<option disabled selected>Select</option>
								<option value='1'>New</option>
								<option value='2'>Accepted</option>
								<option value='3'>Preparing</option>
								<option value='4'>On The Way</option>
								<option value='5'>Delivered</option>
								<option value='6'>Rejected</option>
								<option>All</option>
							</select>
							&nbsp;&nbsp;
					<button type="submit" name="submit">Search</button>
          </form>
          </div><br>
          
          <div align="right">
          <form action="<%=request.getContextPath()+URLConstantOfServlet.NEW_ORDER%>" method="get">
          	Sort By:&nbsp;&nbsp;
          	<select id='sortData' name="sort">
								<option disabled selected>Select</option>
								<option value='OrderId'>OrderId</option>
								<option value='CreatedOn'>Time</option>
								<option value='Total'>Total</option>
								<option value='Status'>Status</option>
								<option value='Restaurant'>Restaurant</option>
								<option value='User'>User</option>
							</select>
          
          </form>
          </div>
          				
          				
          				
          				<table class="table table-striped">
          					<thead>
          						<tr>
          							<th>OrderId</th>
          							<th>Detail</th>
          							<th>Time</th>
          							<th>User</th>
          							<th>Restaurant</th>
          							
          							<th>Total</th>
          							<th>Payment</th>
          							<th>Status</th>
          						</tr>
          						
          					</thead>
          					
          					<tbody>
          					<c:forEach items="${Orders}" var="order">
          					
          					<tr>
          					<td><c:out value="${order.getOrderId()}"></c:out></td>
          					<td>
          					<c:forEach items="${order.getItems()}" var="itm">
          						<c:out value="${itm.getDish().getDishName()}"></c:out>&nbsp;
          						X&nbsp;<c:out value="${itm.getQty()}"></c:out>,&nbsp;&nbsp;	
          					
          					</c:forEach>
          					</td>
          					<td><fmt:formatDate type = "both" value = "${order.getDate()}" timeStyle="short"/></td>
          					<td><c:out value="${order.getUser().getUsername()}"></c:out> </td>
          					
          					<td><c:out value="${order.getRestaurant().getRestName()}"></c:out> </td>
          					
          					<td><c:out value="${order.getTotal()}"></c:out> </td>
          					<td>
          					<c:if test="${order.getTransaction().getPaymentStatus()==Constant.PAYMENT_SUCCESS}">
          					<span class="badge badge-success">Successful</span>
          					</c:if>
          					<c:if test="${order.getTransaction()==NULL}">
          					<span class="badge badge-danger">Failed</span>
          					</c:if>
          					
          					</td>
          					<td><c:out value="${Constant.ORDER_STATUS.get(order.getOrderStatus())}"></c:out>  </td> 	
          					
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
         
     </div>






<%@include file="Scripts.jsp"%>
</body>
</html>