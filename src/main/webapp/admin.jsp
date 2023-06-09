<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="org.bson.Document" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
<link rel="stylesheet" href="static/css/bootstrap.css">
  <link rel="stylesheet" href="static/css/theme.css">
  <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css2?family=Baloo Bhai:wght@400&display=swap"
    />
</head>
<body>
<div class="row justify-content-between w-75 mt-5" style="margin:0px auto;" >
<div>
<div class="row align-items-center px-4 justify-content-between" style="width:620px;height:160px;left:236.32px;box-sizing: border-box;top: 55.25px;background: #d6eaf8;border-radius: 14px;">
<div>
<h2 style="font-family: 'Baloo Bhai'">Admin Page</h2>
<p>Course Management System</p>
</div>
<img src="static/img/banner_image_1.svg" class="img-fluid" alt="Sample image" width="180" height="180">
</div>
<div class="mt-5">
<p style="font-family: 'Baloo Bhai';
font-style: normal;
font-weight: 400;
font-size: 24px;
line-height: 38px;">Courses</p>

<%
Document doc=(Document) request.getSession().getAttribute("admin");
String edit=(String) doc.get("edit");
String editTitle=(String) doc.get("edit-title");
String editCredit=(String) doc.get("edit-credit");
String editTeacher=(String) doc.get("edit-teacher");
String editId=(String) doc.get("editId");
%>


<c:forEach items='<%=doc.get("courses")%>' var="c">
<!--Courses-->
<div class="row align-items-center justify-content-between px-3 mb-3" style="width: 620px;
height: 80px;
background:  #eaf2f8;
border-radius: 14px;">
<img src="https://img.icons8.com/external-flat-gradient-andi-nur-abdillah/64/null/external-course-online-learning-flat-gradient-flat-gradient-andi-nur-abdillah.png"/>
<div class="mt-2">
<p style="font-family: 'Baloo Bhai';
font-style: normal;
font-weight: 400;
font-size: 16px;margin-bottom:0px;">${c.get("title")}</p>
<p>${c.get("teacher")}</p>
</div>
<p style="font-family: 'Baloo Bhai';
font-style: normal;
font-weight: 400;
font-size: 13px;margin-bottom:0px;">${c.get("code")},${c.get("semester")}</p>
<a href='admin?edit=true&edit-title=${c.get("title")}&edit-code=${c.get("code")}&editId=${c.get("_id")}' class="btn btn-light p-1" style="background:  #eaf2f8;"><img src="https://img.icons8.com/plumpy/24/null/edit-row.png"/></a>
<a href='admin?remove-course-id=${c.get("_id")}' class="btn btn-light p-1" style="background:  #eaf2f8;"><img src="https://img.icons8.com/plumpy/24/null/delete--v1.png"/></a>
</div>

<!--End-->
</c:forEach>









</div>
</div>







<div class="align-items-center justify-content-center">
<div class="row align-items-center justify-content-between">
<div class="row align-items-center justify-content-center" style="width:200px;height:97px;background:  #eaf2f8;border-radius: 14px;margin-right:50px">
<h2 style="font-family: 'Baloo Bhai';font-style: normal;
font-weight: 800;
font-size: 64px;
color: #000000;"><%=doc.get("totalTeacher") %></h2>
<p style="font-style: normal;
font-weight: 400;
font-size: 16px;
margin-top:15px;
">Total Teachers
</div>
<div class="row align-items-center justify-content-center" style="width:200px;height:97px;background:  #eaf2f8;border-radius: 14px;margin-right:50px">
<h2 style="font-family: 'Baloo Bhai';font-style: normal;
font-weight: 800;
font-size: 64px;
color: #000000;"><%=doc.get("totalCourses") %></h2>
<p style="font-style: normal;
font-weight: 400;
font-size: 16px;
margin-top:15px;
">Total Courses</p>
</div>
</div>

<div class="row align-items-center px-4 justify-content-between mt-5" style="width: 500px;
height: 193px;
background:  #eaf2f8;
border-radius: 14px;">
<div>
<p style="font-family: 'Baloo Bhai';
font-style: normal;
font-weight: 400;
font-size: 24px;">Add a New Course.</p>
<p>Add course and assign a teacher.</p>

<%  
if (edit == "1") 
{ 
%>

                
<div class="modal" id="editModal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Edit Course</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form action="admin?edit=true&editId=<%=doc.get("editId") %>" method="post" enctype="multipart/form-data">
      	<div class="form-outline mb-4">
            <label class="form-label" for="form3Example3">Course Title</label>
            <input type="text" id="form3Example3" class="form-control form-control-sm"
              placeholder="Enter an course name" value='<%=doc.get("edit-title") %>' name="title" />
          </div>
          <div class="form-outline mb-4">
            <label class="form-label" for="form3Example3">Course Code</label>
            <input type="text" id="form3Example3" class="form-control form-control-sm"
              placeholder="Enter an course name" value='<%=doc.get("edit-code") %>' name="code" />
          </div>
          <div>
          <label class="form-label" for="form3Example4">Select Credits</label>
          <select class="form-control" aria-label="Default select example" name="credit">
          <option value='0'>Select Credit</option>
          <option value='1'>1</option>
          <option value='1.5' >1.5</option>
          <option value='2'>2</option>
          <option value='3'>3</option>
		</select>
          </div>
          <div>
          <label class="form-label" for="form3Example4">Select Semester</label>
          <select class="form-control" aria-label="Default select example" name="semester">
          <option value='1st semester'>1st semester</option>
          <option value='2nd semester'>2nd semester</option>
          <option value='3rd semester'>3rd semester</option>
          <option value='4th semester'>4th semester</option>
          <option value='5th semester'>5th semester</option>
          <option value='6th semester'>6th semester</option>
          <option value='7th semester'>7th semester</option>
          <option value='8th semester'>8th semester</option>
          
		</select>
          </div>
         <div>
          <label class="form-label" for="form3Example4">Select teacher</label>
          <select class="form-control" aria-label="Default select example" name="teacher" value='<%=doc.get("edit-teacher") %>'>
          <option value="0">Select a teacher</option>
          <c:forEach items='<%=doc.get("teachers")%>' var="d">
          <option value='${d.get("username")}'>${d.get("fname")}</option>
          </c:forEach>
		</select>
          </div>
      <div class="modal-footer">
        <button type="submit" class="btn btn-primary">Edit</button>
      </div>
      </form>
      </div>
    </div>
  </div>
</div>
<% } 
%>


<div class="row px-3">
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" style="margin-right:10px">New Course</button>
<a href="admin?logout=true"><button type="button" class="btn btn-primary">Logout</button></a>
</div>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="false">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel" style="font-family: 'Baloo Bhai';font-weight: 400;">Add New Course</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <form action="admin" method="post" enctype="multipart/form-data">
      	<div class="form-outline mb-4">
            <label class="form-label" for="form3Example3">Course Title</label>
            <input type="text" id="form3Example3" class="form-control form-control-sm"
              placeholder="Enter an course name" name="title" />
          </div>
          <div class="form-outline mb-4">
            <label class="form-label" for="form3Example3">Course Code</label>
            <input type="text" id="form3Example3" class="form-control form-control-sm"
              placeholder="Enter an course name" name="code" />
          </div>
          <div>
          <label class="form-label" for="form3Example4">Select Credits</label>
          <select class="form-control" aria-label="Default select example" name="credit">
          <option value='1'>1</option>
          <option value='1.5'>1.5</option>
          <option value='2'>2</option>
          <option value='3'>3</option>
          
		</select>
          </div>
          <div>
          <label class="form-label" for="form3Example4">Select Semester</label>
          <select class="form-control" aria-label="Default select example" name="semester">
          <option value='1st semester'>1st semester</option>
          <option value='2nd semester'>2nd semester</option>
          <option value='3rd semester'>3rd semester</option>
          <option value='4th semester'>4th semester</option>
          <option value='5th semester'>5th semester</option>
          <option value='6th semester'>6th semester</option>
          <option value='7th semester'>7th semester</option>
          <option value='8th semester'>8th semester</option>
          
		</select>
          </div>
         <div>
          <label class="form-label" for="form3Example4">Select teacher</label>
          <select class="form-control" aria-label="Default select example" name="teacher">
          <option value="0">Select a teacher</option>
          <c:forEach items='<%=doc.get("teachers")%>' var="d">
          <option value='${d.get("username")}'>${d.get("fname")}</option>
          </c:forEach>
		</select>
          </div>
      <div class="modal-footer">
        <button type="submit" class="btn btn-primary">Save</button>
      </div>
      </form>
      </div>
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
<script>
$('#editModal').modal('show')
</script>
</body>
</html>