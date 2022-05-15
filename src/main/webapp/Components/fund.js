$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});



// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateFundForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	// If valid------------------------
	$("#formFund").submit();
});


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) {
	$("#hidFundIDSave").val($(this).closest("tr").find('#hidFundIDUpdate').val());
	$("#fundType").val($(this).closest("tr").find('td:eq(0)').text());
	$("#funddescription").val($(this).closest("tr").find('td:eq(1)').text());
	$("#fundAmount").val($(this).closest("tr").find('td:eq(2)').text());
	$("#fundIssuedDate").val($(this).closest("tr").find('td:eq(3)').text());
	$("#fundIssuedBy").val($(this).closest("tr").find('td:eq(4)').text());
});


// Delete============================================
$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "FundAPI",
		type : "DELETE",
		data : "fundId=" + $(this).data("fundId"),
		dataType : "text",
		complete : function(response, status) {
			onFundDeleteComplete(response.responseText, status);
		}
	});
});

// CLIENT-MODEL================================================================
function validateFundForm() {
	// type
	if ($("#fundType").val().trim() == "") {
		return "Insert Type.";
	}
	// description
	if ($("#funddescription").val().trim() == "") {
		return "Insert Fund Description.";
	}
	// Amount-------------------------------
	if ($("#fundAmount").val().trim() == "") {
		return "Insert Fund Amount.";
	}
	// Issued Date-------------------------------
	if ($("#fundIssuedDate").val().trim() == "") {
		return "Insert Fund Issued Date.";
	}
	// Issued By-------------------------------
	if ($("#fundIssuedBy").val().trim() == "") {
		return "Insert Fund Issued By.";
	}
	
	return true;
}

$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateFundForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	// If valid------------------------
	var type = ($("#hidFundIDSave").val() == "") ? "POST" : "PUT";
	$.ajax(
		{
			url: "FundAPI",
			type: type,
			data: $("#formFund").serialize(),
			dataType: "text",
			complete: function(response, status) {
				onFundSaveComplete(response.responseText, status);
			}
		});
});


function onFundSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divFundGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidFundIDSave").val("");
	$("#formFund")[0].reset();
}



function onFundDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divFundGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

function onFundDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divFundGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

function onFundDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divFundGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}