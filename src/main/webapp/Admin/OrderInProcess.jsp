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
          				
          				<table class="table table-striped">
          					<thead>
          						<tr>
          							<th>OrderId</th>
          							<th>Detail</th>
          							<th>Time</th>
          							<th>Total</th>
          							<th>Update</th>
          							<th>Status</th>
          						</tr>
          						
          					</thead>
          					
          					<tbody>
          					<c:forEach items="${Orders}" var="order">
          					
          					<c:if test="${order.getRestaurant().getRestId()==sessionScope.user.getRestaurantId() && order.getTransaction().getPaymentStatus()==Constant.PAYMENT_SUCCESS && order.getOrderStatus()>=Constant.ORDER_ACCEPTED && order.getOrderStatus()<Constant.ORDER_DELIVERED}">
          					
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
          					
          					<td>
          					<form action="<%=request.getContextPath()+URLConstantOfServlet.PROCESSING_ORDER%>" method="post">
          						<input type="hidden" value="${order.getOrderId()}" name="orderId">
          						<input type="hidden" value="${order.getOrderStatus()+1}" name="status">
          						<button class="btn btn-success btn-sm" type="submit">Update</button>
          					</form>
          					</td>
          					<td>
          					<c:set var="i" value="${order.getOrderStatus()+1}"></c:set>
          					<c:out value="${i}"></c:out>
          					<c:out value="${Constant.ORDER_STATUS.get(i)}"></c:out>
          					<c:out value="${Constant.ORDER_STATUS.get(order.getOrderStatus())}"></c:out> 
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