<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/nstyle.css" />
</head>
<body>
<h3>write_view.jsp</h3>
<table>

<form action="write" method="post" enctype="multipart/form-data">
	<tr>
		<td class="left"> 이름 </td>
		<td> <input type="text" name="bName" value="aa"></td>
	</tr>
	<tr>
		<td class="left"> 제목 </td>
		<td> <input type="text" name="bTitle" value="aa"></td>
	</tr>
	<tr>
		<td class="left"> 내용 </td>
		<td> <textarea name="bContent"  rows="10">aa</textarea> </td>
	</tr>
	<!-- <tr>
		<td class="left"> 첨부 </td>
		<td> <input type="file" name="file" /> </td>
	</tr> -->
	<tr>
		<td class="left"> 첨부 </td>
		<td> 
			<input multiple type="file" name="file" />
		</td>
	</tr>
	
	
	<tr>
		<td colspan="2">
			<input type="submit" value="입력" />
			<a href="list">목록</a>
		</td>
	</tr>

</form>
</table>



</body>
</html>