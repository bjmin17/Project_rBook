<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>
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
</style>
</head>
<body>
	<fieldset>
		<legend>독서록 등록</legend>
		<form:form modelAttribute="reportDTO" class="book-form">
			<form:input path="rb_bcode" class="in-box" placeholder="도서코드"/><br/>
			<form:input path="rb_date" class="in-box" placeholder="오늘날짜 등록할거면 입력 안 해도 됩니다."/><br/>
			<form:input path="rb_stime" class="in-box" placeholder="독서시작시간"/><br/>
			<form:input path="rb_rtime" class="in-box" placeholder="독서시간"/><br/>
			<form:input path="rb_subject" class="in-box" placeholder="한줄평"/><br/>
			<form:input path="rb_text" class="in-box" placeholder="후기(긴줄소감)"/><br/>
			<form:input path="rb_star" class="in-box" placeholder="별점"/><br/>
			<button>저장</button>
		</form:form>
	
	
	</fieldset>
</body>
</html>