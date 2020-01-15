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
	margin: 0px auto;
	padding: 0px;
}

body {
	width: 1200px;
	height: 100%;
}

header {
	background-color: black;
	margin: 0;
	padding: 1rem;
}

h3 {
	text-align: center;
	background-color:inherit; 
	
}

table {
	width:70%;
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
table tr:nth-child(odd){
	background-color: #ddd;
}

table a {
	text-decoration: none;
}
a {
	text-decoration: none;
	color:white;
}

.list-body:hover{
	background-color: #50bcdf;
	cursor: pointer;
}

div.btn-box{
	width:70%;
	display:flex;
	margin:5px auto;
	justify-content: center;
	align-items: center;
		
}

.bz_button{
	border-radius: 3px;
	padding:5px 11px;
	color:white;
	display: inline-block;
	background-color: black;
	border : 1px solid #56819d;
	vertical-align: middle;
	text-decoration: none;
	margin: 10px;
	
}

#btn_login, #btn_logout {
	margin-left: auto;
}

.bz_button:hover{
	/*border:1px solid blue;*/
	box-shadow: 5px 5px 8px rgba(80,80,80,0.8);
	cursor: pointer;
}
</style>
<script>
$(function(){
	$("#btn_book").click(function(){
		
		document.location.href = "${rootPath}/book/"
		
	})
	
	$("#btn_insert").click(function(){
		
		document.location.href = "${rootPath}/book/insert"
		
	})
	
	
	
	$("#btn_report").click(function(){
		
		if("${userDTO}" == "") {
			document.location.href = "${rootPath}/member/login"
		} else {
			document.location.href = "${rootPath}/report/"
		}
		
	})
	
	$("#btn_report_insert").click(function(){
		
		if("${userDTO}" == "") {
			document.location.href = "${rootPath}/member/login"
		} else {
			document.location.href = "${rootPath}/report/insert"
		}
		
	})
	
	$("#btn_login").click(function(){
		
		document.location.href = "${rootPath}/member/login"
	})
	
	$("#btn_logout").click(function(){
		
		document.location.href = "${rootPath}/member/logout"
	})
	
	
	
	
	$(".list-body").click(function(){
		let id = $(this).attr("data-id")
		
		//alert(id)
		if("${BODY}" == 'BOOK') {
			document.location.href = "${rootPath}/book/view?id=" + id
		}
		if("${BODY}" == 'REPORT') {
			document.location.href = "${rootPath}/report/view?id=" + id
		}
	})
})
</script>
</head>
<body>
	<header>
		<h3><a href="${rootPath}/report/">독서록정보</a></h3>
	</header>

<% /* 	<c:if test="${BODY == 'BOOK'}"> 
		<%@ include file="/WEB-INF/views/body/join.jsp" %
	</c:if>*/%>
	<section>
		<div class="btn-box">
			<button id="btn_book" class="bz_button">도서리스트 보기</button>
			<button id="btn_insert" class="bz_button">도서등록</button>
			<button id="btn_report" class="bz_button">독서록보기</button>
			<button id="btn_report_insert" class="bz_button">독서록등록</button>
			<c:if test="${userDTO == null || userDTO.m_id == null}">
				<button id="btn_login" class="bz_button">로그인</button>
				<a href="${rootPath}/user/join"><button id="btn_join" class="bz_button">회원가입</button></a>
			</c:if>
			<c:if test="${userDTO != null && userDTO.m_id != null}">
				<button id="btn_logout" class="bz_button">로그아웃</button>
				<a href="#"><button id="btn_join" class="bz_button">${userDTO.m_id}</button></a>
			</c:if>
		</div>
	</section>
	<c:if test = "${BODY == 'BOOK' }">
		<%@ include file = "/WEB-INF/views/include/book-body.jsp" %>
	</c:if>
	<c:if test = "${BODY == 'REPORT' }">
		<%@ include file = "/WEB-INF/views/include/report-body.jsp" %>
	</c:if>



</body>
</html>