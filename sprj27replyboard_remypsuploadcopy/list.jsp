<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" 
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
<style>
.fa-solid{
	color: #333;
}
.fa-solid:hover{
	color: orange;
}
</style>
</head>
<body>
<h3>list.jsp</h3>

<table width="500" border="1">
	<tr>
		<td>번호</td>
		<td>이름</td>
		<td>제목</td>
		<td>날자</td>
		<td>히트</td>	
	</tr>
	
<c:forEach items="${list }" var="dto">
	<tr>
		<td>${dto.bid }</td>
		<td>${dto.bname }</td>
		<td>
			<c:set value="${dto.bindent }" var="endindent" />
			<c:forEach begin="1" end="${dto.bindent }" var="cnt">
				&nbsp;
				<c:if test="${cnt eq endindent }">
					<img src="resources/img/reply.gif" alt="" />
					[re]
				</c:if>
			</c:forEach>
			<a href="content_view?bid=${dto.bid }">${dto.btitle }</a>
		</td>
		<td>${dto.bdate }</td>
		<td>${dto.bhit }</td>	
	</tr>
</c:forEach>	
	<tr>
	 	<td colspan="5"><a href="write_view">글쓰기</a></td>
	</tr>
		
</table>
totCnt : ${totRowcnt } <br />
현재페이지/토탈페이지:${searchVO.page }/${searchVO.totPage }
<hr />
<form action="list" method="post">
	<c:if test="${searchVO.page>1}">
		<a href="list?page=1"><i class="fa-solid fa-angles-left"></i></a>
		<a href="list?page=${searchVO.page-1 }"><i class="fa-solid fa-circle-chevron-left"></i></a>
	</c:if>
	<c:forEach begin="${searchVO.pageStart }" end="${searchVO.pageEnd }" var="i">
		<c:choose>
			<c:when test="${i eq searchVO.page }">
				<span style="color:red; font-weight:bold;">${i }</span>
			</c:when>
			<c:otherwise>
				<a href="list?page=${i }&sk=${resk}&btitle=${btitle==true?'btitle':''}
				&bcontent=${bcontent==true?'bcontent':''}"
				 style="text-decoration:none;">${i }</a> 
			</c:otherwise>
		</c:choose>	
	</c:forEach>
	<c:if test="${searchVO.page < searchVO.totPage}">
		<a href="list?page=${searchVO.page+1 }"><i class="fa-solid fa-circle-chevron-right"></i></a>
		<a href="list?page=${searchVO.totPage }"><i class="fa-solid fa-angles-right"></i></a>
	</c:if>
	<div>
		<c:choose>
			<c:when test="${btitle }">
				<input type="checkbox" name="searchType" value="btitle" checked />
			</c:when>
			<c:otherwise>
				<input type="checkbox" name="searchType" value="btitle" />	
			</c:otherwise>
		</c:choose>		
		제목
		<c:choose>
			<c:when test="${bcontent }">
				<input type="checkbox" name="searchType" value="bcontent" checked />
			</c:when>
			<c:otherwise>
				<input type="checkbox" name="searchType" value="bcontent" />
			</c:otherwise>
		</c:choose>
		내용
		<input type="text" name="sk" value="${resk }" style="width:150px;" maxlength="50" />
		<input type="submit" value="검색" />
	
	</div>

</form>

<a href="empsum">직업별 급여합계</a>
<a href="stuheight">학년별 키 합계</a>
</body>
</html>