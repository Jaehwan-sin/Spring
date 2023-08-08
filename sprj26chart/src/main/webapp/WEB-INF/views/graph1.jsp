<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>graph1.jsp</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" />
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

</head>
<body>
<h1>graph1</h1>
<div class="container">
   <div class="col-md-6">
      <canvas id="myChart"></canvas>
   </div>
</div>

<script>
   const ctx=document.getElementById('myChart').getContext('2d');
   const myChart=new Chart(ctx,{
      type:'bar',
      data:{
         labels:['Red','Blue','Yellow','Green','Purple','Orange'],
         datasets:[{
            label:'# Votes',
            data:[12,9,3,50,2,3],
            backgroundColor:[ 
               'rgba(255,99,132,1.0)',
               'rgba(55,99,16,0.2)',
               'rgba(10,99,13,0.2)',
               'rgba(255,99,255,1.0)',
               'rgba(2,99,132,0.2)',
               '#0000ff'   
            ],
            borderColor:[
               'rgba(255,99,9,1)',
               'rgba(55,99,9,1)',
               'rgba(55,255,9,1)',
               'rgba(255,99,9,1)',
               'rgba(55,255,9,1)',
               'rgba(255,99,9,1)'
            ],
            borderWidth:3
         }]
      },
      options:{
         scales:{
            y:{
               beginAtZero:true
            }
         }
      }
   });
</script>

</body>
</html>