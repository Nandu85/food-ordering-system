<%@page import="com.narola.fooddelivery.cart.CartItem"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@page import="com.narola.fooddelivery.dishes.model.Dish"%>
<%@page import="com.narola.fooddelivery.restaurants.model.Restaurant"%>
<%@page import="com.narola.fooddelivery.cart.Cart"%>
<%@page import="com.narola.fooddelivery.utility.URLConstantUser"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="MetaTag.jsp"%>

</head>
<body class="main-layout">
	

	<div class="wrapper">
		<!-- end loader -->



		<div id="content">
			<!-- header From index.jsp -->
			<%@include file="Navigation.jsp"%>
			<br>
			<br>
			<br>
			<br>
			<div class="container">
				<div class="jumbotron" align="center">

					<%
					Restaurant restaurant = (Restaurant) request.getAttribute("Restaurant");
					%>


					<h2>
						<strong><%=restaurant.getRestName()%></strong>
					</h2>
					<p><%=restaurant.getLocation().getAddressLine()%>,
						<%=restaurant.getLocation().getArea()%>,
						<%=restaurant.getLocation().getCity()%>,
						<%=restaurant.getLocation().getPincode()%>.
					</p>
					<p>
						<%
						List<Dish> catList = null;

						catList = (ArrayList) restaurant.getCategories();

						Iterator itr = catList.iterator();
						while (itr.hasNext()) {
							String str = (String) itr.next();
						%>
						<span class="badge badge-pill badge-primary"><%=str%></span>

						<%
						}
						%>

					</p>
				</div>


				<h3 align="center">
					<strong>Menu </strong>
				</h3>
				<%
				int count = 0;
				List<Dish> al = null;
				

				al = (ArrayList) restaurant.getMenu();

				itr = al.iterator();
				while (itr.hasNext()) {
					Dish d = (Dish) itr.next();
					count++;
				%>




				<div style="align-items: center; align: center;">
					<div class="row">
						<div class="col-12 col-sm-3"></div>
						<div class="card col-12 col-sm-6">

							<div class="card-body" align="center">
								<div class="row">
									<div class="col-12 col-sm-6">
										
										

										<label><h4 id="dishid"><%=d.getDishId()%>. <strong><%=d.getDishName()%></strong>
											</h4></label> <br><%=d.getIngrident()%><br> <label>-/<%=d.getPrice()%></label><br>

										<span class="badge badge-primary"><%=d.getCategory()%></span>
										<%
										if (d.getDishtype() == 0) {
										%>
										<span class="badge badge-success">Veg</span>
										<%
										} else {
										%>
										<span class="badge badge-danger">NonVeg</span>
										<%
										}
										%><br>
										<br>

										<style>
.counter {
	width: 150px;
	margin: auto;
	display: flex;
	align-items: center;
	justify-content: center;
}

.counter input {
	width: 50px;
	border: 0;
	line-height: 30px;
	font-size: 20px;
	text-align: center;
	background: #0052cc;
	color: #fff;
	appearance: none;
	outline: 0;
}

.counter span {
	display: block;
	font-size: 25px;
	padding: 0 10px;
	cursor: pointer;
	color: #0052cc;
	user-select: none;
}
</style>

										<%
										if (cart==null || cart.itemBelongToCart(d) == null) {
										%>
										<%-- <div class="counter">
											<span class="down" onClick='decreaseCount(event, this)'>-</span>
											<input type="text" value="0" name="qty"
												id="dishqty<%=d.getDishId()%>"> <span class="up"
												onClick='increaseCount(event, this)'>+</span>
										</div> --%>

										<button class="btn btn-info addToCart" value=<%=d.getDishId()%>
											onclick="addToCart(this)">Add to Cart</button>


										<%
										} else {
											CartItem item=cart.itemBelongToCart(d);
										%>


										

										<div class="counter">
											<span class="down" onClick='decreaseCount(event, this)'>-</span>
											<input type="text" value="<%=item.getQty()%>" name="qty"
												id="<%=item.getDish().getDishId()%>"> 
											<span class="up" onClick='increaseCount(event, this)'>+</span>
										</div>

										
										<%
										}
										%>

									</div>


									<div class="col-12 col-sm-6">
										<img src="data:image/png;base64,<%=d.getPhotoAsBase64()%>"
											alt="No image found" style="width: 100%;">
									</div>

								</div>
							</div>

						</div>
					</div>
					<br />

					<%
					}
					%>
					<%
					if (count == 0) {
					%>
					<b>No Record</b>
					<%
					}
					%>


				</div>



			</div>




			<!-- end header -->
			<br>
			<br>
			<br>
			<br>
			<input type="hidden" id="address" value="<%= request.getRequestURL().toString()%>">
		</div>
	</div>

	<%@include file="Scripts.jsp"%>

	<script type="text/javascript">
	
		function increaseCount(a, b) {
			var input = b.previousElementSibling;
			var value = parseInt(input.value, 10);
			value = isNaN(value) ? 0 : value;
			value++;
			input.value = value;
			window.location.href = document.getElementById("address").value.slice(0,-19)+"AddtoCart?dishId="
				+ input.id + "&qty=" + input.value;
		}

		function decreaseCount(a, b) {
			var input = b.nextElementSibling;
			var value = parseInt(input.value, 10);
			if (value > 0) {
				value = isNaN(value) ? 0 : value;
				value--;
				input.value = value;
				window.location.href = document.getElementById("address").value.slice(0,-19)+"AddtoCart?dishId="
					+ input.id + "&qty=" + input.value;
			}
		}

		function addToCart(e) {
			var user=document.getElementById("userId");
			if(user.value==0){
				alert("Please Login...")
			}
			else{
				
				var qty = document.getElementById("dishqty" + e.value);
				var dishid = e.value;
				window.location.href = document.getElementById("address").value.slice(0,-19)+"AddtoCart?dishId="
						+ e.value + "&qty=1"; 
				console.log(document.getElementById("address").value.slice(0,-19)+"AddtoCart?dishId=");
			}
			

		}
		
		
		
	</script>

</body>
</html>