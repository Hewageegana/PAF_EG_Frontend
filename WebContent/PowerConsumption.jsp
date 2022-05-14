<%@page import="model.PowerConsumption"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ElectroGrid Online System</title>
<style type="text/css">
		.consumptionForm{
			width: 800px;
			margin: 50px auto;

		}
		
			*{
			margin:0 ;
			padding: 0;
			box-sizing: border-box;
		}
		.body{
			background-color: #32312f;
			font-family: sans-serif;
		}
		.table-container{
			padding: 0 10%;
			margin: 40px auto 0;
		}
		.heading{
			font-size: 40px;
			text-align: center;
			color: #f1f1f1;
			margin-bottom: 40px;
		}
		.table{
			width: 100%;
			border-collapse: collapse;
			margin-top: 50px;
		}
		.table thead{
			background-color: #76030f;
		}
		.table thead tr th{
			font-size: 14px;
			font-weight: 600;
			letter-spacing: 0.35px;
			color: #ffff;
			opacity: 1;
			padding: 12px;
			vertical-align: top;
			border: 1px solid #dee2e685;

		}
		.table tbody tr td{
			font-size: 14px;
			font-weight: normal;
			letter-spacing: 0.35px;
			color: #f1f1f1;
			background-color: #3c3f44;
			padding: 8px;
			text-align: center;
			border:1px solid #dee2e685;
		}

		#btn-record{
			margin-left: 85%;
		}
	</style>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/PowerConsumption.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</head>
<body>
<div class="consumptionForm" >
	<div class="container">
<form class="row g-3"  id="consumptionForm" name="consumptionForm" method="post" action="PowerConsumptionAPI">
  <div class="col-md-2">
    <label for="userid" class="form-label" >User ID</label>
    <input type="number" class="form-control" id="userID" name="userID" placeholder="User ID" >
  </div>
  <div class="col-12">
    <label for="inputAddress" class="form-label">Account Number</label>
    <input type="number" class="form-control" id="account_Number" name="account_Number" placeholder="Account Number">
  </div>
  <div class="col-12">
    <label for="inputAddress" class="form-label">Customer Name</label>
    <input type="text" class="form-control" id="cus_name" name="cus_name" placeholder="Customer Name">
  </div>
 
 
  <div class="col-md-4">
    <label for="inputState" class="form-label">Units</label>
    <input type="number" name="units" id="units" name="units" class="form-control" placeholder="Units" /> 
      
  </div>
  <div class="col-md-2">
    <label for="inputZip" class="form-label">Days</label>
    <input type="text" class="form-control" id="days" name="days" placeholder="Days"/>
  </div><br/><br/>
   <div class="col-md-6">
    <label for="inputCity" class="form-label">Generated date</label>
    <input type="date" class="form-control" id="generated_date" name="generated_date" placeholder="Generated date"/>
  </div>
  
 <input id="btnSave" name="btnSave" type="button" value="Save"
			class="btn btn-primary"/> <input type="hidden"
				id="idpower_consumption" name="idpower_consumption" value=""/>
  
</form>
</div>
</div>
<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>
					<br> <br>
					<div id="divUserGrid" class="table-container" name="divUserGrid">
						<%
						PowerConsumption powerconsObj = new PowerConsumption();
											out.print(powerconsObj.readPwerConsumption());
						%>
					</div>
</body>
</html>