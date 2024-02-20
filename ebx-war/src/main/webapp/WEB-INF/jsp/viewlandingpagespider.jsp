<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page session="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional-//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/jspf/pageHead.jspf"%> 
</head> 
<body onload="spiderPageLoad();">
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="context" value="${pageContext.request.contextPath}"/>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/umRuleUtil.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery.dataTables.nightly.js"></script>
<style type="text/css">

	tbody.unmapped-tbody::before{
	  content: '';
	  display: block;
	  height: 27px;
	
	}
  div.ListUnmappedTableDiv {
	height: 150px;
	width:958px;
	overflow: auto;
	overflow-x: hidden;
	position: relative;
	}
	.unmappedRule{
	width:958px;
	float:left;
	border:1px solid #898989;;
	font-size: 10px
	}
	.unmappedRuleTable{
	width:938px;
	border: 1px solid #949494;
	background:url(./images/title_bg.gif)repeat-x;
	font-size: 11px
	}
	.create{
	width:410px;
	float:left;
	border:1px solid #898989;
	font-size: 11px
	}
	.locateSpider{
	width:530px;
	float:right;
	border:1px solid #898989;
	font-size: 11px
	}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.dataTables.nightly.js"></script>

<script type="text/javascript">

// OOAMessage Scripts start here 30296...
/*Start : Added for Spider Mapping in EBX*/

// Method to open create page on click of 'Create (+) icon'

function openCreateFromUnmappedSection(ruleId) {
	$('#ruleIdHidden').val(ruleId);
	//var umRuleId = $('#ruleIdHidden').val(ruleId);
	if(ruleId!="")
	{
		document.forms['unmappedRuleForm'].action="${context}/viewcreatemappingpagespider/viewFromUnMapped.html";
        document.forms['unmappedRuleForm'].submit();
        
        /* $.ajax({
				url: "${context}/viewcreatemappingpagespider/viewFromUnMapped.html",
				dataType: "html",
				data: "&ruleId="+encodeURIComponent(ruleId),
				success: function(data) {
				document.forms['unmappedRuleForm'].action= url;
				document.forms['unmappedRuleForm'].submit();
				}
			}); */
    }
}

function spiderPageLoad()
{
	document.getElementById('link3').style.visibility = 'hidden';
}
var list = new Array();
		$(document).ready(
						function() {
							$('#ruleId').blur(
											function() {
												if ($(this).val() == null || $.trim($(this).val()).length < 1) 
												{
													$("#ruleIdLabel").text('');
												}
												$('#ruleId').val($('#ruleId').val().toUpperCase());
											});
							$("#ruleId").autocomplete(
											{
												select : function(event, ui)
												 {
													$("#ruleIdLabel").text(ui.item.id)
															.removeClass('invalid_rule_code_value');
												},

												source : function(request,
														response) {
													$.ajax({
																url : "${context}/ajaxruleid.ajax",
																dataType : "json",
																type : "POST",
																data : "key="
																		+ encodeURIComponent(request.term)
																		+ "&name=unmapped",
																success : function(
																		data) {
																	cache:
																			false,
																			response(data.rows);
																	list = data.rows;
																}
															});
												},
												change : function(event, ui) {
													var text = $(this).val();
													$.ajax({
	
														url: "${context}/viewcreatemappingpagespider/verifyUmRuleExistByRuleId.ajax",
														dataType: "json",
														type: "POST",
														data: "ruleId="+escape(text),
														success: function(data){
															if (!ui.item) {
														displayLabelForSelectedItem(
																text,
																list,
																"ruleIdLabel",
																data.isMapped);
															}
															
														}
													
													});
													
												}

											})
							//sniffer();
						});
						
$(document).ready(function() {
					$('#umRuleIdFrmLocate').blur(
									function() {
										if ($(this).val() == null || $.trim($(this).val()).length < 1) 
										{
											$("#umRuleIdLabel").text('');
										}
										$('#umRuleIdFrmLocate').val($('#umRuleIdFrmLocate').val().toUpperCase());
									});
					$("#umRuleIdFrmLocate").autocomplete(
									{
										select : function(event, ui)
										 {
											$("#umRuleIdLabel").text(ui.item.id)
													.removeClass('invalid_rule_code_value');
										},

										source : function(request,
												response) {
											$.ajax({
														url : "${context}/ajaxumruleidfromlocate.ajax",
														dataType : "json",
														type : "POST",
														data : "key="
																+ encodeURIComponent(request.term)
																+ "&name=unmapped",
														success : function(
																data) {
															cache:
																	false,
																	response(data.rows);
															list = data.rows;
														}
													});
										},
										change : function(event, ui) {
											var text = $(this).val();
											if (!ui.item) {
												displayLabelForSelectedItem(
														text,
														list,
														"umRuleIdLabel",
														invalidUmRule);
											}
										}

									})
					//sniffer();
				});
				
var umRuDescriptionLocatelist = new Array();
$(document).ready(function(){
	$('#umRuDescriptionLocate').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#umRuDescriptionLocateLabel0").text('');
	   }
	   $('#umRuDescriptionLocate').val($('#umRuDescriptionLocate').val().toUpperCase());
	   //sensitiveBenefitCheck();
	});  
	$("#umRuDescriptionLocate").autocomplete({ 
		select: function(event, ui) { $("#umRuDescriptionLocateLabel0").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "${context}/ajaxruleiddescription.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=unmapped",
						success: function(data) {
						cache: false,
						response(data.rows);
						umRuDescriptionLocatelist = data.rows;
						}
					});
				},
		change: function(event, ui) {
			var text = $(this).val();
			if(!ui.item){
				displayLabelForSelectedItem(text,umRuDescriptionLocatelist,"umRuDescriptionLocateLabel0","Invalid Rule Description");
			}
		}
								
	})
  });
	
				
				
var eb03list = new Array();
$(document).ready(function(){
	$('#eb03FrmLocate').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#EB03IdLabel0").text('');
	   }
	   $('#eb03FrmLocate').val($('#eb03FrmLocate').val().toUpperCase());
	   //sensitiveBenefitCheck();
	});  
	$("#eb03FrmLocate").autocomplete({ 
		select: function(event, ui) { $("#EB03IdLabel0").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "${context}/ajaxeb03autocompletelistforebx.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=EB03&searchType=locate",
						success: function(data) {
						cache: false,
						response(data.rows);
						eb03list = data.rows;
						}
					});
				},
		change: function(event, ui) {
			var text = $(this).val();
			if(!ui.item){
				displayLabelForSelectedItem(text,eb03list,"EB03IdLabel0","Invalid EB03 Value");
			}
		}
								
	})
  });
  					
/*End : Added for Spider Mapping in EBX*/
function oOAMessage() {
	$
			.ajax({
				url : "${context}/lockedvariableauditreport/ooaMessageView.ajax",
				dataType : "html",
				type : "POST",
				success : function(data) {
					$("#ooaMessageReportDialog").html(data);
					$("#ooaMessageReportDialog").dialog({
						height : '500',
						width : '690px',
						show : 'slide',
						modal : true,
						resizable : false,
						title : 'Out of Area Message Maintenance'

					});
					$("#ooaMessageReportDialog").dialog();
				}
			});
}
		

function ooaMessageAddDateSegment(networkOrContractCode,
		exchangeIndicator, messageId, search, messageEffectiveDate, messageExpiryDate) {
	// Setting to Add Date Segment to test.
	
	var messageEffectiveDate = onDateCheck(messageEffectiveDate.split("/")[0],messageEffectiveDate.split("/")[1],messageEffectiveDate.split("/")[2]);
	var messageExpiryDate = onDateCheck(messageExpiryDate.split("/")[0],messageExpiryDate.split("/")[1],messageExpiryDate.split("/")[2]);
	
	$
			.ajax({
				url : "${context}/lockedvariableauditreport/oOAMessageAddDateSegment.ajax",
				dataType : "html",
				type : "POST",
				data : "netWorkOrContractCode=" + networkOrContractCode
						+ "&exchangeIndicator=" + exchangeIndicator
						+ "&messageId=" + messageId + "&search="
						+ search
						+"&oOAMessageEffectiveDatePickerName="+messageEffectiveDate 
						+"&oOAMessageExpiryDatePickerName="+messageExpiryDate,
						
				success : function(data) {
					$("#contractBxMappingDialog").dialog('close');
					$("#ooaMessageReportDialog").html(data);
					$("#ooaMessageReportDialog").dialog({
						height : '500',
						width : '690px',
						show : 'slide',
						modal : true,
						resizable : false,
						title : 'OOA Message Add Date Segment'

					});
					$("#ooaMessageReportDialog").dialog();
				}
			});

}
		
function oOAMessageCreateScreen() {
	/* 	 var data: myText, dynamicLabel */
	var noErrors = true;

	var radiosNetworkContract = document.getElementsByName('Search');
	var radiosExchangeExchange = document.getElementsByName('Exchange');
	var networkOrContractVar = "";
	var exchangeIndiCator = "";
	var netWorkOrContractCode = $("#myText").val();

	if (null != radiosNetworkContract) {
		for (var i = 0; i < radiosNetworkContract.length; i++) {
			if (radiosNetworkContract[i].checked) {

				networkOrContractVar = radiosNetworkContract[i].value;

			}

		}
	}
	if (null != radiosExchangeExchange) {
		for (var i = 0; i < radiosExchangeExchange.length; i++) {
			if (radiosExchangeExchange[i].checked) {

				exchangeIndiCator = radiosExchangeExchange[i].value;

			}

		}
	}
	
	if(networkOrContractVar == "Network" && exchangeIndiCator.trim() == "" ){
		
		noErrors =false;
		addErrorToNotificationTray(networkOrContractVar+" Exchange Indicator should be selected");
	}
	
	if(netWorkOrContractCode.trim() == "" ){
		
		noErrors =false;
		addErrorToNotificationTray(networkOrContractVar+" code cannot be Empty");
	}
	
	
	
	if(networkOrContractVar == "Network" && netWorkOrContractCode.length > 8){
				
	addErrorToNotificationTray('Please enter the Network id Max of 8 character');
	noErrors =false;
	}
	
	else if(networkOrContractVar == "Contract" && netWorkOrContractCode.length > 4){
		addErrorToNotificationTray('Please enter the contract code Max of 4 character');
		noErrors =false;
	}
	
	if(noErrors) {
	$.ajax({
				url : "${context}/lockedvariableauditreport/oOAMessageCreateScreen.ajax",
				dataType : "html",
				type : "POST",
				data : "networkOrContractVar=" + networkOrContractVar
						+ "&exchangeIndiCator="
						+ exchangeIndiCator
						+ "&netWorkOrContractCode="
						+ netWorkOrContractCode,
				success : function(data) {
					$("#ooaMessageReportDialog").html(data);
					$("#ooaMessageReportDialog")
							.dialog(
									{
										height : '500',
										width : '690px',
										show : 'slide',
										modal : true,
										resizable : false,
										title : 'Create OOA Message Screen'

									});
					$("#ooaMessageReportDialog").dialog();
				}
			});
	
	} else {
		 openTrayNotification();
	}
}

function datePriorAndEqualCheck(oOAMessageEffectiveDatePickerVar, oOAMessageExpiryDatePickerVar, validDate){
	
	if(validDate){
	var effDateSplit = oOAMessageEffectiveDatePickerVar.split("/");
	var expDateSplit = oOAMessageExpiryDatePickerVar.split("/");
	
	
	if(parseInt(effDateSplit[2]) >= parseInt(expDateSplit[2])){
		if (parseInt(effDateSplit[2]) == parseInt(expDateSplit[2])){
			
		      if(parseInt(effDateSplit[0]) > parseInt(expDateSplit[0])){
		    	  return false;
		      } else if (parseInt(effDateSplit[0]) == parseInt(expDateSplit[0])) {
		    	  
		    	  if(parseInt(effDateSplit[1]) >= parseInt(expDateSplit[1])){
		    		  
		    		  return false;
		    	  }
		    	  
		      }
		       
		} else {
			return false;
			
		}
			
		
		
	} 
	}
	return true;
}
	
function oOAMessageSaveNew() {
	if (imposeMaxLength('oOAMessageTextAreaId', '780', 'Message Length')) {
		document.forms['oOAMessageCreate'].action = "${context}/maintainOOAMessageController/oOAMessageSave.ajax"
		document.forms['oOAMessageCreate'].submit();
	}
}
		
function onDateCheck(monthIN, dateIN, yearIN) {

	var month = parseInt(monthIN, 10) + 1;
	var date = parseInt(dateIN, 10);
	var year = parseInt(yearIN, 10) + 1900;
	if (date < 10) {
		date = "0" + date;
	}
	if (month < 10) {
		month = "0" + month;
	}

	return month + '/' + date + "/" + year;

}
		
function oOAMessageEditScreen(networkOrContractCode, exchangeIndicator,
		messageExempted, messageEffectiveDate, messageExpiryDate,
		currentStatus, messageId, messageTextOne, messageTextTwo,
		messageTextThree, search, workFlowAssosciationStatus) {

	
	var messageEffectiveDate = onDateCheck(messageEffectiveDate.split("/")[0],messageEffectiveDate.split("/")[1],messageEffectiveDate.split("/")[2]);
	var messageExpiryDate = onDateCheck(messageExpiryDate.split("/")[0],messageExpiryDate.split("/")[1],messageExpiryDate.split("/")[2]);
	exchangeIndicator = ((null != exchangeIndicator && "" != exchangeIndicator.trim()) ? exchangeIndicator : messageExempted);
	
	 $.ajax({
				url : "${context}/lockedvariableauditreport/oOAMessageEditScreen.ajax",
				dataType : "html",
				type : "POST",
				data : "oOAMessageTextAreaName=" + encodeURIComponent(messageTextOne + messageTextTwo
				+ messageTextThree) + "&netWorkOrContractName=" + search
				+ "&netWorkOrContractCodeName=" + networkOrContractCode
				+ "&ExcahngeOrExplValIdName=" + exchangeIndicator
				+ "&oOAMessageEffectiveDatePickerName="
				+ messageEffectiveDate + "&oOAMessageExpiryDatePickerName="
				+ messageExpiryDate + "&oldMessageId=" + messageId
				+ "&workFlowAssosciationStatus=" + workFlowAssosciationStatus,
				success : function(data) {
					$("#contractBxMappingDialog").dialog('close');
					$("#ooaMessageReportDialog").html(data);

					$("#ooaMessageReportDialog").dialog({
						height : '500',
						width : '690px',
						show : 'slide',
						modal : true,
						resizable : false,
						title : 'Edit OOA Message Screen'

					});
					$("#ooaMessageReportDialog").dialog();
				}
			}); 
}			
//EWPD
	
function openUserCommentsNotApplicableDialogForDelete(networkOrContractId, messageId, search) 
{
	$("#delNetworkOrContractCode").val(networkOrContractId);
	$("#messageId").val(messageId);
	$("#search").val(search);
	$("#ooaMessageDeleteDialog table#approveUserCommentsTable1").css(
			"border-top", "1px solid black");
	$("#ooaMessageDeleteDialog textarea").text('');	
	$("#ooaMessageDeleteDialog").dialog({
		height : 'auto',
		width : '455px',
		resizable : false,
		show : 'slide',
		title : 'Message Delete Dialog',
		modal : true
	});
	$("#ooaMessageDeleteDialog").dialog(); 
}
					
					
function deleteOOAMessage() {
var networkOrContractCode = $("#delNetworkOrContractCode").val();
var messageId = $("#messageId").val();
var search = $("#search").val();
var userComment = $("#userComment").val();

$
		.ajax({
			url : "${context}/ooamessage/deleteOOAMessage.ajax",
			dataType : "html",
			type : "POST",
			data : "networkOrContractCode=" + networkOrContractCode
					+ "&messageId=" + messageId + "&search="
					+ search +"&userComment="+userComment,
			success : function(data) {
				
				
				var newData = JSON.parse(data);
				 $
					.ajax({
						url : "${context}/lockedvariableauditreport/searchOOAMessage.ajax",
						dataType : "html",
						type : "POST",
						data : "searchCriteria=" + networkOrContractCode
								+ "&search=" + search
								+ "&viewOrSearchFunction=searchFunction"
								+ "&excInd=",
						success : function(data) {
							$("#contractBxMappingDialog").dialog('close');
							$("#ooaMessageDeleteDialog").dialog('close');
							$("#contractBxMappingDialog").html(data);
							$("#contractBxMappingDialog").dialog({
								height : '300',
								width : '950px',
								show : 'slide',
								modal : true,
								resizable : false,
								title : 'OOA Message Search',
								close: function( event, ui ) {
                      					$(".ui-dialog #contractBxMappingDialog #messageViewForCascade").click();
                      					}
							});
							$("#contractBxMappingDialog").dialog();
						}
					});
				/* if(null != newData.deleteStatus && 
						newData.deleteStatus == "") */
				addInfoToNotificationTray("Deleted Successfully");     
				openTrayNotification();
			}
		});
}

// OOAMessage Scripts End here...

function setFocus(elementId)
{
     if(elementId=="create"){
	 document.getElementById('createButton').focus();
	}else{
	 document.getElementById('locateButton').focus();
     }
}
function enterKeyPress(elementId,e)
{
// look for window.event in case event isn't passed in
	if (window.event) { 
		e = window.event; 
		}
	if (e.keyCode == 13){
		if(elementId=="create"){
		 document.getElementById('createButton').click();
		}else{
		 document.getElementById('locateButton').click();
	     }
	  }
 }
function referenceData() {
 	$.ajax({
			url: "${context}/referencedata/showReferenceDataPopup.ajax",
			dataType: "html",
			type: "POST",
			data: "key=/viewlandingewpdbx.html",
			success: function(data) {
				$("#referenceDataDialog").html(data);
				$("#referenceDataDialog").dialog({
					height:'300',
					width:'490px',	
					show:'slide',
					modal: true,
					resizable: false,
					title: 'Referance Data'

				});
				$("#referenceDataDialog").dialog();
			}
		});
}

function contractBx(){
 	$.ajax({
			url: "${context}/vieworexportreport/contractBxMapping.ajax",
			dataType: "html",
			type: "POST",
			success: function(data) {
				$("#contractBxMappingDialog").html(data);
				$("#contractBxMappingDialog").dialog({
					height:'500',
					width:'690px',	
					show:'slide',
					modal: true,
					resizable: false,
					title: 'Reports Mapping'

				});
				$("#contractBxMappingDialog").dialog();
			}
		});
 }
 function auditReport(){
 	$.ajax({
			url: "${context}/lockedvariableauditreport/auditReportView.ajax",
			dataType: "html",
			type: "POST",
			success: function(data) {
				$("#lockAuditReportDialog").html(data);
				$("#lockAuditReportDialog").dialog({
					height:'500',
					width:'690px',	
					show:'slide',
					modal: true,
					resizable: false,
					title: 'Lock Audit Report'

				});
				$("#lockAuditReportDialog").dialog();
			}
		});
 }	


// Method to open create page on click of 'Create Button'

function openCreatePage()
{
	var variableID = $("#ruleId").val();
	var ruleIdLabel = document.getElementById("ruleIdLabel").innerHTML;
	if (variableID == null || variableID == "") {
		addErrorToNotificationTray('Rule ID is mandatory to create mapping');
		openTrayNotification();
	} 
	else if(ruleIdLabel == "UM Rule Already Mapped" ){
		addErrorToNotificationTray('UM Rule Already Mapped');
		openTrayNotification();
	}
	else if(ruleIdLabel == "Invalid UM Rule" ){
		addErrorToNotificationTray('Invalid UM Rule');
		openTrayNotification();
	}else if(ruleIdLabel == "UM Rule Already Excluded"){
	    addErrorToNotificationTray('UM Rule Already Excluded');
		openTrayNotification();
	}
	else {
        document.forms['createform'].submit();
	} 		
}

function openLocateResultsDialogLanding(currentPageNo, page) {

			var umRuleId = $("#umRuleIdFrmLocate").val();
			var eb03 = $("#eb03FrmLocate").val();
			var desc = $("#umRuDescriptionLocate").val();
			var currentPage = currentPageNo;
			var fromWhichSection = page;

			if ((umRuleId == null || umRuleId == "") && (eb03 == null || eb03 == "") && (desc == null || desc == "")) {
				addErrorToNotificationTray('Either UM Rule ID or EB03 value or rule id description is required to locate');
				openTrayNotification();
			} else if((umRuleId != "")){
				$
						.ajax({
							url : "${context}/locatespiderresults/locateRequest.ajax",
							dataType : "html",
							type : "POST",
							data : "umRuleId="
									+ encodeURIComponent(umRuleId)
									+ "&umRuleDescription=" + escape(desc)
									+ "&eb03=" + escape(eb03)
									+ "&currentPage=" + currentPage
									+ "&section=" + fromWhichSection,
							success : function(data) {
								$("#viewLoacteDialog").html(data);
								$("#viewLoacteDialog")
										.dialog(
												{
													height : 'auto',
													width : '750px',
													show : 'slide',
													modal : true,
													resizable : false,
													title : 'Locate Results',
													open : function() {
														
													},
													close : function() {
														
													}
												});
								$("#viewLoacteDialog").dialog();
							}
						});
			}
			else if((desc != "")){
				var umRuleId = document.getElementById("umRuDescriptionLocateLabel0").innerHTML;
				if(umRuleId === undefined || umRuleId == "Invalid Rule Description"){
					alert("Please Enter Valid Description");
					return;
				}
				$.ajax({url : "${context}/locatespiderresults/locateRequest.ajax",
							dataType : "html",
							type : "POST",
							data : "umRuleId="
									+ encodeURIComponent(umRuleId)
									+ "&eb03=" + escape(eb03)
									+ "&umRuleDescription=" + escape(desc)
									+ "&currentPage=" + currentPage
									+ "&section=" + fromWhichSection,
							success : function(data) {
								$("#viewLoacteDialog").html(data);
								$("#viewLoacteDialog")
										.dialog(
												{
													height : 'auto',
													width : '750px',
													show : 'slide',
													modal : true,
													resizable : false,
													title : 'Locate Results',
													open : function() {
														
													},
													close : function() {
														
													}
												});
								$("#viewLoacteDialog").dialog();
							}
						});
			}
			else if(eb03 != "")
			{
			  $.ajax({
							url : "${context}/locatespiderresults/locateRequestForEb03.ajax",
							dataType : "html",
							type : "POST",
							data : "umRuleId=" + escape(umRuleId)
									+ "&eb03=" +encodeURIComponent(eb03)
									+ "&currentPage=" + currentPage
									+ "&section=" + fromWhichSection,
							success : function(data) {
								$("#viewLoacteDialog").html(data);
								$("#viewLoacteDialog")
										.dialog(
												{
													height : 'auto',
													width : '750px',
													show : 'slide',
													modal : true,
													resizable : false,
													title : 'Locate Results',
													open : function() {
														
													},
													close : function() {
														
													}
												});
								$("#viewLoacteDialog").dialog();
							}
						});
			}
		}
		
function createIgnoreUnmappedUmRule( ruleId,ruleVersion,ruleStatus){

	$.ajax({
	
		url: "${context}/viewcreatemappingpagespider/createIgnoreUnmappedUMRule.ajax",
		dataType: "html",
		type: "POST",
		data: "ruleId="+escape(ruleId)+"&ruleVersion="+ruleVersion+"&ruleStatus="+ruleStatus,
		success: function(data){
			alert("UM Rule Ignored");
			location.reload();
			
		}
	
	});
}

	
</script>
<%@ include file="/WEB-INF/jspf/pageTop.jspf"%>

<div class="innerContainer" style="height:100%;">

<div class="titleBar">
<div class="headerTitle">SPIDER - UM Rule EB03 Association Module</div>
</div>
<div class="clear"></div>
<div class="mt10"></div>
<div class="create" style="position: relative; left: -1px;height:42%;">
<div class="titleBar">
<div class="headerTitle">Create</div>
</div>
<form name="createform" action="${context}/viewcreatemappingpagespider/viewFromCreateSpider.html">
					<div class="createMapping">
						<table border="0" cellpadding="0" cellspacing="0"
							class="mt10 mL10 Pd3 shadedText">
							<tr>
								<td width="60px">UM Rule<span class="star">*</span></td>
								<td width="160px"><input type="textbox" class="inputbox65"
									name="selectedRuleId" id="ruleId" /></td>
								<!-- <td><label id="ruleIdLabel" for="selectedRuleId"
									style="font-size: 11px"></label></td> -->
							</tr>
							<tr>
							   <td>&#160;&#160;</td> 
							   <td width="230px"><label id="ruleIdLabel" for="selectedRuleId"
									style="font-size: 11px"></label></td>
							</tr>
							<tr>
								<td>&#160;&#160;</td>
								<td><a href="#" onclick="openCreatePage()"><img
										src="${imageDir}/create_but.gif" width="78" height="23" /></a></td>
							</tr>
						</table>
					</div>
</form>
</div>

<div class="locateSpider" style="height:42%;">
<div class="titleBar">
<div class="headerTitle">Locate</div>
</div>
<form name="locateResultsForm">
<div class="createTable">
<table border="0" cellpadding="0" cellspacing="0"
	class="mt10 mL10 Pd3 shadedText" style="height:45%;">
	<tr>
		<td width="60px">UM Rule<span class="star"></span></td>
		<td width="160px"><input type="textbox" class="inputbox65" id="umRuleIdFrmLocate" name="umRuleIDLocate" 
		/></td>
		<td><label id="umRuleIdLabel" for="selectedUmRuleId"
									style="font-size: 11px"></label></td>   
	</tr>
	<tr>
		<td width="60px">Description</td>
		<td width="160px"><input type="textbox" class="inputbox65" id="umRuDescriptionLocate" name="umRuDescriptionLocate" 
		/></td>   
		<td><label id="umRuDescriptionLocateLabel0" for="selectedUmRuleIdDescription"
									style="font-size: 11px"></label></td> 
	</tr>
	<tr>
		<td width="60px">EB03</td>
		<td width="160px"><input type="textbox" class="inputbox65" id="eb03FrmLocate" name="eb03Locate" 
		/>
		<td><label id="EB03IdLabel0" for="eb03FrmLocate"
									style="font-size: 11px"></label>
							
						</td>   
	</tr>	
	<tr>
		<td>&#160;&#160;&#160;</td>	
		<td><a href="#" id="locateButton" onclick="openLocateResultsDialogLanding('0','fromLanding');"><img src="${imageDir}/locate_but.gif" width="78"
			height="23" /></a></td>
	</tr>
</table>
</div>
</form>
</div>
<br></br>
<div class="clear"></div>
<div class="mt10"></div>

<div class="unmappedRule" style="height:45%;" >
				<!--unmapped Starts-->
				<div class="titleBar">
					<div class="headerTitle">UM Change log<span style="padding-left: 790px; position: right;"><a
					style="color: #000000; font-size: 10;"
					href="${context}/locatespiderresults/generateUnmappedExcel.ajax"
					id="link2"><img src="${imageDir}/file-download.png" width="16"
					height="16" alt="Download Unmapped Rules" title="Download Unmapped Rules" /></a>
					</span></div>
				</div>
				<form name="unmappedRuleForm" >
				<!--action="${context}/viewcreatemappingpagespider/viewFromUnMapped.html"-->
						<input type="hidden" name="ruleIdHidden"
												id="ruleIdHidden" value="" />
					<div class="ListUnmappedTableDiv" id="unmappedTableDiv">
						<table border="0" cellpadding="0" cellspacing="0"
							id="unMappedTable" class="mappedTable Pd3 shadedText">
							<THEAD>
								<tr class="UnmappedVariables unmappedRuleTable" style="position: fixed;white-space:nowrap;">
								    <th id="ruleId" width="15%" class="tableHeader" >
										Rule ID <span id="ruleIcon" class="ui-widget-header"
										style="padding-left: 45px; display: inline-block; background: none; border: 0px; position: right; top: -4px; left: -7px; height: 10px;">
											<span 
											style="position: right; height: 10px;" />
									</span>
									</th>
									<th id="ruleType" width="15%" class="tableHeader" >Rule Type <span
										id="ruleIcon" class="ui-widget-header"
										style="padding-left: 30px; display: inline-block; background: none; border: 0px; position: right; top: -4px; left: -7px; height: 10px;">
											<span 
											style="position: right; height: 10px;" />
									</span>
									</th>
									<th id="ruleVersion" width="10%" class="tableHeader" >
										Version <span id="versionIcon" class="ui-widget-header"
										style="padding-left: 50px; display: inline-block; background: none; border: 0px; position: right; top: -4px; left: -7px; height: 10px;">
											&nbsp;&nbsp;<span 
											style="position: right; height: 10px;" />
									</span>
									</th>
									<th id="ruleDesc" width="33%" class="tableHeader" >
										Description <span id="descIcon" class="ui-widget-header"
										style="padding-left: 50px; display: inline-block; background: none; border: 0px; position: right; top: -4px; left: -7px; height: 10px;">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	<span 
											style="position: right; height: 10px;" />
									</span>
									</th>
									<th id="statusId" width="15%" class="tableHeader" 
										nowrap="nowrap">RMA Status<span id="statusIcon"
										class="ui-widget-header"
										style="padding-left: 50px; display: inline-block; background: none; border: 0px; position: right; top: -4px; left: -7px; height: 10px;">
										&nbsp;&nbsp;	<span 
											style="position: right; height: 10px;" />
									</span>
									</th>
									<td  width="10%" class="tableHeader" nowrap="nowrap">Actions</td>
								</tr>
							</THEAD>
							<TBODY class="unmapped-tbody" style="height:40%;">
								<c:set var="rowCount" value="0" />

								<c:if test="${! empty unmappedRules}">

									<c:forEach items="${unmappedRules}" var="unmappedRul">

										<tr class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
										
											 <%-- <input type="hidden" name="ruleIdHidden"
												id="ruleIdHidden" value="${unmappedRul.umRuleId}" /> --%> 
												
											<td width="15%" >${unmappedRul.umRuleId}</td>
											<td width="15%" >${unmappedRul.umRuleType}</td>
											<td width="10%" >${unmappedRul.defaultVersion}</td>
											
											<td width="33%" style="width: 340px">${unmappedRul.umRuleDesc}</td>
											
											<td width="15%" >${unmappedRul.umStatus}</td>
											
											<td width="12%" nowrap>&#160;&#160;&#160;&#160; <a href="#"
												onclick='openCreateFromUnmappedSection("${unmappedRul.umRuleId}");'>
													<img src="${imageDir}/create_icon.gif" alt="Create UM Rule Mapping"
													title="Create UM Rule Mapping" />
													
											</a>&nbsp;&nbsp;											
											<a  href="#" onclick='createIgnoreUnmappedUmRule("${unmappedRul.umRuleId}","${unmappedRul.defaultVersion}","${unmappedRul.umStatus}");'>
														<img src="${imageDir}/markAsNotApp.gif"
														alt="Mark as Ignore"
														title="Mark as Ignore" />
													</a> </td>
										</tr>
										<c:set var="rowCount" value="${rowCount + 1}" />
									</c:forEach>
								</c:if>
								<c:if test="${empty unmappedRules}">
									<tr>
										<td colspan="5" align="center">No Data Found</td>
									</tr>
								</c:if>
							</TBODY>
						</table>
					</div>
				</form>
				<!--UnmappedVariables Ends-->
			</div>
			
</div>

<div id="viewMappingDialog" title="View Mapping"></div>
<!-- innerContainer Ends-->
<%@ include file="/WEB-INF/jspf/pageFooterEWPD.jspf"%> 

<!-- Start 30296 EBX OOAMESSAGE  -->

<div id="userCommentsScheduleToWGSDialog" style="display: none;">
	<form name="userCommentsScheduleToWGSForm"
		action="${context}/lockedvariableauditreport/scheduleToWGS.html"
		method="POST">
		<input type="hidden" name="networkOrContractCodeWGS"
			id="networkOrContractCodeWGS" value="" /> <input type="hidden"
			name="messageIdWGS" id="messageIdWGS" value="" /> <input type="hidden"
			name="searchWGS" id="searchWGS" value="" /> <input type="hidden"
			name="exchangeIndicator" id="exchangeIndicator" value="" />
			<input type="hidden"
			name="testOrProd" id="testOrProd" value="" />
		<table id="sendToTestUserCommentsTable1" border="0" cellpadding="0"
			cellspacing="0" class="Pd3 mT5 bT">
			<tr class="">
				<td><textarea name="schedule2WGSMappingComments"
						id="schedule2WGSMappingComments" rows="5" cols="77" maxlength="100"></textarea></td>
			</tr>
		</table>
		<table border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td><a href="#" onclick="saveUserCommentsForSchedule2WGS();"><img
						id="sendToTestSaveImg" src="${imageDir}/btn_Save.JPG" alt="Save"
						title="Save" /></a></td>
						<td><a  href="#" name="wgsCancel" id="wgsCancel" onclick="cmtToWgsDailogClose();" >
						<img src="${imageDir}/btn_Cancel.JPG" alt="Cancel" title="Cancel"/>
				</a></td>
			</tr>
		</table>
	</form>
</div>

<div id="ooaMessageDeleteDialog" style="display: none;">
	<form name="ooaMessageDeleteDialogForm"
		action="${context}/lockedvariableauditreport/deleteOOAMessage.html"
		method="POST">
		<input type="hidden" name="delNetworkOrContractCode"
			id="delNetworkOrContractCode" value="" /> <input type="hidden"
			name="messageId" id="messageId" value="" /> <input type="hidden"
			name="search" id="search" value="" />
		  <table id="deleteCommentsTable1" border="0" cellpadding="0"
			cellspacing="0" class="Pd3 mT5 bT">
			<tr class="">
				<td><textarea name="userComment"
						id="userComment" rows="5" cols="77" maxlength="100"></textarea></td>
			</tr>
		</table>
		
		<table border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td><a href="#" onclick="deleteOOAMessage();"><img
						id="sendToTestSaveImg" src="${imageDir}/btn_Save.JPG" alt="Save"
						title="Save" /></a></td>
						<td><a  href="#" name="delCancel" id="delCancel" onclick="cmtDeleteDailogClose();" >
						<img src="${imageDir}/btn_Cancel.JPG" alt="Cancel" title="Cancel"/>
				</a></td>
			</tr>
		</table>
	</form>
</div>

<!-- END 30296 EBX OOAMESSAGE  -->
		
<div id="viewLoacteDialog" title="Locate Results"></div>
<div id="confirmationDivUnlock"></div>
<div id="referenceDataDialog" title="Reference Data"></div>
<div id="ooaMessageReportDialog" title="OOA Message Search Criteria"></div>
<div id="contractBxMappingDialog" title="Contract Mapping"></div>
<div id="lockAuditReportDialog" title="Lock Audit Report"></div>

<%-- <%@include file="/WEB-INF/jspf/messageTray.jspf"%> --%>
</body>
</html>