<%@page import="com.narola.fooddelivery.PreviousUrl"%>
<%@page import="com.narola.fooddelivery.URLConstantOfServlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.narola.fooddelivery.URLConstantUser"%>
<%@page import="com.narola.fooddelivery.user.User"%>
<%@ page import="com.narola.fooddelivery.Cart.Cart"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
User user = ((User) request.getSession().getAttribute("user"));
Cart cart= (Cart)request.getSession().getAttribute("Cart");
request.getSession().setAttribute("url", request.getQueryString());

%>

<input type="hidden" value=<%=user==null?0:user.getUserId()%> id="userId">
<style>
#count {
       
        position: relative;
        width: 20px;
        height: 20px;
        border-radius: 100%;
        background-color: #ff4d6b;
        font-size: 15px;
        color: #ffffff;
        text-align: center;
        top: 95%;
        left: 70%; }
        
/* .rating {
        position: absolute;
   	top: -15%;     
    display: flex;
    flex-direction: row-reverse;
    
}

.rating>input {
    display: none
}

.rating>label {
    position: relative;
    width: 1em;
    font-size: 6vw;
    color: #FFD600;
    cursor: pointer
}

.rating>label::before {
    content: "\2605";
    position: absolute;
    opacity: 0
}

.rating>label:hover:before,
.rating>label:hover~label:before {
    opacity: 1 !important
}

.rating>input:checked~label:before {
    opacity: 1
}

.rating:hover>input:checked~label:before {
    opacity: 0.4
} */
        
</style>

<header>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-3">
				<div class="full">
					<a class="logo" href="index.jsp"><img src="images/logo.png"
						alt="#" /></a>
				</div>
			</div>
			<div class="col-md-9">
				<div class="full">
					<div class="right_header_info">
						<ul>
							<li class="dinone"><a
								href="<%=request.getContextPath() + URLConstantUser.DASHBOARD%>">Home</a></li>
							<li class="dinone"><a href="#">About</a></li>
							<li class="dinone"><a href="#">Blog</a></li>
							<li class="dinone"><a href="#">Contact Us</a></li>
							<%
							if (user == null) {
							%>
							<li class="button_user"><a class="button active"
								href="<%=request.getContextPath() + URLConstantOfServlet.LOGIN%>">Login</a>
								<a class="button"
								href="<%=request.getContextPath() + URLConstantOfServlet.REGISTER%>">Register</a></li>
							<li><img style="margin-right: 15px;"
								src="images/search_icon.png" alt="#"></li>
							<%
							} else {
							%>
							<li><a href="<%=request.getContextPath() + URLConstantOfServlet.PROFILE%>"><i class="fa-solid fa-user"></i>&nbsp;&nbsp;<span
								class="font-weight-normal"><%=user.getUsername()%> </span></a></li>



							<%
							}
							%>
							
							<li>
							<%if(cart!=null) %>
							<span class="badge badge-danger" id="count"><c:out value="${sessionScope.Cart.getItems().size()}"></c:out></span>
								<!-- Button trigger modal -->
								<button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#exampleModal">
								
								<i style="font-size:25px" class="fas fa-shopping-cart"></i>
								</button>
							</li>

							<li></li><li></li>
						</ul>



					</div>
				</div>
			</div>
		</div>




	</div>
</header>


<!-- Modal -->
<div class="modal modal-secondary" id="exampleModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="exampleModalLabel"><b>Order Summary</b></h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
			<%if(cart==null){ %>
			You haven't order anything, yet.
			<%} else { %>
			<b><%=cart.getItems().size()%> Item(s)</b>
			
			<c:forEach items="${sessionScope.Cart.getItems()}" var="item">
			<div class="row">
				<div class="col-12 col-sm-6">
					<c:out value="${item.getDish().getDishName()}"></c:out>
				</div>
				<div class="col-12 col-sm-6">
					<i class="fa fa-inr"></i>
					<c:out value="${item.getDish().getPrice()}"></c:out> X
					<c:out value="${item.getQty()}"></c:out>
				</div>
			</div>
			
			</c:forEach>
			<div class="row">
				<div class="col-12 col-sm-6"></div>
				<div class="col-12 col-sm-6">
					<strong style="color: blue;"><i class="fa fa-inr"></i><c:out value="${sessionScope.Cart.getTotal()}"></c:out></strong>
				</div>
			</div>
			<%} %>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<a href="<%=request.getContextPath()+URLConstantOfServlet.CHECKOUT%>"><button type="button" class="btn btn-warning">Proceed to Payment</button></a>
			</div>
		</div>
	</div>
</div>

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