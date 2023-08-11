<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/nstyle.css" />
</head>
<body>
<h3>list</h3>
<table>
	<tr>
		<th>번호</th>
		<th>이름</th>
		<th>제목</th>
		<th>날자</th>
		<th>히트</th>
		<th>그림</th>
		
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
			<td height="50">그림</td>
			
		</tr>
	</c:forEach>
	<tr>
		<td colspan="6"><a href="write_view">글쓰기</a></td>
	</tr>
</table>
totCnt : ${totRowcnt } <br />
현재페이지/토탈페이지 : ${searchVo.page } / ${searchVo.totPage }

<hr />

<form action="list" method="post">
	<c:if test="${searchVo.totPage>1 }">
		<c:if test="${searchVo.page>1 }">
			<a href="list?page=1">[처음]</a>
			<a href="list?page=${searchVo.page-1 }">[이전]</a>
		</c:if>
		<c:forEach begin="${searchVo.pageStart }" end="${searchVo.pageEnd }" var="i">
			<c:choose>
				<c:when test="${i eq searchVo.page }">
					<span style="color: red; font-weight: bold;">${i }&nbsp;</span>
				</c:when>
				<c:otherwise>
					<a href="list?page=${i }&sk=${resk}&btitle=${btitle==true?'btitle':'' }&bcontent=${bcontent==true?'bcontent':'' }" style="text-decoration: none">${i }</a>&nbsp;
				</c:otherwise>
			
			</c:choose>
		</c:forEach>
		<c:if test="${searchVo.totPage>searchVo.page }">
			<a href="list?page=${searchVo.page+1 }">[다음]</a>
			<a href="list?page=${searchVo.totPage }">[마지막]</a>
		</c:if>
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
		<input type="text" name="sk" style="width:150px;" maxlength="50" value="" />
		<input type="submit" value="검색" />
		
	</div>
</form>

</body>
</html>