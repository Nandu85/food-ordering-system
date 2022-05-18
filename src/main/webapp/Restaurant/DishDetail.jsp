<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.narola.fooddelivery.dishes.dao.DishDAOMYSQL"%>
<%@page import="com.narola.fooddelivery.dishes.model.Dish"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DishDetail</title>
<link rel="stylesheet" href="css/styless.css" type="text/css" />

</head>
<body>

<%
Dish d=DishDAOMYSQL.DishFromId(Integer.parseInt(request.getParameter("DishId")));
%>
	<div class="card">	
		Dish Name: <label><%=d.getDishName()%></label>
		<br>
		Category:<label><%=DishDAOMYSQL.CategoryFromId(d.getCategoryId())%></label>
<br>
		Price:<label><%=d.getPrice() %></label>
<br>
		Image:<img src="data:image/png;base64,<%=d.getPhotoAsBase64()%>" alt="No image found" width="200px" name="DishPic1">
<br>
		Ingrediant:<label><%=d.getIngrident() %></label>
<br>
		DishType:<label><%=(d.getDishtype()==0 ? "Veg" : "NonVeg")%></label>
		<br>
	</div>
</body>
</html>