<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/WEB-INF/RapidSpellWeb.tld" prefix="RapidSpellWeb"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/jspf/pageHead.jspf"%>
<title>Enterprise Blue Exchange</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/json.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ebxmapping.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hippaSegmentPopUp.js"></script>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<script type="text/javascript">

	function sniffer() {
	   if(screen.height==1024) 
	   {
	      document.getElementById('createEditContainer').style.height = "510px";	   
	   } 
	   else if(screen.height==864)
	   {
	      document.getElementById("createEditContainer").style.height= "410px";
	   }
	}
	// To check the maxlength
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
	 function sendToTest(ruleId, ruleDesc,spsId,spsDesc){
	  		//Added as part of SSCR 19537
			var result = createEB03AssnObject();
		
			$("#selectedruleId").val(ruleId);	
			$("#ruleDesc").val(ruleDesc);	
			$("#selectedspsId").val(spsId);	
			$("#spsDesc").val(spsDesc);	
			var pass = checkMsgRqdInd();
		if(pass && imposeMaxLengthOnMultipleMessages() && 
				(($('#individualMappingForEB03ChkBox').is(':checked') && specialCharacterValidationForMessage())|| 
				(!$('#individualMappingForEB03ChkBox').is(':checked')))
				&& (imposeMaxLength('changeCommentsId', '250', 'Change Comments'))){		
			setDescription();
			document.forms['editForm'].action="${context}/createoreditcustommessagemapping/sendToTest.html";
            document.forms["editForm"].submit();
		}	
		
      }
	function approve(ruleId, ruleDesc,spsId,spsDesc){
			//Added as part of SSCR 19537
			var result = createEB03AssnObject();
	
			$("#selectedruleId").val(ruleId);	
			$("#ruleDesc").val(ruleDesc);	
			$("#selectedspsId").val(spsId);	
			$("#spsDesc").val(spsDesc);	
			var pass = checkMsgRqdInd();
			if(pass && imposeMaxLengthOnMultipleMessages() && 
				(($('#individualMappingForEB03ChkBox').is(':checked') && specialCharacterValidationForMessage())|| 
				(!$('#individualMappingForEB03ChkBox').is(':checked')))
				&& (imposeMaxLength('changeCommentsId', '250', 'Change Comments'))){					
			setDescription();
			document.forms['editForm'].action="${context}/createoreditcustommessagemapping/approve.html";
            document.forms["editForm"].submit();
		}
		
      }
	function deleteMapping(ruleId, ruleDesc,spsId,spsDesc){
			//Added as part of SSCR 19537
			
			var result = createEB03AssnObject();
		
			$("#selectedruleId").val(ruleId);
			$("#ruleDesc").val(ruleDesc);
			$("#selectedspsId").val(spsId);
			$("#spsDesc").val(spsDesc);			
			trimTextValues();
			var action ="${context}/createoreditcustommessagemapping/delete.html";
		confirmationDialogDeleteChanges(action, 'editForm');		
				
      }
	function discardChanges(ruleId, ruleDesc,spsId,spsDesc){
			//Added as part of SSCR 19537
			var result = createEB03AssnObject();
		
			
			$("#selectedruleId").val(ruleId);
			$("#ruleDesc").val(ruleDesc);
			$("#selectedspsId").val(spsId);
			$("#spsDesc").val(spsDesc);
			var pass = checkMsgRqdInd();
			if(pass && imposeMaxLengthOnMultipleMessages() && 
				(($('#individualMappingForEB03ChkBox').is(':checked') && specialCharacterValidationForMessage())|| 
				(!$('#individualMappingForEB03ChkBox').is(':checked')))
				&& (imposeMaxLength('changeCommentsId', '250', 'Change Comments'))){					
			setDescription();
			var action = "${context}/createoreditcustommessagemapping/discardChanges.html";
			confirmationDialogDiscardChanges(action, 'editForm');		
		}
				
	}
	function cancel(ruleId,spsId){
			var pageFrom = $("#pageFrom").val();
			var action ;
			//alert("Page from value:"+pageFrom);
			if(pageFrom == "advanceSearchEbx"){
				action = "${context}/createoreditcustommessagemapping/cancelToAdvanceSearchPage.html?selectedruleId="+ruleId+"&selectedspsId="+spsId;
			}else{
				action = "${context}/createoreditcustommessagemapping/cancel.html?selectedruleId="+ruleId+"&selectedspsId="+spsId;
			}
			openConfirmationDialog(action);
	}
	function save(ruleId, ruleDesc,spsId,spsDesc){
			//Added as part of SSCR 19537
			var result = createEB03AssnObject();
		
				
			$("#selectedruleId").val(ruleId);	
			$("#ruleDesc").val(ruleDesc);	
			$("#selectedspsId").val(spsId);	
			$("#spsDesc").val(spsDesc);	
			var pass = checkMsgRqdInd();
			if(pass && imposeMaxLengthOnMultipleMessages() && 
			(($('#individualMappingForEB03ChkBox').is(':checked') && specialCharacterValidationForMessage())|| 
			(!$('#individualMappingForEB03ChkBox').is(':checked')))
			&& (imposeMaxLength('changeCommentsId', '250', 'Change Comments'))){

			trimTextValues();			
			setDescription();
			document.forms['editForm'].action="${context}/createoreditcustommessagemapping/saveMapping.html";
	        document.forms["editForm"].submit();
		}
		
      }	
	function done(ruleId, ruleDesc,spsId,spsDesc){	
			//Added as part of SSCR 19537
			var result = createEB03AssnObject();
	
			
			$("#selectedruleId").val(ruleId);	
			$("#ruleDesc").val(ruleDesc);	
			$("#selectedspsId").val(spsId);		
			$("#spsDesc").val(spsDesc);	
			var pass = checkMsgRqdInd();
			if(pass && imposeMaxLengthOnMultipleMessages() && 
				(($('#individualMappingForEB03ChkBox').is(':checked') && specialCharacterValidationForMessage())|| 
				(!$('#individualMappingForEB03ChkBox').is(':checked')))
				&& (imposeMaxLength('changeCommentsId', '250', 'Change Comments'))){	
			trimTextValues();			
			setDescription();
			document.forms['editForm'].action="${context}/createoreditcustommessagemapping/done.html";
	        document.forms["editForm"].submit();
		}
		
      }
	function openViewHistoryDialog(ruleId,spsId) {
		$.ajax({
			url: "${context}/createoreditcustommessagemapping/viewHistory.ajax",
			dataType: "html",
			type: "POST",
			data: "ruleId="+ruleId+"&spsId="+spsId,
			success: function(data) {
				$("#viewHistoryDialog").html(data);
				$("#viewHistoryDialog").dialog({
					height:'450',
					width:'600',
					resizable : 'false',
					modal: true
				});
				$("#viewHistoryDialog").dialog();
			}
		});
	}
    function copyToDialog(){		
				$("#copyToDialog").dialog({
					 		height:'auto',
							width:'450px',
							title: 'Copy to Custom Message',
	                        show:'slide',
	                        modal: true
				});
				$("#copyToDialog").dialog('open');			
	}
	
	function openAlertDialog(){
      if(compareInputs()){
            alertDialog();
      }else{
             if( $('#individualMappingForEB03ChkBox').is(':checked') ){
	      	 var rule = $('#ruleIdHidden').val();
	            addErrorToNotificationTray('The Rule Id '+rule+' has Individual Mapping.');
				openTrayNotification();
	            }else{
	            	copyToDialog();
	            }
      }
	}
	
	function alertDialog(){
	$("#alertDiv").html(warningMsgForCopyTo);
	$("#alertDiv").addClass("UnmappedVariables");
	$("#alertDiv").dialog({
				autoOpen: false,
				title : 'Alert',
				resizable: false,
				height:150,
				modal: true,
				buttons: {
				Ok: function() {
						$(this).dialog('close');
					}
				}
			});
			$("#alertDiv").dialog('open');
}
$(document).ready(function(){
		$('#messageTextId').blur(function() {
			if(null != $('#messageTextId').val()){
				$('#messageTextId').val($('#messageTextId').val().toUpperCase());
			}
		});
	
	if($('#individualMappingForEB03ChkBox').is(':checked')){
		var eb03AssnTable = document.getElementById('eb03AssnTable');
		for(var i = 1; i < eb03AssnTable.getElementsByTagName("TR").length; i++){
			$('#MessageId'+i).blur(function() {
				if(null != $('#MessageId'+i).val()){
					$('#MessageId'+i).val($('#MessageId'+i).val().toUpperCase());
				}
			});
		}
	}
		
});
// for copy to function for doing save
$(document).ready(function(){
	$("#ruleId").autocomplete({ 		
		select: function(event, ui) { $("#createIdLabel").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "${context}/ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term,
						success: function(data) {
						cache: false,
						response(data.rows);
						list = data.rows;
						}
					});
		},
		change: function(event, ui) {
			var text = $(this).val();
			if(!ui.item){
				displayLabelForSelectedItem(text,list,"createIdLabel",inValidVariable);
			}
		}

	})
  });
$(document).ready(function(){
	$("#spsId").autocomplete({ 		
		select: function(event, ui) { $("#createIdLabel").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "${context}/ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term,
						success: function(data) {
						cache: false,
						response(data.rows);
						list = data.rows;
						}
					});
		},
		change: function(event, ui) {
			var text = $(this).val();
			if(!ui.item){
				displayLabelForSelectedItem(text,list,"createIdLabel",inValidVariable);
			}
		}

	})
  });
function copyTo(){
		var ruleIdForCopyTo =$("#spsIdForCopyTo1").val();
		var oldRuleDesc = $("#oldRuleDesc").val();	
		var oldRuleID= $("#oldRuleID").val();
		var spsIdForCopyTo =$("#spsIdForCopyTo").val(); 
		var oldSPSID= $("#oldSPSID").val(); 
		var oldSpsDesc = $("#oldSpsDesc").val();
		var oldVarFormate = $("#oldVarFormate").val();
		if((ruleIdForCopyTo == null || ruleIdForCopyTo == "")&&(spsIdForCopyTo == null || spsIdForCopyTo == "")){ 
			addErrorToNotificationTray('Rule ID  and Sps ID is mandatory to create mapping');
			openTrayNotification();
		}
		else if(spsIdForCopyTo == null || spsIdForCopyTo == ""){ 
			addErrorToNotificationTray('Sps ID is mandatory to create mapping');
			openTrayNotification();
		}
		else if(ruleIdForCopyTo == null || ruleIdForCopyTo == ""){
		addErrorToNotificationTray('Rule ID is mandatory to create mapping');
			openTrayNotification();
		}
		else if((ruleIdForCopyTo==oldRuleID)&&(spsIdForCopyTo==oldSPSID)){
		addErrorToNotificationTray('Mapping already exists');
			openTrayNotification();
		}
		else {			
			$.ajax({
					url: "${context}/copy/invalidCustomMessage.ajax",
					dataType: "json",
					type: "POST",			
					data: "ruleIdForCopyTo="+ruleIdForCopyTo+"&oldRuleID="+oldRuleID+"&oldRuleDesc="+oldRuleDesc+ "&spsIdForCopyTo="+spsIdForCopyTo+"&oldSPSID="+oldSPSID+"&oldSpsDesc="+oldSpsDesc,
					success: function(data) {
					cache: false,
					copyToCreate(data,ruleIdForCopyTo, oldRuleID, oldRuleDesc,spsIdForCopyTo,oldSPSID,oldSpsDesc);
					}
				});	
		}
			
		}	

function copyToCreate(data,ruleIdForCopyTo, oldRuleID, oldRuleDesc,spsIdForCopyTo,oldSPSID,oldSpsDesc){
		$("#selectedruleIdCopyTo").val(ruleIdForCopyTo);
		$("#selectedruleId").val(oldRuleID);
		$("#ruleDesc").val(oldRuleDesc);	
			$("#selectedspsIdCopyTo").val(spsIdForCopyTo);
			$("#selectedspsId").val(oldSPSID);
			$("#spsDesc").val(oldSpsDesc);	
		var errorMessages = data.error_messages;
		if(typeof(errorMessages) != 'undefined' && errorMessages.length >0) {
			for(i=0; i< errorMessages.length; i++) {
				addErrorToNotificationTray(errorMessages[i]);
			}
			openTrayNotification();
		}
		else{						
			if(!imposeMaxLength('changeCommentsId' ,250,'Change Comments')){
				return false;
			}
			if(!imposeMaxLength('messageTextId' ,75,'Message')){
				return false;
			}
			trimTextValues();
			setDescription();
			document.forms['editForm'].action="${context}/createoreditcustommessagemapping/copyToMapping.html";
	        document.forms["editForm"].submit();
		    //document.forms["copyToform"].submit();
		}
      }	
    
//ends copy to for doing save
//Validation for 'Message'
	function checkMsgRqdInd(){
		if(!$('#individualMappingForEB03ChkBox').is(':checked')){
		var checked = $('#msgRqdChkBox').attr('checked');
		if(checked){
			var msgValue = $('#messageTextId').val();
			$('#msgRqdChkBox').val('true');
			if(msgValue == null || $.trim(msgValue) == "" ){
				addErrorToNotificationTray("Enter message");
				openTrayNotification();	
				$('#messageTextId').focus();
				return false;
			}
		}
		else{	
			$('#msgRqdChkBox').val('false');
		}		
		return true;
	}	else{

				var eb03AssnTable = document.getElementById('eb03AssnTable');
				var eb03ListForSpclChar = "";
				for(var i = 1; i < eb03AssnTable.getElementsByTagName("TR").length; i++){
					var eb03Val =  eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(0).innerText;
					var messageText = $.trim($('#MessageId'+(i-1)).val());
					var msgInd = ($('#msgReqd'+(i-1)).is(':checked')) ? true : false;
						if(msgInd && messageText == ""){
							if(eb03ListForSpclChar == ""){
								eb03ListForSpclChar = eb03Val;
							}else{
								eb03ListForSpclChar = eb03ListForSpclChar +", "+eb03Val;
							}
						}
					}
			if(eb03ListForSpclChar != ""){
					var message = "Enter message for EB03(s) – "+eb03ListForSpclChar+". ";					
					addErrorToNotificationTray(message);
					openTrayNotification();	
					return false;
				}
				}
			return true;
		}
	
	
	function searchMessageText(currentPageNo, page,context){
		if(page == 'Init'){
			$('#hdMsgTxtId').val($('#msgTxtId').val());
			$('#hdMsgTxtEB03Id').val($('#msgTxtEb03Id').val());
			$('#hdMsgTxtHdrId').val($('#msgTxtVarId').val());
			page = 'First';
		}
		var message = $('#hdMsgTxtId').val();
		var eb03 = $('#hdMsgTxtEB03Id').val();
		var headerRuleId = $('#hdMsgTxtHdrId').val();
		
		//BXNI CHANGE
		var showOnlyStandardMessages = "false";
		if ($('#showOnlyStandardMessages').is(':checked') ) {
  			showOnlyStandardMessages = "true";
  		}
		
		var fromWhichSection = page;
		var currentPage = currentPageNo;		
		$.ajax({
			url: "${context}/createoreditcustommessagemapping/existingMsgTexts.ajax",
			dataType: "html",
			type: "POST",
			data: "headerRuleId="+headerRuleId+"&eb03="+eb03+"&message="+message+"&currentPage="+currentPage+"&section="+fromWhichSection+"&showOnlyStandardMessages="+showOnlyStandardMessages,
			success: function(data) {
				$("#messageTextResult").html(data);
			}
		});
	}
	
	var tempRuleId = "";
	var tempRuleDesc = "";
	var tempSpsId = "";
	var tempSpsDesc = "";
	var tempActionName = "";
	
	
	//var countJ = 0;
	
	function runSpellCheck(ruleId, ruleDesc,spsId,spsDesc,actionName){
	
	//BXNI Condition
	var msgValue = $('#messageTextId').val(); 
	var isMsgValid = false;
	var textAreaId = '';
	var countOfEb03 = document.getElementById('eb03AssnTable').getElementsByTagName("TR").length;
	if(!$('#individualMappingForEB03ChkBox').is(':checked')){
		isMsgValid = messageCheck(msgValue);
		var eb03AssnTable = document.getElementById('eb03AssnTable');
				var eb03ListForSpclChar = "";
				for(var i = 1; i < eb03AssnTable.getElementsByTagName("TR").length; i++){
					var eb03Val =  eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(0).innerText;
					if(eb03ListForSpclChar == ""){
								eb03ListForSpclChar = eb03Val;
							}else{
								eb03ListForSpclChar = eb03ListForSpclChar +", "+eb03Val;
							}
					}
				
			if(!isMsgValid){
					var message = "* ^ - ~ { } # _ : ; ? ! [ ] < > are invalid characters for Message Text.";			
					addErrorToNotificationTray(message);
					openTrayNotification();	
					return false;
			}else{
			//Spell check for single
				tempRuleId = ruleId;
				tempRuleDesc = ruleDesc;
				tempSpsId = spsId;
				tempSpsDesc = spsDesc;
				tempActionName = actionName;
	
				var rswlCntrls = [];
				rswlCntrls = ["rapidSpellWebLauncher1"];
				var i=0;
				for(var i=0; i<rswlCntrls.length; i++){
					eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
				}
				
			}
	}

	else{
		tempRuleId = ruleId;
		tempRuleDesc = ruleDesc;
		tempSpsId = spsId;
		tempSpsDesc = spsDesc;
		tempActionName = actionName;
		var rswlCntrls = [];
		for(var j = 0; j< (countOfEb03-1) ; j++){

			rswlCntrls = ["rapidSpellWebLauncher_0","rapidSpellWebLauncher_1",
			"rapidSpellWebLauncher_2","rapidSpellWebLauncher_3","rapidSpellWebLauncher_4"
			,"rapidSpellWebLauncher_5","rapidSpellWebLauncher_6","rapidSpellWebLauncher_7"
			,"rapidSpellWebLauncher_8","rapidSpellWebLauncher_9","rapidSpellWebLauncher_10",
			"rapidSpellWebLauncher_11","rapidSpellWebLauncher_12","rapidSpellWebLauncher_13",
			"rapidSpellWebLauncher_14","rapidSpellWebLauncher_15"];
 			var messageText = 	$.trim($('#MessageId'+j).val());
 			if(null != messageText && messageText != ""){
 			
					eval("popUpCheckSpelling"+rswlCntrls[j]+"('rsTCInt"+rswlCntrls[j]+"')");
			}
			}
			//spellFinMessageAction();
	}
	}
	
	
	
	function spellFinMessageAction(cancelled){
	
			if(tempActionName == "approve"){
				approve(tempRuleId, tempRuleDesc, tempSpsId, tempSpsDesc);
			}else if(tempActionName == "done"){
				done(tempRuleId, tempRuleDesc, tempSpsId, tempSpsDesc);
			}else if(tempActionName == "save"){
				save(tempRuleId, tempRuleDesc, tempSpsId, tempSpsDesc);
			}else if(tempActionName == "sendToTest"){
				sendToTest(tempRuleId, tempRuleDesc, tempSpsId, tempSpsDesc);
			}
			tempRuleId = "";
			tempRuleDesc = "";
			tempSpsId = "";
			tempSpsDesc = "";
			tempActionName = "";
	
	}
	
	function RSCustomInterface(tbElementName){
		this.tbName = tbElementName;
		this.getText = getText;
		this.setText = setText;
	}
	function getText(){
		if(!document.getElementById(this.tbName)) {
			//alert('Error: element '+this.tbName+' does not exist, check TextComponentName.');
			return '';
		} else return document.getElementById(this.tbName).value;
	}
	function setText(text){
		if(document.getElementById(this.tbName)) document.getElementById(this.tbName).value = text;
	}
	function printPage() {

	createEB03AssnObject();
  	    	
	var url = "${context}/createoreditcustommessagemapping/printCustomMapping.html";

	
	 var selectedruleId = $("#ruleIdHidden").val();
     var ruleDesc = $("#ruleDescHidden").val();
     var selectedspsId = $("#spsIdHidden").val();
     var spsDesc = $("#spsDescHidden").val();
     var noteType = getTheValuesInTextBox("NOTETYPEID","VALUE");
     var NoteTypeDesc = getTheValuesInTextBox("NOTETYPEID","DESC");

     var messageText = $("#messageTextId").val();
     var msgRqdChkBoxString;
     var mesgReqdString;
     if ($('#msgRqdChkBox:checked').val() == "checked") {
	     msgRqdChkBoxString = "&msgRqdChkBox=Y";
	     mesgReqdString = "Y";
     }else{
     	 mesgReqdString = "N";
     }
     var noteTypeDesc = $("#NOTETYPEIDLabel").val;
       
     //Added as part of SSCR 19537
     var individualMappingString = ""; 
     	if ($('#individualMappingForEB03ChkBox').is(':checked') ){
			var eb03AssnTable = document.getElementById('eb03AssnTable');
			individualMappingString = ""; 
			var count = 0;
			
			if(null != eb03AssnTable.getElementsByTagName("TR") && null != eb03AssnTable.getElementsByTagName("TR").item(1) && 
			null != eb03AssnTable.getElementsByTagName("TR").item(1).getElementsByTagName("TD") &&
			null != eb03AssnTable.getElementsByTagName("TR").item(1).getElementsByTagName("TD").item(0) &&
			eb03AssnTable.getElementsByTagName("TR").item(1).getElementsByTagName("TD").item(0).innerText != ""){
			
				for(var i = 1; i < eb03AssnTable.getElementsByTagName("TR").length; i++){
					var eb03			=	eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(0).innerText;
					var msgText  		=   eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(1).getElementsByTagName("textarea").item(0).value;
					var msgReqd = "N";
					if(eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(3).getElementsByTagName("input").item(0).checked){
						msgReqd			=	"Y";
					}
					var noteType		=	eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(4).getElementsByTagName("input").item(0).value;
					var noteTypeDesVal	=	eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(6).innerText;
					//adding to String
					individualMappingString = individualMappingString +"**"+eb03+"_"+msgText+"_"+msgReqd+"_"+noteType+"_"+noteTypeDesVal;
				}
			}
		}else{
			var eb03AssnTable = document.getElementById('eb03AssnTable');
			individualMappingString = ""; 
			var count = 0;
			
			if(null != eb03AssnTable.getElementsByTagName("TR") && null != eb03AssnTable.getElementsByTagName("TR").item(1) && 
			null != eb03AssnTable.getElementsByTagName("TR").item(1).getElementsByTagName("TD") &&
			null != eb03AssnTable.getElementsByTagName("TR").item(1).getElementsByTagName("TD").item(0) &&
			eb03AssnTable.getElementsByTagName("TR").item(1).getElementsByTagName("TD").item(0).innerText != ""){
			
				for(var i = 1; i < eb03AssnTable.getElementsByTagName("TR").length; i++){
					var eb03			=	eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(0).innerText;
					//adding to String
					individualMappingString = individualMappingString +"**"+eb03+"_"+messageText+"_"+mesgReqdString+"_"+noteType+"_"+noteTypeDesc;
				}
			}
		
		}

     var changeComments = $("#changeCommentsId").val();
  	 
	var param="?selectedruleId="+selectedruleId+"&ruleDesc="+ruleDesc+
	"&selectedspsId="+selectedspsId+"&spsDesc="+spsDesc+"&noteType="+noteType+"&NoteTypeDesc="+NoteTypeDesc+"&messageText="+messageText+
	"&individualMappingString="+individualMappingString+"&changeComments="+changeComments+msgRqdChkBoxString;
	
	url=url+param;
	//newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:890px;resizable=false;status=no;title=Print Mapping;");
 	window.open(url,"","dialogHeight:650px;dialogWidth:890px;resizable=false;status=no;title=Print Mapping;");
  }	
  function closeAction(){
	if(newWinForView!= null && newWinForView!='undefined'){
		newWinForView.close();
	}
  }	
  //BXNI Change
  function messageCheck(msgValue){
	var isMsgvalid = checkMessageValidation(msgValue);
	if(!isMsgvalid){
	return false;}
	else{ 
	return true;}
}
//BXNI Change Ends
  
  // Added as part of SSCR 19537 -- Individual Mapping for EB03
	function displayIndividualMappingPanel(){
		if ($('#individualMappingForEB03ChkBox').is(':checked') ){
				$('#ruleLevelMappingTable').hide();
				$('#eb03AssnTable').show();
				//$('#eb03IndividualMappingTable').show();
		}
		if (!$('#individualMappingForEB03ChkBox').is(':checked') ){
				$('#ruleLevelMappingTable').show();
				$('#eb03AssnTable').hide();
				//$('#eb03IndividualMappingTable').hide();
		}
	}
	
	//Added as part of SSCR 19537
	function createEB03AssnObject(){
		var eb03AssnTable = document.getElementById('eb03AssnTable');
		var eb03AssnJSON = [];
		var count = 0;
		
		if(null != eb03AssnTable.getElementsByTagName("TR") && null != eb03AssnTable.getElementsByTagName("TR").item(1) && 
		null != eb03AssnTable.getElementsByTagName("TR").item(1).getElementsByTagName("TD") &&
		null != eb03AssnTable.getElementsByTagName("TR").item(1).getElementsByTagName("TD").item(0) &&
		eb03AssnTable.getElementsByTagName("TR").item(1).getElementsByTagName("TD").item(0).innerText != ""){
		
			for(var i = 1; i < eb03AssnTable.getElementsByTagName("TR").length; i++){
				var eb03			=	eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(0).innerText;
				var msgText  		=   eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(1).getElementsByTagName("textarea").item(0).value;
				var msgReqd = "N";
				if(eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(3).getElementsByTagName("input").item(0).checked){
					msgReqd			=	"Y";
				}
				var noteType		=	eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(4).getElementsByTagName("input").item(0).value;
				var noteTypeDesVal	=	eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(6).innerText;
				//push the value to the json array
				eb03AssnJSON.push({"EB03": eb03,"MSG":msgText,"MSG_REQD": msgReqd,"NOTE_TYPE": noteType,"NOTE_TYPE_DESC": noteTypeDesVal});
				count++;
			}
				//after the iteration, stringify the json array and assign the same to a hidden request parameter
				eb03AssnJSON = JSON.stringify(eb03AssnJSON);
				document.getElementById('hdeb03AssnJSON').value=eb03AssnJSON;
				document.getElementById('eb03Count').value = count;
				return true;
		}
	
	}
	
		function imposeMaxLengthOnMultipleMessages(){

			var eb03AssnTable = document.getElementById('eb03AssnTable');
			var eb03List = "";
			for(var i = 1; i < eb03AssnTable.getElementsByTagName("TR").length; i++){
				var eb03Val =  eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(0).innerText;
				
				if($('#individualMappingForEB03ChkBox').is(':checked') ){
					var messageText = $.trim($('#MessageId'+(i-1)).val());
					if(messageText!=null && messageText.length > 75){
						if(eb03List == ""){
						eb03List = eb03Val;
						}else{
						eb03List = eb03List +", "+eb03Val;
						}
					}
				
				
				}else{
					var messageText = $.trim($('#messageTextId').val());
					if(messageText!=null && messageText.length > 75){
						if(eb03List == ""){
							eb03List = eb03Val;
						}else{
							eb03List = eb03List +", "+eb03Val;
						}
					}
				}
				

				
				}
			if(eb03List != ""){
				var message = "Text length should be 75 characters or less for message mapped to EB03(s) - "+eb03List;			
				addErrorToNotificationTray(message);
				openTrayNotification();	
				return false;
				}
			return true;
			}
	
			function specialCharacterValidationForMessage(){
			if ($('#individualMappingForEB03ChkBox').is(':checked') ){
				var eb03AssnTable = document.getElementById('eb03AssnTable');
				var eb03ListForSpclChar = "";
				for(var i = 1; i < eb03AssnTable.getElementsByTagName("TR").length; i++){
					var eb03Val =  eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(0).innerText;
					var messageText = $.trim($('#MessageId'+(i-1)).val());
						var spclCharNotPresent = messageCheck(messageText);
						if(!spclCharNotPresent){
							if(eb03ListForSpclChar == ""){
								eb03ListForSpclChar = eb03Val;
							}else{
								eb03ListForSpclChar = eb03ListForSpclChar +", "+eb03Val;
							}
						}
					}
			if(eb03ListForSpclChar != ""){
					var message = "* ^ - ~ { } # _ : ; ? ! [ ] < > are invalid characters for Message Text. Review Message Text mapped to EB03(s) - "+eb03ListForSpclChar;			
					addErrorToNotificationTray(message);
					openTrayNotification();	
					return false;
				}
				}
				return true;
			}
	
</script>
</head>
<body onload="storeinputs(); displayIndividualMappingPanel();">
	<form name="editForm"  method="post">
	<%@include file="/WEB-INF/jspf/pageTop.jspf"%>
		<input type="hidden" id="token" value="${TRANSACTION_TOKEN_KEY}" name="tokenKey" />	
		<input type="hidden" id="pageFrom" name="pageFrom" value="${mapping.pageFrom}" /> 
		<input type="hidden" id="hdeb03AssnJSON" name="hdeb03AssnJSON" /> 
		<input type="hidden" id="eb03Count" name="eb03Count" />
		<input type="hidden" id="createdDate" name="createdDate" value="${mapping.createdDate}" /> 
		<input type="hidden" id="NoteTypeDesc" name="NoteTypeDesc" value="${mapping.noteTypeCode.hippaCodeSelectedValues[0].description}" /> 

		  <div class="innerContainer" style="height:96%;">
            <!-- innerContainer Starts-->
               <div id="variableInfoDiv" class="overflowContainerVariable">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="Pd3">
                  <THEAD>
						<tr class="createEditTable1">
		                    <td width="70" class="tableHeader">Header Rule</td>
		                    <td width="200" class="tableHeader">Description</td>
		                    <td width="70" class="tableHeader">SPS ID</td>
		                    <td width="200" class="tableHeader">SPS Description</td>	
		                    <td align="right"><a href="#" onclick='printPage();'><img src="${imageDir}/print.gif" id="printIcon" style="height:15px; padding-right:30px"></a></td>	   	                   		  	                   
	                  	</tr>
				</THEAD>
				<TBODY>	
				<c:set var="viewHistoryRowCount" value="0" />									
					<tr>			
						<input type="hidden" name="selectedruleId" id="ruleIdHidden" value="${mapping.rule.headerRuleId}"/>	
						<input type="hidden" name="ruleDesc" id="ruleDescHidden" value="${mapping.rule.ruleDesc}"/>	
						<input type="hidden" name="selectedspsId" id="spsIdHidden" value="${mapping.spsId.spsId}"/>	
						<input type="hidden" name="spsDesc" id="spsDescHidden" value="${mapping.spsId.spsDesc}"/>	
					
						<td width="65px">${mapping.rule.headerRuleId}</td>
						<td width="200px">${mapping.rule.ruleDesc}</td>
						<td width="65px">${mapping.spsId.spsId}</td>
						<td width="200px">${mapping.spsId.spsDesc}</td>	
						<td></td>						
					</tr>
				</TBODY>             
                </table>
			</div><!-- variableInfoDiv closed -->
		
		  <div id="createEditContainer" style="margin-top:10px;">	<!--createEditContainer Starts-->			
		  		<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher1"
									textComponentName="messageText"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" finishedListener="spellFinMessageAction"
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>
				<!-- Adding Spell Check Launchers for all Message TextAreas -->
				<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher_0"
									textComponentName="MessageId0"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" finishedListener="spellFinMessageAction"
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>	
				
				<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher_1"
									textComponentName="MessageId1"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" 
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>
				
				<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher_2"
									textComponentName="MessageId2"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" 
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>
				
				<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher_3"
									textComponentName="MessageId3"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" 
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>
				
				<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher_4"
									textComponentName="MessageId4"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" 
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>
				
				<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher_5"
									textComponentName="MessageId5"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" 
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>
				
				<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher_6"
									textComponentName="MessageId6"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" 
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>
				
				<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher_7"
									textComponentName="MessageId7"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" 
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>
				
				<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher_8"
									textComponentName="MessageId8"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" 
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>
				
				<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher_9"
									textComponentName="MessageId9"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" 
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>
				
				<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher_10"
									textComponentName="MessageId10"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" 
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>
				
				<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher_11"
									textComponentName="MessageId11"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" 
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>
				
				<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher_12"
									textComponentName="MessageId12"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True"
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>
				
				<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher_13"
									textComponentName="MessageId13"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True"
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>
				
				<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher_14"
									textComponentName="MessageId14"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" 
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>
				
				<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher_15"
									textComponentName="MessageId15"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" 
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>
				
				<!-- Ends Here -->
								
				<!--First Table-->	
				<table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3" id="ebCodesTable">
						<table width="950">
							<tr>
							 <td class="headText" nowrap style="width:315px;font-size: 11px">
							<input type="checkbox" onclick = "displayIndividualMappingPanel()"<c:if test="${mapping.indvdlEb03AssnIndicator== 'Y'}">checked</c:if> 
							name="individualMappingForEB03ChkBox" id="individualMappingForEB03ChkBox" />&#160;Individual EB03 Mapping Reqd&#160;&#160;&#160;						
						</td>
			            	</tr>
						</table>		
					<table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3" id ="ruleLevelMappingTable" name = "ruleLevelMappingTable">
	                  <tr class="createEditTable1-Listdata">
	                     <td width="25%" colspan="6" nowrap class="headText">Message Text &#160;&#160;<a href="#" onclick="displayInfo('MSG',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;<a href="#">
						<img src="${imageDir}/icon-popup.gif" width="15" height="14"  onclick="openMessageTextDialog('messageTextId')"
						 id="MESSAGE_TEXT" /></td>
	                    <td><textarea name="messageText" id="messageTextId" rows="3" cols="77">${mapping.message}</textarea></td>
	                  </tr>
	                  <tr class="createEditTable1-Listdata alternate">
	                  <td width="25%" class="headText">Message &#160;&#160;&#160;&#160;&#160;&#160;&#160;
                        <input type="checkbox" name="msgRqdChkBox" id="msgRqdChkBox" <c:if test="${mapping.msg_type_required == 'true'}">checked</c:if>  value="checked" />&#160;&#160;&#160;&#160;&#160;Required&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
                        </td>
	                  <td>
	                  </td>
	                  <td>
	                  </td>
	                  <td>
	                  </td>
	                  	<td></td>  
						<td></td>	
						<td></td>  
						<td></td>
						<td></td>	
						<td></td>  
						<td></td>	
						<td></td>  
						<td></td>
						<td></td>	
	                </tr>
	                   <tr class="createEditTable1-Listdata">
	                    <td  width="25%" colspan="6" nowrap class="headText">Note Type Code &#160;&#160;<a href="#" onclick="displayInfo('NOTE_TYPE_CODE',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;<a href="#">
						<img src="${imageDir}/icon-popup.gif" width="15" height="14"
						 id="NOTE_TYPE_CODE" onclick="popupDiv('NOTE_TYPE_CODE','../ajaxpopup.ajax')"/></a></td>
						 <td >
							<input type="text" name="noteType" id="NOTETYPEID" class="inputbox60" value="${mapping.noteTypeCode.hippaCodeSelectedValues[0].value}"
							onselect="autocompleteNoteType();" onclick="autocompleteNoteType();"/>
						&#160;&#160;&#160;<label id="NOTETYPEIDLabel" for="NOTETYPEID" name="NOTETYPEIDLabel" style="font-size:11px">
									<c:out value="${mapping.noteTypeCode.hippaCodeSelectedValues[0].description}" />
							</label>
						</td>
						<!--  <td width="10%">
							<label id="NOTETYPEIDLabel" for="NOTETYPEID" style="font-size:11px">
									<c:out value="${mapping.noteTypeCode.hippaCodeSelectedValues[0].description}" />
							</label>
						</td>-->
						 </tr>
		</table>

	<!-- New Table added for Individual mapping -->
	<table width="950" border="0" cellpadding="0" cellspacing="0"
		class="Pd3 mT5 bT" id="eb03AssnTable" name="eb03AssnTable">
		<tr class="createEditTable1-Listdata">
			<td class="headText" style=width:100px">EB03</td>

			<td class="headText" colspan="3">Message Text <a href="#"
				onclick="displayInfo('MSG',event,'../ajaxhippasegmenttooltip.ajax');">what
			is this?</a>&#160;</td>

			<td class="headText" align="center">Msg Reqd</td>

			<td class="headText" colspan="3">Note Type Code <a href="#"
				onclick="displayInfo('NOTE_TYPE_CODE',event,'../ajaxhippasegmenttooltip.ajax');">what
			is this?</a>&#160;</td>

			<c:set var="count" value="0" />
			<c:set var="rowCount" value="2" />
	
			<c:if test="${mapping.eb03.eb03Association != null && ! empty mapping.eb03.eb03Association}">
			<c:forEach items="${mapping.eb03.eb03Association}" var="eb03Assn"
				begin="0">
				

					<tr class="${rowCount mod 2 == 0 ? 'alternate' : 'white-bg'}">

					<td style="width: 100px"><label id="EB03Label${count}" name="EB03Label${count}"
						for="EB03Label${count}" style="font-size: 11px">
					${eb03Assn.eb03.value} </label></td>
					<td style="width: 500px" colspan="2">
					<textarea style="width: 400px;border:1px solid grey;"
					 colspan="2" name="MessageId${count}" id="MessageId${count}" rows="1" onclick="this.rows = '2';" onblur="this.rows = '1'; changeTextToUpperCase(this.id);"
						>${eb03Assn.message}</textarea></td>
					<td style="width: 25px"><img src="${imageDir}/icon-popup.gif" width="15" height="14" name = "MessageId${count}" onclick="openMessageTextDialog(this.name)"
						 id="MESSAGE_TEXT" />
					</td>

					<td class="headText" nowrap style="width: 75px;" align="center" ><input
						type="checkbox" 
						<c:if test="${eb03Assn.msgReqdInd == 'Y'}">checked</c:if>
						name="msgReqd${count}" id="msgReqd${count}" /></td>
					<td style="width: 100px"><input type="text"
						name="NoteTypeId${count}" class="inputbox60"
						id="NOTETYPEID${count}" value="${eb03Assn.noteType.value}" onselect="autocompleteNoteType();" onclick="autocompleteNoteType();"/></td>
					<td style="width: 25px"><img src="${imageDir}/icon-popup.gif"
						width="15" height="14" id="NoteTypePoup${count}" name="NOTE_TYPE_CODE_${count}"
						onclick="popupDiv(this.name,'../ajaxpopup.ajax');"/>
					</td>
					<td style="width: 125px"><label id="NOTETYPEID${count}Label"
						 for="NOTETYPEID${count}Label" style="font-size: 11px">
					${eb03Assn.noteType.description} </label></td>


					<c:set var="count" value="${count + 1}" />
					<c:set var="rowCount" value="${rowCount + 1}" />

				</tr>
				
				
			</c:forEach>
			</c:if>

		</tr>
	</table>


	<!-- Individual Mapping panel ends here -->

					<table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3" id="accumTable"/>
					<div>
					</div>
					 <table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5 bT">
	                  <tr class="">
	                    <td width="900px" class="headText">Change Comments</td>
					  </tr>
					  <tr class="">
	                    <td><textarea name="changeComments" id="changeCommentsId" rows="5" cols="110" >${changeComments}</textarea></td>
					  </tr> 			
					</table>
			
				</table></table>
				</div><!--createEditContainer Ends-->	
        	</div>
		  <!-- innerContainer Ends-->
	</div>
<!-- container Ends-->
<div class="footer">
			<div class="ajaxIdle" id="ajaxIdleIcon">
				<div class="clear"></div>
			</div>
			<div class="ajaxActive" id="ajaxActiveIcon">
				<div class="clear"></div>
			</div>
				<input type="hidden" id="selectedruleIdCopyTo" value="" name="selectedruleIdCopyTo"/>
 				<input type="hidden" id="selectedruleId" value="" name="selectedruleId"/>	
 				<input type="hidden" id="ruleDesc" value="" name="ruleDesc"/>
 				<input type="hidden" id="selectedspsIdCopyTo" value="" name="selectedspsIdCopyTo"/>
 				<input type="hidden" id="selectedspsId" value="" name="selectedspsId"/>	
 				<input type="hidden" id="spsDesc" value="" name="spsDesc"/>				
				<input type="hidden" id="token" value="${TRANSACTION_TOKEN_KEY}" name="tokenKey" />
				<c:set var="discardChange" value="0"/>				
				<c:if test="${empty mapping.inTempTable || mapping.inTempTable == 'N' }">
					<c:set var="discardChange" value="1"/>
				</c:if>
			<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
		      <tr>
				<td width="18" height="20"><img src="${imageDir}/footer_left.gif" width="18" height="20" /></td>
				<td width="0" height="20"><a href="#" onclick ='openAlertDialog();'>Copy To</a></td>
		        <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>		       
		        <td width="0" height="20"><a href="#" onclick ='openViewHistoryDialog("${mapping.rule.headerRuleId}","${mapping.spsId.spsId}");'>View history</a></td>
		        <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
		        <td width="0" height="20"><a href="#" onclick='runSpellCheck("${mapping.rule.headerRuleId}", "${mapping.rule.ruleDesc}","${mapping.spsId.spsId}", "${mapping.spsId.spsDesc}","save");'>Save</a></td>
		        <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
		        <td width="0" height="20"><a href="#" onclick='runSpellCheck("${mapping.rule.headerRuleId}", "${mapping.rule.ruleDesc}","${mapping.spsId.spsId}", "${mapping.spsId.spsDesc}","done");'>Done</a></td>
		        <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
		        <td width="0" height="20"><a href="#" onclick ='cancel("${mapping.rule.headerRuleId}","${mapping.spsId.spsId}");'>Cancel</a></td>
				<td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
				<td width="0" height="20"><a href="#" onclick='deleteMapping("${mapping.rule.headerRuleId}", "${mapping.rule.ruleDesc}","${mapping.spsId.spsId}", "${mapping.spsId.spsDesc}");'>Delete</a></td>
				
				<c:if test="${mapping.variableMappingStatus != 'SCHEDULED_TO_PRODUCTION' &&
							mapping.variableMappingStatus != 'SCHEDULED_TO_TEST' &&
							mapping.variableMappingStatus != 'NOT_APPLICABLE' &&
							mapping.variableMappingStatus != 'PUBLISHED'}">	
						
						 <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
						<td width="0" height="20"><a href="#" onclick='runSpellCheck("${mapping.rule.headerRuleId}", "${mapping.rule.ruleDesc}","${mapping.spsId.spsId}", "${mapping.spsId.spsDesc}","sendToTest");'>Send to Test</a></td>
				
				</c:if>
				
				<c:if test="${mapping.variableMappingStatus != 'SCHEDULED_TO_PRODUCTION' &&
							mapping.variableMappingStatus != 'SCHEDULED_TO_TEST' &&
							mapping.variableMappingStatus != 'NOT_APPLICABLE' &&
							mapping.variableMappingStatus != 'PUBLISHED'}">	
							<c:if test="${userUIPermissions.authorizedToapprove}">	
						<td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
						<td width="0" height="20"><a href="#" onclick='runSpellCheck("${mapping.rule.headerRuleId}", "${mapping.rule.ruleDesc}","${mapping.spsId.spsId}", "${mapping.spsId.spsDesc}","approve");'>Approve</a></td>				
					</c:if>
				</c:if>
				
				<c:if test="${discardChange != 1}">
					<td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
			        <td width="0" height="20"><a href="#" onclick='discardChanges("${mapping.rule.headerRuleId}", "${mapping.rule.ruleDesc}","${mapping.spsId.spsId}", "${mapping.spsId.spsDesc}");' class="dicard">Discard Changes</a></td>				
					<td width="18" height="20"><img src="${imageDir}/footer_right.gif" width="18" height="20" /></td>
				</c:if>

				<c:if test="${discardChange == 1}">					
					<td width="18" height="20"><img src="${imageDir}/footer_right.gif" width="18" height="20" /></td>
				</c:if>
				<!-- <c:if test="${userUIPermissions.authorizedToMarkAsNotApplicable}">
				</c:if>-->
				<!--<c:if test="${userUIPermissions.authorizedToapprove}">
				</c:if>-->
		      </tr>
		    </table>					
		</div>	
	
	</div>
<!-- wrapper Ends-->
<div id="copyToDialog" >
	<%@include file="/WEB-INF/jsp/copytocustommsg.jsp"%>
</div>
</form>
<div id="viewHistoryDialog" title="View History">
</div>
<!-- wrapper Ends-->
<div id="hippaCodePopUpDiv"></div>
<script type="text/javascript">
	sniffer();
</script>
<%@include file="/WEB-INF/jspf/whatIsThisToolTip.jspf"%>
<div id="confirmationDiv"></div>
<div id="messageTextDialog">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="Pd3">
        <tr class="createEditTable1-Listdata">
          <td colspan="6" nowrap class="headText">EBO3 &#160;</td>
          <td><input type="text" name="msgTxtEb03" class="inputbox60" id="msgTxtEb03Id" value=""/></td>
          <td colspan="6" nowrap class="headText">&#160;Header Rule ID &#160;</td>
          <td><input type="text" name="msgTxtVar" class="inputbox60" id="msgTxtVarId" value=""/></td>
          <td colspan="6" nowrap class="headText">&#160;Message Text &#160;</td>
          <td><input type="text" name="msgTxt" class="inputbox60" id="msgTxtId" value=""/></td>
        </tr>
        <tr><td colspan="6"></td><td></td>
        <td colspan="6"></td><td></td>
        <td colspan ="8"nowrap class="headText"><input type="checkbox" id="showOnlyStandardMessages" name ="showOnlyStandardMessages" value ="false">&#160; Show Only Standard Messages&#160;</td>
        </tr>
    </table>     
    <table width="100%" height="20" ><tr><td></td></tr></table>
	<table  border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>  
		<td>
			<a href="#" onclick="searchMessageText('1','Init')"><img src="${imageDir}/search_but.png" 
			alt="Search" title="Search"/></a>
		</td>	
      </tr>
	</table>
	<table width="100%" height="20" ><tr><td>
		<input type="hidden" id="hdMsgTxtId" name="hdMsgTxt"/>
		<input type="hidden" id="hdMsgTxtEB03Id" name="hdMsgEB03Txt"/>
		<input type="hidden" id="hdMsgTxtHdrId" name="hdMsgHdrTxt"/>
		<input type="hidden" id="hdMessageId" name="hdMessageName"/>
			</td></tr></table>
	<div id="messageTextResult"></div>
</div>
<div id="alertDiv"></div>
<div id="confirmationDivDiscardChanges"></div>
<div id="confirmationDivDeleteChanges"></div>
<%@include file="/WEB-INF/jspf/messageTray.jspf"%>
</body>
</html>