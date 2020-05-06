$(document).ready(function() 
		{  
	if ($("#alertSuccess").text().trim() == "")  
    {   
		$("#alertSuccess").hide();  
     }  
	     $("#alertError").hide(); 
	  
});

$(document).on("click", "#btnSave", function(event) 
		{  
			$("#alertSuccess").text("");  
			$("#alertSuccess").hide();  
			$("#alertError").text("");  
			$("#alertError").hide(); 
			
			
			var status = validatePharmacyForm();  
			if (status != true)  
			{   
				$("#alertError").text(status);   
				$("#alertError").show();   
				return;  
			} 
			
			var type = ($("#hidHospitalIDSave").val() == "") ? "POST" : "PUT"; 
			
			$.ajax( 
			{  
				url : "HospitalsAPI",  
				type : type,  
				data : $("#formHospital").serialize(),  
				dataType : "text",  
				complete : function(response, status)  
				{   
					onHospitalSaveComplete(response.responseText, status);  
					
				} 
			
		}); 
}); 
		
function onHospitalSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 

			$("#divHospitalsGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

		} else if (status == "error")  
		{   
			$("#alertError").text("Error while saving.");   
			$("#alertError").show();  
		} else  
		{   
			$("#alertError").text("Unknown error while saving..");   
			$("#alertError").show();  
		} 

		$("#hidHospitalIDSave").val("");  
		$("#formHospital")[0].reset(); 
		
}

$(document).on("click", ".btnRemove", function(event) 
{  
	$.ajax(  
	{   
		url : "HospitalsAPI",   
		type : "DELETE",   
		data : "hospitalID=" + $(this).data("id"),   
		dataType : "text",   
		complete : function(response, status)   
		{    
			onHospitalDeleteComplete(response.responseText, status);   
		}  
	}); 
}); 


function onHospitalDeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully deleted.");    
			$("#alertSuccess").show(); 

			$("#divHospitalsGrid").html(resultSet.data);   
			} else if (resultSet.status.trim() == "error")   
			{    
				$("#alertError").text(resultSet.data);    
				$("#alertError").show();   
			} 

			} else if (status == "error")  
			{   
				$("#alertError").text("Error while deleting.");   
				$("#alertError").show();  
			} else  
			{   
				$("#alertError").text("Unknown error while deleting..");   
				$("#alertError").show();  
			} 
	} 

$(document).on("click", ".btnUpdate", function(event) 
{     
	$("#hidHospitalIDSave").val($(this).closest("tr").find('#hidHospitalIDUpdate').val());     
	$("#hospitalName").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#hospitalphoneNo").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#hospitalAddress").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#hospitalEmail").val($(this).closest("tr").find('td:eq(3)').text()); 
	 

}); 


function validateHospitalForm() 
{  
	// CODE  
	if ($("#hospitalName").val().trim() == "")  
	{   
		return "Insert Name of the hospital.";   
	}

	 
	 // Addres 
	if ($("#hospitalAddress").val().trim() == "")  
	{   
		return "Insert Address of the Hospital.";  
	}
	
	if ($("#hospitalphoneNo").val().trim() == "")  
	{   
		return "Insert Telephone No.";  
	} 
	 
	 // is numerical value  
	var tmpPhone = $("#hospitalphoneNo").val().trim();  
	if (!$.isNumeric(tmpPhone))  
	{   
		return "Insert a numerical value for Phone Number.";  
	} 
	 

	
	 return true;
	
}



