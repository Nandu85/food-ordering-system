<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.narola.fooddelivery.utility.*"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="MetaTag.jsp"%>
</head>
<body class="main-layout">
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
			<br><br><br><br>
			<div class="container">
				<div class="row">
					<div class="col-12 col-sm-6">
						<br><h2>
						<strong>My Cart</strong>
						<c:if test="${sessionScope.Cart!=null}">(<c:out value="${sessionScope.Cart.getItems().size()}"></c:out>)</c:if></h2>
						<h3 align="right">Total:&nbsp;&nbsp;&nbsp;&nbsp;  <i class="fa fa-inr"></i>${sessionScope.Cart.getTotal()}</h3>
						<div style="background-color: #e6e6e6;border-radius: 2%;">
						<c:forEach items="${sessionScope.Cart.getItems()}" var="item">
							<section style="padding: 10px;">
								<div class="row">
									<div class="col-12 col-sm-4">
										<img class="img-thumbnail" alt="Not Found" src="data:image/png;base64,${item.getDish().getPhotoAsBase64()}" style="width: 100%;padding: 10px;">
									</div>
									<div class="col-12 col-sm-6">
										<b><c:out value="${item.getDish().getDishName()}"></c:out></b><br>
										<b><i class="fa fa-inr"></i><c:out value="${item.getAmount()}"></c:out></b><br>
										<div class="counter">
											<span class="down" onClick='decreaseCount(event, this)'>-</span>
											<input type="text" value="${item.getQty()}" name="qty"
												id="${item.getDish().getDishId()}"> 
											<span class="up" onClick='increaseCount(event, this)'>+</span>
										</div>
									</div>
								</div>
							</section>
						<hr>
						
						</c:forEach>
						
						</div>
						
						
					</div>
					
					<div class="col-12 col-sm-6 container">
						<br><h2 align="center">
						<strong>Payment Detail</strong>
						</h2><br>
						<div style="background-color: #e6e6e6;border-radius: 2%;margin-left: 10px;">
							<div class="container">
							<br>
								<h3><strong>Apply Coupon</strong></h3>
								Apply coupon to see best offers :)<br><br>
						
							</div>
							
						</div><br>
						<div style="background-color: #e6e6e6;border-radius: 2%;margin-left: 10px;">
							<div class="container">
							<br>
								<h3><strong>Bill Detail</strong></h3>
								<div class="row">
									<div class="col-12 col-sm-6">
										<p>Item Total</p>
									</div>
									<div class="col-12 col-sm-6">
										<p><i class="fa fa-inr"></i><c:out value="${sessionScope.Cart.getTotal()}"></c:out></p>
									</div>
								</div>
								<div class="row">
									<div class="col-12 col-sm-6">
										<p>Discount</p>
									</div>
									<div class="col-12 col-sm-6">
										<p><i class="fa fa-inr"></i>0</p>
									</div>
								</div>
								<div class="row">
									<div class="col-12 col-sm-6">
										<p>Taxes & Charges</p>
									</div>
									<div class="col-12 col-sm-6">
										<p><i class="fa fa-inr"></i>0</p>
									</div>
								</div><hr>
								<div class="row">
									<div class="col-12 col-sm-6">
										<b>To Pay</b>
									</div>
									<div class="col-12 col-sm-6">
										<b><i class="fa fa-inr"></i><c:out value="${sessionScope.Cart.getTotal()}"></c:out></b>
									</div>
								</div><br><br>
								
								
							</div>
							
						</div>
						<br>
						<form action="<%=request.getContextPath()+URLConstantOfServlet.PLACEORDER%>" method="get">
						<div style="background-color: #e6e6e6;border-radius: 2%;margin-left: 10px;">
							<div class="container">
								<br>
								<h3><strong>Select Location</strong></h3>
								<c:forEach items="${sessionScope.user.getLocations()}" var="Loc">
									<input type="radio" name="Location" value="${Loc.getLocationId()}" required="required">&nbsp;&nbsp;
									<label for="card"><c:out value="${Loc.toString()}"></c:out> </label>
									<br>
								</c:forEach>
								
								
								
							</div>
							
						
						</div>
						<br>
						<button type="submit" class="btn btn-primary" style="margin-left: 10px;">Place Order</button>
						</form>
						<br><br>
					</div>
			
				</div>
			
			
			
			</div>
			
			<input type="hidden" id="address" value="<%= request.getRequestURL().toString()%>">
		</div>
	</div>

<script type="text/javascript">
		function increaseCount(a, b) {
			var input = b.previousElementSibling;
			var value = parseInt(input.value, 10);
			value = isNaN(value) ? 0 : value;
			value++;
			input.value = value;
			window.location.href = document.getElementById("address").value.slice(0,-13)+"UpdateItem?dishId="
				+ input.id + "&qty=" + input.value;
		}

		function decreaseCount(a, b) {
			var input = b.nextElementSibling;
			var value = parseInt(input.value, 10);
			if (value > 0) {
				value = isNaN(value) ? 0 : value;
				value--;
				input.value = value;
				window.location.href = document.getElementById("address").value.slice(0,-13)+"UpdateItem?dishId="
					+ input.id + "&qty=" + input.value;
			}
		}

		
	</script>
<%@include file="Scripts.jsp" %>
</body>
</html>