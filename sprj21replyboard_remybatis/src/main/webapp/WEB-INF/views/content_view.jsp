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
<!-- 32 글내용 페이지 폼 만들기 -->
<h2>content_view.jsp</h2>
        <table>
                <tr>
                        <td class="left">번호</td>
                        <td>${content_view.bid }</td> <!-- 37 content_view에 담긴 객체 값을 불러온다. -->
                </tr>
                <tr>
                        <td class="left">조회 수</td>
                        <td>${content_view.bhit }</td>
                </tr>
                <tr>
                        <td class="left">이름</td>
                        <td>${content_view.bname }</td>
                </tr>
                <tr>
                        <td class="left">제목</td>
                        <td>${content_view.btitle }</td>
                </tr>
                <tr>
                        <td class="left">내용</td>
                        <td>${content_view.bcontent }</td>
                </tr>
                <tr>
                        <td colspan="2" id="contentview_bottom">
                                  <a href="content_update?bid=${content_view.bid }"> 수정 </a> &nbsp; &nbsp;<!-- 38~45 수정작업 -->
                                  <a href="list">목록 </a>&nbsp; &nbsp;
                                  <a href="delete?bid=${content_view.bid }"> 삭제 </a>&nbsp; &nbsp;<!-- 46~51 삭제작업 -->
                                  <a href="reply_view?bid=${content_view.bid }">답변</a>&nbsp; &nbsp; <!-- 52~67 답변작업 -->
                        </td>
                </tr>
        </table>
</body>
</html>