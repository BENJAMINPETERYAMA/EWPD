 function enableField(checkName, fieldName, nvFieldName){
    	$("#"+fieldName).val(''); 
    	$("#"+nvFieldName).val(''); 
       	if ($("#"+checkName).is(':checked') ) {
      		$("#"+fieldName). removeClass("inputbox50Disabled");
            $("#"+fieldName). addClass("inputbox50");
            $("#"+fieldName).attr('disabled', false);
            $("#"+nvFieldName). removeClass("inputbox30Disabled");
            $("#"+nvFieldName). addClass("inputbox30");
            $("#"+nvFieldName).attr('disabled', false);
      	}else{
            $("#"+fieldName). removeClass("inputbox50");
            $("#"+fieldName). addClass("inputbox50Disabled");
            $("#"+fieldName).attr('disabled', true);
            $("#"+nvFieldName). removeClass("inputbox30");
            $("#"+nvFieldName). addClass("inputbox30Disabled");
            $("#"+nvFieldName).attr('disabled', true);
      	}
 }
 
 function enableEB03Update(){
    	$("#uEB03Id").val(''); 
    	$("#nvEB03Id").val(''); 
       	if ($("#isUdtEB03").is(':checked') ) {
      		$("#uEB03Id"). removeClass("inputbox50Disabled");
            $("#uEB03Id"). addClass("inputbox50");
            $("#uEB03Id").attr('disabled', false);
            $("#nvEB03Id"). removeClass("inputbox50Disabled");
            $("#nvEB03Id"). addClass("inputbox50");
            $("#nvEB03Id").attr('disabled', false);
      	}else{
            $("#uEB03Id"). removeClass("inputbox50");
            $("#uEB03Id"). addClass("inputbox50Disabled");
            $("#uEB03Id").attr('disabled', true);
            $("#nvEB03Id"). removeClass("inputbox50");
            $("#nvEB03Id"). addClass("inputbox50Disabled");
            $("#nvEB03Id").attr('disabled', true);
      	}
 }
 
$(document).ready(function(){
	$('#nvEB01Id').blur(function() {
	   $('#nvEB01Id').val($('#nvEB01Id').val().toUpperCase());
	});
	var variableFormat = "";
	$("#nvEB01Id").autocomplete({ 
		source: function(request, response) {
					$.ajax({
						url: autoPopulateUrl,
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=EB01"+"&varformat="+variableFormat,
						success: function(data) {
						cache: false,
						response(data.rows);
						list = data.rows;
						}
					});
				}
	})
	
	$('#nvEB02Id').blur(function() {
	   $('#nvEB02Id').val($('#nvEB02Id').val().toUpperCase());
	});
	$("#nvEB02Id").autocomplete({ 
		source: function(request, response) {
					$.ajax({
						url: autoPopulateUrl,
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=EB02",
						success: function(data) {
						cache: false,
						response(data.rows);
						list = data.rows;
						}
					});
				}
	})
	
	$('#nvEB06Id').blur(function() {
	   $('#nvEB06Id').val($('#nvEB06Id').val().toUpperCase());
	});
	$("#nvEB06Id").autocomplete({ 
		source: function(request, response) {
					$.ajax({
						url: autoPopulateUrl,
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=EB06",
						success: function(data) {
						cache: false,
						response(data.rows);
						list = data.rows;
						}
					});
				}
	})
	
	$('#nvEB09Id').blur(function() {
	   $('#nvEB09Id').val($('#nvEB09Id').val().toUpperCase());
	});
	$("#nvEB09Id").autocomplete({ 
		source: function(request, response) {
					$.ajax({
						url: autoPopulateUrl,
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=EB09",
						success: function(data) {
						cache: false,
						response(data.rows);
						list = data.rows;
						}
					});
				}
	})
	
	$('#nvHSD01Id').blur(function() {
	   $('#nvHSD01Id').val($('#nvHSD01Id').val().toUpperCase());
	});
	$("#nvHSD01Id").autocomplete({ 
		source: function(request, response) {
					$.ajax({
						url: autoPopulateUrl,
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=HSD01",
						success: function(data) {
						cache: false,
						response(data.rows);
						list = data.rows;
						}
					});
				}
	})
	
	$('#nvHSD02Id').blur(function() {
	   $('#nvHSD02Id').val($('#nvHSD02Id').val().toUpperCase());
	});
		
	$('#nvHSD03Id').blur(function() {
	   $('#nvHSD03Id').val($('#nvHSD03Id').val().toUpperCase());
	});
	$("#nvHSD03Id").autocomplete({ 
		source: function(request, response) {
					$.ajax({
						url: autoPopulateUrl,
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=HSD03",
						success: function(data) {
						cache: false,
						response(data.rows);
						list = data.rows;
						}
					});
				}
	})
	
	$('#nvHSD04Id').blur(function() {
	   $('#nvHSD04Id').val($('#nvHSD04Id').val().toUpperCase());
	});
		
	$('#nvHSD05Id').blur(function() {
	   $('#nvHSD05Id').val($('#nvHSD05Id').val().toUpperCase());
	});
	$("#nvHSD05Id").autocomplete({ 
		source: function(request, response) {
					$.ajax({
						url: autoPopulateUrl,
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=HSD05",
						success: function(data) {
						cache: false,
						response(data.rows);
						list = data.rows;
						}
					});
				}
	})
	
	
	$('#nvHSD06Id').blur(function() {
	   $('#nvHSD06Id').val($('#nvHSD06Id').val().toUpperCase());
	});
		
	$('#nvHSD07Id').blur(function() {
	   $('#nvHSD07Id').val($('#nvHSD07Id').val().toUpperCase());
	});
	$("#nvHSD07Id").autocomplete({ 
		source: function(request, response) {
					$.ajax({
						url: autoPopulateUrl,
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=HSD07",
						success: function(data) {
						cache: false,
						response(data.rows);
						list = data.rows;
						}
					});
				}
	})
	
	
	$('#nvHSD08Id').blur(function() {
	   $('#nvHSD08Id').val($('#nvHSD08Id').val().toUpperCase());
	});
	$("#nvHSD08Id").autocomplete({ 
		source: function(request, response) {
					$.ajax({
						url: autoPopulateUrl,
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=HSD08",
						success: function(data) {
						cache: false,
						response(data.rows);
						list = data.rows;
						}
					});
				}
	})
	
	$('#nviii02Id').blur(function() {
	   $('#nviii02Id').val($('#nviii02Id').val().toUpperCase());
	});
	$("#nviii02Id").autocomplete({ 
		source: function(request, response) {
					$.ajax({
						url: autoPopulateUrl,
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=III02",
						success: function(data) {
						cache: false,
						response(data.rows);
						list = data.rows;
						}
					});
				}
	})
	
	$('#nvnoteTypeId').blur(function() {
	   $('#nvnoteTypeId').val($('#nvnoteTypeId').val().toUpperCase());
	});
	$("#nvnoteTypeId").autocomplete({ 
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						// changed for Reference Data Values
						data: "key="+request.term + "&name=NOTE_TYPE_CODE",
						success: function(data) {
						cache: false,
						response(data.rows);
						list = data.rows;
						}
					});
				}
	})
	
	$('#nvnoteTypeCodeId').blur(function() {
	   $('#nvnoteTypeCodeId').val($('#nvnoteTypeCodeId').val().toUpperCase());
	});
	$("#nvnoteTypeCodeId").autocomplete({ 
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocompletelistcreatepage.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=NOTE_TYPE_CODE",
						success: function(data) {
						cache: false,
						response(data.rows);
						list = data.rows;
						}
					});
				}
	})
	
	$('#uEB01Id').blur(function() {
	   $('#uEB01Id').val($('#uEB01Id').val().toUpperCase());
	});
	$('#uEB02Id').blur(function() {
	   $('#uEB02Id').val($('#uEB02Id').val().toUpperCase());
	});
	$('#nvEB03Id').blur(function() {
	   $('#nvEB03Id').val($('#nvEB03Id').val().toUpperCase());
	});
	$('#uEB03Id').blur(function() {
		$('#uEB03Id').val($('#uEB03Id').val().toUpperCase());
	});
	$('#uEB06Id').blur(function() {
	   $('#uEB06Id').val($('#uEB06Id').val().toUpperCase());
	});
	$('#uEB09Id').blur(function() {
	   $('#uEB09Id').val($('#uEB09Id').val().toUpperCase());
	});
	
	$('#uHSD01Id').blur(function() {
	   $('#uHSD01Id').val($('#uHSD01Id').val().toUpperCase());
	});
	$('#uHSD02Id').blur(function() {
	   $('#uHSD02Id').val($('#uHSD02Id').val().toUpperCase());
	});
	$('#uHSD03Id').blur(function() {
	   $('#uHSD03Id').val($('#uHSD03Id').val().toUpperCase());
	});
	$('#uHSD04Id').blur(function() {
	   $('#uHSD04Id').val($('#uHSD04Id').val().toUpperCase());
	});
	$('#uHSD05Id').blur(function() {
	   $('#uHSD05Id').val($('#uHSD05Id').val().toUpperCase());
	});
	$('#uHSD06Id').blur(function() {
	   $('#uHSD06Id').val($('#uHSD06Id').val().toUpperCase());
	});
	$('#uHSD07Id').blur(function() {
	   $('#uHSD07Id').val($('#uHSD07Id').val().toUpperCase());
	});
	$('#uHSD08Id').blur(function() {
	   $('#uHSD08Id').val($('#uHSD08Id').val().toUpperCase());
	});
	
	$('#unoteTypeId').blur(function() {
	   $('#unoteTypeId').val($('#unoteTypeId').val().toUpperCase());
	});
	
	$('#uiii02Id').blur(function() {
	   $('#uiii02Id').val($('#uiii02Id').val().toUpperCase());
	});
	
	$('#unoteTypeCodeId').blur(function() {
	   $('#unoteTypeCodeId').val($('#unoteTypeCodeId').val().toUpperCase());
	});
	
	$('#massUpdateConfirmationDiv').hide();
	$("progressPopup").hide();
  });
  

	function closevariableerrorpopup(){
		$("#varlockstatusPopUpDiv").dialog( "close" );
	}

  var massUpdateTrigger = null;
  function openMassUpdateConfirmation(trigger){
	  closevariableerrorpopup();
  	if(validateRecordSelectedForMassUpdate() == false){
  		addErrorToNotificationTray('Any of the searched record(s) need to be selected for mass update.');
		openTrayNotification();
  		return;
  	}
  	massUpdateTrigger = trigger;
  	if(trigger == "update"){
  		if(validateCriteriaSelectedForMassUpdate() == false){
  			addErrorToNotificationTray('Any of the update field(s) need to be selected for mass update.');
			openTrayNotification();
  			return;
  		}
  		document.getElementById('muConformationContent').style.display= 'block';
  		document.getElementById('muConformationContent').style.height= '100px';
  		$("#muConformationContent").html(getConfirmationContent());
  	}else{
  		document.getElementById('muConformationContent').style.display= 'none';
  		document.getElementById('muConformationContent').style.height= '0px';
  		$("#muConformationContent").html('');
  	}
  	var searchCriteria = $('#hdSpsIdCriteriaId').val();
  	if(searchCriteria == 'spsid'){
		$("#muConformationTitle").html('For selected sps ids.');
	}else if(searchCriteria == 'ruleid'){
		$("#muConformationTitle").html('For selected header rules.');
	}else if(searchCriteria == 'msgtxt'){
		$("#muConformationTitle").html('For selected messages.');				
	}else if(trigger == 'lock'){
		$("#muConformationTitle").html('The selected variable will be audit locked. Please enter comment below.');				
	} else if(trigger == 'unlock'){
		$("#muConformationTitle").html('The selected variable will be audit unlocked. Please enter comment below.');				
	}else if(trigger == 'notApplicable'){
		$("#muConformationTitle").html('The selected variable will be set as NOT APPLICABLE. Please enter comment below.');				
	} else if(trigger == 'sendToTest'){
		$("#muConformationTitle").html('The selected variable will be send To Test. Please enter comment below.');				
	} else if(trigger == 'approve'){
		$("#muConformationTitle").html('The selected variable will be approved. Please enter comment below.');				
	} else {
		$("#muConformationTitle").html('For selected variables.');	
	}
  	$('#updateComments').val('');
  	$("#massUpdateConfirmationDiv").dialog({
        height:'auto',
		resizable:false,
		width:'550px',	
        show:'slide',
		title: 'Confirmation',
        modal: true
        });
  
  }
  
  function closeMassUpdateConfirmation(context,url){
	  $('#commenterror').hide();
	  var masscomment = $('#updateComments').val() ;
  	if(massUpdateTrigger == "update"){
  		url = context+'/'+url+'/update.html';
  	}else if(massUpdateTrigger == "notApplicable"){
  		url = context+'/'+url+'/markNotApplicable.html';
  	}else if(massUpdateTrigger == "sendToTest"){
  		url = context+'/'+url+'/sendToTest.html';
  	}else if(massUpdateTrigger == "approve"){
  		url = context+'/'+url+'/approve.html';
  	}
  	else if(massUpdateTrigger == "lock"){
  		url = context+'/'+url+'/lock.html';
  		if(null != masscomment && masscomment.length > 0 ){
  		  
  	  } else {
  		  $('#commenterror').show();
  		  return;
  	  }
  	}
  	else if(massUpdateTrigger == "unlock"){
  		url = context+'/'+url+'/unlock.html';
  		if(null != masscomment && masscomment.length > 0 ){
  		  
  	  } else {
  		  $('#commenterror').show();
  		  return;
  	  }
  	}
  	$('#hdMassUpdateComment').val($('#updateComments').val());
  	$('#massUpdateConfirmationDiv').dialog( "close" );
  	showProgress(0,context);
  	document.forms['submitCreateForm'].action=url;
	document.forms["submitCreateForm"].submit();
  }
  
  
  function getConfirmationContent(){
  	var str = '';
  		str = str+getSingleConfirmationContent('isUdtEB01','uEB01Id','nvEB01Id','EB01');
  		str = str+getSingleConfirmationContent('isUdtEB02','uEB02Id','nvEB02Id','EB02');
  		str = str+getSingleConfirmationContent('isUdtEB03','uEB03Id','nvEB03Id','EB03');
  		str = str+getSingleConfirmationContent('isUdtEB06','uEB06Id','nvEB06Id','EB06');
  		str = str+getSingleConfirmationContent('isUdtEB09','uEB09Id','nvEB09Id','EB09');
  		str = str+getSingleConfirmationContent('isUdtHSD01','uHSD01Id','nvHSD01Id','HSD01');
  		str = str+getSingleConfirmationContent('isUdtHSD02','uHSD02Id','nvHSD02Id','HSD02');
  		str = str+getSingleConfirmationContent('isUdtHSD03','uHSD03Id','nvHSD03Id','HSD03');
  		str = str+getSingleConfirmationContent('isUdtHSD04','uHSD04Id','nvHSD04Id','HSD04');
  		str = str+getSingleConfirmationContent('isUdtHSD05','uHSD05Id','nvHSD05Id','HSD05');
  		str = str+getSingleConfirmationContent('isUdtHSD06','uHSD06Id','nvHSD06Id','HSD06');
  		str = str+getSingleConfirmationContent('isUdtHSD07','uHSD07Id','nvHSD07Id','HSD07');
  		str = str+getSingleConfirmationContent('isUdtHSD08','uHSD08Id','nvHSD08Id','HSD08');
  		str = str+getSingleConfirmationContent('isUdtiii02','uiii02Id','nviii02Id','III02');
  		str = str+getSingleConfirmationContent('isUdtnoteType','unoteTypeId','nvnoteTypeId','Note Type Code');
  		str = str+getSingleConfirmationContent('isUdtnoteTypeCode','unoteTypeCodeId','nvnoteTypeCodeId','Note Type');
  		str = str+getSingleConfirmationContent('isUdtMsgTxt','uMsgTxtId','nvMsgTxtId','Message Text');
  	return str;
  }
  function hasMultipleValues(value){
  	var splitResult = value.split(",");
  	if(splitResult.length >1){
  		return true;	
  	} 	
  	return false;
  }
  
  function formatValue(value){
  	var newValue = '';
  	var splitResult = value.split(",");
  	var flag = false;
  	 for (var j = 0; j < splitResult.length; j++){ 
  	 	var s = jQuery.trim(splitResult[j]);
  	 	if(s != ''){
  	 		if(flag){
  	 			newValue = newValue+',';
  	 		}
  	 		newValue = newValue+s.replace(',','');
  	 		flag = true;
  	 	}	
  	 }
  	return newValue;
  }
  
  function getSingleConfirmationContent(checkName, fieldName, nvFieldName, prefix){
  	var str = '';
       	if ($("#"+checkName).is(':checked') ) {
       		var oldValue = formatValue($("#"+fieldName).val());
       		var newValue = formatValue($("#"+nvFieldName).val());
       		if(checkName == 'isUdtEB03'){
  				if(oldValue == '' && newValue != ''){
	       		//add all
	       			str = 'Add '+newValue+' to '+prefix+'.<br>';
	       		}else if(oldValue != '' && newValue == ''){
				//delete
       				str = 'Remove '+oldValue+' from '+prefix+'.<br>';
	       		}else if(oldValue != '' && newValue != ''){
	       		//update 
	       			if(hasMultipleValues(oldValue) && hasMultipleValues(newValue)){
	       				str = prefix+" Criteria : "+oldValue+" with New Value "+newValue+" is an invalid update and will be discarded.<br>";
	       			}else{
	       				str = 'Remove '+oldValue+' value(s) from '+prefix+' and add '+newValue+'. '+newValue+' will not be added if '+oldValue+' not present.<br>';
	       			}
	       		}else if(oldValue == '' && newValue == ''){
	       			str = 'Clear '+prefix+'.<br>';
	       		} 
  			}else{
				if(oldValue == '' && newValue == ''){
				//delete all
					str = 'Clear '+prefix+'.<br>';
				}else if(oldValue != '' && newValue == ''){
				//delete
       				str = 'Clear '+prefix+' for records with '+prefix+' in '+oldValue+'.<br>';
	       		}else if(oldValue == '' && newValue != ''){
	       		//update all
	       			str = 'Update '+prefix+' with '+newValue+'.<br>';
	       		}else if(oldValue != '' && newValue != ''){
	       		//update
	       			str = 'Update '+prefix+' with '+newValue+' for records having '+prefix+' values as '+oldValue+'.<br>';
	       		}
  			}
      	}
  	return str;
  }
  
  function getTheProgress(context){
	$.ajax({
		url: context+"/massupdateebx/progressPercentage.ajax",
		dataType: "html",
		type: "POST",			
		data: "",
		success: function(data) {
		cache: false,
		showProgress(parseInt(data),context);}
	});
}

function showProgress(count,context){
	$("#progressbar").progressbar({ value: count });
	if(count < 100){
		$("#progressPopup").dialog({
			height:'80',
			width:'500px',	
			show:'slide',
			modal: true,
			resizable: false,	
			closeOnEscape:false,	
			open: function(event, ui) { 
			//hide close button.
			$(this).parent().children().children('.ui-dialog-titlebar-close').hide();
			},
			title: 'Mass update progress - '+count+'% Completed'
	
		});
		$("#progressbar").progressbar({ value: count });
		var t=setTimeout("getTheProgress('"+context+"')",1000);
	}else{
		$("#progressPopup").dialog( "close" );
		refreshSearch();
	}
		
}

 function populateEbxUpdate(searchCriteria){
 	if(searchCriteria == 'spsid'){
		document.getElementById('updateFieldTableSps').style.display= 'block';
        document.getElementById('updateFieldTableRule').style.display= 'none';
        document.getElementById('updateFieldTableMsg').style.display= 'none';
	}
	if(searchCriteria == 'ruleid'){
        document.getElementById('updateFieldTableSps').style.display= 'none';
        document.getElementById('updateFieldTableRule').style.display= 'block';
        document.getElementById('updateFieldTableMsg').style.display= 'none';
	}
	if(searchCriteria == 'msgtxt'){
        document.getElementById('updateFieldTableSps').style.display= 'none';
        document.getElementById('updateFieldTableRule').style.display= 'none';
        document.getElementById('updateFieldTableMsg').style.display= 'block';			
	}
	clearFields();
 }

 function clearFields(){
 	var checkBoxes=['EB01','EB02','EB06','EB09',
 				'HSD01','HSD02','HSD03','HSD04','HSD05','HSD06','HSD07','HSD08',
 				'iii02','noteType','noteTypeCode'];
	
    for (var j = 0; j < checkBoxes.length; j++){ 
         $("#isUdt"+checkBoxes[j]).attr('checked', false);
         enableField("isUdt"+checkBoxes[j], "u"+checkBoxes[j]+"Id", "nv"+checkBoxes[j]+"Id");
    }  
    $("#isUdtEB03").attr('checked', false);
    enableEB03Update();
     
 }
 
 function updateAllSelected(context, checkBox, pageNo){
  	$.ajax({
		url: context+"/massupdateebx/storeAllSelected.ajax",
		dataType: "html",
		type: "POST",			
		data: "checked="+checkBox.checked+"&pageNo="+pageNo,
		success: function(data) {
		cache: false,
		setSelectedPage(data);
		}
	});
	
	$("#inSearchDivSub input").each( function() {
		$(this).attr("checked",checkBox.checked);
	})
 }
 
 function checkAllOrNot(){
 	var allChecked = true;
 	$("#inSearchDivSub input").each( function() {
		if($(this).attr("name") == "searchCheckBox" && ($(this).attr("checked") == "false" || $(this).attr("checked") == false)){
			allChecked = false;
		}
		
	})
	$("#headerCheck").attr("checked",allChecked);
 }
 
 function updateSelected(context, checkBox, spsid, ruleid, variableid,pageNo){
 	$.ajax({
		url: context+"/massupdateebx/storeSelected.ajax",
		dataType: "html",
		type: "POST",			
		data: "spsid="+escape(spsid)+"&ruleid="+escape(ruleid)+"&variableid="+encodeURIComponent(variableid)+"&checked="+checkBox.checked+"&pageNo="+pageNo,
		success: function(data) {
		cache: false,
		setSelectedPage(data);
		}
	});
	checkAllOrNot();
 }
 
	function checkLockstatus(context, trigger){
		$('#hdMassUpdateComment').val($('#updateComments').val());
		if(validateRecordSelectedForMassUpdate() == false){
	  		addErrorToNotificationTray('Any of the searched record(s) need to be selected for mass update.');
			openTrayNotification();
	  		return;
	  	}
		$.ajax({
				url:context+"/massupdateebx/checkLockStatus.ajax",
				dataType:"html",
				type:"POST",
				data: "trigger="+trigger,
				success: function(data){
				if (matchVar(data) != -1){
					$("#varlockstatusPopUpDiv").dialog( "close" );
					openMassUpdateConfirmation(trigger);
				} else {
						$("#varlockstatusPopUpDiv").html(data);
						$("#varlockstatusPopUpDiv").dialog({
					            height:'auto',
					            width : '350',
								position:'center',
								resizable : 'false',
								zIndex: 9999,
								title: 'Confirm',
					            modal: true,
					            open : function() {
		            							var newTitle = "Confirm";
												$('#varlockstatusPopUpDiv').dialog( "option" , "title" ,newTitle);
		        				}
					    });
				} 
		}
    	});	
 	}
 	
	function matchVar(dataString){
		var str=dataString;
		var output = str.search(/No Variable found/i);
		return output;
	}
	
 function setSelectedPage(value){
 	$("#selectedPageDisplayContainer").html('' + value);
  }
  
  function showUpdateTable(searchCriteria){
  	openAccordion = "Accordion1Content";
	flag = true;
	runAccordian = true;
	runAccordionSearch(2);
	document.getElementById('massUpdateContainer').style.display= 'block';
	checkAllOrNot();
	document.getElementById('sepLnkUpdateId').style.display= '';
	document.getElementById('lnkUpdateId').style.display= 'block';
	if(searchCriteria == 'msgtxt'){
        document.getElementById('sepLnkNotApplicableId').style.display= '';
		document.getElementById('lnkNotApplicableId').style.display= 'none';		
	}else{
		if(document.getElementById('sepLnkNotApplicableId') != null){
			document.getElementById('sepLnkNotApplicableId').style.display= '';
		}
		if(document.getElementById('lnkNotApplicableId') != null){
			document.getElementById('lnkNotApplicableId').style.display= 'block';
		}
		if(document.getElementById('lnklockId') != null){
			document.getElementById('lnklockId').style.display= 'block';
		}
		if(document.getElementById('sepLnklockId') != null) {
			document.getElementById('sepLnklockId').style.display= '';
		}
		if(document.getElementById('lnkunlockId') != null ){
			document.getElementById('lnkunlockId').style.display= 'block';
		}
		if(document.getElementById('sepLnkunlockId') != null) {
			document.getElementById('sepLnkunlockId').style.display= '';
		}
	}
	document.getElementById('sepLnkSendTestId').style.display= '';
	document.getElementById('lnkSendTestId').style.display= 'block';
	document.getElementById('sepLnkApproveId').style.display= '';
	document.getElementById('lnkApproveId').style.display= 'block';
	openAccordion = "Accordion1Content";
  }
  
  function validateRecordSelectedForMassUpdate(){
  	var selectedText = $("#selectedPageDisplayContainer").html();
  	if(jQuery.trim(selectedText) == ''){
  		return false
  	}else{
  		return true;
  	}
  }
  function validateCriteriaSelectedForMassUpdate(){
  	var checkBoxes=['EB01','EB02','EB03','EB06','EB09',
 				'HSD01','HSD02','HSD03','HSD04','HSD05','HSD06','HSD07','HSD08',
 				'iii02','noteType','noteTypeCode','MsgTxt'];
	var anyChecked = false;
    for (var j = 0; j < checkBoxes.length; j++){ 
         if($("#isUdt"+checkBoxes[j]).attr('checked') == true){
         	anyChecked = true;
         }
    }  
    
    return anyChecked;
  }
  
  function enableMsgField(checkName, fieldName, nvFieldName){
  	$("#"+fieldName).val(''); 
  	$("#"+nvFieldName).val(''); 
     	if ($("#"+checkName).is(':checked') ) {
    		$("#"+fieldName). removeClass("inputboxMsgDisabled");
          $("#"+fieldName). addClass("inputboxMsg");
          $("#"+fieldName).attr('disabled', false);
          $("#"+nvFieldName). removeClass("inputboxMsgDisabled");
          $("#"+nvFieldName). addClass("inputboxMsg");
          $("#"+nvFieldName).attr('disabled', false);
    	}else{
          $("#"+fieldName). removeClass("inputboxMsg");
          $("#"+fieldName). addClass("inputboxMsgDisabled");
          $("#"+fieldName).attr('disabled', true);
          $("#"+nvFieldName). removeClass("inputboxMsg");
          $("#"+nvFieldName). addClass("inputboxMsgDisabled");
          $("#"+nvFieldName).attr('disabled', true);
    	}
}