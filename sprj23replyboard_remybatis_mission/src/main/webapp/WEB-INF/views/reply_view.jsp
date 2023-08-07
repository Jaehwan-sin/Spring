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
<!-- 54 reply_view 페이지 만들기 -->
<h2>reply_view.jsp</h2>
        <form action="reply" method="post">
        <!-- 60 전달 할 값 hidden 처리 -->
        <input type="hidden" name="brd_id" value="${reply_view.brd_id }" />
        <input type="hidden" name="brd_group" value="${reply_view.brd_group }" />
        <input type="hidden" name="brd_step" value="${reply_view.brd_step }" />
        <input type="hidden" name="brd_indent" value="${reply_view.brd_indent }" />
                <table>
                <tr>
                        <td class="left">번호</td>
                        <td>${reply_view.brd_id }</td> <!-- 59 값을 reply_view에서 받아온다 -->
                </tr>
                <tr>
                        <td class="left">조회 수</td>
                        <td>${reply_view.brd_hit }</td>
                </tr>
                <tr>
                        <td class="left">이름</td>
                        <td>
                                <input type="text" name="brd_name" value="${reply_view.brd_name }" size="50" />
                        </td>
                </tr>
                <tr>
                        <td class="left">제목</td>
                        <td>
                                <input type="text" name="brd_title" value="${reply_view.brd_title }" size="50" />
                        </td>
                </tr>
                <tr>
                        <td class="left">내용</td>
                        <td>
                                <textarea name="brd_content" cols="50" rows="10">${reply_view.brd_content }</textarea>
                        </td>
                </tr>
                <tr>
                        <td colspan="2" id="contentview_bottom">
                                    <input type="submit" value="reply" />
                                    <button><a href="list">목록</a></button>
                        </td>
                </tr>
        </table>
        </form>
</body>
</html>