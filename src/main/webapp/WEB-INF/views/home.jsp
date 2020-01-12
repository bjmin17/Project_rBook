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
	margin: 0px;
	padding: 0px;
}

body {
	width: 100%;
}

header {
	background-color: #41D3BD;
	margin: 0;
	padding: 1rem;
	color: white;
}

table {
	width:90%;
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

table a {
	text-decoration: none;
}

list-body:hover{
	background-color: gray;

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
		<h3><a href="${rootPath}/report/">도서정보</a></h3>
		<p><a href="${rootPath}/member/logout">${userDTO.m_id}</a></p>
	</header>

<% /* 	<c:if test="${BODY == 'BOOK'}"> 
		<%@ include file="/WEB-INF/views/body/join.jsp" %
	</c:if>*/%>
	<section>
		<button id="btn_book" class="bz_button">도서리스트 보기</button>
		<button id="btn_insert" class="bz_button">도서등록</button>
		<button id="btn_report" class="bz_button">독서록보기</button>
		<button id="btn_report_insert" class="bz_button">독서록등록</button>
		<c:if test="${userDTO == null || userDTO.m_id == null}">
			<a href="${rootPath}/member/login"><button id="btn_login" class="bz_button">로그인</button></a>
			<a href="${rootPath}/user/join"><button id="btn_join" class="bz_button">회원가입</button></a>
		</c:if>
		<c:if test="${userDTO != null && userDTO.m_id != null}">
			<a href="${rootPath}/member/logout"><button id="btn_login" class="bz_button">로그아웃</button></a>
			<a href="#"><button id="btn_join" class="bz_button">${userDTO.m_id}님 로그인을 환영합니다</button></a>
		</c:if>
		
	</section>
	<c:if test = "${BODY == 'BOOK' }">
		<%@ include file = "/WEB-INF/views/include/book-body.jsp" %>
	</c:if>
	<c:if test = "${BODY == 'REPORT' }">
		<%@ include file = "/WEB-INF/views/include/report-body.jsp" %>
	</c:if>



</body>
</html>