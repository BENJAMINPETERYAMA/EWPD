<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/jspf/pageHead.jspf"%>
</head>

<body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/simulationInputPage.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/json.js"></script>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="context" value="${pageContext.request.contextPath}" />

<script>

// OOAMessage Scripts start here 30296...

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
		
		function cmtDeleteDailogClose(){
		 $("div[id='ooaMessageDeleteDialog']").parent('.ui-dialog').remove();
		}
		function cmtToWgsDailogClose(){
		 $("div[id='userCommentsScheduleToWGSDialog']").parent('.ui-dialog').remove();
		}
		
	function saveUserCommentsForSchedule2WGS() {
			
			var networkOrContractCode = $(".ui-dialog>div[id='userCommentsScheduleToWGSDialog'] #networkOrContractCodeWGS").val();
			var messageId = $(".ui-dialog>div[id='userCommentsScheduleToWGSDialog'] #messageIdWGS").val();
			var search =$(".ui-dialog>div[id='userCommentsScheduleToWGSDialog'] #searchWGS").val();				
			var testOrProd = $(".ui-dialog>div[id='userCommentsScheduleToWGSDialog'] #testOrProd").val();
			var userComments=$("#schedule2WGSMappingComments").val();
			$
					.ajax({
						url : "${context}/lockedvariableauditreport/scheduleToWGS.ajax",
						dataType : "html",
						type : "POST",
						data : "networkOrContractCode=" + networkOrContractCode
								+ "&messageId=" + messageId + "&search="
								+ search + "&testOrProd=" 
								+ testOrProd + "&userComments="
								+ encodeURIComponent(userComments),
						
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
										$("#userCommentsScheduleToWGSDialog").dialog('close');
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
							addInfoToNotificationTray(search+" "+networkOrContractCode+" is scheduled to "+testOrProd+" Successfully");     
							openTrayNotification();
						}
					});
		}
		 
		 function openUserCommentsSendToWGSDialog(networkOrContractId, messageId,search,test) {
				
				$("#networkOrContractCodeWGS").val(networkOrContractId);
				$("#messageIdWGS").val(messageId);
				$("#searchWGS").val(search);
				$("#testOrProd").val(test);
				$("#userCommentsScheduleToWGSDialog table#approveUserCommentsTable1")
						.css("border-top", "1px solid black");
				$("#userCommentsScheduleToWGSDialog textarea").text('');
				$("#userCommentsScheduleToWGSDialog").dialog({
					height : 'auto',
					width : '455px',
					resizable : false,
					show : 'slide',
					title : 'ScheduleToWGS Dialog',
					modal : true
				});
				$("#userCommentsScheduleToWGSDialog").dialog();
				
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

		function isValidDate(dateString)
		{
		    if(!/^\d{1,2}\/\d{1,2}\/\d{4}$/.test(dateString))
		        return false;

		    var parts = dateString.split("/");
		    var day = parseInt(parts[1], 10);
		    var month = parseInt(parts[0], 10);
		    var year = parseInt(parts[2], 10);

		    if(year < 1900 || month == 0 || month > 12)
		        return false;

		    var monthLength = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];

		    if(year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))
		        monthLength[1] = 29;

		    return day > 0 && day <= monthLength[month - 1];
		};

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
						   $("#ooaMessageReportDialog").dialog('close');
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
			
			
		
		
		function openUserCommentsNotApplicableDialogForDelete(networkOrContractId, messageId, search) {
			
						
			
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


  $(document).ready(function() {   
    $("#startDateX12").datepicker({ showOn: 'button', buttonText: "Select", buttonImage: '${imageDir}/cal.gif', buttonImageOnly: true, changeYear: true, yearRange: '1900:2900' });
    $("#birthDateX12").datepicker({ showOn: 'button', buttonText: "Select", buttonImage: '${imageDir}/cal.gif', buttonImageOnly: true, changeYear: true, yearRange: '1900:2900', maxDate: '+0d' });
    $("#startDateMapping").datepicker({ showOn: 'button', buttonText: "Select", buttonImage: '${imageDir}/cal.gif', buttonImageOnly: true, changeYear: true, yearRange: '1900:2900' });
    $("#startDateInfo").datepicker({ showOn: 'button', buttonText: "Select", buttonImage: '${imageDir}/cal.gif', buttonImageOnly: true, changeYear: true, yearRange: '1900:2900' });
    $("#endDateX12").datepicker({ showOn: 'button', buttonText: "Select", buttonImage: '${imageDir}/cal.gif', buttonImageOnly: true, changeYear: true, yearRange: '1900:2900' });
    $("#endDateInfo").datepicker({ showOn: 'button', buttonText: "Select", buttonImage: '${imageDir}/cal.gif', buttonImageOnly: true, changeYear: true, yearRange: '1900:2900' });
    $("#dateOfBirthInfo").datepicker({ showOn: 'button', buttonText: "Select", buttonImage: '${imageDir}/cal.gif', buttonImageOnly: true, changeYear: true, yearRange: '1900:2900' });
    var selectedSystem = $("#systemInfo").val();
    if(selectedSystem == 'WGS' || selectedSystem == 'STAR'){
 		$('#rowDateOfBirth').show();
 		$('#dateOfBirthInfo').show();
 		$('#lblDateOfBirth').show();
 		$('#hrefDateOfBirth').show();
 		
 	}else
 		{
 			$('#rowDateOfBirth').hide();
 			$('#dateOfBirthInfo').hide();
 			$('#lblDateOfBirth').hide();
 			$('#hrefDateOfBirth').hide();
 		}
          
  });
</script>
<script type="text/javascript">

var loadedBackEndRegionsAsJson = [];
function getStartDatesAndVersion() {
var system = $("#systemName").val();
var environment = $("input[name='environmentError']:checked").val();
$("#contractIDMapping").val($.trim($("#contractIDMapping").val()));
$('#contractIDMapping').val($('#contractIDMapping').val().toUpperCase());		
var contractId = $("#contractIDMapping").val();
		$.ajax({
			url: "${context}/simulation/populateStartDateAndVersionOfContract.ajax",
			dataType: "json",
			type: "POST",
			data: "systemName="+escape(system)+"&contractIDMapping="+escape(contractId)+"&environment="+escape(environment),
			success: function(data) {
			cache: false;
				if (data.systemForContract == 'EWPD') {
					$('#versionNumberMapping').val(data.version);
				}
				$('#startDateMapping').html(data.rows);
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
				$("#systemName").hide();
				$("#systemInfo").hide();
				$("#lockAuditReportDialog").dialog({
					height:'500',
					width:'690px',	
					show:'slide',
					modal: true,
					resizable: false,
					title: 'Lock Audit Report',
					close: function(){ 
			        	$("#systemName").show();
						$("#systemInfo").show();
			        }	
				});
				$("#lockAuditReportDialog").dialog();
			}
		});
 }
 
 

function referenceData() {
 	$.ajax({
			url: "${context}/referencedata/showReferenceDataPopup.ajax",
			dataType: "html",
			type: "POST",
			data: "key=/simulation/viewSimulationRequest.html",
			success: function(data) {
				$("#referenceDataDialog").html(data);
				$("#systemName").hide();
				$("#systemInfo").hide();
				$("#referenceDataDialog").dialog({
					height:'300',
					width:'490px',	
					show:'slide',
					modal: true,
					resizable: false,
					title: 'Referance Data',
					close: function(){ 
			        	$("#systemName").show();
						$("#systemInfo").show();
			        }

				});
				$("#referenceDataDialog").dialog();
			}
		});
}

// validation and 271popup response for X12 
function openResultPopup() {	
		var errorFlag = false;
		var serviceTypecodeX12 = $("#serviceTypecodeX12").val();
		serviceTypecodeX12 = $.trim(serviceTypecodeX12);
		var startDateX12 = $("#startDateX12").val();
		var endDateX12 = $("#endDateX12").val();
		var memberIDX12 = $("#memberIDX12").val();
		memberIDX12 = $.trim(memberIDX12);
		var alphaPrefixX12 = $("#alphaPrefixX12").val();
		alphaPrefixX12 = $.trim(alphaPrefixX12);
		var firstNameX12 = $("#firstNameX12").val();
		firstNameX12 = $.trim(firstNameX12);
		var lastNameX12 = $("#lastNameX12").val();
		lastNameX12 = $.trim(lastNameX12);
		var birthDateX12 = $("#birthDateX12").val();		
		var checked = $('#dependentInfo').attr('checked');
		var dependentInfo;
		var environment = $("input[name='environment']:checked").val();
		var responseFormat = $("input[name='responseFormat']:checked").val();
		var selectedSystem = $("#ddlSystemFor270").val();
		var backEndRegion = $("#ddlBackEndRegionFor270").val();
		var senderID = $("#hidSenderIdFor270").val();
		var numberOfConfigurationsIn270 = $("#hidNumberOfConfigurationsIn270").val();
		
		//var var_name1 =$('input:radio[name=environment]:checked').val();
		
		if (checked){
			var msgValue = $('#dependentInfo').val();
			$('#dependentInfo').val('true');
			dependentInfo=$("#dependentInfo").val();
		} else {
			$('#dependentInfo').val('false');
			dependentInfo=$("#dependentInfo").val();
		}
		
		if (selectedSystem == null || selectedSystem == "") {
			addErrorToNotificationTray('Member id, Alpha Prefix and System are mandatory fields.');			
			errorFlag = true;
		}else{
				if ( (backEndRegion == null || backEndRegion == "") && (parseInt(numberOfConfigurationsIn270) > 1)  ) {
					addErrorToNotificationTray('Please enter the mandatory fields');
					errorFlag = true;
			}
		}	
		
		if(parseInt(numberOfConfigurationsIn270) == 0){
			addErrorToNotificationTray('No Configuration exists to generate the report. Please contact IT support');			
			errorFlag = true;
		}
			
		if((memberIDX12 == null || memberIDX12 == "") || (alphaPrefixX12 == null || alphaPrefixX12 == "") || (selectedSystem == null || selectedSystem == "")){
			addErrorToNotificationTray('Member id, Alpha Prefix and System are mandatory fields.');
			errorFlag = true;
		}else if((firstNameX12 == null || firstNameX12 == "") && (lastNameX12 == null || lastNameX12 == "") && (birthDateX12 == null || birthDateX12 == "")){
			addErrorToNotificationTray('Atleast one of First Name, Last Name, Date of Birth is required.');
			errorFlag = true;
		}else if ( firstNameX12 != "" && (isNumeric(firstNameX12) || !isSpecial(firstNameX12))) {
			addErrorToNotificationTray('Invalid First Name');
			errorFlag = true;
		} else if (lastNameX12 != "" && (isNumeric(lastNameX12) || !isSpecial(lastNameX12))) {
			addErrorToNotificationTray('Invalid Last Name');
			errorFlag = true;
		} else if (!isSpecial(alphaPrefixX12)) {    
			addErrorToNotificationTray('Invalid Alpha Prefix');
			errorFlag = true;
		} else if (!isSpecial(memberIDX12)) {    
			addErrorToNotificationTray('Invalid MemberID');
			errorFlag = true;
		} else if (!isSpecial(serviceTypecodeX12) && !(serviceTypecodeX12 == null || serviceTypecodeX12 == "")) { 
		   	addErrorToNotificationTray('Invalid Service Type Code');
			errorFlag = true;
		}else if(!isEmpty(startDateX12) && !isDate(startDateX12)){
			addErrorToNotificationTray('Invalid Start Date');
            errorFlag = true;
        }else if(!isEmpty(endDateX12) && !isDate(endDateX12)){
			addErrorToNotificationTray('Invalid End Date');
            errorFlag = true;
		}else if(birthDateX12 != "" && (!isDate(birthDateX12))){
			addErrorToNotificationTray('Invalid Date of Birth');
            errorFlag = true;
		}else if(!isGreaterThanTodaysDate(birthDateX12)){
            addErrorToNotificationTray('Date of Birth should be lesser than Today\'s Date');
            errorFlag = true;
		}else if(!isEmpty(endDateX12) && isEmpty(startDateX12)){
            addErrorToNotificationTray('End Date can be entered only if Start date is present.');
            errorFlag = true;
		}else if(!endDateGreaterThanStartDate(startDateX12, endDateX12)){
            addErrorToNotificationTray('End Date should be greater than Start Date.');
            errorFlag = true;
        }
		 	
	if (errorFlag) {
		openTrayNotification();
	} else {
		$.ajax({
			url: "${context}/simulation/get27xHIPAABX.ajax",
			dataType: "html",
			type: "POST",
			data: "serviceTypecodeX12="+serviceTypecodeX12+
			      "&startDateX12="+startDateX12+
			      "&endDateX12="+endDateX12+
			      "&memberIDX12="+memberIDX12+
			      "&alphaPrefixX12="+alphaPrefixX12+
			      "&firstNameX12="+firstNameX12+
			      "&lastNameX12="+lastNameX12+
			      "&birthDateX12="+birthDateX12+
			      "&dependentInfo="+dependentInfo+
			      "&environment="+environment+
			      "&responseFormat="+responseFormat+
			      "&senderID="+senderID,
			success: function(data) {
				$("#get27xHIPAABX").html(data);
				$("#systemName").hide();
				$("#systemInfo").hide();
				$("#get27xHIPAABX").dialog({
					height:'490',
					width:'auto',	
					show:'slide',
					modal: true,
					resizable: true,
					title: '271 Response',
					close: function(){ 
			        	$("#systemName").show();
						$("#systemInfo").show();
			        }
				});				
			}
		});
	 }				
} 

// validation for contract mapping 
function generateErrorReport() {	
	var errorFlag = false;
	var systemName = $("#systemName").val();
	systemName = $.trim(systemName);
	var contractIDMapping = $("#contractIDMapping").val();
	contractIDMapping = $.trim(contractIDMapping);
	var startDateMapping = $("#startDateMapping").val();
	var versionNumberMapping = $("#versionNumberMapping").val();
	var environment = $("input[name='environmentError']:checked").val();
	versionNumberMapping = $.trim(versionNumberMapping);
	
	if (systemName == null || systemName == "") {
		addErrorToNotificationTray('Please enter the mandatory fields');
		errorFlag = true;
	} else if (contractIDMapping == null || contractIDMapping == "") {
		addErrorToNotificationTray('Please enter the mandatory fields');
		errorFlag = true;
	} else if (startDateMapping == null || startDateMapping == "") {
		addErrorToNotificationTray('Please enter the mandatory fields');
		errorFlag = true;
	} else if (isNumeric(systemName) || !isSpecial(systemName)) {
		addErrorToNotificationTray('Invalid System');
		errorFlag = true;
	} else if (systemName.toUpperCase() == 'EWPD') { 
		if (versionNumberMapping == null || versionNumberMapping == "" ) {
			addErrorToNotificationTray('Please enter the mandatory fields');
			errorFlag = true;	
		} else {
			if (!isNumeric(versionNumberMapping)) {
			addErrorToNotificationTray('Invalid  Version Number');
			errorFlag = true;
			}
		}
	}else if(!isEmpty(startDateMapping) && !isDate(startDateMapping)){
			addErrorToNotificationTray('Invalid Start Date');
            errorFlag = true;
	}
	if (errorFlag) {
		openTrayNotification();
	} else {
		validateContractId(contractIDMapping,systemName);
		document.forms['contractMappingValidationForm'].submit();
	}
}

// validation for contract information 
function getContractInfo(){
	var errorFlag = false;
	var systemInfo = $.trim($("#systemInfo").val());
	var contractIdInfo = $("#contractIdInfo").val();
	var startDateInfo = $("#startDateInfo").val();
	var endDateInfo = $("#endDateInfo").val();
	var systemInfo =  $("#systemInfo").val();
	var backEndRegion =  $("#ddlBackEndRegionForStaticReport").val();
	systemInfo = $.trim(systemInfo.toUpperCase());
	var environment = $("input[name='environmentInfo']:checked").val();
	var responseFormat = $("input[name='responseFormatInfo']:checked").val();
		var startDateMapping = $('#startDateMapping').val();
	var numberOfConfigurationsInStaticReport = $("#hidNumberOfConfigurationsInStaticReport").val();
	var dateOfBirthInfo=$("#dateOfBirthInfo").val();
	
	if (systemInfo == null || systemInfo == "") {
		addErrorToNotificationTray('Please enter the mandatory fields');
		errorFlag = true;
	}else{
			//alert('numberOfConfigurationsInStaticReport' +numberOfConfigurationsInStaticReport);
			if(parseInt(numberOfConfigurationsInStaticReport) == 0){
				addErrorToNotificationTray('No Configuration exists to generate the report. Please contact IT support');			
				errorFlag = true;
			}
			else if(parseInt(numberOfConfigurationsInStaticReport) > 1) {
				if(backEndRegion == null || backEndRegion == ""){
					addErrorToNotificationTray('Please enter the mandatory fields');
					errorFlag = true;
				}
			}
	}
	
	if (contractIdInfo == null || contractIdInfo == "") { 
		addErrorToNotificationTray('Please enter the mandatory fields');
		errorFlag = true;
	}else if(!isEmpty(startDateInfo) && !isDate(startDateInfo)){
			addErrorToNotificationTray('Invalid Start Date');
            errorFlag = true;
	}else if(!isEmpty(endDateInfo) && !isDate(endDateInfo)){
			addErrorToNotificationTray('Invalid End Date');
            errorFlag = true;
	}else if(isEmpty(startDateInfo) && !isEmpty(endDateInfo)){
            addErrorToNotificationTray('End date can be entered only if start date is present.');
            errorFlag = true;
	}else if(!isEmpty(startDateInfo) && !isEmpty(endDateInfo)){
		if(!endDateGreaterThanStartDate(startDateInfo,endDateInfo)){
            addErrorToNotificationTray('End date should be greater than start date.');
            errorFlag = true;
		}
	}else if (!(systemInfo == null || systemInfo == "" )){
			if(!(systemInfo == 'ACES' || systemInfo == 'FACETS'|| systemInfo == 'WGS'||systemInfo == 'STAR')){
			addErrorToNotificationTray('System should be WGS/FACETS/ACES/STAR');
			errorFlag = true;
			}
	}
	
	
	if (errorFlag){
	    openTrayNotification();
	} else {		
		if(systemInfo == 'WGS'){	
			validateContractId(contractIdInfo,'ALL');
		}
		 $("#ddlBackEndRegionForStaticReport option").each(
			function(index, option){
			loadedBackEndRegionsAsJson.push({'ddlText': option.text, 'ddlValue': option.value});
			}	
		);
		 $("#hidPopulatedBackEndRegionsAsJson").val(JSON.stringify(loadedBackEndRegionsAsJson));
		document.forms['contractInfoForm'].submit();	
	}
}
    
// validation for version number field in contract mapping
 	function checkVersion(contractMappingValidationForm) {
		checkSystem = document.forms['contractMappingValidationForm'].elements['systemName'].value;
		checkSystem = $.trim(checkSystem.toUpperCase());
		if (checkSystem == 'EWPD') {
			document.forms['contractMappingValidationForm'].elements['versionNumberMapping'].disabled = false;
			document.getElementById('versionSymbol').style.display = "inline";
		} else {
			document.getElementById('versionNumberMapping').value = "";
			document.forms['contractMappingValidationForm'].elements['versionNumberMapping'].disabled = true;
			document.getElementById('versionSymbol').style.display = "none";
	}
}

function contractBx(){
 	$.ajax({
			url: "${context}/vieworexportreport/contractBxMapping.ajax",
			dataType: "html",
			type: "POST",
			success: function(data) {
				$("#contractBxMappingDialog").html(data);
				$("#systemName").hide();
				$("#systemInfo").hide();
				$("#contractBxMappingDialog").dialog({
					height:'500',
					width:'690px',	
					show:'slide',
					modal: true,
					resizable: false,
					title: 'Reports Mapping',
					close: function(){ 
			        	$("#systemName").show();
						$("#systemInfo").show();
			        }
				});
			}
		});
 }
 
 function validateContractId(contractId,system){
 	
 	$.ajax({
			url: "${context}/simulation/validateContractId.ajax",
			dataType: "html",
			type: "POST",
			data: "contractId="+contractId +"&system="+system,
			success: function(data) {
				cache: false,
				showValidateWarning(data);}
		});
 
 }
 
 function showValidateWarning(warning){
 	if(jQuery.trim(warning) == ''){
  		return;
  	}else{
  		addWarnToNotificationTray(warning);
  		openTrayNotification();
  	}
 }
 
 function loadBackEndRegionDescriptionForSystemIn270(){
 	var selectedSystem = $("#ddlSystemFor270").val();
 	var selectedEnvironment = $("input[name='environment']:checked").val();
 	if(selectedSystem != null && selectedSystem != ""){
			 		$.ajax({
					url: "${context}/simulation/loadBackEndRegionBasedOnSystem.ajax",
					dataType: "json",
					type: "POST",			
					data: 	"system="+selectedSystem+
							"&functionality=GENERATE271"+
							"&env="+selectedEnvironment,
					success: function(data) {
						cache: false,
						addMessages(data);
						handleBackEndDescriptionDisplayFor270(data,true);
					}
				});
 	}else{
 		$('#trBackEndRegionFor270').hide();
 		$('#lblBackEndRegionDescriptionFor270').text('');
 		$('#lblBackEndRegionDescriptionFor270').css("color", 'green');
 	}
 }
 
 
 
 function loadBackEndRegionDescriptionForBackEndRegionIn270(){
 	var selectedSystem = $("#ddlSystemFor270").val();
 	var selectedEnvironment = $("input[name='environment']:checked").val();
 	var selectedBackEndRegion = $("#ddlBackEndRegionFor270").val();
 	if(selectedBackEndRegion != null && selectedBackEndRegion != ""){
 		$.ajax({
		url: "${context}/simulation/getBackEndRegionDescription.ajax",
		dataType: "json",
		type: "POST",			
		data: 	"system="+selectedSystem+
				"&functionality=GENERATE271"+
				"&env="+selectedEnvironment+
				"&backEndRegion="+selectedBackEndRegion,
		success: function(data) {
			cache: false,
			addMessages(data);
			handleBackEndDescriptionDisplayFor270(data,false);
		}
		});
 	}else{
 		$('#hidSenderIdFor270').val(-1);	
		$('#lblBackEndRegionDescriptionFor270').text('');
		$('#lblBackEndRegionDescriptionFor270').css("color", 'green');
 	}
 } 
 
 
 	function handleBackEndDescriptionDisplayFor270(data,hideBackEndRegionDropDown){
 		if(data.noConfigurationExists == 'true'){
 			$('#hidNumberOfConfigurationsIn270').val(0);
 			$('#lblBackEndRegionDescriptionFor270').css("color", 'red');
 			$('#lblBackEndRegionDescriptionFor270').text('No Configuration exists'); 			
			//$('#lblBackEndRegionDescriptionFor270').text('No Configuration exists');
			$('#hidSenderIdFor270').val(-1);			
			$('#trBackEndRegionFor270').hide();
		}
		else{
			$('#lblBackEndRegionDescriptionFor270').css("color", 'green');
			var numberOfRows = countProperties(data.backEndRegionDetailsAsJson);
			$('#hidNumberOfConfigurationsIn270').val(numberOfRows);
			if(numberOfRows == 1){
				var backEndRegionDescription = data.backEndRegionDetailsAsJson[0].backEndRegionDescription;
				var senderID = data.backEndRegionDetailsAsJson[0].senderID;
				$('#lblBackEndRegionDescriptionFor270').text(backEndRegionDescription);
				$('#hidSenderIdFor270').val(senderID);
				if(hideBackEndRegionDropDown){
					$('#trBackEndRegionFor270').hide();
				}
			}
			else{
				$('#lblBackEndRegionDescriptionFor270').css("color", 'green');
				$('#lblBackEndRegionDescriptionFor270').text('');
				$('#hidSenderIdFor270').val(-1);
				$('#ddlBackEndRegionFor270').empty();
				var ddlBackEndRegionFor270 = document.getElementById('ddlBackEndRegionFor270');
				ddlEmptyOption = document.createElement("option");
				ddlEmptyOption.value='';
				ddlEmptyOption.text='';
				ddlBackEndRegionFor270.options.add(ddlEmptyOption);
				for(var i=0; i<numberOfRows; i++){
					var backEndRegion = data.backEndRegionDetailsAsJson[i].backEndRegion;					
					ddlOption = document.createElement("option");
					ddlOption.value=backEndRegion;
					ddlOption.text=backEndRegion;
					ddlBackEndRegionFor270.options.add(ddlOption);
				}
				$('#trBackEndRegionFor270').show();
			}							
		}
	}	
 	
 	
 	function countProperties(obj) {
		var prop;
		var propCount = 0;
		for (prop in obj) {
		propCount++;
		}
		return propCount;
	}	
	
	function addMessages(data){
		var infoMessages = data.info_messages;
		var errorMessages = data.error_messages;
		var warningMessages = data.warning_messages;
		var isTrayToBeOpened = "false"; 
		if(typeof(infoMessages) != 'undefined' && infoMessages.length >0) {
			for(i=0; i< infoMessages.length; i++) {
				addInfoToNotificationTray(infoMessages[i]);
			}
			isTrayToBeOpened = "true";		
		}
		if(typeof(errorMessages) != 'undefined' && errorMessages.length >0) {
			for(i=0; i< errorMessages.length; i++) {
				addErrorToNotificationTray(errorMessages[i]);
			}
			isTrayToBeOpened = "true";
		}
		//if(typeof(warningMessages) != 'undefined' && warningMessages.length >0) {
		//	for(i=0; i< warningMessages.length; i++) {
		//		addWarnToNotificationTray(warningMessages[i]);
		//	}
		//}
		if(isTrayToBeOpened == "true"){
			openTrayNotification();
		}
	}	
	
	function clearSelectedBackEndRegionDetailsFor270(){	
		var selectedSystem = $("#ddlSystemFor270").val();
		if(selectedSystem !=null && selectedSystem != ''){
			loadBackEndRegionDescriptionForSystemIn270();
		}else{
			$('#lblBackEndRegionDescriptionFor270').text('');			
			$('#lblBackEndRegionDescriptionFor270').css("color", 'green');			
			$('#hidSenderIdFor270').val(-1);
			$('#trBackEndRegionFor270').hide();
			//$("#ddlSystemFor270").val('');
			//$('#ddlBackEndRegionFor270').empty();		
		}
	}
	
	
	
 function loadBackEndRegionDescriptionForBackEndRegionInStaticReport(){
 	var selectedSystem = $("#systemInfo").val();
 	if(selectedSystem == 'WGS' || selectedSystem == 'STAR'){
 		selectedSystem ='WGS/STAR';
 	}
 	var selectedEnvironment = $("input[name='environmentInfo']:checked").val();
 	var selectedBackEndRegion = $("#ddlBackEndRegionForStaticReport").val();
 	if(selectedBackEndRegion != null && selectedBackEndRegion != ""){
 		$.ajax({
		url: "${context}/simulation/getBackEndRegionDescription.ajax",
		dataType: "json",
		type: "POST",			
		data: 	"system="+selectedSystem+
				"&functionality=STATIC REPORT"+
				"&env="+selectedEnvironment+
				"&backEndRegion="+selectedBackEndRegion,
		success: function(data) {
			cache: false,
			addMessages(data);
			handleBackEndDescriptionDisplayForStaticReport(data,false,"backEndRegion");
		}
		});
 	}else{
 		$('#hidSenderIdForStaticReport').val(-1);
 		$('#lblBackEndRegionDescriptionForStaticReport').css("color", 'green');	
		$('#lblBackEndRegionDescriptionForStaticReport').text('');
		$('#hidSelectedBackEndRegionDescriptionInStaticReport').val('');
		//$("#hidNumberOfConfigurationsInStaticReport").val(0);
 	}
 }
 
 function loadBackEndRegionDescriptionForSystemInStaticReport(){
 	loadedBackEndRegionsAsJson = [];
 	var selectedSystem = $("#systemInfo").val();
 	if(selectedSystem == 'WGS' || selectedSystem == 'STAR'){
 		selectedSystem ='WGS/STAR';
 		$('#rowDateOfBirth').show();
 		$('#dateOfBirthInfo').show();
 		$('#lblDateOfBirth').show();
 		$('#hrefDateOfBirth').show();
 		
 	}else
 		{
 			$('#rowDateOfBirth').hide();
 			$('#dateOfBirthInfo').hide();
 			$('#lblDateOfBirth').hide();
 			$('#hrefDateOfBirth').hide();
 		}
 	var selectedEnvironment = $("input[name='environmentInfo']:checked").val();
 	if(selectedSystem != null && selectedSystem != ""){
			 		$.ajax({
					url: "${context}/simulation/loadBackEndRegionBasedOnSystem.ajax",
					dataType: "json",
					type: "POST",			
					data: 	"system="+selectedSystem+
							"&functionality=STATIC REPORT"+
							"&env="+selectedEnvironment,
					success: function(data) {
						cache: false,
						addMessages(data);
						handleBackEndDescriptionDisplayForStaticReport(data,true,"system");
					}
				});
 	}else{
 		$('#trBackEndRegionForStaticReport').hide();
 		$('#lblBackEndRegionDescriptionForStaticReport').css("color", 'green');
 		$('#lblBackEndRegionDescriptionForStaticReport').text('');
 		$('#hidSelectedBackEndRegionDescriptionInStaticReport').val('');
 	}
 } 
 
 
 	function handleBackEndDescriptionDisplayForStaticReport(data,hideBackEndRegionDropDown,invokedFrom){
 		if(data.noConfigurationExists == 'true'){
 			$('#hidNumberOfConfigurationsInStaticReport').val(0);
 			$('#lblBackEndRegionDescriptionForStaticReport').css("color", 'red');
			$('#lblBackEndRegionDescriptionForStaticReport').text('No Configuration exists');
			$('#hidSelectedBackEndRegionDescriptionInStaticReport').val('No Configuration exists');
			$('#hidSenderIdForStaticReport').val(-1);
			$('#hidSourceEnvironmentStaticReport').val(-1);
			$('#hidDestinationEnvironmentForStaticReport').val(-1);
			$('#trBackEndRegionForStaticReport').hide();
		}
		else{
			var numberOfRows = countProperties(data.backEndRegionDetailsAsJson);
			if(invokedFrom == "system"){
				$('#hidNumberOfConfigurationsInStaticReport').val(numberOfRows);
			}
			//alert('inside else' +numberOfRows);
			if(numberOfRows == 1){				
				var backEndRegionDescription = data.backEndRegionDetailsAsJson[0].backEndRegionDescription;
				var senderID = data.backEndRegionDetailsAsJson[0].senderID;
				var sourceEnvironment = data.backEndRegionDetailsAsJson[0].sourceEnvironment;
				var destinationEnvironment = data.backEndRegionDetailsAsJson[0].destinationEnvironment;
				$('#lblBackEndRegionDescriptionForStaticReport').css("color", 'green');
				$('#lblBackEndRegionDescriptionForStaticReport').text(backEndRegionDescription);
				$('#hidSelectedBackEndRegionDescriptionInStaticReport').val(backEndRegionDescription);
				$('#hidSenderIdForStaticReport').val(senderID);
				$('#hidSourceEnvironmentStaticReport').val(sourceEnvironment);
				$('#hidDestinationEnvironmentForStaticReport').val(destinationEnvironment);
				if(hideBackEndRegionDropDown){
					$('#trBackEndRegionForStaticReport').hide();
				}
			}
			else{
				$('#lblBackEndRegionDescriptionForStaticReport').css("color", 'green');
				$('#lblBackEndRegionDescriptionForStaticReport').text('');
				$('#hidSelectedBackEndRegionDescriptionInStaticReport').val('');
				$('#hidSenderIdForStaticReport').val(-1);
				$('#hidSourceEnvironmentStaticReport').val(-1);
				$('#hidDestinationEnvironmentForStaticReport').val(-1);
				$('#ddlBackEndRegionForStaticReport').empty();
				var ddlBackEndRegionForStaticReport = document.getElementById('ddlBackEndRegionForStaticReport');
				ddlEmptyOption = document.createElement("option");
				ddlEmptyOption.value='';
				ddlEmptyOption.text='';
				ddlBackEndRegionForStaticReport.options.add(ddlEmptyOption);
				for(var i=0; i<numberOfRows; i++){
					var backEndRegion = data.backEndRegionDetailsAsJson[i].backEndRegion;					
					ddlOption = document.createElement("option");
					ddlOption.value=backEndRegion;
					ddlOption.text=backEndRegion;
					ddlBackEndRegionForStaticReport.options.add(ddlOption);					
				}
				$('#trBackEndRegionForStaticReport').show();
			}							
		}
	}	
	
	function clearSelectedBackEndRegionDetailsForStaticReport(){	
		var selectedSystem = $("#systemInfo").val();
		if(selectedSystem !=null && selectedSystem != ''){
			loadBackEndRegionDescriptionForSystemInStaticReport();
		}else{
			$('#lblBackEndRegionDescriptionForStaticReport').css("color", 'green');
			$('#lblBackEndRegionDescriptionForStaticReport').text('');
			$('#hidSelectedBackEndRegionDescriptionInStaticReport').val('');
			$('#hidSenderIdForStaticReport').val(-1);
			$('#trBackEndRegionForStaticReport').hide();
			//$("#systemInfo").val('');
			//$('#ddlBackEndRegionFor270').empty();			
		}
	}	
 
</script>

<%
	String is4010Exists = (String) request.getAttribute("is4010Exists");
	List systemForErrorValidation = (List) request
			.getAttribute("systemForErrorValidation");
	List contractInformationSystemList = (List) request
			.getAttribute("contractInformationSystemList");
	String startDates =(String)request.getAttribute("startDates");		
%>
<%@ include file="/WEB-INF/jspf/pageTop.jspf"%>
<div class="Level1">
<center class="contractin"><!-- X12 division starts -->

<div class="titleBar">
<div class="headerTitle">Contract Information</div>
</div>
<%-- <form name="x12Info" id="x12" action="${context}/simulation/get27xHIPAABX.html" method="POST" style="height: 90%;">
<input type="hidden" id="hidSenderIdFor270"  />
<input type="hidden" id="hidNumberOfConfigurationsIn270"  />
<div align="center" style="height: 100%;">
<table border="0" cellpadding="0" cellspacing="0" id="x12Table"
	width="408">
	<tr class="rowHeight">
		&nbsp;
	</tr>
	<tr class="createEditTable1-Listdata">
		<td align="left" height="28" width="204"><font size="+1">Service
		Type Code </font> <a href="#"
			onclick="openPopUp('Service Type Code ','The service type code for 270. If no value is specified, 270 will be submitted for service type code 30.',event);">
		what is this?</a></td>
		<td height="28" align="left" colspan="2" width="166"><input
			type="text" class="inputbox65" id="serviceTypecodeX12"
			name="serviceTypeCodeX12"></td>
	</tr>
	<tr class="createEditTable1-Listdata">
		<td align="left" height="32" width="204"><font size="+1">Date
		of Service</font><a href="#"
			onclick="openPopUp('Date of Service','The date of service',event);">
		what is this?</a></td>
		
		
	</tr>
	<tr class="createEditTable1-Listdata">
	
			<td  height="32" width="204"><font size="+1">Start Date</font></td>
		
		<td align="left" colspan="2"><input type="text"
			class="inputbox65" id="startDateX12" name="startDateX12"
			style="margin-right: 6px"> <br>
		<font face="Verdana" color="black"><span
			style="font-size: xx-small">(mm/dd/yyyy)</span></font></td>
	</tr>
	<tr class="createEditTable1-Listdata">
	
		<td height="32" width="204"><font size="+1">End Date</font></td>
		
		<td align="left" colspan="2"><input type="text"
			class="inputbox65" id="endDateX12" name="endDateX12"
			style="margin-right: 6px"> <br>
		<font face="Verdana" color="black"><span
			style="font-size: xx-small">(mm/dd/yyyy)</span></font></td>
	</tr>
	<tr class="createEditTable1-Listdata">
		<td align="left" height="31" width="204"><font size="+1">Dependent
		Information</font> <a href="#"
			onclick="openPopUp('Dependent Information','If this check box is cheked, the member information entered will be taken as dependent information.',event);">what
		is this?</a>&#160;</td>
		<td align="left" colspan="2"><input type="checkbox"
			name="dependentInfo" id="dependentInfo"></td>
	</tr>
	<tr class="createEditTable1-Listdata">
		<td align="left" height="36" width="204"><font size="+1">Member
		ID *</font><a href="#"
			onclick="openPopUp('Member ID','The member ID.',event);"> what is
		this?</a></td>
		<td align="left" colspan="2" width="166"><input type="text"
			class="inputbox65" id="memberIDX12" name="memberIDX12"></td>
	</tr>
	<tr class="createEditTable1-Listdata">
		<td align="left" height="32" width="204"><font size="+1">Alpha
		Prefix *</font><a href="#"
			onclick="openPopUp('Alpha Prefix','The alpha prefix for the member ID.',event);">
		what is this?</a></td>
		<td align="left" colspan="2" width="166"><input type="text"
			class="inputbox65" id="alphaPrefixX12" name="alphaPrefixX12">
		</td>
	</tr>
	<tr class="createEditTable1-Listdata">
		<td align="left" height="4" width="204"><font size="+1">First
		Name </font><a href="#"
			onclick="openPopUp('First Name','The first name.',event);"> what
		is this?</a></td>
		<td align="left" colspan="2" width="166"><input type="text"
			class="inputbox65" id="firstNameX12" name="firstNameX12"></td>
	</tr>
	<tr class="createEditTable1-Listdata">
		<td align="left" height="26" width="204"><font size="+1">Last
		Name </font> <a href="#"
			onclick="openPopUp('Last Name','The last name.',event);">what is
		this?</a></td>
		<td align="left" colspan="2" width="166"><input type="text"
			class="inputbox65" id="lastNameX12" name="lastNameX12"></td>
	</tr>
	<tr class="createEditTable1-Listdata">
		<td align="left" height="27" width="204"><font size="+1">Date
		of Birth </font><a href="#"
			onclick="openPopUp('Date of Birth','The date of birth.',event);">
		what is this?</a></td>
		<td align="left" colspan="2" width="166"><input type="text"
			class="inputbox65" id="birthDateX12" name="birthDateX12"
			style="margin-right: 6px"> <br>
		<font face="Verdana" color="black"><span
			style="font-size: xx-small">(mm/dd/yyyy)</span></font></td>
	</tr>
	<tr class="createEditTable1-Listdata">
		<td align="left" height="27" width="204"><font size="+1">Environment</font><a
			href="#"
			onclick="openPopUp('Environment','Specify the region to run the 270.',event);">
		what is this?</a><br>
		</td>
		<td align="left" width="90"><input type="radio"
			name="environment" id="environment" value="Production"
			onclick="clearSelectedBackEndRegionDetailsFor270();"> <font
			size="+1">Production</font></td>
		<td align="left"><input type="radio" name="environment"
			id="environment" value="Test" checked
			onclick="clearSelectedBackEndRegionDetailsFor270();"> <font
			size="+1">Test</font></td>
	</tr>


	<tr class="createEditTable1-Listdata">
		<td align="left" height="27" width="204"><font size="+1">System
		*</font><a href="#"
			onclick="openPopUp('Environment','The system the report will be generated from. Valid inputs ACES/FACETS/WGS/STAR/OTHER.',event);">
		what is this?</a><br>
		</td>
		<td align="left" colspan="2"><select id="ddlSystemFor270"
			onchange="loadBackEndRegionDescriptionForSystemIn270();" style="font-family: Arial, sans-serif;font-size:12px;">
			<option value=""></option>
			<option value="ACES">ACES</option>
			<option value="FACETS">FACETS</option>
			<option value="WGS/STAR">WGS/STAR</option>
			<option value="OTHER">OTHER</option>
		</select></td>
	</tr>

	<tr class="createEditTable1-Listdata" id="trBackEndRegionFor270"
		style="display: none;">
		<td align="left" height="27" width="204"><font size="+1">Back
		End Region *</font><a href="#"
			onclick="openPopUp('Environment','Specify the Back End Region to run the 270.',event);">
		what is this?</a><br>
		</td>
		<td align="left" colspan="2"><select id="ddlBackEndRegionFor270"
			onchange="loadBackEndRegionDescriptionForBackEndRegionIn270();">
		</select></td>
	</tr>

	<tr class="createEditTable1-Listdata">
		<td align="left" height="27" width="204">
		<%
			if (null != is4010Exists && "" != is4010Exists
					&& "true".equals(is4010Exists)) {
		%>
		<font size="+1">Response Format</font><a href="#"
			onclick="openPopUp('Response Format','Specify the Response format.',event);">
		what is this?</a><br>
		</td>
		<td align="left" width="90"><input type="radio"
			name="responseFormat" id="responseFormat" value="4010"> <font
			size="+1">4010</font></td>
		<td align="left"><input type="radio" name="responseFormat"
			id="responseFormat" value="5010" checked> <font size="+1">5010</font>
		<%
			}
		%>
		</td>
	</tr>

	<tr class="createEditTable1-Listdata">
		<td align="right" colspan="3">
		<label id="lblBackEndRegionDescriptionFor270" class="backEndDecription" ></label></td>
	</tr>

	<tr>
		<td colspan="3" height="33">
			<a href="#" onclick="openResultPopup()">
				<img border="0" src="${imageDir}/Generate271.GIF" width="128" 
					height="23" style="padding-top: 12px; padding-bottom: 8px;">
			</a>
		</td>
	</tr>
</table>
</div>
<input type="hidden" id="is4010Existsx12" name="is4010Existsx12"
	value="<%=is4010Exists%>" /></form>  --%>
<form name="contractInfoForm"
	action="${context}/simulation/getContractBenefitInfo.html"
	method="POST">
	<input type="hidden" id="hidSenderIdForStaticReport" name="staticReportSenderId" />
	<input type="hidden" id="hidSourceEnvironmentStaticReport" name="sourceEnvironmentStaticReport" />
	<input type="hidden" id="hidDestinationEnvironmentForStaticReport" name="destinationEnvironmentForStaticReport" />
	<input type="hidden" id="hidNumberOfConfigurationsInStaticReport"  name="numberOfConfigurationsLoadedInStaticReport"/>
	<input type="hidden" id="hidSelectedBackEndRegionDescriptionInStaticReport" name="selectedBackEndRegionDescriptionInStaticReport"/>
	<input type="hidden" id="hidPopulatedBackEndRegionsAsJson" name="populatedBackEndRegionsAsJson"/>
<table width="391">
	<tr class="createEditTable1-Listdata" align="left">
		<td align="left" width="220" height="25"><font color="black">
		System *<a href="#"
			onclick="openPopUp('System','The system the report will be generated from. Valid inputs WGS/ACES/FACETS.',event);">
		what is this?</a></font></td>
		<td align="left" colspan="2" height="25" width="242px"><!--<input type="text"
			class="inputbox65" id="systemInfo" name="systemInfo"></td>--> <select
			class="dropdown135" id="systemInfo" name="systemInfo"
			onchange="loadBackEndRegionDescriptionForSystemInStaticReport();">
			<option value="">&#160</option>
			<%
				for (int i = 0; i < contractInformationSystemList.size(); i++) {
			%>
			<option value="<%=contractInformationSystemList.get(i)%>">
			<%=contractInformationSystemList.get(i)%></option>
			<%
				}
			%>
		</select>		
	</td>
	</tr>
	<tr class="createEditTable1-Listdata" align="left">
		<td align="left" width="220" height="25"><font color="black">
		Contract ID *<a href="#"
			onclick="openPopUp('Contract ID','The contract ID',event);"> what
		is this?</a></font></td>
		<td align="left" colspan="2" height="25"><input type="text"
			class="inputbox65" id="contractIdInfo" name="contractIdInfo"></td>
	</tr>
	<tr class="createEditTable1-Listdata" align="left">
		<td align="left" width="220" height="25"><font color="black">Start
		Date <a href="#"
			onclick="openPopUp('Start Date','The Contract start date.',event);">
		what is this?</a></font></td>
		<td align="left" colspan="2" height="25"><input type="text"
			class="inputbox65" id="startDateInfo" name="startDateInfo"
			style="margin-right: 6px"><br>
		<font size="6" face="Verdana" color="black"><span
			style="color: black; font-size: smaller"><span
			style="font-weight: 100"><span style="font-size: xx-small">(mm/dd/yyyy)</span></span></span></font></td>
	</tr>
	
	<tr class="createEditTable1-Listdata" align="left">
		<td align="left" width="220" height="25"><font color="black">End
		Date <a href="#"
			onclick="openPopUp('End Date','The Contract end date.',event);">
		what is this?</a></font></td>
		<td align="left" colspan="2" height="25"><input type="text"
			class="inputbox65" id="endDateInfo" name="endDateInfo"
			style="margin-right: 6px"><br>
		<font size="6" face="Verdana" color="black"><span
			style="color: black; font-size: smaller"><span
			style="font-weight: 100"><span style="font-size: xx-small">(mm/dd/yyyy)</span></span></span></font></td>
	</tr>
	<tr class="createEditTable1-Listdata" align="left" id="rowDateOfBirth">
		<td align="left" width="220" height="25"><label id="lblDateOfBirth"><font color="black">Date of Birth</font> </label><a id="hrefDateOfBirth" href="#"
			onclick="openPopUp('Date of Birth','The Date of Birth.',event);">
		what is this?</a></td>
		<td align="left" colspan="2" height="25"><input type="text"
			class="inputbox65" id="dateOfBirthInfo" name="dateOfBirthInfo"
			style="margin-right: 6px"><br>
		<font size="6" face="Verdana" color="black"><span
			style="color: black; font-size: smaller"><span
			style="font-weight: 100"><span style="font-size: xx-small">(mm/dd/yyyy)</span></span></span></font></td>
	</tr>
	
	<tr class="createEditTable1-Listdata" align="left">
		<td align="left" width="220" height="25"><font color="black">
		Service Type Code <a href="#"
			onclick="openPopUp('Service Type Code','The service type code for 270. If no value is specified, 270 will be submitted for service type code 30.',event);">
		what is this?</a></font></td>
		<td align="left" colspan="2" height="25"><input type="text"
			class="inputbox65" id="serviceTypeCodeInfo"
			name="serviceTypeCodeInfo"></td>
	</tr>
	<tr class="createEditTable1-Listdata">
		<td align="left" height="25" width="220"><font size="+1">Environment</font><a
			href="#"
			onclick="openPopUp('Environment','Specify the region to run the report.',event);">
		what is this?</a><br>
		</td>
		<td align="left" height="25"><input type="radio"
			name="environmentInfo" id="environmentInfo" value="Production"
			onclick="clearSelectedBackEndRegionDetailsForStaticReport();">
		<font size="+1">Production</font></td>
		<td align="left" height="25"><input type="radio"
			name="environmentInfo" id="environmentInfo" value="Test" checked
			onclick="clearSelectedBackEndRegionDetailsForStaticReport();">
		<font size="+1">Test</font></td>
	</tr>
	<tr class="createEditTable1-Listdata"
		id="trBackEndRegionForStaticReport" style="display: none;">
		<td align="left" height="27" width="204"><font size="+1">Back
		End Region *</font><a href="#"
			onclick="openPopUp('Environment','Specify the Back End Region to run the 270.',event);">
		what is this?</a><br>
		</td>
		<td align="left" colspan="2"><select
			id="ddlBackEndRegionForStaticReport" name="backEndRegionSelectedInStaticReport"
			onchange="loadBackEndRegionDescriptionForBackEndRegionInStaticReport();" style="font-family: Arial, sans-serif;font-size:12px;">
		</select></td>
	</tr>

	<tr class="createEditTable1-Listdata">
		<td align="left" height="25" width="220">
		<%
			if (null != is4010Exists && "" != is4010Exists
					&& "true".equals(is4010Exists)) {
		%>
		<font size="+1">Response Format</font> <a href="#"
			onclick="openPopUp('Response Format','Specify the Response format.',event);">
		what is this?</a><br>
		</td>
		<td align="left" height="25"><input type="radio"
			name="responseFormatInfo" id="responseFormatInfo" value="4010">
		<font size="+1">4010</font></td>
		<td align="left" height="25"><input type="radio"
			name="responseFormatInfo" id="responseFormatInfo" value="5010"
			checked> <font size="+1">5010</font> <%
 	}
 %>
		</td>
	</tr>

	<tr class="createEditTable1-Listdata">
		<td align="right" colspan="3"><label
			id="lblBackEndRegionDescriptionForStaticReport" class="backEndDecription"></label></td>
	</tr>

	<tr>
		<td colspan="3" align="center" height="33"><a href="#"
			onclick="getContractInfo()">
			<img border="0" src="${imageDir}/ContractInfo.GIF" width="128" height="23" style="padding-top: 4px;"></a></td>
	</tr>
</table>
<input type="hidden" id="is4010Exists" name="is4010Exists"
	value="<%=is4010Exists%>"></form>
<div id="get27xHIPAABX" title="271 Response" style="width: 725px !important; overflow-x: hidden; overflow-y: hidden;"></div>
</center>

<center class="contractmapping"><!-- contract mappping division starts -->
<div class="titleBar">
<div class="headerTitle">Contract Mapping Validation</div>
</div>
<form name="contractMappingValidationForm"
	action="${context}/simulation/getContractMappingValidationInfo.html"
	method="POST">
<table id="contractmappingTable" border="0" cellpadding="0"
	cellspacing="0" width="391">
	<tr class="createEditTable1-Listdata" align="left">
		<td align="left" nowrap height="25" width="230"><font size="+1">System
		* </font><a href="#"
			onclick="openPopUp('System','The system where the contract is residing in.',event);">
		what is this?</a></td>
		<td nowrap height="25" colspan="2" align="left" width="161"><!-- <input type="text"
				class="inputbox65" id="systemName" name="systemName" onchange="checkVersion()"> -->
		<select class="dropdown135" id="systemName" name="systemName"
			onchange="checkVersion();getStartDatesAndVersion()">
			<option value="">&#160</option>
			<%
				for (int i = 0; i < systemForErrorValidation.size(); i++) {
			%>
			<option value="<%=systemForErrorValidation.get(i)%>"><%=systemForErrorValidation.get(i)%></option>
			<%
				}
			%>
		</select></td>
	</tr>
	<tr class="createEditTable1-Listdata" align="left">
		<td align="left" nowrap width="230"><font size="+1">Contract
		ID *</font><a href="#"
			onclick="openPopUp('Contract ID','The contract ID',event);"> what
		is this?</a></td>
		<td nowrap align="left" colspan="2" width="161"><input type="text"
			class="inputbox65" id="contractIDMapping" name="contractIDMapping" onchange="getStartDatesAndVersion()"></td>
	</tr>
	<tr class="createEditTable1-Listdata" align="left">
			<td align="left"  width="230"><font color="black">Start
			Date *<a href="#" onclick="openPopUp('Start Date','The date segment start date.',event);"> what is this?</a></font></td>
			<td nowrap  align="left" colspan="2" width="161">
				<select class="dropdown135" id="startDateMapping" name="startDateMapping">
				<%if(startDates !=null) {out.println(startDates);%>
				
				<%}else{ %>
				<option value = ""> &#160</option>
				<% }%>
					
				</select>
			</td>
		</tr>
	<tr class="createEditTable1-Listdata" align="left">
		<td align="left" nowrap height="25" width="230"><font size="+1">Version
		Number <span id="versionSymbol" style="display: none; width: 20px;">*</span></font><a
			href="#"
			onclick="openPopUp('Version Number','The version number of the contract if it is eWPD contract.',event);"><font
			size="+1"></font> what is this?</a>&#160;<a href="#"></a></td>
		<td nowrap height="25" colspan="2" align="left"width="161"><input
			type="text" class="inputbox65" id="versionNumberMapping"
			name="versionNumberMapping" disabled="disabled"></td>
	</tr>
	<!-- Added as part of BXNI requirement -->
		<tr class="createEditTable1-Listdata">
			<td align="left" height="27" width="204">
				<font size="+1">Environment</font><a href="#" onclick="openPopUp('Environment','Specify the region to run the Contract Mapping Validation.',event);"> what is this?</a><br>
			</td>
			<td align="left" width="90">
				<input type="radio" name="environmentError" id="environmentError" value="Production" onclick="getStartDatesAndVersion()" checked>
				<font size="+1">Production</font>
			</td>
			<td align="left">
				<input type="radio" name="environmentError" id="environmentError"  value="Test" onclick="getStartDatesAndVersion()">
				<font size="+1">Test</font>
			</td>
		</tr>
		
		<!-- Ends BXNI requirememnt -->
	<tr>
		<td colspan="3" align="center" height="25" vAlign="bottom" style="padding-bottom: 5px; padding-top: 15px;" colSpan="2">
		<a href="#" onclick="generateErrorReport()"> <img border="0"
			src="${imageDir}/Error_report.GIF" width="128" height="23" /> </a></td>
	</tr>
</table>
<input type="hidden" id="is4010Exists" name="is4010Exists"
	value="<%=is4010Exists%>"></form>
</center>
<div id="dialog"></div>
<!-- what is this link pop up -->
<div class="space"></div>
<!-- space between the contract mapping and contract info division -->

<center class="x12"><!-- contract information division starts -->
<div class="titleBar">
<div class="headerTitle">270/271 X12 Information</div>
</div>
<div style="height: 107px; font-size: large; text-align: center; padding-top: 50px;">
<a style="color: #064ceb; text-decoration: underline; font-size: large;" href="<c:out value="${newBXUrl}"/>" target="_blank">Click here</a> to access 270/271 X12 Simulation.<br>
</div>
</center>
</div>
<%@include file="/WEB-INF/jspf/pageFooterSimulation.jspf"%>
<%@include file="/WEB-INF/jspf/messageTray.jspf"%>
<div id="referenceDataDialog" title="Reference Data"></div>
<div id="contractBxMappingDialog" title="Contract Mapping"></div>
<div id="lockAuditReportDialog" title="Lock Audit Report"></div>
<c:if test="${not empty error_messages}">
	<c:if test="<%=request.getAttribute(\"environment_info\")!=null%>">
		<script>
                  $("[name=environmentInfo]").filter("[value=<%=request.getAttribute("environment_info")%>]").attr("checked","checked"); 
            </script>
	</c:if>
	<c:if test="<%=request.getAttribute(\"responseformat_info\")!=null%>">
		<script>
                  $("[name=responseFormatInfo]").filter("[value=<%=request.getAttribute("responseformat_info")%>]").attr("checked","checked"); 
            </script>
	</c:if>

	<c:if test="<%=request.getAttribute(\"system_val\")!=null%>">
		<script>
                  document.getElementById('systemName').value = "<%=request.getAttribute("system_val")%>"; 
            </script>
	</c:if>
	<c:if test="<%=request.getAttribute(\"system_info\")!=null%>">
		<script>
                  document.getElementById('systemInfo').value = "<%=request.getAttribute("system_info")%>"; 
            </script>
	</c:if>
	<c:if test="<%=request.getAttribute(\"service_Type_Code_val\")!=null%>">
		<script>
                  document.getElementById('serviceTypeCodeInfo').value = "<%=request.getAttribute("service_Type_Code_val")%>"; 
            </script>
	</c:if>
	<c:if test="<%=request.getAttribute(\"contract_id\")!=null%>">
		<script>
                  document.getElementById('contractIDMapping').value = "<%=request.getAttribute("contract_id")%>"; 
            </script>
	</c:if>
	<c:if test="<%=request.getAttribute(\"eff_date\")!=null%>">
		<script>
                  document.getElementById('startDateMapping').value = "<%=request.getAttribute("eff_date")%>"; 
            </script>
	</c:if>
	<script>
      checkVersion();
      </script>
	<c:if test="<%=request.getAttribute(\"ver_num\")!=null%>">
		<script>
                  document.getElementById('versionNumberMapping').value = "<%=request.getAttribute("ver_num")%>";
            </script>
	</c:if>
	<c:if test="<%=request.getAttribute(\"contract_Id_val\")!=null%>">
		<script>
                  document.getElementById('contractIdInfo').value = "<%=request.getAttribute("contract_Id_val")%>";
            </script>
	</c:if>
	<c:if test="<%=request.getAttribute(\"start_Date_val\")!=null%>">
		<script>
                  document.getElementById('startDateInfo').value = "<%=request.getAttribute("start_Date_val")%>";
            </script>
	</c:if>
	
	<c:if test="<%=request.getAttribute(\"birth_Date_val\")!=null%>">
		<script>
                  document.getElementById('dateOfBirthInfo').value = "<%=request.getAttribute("birth_Date_val")%>";
            </script>
	</c:if>
	
		<c:if test="<%=request.getAttribute(\"end_Date_val\")!=null%>">
		<script>
                  document.getElementById('endDateInfo').value = "<%=request.getAttribute("end_Date_val")%>";
            </script>
	</c:if>
	
	
	
</c:if>

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

<div id="ooaMessageReportDialog" title="OOA Message Search Criteria">
</div>

	<!-- END 30296 EBX OOAMESSAGE  -->


</body>

<script type="text/javascript">
	var backEndRegionSelected = "${backEndRegionSelected}";
	var selectedBackEndRegionDescription = "${selectedBackEndRegionDescription}";
	var numberOfConfigurationsInStaticReport = "${numberOfConfigurationsLoadedInStaticReport}";
	var selectedSenderID = "${senderID}";
	var selectedSourceEnvironment = "${sourceEnvironment}";
	var selectedDestinationEnvironment = "${destinationEnvironment}";
	
	$('#lblBackEndRegionDescriptionForStaticReport').css("color", 'green');
	$('#lblBackEndRegionDescriptionForStaticReport').text(selectedBackEndRegionDescription);
	$('#hidSelectedBackEndRegionDescriptionInStaticReport').val(selectedBackEndRegionDescription);
	$('#lblBackEndRegionDescriptionForStaticReport').show();
	$('#hidNumberOfConfigurationsInStaticReport').val(numberOfConfigurationsInStaticReport);
	$('#hidSenderIdForStaticReport').val(selectedSenderID);
	$('#hidSourceEnvironmentStaticReport').val(selectedSourceEnvironment);
	$('#hidDestinationEnvironmentForStaticReport').val(selectedDestinationEnvironment);		
		
	if ( backEndRegionSelected != null && backEndRegionSelected != ''){	
		var loadedBackEndRegions = JSON.parse('${populatedBackEndRegionsAsJson}');
		var numberOfLoadedBackEndRegions = countProperties(loadedBackEndRegions);
		if(numberOfLoadedBackEndRegions > 1){
			var ddlBackEndRegionForStaticReport = 
			document.getElementById('ddlBackEndRegionForStaticReport');
			for(var i=0; i<numberOfLoadedBackEndRegions; i++){
				ddlEmptyOption = document.createElement("option");
				ddlEmptyOption.value=loadedBackEndRegions[i].ddlValue;
				ddlEmptyOption.text=loadedBackEndRegions[i].ddlText;
				ddlBackEndRegionForStaticReport.options.add(ddlEmptyOption);
			}
			$('#ddlBackEndRegionForStaticReport').val(backEndRegionSelected);
	 		$('#trBackEndRegionForStaticReport').show();
		}				
	}
</script>

</html>
