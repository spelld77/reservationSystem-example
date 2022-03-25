<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約状況</title>
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/main.css" rel="stylesheet">
</head>
<body class="d-flex align-items-center justify-content-center">
<!-- <br><br> -->
<div class="">
	<div>
		<h2 class="heading">予約状況</h2>
		<button class="btn btn-secondary" onclick="javascript:history.back()">戻る</button>
		<div class="div_text">
			<c:out value="${dateFullStr}" />の予約状況は以下の通りです。<br>
			◎:余裕あり　〇:空きあり　△:残りわずか　ー:満席
		</div>
	</div>


	<div style="background-color: #eee;">
	
		<table class="table table-bordered border-secondary">
			<thead class="table-success">
				<tr>
					<th class="text-center">時間</th>
					<th class="text-center">予約状況</th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach var = "entry" items="${reserveStatus}">
				<tr>
					<td class="text-center">${entry.key}:00 - ${entry.key + 1}:00</td>					
					<td class="text-center">${entry.value}</td>

				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
</div>
</body>
</html>