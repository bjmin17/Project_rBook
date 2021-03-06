<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<article>
	<div class="paginationDiv">
		<ul class="pagination">
		
			<c:if test="${pageDTO.startPageNo > 1}">
				<li class="page-item"><a href="${rootPath}/book/?currentPageNo=1&search=${search}" class="page-link">&laquo;</a></li>
				<li class="page-item"><a href="${rootPath}/book/?currentPageNo=${pageDTO.prePageNo}&search=${search}" class="page-link">&lt;</a></li>
			</c:if>
			
			<c:if test="${pageDTO.startPageNo > 2}">
				<li class="page-item"><a class="page-link">&middot;&middot;&middot;</a></li>
			</c:if>
			
			<c:forEach begin="${pageDTO.startPageNo}" end="${pageDTO.endPageNo}" var="pageNo">
			<li class="page-item <c:if test="${pageNo == pageDTO.currentPageNo}">active</c:if>"><a href="${rootPath}/book/?currentPageNo=${pageNo}&search=${search}" class="page-link">${pageNo}</a></li>
			</c:forEach>
			
			<c:if test="${pageDTO.endPageNo != pageDTO.finalPageNo}">
				<li class="page-item"><a class="page-link">&middot;&middot;&middot;</a></li>
				<li class="page-item"><a href="${rootPath}/book/?currentPageNo=${pageDTO.nextPageNo}&search=${search}" class="page-link">&gt;</a></li>
				<li class="page-item"><a href="${rootPath}/book/?currentPageNo=${pageDTO.finalPageNo}&search=${search}" class="page-link">&raquo;</a></li>
			</c:if>
		</ul>
	</div>
</article>