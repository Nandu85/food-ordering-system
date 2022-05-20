<%@page import="com.narola.fooddelivery.utility.URLConstantOfServlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.narola.fooddelivery.user.User"%>
<%@page import="com.narola.fooddelivery.utility.URLConstantAdmin"%>
<%@page import="com.narola.fooddelivery.utility.URLConstantOfServlet"%>


<nav class="sidebar sidebar-offcanvas" id="sidebar">
          <ul class="nav">
            <li class="nav-item nav-profile">
              <a href="#" class="nav-link">
                <div class="profile-image">
                  <img class="img-xs rounded-circle" src="images/faces/face1.png" alt="profile image">
                  <!-- <div class="dot-indicator bg-success"></div> -->
                </div>
                <div class="text-wrapper">
                  <p class="profile-name"><%=user.getUsername()%></p><!-- Not Declared Because it already has variable user -->
                  <p class="designation">Administrator</p>
                </div>
                <div class="icon-container">
                  <i class="icon-bubbles"></i>
                  <div class="dot-indicator bg-danger"></div>
                </div>
              </a>
            </li>
            
            <li class="nav-item nav-category">
              <span class="nav-link">Dashboard</span>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()+URLConstantAdmin.DASHBOARD%>">
                <span class="menu-title">Dashboard</span>
                <i class="icon-screen-desktop menu-icon"></i>
              </a>
            </li>
            
             <li class="nav-item nav-category"><span class="nav-link">Order</span></li>
            
            <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()+URLConstantOfServlet.NEW_ORDER%>">
                <span class="menu-title">order</span>
                <i class="icon-globe menu-icon"></i>
              </a>
            </li>
            
            
            <%
                                    if(user.getAdmin()==1 || user.getAdmin()==2){
                                    %>
            
            
            
            <li class="nav-item nav-category"><span class="nav-link">Restaurant</span></li>
            
            <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()+URLConstantOfServlet.ADDRESTAURANT%>">
                <span class="menu-title">Add</span>
                <i class="icon-globe menu-icon"></i>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()+URLConstantOfServlet.SEARCHRESTAURANT%>">
                <span class="menu-title">View</span>
                <i class="icon-book-open menu-icon"></i>
              </a>
            </li>
            
            <li class="nav-item nav-category"><span class="nav-link">Users</span></li>
            
            <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()+URLConstantOfServlet.ADDUSER%>">
                <span class="menu-title">Add</span>
                <i class="icon-globe menu-icon"></i>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()+URLConstantOfServlet.VIEWUSER%>">
                <span class="menu-title">View</span>
                <i class="icon-book-open menu-icon"></i>
              </a>
            </li>
            
            <%
                        }
                                    /*else{  */
                        %>
           <%--  <li class="nav-item nav-category">
              <span class="nav-link">Dashboard</span>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()+URLConstantAdmin.DASHBOARD%>">
                <span class="menu-title">Dashboard</span>
                <i class="icon-screen-desktop menu-icon"></i>
              </a>
            </li>
            
             <li class="nav-item nav-category"><span class="nav-link">Order</span></li>
            
            <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()+URLConstantOfServlet.NEW_ORDER%>">
                <span class="menu-title">order</span>
                <i class="icon-globe menu-icon"></i>
              </a>
            </li>
            
 			<%
 			}
 			%>            --%>
            <li class="nav-item nav-category"><span class="nav-link">Dishes</span></li>
            
            <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()+URLConstantOfServlet.ADDDISH%>">
                <span class="menu-title">Add</span>
                <i class="icon-globe menu-icon"></i>
              </a>
            </li>
            
            <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()+URLConstantOfServlet.SEARCHDISH_WITH_NO_FILTER%>">
                <span class="menu-title">View</span>
                <i class="icon-book-open menu-icon"></i>
              </a>
            </li>
            <%
            if(user.getAdmin()==1 || user.getAdmin()==2){
            %>
            <li class="nav-item nav-category"><span class="nav-link">Categories</span></li>
            
            <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()+URLConstantOfServlet.ADDCATEGORY%>">
                <span class="menu-title">Super Categories</span>
                <i class="icon-doc menu-icon"></i>
              </a>
            </li>
            
            <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()+URLConstantOfServlet.SUBCATEGORIES%>">
                <span class="menu-title">Sub Categories</span>
                <i class="icon-doc menu-icon"></i>
              </a>
            </li>
            
            <%} %>
            <li class="nav-item nav-category"><span class="nav-link">Sample Pages</span></li>
            <li class="nav-item">
              <a class="nav-link" data-toggle="collapse" href="#auth" aria-expanded="false" aria-controls="auth">
                <span class="menu-title">General Pages</span>
                <i class="icon-doc menu-icon"></i>
              </a>
              <div class="collapse" id="auth">
                <ul class="nav flex-column sub-menu">
                  <li class="nav-item"> <a class="nav-link" href="pages/samples/login.html"> Login </a></li>
                  <li class="nav-item"> <a class="nav-link" href="pages/samples/register.html"> Register </a></li>
                  <li class="nav-item"> <a class="nav-link" href="pages/samples/error-404.html"> 404 </a></li>
                  <li class="nav-item"> <a class="nav-link" href="pages/samples/error-500.html"> 500 </a></li>
                  <li class="nav-item"> <a class="nav-link" href="pages/samples/blank-page.html"> Blank Page </a></li>
                </ul>
              </div>
            </li>
            
          </ul>
        </nav>