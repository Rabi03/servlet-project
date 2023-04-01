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
Document data=(Document) request.getSession().getAttribute("teacher");
Document students=(Document) request.getSession().getAttribute("students");
%>
<div class="row justify-content-between mt-5" style="margin:0px auto;width:80%" >
<div>
<div class="row align-items-center px-4 justify-content-between" style="width:620px;height:160px;left:236.32px;box-sizing: border-box;top: 55.25px;background:#fcf3cf;border-radius: 14px;">
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
<div class="row align-items-center justify-content-between px-3 mb-" style="width: 620px;
height: 80px;
background: #fef9e7;
border-radius: 14px;cursor: pointer;">
<img src="https://img.icons8.com/external-flat-gradient-andi-nur-abdillah/64/null/external-course-online-learning-flat-gradient-flat-gradient-andi-nur-abdillah.png"/>

<p style="font-family: 'Baloo Bhai';
font-style: normal;
font-weight: 400;
font-size: 16px;margin-bottom:0px;"><a href='teacher?course_id=${c.get("_id")}' style="color:black">${c.get("title")}</a></p>
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
<div class="row align-items-center justify-content-center" style="width:200px;height:97px;background:  #fef9e7 ;border-radius: 14px;margin-right:50px">
<h2 style="font-family: 'Baloo Bhai';font-style: normal;
font-weight: 800;
font-size: 64px;
color: #000000;"><%=data.get("totalUserCourses")!=null?data.get("totalUserCourses"):0 %></h2>
<p style="font-style: normal;
font-weight: 400;
font-size: 16px;
margin-top:15px;
">Total Courses</p>
</div>
</div>

<%
if(request.getParameter("course_id")!=null){
%>
<div class="px-4 py-2 mt-5" style="width: 600px;
background:  #fef9e7;
border-radius: 14px;">
<div class="row align-items-start justify-content-between">
<div class="px-3">
<p style="font-family: 'Baloo Bhai';
font-style: normal;
font-weight: 400;
font-size: 24px;width:400px">Introduction to machine Learning</p>
<p>Students</p>

</div>
<a href="teacher?logout=true"><p style="font-family: 'Baloo Bhai';color:#6C55F9" >Logout</p></a>
</div>

<div class="p-2">
<ul class="list-group">
<c:forEach items='<%=students.get("value")%>' var="s">
<li class="list-group-item mt-2" style="border:0px;background:white;border-radius:10px">${s.get("fname")}</li>
</c:forEach>
</ul>
</div>
</div>

<% }else{ %>

<div class="row align-items-center px-4 justify-content-between mt-5" style="width: 500px;
height: 193px;
background:  #fef9e7;
border-radius: 14px;">
<div>
<p style="font-family: 'Baloo Bhai';
font-style: normal;
font-weight: 400;
font-size: 24px;">Course Management System.</p>
<p>Click one any course to see registered students.</p>
<div class="row px-3">
<a href="teacher?logout=true"><button type="button" class="btn btn-primary">Logout</button></a>
</div>
</div>

<%} %>


</div>
</div>
<div>
</div>
</body>
</html>