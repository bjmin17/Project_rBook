<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>도서정보 상세보기</title>
<link href="${rootPath}/css/view.css?version=2020-01-17-001" type="text/css" rel="stylesheet">
</head>
<style>

</style>
<script>
$(function(){
	$("#btn-update").on("click",function(){
		//alert("update")
		/*
		새 페이지를 만들어 지금 페이지 위에 겹쳐서 보여라
		뒤로가기를 하면 이전페이지로 돌아가기가 되고
		*/
		document.location.href 
			= "${rootPath}/book/update?id=${book.b_code}" 
		
	})
	
	$("#btn-delete").click(function(){
		if(confirm("메모를 삭제합니다!!!")) {
			/*
			현재 페이지를 지우고, 새로운 페이지로 다시 그려라
			뒤로가기를 하면 엉뚱한 페이지(history에 저장된)로 이동
			*/
			let query = "${rootPath}/book/delete?id=${book.b_code}"
			document.location.replace(query)
		}
	})
})
</script>
<body>
	<table>
		<caption><a href="${rootPath}/report/">도서정보 상세</a></caption>
		<tr>
			<th>도서코드</th><td>${book.b_code}</td>
			<th>도서명</th><td>${book.b_name}</td>
		</tr>
		<tr>
			<th>저자</th><td>${book.b_auther}</td>
			<th>출판사</th><td>${book.b_comp}</td>
		</tr>
		<tr>
			<th>출판연도</th><td>${book.b_year}</td>
			<th>가격</th><td>${book.b_iprice}</td>
		</tr>
	</table>
	<br/><br/>
	<div class="btn-box">
		<a href ="javascript:void(0)" class="btn" id="btn-update">수정</a>
		<a href ="javascript:void(0)" class="btn" id="btn-delete">삭제</a>
	</div>
</body>
</html>