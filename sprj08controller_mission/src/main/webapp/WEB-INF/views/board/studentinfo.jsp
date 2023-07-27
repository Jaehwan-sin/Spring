<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>studentinfo.jsp</h2>
<h3>결과</h3>
<!-- studentinfo의 getter,setter에서 지정한 값과 name과 같아야해서 받아오는 .뒤에 값도 같아야한다. -->
${studentinfo.id } <br />
${studentinfo.pw } <br />
${studentinfo.age } <br />
<hr />
<!-- controller에서 값을 stu로 지정해서 stu로 받아와야한다. -->
${stu.id } <br />
${stu.pw } <br />
${stu.age } <br />
</body>
</html>