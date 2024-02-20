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

<script type="text/javascript">



function openStandardMessageViewHistoryPopUp() {
	$.ajax({
		url: "../referencedata/viewHistoryOfStandardMessage.ajax",
		dataType: "html",
		type: "POST",
		//data: 
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

function deleteStandardMessageConfirmationDialog(stdMsgToDelete){
 var warningMsgDeleteAllAction  = "The standard message will be deleted. "
 								+"Do you wish to continue?";	
 						   
 $("#confirmationDivDeleteAction").html(warningMsgDeleteAllAction);
	$("#confirmationDivDeleteAction").dialog({
			autoOpen: false,
			title : 'Confirm',
			resizable: false,
			height:140,
			width:400,
			modal: true,
			buttons: {			
			No: function() {
				$(this).dialog('close');
				},		
			Yes: function() {
					deleteStandardMessage(stdMsgToDelete);
					$(this).dialog('close');
				}
			}
		});
		$("#confirmationDivDeleteAction").dialog('open');
}

function deleteStandardMessage(stdMsgToDelete){
		$("#deleteActionStandardMessage").val(stdMsgToDelete);
		$("#searchActionStandardMessage").val($.trim($("#StdMsg").val()));
		
		$("#userCommentsDeleteDialog table#deleteActionUserCommentsTable").css("border-top","1px solid black");
				$("#userCommentsDeleteDialog").dialog({
	                        height:'auto',
							width:'450px',	
							resizable:false,
	                        show:'slide',
							title: 'Confirm',
	                        modal: true
	                  });
			$("#userCommentsDeleteDialog").dialog();
	}

function saveUserCommentsForDeleteStandardMessage(){	
var userComments = $("#deleteAllUserComments").val();
		if(userComments == null || userComments == ""){
		var message = "User Comments are mandatory";			
		addErrorToNotificationTray(message);
		openTrayNotification();	
		return false;
		}
	if(!imposeMaxLength('deleteAllUserComments',250,'User comments')){						
			return false;
		}				
		document.forms['userCommentsDeleteActionDialogForm'].submit();
	}
function createStandardMessage(){
var newStdMsgToCreate = $.trim($("#StdMsg").val());
if(newStdMsgToCreate == null || newStdMsgToCreate == ""){
var message = "Please enter the message to create";			
		addErrorToNotificationTray(message);
		openTrayNotification();	
		document.getElementById('exclusionSectionTwo1').style.display= 'none';
		document.getElementById('exclusionSectionThree1').style.display= 'none';
		return false;
}
	if(!validateLengthOfStdMsg('StdMsg',75)){
		document.getElementById('exclusionSectionTwo1').style.display= 'none';
		document.getElementById('exclusionSectionThree1').style.display= 'none';					
		return false;
	}
	if(!validateStandardMessage('StdMsg')){
		document.getElementById('exclusionSectionTwo1').style.display= 'none';
		document.getElementById('exclusionSectionThree1').style.display= 'none';						
		return false;
	}
	$("#stdMsgCreateActionSave").val($.trim($("#StdMsg").val()));
	$("#stdMsgCreateActionSearch").val($.trim($("#stdMsgSearch").val()));
	
	$("#userCommentsCreateDialog table#createActionUserCommentsTable").css("border-top","1px solid black");
		$("#userCommentsCreateDialog").dialog({
                       height:'auto',
					width:'450px',	
					resizable:false,
                       show:'slide',
					title: 'Confirm',
                       modal: true
                 });
	$("#userCommentsCreateDialog").dialog();
}

function createUserCommentsStandardMessage(){	
var userComments = $("#createUserComments").val();
		if(userComments == null || userComments == ""){
		var message = "User Comments are mandatory";			
		addErrorToNotificationTray(message);
		openTrayNotification();
		return false;
		}
	if(!imposeMaxLength('createUserComments',250,'User comments')){						
			return false;
		}	
		 $("#saveUserComments").val($.trim($("#createUserComments").val()));
		 $('#saveUserComments').val($('#saveUserComments').val().toUpperCase());				
		document.forms['standardMessageForm'].action="${context}/referencedata/createStandardMessage.html";
		document.forms['standardMessageForm'].submit();
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
			action = "${context}/referencedata/showManageStandardMessagePage.html";
			window.location.href = action; 
			break; 
	}
}
$(document).ready(function(){	
	$('#StdMsg').blur(function() {
		$('#StdMsg').val($('#StdMsg').val().toUpperCase());		
	});
 });
 
function fetchStandardMessage() {
		var standardMsg = $.trim($("#StdMsg").val());
		if((standardMsg == null || standardMsg == "")) {
			addErrorToNotificationTray('Please enter the search criteria');
			openTrayNotification();
			document.getElementById('exclusionSectionTwo1').style.display= 'none';
			document.getElementById('exclusionSectionThree1').style.display= 'none';
			return false;
		}
		if(!validateLengthOfStdMsg('StdMsg',75)){
			document.getElementById('exclusionSectionTwo1').style.display= 'none';
			document.getElementById('exclusionSectionThree1').style.display= 'none';						
			return false;
		}	
		if(!validateStandardMessage('StdMsg')){
			document.getElementById('exclusionSectionTwo1').style.display= 'none';
			document.getElementById('exclusionSectionThree1').style.display= 'none';				
			return false;
		}
			$.ajax({
			url: "${context}/referencedata/fetchStandardMessage.ajax",
		dataType: "html",
		type: "POST",
		data: "standardMsg="+escape(standardMsg),
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
function saveStandardMessage(){

var newStdMsgToSave = $.trim($("#standardMsgEditNew").val());
if(newStdMsgToSave == null || newStdMsgToSave == ""){
var message = "Please enter the message to update";			
		addErrorToNotificationTray(message);
		openTrayNotification();	
		return false;
}
if(!validateLengthOfStdMsg('standardMsgEditNew',75)){						
		return false;
}
if(!validateStandardMessage('standardMsgEditNew')){	
		return false;
}
	$("#stdMsgValueUpdateSaveAction").val($.trim($("#standardMsgEditFromPage").val()));
	$("#stdMsgSearchActionSave").val($.trim($("#StdMsg").val()));
	$("#standardMsgUpdatedNewSave").val($.trim($("#standardMsgEditNew").val()));
 
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

function saveUserCommentsForSaveAction(){	
var userComments = $("#saveUserCommentsTextArea").val();
		if(userComments == null || userComments == ""){
		var message = "User Comments are mandatory";			
		addErrorToNotificationTray(message);
		openTrayNotification();	
		return false;
		}
	if(!imposeMaxLength('saveUserCommentsTextArea',250,'User comments')){						
			return false;
		}	
		 $("#saveUserComments").val($.trim($("#saveUserCommentsTextArea").val()));
		 $('#saveUserComments').val($('#saveUserComments').val().toUpperCase());				
		document.forms['standardMessageForm'].action="${context}/referencedata/saveStandardMessage.html";
		document.forms['standardMessageForm'].submit();
	}

  
function disableUserCommentsDiv(){
document.getElementById('userCommentsCreateDialog').style.display= 'none';
document.getElementById('userCommentsSaveDialog').style.display= 'none';
document.getElementById('userCommentsDeleteDialog').style.display= 'none';
}

function editStandardMessage(stdMessage) {
		$.ajax({
			url: "${context}/referencedata/editStandaredMessage.ajax",
			dataType: "html",
			type: "POST",
			data: "standardMsg="+escape(stdMessage),
			success: function(data) {
					document.getElementById('exclusionSectionThree1').style.display= 'block';
						document.getElementById('editpage').style.display= 'block';
						document.getElementById('initialLoad').style.display= 'none';
						document.getElementById('createupdate').style.display= 'block';
					$("#editpage").html(data);
			}
		});
 }	
 
function saveUserCommentsForDeleteAssociation(){	
var userComments = $("#deleteAllUserComments").val();
		if(userComments == null || userComments == ""){
		var message = "User Comments are mandatory";			
		addErrorToNotificationTray(message);
		openTrayNotification();	
		return false;
		}
	if(!imposeMaxLength('deleteAllUserComments',250,'User comments')){						
			return false;
		}				
		document.forms['userCommentsDeleteAllActionDialogForm'].submit();
	}

function imposeMaxLength(elementId, MaxLen, element){
	var valueOfTextArea = $.trim($('#'+elementId).val());
	$('#'+elementId).val(valueOfTextArea);
	if(valueOfTextArea!=null && valueOfTextArea.length > MaxLen) {
		var message = element + "cannot be more than "+MaxLen+" characters";
		addErrorToNotificationTray(message);
		openTrayNotification();	
		return false;
  		}
		return true;
	}	
	
function validateLengthOfStdMsg(elementId, MaxLen){
	var textValue = $.trim($('#'+elementId).val());
	$('#'+elementId).val(textValue);
	if(textValue!=null && textValue.length > MaxLen) {
		var message = "The message cannot be more than " +MaxLen+" characters.";			
		addErrorToNotificationTray(message);
		openTrayNotification();	
		return false;
  		}
		return true;
	}		
function validateStandardMessage(msgText){

var textValue = $.trim($('#'+msgText).val());
var spclChars = "*^~{}#_:;?![]<>-";
	for (var i = 0; i < textValue.length; i++) {
		if (spclChars.indexOf(textValue.charAt(i)) != -1) {
             addErrorToNotificationTray('* ^ ~ { } – _ : ; ? ! [ ] < > are invalid characters for Message Text');
             openTrayNotification();
             return false;
         } 
    }
	return true;
}

      
</script>
</head>
<script type="text/javascript">

/*  function backAction(){
		var action ;
		action = "../referencedata/closeAction.ajax";
		window.location.href = action;
} */

 function backAction(){
	document.forms['standardMessageForm'].action="../ebxspiderworkflow/back.html";
    document.forms["standardMessageForm"].submit();
}

function sniffer() {
	   if(screen.height==1024) {
	   
	     document.getElementById('exclusionEditContainer1').style.height = "580px";
	     document.getElementById('exclusionSectionTwo1').style.height = "350px";
	     document.getElementById('exclusionContentSectionTwo1').style.height = "320px";
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

<body onload="disableUserCommentsDiv();">
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="context" value="${pageContext.request.contextPath}"/>



<form name="standardMessageForm" method="POST">

<input type="hidden" name="stdMsgValueUpdateSaveAction" id="stdMsgValueUpdateSaveAction" value="" /> 
<input type="hidden" name="stdMsgSearchActionSave" id="stdMsgSearchActionSave" value="" /> 
<input type="hidden" name="standardMsgUpdatedNewSave" id="standardMsgUpdatedNewSave" value="" />
<input type="hidden" name="saveUserComments" id="saveUserComments" value="" />
<input type="hidden" name="stdMsgCreateActionSave" id="stdMsgCreateActionSave" value="" />
<input type="hidden" name="stdMsgCreateActionSearch" id="stdMsgCreateActionSearch" value="" />


 
<%@include	file="/WEB-INF/jspf/pageTop.jspf"%>
<div class="innerContainer" style="height: 98%" class="Pd3">
<div id="exclusionEditContainer1">
<div id="exclusionSectionOne1" style=" height: 120px">
<div id="exclusionTitleBarSectionOne1">
<div class="headerTitleExclusion">Manage Standard Message</div>
</div>
<div id="exclusionContentSectionOne1">
<table style="margin-top: 3px;" class="mt10 mL10 Pd3 shadedText" id="exclusionTableWidth1" border="0" cellspacing="0" cellpadding="0">
	<TBODY>
		<tr class="createEditTable1-Listdata" style="padding-top: 0px">
		</tr>
		<div><tr> &nbsp </td></tr></div>
		<tr>
			<td style="vertical-align:middle" width="100px" class="headTextExclusions">Message
			</td>
	        <td>
				<textarea class="standardMsg" id="StdMsg" name="StdMsg" >${StdMsg}</textarea></td>
				<td width="10%"></td>
				<td width="40%px"></td>
			
							
		</tr> 
		<tr>
			<c:if test="${userUIPermissions.authorizedToEditStandardMessage}">				
				<td align="right" colspan="2"><a href="#"><IMG id="Locate"	src="${imageDir}/locate_but.gif" width="70" height="18" alt="Locate"
					onclick="fetchStandardMessage();"></a>
				</td>
				<td width="10%" align="right"><a href="#"><IMG id="Create"	src="${imageDir}/create_but.gif" width="70" height="18" alt="Create"
					onclick="createStandardMessage();"></a>
				</td>
				<td width="40%px">
			</c:if>
			<c:if test="${!userUIPermissions.authorizedToEditStandardMessage}">				
				<td align="center" colspan="4"><a href="#"><IMG id="Locate"	src="${imageDir}/locate_but.gif" width="70" height="18" alt="Locate"
					onclick="fetchStandardMessage();"></a>
				</td>
			</c:if>
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
			<th id="systemId" width="5%" class="tableHeader">Message</th>
			<!--<th id="systemId" width="29%" class="tableHeader">Data Type</th>
			--><th id="createdId" width="7%" class="tableHeader">&nbsp;</th>

		</tr>
	</THEAD>
</table>
</div>


<div id="exclusionContentSectionTwo1" style="width: 926px;">
<div id="dynamicRenderer">
<jsp:include flush="true" page="standardmessagesearchresult.jsp"></jsp:include></div>
<br>
</div>
</div>
<div id="exclusionSectionThree1" style="display: none; height: 100px">
<div class="exclusionTitleBarSectionTwo">
<div class="headerTitleSub">Manage</div>
</div>
<!-- <div class="exclusionSectionThreeSub1"> -->

<div id="editpage">
<jsp:include flush="true" page="standardmessageedit.jsp"></jsp:include></div>

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
		<td width="0" height="20"><a href="#" onclick='saveStandardMessage()'>Save</a></td>
		<td width="9" height="0"><img src="${imageDir}/seperator.gif"
			width="9" height="20" /></td>
		<td width="0" height="20"><a href="#" onclick='openStandardMessageViewHistoryPopUp()'>View	History</a></td>
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
		<td width="9" height="0"><img src="${imageDir}/seperator.gif"
			width="9" height="20" /></td>
		<td width="0" height="20"><a href="#" onclick='openStandardMessageViewHistoryPopUp()'>View	History</a></td>
		<td width="18" height="20"><img
			src="${imageDir}/footer_right.gif" width="18" height="20" /></td>
	</tr>
</table>

</div>

</div>
<script>
	       	sniffer();
	    
       	</script></form>

<c:if test="${ fromaction == 'DELETE' || fromaction == 'SELECT' || fromaction == 'UPDATE' || fromaction == 'CREATE'}">
	<script>
		document.getElementById('dynamicRenderer').style.display= 'block';
		document.getElementById('exclusionSectionTwo1').style.display= 'block';
	</script>
</c:if>
<c:if test="${ fromaction == 'DELETE' || fromaction == 'SELECT' }">
	<script>
		document.getElementById('initialLoad').style.display= 'block';
		document.getElementById('createupdate').style.display= 'none';
	</script>
</c:if>  
<c:if test="${ fromaction == 'CREATE' || fromaction == 'UPDATE' }">
	<script>
		document.getElementById('initialLoad').style.display= 'none';
		document.getElementById('createupdate').style.display= 'block';
	</script>
</c:if>  
<c:if test="${ fromaction == 'UPDATE' || fromaction == 'CREATE'}">
	<script>
		document.getElementById('exclusionSectionThree1').style.display= 'block';
		document.getElementById('editpage').style.display= 'block';
	</script>
</c:if> 

<div id="viewHistoryDialog" title="View History"></div>
<div id="referenceDataDialog"></div>
<div id="confirmationDivDeleteAction"></div>
<div id="confirmationDivEditAction"></div>
<div id="viewHistoryDivForAssociation"></div>
<div id="referanceDataPopUpDiv"></div>

</div> 
<div id="userCommentsCreateDialog" >
<form name="userCommentsSaveActionDialogForm" action="" method="POST">
	<table id="createActionUserCommentsTable" border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5 bT">          
	  <tr class="">
        <td > <textarea name="createUserComments" id="createUserComments"
		rows="5" cols="77"></textarea></td>
	  </tr> 			
	</table>
	<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
      <tr>       
        <td >
			<a
			href="#" onclick="createUserCommentsStandardMessage();"><img id="createActionSaveImg" src="${imageDir}/confirm_but.png" 
			alt="Confirm" title="Confirm" /></a>
		</td>		
      </tr>
	</table>	
</form>
</div>

<div id="userCommentsSaveDialog" >
<form name="userCommentsSaveActionDialogForm" action="" method="POST">
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
			href="#" onclick="saveUserCommentsForSaveAction();"><img id="saveActionSaveImg" src="${imageDir}/confirm_but.png" 
			alt="Confirm" title="Confirm" /></a>
		</td>		
      </tr>
	</table>	
</form>
</div>

<div id="userCommentsDeleteDialog" >
<form name="userCommentsDeleteActionDialogForm" action="${context}/referencedata/deleteStandardMessage.html" method="POST">
 <input type="hidden" name="deleteActionStandardMessage" id="deleteActionStandardMessage" value="" /> 
 <input type="hidden" name="searchActionStandardMessage" id="searchActionStandardMessage" value="" /> 
 
	<table id="deleteActionUserCommentsTable" border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5 bT">          
	  <tr class="">
        <td > <textarea name="deleteAllUserComments" id="deleteAllUserComments"
		rows="5" cols="77"></textarea></td>
	  </tr> 			
	</table>
	<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
      <tr>       
        <td >
			<a
			href="#" onclick="saveUserCommentsForDeleteStandardMessage();"><img id="deleteAllActionSaveImg" src="${imageDir}/save_but.gif" 
			alt="Save" title="Save" /></a>
		</td>		
      </tr>
	</table>	
</form>
</div>

<%@include file="/WEB-INF/jspf/whatIsThisToolTip.jspf"%>
<%@include file="/WEB-INF/jspf/messageTray.jspf"%>


</body>
</html>
