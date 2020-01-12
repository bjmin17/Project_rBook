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
<script>
$(function(){
	$("#m_id").keypress(function(e){
		if(e.keyCode == 13){
			idcheck()
		}
		
		$("#id_check").click(idcheck)
		
		$("#btn-save").click(function(){

			$("form").submit()
			
		})
		
	})
	
	
	
})
</script>
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
	
	input:hover{
		background-color: #ddd;
		border:2px solid red;
	}
	
	.in-error{
		display:inline-block;
		margin-left:20px;
		font-size: 12px; 
		font-weight:bold;
		color:red;
	}
	
	span#u_id_msg{
		display:none;
	}
	
	#u_id {
		width:70%;
	}
</style>
</head>
<body>
<fieldset>
	<legend>
		<a href="${rootPath}/report/"><c:if test="${TITLE == null }">회원가입</c:if></a>
		<a href="${rootPath}/report/"><c:if test="${TITLE != null }">${TITLE}</c:if></a>
	</legend>

	<form:form modelAttribute="userDTO" autocomplete="on" class="memo-form">
	
	<div class="in-box-border">
		<form:input path="m_id" type="email" class="in-box" placeholder="사용자 ID를 입력하고 Enter..."/>
		<%/* <button type="button" id="id_check">아이디검사</button>*/%>
		<br/>
		
		<form:errors path="m_id" class="in-error"/><br/>
		<span id="m_id_msg"></span>
	</div>
	<div class="in-box-border">
		<form:input path="m_password" type="password" class="in-box" placeholder="비밀번호를 입력하세요"/><br/>
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