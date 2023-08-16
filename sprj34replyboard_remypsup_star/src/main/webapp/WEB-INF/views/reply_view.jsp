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
        <input type="hidden" name="bid" value="${reply_view.bid }" />
        <input type="hidden" name="bgroup" value="${reply_view.bgroup }" />
        <input type="hidden" name="bstep" value="${reply_view.bstep }" />
        <input type="hidden" name="bindent" value="${reply_view.bindent }" />
                <table>
                <tr>
                        <td class="left">번호</td>
                        <td>${reply_view.bid }</td> <!-- 59 값을 reply_view에서 받아온다 -->
                </tr>
                <tr>
                        <td class="left">조회 수</td>
                        <td>${reply_view.bhit }</td>
                </tr>
                <tr>
                        <td class="left">이름</td>
                        <td>
                                <input type="text" name="bname" value="${reply_view.bname }" size="50" />
                        </td>
                </tr>
                <tr>
                        <td class="left">제목</td>
                        <td>
                                <input type="text" name="btitle" value="${reply_view.btitle }" size="50" />
                        </td>
                </tr>
                <tr>
                        <td class="left">내용</td>
                        <td>
                                <textarea name="bcontent" cols="50" rows="10">${reply_view.bcontent }</textarea>
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