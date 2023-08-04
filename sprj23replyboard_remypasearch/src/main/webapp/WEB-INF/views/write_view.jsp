<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/nstyle.css" />
</head>
<body>
<h2>write_view.jsp</h2>
<!-- 26 글쓰기 폼 작업 CSS까지 -->
<form action="write">
        <table>
                <tr>
                        <td class="left">이름</td>
                        <td><input type="text" name="bname" value="JH" /></td>
                </tr>
                <tr>
                        <td class="left">제목</td>
                        <td><input type="text" name="btitle" value="JHTITLE" /></td>
                </tr>
                <tr>
                        <td class="left">내용</td>
                        <td><textarea type="text" name="bcontent" rows="10"/></textarea></td>
                </tr>
                <tr>
                        <td colspan="2">
                                <input type="submit" value="작성" />
                                <button><a href="list">목록</a></button>
                        </td>
                </tr>
        </table>
</form>
</body>
</html>