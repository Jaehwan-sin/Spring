<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>graph2.jsp</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" />
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

</head>
<body>
<h1>graph2</h1>
<div class="container">
   <div class="row">
        <div class="col-md-6">
            <canvas id="myChart1"/>
        </div>
        <div class="col-md-6">
            <canvas id="myChart2"/>
        </div>
        <div class="col-md-6">
            <canvas width="400" height="400" id="myChart3"/>
        </div>
   </div>
</div>

<script>
const ctx1=document.getElementById('myChart1').getContext('2d');
const ctx2=document.getElementById('myChart2').getContext('2d');
const ctx3=document.getElementById('myChart3').getContext('2d');
   const myChart1=new Chart(ctx1,{
      type:'bar',
      data:{
         labels:['aa1','aa2','aa3','aa4','aa5'],
         datasets:[{
            label:'# 청바지매출액',
            data:[
            	10,
            	100,
            	100,
            	200,
            	1000
            	],
            backgroundColor:[
               'rgba(255,99,132,1.0)',
               'rgba(55,99,16,0.2)',
               'rgba(10,99,13,0.2)',
               'rgba(2,99,132,0.2)',
               '#0000ff'
            ],
            borderColor:[
               'rgba(255,99,9,1)',
               'rgba(55,99,9,1)',
               'rgba(55,255,9,1)',
               'rgba(255,99,9,1)',
               'rgba(55,255,9,1)'
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

   const myChart2=new Chart(ctx2,{  
	      type:'line',
	      data:{
	         labels:['aa1','aa2','aa3','aa4','aa5'],
	         datasets:[{
	            label:'# 청바지매출액',
	            data:[
	                50,
	                20,
	                300,
	                150,
	                1000
	                ],
	            backgroundColor:[
	               'rgba(255,99,132,1.0)',
	               'rgba(55,99,16,0.2)',
	               'rgba(10,99,13,0.2)',
	               'rgba(2,99,132,0.2)',
	               '#0000ff'
	            ],
	            borderColor:[
	               'rgba(255,99,9,1)',
	               'rgba(55,99,9,1)',
	               'rgba(55,255,9,1)',
	               'rgba(255,99,9,1)',
	               'rgba(55,255,9,1)'
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

   
   const myChart3=new Chart(ctx3,{  
       type:'doughnut',
       data:{
          labels:['aa1','aa2','aa3','aa4','aa5'],
          datasets:[{
             label:'# 청바지매출액',
             data:[
                 70,
                 10,
                 170,
                 150,
                 300
                 ],
             backgroundColor:[
                'rgba(255,99,132,1.0)',
                'rgba(55,99,16,0.2)',
                'rgba(10,99,13,0.2)',
                'rgba(2,99,132,0.2)',
                '#0000ff'
             ],
             borderColor:[
                'rgba(255,99,9,1)',
                'rgba(55,99,9,1)',
                'rgba(55,255,9,1)',
                'rgba(255,99,9,1)',
                'rgba(55,255,9,1)'
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