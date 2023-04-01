<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

  <link rel="stylesheet" href="static/css/bootstrap.css">
  <link rel="stylesheet" href="static/css/theme.css">
</head>
<body>

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
              <a class="nav-link" href="index.html">Home</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="about.html">About</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="contact.html">Contact</a>
            </li>
          </ul>
        </div>

      </div>
    </nav>

    <div class="container">
      <div class="page-banner home-banner">
        <div class="row align-items-center flex-wrap-reverse h-100">
          <div class="col-md-6 py-5 wow fadeInLeft">
            <h1 class="mb-4">An online environment for course interactions!</h1>
            <p class="text-lg text-grey mb-5">It's a virtual learning environments that are categorized based on pedagogical tools such as content creation, communication, administration and assessment</p>
            <div class="row align-items-center">
            <br/>
            <a href="login" class="btn btn-primary text-center mr-3">Signin</a>
            <a href="register" class="btn btn-primary text-center">Signup</a>
            </div>
            
          </div>
          <div class="col-md-6 py-5 wow zoomIn">
            <div class="img-fluid text-center">
              <img src="static/img/banner_image_1.svg" alt="">
            </div>
          </div>
        </div>
      </div>
    </div>
  </header>

</body>
</html>