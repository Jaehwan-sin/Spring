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
<h2>pzcontent_view.jsp</h2>
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
                        <td>${pzcontent_view.pzname }</td>
                </tr>
                <tr>
                        <td class="left">제목</td>
                        <td>${pzcontent_view.pzsubj }</td>
                </tr>
                <tr>
                        <td class="left">내용</td>
                        <td>${pzcontent_view.pzcontent }</td>
                </tr>
                <tr>
                        <td colspan="2" class="content_view_bottom">
                           <a href="PizzaUpdate?pzid=${pzcontent_view.pzid }">수정</a> &nbsp;&nbsp;
                           <a href="pzlist">목록</a> &nbsp;&nbsp;
                           <a href="PizzaDelete?pzid=${pzcontent_view.pzid }">삭제</a> &nbsp;&nbsp;  
                          <a href="pzreply_view?pzid=${pzcontent_view.pzid }">답변</a>   
                        </td>
                </tr>
        </table>
</body>
</html>