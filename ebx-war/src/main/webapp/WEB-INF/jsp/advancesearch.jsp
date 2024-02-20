<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/jspf/pageHead.jspf"%>
<title>Enterprise Blue Exchange</title>
<script type="text/javascript">
	var autoPopulateUrl = "../ajaxautocompletelistcreatepage.ajax";
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/massUpdateUtils.js"></script>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="context" value="${pageContext.request.contextPath}" />


<script type="text/javascript">
$(document).ready(function(){	
	$('#userCommentsNotApplicableDialog').hide();
	$('#userCommentsDialog').hide();
	  });
$(document).ready(function(){
	//$("#variableId").autocomplete({ source: [{"id":"1","label":"India","value":"India"},{"id":"2","label":"China","value":"China"},{"id":"3","label":"USA","value":"USA"},{"id":"4","label":"United Kingdom","value":"United Kingdom"}]});
	//Scrollbar not implemented
	$('#varIdFrmLocate').blur(function() {
	$('#varIdFrmLocate').val($('#varIdFrmLocate').val().toUpperCase());	
	});
	$("#varIdFrmLocate").autocomplete({ 
		source: function(request, response) {
		
					$.ajax({
						url: "${context}/ajaxvariablelist.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+encodeURIComponent(request.term),
						success: function(data) {
						cache: false,
						response(data.rows);
						}
					});
				}
	});
	sniffer();
  });
  
var variableFormat = "";
$(document).ready(function(){	
	//Scrollbar not implemented
	$('#EB01Id').blur(function() {
		$('#EB01Id').val($('#EB01Id').val().toUpperCase());		
	});
	$("#EB01Id").autocomplete({ 
		source: function(request, response) {
					$.ajax({
						url: autoPopulateUrl,
						dataType: "json",
						type:"POST",
						data: "key="+request.term +"&name=EB01"+"&varformat="+variableFormat,
						success: function(data) {
						cache: false,
						response(data.rows);
						}
					});
				}
	})
	sniffer();
  });
 
 
$(document).ready(function(){
	$('#III02Id').blur(function() {
	   $('#III02Id').val($('#III02Id').val().toUpperCase());
	});
	$("#III02Id").autocomplete({ 
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
	});
	sniffer();
  });

$(document).ready(function(){
	$('#NOTETYPEID').blur(function() {
	   $('#NOTETYPEID').val($('#NOTETYPEID').val().toUpperCase());
	});
	$("#NOTETYPEID").autocomplete({ 
		source: function(request, response) {
					$.ajax({
						url: autoPopulateUrl,
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
	});
	sniffer();
  });
  
   var fromHistory = false;
   var fromPrint = false;
  $(document).ready(function(){
	sniffer();
	document.getElementById('massUpdateContainer').style.display= 'none';
	document.getElementById('Accordion1Content').style.height = ContentHeight + '%';
	document.getElementById('inSearchDiv').style.display= 'block';
	document.getElementById('inSearchDivSub').style.display= 'none';
	document.getElementById('inSearchDivHeader1').style.display= 'none';
	document.getElementById('inSearchDivHeader2').style.display= 'none';
	runAccordian = false;
	//document.getElementById('inSearchDivHeader1').style.display= 'none';
	//openAccordion = "Accordion1Content";
  	//flag = true;
	//runAccordionSearch(1);
  });
 
$(document).ready(function(){
	if(fromHistory == true){
	searchMessageTextHistory();
}
});

$(document).ready(function(){
	if(fromPrint == true){
		persistSearchCriteria();
	//searchMessageTextPrint();
	
}
});

$(document).ready(function(){
	$('#contractId').blur(function() {
	$('#contractId').val($('#contractId').val().toUpperCase());	
	});
});
$(document).ready(function(){
	$('#revisionDateTD').hide("");
	$('#revisionDateTD1').hide("");
	});
	

</script>

<script type="text/javascript">

function enterKeyPress(e){
// look for window.event in case event isn't passed in
if (window.event) { 
	e = window.event; 
	}
if (e.keyCode == 13){
	 document.getElementById('search').click();
     }
  }
	
	function sniffer() {
	   document.getElementById('container').style.paddingTop = "0px";
	   document.getElementById('container').style.height = "76%";
	   if(screen.height>864) 
	  {
		ContentHeight = 94;
//	     document.getElementById('container').style.height = "525px";	   
	   }else if(screen.height>768) 
	  {
		ContentHeight = 91;
//	     document.getElementById('container').style.height = "525px";	   
	   } 
	}
	
  
function openViewMappingDialog(variableId) {	

		$.ajax({
			url: "${context}/viewmappingpage/viewUnmappedVariable.ajax",
			dataType: "html",
			type: "POST",
			data: "&variableId="+encodeURIComponent(variableId),
			success: function(data) {
				$("#viewMappingDialog").html(data);
				$("#viewMappingDialog").dialog({
					height:'auto',
					width:'890px',	
					show:'slide',
					modal: true,
					resizable: false,
					title: 'View Mapping'

				});
				$("#viewMappingDialog").dialog();
			}
		});
	}
function openViewMappingInProgressDialog(variableId) {

      $.ajax({
            url: "${context}/viewmappingpage/viewMapping.ajax",
            dataType: "html",
			type: "POST",
            data: "&variableId="+encodeURIComponent(variableId),
            success: function(data) {
                  $("#viewMappingDialog").html(data);
                  $("#viewMappingDialog").dialog({
                        height:'auto',	
						width:'910px',					
                        show:'slide',
                        modal: true,
						resizable: false,
						title: 'View Mapping'

                  });
                  $("#viewMappingDialog").dialog();
            }
      });
	}
function openViewPopUpForMappedAndUnMapped(variableId,status,system,formattedDate) {	

		// Code for positioning the dialog box
		var posx = 0;
		var posy = 0;
		if (!e) 
		var e = window.event;
		if (e.pageX || e.pageY) 	{
			posx = e.pageX;
			posy = e.pageY;
		}
		else if (e.clientX || e.clientY) 	{
			posx = e.clientX + document.body.scrollLeft
			+ document.documentElement.scrollLeft;
			posy = e.clientY + document.body.scrollTop
			+ document.documentElement.scrollTop;
		}
	// Code for positioning the dialog box ends
		

 		var heading = system+" - "+variableId+ " "+"   Created On  "+formattedDate;
		var urlString = "";
		if(status=='UNMAPPED'){
			urlString = "${context}/advancesearch/viewPopUpForUnmappedVariable.ajax";
		}else{
			urlString = "${context}/advancesearch/viewPopUpForMapped.ajax";
		}

		$.ajax({
			url: urlString,
			dataType: "html",
			type: "POST",
			data: "&variableId="+encodeURIComponent(variableId),
			success: function(data) {
				$("#viewMappingDialog").html(data);
				$("#viewMappingDialog").dialog({
					height:'auto',
					width:'680px',	
					show:'clip',						
                    hide:'clip',
					resizable: false,
					title: heading,
					position: [posx,posy+10]

				});
				$("#viewMappingDialog").dialog();
			}
		});
	}
	 function editMappingVariable(variableId){
            $("#selectedvariableForEditId").val(jQuery.trim(variableId));			           
            document.forms["editForm"].submit();		
     }
     
	function viewSearchHistory(){
	fromHistory = true;
}
	function viewSearchPrint(){
		fromPrint = true;
	}
	
	function handleIconsWhileUnlockFromLanding(data) {		
		addMessages(data);			
		var editComp = '<a href = "#" id="'+data.variableId+'_Edit" onclick="editMapping(\''+data.variableId+'\');"><img src="${imageDir}/edit_icon.gif" alt="Edit" title="Edit" style="height:15px;"/></a>';
		$('#'+data.variableId+'_Lock').replaceWith(editComp);
		$('#'+data.variableId+'_Edit').append("&#160;&#160;");		
		
		var sendtotestComp = '<a href = "#" id="'+data.variableId+'_Test" onclick="<form></form>UserCommentsSend2TestDialog(\''+data.variableId+'\');"><img src="${imageDir}/test_icon.gif" alt="Send to Test" title="Send to Test" style="height:15px;"/></a>';
		$('#'+data.variableId+'_Edit').after(sendtotestComp);
		$('#'+data.variableId+'_Test').append("&#160;&#160;");
		
		if(data.authorizedToApprove){		
			var approveComp = '<a href = "#" id="'+data.variableId+'_Approve" onclick="openUserCommentsApproveDialog(\''+data.variableId+'\');"><img src="${imageDir}/approve_icon.gif" alt="Approve" title="Approve" style="height:15px;"/></a>';
			$('#'+data.variableId+'_Test').after(approveComp);
		}
		
		if(data.auditLockStatus=='N' && data.authorizedToAuditUnlock){
			var unLockIcon = '<a href = "#" id="AuditUnLock_'+data.variableId+ 
			'" onclick="openUserCommentsLockDialog(\''+data.variableId+'\',\''+data.systemName+'\',\''+data.userName+'\',\''+escape(data.variableDesc)+'\',\'Lock\');"><img src="${imageDir}/AuditLockIndicatorNew.jpg" alt="Lock" title="Lock" style="height:15px;width=12px;"/></a>';
			$("[id=AuditLock_"+data.variableId+"]").replaceWith(unLockIcon);
		}
		if(data.auditLockStatus=='Y' && data.authorizedToAuditLock){
			var lockIcon = '<a href = "#" id="AuditLock_'+data.variableId+ 
			'" onclick="openUserCommentsLockDialog(\''+data.variableId+'\',\''+data.systemName+'\',\''+data.userName+'\',\''+escape(data.variableDesc)+'\',\'Unlock\');"><img src="${imageDir}/auditUnLockIndicator.GIF" alt="Unlock" title="Unlock" style="height:15px;width=12px;"/></a>';
			$("[id=AuditUnLock_"+data.variableId+"]").replaceWith(lockIcon);
		}
	}
		
	function handleUnlockFromLocate(data) {
		addMessages(data);			
		if(data.variableId != null){
			handleIconsWhileUnlock(data.variableId,data.mapping.user.lastUpdatedUserName, data.authorizedToApprove, 'variable',data.auditLockStatus,data.userName,data.variableDesc,data.systemName,data.authorizedToAuditUnlock,data.authorizedToAuditLock);
		}		
	}
	function handleIconsWhileUnlock(variableId, userId, permissionToApprove, action,auditStatus,userName,variableDesc,systemName,authorizedToAuditUnlock,authorizedToAuditLock) {		
			
		var sendToTest = "Send2TestFromLocate";
		var approve = "ApproveFromLocate";
				
		var editComp = '<a href = "#" id="locate_EditIcon_'+variableId+ 
		'" onclick="editMappingVariable(\''+variableId+'\');"><img src="${imageDir}/edit_icon.gif" alt="'+userId+'" title="'+userId+'" style="height:15px;" /></a>';
		$('#locate_UnlockIcon_'+variableId).replaceWith(editComp);
		$('#locate_EditIcon_'+variableId).append("&#160;&#160;");
				
		var sendtotestComp = '<a href = "#" id="locate_SendToTestIcon_'+variableId+ 
		'" onclick="openUserCommentsDialog(\''+variableId+'\',\''+sendToTest+'\');"><img src="${imageDir}/test_icon.gif" alt="Send to Test" title="Send to Test" style="height:15px;"/></a>';
		$('#locate_EditIcon_'+variableId).after(sendtotestComp);
		$('#locate_SendToTestIcon_'+variableId).append("&#160;&#160;");
		
		if(permissionToApprove){		
			var approveComp = '<a href = "#" id="locate_approveIcon_'+variableId+ 
			'" onclick="openUserCommentsDialog(\''+variableId+'\',\''+approve+'\');"><img src="${imageDir}/approve_icon.gif" alt="Approve" title="Approve" style="height:15px;"/></a>';
			$('#locate_SendToTestIcon_'+variableId).after(approveComp);
		}
		
		if(auditStatus=='N' && authorizedToAuditUnlock){
			var unLockIcon = '<a href = "#" id="AuditUnLock_'+variableId+ 
			'" onclick="openUserCommentsLockDialog(\''+variableId+'\',\''+systemName+'\',\''+userName+'\',\''+escape(variableDesc)+'\',\'Lock\');"><img src="${imageDir}/AuditLockIndicatorNew.jpg" alt="Lock" title="Lock" style="height:15px;width=12px;"/></a>';
			$("[id=AuditUnLock_"+variableId+"]").replaceWith(unLockIcon);
		}
		if(auditStatus=='Y' && authorizedToAuditLock){
			var lockIcon = '<a href="#" id="AuditLock_'+variableId+ 
			'" onclick ="openUserCommentsLockDialog(\''+variableId+'\',\''+systemName+'\',\''+userName+'\',\''+escape(variableDesc)+'\',\'Unlock\');"><img src="${imageDir}/auditUnLockIndicator.GIF" alt="Unlock" title="Unlock" style="height:15px;width:12px;"/></a>';
			$("[id=AuditLock_"+variableId+"]").replaceWith(lockIcon);
		}
	}
	
		function addMessages(data){
	
		var infoMessages = data.info_messages;
		var errorMessages = data.error_messages;
		var warningMessages = data.warning_messages;
		
		
		if(typeof(infoMessages) != 'undefined' && infoMessages.length >0) {
			for(i=0; i< infoMessages.length; i++) {
				addInfoToNotificationTray(infoMessages[i]);
			}		
		}
		if(typeof(errorMessages) != 'undefined' && errorMessages.length >0) {
			for(i=0; i< errorMessages.length; i++) {
				addErrorToNotificationTray(errorMessages[i]);
			}
		}
		//if(typeof(warningMessages) != 'undefined' && warningMessages.length >0) {
		//	for(i=0; i< warningMessages.length; i++) {
		//		addWarnToNotificationTray(warningMessages[i]);
		//	}
		//}
		openTrayNotification();

	}
	
		// Method to open create page on click of 'Create (+) icon'
	function openCreateFromUnmappedSection(variableId){
		$('#variableIdHidden').val(jQuery.trim(variableId));		
		document.forms['unmappedVarForm'].submit();
	}
	
		function saveUserCommentsForNotApplicable(){
		if(!imposeMaxLength('notApplicableMappingComments' ,250,'user comments')){						
				return false;
		}
		// LO changes to avoid multiple insert of variables in db. September 2014 Release
		 $("a").removeAttr("onclick");
		document.forms['userCommentsNotApplicableForm'].submit();
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
	var variableIdForViewPage ="";
var userCommentsFrom = "";

function openUserCommentsDialog(variableId,actionFrom){
		variableIdForViewPage = escape(jQuery.trim(variableId));
		userCommentsFrom = actionFrom;	
		$("#userMappingComments").val('');
		$("#userCommentsDialog table#userCommentsTable1").css("border-top","1px solid black");
		if(userCommentsFrom=='Send2TestFromLocate') {
			$("#userCommentsDialog").dialog({
	                        height:'auto',
							resizable:false,
							width:'450px',	
	                        show:'slide',
							title: variableId +' - Send to test',
	                        modal: true
	                  });
		}else if(userCommentsFrom=='ApproveFromLocate') {
			$("#userCommentsDialog").dialog({
	                        height:'auto',
							resizable:false,
							width:'450px',	
	                        show:'slide',
							title: variableId +' - Approve',
	                        modal: true
	                  });
		}else if(userCommentsFrom=='Send2TestFromView') {		
				$("#userCommentsDialog").dialog({
	                        height:'auto',
							resizable:false,
							width:'450px',	
	                        show:'slide',
							title: 'User comments - Send to test',
	                        modal: true
			    });	
		}else if(userCommentsFrom=='ApproveFromView') {	
				$("#userCommentsDialog").dialog({
	                        height:'auto',
							resizable:false,
							width:'450px',	
	                        show:'slide',
							title: 'User comments - Approve',
	                        modal: true
			    });	
		}
	}
	
//Method to invoke the save the user comments n id from comments dialog box
	function saveUserComments(){
		$('#userCommentsDialog').dialog( "close" );
		var userComments = $("#userMappingComments").val();	
		var pageFrom = "advancesearch";
		if(userCommentsFrom=='Send2TestFromLocate') {
			$.ajax({
				url: "${context}/locatepagestateflow/sendToTest.ajax",
				dataType: "json",
				type: "POST",			
				data: "variableId="+variableIdForViewPage+"&userComments="+userComments +"&pageFrom="+pageFrom,
				success: function(data) {
				cache: false,
				handleSendToTestFromLocate(data);}
			});
		}else if(userCommentsFrom=='ApproveFromLocate') {
			$.ajax({
				url: "${context}/locatepagestateflow/approve.ajax",
				dataType: "json",
				type: "POST",
				data: "variableId="+variableIdForViewPage+"&userComments="+userComments,			
				success: function(data) {
				cache: false,
				handleApproveFromLocate(data);}
			});
		}else if(userCommentsFrom=='Send2TestFromView') {
			$.ajax({
				url: "${context}/stateflow/sendToTest.ajax",
				dataType: "json",
				type: "POST",			
				data: "variableId="+variableIdForViewPage+"&userComments="+userComments,
				success: function(data) {
				cache: false,
				handleSendToTestFromView(data);}
			});
		}else if(userCommentsFrom=='ApproveFromView') {
			$.ajax({
				url: "${context}/stateflow/approve.ajax",
				dataType: "json",
				type: "POST",			
				data: "variableId="+variableIdForViewPage+"&userComments="+userComments,
				success: function(data) {
				cache: false,
				handleApproveFromView(data);}					
			});
		}
	}
	function handleSendToTestFromView(data) {		
		addMessages(data);
		$('#statusImageContainer').html('<img src="${imageDir}/totest.gif" />');
		$('#sendToTestButton').html("");
		$('#sendToTestButtonSep').html("");
		if(isElementDefined($('#approveButton'))){
			$('#approveButton').html("");
		}
		if(isElementDefined($('#copyToSep'))){
			$('#copyToSep').html("");
		}
		var variableId = data.variableId;
		handleIconsWhileSendToTest(variableId);
		var auditTrail = data.auditTrail;		
		var auditRow = 
		"<tr id=''>" +
		"<td width='90px'>"+data.auditTrail.createdAuditDate+"</td>"+
		"<td width='93px'>"+data.auditTrail.createdUser+"</td>"+
		"<td width='180px'>"+data.auditTrail.systemComments+"</td>"+
		"<td width='250px'>"+data.auditTrail.userComments+"</td>"+
		"</tr>"	;
		$('#auditTrailsTable tr:nth-child(1)').append(auditRow);	
		$('#auditTrailsTable tr:nth-child(even)').removeClass('white-bg');
		$('#auditTrailsTable tr:nth-child(odd)').removeClass('alternate');	
		$('#auditTrailsTable tr:nth-child(even)').addClass('alternate');
		$('#auditTrailsTable tr:nth-child(odd)').addClass('white-bg');		
		$('#auditTrailsTable tr:first-child').removeClass('white-bg').addClass('createEditTableShade');
	
	}	
	function handleApproveFromView(data) {	
		addMessages(data);
		$('#statusImageContainer').html('<img src="${imageDir}/toproduction.gif" />');
		$('#approveButton').html("");
		if(isElementDefined($('#copyToSep'))){
			$('#copyToSep').html("");
		}
		if(isElementDefined($('#sendToTestButton'))){
			$('#sendToTestButton').html("");
			$('#sendToTestButtonSep').html("");
		}
		var variableId = data.variableId;		
		handleIconsWhileSendToTest(variableId);	
		handleIconsWhileApprove(variableId);
		var auditRow = 
		"<tr id=''>" +
		"<td width='90px'>"+data.auditTrail.createdAuditDate+"</td>"+
		"<td width='93px'>"+data.auditTrail.createdUser+"</td>"+
		"<td width='180px'>"+data.auditTrail.systemComments+"</td>"+
		"<td width='250px'>"+data.auditTrail.userComments+"</td>"+
		"</tr>"	;
		$('#auditTrailsTable tr:nth-child(1)').append(auditRow);	
		$('#auditTrailsTable tr:nth-child(even)').removeClass('white-bg');
		$('#auditTrailsTable tr:nth-child(odd)').removeClass('alternate');	
		$('#auditTrailsTable tr:nth-child(even)').addClass('alternate');
		$('#auditTrailsTable tr:nth-child(odd)').addClass('white-bg');		
		$('#auditTrailsTable tr:first-child').removeClass('white-bg').addClass('createEditTableShade');
	}
	function handleSendToTestFromLocate(data) {		
		var variableId = data.variableId;
		handleIconsWhileSendToTest(variableId);
		addMessages(data);		
	}	
	function handleIconsWhileSendToTest(variableId) {
		if(isElementDefined($('#'+variableId+'_Test'))){
			$('#'+variableId+'_Test').remove();
		}
		if(isElementDefined($('#'+'locate_SendToTestIcon_'+variableId))){	
			$('#'+'locate_SendToTestIcon_'+variableId).remove();
		}		
		if(isElementDefined($('#'+'status_'+variableId))){				
				$('#'+'status_'+variableId).html("SCHEDULED_TO_TEST");			
		}
		// Remove approve as approve will be available only after object transfered
		if(isElementDefined($('#'+'locate_approveIcon_'+variableId))) {
			$('#'+'locate_approveIcon_'+variableId).remove();	
		}
		if(isElementDefined($('#'+variableId+'_Approve'))) {
			$('#'+variableId+'_Approve').remove();
		}
		
	}

	function handleIconsWhileApprove(variableId) {
		if(isElementDefined($('#'+variableId+'_Approve'))) {
			$('#'+variableId+'_Approve').remove();
		}
		$('#'+'locate_approveIcon_'+variableId).remove();
		$('#'+'status_'+variableId).html("SCHEDULED_TO_TEST");	
		if(isElementDefined($('#'+variableId+'_Test'))) {
			$('#'+variableId+'_Test').remove();
		}		
		if(isElementDefined($('#'+'locate_SendToTestIcon_'+variableId))) {
			$('#'+'locate_SendToTestIcon_'+variableId).remove();
		}
	}
	
	function handleApproveFromLocate(data) {	
		var variableId = data.variableId;
		handleIconsWhileApprove(variableId);
		addMessages(data);	
	}
	
	function openUserCommentsNotApplicableDialog(variableId, variableDesc){

		$("#notApplicablestateflowvariableId").val(jQuery.trim(variableId));
		$("#notApplicablestateflowvariableDesc").val(variableDesc);
		$("#notApplicableMappingComments").val('');	
		$("#userCommentsNotApplicableDialog table#NACommentsTable1").css("border-top","1px solid black");	
				$("#userCommentsNotApplicableDialog").dialog({
	                        height:'auto',
							width:'450px',	
							resizable:false,
	                        show:'slide',
							title: variableId +' - Not Applicable',
	                        modal: true
	                  });
			$("#userCommentsNotApplicableDialog").dialog();
	}
		
	function throwError(){
 	addErrorToNotificationTray('At least one search criteria is mandatory for search');
	openTrayNotification();
 	}
 	
function refreshSearch(){
	searchMessageText($('#currentPage').val(),'Refresh');
}
 // Method to open search on click of 'Search Button'
function searchMessageText(currentPageNo, page){

		var varIdToLocate = $('#varIdFrmLocate').val();
		
		var eb01 =  $('#EB01Id').val();
		var III02 = $('#III02Id').val();
		var msgtxt = $('#messageValueId').val();
		var eb03 = $('#EB03Id0').val();
		var noteType = $('#NOTETYPEID').val();
		var user = $('#userId').val();
		var authorizedToApprove  = $('#hdAuthorizedToApprove').val();
		
		var isUnMapped = "false";
		var isMapped = "false";
		var isNotApplicable = "false";
		var isNotFinalized = "false";
		
		var contractId = $('#contractId').val();
		var systemNameForContract = $('#systemNameForContract').val();
		var startDateForContract = $('#startDateForContract').val();
		var revisionDateForISGContract = $('#revisionDateForISGContract').val();
		
		var varDescription = $('#varDescId').val();
		var majorHeading = $('#majHdgId').val();
		var minorHeading = $('#minHdgId').val();
		
		$('#currentPage').val(currentPageNo);

		if ($('#isUnMapped').is(':checked') ){
			isUnMapped = "true";
		}
		if ($('#isMapped').is(':checked') ){
			isMapped = "true";
		}
		if ($('#isNotApplicable').is(':checked') ){
			isNotApplicable = "true";
		}
		if ($('#isNotFinalized').is(':checked') ){
			isNotFinalized = "true";
		}
		if(user !="" ) {
		var alphanum=/^[0-9a-zA-Z, ]+$/; //This contains A to Z , 0 to 9 and A to B and space
		if(!alphanum.test(user)){
		addErrorToNotificationTray('Invalid User');
		openTrayNotification();
		return false;
		}
		else if(user ==','){
		addErrorToNotificationTray('Invalid User');
		openTrayNotification();
		return false;
		}
		}
		
		if(isUnMapped == "true" && isMapped != "true" &&  isNotApplicable != "true" && isNotFinalized != "true" ) {
		if( ( eb01 != "") || (eb03 != "") ||(III02 != "") || (msgtxt != "") || (noteType != "") ) {
			addErrorToNotificationTray('Invaid Search Criteria');
			openTrayNotification();
			return false;
			}
		}
		if(isUnMapped != "true" && isMapped != "true" &&  isNotApplicable != "true" && isNotFinalized != "true" 
			&& varIdToLocate == "" && eb01 == "" && eb03 == "" && III02 == "" && msgtxt == "" && noteType == "" && user == "" && contractId == "" ) {
		throwError();
		return false;
		}
		
		var fromWhichSection = page;
		var currentPage = currentPageNo;
		var buttonId = document.getElementById("search");
		if(buttonId.disabled){
			return false;
		}
		if(contractId != "" ){
		if(systemNameForContract == null || systemNameForContract == '' 
				|| startDateForContract == null || startDateForContract == '' ){
					addErrorToNotificationTray('Invalid Contract Id');
					openTrayNotification();
					return false;
				}
			$.ajax({
				url: "${context}/advancesearch/advanceSearch.ajax",
				dataType: "html",
				type: "POST",
				data: "varIdToLocate="+encodeURIComponent(varIdToLocate)+
				"&eb01="+eb01+"&III02="+III02+"&msgtxt="+msgtxt+"&eb03="+eb03+"&noteType="+noteType+"&user="+user+
				"&isUnMapped="+isUnMapped+"&isMapped="+isMapped+"&isNotApplicable="+isNotApplicable+"&isNotFinalized="+isNotFinalized
				+"&section="+fromWhichSection+"&currentPage="+currentPage+"&authorizedToApprove="+authorizedToApprove
				+"&contractId="+contractId+"&systemNameForContract="+systemNameForContract+"&startDateForContract="+startDateForContract
				+"&revisionDateForISGContract="+revisionDateForISGContract+"&varDescription="+varDescription+"&majorHeading="+majorHeading+"&minorHeading="+minorHeading,
			success: function(data) {
				$("#inSearchDivSub").html(data);
  				showUpdateTable('variableid');
			}
		});
		}
		else{
		//alert("Normal Advance Search");
		$.ajax({
			url: "${context}/advancesearch/advanceSearch.ajax",
			dataType: "html",
			type: "POST",
			data: "varIdToLocate="+encodeURIComponent(varIdToLocate)+
			"&eb01="+eb01+"&III02="+III02+"&msgtxt="+msgtxt+"&eb03="+eb03+"&noteType="+noteType+"&user="+user+
			"&isUnMapped="+isUnMapped+"&isMapped="+isMapped+"&isNotApplicable="+isNotApplicable+"&isNotFinalized="+isNotFinalized
				+"&section="+fromWhichSection+"&currentPage="+currentPage+"&authorizedToApprove="+authorizedToApprove
				+"&varDescription="+varDescription+"&majorHeading="+majorHeading+"&minorHeading="+minorHeading,	
			success: function(data) {
				$("#inSearchDivSub").html(data);
  				showUpdateTable('variableid');
			}
		});
		}
		if(page == 'Init'){
			clearFields();
		}
	}
	
	function persistSearchCriteria(){
		var hdFromHistory = $('#hdFromHistory').val();
		var hdFromPrint = $('#hdFromPrint').val();
		var hdVarIdFrmLocate = $('#hdVarIdFrmLocate').val();
		var hdEB01Id = $('#hdEB01Id').val();
		var hdIII02Id = $('#hdIII02Id').val();
		var hdEB03Id0 = $('#hdEB03Id0').val();
		var hdNOTETYPEID = $('#hdNOTETYPEID').val();
		var hdmessageValueId = $('#hdmessageValueId').val();
		var hduserId = $('#hduserId').val();
		var hdmajHdgId = $('#hdmajHdgId').val();
		var hdminHdgId = $('#hdminHdgId').val();
		var hdvarDescId = $('#hdvarDescId').val();
		var hdcontractId = $('#hdcontractId').val();

		if(hdFromHistory == 'true' || hdFromPrint == 'true'){
			$('#varIdFrmLocate').val(hdVarIdFrmLocate);		
			$('#EB01Id').val(hdEB01Id);
			$('#III02Id').val(hdIII02Id);
			$('#EB03Id0').val(hdEB03Id0);
			$('#NOTETYPEID').val(hdNOTETYPEID);
			$('#messageValueId').val(hdmessageValueId);
			$('#userId').val(hduserId);
			$('#majHdgId').val(hdmajHdgId);		
			$('#minHdgId').val(hdminHdgId);
			$('#varDescId').val(hdvarDescId);
			$('#contractId').val(hdcontractId);

			var isUnMapped = $('#hdIsUnMappedId').val();
           	if(isUnMapped == "true"){
				 $("#isUnMapped").attr('checked', true);
			}else{
				$("#isUnMapped").attr('checked', false);
			}                  
            
			var isMapped = $('#hdIsMappedId').val();
            if(isMapped == "true"){
				 $("#isMapped").attr('checked', true);
			}else{
				$("#isMapped").attr('checked', false);
			}                        
                     
			var isNotApplicable = $('#hdIsNotApplicableId').val();                       	
			if(isNotApplicable == "true"){
				 $("#isNotApplicable").attr('checked', true);
			}else{
				$("#isNotApplicable").attr('checked', false);
			}
                        
			var isNotFinalized = $('#hdIsNotFinalizedId').val();
			if(isNotFinalized == "true"){
				$("#isNotFinalized").attr('checked', true);
			}else{
				$("#isNotFinalized").attr('checked', false);
			}
		}	
		
	}

	function unlockMappingFromLocate(variableId, action, lockUserId,auditLockStatus,systemName,variableDesc){
	var warningMsgForUnlocking = "The mapping is locked by the user " + lockUserId + " . Do you want to unlock?";
	$("#confirmationDivUnlock").html(warningMsgForUnlocking);
	$("#confirmationDivUnlock").addClass("UnmappedVariables");
	var authorizedToApprove = $('#authorizedToapprove').val();
	var authorizedToAuditUnlock = $('#authorizedToAuditUnlock').val();
	var authorizedToAuditLock = $('#authorizedToAuditLock').val();
	$("#confirmationDivUnlock").dialog({
			autoOpen: false,
			title : 'Confirm',
			resizable: false,
			height:130,
			modal: true,
			buttons: {
				Cancel: function() {
					$(this).dialog('close');
				},
				Ok: function() {
					$(this).dialog('close');
					if(action=='unlockVariableFromLocate') {
						$.ajax({
							url: "${context}/stateflow/unlockVariableMapping.ajax",
							dataType: "json",
							type: "POST",			
							data: "variableId="+escape(variableId)+"&authorizedToApprove="+authorizedToApprove+"&auditLockStatus="+auditLockStatus+"&systemName="+systemName+"&variableDesc="+variableDesc+"&authorizedToAuditLock="+authorizedToAuditLock+"&authorizedToAuditUnlock="+authorizedToAuditUnlock,
							success: function(data) {
							cache: false,
							handleUnlockFromLocate(data);}
						});
					}
					if(action=='unlockVariableFromLanding') {
						$.ajax({
							url: "${context}/stateflow/unlockVariableMapping.ajax",
							dataType: "json",
							type: "POST",			
							data: "variableId="+escape(variableId)+"&authorizedToApprove="+authorizedToApprove+"&auditLockStatus="+auditLockStatus+"&systemName="+systemName+"&variableDesc="+variableDesc+"&authorizedToAuditLock="+authorizedToAuditLock+"&authorizedToAuditUnlock="+authorizedToAuditUnlock,
							success: function(data) {
							cache: false,
							handleIconsWhileUnlockFromLanding(data);}
						});
					}
		}
	}
});
$("#confirmationDivUnlock").dialog('open');	
}

	function openViewPopUpForMappedVariable(varID,desc,maj,min,system,pva,
		formattedDate,EB03,EB02,EB06,EB09,hsd01,hsd02,hsd03,hsd04,hsd05,hsd06,hsd07,hsd08,
		status,noteTypeCode,III02,notCompleteFlag,accumNotReqrdIndctr,accumulator,msgReqrdIndctr,messageText,dataType
		 ) {
	 //Code for positioning the dialog box
	var posx = 0;
	var posy = 0;
	if (!e) 
	var e = window.event;
	if (e.pageX || e.pageY) 	{
		posx = e.pageX;
		posy = e.pageY;
	}
	else if (e.clientX || e.clientY) 	{
		posx = e.clientX + document.body.scrollLeft
			+ document.documentElement.scrollLeft;
		posy = e.clientY + document.body.scrollTop
			+ document.documentElement.scrollTop;
	}
	 //Code for positioning the dialog box ends
	 var heading = system+" - "+varID+ " "+"   Created On  "+formattedDate;
	 var data = "&variableId="+varID+"&desc="+desc+"&maj="+maj+"&min="+min+"&pva="+pva+
            				"&EB03="+EB03+"&EB02="+EB02+"&EB06="+EB06+"&EB09="+EB09+"&hsd01="+hsd01+
            				"&hsd02="+hsd02+"&hsd03="+hsd03+"&hsd04="+hsd04+"&hsd05="+hsd05+
            				"&hsd06="+hsd06+"&hsd07="+hsd07+"&hsd08="+hsd08+"&status="+status+
            				"&noteTypeCode="+noteTypeCode+"&III02="+III02+"&notCompleteFlag="+notCompleteFlag+
            				"&accumNotReqrdIndctr="+accumNotReqrdIndctr+"&accumulator="+accumulator+
            				"&msgReqrdIndctr="+msgReqrdIndctr+"&messageText="+messageText+"&dataType="+dataType;
	$.ajax({
            url: "${context}/advancesearch/viewPopUpVariable.ajax",
            dataType: "html",
			type: "POST",
            data: data,
            success: function(data) {
                  $("#viewMappingDialog").html(data);
                  $("#viewMappingDialog").dialog({
                        height:'auto',	
						width:'630px',					
                        show:'clip',
                        modal: true,
						resizable: false,
						title: heading,
						position: [posx,posy]

                  });
                  $("#viewMappingDialog").dialog();
            }
      });
	}
	
	function searchMessageTextHistory(){
	$.ajax({
			url: "${context}/advancesearch/advanceSearchHistory.ajax",
			dataType: "html",
			type: "POST",
			data: "",
			success: function(data) {
				$("#inSearchDivSub").html(data);
  				showUpdateTable('variableid');
			}
		});
	}
</script>
<script type="text/javascript">
var ContentHeight = 89 ;
var TimeToSlide = 100.0;
var flag=false;
var runAccordian = true;
var openAccordion = '';

function runAccordionSearch(index)
{
  if(runAccordian == false)
  {
  	return;
  }
  var nID = "Accordion" + index + "Content";
  if(openAccordion == nID)
    nID = '';
  setTimeout("animate(" + new Date().getTime() + "," + TimeToSlide + ",'" + openAccordion + "','" + nID + "')", 33);
  openAccordion = nID;
  if (index == '1') {
	  document.getElementById('inSearchDiv').style.display= 'block';
	  document.getElementById('inSearchDivSub').style.display= 'none';
  } else if (index == '2') {
	  document.getElementById('inSearchDiv').style.display= 'none';
	  document.getElementById('inSearchDivSub').style.display= 'block';
  }
}

function animate(lastTick, timeLeft, closingId, openingId)
{  
  var curTick = new Date().getTime();
  var elapsedTicks = curTick - lastTick;
  var opening = (openingId == '') ? null : document.getElementById(openingId);
  var closing = (closingId == '') ? null : document.getElementById(closingId);
  if(timeLeft <= elapsedTicks)
  {
    if(opening != null)
    {
      opening.style.height = ContentHeight + '%';
      if(flag == false)
      {
	      document.getElementById('inSearchDivHeader1').style.display= 'none';
	      document.getElementById('inSearchDivHeader2').style.display= 'block';
	  }
      else
	  {
	      if(openingId == "Accordion1Content")
		  {
			  document.getElementById('inSearchDivHeader1').style.display= 'none';
			  document.getElementById('inSearchDivHeader2').style.display= 'block';
		  }
		  else
		  {
			  document.getElementById('inSearchDivHeader1').style.display= 'block';
			  document.getElementById('inSearchDivHeader2').style.display= 'none';
		  }
	  }
    }
   if(closing != null)
    {
      closing.style.display = 'none';
      closing.style.height = '0px';
      if(flag== true)
      {
        	if(openingId == "Accordion1Content")
		    {
			     document.getElementById('inSearchDivHeader1').style.display= 'none';
			     document.getElementById('inSearchDivHeader2').style.display= 'block';
			 }
			else
			{
			    document.getElementById('inSearchDivHeader1').style.display= 'block';
			    document.getElementById('inSearchDivHeader2').style.display= 'none';
			}
      }
      else
      {
            document.getElementById('inSearchDivHeader1').style.display= 'block';
            document.getElementById('inSearchDivHeader2').style.display= 'block';
       }
      
    }
    flag=false;
    return;
  }
  timeLeft -= elapsedTicks;
  var newClosedHeight = Math.round((timeLeft/TimeToSlide) * ContentHeight);
 
  if(opening != null)
  {
    flag=true;
    if(opening.style.display != 'block')
    opening.style.display = 'block';
    opening.style.height = (ContentHeight) + '%';
  }
  if(closing != null)
  {
    closing.style.height = ContentHeight + '%';
  }
  setTimeout("animate(" + curTick + "," + timeLeft +",'" + closingId + "','" + openingId + "')", 33);
}

function generateReport(){
	document.forms['printReportForm'].action="${context}/advancesearch/generateExcelReport.ajax";
	document.forms['printReportForm'].method="GET";
	document.forms['printReportForm'].submit();
} 
	function openUserCommentsLockDialog(variableId, systemName, lockUserId, variableDesc, action) {
		$("#variableIdForLockFlow").val(variableId);
		$("#systemForLockFlow").val(systemName);
		$("#variableDescForLockFlow").val(escape(variableDesc));
		$("#actionForLockFlow").val(action);
		$("#userForLockFlow").val(lockUserId);
		$("#lockComments").val('');
		if (action == 'LockView' || action == 'LockLocate' || action == 'Lock') {
			action = 'Lock';
			if (isElementDefined($("[id=buttonUnlock]"))) {
				var lockImage = '<img id="buttonLock" src="${imageDir}/Lock_but.gif" alt="Lock"  title="Lock"/>';
				$("#buttonUnlock").replaceWith(lockImage);
			}
		} else if (action == 'UnlockView' || action == 'UnlockLocate' || action == 'Unlock') {
			action = 'Unlock';
			if (isElementDefined($("[id=buttonLock]"))) {
				var unlockImage = '<img id="buttonUnlock" src="${imageDir}/Unlock_but.gif" alt="Unlock"  title="Unlock"/>';
				$("#buttonLock").replaceWith(unlockImage);
			}
		}
		$("#lockusercomment").dialog({
					 		height:'auto',
							width:'450px',
							title: action + ' - ' + variableId,
	                        show:'slide',
	                        modal: true
				});
		$("#lockusercomment").dialog('open');
	}

	function lockAuditLock (variableId, systemName, lockUserId, variableDesc, usercomment) {
					$.ajax(	{
							url: "${context}/stateflow/auditLockVariable.ajax",
							dataType: "json",
							type: "POST",			
							data: "variableId="+escape(variableId)+"&systemName="+escape(systemName)+"&userName="+escape(lockUserId)+"&variableDesc="+escape(variableDesc)+"&userComment="+escape(usercomment),
							success: function(data) {
							cache: false,
							displayUnLockIcon(data);}
						});
	}
	function displayUnLockIcon(data) {
	var errorMessages = data.error_messages;
	if(typeof(errorMessages) != 'undefined' && errorMessages.length >0) {
			for(i=0; i< errorMessages.length; i++) {
				addErrorToNotificationTray(errorMessages[i]);
			}
	openTrayNotification();
	}else{
	
	var unLockIcon = '<a href = "#" id="AuditUnLock_'+data.variableId+ 
		'" onclick="openUserCommentsLockDialog(\''+data.variableId+'\',\''+data.systemName+'\',\''+data.userName+'\',\''+escape(data.variableDesc)+'\',\'Lock\');"><img src="${imageDir}/AuditLockIndicatorNew.jpg" alt="Lock" title="Lock" style="height:15px;"/></a>';
		$("[id=AuditLock_"+data.variableId+"]").replaceWith(unLockIcon);
		$("[id=AuditLockImg_"+data.variableId+"]").hide();
	if ($("[id=auditLockEditStatus]").val() == 'false') {
		var editIconLocate = '<a href="#" id="locate_EditIcon_'+ data.variableId +'" onclick=\'editMappingVariable("'+ data.variableId +'");\'><img src="${imageDir}/edit_icon.gif"  alt="Edit" title="Edit" /></a>';
		$("[id="+data.variableId+"_LocateSpace]").replaceWith(editIconLocate);
		var markNAIconLocate = '<a href="#" id="locate_NA_' + data.variableId + '" onclick=\'editMappingVariable("'+ data.variableId +'");\'><img src="${imageDir}/Applicable.gif" alt="Mark as Applicable"  title="Mark as Applicable"/></a>';
		$("[id="+data.variableId+"_NASpace]").replaceWith(markNAIconLocate);
	}
	if(data.isLockedOrUnlocked=='unlocked'){
		addErrorToNotificationTray('Mapping is not locked');
		openTrayNotification();
	}
	}
	
	}
	
	function unLockAuditLock (variableId, systemName, lockUserId, variableDesc, usercomment) {
					$.ajax({
							url: "${context}/stateflow/auditUnLockVariable.ajax",
							dataType: "json",
							type: "POST",			
							data: "variableId="+escape(variableId)+"&systemName="+escape(systemName)+"&userName="+escape(lockUserId)+"&variableDesc="+escape(variableDesc)+"&userComment="+escape(usercomment),
							success: function(data) {
							cache: false,
							displayLockIcon(data);}
						});
	}
	function displayLockIcon(data) {
	var errorMessages = data.error_messages;
	if(typeof(errorMessages) != 'undefined' && errorMessages.length >0) {
			for(i=0; i< errorMessages.length; i++) {
				addErrorToNotificationTray(errorMessages[i]);
			}
	openTrayNotification();
	}else{
	var lockIcon = '<a href = "#" id="AuditLock_'+data.variableId+ 
		'" onclick="openUserCommentsLockDialog(\''+data.variableId+'\',\''+data.systemName+'\',\''+data.userName+'\',\''+escape(data.variableDesc)+'\',\'Unlock\');"><img src="${imageDir}/auditUnLockIndicator.GIF" alt="Unlock" title="Unlock" style="height:15px;"/></a>';
		$("[id=AuditUnLock_"+data.variableId+"]").replaceWith(lockIcon);
	var lockImgIcon = '<img src="${imageDir}/AuditLockIndicatorNew.jpg" id="AuditLockImg_'+data.variableId+'" alt="Lock" title="Lock" style="height:9px;width:9px"/>';
		$("[id=AuditLockImg_"+data.variableId+"]").replaceWith(lockImgIcon);
		$("[id=AuditLockImg_"+data.variableId+"]").show();
	if ($("[id=auditLockEditStatus]").val() == 'false') {
		var hideEditIcon = '<span id="'+data.variableId+'_LocateSpace" style="display:none;"></span>';
		$("[id=locate_EditIcon_"+data.variableId+"]").replaceWith(hideEditIcon);
		var hideMarkNAIcon = '<span id="'+data.variableId+'_NASpace" style="display:none;"></span>';
		$("[id=locate_NA_"+data.variableId+"]").replaceWith(hideMarkNAIcon);
	}
	if(data.isLockedOrUnlocked=='locked'){
		addErrorToNotificationTray('Mapping is already locked');
		openTrayNotification();
	}
	}
	
	}

//Added
function lockAuditLockView (variableId, systemName, lockUserId, variableDesc, usercomment) {
					$.ajax(	{
							url: "${context}/stateflow/auditLockVariable.ajax",
							dataType: "json",
							type: "POST",			
							data: "variableId="+escape(variableId)+"&systemName="+escape(systemName)+"&userName="+escape(lockUserId)+"&variableDesc="+escape(variableDesc)+"&userComment="+escape(usercomment),
							success: function(data) {
							cache: false,
							displayUnLockIconForView(data);}
						});
	}
	function displayUnLockIconForView(data) {
	var errorMessages = data.error_messages;
	if(typeof(errorMessages) != 'undefined' && errorMessages.length >0) {
			for(i=0; i< errorMessages.length; i++) {
				addErrorToNotificationTray(errorMessages[i]);
			}
	openTrayNotification();
	}else{
		var unLockIcon = '<a href = "#" id="AuditUnLockView_'+data.variableId+ 
			'" onclick="openUserCommentsLockDialog(\''+data.variableId+'\',\''+data.systemName+'\',\''+data.userName+'\',\''+escape(data.variableDesc)+'\',\'LockView\');">Lock</a>';
			$("[id=AuditLockView_"+data.variableId+"]").replaceWith(unLockIcon);
			$("[id=AuditLockViewImg_"+data.variableId+"]").hide();
		
		if(isElementDefined($("[id=AuditLock_"+data.variableId+"]"))){
			var unLockIconforLanding = '<a href = "#" id="AuditUnLock_'+data.variableId+ 
				'" onclick="openUserCommentsLockDialog(\''+data.variableId+'\',\''+data.systemName+'\',\''+data.userName+'\',\''+escape(data.variableDesc)+'\',\'Lock\');"><img src="${imageDir}/AuditLockIndicatorNew.jpg" alt="Lock" title="Lock" style="height:15px;"/></a>';
			$("[id=AuditLock_"+data.variableId+"]").replaceWith(unLockIconforLanding);
			if ($("[id=auditLockEditStatus]").val() == 'false') {
				var editIcon = '<a href = "#" id="'+data.variableId+'_Edit" onclick="editMapping(\''+data.variableId+'\')";\'><img src="${imageDir}/edit_icon.gif" alt="Edit" title="Edit"  style="height:15px;"/></a>';
				$("[id="+data.variableId+"_Space]").replaceWith(editIcon);
			}
		}
		
		if(isElementDefined($("[id=AuditLockImg_"+data.variableId+"]"))){
			$("[id=AuditLockImg_"+data.variableId+"]").hide();
		}
		
		if(isElementDefined($("[id=AuditLockLocate_"+data.variableId+"]"))){
			var unLockIconForLocate = '<a href = "#" id="AuditUnLockLocate_'+data.variableId+ 
				'" onclick="openUserCommentsLockDialog (\''+data.variableId+'\',\''+data.systemName+'\',\''+data.userName+'\',\''+escape(data.variableDesc)+'\',\'LockLocate\');"><img src="${imageDir}/AuditLockIndicatorNew.jpg" alt="Lock" title="Lock" style="height:15px;width:12px;"/></a>';
			$("[id=AuditLockLocate_"+data.variableId+"]").replaceWith(unLockIconForLocate);
			if ($("[id=auditLockEditStatus]").val() == 'false') {
				var editIconLocate = '<a href="#" id="locate_EditIcon_' + data.variableId + '" onclick=\'editMapping("'+ data.variableId +'");\'><img src="${imageDir}/edit_icon.gif"  alt="Edit" title="Edit" /></a>';
				$("[id="+data.variableId+"_LocateSpace]").replaceWith(editIconLocate);
				var markNAIconLocate = '<a href="#" id="locate_NA_' + data.variableId + '" onclick=\'editMappingVariable("'+ data.variableId +'");\'><img src="${imageDir}/Applicable.gif" alt="Mark as Applicable"  title="Mark as Applicable"/></a>';
				$("[id="+data.variableId+"_NASpace]").replaceWith(markNAIconLocate);
			}
		}
		
		if(isElementDefined($("[id=AuditLockLocate_"+data.variableId+"]"))){
			$("[id=AuditUnLockLocateImg_"+data.variableId+"]").hide();
		}
		
		if(data.isLockedOrUnlocked=='unlocked'){
			addErrorToNotificationTray('Mapping is not locked');
			openTrayNotification();
		}else{
			$("#viewAuditTrailId").html(data.auditTrailList);
		}
	}
	}
	function unLockAuditLockView (variableId, systemName, lockUserId, variableDesc, usercomment) {
					$.ajax({
							url: "${context}/stateflow/auditUnLockVariable.ajax",
							dataType: "json",
							type: "POST",			
							data: "variableId="+escape(variableId)+"&systemName="+escape(systemName)+"&userName="+escape(lockUserId)+"&variableDesc="+escape(variableDesc)+"&userComment="+escape(usercomment),
							success: function(data) {
							cache: false,
							displayLockIconFromView(data);}
						});
	}
	function displayLockIconFromView(data) {
	var errorMessages = data.error_messages;
	if(typeof(errorMessages) != 'undefined' && errorMessages.length >0) {
			for(i=0; i< errorMessages.length; i++) {
				addErrorToNotificationTray(errorMessages[i]);
			}
	openTrayNotification();
	}else{
		var lockIcon = '<a href = "#" id="AuditLockView_'+data.variableId+ 
			'" onclick="openUserCommentsLockDialog(\''+data.variableId+'\',\''+data.systemName+'\',\''+data.userName+'\',\''+escape(data.variableDesc)+'\',\'UnlockView\');">Unlock</a>';
			$("[id=AuditUnLockView_"+data.variableId+"]").replaceWith(lockIcon);
		var lockImgIcon = '<img src="${imageDir}/AuditLockIndicatorNew.jpg" id="AuditLockViewImg_'+data.variableId+'" alt="Lock" title="Lock" style="height:9px;width:9px"/>';
			$("[id=AuditLockViewImg_"+data.variableId+"]").replaceWith(lockImgIcon);
			$("[id=AuditLockViewImg_"+data.variableId+"]").show();
		
		if(isElementDefined($("[id=AuditUnLock_"+data.variableId+"]"))){
			var lockIconForLanding = '<a href = "#" id="AuditLock_'+data.variableId+ 
			'" onclick="openUserCommentsLockDialog(\''+data.variableId+'\',\''+data.systemName+'\',\''+data.userName+'\',\''+escape(data.variableDesc)+'\',\'Unlock\');"><img src="${imageDir}/auditUnLockIndicator.GIF" alt="Unlock" title="Unlock" style="height:15px;"/></a>';
			$("[id=AuditUnLock_"+data.variableId+"]").replaceWith(lockIconForLanding);
			if ($("[id=auditLockEditStatus]").val() == 'false') {
				var editIcon = '<a href = "#" id="'+data.variableId+'_Edit" onclick="editMapping(\''+data.variableId+'\')";\'><img src="${imageDir}/edit_icon.gif" alt="Edit" title="Edit"  style="height:15px;"/></a>';
				$("[id="+data.variableId+"_Space]").replaceWith(editIcon);
			}
		}
		
		if(isElementDefined($("[id=AuditLockImg_"+data.variableId+"]"))){
		var lockImgIconForLanding = '<img src="${imageDir}/AuditLockIndicatorNew.jpg" id="AuditLockImg_'+data.variableId+'" alt="Lock" title="Lock" style="height:9px;width:9px"/>';
			$("[id=AuditLockImg_"+data.variableId+"]").replaceWith(lockImgIconForLanding);
			$("[id=AuditLockImg_"+data.variableId+"]").show();
			if ($("[id=auditLockEditStatus]").val() == 'false') {
				var hideEditIcon = '<span id="'+data.variableId+'_Space" style="display:none;"></span>';
				$("[id="+data.variableId+"_Edit]").replaceWith(hideEditIcon);
			}
		}
		if(isElementDefined($("[id=AuditUnLockLocate_"+data.variableId+"]"))){
			var lockIconForLocate = '<a href = "#" id="AuditLockLocate_'+data.variableId+ 
			'" onclick="openUserCommentsLockDialog (\''+data.variableId+'\',\''+data.systemName+'\',\''+data.userName+'\',\''+escape(data.variableDesc)+'\',\'UnlockLocate\');"><img src="${imageDir}/auditUnLockIndicator.GIF" alt="Unlock" title="Unlock" style="height:15px;width:12px;"/></a>';
			$("[id=AuditUnLockLocate_"+data.variableId+"]").replaceWith(lockIconForLocate);
		}
		if(isElementDefined($("[id=AuditUnLockLocateImg_"+data.variableId+"]"))){
			var lockImgIconForLocate = '<img src="${imageDir}/AuditLockIndicatorNew.jpg" id="AuditUnLockLocateImg_'+data.variableId+'" alt="Audit Lock" title="Audit Lock" style="height:9px;width:9px"/>';
			$("[id=AuditUnLockLocateImg_"+data.variableId+"]").replaceWith(lockImgIconForLocate);
			$("[id=AuditUnLockLocateImg_"+data.variableId+"]").show();
			if ($("[id=auditLockEditStatus]").val() == 'false') {
				var hideEditIconLocate = '<span id="'+data.variableId+'_LocateSpace" style="display:none;"></span>';
				$("[id=locate_EditIcon_" + data.variableId + "]").replaceWith(hideEditIconLocate);
				var hideMarkNAIcon = '<span id="'+data.variableId+'_NASpace" style="display:none;"></span>';
				$("[id=locate_NA_"+data.variableId+"]").replaceWith(hideMarkNAIcon);
			}
		}
			
		if(data.isLockedOrUnlocked=='locked'){
			addErrorToNotificationTray('Mapping is already locked');
			openTrayNotification();
		}else{
			$("#viewAuditTrailId").html(data.auditTrailList);
		}
	}
	}
	
/***************************January Release*************************************/	
function getSystemForContract(){
$('#revisionDateTD').hide("");
$('#revisionDateTD1').hide("");
	var buttonId = document.getElementById("search");
	buttonId.disabled = true;
	var contractId=$("#contractId").val();
	var warningMsgForContractSystem = "The Contract is present in both LG and ISG systems."  
 						   +" By default, ISG is selected.";
	$.ajax({
			url: "${context}/advancesearch/populateSystemAndStartDateForContract.ajax",
			dataType: "json",
			type: "POST",
			data: "contractId="+contractId,
			success: function(data) {
				cache: false;
				if(data.systemForContract=='LG' || data.systemForContract=='ISG' ){
					$('#systemNameForContract').html(data.system);
					$('#startDateForContract').html(data.rows);
					if(data.systemForContract=='ISG'){
					 if(data.rowsRev != ""){
						 $('#revisionDateForISGContract').html(data.rowsRev);
						 $('#revisionDateTD').show("");
						 $('#revisionDateTD1').show("");
						 $('#revisionDateForISGContract').show();
					 }
					 else{
					 	$('#revisionDateForISGContract').html('');
					 	$('#revisionDateForISGContract').hide("");
					 }
					}
				}else {
					$('#systemNameForContract').html(data.system);
					$('#startDateForContract').html(data.rows);
						if(data.rowsRev != ""){
							$('#revisionDateForISGContract').html(data.rowsRev);
							$('#revisionDateTD').show("");
							$('#revisionDateTD1').show("");
							$('#revisionDateForISGContract').show();
						}
						else{
						 $('#revisionDateForISGContract').html('');
						 $('#revisionDateForISGContract').hide("");
						}
					if(data.systemForContract == "LG,ISG"){
						 confirmationDialogForContractSystem(warningMsgForContractSystem);
					 }
				}
				document.getElementById('search').style.display= 'block';
				var buttonId = document.getElementById("search");
				buttonId.disabled = false;
				}
		});

}

function confirmationDialogForContractSystem(warningMsgForContractSystem){
	$("#confirmationDivForContractSystem").html(warningMsgForContractSystem);
	$('#revisionDateForISGContract').hide("");
	$('#systemNameForContract').hide("");
	$('#startDateForContract').hide("");
	$("#confirmationDivForContractSystem").dialog({
			autoOpen: false,
			title : 'Confirm',
			resizable: false,
			height:140,
			width:400,
			modal: true,
			close: function(){ 
			        	$('#revisionDateForISGContract').show("");
						$('#systemNameForContract').show("");
						$('#startDateForContract').show("");
			        },
			buttons: {			
			OK: function() {
					$('#revisionDateForISGContract').show("");
					$('#systemNameForContract').show("");
					$('#startDateForContract').show("");
					$(this).dialog('close');					
				}
				
			}
		});
		$("#confirmationDivForContractSystem").dialog('open');
	}

function getStartDateForContract(){
$('#revisionDateTD').hide("");
$('#revisionDateTD1').hide("");
var systemNameForContract=$("#systemNameForContract").val();
var contractId = $('#contractId').val();
//alert(contractId+"+"+systemNameForContract);
$.ajax({
			url: "${context}/advancesearch/populateSystemAndStartDateForContract.ajax",
			dataType: "json",
			type: "POST",
			data: "systemNameForContract="+systemNameForContract+"&contractId="+contractId,
			success: function(data) {
				cache: false;
				if(data.systemForContract=='LG'){
					$('#systemNameForContract').html(data.system);
					$('#startDateForContract').html(data.rows);
					$('#systemNameForContract').val(data.systemForContract);
					$('#revisionDateForISGContract').html('');
					$('#revisionDateForISGContract').hide();
				}else if(data.systemForContract=='ISG'){
					$('#systemNameForContract').html(data.system);
					$('#startDateForContract').html(data.rows);
					$('#systemNameForContract').val(data.systemForContract);
					if(data.rowsRev != ""){
							$('#revisionDateForISGContract').html(data.rowsRev);
							$('#revisionDateTD').show("");
							$('#revisionDateTD1').show("");
							$('#revisionDateForISGContract').show();
						}
					else{
						 $('#revisionDateForISGContract').html('');
						 $('#revisionDateForISGContract').hide();
					}
				}else{
					$('#systemNameForContract').html(data.system);
					$('#startDateForContract').html('');
					$('#revisionDateForISGContract').html('');
					$('#revisionDateForISGContract').hide();
				}
				}
		});
}

function getRevisionDateForISGContract(){
$('#revisionDateTD').hide("");
$('#revisionDateTD1').hide("");


var systemNameForContract=$("#systemNameForContract").val();
var contractId = $('#contractId').val();
var contractStartDate=$('#startDateForContract').val();
//alert(contractStartDate);
//alert(contractId+"+"+systemNameForContract);
if(systemNameForContract=='ISG'){
$.ajax({
			url: "${context}/advancesearch/populateSystemAndStartDateForContract.ajax",
			dataType: "json",
			type: "POST",
			data: "systemNameForContract="+systemNameForContract+"&contractId="+contractId+"&startDateForContract="+contractStartDate,
			success: function(data) {
				cache: false;
				//alert(data.rowsRev);
				if(data.systemForContract=='ISG'){
					//alert($("#revisionDateForISGContract").size()); 
					if(data.rowsRev != ""){
							$('#revisionDateForISGContract').html(data.rowsRev);
							$('#revisionDateTD').show("");
							$('#revisionDateTD1').show("");
							$('#revisionDateForISGContract').show();
						}
						else{
						$('#revisionDateForISGContract').html('');
						$('#revisionDateForISGContract').hide();
						}
					}
					else{
					$('#revisionDateForISGContract').html('');
					$('#revisionDateForISGContract').hide();
					}
				}
			});
		}
}
/***************************January Release*************************************/
</script>
</head>
<body onload="persistSearchCriteria();">
<form name="editForm" action="${context}/editmapping/editMapping.html" method="POST">
	<input type="hidden" id="pageName" name="pageName" value="advanceSearch"/>
    <input type="hidden" id="selectedvariableForEditId" name="selectedvariableForEditId" value="" />
    <input type="hidden" id="getAllPrint" name="getAllPrint" value="" />
	<input type="hidden" id="appendUserPrint" name="appendUserPrint" value="" />
</form>


<form name="unmappedVarForm" action="${context}/viewcreatemappingpage/viewFromUnMapped.html">
<div  id="unmappedTableDiv"><!--Starts submitterContinue-->
<input type="hidden" id="unmappedVariableId" value=""/>
<input type="hidden" name="variableIdHidden" id="variableIdHidden" value="" />
<input type="hidden" id="pageName" name="pageName" value="advanceSearch"/>

</div>
</form>
	<form name="submitCreateForm"  method="post" style="height: 100%;" onkeypress="enterKeyPress(event);">
	<input type="hidden" id="selectedSpsIdForEdit" name="selectedSpsIdForEdit" value="" />
	<input type="hidden" id="pageName" value="advanceSearchEbx"/>
	<input type="hidden" id="contextPath" value= "<%=request.getContextPath()%>"/>
	<%@include file="/WEB-INF/jspf/pageTop.jspf"%>
		
    <div class="innerContainer" style="height:99%">
    	<div id="createEditContainerAdvanceSearch"  style="height: 100%; overflow: hidden;">	<!--createEditContainer Starts-->
		<div id="advanceSearchResultContainer" class="AdavanceSearchOuterResult" style="height:62%;">
			<div id="advanceSearchOuterContainer" style="height:98%">
				<div id="advanceSearchInnerContainer" class="AdavanceSearchInnerResult" style="border:1px solid #898989;">
					<div class="titleBar">
						<div class="headerTitle">Advanced Search</div>
					</div>
					<div class="AccordionContainer" style="width: auto; height:92%;">
						<div>
							<div class="SearchAccordianTableDivSub" id="inSearchDivHeader1" style="display: block;">
								<table id="advanceSearchTable1" border="1" cellpadding="0" cellspacing="0" width="100%">
									<TBODY>
										<tr style="height: 15px; _height:15px">
											<td class="tableHeaderAdvanceSearch" nowrap="nowrap">Parameters							
												<div class="AccordionTitle" onClick="runAccordionSearch(1);" style="text-align : right;padding-right : 9px;" onselectstart="return false;">
												
												
												 <span class="ui-widget-header" style="display:inline-block;background:none; border:0px; position:relative; top:-10px; left:-7px;height:5px;">
														<span class="ui-icon ui-icon-triangle-1-e" style="position:relative;height:15px;"/>
												</span>
												
												</div>
											</td>
										</tr>
									</TBODY>
								</table>
							</div>	
						</div>
						<div class="AccordionContentSearch" id="Accordion1Content">
							<div class="SearchAccordianTableDiv" id="inSearchDiv" style="display:block;overflow:auto;overflow-x:hidden;">
								<div> <!-- search criteria container starts-->
										<table border="1" cellpadding="0" cellspacing="0" width="100%">
											<TBODY>
												<tr style="height: 15px; _height:15px">
													<td class="tableHeaderAdvanceSearch" nowrap="nowrap">Parameters							
														<div id="paramArdnIcon" class="AccordionTitle" onClick="runAccordionSearch(2);" style="text-align : right;padding-right : 9px;" onselectstart="return false;">
														
														
														 <span class="ui-widget-header" style="display:inline-block;background:none; border:0px; position:relative; top:-10px; left:-10px;height:5px;">
																<span class="ui-icon ui-icon-triangle-1-s" style="position:relative;height:15px;"/>
														</span>
														
														</div>
													</td>
												</tr>
											</TBODY>
										</table>
										<div class="createTable" style="padding-bottom: 10;">
											<input type="hidden" name="currentPage" id="currentPage" value="${page.currentPage}"/>
											<input type="hidden" name="auditLockEditStatus" id="auditLockEditStatus" value="${userUIPermissions.authorizedEditLockVar}" />
											<table border="0" cellpadding="0" cellspacing="0" width="100%" class="shadedText">
												<tr height="20px" class="createEditTable1-Listdata" style="vertical-align: bottom;">
													<td colspan="3" style="vertical-align: bottom;">&nbsp;</td>
													<td colspan="4" style="vertical-align: bottom; padding-left: 150px;"><b style="font-size:11px;">SHOW ONLY</b></td>
												</tr>
												<tr height="20px" class="createEditTable1-Listdata" style="vertical-align: bottom;">
													
													<td class="headText" style="vertical-align: bottom;" width="140px">Variable ID</td>
													<td class="headText" width="150px" style="vertical-align: bottom;">Variable Description<a href="#" onclick="displayInfo('EB01_SRCH',event,'../ajaxhippasegmenttooltip.ajax');"></a></td>
													<td class="headText" style="vertical-align: bottom;">Major Heading<a href="#" onclick="displayInfo('MAJ_HEADING_SRCH',event,'../ajaxhippasegmenttooltip.ajax');"> what is this?</a></td>
													<td class="headText" style="vertical-align: bottom; width: 50px; border-left: #d9e5eb 1px solid;"" ></td>
													<td class="headText" style="vertical-align: bottom;width:100px">User<a href="#"  onclick="displayInfo('USER',event,'../ajaxhippasegmenttooltip.ajax');"> what is this?</a></td>
																													
													<td rowspan="9" colspan="2" style="vertical-align: top;padding-left:50px;" >
													<table border="0" cellpadding="0" cellspacing="0"
															class="mt10 mL10 Pd3 shadedText" >
															<tr class="createEditTable1-Listdata">
																<td width="120px" class="verdana">
																	<input type="checkbox" checked name="isUnMapped" id="isUnMapped" value="unMapped"/>&nbsp;Unmapped
																</td>
																<td width="90px" class="verdana">
																	
																</td>
															</tr>
															<tr class="createEditTable1-Listdata">
																
																<td class="verdana"><input type="checkbox" checked name="isMapped" id="isMapped" value="isMapped" />&nbsp;Mapped</td>
																<td>&#160;</td>
															</tr>	
															<tr class="createEditTable1-Listdata">
																<td class="verdana" height="21"><input type="checkbox" checked name="isNotApplicable" id="isNotApplicable" value="isNotApplicable"/>&nbsp;Not Applicable</td>
																<td></td>
															</tr>
															<tr class="createEditTable1-Listdata">
																<td class="verdana"><input type="checkbox" checked name="isNotFinalized" id="isNotFinalized" value="notFinalized"/>&nbsp;Not Finalized</td>
																<td></td>
															</tr>
															<tr class="createEditTable1-Listdata">
																<td class="verdana">&#160;</td>
																<td></td>
															</tr>	
														</table>
													</td>
												</tr>
												<tr class="createEditTable1-Listdata" style="padding-top: 0px">
													
													<td><input type="text" name="varIDLocate"class="inputbox60" id="varIdFrmLocate" onkeypress="enterKeyPress(event);"/></td>
													<td><input type="text" name="varDescVal" class="inputbox60" id="varDescId" onkeypress="enterKeyPress(event);"/></td>
													<td><input type="text" name="majHdgVal" id="majHdgId" class="inputbox60" onkeypress="enterKeyPress(event);"/></td>	
													<td class="headText" style="vertical-align: bottom; width: 50px; border-left: #d9e5eb 1px solid;"" ></td>
													<td><input type="text" name="userVal" id="userId" class="inputbox60" onkeypress="enterKeyPress(event);"/></td>	
												</tr>
												<tr height="20px" class="createEditTable1-Listdata" style="vertical-align: bottom;">
													
													<td class="headText" width="150px" style="vertical-align: bottom;">EB01<a href="#" onclick="displayInfo('EB01_SRCH',event,'../ajaxhippasegmenttooltip.ajax');"> what is this?</a></td>
													<td class="headText" style="vertical-align: bottom;">EB03<a href="#"  onclick="displayInfo('EB03_SRCH',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a></td>
													<td class="headText" width="165px" style="vertical-align: bottom;">Minor Heading<a href="#" onclick="displayInfo('MIN_HEADING_SRCH',event,'../ajaxhippasegmenttooltip.ajax');"> what is this?</a></td>
													<td class="headText" style="vertical-align: bottom; width: 50px; border-left: #d9e5eb 1px solid;"" ></td>
													<td></td>
												</tr>
												<tr class="createEditTable1-Listdata" style="padding-top: 0px">
													
													<td><input type="text" name="eb01Val" class="inputbox60" id="EB01Id" onkeypress="enterKeyPress(event);"/></td>
													<td><input type="text" name="eb03Val" class="inputbox60" id="EB03Id0" onkeypress="enterKeyPress(event);"></td>
													<td><input type="text" id="minHdgId" name="minHdgVal" class="inputbox60" onkeypress="enterKeyPress(event);"/></td>
													<td class="headText" style="vertical-align: bottom; width: 50px; border-left: #d9e5eb 1px solid;"" ></td>
													<td></td>
												</tr>
												
												<tr height="20px" class="createEditTable1-Listdata" style="vertical-align: bottom;">
													
													<td class="headText" width="150px" style="vertical-align: bottom;">III02 <a	href="#" onclick="displayInfo('III02_SRCH',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a></td>
													<td class="headText" style="vertical-align: bottom;">Note Type<a href="#"  onclick="displayInfo('EB03_SRCH',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a></td>
													<td class="headText" width="165px" style="vertical-align: bottom;">Message Text<a href="#"  onclick="displayInfo('MSG_SRCH',event,'../ajaxhippasegmenttooltip.ajax');"> what is this?</a></td>
													<td class="headText" style="vertical-align: bottom; width: 50px; border-left: #d9e5eb 1px solid;"" ></td>
													<td></td>
												</tr>
												<tr class="createEditTable1-Listdata" style="padding-top: 0px">
													
													<td><input type="text" name="ii02Val" class="inputbox60" id="III02Id" onkeypress="enterKeyPress(event);"/></td>
													<td><input type="text" name="noteTypeCodeVal" id="NOTETYPEID" class="inputbox60" onkeypress="enterKeyPress(event);"/></td>	
													<td><input type="text" id="messageValueId" name="messageValue" class="inputbox60" onkeypress="enterKeyPress(event);"/></td>
													<td class="headText" style="vertical-align: bottom; width: 50px; border-left: #d9e5eb 1px solid;"" ></td>
													<td></td>
												</tr>												<tr  class="createEditTable1-Listdata" style="vertical-align: bottom;">
													<td class="headText" width="165px" style="vertical-align: bottom;"></td>
													<td class="headText" style="vertical-align: bottom;"></td>
													<td>&nbsp;</td>
													<td class="headText" style="vertical-align: bottom; width: 50px; border-left: #d9e5eb 1px solid;"" ></td>
													<td></td>
												</tr>
												<!-- Added For January Release Contract Advance Search -->
												<tr  class="createEditTable1-Listdata" style="vertical-align: bottom;">
													
													<td class="headText" style="vertical-align: bottom; padding-left: "><b style="font-size:11px;">Filter By Contract</b> <a href="#"  onclick="displayInfo('CONTSEARCH',event,'../ajaxhippasegmenttooltip.ajax');"> what is this?</a></td>
													<td></td>
													<td></td>
													<td class="headText" style="vertical-align: bottom; width: 50px; border-left: #d9e5eb 1px solid;"" ></td>
													<td></td>
												</tr>
												<tr height="20px" class="createEditTable1-Listdata" style="vertical-align: bottom;">
													
													<td class="headText" width="150px" style="vertical-align: bottom;">Contract Id</td>
													<td class="headText" style="vertical-align: bottom;">System</td>
													<td class="headText" style="vertical-align: bottom;">Start Date</td>
													<td class="headText" style="vertical-align: bottom; width: 50px; border-left: #d9e5eb 1px solid;"" ></td>
													<td></td>
												</tr>
												<tr class="createEditTable1-Listdata" style="padding-top: 0px">
													
													<td><input type="text" name="contractId" class="inputbox60" id="contractId" onchange="getSystemForContract();"/></td>
													<td>
													<select class="dropdown136" id="systemNameForContract" name="systemNameForContract" onchange="getStartDateForContract()">
														<option value = ""> &#160</option>
													</select>
													</td>
													<td>
													<select class="dropdown136" id="startDateForContract" name="startDateForContract" onchange="getRevisionDateForISGContract();">
														<option value = ""> &#160</option>
													</select>
													</td>
													<td class="headText" style="vertical-align: bottom; width: 50px; border-left: #d9e5eb 1px solid;"" ></td>
													<td></td>
												</tr>
												<!-- Contract Advance Search End -->
												
												<tr height="20px" class="createEditTable1-Listdata" style="vertical-align: bottom;">
													<td class="headText" width="165px" style="vertical-align: bottom;"></td>
													<td></td>
													<td  id = "revisionDateTD" class="headText" style="vertical-align: bottom;">Revision Date</td>
													<td class="headText" style="vertical-align: bottom; width: 50px; " ></td>
													<td></td>
												</tr>
												<tr class="createEditTable1-Listdata" style="padding-top: 0px">
													<td></td>
													<td></td>
													<td id = "revisionDateTD1">
													<select class="dropdown136" id="revisionDateForISGContract" name="revisionDateForISGContract">
														<option value = ""> &#160</option>
													</select>
													</td>
													<td></td>
													<td></td>								
												</tr>
												
											</table>
											<table width="100%" height="5px">
												<tr>
													<td>
													
													<input type="hidden" id="hdVarIdFrmLocate" name="hdVarIdFrmLocate" value="${searchCriteria.variableId}" />
													<input type="hidden" id="hdvarDescId" name="hdvarDescId" value="${searchCriteria.variableDescription}" />
													<input type="hidden" id="hdmajHdgId" name="hdmajHdgId" value="${searchCriteria.majorHeading}" />
													<input type="hidden" id="hdminHdgId" name="hdminHdgId" value="${searchCriteria.minorHeading}" />
													<input type="hidden" id="hdcontractId" name="hdcontractId" value="${searchCriteria.contractId}" />
													<input type="hidden" id="hdEB01Id" name="hdEB01Id" value="${searchCriteria.EB01}" />
													<input type="hidden" id="hdIII02Id" name="hdIII02Id" value="${searchCriteria.III02}"  /> 
													<input type="hidden" id="hdEB03Id0" name="hdEB03Id0" value="${searchCriteria.EB03}" /> 
													<input type="hidden" id="hdNOTETYPEID" name="hdNOTETYPEID" value="${searchCriteria.noteType}" /> 
													<input type="hidden" id="hdmessageValueId" name="hdmessageValueId" value="${searchCriteria.messageText}" /> 
													<input type="hidden" id="hdIsUnMappedId" name="hdIsUnMapped" value="${searchCriteria.unMapped}" /> 
													<input type="hidden" id="hdIsMappedId" name="hdIsMapped" value="${searchCriteria.mapped}"  /> 
													<input type="hidden" id="hdIsNotApplicableId" name="hdIsNotApplicable" value="${searchCriteria.notApplicable}" /> 
													<input type="hidden" id="hdIsNotFinalizedId" name="hdIsNotFinalized" value="${searchCriteria.notFinalized}" />
													<input type="hidden" id="hduserId" name="hduserId" value="${searchCriteria.user}" />
													<input type="hidden" id="hdFromHistory" name="hdFromHistory" value="${fromHistory}" />
													<input type="hidden" id="hdFromPrint" name="hdFromPrint" value="${fromPrint}" />
													<input type="hidden" id="hdAuthorizedToApprove" name="hdAuthorizedToApprove" value="${userUIPermissions.authorizedToapprove}" />
												</td>
														
												</tr>
											</table>
									</div>
							
								</div> <!-- search criteria container ends-->
							</div>
						</div>  <!-- test end -->
						<div>
							<div class="SearchAccordianTableDivSub" id="inSearchDivHeader2">
								<table id="updateSearchTable1" border="1" cellpadding="0" cellspacing="0" width="100%">
									<TBODY>
										<tr style="height: 15px; _height:15px">
											<td class="tableHeaderAdvanceSearch" nowrap="nowrap" >Advanced Search Result								
												<div class="AccordionTitle" onClick="runAccordionSearch(2);" style="text-align : right;padding-right : 9px;" onselectstart="return false;">
												
												
												 <span class="ui-widget-header" style="display:inline-block;background:none; border:0px; position:relative; top:-10px; left:-7px;height:5px;">
														<span class="ui-icon ui-icon-triangle-1-e" style="position:relative;height:15px;"/>
												</span>
												
												</div>
											</td>
										</tr>
									</TBODY>
								</table>
							</div>
						</div>
						<div class="AccordionContentSearch" id="Accordion2Content">
							<div class="SearchAccordianTableDiv" id="inSearchDivSub" >
								<!-- Search result goes here -->
								<jsp:include flush="true" page="advancesearchresultbx.jsp"></jsp:include>	
							</div>
						</div>
					</div>
				
				</div>
							
			</div>
			
		</div>
		<div>
		</div>
		<div id="massUpdateContainer" style="overflow-x: hidden; display: none; overflow: auto; border:1px solid #898989;height: 37%;" ><!-- update div starts -->
			<input type="hidden" id="hdMassUpdateComment" name="hdMassUpdateComment"/> 
				<table id="updateFieldTableVariable" width="100%" border="1" cellpadding="0" cellspacing="0">
					<THEAD>
						<tr style="height: 15px; _height:15px">
							<td class="tableHeader" nowrap="nowrap" colspan="12" style="height: 15px; _height:15px">Update Fields</td>							
						</tr>
					</THEAD>
					<TBODY>
						<tr>
							<td class="headText" style="vertical-align: middle;" colspan="2">&nbsp;</td>
							<td class="headText" style="vertical-align: middle; padding-left: 10px;" colspan="1">Current</td>
							<td class="headText" style="vertical-align: middle; padding-left: 10px;" colspan="3">New</td>
							<td class="headText" style="vertical-align: middle; padding-left: 10px;" colspan="1">Current</td>
							<td class="headText" style="vertical-align: middle; padding-left: 10px;" colspan="3">New</td>
							<td class="headText" style="vertical-align: middle; padding-left: 10px;" colspan="1">Current</td>
							<td class="headText" style="vertical-align: middle; padding-left: 10px;" colspan="1">New</td>	
							
						</tr>
						<tr height="20px" class="createEditTable1-Listdata" style="vertical-align: bottom;">
								<td class="headText" style="vertical-align: middle;"  width="20px"><input type="checkbox" onclick="enableField('isUdtEB01','uEB01Id','nvEB01Id')" name="isUdtEB01" id="isUdtEB01" value="udtEB01"/></td>
								<td class="headText" style="vertical-align: middle;"  width="50px">EB01</td>
								<td class="headText" style="vertical-align: middle;"  width="80px"><input type="text" name="ueb01Val" class="inputbox50Disabled" id="uEB01Id" disabled="disabled"/></td>
								<td class="headText" style="vertical-align: middle;"  width="100px"><input type="text" name="nveb01Val" class="inputbox30Disabled" id="nvEB01Id" disabled="disabled"/>&#160;</td>
								<td class="headText" style="vertical-align: middle;"  width="20px"><input type="checkbox" onclick="enableField('isUdtiii02','uiii02Id','nviii02Id')" name="isUdtiii02" id="isUdtiii02" value="udtiii02"/></td>
								<td class="headText" style="vertical-align: middle;"  width="60px">III02</td>
								<td class="headText" style="vertical-align: middle;"  width="80px"><input type="text" name="uiii02Val" class="inputbox50Disabled" id="uiii02Id" disabled="disabled"/></td>
								<td class="headText" style="vertical-align: middle;" align="left" ><input type="text" name="nviii02Val" class="inputbox30Disabled" id="nviii02Id" disabled="disabled"/></td>
								<td class="headText" style="vertical-align: middle;"  width="20px"><input type="checkbox" onclick="enableField('isUdtHSD05','uHSD05Id','nvHSD05Id')" name="isUdtHSD05" id="isUdtHSD05" value="udtHSD05"/></td>
								<td class="headText" style="vertical-align: middle;"  width="50px">HSD05</td>
								<td class="headText" style="vertical-align: middle;"  width="80px"><input type="text" name="uhsd05Val" class="inputbox50Disabled" id="uHSD05Id" disabled="disabled"/></td>
								<td class="headText" style="vertical-align: middle;"  width="100px"><input type="text" name="nvhsd05Val" class="inputbox30Disabled" id="nvHSD05Id" disabled="disabled"/>&#160;</td>
						</tr>
						<tr height="20px" class="createEditTable1-Listdata" style="vertical-align: bottom;">
								<td class="headText" style="vertical-align: middle;"  width="20px"><input type="checkbox" onclick="enableField('isUdtEB02','uEB02Id','nvEB02Id')" name="isUdtEB02" id="isUdtEB02" value="udtEB02"/></td>
								<td class="headText" style="vertical-align: middle;"  width="50px">EB02</td>
								<td class="headText" style="vertical-align: middle;"  width="80px"><input type="text" name="ueb02Val" class="inputbox50Disabled" id="uEB02Id" disabled="disabled"/></td>
								<td class="headText" style="vertical-align: middle;"  width="100px"><input type="text" name="nveb02Val" class="inputbox30Disabled" id="nvEB02Id" disabled="disabled"/>&#160;</td>
								<td class="headText" style="vertical-align: middle;"  width="20px"><input type="checkbox" onclick="enableField('isUdtHSD01','uHSD01Id','nvHSD01Id')" name="isUdtHSD01" id="isUdtHSD01" value="udtHSD01"/></td>
								<td class="headText" style="vertical-align: middle;"  width="50px">HSD01</td>
								<td class="headText" style="vertical-align: middle;"  width="80px"><input type="text" name="uhsd01Val" class="inputbox50Disabled" id="uHSD01Id" disabled="disabled"/></td>
								<td class="headText" style="vertical-align: middle;"  width="100px"><input type="text" name="nvhsd01Val" class="inputbox30Disabled" id="nvHSD01Id" disabled="disabled"/>&#160;</td>
								<td class="headText" style="vertical-align: middle;"  width="20px"><input type="checkbox" onclick="enableField('isUdtHSD06','uHSD06Id','nvHSD06Id')" name="isUdtHSD06" id="isUdtHSD06" value="udtHSD06"/></td>
								<td class="headText" style="vertical-align: middle;"  width="50px">HSD06</td>
								<td class="headText" style="vertical-align: middle;"  width="80px"><input type="text" name="uhsd06Val" class="inputbox50Disabled" id="uHSD06Id" disabled="disabled"/></td>
								<td class="headText" style="vertical-align: middle;"  width="100px"><input type="text" name="nvhsd06Val" class="inputbox30Disabled" id="nvHSD06Id" disabled="disabled"/>&#160;</td>
						</tr>
						<tr height="20px" class="createEditTable1-Listdata" style="vertical-align: bottom;">
								<td class="headText" style="vertical-align: middle;"  width="20px"><input type="checkbox" onclick="enableEB03Update();" name="isUdtEB03" id="isUdtEB03" value="udtEB03"/></td>
								<td class="headText" style="vertical-align: middle;"  width="50px">EB03</td>
								<td class="headText" style="vertical-align: middle;"  width="80px"><input type="text" name="ueb03Val" class="inputbox50Disabled" id="uEB03Id" disabled="disabled"/></td>
								<td class="headText" style="vertical-align: middle;"  width="100px">
									<input type="text" name="nveb03Val" class="inputbox50Disabled" id="nvEB03Id" disabled="disabled"/>
								</td>
								<td class="headText" style="vertical-align: middle;"  width="20px"><input type="checkbox" onclick="enableField('isUdtHSD02','uHSD02Id','nvHSD02Id')" name="isUdtHSD02" id="isUdtHSD02" value="udtHSD02"/></td>
								<td class="headText" style="vertical-align: middle;"  width="50px">HSD02</td>
								<td class="headText" style="vertical-align: middle;"  width="80px"><input type="text" name="uhsd02Val" class="inputbox50Disabled" id="uHSD02Id" disabled="disabled"/></td>
								<td class="headText" style="vertical-align: middle;"  width="100px"><input type="text" name="nvhsd02Val" class="inputbox30Disabled" id="nvHSD02Id" disabled="disabled"/>&#160;</td>
								<td class="headText" style="vertical-align: middle;"  width="20px"><input type="checkbox" onclick="enableField('isUdtHSD07','uHSD07Id','nvHSD07Id')" name="isUdtHSD07" id="isUdtHSD07" value="udtHSD07"/></td>
								<td class="headText" style="vertical-align: middle;"  width="50px">HSD07</td>
								<td class="headText" style="vertical-align: middle;"  width="80px"><input type="text" name="uhsd07Val" class="inputbox50Disabled" id="uHSD07Id" disabled="disabled"/></td>
								<td class="headText" style="vertical-align: middle;"  width="100px"><input type="text" name="nvhsd07Val" class="inputbox30Disabled" id="nvHSD07Id" disabled="disabled"/></td>
						</tr>
						<tr height="20px" class="createEditTable1-Listdata" style="vertical-align: bottom;">
								<td class="headText" style="vertical-align: middle;"  width="20px"><input type="checkbox" onclick="enableField('isUdtEB06','uEB06Id','nvEB06Id')" name="isUdtEB06" id="isUdtEB06" value="udtEB06"/></td>
								<td class="headText" style="vertical-align: middle;"  width="50px">EB06</td>
								<td class="headText" style="vertical-align: middle;"  width="80px"><input type="text" name="ueb06Val" class="inputbox50Disabled" id="uEB06Id" disabled="disabled"/></td>
								<td class="headText" style="vertical-align: middle;"  width="100px"><input type="text" name="nveb06Val" class="inputbox30Disabled" id="nvEB06Id" disabled="disabled"/>&#160;</td>
								<td class="headText" style="vertical-align: middle;"  width="20px"><input type="checkbox" onclick="enableField('isUdtHSD03','uHSD03Id','nvHSD03Id')" name="isUdtHSD03" id="isUdtHSD03" value="udtHSD03"/></td>
								<td class="headText" style="vertical-align: middle;"  width="50px">HSD03</td>
								<td class="headText" style="vertical-align: middle;"  width="80px"><input type="text" name="uhsd03Val" class="inputbox50Disabled" id="uHSD03Id" disabled="disabled"/></td>
								<td class="headText" style="vertical-align: middle;"  width="100px"><input type="text" name="nvhsd03Val" class="inputbox30Disabled" id="nvHSD03Id" disabled="disabled"/>&#160;</td>
								
								<td class="headText" style="vertical-align: middle;"  width="20px"><input type="checkbox" onclick="enableField('isUdtHSD08','uHSD08Id','nvHSD08Id')" name="isUdtHSD08" id="isUdtHSD08" value="udtHSD08"/></td>
								<td class="headText" style="vertical-align: middle;"  width="50px">HSD08</td>
								<td class="headText" style="vertical-align: middle;"  width="80px"><input type="text" name="uhsd08Val" class="inputbox50Disabled" id="uHSD08Id" disabled="disabled"/></td>
								<td class="headText" style="vertical-align: middle;"  width="100px"><input type="text" name="nvhsd08Val" class="inputbox30Disabled" id="nvHSD08Id" disabled="disabled"/>&#160;</td>
						</tr>
						<tr height="20px" class="createEditTable1-Listdata" style="vertical-align: bottom;">
								<td class="headText" style="vertical-align: middle;"  width="20px"><input type="checkbox" onclick="enableField('isUdtEB09','uEB09Id','nvEB09Id')" name="isUdtEB09" id="isUdtEB09" value="udtEB09"/></td>
								<td class="headText" style="vertical-align: middle;"  width="50px">EB09</td>
								<td class="headText" style="vertical-align: middle;"  width="80px"><input type="text" name="ueb09Val" class="inputbox50Disabled" id="uEB09Id" disabled="disabled"/></td>
								<td class="headText" style="vertical-align: middle;"  width="100px"><input type="text" name="nveb09Val" class="inputbox30Disabled" id="nvEB09Id" disabled="disabled"/>&#160;</td>
								<td class="headText" style="vertical-align: middle;"  width="20px"><input type="checkbox" onclick="enableField('isUdtHSD04','uHSD04Id','nvHSD04Id')" name="isUdtHSD04" id="isUdtHSD04" value="udtHSD04"/></td>
								<td class="headText" style="vertical-align: middle;"  width="50px">HSD04</td>
								<td class="headText" style="vertical-align: middle;"  width="80px"><input type="text" name="uhsd04Val" class="inputbox50Disabled" id="uHSD04Id" disabled="disabled"/></td>
								<td class="headText" style="vertical-align: middle;"  width="100px"><input type="text" name="nvhsd04Val" class="inputbox30Disabled" id="nvHSD04Id" disabled="disabled"/>&#160;</td>
								<td class="headText" style="vertical-align: middle;"  width="20px"><input type="checkbox" onclick="enableField('isUdtnoteTypeCode','unoteTypeCodeId','nvnoteTypeCodeId')" name="isUdtnoteTypeCode" id="isUdtnoteTypeCode" value="udtnoteTypeCode"/></td>
								<td class="headText" style="vertical-align: middle;"  width="60px">Note Type</td>
								<td class="headText" style="vertical-align: middle;"  width="80px"><input type="text" name="unoteTypeCodeVal" class="inputbox50Disabled" id="unoteTypeCodeId" disabled="disabled"/></td>
								<td class="headText" style="vertical-align: middle;"  width="100px"><input type="text" name="nvnoteTypeCodeVal" class="inputbox30Disabled" id="nvnoteTypeCodeId" disabled="disabled"/>&#160;</td>
						</tr>
						<tr height="20px" class="createEditTable1-Listdata" style="vertical-align: bottom;">
						<td class="headText" style="vertical-align: middle;" colspan="2">&nbsp;</td>
							<td class="headText" style="vertical-align: middle; padding-left: 10px;" colspan="4">Current</td>
							<td class="headText" style="vertical-align: middle; padding-left: 10px;" colspan="4">New</td>
						</tr>
						<tr height="20px" class="createEditTable1-Listdata" style="vertical-align: bottom;">
								<td class="headText" style="vertical-align: middle;"  width="20px"><input type="checkbox" onclick="enableMsgField('isUdtMsgTxt','uMsgTxtId','nvMsgTxtId')" name="isUdtMsgTxt" id="isUdtMsgTxt" value="udtMsgTxt"/></td>
								<td class="headText" style="vertical-align: middle;"  width="50px">Message&#160;Text</td>
								<td class="headText" style="vertical-align: middle;"  colspan = 4>
									<input type="text" name="uMsgTxtVal" class="inputboxMsgDisabled" id="uMsgTxtId" disabled="disabled" size="45" />
								</td>
								<td class="headText" style="vertical-align: middle;"   colspan = 4>
									<input type="text" name="nvMsgTxtVal" class="inputboxMsgDisabled" id="nvMsgTxtId" disabled="disabled" size="45" />
								</td>
						</tr>
						
						<tr height="10px" class="createEditTable1-Listdata" style="vertical-align: bottom;">
								<td class="headText" style="vertical-align: middle;"  colspan="12">
								</td>
						</tr>		
					</TBODY>
				 </table>
		</div><!-- update div ends -->
							 
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
	<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
      <tr>
        <td width="20" height="20"><img src="${imageDir}/footer_left.gif" width="20" height="20" /></td>
		<td width="0" height="20"><a href="#" id="search" onclick="searchMessageText('1','Init')">Search</a></td>
		<td width="9" height="0" id="sepLnkSearchId"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>	
        <td width="0" height="20"><a href="${pageContext.request.contextPath}/viewlandingpage.html" >Back</a></td>
        <td width="9" height="0" id="sepLnkUpdateId" style="display: none;"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>	
        <td width="0" height="20" id="lnkUpdateId" style="vertical-align: middle;display: none;"><a href="#" onclick="checkLockstatus('${pageContext.request.contextPath}','update');" >Update</a></td>
        <c:if test="${userUIPermissions.authorizedToMarkAsNotApplicable}">
        <td width="9" height="0" id="sepLnkNotApplicableId" style="display: none;"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>	
        <td width="0" height="20" id="lnkNotApplicableId" style="vertical-align: middle;display: none;"><a href="#" onclick="checkLockstatus('${pageContext.request.contextPath}','notApplicable');" >Not Applicable</a></td>
        </c:if>
        <td width="9" height="0" id="sepLnkSendTestId" style="display: none;"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>	
        <td width="0" height="20" id="lnkSendTestId" style="vertical-align: middle;display: none;"><a href="#" onclick="checkLockstatus('${pageContext.request.contextPath}','sendToTest');" >Send to Test</a></td>
        <td width="9" height="0" id="sepLnkApproveId" style="display: none;"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
        <td width="0" height="20" id="lnkApproveId" style="vertical-align: middle;display: none;"><a href="#" onclick="checkLockstatus('${pageContext.request.contextPath}','approve');" >Approve</a></td>
       <c:if test="${userUIPermissions.authorizedToAuditLock}">
	        <td width="9" height="0" id="sepLnklockId" style="display: none;"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
	        <td width="0" height="20" id="lnklockId" style="vertical-align: middle;;display: none;"><a href="#" onclick="checkLockstatus('${pageContext.request.contextPath}','lock');" >Lock</a></td>
        </c:if>
        <c:if test="${userUIPermissions.authorizedToAuditUnlock}">
	        <td width="9" height="0" id="sepLnkunlockId" style="display: none;"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
	        <td width="0" height="20" id="lnkunlockId" style="vertical-align: middle;;display: none;"><a href="#" onclick="checkLockstatus('${pageContext.request.contextPath}','unlock');" >Unlock</a></td>
        </c:if>
		<td width="18" height="20"><img src="${imageDir}/footer_right.gif" width="18" height="20" /></td>
      </tr>
    </table>	    
</div>      
<div id="hippaCodePopUpDiv"></div>
<div id="ruleViewPopUpDiv"></div>
<div id="groupPagePopUpDiv"></div>
<div id="spsHoverPopUp" title="Locate Results"></div>
<div id="massUpdateConfirmationDiv">
	<div id = "commenterror" style="color: red; display: none;">
		*Please enter comment
	</div>
	<div id="muConformationTitle" style="padding-left: 5px; padding-bottom:10px; font-weight: bold;">
		
	</div>
	<div id="muConformationContent" style="overflow:auto; padding-left: 25px; padding-bottom:10px; font-weight: bold; width: auto; height: 100px;">
		
	</div>
	<div id="muConformationMsg">
		<textarea id="updateComments" name="updateComments"  rows="5" cols="80" ></textarea>
	</div>
	<div id="muConformationButton" align="center" style="padding-top: 10px; padding-bottom: 10px;">
		<a onclick="closeMassUpdateConfirmation('${pageContext.request.contextPath}','massupdateebx');" href="#" >
		<img id="sendMassUpdate" src="${imageDir}/confirm_but.png" alt="Confirm" title="Confirm" /></a>
	</div>
</div>
</div>
</form>
<script type="text/javascript">
	sniffer();
</script>
<div id="confirmationDiv"></div>
<div id="progressPopup" style="vertical-align: middle;display: none">
	<div id="progressbar"></div>
</div>
 
<div id="viewMappingDialog" title="View Mapping" width = auto></div>
<div id="confirmationDivUnlock"></div>
<div id="confirmationDivForContractSystem"></div>
<c:if test="${fromHistory == 'true'}">
<script type="text/javascript">
viewSearchHistory();
</script>
</c:if>
<c:if test="${fromPrint == 'true'}">
<script type="text/javascript">
viewSearchPrint();
</script>
</c:if>

<div id="userCommentsNotApplicableDialog" >
<form name="userCommentsNotApplicableForm" action="${context}/landingpagestateflow/markVariableAsNotApplicable.html" method="POST">
 <input type="hidden" name="notApplicablestateflowvariableId" id="notApplicablestateflowvariableId" value="" /> 
 <input type="hidden" name="notApplicablestateflowvariableDesc" id="notApplicablestateflowvariableDesc"	value="" />
 <input type="hidden" id="pageName" name="pageName" value="advanceSearch"/>
	<table id="NACommentsTable1" border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5 bT">          
	  <tr class="">
        <td ><textarea name="notApplicableMappingComments"  id="notApplicableMappingComments" 
		rows="5" cols="77"></textarea></td>
	  </tr> 			
	</table>
	<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
      <tr>       
        <td >
			<a
			href="#" onclick="saveUserCommentsForNotApplicable();"><img  id="notApplicableSaveImg" src="${imageDir}/save_but.gif" 
			alt="Save"  title="Save"/></a>
		</td>		
      </tr>
	</table>	
</form>
</div>
<div id="userCommentsDialog" >	
		 <table id="userCommentsTable1" border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5 bT">          
		  <tr class="">
	        <td ><textarea name="userMappingComments"
			id="userMappingComments" rows="5" cols="77"></textarea></td>
		  </tr> 			
		</table>
		<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
	      <tr>               
			<td >
				<a
				href="#" onclick="saveUserComments();"><img src="${imageDir}/save_but.gif" 
				alt="Save" title="Save"/></a>
			</td>				
	      </tr>
		</table>
	</div>
	<form action="${context}/advancesearch/generateExcelReport.ajax" name="printReportForm">
</form>	
<div id="lockusercomment" style="display:none;" >

	<%@include file = "/WEB-INF/jsp/lockUserCommentPopup.jsp" %>
</div>
<div id="varlockstatusPopUpDiv"></div>
<%@include file="/WEB-INF/jspf/whatIsThisToolTip.jspf"%>
<%@include file="/WEB-INF/jspf/messageTray.jspf"%>
</body>
</html>