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
<title>로그인 화면</title>
<style>
* {
	margin:0;
	padding:0;
	box-sizing: border-box;
}

.login-form{
	
	width:400px;
	padding:40px;
	
	background: #191919;
	text-align: center;
	z-index:10;
	
	border-radius:20px;
	box-shadow: 12px 12px 2px 1px rgba(0,0,255,0.2);
	
	margin:20px auto;
	
}

.login-form h2{
	color:white;
	font-weight: 500;
}
.login-form h3{
	color:white;
	font-weight: 300;
	background-color: red;
	border-radius: 20px;
}

.login-form input{
	
	background: none;
	margin:20px auto;
	text-align: center;
	
	border:2px solid #3498db;
	
	padding:14px 10px;
	
	width:200px;
	outline:none;
	
	color:white;
	
	border-radius: 25px;
}

.login-form button{
	
	border:2px solid #2ECC71;
	padding:14px 40px;
	background: none;
	display:block;
	margin:20px auto;
	padding:14px 40px;
	
	outline:none;
	color:white;
	
	border-radius: 25px;
	transition : 0.2s;
	
	cursor: pointer;
}

.login-form input:focus{
	width:280px;
	border-color:#2ECC71;
}

.login-form button:hover, .login-form button:focus{
	background-color: #2ECC71;
}
</style>
<script>
$(function(){
	
	$("#btn_join").click(function(){
		
		document.location.href = "${rootPath}/user/join"
		
	})
	
})
</script>
</head>
<body>
	<form method="POST" action="${rootPath}/member/login" class="login-form">
		<h2>로그인</h2>
		
		<c:if test="${LOGIN_MSG == 'FAIL' }">
			<h3>아이디나 비밀번호가 잘못되었습니다.</h3>
		</c:if>
		<c:if test="${LOGIN_MSG == 'TRY' }">
			<h3>로그인을 해야합니다.</h3>
		</c:if>
		<c:if test="${LOGIN_MSG == 'NO_AUTH' }">
			<h3>작성자만이 볼 수 있습니다.</h3>
		</c:if>
		<c:if test="${LOGIN_MSG == '0' }">
			<h3>로그인을 환영합니다.</h3>
		</c:if>
		
		<input type="text" name="m_id" placeholder="사용자 ID">
		<input type="password" name="m_password" placeholder="비밀번호">
		<button>로그인</button>
		<c:if test="${LOGIN_MSG == '0' }">
			<button id="btn_join" class="bz_button">회원가입</button>
		</c:if>
	
	</form>
</body>
</html>