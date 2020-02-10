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
<link href="${rootPath}/css/home.css?version=2020-01-17-001" type="text/css" rel="stylesheet">
<link href="${rootPath}/css/insert.css?version=2020-01-17-001" type="text/css" rel="stylesheet">
</head>
<body>
	<fieldset>
		<legend><a href="${rootPath}/report/">도서정보 등록</a></legend>
		
		<form method="POST" class="book-form">
			<div>
				<label>도서코드(13자리, 000-00-000000-0-0)</label><br/><input type="text" name="b_code" id="b_code" value="${bookVO.b_code}">
			</div>
			<div>
				<label>도서명</label><br/><input type="text" name="b_name" id="b_name" value="${bookVO.b_name}">
			</div>
			<div>
				<label>저자</label><br/><input type="text" name="b_auther" id="b_auther" value="${bookVO.b_auther}">
			</div>
			<div>
				<label>출판사</label><br/><input type="text" name="b_comp" id="b_comp" value="${bookVO.b_comp}">
			</div>
			<div>
				<label>출판연도(MM-DD-YYYY)</label><br/><input type="date" name="b_year" id="b_year" value="${bookVO.b_year}">
			</div>
			<div>
				<label>가격</label><br/><input type="number" name="b_iprice" id="b_iprice" value="${bookVO.b_iprice}">
			</div>
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