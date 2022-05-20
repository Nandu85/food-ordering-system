<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.narola.fooddelivery.utility.Constant" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
          				<h4 class="card-title">New Orders</h4>
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
								
							</select>
          
          </form>
          </div>
          				<table class="table table-striped">
          					<thead>
          						<tr>
          							<th>OrderId</th>
          							<th>Detail</th>
          							<th>Time</th>
          							<th>Total</th>
          							<th>Status</th>
          							<th>Action</th>
          						</tr>
          						
          					</thead>
          					
          					<tbody>
          					<c:forEach items="${Orders}" var="order">
          					
          					<c:if test="${order.getRestaurant().getRestId()==sessionScope.user.getRestaurantId() && order.getTransaction().getPaymentStatus()==Constant.PAYMENT_SUCCESS}">
          					
          					<tr>
          					<td><c:out value="${order.getOrderId()}"></c:out></td>
          					<td>
          					<c:forEach items="${order.getItems()}" var="itm">
          						<c:out value="${itm.getDish().getDishName()}"></c:out>&nbsp;
          						X&nbsp;<c:out value="${itm.getQty()}"></c:out>,&nbsp;&nbsp;	
          					
          					</c:forEach>
          					</td>
          					<td><fmt:formatDate type = "both" value = "${order.getDate()}" timeStyle="short"/></td>
          					<td><c:out value="${order.getTotal()}"></c:out> </td>
          					<td><span class="badge badge-${Constant.ORDER_STATUS_COLOR.get(order.getOrderStatus())}"><c:out value="${Constant.ORDER_STATUS.get(order.getOrderStatus())}"></c:out></span></td>
          					<td>
          					<c:if test="${order.getOrderStatus()<Constant.ORDER_DELIVERED}">
          					<div class="row">
          						<form action="<%=request.getContextPath()+URLConstantOfServlet.NEW_ORDER%>" method="post">
          						<input type="hidden" value="${order.getOrderId()}" name="orderId">
          						<input type="hidden" value="${order.getOrderStatus()+1}" name="status">
          						<button class="btn btn-success btn-sm" type="submit"><c:out value="${Constant.ORDER_PROCESS.get(order.getOrderStatus())}"></c:out></button>
          					</form>&nbsp;
          					<form action="<%=request.getContextPath()+URLConstantOfServlet.NEW_ORDER%>" method="post">
          						<input type="hidden" value="${order.getOrderId()}" name="orderId">
          						<input type="hidden" value="<%=Constant.ORDER_REJECTED%>" name="status">
          						<button class="btn btn-danger btn-sm" type="submit">Reject</button>
          					</form>
          					
          					</div>
          					
          					</c:if>
          					</td>
          					
          					</tr>
          					
          					</c:if>
          					
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