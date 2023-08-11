<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    table{
        border: 1px solid black;
        border-collapse: collapse;
        text-align : center;
        align-items: center;
    }
</style>
</head>
<body>
<h2>list.jsp</h2>
${list }

<table>
    <tr>
    	<td>번호</td>
    	<td>제목</td>
    	<td>조회수</td>
    	<td>작성일</td>
    </tr>
    <tr>
    	<td>1</td>
    	<td>하이</td>
    	<td>0</td>
    	<td>2023-08-11</td>
    </tr>

</table>
</body>
</html>