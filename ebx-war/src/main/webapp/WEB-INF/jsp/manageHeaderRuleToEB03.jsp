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



function enterKeyPress(e) {
	if(e.keyCode == 13) {
		$('#EB03Id').val($('#EB03Id').val().toUpperCase());
		$('#headerRule').val($('#headerRule').val().toUpperCase());	
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
			action = "${context}/referencedata/showManageHeaderRuleToEB03Page.html";
			window.location.href = action; 
			break; 
	}
}
</script>
</head>
<script type="text/javascript"><!--
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
	      document.getElementById('exclusionSectionTwo1').style.height = "230px";
	     document.getElementById('exclusionContentSectionTwo1').style.height = "202px";
	   } else if(screen.height==960)
	   {	  
	      document.getElementById("exclusionEditContainer1").style.height= "500px";
	      document.getElementById('exclusionSectionTwo1').style.height = "175px";
	      document.getElementById('exclusionContentSectionTwo1').style.height = "147px";
	      
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

function fetchHeaderRuleToEB01Mappings() {
		
		var EB03Id = $("#EB03Id").val();
		var headerRule = $("#headerRule").val();
		if((EB03Id == null || EB03Id == "")&&(headerRule == null || headerRule == "")){
				addErrorToNotificationTray('Please enter atleast one search criteria');
				openTrayNotification();
				document.getElementById('exclusionSectionTwo1').style.display= 'none';
				document.getElementById('exclusionSectionThree1').style.display= 'none';
				return false;
			}
		$.ajax({
			url: "${context}/referencedata/fetchHeaderRuleToEB03Mapping.ajax",
			dataType: "html",
			type: "POST",
			data: "EB03Id="+escape(EB03Id)+"&headerRule="+escape(headerRule),
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
 
 
 function backAction(){
		var action ;
		action = "../referencedata/closeAction.ajax";
		window.location.href = action;
}


/*********************************************************************************************/


$(document).ready(function(){
	$('#EB03Id').blur(function() {
		$('#EB03Id').val($('#EB03Id').val().toUpperCase());		
	});
	$("#EB03Id").autocomplete({ 
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=EB03",
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
	//Scrollbar not implemented
	$('#headerRule').blur(function() {
		$('#headerRule').val($('#headerRule').val().toUpperCase());		
	});
	$("#headerRule").autocomplete({ 
		source: function(request, response) {
					$.ajax({
						url: "${context}/referencedata/fetchHeaderRuleForAutoComplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term)+ "&name=RULEID",
						success: function(data) {
						cache: false,
						response(data.rows);
						}
					});
				}
	})
	
  });
function disableUserCommentsDiv(){
document.getElementById('userCommentsDeleteAllDialog').style.display= 'none';
document.getElementById('userCommentsSaveDialog').style.display= 'none';
//$("#userCommentsSaveDialog").hide();

}
function deleteAllActionConfirmationDialog(eb03Id,commaSeperatedHeaderRules){
 var warningMsgdeleteAllAction = "All the Header Rule(s) associated with EB03 = "+eb03Id+" will be deleted."  
 						   +" Do you wish to continue?";
 						   
 $("#confirmationDivDeleteAllAction").html(warningMsgdeleteAllAction);
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
					deleteAllAction(eb03Id,commaSeperatedHeaderRules);
					$(this).dialog('close');
				}
			}
		});
		$("#confirmationDivDeleteAllAction").dialog('open');
}
function editActionConfirmationDialog(eb03Id,eb03Description,commaSeperatedHeaderRules){
	var warningMsgdeleteAllAction = "Header Rule(s) associated with EB03 = "+eb03Id+" will be updated."  
 						   +" Do you wish to continue?";
	$("#confirmationDivEditAction").html(warningMsgdeleteAllAction);
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
					editAction(eb03Id,eb03Description,commaSeperatedHeaderRules);
					$(this).dialog('close');
				}
			}
		});
		$("#confirmationDivEditAction").dialog('open');
}
function deleteAllAction(eb03Id,commaSeperatedHeaderRules){
	
		$("#deleteAllActionEB03Id").val(eb03Id);
		$("#commaSeperatedHeaderRules").val(commaSeperatedHeaderRules);

		$("#searchCriteriaEB03IdDelete").val($("#EB03Id").val());
		$("#searchCriteriaHeaderRuleIdDelete").val($("#headerRule").val());
		
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
 
function editAction(eb03Id,eb03Description,commaSeperatedHeaderRules){

	$.ajax({
			url: "${context}/referencedata/editHeaderRuleToEB03Mapping.ajax",
			dataType: "html",
			type: "POST",
			data: "eb03IdForEditPage="+escape(eb03Id)+"&eb03DescriptionForEditPage="+escape(eb03Description),
			success: function(data) {
					document.getElementById('exclusionSectionThree1').style.display= 'block';
						document.getElementById('editpage').style.display= 'block';
						document.getElementById('initialLoad').style.display= 'none';
						document.getElementById('createupdate').style.display= 'block';
						$("#eb03IdFromEdit").val(eb03Id);
						$("#eb03DescriptionFromEdit").val(eb03Description);
						
					$("#editpage").html(data);
					
			}
		});

}
function saveUserCommentsForDeleteAllAction(){	
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
	
function saveAction(){

setDescriptionForHeaderRule();
var eb03Id = $('#eb03IdForPage').val();
var eb03Description = $('#eb03DescriptionForPage').val();


	$("#eb03IdFromEditSaveAction").val(eb03Id);
	$("#eb03DescriptionFromEditSaveAction").val(eb03Description);
	
	
	$("#searchCriteriaEB03Id").val($.trim($("#EB03Id").val()));
     $("#searchCriteriaHeaderRuleId").val($.trim($("#headerRule").val()));
     
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

function setDescriptionForHeaderRule(){
	var rowLength = $('#headerRuletable > tbody > tr').length;
	for(var i = 0; i < rowLength * 3; i++){	
		$('#headerRuleDesc'+i).val($('#ruleId'+i).attr('alt'));
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
		 
		document.forms['categoryMappingForm'].action="${context}/referencedata/saveHeaderRuleToEB03Mapping.html";
		document.forms['categoryMappingForm'].submit();
	}
function headerRuleToEB03ViewHistory(){
		//if(key == null || key == ""){
		//	addErrorToNotificationTray('Please choose an error code');
		//	openTrayNotification();
		//	return false;
		//}
		      resetMessages();
      $('#eb03IdFromEditViewHistory').val($.trim($('#eb03IdForPage').val()));
      var key = $("#eb03IdFromEditViewHistory").val().toUpperCase();
      $.ajax({
            url: "../referencedata/viewHistoryOfHeaderRuleToEB03Mapping.html",
            dataType: "html",
            type: "POST",
            data: "eb03IdFromEdit="+key,
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
/*********************************************************************************************/










--></script>
<script type="text/javascript">
function backAction()
{
    document.forms['categoryMappingForm'].action="${context}/ebxspiderworkflow/back.html";
    document.forms["categoryMappingForm"].submit();
}
</script>
<body onload="disableUserCommentsDiv();">
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="context" value="${pageContext.request.contextPath}"/>




<form name="categoryMappingForm"  method = "POST">
	<input type="hidden" name="searchCriteriaEB03Id" id="searchCriteriaEB03Id" value="" />
	<input type="hidden" name="searchCriteriaHeaderRuleId" id="searchCriteriaHeaderRuleId" value="" />
	
	 <input type="hidden" name="saveUserComments" id="saveUserComments" value="" />
	 
	 
	<input type="hidden" name="eb03IdFromEdit" id="eb03IdFromEdit" value="" />
	<input type="hidden" name="eb03DescriptionFromEdit" id="eb03DescriptionFromEdit" value="" />
	
	<input type="hidden" name="eb03IdFromEditViewHistory" id="eb03IdFromEditViewHistory" value="" />
	
	<input type="hidden" name="eb03IdFromEditSaveAction" id="eb03IdFromEditSaveAction" value="" />
	<input type="hidden" name="eb03DescriptionFromEditSaveAction" id="eb03DescriptionFromEditSaveAction" value="" />
	
	<%@include file="/WEB-INF/jspf/pageTop.jspf"%>

<div class="innerContainer" style="height:98%" class="Pd3">

<div id="exclusionEditContainer1" >

		<div id="exclusionSectionOne1" style=" height: 100px">
		 		<div id="exclusionTitleBarSectionOne1">
		 			 			 		<div class="headerTitleExclusion">Manage EB03 – Header Rule Association</div>	
		 			 			 		
		 		</div>
		 		
		 		
			 	<div id="exclusionContentSectionOne1">
					<table style="margin-top:3px;" class="mt10 mL10 Pd3 shadedText" id="exclusionTableWidth1" border="0" cellspacing="0" cellpadding="0" >
						<TBODY>
							<tr class="createEditTable1-Listdata" style="padding-top: 0px">
										<td   width="200px" class="headTextExclusions">EB03</td>
										<td  width="152px" ><input type="text" class="inputbox60" id="EB03Id" name="eb03Val" value="${EB03Id1}"  onkeypress="enterKeyPress(event);"/></td>
											<td    width="152px" >&nbsp;</td>	
										<td    width="152px" class="headTextExclusions">Header Rule</td>
										<td  width="152px" ><input type="text" class="inputbox60" id="headerRule" name="headerRule" value="${headerRule1}" onkeypress="enterKeyPress(event);"/></td>
															<td  class="headText"  width="152px">&nbsp;</td>		
							</tr>	
							<tr>
								<td align="center" colspan="6">
											<a href="#"><IMG id="Locate" src="${imageDir}/locate_but.gif" width="70" height="18" alt="Locate"
												onclick="fetchHeaderRuleToEB01Mappings();"></a>
								</td>	
								
							</tr>
							
					 </TBODY>   
				</table>
				</div>
		 	
		</div>
				
			<div id="exclusionSectionTwo1" style="display: none" >
		 		 			 		<div class="exclusionTitleBarSectionTwo1">
		 		 			 		<table  class="mappedTable1 pd3 shadedText" border="0" width="100%">
			 								<THEAD>
												<tr >
													<th id="systemId" width="10%" class="tableHeader">
													EB03
														
													</th> 
													<th id="systemId" width="29%" class="tableHeader">
													Header Rule
														
													</th> 
													<th id="createdId" width="7%" class="tableHeader" >
														&nbsp;
														
													</th>
													
												</tr>
											</THEAD> 			
		 			 			 		</table>
								</div>
		 		
		 		
		 			<div id="exclusionContentSectionTwo1" style="width:926px;" >
		 				<div id="dynamicRenderer" >
		 					<jsp:include flush="true" page="headerRuleToEB03SearchResult.jsp"></jsp:include>	
		 				</div>
		 			<br>
				</div>
			</div>	 	
	<div id="exclusionSectionThree1" style="display: none; height:220px">
				 		<div class="exclusionTitleBarSectionTwo">
		 			 			 		<div class="headerTitleSub">Manage</div>	
		 				</div>
		 				<!-- <div class="exclusionSectionThreeSub1"> -->
		 				<div id="editpage" >
		 						<jsp:include flush="true" page="headerRuleToEB03EditMapping.jsp"></jsp:include>	
		 				</div>
		 				
		 				<!-- </div> -->
		 				
		 				
	</div>
	
	 		 

	
	</div> <!-- Edit container ends here -->
	

		
		


</div> <!-- Inner container ends here -->
</div> <!-- Container ends here -->
	
	
	
<!-- Wrapper ends here -->

 
    <div id="createupdate" class="footer"  style="display: none;">
			<div class="ajaxIdle" id="ajaxIdleIcon">
				<div class="clear"></div>
			</div>
			<div class="ajaxActive" id="ajaxActiveIcon">
				<div class="clear"></div>
			</div>
				
			<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
		      <tr>
				<td width="18" height="20"><img src="${imageDir}/footer_left.gif" width="18" height="20" /></td>
				<td width="0" height="20"><a href="#" onclick ='backAction()'>Back</a></td>
		       		<td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
		       		<td width="0" height="20"><a href="#" onclick ='saveAction()'>Save</a></td>
		       		<td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
			        <td width="0" height="20"><a href="#" onclick ='headerRuleToEB03ViewHistory()'>View History</a></td>
				<td width="18" height="20"><img src="${imageDir}/footer_right.gif" width="18" height="20" /></td>
				
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
				
			<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
		      <tr>
				<td width="18" height="20"><img src="${imageDir}/footer_left.gif" width="18" height="20" /></td>
				<td width="0" height="20"><a href="#" onclick ='backAction()'>Back</a></td>
				<td width="18" height="20"><img src="${imageDir}/footer_right.gif" width="18" height="20" /></td>
				</tr>
			</table>

	</div>
    </div>
    
   
		 <script>
	       	sniffer();
	    
       	</script>
 </form>  
 
 
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
	<div id="referanceDataPopUpDiv">	</div>


</div>
<div id="userCommentsDeleteAllDialog" >
<form name="userCommentsDeleteAllActionDialogForm" action="${context}/referencedata/deleteHeaderRuleToEB03Mapping.html" method="POST">
 <input type="hidden" name="deleteAllActionEB03Id" id="deleteAllActionEB03Id" value="" /> 
 <input type="hidden" name="commaSeperatedHeaderRules" id="commaSeperatedHeaderRules" value="" />
 
 <input type="hidden" name="searchCriteriaEB03IdDelete" id="searchCriteriaEB03IdDelete" value="" /> 
 <input type="hidden" name="searchCriteriaHeaderRuleIdDelete" id="searchCriteriaHeaderRuleIdDelete" value="" /> 
 
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
			href="#" onclick="saveUserCommentsForDeleteAllAction();"><img id="deleteAllActionSaveImg" src="${imageDir}/save_but.gif" 
			alt="Save" title="Save" /></a>
		</td>		
      </tr>
	</table>	
</form>
</div>

<div id="userCommentsSaveDialog" >
<form name="userCommentsSaveActionDialogForm" >
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
