<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/nstyle.css" />
</head>
<body>
<h3>content_view.jsp</h3>

<table>
	<tr>
		<td class="left">번호</td>
		<td>${content_view.bid }</td>
	</tr>
	<tr>
		<td class="left">히트</td>
		<td>${content_view.bhit }</td>
	</tr>
	<tr>
		<td class="left">이름</td>
		<td>${content_view.bname }</td>
	</tr>
	<tr>
		<td class="left">제목</td>
		<td>${content_view.btitle }</td>
	</tr>
	<tr>
		<td class="left">내용</td>
		<td>${content_view.bcontent }</td>
	</tr>
	<tr>
		<td class="left">첨부</td>
		<td>
			<a href="download?p=resources/upload/&f=${content_view.filesrc }
			&bid=${content_view.bid }">${content_view.filesrc }</a>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<a href="content_update?bid=${content_view.bid }">수정</a> &nbsp;&nbsp;
			<a href="list">목록</a> &nbsp;&nbsp;
			<a href="delete?bid=${content_view.bid }">삭제</a> &nbsp;&nbsp;
			<a href="reply_view?bid=${content_view.bid }">답변</a> &nbsp;&nbsp;
		</td>
	</tr>
	
</table>
<c:if test="${content_view.filesrc ne null }">
	<img border="1" width="100" height="100" 
		src="resources/upload/${content_view.filesrc }" alt="" />
</c:if>
</body>
</html>