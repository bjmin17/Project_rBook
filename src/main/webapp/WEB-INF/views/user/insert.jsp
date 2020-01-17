<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회원가입</title>
<link href="${rootPath}/css/user-insert.css?version=2020-01-17-001" type="text/css" rel="stylesheet">
<script>
$(function(){
	$("#m_id").keypress(function(e){
		if(e.keyCode == 13){
			idcheck()
		}
		
		$("#id_check").click(idcheck)
		
		$("#btn-save").click(function(){

			$("form").submit()
			alert("회원가입 완료")
		})
		
	})
})
</script>
</head>
<body>
<fieldset>
	<legend>
		<a href="${rootPath}/report/"><c:if test="${TITLE == null }">회원가입</c:if></a>
		<a href="${rootPath}/report/"><c:if test="${TITLE != null }">${TITLE}</c:if></a>
	</legend>

	<form:form modelAttribute="userDTO" autocomplete="on" class="memo-form">
	
	<div class="in-box-border">
		<form:input path="m_id" type="text" class="in-box" placeholder="사용자 ID(4~20자리)를 입력하고 Enter..."/>
		<%/* <button type="button" id="id_check">아이디검사</button>*/%>
		<br/>
		
		<form:errors path="m_id" class="in-error"/><br/>
		<span id="m_id_msg"></span>
	</div>
	<div class="in-box-border">
		<form:input path="m_password" type="password" class="in-box" placeholder="비밀번호(8~20자리)를 입력하세요"/><br/>
		<form:errors path="m_password" class="in-error"/>
	</div>
	
	<%
	/*
	비밀번호 확인 input box를 표준 html로 작성
	비밀번호 확인은 서버로 데이터를 전송할 필요가 없기 때문
	또한 form:input으로 작성을 하게 되면
	DTO에 해당 필드변수를 작성해야하는 불편이 있기 때문에
	이 항목에 입력된 값은 서버로 전송하기 전
	두번 입력한 비밀번호 같은지만 검사하면 되기 때문에
	*/
	%>
	<button id="btn-save">저장</button>
	
	</form:form>
</fieldset>


</body>
</html>