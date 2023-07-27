<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
            table {
                    text-align: center;
                    border-collapse: collapse;
            }
            tr:nth-child(odd) { /* 홀수행 색 다르게 짝수는 even */
                    background-color: #f7f9fc;
            }
</style>
</head>
<body>
<!-- 4 -->
<h2>list.jsp</h2>
<table width="500" border="1">
        <tr>
                <td>번호</td>
                <td>이름</td>
                <td>제목</td>
                <td>날짜</td>
                <td>조회수</td>
        </tr>    
<c:forEach items="${list }" var="dto"> <!-- 23 c:foreach문을 활용해 DB 테이블 값 불러오기 -->
        <tr>
                <td>${dto.bid }</td>
                <td>${dto.bname }</td>
                <td>${dto.btitle }</td>
                <td>${dto.bdate }</td>
                <td>${dto.bhit }</td>
        </tr>    
</c:forEach>
</table>
</body>
</html>