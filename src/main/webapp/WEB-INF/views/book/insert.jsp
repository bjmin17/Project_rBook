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
		<legend><a href="${rootPath}/report/">도서정보 등록</a></legend>
		
		<form method="POST" class="book-form">
			<label>도서코드</label><input type="text" name="b_code" id="b_code" value="${bookDTO.b_code}">
			<label>도서명</label><input type="text" name="b_name" id="b_name" value="${bookDTO.b_name}">
			<label>저자</label><input type="text" name="b_auther" id="b_auther" value="${bookDTO.b_auther}">
			<label>출판사</label><input type="text" name="b_comp" id="b_comp" value="${bookDTO.b_comp}">
			<label>출판연도</label><input type="date" name="b_year" id="b_year" value="${bookDTO.b_year}">
			<label>가격</label><input type="number" name="b_iprice" id="b_iprice" value="${bookDTO.b_iprice}">
			<button id="btn-save" >저장</button>
		</form>
		<%/* 
		
		<form:form modelAttribute="bookVO" class="book-form">
			<form:input path="b_code" class="in-box" placeholder="도서코드"/><br/>
			<form:input path="b_name" class="in-box" placeholder="도서명"/><br/>
			<form:input path="b_auther" class="in-box" placeholder="저자"/><br/>
			<form:input path="b_comp" class="in-box" placeholder="출판사"/><br/>
			<form:input path="b_year" class="in-box" placeholder="출판연도"/><br/>
			<form:input path="b_iprice" class="in-box" placeholder="가격"/><br/>
			
			<button>저장</button>
		</form:form>
		*/%>
	
	</fieldset>
</body>
</html>