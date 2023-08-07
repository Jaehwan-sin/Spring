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
<!-- 40 수정 페이지 만들기 -->
<h2>content_update.jsp</h2>
<form action="modify" method="post">
<!-- 42 보내야 할 값 코드 수정하기 --> 
<input type="hidden" name="bid" value="${content_view.brd_id }" />
        <table>
                <tr>
                        <td class="left">번호</td>
                        <td>${content_view.brd_id }</td> 
                </tr>
                <tr>
                        <td class="left">조회 수</td>
                        <td>${content_view.brd_hit }</td>
                </tr>
                <tr>
                        <td class="left">이름</td>
                        <td>
                                <input type="text" name="bname" value="${content_view.brd_name }" size="50" />
                        </td>
                </tr>
                <tr>
                        <td class="left">제목</td>
                        <td>
                                <input type="text" name="btitle" value="${content_view.brd_title }" size="50" />
                        </td>
                </tr>
                <tr>
                        <td class="left">내용</td>
                        <td>
                                <textarea name="bcontent" cols="50" rows="10">${content_view.brd_content }</textarea>
                        </td>
                </tr>
                <tr>
                        <td colspan="2" id="contentview_bottom">
                                  <input type="submit" value="수정하기" /> &nbsp;&nbsp;
                                  <button><a href="list">목록</a></button>   
                        </td>
                </tr>
        </table>       
</form>
</body>
</html>