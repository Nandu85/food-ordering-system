<%@page import="com.narola.fooddelivery.URLConstantUser"%>
<%@page import="com.narola.fooddelivery.URLConstantOfServlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.narola.fooddelivery.URLConstantUser"%>
<%@page import="com.narola.fooddelivery.URLConstantOfServlet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
<!-- basic index.jsp -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- mobile metas -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport" content="initial-scale=1, maximum-scale=1">
<!-- site metas -->
<title>Foodies</title>
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="author" content="">
<!-- bootstrap css -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- owl css -->
<link rel="stylesheet" href="css/owl.carousel.min.css">
<!-- style css -->
<link rel="stylesheet" href="css/style_user.css">
<!-- responsive-->
<link rel="stylesheet" href="css/responsive.css">
<!-- awesome fontfamily -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/fontawesome.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/solid.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/js/solid.min.js">
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
</head>
<!-- body -->

<body class="main-layout">
	<!-- loader  -->
	<div class="loader_bg">
		<div class="loader">
			<img src="images/loading.gif" alt="" />
		</div>
	</div>

	<div class="wrapper">
		<!-- end loader -->



		<div id="content">
			<!-- header From index.jsp -->
			<%@include file="Navigation.jsp"%>
			<!-- end header -->
			<!-- start slider section -->
			<div class="slider_section">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<div class="full">
								<div id="main_slider" class="carousel vert slide"
									data-ride="carousel" data-interval="5000">
									<div class="carousel-inner">
										<div class="carousel-item active">
											<div class="row">
												<div class="col-md-5">
													<div class="slider_cont">
														<h3>
															Discover Restaurants<br>That deliver near You
														</h3>
														<p>It is a long established fact that a reader will be
															distracted by the readable content of a page when looking
															at its layout.</p>
														<a class="main_bt_border" href="<%=request.getContextPath()+URLConstantOfServlet.SEARCHRESTAURANT%>">Explore Now</a>
													</div>
												</div>
												<div class="col-md-7">
													<div class="slider_image full text_align_center">
														<img class="img-responsive" src="images/burger_slide.png"
															alt="#" />
													</div>
												</div>
											</div>
										</div>
										<div class="carousel-item">
											<div class="row">
												<div class="col-md-5">
													<div class="slider_cont">
														<h3>
															Discover Restaurants<br>That deliver near You
														</h3>
														<p>It is a long established fact that a reader will be
															distracted by the readable content of a page when looking
															at its layout.</p>
														<a class="main_bt_border" href="<%=request.getContextPath()+URLConstantOfServlet.SEARCHRESTAURANT%>">Explore Now</a>
													</div>
												</div>
												<div class="col-md-7 full text_align_center">
													<div class="slider_image">
														<img class="img-responsive" src="images/Untitled.png"
															alt="#" />
													</div>
												</div>
											</div>
										</div>
									</div>
									<a class="carousel-control-prev" href="#main_slider"
										role="button" data-slide="prev"> <i class="fa fa-angle-up"></i>
									</a> <a class="carousel-control-next" href="#main_slider"
										role="button" data-slide="next"> <i
										class="fa fa-angle-down"></i>
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- end slider section -->

<%

if(request.getAttribute("Dishes")==null)

//request.getRequestDispatcher(URLConstant_User.DASHBOARD_SERVLET).forward(request, response); 
response.sendRedirect(request.getContextPath()+URLConstantUser.DASHBOARD_SERVLET); 
%>
<%-- <c:out value="${Dishes}"></c:out> --%>


<c:forEach items="${Categories}" var="cat">
			<!-- section -->
			<section class="resip_section">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<div class="ourheading">
								<h2><c:out value="${cat.getCategoryName()}"></c:out></h2>
							</div>
						</div>
						<div class="container">
							<div class="row">
								<div class="col-md-12">
									<div class="owl-carousel owl-theme">

									<c:forEach items="${SubCategories}" var="subCat">
										<c:if test="${subCat.getCategory().getCategoryId()==cat.getCategoryId()}">
										<div class="item">
										<a href="<%=request.getContextPath()+URLConstantOfServlet.VIEWDISH%>?id=${subCat.getCategoryId()}">
											<div class="product_blog_img">
												<img class="img-fluid" src="data:image/png;base64,${subCat.getImageAsBase64()}" alt="#" style="border-radius: 100%;height:200px;"/>
											</div>
										</a>
											<div class="product_blog_cont">
												<h3><c:out value="${subCat.getCategoryName()}"></c:out></h3>
												<%-- <h4>
													<span class="theme_color">$</span><c:out value="${dish.getPrice()}"></c:out>
												</h4> --%>
											</div>
										</div>
										</c:if>
									</c:forEach>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>

		</c:forEach>









			<!-- section -->
			<section class="resip_section">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<div class="ourheading">
								<h2>More From Us</h2>
							</div>
						</div>
						<div class="container">
							<div class="row">
								<div class="col-md-12">
									<div class="owl-carousel owl-theme">

										<div class="item">
											<div class="product_blog_img">
												<img src="images/rs1.png" alt="#" />
											</div>
											<div class="product_blog_cont">
												<h3>Pizzas</h3>
												<h4>
													<span class="theme_color">$</span>10
												</h4>
											</div>
										</div>
										<div class="item">
											<div class="product_blog_img">
												<img src="images/rs2.png" alt="#" />
											</div>
											<div class="product_blog_cont">
												<h3>Noodles</h3>
												<h4>
													<span class="theme_color">$</span>20
												</h4>
											</div>
										</div>
										<div class="item">
											<div class="product_blog_img">
												<img src="images/rs3.png" alt="#" />
											</div>
											<div class="product_blog_cont">
												<h3>PavBhaji</h3>
												<h4>
													<span class="theme_color">$</span>30
												</h4>
											</div>
										</div>
										<div class="item">
											<div class="product_blog_img">
												<img src="images/rs4.png" alt="#" />
											</div>
											<div class="product_blog_cont">
												<h3>DahiKofta</h3>
												<h4>
													<span class="theme_color">$</span>40
												</h4>
											</div>
										</div>
										<div class="item">
											<div class="product_blog_img">
												<img src="images/rs5.png" alt="#" />
											</div>
											<div class="product_blog_cont">
												<h3>Sandwitches</h3>
												<h4>
													<span class="theme_color">$</span>50
												</h4>
											</div>
										</div>
										<div class="item">
											<div class="product_blog_img">
												<img src="images/rs1.png" alt="#" />
											</div>
											<div class="product_blog_cont">
												<h3>Homemade</h3>
												<h4>
													<span class="theme_color">$</span>10
												</h4>
											</div>
										</div>
										<div class="item">
											<div class="product_blog_img">
												<img src="images/rs2.png" alt="#" />
											</div>
											<div class="product_blog_cont">
												<h3>Noodles</h3>
												<h4>
													<span class="theme_color">$</span>20
												</h4>
											</div>
										</div>
										<div class="item">
											<div class="product_blog_img">
												<img src="images/rs3.png" alt="#" />
											</div>
											<div class="product_blog_cont">
												<h3>Egg</h3>
												<h4>
													<span class="theme_color">$</span>30
												</h4>
											</div>
										</div>
										<div class="item">
											<div class="product_blog_img">
												<img src="images/rs4.png" alt="#" />
											</div>
											<div class="product_blog_cont">
												<h3>Sushi Dizzy</h3>
												<h4>
													<span class="theme_color">$</span>40
												</h4>
											</div>
										</div>
										<div class="item">
											<div class="product_blog_img">
												<img src="images/rs5.png" alt="#" />
											</div>
											<div class="product_blog_cont">
												<h3>The Coffee Break</h3>
												<h4>
													<span class="theme_color">$</span>50
												</h4>
											</div>
										</div>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
			<div class="bg_bg">
				<!-- about -->
				<div class="about">
					<div class="container">
						<div class="row">
							<div class="col-md-12">
								<div class="title">
									<i><img src="images/title.png" alt="#" /></i>
									<h2>About Our Food & Restaurant</h2>
									<span>It is a long established fact that a reader will
										be distracted by the readable content of a <br> page when
										looking at its layout. The point of using Lorem
									</span>
								</div>
							</div>
						</div>
						
					</div>
				</div>
				<!-- end about -->

				<!-- blog -->

				<!-- end blog -->

				<!-- Our Client -->

				<!-- end Our Client -->
			</div>
			<!-- footer -->
			<%@include file="footer.jsp" %>
			<!-- end footer -->

		</div>
	</div>
	<div class="overlay"></div>
	<!-- Javascript files-->
	<script src="js/jquery.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.bundle.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/custom.js"></script>
	<script src="js/jquery.mCustomScrollbar.concat.min.js"></script>

	<script src="js/jquery-3.0.0.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#sidebar").mCustomScrollbar({
				theme : "minimal"
			});

			$('#dismiss, .overlay').on('click', function() {
				$('#sidebar').removeClass('active');
				$('.overlay').removeClass('active');
			});

			$('#sidebarCollapse').on('click', function() {
				$('#sidebar').addClass('active');
				$('.overlay').addClass('active');
				$('.collapse.in').toggleClass('in');
				$('a[aria-expanded=true]').attr('aria-expanded', 'false');
			});
		});
	</script>

	<style>
#owl-demo .item {
	margin: 3px;
}

#owl-demo .item img {
	display: block;
	width: 100%;
	height: auto;
}
</style>


	<script>
		$(document).ready(function() {
			var owl = $('.owl-carousel');
			owl.owlCarousel({
				margin : 10,
				nav : true,
				loop : true,
				responsive : {
					0 : {
						items : 1
					},
					600 : {
						items : 2
					},
					1000 : {
						items : 5
					}
				}
			})
		})
	</script>

</body>

</html>