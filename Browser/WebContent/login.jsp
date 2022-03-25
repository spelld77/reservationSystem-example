<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/login.css" rel="stylesheet">
</head>
<body class="">
<div class="login d-flex align-items-center justify-content-center h-100 w-100">
  <div class="heading">
    <h2>Sign in</h2>
    <form action="login" method="post">

      <div class="input-group input-group-lg">
        <span class="input-group-addon"><i class="fa fa-user"></i></span>
        <input type="text" name="id" class="form-control" placeholder="ID">
          </div>

        <div class="input-group input-group-lg">
          <span class="input-group-addon"><i class="fa fa-lock"></i></span>
          <input type="password" name="pw" class="form-control" placeholder="Password">
        </div>

        <button type="submit" class="btn-primary">Login</button>
       </form>
 		</div>
 </div>
</body>
</html>