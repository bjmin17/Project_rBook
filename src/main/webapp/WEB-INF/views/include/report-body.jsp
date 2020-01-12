<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<section>
		<div>
			<table border="1">
				<tr>
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
							<td colspan="6">독서록자료가 없음</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${reportList}" var="report">
							<c:if test="${userDTO != null && userDTO.m_id != null && userDTO.m_id == report.rb_id}">
							<tr class="list-body"
								data-id="${report.rb_seq}">
								<!--  유저id, 도서코드, 도서제목, 독서일자, 한줄소감, 별점-->
								<td>${report.rb_id}</td>
								<td>${report.rb_bcode}</td>
								<td>도서제목</td>
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