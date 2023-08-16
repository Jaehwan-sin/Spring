<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path=request.getContextPath();

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>


   <script>
   function redeptlist(target) {
      // alert("target : "+target.value);
      var htmltxt="";
      //var pointvalue=document.getElementById("point").value;
      $('input[name=point]').attr('value',target.value);
      
   
   }
   </script>
   
   
   <script src="resources/js/scriptjsp.js"></script>
   
   <script type="text/javascript">
   function getvalue(target) {
      // alert(target.value);
      
   } 

   </script>
   
    
    <link rel="stylesheet" href="resources/js/style.css">

</head>

<body>
<div> ${point }</div>
<div id="data"></div>
    <span class="star">
        ★★★★★
        <span>★★★★★</span>
        <input type="range" onclick="redeptlist(this);"  oninput="drawStar(this)" value="1" step="1" min="0" max="10">
      </span>
       
      <form action="viewwrite">
	      <input type="hidden" name="point" value="" size="20" />
	      리뷰 : <input type="text" name="review" size="20" /> <br />
	      <button type="submit">review write</button>
      </form>
      
</body>
</html>
