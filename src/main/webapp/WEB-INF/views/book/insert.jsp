<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>도서정보 등록</title>
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
		<legend>도서정보 등록</legend>
		<form:form modelAttribute="bookVO" class="book-form">
			<form:input path="b_code" class="in-box" placeholder="도서코드"/><br/>
			<form:input path="b_name" class="in-box" placeholder="도서명"/><br/>
			<form:input path="b_auther" class="in-box" placeholder="저자"/><br/>
			<form:input path="b_comp" class="in-box" placeholder="출판사"/><br/>
			<form:input path="b_year" class="in-box" placeholder="출판연도"/><br/>
			<form:input path="b_iprice" class="in-box" placeholder="가격"/><br/>
			
			<button>저장</button>
		</form:form>
	
	
	</fieldset>
</body>
</html>