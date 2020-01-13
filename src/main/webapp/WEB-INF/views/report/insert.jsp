<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>독서록 등록</title>
<style>
	fieldset{
		width:70%;
		margin:20px auto;
		border:1px solid green;
		border-radius: 10px;
	}
	legend {
		font-weight: bold;
		font-size: 20px;
	}
	input, textarea{
		display:inline-block;
		width:90%;
		padding:8px;
		margin:5px;
		border-radius: 20px;
	}
	input:focus, textarea:focus, button{
		border:2px solid blue;
		outline:none;
		
	}
	a {
		text-decoration: none;
	}
</style>
</head>
<body>
	<fieldset>
		<legend><a href="${rootPath}/report/">독서록 등록</a></legend>
		<form method="POST" class="book-form">
			<div>
				<label>도서코드</label><input type="text" name="rb_bcode" id="rb_bcode" value="${reportDTO.rb_bcode}">
			</div>
			<div>
				<input type="hidden" name="rb_id" id="rb_id" value="${reportDTO.rb_id}">
			</div>
			<div>
				<label>독서일자</label><input type="date" name="rb_date" id="rb_date" placeholder="오늘날짜 등록할거면 입력 안 해도 됩니다." value="${reportDTO.rb_date}">
			</div>
			<div>
				<label>시작시간</label><input type="text" name="rb_stime" id="rb_stime" value="${reportDTO.rb_stime}">
			</div>
			<div>
				<label>읽은시간</label><input type="text" name="rb_rtime" id="rb_rtime" value="${reportDTO.rb_rtime}">
			</div>
			<div>
				<label>한줄소감</label><input type="text" name="rb_subject" id="rb_subject" value="${reportDTO.rb_subject}">
			</div>
			<div>
				<label>긴줄소감</label><input type="text" name="rb_text" id="rb_text" value="${reportDTO.rb_text}">
			</div>
			<div>
				<label>별점</label><input type="number" min="0" max="5" name="rb_star" id="rb_star" value="${reportDTO.rb_star}">
			</div>
			<button id="btn-save" >저장</button>
		</form>
		<%/* 
		
		<form:form modelAttribute="reportDTO" class="book-form">
			<form:input path="rb_bcode" class="in-box" placeholder="도서코드"/><br/>
			<form:input path="rb_date" class="in-box" placeholder="오늘날짜 등록할거면 입력 안 해도 됩니다."/><br/>
			<form:input path="rb_stime" class="in-box" placeholder="독서시작시간"/><br/>
			<form:input path="rb_rtime" class="in-box" placeholder="독서시간"/><br/>
			<form:input path="rb_subject" class="in-box" placeholder="한줄평"/><br/>
			<form:input path="rb_text" class="in-box" placeholder="후기(긴줄소감)"/><br/>
			<form:input path="rb_star" min="0" max="5" class="in-box" placeholder="별점"/><br/>
			<button>저장</button>
		</form:form>
		*/%>
	
	</fieldset>
</body>
</html>