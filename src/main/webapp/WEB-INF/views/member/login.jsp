<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link href="${rootPath}/css/login.css?version=2020-01-17-001" type="text/css" rel="stylesheet">
<title>로그인 화면</title>
<script>
$(function(){
	
	$("#btn_join").click(function(){
		
		document.location.href = "${rootPath}/user/join"
	})
	$("#btn_report").click(function(){
		
		document.location.href = "${rootPath}/report/"
		
	})
	
})
</script>
</head>
<body>
	<% /* 
		로그인 페이지에서 입력한 값들을 POST방식으로 해서 
		action에서 지정한 주소로 보내준다.
	*/ %>
	<form method="POST" action="${rootPath}/member/login" class="login-form">
		<h2><a href="${rootPath}/book/">로그인</a></h2>
		
		<% /* 
			LOGIN_MSG의 값에 따라서 보여열을 다르게 보여준다.
		*/ %>
		<c:if test="${LOGIN_MSG == 'FAIL' }">
			<h3>아이디나 비밀번호가 잘못되었습니다.</h3>
		</c:if>
		<c:if test="${LOGIN_MSG == '0' }">
			<h3>로그인을 환영합니다.</h3>
		</c:if>
		<%/* 
		<input>을 사용할 때, name의 값은 DTO에 있는 값들과 일치시켜주어야 한다.
		*/ %>
		<input type="text" name="m_id" placeholder="사용자 ID">
		<input type="password" name="m_password" placeholder="비밀번호">
		<button>로그인</button>
		<% /* LOGIN_MSG가 0인 경우는 디폴트 값으로 0이 지정되어 있으므로
			로그인을 첫 시도 했을경우이고, 처음일 경우만 회원가입이 보여지도록 해주는 코드*/ %>
		<c:if test="${LOGIN_MSG == '0' }">
			<button type="button" id="btn_join" class="bz_button">회원가입</button>
		</c:if>
	
	</form>
</body>
</html>