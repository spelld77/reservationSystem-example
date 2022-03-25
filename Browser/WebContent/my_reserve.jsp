<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My予約</title>
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/main.css" rel="stylesheet">
<script src="js/my_reserve.js" type="text/javascript"></script>
</head>
<body>	
<div class = "container w-50 mt-4">
	<div>
		<h2 class="heading">My予約</h2>
		<button class="btn btn-secondary" onclick="location.href = 'main'">戻る</button>
	</div>	
	<div style="background-color: #eee;">
		<table class="table table-bordered">
			<thead>
				<tr class="table-success">
					<th class="text-center">予約日</th>
					<th class="text-center">予約時間</th>
					<th class="text-center">座席番号</th>
					<th class="text-center">取消</th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach var="reservation" items="${reservations}">				
					<tr>
						<td class="text-center"><c:out value="${reservation.reserveDate}" />(<c:out value="${reservation.dayOfWeek}" />)</td>
						<td class="text-center">${reservation.startTime}:00 - ${reservation.startTime + 1}:00</td>
						<td class="text-center"><c:out value="${reservation.seatId}" /></td>						
						<td class="text-center">
							<form action="delete" method="post">
								<button type="button" onclick = "deleteCheck(this)" class="btn btn-danger">取消</button>
								<input type="hidden" name="dateToDelete" value="${reservation.reserveDate}" />
								<input type="hidden" name="timeToDelete" value="${reservation.startTime}" />
								<input type="hidden" name="seatToDelete" value="${reservation.seatId}" />
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</body>
</html>