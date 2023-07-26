<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>studentform.jsp</h2>
<form action="studentinfo">
		ID : <input type="text" id="id" name="id" /> <br />
		PW : <input type="text" id="pwd" name="pw" /> <br /> <!-- studentinfo의 getter,setter에서 지정한 값과 name과 같아야한다. -->
		AGE : <input type="text" id="age" name="age" /> <br />
		<input type="submit" value="login" />
</form>
</body>
</html>