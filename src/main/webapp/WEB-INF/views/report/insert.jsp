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
<title>독서록 등록</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link href="${rootPath}/css/insert.css?version=2020-01-17-001" type="text/css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.15/dist/summernote-lite.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.15/dist/summernote-lite.js"></script>
<script src="${rootPath}/js/summernote-ko-KR.js"></script>
<script>
$(function(){

	$("#btn-save").click(function(){
		$("form").submit
	})
	
	// 누르면, 선택되면 전체 블록
	$("#rb_bname").focus(function(){
		$(this).select()
	})
	
	$("#rb_bname").keypress(function(event){
		
		if(event.keyCode == 13) {
			
			let strText = $(this).val()
			if(strText == "") {
				
				alert("도서이름을 입력한 후 Enter를 눌러주세요")
				return false
				
			}
			$("#modal-box").css('display','block')
			// strText는 보낼 데이터, 여기서는 검색하고자 하는 도서명
			$.post("${rootPath}/book/search", {strText:strText},function(result){
				$("#modal-content").html(result)
			})			
		}
		return false
	})
	
	$(".modal-header span").click(function(){
		$("#modal-box").css("display","none")
	})
	var toolbar = [
		
		['style', ['bold','italic','underline']],
		['fontsize',['fontsize']],
		['font Style',['fontname']],
		['color',['color']],
		['para',['ul','ol','paragraph']],
		['height',['height']],
		['table',['table']],
		['insert',['link','picture','hr']],
		['view',['fullscreen','codeview']]
		
	]
	
	$("#rb_text").summernote({
		
		lang:'ko-KR',
		placeholder: '본문을 입력하세요',
		width:'100%',
		toolbar:toolbar,
		height:'200px',
		disableDragAndDrop: true
		
	})
	
})
</script>
</head>
<body>
	<fieldset>
		<legend><a href="${rootPath}/report/">독서록 등록</a></legend>
		<form method="POST" class="book-form">
			<div>
				<label>도서코드</label><br/><input type="text" name="rb_bcode" id="rb_bcode" value="${reportDTO.rb_bcode}">
				<input id="rb_bname" name="rb_bname" placeholder="도서명 검색">
			</div>
			<div>
				<input type="hidden" name="rb_id" id="rb_id" value="${reportDTO.rb_id}">
			</div>
			<div>
				<label>독서일자</label><br/><input type="date" name="rb_date" id="rb_date" placeholder="오늘날짜 등록할거면 입력 안 해도 됩니다." value="${reportDTO.rb_date}">
			</div>
			<div>
				<label>시작시간</label><br/><input type="text" name="rb_stime" id="rb_stime" value="${reportDTO.rb_stime}">
			</div>
			<div>
				<label>읽은시간</label><br/><input type="number" name="rb_rtime" id="rb_rtime" value="${reportDTO.rb_rtime}">
			</div>
			<div>
				<label>한줄소감</label><br/><input type="text" name="rb_subject" id="rb_subject" value="${reportDTO.rb_subject}">
			</div>
			<div>
				<label>별점</label><br/><input type="number" min="0" max="5" name="rb_star" id="rb_star" value="${reportDTO.rb_star}">
			</div>
			<div>
				<label>긴줄소감</label><br/><textarea name="rb_text" id="rb_text"></textarea>
			</div>
			<button id="btn-save" type="submit">저장</button>
		</form>
		<%/* 
		<form:form modelAttribute="reportDTO" class="book-form">
			<form:input path="rb_bcode" class="in-box" placeholder="도서코드"/><br/>
			<form:input path="rb_date" class="in-box" placeholder="오늘날짜 등록할거면 입력 안 해도 됩니다."/><br/>
			<form:input path="rb_stime" class="in-box" placeholder="독서시작시간"/><br/>
			<form:input path="rb_rtime" class="in-box" placeholder="독서시간"/><br/>
			<form:input path="rb_subject" class="in-box" placeholder="한줄평"/><br/>
			<form:input path="rb_text" class="in-box" placeholder="후기(긴줄소감)"/><br/>
			<form:input path="rb_star" min="0" max="5" class="in-box" placeholder="별점"/><br/>
			<button>저장</button>
		</form:form>
		*/%>
	
	</fieldset>
	<div id="modal-box">
		<div class="modal-header">
			<span>&times;</span>
		</div>
		<div id="modal-content">
		
		</div>
	</div>
</body>
</html>