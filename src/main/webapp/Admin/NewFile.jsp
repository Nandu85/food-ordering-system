<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@ include file="MetaTag.jsp" %>
</head>
<body style="background-color: aqua;">
<div class="modal" style="position:absolute;top: 40%;left: 30%;background-color: white;">
<div class="modal-body">
<form method="post" action="#">
					
					<div class="row">
						<div class="col-12 col-sm-3">
							<input type="text" class="form-control" name="categoryName" placeholder="Name of Category" required="required">	
						</div>
						<div class="col-12 col-sm-3">
							<select class="form-control" name="popularity">
								<option value="0" hidden>Select Popularity</option>
								<option value="1">1.Very High</option>
								<option value="2">2.High</option>
								<option value="3">3.Medium</option>
								<option value="4">4.Low</option>
								<option value="5">5.Very Low</option>
							</select>					
						</div>
						<div class="col-12 col-sm-3">
							<input type="submit" class="btn btn-inverse-info btn-rounded" name="Submit" value="Add">	
						</div>
					</div>
					</form>
					</div>
</div>
</body>
</html>