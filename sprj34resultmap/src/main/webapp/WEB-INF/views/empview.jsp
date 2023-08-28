<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path=request.getContextPath();

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 모바일에서 내용물사이즈 보정 -->
    <title>Document</title>

</head>

<body>
<h3>empview</h3>
<table width="500" border="1">
				<tr>
					<td>회사번호</td>
					<td>이름</td>
					<td>메니저</td>
					<td>날자</td>
					<td>부서번호</td>
				</tr>
				<c:forEach items="${list }" var="emp">
				<tr>
					<td>${emp.empno }</td>
					<td>${emp.ename }</td>
					<td>${emp.mgr }</td>
					<td>${emp.hiredate }</td>	
					<td>${emp.deptno }</td>
				</tr>
				</c:forEach>
				<tr>
					<td colspan="5"><a href="writeview">글쓰기</a></td>
				</tr>
			</table>
			<a href="/sWeb05">home</a>
</body>
</html>
