<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
	String path = request.getContextPath();
%>
<html>
<head>
	<title>Home</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<h1>
	Hello world!  
</h1>
<script>
		function restdeptlist() {
			<%-- alert("<%=path %>"); --%>
			var htmltxt = "";
			$.ajax({
				type : "post",
				url : "<%=path %>/test/deptlist",
				success : function(result) {
					htmltxt = "<table border='1'>";
					
					for ( var ele in result) {
						console.log(result[ele].loc);
						 
						htmltxt = htmltxt+"<tr><td>no:"+result[ele].deptno+
						"</td><td>dname:"+result[ele].dname+
						"</td><td>loc:"+result[ele].loc+"</td></tr>";
					}
					htmltxt = htmltxt + "</table>";
					$("#display").html(htmltxt);
				}
			});
		}
</script>



<P>  The time on the server is ${serverTime}. </P>
<a href="list">list</a> <!-- 순서 1번째 --> <br />
<h3>DB접속 데이터 가져오기</h3>
<hr />
<a href="javascript:restdeptlist();">restdeptlist</a>
<hr />
<div id="display">이 곳이 데이터 추가되는 곳</div>
<hr />
<a href="star">star</a>
</body>
</html>
