$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});


//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
 // Form validation-------------------
var status = validateInsertForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }

 var type = ($("#idpower_consumption").val() == "") ? "POST" : "PUT";


$.ajax(
		{
		 url : "PowerConsumptionAPI",
		 type : type,
		data: $("#consumptionForm").serialize(),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onConsumptionSaveComplete(response.responseText, status);
		 }
		});

});

function onConsumptionSaveComplete(response, status)
{
if (status == "success")
 {
	var resultSet = JSON.parse(response);
	if (resultSet.status.trim() == "success")
	{
		$("#alertSuccess").text("Successfully saved.");
		$("#alertSuccess").show();
		
		$("#divUserGrid").html(resultSet.data);
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
		$("#idpower_consumption").val("");
		$("#consumptionForm")[0].reset();
}


//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 $("#idpower_consumption").val($(this).closest("tr").find('#hididUpdate').val());
 $("#userID").val($(this).closest("tr").find('td:eq(0)').text());
 $("#account_Number").val($(this).closest("tr").find('td:eq(1)').text());
 $("#cus_name").val($(this).closest("tr").find('td:eq(2)').text());
 $("#units").val($(this).closest("tr").find('td:eq(3)').text());
 $("#days").val($(this).closest("tr").find('td:eq(4)').text());
 $("#generated_date").val($(this).closest("tr").find('td:eq(5)').text());

});

$(document).on("click", ".btnRemove", function(event)
		{
		 $.ajax(
		 {
		 url : "PowerConsumptionAPI",
		 type : "DELETE",
		 data : "idpower_consumption=" + $(this).data("idpower_consumption"),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onConsumptionDeleteComplete(response.responseText, status);
		 }
		 });
		});

function onConsumptionDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divUserGrid").html(resultSet.data);
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

//CLIENTMODEL=========================================================================
function validateInsertForm()
{
	//cus_name
if ($("#userID").val().trim() == "")
{
return "Insert User ID.";
}

//cus_address
if ($("#account_Number").val().trim() == "")
{
return "Insert Account No.";
}

//cus_phone_no
if ($("#cus_name").val().trim() == "")
{
return "Insert Customer Name.";
}

//cus_nic
if ($("#units").val().trim() == "")
{
return "Insert Units.";
}

//username
if ($("#days").val().trim() == "")
{
return "Insert Days.";
}

//password
if ($("#generated_date").val().trim() == "")
{
return "Insert Geerate Date.";
}



return true;
}