<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
<style>
            table {
                    text-align: center;
                    border-collapse: collapse;
            }
            tr:nth-child(odd) { /* 홀수행 색 다르게 짝수는 even */
                    background-color: #f7f9fc;
            }
            #title {
                    text-align: left;
            }
             .fa-solid {
                    color : #333;
                    size: 1.2pm;
            }
            .fa-solid:hover {
                    color : orange;
            }
</style>
</head>
<body>
<!-- 4 -->
<h2>list.jsp</h2>
<table width="800" border="1">
        <tr>
                <td>번호</td>
                <td>이름</td>
                <td>제목</td>
                <td>날짜</td>
                <td>조회수</td>
        </tr>    
<c:forEach items="${list }" var="dto"> <!-- 23 c:foreach문을 활용해 DB 테이블 값 불러오기 -->
        <tr>
                <td>${dto.brd_id }</td>
                <td>${dto.brd_name }</td>
                <td id="title"><!-- 64 indent 활용해 view처리 -->
                        <c:set value="${dto.brd_indent }" var="endindent" />
                        <c:forEach begin="1" end="${dto.brd_indent }" var="cnt"> <!-- begin,end가 1이면 한 바퀴 실행한다. -->
                                &nbsp;
                                <c:if test="${cnt eq endindent }">
                                        <img src="resources/img/arrow.png" alt="arrow.png" />
                                </c:if>
                        </c:forEach>
                        <a href="content_view?brd_id=${dto.brd_id }">${dto.brd_title }</a>
                </td> 
                <td>${dto.brd_date }</td>
                <td>${dto.brd_hit }</td>
        </tr>    
</c:forEach>
        <tr> <!-- 24 글쓰기 페이지 만들기 -->
                <td colspan="5"><a href="write_view">글쓰기</a></td>
        </tr>
</table>
totCnt : ${totRowcnt } <br />
현재 페이지 / 총 페이지 : ${searchVO.page } / ${searchVO.totPage }
<hr />
<form action="list" method="post">
		<c:if test="${searchVO.page>1 }">
		         <a href="list?page=1"><i class="fa-solid fa-angles-left"></i></a>
                 <a href="list?page=${searchVO.page-1 }"><i class="fa-solid fa-angle-left fa-beat"></i></a>
		</c:if>
		
		<c:forEach begin="${searchVO.pageStart }" end="${searchVO.pageEnd }" var="i">
		        <c:choose>
		                <c:when test="${i eq searchVO.page }">
		                            <span style="color:red; font-weight: bold;">${i }</span>
		                </c:when>
		                <c:otherwise>
		                             <a href="list?page=${i }&sk=${resk}&brd_title=${brd_title==true?'brd_title':''}&brd_content=${brd_content==true?'brd_content':''}"  id="page">${i }</a>
		                </c:otherwise>
		        </c:choose>
		</c:forEach>
		        
		<c:if test="${searchVO.page < searchVO.totPage}">
            <a href="list?page=${searchVO.page+1 }"><i class="fa-solid fa-angle-right fa-beat"></i></a>
            <a href="list?page=${searchVO.totPage }"><i class="fa-solid fa-angles-right"></i></a>
    </c:if>

<div>
        <c:choose>
                <c:when test="${brd_title }">
                        <input type="checkbox" name="searchType" value="brd_title" checked />        
                </c:when>
                <c:otherwise>
                        <input type="checkbox" name="searchType" value="brd_title" />
                </c:otherwise>
        </c:choose>제목
        <c:choose>
                <c:when test="${brd_content }">
                        <input type="checkbox" name="searchType" value="brd_content" checked />        
                </c:when>
                <c:otherwise>
                        <input type="checkbox" name="searchType" value="brd_content" />
                </c:otherwise>
        </c:choose>내용
        
        <input type="text" name="sk" value="${resk }" style="width : 150px;" maxlength="50" />
           <input type="submit" value="검색" />
</div>
</form>

</body>
</html>