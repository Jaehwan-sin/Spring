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
<h2>pzcontent_update.jsp</h2>
        <form action="pzmodify" method="post">
        <input type="hidden" name="pzid" value="${pzcontent_view.pzid }" />
                    <table>
                <tr>
                        <td class="left">번호</td>
                        <td>${pzcontent_view.pzid }</td> 
                </tr>
                <tr>
                        <td class="left">조회 수</td>
                        <td>${pzcontent_view.pzhit }</td>
                </tr>
                <tr>
                        <td class="left">이름</td>
                        <td>
                                <input type="text" name="pzname" value="${pzcontent_view.pzname }" size="50" />
                        </td>
                </tr>
                <tr>
                        <td class="left">제목</td>
                        <td>
                                <input type="text" name="pzsubj" value="${pzcontent_view.pzsubj }" size="50" />
                        </td>
                </tr>
                <tr>
                        <td class="left">내용</td>
                        <td>
                                <textarea name="pzcontent" cols="50" rows="10">${pzcontent_view.pzcontent }</textarea>
                        </td>
                </tr>
                <tr>
                        <td colspan="2" class="content_view_bottom">
                                <input type="submit" value="수정하기" /> &nbsp;&nbsp;
                                <button><a href="pzlist">목록</a></button>
                        </td>
                </tr>
        </table>
        </form>
</body>
</html>