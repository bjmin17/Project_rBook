<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<section>
		<div>
			<table border="1">
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
						<c:forEach items="${bookList}" var="book">
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