<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>도서정보</title>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-contextmenu/2.9.0/jquery.contextMenu.min.css"
	rel="stylesheet">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-contextmenu/2.9.0/jquery.contextMenu.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-contextmenu/2.9.0/jquery.ui.position.min.js"></script>

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
	crossorigin="anonymous">
<style>
* {
	box-sizing: border-box;
	margin: 0px;
	padding: 0px;
}

body {
	width: 100%;
}

header {
	background-color: #41D3BD;
	margin: 0;
	padding: 1rem;
	color: white;
}

table {
	width:90%;
	margin: 20px auto;
	border-bottom: 1px solid #ccc;
	border-collapse: collapse;
	border-spacing: 0;
	table-layout: fixed;
	white-space: nowrap;
	font-size: 14px;
	text-align: center;
}

table th {
	font-weight: bold;
	text-align: center;
	background-color: #c4c2c2;
	color: #3b3b3b;
	padding: 10px 11px 11px 11px;
	border-bottom: 1px solid #ccc;
}

table td {
	padding: 12px 10px;
	border-bottom: 1px solid #ccc;
	height: 23px;
	text-overflow: ellipsis;
	overflow: hidden;
}

table a {
	text-decoration: none;
}
</style>
<script>
$(function(){
	$("#btn_insert").click(function(){
		
		document.location.href = "${rootPath}/book/insert"
		
	})
	
	$(".list-body").click(function(){
		let id = $(this).attr("data-id")
		
		alert(id)
		document.location.href = "${rootPath}/book/view?id=" + id
	})
})
</script>
</head>
<body>
	<header>
		<h3>독서록</h3>
	</header>

	<section>
		<button id="btn_insert" class="bz_button">도서등록</button>
	</section>

	<section>
		<div>
			<table border="1">
				<tr>
					<th>도서코드</th>
					<th>도서명</th>
					<th>저자</th>
					<th>출판사</th>
					<th>구입일자</th>
					<th>구입가격제목</th>
				</tr>
				<c:choose>
					<c:when test="${empty bookList}">
						<tr>
							<td colspan="6">도서자료가 없음</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${bookList}" var="book">
							<tr class="list-body"
								data-id="${book.b_code}">
								<td>${book.b_code}</td>
								<td>${book.b_name}</td>
								<td>${book.b_auther}</td>
								<td>${book.b_comp}</td>
								<td>${book.b_year}</td>
								<td>${book.b_iprice}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
		</div>
	</section>

</body>
</html>