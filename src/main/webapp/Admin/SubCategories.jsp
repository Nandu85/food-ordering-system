<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="MetaTag.jsp"%>
<title>SubCategories</title>
</head>
<body>
	<div class="container-scroller">
		<%@include file="Navigation.jsp"%>
		<div class="container-fluid page-body-wrapper">
			<%@include file="Sidebar.jsp"%>
			<div class="main-panel">
				<div class="content-wrapper">

					<form method="post" action="<%=request.getContextPath() + URLConstantOfServlet.SUBCATEGORIES%>" enctype="multipart/form-data">

						<div class="row">
							<div class="col-12 col-md-3">
								<input type="text" class="form-control" name="subcategoryName"
									placeholder="Name of Category" required="required">
							</div>
							<div class="col-12 col-md-2">
								<select class="form-control" name="catId">

									<c:forEach items="${categories}" var="catg">
										<option value="${catg.getCategoryId()}">${catg.getCategoryName()}</option>
									</c:forEach>
								</select>
							</div>
							 <div class="col-12 col-md-4">
								<input type="file" class="btn btn-inverse-primary btn-rounded"
									name="CategoryPhoto" value="Upload Image" required="required">
							</div> 
							<div class="col-12 col-sm-2">
								<input type="submit" class="btn btn-inverse-info btn-rounded"
									name="Submit" value="Add">
							</div>
						</div>
					</form>
<br><br>

				<div class="card">
						<div class="card-body">
							<h4 class="card-title">
								<strong>Find SubCategories here </strong>
							</h4>
							<table class="table table-striped">
								<thead>
									<tr>
										<th><b>CategoryId</b></th>
										<th><b>Category Name</b></th>
										<th><b> Super Category </b></th>
										<th><b> Image </b></th>
									</tr>
								</thead>
								<tbody>

									<c:forEach items="${subCategories}" var="cat">
										<tr>
											<td><c:out value="${cat.getCategoryId()}"></c:out></td>
											<td>${cat.getCategoryName()}</td>

											<td>${cat.getCategory().getCategoryName()}</td>
											
											<td><img class="img-thumbnail" src="data:image/png;base64,${cat.getImageAsBase64()}" alt="Not added">	 </td>

											<td><button type="button"
													class="btn btn-primary btn-rounded btn-inverse"
													data-toggle="modal"
													data-target=".bd-example-modal-md${cat.getCategoryId()}">
													<i class="icon-pencil"></i>
												</button> &nbsp;&nbsp;&nbsp; <a
												class="btn btn-secondary btn-rounded btn-inverse"
												href="<%=request.getContextPath()+URLConstantOfServlet.DELETESUBCATEGORY%>?Id=${cat.getCategoryId()}"><i
													class="icon-trash"></i></a>
													
													

												<div
													class="modal fade bd-example-modal-md${cat.getCategoryId()}"
													tabindex="-1" role="dialog"
													aria-labelledby="mySmallModalLabel" aria-hidden="true">
													<div class="modal-dialog modal-md">
														<div class="modal-content">
															<form method="post"
																action="<%=request.getContextPath()+URLConstantOfServlet.UPDATESUBCATEGORIES%>"
																style="padding: 30px;" enctype="multipart/form-data">


																<input type="hidden" name="categoryId"
																	value="${cat.getCategoryId()}"> <input
																	type="text" class="form-control" name="categoryName"
																	placeholder="Name of Category"
																	value="${cat.getCategoryName()}" required="required">
																<br>
																<br> <select class="form-control" name="catId">
																		<option value="${cat.getCategory().getCategoryId()}">${cat.getCategory().getCategoryName()}</option>
																		<c:forEach items="${categories}" var="catg">
																			<option value="${catg.getCategoryId()}">${catg.getCategoryName()}</option>
																		</c:forEach>
																	</select> <br>
																	<input type="file" class="btn btn-inverse-primary btn-rounded"
																	name="CategoryPhoto" value="Upload Image"><br><br>
																	
																<input type="submit"
																	class="btn btn-inverse-info btn-rounded" name="Submit"
																	value="Edit">

															</form>


														</div>
													</div>
												</div></td>

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



	<%@include file="Scripts.jsp"%>
</body>
</html>