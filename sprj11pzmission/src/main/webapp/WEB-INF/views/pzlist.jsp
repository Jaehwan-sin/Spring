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
            #title {
                    text-align: left;
                    width: 300px;
            }
</style>
</head>
<body>
<h2>pzlist.jsp</h2>
<table width="1000" border="1">
        <tr>
                <td>번호</td>
                <td>이름</td>
                <td>제목</td>
                <td>날짜</td>
                <td>조회수</td>
        </tr>
<c:forEach items="${pzlist }" var="dto">
        <tr>
        	<td>${dto.pzid }</td>
        	<td>${dto.pzname }</td>
     <%-- <td><a href="pzcontent_view?pzid=${dto.pzid }">${dto.pzsubj }</a></td> --%>
        	<td id="title">
        	       <c:set value="${dto.pzintent}" var="endintent" />
					<c:forEach begin="1" end="${dto.pzintent}" var="cnt">
							    &nbsp;
							    <c:if test="${cnt eq endintent}">
							        <!-- <img src="resources/img/arrow.png" alt="arrow.png" /> -->
							        [re]
							    </c:if>
					</c:forEach> 
        	        <a href="pzcontent_view?pzid=${dto.pzid }">${dto.pzsubj } </a>
        	</td>
        	<td>${dto.pzdate }</td>
        	<td>${dto.pzhit }</td>
        </tr>  
</c:forEach>
         <tr> 
                <td colspan="5"><a href="pzwrite_view">글쓰기</a></td>
        </tr>
</table>
</body>
</html>