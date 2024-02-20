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
	var autoPopulateUrl = "../ajaxautocomplete.ajax";
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/massUpdateUtils.js"></script>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="context" value="${pageContext.request.contextPath}"/>


<script type="text/javascript">
$(document).ready(function(){	
	$('#userCommentsNotApplicableRuleDialog').hide();
	$('#userCommentsNotApplicableSpsDialog').hide();
	$('#viewMappingForSpsDialog').hide();
	$('#viewMappingInProgressSpsDialog').hide();
	$('#viewMappingForRuleDialog').hide();
	$('#viewMappingInProgressRuleDialog').hide();
	$('#viewMappingCustomMessageDialog').hide();
	$('#userCommentsDialogForSps').hide();
	$('#userCommentsDialogForRule').hide();
	$('#userCommentsDialogForCustomMsg').hide();
	$('#userCommentsSent2TestDialogForSps').hide();
	$('#userCommentsApproveDialogForSps').hide();
	$('#userCommentsSent2TestDialogForRule').hide();
	$('#userCommentsApproveDialogForRule').hide();
	$('#userCommentsSent2TestDialogForCustomMsg').hide();
	$('#userCommentsApproveDialogForCustomMsg').hide();
	//Scrollbar not implemented
	$('#spsIdFrmLocate').blur(function() {
		$('#spsIdFrmLocate').val($('#spsIdFrmLocate').val().toUpperCase());			
	});
	$("#spsIdFrmLocate").autocomplete({ 
		source: function(request, response) {
					$.ajax({
						url: "${context}/ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term)+ "&name=SPSID",
						success: function(data) {
						cache: false,
						response(data.rows);
						}
					});
				}
	})
	
  });
	//Auto complete for msg sps id
	$(document).ready(function(){
	$('#spsIdFrmLocateMsg').blur(function() {
		$('#spsIdFrmLocateMsg').val($('#spsIdFrmLocateMsg').val().toUpperCase());			
	});
	$("#spsIdFrmLocateMsg").autocomplete({ 
		source: function(request, response) {
					$.ajax({
						url: "${context}/ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term)+ "&name=SPSID",
						success: function(data) {
						cache: false,
						response(data.rows);
						}
					});
				}
	})
	
  });
  
  $(document).ready(function(){	
	//Scrollbar not implemented
	$('#ruleIdFrmLocate').blur(function() {
		$('#ruleIdFrmLocate').val($('#ruleIdFrmLocate').val().toUpperCase());		
	});
	$("#ruleIdFrmLocate").autocomplete({ 
		source: function(request, response) {
					$.ajax({
						url: "${context}/ajaxautocomplete.ajax",
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
 //Auto complete for msg rule id
	$(document).ready(function(){	
	//Scrollbar not implemented
	$('#ruleIdFrmLocateMsg').blur(function() {
		$('#ruleIdFrmLocateMsg').val($('#ruleIdFrmLocateMsg').val().toUpperCase());		
	});
	$("#ruleIdFrmLocateMsg").autocomplete({ 
		source: function(request, response) {
					$.ajax({
						url: "${context}/ajaxautocomplete.ajax",
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

	var variableFormat = "";
	variableFormat = $("#variableFormat").val();
	var spsFormat = new Array();						
	spsFormat = getSPSformat();	
 $(document).ready(function(){	
	//Scrollbar not implemented
	$('#EB01Id').blur(function() {
		$('#EB01Id').val($('#EB01Id').val().toUpperCase());		
	});
	$("#EB01Id").autocomplete({ 
		source: function(request, response) {
		
					$.ajax({
						url: "${context}/ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term +"&name=EB01"+"&varformat="+variableFormat+"&spsFormat="+spsFormat,
						success: function(data) {
						cache: false,
						response(data.rows);
						}
					});
				}
	})
  });
  
$(document).ready(function(){	
	//Scrollbar not implemented
	$('#III02Id0').blur(function() {
		$('#III02Id0').val($('#III02Id0').val().toUpperCase());		
	});
	$("#III02Id0").autocomplete({ 
		source: function(request, response) {
					$.ajax({
						url: "${context}/ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term +"&name=III02"+"&varformat="+variableFormat+"&spsFormat="+spsFormat,
						success: function(data) {
						cache: false,
						response(data.rows);
						}
					});
				}
	})
  });
 
$(document).ready(function(){
	$('#NOTETYPEID0').blur(function() {
	   $('#NOTETYPEID0').val($('#NOTETYPEID0').val().toUpperCase());
	});
	$("#NOTETYPEID0").autocomplete({ 
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						// changed for reference data values
						data: "key="+request.term + "&name=NOTE_TYPE_CODE",
						success: function(data) {
						cache: false,
						response(data.rows);
						list = data.rows;
						}
					});
				}
	});
  });
  
 var fromHistory = false;
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
	
function generateReport(){
	document.forms['printReportForm'].action="${context}/advancesearchebx/generateExcelReport.ajax";
	document.forms['printReportForm'].submit();
}

	function sniffer() {
 		document.getElementById('container').style.paddingTop = "0px";
		document.getElementById('container').style.height = "76%";

		//alert(document.getElementById('advanceSearchInnerContainer').style.height+'');
		//ContentHeight = document.getElementById('advanceSearchInnerContainer').style.height-40;
		//document.getElementById('wrapper').style.height = "100%";
		//var height = parseInt(screen.height * 0.6);
		//document.getElementById("container").style.height= height+"px";
	  if(screen.height>864) 
	  {
		ContentHeight = 96;
//	     document.getElementById('container').style.height = "525px";	   
	   }else if(screen.height>768) 
	  {
		ContentHeight = 93;
//	     document.getElementById('container').style.height = "525px";	   
	   } 

	}
	
	function clearCreateOrLocateFields(fieldName, section) {
	 /*
      if (section == 'Locate') {
             if (fieldName == 'ruleIdFrmLocate') {
                   $('#spsIdFrmLocate').val('');       
                   $("#spsIdFrmLocate"). removeClass("inputbox65");
                   $("#spsIdFrmLocate"). addClass("inputboxDisabled");   
                   $("#ruleIdFrmLocate"). removeClass("inputboxDisabled");
                   $("#ruleIdFrmLocate"). addClass("inputbox65");
                   
                   $('#EB03Id0').val('');      
                   $("#EB03Id0"). removeClass("inputboxDisabled");
                   $("#EB03Id0"). addClass("inputbox60");
                   $("#EB03Id0").attr('disabled', false);
                   
                   $('#EB01Id').val('');      
                   $("#EB01Id"). removeClass("inputbox60");
                   $("#EB01Id"). addClass("inputboxDisabled");
                   $("#EB01Id").attr('disabled', true);
             }
             if (fieldName == 'spsIdFrmLocate') {
                   $('#ruleIdFrmLocate').val('');      
                   $("#ruleIdFrmLocate"). removeClass("inputbox65");
                   $("#ruleIdFrmLocate"). addClass("inputboxDisabled");
                   $("#spsIdFrmLocate"). removeClass("inputboxDisabled");
                   $("#spsIdFrmLocate"). addClass("inputbox65");   
                   
                   $('#EB03Id0').val('');      
                   $("#EB03Id0"). removeClass("inputbox60");
                   $("#EB03Id0"). addClass("inputboxDisabled");
                   $("#EB03Id0").attr('disabled', true);
                    
                   
                   $('#EB01Id').val('');      
                   $("#EB01Id"). removeClass("inputboxDisabled");
                   $("#EB01Id"). addClass("inputbox60");
                   $("#EB01Id").attr('disabled', false);
                   
             }
      }
      */
      
}
  
 function throwError(){
 	addErrorToNotificationTray('At least one search criteria is mandatory for search');
	openTrayNotification();
 }
function searchMessageTextHistory(){
	//alert("Inside history method");
	$.ajax({
			url: "${context}/advancesearchebx/advanceSearchHistory.ajax",
			dataType: "html",
			type: "POST",
			data: "",
			success: function(data) {
				//alert("data inside :"+data);
				$("#inSearchDivSub").html(data);
				//runAccordionSearch(1);
  				showUpdateTable($('#hdSpsIdCriteriaId').val());
				populateEbxUpdate($('input[name=searchRadio]:checked').val());
			}
		});
}
function validateUser(user){
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
}
function validateSpsId(spsid){
	
	if(spsid !="" ) {
		var alphanum=/^[0-9a-zA-Z]+$/; //This contains A to Z , 0 to 9 and A to B
		if(!alphanum.test(spsid)){
			addErrorToNotificationTray('Invalid Sps Id');
			openTrayNotification();
			return false;
		}
	}
}
function validateRuleId(ruleid){
	
	if(ruleid !="" ) {
		var alphanum=/^[0-9a-zA-Z]+$/; //This contains A to Z , 0 to 9 and A to B
		if(!alphanum.test(ruleid)){
			addErrorToNotificationTray('Invalid Rule Id');
			openTrayNotification();
			return false;
		}
	}
}

function validateEB01(eb01){
	
	if(eb01 !="" ) {
		var alphanum=/^[0-9a-zA-Z]+$/; //This contains A to Z , 0 to 9 and A to B
		if(!alphanum.test(eb01)){
			addErrorToNotificationTray('Invalid EB01');
			openTrayNotification();
			return false;
		}
	}
}
function validateEB03(eb03){
	
	if(eb03 !="" ) {
		var alphanum=/^[0-9a-zA-Z]+$/; //This contains A to Z , 0 to 9 and A to B
		if(!alphanum.test(eb03)){
			addErrorToNotificationTray('Invalid EB03');
			openTrayNotification();
			return false;
		}
	}
}

function validateAtleast(checkBoxes, textInputs, errorMsg){
	 
	 for (var j = 0; j < checkBoxes.length; j++){ 
         if($("#"+checkBoxes[j]).is(':checked') == true){
         	return true;
         } 
     }
     
     for (var i = 0; i < checkBoxes.length; i++){ 
         if(jQuery.trim($("#"+textInputs[i]).val()) != ''){
         	return true;
         } 
     }	
	addErrorToNotificationTray(errorMsg);
	openTrayNotification();	
	return false;   
}

function refreshSearch(){
	searchMessageText($('#currentPage').val(),'Refresh');
}

function searchMessageText(currentPageNo, page){
		var isUnMapped = "false";
		var isMapped = "false";
		var isNotApplicable = "false";		
		var isNotFinalized = "false";
		var spsIdToLocate;
		var eb01="";
		var ruleIdToLocate;
		var eb03="";
		var iii02="";
		var msgText;
		var noteType;

		var valueForSearchCriteria = "spsid";
		

		var searchCriteria = $('input[name=searchRadio]:checked').val();
		var authorizedToApprove  = $('#hdAuthorizedToApprove').val();
		//alert("authorizedToApprove"+authorizedToApprove);
		//alert("searchCriteria : "+searchCriteria);
		$('#currentPage').val(currentPageNo);
		if(page == 'Init'){
			if(searchCriteria == 'spsid'){
				//validation added
				if(validateAtleast(['isUnMapped','isMapped','isNotApplicable','isNotFinalized'],['spsIdFrmLocate','EB01Id','userId0'],'Atleast one search criteria is mandatory for search.') == false){
					return;
				}
				$('#hdSpsIdToLocateId').val($('#spsIdFrmLocate').val());
				$('#hdEb01Id').val($('#EB01Id').val());
			}
			if(searchCriteria == 'ruleid'){
				//validation added
				if(validateAtleast(['isUnMapped','isMapped','isNotApplicable'],['ruleIdFrmLocate','EB03Id0','userId0'],'Atleast one search criteria is mandatory for search.') == false){
					return;
				}
				$('#hdRuleIdFrmLocate').val($('#ruleIdFrmLocate').val());
				$('#hdEB03Id0').val($('#EB03Id0').val());
				$('#hdIII02Id0').val($('#III02Id0').val());
			}
			if(searchCriteria == 'msgtxt'){
				$('#hdNoteType').val($('#NOTETYPEID0').val());
				$('#hdMsgText').val($('#messageValueId').val());
				$('#hdSpsIdToLocateIdMsg').val($('#spsIdFrmLocateMsg').val());
				$('#hdRuleIdFrmLocateMsg').val($('#ruleIdFrmLocateMsg').val());				
			}
			$('#hdUserId').val($('#userId0').val());
			
			$('#hdSpsIdCriteriaId').val(searchCriteria);

			if ($('#isUnMapped').is(':checked') ){
				$('#hdIsUnMappedId').val("true");
			}else{
				$('#hdIsUnMappedId').val("false");
			}

			if ($('#isMapped').is(':checked') ){
				$('#hdIsMappedId').val("true");
			}else{
				$('#hdIsMappedId').val("false");
			}

			if ($('#isNotApplicable').is(':checked') ){
				$('#hdIsNotApplicableId').val("true");
			}else{
				$('#hdIsNotApplicableId').val("false");
			}
			if(searchCriteria == 'spsid'){
				if ($('#isNotFinalized').is(':checked') ){
					$('#hdIsNotFinalizedId').val("true");
				}else{
					$('#hdIsNotFinalizedId').val("false");
				}
			}
			//page = 'First';
		}
		if(searchCriteria == 'spsid'){
			spsIdToLocate = $('#hdSpsIdToLocateId').val();
			eb01 =  $('#hdEb01Id').val();
		}
		if(searchCriteria == 'ruleid'){
			ruleIdToLocate = $('#hdRuleIdFrmLocate').val();
			eb03 = $('#hdEB03Id0').val();
			iii02 = $('#hdIII02Id0').val();
		}
		if(searchCriteria == 'msgtxt'){
			noteType = $('#hdNoteType').val();
			msgText = $('#hdMsgText').val();
			spsIdToLocate = $('#hdSpsIdToLocateIdMsg').val();
			ruleIdToLocate = $('#hdRuleIdFrmLocateMsg').val();				
		}

		valueForSearchCriteria = $('#hdSpsIdCriteriaId').val();
		//alert("valueForSearchCriteria"+valueForSearchCriteria);
		isUnMapped = $('#hdIsUnMappedId').val();
		isMapped = $('#hdIsMappedId').val();
		isNotApplicable = $('#hdIsNotApplicableId').val();
		if(searchCriteria == 'spsid'){
			isNotFinalized = $('#hdIsNotFinalizedId').val();
		}
		
		//alert("Chked:"+$('#hdSpsIdCriteriaId').val()) 
		
		var userId = $('#hdUserId').val();
		if(validateUser(userId) == false){
			return;
		}
		//if(validateSpsId(spsIdToLocate) == false){
		//	return;
		//}
		//if(validateRuleId(ruleIdToLocate) == false){
		//	return;
		//}
		if(validateEB01(eb01) == false){
			return;
		}
	//	if(validateEB03(eb03) == false){
		//	return;
		//}
		if(isUnMapped == "true" && isMapped != "true" &&  isNotApplicable != "true" && isNotFinalized != "true" ) {
		if( ( eb01 != "") || (eb03 != "") || (userId !="")) {	
			addErrorToNotificationTray('Invaid Search Criteria');
			openTrayNotification();
			return false;
			}
		}
		var fromWhichSection = page;
		var currentPage = currentPageNo;
		
		
		$.ajax({
			url: "${context}/advancesearchebx/advanceSearch.ajax",
			dataType: "html",
			type: "POST",
			data: "spsIdToLocate="+escape(spsIdToLocate)+"&eb01="+eb01+"&isUnMapped="+isUnMapped+"&isMapped="+isMapped+"&isNotApplicable="+isNotApplicable+"&isNotFinalized="
					+isNotFinalized+"&valueForSearchCriteria="+valueForSearchCriteria+"&section="+fromWhichSection
					+"&currentPage="+currentPage+"&users="+userId+"&ruleIdToLocate="+escape(ruleIdToLocate)+"&eb03="+eb03+"&iii02="+iii02
					+"&noteType="+noteType+"&msgText="+msgText+"&authorizedToApprove="+authorizedToApprove,
			success: function(data) {
				//alert("data inside :"+data);
				$("#inSearchDivSub").html(data);
				//runAccordionSearch(1);
  				showUpdateTable($('#hdSpsIdCriteriaId').val());
			}
		});
		if(page == 'Init'){
			populateEbxUpdate(searchCriteria);
		}
}
function openViewPopForSpsAndRuleID(viewId,status,viewType,date) {
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
	var dataString;
	var titleString;
	titleString = viewId+' Created on '+date;
	if(status == "UNMAPPED"){
		dataString= "viewId="+escape(viewId)+ "&section=UNMAPPED"+"&viewType="+viewType;
	}else{
		dataString= "viewId="+escape(viewId)+ "&section=MAPPED"+"&viewType="+viewType;
	}
      $.ajax({
            url: "${context}/advancesearchebx/viewSpsAndRuleIdPopUp.ajax",
            dataType: "html",
			type: "POST",			
            data: dataString,
            success: function(data) {
                  $("#spsHoverPopUp").html(data);
                  $("#spsHoverPopUp").dialog({
                        height:'auto',	
						width:'580px',					
                        show:'clip',						
                        hide:'clip',					
                        //modal: true,
						resizable: false,
						title: titleString,
						position: [posx,posy+10]

                  });
                  $("#spsHoverPopUp").dialog();
            }
      });
	}
function openViewPopCustomMsg(spsId,ruleId,date) {
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
	var dataString = "spsId="+escape(spsId)+"&ruleId="+escape(ruleId);
      $.ajax({
            url: "${context}/advancesearchebx/viewCustomMessagePopUp.ajax",
            dataType: "html",
			type: "POST",			
            data: dataString,
            success: function(data) {
                  $("#spsHoverPopUp").html(data);
                  $("#spsHoverPopUp").dialog({
                        height:'auto',	
						width:'550',					
                        show:'clip',						
                        hide:'clip',					
                       // modal: true,
						resizable: false,
						title: "SPS ID - "+spsId+" Header Rule ID - "+ruleId +" Created On -"+date,
						position: [posx,posy+10]

                  });
                  $("#spsHoverPopUp").dialog();
            }
      });
}	
function editMappingForCustomMsg(ruleId, spsId){
            $("#selectedRuleIdForEditSub").val(ruleId);	
            $("#selectedSpsIdForEditSub").val(spsId);	
            document.forms['submitCreateForm'].action="${context}/createoreditcustommessagemapping/editCustomMessageMapping.html";		           
            document.forms["submitCreateForm"].submit();		
}
function editMappingForSps(spsId){
    $("#selectedSpsIdForEdit").val(jQuery.trim(spsId));	
    document.forms['submitCreateForm'].action="${context}/createoreditspsmapping/editSPSMapping.html";		           
    document.forms["submitCreateForm"].submit();		
}
function openCreateFromUnmappedSectionForSps(spsId){
		$("#spsIdHidden").val(jQuery.trim(spsId));		
		document.forms['submitCreateForm'].action="${context}/vieworcreatemapping/spsIdCreateFromUnmapped.html";
		document.forms['submitCreateForm'].submit();
}
function editMappingForRule(ruleId){
   $('#selectedruleForEdit').val(jQuery.trim(ruleId));	
   document.forms['submitCreateForm'].action="${context}/createoreditrulemapping/editRuleMapping.html";		           
   document.forms["submitCreateForm"].submit();		
}
function openCreateFromUnmappedSectionForRule(ruleId){
		$("#ruleIdHidden").val(jQuery.trim(ruleId));
		document.forms['submitCreateForm'].action="${context}/vieworcreatemapping/ruleIdCreateFromUnmapped.html";
		document.forms['submitCreateForm'].submit();
	}
function openUserCommentsSend2TestDialogForSps(spsId){
	
		$("#send2teststateflowspsId").val(jQuery.trim(spsId));
		$("#userCommentsSent2TestDialogForSps table#sendToTestUserCommentsTable1").css("border-top","1px solid black");
				$("#userCommentsSent2TestDialogForSps").dialog({
	                        height:'auto',
							width:'450px',	
							resizable:false,
	                        show:'slide',
							title: spsId +' - Send to test',
	                        modal: true
	                  });
			$("#userCommentsSent2TestDialogForSps").dialog();
	}
function saveUserCommentsForSent2TestForSps(){	
	if(!imposeMaxLength('send2TestMappingSpsComments' ,250,'user comments')){						
			return false;
		}					
		document.forms['userCommentsSent2TestSpsForm'].submit();
	}
function openUserCommentsSend2TestDialogForRule(ruleId){
	
		$("#send2teststateflowruleId").val(jQuery.trim(ruleId));
		$("#userCommentsSent2TestDialogForRule table#sendToTestUserCommentsTable1").css("border-top","1px solid black");
				$("#userCommentsSent2TestDialogForRule").dialog({
	                        height:'auto',
							width:'450px',	
							resizable:false,
	                        show:'slide',
							title: ruleId +' - Send to test',
	                        modal: true
	                  });
			$("#userCommentsSent2TestDialogForRule").dialog();
	}
function saveUserCommentsForSent2TestForRule(){	
	if(!imposeMaxLength('send2TestMappingRuleComments' ,250,'user comments')){						
			return false;
		}				
		document.forms['userCommentsSent2TestRuleForm'].submit();
	}

function openUserCommentsApproveDialogForSps(spsId){	

		$("#approvestateflowspsId").val(jQuery.trim(spsId));
		$("#userCommentsApproveDialogForSps table#approveUserCommentsTable1").css("border-top","1px solid black");
			$("#userCommentsApproveDialogForSps").dialog({
	                        height:'auto',
							width:'450px',	
							resizable:false,
	                        show:'slide',
							title: spsId +' - Approve',
	                        modal: true
	                  });
			$("#userCommentsApproveDialogForSps").dialog();
	}
function saveUserCommentsForApproveForSps(){
		if(!imposeMaxLength('approveMappingSpsComments' ,250,'user comments')){						
			return false;
		} 
		document.forms['userCommentsApproveSpsForm'].submit();
	}
function openUserCommentsApproveDialogForRule(ruleId){	

		$("#approvestateflowruleId").val(jQuery.trim(ruleId));
		$("#userCommentsApproveDialogForRule table#approveUserCommentsTable1").css("border-top","1px solid black");
			$("#userCommentsApproveDialogForRule").dialog({
	                        height:'auto',
							width:'450px',	
							resizable:false,
	                        show:'slide',
							title: ruleId +' - Approve',
	                        modal: true
	                  });
			$("#userCommentsApproveDialogForRule").dialog();
	}
function saveUserCommentsForApproveForRule(){
		 if(!imposeMaxLength('approvedMappingRuleComments' ,250,'user comments')){						
			return false;
		}
		document.forms['userCommentsApproveRuleForm'].submit();
	}
function openUserCommentsSend2TestDialogForCustomMsg(ruleId, spsId){
	
		$("#send2teststateflowruleIdCus").val(jQuery.trim(ruleId));
		$("#send2teststateflowspsIdCus").val(jQuery.trim(spsId));
		
		$("#userCommentsSent2TestDialogForCustomMsg table#sendToTestUserCommentsTable1").css("border-top","1px solid black");
				$("#userCommentsSent2TestDialogForCustomMsg").dialog({
	                        height:'auto',
							width:'450px',	
							resizable:false,
	                        show:'slide',
							title: ruleId + ' ' + spsId +' - Send to test',
	                        modal: true
	                  });
			$("#userCommentsSent2TestDialogForCustomMsg").dialog();
	}
function saveUserCommentsForSent2TestForCustomMsg(){	
	if(!imposeMaxLength('send2TestMappingCustomMsgComments' ,250,'user comments')){						
			return false;
		}				
		document.forms['userCommentsSent2TestCustomMsgForm'].submit();
	}
function openUserCommentsApproveDialogForCustomMsg(ruleId, spsId){	

		$("#approvestateflowruleIdCus").val(jQuery.trim(ruleId));
		$("#approvestateflowspsIdCus").val(jQuery.trim(spsId));
		$("#userCommentsApproveDialogForCustomMsg table#approveUserCommentsTable1").css("border-top","1px solid black");
			$("#userCommentsApproveDialogForCustomMsg").dialog({
	                        height:'auto',
							width:'450px',	
							resizable:false,
	                        show:'slide',
							title: ruleId + ' ' + spsId + ' - Approve',
	                        modal: true
	                  });
			$("#userCommentsApproveDialogForCustomMsg").dialog();
	}
function saveUserCommentsForApproveForCustomMsg(){
		 if(!imposeMaxLength('approvedMappingCustomMsgComments' ,250,'user comments')){						
			return false;
		}
		document.forms['userCommentsApproveCustomMsgForm'].submit();
	}

function viewSearchHistory(){
	//openAccordion = "Accordion1Content";
  	//flag = true;
  	//runAccordian = true;
	fromHistory = true;
	//runAccordionSearch(2);
	//document.getElementById('massUpdateContainer').style.display= 'block';
}

function unlockMappingFromLocate(ruleId, spsId, action, lockUserId){
	
	var warningMsgForUnlocking = "The mapping is locked by the user " + lockUserId + " . Do you want to unlock?";
	$("#confirmationDivUnlock").html(warningMsgForUnlocking);
	$("#confirmationDivUnlock").addClass("UnmappedVariables");
	var authorizedToApprove = $('#hdAuthorizedToApprove').val();
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
					if(action=='unlockRuleFromAdvanceSearch') {
						$.ajax({
							url: "${context}/stateflowewpdbx/unlockRuleMapping.ajax",
							dataType: "json",
							type: "POST",			
							data: "ruleId="+escape(ruleId)+"&authorizedToApprove="+authorizedToApprove,
							success: function(data) {
							cache: false,
							handleUnlock(data, 'unlockRuleFromLocate');}
						});
					}else if(action=='unlockSpsFromAdvanceSearch') {
						$.ajax({
							url: "${context}/stateflowewpdbx/unlockSpsMapping.ajax",
							dataType: "json",
							type: "POST",
							data: "spsId="+escape(spsId)+"&authorizedToApprove="+authorizedToApprove,			
							success: function(data) {
							cache: false,
							handleUnlock(data, 'unlockSpsFromLocate');}
						});
					}else if(action=='unlockCustomMsgFromAdvanceSearch') {
						$.ajax({
							url: "${context}/stateflowewpdbx/unlockCustomMsgMapping.ajax",
							dataType: "json",
							type: "POST",			
							data: "spsId="+escape(spsId)+"&ruleId="+escape(ruleId)+"&authorizedToApprove="+authorizedToApprove,
							success: function(data) {
							cache: false,
							handleUnlock(data, 'unlockCustomMsgFromLocate');}
						});
					}				
				}
			}
		});
		$("#confirmationDivUnlock").dialog('open');	
		
	}
function handleUnlock(data, fromAction) {
		addMessages(data);
					
		if(data.ruleId != null && data.spsId == null){		
			handleIconsWhileUnlockFromLanding(data.ruleId,'',data.mapping.user.lastUpdatedUserName, data.authorizedToApprove, 'rule');		  
		}
		else if(data.spsId != null && data.ruleId == null){			
			handleIconsWhileUnlockFromLanding('',data.spsId,data.mapping.user.lastUpdatedUserName, data.authorizedToApprove, 'sps');			
		}
		else if(data.ruleId != null && data.spsId != null){
			handleIconsWhileUnlockFromLanding(data.ruleId,data.spsId, data.mapping.user.lastUpdatedUserName, data.authorizedToApprove, 'customMessage'); 
			
		}
	
	}
function handleIconsWhileUnlockFromLanding(ruleId, SpsId, userId, permissionToApprove, action) {		
		
		var editFunction = "";
		var userCmntsTest = "";
		var userCmntsApprove = "";
		var editComp = "";
		var sendtotestComp = "";
		var approveComp = "";
		var markAsNotApp = ""
		var markAsNotAppComp = "";
		
		if(action == 'rule'){		
			editFunction = "editMappingForRule";
			userCmntsTest = "openUserCommentsSend2TestDialogForRule";
			userCmntsApprove = "openUserCommentsApproveDialogForRule";
			markAsNotApp = "openUserCommentsNotApplicableDialogForSps";
			
			editComp = '<a href = "#" id="'+ruleId+'_Edit" onclick="'+editFunction+'(\''+ruleId+'\');"><img src="${imageDir}/edit_icon.gif" alt="Edit" title="Edit" style="height:15px;"/></a>';
			$('#'+ruleId+'_Lock').replaceWith(editComp);
			$('#'+ruleId+'_Edit').append("&#160;&#160;");	

			sendtotestComp = '<a href = "#" id="'+ruleId+'_Test" onclick="'+userCmntsTest+'(\''+ruleId+'\');"><img src="${imageDir}/test_icon.gif" alt="Send to Test" title="Send to Test" style="height:15px;"/></a>';
			$('#'+ruleId+'_Edit').after(sendtotestComp);
			$('#'+ruleId+'_Test').append("&#160;&#160;");
			
			if(permissionToApprove){		
				approveComp = '<a href = "#" id="'+ruleId+'_Approve" onclick="'+userCmntsApprove+'(\''+ruleId+'\');"><img src="${imageDir}/approve_icon.gif" alt="Approve" title="Approve" style="height:15px;"/></a>';
				$('#'+ruleId+'_Test').after(approveComp);
			}
		}	
		if(action == 'sps'){			
			editFunction = "editMappingForSps";
			userCmntsTest = "openUserCommentsSend2TestDialogForSps";
			userCmntsApprove = "openUserCommentsApproveDialogForSps";
			markAsNotApp = "openUserCommentsNotApplicableDialogForSps";

			editComp = '<a href = "#" id="'+SpsId+'_Edit" onclick="'+editFunction+'(\''+SpsId+'\');"><img src="${imageDir}/edit_icon.gif" alt="Edit" title="Edit" style="height:15px;"/></a>';
			$('#'+SpsId+'_Lock').replaceWith(editComp);
			$('#'+SpsId+'_Edit').append("&#160;&#160;");

			sendtotestComp = '<a href = "#" id="'+SpsId+'_Test" onclick="'+userCmntsTest+'(\''+SpsId+'\');"><img src="${imageDir}/test_icon.gif" alt="Send to Test" title="Send to Test" style="height:15px;"/></a>';
			$('#'+SpsId+'_Edit').after(sendtotestComp);
			$('#'+SpsId+'_Test').append("&#160;&#160;");
			
			if(permissionToApprove){		
				approveComp = '<a href = "#" id="'+SpsId+'_Approve" onclick="'+userCmntsApprove+'(\''+SpsId+'\');"><img src="${imageDir}/approve_icon.gif" alt="Approve" title="Approve" style="height:15px;"/></a>';
				$('#'+SpsId+'_Test').after(approveComp);
			}
		}
		if(action == 'customMessage'){			
			editFunction = "editMappingForCustomMsg";
			userCmntsTest = "openUserCommentsSend2TestDialogForCustomMsg";
			userCmntsApprove = "openUserCommentsApproveDialogForCustomMsg";
			
			editComp = '<a href = "#" id="'+ruleId+SpsId+'_Edit" onclick="'+editFunction+'(\''+ruleId+'\', \''+SpsId+'\');"><img src="${imageDir}/edit_icon.gif" alt="Edit" title="Edit" style="height:15px;"/></a>';
			$('#'+ruleId+SpsId+'_Lock').replaceWith(editComp);
			$('#'+ruleId+SpsId+'_Edit').append("&#160;&#160;");		
			
			sendtotestComp = '<a href = "#" id="'+ruleId+SpsId+'_Test" onclick="'+userCmntsTest+'(\''+ruleId+'\', \''+SpsId+'\');"><img src="${imageDir}/test_icon.gif" alt="Send to Test" title="Send to Test" style="height:15px;"/></a>';
			$('#'+ruleId+SpsId+'_Edit').after(sendtotestComp);
			$('#'+ruleId+SpsId+'_Test').append("&#160;&#160;");
			
			if(permissionToApprove){		
				approveComp = '<a href = "#" id="'+ruleId+SpsId+'_Approve" onclick="'+userCmntsApprove+'(\''+ruleId+'\', \''+SpsId+'\');"><img src="${imageDir}/approve_icon.gif" alt="Approve" title="Approve" style="height:15px;"/></a>';
				$('#'+ruleId+SpsId+'_Test').after(approveComp);
			}
		}		
	}	
</script>
<script type="text/javascript">
var ContentHeight = 90;
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
function showHideTextBox(fieldName, section, source) {
	  var hdFromHistory = $('#hdFromHistory').val();
		//alert("hdFromHistory"+hdFromHistory);
	  if(hdFromHistory == 'true' && source == "0"){
		var searchCriteria = $('#hdSpsIdCriteriaId').val();
		//alert("searchCriteria jjj"+searchCriteria);
	
      if (searchCriteria == 'spsid') {
						document.forms['submitCreateForm'].searchRadio[0].checked = true;
                        //Disables Rule Box
                        $('#ruleIdFrmLocate').val('');      
                        $("#ruleIdFrmLocate"). removeClass("inputbox60");
                        $("#ruleIdFrmLocate").attr('disabled', true);
                        $("#ruleIdFrmLocate"). addClass("inputboxDisabled1");
						
						//Disables Msg Rule Id Box
                        $('#ruleIdFrmLocateMsg').val('');      
                        $("#ruleIdFrmLocateMsg"). removeClass("inputbox60");
                        $("#ruleIdFrmLocateMsg").attr('disabled', true);
                        $("#ruleIdFrmLocateMsg"). addClass("inputboxDisabled1");
						
						//Disables Msg Sps Id Box
                        $('#spsIdFrmLocateMsg').val('');      
                        $("#spsIdFrmLocateMsg"). removeClass("inputbox60");
                        $("#spsIdFrmLocateMsg").attr('disabled', true);
                        $("#spsIdFrmLocateMsg"). addClass("inputboxDisabled1");
                        
                         //Disables EBO3 Box
                        $('#EB03Id0').val('');      
                        $("#EB03Id0"). removeClass("inputbox60");
                        $("#EB03Id0").attr('disabled', true);
                        $("#EB03Id0"). addClass("inputboxDisabled1");
                        
                         //Disables Msg Txt Box
                        $('#messageValueId').val('');      
                        $("#messageValueId"). removeClass("inputbox60");
                        $("#messageValueId").attr('disabled', true);
                        $("#messageValueId"). addClass("inputboxDisabled1");
                        
                         //Disables Note Type Box
                        $('#NOTETYPEID0').val('');      
                        $("#NOTETYPEID0"). removeClass("inputbox60");
                        $("#NOTETYPEID0").attr('disabled', true);
                        $("#NOTETYPEID0"). addClass("inputboxDisabled1");
                        
                       //Enables SPS Box
						var spsId = $('#hdSpsIdToLocateId').val();
                       	$('#spsIdFrmLocate').val(spsId);
                        $("#spsIdFrmLocate"). removeClass("inputboxDisabled1");
                        $("#spsIdFrmLocate"). addClass("inputbox60"); 
                        $("#spsIdFrmLocate").attr('disabled', false);
                        
                        //Enables EBO1 Box
						var eb01 = $('#hdEb01Id').val();
                        $('#EB01Id').val(eb01);
                        $("#EB01Id"). removeClass("inputboxDisabled1");
                        $("#EB01Id"). addClass("inputbox60"); 
                        $("#EB01Id").attr('disabled', false);
                        
                                       
                         //Enables Unmapped check box
						var isUnMapped = $('#hdIsUnMappedId').val();
                        $('#isUnMapped').val('');
                        $("#isUnMapped").attr('disabled', false);
						if(isUnMapped == "true"){
							 $("#isUnMapped").attr('checked', true);
						}else{
							$("#isUnMapped").attr('checked', false);
						}
                        
                         //Enables Mapped check box
						var isMapped = $('#hdIsMappedId').val();
                        $('#isMapped').val('');
                        $("#isMapped").attr('disabled', false);
						if(isMapped == "true"){
							 $("#isMapped").attr('checked', true);
						}else{
							$("#isMapped").attr('checked', false);
						}
                        
                         //Enables Not Applicable check box
						var isNotApplicable = $('#hdIsNotApplicableId').val();
                        $('#isNotApplicable').val('');
                        $("#isNotApplicable").attr('disabled', false);                 
                       	
						if(isNotApplicable == "true"){
							 $("#isNotApplicable").attr('checked', true);
						}else{
							$("#isNotApplicable").attr('checked', false);
						}
                        
                         //Enables Not Finalised check box
						var isNotFinalized = $('#hdIsNotFinalizedId').val();
                        $('#isNotFinalized').val('');
                        $("#isNotFinalized").attr('disabled', false);
						if(isNotFinalized == "true"){
							$("#isNotFinalized").attr('checked', true);
						}else{
							$("#isNotFinalized").attr('checked', false);
						}
						var user = $('#hdUserId').val();
						$('#userId0').val(user);

                        
                }
      if (searchCriteria == 'ruleid') {
																	
						document.forms['submitCreateForm'].searchRadio[1].checked = true;

      					//Disables SPS ID Box
                        $('#spsIdFrmLocate').val('');      
                        $("#spsIdFrmLocate"). removeClass("inputbox60");
                        $("#spsIdFrmLocate").attr('disabled', true);
                        $("#spsIdFrmLocate"). addClass("inputboxDisabled1");

						//Disables Msg Rule Id Box
                        $('#ruleIdFrmLocateMsg').val('');      
                        $("#ruleIdFrmLocateMsg"). removeClass("inputbox60");
                        $("#ruleIdFrmLocateMsg").attr('disabled', true);
                        $("#ruleIdFrmLocateMsg"). addClass("inputboxDisabled1");
						
						//Disables Msg Sps Id Box
                        $('#spsIdFrmLocateMsg').val('');      
                        $("#spsIdFrmLocateMsg"). removeClass("inputbox60");
                        $("#spsIdFrmLocateMsg").attr('disabled', true);
                        $("#spsIdFrmLocateMsg"). addClass("inputboxDisabled1");
                        
      					//Disables EB01 Box
                        $('#EB01Id').val('');      
                        $("#EB01Id"). removeClass("inputbox60");
                        $("#EB01Id").attr('disabled', true);
                        $("#EB01Id"). addClass("inputboxDisabled1");
                        
                         //Disables Msg Txt Box
                        $('#messageValueId').val('');      
                        $("#messageValueId"). removeClass("inputbox60");
                        $("#messageValueId").attr('disabled', true);
                        $("#messageValueId"). addClass("inputboxDisabled1");
                        
                         //Disables Note Type Box
                        $('#NOTETYPEID0').val('');      
                        $("#NOTETYPEID0"). removeClass("inputbox60");
                        $("#NOTETYPEID0").attr('disabled', true);
                        $("#NOTETYPEID0"). addClass("inputboxDisabled1");
                        
                         //Disables Not Finalised check box 
                        $('#isNotFinalized').val('');      
                        $("#isNotFinalized").attr('disabled', true);
                        
                                         
                        //Enables RULE Box
						var ruleId = $('#hdRuleIdFrmLocate').val();
                        $('#ruleIdFrmLocate').val(ruleId);
                        $("#ruleIdFrmLocate"). removeClass("inputboxDisabled1");
                        $("#ruleIdFrmLocate"). addClass("inputbox60"); 
                        $("#ruleIdFrmLocate").attr('disabled', false);
                        
                        //Enables EBO3 Box
						var eb03 = $('#hdEB03Id0').val();
                        $('#EB03Id0').val(eb03);
                        $("#EB03Id0"). removeClass("inputboxDisabled1");
                        $("#EB03Id0"). addClass("inputbox60"); 
                        $("#EB03Id0").attr('disabled', false);
                        
                         //Enables III02 Box
						var iii02 = $('#hdIII02Id0').val();
                        $('#III02Id0').val(iii02);
                        $("#III02Id0"). removeClass("inputboxDisabled1");
                        $("#III02Id0"). addClass("inputbox60"); 
                        $("#III02Id0").attr('disabled', false);
                        
                                               
                        //Enables Unmapped check box
                        var isUnMapped = $('#hdIsUnMappedId').val();
                        $('#isUnMapped').val('');
                        $("#isUnMapped").attr('disabled', false);
						if(isUnMapped == "true"){
							 $("#isUnMapped").attr('checked', true);
						}else{
							$("#isUnMapped").attr('checked', false);
						}
                        
                        //Enables Mapped check box
                        var isMapped = $('#hdIsMappedId').val();
                        $('#isMapped').val('');
                        $("#isMapped").attr('disabled', false);
						if(isMapped == "true"){
							 $("#isMapped").attr('checked', true);
						}else{
							$("#isMapped").attr('checked', false);
						}
                        
                         //Enables Not Applicable check box
						var isNotApplicable = $('#hdIsNotApplicableId').val();
                        $('#isNotApplicable').val('');
                        $("#isNotApplicable").attr('disabled', false);                 
                       	
						if(isNotApplicable == "true"){
							 $("#isNotApplicable").attr('checked', true);
						}else{
							$("#isNotApplicable").attr('checked', false);
						}
						
						var user = $('#hdUserId').val();
						$('#userId0').val(user);
                                               
                }    
      if (searchCriteria == 'msgtxt') {
						document.forms['submitCreateForm'].searchRadio[2].checked = true;
						//Disables SPS ID Box
                        $('#spsIdFrmLocate').val('');      
                        $("#spsIdFrmLocate"). removeClass("inputbox60");
                        $("#spsIdFrmLocate").attr('disabled', true);
                        $("#spsIdFrmLocate"). addClass("inputboxDisabled1");
						//Disables Rule Box
                        $('#ruleIdFrmLocate').val('');      
                        $("#ruleIdFrmLocate"). removeClass("inputbox60");
                        $("#ruleIdFrmLocate").attr('disabled', true);
                        $("#ruleIdFrmLocate"). addClass("inputboxDisabled1");
                        //Disables EB01 Box
                        $('#EB01Id').val('');      
                        $("#EB01Id"). removeClass("inputbox60");
                        $("#EB01Id").attr('disabled', true);
                        $("#EB01Id"). addClass("inputboxDisabled1");
                        
                         //Disables EBO3 Box
                        $('#EB03Id0').val('');      
                        $("#EB03Id0"). removeClass("inputbox60");
                        $("#EB03Id0").attr('disabled', true);
                        $("#EB03Id0"). addClass("inputboxDisabled1");
                        
                         //Disables III02 Box
                        $('#III02Id0').val('');      
                        $("#III02Id0"). removeClass("inputbox60");
                        $("#III02Id0").attr('disabled', true);
                        $("#III02Id0"). addClass("inputboxDisabled1");
                        
                        //Disables Unmapped check box 
                        $('#isUnMapped').val('');
                        $("#isUnMapped").attr('checked', false);      
                        $("#isUnMapped").attr('disabled', true);
                        
                        //Disables Mapped check box 
                        $('#isMapped').val('');
                        $("#isMapped").attr('checked', false);      
                        $("#isMapped").attr('disabled', true);
                        
                        //Disables Not Applicable check box 
                        $('#isNotApplicable').val('');
                        $("#isNotApplicable").attr('checked', false);      
                        $("#isNotApplicable").attr('disabled', true);
                        
                         //Disables In Progress check box 
                        $('#isInProgress').val('');      
                        $("#isInProgress").attr('disabled', true);
                        
                        //Disables Not Finalised check box 
                        $('#isNotFinalized').val('');
                        $("#isNotFinalized").attr('checked', false);      
                        $("#isNotFinalized").attr('disabled', true);
                        
                        //Enables Msg SPS Box
						var spsId = $('#hdSpsIdToLocateIdMsg').val();
                       	$('#spsIdFrmLocateMsg').val(spsId);
                        $("#spsIdFrmLocateMsg"). removeClass("inputboxDisabled1");
                        $("#spsIdFrmLocateMsg"). addClass("inputbox60"); 
                        $("#spsIdFrmLocateMsg").attr('disabled', false);
                        
                        //Enables Msg RULE Box
						var ruleId = $('#hdRuleIdFrmLocateMsg').val();
                        $('#ruleIdFrmLocateMsg').val(ruleId);
                        $("#ruleIdFrmLocateMsg"). removeClass("inputboxDisabled1");
                        $("#ruleIdFrmLocateMsg"). addClass("inputbox60"); 
                        $("#ruleIdFrmLocateMsg").attr('disabled', false);
                        
                        //Enables Note Type Box
                        $('#NOTETYPEID0').val('');
                        $("#NOTETYPEID0"). removeClass("inputboxDisabled1");
                        $("#NOTETYPEID0"). addClass("inputbox60"); 
                        $("#NOTETYPEID0").attr('disabled', false);
                        
                        //Enables Msg Txt Box
						var msgText = $('#hdMsgText').val();
                        $('#messageValueId').val(msgText);
                        $("#messageValueId"). removeClass("inputboxDisabled1");
                        $("#messageValueId"). addClass("inputbox60"); 
                        $("#messageValueId").attr('disabled', false);
                        
                        //Enables USER Box
                       	$('#userId').val('');
                        $("#userId"). removeClass("inputboxDisabled1");
                        $("#userId"). addClass("inputbox60"); 
                        $("#userId").attr('disabled', false);
						
						var user = $('#hdUserId').val();
						$('#userId0').val(user);
                }    
		
	  }else{
	
      if (section == 'spsid') {
                        //Disables Rule Box
                        $('#ruleIdFrmLocate').val('');      
                        $("#ruleIdFrmLocate"). removeClass("inputbox60");
                        $("#ruleIdFrmLocate").attr('disabled', true);
                        $("#ruleIdFrmLocate"). addClass("inputboxDisabled1");

						//Disables Msg Rule Id Box
                        $('#ruleIdFrmLocateMsg').val('');      
                        $("#ruleIdFrmLocateMsg"). removeClass("inputbox60");
                        $("#ruleIdFrmLocateMsg").attr('disabled', true);
                        $("#ruleIdFrmLocateMsg"). addClass("inputboxDisabled1");
						
						//Disables Msg Sps Id Box
                        $('#spsIdFrmLocateMsg').val('');      
                        $("#spsIdFrmLocateMsg"). removeClass("inputbox60");
                        $("#spsIdFrmLocateMsg").attr('disabled', true);
                        $("#spsIdFrmLocateMsg"). addClass("inputboxDisabled1");
                        
                         //Disables EBO3 Box
                        $('#EB03Id0').val('');      
                        $("#EB03Id0"). removeClass("inputbox60");
                        $("#EB03Id0").attr('disabled', true);
                        $("#EB03Id0"). addClass("inputboxDisabled1");
                        
                         //Disables III02 Box
                        $('#III02Id0').val('');      
                        $("#III02Id0"). removeClass("inputbox60");
                        $("#III02Id0").attr('disabled', true);
                        $("#III02Id0"). addClass("inputboxDisabled1");
                        
                         //Disables Msg Txt Box
                        $('#messageValueId').val('');      
                        $("#messageValueId"). removeClass("inputbox60");
                        $("#messageValueId").attr('disabled', true);
                        $("#messageValueId"). addClass("inputboxDisabled1");
                        
                         //Disables Note Type Box
                        $('#NOTETYPEID0').val('');      
                        $("#NOTETYPEID0"). removeClass("inputbox60");
                        $("#NOTETYPEID0").attr('disabled', true);
                        $("#NOTETYPEID0"). addClass("inputboxDisabled1");
                        
                       //Enables SPS Box
                       	$('#spsIdFrmLocate').val('');
                        $("#spsIdFrmLocate"). removeClass("inputboxDisabled1");
                        $("#spsIdFrmLocate"). addClass("inputbox60"); 
                        $("#spsIdFrmLocate").attr('disabled', false);
                        
                        //Enables EBO1 Box
                        $('#EB01Id').val('');
                        $("#EB01Id"). removeClass("inputboxDisabled1");
                        $("#EB01Id"). addClass("inputbox60"); 
                        $("#EB01Id").attr('disabled', false);
                        
                                       
                         //Enables Unmapped check box
                        $('#isUnMapped').val('');
                        $("#isUnMapped").attr('disabled', false);
                        $("#isUnMapped").attr('checked', true);

                         //Enables Mapped check box
                        $('#isMapped').val('');
                        $("#isMapped").attr('disabled', false);
						$("#isMapped").attr('checked', true);
                        
                         //Enables Not Applicable check box
                        $('#isNotApplicable').val('');
                        $("#isNotApplicable").attr('disabled', false); 
						$("#isNotApplicable").attr('checked', true);                
                       
                        
                         //Enables Not Finalised check box
                        $('#isNotFinalized').val('');
                        $("#isNotFinalized").attr('disabled', false);
						$("#isNotFinalized").attr('checked', true);  
                        
                }
      if (section == 'ruleid') {
      
      					//Disables SPS ID Box
                        $('#spsIdFrmLocate').val('');      
                        $("#spsIdFrmLocate"). removeClass("inputbox60");
                        $("#spsIdFrmLocate").attr('disabled', true);
                        $("#spsIdFrmLocate"). addClass("inputboxDisabled1");

						//Disables Msg Rule Id Box
                        $('#ruleIdFrmLocateMsg').val('');      
                        $("#ruleIdFrmLocateMsg"). removeClass("inputbox60");
                        $("#ruleIdFrmLocateMsg").attr('disabled', true);
                        $("#ruleIdFrmLocateMsg"). addClass("inputboxDisabled1");
						
						//Disables Msg Sps Id Box
                        $('#spsIdFrmLocateMsg').val('');      
                        $("#spsIdFrmLocateMsg"). removeClass("inputbox60");
                        $("#spsIdFrmLocateMsg").attr('disabled', true);
                        $("#spsIdFrmLocateMsg"). addClass("inputboxDisabled1");
                        
      					//Disables EB01 Box
                        $('#EB01Id').val('');      
                        $("#EB01Id"). removeClass("inputbox60");
                        $("#EB01Id").attr('disabled', true);
                        $("#EB01Id"). addClass("inputboxDisabled1");
                        
                         //Disables Msg Txt Box
                        $('#messageValueId').val('');      
                        $("#messageValueId"). removeClass("inputbox60");
                        $("#messageValueId").attr('disabled', true);
                        $("#messageValueId"). addClass("inputboxDisabled1");
                        
                         //Disables Note Type Box
                        $('#NOTETYPEID0').val('');      
                        $("#NOTETYPEID0"). removeClass("inputbox60");
                        $("#NOTETYPEID0").attr('disabled', true);
                        $("#NOTETYPEID0"). addClass("inputboxDisabled1");
                        
                         //Disables Not Finalised check box 
                        $('#isNotFinalized').val('');      
                        $("#isNotFinalized").attr('disabled', true);
                        
                                         
                        //Enables RULE Box
                        $('#ruleIdFrmLocate').val('');
                        $("#ruleIdFrmLocate"). removeClass("inputboxDisabled1");
                        $("#ruleIdFrmLocate"). addClass("inputbox60"); 
                        $("#ruleIdFrmLocate").attr('disabled', false);
                        
                        //Enables EBO3 Box
                        $('#EB03Id0').val('');
                        $("#EB03Id0"). removeClass("inputboxDisabled1");
                        $("#EB03Id0"). addClass("inputbox60"); 
                        $("#EB03Id0").attr('disabled', false);
                        
                        //Enables III02 Box
                        $('#III02Id0').val('');
                        $("#III02Id0"). removeClass("inputboxDisabled1");
                        $("#III02Id0"). addClass("inputbox60"); 
                        $("#III02Id0").attr('disabled', false);
                                               
                        //Enables Unmapped check box
                        $('#isUnMapped').val('');
                        $("#isUnMapped").attr('disabled', false);
                        $("#isUnMapped").attr('checked', true); 

                        //Enables Mapped check box
                        $('#isMapped').val('');
                        $("#isMapped").attr('disabled', false);
                        $("#isMapped").attr('checked', true);

						//Enables Not Applicable check box
                        $('#isNotApplicable').val('');
                        $("#isNotApplicable").attr('disabled', false); 
						$("#isNotApplicable").attr('checked', true);  

                                                                       
                }    
      if (section == 'msgtxt') {
						//Disables SPS ID Box
                        $('#spsIdFrmLocate').val('');      
                        $("#spsIdFrmLocate"). removeClass("inputbox60");
                        $("#spsIdFrmLocate").attr('disabled', true);
                        $("#spsIdFrmLocate"). addClass("inputboxDisabled1");
						//Disables Rule Box
                        $('#ruleIdFrmLocate').val('');      
                        $("#ruleIdFrmLocate"). removeClass("inputbox60");
                        $("#ruleIdFrmLocate").attr('disabled', true);
                        $("#ruleIdFrmLocate"). addClass("inputboxDisabled1");
                        //Disables EB01 Box
                        $('#EB01Id').val('');      
                        $("#EB01Id"). removeClass("inputbox60");
                        $("#EB01Id").attr('disabled', true);
                        $("#EB01Id"). addClass("inputboxDisabled1");
                        
                         //Disables EBO3 Box
                        $('#EB03Id0').val('');      
                        $("#EB03Id0"). removeClass("inputbox60");
                        $("#EB03Id0").attr('disabled', true);
                        $("#EB03Id0"). addClass("inputboxDisabled1");
                        
                         //Disables III02 Box
                        $('#III02Id0').val('');      
                        $("#III02Id0"). removeClass("inputbox60");
                        $("#III02Id0").attr('disabled', true);
                        $("#III02Id0"). addClass("inputboxDisabled1");
                        
                        //Disables Unmapped check box 
                        $('#isUnMapped').val('');
                        $("#isUnMapped").attr('checked', false);   
                        $("#isUnMapped").attr('disabled', true);
                        
                        //Disables Mapped check box 
                        $('#isMapped').val('');
                        $("#isMapped").attr('checked', false);
                        $("#isMapped").attr('disabled', true);
                        
                        //Disables Not Applicable check box 
                        $('#isNotApplicable').val('');
                        $("#isNotApplicable").attr('checked', false);      
                        $("#isNotApplicable").attr('disabled', true);                        
                                  
                        //Disables Not Finalised check box 
                        $('#isNotFinalized').val('');
                        $("#isNotFinalized").attr('checked', false);      
                        $("#isNotFinalized").attr('disabled', true);
                        
                        //Enables SPS Box
                       	$('#spsIdFrmLocateMsg').val('');
                        $("#spsIdFrmLocateMsg"). removeClass("inputboxDisabled1");
                        $("#spsIdFrmLocateMsg"). addClass("inputbox60"); 
                        $("#spsIdFrmLocateMsg").attr('disabled', false);
                        
                        //Enables RULE Box
                        $('#ruleIdFrmLocateMsg').val('');
                        $("#ruleIdFrmLocateMsg"). removeClass("inputboxDisabled1");
                        $("#ruleIdFrmLocateMsg"). addClass("inputbox60"); 
                        $("#ruleIdFrmLocateMsg").attr('disabled', false);
                        
                        //Enables Note Type Box
                        $('#NOTETYPEID0').val('');
                        $("#NOTETYPEID0"). removeClass("inputboxDisabled1");
                        $("#NOTETYPEID0"). addClass("inputbox60"); 
                        $("#NOTETYPEID0").attr('disabled', false);
                        
                        //Enables Msg Txt Box
                        $('#messageValueId').val('');
                        $("#messageValueId"). removeClass("inputboxDisabled1");
                        $("#messageValueId"). addClass("inputbox60"); 
                        $("#messageValueId").attr('disabled', false);
                        
                        //Enables USER Box
                       	$('#userId').val('');
                        $("#userId"). removeClass("inputboxDisabled1");
                        $("#userId"). addClass("inputbox60"); 
                        $("#userId").attr('disabled', false);
                }    
		}            
      
}
function openUserCommentsNotApplicableDialogForRule(ruleId){

		$("#notApplicableruleId").val(ruleId);		
		$("#notApplicableRuleMappingComments").val('');	
		$("#userCommentsNotApplicableRuleDialog table#NARuleCommentsTable").css("border-top","1px solid black");	
				$("#userCommentsNotApplicableRuleDialog").dialog({
	                        height:'auto',
							width:'450px',	
							resizable:false,
	                        show:'slide',
							title: ruleId +' - Not Applicable',
	                        modal: true
	                  });
			$("#userCommentsNotApplicableRuleDialog").dialog();
	}  
function saveUserCommentsForNotApplicableForRule(){
		if(!imposeMaxLength('notApplicableMappingRuleComments' ,250,'user comments')){						
				return false;
		}
		document.forms['userCommentsNotApplicableRuleForm'].submit();
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
	function viewSpsMapping(spsId, status){
		if(status == 'UNMAPPED'){
			openViewMappingDialogForSps(spsId);
		}else{
			openViewMappingInProgressDialogForSps(spsId);		
		}
		
	}
	function openViewMappingDialogForSps(spsId) {	
		
		$.ajax({
			url: "${context}/vieworcreatemapping/viewMappingSps.ajax",
			dataType: "html",
			type: "POST",
			data: "spsId="+escape(spsId)+ "&section=UNMAPPED",
			success: function(data) {
				$("#viewMappingForSpsDialog").html(data);
				$("#viewMappingForSpsDialog").dialog({
					height:'auto',
					width:'890px',	
					show:'slide',
					modal: true,
					resizable: false,
					title: 'View Mapping'

				});
				$("#viewMappingForSpsDialog").dialog();
			}
		});
	}		 	
	function openViewMappingInProgressDialogForSps(spsId) {
	
      $.ajax({
            url: "${context}/vieworcreatemapping/viewMappingSps.ajax",
            dataType: "html",
			type: "POST",
            data: "spsId="+escape(spsId)+ "&section=MAPPED",
            success: function(data) {
                  $("#viewMappingInProgressSpsDialog").html(data);
                  $("#viewMappingInProgressSpsDialog").dialog({
                        height:'auto',	
						width:'910px',					
                        show:'slide',
                        modal: true,
						resizable: false,
						title: 'View Mapping'

                  });
                  $("#viewMappingInProgressSpsDialog").dialog();
            }
      });
	}
	function viewRuleMapping(ruleId, status){
		if(status == 'UNMAPPED'){
			openViewMappingDialogForRule(ruleId);
		}else{
			openViewMappingInProgressDialogForRule(ruleId);		
		}
	}
	function openViewMappingDialogForRule(ruleId) {	
		
		$.ajax({
			url: "${context}/vieworcreatemapping/viewMappingRule.ajax",
			dataType: "html",
			type: "POST",
			data: "ruleId="+escape(ruleId)+ "&section=UNMAPPED",
			success: function(data) {
				$("#viewMappingForRuleDialog").html(data);
				$("#viewMappingForRuleDialog").dialog({
					height:'auto',
					width:'890px',	
					show:'slide',
					modal: true,
					resizable: false,
					title: 'View Mapping'

				});
				$("#viewMappingForRuleDialog").dialog();
			}
		});
	}		 	
	function openViewMappingInProgressDialogForRule(ruleId) {
	
      $.ajax({
            url: "${context}/vieworcreatemapping/viewMappingRule.ajax",
            dataType: "html",
			type: "POST",
            data: "ruleId="+escape(ruleId)+ "&section=MAPPED",
            success: function(data) {
                  $("#viewMappingInProgressRuleDialog").html(data);
                  $("#viewMappingInProgressRuleDialog").dialog({
                        height:'auto',	
						width:'910px',					
                        show:'slide',
                        modal: true,
						resizable: false,
						title: 'View Mapping'

                  });
                  $("#viewMappingInProgressRuleDialog").dialog();
            }
      });
	}
	function openViewMappingInProgressDialogForCustomMsg(ruleId, spsId) {
	
      $.ajax({
            url: "${context}/vieworcreatemapping/viewMappingCustomMsg.ajax",
            dataType: "html",
			type: "POST",
            data: "ruleId="+escape(ruleId)+ "&spsId="+escape(spsId) + "&section=VIEW",
            success: function(data) {
                  $("#viewMappingCustomMessageDialog").html(data);
                  $("#viewMappingCustomMessageDialog").dialog({
                        height:'auto',	
						width:'910px',					
                        show:'slide',
                        modal: true,
						resizable: false,
						title: 'View Mapping'

                  });
                  $("#viewMappingCustomMessageDialog").dialog();
            }
      });
	}
	function openUserCommentsNotApplicableDialogForSps(spsId){

		$("#notApplicablespsId").val(jQuery.trim(spsId));		
		$("#notApplicableSpsMappingComments").val('');	
		$("#userCommentsNotApplicableSpsDialog table#NASpsCommentsTable").css("border-top","1px solid black");	
				$("#userCommentsNotApplicableSpsDialog").dialog({
	                        height:'auto',
							width:'450px',	
							resizable:false,
	                        show:'slide',
							title: spsId +' - Not Applicable',
	                        modal: true
	                  });
			$("#userCommentsNotApplicableSpsDialog").dialog();
	}
function saveUserCommentsForNotApplicableForSps(){
		if(!imposeMaxLength('notApplicableMappingSpsComments' ,250,'user comments')){						
				return false;
		}
		document.forms['userCommentsNotApplicableSpsForm'].submit();
	}
	var ruleIdForViewPage ="";
var userCommentsFrom = "";
var spsIdForViewPage = "";
//open user comments dialog from view and locate for Sps id
function openUserCommentsDialogForSps(spsId,actionFrom){
		spsIdForViewPage = escape(jQuery.trim(spsId));
		userCommentsFrom = actionFrom;		
		$("#userMappingCommentsForSps").val('');
		if(userCommentsFrom=='Send2TestSpsFromView') {		
				$("#userCommentsDialogForSps").dialog({
	                        height:'auto',
							resizable:false,
							width:'450px',	
	                        show:'slide',
							title: 'User comments - Send to test',
	                        modal: true
			    });	
		}else if(userCommentsFrom=='ApproveSpsFromView') {	
				$("#userCommentsDialogForSps").dialog({
	                        height:'auto',
							resizable:false,
							width:'450px',	
	                        show:'slide',
							title: 'User comments - Approve',
	                        modal: true
			    });	
		}	
	}
	// open user comments dialog from view and locate for rule
	function openUserCommentsDialogForRule(ruleId,actionFrom){
		ruleIdForViewPage = escape(jQuery.trim(ruleId));
		userCommentsFrom = actionFrom;		
		$("#userMappingCommentsForRule").val('');

		if(userCommentsFrom=='Send2TestRuleFromView') {		
				$("#userCommentsDialogForRule").dialog({
	                        height:'auto',
							resizable:false,
							width:'450px',	
	                        show:'slide',
							title: 'User comments - Send to test',
	                        modal: true
			    });	
		}else if(userCommentsFrom=='ApproveRuleFromView') {	
				$("#userCommentsDialogForRule").dialog({
	                        height:'auto',
							resizable:false,
							width:'450px',	
	                        show:'slide',
							title: 'User comments - Approve',
	                        modal: true
			    });	
		}	
	}
	
	// open user comments dialog from view and locate for custom message
	function openUserCommentsDialogForCustomMsg(ruleId,spsId,actionFrom){
		ruleIdForViewPage = escape(jQuery.trim(ruleId));
		spsIdForViewPage = escape(jQuery.trim(spsId));
		userCommentsFrom = actionFrom;			
		$("#userMappingCommentsForCustomMsg").val('');

		if(userCommentsFrom=='Send2TestCustomMsgFromView') {		
				$("#userCommentsDialogForCustomMsg").dialog({
	                        height:'auto',
							resizable:false,
							width:'450px',	
	                        show:'slide',
							title: 'User comments - Send to test',
	                        modal: true
			    });	
		}else if(userCommentsFrom=='ApproveCustomMsgFromView') {	
				$("#userCommentsDialogForCustomMsg").dialog({
	                        height:'auto',
							resizable:false,
							width:'450px',	
	                        show:'slide',
							title: 'User comments - Approve',
	                        modal: true
			    });	
		}	
	}
//Method to invoke save user comments from comments dialog box for SPS id
	function saveUserCommentsForSps(){
		$('#userCommentsDialogForSps').dialog( "close" );
		var userComments = $("#userMappingCommentsForSps").val();	
		if(userCommentsFrom=='Send2TestSpsFromView') {
			$.ajax({
				url: "${context}/stateflowewpdbx/sendToTestSpsAjax.ajax",
				dataType: "json",
				type: "POST",			
				data: "spsId="+spsIdForViewPage+"&userComments="+userComments,
				success: function(data) {
				cache: false,
				handleSendToTestFromView(data);}
			});
		}else if(userCommentsFrom=='ApproveSpsFromView') {
			$.ajax({
				url: "${context}/stateflowewpdbx/approveSpsAjax.ajax",
				dataType: "json",
				type: "POST",			
				data: "spsId="+spsIdForViewPage+"&userComments="+userComments,
				success: function(data) {
				cache: false,
				handleApproveFromView(data);}					
			});
		}
	}
	//Method to invoke save user comments from comments dialog box for Rule id
	function saveUserCommentsForRule(){
		$('#userCommentsDialogForRule').dialog( "close" );
		var userComments = $("#userMappingCommentsForRule").val();	
		if(userCommentsFrom=='Send2TestRuleFromView') {
			$.ajax({
				url: "${context}/stateflowewpdbx/sendToTestRuleAjax.ajax",
				dataType: "json",
				type: "POST",			
				data: "ruleId="+ruleIdForViewPage+"&userComments="+userComments,
				success: function(data) {
				cache: false,
				handleSendToTestFromView(data);}
			});
		}else if(userCommentsFrom=='ApproveRuleFromView') {
			$.ajax({
				url: "${context}/stateflowewpdbx/approveRuleAjax.ajax",
				dataType: "json",
				type: "POST",			
				data: "ruleId="+ruleIdForViewPage+"&userComments="+userComments,
				success: function(data) {
				cache: false,
				handleApproveFromView(data);}					
			});
		}
	}
	//Method to invoke save user comments from comments dialog box for custom message
	function saveUserCommentsForCustomMsg(){
		$('#userCommentsDialogForCustomMsg').dialog( "close" );
		var userComments = $("#userMappingCommentsForCustomMsg").val();	
		if(userCommentsFrom=='Send2TestCustomMsgFromView') {
			$.ajax({
				url: "${context}/stateflowewpdbx/sendToTestCustomMsgAjax.ajax",
				dataType: "json",
				type: "POST",			
				data: "ruleId="+ruleIdForViewPage+"&spsId="+spsIdForViewPage+"&userComments="+userComments,
				success: function(data) {
				cache: false,
				handleSendToTestFromView(data);}
			});
		}else if(userCommentsFrom=='ApproveCustomMsgFromView') {
			$.ajax({
				url: "${context}/stateflowewpdbx/approveCustomMsgAjax.ajax",
				dataType: "json",
				type: "POST",			
				data: "ruleId="+ruleIdForViewPage+"&spsId="+spsIdForViewPage+"&userComments="+userComments,
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
		if(data.ruleId != null && data.spsId == null){		
			handleIconsWhileSendToTest(data.ruleId);
		}
		else if(data.spsId != null && data.ruleId == null){
			handleIconsWhileSendToTest(data.spsId);
		}
		else if(data.ruleId != null && data.spsId != null){
			var cusMsg = data.ruleId + data.spsId;
			handleIconsWhileSendToTest(cusMsg); 
		}
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
		if(data.ruleId != null && data.spsId == null){		
			handleIconsWhileSendToTest(data.ruleId);
			handleIconsWhileApprove(data.ruleId);
		}
		else if(data.spsId != null && data.ruleId == null){
			handleIconsWhileSendToTest(data.spsId);
			handleIconsWhileApprove(data.spsId);
		}
		else if(data.ruleId != null && data.spsId != null){
			var cusMsg = data.ruleId + data.spsId;
			handleIconsWhileSendToTest(cusMsg); 
			handleIconsWhileApprove(cusMsg);
		}
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
		if(typeof(warningMessages) != 'undefined' && warningMessages.length >0) {
			for(i=0; i< warningMessages.length; i++) {
				addWarnToNotificationTray(warningMessages[i]);
			}
		}
		openTrayNotification();

	}	
</script>
</head>
<body onload="showHideTextBox('searchRadio','spsid','0');">
	<form name="submitCreateForm"  method="post" style="height: 100%;" onkeypress="enterKeyPress(event);">
	<input type="hidden" id="selectedSpsIdForEdit" name="selectedSpsIdForEdit" value="" />
	<input type="hidden" id="selectedruleForEdit" name="selectedruleForEdit" value="" />
	<input type="hidden" id="ruleIdHidden" name="ruleIdHidden" value="" />
	<input type="hidden" id="pageName" name="pageName" value="advanceSearchEbx"/>
	<input type="hidden" id="spsIdHidden" name="spsIdHidden" value=""/>
	<input type="hidden" id="selectedSpsIdForEditSub" name="selectedSpsIdCusForEdit" value="" />
	<input type="hidden" id="selectedRuleIdForEditSub" name="selectedRuleIdCusForEdit" value="" />
	<input type="hidden" id="contextPath" value= "<%=request.getContextPath()%>"/>
	<%@include file="/WEB-INF/jspf/pageTop.jspf"%>
    <div class="innerContainer" style="height:99%">
    	<div id="createEditContainerAdvanceSearch" style="height: 100%; overflow: hidden;">	<!--createEditContainer Starts-->

		<div id="advanceSearchResultContainer" class="AdavanceSearchOuterResult">
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
										<tr height="15">
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
							<div class="SearchAccordianTableDiv" id="inSearchDiv" style="display:block;overflow:hidden;overflow-y:hidden;overflow-x:hidden;">
								<div> <!-- search criteria container starts-->
										<table border="1" cellpadding="0" cellspacing="0" width="100%">
											<TBODY>
												<tr height="20">
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
										<div class="createTable" style="padding-bottom: 10">
											<input type="hidden" name="currentPage" id="currentPage" value="${pageObject.currentPage}"/>
											<table border="0" cellpadding="0" cellspacing="0" width="100%" class="shadedText">
												<tr height="10px" class="createEditTable1-Listdata" style="vertical-align: bottom;">
													<td colspan="3" style="vertical-align: bottom;"><b style="font-size:11px;">SEARCH FOR</b></td>
													<td colspan="4" style="vertical-align: bottom; padding-left: 150px;"><b style="font-size:11px;">SHOW ONLY</b></td>
												</tr>
												<tr height="10px" class="createEditTable1-Listdata" style="vertical-align: bottom;">
													<td style="vertical-align: bottom;"><input type="radio"  id="searchRadio" name="searchRadio" value = "spsid" checked onclick="showHideTextBox('searchRadio','spsid','1');" >&nbsp;SPS ID
													</td>
													<td style="vertical-align: bottom;"><input type="radio"  id="searchRadio" name="searchRadio" value = "ruleid" onclick="showHideTextBox('searchRadio','ruleid','1');">&nbsp;Rule ID
													</td>
													<td style="vertical-align: bottom;padding-right:50px;"><input type="radio"  id="searchRadio" name="searchRadio" value = "msgtxt" onclick="showHideTextBox('searchRadio','msgtxt','1');">&nbsp;Message Text
													</td>	
													<td></td>
													<td colspan="3"> </td>
													
												</tr>		
												<tr height="10px" class="createEditTable1-Listdata" style="vertical-align: bottom;">
													<td class="headText" style="vertical-align: bottom;"  width="100px">SPS ID</td>
													<td class="headText" width="150px" style="vertical-align: bottom;">Rule ID</td>
													<td class="headText" style="vertical-align: bottom;" width="128">SPS ID</td>
													<td style="vertical-align: bottom;width:50px;height:10px;border-left: #d9e5eb 1px solid;" ></td>
													<TD class="headText" >User<a href="#"  onclick="displayInfo('USER',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a></TD>
													<td rowspan="8" colspan="2" style="vertical-align: top; padding-left:50px;" >
													<table border="0" cellpadding="0" cellspacing="0"
															class="mt10 mL10 Pd3 shadedText">
															<tr class="createEditTable1-Listdata">
																
																<td width="120px" class="verdana">																	
																	<input type="checkbox" checked name="isUnMapped" id="isUnMapped" value="unMapped"/>&nbsp;Unmapped																	
																</td>
																<td width="90px" class="verdana">
																	
																</td>
															</tr>
															<tr class="createEditTable1-Listdata">
																
																<td class="verdana" height="21"><input type="checkbox" checked name="isMapped" id="isMapped" value="isMapped" />&nbsp;Mapped</td>
																<td height="21">&#160;</td>
															</tr>	
															<tr class="createEditTable1-Listdata">
																<td class="verdana" height="21"><input type="checkbox" checked name="isNotApplicable" id="isNotApplicable" value="isNotApplicable"/>&nbsp;Not Applicable</td>
																<td></td>
															</tr>
															<tr class="createEditTable1-Listdata">
																<td class="verdana" height="21"><input type="checkbox" checked name="isNotFinalized" id="isNotFinalized" value="notFinalized"/>&nbsp;Not Finalized</td>
																<td></td>
															</tr>
														</table>
													</td>	
												</tr>
												<tr class="createEditTable1-Listdata" style="padding-top: 0px">
													<td><input type="text" class="inputbox60" id="spsIdFrmLocate" name="spsIDLocate" onfocus="clearCreateOrLocateFields('spsIdFrmLocate','Locate')" onkeypress="enterKeyPress(event);"/></td>
													<td><input type="text" class="inputbox60" id="ruleIdFrmLocate" name="ruleIDLocate" onfocus="clearCreateOrLocateFields('ruleIdFrmLocate','Locate')" onkeypress="enterKeyPress(event);"/></td>
													<td width="128"><input type="text" class="inputbox60" id="spsIdFrmLocateMsg" name="spsIdFrmLocateMsg" onfocus="clearCreateOrLocateFields('spsIdFrmLocateMsg','Locate')" onkeypress="enterKeyPress(event);"/></td>								
													<td  style="vertical-align: top;width:50px;border-left: #d9e5eb 1px solid;" ></td>
													<td><INPUT type="text" name="userId0" id="userId0" class="inputbox60" onkeypress="enterKeyPress(event);"></td>
												</tr>
												<tr height="10px" class="createEditTable1-Listdata" style="vertical-align: bottom;">
													<td class="headText" width="150px" style="vertical-align: bottom;">EB01<a href="#" onclick="displayInfo('EB01_SRCH',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a></td>
													<td class="headText" style="vertical-align: bottom;">EB03<a href="#"  onclick="displayInfo('EB03_SRCH',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a></td>
													<td class="headText" style="vertical-align: bottom;" width="128">Rule ID</td>
													<td  style="vertical-align: top;width:50px;border-left: #d9e5eb 1px solid;"></td>
													<td></td>
												</tr>
												<tr class="createEditTable1-Listdata" style="padding-top: 0px">
													<td><input type="text" name="eb01Val" class="inputbox60" id="EB01Id" onkeypress="enterKeyPress(event);"/></td>
													<td><input type="text" name="eb03Val" class="inputbox60" id="EB03Id0" onkeypress="enterKeyPress(event);"></td>
													<td width="128"><input type="text" class="inputbox60" id="ruleIdFrmLocateMsg" name="ruleIdFrmLocateMsg" onfocus="clearCreateOrLocateFields('ruleIdFrmLocateMsg','Locate')" onkeypress="enterKeyPress(event);"/></td>								
													<td style="vertical-align: top;width:50px;border-left: #d9e5eb 1px solid;"></td>													
													<td></td>
												</tr>
												<tr height="10px" class="createEditTable1-Listdata" style="vertical-align: bottom;">
													<td class="headText" width="165px" style="vertical-align: bottom;"></td>
													<td class="headText" style="vertical-align: bottom;">III02<a href="#"  onclick="displayInfo('III02_SRCH',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a></td>
													<td class="headText" width="165px" style="vertical-align: bottom;">Message Text<a href="#"  onclick="displayInfo('MSG_SRCH',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a></td>
													<td style="vertical-align: top;width:50px;border-left: #d9e5eb 1px solid;"></td>													
													<td></td>
												</tr>
												<tr class="createEditTable1-Listdata" style="padding-top: 0px">
													<td></td>
													<td><input type="text" name="iii02Val" class="inputbox60" id="III02Id0" onkeypress="enterKeyPress(event);"></td>
													<td width="128"><input type="text" id="messageValueId" name="messageValue" class="inputbox60" onkeypress="enterKeyPress(event);"/></td>								
													<td style="vertical-align: top;width:50px;border-left: #d9e5eb 1px solid;"></td>													
													<td></td>
													
												</tr>
												<tr height="10px" class="createEditTable1-Listdata" style="vertical-align: bottom;">
													<td class="headText" width="165px" style="vertical-align: bottom;"></td>
													<td class="headText" style="vertical-align: bottom;"></td>
													<td class="headText" style="vertical-align: bottom;">Note Type<a href="#"  onclick="displayInfo('NOTE_TYPE_CODE_SRCH',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a></td>
													<td  style="vertical-align: top;width:50px;border-left: #d9e5eb 1px solid;"></td>
													<td></td>
												</tr>
												<tr class="createEditTable1-Listdata" style="padding-top: 0px">
													<td></td>
													<td></td>
													<td width="128"><INPUT type="text" name="noteTypeCodeVal0" id="NOTETYPEID0"
													
								class="inputbox60" onkeypress="enterKeyPress(event);"></td>	
													<td  style="vertical-align: top;width:50px;border-left: #d9e5eb 1px solid;"></td>	
													<td></td>						
												</tr>
												
											</table>
											<table width="100%" height="0px">
												<tr>
													<td>
													
													<input type="hidden" id="hdSpsIdToLocateId" name="hdSpsIdToLocate" value="${searchCriteria.spsId}" />
													<input type="hidden" id="hdSpsIdToLocateIdMsg" name="hdSpsIdToLocateIdMsg" value="${searchCriteria.spsId}" />
													<input type="hidden" id="hdEb01Id" name="hdEb01" value="${searchCriteria.EB01}"  /> 
													<input type="hidden" id="hdSpsIdCriteriaId" name="hdSpsIdCriteria" value="${searchCriteria.viewType}" /> 
													<input type="hidden" id="hdRuleIdCriteriaId" name="hdRuleIdCriteria" value="${searchCriteria.ruleIdCriteria}" /> 
													<input type="hidden" id="hdMsgCriteriaId" name="hdMsgCriteria" value="${searchCriteria.msgCrteria}" /> 
													<input type="hidden" id="hdIsUnMappedId" name="hdIsUnMapped" value="${searchCriteria.unMapped}" /> 
													<input type="hidden" id="hdIsMappedId" name="hdIsMapped" value="${searchCriteria.mapped}"  /> 
													<input type="hidden" id="hdIsNotApplicableId" name="hdIsNotApplicable" value="${searchCriteria.notApplicable}" /> 
													<input type="hidden" id="hdIsNotFinalizedId" name="hdIsNotFinalized" value="${searchCriteria.notFinalized}" />
													<input type="hidden" id="hdUserId" name="hdUserId" value="${searchCriteria.user}" />
													<input type="hidden" id="hdRuleIdFrmLocate" name="hdRuleIdFrmLocate" value="${searchCriteria.headerRuleId}" />
													<input type="hidden" id="hdRuleIdFrmLocateMsg" name="hdRuleIdFrmLocateMsg" value="${searchCriteria.headerRuleId}" />
													<input type="hidden" id="hdEB03Id0" name="hdEB03Id0" value="${searchCriteria.EB03}" />
													<input type="hidden" id="hdIII02Id0" name="hdIII02Id0" value="${searchCriteria.III02}" />
													<input type="hidden" id="hdNoteType" name="hdNoteType" value="${searchCriteria.noteType}"  />
													<input type="hidden" id="hdMsgText" name="hdMsgText" value="${searchCriteria.messageText}" />
													<input type="hidden" id="hdFromHistory" name="hdFromHistory" value="${fromHistory}" />

													<input type="hidden" id="hdAuthorizedToApprove" name="hdAuthorizedToApprove" value="${userUIPermissions.authorizedToapprove}" />
													
														</td>
														
												</tr>
											</table>
									</div>
							
								</div> <!-- search criteria container ends-->
							</div>
						</div>
						<div>
							<div class="SearchAccordianTableDivSub" id="inSearchDivHeader2">
								<table id="updateSearchTable1" border="1" cellpadding="0" cellspacing="0" width="100%">
									<TBODY>
										<tr height="20">
											<td class="tableHeaderAdvanceSearch" nowrap="nowrap">Advanced Search Result								
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
								<jsp:include flush="true" page="advancesearchresult.jsp"></jsp:include>	
							</div>
						</div>
					</div>
				
				</div>
							
			</div>
		</div>
		<div >
		</div>
		<div id="massUpdateContainer" style="border:1px solid #898989; height: 30%;"><!-- update div starts -->
			<input type="hidden" id="hdMassUpdateComment" name="hdMassUpdateComment"/> 
				<table id="updateFieldTableSps" style="display: block;" width="100%" border="1" cellpadding="0" cellspacing="0">
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
								<td class="headText" style="vertical-align: middle;"  width="20px"><input type="checkbox" onclick="enableField('isUdtHSD01','uHSD01Id','nvHSD01Id')" name="isUdtHSD01" id="isUdtHSD01" value="udtHSD01"/></td>
								<td class="headText" style="vertical-align: middle;"  width="50px">HSD01</td>
								<td class="headText" style="vertical-align: middle;"  width="80px"><input type="text" name="uhsd01Val" class="inputbox50Disabled" id="uHSD01Id" disabled="disabled"/></td>
								<td class="headText" style="vertical-align: middle;"  width="100px"><input type="text" name="nvhsd01Val" class="inputbox30Disabled" id="nvHSD01Id" disabled="disabled"/>&#160;</td>
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
								<td class="headText" style="vertical-align: middle;"  width="20px"><input type="checkbox" onclick="enableField('isUdtHSD02','uHSD02Id','nvHSD02Id')" name="isUdtHSD02" id="isUdtHSD02" value="udtHSD02"/></td>
								<td class="headText" style="vertical-align: middle;"  width="50px">HSD02</td>
								<td class="headText" style="vertical-align: middle;"  width="80px"><input type="text" name="uhsd02Val" class="inputbox50Disabled" id="uHSD02Id" disabled="disabled"/></td>
								<td class="headText" style="vertical-align: middle;"  width="100px"><input type="text" name="nvhsd02Val" class="inputbox30Disabled" id="nvHSD02Id" disabled="disabled"/>&#160;</td>
								<td class="headText" style="vertical-align: middle;"  width="20px"><input type="checkbox" onclick="enableField('isUdtHSD06','uHSD06Id','nvHSD06Id')" name="isUdtHSD06" id="isUdtHSD06" value="udtHSD06"/></td>
								<td class="headText" style="vertical-align: middle;"  width="50px">HSD06</td>
								<td class="headText" style="vertical-align: middle;"  width="80px"><input type="text" name="uhsd06Val" class="inputbox50Disabled" id="uHSD06Id" disabled="disabled"/></td>
								<td class="headText" style="vertical-align: middle;"  width="100px"><input type="text" name="nvhsd06Val" class="inputbox30Disabled" id="nvHSD06Id" disabled="disabled"/>&#160;</td>
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
								<td class="headText" style="vertical-align: middle;"  width="20px"><input type="checkbox" onclick="enableField('isUdtHSD07','uHSD07Id','nvHSD07Id')" name="isUdtHSD07" id="isUdtHSD07" value="udtHSD07"/></td>
								<td class="headText" style="vertical-align: middle;"  width="50px">HSD07</td>
								<td class="headText" style="vertical-align: middle;"  width="80px"><input type="text" name="uhsd07Val" class="inputbox50Disabled" id="uHSD07Id" disabled="disabled"/></td>
								<td class="headText" style="vertical-align: middle;"  width="100px"><input type="text" name="nvhsd07Val" class="inputbox30Disabled" id="nvHSD07Id" disabled="disabled"/></td>
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
								<td class="headText" style="vertical-align: middle;"  width="20px"><input type="checkbox" onclick="enableField('isUdtHSD08','uHSD08Id','nvHSD08Id')" name="isUdtHSD08" id="isUdtHSD08" value="udtHSD08"/></td>
								<td class="headText" style="vertical-align: middle;"  width="50px">HSD08</td>
								<td class="headText" style="vertical-align: middle;"  width="80px"><input type="text" name="uhsd08Val" class="inputbox50Disabled" id="uHSD08Id" disabled="disabled"/></td>
								<td class="headText" style="vertical-align: middle;"  width="100px"><input type="text" name="nvhsd08Val" class="inputbox30Disabled" id="nvHSD08Id" disabled="disabled"/>&#160;</td>
						</tr>
						<tr height="10px" class="createEditTable1-Listdata" style="vertical-align: bottom;">
								<td class="headText" style="vertical-align: middle;"  colspan="12">
								</td>
						</tr>		
					</TBODY>
				 </table>
				<table id="updateFieldTableRule" style="display: none;" width="100%" border="1" cellpadding="0" cellspacing="0">
					<THEAD>
						<tr height="20">
							<td class="tableHeaderAdvanceSearch" nowrap="nowrap" colspan="12">Update Fields								
							</td>
						</tr>
					</THEAD>
					<TBODY>
						<tr>
							<td class="headText" style="vertical-align: middle;" colspan="12">&nbsp;</td>
						</tr>
						<tr>
							<td class="headText" style="vertical-align: middle;" colspan="2">&nbsp;</td>
							<td class="headText" style="vertical-align: middle; padding-left: 5px;" colspan="1">Current</td>
							<td class="headText" style="vertical-align: middle; padding-left: 20px;" colspan="9">New</td>
						</tr>
						<tr height="20px" class="createEditTable1-Listdata" style="vertical-align: bottom;">
								<td class="headText" style="vertical-align: middle;"  width="20px"><input type="checkbox" onclick="enableEB03Update();" name="isUdtEB03" id="isUdtEB03" value="udtEB03"/></td>
								<td class="headText" style="vertical-align: middle;"  width="70px">EB03</td>
								<td class="headText" style="vertical-align: middle;"  width="80px"><input type="text" name="ueb03Val" class="inputbox50Disabled" id="uEB03Id" disabled="disabled"/></td>
								<td class="headText" style="vertical-align: middle; padding-left: 20px;"  colspan="9">
									<input type="text" name="nveb03Val" class="inputbox50Disabled" id="nvEB03Id" disabled="disabled"/>
								</td>
						</tr>
						<tr height="10px" class="createEditTable1-Listdata" style="vertical-align: bottom;">
								<td class="headText" style="vertical-align: middle;"  colspan="12">
								</td>
						</tr>		
					</TBODY>
				 </table>
				 <table id="updateFieldTableMsg" style="display: none;" width="100%" border="1" cellpadding="0" cellspacing="0">
					<THEAD>
						<tr height="20">
							<td class="tableHeaderAdvanceSearch" nowrap="nowrap" colspan="12">Update Fields								
							</td>
						</tr>
					</THEAD>
					<TBODY>
						<tr>
							<td class="headText" style="vertical-align: middle;" colspan="12">&nbsp;</td>
						</tr>
						<tr>
							<td class="headText" style="vertical-align: middle;" colspan="2">&nbsp;</td>
							<td class="headText" style="vertical-align: middle; padding-left: 10px;" colspan="1">Current</td>
							<td class="headText" style="vertical-align: middle; padding-left: 10px;" colspan="9">New</td>
						</tr>
						
						<tr height="20px" class="createEditTable1-Listdata" style="vertical-align: bottom;">
								<td class="headText" style="vertical-align: middle;"  width="20px"><input type="checkbox" onclick="enableField('isUdtnoteType','unoteTypeId','nvnoteTypeId')" name="isUdtnoteType" id="isUdtnoteType" value="udtnoteType"/></td>
								<td class="headText" style="vertical-align: middle;"  width="60px">Note Type</td>
								<td class="headText" style="vertical-align: middle;"  width="80px"><input type="text" name="unoteTypeVal" class="inputbox50Disabled" id="unoteTypeId" disabled="disabled"/></td>
								<td class="headText" style="vertical-align: middle;"  width="100px"><input type="text" name="nvnoteTypeVal" class="inputbox30Disabled" id="nvnoteTypeId" disabled="disabled"/>&#160;</td>
								<td class="headText" style="vertical-align: middle;"  width="20px"></td>
								<td class="headText" style="vertical-align: middle;"  width="50px"></td>
								<td class="headText" style="vertical-align: middle;"  width="80px"></td>
								<td class="headText" style="vertical-align: middle;"  width="100px">&#160;</td>
								<td class="headText" style="vertical-align: middle;"  width="20px"></td>
								<td class="headText" style="vertical-align: middle;"  width="50px"></td>
								<td class="headText" style="vertical-align: middle;"  width="80px"></td>
								<td class="headText" style="vertical-align: middle;"  width="100px"></td>
						
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
        <td width="0" height="20"><a href="${pageContext.request.contextPath}/viewlandingewpdbx.html" >Back</a></td>
        <td width="9" height="0" id="sepLnkUpdateId" style="display: none;"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>	
        <td width="0" height="20" id="lnkUpdateId" style="vertical-align: middle;display: none;"><a href="#" onclick="openMassUpdateConfirmation('update');" >Update</a></td>
        <td width="9" height="0" id="sepLnkNotApplicableId" style="display: none;"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>	
        <td width="0" height="20" id="lnkNotApplicableId" style="vertical-align: middle;display: none;"><a href="#" onclick="openMassUpdateConfirmation('notApplicable');" >Not Applicable</a></td>
        <td width="9" height="0" id="sepLnkSendTestId" style="display: none;"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>	
        <td width="0" height="20" id="lnkSendTestId" style="vertical-align: middle;display: none;"><a href="#" onclick="openMassUpdateConfirmation('sendToTest');" >Send to Test</a></td>
        <td width="9" height="0" id="sepLnkApproveId" style="display: none;"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
        <td width="0" height="20" id="lnkApproveId" style="vertical-align: middle;display: none;"><a href="#" onclick="openMassUpdateConfirmation('approve');" >Approve</a></td>
		<td width="18" height="20"><img src="${imageDir}/footer_right.gif" width="18" height="20" /></td>
      </tr>
    </table>	    
</div>    
    
    
    
<div id="hippaCodePopUpDiv"></div>
<div id="ruleViewPopUpDiv"></div>
<div id="groupPagePopUpDiv"></div>
<div id="spsHoverPopUp" title="Locate Results"></div>
<div id="massUpdateConfirmationDiv">
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
<c:if test="${fromHistory == 'true'}">
<script type="text/javascript">
viewSearchHistory();
</script>
</c:if>
<div id="confirmationDiv"></div>
<div id="progressPopup" style="vertical-align: middle;display: none">
	<div id="progressbar"></div>
</div>
<div id="userCommentsNotApplicableSpsDialog" >
<form name="userCommentsNotApplicableSpsForm" action="${context}/stateflowewpdbx/markSpsAsNotApplicable.html" method="POST">
 <input type="hidden" name="notApplicablespsId" id="notApplicablespsId" value="" />
  <input type="hidden" id="pageName" name="pageName" value="advanceSearchEbx"/>
	<table id="NASpsCommentsTable" border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5 bT">          
	  <tr class="">
        <td ><textarea name="notApplicableMappingSpsComments"  id="notApplicableMappingSpsComments" 
		rows="5" cols="77"></textarea></td>
	  </tr> 			
	</table>
	<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
      <tr>       
        <td >
			<a
			href="#" onclick="saveUserCommentsForNotApplicableForSps();"><img  id="notApplicableSaveImg" src="${imageDir}/save_but.gif" 
			alt="Save"  title="Save"/></a>
		</td>	 	
      </tr>
	</table>	
</form>
</div>
<div id="userCommentsNotApplicableRuleDialog">
<form name="userCommentsNotApplicableRuleForm"
	action="${context}/stateflowewpdbx/markRuleAsNotApplicable.html"
	method="POST">
	<input type="hidden" name="notApplicableruleId" id="notApplicableruleId" value="" />
	<input type="hidden" id="pageName" name="pageName" value="advanceSearchEbx"/>
<table id="NARuleCommentsTable" border="0" cellpadding="0"
	cellspacing="0" class="Pd3 mT5 bT">
	<tr class="">
		<td><textarea name="notApplicableMappingRuleComments"
			id="notApplicableMappingRuleComments" rows="5" cols="77"></textarea></td>
	</tr>
</table>
<table border="0" align="center" cellpadding="0" cellspacing="0"
	class="footerTable">
	<tr>
		<td><a href="#"
			onclick='saveUserCommentsForNotApplicableForRule();'><img
			id="notApplicableSaveImg" src="${imageDir}/save_but.gif" alt="Save"
			title="Save" /></a></td>
	</tr>
</table>
</form>
</div>

<div id="userCommentsSent2TestDialogForSps" >
<form name="userCommentsSent2TestSpsForm" action="${context}/stateflowewpdbx/sendToTestSps.html" method="POST">
 <input type="hidden" name="send2teststateflowspsId" id="send2teststateflowspsId" value="" /> 
 <input type="hidden" id="pageName" name="pageName" value="advanceSearchEbx"/>
	<table id="sendToTestUserCommentsTable1" border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5 bT">          
	  <tr class="">
        <td ><textarea name="send2TestMappingSpsComments" id="send2TestMappingSpsComments"
		rows="5" cols="77"></textarea></td>
	  </tr> 			
	</table>
	<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
      <tr>       
        <td >
			<a
			href="#" onclick="saveUserCommentsForSent2TestForSps();"><img id="sendToTestSaveImg" src="${imageDir}/save_but.gif" 
			alt="Save" title="Save" /></a>
		</td>		
      </tr>
	</table>	
</form>
</div>

<div id="userCommentsSent2TestDialogForRule" >
<form name="userCommentsSent2TestRuleForm" action="${context}/stateflowewpdbx/sendToTestRule.html" method="POST">
 <input type="hidden" name="send2teststateflowruleId" id="send2teststateflowruleId" value="" /> 
	 <input type="hidden" id="pageName" name="pageName" value="advanceSearchEbx"/>
	<table id="sendToTestUserCommentsTable1" border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5 bT">          
	  <tr class="">
        <td ><textarea name="send2TestMappingRuleComments" id="send2TestMappingRuleComments"
		rows="5" cols="77"></textarea></td>
	  </tr> 			
	</table>
	<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
      <tr>       
        <td >
			<a
			href="#" onclick="saveUserCommentsForSent2TestForRule();"><img id="sendToTestSaveImg" src="${imageDir}/save_but.gif" 
			alt="Save" title="Save" /></a>
		</td>		
      </tr>
	</table>	
</form>
</div>

<div id="userCommentsApproveDialogForSps" >
<form name="userCommentsApproveSpsForm" action="${context}/stateflowewpdbx/approveSps.html" method="POST">
<input type="hidden" name="approvestateflowspsId" id="approvestateflowspsId" value="" />
 <input type="hidden" id="pageName" name="pageName" value="advanceSearchEbx"/>
	 <table border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5 bT">          
	  <tr class="">
        <td ><textarea name="approvedMappingSpsComments"
		id="approvedMappingSpsComments" rows="5" cols="77"></textarea></td>
	  </tr> 			
	</table>
	<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
      <tr>               
		<td >
			<a
			href="#" onclick="saveUserCommentsForApproveForSps()"><img src="${imageDir}/save_but.gif" 
			alt="Save" title="Save" /></a>
		</td>	
		
      </tr>
	</table>	
</form>
</div>

<div id="userCommentsApproveDialogForRule" >
<form name="userCommentsApproveRuleForm" action="${context}/stateflowewpdbx/approveRule.html" method="POST">
<input type="hidden" name="approvestateflowruleId" id="approvestateflowruleId" value="" />
<input type="hidden" id="pageName" name="pageName" value="advanceSearchEbx"/>
	 <table border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5 bT">          
	  <tr class="">
        <td ><textarea name="approvedMappingRuleComments"
		id="approvedMappingRuleComments" rows="5" cols="77"></textarea></td>
	  </tr> 			
	</table>
	<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
      <tr>               
		<td >
			<a
			href="#" onclick="saveUserCommentsForApproveForRule()"><img src="${imageDir}/save_but.gif" 
			alt="Save" title="Save" /></a>
		</td>	
		
      </tr>
	</table>	
</form>
</div>

<div id="userCommentsSent2TestDialogForCustomMsg" >
<form name="userCommentsSent2TestCustomMsgForm" action="${context}/stateflowewpdbx/sendToTestCustomMsg.html" method="POST">
<input type="hidden" name="send2teststateflowruleIdCus" id="send2teststateflowruleIdCus" value="" /> 
<input type="hidden" name="send2teststateflowspsIdCus" id="send2teststateflowspsIdCus" value="" />
<input type="hidden" id="pageName" name="pageName" value="advanceSearchEbx"/>
	<table id="sendToTestUserCommentsTable1" border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5 bT">          
	  <tr class="">
        <td ><textarea name="send2TestMappingCustomMsgComments" id="send2TestMappingCustomMsgComments"
		rows="5" cols="77"></textarea></td>
	  </tr> 			
	</table>
	<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
      <tr>       
        <td >
			<a
			href="#" onclick="saveUserCommentsForSent2TestForCustomMsg();"><img id="sendToTestSaveImg" src="${imageDir}/save_but.gif" 
			alt="Save" title="Save" /></a>
		</td>		
      </tr>
	</table>	
</form>
</div>

<div id="userCommentsApproveDialogForCustomMsg" >
<form name="userCommentsApproveCustomMsgForm" action="${context}/stateflowewpdbx/approveCustomMsg.html" method="POST">
<input type="hidden" name="approvestateflowruleIdCus" id="approvestateflowruleIdCus" value="" />
<input type="hidden" name="approvestateflowspsIdCus" id="approvestateflowspsIdCus" value="" />
<input type="hidden" id="pageName" name="pageName" value="advanceSearchEbx"/>

	 <table border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5 bT">          
	  <tr class="">
        <td ><textarea name="approvedMappingCustomMsgComments"
		id="approvedMappingCustomMsgComments" rows="5" cols="77"></textarea></td>
	  </tr> 			
	</table>
	<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
      <tr>               
		<td >
			<a
			href="#" onclick="saveUserCommentsForApproveForCustomMsg()"><img src="${imageDir}/save_but.gif" 
			alt="Save" title="Save" /></a>
		</td>	
		
      </tr>
	</table>	
</form>
</div>


<div id="viewMappingForSpsDialog" title="View Mapping">
</div>
<div id="viewMappingInProgressSpsDialog" title="View Mapping">
</div>
<div id="viewMappingForRuleDialog" title="View Mapping">
</div>
<div id="viewMappingInProgressRuleDialog" title="View Mapping">
</div>
<div id="viewMappingCustomMessageDialog" title="View Mapping">
</div>
	<div id="userCommentsDialogForSps" >	
		 <table border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5 bT">          
		  <tr class="">
	        <td ><textarea name="userMappingCommentsForSps"
			id="userMappingCommentsForSps" rows="5" cols="77"></textarea></td>
		  </tr> 			
		</table>
		<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
	      <tr>               
			<td >
				<a
				href="#" onclick="saveUserCommentsForSps();"><img src="${imageDir}/save_but.gif" 
				alt="Save" title="Save" /></a>
			</td>				
	      </tr>
		</table>
	</div>	
	
	<div id="userCommentsDialogForRule" >	
		 <table border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5 bT">          
		  <tr class="">
	        <td ><textarea name="userMappingCommentsForRule"
			id="userMappingCommentsForRule" rows="5" cols="77"></textarea></td>
		  </tr> 			
		</table>
		<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
	      <tr>               
			<td >
				<a
				href="#" onclick="saveUserCommentsForRule();"><img src="${imageDir}/save_but.gif" 
				alt="Save" title="Save" /></a>
			</td>				
	      </tr>
		</table>
	</div>		
	
	<div id="userCommentsDialogForCustomMsg" >	
		 <table border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5 bT">          
		  <tr class="">
	        <td ><textarea name="userMappingCommentsForCustomMsg"
			id="userMappingCommentsForCustomMsg" rows="5" cols="77"></textarea></td>
		  </tr> 			
		</table>
		<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
	      <tr>               
			<td >
				<a
				href="#" onclick="saveUserCommentsForCustomMsg();"><img src="${imageDir}/save_but.gif" 
				alt="Save" title="Save" /></a>
			</td>				
	      </tr>
		</table>
	</div>	
	
<div id="confirmationDivUnlock"></div>
<form action="${context}/advancesearchebx/generateExcelReport.ajax" name="printReportForm">
</form>		
<%@include file="/WEB-INF/jspf/whatIsThisToolTip.jspf"%>
<%@include file="/WEB-INF/jspf/messageTray.jspf"%>
</body>
</html>