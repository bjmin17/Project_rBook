<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<style>
	table th:nth-child(1){
		width:160px;
	}
	table th:nth-child(2){
		width:250px;
	}
	table th:nth-child(5){
		width:110px;
	}
	table th:nth-child(6){
		width:90px;
	}
		#modal-box {
		display:none;
		position: fixed;
		z-index:5;
		padding-top: 0;
		
		left:0;
		top:0;
		
		background-color: rgba(0,0,0,0.4);
		width: 100%;
		height: 100%;
	}	
	.modal-header {
	display: flex;
	}
.modal-header span{
	color: white;
	font-size:30px;
	font-weight: bold;
	margin-left: auto;
	margin-right: 10px;
	
}

.modal-header span:hover {
	cursor: pointer;
	color:#000;
}

#modal-content{
	
	position:relative;
	overflow: auto;
	width: 80%;
	margin: 0 auto;

	height : 80%;
	background-color:gray;
	padding:5px 10px;
	border-radius:20px;
		
	animation-name : book-box;
	animation-duration : 0.8s;
	animation-fill-mode : forwards;
	
	-webkit-animation-name : book-box;
	-webkit-animation-duration : 0.8s;
	
	-moz-animation-name : book-box;
	-moz-animation-duration : 0.8s;
	
	-ms-animation-name : book-box;
	-ms-animation-duration : 0.8s;
}


@keyframes book-box{
	from{
		top: -400px;
		opacity: 0;
	}
	to{
		top: 50px;
		opacity: 1;
	}
}
@-webkit-keyframes book-box{
	from{
		top: -400px;
		opacity: 0;
	}
	to{
		top: 50px;
		opacity: 1;
	}
}	
@-moz-keyframes book-box{
	from{
		top: -400px;
		opacity: 0;
	}
	to{
		top: 50px;
		opacity: 1;
	}
}	
@-ms-keyframes book-box{
	from{
		top: -400px;
		opacity: 0;
	}
	to{
		top: 50px;
		opacity: 1;
	}
}	
#search-table {
	
	background-color: white;
	border-collapse: collapse;
	width: 85%;
	
	margin: 10px auto;
	border-bottom: 1px solid #ddd;	
	
	height: 100%;
	
}

#search-table td, #search-table th{
	white-space: nowrap;
	text-align: left;
	padding: 8px;
	vertical-align: top;
}
	</style>
	<script>
	$(function(){
		
		$("#search-table tr").click(function(){
		
			let trs = $(this).children()	
			let b_code = trs.eq(0).text()
			let b_name = trs.eq(1).text()
			
			$("#rb_bcode").val(b_code)
			$("#rb_bname").val(b_name)
			
			$("#modal-box").css("display","none")
		})
	})
	</script>
	<section id="search-list">
		
		<div>
			<table id="search-table"border="1">
				<tr>
					<th>도서코드</th>
					<th>도서명</th>
					<th>저자</th>
					<th>출판사</th>
					<th>구입일자</th>
					<th>구입가격</th>
				</tr>
				<c:choose>
					<c:when test="${empty bookList}">
						<tr>
							<td colspan="6">도서자료가 없음</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${bookList}" var="book" varStatus="index">
							<tr class="list-body"
								data-id="${book.b_code}">
								<td>${book.b_code}</td>
								<td>${book.b_name}</td>
								<td>${book.b_auther}</td>
								<td>${book.b_comp}</td>
								<td>${book.b_year}</td>
								<td>${book.b_iprice}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
		</div>
	</section>
	<c:if test="${BODY == 'BOOK'}">
		<section>
			<%@ include file = "/WEB-INF/views/include/pagination.jsp" %>
		</section>
	</c:if>