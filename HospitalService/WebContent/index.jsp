<%@page import="model.Hospital"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hospital Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.2.1.min.js"></script> 
<script src="Components/hospital.js"></script> 
</head>
<body>
<div class="container">
 <div class="row"> <div class="col-6">  
 <h1>Hospital Management </h1> 
 <form id="formHospital" name="formHospital" >  
 
 Hospital Name:   
 <input id="hospitalName" name="hospitalName" type="text"     class="form-control form-control-sm"> 
 
  <br> Hospital Phone Number :   
  <input id="hospitalphoneNo" name="hospitalphoneNo" type="text"        class="form-control form-control-sm"> 
 
  <br> Hospital Address :   
  <input id="hospitalAddress" name="hospitalAddress" type="text"        class="form-control form-control-sm"> 
 
  <br> Hospital Email:   
  <input id="hospitalEmail" name="hospitalEmail" type="text"        class="form-control form-control-sm"> 
 
 
 
  <br>   
  <input id="btnSave" name="btnSave" type="button" value="Save"      
  class="btn btn-primary">   <input type="hidden" id="hidHospitalIDSave"         
  name="hidHospitalIDSave" value="">  </form> 
 
 <div id="alertSuccess" class="alert alert-success"></div>  
 <div id="alertError" class="alert alert-danger"></div> 
 
 <br>  <div id="divHospitalsGrid">   
 <%    
 	Hospital PharObj = new Hospital();    
 	out.print(PharObj.readHospital());   
 %>  </div>
 </div>
 </div>
 </div>

</body>
</html>
 

 

 

 
 