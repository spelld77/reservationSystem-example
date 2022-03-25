<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約画面</title>
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/main.css" rel="stylesheet">
</head>
<body>
<div class="container w-50 mt-4">
	
	<div>
		<h2 class="heading">予約</h2>
		<button class="btn btn-secondary" onclick="javascript:history.back()">戻る</button>
	</div>
	<div class="" style="background-color: #eee;">
		<table class="table table-bordered">
			<thead>
				<tr class="table-success">
					<th class="text-center">予約日</th>
					<th class="text-center">予約時間</th>
					<th class="text-center">座席番号</th>
					<th class="text-center">予約</th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach var="entry" items="${vacantTimeAndSeat}">
					
					<c:forEach var="seatNo" items="${entry.value}">
						<tr>
							<td class="text-center"><c:out value="${dateFullStr}" /></td>
							<td class="text-center">${entry.key}:00 - ${entry.key + 1}:00</td>
							<td class="text-center"><c:out value="${seatNo}" /></td>
							<td class="text-center">
								<form action="add" method="post">
									<input type="submit" class="btn btn-primary" value="予約" data-time="${entry.key}" data-seatNo = "${seatNo}"/>
									<input type="hidden" value="${reserveDate}" name="reserveDate" />
									<input type="hidden" value="${entry.key}" name="reserveTime" />
									<input type="hidden" value="${seatNo}" name="seatNo" />
								</form>
							</td>
						</tr>
					</c:forEach>
					
				</c:forEach>
				<tr>
				
				</tr>
			</tbody>
		</table>
	</div>
</div>
</body>
</html>