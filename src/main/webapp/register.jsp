<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.mongodb.client.MongoCollection"  %>
<%@page import="com.mongodb.client.MongoDatabase"  %>
<%@page import="org.bson.Document"  %>
<%@page import="com.cse.MongoConnection"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="static/css/bootstrap.css">
  <link rel="stylesheet" href="static/css/theme.css">
</head>
<body>
<section>
  <div class="container">
  <div class="page-banner home-banner">
    <div class="row flex-wrap-reverse justify-content-center align-items-center h-100 mt-5">
      <div class="col-md-9 col-lg-6 col-xl-5">
        <img src="static/img/banner_image_1.svg"
          class="img-fluid" alt="Sample image">
      </div>
      <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
        <h1 class="navbar-brand mb-4 text-center ml-5">Course<span class="text-primary">Management</span></h1>
        <form action="register" method="post" enctype="multipart/form-data">
          <!-- Email input -->
          <div class="form-outline mb-4">
            <label class="form-label" for="form3Example3">Username</label>
            <input type="text" id="form3Example3" class="form-control form-control-sm"
              placeholder="Enter an unique username" name="username" />
          </div>

          <!-- Password input -->
          <div class="form-outline mb-3">
            <label class="form-label" for="form3Example4">Password</label> 
            <input type="password" id="form3Example4" class="form-control form-control-sm"
              placeholder="Enter password" name="password" />
          </div>
          <div>
          <label class="form-label" for="form3Example4">Register As</label>
          <select class="form-control" aria-label="Default select example" name="res-type">
		  <option value='1'>Student</option>
		  <option value="2">Teacher</option>
		</select>
          </div>
          
		${alert }

          <div class="text-center text-lg-start mt-4 pt-2">
            <button type="submit" class="btn btn-primary btn-lg"
              style="padding-left: 2.5rem; padding-right: 2.5rem;">Register</button>
            <p class="small fw-bold mt-2 pt-1 mb-0">Already have an account? <a href="login.jsp"
                class="link-danger">Login</a> or <a href="index.jsp"
                class="link-danger">Home</a></p>
          </div>

        </form>
      </div>
    </div>
    </div>
  </div>
</section>

</body>
</html>