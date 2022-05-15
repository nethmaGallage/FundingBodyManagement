<%@page import="com.Fund"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script src="Components/jquery-3.6.0.js" type="text/javascript"></script>
<script src="Components/fund.js" type="text/javascript"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
    <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
<title>Funding Bodies</title>
</head>
<body>
<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Funding Bodies</h1>
				
				<form id="formFund" name="formFund" action="">
						
					Fund Type: <input id="fundType" name="fundType" type="text" class="form-control form-control-sm"> <br> 
						
					Fund Description: <input id="funddescription" name="funddescription" type="text" class="form-control form-control-sm"> <br> 
						
					Fund Amount: <input id="fundAmount" name="fundAmount" type="text" class="form-control form-control-sm"> <br>
					
					Fund Issued Date: <input id="fundIssuedDate" width="276" name="fundIssuedDate" type="text" class="form-control form-control-sm"> <br>
					
					Fund Issued By: <input id="fundIssuedBy" name="fundIssuedBy" type="text" class="form-control form-control-sm"> <br>
					 
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> 
					<input type="hidden" id="hidFundIDSave" name="hidFundIDSave" value="">
					
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divFundGrid">
					<%
					Fund fundObj = new Fund();
					out.print(fundObj.readFund());
					%>
				</div> 
			</div>
		</div>
	</div>
	
	<script>
        $('#fundIssuedDate').datepicker({
            uiLibrary: 'bootstrap4'
        });
        
    </script>
</body>
</html>