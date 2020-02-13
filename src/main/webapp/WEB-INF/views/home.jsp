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
<title>독서록정보</title>
<link href="https://cdnjs.cloudflare.com/ajax/libs/jquery-contextmenu/2.9.0/jquery.contextMenu.min.css"	rel="stylesheet">
<script	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-contextmenu/2.9.0/jquery.contextMenu.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-contextmenu/2.9.0/jquery.ui.position.min.js"></script>


<!-- 부트스트링4 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<link href="${rootPath}/css/home.css?version=2020-01-17-001" type="text/css" rel="stylesheet">

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
<%@ include file="/WEB-INF/views/include/header.jspf" %>
<%@ include file="/WEB-INF/views/include/nav.jspf" %>
	<c:if test = "${BODY == 'BOOK' }">
		<%@ include file = "/WEB-INF/views/include/book-body.jsp" %>
	</c:if>
	<c:if test = "${BODY == 'REPORT' }">
		<%@ include file = "/WEB-INF/views/include/report-body.jsp" %>
	</c:if>
	


</body>
</html>