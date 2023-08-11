<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" />
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
<h2>stuheight.jsp</h2>

<div class="container">
   <div class="row">
        <div class="col-md-6">
            <canvas width="300" height="300" id="myChart1"/>
        </div>
        <div class="col-md-6">
            <canvas width="300" height="300" id="myChart2"/>
        </div>
        <div class="col-md-6">
            <canvas width="300" height="300" id="myChart3"/>
        </div>
   </div>
</div>

<script>
var jArray=new Array();
jArray='${arr}';

/*파싱*/
jArray=JSON.parse(jArray);
// alert(jArray[0].sumheight);
 
const ctx1=document.getElementById('myChart1').getContext('2d');
const ctx2=document.getElementById('myChart2').getContext('2d');
const ctx3=document.getElementById('myChart3').getContext('2d');

   const myChart1=new Chart(ctx1,{
      type:'line',
      data:{
         labels:[
        	 jArray[0].grade,
        	 jArray[1].grade,
        	 jArray[2].grade,
        	 jArray[3].grade],
         datasets:[{
            label:'# 학생들의 키 합계',
            data:[
            	jArray[0].sumheight,
            	jArray[1].sumheight,
            	jArray[2].sumheight,
            	jArray[3].sumheight,
            	],
            backgroundColor:[
               'rgba(255,99,132,1.0)',
               'rgba(55,99,16,0.2)',
               'rgba(10,99,13,0.2)',
               '#0000ff'
            ],
            borderColor:[
               'rgba(255,99,9,1)',
               'rgba(55,99,9,1)',
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

   const myChart2=new Chart(ctx2,{
	      type:'bar',
	      data:{
	         labels:[
	        	 jArray[0].grade,
	        	 jArray[1].grade,
	        	 jArray[2].grade,
	        	 jArray[3].grade],
	         datasets:[{
	            label:'# 학생들의 키 합계',
	            data:[
	            	jArray[0].sumheight,
	            	jArray[1].sumheight,
	            	jArray[2].sumheight,
	            	jArray[3].sumheight,
	            	],
	            backgroundColor:[
	               'rgba(255,99,132,1.0)',
	               'rgba(55,99,16,0.2)',
	               'rgba(10,99,13,0.2)',
	               '#0000ff'
	            ],
	            borderColor:[
	               'rgba(255,99,9,1)',
	               'rgba(55,99,9,1)',
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

   
   const myChart3=new Chart(ctx3,{
	      type:'pie',
	      data:{
	         labels:[
	        	 jArray[0].grade,
	        	 jArray[1].grade,
	        	 jArray[2].grade,
	        	 jArray[3].grade],
	         datasets:[{
	            label:'# 학생들의 키 합계',
	            data:[
	            	jArray[0].sumheight,
	            	jArray[1].sumheight,
	            	jArray[2].sumheight,
	            	jArray[3].sumheight,
	                ],
	            backgroundColor:[
	               'rgba(255,99,132,1.0)',
	               'rgba(55,99,16,0.2)',
	               'rgba(10,99,13,0.2)',
	               '#0000ff'
	            ],
	            borderColor:[
	               'rgba(255,99,9,1)',
	               'rgba(55,99,9,1)',
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