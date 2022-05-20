<%@page import="com.narola.fooddelivery.utility.Constant"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.narola.fooddelivery.utility.Constant" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="MetaTag.jsp"%>
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
			<br> <br> <br> <br>
			<c:if test="${ErrMsg!=NULL}">
<script type="text/javascript">

alert("${ErrMsg}");

</script>
</c:if>
			<div class="container">
				<h2>
					<strong>My Account</strong>
				</h2>
				<div class="row">
					<div class="col-12 col-md-3">
						<div style="background-color: #e6e6e6; border-radius: 2%;">
							<div class="container">
								<br>
								<ul>
									<li>My Account</li>
									<hr>
									<li>My Addresses</li>
									<hr>
									<li>WishList</li>
									<hr>
									<li>Past Orders</li>
									<hr>
									<li><a href="<%=request.getContextPath()+URLConstantOfServlet.LOGOUT%>">Logout</a></li><br>
								</ul>

							</div>
						</div>


					</div>

					<div class="col-12 col-sm-9">
						<div style="background-color: #e6e6e6; border-radius: 2%;">
							<br>
							<h2 align="left" style="margin-left: 25px;">
								<b>Account Information</b>
							</h2>
							<h4 align="right" style="margin-right: 25px;">
								<button type="button" class="btn btn-light" data-toggle="modal"
									data-target="#EditModal">Edit</button>
							</h4>
							<div class="row">
								<div class="col-12 col-sm-4">
									<img class="rounded-circle" alt="Not Found"
										src="images/faces/faceX.png" style="padding: 10px;"> <br>
									<br>
								</div>
								<div class="col-12 col-sm-8">
									<div class="row">
										<div class="col-12 col-sm-6">
											<font>Username</font>
											<h3>
												<c:out value="${sessionScope.user.getUsername()}"></c:out>
											</h3>
										</div>
										<div class="col-12 col-sm-6">
											<font>Email</font>
											<h3>
												<c:out value="${sessionScope.user.getEmail()}"></c:out>
											</h3>
										</div>
									</div>
									<!-- <div class="row"> -->
									
									<!-- </div> -->
									<br> <br>
								</div>
							</div>


						</div>
						<br>
						<br>
						<div style="background-color: #e6e6e6; border-radius: 2%;">
							<br>
							<h2 align="left" style="margin-left: 25px;">
								<b>Address Information</b>
							</h2>


							<c:set var="i" value="0"></c:set>

							<c:forEach items="${sessionScope.user.getLocations()}" var="loc">
								<c:set var="i" value="${i+1}"></c:set>
								<font style="margin-left: 25px;">Address-<c:out
										value="${i}"></c:out></font>
								<h3 align="left" style="margin-left: 25px;">
									<c:out value="${loc.toString()}"></c:out>
								</h3>
								<h4 align="right" style="margin-right: 25px;">
									<button type="button" class="btn btn-light" data-toggle="modal"
										data-target="#EditModal${loc.getLocationId()}">Edit</button>
								</h4>

								<div class="modal modal-secondary"
									id="EditModal${loc.getLocationId()}" tabindex="-1"
									role="dialog" aria-labelledby="exampleModalLabel"
									aria-hidden="true">
									<div class="modal-dialog" role="document">
										<div class="modal-content" style="background: #e6e6e6;">
											<div class="modal-header">
												<h4 class="modal-title" id="exampleModalLabel">
													<b>Edit Profile${loc.getLocationId()}</b>
												</h4>
												<button type="button" class="close" data-dismiss="modal"
													aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div>
											<div class="modal-body">
												<form
													action="<%=request.getContextPath() + URLConstantOfServlet.ADDLOCATION%>"
													method="post">
													<input name="LocId" class="form-control" type="hidden"
														value="${loc.getLocationId()}">
														<label
														for="addressline">Address Line</label> 
													<input name="addressline" class="form-control" type="text"
														value="${loc.getAddressLine()}"> 
														<label
														for="area">Area</label> <input name="area"
														class="form-control" type="text"
														value="${loc.getArea()}"> 
														<label
														for="city">City</label> <input name="city"
														class="form-control" type="text"
														value="${loc.getCity()}"> 
														<label
														for="state">State</label> <input name="state"
														class="form-control" type="text"
														value="${loc.getState()}">
														<label
														for="pincode">Pincode</label> <input name="pincode"
														class="form-control" type="text"
														value="${loc.getPincode()}">
													<button type="button" class="btn btn-secondary"
														data-dismiss="modal">Close</button>
													<button type="submit" class="btn btn-primary">Save</button>
												</form>

											</div>

										</div>
									</div>
								</div>


							</c:forEach>




							<br> <a class="btn btn-light" style="margin-left: 25px;"
								href="<%=request.getContextPath() + URLConstantOfServlet.ADDLOCATION%>">Add
								Location</a>
							<!-- </div> -->
							<br> <br>



						</div>
						<br>
						<br>

						<div style="background-color: #e6e6e6; border-radius: 2%;">
							<br>
							<div class="container" style="margin-left: 25px;">
								<h2 align="left">
								<b>Your Orders	</b>
							</h2>
							<c:forEach items="${user.getOrders()}" var="order">
								<div class="row">
									<div class="col-12 col-sm-8">
										<h4 align="left">From ${order.getRestaurant().getRestName()}</h4>
									</div>
									<div class="col-12 col-sm-2">
									<c:if test="${order.getTransaction()==NULL}">
									<form method="POST" action="https://api.razorpay.com/v1/checkout/embedded">
  <input type="hidden" name="key_id" value="rzp_test_f2BZIUq23nSlLZ">
  <input type="hidden" name="amount" value="${order.getTotal()}">
  <input type="hidden" name="order_id" value="${order.getRazorpayOrderId()}">
  <input type="hidden" name="name" value="Acme Corp">
  <input type="hidden" name="description" value="A Wild Sheep Chase">
  <input type="hidden" name="image" value="https://cdn.razorpay.com/logos/BUVwvgaqVByGp2_large.png">
  <input type="hidden" name="prefill[name]" value="${sessionScope.user.getUsername()}">
  <input type="hidden" name="prefill[contact]" value="9123456780">
  <input type="hidden" name="prefill[email]" value="gaurav.kumar@example.com">
  <input type="hidden" name="notes[shipping address]" value="L-16, The Business Centre, 61 Wellfield Road, New Delhi - 110001">
  <input type="hidden" name="callback_url" value="<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+URLConstantOfServlet.TRANSACTION_SUCCESS%>">
  <input type="hidden" name="cancel_url" value="https://example.com/payment-cancel">
  <button id="paybutton" class="btn btn-primary btn-sm" style="color: white;">Make Payment</button>
</form>
									
									 </c:if>
									 <c:if test="${order.getTransaction().getPaymentStatus()==Constant.PAYMENT_SUCCESS}">
										<span class="badge badge-info" style="font-size: 15px;"><c:out value="${Constant.ORDER_STATUS.get(order.getOrderStatus())}"></c:out></span>
									</c:if>
									</div>
								</div>
							
								
								
								<div class="row">
									<div class="col-12 col-sm-8">
										<c:forEach items="${order.getItems()}" var="itm">
											<c:out value="${itm.getDish().getDishName()}"></c:out>&nbsp;X&nbsp;<c:out value="${itm.getQty()}"></c:out>&nbsp;,
										</c:forEach><br>
										<fmt:formatDate type = "both" value = "${order.getDate()}" timeStyle="short"/>
										
									</div>
									<div class="col-12 col-sm-4">
										<c:out value="${order.getTotal()}"></c:out><br>
										
										<c:if test="${order.getOrderStatus()==Constant.ORDER_DELIVERED}">
										
										<button type="button" class="btn btn-light" data-toggle="modal"
										data-target="#rating${order.getOrderId()}">Rate Food</button>
										
										</c:if>
									</div>
								</div>
								<c:if test="${order.getOrderStatus()==Constant.ORDER_DELIVERED}">
								<div class="modal modal-secondary" id="rating${order.getOrderId()}" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content" style="background: #ffffff;">
				<div class="modal-header">
					<h4 class="modal-title" id="exampleModalLabel">
						<b>Rating</b>
					</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					
					<form
						action="<%=request.getContextPath() + URLConstantOfServlet.ADDRETING%>"
						method="post">
						<input type="text" value="${order.getOrderId()}" name="orderId">
						<div class="rating"> <input type="radio" name="rating" value="5" id="5"><label for="5">5</label> <input type="radio" name="rating" value="4" id="4"><label for="4">4</label> <input type="radio" name="rating" value="3" id="3"><label for="3">3</label> <input type="radio" name="rating" value="2" id="2"><label for="2">2</label> <input type="radio" name="rating" value="1" id="1"><label for="1">1</label>
						</div><br><br><label>Comments</label><br>
						<textarea rows="4" cols="60" name="comments"></textarea><br>
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Save</button>
					</form>

				</div>

			</div>
		</div>
	</div>
						</c:if>
							<hr style="margin-right: 28px;">
							
							</c:forEach>
							
							</div>
							
						</div>
						<br>
						<br>	

					</div>
				</div>



			</div>


		</div>

	</div>
	
	
	
	
	
	
	
	


	<div class="modal modal-secondary" id="EditModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content" style="background: #e6e6e6;">
				<div class="modal-header">
					<h4 class="modal-title" id="exampleModalLabel">
						<b>Edit Profile</b>
					</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form
						action="<%=request.getContextPath() + URLConstantOfServlet.PROFILE%>"
						method="post">
						<input name="userId" class="form-control" type="text"
							value="${sessionScope.user.getUserId()}"> <label
							for="username">UserName</label> <input name="username"
							class="form-control" type="text"
							value="${sessionScope.user.getUsername()}"> <label
							for="email">Email</label> <input name="email"
							class="form-control" type="text"
							value="${sessionScope.user.getEmail()}"> <label
							for="password">Password</label> <input name="password"
							class="form-control" type="text"
							value="${sessionScope.user.getPassword()}">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Save</button>
					</form>

				</div>

			</div>
		</div>
	</div>

	<%@include file="Scripts.jsp"%>
</body>
</html>