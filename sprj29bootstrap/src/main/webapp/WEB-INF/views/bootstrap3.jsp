<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<script>
function lengthdiv() {
	// alert("lengthdiv")
	var d1width = document.getElementById("d1").clientWidth;
	// alert(d1width);
	var y = document.getElementById("window");
	y.innerHTML = "스크린 폭 : "+screen.width+", 창 폭 : "+window.innerWidth+",div 폭 : "+d1width;
}
</script>

<style>
    #d1{
         background-color: green;
    }
    #d2{
         background-color: blue;
    }
    #d3{
         background-color: red;
    }
    #d1,#d2,#d3 {
        color : white;
    }
</style>
</head>
<body onresize="lengthdiv();">
<h3>bootstrap3</h3>
<div class="jumbotron text-center">
    <h1>My First Bootstrap Page</h1>
    <p>Resize this responsive page to see the effect!</p>
</div>
<div class="container">
<!-- https://getbootstrap.com/docs/5.3/layout/grid/ col-sm-4 size 참고 -->
    <div class="row">
        <div id="d1" class="col-xs-12 col-sm-6 col-md-4"> col-xs-12 col-sm-6 11</div>
        <div id="d2" class="col-xs-12 col-sm-6 col-md-4"> col-xs-12 col-sm-6 12</div>
        <div id="d3" class="col-xs-12 col-sm-6 col-md-4"> col-xs-12 col-sm-6 13</div>
    </div>
</div>
<p align="center" style="font-size: 20px" id="window">size</p>

</body>
</html>