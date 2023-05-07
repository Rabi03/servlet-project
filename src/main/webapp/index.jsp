<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page  import="org.bson.Document"%>
<%@ page  import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Course Management System</title>

  <link rel="stylesheet" href="static/css/bootstrap.css">
  <link rel="stylesheet" href="static/css/theme.css">
</head>
<body>

<%
List<Document> courses=(List<Document>) request.getSession().getAttribute("courses");
List<Document> teachers=(List<Document>) request.getSession().getAttribute("teachers");
%>

  <header>
    <nav class="navbar navbar-expand-lg navbar-light bg-white sticky" data-offset="500">
      <div class="container">
        <a href="#" class="navbar-brand">Course<span class="text-primary">Management.</span></a>

        <button class="navbar-toggler" data-toggle="collapse" data-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="navbar-collapse collapse" id="navbarContent">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <a class="nav-link" href="/EduTech">Home</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#teachers">Teachers</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#courses">Courses</a>
            </li>
          </ul>
        </div>

      </div>
    </nav>

    <div class="container">
      <div class="page-banner home-banner">
        <div class="row align-items-center flex-wrap-reverse h-100">
        <div class="col-md-6 py-5 wow zoomIn">
            <div class="img-fluid text-center">
              <img src="static/img/banner_image_1.svg" alt="">
            </div>
          </div>
          <div class="col-md-6 py-5 wow fadeInLeft">
            <h1 class="mb-4">An online environment for course interactions!</h1>
            <p class="text-lg text-grey mb-5">It's a virtual learning environments that are categorized based on pedagogical tools such as content creation, communication, administration and assessment</p>
            <div class="row align-items-center">
            <br/>
            <a href="login" class="btn btn-primary text-center mr-3">Signin</a>
            <a href="register" class="btn btn-primary text-center">Signup</a>
            </div>
            
          </div>
          
        </div>
      </div>
    </div>
    
    
  </header>
  

    
    <div id="teachers" class="container">
      <div class="home-banner">
        <div class="row align-items-center flex-wrap-reverse p-5">
          <div class="col-md-9 wow fadeInLeft">
            <h1 class="mb-4">Our Teachers!</h1>
            <p class="text-lg text-grey mb-5">Our honorable teachers from all departments!</p>
            <div class="row align-items-center">
            
            <c:forEach items='<%=teachers%>' var="c">
            <div class="col-4" style="background-color:#645f881a;padding:10px;margin:5px;border-radius:10px">
			  <h5 style="font-size:15px">${c.get("fname")}</h5>
			        <p style="font-size:12px">${c.get("dept")}</p>
			        <p style="font-size:12px"><small class="text-muted">${c.get("username")}</small></p>
			</div>
            
            </c:forEach>
            
            
            </div>
            
          </div>
          <div class="col-md-3 py-5 wow zoomIn">
            <div class="img-fluid text-center">
              <img src="static/img/banner_image_1.svg" alt="">
            </div>
          </div>
        </div>
      </div>
    </div>
    
    <div id="courses" class="container">
      <div class=" home-banner">
        <div class="row align-items-center flex-wrap-reverse p-5">
          <div class="col-md-9 wow fadeInLeft">
            <h1 class="mb-4">Our Courses!</h1>
            <p class="text-lg text-grey mb-5">Our available courses..</p>
            <div class="row align-items-center">
            
            <c:forEach items='<%=courses%>' var="c">
            <div class="col-4" style="background-color:#645f881a;padding:10px;margin:5px;border-radius:10px">
			  <h5 style="font-size:15px">${c.get("title")}</h5>
			        <p style="font-size:12px">${c.get("credit")} credits</p>
			        <p style="font-size:12px"><small class="text-muted">${c.get("code")} , ${c.get("semester")}</small></p>
			</div>
            
            </c:forEach>
            
            
            </div>
            
          </div>
          <div class="col-md-3 py-5 wow zoomIn">
            <div class="img-fluid text-center">
              <img src="static/img/banner_image_1.svg" alt="">
            </div>
          </div>
        </div>
      </div>
    </div>

</body>
</html>