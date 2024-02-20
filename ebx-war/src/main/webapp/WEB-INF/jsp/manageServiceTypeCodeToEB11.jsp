<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.net.*" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@include file="/WEB-INF/jspf/pageHead.jspf"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ebxmapping.js"></script>
<script type="text/javascript">
/****************** EB01-Data Type - start ******************/
function enterKeyPress(e) {
	if (e.keyCode == 13){
		$('#lobName').val($('#lobName').val().toUpperCase());	
		$('#mbuName').val($('#mbuName').val().toUpperCase());
	 	document.getElementById('Locate').click();
     }
}

document.attachEvent("onkeydown", my_onkeydown_handler); 
function my_onkeydown_handler() 
{ 
	switch (event.keyCode) 
	{ 
		case 116 : 
		
			event.returnValue = false;
			event.keyCode = 0;
			
			var action ;
			action = "${context}/referencedata/showManageServiceTypeCodeToEB11Page.html";
			window.location.href = action; 
			break; 
	}
}

$(document).ready(function(){
	$('#lobName').blur(function() {
		$('#lobName').val($('#lobName').val().toUpperCase());		
	});
});
$(document).ready(function(){
	$('#mbuName').blur(function() {
		$('#mbuName').val($('#mbuName').val().toUpperCase());		
	});
});
$(document).ready(function(){
	$('#lobText').blur(function() {
		$('#lobText').val($('#lobText').val().toUpperCase());		
	});
});	
	
$(document).ready(function(){
	$('#mbuText').blur(function() {
		$('#mbuText').val($('#mbuText').val().toUpperCase());		
	});
});	
	
 
$(document).ready(function(){
   
    var saveFromPopOut = $("#saveFromPopOut").val();
    if(saveFromPopOut == "true"){
    	serviceTypemappingsPopOut();
    }
				
});	
function disableUserCommentsDiv(){
document.getElementById('userCommentsDeleteAllDialog').style.display= 'none';
document.getElementById('userCommentsSaveDialog').style.display= 'none';
}


function imposeMaxLength(elementId, MaxLen, element){
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

/*  function backAction(){
		var action ;
		action = "../referencedata/closeAction.ajax";
		window.location.href = action;
} */

 function backAction(){
	document.forms['servicetypeMappingsForm'].action="../ebxspiderworkflow/back.html";
    document.forms["servicetypeMappingsForm"].submit();
}
//apply css to invalid hippaSegment label
$(document).ready(function(){
		$("label").each(function(){
			var labelText = $.trim($(this).text());
			if(labelText!=null && labelText.length > 0){
				if(labelText == $.trim(invalidDataType)){
					$(this).addClass('invalid_hippacode_value');
				}
			}
		});
});

</script>
</head>
<script type="text/javascript">
function displaydiv() {
var fromaction=$("#fromaction").val();
var categoryCode = $("#categoryCode").val();
		var system = $("#system").val();
			var categorydesc = $("#categorydesc").val();
		var EB03 = $("#EB03").val();
		if(null!=fromaction){
		  if(fromaction=='DELETE'||fromaction=='SELECT'||fromaction=='CREATE'||fromaction=='UPDATE'){
		  document.getElementById('dynamicRenderer').style.display= 'block';
					document.getElementById('exclusionSectionTwo1').style.display= 'block';
					document.getElementById('exclusionSectionTwo1').style.display= 'block';
		  }
		}
}

function sniffer() {
	   if(screen.height==1024) {
	   
	      document.getElementById('exclusionEditContainer1').style.height = "580px";
	      document.getElementById('exclusionSectionTwo1').style.height = "200px";
	     document.getElementById('exclusionContentSectionTwo1').style.height = "172px";
	   } else if(screen.height==960)
	   {	  
	      document.getElementById("exclusionEditContainer1").style.height= "500px";
	      document.getElementById('exclusionSectionTwo1').style.height = "145px";
	      document.getElementById('exclusionContentSectionTwo1').style.height = "117px";
	      
	   }
	   else if(screen.height==864)
	   { 
	      document.getElementById("exclusionEditContainer1").style.height= "497px";
	      document.getElementById('exclusionSectionTwo1').style.height = "175px";
	      document.getElementById('exclusionContentSectionTwo1').style.height = "148px";
	   }
	   else if(screen.height==720)
	   {  
	      document.getElementById("exclusionEditContainer1").style.height= "360px";
	      document.getElementById('exclusionSectionTwo1').style.height = "95px";
	      document.getElementById('exclusionContentSectionTwo1').style.height = "67px";
	   }
	    else if(screen.height==768)
	   {  
	      document.getElementById("exclusionEditContainer1").style.height= "363 px";
	      document.getElementById('exclusionSectionTwo1').style.height = "104px";
	      document.getElementById('exclusionContentSectionTwo1').style.height = "76px";
	   }
	   	else if(screen.height==600)
	   {  
	      document.getElementById("exclusionEditContainer1").style.height= "291 px";
	      document.getElementById('exclusionSectionTwo1').style.height = "50px";
	      document.getElementById('exclusionContentSectionTwo1').style.height = "33px";
	   } else {
	     
	   		var setPixMain = (screen.height * 0.6) + "px";
	   		var setPixSub = ((screen.height * 0.6) - 274) + "px"
	   		var setPixSubTask = ((screen.height * 0.6) - 285) + "px"
	   		 document.getElementById("exclusionEditContainer1").style.height= setPixMain;
	   		 document.getElementById("exclusionSectionTwo1").style.height= setPixSub;
	   		document.getElementById("exclusionContentSectionTwo1").style.height= setPixSubTask;
	   }
}
 
</script>
<script type="text/javascript">
/****javaScript functions for Service Type Code - EB11 Mappings******/

//method is called on click of the Locate button
function fetchLobMbuAssociation() {

		var lobName = $.trim($("#lobName").val());
		var mbuName = $.trim($("#mbuName").val());
			$.ajax({
			url: "${context}/referencedata/fetchServiceTypeCodeToEB11Mapping.ajax",
		dataType: "html",
		type: "POST",
		data: "lobName="+escape(lobName)+"&mbuName="+escape(mbuName),
		success: function(data) {
				document.getElementById('dynamicRenderer').style.display= 'block';
				document.getElementById('exclusionSectionTwo1').style.display= 'block';
				document.getElementById('exclusionSectionThree1').style.display= 'none';
				document.getElementById('editpage').style.display= 'none';
				document.getElementById('initialLoad').style.display= 'block';
					document.getElementById('createupdate').style.display= 'none';
				$("#dynamicRenderer").html(data);
					
			}
		});
 }
 
 //method is to view the STC-EB11 mappings of an LOB
function viewServiceTypeMappings(lobId) {
			$.ajax({
			url: "${context}/referencedata/viewServiceTypeCodeToEB11Mapping.ajax",
		dataType: "html",
		type: "POST",
		data: "lobId="+escape(lobId),
		success: function(data) {
				 $("#viewMappingDialog").html(data);
                  $("#viewMappingDialog").dialog({
                        height:'auto',	
						width:'600px',					
                        show:'slide',
                        modal: true,
						resizable: false,
						title: 'Service Type Code - EB11 Association'

                  });
                  $("#viewMappingDialog").dialog();
					
			}
		});

 }
 function editServiceTypeMappings(lobId){
 var action = "update";
	$.ajax({
			url: "${context}/referencedata/editServiceTypeCodeToEB11Mapping.ajax",
			dataType: "html",
			type: "POST",
			data: "lobId="+escape(lobId)+"&action"+action,
			success: function(data) {
						document.getElementById('exclusionSectionThree1').style.display= 'block';
						document.getElementById('editpage').style.display= 'block';
						document.getElementById('initialLoad').style.display= 'none';
						document.getElementById('createupdate').style.display= 'block';
					$("#editpage").html(data);
					
			}
		});

}
 function viewHistoryofServiceTypeMappings(lobName){
 resetMessages();
	var key = "";
	if(lobName == ""){
		key =  $.trim($("#lobNamehidden").val());
	}else{
		key = lobName;
	}
	$.ajax({
		url: "../referencedata/viewHistoryOfServiceTypeCodeToEB11Mapping.ajax",
		dataType: "html",
		type: "POST",
		data: "lobName="+key,
		success: function(data) {
			$("#viewHistoryDivForAssociation").html(data);
			$("#viewHistoryDivForAssociation").dialog({
				width:'620',	
				height:'auto',
				resizable : 'false',
				title: 'View History',
				modal: true				
			});
			
		}
	});
 
 }
 
 function editLobMbu(){
 	$('#lobLabel').hide();
 	$('#lobText').show();
		
	$('#mbuLabel').hide();
 	$('#mbuText').show();
 	$('#isSSB').removeAttr("disabled"); 
 	$('#editTD').hide();
 	
 	$("#actionhidden").val("update");
 	
 }
 
  function editLobMbuPopOut(){
 	$('#lobLabelPopOut').hide();
 	$('#lobTextPopOut').show();
		
	$('#mbuLabelPopOut').hide();
 	$('#mbuTextPopOut').show();
 	$('#isSSBPopOut').removeAttr("disabled"); 
 	$('#editTDPopOut').hide();
 	if($("#actionhidden").val() == "create"){
 		$("#actionhiddenPopOut").val("create");
 	}else{
 		$("#actionhiddenPopOut").val("update");
 	}
 }
  
 function saveServiceTypeMappings(){
 	var lobName = $("#lobText").val();
 	if(lobName == ""){
 	addErrorToNotificationTray("LOB Name is mandatory for a mapping.");
			openTrayNotification();	
			$('#userCommentsSaveDialog').dialog('close');
			return false;
 	}
 	
 	//Check if EB03/EB11 is missing for a mapping.
 	var rowLength = $('#serviceTypeMappingsTable > tbody > tr').length;
	for(var i = 0; i < rowLength ; i++){
		var serviceTypeCode = $('#serviceTypeCodeId'+i).val();
		var eb11 = $('#eb11Id'+i).val();
		var placeOfService = $('#placeOfServiceId'+i).val();
		var serviceTypeCodeDesc = $('#serviceTypeCodeIdLabel'+i).text();
		var eb11Desc = $('#eb11IdLabel'+i).text();
		var placeOfServiceDesc = $('#placeOfServiceIdLabel'+i).text();
		 
		 if(serviceTypeCode== "" && (eb11 != "" || placeOfService != "" )){
		 	addErrorToNotificationTray("EB03 and EB11 is mandatory for a mapping.");
			openTrayNotification();	
			$('#userCommentsSaveDialog').dialog('close');
			return false;
		 }
		 if(eb11== "" && (serviceTypeCode != "" || placeOfService != "" )){
		 	addErrorToNotificationTray("EB03 and EB11 is mandatory for a mapping.");
			openTrayNotification();	
			$('#userCommentsSaveDialog').dialog('close');
			return false;
		 }
		  if(serviceTypeCodeDesc == invalidHippaCodeValue || eb11Desc == invalidHippaCodeValue|| placeOfServiceDesc == invalidHippaCodeValue ){
			var errorMessage = "Invalid Service Type Mapping:";
			if(serviceTypeCode != ''){
				errorMessage = errorMessage+"  EB03 ='"+serviceTypeCode+"'";
			}
			if(eb11 != ''){
				errorMessage = errorMessage+"  EB11 ='"+eb11+"'";
			}
			if(placeOfService != ''){
				errorMessage = errorMessage+"  POS ='"+placeOfService+"'";
			}
			addErrorToNotificationTray(errorMessage);
			openTrayNotification();	
			$('#userCommentsSaveDialog').dialog('close');
			return false;
			}
	}
	//Set the description from the label 
	setDescriptionServiceTypeMappings();
	$("#searchActionLobNameSave").val($.trim($("#lobName").val()));
	$("#searchActionMbuNameSave").val($.trim($("#mbuName").val()));
	$("#lobValueToSave").val($.trim($("#lobText").val()));
	$("#mbuValueToSave").val($.trim($("#mbuText").val()));
	$("#lobIdhidden").val($.trim($("#lobidhidden").val()));
	$("#actionToSave").val($.trim($("#actionhidden").val()));
	 var isSSBCheckedIn = $('#isSSB').attr("checked");
 	 if(isSSBCheckedIn == true){
 	 	$("#ssbValue").val("Y");
 	 }else{
 	 	$("#ssbValue").val("N");
 	 }
	
	$("#userCommentsSaveDialog table#saveActionUserCommentsTable").css("border-top","1px solid black");
		$("#userCommentsSaveDialog").dialog({
                       height:'auto',
					width:'450px',	
					resizable:false,
                       show:'slide',
					title: 'Confirm',
                       modal: true
                 });
	$("#userCommentsSaveDialog").dialog();
	}
function setDescriptionServiceTypeMappings(){
	var rowLength = $('#serviceTypeMappingsTable > tbody > tr').length;
	for(var i = 0; i < rowLength ; i++){
		$('#serviceTypeCodeDesc'+i).val($('#serviceTypeCodeIdLabel'+i).text());
		$('#eb11Desc'+i).val($('#eb11IdLabel'+i).text());
		$('#placeOfServiceDesc'+i).val($('#placeOfServiceIdLabel'+i).text());
	}
} 
function setDescriptionServiceTypeMappingsPopOut(){
	var rowLength = $('#serviceTypeMappingsTablePopOut > tbody > tr').length;
	for(var i = 0; i < rowLength ; i++){
		$('#serviceTypeCodeDescPopOut'+i).val($('#serviceTypeCodePopOutIdLabel'+i).text());
		$('#eb11DescPopOut'+i).val($('#eb11IdPopOutLabel'+i).text());
		$('#placeOfServiceDescPopOut'+i).val($('#placeOfServicePopOutIdLabel'+i).text());
	}
}
function saveUserCommentsForSaveAction(){	
var userComments = $("#saveUserCommentsTextArea").val();
		if(userComments == null || userComments == ""){
		var message = "User Comments are mandatory";			
		addErrorToNotificationTray(message);
		openTrayNotification();	
		return false;
		}
	if(!imposeMaxLength('saveUserCommentsTextArea',250,'user comments')){						
			return false;
		}	
		 $("#saveUserComments").val($.trim($("#saveUserCommentsTextArea").val()));		
		document.forms['servicetypeMappingsForm'].action="${context}/referencedata/saveServiceTypeCodeToEB11Mapping.html";
		document.forms['servicetypeMappingsForm'].submit();
	}

 function createLobMbuAssociation(){
 	var action = "create";
 	var lobName = $.trim($("#lobName").val());
	var mbuName = $.trim($("#mbuName").val());
 	$.ajax({
			url: "${context}/referencedata/createServiceTypeCodeToEB11Mapping.ajax",
			dataType: "html",
			type: "POST",
			data: "action="+action+"&lobName="+escape(lobName)+"&mbuName="+escape(mbuName),
			success: function(data) {
						document.getElementById('exclusionSectionThree1').style.display= 'block';
						document.getElementById('editpage').style.display= 'block';
						document.getElementById('initialLoad').style.display= 'none';
						document.getElementById('createupdate').style.display= 'block';
						document.getElementById('exclusionSectionTwo1').style.display= 'none';
						$("#editpage").html(data);
	$('#lobLabel').hide();
 	$('#lobText').show();
		
	$('#mbuLabel').hide();
 	$('#mbuText').show();
 	$('#isSSB').removeAttr("disabled"); 
 	$('#editTD').hide();
			}
		});

 	
 }
 
 function deleteServiceTypeMappings(lobId,lobName){
 $("#lobIdWhileDelete").val(lobId);
  $("#lobNameWhileDelete").val(lobName);
		$("#userCommentsDeleteAllDialog table#deleteActionUserCommentsTable1").css("border-top","1px solid black");
				$("#userCommentsDeleteAllDialog").dialog({
	                        height:'auto',
							width:'450px',	
							resizable:false,
	                        show:'slide',
							title: 'Confirm',
	                        modal: true
	                  });
			$("#userCommentsDeleteAllDialog").dialog();
	}
  function saveUserCommentsForDeleteAssociation(){	
   
   $("#lobIdWhileDelete").val($("#lobIdWhileDelete").val());
   $("#lobNameWhileDelete").val($("#lobNameWhileDelete").val());
   
	var userComments = $("#deleteAllUserComments").val();
		if(userComments == null || userComments == ""){
		var message = "User Comments are mandatory";			
		addErrorToNotificationTray(message);
		openTrayNotification();	
		return false;
		}
	if(!imposeMaxLength('deleteAllUserComments',250,'user comments')){						
			return false;
		}				
		document.forms['userCommentsDeleteAllActionDialogForm'].submit();
	}
 
 
 
  function serviceTypemappingsPopOut(){
 	$.ajax({
		url: "../referencedata/serviceTypeMappingsPopOut.html",
		dataType: "html",
		type: "POST",
		success: function(data) {
			
			$("#servicetypeMappingsPopOutDiv").html(data);
			$("#servicetypeMappingsPopOutDiv").dialog({
				height:'570',
				width:'920px',	
				resizable : 'false',
				title: " <span style='clear: right;float:left;'>Service Type Mappings Pop Out</span> <span style='float: right;'>  <a href='#' onclick='serviceTypemappingsPopIn();'> <img src='../images/PopIn_icon.gif' alt='Pop In' id='popin' title='Pop In' width='11px' height='11px'/> </a> </span>",	
				modal: true
			});
			 //set lob value to the lob label in the popout
			 $('#lobLabelPopOut').text($('#lobText').val());
			 //set lob value to the lob text in the popout
			 $('#lobTextPopOut').val($('#lobLabelPopOut').text());
			
			//set mbu value to the mbu label in the popout
			 $('#mbuLabelPopOut').text($('#mbuText').val());
			 //set mbu value to the mbu text in the popout
			 $('#mbuTextPopOut').val($('#mbuLabelPopOut').text());
			
			//set the ssb value to the ssb value in the popout
			 var isSSBChecked = $('#isSSB').attr("checked");
			 if(isSSBChecked ==  true){
				 $('#isSSBPopOut').click(); 
			 }else{
				 $('#isSSBPopOut').attr("checked", false); 
			 }
			 
			 $('#isSSBPopOut').val($('#isSSB').val());
			 $('#isSSBPopOut').attr("disabled", true); 
			 
			 //set the hidden values
			  $('#lobNamehiddenPopOut').val($('#lobNamehidden').val());
			  $('#lobidhiddenPopOut').val($('#lobidhidden').val());
			  $('#actionhiddenPopOut').val($('#actionhidden').val());
			
				
			//setting the mapping values into the popup	
			var rowLength = $('#serviceTypeMappingsTable > tbody > tr').length;
 			for(var i = 0; i < rowLength ; i++){
			 	if(i <= 2){ 
			 	//EB03 values
			 	 $('#serviceTypeCodePopOutId'+i).val($('#serviceTypeCodeId'+i).val());
			 	 $('#serviceTypeCodePopOutIdLabel'+i).text($('#serviceTypeCodeIdLabel'+i).text());	
				 $('#serviceTypeCodeDescPopOut'+i).val($('#serviceTypeCodeDesc'+i).val());
				 //EB11 Values	
				 $('#eb11PopOutId'+i).val($('#eb11Id'+i).val());
				 $('#eb11IdPopOutLabel'+i).text($('#eb11IdLabel'+i).text());	
				 $('#eb11DescPopOut'+i).val($('#eb11Desc'+i).val());
				 //POS Values	
				 $('#placeOfServicePopOutId'+i).val($('#placeOfServiceId'+i).val());
				 $('#placeOfServicePopOutIdLabel'+i).text($('#placeOfServiceIdLabel'+i).text());	
				 $('#placeOfServiceDescPopOut'+i).val($('#placeOfServiceDesc'+i).val());
		 	 	}else if(i > 2){
			 		var serviceTypeCodePopOutIdVal = $('#serviceTypeCodeId'+i).val();
			 		var serviceTypeCodePopOutIdLabelDesc = $('#serviceTypeCodeIdLabel'+i).text();
			 		
			 		var eb11PopOutIdVal = $('#eb11Id'+i).val(); 
	 		 		var eb11IdPopOutLabelDesc = $('#eb11IdLabel'+i).text();
			 		var placeOfServicePopOutIdVal = $('#placeOfServiceId'+i).val();
			 		var placeOfServicePopOutIdLabelDesc = $('#placeOfServiceIdLabel'+i).text();
			 	 
			 		insertServiceTypeMappingsRowPopOut('serviceTypeMappingsTablePopOut',true,'serviceTypeCodePopOutId'+i,'serviceTypeCodeValPopOut',serviceTypeCodePopOutIdVal,'serviceTypeCodePopOutIdLabel'+i,serviceTypeCodePopOutIdLabelDesc,'serviceTypeCodeDescPopOut'+i,
																'eb11PopOutId'+i,'eb11ValPopOut',eb11PopOutIdVal,'eb11IdPopOutLabel'+i,eb11IdPopOutLabelDesc,'eb11DescPopOut'+i,
																'placeOfServicePopOutId'+i,'placeOfServiceValPopOut',placeOfServicePopOutIdVal,'placeOfServicePopOutIdLabel'+i,placeOfServicePopOutIdLabelDesc,'placeOfServiceDescPopOut'+i);
			 		 
			 	} 
			}
			
			var saveFromPopOut = $("#saveFromPopOut").val();
    		if(saveFromPopOut == "true"){
    		//Add errors and open the notification tray while saving from the popout
			var v = $("#errorMessage").val();
			var errorMessageList = v.split(",");
			if(errorMessageList != '' && errorMessageList != 'undefined'){
				for(i=0; i< errorMessageList.length; i++) {
					var errorMessage = errorMessageList[i];
					var formattedErrorMessage = "";
					if(errorMessage.charAt(0) == "[" && errorMessage.charAt(errorMessage.length-1) != "]" ){
						formattedErrorMessage = errorMessage.substring(1,errorMessage.length);
					}else if(errorMessage.charAt(errorMessage.length-1) == "]" && errorMessage.charAt(0) != "["){
						formattedErrorMessage = errorMessage.substring(0,errorMessage.length-1);
					}else if(errorMessage.charAt(0) == "[" && errorMessage.charAt(errorMessage.length-1) == "]" ){
						formattedErrorMessage = errorMessage.substring(1,errorMessage.length-1);
					}else{
						formattedErrorMessage = errorMessage.substring(0,errorMessage.length)
					}
					addErrorToNotificationTray(formattedErrorMessage);  
				}
				openTrayNotification();
				}
			//Add warnings and open the notification tray
			var warningMessages = $("#warningMessage").val();
			if(warningMessages != '' && warningMessages != 'undefined'){
			addWarnToNotificationTray(warningMessages);
			openTrayNotification();	
			}
			
			//Add info messages and open the notification tray
			var infoMessages = $("#infoMessage").val();
			if(infoMessages != '' && infoMessages != 'undefined'){
			addInfoToNotificationTray(infoMessages);
			openTrayNotification();	
			}
			$("#saveFromPopOut").val('');
    		}
		} 
	});
  } 
  
  
   function serviceTypemappingsPopIn(){
   	 		 //set lob value from popout to the lob label in the edit div
			 $('#lobLabel').text($('#lobTextPopOut').val());
			 //set lob value to the lob text in the popout
			 $('#lobText').val($('#lobLabel').text());
   	
   	
   			//set mbu value from popout to the mbu label in the edit div
			 $('#mbuLabel').text($('#mbuTextPopOut').val());
			 //set mbu value to the mbu text in the popout
			 $('#mbuText').val($('#mbuLabel').text());
			
			//set the ssb value from popout to the ssb value in edit div
			 var isSSBCheckedinPopOut = $('#isSSBPopOut').attr("checked");
			
			 if( isSSBCheckedinPopOut ==  true){
				 $('#isSSB').click(); 
			 }else{
				 $('#isSSB').attr("checked", false); 
			 }
			 $('#isSSB').val($('#isSSBPopOut').val());
			 //$('#isSSB').attr("disabled", true); 
			 
			 //set the hidden values
			  $('#lobNamehidden').val($('#lobNamehiddenPopOut').val());
			  $('#lobidhidden').val($('#lobidhiddenPopOut').val());
			  $('#actionhidden').val($('#actionhiddenPopOut').val());
   	
   	
   			//setting the mapping values from popout into the edit div	
			var rowLength = $('#serviceTypeMappingsTablePopOut > tbody > tr').length;
 			for(var i = 0; i < rowLength ; i++){
			 	if(i <= 3){
			 	//EB03 values
			 	 $('#serviceTypeCodeId'+i).val($('#serviceTypeCodePopOutId'+i).val());
			 	 $('#serviceTypeCodeIdLabel'+i).text($('#serviceTypeCodePopOutIdLabel'+i).text());	
				 $('#serviceTypeCodeDesc'+i).val($('#serviceTypeCodeDescPopOut'+i).val());
				 //EB11 Values	
				 $('#eb11Id'+i).val($('#eb11PopOutId'+i).val());
				 $('#eb11IdLabel'+i).text($('#eb11IdPopOutLabel'+i).text());	
				 $('#eb11Desc'+i).val($('#eb11DescPopOut'+i).val());
				 //POS Values	
				 $('#placeOfServiceId'+i).val($('#placeOfServicePopOutId'+i).val());
				 $('#placeOfServiceIdLabel'+i).text($('#placeOfServicePopOutIdLabel'+i).text());	
				 $('#placeOfServiceDesc'+i).val($('#placeOfServiceDescPopOut'+i).val());
		 	 	}else if(i > 3){
			 		var serviceTypeCodeIdVal = $('#serviceTypeCodePopOutId'+i).val();
			 		var serviceTypeCodeLabelDesc = $('#serviceTypeCodePopOutIdLabel'+i).text();
			 		
			 		var eb11IdVal = $('#eb11PopOutId'+i).val(); 
	 		 		var eb11IdLabelDesc = $('#eb11IdPopOutLabel'+i).text();
			 		
			 		var placeOfServiceIdVal = $('#placeOfServicePopOutId'+i).val();
			 		var placeOfServiceIdLabelDesc = $('#placeOfServicePopOutIdLabel'+i).text();
			 		insertServiceTypeMappingsRow('serviceTypeMappingsTable',true,'serviceTypeCodeId'+i,'serviceTypeCodeVal',serviceTypeCodeIdVal,'serviceTypeCodeIdLabel'+i,serviceTypeCodeLabelDesc,'serviceTypeCodeDesc'+i,
																'eb11Id'+i,'eb11Val',eb11IdVal,'eb11IdLabel'+i,eb11IdLabelDesc,'eb11Desc'+i,
																'placeOfServiceId'+i,'placeOfServiceVal',placeOfServiceIdVal,'placeOfServiceIdLabel'+i,placeOfServiceIdLabelDesc,'placeOfServiceDesc'+i);
			 		  
			 	} 
			}
				$('#lobLabel').show();
 				$('#lobText').hide();
		
				$('#mbuLabel').show();
 				$('#mbuText').hide();
 				
 				$('#isSSB').attr("disabled", true); 
 				$('#editTD').show();
			
			
			 			 	
   	//close the popout div after seting the values
   	$("#servicetypeMappingsPopOutDiv").dialog("close");
   }
   
  function saveFromPopOut(){ 
  $("#saveFromPopOut").val("true");
   var lobName = $("#lobTextPopOut").val();
 	if(lobName == ""){
 	addErrorToNotificationTray("LOB Name is mandatory for a mapping.");
			openTrayNotification();	
			$('#userCommentsSaveDialog').dialog('close');
			return false;
 	}
  //Check if EB03/EB11 is missing for a mapping.
 	var rowLength = $('#serviceTypeMappingsTablePopOut > tbody > tr').length;
	for(var i = 0; i < rowLength ; i++){
		var serviceTypeCode = $('#serviceTypeCodePopOutId'+i).val();
		var eb11 = $('#eb11PopOutId'+i).val();
		var placeOfService = $('#placeOfServicePopOutId'+i).val();
		var serviceTypeCodeDesc = $('#serviceTypeCodePopOutIdLabel'+i).text();
		var eb11Desc = $('#eb11IdPopOutLabel'+i).text();
		var placeOfServiceDesc = $('#placeOfServicePopOutIdLabel'+i).text();
		 
		 if(serviceTypeCode== "" && (eb11 != "" || placeOfService != "" )){
		 	addErrorToNotificationTray("EB03 and EB11 is mandatory for a mapping.");
			openTrayNotification();	
			$('#userCommentsSaveDialog').dialog('close');
			return false;
		 }
		 if(eb11== "" && (serviceTypeCode != "" || placeOfService != "" )){
		 	addErrorToNotificationTray("EB03 and EB11 is mandatory for a mapping.");
			openTrayNotification();	
			$('#userCommentsSaveDialog').dialog('close');
			return false;
		 }
		 
		  if(serviceTypeCodeDesc == invalidHippaCodeValue || eb11Desc == invalidHippaCodeValue|| placeOfServiceDesc == invalidHippaCodeValue ){
			var errorMessage = "Invalid Service Type Mapping:";
			if(serviceTypeCode != ''){
				errorMessage = errorMessage+"  EB03 ='"+serviceTypeCode+"'";
			}
			if(eb11 != ''){
				errorMessage = errorMessage+"  EB11 ='"+eb11+"'";
			}
			if(placeOfService != ''){
				errorMessage = errorMessage+"  POS ='"+placeOfService+"'";
			}
			addErrorToNotificationTray(errorMessage);
			openTrayNotification();	
			$('#userCommentsSaveDialog').dialog('close');
			return false;
			}
	}
	//Set the description from the label 
	setDescriptionServiceTypeMappingsPopOut();
	$("#searchActionLobNameSave").val($.trim($("#lobName").val()));
	$("#searchActionMbuNameSave").val($.trim($("#mbuName").val()));
	$("#lobValueToSave").val($.trim($("#lobTextPopOut").val()));
	$("#mbuValueToSave").val($.trim($("#mbuTextPopOut").val()));
	$("#lobIdhidden").val($.trim($("#lobidhiddenPopOut").val()));
	$("#actionToSave").val($.trim($("#actionhiddenPopOut").val()));
	
	var isSSBCheckedinPopOut = $('#isSSBPopOut').attr("checked");
 	 if(isSSBCheckedinPopOut == true){
 	 	$("#ssbValue").val("Y");
 	 }else{
 	 	$("#ssbValue").val("N");
 	 }
	//set the mapping values from the popup to the id in the edit page
	var rowLength = $('#serviceTypeMappingsTablePopOut > tbody > tr').length;
 			for(var i = 0; i < rowLength ; i++){
 				//dynamically loads the rows and then set the values if rowLength is greater than 2
 				if(i>2){
				 	addRowForServiceTypeMappings('serviceTypeMappingsTable',true,'serviceTypeCodeId','serviceTypeCodeVal','','','eb11Id','eb11Val','','','placeOfServiceId','placeOfServiceVal','','');
				 	}
			 	//EB03 values
			 	 $('#serviceTypeCodeId'+i).val($('#serviceTypeCodePopOutId'+i).val());
			 	 $('#serviceTypeCodeIdLabel'+i).text($('#serviceTypeCodePopOutIdLabel'+i).text());	
				 $('#serviceTypeCodeDesc'+i).val($('#serviceTypeCodeDescPopOut'+i).val());
				 //EB11 Values	
				 $('#eb11Id'+i).val($('#eb11PopOutId'+i).val());
				 $('#eb11IdLabel'+i).text($('#eb11IdPopOutLabel'+i).text());	
				 $('#eb11Desc'+i).val($('#eb11DescPopOut'+i).val());
				 //POS Values	
				 $('#placeOfServiceId'+i).val($('#placeOfServicePopOutId'+i).val());
				 $('#placeOfServiceIdLabel'+i).text($('#placeOfServicePopOutIdLabel'+i).text());	
				 $('#placeOfServiceDesc'+i).val($('#placeOfServiceDescPopOut'+i).val());
				 }
	$("#userCommentsSaveDialog table#saveActionUserCommentsTable").css("border-top","1px solid black");
		$("#userCommentsSaveDialog").dialog({
                       height:'auto',
					width:'450px',	
					resizable:false,
                       show:'slide',
					title: 'Confirm',
                       modal: true
                 });
	$("#userCommentsSaveDialog").dialog();
  }
  
  
  function deleteAssociationConfirmationDialog(lobId,lobName){
 var warningMsgDeleteAllAction  = "The association will be deleted. Do you wish to continue?";	
 						   
 $("#confirmationDivDeleteAllAction").html(warningMsgDeleteAllAction);
	$("#confirmationDivDeleteAllAction").dialog({
			autoOpen: false,
			title : 'Service Type Code - EB11 Association',
			resizable: false,
			height:140,
			width:400,
			modal: true,
			buttons: {			
			No: function() {
				$(this).dialog('close');
				},		
			Yes: function() {
					deleteServiceTypeMappings(lobId,lobName);
					$(this).dialog('close');
				}
			}
		});
		$("#confirmationDivDeleteAllAction").dialog('open');
}
</script>

<body onload="disableUserCommentsDiv();">
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="context" value="${pageContext.request.contextPath}"/>



<form name="servicetypeMappingsForm" method="POST">
 <input type="hidden" name="searchActionLobNameSave" id="searchActionLobNameSave" value="" />
 <input type="hidden" name="searchActionMbuNameSave" id="searchActionMbuNameSave" value="" />
 <input type="hidden" name="lobValueToSave" id="lobValueToSave" value="" />
 <input type="hidden" name="mbuValueToSave" id="mbuValueToSave" value="" />
 <input type="hidden" name="ssbValue" id="ssbValue" value="" />
 <input type="hidden" name="lobIdhidden" id="lobIdhidden" value="" />
 <input type="hidden" name="actionToSave" id="actionToSave" value="" />
 <input type="hidden" name="saveUserComments" id="saveUserComments" value="" />
 <input type="hidden" name="saveFromPopOut" id="saveFromPopOut" value="${saveFromPopOut}" />
 <input type="hidden" name="warningMessage" id="warningMessage" value="${warning_messages}" />
 <input type="hidden" name="errorMessage" id="errorMessage" value="${error_messages}" />
 <input type="hidden" name="infoMessage" id="infoMessage" value="${info_messages}" />
 
 
<%@include	file="/WEB-INF/jspf/pageTop.jspf"%>
<div class="innerContainer" style="height: 98%" class="Pd3">
<div id="exclusionEditContainer1">
<div id="exclusionSectionOne1" style=" height: 100px">
<div id="exclusionTitleBarSectionOne1">
<div class="headerTitleExclusion">Manage Service Type Code - EB11 Association</div>
</div>

<div id="exclusionContentSectionOne1">
<table style="margin-top: 3px;" class="mt10 mL10 Pd3 shadedText" id="exclusionTableWidth1" border="0" cellspacing="0" cellpadding="0">
	<TBODY>
		<tr class="createEditTable1-Listdata" style="padding-top: 0px">
			<td width="200px" class="headTextExclusions">LOB/State</td>
			<td width="152px"><input type="text" class="inputbox60"	id="lobName" name="lobName" value="${lobName}" onkeypress="enterKeyPress(event);"/></td>
			<td width="152px">&nbsp;</td>
			<td width="152px" class="headTextExclusions">MBU</td>
			<td width="152px"><input type="text" class="inputbox60"	id="mbuName" name="mbuName" value="${mbuName}" onkeypress="enterKeyPress(event);"/></td>
			<td class="headText" width="152px">&nbsp;</td>
		</tr>
</table>
<table align="center">
				
			<tr align="center">
				<td align="right" colspan="2"><a href="#"><IMG id="Locate"	src="${imageDir}/locate_but.gif" width="70" height="18" alt="Locate"
					onclick="fetchLobMbuAssociation();"></a>
				</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td width="10%" align="right"><a href="#"><IMG id="Create"	src="${imageDir}/create_but.gif" width="70" height="18" alt="Create"
					onclick="createLobMbuAssociation();"></a>
				</td>
				<td width="40%px">
			
			</tr>
		
	</TBODY>
</table>
</div>
</div>

<div id="exclusionSectionTwo1" style="display: none">
<div class="exclusionTitleBarSectionTwo1">
<table class="mappedTable1 pd3 shadedText" border="0" width="100%">
	<THEAD>
		<tr>
			<th id="systemId" width="5%" class="tableHeader">LOB/State</th>
			<th id="systemId" width="29%" class="tableHeader">MBU</th>
			<th id="createdId" width="7%" class="tableHeader">&nbsp;</th>

		</tr>
	</THEAD>
</table>
</div>


<div id="exclusionContentSectionTwo1" style="width: 926px;">
<div id="dynamicRenderer">
<jsp:include flush="true" page="serviceTypeCodetoEB11SearchResult.jsp"></jsp:include></div>
<br>
</div>
</div>
<div id="exclusionSectionThree1" style="display: none; height: 255px">
<div class="exclusionTitleBarSectionTwo">
<div class="headerTitleSub">
 	<span style='clear: right;float:left;'>Manage</span>	
	<span style='float: right;'>
		<a href="#" onclick='serviceTypemappingsPopOut();'>
			<img src="${imageDir}/PopOut_icon.gif" alt="Pop Out" id="popout" title="Pop Out" width='11px' height='11px'/>
		</a>&nbsp;
	</span>
</div>
</div>
<!-- <div class="exclusionSectionThreeSub1"> -->

<div id="editpage" style="display: none; height: 240px">
<jsp:include flush="true" page="serviceTypeCodeToEB11Edit.jsp" ></jsp:include></div>

<!-- </div> --></div>


</div>
<!-- Edit container ends here --></div>
<!-- Inner container ends here -->
</div>
<!-- Container ends here --> <!-- Wrapper ends here -->


<div id="createupdate" class="footer" style="display: none;">
<div class="ajaxIdle" id="ajaxIdleIcon">
<div class="clear"></div>
</div>
<div class="ajaxActive" id="ajaxActiveIcon">
<div class="clear"></div>
</div>

<table border="0" align="center" cellpadding="0" cellspacing="0"
	class="footerTable">
	<tr>
		<td width="18" height="20"><img src="${imageDir}/footer_left.gif"
			width="18" height="20" /></td>
		<td width="0" height="20"><a href="#" onclick='backAction()'>Back</a></td>
		<td width="9" height="0"><img src="${imageDir}/seperator.gif"
			width="9" height="20" /></td>
		<td width="0" height="20"><a href="#" onclick='saveServiceTypeMappings()'>Save</a></td>
		<td width="9" height="0"><img src="${imageDir}/seperator.gif"
			width="9" height="20" /></td>
		<td width="0" height="20"><a href="#" onclick='viewHistoryofServiceTypeMappings("")'>View	History</a></td>
		<td width="18" height="20"><img
			src="${imageDir}/footer_right.gif" width="18" height="20" /></td>
	</tr>
</table>
</div>

<div id="initialLoad" class="footer">
<div class="ajaxIdle" id="ajaxIdleIcon">
<div class="clear"></div>
</div>
<div class="ajaxActive" id="ajaxActiveIcon">
<div class="clear"></div>
</div>

<table border="0" align="center" cellpadding="0" cellspacing="0"
	class="footerTable">
	<tr>
		<td width="18" height="20"><img src="${imageDir}/footer_left.gif"
			width="18" height="20" /></td>
		<td width="0" height="20"><a href="#" onclick='backAction()'>Back</a></td>
		<td width="18" height="20"><img
			src="${imageDir}/footer_right.gif" width="18" height="20" /></td>
	</tr>
</table>

</div>

</div>
<script>
	       	sniffer();
	    
       	</script></form>
<c:if test="${ fromaction == 'UPDATE'}">
	<script>
		document.getElementById('dynamicRenderer').style.display= 'block';
		document.getElementById('exclusionSectionTwo1').style.display= 'block';
		document.getElementById('initialLoad').style.display= 'none';
		document.getElementById('createupdate').style.display= 'block';
		document.getElementById('exclusionSectionThree1').style.display= 'block';
		document.getElementById('editpage').style.display= 'block';
	</script>
</c:if>
<c:if test="${ fromaction == 'DELETE'}">
	<script>
		document.getElementById('dynamicRenderer').style.display= 'block';
		document.getElementById('exclusionSectionTwo1').style.display= 'block';
		document.getElementById('initialLoad').style.display= 'block';
		document.getElementById('createupdate').style.display= 'none';
		document.getElementById('exclusionSectionThree1').style.display= 'none';
		document.getElementById('editpage').style.display= 'none';
	</script>
</c:if>
<c:if test="${ fromaction == 'ERRORWHILECREATE'}">
	<script>
		document.getElementById('dynamicRenderer').style.display= 'block';
		document.getElementById('exclusionSectionTwo1').style.display= 'none';
		document.getElementById('initialLoad').style.display= 'none';
		document.getElementById('createupdate').style.display= 'block';
		document.getElementById('exclusionSectionThree1').style.display= 'block';
		document.getElementById('editpage').style.display= 'block';
		
	$('#lobLabel').hide();
 	$('#lobText').show();
		
	$('#mbuLabel').hide();
 	$('#mbuText').show();
 	$('#isSSB').removeAttr("disabled"); 
 	$('#editTD').hide();
	</script>
</c:if>
<c:if test="${ fromaction == 'ERRORWHILEUPDATE'}">
	<script>
		document.getElementById('dynamicRenderer').style.display= 'block';
		document.getElementById('exclusionSectionTwo1').style.display= 'block';
		document.getElementById('initialLoad').style.display= 'none';
		document.getElementById('createupdate').style.display= 'block';
		document.getElementById('exclusionSectionThree1').style.display= 'block';
		document.getElementById('editpage').style.display= 'block';
		
	$('#lobLabel').hide();
 	$('#lobText').show();
		
	$('#mbuLabel').hide();
 	$('#mbuText').show();
 	$('#isSSB').removeAttr("disabled"); 
 	$('#editTD').hide();
	</script>
</c:if>

<div id="viewHistoryDialog" title="View History"></div>
<div id="referenceDataDialog"></div>
<div id="confirmationDivDeleteAllAction"></div>
<div id="confirmationDivEditAction"></div>
<div id="viewHistoryDivForAssociation"></div>
<div id="confirmationDivDeleteAllAction"></div>
<div id="servicetypeMappingsPopOutDiv" style="overflow-x: hidden; overflow-y: hidden;" ></div>

<div id="referanceDataPopUpDiv" ></div>

</div> 

<div id="userCommentsDeleteAllDialog" >
<form name="userCommentsDeleteAllActionDialogForm" action="${context}/referencedata/deleteServiceTypeCodeToEB11Mapping.html" method="POST">
   <input type="hidden" name="lobIdWhileDelete" id="lobIdWhileDelete" value="" />
   <input type="hidden" name="lobNameWhileDelete" id="lobNameWhileDelete" value="" />
 
	<table id="deleteActionUserCommentsTable1" border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5 bT">          
	  <tr class="">
        <td > <textarea name="deleteAllUserComments" id="deleteAllUserComments"
		rows="5" cols="77"></textarea></td>
	  </tr> 			
	</table>
	<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
      <tr>       
        <td >
			<a href="#" onclick="saveUserCommentsForDeleteAssociation();"><img id="deleteAllActionSaveImg" src="${imageDir}/save_but.gif" 
			alt="Save" title="Save" /></a>
		</td>		
      </tr>
	</table>	
</form>
</div>

<div id="userCommentsSaveDialog" >
<form name="userCommentsSaveActionDialogForm" action="${context}/referencedata/saveServiceTypeCodeToEB11Mapping.html" method="POST">
<!--
 <input type="hidden" name="searchActionEB01IdSave" id="searchActionEB01IdSave" value="" /> 
 <input type="hidden" name="searchActionDataTypeIdSave" id="searchActionDataTypeIdSave" value="" /> 
 <input type="hidden" name="eb01ValueUpdate" id="eb01ValueUpdate" value="" />
    -->
	<table id="saveActionUserCommentsTable" border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5 bT">          
	  <tr class="">
        <td > <textarea name="saveUserCommentsTextArea" id="saveUserCommentsTextArea"
		rows="5" cols="77"></textarea></td>
	  </tr> 			
	</table>
	<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
      <tr>       
        <td >
			<a
			href="#" onclick="saveUserCommentsForSaveAction();"><img id="saveActionSaveImg" src="${imageDir}/save_but.gif" 
			alt="Save" title="Save" /></a>
		</td>		
      </tr>
	</table>	
</form>
</div>
<div id="viewMappingDialog" title="View Mapping" style="overflow-x: hidden; overflow-y: hidden;"></div>
<%@include file="/WEB-INF/jspf/whatIsThisToolTip.jspf"%>
<%@include file="/WEB-INF/jspf/messageTray.jspf"%>


</body>
</html>
