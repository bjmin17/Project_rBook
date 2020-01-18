<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<style>
		table th:nth-child(1){
			width: 60px;
		}
		table th:nth-child(2){
		}
		table th:nth-child(3){
			width: 160px;
		}
		table th:nth-child(5){
			width: 90px;
		}
		table th:nth-child(7){
			width: 60px;
		}
	</style>
	<section>
		<div>
			<h3>${userDTO.m_id}님의 독서록 리스트</h3>
			<table border="1">
				<tr>
					<th>번호</th>
					<th>유저id</th>
					<th>도서코드</th>
					<th>도서제목</th>
					<th>독서일자</th>
					<th>한줄소감</th>
					<th>별점</th>
				</tr>
				<c:choose>
					<c:when test="${empty reportList}">
						<tr>
							<td colspan="7">작성된 독서록이 없음</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${reportList}" var="report" varStatus="status">
							<c:if test="${userDTO != null && userDTO.m_id != null && userDTO.m_id == report.rb_id}">
							<tr class="list-body"
								data-id="${report.rb_seq}">
								<!--  유저id, 도서코드, 도서제목, 독서일자, 한줄소감, 별점-->
								<td>${status.count}</td>
								<td>${report.rb_id}</td>
								<td>${report.rb_bcode}</td>
								<td>${report.b_name_list[0].b_name}</td>
								<td>${report.rb_date}</td>
								<td>${report.rb_subject}</td>
								<td>${report.rb_star}</td>
							</tr>
							</c:if>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
		</div>
	</section>
	<section>
		<%@ include file = "/WEB-INF/views/include/pagination-read.jsp" %>
	</section>
	