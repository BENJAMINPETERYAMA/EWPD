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

<!--
/****************** EB01-Data Type - start ******************/

function enterKeyPress(e) {
	if (e.keyCode == 13){
		$('#EB01Id').val($('#EB01Id').val().toUpperCase());	
		$('#dataTypeId').val($('#dataTypeId').val().toUpperCase());
	 	document.getElementById('Locate').click();
     }
}
function openViewHistoryPopUpAssociation() {
	resetMessages();
	$('#eb01ValueUpdateViewHistory').val($.trim($('#eb01IdFromPage').val()));
	var key = $("#eb01ValueUpdateViewHistory").val().toUpperCase();
	$.ajax({
		url: "../referencedata/viewHistoryOfDatatypeToEB01Mapping.ajax",
		dataType: "html",
		type: "POST",
		data: "eb01="+key,
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
function saveAssociation(){
        //BXNI-November Release Starts
        //Added Script for prventing Users to map EB01 value as "I"(Non-covered)
		var eb01 = $.trim($("#EB01Id").val());
		if(eb01=="I")
            {
            addErrorToNotificationTray('Data Type cannot be associated to EB01 = ‘I’');
			openTrayNotification();

           return false;
           }
          //BXNI-November Release Ends
   
	setDescriptionForDataType();
	$("#eb01ValueUpdateSaveAction").val($.trim($("#eb01IdFromPage").val()));
	$("#eb01DescUpdateSaveAction").val($.trim($("#eb01DescriptionFromPage").val()));
	$("#searchActionEB01IdSave").val($.trim($("#EB01Id").val()));
     $("#searchActionDataTypeIdSave").val($.trim($("#dataTypeId").val()));
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

function setDescriptionForDataType(){
	var rowLength = $('#dataTypeTable > tbody > tr').length;
	for(var i = 0; i < rowLength * 3; i++){	
		$('#dataTypeDesc'+i).val($('#dataTypeIdLabel'+i).text());
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
		//document.forms['userCommentsSaveActionDialogForm'].submit();
		document.forms['dataTypeToEB01Form'].action="${context}/referencedata/saveDatatypeToEB01Mapping.html";
		document.forms['dataTypeToEB01Form'].submit();
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
			action = "${context}/referencedata/showManageDataTypeToEB01Page.html";
			window.location.href = action; 
			break; 
	}
}

function fetchDataType() {
		
		var eb01 = $.trim($("#EB01Id").val());
		var dataType = $.trim($("#dataTypeId").val());
		if((eb01 == null || eb01 == "")&&(dataType == null || dataType == "")) {
			addErrorToNotificationTray('Please enter atleast one search criteria');
			openTrayNotification();
			document.getElementById('exclusionSectionTwo1').style.display= 'none';
			document.getElementById('exclusionSectionThree1').style.display= 'none';
			return false;
		}
		$.ajax({
			url: "${context}/referencedata/fetchDataTypeToEB01Mapping.ajax",
		dataType: "html",
		type: "POST",
		data: "eb01="+escape(eb01)+"&dataType="+escape(dataType),
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
 
 

$(document).ready(function(){
	$('#EB01Id').blur(function() {
		$('#EB01Id').val($('#EB01Id').val().toUpperCase());		
	});
	$("#EB01Id").autocomplete({ 
	
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=EB01",
						success: function(data) {
						cache: false,
						response(data.rows);
						list = data.rows;
						}
					});
				}
	})
  });

 $(document).ready(function(){	
	$('#dataTypeId').blur(function() {
		$('#dataTypeId').val($('#dataTypeId').val().toUpperCase());		
	});
	$("#dataTypeId").autocomplete({ 
		source: function(request, response) {
					$.ajax({
						url: "${context}/referencedata/fetchDataTypeForAutoComplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=DATATYPE",
						success: function(data) {
						cache: false,
						response(data.rows);
						list = data.rows;
						}
					});
				}
	})
	 
  });
  
function disableUserCommentsDiv(){
document.getElementById('userCommentsDeleteAllDialog').style.display= 'none';
document.getElementById('userCommentsSaveDialog').style.display= 'none';
}

function deleteAssociationConfirmationDialog(eb01ToDelete, dataTypeValuesDelete){
 var warningMsgDeleteAllAction  = "All the Data Type(s) associated with EB01 = "+eb01ToDelete+" will be deleted."
 								+"Do you wish to continue?";	
 						   
 $("#confirmationDivDeleteAllAction").html(warningMsgDeleteAllAction);
	$("#confirmationDivDeleteAllAction").dialog({
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
					deleteAssociation(eb01ToDelete, dataTypeValuesDelete);
					$(this).dialog('close');
				}
			}
		});
		$("#confirmationDivDeleteAllAction").dialog('open');
}
function editAssociationConfirmationDialog(eb01ToUpdate,eb01Description){
	var warningMsgEditAllAction = "Data Type(s) associated with EB01 = "+eb01ToUpdate+" will be updated."  
 						   +" Do you wish to continue?";
	$("#confirmationDivEditAction").html(warningMsgEditAllAction);
		$("#confirmationDivEditAction").dialog({
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
					editAssociation(eb01ToUpdate,eb01Description);
					$(this).dialog('close');
				}
			}
		});
		$("#confirmationDivEditAction").dialog('open');
}
function deleteAssociation(eb01ToDelete, dataTypeValuesDelete){
	
		$("#deleteAllActionEB01Id").val(eb01ToDelete);
		$("#deleteAllActionDataTypeValues").val(dataTypeValuesDelete);
    	 $("#searchActionEB01Id").val($.trim($("#EB01Id").val()));
    	 $("#searchActionDataTypeId").val($.trim($("#dataTypeId").val()));
		
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
 function editAssociation(eb01Value,eB01Description) {

	$("#eB01Description").val($.trim($("#eB01Description").val()));
		var key = "";
		$.ajax({
			url: "${context}/referencedata/editDataTypeToEB01Mapping.ajax",
			dataType: "html",
			type: "POST",
			data: "eb01ValueEdit="+escape(eb01Value)+"&eb01DescEdit="+escape(eB01Description),
			success: function(data) {
					document.getElementById('exclusionSectionThree1').style.display= 'block';
						document.getElementById('editpage').style.display= 'block';
						document.getElementById('initialLoad').style.display= 'none';
						document.getElementById('createupdate').style.display= 'block';
						$("#eb01ValueUpdate").val(eb01Value);
						$("#eb01DescUpdate").val(eB01Description);
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
	if(!imposeMaxLength('deleteAllUserComments',250,'user comments')){						
			return false;
		}				
		document.forms['userCommentsDeleteAllActionDialogForm'].submit();
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

 function backAction(){
		var action ;
		action = "../referencedata/closeAction.ajax";
		window.location.href = action;
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
/*
 function saveAssociation(){			
				document.forms['dataTypeToEB01Form'].action="${context}/referencedata/saveDatatypeToEB01Mapping.html";
		        document.forms["dataTypeToEB01Form"].submit();
			
      }	
      */
     
      
    
/****************** EB01-Data Type - End ******************/
--></script>
</head>
<script type="text/javascript">
function backAction()
{
    document.forms['dataTypeToEB01Form'].action="${context}/ebxspiderworkflow/back.html";
    document.forms["dataTypeToEB01Form"].submit();
}
<!--
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
 


      
      
 function cancelAction(){
		var categoryCode = $("#categorysave").val();
		var system = $("#systemsave").val();
		
		var categoryDescription = $("#categoryDescription").val();
		var serviceTypesave = $("#serviceTypesave").val();
		
		editMapping(categoryCode,system,serviceTypesave,categoryDescription);
}
function editAction(eb01ToUpdate,eb01Description){
	$.ajax({
			url: "${context}/referencedata/saveDatatypeToEB01Mapping.ajax",
			dataType: "html",
			type: "POST",
			data: "eb01IdForEditPage="+escape(eb01ToUpdate)+"eb01DescriptionForEditPage="+escape(eb01ToUpdate),
			success: function(data) {
					document.getElementById('exclusionSectionThree1').style.display= 'block';
						document.getElementById('editpage').style.display= 'block';
						document.getElementById('initialLoad').style.display= 'none';
						document.getElementById('createupdate').style.display= 'block';
					$("#editpage").html(data);
					
			}
		});

}


--></script>

<body onload="disableUserCommentsDiv();">
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="context" value="${pageContext.request.contextPath}"/>



<form name="dataTypeToEB01Form" method="POST">

 <input type="hidden" name="searchActionEB01IdSave" id="searchActionEB01IdSave" value="" /> 
 <input type="hidden" name="searchActionDataTypeIdSave" id="searchActionDataTypeIdSave" value="" /> 
 <input type="hidden" name="eb01ValueUpdate" id="eb01ValueUpdate" value="" />
 <input type="hidden" name="eb01DescUpdate" id="eb01DescUpdate" value="" />
 <input type="hidden" name="saveUserComments" id="saveUserComments" value="" />
 
 <input type="hidden" name="eb01ValueUpdateViewHistory" id="eb01ValueUpdateViewHistory" value="" />
 
  <input type="hidden" name="eb01ValueUpdateSaveAction" id="eb01ValueUpdateSaveAction" value="" />
  <input type="hidden" name="eb01DescUpdateSaveAction" id="eb01DescUpdateSaveAction" value="" />
 
 
 
<%@include	file="/WEB-INF/jspf/pageTop.jspf"%>
<div class="innerContainer" style="height: 98%" class="Pd3">
<div id="exclusionEditContainer1">
<div id="exclusionSectionOne1" style=" height: 100px">
<div id="exclusionTitleBarSectionOne1">
<div class="headerTitleExclusion">Manage EB01 – Data Type Association</div>
</div>

<div id="exclusionContentSectionOne1">
<table style="margin-top: 3px;" class="mt10 mL10 Pd3 shadedText" id="exclusionTableWidth1" border="0" cellspacing="0" cellpadding="0">
	<TBODY>
		<tr class="createEditTable1-Listdata" style="padding-top: 0px">
			<td width="200px" class="headTextExclusions">EB01</td>
			<td width="152px"><input type="text" class="inputbox60"	id="EB01Id" name="eb01Val" value="${EB01Id}" onkeypress="enterKeyPress(event);"/></td>
			<td width="152px">&nbsp;</td>
			<td width="152px" class="headTextExclusions">Data Type</td>
			<td width="152px"><input type="text" class="inputbox60"	id="dataTypeId" name="dataTypeValue" value="${dataTypeId}" onkeypress="enterKeyPress(event);"/></td>
			<td class="headText" width="152px">&nbsp;</td>
		</tr>
		<tr>
			<td align="center" colspan="6"><a href="#"><IMG id="Locate"	src="${imageDir}/locate_but.gif" width="70" height="18" alt="Locate"
				onclick="fetchDataType();"></a></td>
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
			<th id="systemId" width="5%" class="tableHeader">EB01</th>
			<th id="systemId" width="29%" class="tableHeader">Data Type</th>
			<th id="createdId" width="7%" class="tableHeader">&nbsp;</th>

		</tr>
	</THEAD>
</table>
</div>


<div id="exclusionContentSectionTwo1" style="width: 926px;">
<div id="dynamicRenderer">
<jsp:include flush="true" page="dataTypeToEB01SearchResult.jsp"></jsp:include></div>
<br>
</div>
</div>
<div id="exclusionSectionThree1" style="display: none; height: 250px">
<div class="exclusionTitleBarSectionTwo">
<div class="headerTitleSub">Manage</div>
</div>
<!-- <div class="exclusionSectionThreeSub1"> -->

<div id="editpage">
<jsp:include flush="true" page="dataTypeToEB01EditMapping.jsp"></jsp:include></div>

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
		<td width="0" height="20"><a href="#" onclick='saveAssociation()'>Save</a></td>
		<td width="9" height="0"><img src="${imageDir}/seperator.gif"
			width="9" height="20" /></td>
		<td width="0" height="20"><a href="#" onclick='openViewHistoryPopUpAssociation()'>View	History</a></td>
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
<div id="confirmationDivDeleteAllAction"></div>
<div id="confirmationDivEditAction"></div>
<div id="viewHistoryDivForAssociation"></div>
<div id="referanceDataPopUpDiv"></div>

</div> 

<div id="userCommentsDeleteAllDialog" >
<form name="userCommentsDeleteAllActionDialogForm" action="${context}/referencedata/deleteDatatypeToEB01Mapping.html" method="POST">
 <input type="hidden" name="deleteAllActionEB01Id" id="deleteAllActionEB01Id" value="" /> 
 <input type="hidden" name="deleteAllActionDataTypeValues" id="deleteAllActionDataTypeValues" value="" /> 
 <input type="hidden" name="searchActionEB01Id" id="searchActionEB01Id" value="" /> 
 <input type="hidden" name="searchActionDataTypeId" id="searchActionDataTypeId" value="" /> 
 
	<table id="deleteActionUserCommentsTable1" border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5 bT">          
	  <tr class="">
        <td > <textarea name="deleteAllUserComments" id="deleteAllUserComments"
		rows="5" cols="77"></textarea></td>
	  </tr> 			
	</table>
	<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
      <tr>       
        <td >
			<a
			href="#" onclick="saveUserCommentsForDeleteAssociation();"><img id="deleteAllActionSaveImg" src="${imageDir}/save_but.gif" 
			alt="Save" title="Save" /></a>
		</td>		
      </tr>
	</table>	
</form>
</div>

<div id="userCommentsSaveDialog" >
<form name="userCommentsSaveActionDialogForm" action="${context}/referencedata/saveDatatypeToEB01Mapping.html" method="POST">
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
<%@include file="/WEB-INF/jspf/whatIsThisToolTip.jspf"%>
<%@include file="/WEB-INF/jspf/messageTray.jspf"%>


</body>
</html>
