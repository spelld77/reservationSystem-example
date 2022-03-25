<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約システム</title>
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/main.css" rel="stylesheet">
</head>
<body class="d-flex align-items-center justify-content-center">
<div>
	<h2 class="heading">予約システム</h2>
	<form name="frm" action="#" method="post">
	 	<span class="label_day">日付：</span>
        <input type="date" id="inputDate" name="inputDate" value="2022-09-01" min="2022-01-01" max="2022-12-31">

        <br/><br/>
        <div>
        	<input type="button" class="btn btn-secondary btn-lg" onclick="return validate(this)" value="全予約状況" data-action="view"/>
        	<input type="button" class="btn btn-primary btn-lg" onclick="return validate(this)" value="予約する" data-action="add" data-method="do">
        	<input type="button" class="btn btn-success btn-lg" onclick="return validate(this)" value="My予約" data-action="myReservation" />
        	<input type="button" class="btn btn-danger btn-lg" onclick="return validate(this)" value="Logout" data-action="logout"/>
        </div>
     </form>
     
     
     <script src="js/moment.js"></script>
	<script src="js/dateValidation.js"></script>
</div>
</body>
</html>