/*Auto complete for Exclusion list */
$(document).ready(function(){
	$('#errorCodeLocate').blur(function() {
		$('#errorCodeLocate').val($('#errorCodeLocate').val().toUpperCase());	
	});
	$("#errorCodeLocate").autocomplete({ 
		source: function(request, response) {
					$.ajax({
						url: "../referencedata/fetchMatchingErrorCodesForAutoComplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term),
						success: function(data) {
						cache: false,
						response(data.rows);
						}
					});
				}
	})
});

/*Auto complete for Primary Values */
$(document).ready(function(){
	$('#primeValueAuto').blur(function() {
		$('#primeValueAuto').val($('#primeValueAuto').val().toUpperCase());	
	});
	$("#primeValueAuto").autocomplete({ 
		source: function(request, response) {
					$.ajax({
						url: document.getElementById('hiddenURLForPrimary').value,
						dataType: "json",
						type:"POST",
						data: "key="+request.term+"&name="+document.getElementById('hiddenURLForType').value,
						success: function(data) {
						cache: false,
						response(data.rows);
						}
					});
				}
	})
});



  
/*Auto complete for Secondary Values */
$(document).ready(function(){
	$('#secValueAuto').blur(function() {
		$('#secValueAuto').val($('#secValueAuto').val().toUpperCase());	
	});
	$("#secValueAuto").autocomplete({ 
		source: function(request, response) {
					$.ajax({
						url: document.getElementById('hiddenURLForPrimary').value,
						dataType: "json",
						type:"POST",
						data: "key="+request.term+"&name="+document.getElementById('hiddenURLForType').value,
						success: function(data) {
						cache: false,
						response(data.rows);
						}
					});
				}
	})
 });
 
function clearHighLight() {
		var table = document.getElementById("exclusionResultTab");
		for (var i = 0, row; row = table.rows[i]; i ++) {
				if (i%2 != 0) {
					document.getElementById(row.id).style.background = "";
				}
		}
}

function sniffer() {
		var ie6Flag = ''
	   if ($.browser.msie) {
	   		if ($.trim($.browser.version) == '6.0') {
	   			ie6Flag = 'ie6'
	   		}
	   }
	   if (screen.height==1024) {
		   	if (ie6Flag == 'ie6') {
			   	  document.getElementById('exclusionEditContainer').style.height = "560px";
			      document.getElementById('exclusionSectionTwo').style.height = "252px";
			     document.getElementById('exclusionContentSectionTwo').style.height = "226px";
		   	} else {
			      document.getElementById('exclusionEditContainer').style.height = "560px";
			      document.getElementById('exclusionSectionTwo').style.height = "252px";
			     document.getElementById('exclusionContentSectionTwo').style.height = "226px";
			}
	   } else if(screen.height==960) {
	  	 	if (ie6Flag == 'ie6') {
			      document.getElementById("exclusionEditContainer").style.height= "488px";
			      document.getElementById('exclusionSectionTwo').style.height = "173px";
			      document.getElementById('exclusionContentSectionTwo').style.height = "144px";
			 } else {
			 	  document.getElementById("exclusionEditContainer").style.height= "488px";
			      document.getElementById('exclusionSectionTwo').style.height = "173px";
			      document.getElementById('exclusionContentSectionTwo').style.height = "144px";
			 }
	   } else if(screen.height==864) {
	  		 if (ie6Flag == 'ie6') {
			      document.getElementById("exclusionEditContainer").style.height= "455px";
			      document.getElementById('exclusionSectionTwo').style.height = "159px";
			      document.getElementById('exclusionContentSectionTwo').style.height = "131px";
			 } else {
			 	  document.getElementById("exclusionEditContainer").style.height= "455px";
			      document.getElementById('exclusionSectionTwo').style.height = "159px";
			      document.getElementById('exclusionContentSectionTwo').style.height = "131px";
			 }
	   } else if(screen.height==720) {
		   if (ie6Flag == 'ie6') {
			      document.getElementById("exclusionEditContainer").style.height= "335px";
			      document.getElementById('exclusionSectionTwo').style.height = "78px";
			      document.getElementById('exclusionContentSectionTwo').style.height = "55px";
			} else {
				  document.getElementById("exclusionEditContainer").style.height= "335px";
			      document.getElementById('exclusionSectionTwo').style.height = "78px";
			      document.getElementById('exclusionContentSectionTwo').style.height = "55px";
			}
	   } else if(screen.height==768) {
	  		 if (ie6Flag == 'ie6') {
			      document.getElementById("exclusionEditContainer").style.height= "377 px";
			      document.getElementById('exclusionSectionTwo').style.height = "80px";
			      document.getElementById('exclusionContentSectionTwo').style.height = "55px";
			 } else {
			 	 document.getElementById("exclusionEditContainer").style.height= "377 px";
			      document.getElementById('exclusionSectionTwo').style.height = "80px";
			      document.getElementById('exclusionContentSectionTwo').style.height = "55px";
			 }
	   } else if(screen.height==600) {
	   	 if (ie6Flag == 'ie6') {
		      document.getElementById("exclusionEditContainer").style.height= "291 px";
		      document.getElementById('exclusionSectionTwo').style.height = "50px";
		      document.getElementById('exclusionContentSectionTwo').style.height = "33px";
		 } else {
		 	  document.getElementById("exclusionEditContainer").style.height= "291 px";
		      document.getElementById('exclusionSectionTwo').style.height = "50px";
		      document.getElementById('exclusionContentSectionTwo').style.height = "33px";
		 }
	   } else {
	   		if (screen.height > 768) {
		   		var setPixMain = (screen.height * 0.49) + "px";
		   		var setPixSub = ((screen.height * 0.49) - 292) + "px"
		   		var setPixSubTask = ((screen.height * 0.49) - 313) + "px"
		   		 document.getElementById("exclusionEditContainer").style.height= setPixMain;
		   		 document.getElementById("exclusionSectionTwo").style.height= setPixSub;
		   		document.getElementById("exclusionContentSectionTwo").style.height= setPixSubTask;
		   	} else {
				  document.getElementById("exclusionEditContainer").style.height= "330px";
			      document.getElementById('exclusionSectionTwo').style.height = "73px";
			      document.getElementById('exclusionContentSectionTwo').style.height = "50px";
		   	}
	   }
}


function editExclusion(exclusionId, primaryExclusion, primaryVals, secExclusion, secVals) {
	clearHighLight();
	$('#exclusionIDForSave').val($.trim(exclusionId));
	$('#criteriaSelectOne').val($.trim(primaryExclusion));
	$('#valuesTextOne').val($.trim(primaryVals));
	enableDisableSecondaryFields(primaryExclusion);
	if  (!(secExclusion == null || secExclusion == "" || secExclusion == 'null')) {
		$('#criteriaSelectTwo').val($.trim(secExclusion));
		$('#valuesTextTwo').val($.trim(secVals));
	} else {
		$('#criteriaSelectTwo').val('');
		$('#valuesTextTwo').val('');
	}
	document.getElementById(exclusionId).style.background = "#FCF6CF";
	setAutoCompleteFieldsCategoryBased(primaryExclusion, secExclusion);
}



function enableDisableSecondaryFields(primaryExclusion) {
	if (primaryExclusion == 'CONTRACT' ||  primaryExclusion == 'PRODUCT' || primaryExclusion == 'PRODUCTLINE') {
		enabler = 'enable';
	} else {
		enabler = 'disable';
	}
	if (enabler == 'enable') {
	          $("#valuesTextTwo"). removeClass("exclusionsTextDisabled");
             $("#valuesTextTwo").attr('disabled', false);
             $("#valuesTextTwo"). addClass("exclusionsText");
             $("#criteriaSelectTwo"). removeClass("dropdown200Disabled");
             $("#criteriaSelectTwo").attr('disabled', false);
             $("#criteriaSelectTwo"). addClass("dropdown200");
             $("#secValue"). removeClass("inputbox200ExcludeDisabled");
             $("#secValue").attr('disabled', false);
             $("#secValue"). addClass("inputbox200Exclude");
             
              $("#addSec").attr('disabled', false);
              
             $("#secValueAuto"). removeClass("inputbox200ExcludeDisabled");
             $("#secValueAuto").attr('disabled', false);
             $("#secValueAuto"). addClass("inputbox200Exclude");
	} else {
             $('#valuesTextTwo').val('');      
	          $("#valuesTextTwo"). removeClass("exclusionsText");
             $("#valuesTextTwo").attr('disabled', true);
             $("#valuesTextTwo"). addClass("exclusionsTextDisabled");
             $('#criteriaSelectTwo').val('');   
              $("#criteriaSelectTwo"). removeClass("dropdown200");
             $("#criteriaSelectTwo").attr('disabled', true);
             $("#criteriaSelectTwo"). addClass("dropdown200Disabled");
             $("#addSec").attr('disabled', true);
              $("#secValue"). removeClass("inputbox200Exclude");
             $("#secValue").attr('disabled', true);
             $("#secValue"). addClass("inputbox200ExcludeDisabled");
             $('#secValue').val('');   
             
             $("#secValueAuto").attr('disabled', true);
             $("#secValueAuto"). addClass("inputbox200ExcludeDisabled");
             $('#secValueAuto').val('');   
	}
}

var currentTextSelectionPrimary;
var currentTextSelectionSec;

function setAutoCompleteFieldsCategoryBased(primaryExclusion, secExclusion) {
	var field;
	if (primaryExclusion == 'VARIABLE' || primaryExclusion == 'SPS' || primaryExclusion == 'HEADERRULE') {
		document.getElementById('primeValueAuto').style.display= '';
		document.getElementById('primeValue').style.display= 'none';
		field = primaryExclusion;
		currentTextSelectionPrimary = 'primeValueAuto';
	} else {
		document.getElementById('primeValueAuto').style.display= 'none';
		document.getElementById('primeValue').style.display= '';
		currentTextSelectionPrimary = 'primeValue';
	}
	
	if (secExclusion == 'VARIABLE' || secExclusion == 'SPS' || secExclusion == 'HEADERRULE') {
		document.getElementById('secValueAuto').style.display= '';
		document.getElementById('secValue').style.display= 'none';
		field = secExclusion;
		currentTextSelectionSec = 'secValueAuto';
	} else {
		document.getElementById('secValueAuto').style.display= 'none';
		document.getElementById('secValue').style.display= '';
		currentTextSelectionSec = 'secValue';
	}
	if (field == 'VARIABLE') {
		document.getElementById('hiddenURLForPrimary').value = "../ajaxvariablelist.ajax";
		document.getElementById('hiddenURLForType').value = 'Variable';
		
	} else if (field == 'HEADERRULE') {
		document.getElementById('hiddenURLForPrimary').value = "../ajaxautocomplete.ajax";
		document.getElementById('hiddenURLForType').value = 'RULEID';
	} else {
		document.getElementById('hiddenURLForPrimary').value = "../ajaxautocomplete.ajax";
		document.getElementById('hiddenURLForType').value = 'SPSID';
	}

	
}

function primaryCategorySelectActions() {
	var primaryExclusion = $("#criteriaSelectOne").val();
	setAutoCompleteFieldsCategoryBased(primaryExclusion, '');
	enableDisableSecondaryFields(primaryExclusion);
}

function secondaryCategorySelectActions() {
	var secExclusion = $("#criteriaSelectTwo").val();
	setAutoCompleteFieldsCategoryBased('', secExclusion);
	
}

function populateToTextPrimary() {
	var primaryExclusion = $.trim($("#criteriaSelectOne").val());
	if (primaryExclusion == null || primaryExclusion == "") {
		addErrorToNotificationTray('Please choose the first criteria');
		openTrayNotification();
		return false;
	}

	var currentPrimaryText =  $.trim($("#valuesTextOne").val());
	var newVal = $.trim($("#"+currentTextSelectionPrimary).val().toUpperCase());
	if (newVal == null || newVal == "") {
		addErrorToNotificationTray('Please choose a value to add');
		openTrayNotification();
		return false;
	}
	if (currentPrimaryText.indexOf(newVal) != -1) {
		addErrorToNotificationTray('The value is already added to primary criteria');
		openTrayNotification();
		return false;
	}
	var newPrimaryText;
	if (currentPrimaryText == null || currentPrimaryText == "") {
		newPrimaryText = ($.trim(newVal));
	} else {
		newPrimaryText = currentPrimaryText + ", " + ($.trim(newVal));
	}
	$('#valuesTextOne').val(newPrimaryText); 
	$("#"+currentTextSelectionPrimary).val(''); 
}

function populateToTextSecondary() {
	var secExclusion = $.trim($("#criteriaSelectTwo").val());
	if (secExclusion == null || secExclusion == "") {
		addErrorToNotificationTray('Please choose the second criteria');
		openTrayNotification();
		return false;
	}
	var currentSecText =  $.trim($("#valuesTextTwo").val());

	var newVal = $.trim($("#"+currentTextSelectionSec).val().toUpperCase());
	if (newVal == null || newVal == "") {
		addErrorToNotificationTray('Please choose a value to add');
		openTrayNotification();
		return false;
	}
	
	if (currentSecText.indexOf(newVal) != -1) {
		addErrorToNotificationTray('The value is already added to secondary criteria');
		openTrayNotification();
		return false;
	}
	
	var newSecText;
	if (currentSecText == null || currentSecText == "") {
		newSecText = ($.trim(newVal));
	} else {
		newSecText = currentSecText + ", " + ($.trim(newVal));
	}
	$('#valuesTextTwo').val(newSecText); 
	$("#"+currentTextSelectionSec).val(''); 
	  	
}

function validatePrimaryText() {
		var secExclusion = $.trim($("#criteriaSelectTwo").val());
		var primaryVals = $.trim($("#valuesTextOne").val()).toUpperCase();

		if (!(secExclusion == null || secExclusion == "")) {
			if (primaryVals.indexOf(",") != -1) {
				enableDisableSecondaryFields("VARIABLE");
			} else {
				enableDisableSecondaryFields("CONTRACT");
			}
		}
}

var currentAction;

function openExclusionUpdateConfirmation() {
	var errorCode = $.trim($("#errorCodeLocate").val()).toUpperCase();
	var primaryExclusion = $.trim($("#criteriaSelectOne").val());
	var secExclusion = $.trim($("#criteriaSelectTwo").val());
	var primaryVals = $.trim($("#valuesTextOne").val()).toUpperCase();
	var secVals = $.trim($("#valuesTextTwo").val()).toUpperCase();
	$('#criteriaSelectOne').val(primaryExclusion);
	$('#valuesTextOne').val(primaryVals);
	$('#criteriaSelectTwo').val(secExclusion);
	$('#valuesTextTwo').val(secVals);
	$('#primaryErrorCode').val(errorCode);
	if (errorCode == null || errorCode == "") {
			addErrorToNotificationTray('Please select the error code');
			openTrayNotification();
			return false;
	}
	if (errorCode.length != 4) {
			addErrorToNotificationTray('Error code should be of 4 characters');
			openTrayNotification();
			return false;
	}
	var regularExpression = /^[0-9A-Za-z]+$/;
	for (var i=0; i < errorCode.length; i++) {
		keychar = errorCode.charAt(i);
		if (keychar != null && !keychar.match(regularExpression)) {
			addErrorToNotificationTray('Please choose a valid error code');
			openTrayNotification();
			return false;
		}
	}
	
	if (primaryExclusion == null || primaryExclusion == "") {
			addErrorToNotificationTray('Please select the first criteria');
			openTrayNotification();
			return false;
	}
	if (primaryVals == null || primaryVals == "") {
			addErrorToNotificationTray('Please enter at least one value for first criteria');
			openTrayNotification();
			return false;
	}
	if (primaryVals.length > 3999) {
				addErrorToNotificationTray('The values for primary criteria should be less than 4000 characters');
				openTrayNotification();
				return false;
	}
	primaryVals = primaryVals.replace(/\n/g, " ");
	secVals = secVals.replace(/\n/g, " ");
	
	if (primaryVals != null && primaryVals != "") {
		for (var i = 0; i < primaryVals.length; i++) {
			var uniCode = primaryVals.charCodeAt(i);
			if (!(uniCode  >=0 && uniCode <=127)) {
					addErrorToNotificationTray('Please enter valid values for first criteria');
					openTrayNotification();
					return false;
			}
			if (primaryVals.charAt(i) == "\'" || primaryVals.charAt(i) == '\"') {
					addErrorToNotificationTray('Please enter valid values for first criteria');
					openTrayNotification();
					return false;
			}
		}
	}
	
	
	$('#valuesTextOne').val(primaryVals);
	$('#valuesTextTwo').val(secVals);
	
	if (!(secExclusion == null || secExclusion == "") || !(secVals == null || secVals == "")) {
			if (primaryVals.indexOf(",") != -1) {
				addErrorToNotificationTray('Exclusions with two criteria can have only a single value for First criteria');
				openTrayNotification();
				return false;
			}
	}
	
	var maxsizePrimary = 0;
	var currentExclusionPrimary = "";

	if (!(primaryExclusion == null || primaryExclusion == "")) {
		if (primaryExclusion == 'CONTRACT') { 
			maxsizePrimary = 4;
			currentExclusionPrimary = "Contract ID";
		}
		else if (primaryExclusion == 'SPS') {
			maxsizePrimary = 4;
			currentExclusionPrimary =  "SPS ID";
		}
		else if (primaryExclusion == 'PRODUCT') {
			maxsizePrimary = 4;
			currentExclusionPrimary =  "Product Code";
		}
		else if (primaryExclusion == 'PRODUCTLINE') {
			maxsizePrimary = 4;
			currentExclusionPrimary =  "Product Line";
		}
		else if (primaryExclusion  == 'HEADERRULE') {
			maxsizePrimary = 9;
			currentExclusionPrimary =  "Header Rule";
		}
		else if (primaryExclusion  == 'ACCUM') {
			maxsizePrimary = 10;
			currentExclusionPrimary =  "Accum";
		}
		else if (primaryExclusion  == 'VARIABLE') {
			maxsizePrimary = 12;
			currentExclusionPrimary =  "Variable";
		}
		if (primaryVals != null && primaryVals != "") {
			var splitterString = primaryVals.split(",");
			for (count = 0; count < splitterString.length; count++) {
				var currentVal = splitterString[count];
				if (null != currentVal) {
					currentVal = $.trim(currentVal);
					if (currentVal.length > maxsizePrimary) {
							var validationMessage = "All the values for " + currentExclusionPrimary + " must be less than or equal to " + maxsizePrimary + " characters";
							addErrorToNotificationTray(validationMessage);
							openTrayNotification();
							return false;
					}
				}
			}
		}
	}
	
	
	
	if (!(secVals == null || secVals == "")) {
		if (secExclusion == null || secExclusion == "") {
			addErrorToNotificationTray('Please select the second criteria');
			openTrayNotification();
			return false;
		}
	}
	
	if (!(secExclusion == null || secExclusion == "")) {
		if (secVals == null || secVals == "") {
			addErrorToNotificationTray('Please enter at least one value for second criteria');
			openTrayNotification();
			return false;
		}
	}
	
	if (secVals.length > 3999) {
				addErrorToNotificationTray('The values for secondary criteria should be less than 4000 characters');
				openTrayNotification();
				return false;
	}
	
	if (secVals != null && secVals != "") {
		for (var i = 0; i < secVals.length; i++) {
			var uniCode = secVals.charCodeAt(i);
			if (!(uniCode  >=0 && uniCode <=127)) {
					addErrorToNotificationTray('Please enter valid values for second criteria');
					openTrayNotification();
					return false;
			}
			if (secVals.charAt(i) == "\'" || secVals.charAt(i) == '\"') {
					addErrorToNotificationTray('Please enter valid values for second criteria');
					openTrayNotification();
					return false;
			}
		}
	}
	
	var maxsizeSecondary = 0;
	var currentExclusionSecondary = "";

	if (!(secExclusion == null || secExclusion == "")) {
		if (secExclusion == 'CONTRACT') { 
			maxsizeSecondary = 4;
			currentExclusionSecondary = "Contract ID";
		}
		else if (secExclusion == 'SPS') {
			maxsizeSecondary = 4;
			currentExclusionSecondary =  "SPS ID";
		}
		else if (secExclusion == 'PRODUCT') {
			maxsizeSecondary = 4;
			currentExclusionSecondary =  "Product Code";
		}
		else if (secExclusion == 'PRODUCTLINE') {
			maxsizeSecondary = 4;
			currentExclusionSecondary =  "Product Line";
		}
		else if (secExclusion  == 'HEADERRULE') {
			maxsizeSecondary = 9;
			currentExclusionSecondary =  "Header Rule";
		}
		else if (secExclusion  == 'ACCUM') {
			maxsizeSecondary = 10;
			currentExclusionSecondary =  "Accum";
		}
		else if (secExclusion  == 'VARIABLE') {
			maxsizeSecondary = 12;
			currentExclusionSecondary =  "Variable";
		}
		if (secVals != null && secVals != "") {
			var splitterStringSec = secVals.split(",");
			for (counts = 0; counts < splitterStringSec.length; counts++) {
				var currentValSec = splitterStringSec[counts];
				if (null != currentValSec) {
					currentValSec = $.trim(currentValSec);
					if (currentValSec.length > maxsizeSecondary) {
							var validationMessageSec = "All the values for " + currentExclusionSecondary + " must be less than or equal to " + maxsizeSecondary + " characters";
							addErrorToNotificationTray(validationMessageSec);
							openTrayNotification();
							return false;
					}
				}
			}
		}
	}
	
	
	currentAction = 'Update';
	$("#exclusionConfirmTitle").html('Comments related to the modification');
	$("#criteriaSelectOne").hide();
	$("#criteriaSelectTwo").hide();
 	$("#exclusionUpdateConfirm").dialog({
		resizable:false,
		width:'430',	
		height:'230',
        show:'slide',
		title: 'Confirmation',
        modal: true,
        close: function(){ 
        	$("#criteriaSelectOne").show();
			$("#criteriaSelectTwo").show();
        }
        });
}

function closeAction(){
		var action ;
		action = "../referencedata/closeAction.ajax";
		window.location.href = action;
}

function openExclusionDeleteConfirmation(exclusionId) {
	$('#exclusionIDForSave').val($.trim(exclusionId));
	currentAction = 'Delete';
	$("#exclusionConfirmTitle").html('Comments related to the modification');
	$("#criteriaSelectOne").hide();
	$("#criteriaSelectTwo").hide();
 	$("#exclusionUpdateConfirm").dialog({
		resizable:false,
		width:'430',	
		height:'230',
        show:'slide',
		title: 'Confirmation',
        modal: true,
        close: function(){ 
        	$("#criteriaSelectOne").show();
			$("#criteriaSelectTwo").show();
        }
        });

}



function openCommentsLogPopUp() {
		$('#errorCodeLocate').val($.trim($('#errorCodeLocate').val()));
		var key = $("#errorCodeLocate").val().toUpperCase();
		$('#primaryErrorCode').val(key);
		if(key == null || key == ""){
			addErrorToNotificationTray('Please choose an error code');
			openTrayNotification();
			return false;
		}
		$.ajax({
			url: "../referencedata/fetchCommentLog.ajax",
			dataType: "html",
			type: "POST",
			data: "errorCodeLocate="+key,
			success: function(data) {
				$("#exclusionCommentsLogDiv").html(data);
				$("#criteriaSelectOne").hide();
				$("#criteriaSelectTwo").hide();
				$("#exclusionCommentsLogDiv").dialog({
					width:'620',	
					height:'420',
					resizable : 'false',
					title: 'Comments Log',
					modal: true,
			        close: function(){ 
			        	$("#criteriaSelectOne").show();
						$("#criteriaSelectTwo").show();
			        }					
				});
				
			}
		});

}




function closeExclusionUpdateConfirmation() {
	var url;
	var comments = $.trim($("#updateComments").val());
	$('#userCommentsExclusion').val(comments);

	$('#actionIdentifier').val('ActionStarted');		
	if(comments == null || comments == ""){
			addErrorToNotificationTray('Enter comments and click on DONE to save the changes made.');
			openTrayNotification();
			return false;
	} else {
			if(currentAction == 'Update'){
  				url = "../referencedata/saveExclusionAction.html";
  			}
  			if(currentAction == 'Delete'){
  				url = "../referencedata/deleteExclusion.html";
  			}
		  	$('#exclusionUpdateConfirm').dialog( "close" );
		  	document.forms['exclusionForm'].action=url;
			document.forms["exclusionForm"].submit();
	}
}



function clearOnPageReLoad() {
	$('#actionIdentifier').val('ActionCompleted');
	$('#exclusionIDForSave').val('0');
	if (document.getElementById('exclusionSectionTwo').style.visibility == '') {
		clearHighLight();
	}
	if (document.getElementById('exclusionSectionThree').style.visibility == '') {
		$('#criteriaSelectOne').val('');
		$('#valuesTextOne').val('');
		$('#criteriaSelectTwo').val('');
		$('#valuesTextTwo').val('');
		document.getElementById('secValueAuto').style.display= 'none';
		document.getElementById('secValue').style.display= '';
		document.getElementById('primeValueAuto').style.display= 'none';
		document.getElementById('primeValue').style.display= '';
		$('#primeValue').val('');
		$('#secValue').val('');
		enableDisableSecondaryFields('VARIABLE');
	}

}

function clearTextArea() {
	if (document.getElementById('exclusionSectionThree').style.visibility == '') {
			$('#valuesTextOne').val('');
			$('#valuesTextTwo').val('');
			$('#primeValue').val('');
			$('#secValue').val('');
			$('#primeValueAuto').val('');
			$('#secValueAuto').val('');
	}
}

function deleteAllErrorCode() {
	$('#errorCodeLocate').val($.trim($('#errorCodeLocate').val()));
	var key = $("#errorCodeLocate").val().toUpperCase();
	
	var action = "../referencedata/deleteAllExclusions.html";
	confirmationDialogForDelete(action, 'exclusionForm');
}

/* Reference Data Values - START */

function dataValueSniffer() {

	   if(screen.height==1024) {
	      document.getElementById('referenceDataValueContainer').style.height = "570px";
	      document.getElementById('referenceDataValueSectionTwo').style.height = "280px";
	     document.getElementById('referenceDataValueContentTwo').style.height = "250px";
	   } else if(screen.height==960)
	   {
	      document.getElementById('referenceDataValueContainer').style.height= "494px";
	      document.getElementById('referenceDataValueSectionTwo').style.height = "198px";
	      document.getElementById('referenceDataValueContentTwo').style.height = "169px";
	      
	   }
	   else if(screen.height==864)
	   {
	      document.getElementById('referenceDataValueContainer').style.height= "465px";
	      document.getElementById('referenceDataValueSectionTwo').style.height = "188px";
	      document.getElementById('referenceDataValueContentTwo').style.height = "160px";
	   }
	   else if(screen.height==720)
	   {
	      document.getElementById('referenceDataValueContainer').style.height= "347px";
	      document.getElementById('referenceDataValueSectionTwo').style.height = "90px";
	      document.getElementById('referenceDataValueContentTwo').style.height = "67px";
	   }
	    else if(screen.height==768)
	   {
	      document.getElementById('referenceDataValueContainer').style.height= "387 px";
	      document.getElementById('referenceDataValueSectionTwo').style.height = "103px";
	      document.getElementById('referenceDataValueContentTwo').style.height = "76px";
	   }
	   	else if(screen.height==600)
	   {
	      document.getElementById('referenceDataValueContainer').style.height= "291 px";
	      document.getElementById('referenceDataValueSectionTwo').style.height = "50px";
	      document.getElementById('referenceDataValueContentTwo').style.height = "33px";
	   } else {
	   		var setPixMain = (screen.height * 0.6) + "px";
	   		var setPixSub = ((screen.height * 0.6) - 300) + "px"
	   		var setPixSubTask = ((screen.height * 0.6) - 330) + "px"
	   		 document.getElementById('referenceDataValueContainer').style.height= setPixMain;
	   		 document.getElementById('referenceDataValueSectionTwo').style.height= setPixSub;
	   		 document.getElementById('referenceDataValueContentTwo').style.height= setPixSubTask;
	   }
}

function openViewHistoryPopUp() {
	resetMessages();
	$('#catalogNameLocate').val($.trim($('#catalogNameLocate').val()));
	var key = $("#catalogNameLocate").val().toUpperCase();
	if(key == null || key == ""){
		addErrorToNotificationTray('Please enter a Catalog Name');
		openTrayNotification();
		return false;
	}
	$.ajax({
		url: "../referencedata/viewHistoryOfCatalog.ajax",
		dataType: "html",
		type: "POST",
		data: "catalogNameLocate="+key,
		success: function(data) {
			$("#viewHistoryDiv").html(data);
			$("#viewHistoryDiv").dialog({
				width:'620',	
				height:'auto',
				resizable : 'false',
				title: 'View History',
				modal: true				
			});
			
		}
	});
}


function openDataValueUpdateConfirmation() {
	var catalogNameLocate = $.trim($("#catalogNameLocate").val()).toUpperCase();
}


/*Auto complete for Catalog Names - START */

$(document).ready(function(){
	$('#catalogNameLocate').blur(function() {
		$('#catalogNameLocate').val($('#catalogNameLocate').val().toUpperCase());	
	});
	$("#catalogNameLocate").autocomplete({ 
		source: function(request, response) {
					$.ajax({
						url: "../referencedata/fetchCatalogNames.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term),
						success: function(data) {
						cache: false,
						response(data.rows);
						}
					});
				}
	})
});

/*Auto complete for Catalog Names - END */

function closeDataValueAction(){
	var action ;
	action = "../referencedata/closeAction.ajax";
	window.location.href = action;
}

function clearDataValueEdit() {
	resetMessages();
	if (document.getElementById('referenceDataValueSectionThree').style.visibility == '') {
			$('#secondaryCodeForSave').val('');
			$('#descriptionForSave').val('');
	}
}

function clearDataValueHighLight() {
	var table = document.getElementById("dataValueResultTab");
	for (var i = 0, row; row = table.rows[i]; i ++) {
		var element = document.getElementById(row.id);
		element.style.background = "";
		if (i%2 != 0) {
			element.style.background = "#E9F3F9";
		} 
	}
}

function editDataValue(primaryCode, secondaryCode, description) {

	clearDataValueHighLight();
	
	document.getElementById('referenceDataValueSectionThree').style.display= 'block';
	document.getElementById('userBasedForUpdate').style.display= 'block';
	document.getElementById('userBasedForCreate').style.display= 'none';
	document.getElementById('initialLoad').style.display= 'none';
	document.getElementById('additionalCommentsDialog').style.display= 'none';
	document.getElementById('primaryCodeForSave').disabled = true;

	$('#primaryCodeForSave').val($.trim(primaryCode));
	$('#secondaryCodeForSave').val($.trim(secondaryCode));
	$('#descriptionForSave').val($.trim(description));
	document.getElementById(primaryCode).style.background = "#FCF6CF";
}

function deleteDataValue(primaryCode) {
	resetMessages();
	var key = $("#catalogNameLocate").val().toUpperCase();
	if(key == null || key == ""){
		addErrorToNotificationTray('Please choose a Catalog Name');
		openTrayNotification();
		return false;
	}
	document.getElementById('valueForSave').value = primaryCode;
	document.forms['referenceDataValueForm'].action="../referencedata/checkItemMappings.html";
	document.forms['referenceDataValueForm'].submit();
}

function openAdditonalCommentsDialog(event, valueForDelete){
	resetMessages();
	var primaryCode;
	var key = $.trim($("#catalogNameLocate").val()).toUpperCase();
	if(key == null || key == ""){
		addErrorToNotificationTray('Please choose a Catalog Name');
		openTrayNotification();
		return false;
	}
	if('update'==event) { 
		primaryCode = document.getElementById('primaryCodeForSave').value;
		var secondaryCode = $.trim($("#secondaryCodeForSave").val());
		var description = $.trim($("#descriptionForSave").val());
		if(secondaryCode == null || secondaryCode == ""){
			addErrorToNotificationTray('Please enter data in Description field');
			openTrayNotification();
			return false;
		}		
		if(description == null || description == ""){
			addErrorToNotificationTray('Please enter data in Definition field');
			openTrayNotification();
			return false;
		}
	}
	if('delete'==event) {
		primaryCode = valueForDelete;
	}
	$("#primaryCodeInDialog").val(primaryCode);	
	$("#eventNameInDialog").val(event);
	document.getElementById('additionalCommentsDialog').style.display= 'block';
	$("#additionalComments").val('');	
	$("#additionalCommentsDialog table#additionalCommentsTable").css("border-top","1px solid black");	
	$("#additionalCommentsDialog").dialog({
        height:'auto',
		width:'450px',	
		resizable:false,
        show:'slide',
		title: 'Comments',
        modal: true
		});
	$("#additionalCommentsDialog").dialog();
}

function imposeMaxLength(elementId, MaxLen, element){
	resetMessages();
	var valueOfTextArea = $.trim($('#'+elementId).val());
	$('#'+elementId).val(valueOfTextArea);
	if(valueOfTextArea!=null && valueOfTextArea.length > MaxLen) {
		var message = "Text length cannot be greater than " +MaxLen+" for "+element;			
		addErrorToNotificationTray(message);
		openTrayNotification();	
		return false;
		}
	return true;
}

function saveAdditionalComments() {
	resetMessages();
	if(!imposeMaxLength('additionalComments', 250, 'user comment')){						
		return false;
	} 

	var comments = $.trim($("#additionalComments").val());
	if(	null == comments || "" == comments) {
		addErrorToNotificationTray('User comment is mandatory');
		openTrayNotification();
		return false;
	} 
	document.getElementById('userComments').value = comments;	
	$("#additionalCommentsDialog").dialog( "close" );
	var event = $("#eventNameInDialog").val();
	if('update'==event) { 
		document.getElementById('valueForSave').value = $.trim($("#primaryCodeForSave").val());
		document.forms['referenceDataValueForm'].action="../referencedata/updateItem.html";
		document.forms['referenceDataValueForm'].submit();
	} else if('delete'==event) {
		document.getElementById('valueForSave').value = $.trim($("#primaryCodeInDialog").val());
		document.forms['referenceDataValueForm'].action="../referencedata/deleteItem.html";
		document.forms['referenceDataValueForm'].submit();
	}
}

function createDataValue(catalogMinLength) {
	resetMessages();
	$('#catalogNameLocate').val($.trim($('#catalogNameLocate').val()));
	var key = $("#catalogNameLocate").val().toUpperCase();
	if(key == null || key == ""){
		addErrorToNotificationTray('Please choose a Catalog Name');
		openTrayNotification();
		return false;
	}
	$('#primaryCodeForSave').val($.trim($('#primaryCodeForSave').val()));
	var primaryCode = $("#primaryCodeForSave").val().toUpperCase();
	if(primaryCode == null || primaryCode == ""){
		addErrorToNotificationTray('Please enter data in Value field');
		openTrayNotification();
		return false;
	}
	$('#secondaryCodeForSave').val($.trim($('#secondaryCodeForSave').val()));
	var description = $("#secondaryCodeForSave").val();
	if(description == null || description == ""){
		addErrorToNotificationTray('Please enter data in Description field');
		openTrayNotification();
		return false;
	}
	$('#descriptionForSave').val($.trim($('#descriptionForSave').val()));
	var description = $("#descriptionForSave").val();
	if(description == null || description == ""){
		addErrorToNotificationTray('Please enter data in Definition field');
		openTrayNotification();
		return false;
	} else {
		if(description.length > 240) {
			addErrorToNotificationTray('Definition field should have a maximum of 240 characters');
			openTrayNotification();
			return false;
		}
	}
	
	if(false == alphaNumericCheck(primaryCode)) {
		addErrorToNotificationTray('Value can have only alphanumberic characters');
		openTrayNotification();
		return false;
	}
	
	document.forms['referenceDataValueForm'].action="../referencedata/addItem.html";
	document.forms['referenceDataValueForm'].submit();
}

function alphaNumericCheck(inputValue) {
	var check = false;
	for(var count = 0; count < inputValue.length; count++) {
		var unicode = inputValue.charCodeAt(count);
		if(unicode >= 48 && unicode <= 57 ) {
			check = true;
		} else if(unicode >= 65 && unicode <= 90) {
			check = true;
		} else if(unicode >= 97 && unicode <= 122) {
			check = true;
		} else {
			check = false;
			return check;
		}
	}
}

function viewRefreshedPage(isAdmin) {

	$('#primaryCodeForSave').val('');
	$('#secondaryCodeForSave').val('');
	$('#descriptionForSave').val('');	
	document.getElementById('initialLoad').style.display= 'none';
	document.getElementById('userBasedForUpdate').style.display= 'none';
	document.getElementById('userBasedForCreate').style.display= 'block';
	document.getElementById('referenceDataValueSectionTwo').style.display= 'block';
	if("true" == isAdmin) {
		document.getElementById('referenceDataValueSectionThree').style.display= 'block';
		document.getElementById('primaryCodeForSave').disabled = false;
	}	
	dataValueSniffer();
}

function clearOnDataValuePageReLoad(isAdmin) {
	
	if ("true" == isAdmin && document.getElementById('referenceDataValueSectionThree').style.visibility == '') {	
		viewRefreshedPage(isAdmin);
	}
	if (document.getElementById('referenceDataValueSectionTwo').style.visibility == '') {
		clearDataValueHighLight();
	}
}
/* Reference Data Values - END */


/*Auto complete for Exclusion list */
$(document).ready(function(){
	$('#errorCodeLocate').blur(function() {
		$('#errorCodeLocate').val($('#errorCodeLocate').val().toUpperCase());	
	});
	$("#errorCodeLocate").autocomplete({ 
		source: function(request, response) {
					$.ajax({
						url: "../referencedata/fetchMatchingErrorCodesForAutoComplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term),
						success: function(data) {
						cache: false,
						response(data.rows);
						}
					});
				}
	})
});

/*Auto complete for Primary Values */
$(document).ready(function(){
	$('#primeValueAuto').blur(function() {
		$('#primeValueAuto').val($('#primeValueAuto').val().toUpperCase());	
	});
	$("#primeValueAuto").autocomplete({ 
		source: function(request, response) {
					$.ajax({
						url: document.getElementById('hiddenURLForPrimary').value,
						dataType: "json",
						type:"POST",
						data: "key="+request.term+"&name="+document.getElementById('hiddenURLForType').value,
						success: function(data) {
						cache: false,
						response(data.rows);
						}
					});
				}
	})
});



  
/*Auto complete for Secondary Values */
$(document).ready(function(){
	$('#secValueAuto').blur(function() {
		$('#secValueAuto').val($('#secValueAuto').val().toUpperCase());	
	});
	$("#secValueAuto").autocomplete({ 
		source: function(request, response) {
					$.ajax({
						url: document.getElementById('hiddenURLForPrimary').value,
						dataType: "json",
						type:"POST",
						data: "key="+request.term+"&name="+document.getElementById('hiddenURLForType').value,
						success: function(data) {
						cache: false,
						response(data.rows);
						}
					});
				}
	})
 });
 
function clearHighLight() {
		var table = document.getElementById("exclusionResultTab");
		for (var i = 0, row; row = table.rows[i]; i ++) {
				if (i%2 != 0) {
					document.getElementById(row.id).style.background = "";
				}
		}
}

function sniffer() {
		var ie6Flag = ''
	   if ($.browser.msie) {
	   		if ($.trim($.browser.version) == '6.0') {
	   			ie6Flag = 'ie6'
	   		}
	   }
	   if (screen.height==1024) {
		   	if (ie6Flag == 'ie6') {
			   	  document.getElementById('exclusionEditContainer').style.height = "560px";
			      document.getElementById('exclusionSectionTwo').style.height = "252px";
			     document.getElementById('exclusionContentSectionTwo').style.height = "226px";
		   	} else {
			      document.getElementById('exclusionEditContainer').style.height = "560px";
			      document.getElementById('exclusionSectionTwo').style.height = "252px";
			     document.getElementById('exclusionContentSectionTwo').style.height = "226px";
			}
	   } else if(screen.height==960) {
	  	 	if (ie6Flag == 'ie6') {
			      document.getElementById("exclusionEditContainer").style.height= "488px";
			      document.getElementById('exclusionSectionTwo').style.height = "173px";
			      document.getElementById('exclusionContentSectionTwo').style.height = "144px";
			 } else {
			 	  document.getElementById("exclusionEditContainer").style.height= "488px";
			      document.getElementById('exclusionSectionTwo').style.height = "173px";
			      document.getElementById('exclusionContentSectionTwo').style.height = "144px";
			 }
	   } else if(screen.height==864) {
	  		 if (ie6Flag == 'ie6') {
			      document.getElementById("exclusionEditContainer").style.height= "455px";
			      document.getElementById('exclusionSectionTwo').style.height = "159px";
			      document.getElementById('exclusionContentSectionTwo').style.height = "131px";
			 } else {
			 	  document.getElementById("exclusionEditContainer").style.height= "455px";
			      document.getElementById('exclusionSectionTwo').style.height = "159px";
			      document.getElementById('exclusionContentSectionTwo').style.height = "131px";
			 }
	   } else if(screen.height==720) {
		   if (ie6Flag == 'ie6') {
			      document.getElementById("exclusionEditContainer").style.height= "335px";
			      document.getElementById('exclusionSectionTwo').style.height = "78px";
			      document.getElementById('exclusionContentSectionTwo').style.height = "55px";
			} else {
				  document.getElementById("exclusionEditContainer").style.height= "335px";
			      document.getElementById('exclusionSectionTwo').style.height = "78px";
			      document.getElementById('exclusionContentSectionTwo').style.height = "55px";
			}
	   } else if(screen.height==768) {
	  		 if (ie6Flag == 'ie6') {
			      document.getElementById("exclusionEditContainer").style.height= "377 px";
			      document.getElementById('exclusionSectionTwo').style.height = "80px";
			      document.getElementById('exclusionContentSectionTwo').style.height = "55px";
			 } else {
			 	 document.getElementById("exclusionEditContainer").style.height= "377 px";
			      document.getElementById('exclusionSectionTwo').style.height = "80px";
			      document.getElementById('exclusionContentSectionTwo').style.height = "55px";
			 }
	   } else if(screen.height==600) {
	   	 if (ie6Flag == 'ie6') {
		      document.getElementById("exclusionEditContainer").style.height= "291 px";
		      document.getElementById('exclusionSectionTwo').style.height = "50px";
		      document.getElementById('exclusionContentSectionTwo').style.height = "33px";
		 } else {
		 	  document.getElementById("exclusionEditContainer").style.height= "291 px";
		      document.getElementById('exclusionSectionTwo').style.height = "50px";
		      document.getElementById('exclusionContentSectionTwo').style.height = "33px";
		 }
	   } else {
	   		if (screen.height > 768) {
		   		var setPixMain = (screen.height * 0.49) + "px";
		   		var setPixSub = ((screen.height * 0.49) - 292) + "px"
		   		var setPixSubTask = ((screen.height * 0.49) - 313) + "px"
		   		 document.getElementById("exclusionEditContainer").style.height= setPixMain;
		   		 document.getElementById("exclusionSectionTwo").style.height= setPixSub;
		   		document.getElementById("exclusionContentSectionTwo").style.height= setPixSubTask;
		   	} else {
				  document.getElementById("exclusionEditContainer").style.height= "330px";
			      document.getElementById('exclusionSectionTwo').style.height = "73px";
			      document.getElementById('exclusionContentSectionTwo').style.height = "50px";
		   	}
	   }
}


function editExclusion(exclusionId, primaryExclusion, primaryVals, secExclusion, secVals) {
	clearHighLight();
	$('#exclusionIDForSave').val($.trim(exclusionId));
	$('#criteriaSelectOne').val($.trim(primaryExclusion));
	$('#valuesTextOne').val($.trim(primaryVals));
	enableDisableSecondaryFields(primaryExclusion);
	if  (!(secExclusion == null || secExclusion == "" || secExclusion == 'null')) {
		$('#criteriaSelectTwo').val($.trim(secExclusion));
		$('#valuesTextTwo').val($.trim(secVals));
	} else {
		$('#criteriaSelectTwo').val('');
		$('#valuesTextTwo').val('');
	}
	document.getElementById(exclusionId).style.background = "#FCF6CF";
	setAutoCompleteFieldsCategoryBased(primaryExclusion, secExclusion);
}



function enableDisableSecondaryFields(primaryExclusion) {
	if (primaryExclusion == 'CONTRACT' ||  primaryExclusion == 'PRODUCT' || primaryExclusion == 'PRODUCTLINE') {
		enabler = 'enable';
	} else {
		enabler = 'disable';
	}
	if (enabler == 'enable') {
	          $("#valuesTextTwo"). removeClass("exclusionsTextDisabled");
             $("#valuesTextTwo").attr('disabled', false);
             $("#valuesTextTwo"). addClass("exclusionsText");
             $("#criteriaSelectTwo"). removeClass("dropdown200Disabled");
             $("#criteriaSelectTwo").attr('disabled', false);
             $("#criteriaSelectTwo"). addClass("dropdown200");
             $("#secValue"). removeClass("inputbox200ExcludeDisabled");
             $("#secValue").attr('disabled', false);
             $("#secValue"). addClass("inputbox200Exclude");
             
              $("#addSec").attr('disabled', false);
              
             $("#secValueAuto"). removeClass("inputbox200ExcludeDisabled");
             $("#secValueAuto").attr('disabled', false);
             $("#secValueAuto"). addClass("inputbox200Exclude");
	} else {
             $('#valuesTextTwo').val('');      
	          $("#valuesTextTwo"). removeClass("exclusionsText");
             $("#valuesTextTwo").attr('disabled', true);
             $("#valuesTextTwo"). addClass("exclusionsTextDisabled");
             $('#criteriaSelectTwo').val('');   
              $("#criteriaSelectTwo"). removeClass("dropdown200");
             $("#criteriaSelectTwo").attr('disabled', true);
             $("#criteriaSelectTwo"). addClass("dropdown200Disabled");
             $("#addSec").attr('disabled', true);
              $("#secValue"). removeClass("inputbox200Exclude");
             $("#secValue").attr('disabled', true);
             $("#secValue"). addClass("inputbox200ExcludeDisabled");
             $('#secValue').val('');   
             
             $("#secValueAuto").attr('disabled', true);
             $("#secValueAuto"). addClass("inputbox200ExcludeDisabled");
             $('#secValueAuto').val('');   
	}
}

var currentTextSelectionPrimary;
var currentTextSelectionSec;

function setAutoCompleteFieldsCategoryBased(primaryExclusion, secExclusion) {
	var field;
	if (primaryExclusion == 'VARIABLE' || primaryExclusion == 'SPS' || primaryExclusion == 'HEADERRULE') {
		document.getElementById('primeValueAuto').style.display= '';
		document.getElementById('primeValue').style.display= 'none';
		field = primaryExclusion;
		currentTextSelectionPrimary = 'primeValueAuto';
	} else {
		document.getElementById('primeValueAuto').style.display= 'none';
		document.getElementById('primeValue').style.display= '';
		currentTextSelectionPrimary = 'primeValue';
	}
	
	if (secExclusion == 'VARIABLE' || secExclusion == 'SPS' || secExclusion == 'HEADERRULE') {
		document.getElementById('secValueAuto').style.display= '';
		document.getElementById('secValue').style.display= 'none';
		field = secExclusion;
		currentTextSelectionSec = 'secValueAuto';
	} else {
		document.getElementById('secValueAuto').style.display= 'none';
		document.getElementById('secValue').style.display= '';
		currentTextSelectionSec = 'secValue';
	}
	if (field == 'VARIABLE') {
		document.getElementById('hiddenURLForPrimary').value = "../ajaxvariablelist.ajax";
		document.getElementById('hiddenURLForType').value = 'Variable';
		
	} else if (field == 'HEADERRULE') {
		document.getElementById('hiddenURLForPrimary').value = "../ajaxautocomplete.ajax";
		document.getElementById('hiddenURLForType').value = 'RULEID';
	} else {
		document.getElementById('hiddenURLForPrimary').value = "../ajaxautocomplete.ajax";
		document.getElementById('hiddenURLForType').value = 'SPSID';
	}

	
}

function primaryCategorySelectActions() {
	var primaryExclusion = $("#criteriaSelectOne").val();
	setAutoCompleteFieldsCategoryBased(primaryExclusion, '');
	enableDisableSecondaryFields(primaryExclusion);
}

function secondaryCategorySelectActions() {
	var secExclusion = $("#criteriaSelectTwo").val();
	setAutoCompleteFieldsCategoryBased('', secExclusion);
	
}

function populateToTextPrimary() {
	var primaryExclusion = $.trim($("#criteriaSelectOne").val());
	if (primaryExclusion == null || primaryExclusion == "") {
		addErrorToNotificationTray('Please choose the first criteria');
		openTrayNotification();
		return false;
	}

	var currentPrimaryText =  $.trim($("#valuesTextOne").val());
	var newVal = $.trim($("#"+currentTextSelectionPrimary).val().toUpperCase());
	if (newVal == null || newVal == "") {
		addErrorToNotificationTray('Please choose a value to add');
		openTrayNotification();
		return false;
	}
	if (currentPrimaryText.indexOf(newVal) != -1) {
		addErrorToNotificationTray('The value is already added to primary criteria');
		openTrayNotification();
		return false;
	}
	var newPrimaryText;
	if (currentPrimaryText == null || currentPrimaryText == "") {
		newPrimaryText = ($.trim(newVal));
	} else {
		newPrimaryText = currentPrimaryText + ", " + ($.trim(newVal));
	}
	$('#valuesTextOne').val(newPrimaryText); 
	$("#"+currentTextSelectionPrimary).val(''); 
}

function populateToTextSecondary() {
	var secExclusion = $.trim($("#criteriaSelectTwo").val());
	if (secExclusion == null || secExclusion == "") {
		addErrorToNotificationTray('Please choose the second criteria');
		openTrayNotification();
		return false;
	}
	var currentSecText =  $.trim($("#valuesTextTwo").val());

	var newVal = $.trim($("#"+currentTextSelectionSec).val().toUpperCase());
	if (newVal == null || newVal == "") {
		addErrorToNotificationTray('Please choose a value to add');
		openTrayNotification();
		return false;
	}
	
	if (currentSecText.indexOf(newVal) != -1) {
		addErrorToNotificationTray('The value is already added to secondary criteria');
		openTrayNotification();
		return false;
	}
	
	var newSecText;
	if (currentSecText == null || currentSecText == "") {
		newSecText = ($.trim(newVal));
	} else {
		newSecText = currentSecText + ", " + ($.trim(newVal));
	}
	$('#valuesTextTwo').val(newSecText); 
	$("#"+currentTextSelectionSec).val(''); 
	  	
}

function validatePrimaryText() {
		var secExclusion = $.trim($("#criteriaSelectTwo").val());
		var primaryVals = $.trim($("#valuesTextOne").val()).toUpperCase();

		if (!(secExclusion == null || secExclusion == "")) {
			if (primaryVals.indexOf(",") != -1) {
				enableDisableSecondaryFields("VARIABLE");
			} else {
				enableDisableSecondaryFields("CONTRACT");
			}
		}
}

var currentAction;

function openExclusionUpdateConfirmation() {
	var errorCode = $.trim($("#errorCodeLocate").val()).toUpperCase();
	var primaryExclusion = $.trim($("#criteriaSelectOne").val());
	var secExclusion = $.trim($("#criteriaSelectTwo").val());
	var primaryVals = $.trim($("#valuesTextOne").val()).toUpperCase();
	var secVals = $.trim($("#valuesTextTwo").val()).toUpperCase();
	$('#criteriaSelectOne').val(primaryExclusion);
	$('#valuesTextOne').val(primaryVals);
	$('#criteriaSelectTwo').val(secExclusion);
	$('#valuesTextTwo').val(secVals);
	$('#primaryErrorCode').val(errorCode);
	if (errorCode == null || errorCode == "") {
			addErrorToNotificationTray('Please select the error code');
			openTrayNotification();
			return false;
	}
	if (errorCode.length != 4) {
			addErrorToNotificationTray('Error code should be of 4 characters');
			openTrayNotification();
			return false;
	}
	var regularExpression = /^[0-9A-Za-z]+$/;
	for (var i=0; i < errorCode.length; i++) {
		keychar = errorCode.charAt(i);
		if (keychar != null && !keychar.match(regularExpression)) {
			addErrorToNotificationTray('Please choose a valid error code');
			openTrayNotification();
			return false;
		}
	}
	
	if (primaryExclusion == null || primaryExclusion == "") {
			addErrorToNotificationTray('Please select the first criteria');
			openTrayNotification();
			return false;
	}
	if (primaryVals == null || primaryVals == "") {
			addErrorToNotificationTray('Please enter at least one value for first criteria');
			openTrayNotification();
			return false;
	}
	if (primaryVals.length > 3999) {
				addErrorToNotificationTray('The values for primary criteria should be less than 4000 characters');
				openTrayNotification();
				return false;
	}
	primaryVals = primaryVals.replace(/\n/g, " ");
	secVals = secVals.replace(/\n/g, " ");
	
	if (primaryVals != null && primaryVals != "") {
		for (var i = 0; i < primaryVals.length; i++) {
			var uniCode = primaryVals.charCodeAt(i);
			if (!(uniCode  >=0 && uniCode <=127)) {
					addErrorToNotificationTray('Please enter valid values for first criteria');
					openTrayNotification();
					return false;
			}
			if (primaryVals.charAt(i) == "\'" || primaryVals.charAt(i) == '\"') {
					addErrorToNotificationTray('Please enter valid values for first criteria');
					openTrayNotification();
					return false;
			}
		}
	}
	
	
	$('#valuesTextOne').val(primaryVals);
	$('#valuesTextTwo').val(secVals);
	
	if (!(secExclusion == null || secExclusion == "") || !(secVals == null || secVals == "")) {
			if (primaryVals.indexOf(",") != -1) {
				addErrorToNotificationTray('Exclusions with two criteria can have only a single value for First criteria');
				openTrayNotification();
				return false;
			}
	}
	
	var maxsizePrimary = 0;
	var currentExclusionPrimary = "";

	if (!(primaryExclusion == null || primaryExclusion == "")) {
		if (primaryExclusion == 'CONTRACT') { 
			maxsizePrimary = 4;
			currentExclusionPrimary = "Contract ID";
		}
		else if (primaryExclusion == 'SPS') {
			maxsizePrimary = 4;
			currentExclusionPrimary =  "SPS ID";
		}
		else if (primaryExclusion == 'PRODUCT') {
			maxsizePrimary = 4;
			currentExclusionPrimary =  "Product Code";
		}
		else if (primaryExclusion == 'PRODUCTLINE') {
			maxsizePrimary = 4;
			currentExclusionPrimary =  "Product Line";
		}
		else if (primaryExclusion  == 'HEADERRULE') {
			maxsizePrimary = 9;
			currentExclusionPrimary =  "Header Rule";
		}
		else if (primaryExclusion  == 'ACCUM') {
			maxsizePrimary = 10;
			currentExclusionPrimary =  "Accum";
		}
		else if (primaryExclusion  == 'VARIABLE') {
			maxsizePrimary = 12;
			currentExclusionPrimary =  "Variable";
		}
		if (primaryVals != null && primaryVals != "") {
			var splitterString = primaryVals.split(",");
			for (count = 0; count < splitterString.length; count++) {
				var currentVal = splitterString[count];
				if (null != currentVal) {
					currentVal = $.trim(currentVal);
					if (currentVal.length > maxsizePrimary) {
							var validationMessage = "All the values for " + currentExclusionPrimary + " must be less than or equal to " + maxsizePrimary + " characters";
							addErrorToNotificationTray(validationMessage);
							openTrayNotification();
							return false;
					}
				}
			}
		}
	}
	
	
	
	if (!(secVals == null || secVals == "")) {
		if (secExclusion == null || secExclusion == "") {
			addErrorToNotificationTray('Please select the second criteria');
			openTrayNotification();
			return false;
		}
	}
	
	if (!(secExclusion == null || secExclusion == "")) {
		if (secVals == null || secVals == "") {
			addErrorToNotificationTray('Please enter at least one value for second criteria');
			openTrayNotification();
			return false;
		}
	}
	
	if (secVals.length > 3999) {
				addErrorToNotificationTray('The values for secondary criteria should be less than 4000 characters');
				openTrayNotification();
				return false;
	}
	
	if (secVals != null && secVals != "") {
		for (var i = 0; i < secVals.length; i++) {
			var uniCode = secVals.charCodeAt(i);
			if (!(uniCode  >=0 && uniCode <=127)) {
					addErrorToNotificationTray('Please enter valid values for second criteria');
					openTrayNotification();
					return false;
			}
			if (secVals.charAt(i) == "\'" || secVals.charAt(i) == '\"') {
					addErrorToNotificationTray('Please enter valid values for second criteria');
					openTrayNotification();
					return false;
			}
		}
	}
	
	var maxsizeSecondary = 0;
	var currentExclusionSecondary = "";

	if (!(secExclusion == null || secExclusion == "")) {
		if (secExclusion == 'CONTRACT') { 
			maxsizeSecondary = 4;
			currentExclusionSecondary = "Contract ID";
		}
		else if (secExclusion == 'SPS') {
			maxsizeSecondary = 4;
			currentExclusionSecondary =  "SPS ID";
		}
		else if (secExclusion == 'PRODUCT') {
			maxsizeSecondary = 4;
			currentExclusionSecondary =  "Product Code";
		}
		else if (secExclusion == 'PRODUCTLINE') {
			maxsizeSecondary = 4;
			currentExclusionSecondary =  "Product Line";
		}
		else if (secExclusion  == 'HEADERRULE') {
			maxsizeSecondary = 9;
			currentExclusionSecondary =  "Header Rule";
		}
		else if (secExclusion  == 'ACCUM') {
			maxsizeSecondary = 10;
			currentExclusionSecondary =  "Accum";
		}
		else if (secExclusion  == 'VARIABLE') {
			maxsizeSecondary = 12;
			currentExclusionSecondary =  "Variable";
		}
		if (secVals != null && secVals != "") {
			var splitterStringSec = secVals.split(",");
			for (counts = 0; counts < splitterStringSec.length; counts++) {
				var currentValSec = splitterStringSec[counts];
				if (null != currentValSec) {
					currentValSec = $.trim(currentValSec);
					if (currentValSec.length > maxsizeSecondary) {
							var validationMessageSec = "All the values for " + currentExclusionSecondary + " must be less than or equal to " + maxsizeSecondary + " characters";
							addErrorToNotificationTray(validationMessageSec);
							openTrayNotification();
							return false;
					}
				}
			}
		}
	}
	
	
	currentAction = 'Update';
	$("#exclusionConfirmTitle").html('Comments related to the modification');
	$("#criteriaSelectOne").hide();
	$("#criteriaSelectTwo").hide();
 	$("#exclusionUpdateConfirm").dialog({
		resizable:false,
		width:'430',	
		height:'230',
        show:'slide',
		title: 'Confirmation',
        modal: true,
        close: function(){ 
        	$("#criteriaSelectOne").show();
			$("#criteriaSelectTwo").show();
        }
        });
}

function closeAction(){
		var action ;
		action = "../referencedata/closeAction.ajax";
		window.location.href = action;
}

function openExclusionDeleteConfirmation(exclusionId) {
	$('#exclusionIDForSave').val($.trim(exclusionId));
	currentAction = 'Delete';
	$("#exclusionConfirmTitle").html('Comments related to the modification');
	$("#criteriaSelectOne").hide();
	$("#criteriaSelectTwo").hide();
 	$("#exclusionUpdateConfirm").dialog({
		resizable:false,
		width:'430',	
		height:'230',
        show:'slide',
		title: 'Confirmation',
        modal: true,
        close: function(){ 
        	$("#criteriaSelectOne").show();
			$("#criteriaSelectTwo").show();
        }
        });

}



function openCommentsLogPopUp() {
		$('#errorCodeLocate').val($.trim($('#errorCodeLocate').val()));
		var key = $("#errorCodeLocate").val().toUpperCase();
		$('#primaryErrorCode').val(key);
		if(key == null || key == ""){
			addErrorToNotificationTray('Please choose an error code');
			openTrayNotification();
			return false;
		}
		$.ajax({
			url: "../referencedata/fetchCommentLog.ajax",
			dataType: "html",
			type: "POST",
			data: "errorCodeLocate="+key,
			success: function(data) {
				$("#exclusionCommentsLogDiv").html(data);
				$("#criteriaSelectOne").hide();
				$("#criteriaSelectTwo").hide();
				$("#exclusionCommentsLogDiv").dialog({
					width:'620',	
					height:'420',
					resizable : 'false',
					title: 'Comments Log',
					modal: true,
			        close: function(){ 
			        	$("#criteriaSelectOne").show();
						$("#criteriaSelectTwo").show();
			        }					
				});
				
			}
		});

}




function closeExclusionUpdateConfirmation() {
	var url;
	var comments = $.trim($("#updateComments").val());
	$('#userCommentsExclusion').val(comments);

	$('#actionIdentifier').val('ActionStarted');		
	if(comments == null || comments == ""){
			addErrorToNotificationTray('Enter comments and click on DONE to save the changes made.');
			openTrayNotification();
			return false;
	} else {
			if(currentAction == 'Update'){
  				url = "../referencedata/saveExclusionAction.html";
  			}
  			if(currentAction == 'Delete'){
  				url = "../referencedata/deleteExclusion.html";
  			}
		  	$('#exclusionUpdateConfirm').dialog( "close" );
		  	document.forms['exclusionForm'].action=url;
			document.forms["exclusionForm"].submit();
	}
}



function clearOnPageReLoad() {
	$('#actionIdentifier').val('ActionCompleted');
	$('#exclusionIDForSave').val('0');
	if (document.getElementById('exclusionSectionTwo').style.visibility == '') {
		clearHighLight();
	}
	if (document.getElementById('exclusionSectionThree').style.visibility == '') {
		$('#criteriaSelectOne').val('');
		$('#valuesTextOne').val('');
		$('#criteriaSelectTwo').val('');
		$('#valuesTextTwo').val('');
		document.getElementById('secValueAuto').style.display= 'none';
		document.getElementById('secValue').style.display= '';
		document.getElementById('primeValueAuto').style.display= 'none';
		document.getElementById('primeValue').style.display= '';
		$('#primeValue').val('');
		$('#secValue').val('');
		enableDisableSecondaryFields('VARIABLE');
	}

}

function clearTextArea() {
	if (document.getElementById('exclusionSectionThree').style.visibility == '') {
			$('#valuesTextOne').val('');
			$('#valuesTextTwo').val('');
			$('#primeValue').val('');
			$('#secValue').val('');
			$('#primeValueAuto').val('');
			$('#secValueAuto').val('');
	}
}

function deleteAllErrorCode() {
	$('#errorCodeLocate').val($.trim($('#errorCodeLocate').val()));
	var key = $("#errorCodeLocate").val().toUpperCase();
	
	var action = "../referencedata/deleteAllExclusions.html";
	confirmationDialogForDelete(action, 'exclusionForm');
}

/* Reference Data Values - START */

function dataValueSniffer() {

	   if(screen.height==1024) {
	      document.getElementById('referenceDataValueContainer').style.height = "570px";
	      document.getElementById('referenceDataValueSectionTwo').style.height = "280px";
	     document.getElementById('referenceDataValueContentTwo').style.height = "250px";
	   } else if(screen.height==960)
	   {
	      document.getElementById('referenceDataValueContainer').style.height= "494px";
	      document.getElementById('referenceDataValueSectionTwo').style.height = "198px";
	      document.getElementById('referenceDataValueContentTwo').style.height = "169px";
	      
	   }
	   else if(screen.height==864)
	   {
	      document.getElementById('referenceDataValueContainer').style.height= "465px";
	      document.getElementById('referenceDataValueSectionTwo').style.height = "188px";
	      document.getElementById('referenceDataValueContentTwo').style.height = "160px";
	   }
	   else if(screen.height==720)
	   {
	      document.getElementById('referenceDataValueContainer').style.height= "347px";
	      document.getElementById('referenceDataValueSectionTwo').style.height = "90px";
	      document.getElementById('referenceDataValueContentTwo').style.height = "67px";
	   }
	    else if(screen.height==768)
	   {
	      document.getElementById('referenceDataValueContainer').style.height= "387 px";
	      document.getElementById('referenceDataValueSectionTwo').style.height = "103px";
	      document.getElementById('referenceDataValueContentTwo').style.height = "76px";
	   }
	   	else if(screen.height==600)
	   {
	      document.getElementById('referenceDataValueContainer').style.height= "291 px";
	      document.getElementById('referenceDataValueSectionTwo').style.height = "50px";
	      document.getElementById('referenceDataValueContentTwo').style.height = "33px";
	   } else {
	   		var setPixMain = (screen.height * 0.6) + "px";
	   		var setPixSub = ((screen.height * 0.6) - 300) + "px"
	   		var setPixSubTask = ((screen.height * 0.6) - 330) + "px"
	   		 document.getElementById('referenceDataValueContainer').style.height= setPixMain;
	   		 document.getElementById('referenceDataValueSectionTwo').style.height= setPixSub;
	   		 document.getElementById('referenceDataValueContentTwo').style.height= setPixSubTask;
	   }
}

function openViewHistoryPopUp() {
	resetMessages();
	$('#catalogNameLocate').val($.trim($('#catalogNameLocate').val()));
	var key = $("#catalogNameLocate").val().toUpperCase();
	if(key == null || key == ""){
		addErrorToNotificationTray('Please enter a Catalog Name');
		openTrayNotification();
		return false;
	}
	$.ajax({
		url: "../referencedata/viewHistoryOfCatalog.ajax",
		dataType: "html",
		type: "POST",
		data: "catalogNameLocate="+key,
		success: function(data) {
			$("#viewHistoryDiv").html(data);
			$("#viewHistoryDiv").dialog({
				width:'620',	
				height:'auto',
				resizable : 'false',
				title: 'View History',
				modal: true				
			});
			
		}
	});
}


function openDataValueUpdateConfirmation() {
	var catalogNameLocate = $.trim($("#catalogNameLocate").val()).toUpperCase();
}


/*Auto complete for Catalog Names - START */

$(document).ready(function(){
	$('#catalogNameLocate').blur(function() {
		$('#catalogNameLocate').val($('#catalogNameLocate').val().toUpperCase());	
	});
	$("#catalogNameLocate").autocomplete({ 
		source: function(request, response) {
					$.ajax({
						url: "../referencedata/fetchCatalogNames.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term),
						success: function(data) {
						cache: false,
						response(data.rows);
						}
					});
				}
	})
});

/*Auto complete for Catalog Names - END */

function closeDataValueAction(){
	var action ;
	action = "../referencedata/closeAction.ajax";
	window.location.href = action;
}

function clearDataValueEdit() {
	resetMessages();
	if (document.getElementById('referenceDataValueSectionThree').style.visibility == '') {
			$('#secondaryCodeForSave').val('');
			$('#descriptionForSave').val('');
	}
}

function clearDataValueHighLight() {
	var table = document.getElementById("dataValueResultTab");
	for (var i = 0, row; row = table.rows[i]; i ++) {
		var element = document.getElementById(row.id);
		element.style.background = "";
		if (i%2 != 0) {
			element.style.background = "#E9F3F9";
		} 
	}
}

function editDataValue(primaryCode, secondaryCode, description) {

	clearDataValueHighLight();
	
	document.getElementById('referenceDataValueSectionThree').style.display= 'block';
	document.getElementById('userBasedForUpdate').style.display= 'block';
	document.getElementById('userBasedForCreate').style.display= 'none';
	document.getElementById('initialLoad').style.display= 'none';
	document.getElementById('additionalCommentsDialog').style.display= 'none';
	document.getElementById('primaryCodeForSave').disabled = true;

	$('#primaryCodeForSave').val($.trim(primaryCode));
	$('#secondaryCodeForSave').val($.trim(secondaryCode));
	$('#descriptionForSave').val($.trim(description));
	document.getElementById(primaryCode).style.background = "#FCF6CF";
}

function deleteDataValue(primaryCode) {
	resetMessages();
	var key = $("#catalogNameLocate").val().toUpperCase();
	if(key == null || key == ""){
		addErrorToNotificationTray('Please choose a Catalog Name');
		openTrayNotification();
		return false;
	}
	document.getElementById('valueForSave').value = primaryCode;
	document.forms['referenceDataValueForm'].action="../referencedata/checkItemMappings.html";
	document.forms['referenceDataValueForm'].submit();
}

function openAdditonalCommentsDialog(event, valueForDelete){
	resetMessages();
	var primaryCode;
	var key = $.trim($("#catalogNameLocate").val()).toUpperCase();
	if(key == null || key == ""){
		addErrorToNotificationTray('Please choose a Catalog Name');
		openTrayNotification();
		return false;
	}
	if('update'==event) { 
		primaryCode = document.getElementById('primaryCodeForSave').value;
		var secondaryCode = $.trim($("#secondaryCodeForSave").val());
		var description = $.trim($("#descriptionForSave").val());
		if(secondaryCode == null || secondaryCode == ""){
			addErrorToNotificationTray('Please enter data in Description field');
			openTrayNotification();
			return false;
		}		
		if(description == null || description == ""){
			addErrorToNotificationTray('Please enter data in Definition field');
			openTrayNotification();
			return false;
		}
	}
	if('delete'==event) {
		primaryCode = valueForDelete;
	}
	$("#primaryCodeInDialog").val(primaryCode);	
	$("#eventNameInDialog").val(event);
	document.getElementById('additionalCommentsDialog').style.display= 'block';
	$("#additionalComments").val('');	
	$("#additionalCommentsDialog table#additionalCommentsTable").css("border-top","1px solid black");	
	$("#additionalCommentsDialog").dialog({
        height:'auto',
		width:'450px',	
		resizable:false,
        show:'slide',
		title: 'Comments',
        modal: true
		});
	$("#additionalCommentsDialog").dialog();
}

function imposeMaxLength(elementId, MaxLen, element){
	resetMessages();
	var valueOfTextArea = $.trim($('#'+elementId).val());
	$('#'+elementId).val(valueOfTextArea);
	if(valueOfTextArea!=null && valueOfTextArea.length > MaxLen) {
		var message = "Text length cannot be greater than " +MaxLen+" for "+element;			
		addErrorToNotificationTray(message);
		openTrayNotification();	
		return false;
		}
	return true;
}

function saveAdditionalComments() {
	resetMessages();
	if(!imposeMaxLength('additionalComments', 250, 'user comment')){						
		return false;
	} 

	var comments = $.trim($("#additionalComments").val());
	if(	null == comments || "" == comments) {
		addErrorToNotificationTray('User comment is mandatory');
		openTrayNotification();
		return false;
	} 
	document.getElementById('userComments').value = comments;	
	$("#additionalCommentsDialog").dialog( "close" );
	var event = $("#eventNameInDialog").val();
	if('update'==event) { 
		document.getElementById('valueForSave').value = $.trim($("#primaryCodeForSave").val());
		document.forms['referenceDataValueForm'].action="../referencedata/updateItem.html";
		document.forms['referenceDataValueForm'].submit();
	} else if('delete'==event) {
		document.getElementById('valueForSave').value = $.trim($("#primaryCodeInDialog").val());
		document.forms['referenceDataValueForm'].action="../referencedata/deleteItem.html";
		document.forms['referenceDataValueForm'].submit();
	}
}

function createDataValue(catalogMinLength) {
	resetMessages();
	$('#catalogNameLocate').val($.trim($('#catalogNameLocate').val()));
	var key = $("#catalogNameLocate").val().toUpperCase();
	if(key == null || key == ""){
		addErrorToNotificationTray('Please choose a Catalog Name');
		openTrayNotification();
		return false;
	}
	$('#primaryCodeForSave').val($.trim($('#primaryCodeForSave').val()));
	var primaryCode = $("#primaryCodeForSave").val().toUpperCase();
	if(primaryCode == null || primaryCode == ""){
		addErrorToNotificationTray('Please enter data in Value field');
		openTrayNotification();
		return false;
	}
	$('#secondaryCodeForSave').val($.trim($('#secondaryCodeForSave').val()));
	var description = $("#secondaryCodeForSave").val();
	if(description == null || description == ""){
		addErrorToNotificationTray('Please enter data in Description field');
		openTrayNotification();
		return false;
	}
	$('#descriptionForSave').val($.trim($('#descriptionForSave').val()));
	var description = $("#descriptionForSave").val();
	if(description == null || description == ""){
		addErrorToNotificationTray('Please enter data in Definition field');
		openTrayNotification();
		return false;
	} else {
		if(description.length > 240) {
			addErrorToNotificationTray('Definition field should have a maximum of 240 characters');
			openTrayNotification();
			return false;
		}
	}
	
	if(false == alphaNumericCheck(primaryCode)) {
		addErrorToNotificationTray('Value can have only alphanumberic characters');
		openTrayNotification();
		return false;
	}
	
	document.forms['referenceDataValueForm'].action="../referencedata/addItem.html";
	document.forms['referenceDataValueForm'].submit();
}

function alphaNumericCheck(inputValue) {
	var check = false;
	for(var count = 0; count < inputValue.length; count++) {
		var unicode = inputValue.charCodeAt(count);
		if(unicode >= 48 && unicode <= 57 ) {
			check = true;
		} else if(unicode >= 65 && unicode <= 90) {
			check = true;
		} else if(unicode >= 97 && unicode <= 122) {
			check = true;
		} else {
			check = false;
			return check;
		}
	}
}

function viewRefreshedPage(isAdmin) {

	$('#primaryCodeForSave').val('');
	$('#secondaryCodeForSave').val('');
	$('#descriptionForSave').val('');	
	document.getElementById('initialLoad').style.display= 'none';
	document.getElementById('userBasedForUpdate').style.display= 'none';
	document.getElementById('userBasedForCreate').style.display= 'block';
	document.getElementById('referenceDataValueSectionTwo').style.display= 'block';
	if("true" == isAdmin) {
		document.getElementById('referenceDataValueSectionThree').style.display= 'block';
		document.getElementById('primaryCodeForSave').disabled = false;
	}	
	dataValueSniffer();
}

function clearOnDataValuePageReLoad(isAdmin) {
	
	if ("true" == isAdmin && document.getElementById('referenceDataValueSectionThree').style.visibility == '') {	
		viewRefreshedPage(isAdmin);
	}
	if (document.getElementById('referenceDataValueSectionTwo').style.visibility == '') {
		clearDataValueHighLight();
	}
}
/* Reference Data Values - END */

