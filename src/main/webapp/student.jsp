<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page  import="org.bson.Document"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="static/css/bootstrap.css">
  <link rel="stylesheet" href="static/css/theme.css">
  <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css2?family=Baloo Bhai:wght@400&display=swap"
    />
</head>
<body>
<%
Document doc=(Document) request.getSession().getAttribute("user");
Document data=(Document) request.getSession().getAttribute("student");
%>
<div class="row justify-content-between w-75 mt-5" style="margin:0px auto;" >
<div>
<div class="row align-items-center px-4 justify-content-between" style="width:620px;height:160px;left:236.32px;box-sizing: border-box;top: 55.25px;background:#d1f2eb;border-radius: 14px;">
<div>
<h2 style="font-family: 'Baloo Bhai'">Hello <%= doc.get("fname") %></h2>

<p><%= doc.get("dept") %></p>

</div>
<img src="static/img/banner_image_1.svg" class="img-fluid" alt="Sample image" width="180" height="180">
</div>
<div class="mt-5">
<p style="font-family: 'Baloo Bhai';
font-style: normal;
font-weight: 400;
font-size: 24px;
line-height: 38px;">Courses</p>

<c:forEach items='<%=data.get("usercourses")%>' var="c">
<!--Courses-->
<div class="row align-items-center justify-content-between px-3 mb-3" style="width: 620px;
height: 80px;
background:  #eafaf1;
border-radius: 14px;">
<img src="https://img.icons8.com/external-flat-gradient-andi-nur-abdillah/64/null/external-course-online-learning-flat-gradient-flat-gradient-andi-nur-abdillah.png"/>
<div class="mt-2">
<p style="font-family: 'Baloo Bhai';
font-style: normal;
font-weight: 400;
font-size: 16px;margin-bottom:0px;">${c.get("title")}</p>
<p>by ${c.get("teacher")}</p>
</div>
<p style="font-family: 'Baloo Bhai';
font-style: normal;
font-weight: 400;
font-size: 13px;">${c.get("credit")} Credits</p>
<div class="align-items-center justify-content-center mt-2">
<p style="font-family: 'Baloo Bhai';
font-style: normal;
font-weight: 400;
font-size: 16px;margin-bottom:0px;">11 May</p>
<p style="font-family: 'Baloo Bhai';
font-style: normal;
font-weight: 400;
font-size: 16px;">2023</p>
</div>
</div>

<!--End-->
</c:forEach>







</div>
</div>







<div class="align-items-center justify-content-center">
<div class="row align-items-center justify-content-center">
<div class="row align-items-center justify-content-center" style="width:200px;height:97px;background:  #eafaf1;border-radius: 14px;margin-right:50px">
<h2 style="font-family: 'Baloo Bhai';font-style: normal;
font-weight: 800;
font-size: 64px;
color: #000000"><%=data.get("totalUserCourses")!=null?data.get("totalUserCourses"):0 %></h2>
<p style="font-style: normal;
font-weight: 400;
font-size: 16px;
margin-top:15px;
">Total Courses</p>
</div>
</div>

<div class="row align-items-center px-4 justify-content-between mt-5" style="width: 500px;
height: 193px;
background:  #eafaf1;
border-radius: 14px;">
<div>
<p style="font-family: 'Baloo Bhai';
font-style: normal;
font-weight: 400;
font-size: 24px;">Register for New Course.</p>
<p>Find your courses and teachers.</p>
<div class="row px-3">
<button type="button" class="btn btn-primary mr-2" data-toggle="modal" data-target="#exampleModal">Register</button>
<a href="student?logout=true"><button type="button" class="btn btn-primary">Logout</button></a>
</div>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel" style="font-family: 'Baloo Bhai';font-weight: 400;">Course Registration</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form action="student" method="post" enctype="multipart/form-data">
      <div class="modal-body">
         <div>
          <label class="form-label" for="form3Example4">Select course</label>
          <select class="form-control" aria-label="Default select example" name="course">
          <option value="0">Select a course</option>
          <c:forEach items='<%=data.get("courses")%>' var="c">
			<option value='${c.get("_id")}'>${c.get("title")} by ${c.get("teacher")}</option>
			</c:forEach>
		</select>
          </div>
      </div>
      <div class="modal-footer">
        <button type="submit" class="btn btn-primary">Save</button>
      </div>
      </form>
    </div>
  </div>
</div>
</div>
<img src="static/img/banner_image_2.svg" class="img-fluid" alt="Sample image" width="100" height="100">
</div>

</div>
</div>
<div>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>