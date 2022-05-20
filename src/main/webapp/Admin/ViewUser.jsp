<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.narola.fooddelivery.utility.URLConstantAdmin"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="MetaTag.jsp"%>

</head>
<body>
	<div class="container-scroller">
		<%@include file="Navigation.jsp"%>
		<div class="container-fluid page-body-wrapper">
			<%@include file="Sidebar.jsp"%>
			<div class="main-panel">
				<div class="content-wrapper">
					
					<br />
					
					
					<c:forEach var="i" begin="0" end="3">
						<c:if test="${sessionScope.user.getAdmin()==1||i==0||i==3}">
					<div class="col-lg-12 grid-margin stretch-card">
                <div class="card">
                  <div class="card-body">
                    <h4 class="card-title"><strong>Find <c:choose>
                          		<c:when test="${3-i==1}">SuperAdmin</c:when>
                          		<c:when test="${3-i==2}">Admin</c:when>
                          		<c:when test="${3-i==3}">RestaurantAdmin</c:when>
                          		<c:otherwise>EndUser</c:otherwise>
                          	</c:choose></strong></h4>
                    <table class="table table-striped">
                      <thead>
                      
                        <tr>
                          <th> <b>UserId</b> </th>
                          <th> <b>username</b> </th>
                          <th><b> email </b></th>
                          <th><b> Password </b></th>
                         <th><b>UserType </b></th>
                         <c:if test="${3-i==3}">
                         <th><b>Restaurant</b></th>
                         
                         
                         </c:if>
                        </tr>
                      </thead>
                      <tbody>
                      
                      <c:forEach items="${users}" var="user">
                      <c:if test="${user.getAdmin()==3-i}">
                      	<tr>
                          <td>
                            <c:out value="${user.getUserId()}"></c:out>
                          </td>
                          <td> ${user.getUsername()} </td>
                          <td>
                            ${user.getEmail()}
                          </td>
                          <td> 
                          	${user.getEncryptedPass()}
                          </td>
                          <td>
                          	<c:choose>
                          		<c:when test="${user.getAdmin()==1}">SuperAdmin</c:when>
                          		<c:when test="${user.getAdmin()==2}">Admin</c:when>
                          		<c:when test="${user.getAdmin()==3}">RestaurantAdmin</c:when>
                          		<c:otherwise>EndUser</c:otherwise>
                          	</c:choose>
                          
                          
                         </td>
                         <c:if test="${3-i==3}">
                         <td>
                         <c:out value="${user.getRestaurant().getRestName()==null?Assign:user.getRestaurant().getRestName()}"></c:out>
                         
                         
                         
                         </td>
                         
                         
                         </c:if>
                          <td><a href="<%=request.getContextPath()+URLConstantOfServlet.UPDATEUSER+"?UserId="%>${user.getUserId()}"><i class="icon-pencil"></i></a></td>
                          <td><a href="<%=request.getContextPath()+URLConstantOfServlet.DELETEUSER+"?UserId="%>${user.getUserId()}"><i class="icon-trash"></i></a></td>

                        </tr>
                      </c:if>
                      </c:forEach>
                      
                        
                       
                        
                      </tbody>
                    </table>
                   </div>
                  </div>
					
					
					
					
					
					<br>
					<br>
				</div>
				</c:if>
				</c:forEach>
				
			</div>

		</div>
	</div>





	<%@include file="Scripts.jsp"%>

	
	
	
</body>
</html>