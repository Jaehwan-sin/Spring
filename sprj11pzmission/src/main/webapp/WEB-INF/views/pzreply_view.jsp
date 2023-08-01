<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>pzreply_view.jsp</h2>
        <form action="pzreply" method="post">
                <input type="hidden" name="pzid" value="${pzreply_view.pzid }" />
                <input type="hidden" name="pzgroup" value="${pzreply_view.pzgroup }" />
                <input type="hidden" name="pzstep" value="${pzreply_view.pzstep }" />
                <input type="hidden" name="pzintent" value="${pzreply_view.pzintent }" />
                <table>
                <tr>
                        <td class="left">번호</td>
                        <td>${pzreply_view.pzid }</td> 
                </tr>
                <tr>
                        <td class="left">조회 수</td>
                        <td>${pzreply_view.pzhit }</td>
                </tr>
                <tr>
                        <td class="left">이름</td>
                        <td>
                                <input type="text" name="pzname" value="${pzreply_view.pzname }" size="50" />
                        </td>
                </tr>
                <tr>
                        <td class="left">제목</td>
                        <td>
                                <input type="text" name="pzsubj" value="${pzreply_view.pzsubj }" size="50" />
                        </td>
                </tr>
                <tr>
                        <td class="left">내용</td>
                        <td>
                                <textarea name="pzcontent" cols="50" rows="10">${pzreply_view.pzcontent }</textarea>
                        </td>
                </tr>
                <tr>
                        <td colspan="2" class="content_view_bottom">
	                          <input type="submit"  value="reply"/>
	                          <a href="pzlist">목록</a>
                        </td>
                </tr>
        </table>
        </form>
</body>
</html>