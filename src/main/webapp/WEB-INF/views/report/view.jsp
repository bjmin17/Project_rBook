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
<link href="${rootPath}/css/view.css?version=2020-01-17-001" type="text/css" rel="stylesheet">
<title>독서록정보 상세보기</title>
</head>

<script>
$(function(){
	$("#btn-update").on("click",function(){
		//alert("update")
		/*
		새 페이지를 만들어 지금 페이지 위에 겹쳐서 보여라
		뒤로가기를 하면 이전페이지로 돌아가기가 되고
		*/
		document.location.href 
			= "${rootPath}/report/update?id=${reportDTO.rb_seq}" 
		
	})
	
	$("#btn-delete").click(function(){
		if(confirm("독서록을 삭제합니다!!!")) {
			/*
			현재 페이지를 지우고, 새로운 페이지로 다시 그려라
			뒤로가기를 하면 엉뚱한 페이지(history에 저장된)로 이동
			*/
			let query = "${rootPath}/report/delete?id=${reportDTO.rb_seq}"
			document.location.replace(query)
		}
	})
	
	$(".book_link").click(function(){
		
		let id = $(this).attr("data-id")
		
		document.location.href="${rootPath}/book/view?id=${reportDTO.rb_bcode}"
	})
	
})
</script>
<body>
	<table>
		<caption><a href="${rootPath}/book/">독서록정보 상세</a></caption>
		<tr>
			<th>일련번호</th><td>${reportDTO.rb_seq}</td>
			<th>도서코드</th><td class="book_link" data-id="book_link">${reportDTO.rb_bcode}</td>
		</tr>
		<tr>
			<th>독서일자</th><td>${reportDTO.rb_date}</td>
			<th>독서시작시각</th><td>${reportDTO.rb_stime}</td>
		</tr>
		<tr>
			<th>독서시간</th><td>${reportDTO.rb_rtime}</td>
			<th>별점</th><td>${reportDTO.rb_star}</td>
		</tr>
		<tr class="tr_subject">
			<th>한줄소감</th><td colspan="3">${reportDTO.rb_subject}</td>
		</tr>
		<tr class="tr_text">
			<th class="th_text">긴줄소감</th><td class="td_text" colspan="3">${reportDTO.rb_text}</td>
		</tr>
	</table>
	<br/><br/>
	<div class="btn-box">
		<a href ="javascript:void(0)" class="btn" id="btn-update">수정</a>
		<a href ="javascript:void(0)" class="btn" id="btn-delete">삭제</a>
	</div>
</body>
</html>