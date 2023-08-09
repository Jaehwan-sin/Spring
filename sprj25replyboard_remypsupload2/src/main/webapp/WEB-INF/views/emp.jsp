<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="javax.servlet.jsp.jstl.sql.Result"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.tech.db.DBcon"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>emp from DB</h2>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" />
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<%
String sql = "SELECT job, SUM(sal) total_salary "+
		               "FROM emp "+
                       "GROUP BY job";

//DB 접속
Connection con = DBcon.getConnection();
PreparedStatement pstmt = con.prepareStatement(sql);
ResultSet rs = pstmt.executeQuery();

JSONArray arr = new JSONArray();
while(rs.next()){
   JSONObject obj = new JSONObject();
   String job = rs.getString("job");
   String total_salary = rs.getString("total_salary");
   obj.put("job",job);
   obj.put("total_salary",total_salary);
   if(obj!=null)
      arr.add(obj);
}

rs.close();
pstmt.close();
con.close();

%>

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
jArray='<%=arr%>';

/*파싱*/
jArray=JSON.parse(jArray);
// alert(jArray[0].sum);

const ctx1=document.getElementById('myChart1').getContext('2d');
const ctx2=document.getElementById('myChart2').getContext('2d');
const ctx3=document.getElementById('myChart3').getContext('2d');

   const myChart1=new Chart(ctx1,{
      type:'line',
      data:{
         labels:[
             jArray[0].job,
             jArray[1].job,
             jArray[2].job,
             jArray[3].job,
             jArray[4].job],
         datasets:[{
            label:'# emp job for salery sum',
            data:[
                jArray[0].total_salary,
                jArray[1].total_salary,
                jArray[2].total_salary,
                jArray[3].total_salary,
                jArray[4].total_salary
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
          type:'bar',
          data:{
             labels:[
            	 jArray[0].job,
            	 jArray[1].job,
            	 jArray[2].job,
            	 jArray[3].job,
            	 jArray[4].job,],
             datasets:[{
                label:'# emp job for salery sum',
                data:[
                	 jArray[0].total_salary,
                     jArray[1].total_salary,
                     jArray[2].total_salary,
                     jArray[3].total_salary,
                     jArray[4].total_salary
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
             labels:[
            	 jArray[0].job,
                 jArray[1].job,
                 jArray[2].job,
                 jArray[3].job,
                 jArray[4].job],
             datasets:[{
                label:'# emp job for salery sum',
                data:[
                	 jArray[0].total_salary,
                     jArray[1].total_salary,
                     jArray[2].total_salary,
                     jArray[3].total_salary,
                     jArray[4].total_salary
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