<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page session="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/jspf/pageHead.jspf"%> 
</head> 
<body>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="context" value="${pageContext.request.contextPath}"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/umRuleUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.dataTables.nightly.js"></script>

<script type="text/javascript">

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
			
			var networkOrContractCode = $("#networkOrContractCodeWGS").val();
			var messageId = $("#messageIdWGS").val();
			var search = $("#searchWGS").val();				
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

function setFocus(elementId)
{
     if(elementId=="create"){
	 document.getElementById('createButton').focus();
	}else{
	 document.getElementById('locateButton').focus();
     }
}
function enterKeyPress(elementId,e){
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
$(document).ready(function(){

   $("#spsIdUnmapped").click(function(event){
         $('#spsheaderIconUnmapped').attr('style','display : block, background : none');
         $('#descriptionIconUnmapped').attr('style','display : none');
         $('#createdIconUnmapped').attr('style','display : none');
    });
   $("#descriptionUnmapped").click(function(event){
         $('#spsheaderIconUnmapped').attr('style','display : none');
         $('#descriptionIconUnmapped').attr('style','display : block, background : none');
         $('#createdIconUnmapped').attr('style','display : none');
   });
   $("#createdUnmapped").click(function(event){
         $('#spsheaderIconUnmapped').attr('style','display : none');
         $('#descriptionIconUnmapped').attr('style','display : none');
         $('#createdIconUnmapped').attr('style','display : block, background : none');
   });
   
   $("#spsheaderId").click(function(event){
         $('#spsheaderIcon').attr('style','display : block, background : none');
         $('#descriptionIcon').attr('style','display : none');
         $('#updatedIcon1').attr('style','display : none');
         $('#userIcon1').attr('style','display : none');
   });
   
   $("#descriptionId").click(function(event){
         $('#spsheaderIcon').attr('style','display : none');
         $('#descriptionIcon').attr('style','display : block, background : none');
         $('#updatedIcon1').attr('style','display : none');
         $('#userIcon1').attr('style','display : none');
   });
   
   $("#updatedId1").click(function(event){
         $('#spsheaderIcon').attr('style','display : none');
         $('#descriptionIcon').attr('style','display : none');
         $('#updatedIcon1').attr('style','display : block, background : none');
         $('#userIcon1').attr('style','display : none');
   });

   $("#userId").click(function(event){
         $('#spsheaderIcon').attr('style','display : none');
         $('#userIcon1').attr('style','display : block, background : none');
         $('#updatedIcon1').attr('style','display : none');
         $('#descriptionIcon').attr('style','display : none');
   });
   
   $("#ruleEwpdId").click(function(event){
         $('#ruleIcon').attr('style','display : block, background : none');
         $('#spsIcon').attr('style','display : none');
         $('#messageIcon').attr('style','display : none');
         $('#updatedIcon2').attr('style','display : none');
          $('#userIcon2').attr('style','display : none');
   });
   
   $("#spsEwpdId").click(function(event){
         $('#ruleIcon').attr('style','display : none');
         $('#spsIcon').attr('style','display : block, background : none');
         $('#messageIcon').attr('style','display : none');
         $('#updatedIcon2').attr('style','display : none');
         $('#userIcon2').attr('style','display : none');
   });
   
   $("#messageId").click(function(event){
          $('#ruleIcon').attr('style','display : none');
         $('#spsIcon').attr('style','display : none');
         $('#messageIcon').attr('style','display : block, background : none');
         $('#updatedIcon2').attr('style','display : none');
         $('#userIcon2').attr('style','display : none');
   });
   
   $("#updatedId2").click(function(event){
         $('#ruleIcon').attr('style','display : none');
         $('#spsIcon').attr('style','display : none');
         $('#messageIcon').attr('style','display : none');
         $('#updatedIcon2').attr('style','display : block, background : none');
         $('#userIcon2').attr('style','display : none');
   });
   $("#userId2").click(function(event){
         $('#ruleIcon').attr('style','display : none');
         $('#spsIcon').attr('style','display : none');
         $('#messageIcon').attr('style','display : none');
         $('#updatedIcon2').attr('style','display : none');
         $('#userIcon2').attr('style','display : block, background : none');
   });
  
   
   
 
 });

 $(document).ready(function() {
   
        		
	    		
	    		$('#spsheaderIconUnmapped').attr('style','display : none');
         		$('#descriptionIconUnmapped').attr('style','display : none');
         		$('#createdIconUnmapped').attr('style','display : none');
         		
         		$('#spsheaderIcon').attr('style','display : none');
         		$('#descriptionIcon').attr('style','display : none');
         		$('#updatedIcon1').attr('style','display : none');
         		$('#userIcon1').attr('style','display : none');
         		
         		$('#ruleIcon').attr('style','display : none');
         		$('#spsIcon').attr('style','display : none');
         		$('#messageIcon').attr('style','display : none');
         		$('#updatedIcon2').attr('style','display : none');
         		$('#userIcon2').attr('style','display : none');
	    	             
                var strUnMapped=$("#unMappedTable tbody tr td").html();
                var strInProgTable2=$("#inProgressTable2 tbody tr td").html();
                var strInProgTableSub=$("#inProgressTableSub tbody tr td").html();
                           
                if($.trim(strUnMapped) != 'No Data Found')
                {
		                $('#unMappedTable').dataTable(
						{ 			   
								"bPaginate": false ,				    
								"bFilter": false, 
							 	"bSearchable":false, 
								"bInfo":false,
								"bSort": true,
								"bStateSave": false,
								"bJQueryUI": false,
								"bProcessing": false,
								"aaSorting": [],
								"bAutoWidth": false,
								"bSortClasses": true,
								"fnRowCallback": function( nRow, aData, iDisplayIndex ) {
								             
																if (iDisplayIndex % 2  == 0 )
																{  
																    $(nRow).removeClass( 'alternate' );
																	$(nRow).addClass( 'white-bg' );
																}
																else
																{
																    $(nRow).removeClass( 'white-bg' );
																    $(nRow).addClass( 'alternate' );
																}
																return nRow;
															},
								
								"aoColumns" : [
						            { sWidth: '122px' },
						            { sWidth: '144px' },
						            { sWidth: '99px' }
						           ]   
								
						} );
				}

                	
                	
						oTable  = $('#inProgressTable2').dataTable(
						{ 			   
								"bPaginate": false ,				    
								"bFilter": false, 
							 	"bSearchable":false, 
								"bInfo":false,
								"bSort": true,
								"bStateSave": false,
								"bJQueryUI": false,
								"bProcessing": false,
								"aaSorting": [] ,
								"bAutoWidth": false,
								"asStripClasses":['white-bg', 'alternate'], 
								"oLanguage": {sEmptyTable: 'No Data Found'},
								"fnRowCallback": function( nRow, aData, iDisplayIndex ) {
      															var sps = aData[0];
      														    var finilaizedFlag = sps.substring(0,9); 
  
      															if(finilaizedFlag=="FINALIZED") {      															
      																aData[0] = sps.substring(9);      															
      																$('td:eq(0)', nRow).html(sps.substring(9));
      																
      																$(nRow).addClass('finalized');
      															}      															
																return nRow;
															},
								
								"aoColumns" : [
						           { sWidth: '50px' },
						            { sWidth: '114px' },
						            { sWidth: '69px' },
						            { sWidth: '89px' },
						             { sWidth: '105px' }
						           ] 
						} );

                 		inProgressTableSubData =  $('#inProgressTableSub').dataTable(
						{ 			   
								"bPaginate": false ,				    
								"bFilter": false, 
							 	"bSearchable":false, 
								"bInfo":false,
								"bSort": true,
								"bStateSave": false,
								"bJQueryUI": false,
								"bProcessing": false,
								"aaSorting": [],
								"asStripClasses":['white-bg', 'alternate'],
								"oLanguage": {sEmptyTable: 'No Data Found'},
								"fnRowCallback": function( nRow, aData, iDisplayIndex ) {
																return nRow;
															},
								"bAutoWidth": false,
								"aoColumns" : [
						           { sWidth: '50px' },
						            { sWidth: '30px' },
						            { sWidth: '104px' },
						            { sWidth: '69px' },
						             { sWidth: '89px' },
						             { sWidth: '128px' }
						           ] 
						} );
	
 	});
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
function sniffer() {

// checking ie version for fixing the styling issue in accordion
	if(navigator.appVersion.match(/MSIE [\d.]+/) == '8.0') {
	    
		   if(screen.height==1024) 
		   {
		      document.getElementById('unmappedTableDiv').style.height = "380px";
		      document.getElementById("inProgressDiv").style.height=  "380px";
		      document.getElementById("inProgressDiv").style.width=  "480px";
		   } 
		   else if(screen.height==864)
		   {
		      document.getElementById("unmappedTableDiv").style.height= "277px";
		      document.getElementById("inProgressDiv").style.height= "277px";        
		   }
	}
	else if(navigator.appVersion.match(/MSIE [\d.]+/) == '6.0'){
		
		if(screen.height==1024) 
		   {
		      document.getElementById('unmappedTableDiv').style.height = "250px";
		      document.getElementById("inProgressDiv").style.height=  "220px";
		   } 
		   else if(screen.height==864)
		   {
		      document.getElementById("unmappedTableDiv").style.height= "277px";
		      document.getElementById("inProgressDiv").style.height= "277px";        
		   }
	}
}	

$(document).ready(function(){	
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
  
$(document).ready(function(){	
	ContentHeight = 223;
	runAccordion(1);	
  });
  
var list = new Array();
$(document).ready(function(){ 
      //Scrollbar not implemented   
      $('#spsId').blur(function() {     
         if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
            $("#spsIdLabel").text('');
         }
            $('#spsId').val($('#spsId').val().toUpperCase());     
      });
      $("#spsId").autocomplete({ 
            select: function(event, ui) { $("#spsIdLabel").text(ui.item.id).removeClass('invalid_hippacode_value'); },
            source: function(request, response) {
                              $.ajax({
                                    url: "${context}/ajaxautocomplete.ajax",
                                    dataType: "json",
                                    type:"POST",
                                    data: "key="+escape(request.term)+ "&name=SPSID",                         
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
                        displayLabelForSelectedItem(text,list,"spsIdLabel",inValidSPSID);
                  }
            }

      })
      sniffer();
  });
  
  $(document).ready(function(){     
      //Scrollbar not implemented
      $('#ruleId').blur(function() {      
         if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
            $("#ruleIdLabel").text('');
         }
            $('#ruleId').val($('#ruleId').val().toUpperCase());   
      });
      $("#ruleId").autocomplete({ 
            select: function(event, ui) { $("#ruleIdLabel").text(ui.item.id).removeClass('invalid_hippacode_value'); },
            source: function(request, response) {
                              $.ajax({
                                    url: "${context}/ajaxautocomplete.ajax",
                                    dataType: "json",
                                    type:"POST",
                                    data: "key="+escape(request.term)+ "&name=RULEID",                         
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
                        displayLabelForSelectedItem(text,list,"ruleIdLabel",inValidRuleID);
                  }
            }

      })
      sniffer();
  });

function clearCreateOrLocateFields(fieldName, section) {

      if (section == 'Create') {
            if (fieldName == 'customMessageChkBox') {
                  if ($('#customMessageChkBox').is(':checked') ) {
                        $("#ruleId"). removeClass("inputboxDisabled");
                        $("#spsId"). removeClass("inputboxDisabled");
                        $("#ruleId"). addClass("inputbox65");
                        $("#spsId"). addClass("inputbox65");
                  }
                  else if (!$('#customMessageChkBox').is(':checked') ) {
                        $('#spsId').val('');
                        $('#ruleId').val('');
                        $('#ruleIdLabel').text('');   
                        $('#spsIdLabel').text('');          
                        $("#ruleId"). removeClass("inputboxDisabled");
                        $("#spsId"). removeClass("inputboxDisabled");
                        $("#ruleId"). addClass("inputbox65");
                        $("#spsId"). addClass("inputbox65");
                  }
            }
            else if (!$('#customMessageChkBox').is(':checked') ){
                  if (fieldName == 'ruleId') {
                        $('#spsId').val('');    
                        $('#spsIdLabel').text('');                
                        $("#spsId"). removeClass("inputbox65");
                        $("#spsId"). addClass("inputboxDisabled");
                        $("#ruleId"). removeClass("inputboxDisabled");
                        $("#ruleId"). addClass("inputbox65");
                  }
                  if (fieldName == 'spsId') {
                        $('#ruleId').val('');   
                        $('#ruleIdLabel').text('');   
                        $("#ruleId"). removeClass("inputbox65");
                        $("#ruleId"). addClass("inputboxDisabled");
                        $("#spsId"). removeClass("inputboxDisabled");
                        $("#spsId"). addClass("inputbox65");
                  }
            } 
      }
      if (section == 'Locate') {
            if (fieldName == 'customMessageLocateChkBox') {
            
                  if ($('#customMessageLocateChkBox').is(':checked') ) {
                        $("#ruleIdFrmLocate"). removeClass("inputboxDisabled");
                        $("#spsIdFrmLocate"). removeClass("inputboxDisabled");
                        $("#ruleIdFrmLocate"). addClass("inputbox65");
                        $("#spsIdFrmLocate"). addClass("inputbox65");
                        
                        $("#isUnMapped").attr('disabled', true);
                        $("#isMapped").attr('disabled', true);
                        $("#isNotApplicable").attr('disabled', true);
                  }
                  else if (!$('#customMessageLocateChkBox').is(':checked') ) {
                        $('#spsIdFrmLocate').val('');
                        $('#ruleIdFrmLocate').val('');
                        $("#ruleIdFrmLocate"). removeClass("inputboxDisabled");
                        $("#spsIdFrmLocate"). removeClass("inputboxDisabled");
                        $("#ruleIdFrmLocate"). addClass("inputbox65");
                        $("#spsIdFrmLocate"). addClass("inputbox65");

 						$("#isUnMapped").removeAttr('disabled', true);
                        $("#isMapped").removeAttr('disabled', true);
                        $("#isNotApplicable").removeAttr('disabled', true);
                  }
            }
            else if (!$('#customMessageLocateChkBox').is(':checked') ) {
                  if (fieldName == 'ruleIdFrmLocate') {
                        $('#spsIdFrmLocate').val('');       
                        $("#spsIdFrmLocate"). removeClass("inputbox65");
                        $("#spsIdFrmLocate"). addClass("inputboxDisabled");   
                        $("#ruleIdFrmLocate"). removeClass("inputboxDisabled");
                        $("#ruleIdFrmLocate"). addClass("inputbox65");
                  }
                  if (fieldName == 'spsIdFrmLocate') {
                        $('#ruleIdFrmLocate').val('');      
                        $("#ruleIdFrmLocate"). removeClass("inputbox65");
                        $("#ruleIdFrmLocate"). addClass("inputboxDisabled");
                        $("#spsIdFrmLocate"). removeClass("inputboxDisabled");
                        $("#spsIdFrmLocate"). addClass("inputbox65");   
                  }
            }
      }
      
}  
	// Method to open create page on click of 'Create Button'
	function openCreatePage(){
		// check whether the custom message checkbox is checked
		if ($('#customMessageChkBox').is(':checked') ){
		
			if(($("#ruleId").val() == null || $.trim($("#ruleId").val()) == "") 
				&& ($("#spsId").val() == null || $.trim($("#spsId").val()) == "")){ 
				addErrorToNotificationTray('Rule ID and SPS ID is mandatory to create a custom message mapping');
				openTrayNotification();			
			}
			else if(($("#ruleId").val() == null || $.trim($("#ruleId").val()) == "") 
				&& ($("#spsId").val() != null || $.trim($("#spsId").val()) != "")){ 
				addErrorToNotificationTray('Rule ID is mandatory to create a custom message mapping');
				openTrayNotification();			
			}
			else if(($("#ruleId").val() != null || $.trim($("#ruleId").val()) != "") 
				&& ($("#spsId").val() == null || $.trim($("#spsId").val()) == "")){ 
				addErrorToNotificationTray('SPS ID is mandatory to create a custom message mapping');
				openTrayNotification();			
			}
			else if(($("#ruleId").val() != null || $.trim($("#ruleId").val()) != "") 
				&& ($("#spsId").val() != null || $.trim($("#spsId").val()) != "")) {
		  	 	document.forms['createform'].action="${context}/vieworcreatemapping/viewCustomMsgCreate.html";
		        document.forms["createform"].submit();	       
	 	  }			
		}
		else {		
				if(($("#ruleId").val() == null || $.trim($("#ruleId").val()) == "") 
				&& ($("#spsId").val() == null || $.trim($("#spsId").val()) == "")){ 
					addErrorToNotificationTray('Rule ID or SPS ID is mandatory to create mapping');
					openTrayNotification();			
				}				
				else if( $("#ruleId").val()!= null && ($.trim($("#ruleId").val()).length > 0)) {
		  	 	document.forms['createform'].action="${context}/vieworcreatemapping/ruleIdCreateUsingAutocomplete.html";
		        document.forms["createform"].submit();	       
		  		 }
	   
				else if( ($("#spsId").val()!= null) && ($.trim($("#spsId").val()).length > 0) ) {		
		  	 	document.forms['createform'].action="${context}/vieworcreatemapping/spsIdCreateUsingAutocomplete.html";
		        document.forms["createform"].submit();
		   		}
		 }  		
	}
	// Method to open create page on click of 'Create (+) icon'
	function openCreateFromUnmappedSectionForSps(spsId){
		$("#spsIdHidden").val(spsId);
		$("#selectedSpsId").val(spsId);
		document.forms['unmappedVarForm'].action="${context}/vieworcreatemapping/spsIdCreateFromUnmapped.html";
		document.forms['unmappedVarForm'].submit();
	}
	function openCreateFromUnmappedSectionForRule(ruleId){
		$("#ruleIdHidden").val(ruleId);
		document.forms['unmappedVarForm'].action="${context}/vieworcreatemapping/ruleIdCreateFromUnmapped.html";
		document.forms['unmappedVarForm'].submit();
	}
	// Method to open create page on click of 'Locate'
	function openLocateResultsDialog(currentPageNo, page){

		var variableId = $("#selectedVariableIdFrmLocate").val();
		var variableDesc = $("#selectedVariableDescFrmLocate").val();
		var selectedSpsId = $("#spsIdFrmLocate").val();
		var selectedRuleId = $("#ruleIdFrmLocate").val();
		var currentPage = currentPageNo;
        var fromWhichSection = page;
		if(document.locateResultsForm.isUnMapped.checked){
		var isUnmapped = document.locateResultsForm.isUnMapped.value;
		}
		if(document.locateResultsForm.isMapped.checked){
		var isMapped =document.locateResultsForm.isMapped.value;
		}
		if(document.locateResultsForm.isNotApplicable.checked){
		var isNotApplicable =document.locateResultsForm.isNotApplicable.value;
		}		
		if(document.locateResultsForm.customMessageLocateChkBox.checked){
		var isCustomMessage = document.locateResultsForm.customMessageLocateChkBox.value;
		}
		if((selectedSpsId == null || $.trim(selectedSpsId) == "") && (selectedRuleId == null || $.trim(selectedRuleId) == "")){
			addErrorToNotificationTray('Either SPS ID or Rule id is required to locate');
			openTrayNotification();
		}			
		else{
			$.ajax({
			url: "${context}/locateebxresults/locateRequest.ajax",
			dataType: "html",
			type: "POST",
			data: "isCustomMessage="+isCustomMessage+"&selectedRuleId="+escape(selectedRuleId)+"&selectedSpsId="+escape(selectedSpsId)+"&variableId="+escape(variableId)+"&variableDesc="+escape(variableDesc)+"&mappedStatus="+isMapped+"&unMappedStatus="+isUnmapped+"&notAppStatus="+isNotApplicable+"&currentPage="+currentPage+"&section="+fromWhichSection,
			success: function(data) {
				$("#viewLoacteDialog").html(data);
				$("#viewLoacteDialog").dialog({
					height:'auto',
					width:'750px',	
					show:'slide',
					modal: true,
					resizable: false,
					title: 'Locate Results',
					open:function(){
					$('.ui-dialog-titlebar').append("<a href='#' onclick='generateReport();' ><img  src='${imageDir}/print.gif' class= 'printIt' id='printIconForReport' style='height:15px; position:relative; left:75%;' alt='Print' title='Print'></a>");
					},
					close: function() {
					$('.printIt').remove();
                    }
				});
				$("#viewLoacteDialog").dialog();
			}
		});
		}
	}

	// Method to open pagination results
	function openPagntnResultsDialog(currentPageNo, page){

		var variableId = $("#selectedVariableIdFrmLocate").val();
		var variableDesc = $("#selectedVariableDescFrmLocate").val();
		var selectedSpsId = $("#spsIdFrmLocate").val();
		var selectedRuleId = $("#ruleIdFrmLocate").val();
		
		var currentPage = currentPageNo;
        var fromWhichSection = page;
		if(document.locateResultsForm.isUnMapped.checked){
		var isUnmapped = document.locateResultsForm.isUnMapped.value;
		}
		if(document.locateResultsForm.isMapped.checked){
		var isMapped =document.locateResultsForm.isMapped.value;
		}
		if(document.locateResultsForm.isNotApplicable.checked){
		var isNotApplicable =document.locateResultsForm.isNotApplicable.value;
		}
		if(document.locateResultsForm.customMessageLocateChkBox.checked){
		var isCustomMessage = document.locateResultsForm.customMessageLocateChkBox.value;
		}
		
		
		if((selectedSpsId == null || selectedSpsId == "") && (selectedRuleId == null || selectedRuleId == "")){
			addErrorToNotificationTray('Either selectedSpsId ID or Rule Id is required to perform a search');
			openTrayNotification();
		}			
		else{
			$.ajax({
			url: "${context}/locateebxresults/locateRequest.ajax",
			dataType: "html",
			type: "POST",
			data: "isCustomMessage="+isCustomMessage+"&selectedRuleId="+escape(selectedRuleId)+"&selectedSpsId="+escape(selectedSpsId)+"&variableId="+escape(variableId)+"&variableDesc="+escape(variableDesc)+"&mappedStatus="+isMapped+"&unMappedStatus="+isUnmapped+"&notAppStatus="+isNotApplicable+"&currentPage="+currentPage+"&section="+fromWhichSection,
			success: function(data) {
				$("#viewLoacteDialog").html(data);
			}
		});
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
     function editMappingForSps(spsId){
            $("#selectedSpsIdForEdit").val(spsId);	
            document.forms['editForm'].action="${context}/createoreditspsmapping/editSPSMapping.html";		           
            document.forms["editForm"].submit();		
     }
     
     function editMappingForRule(ruleId){
            $('#selectedruleForEditId').val(ruleId);	
            document.forms['editForm'].action="${context}/createoreditrulemapping/editRuleMapping.html";		           
            document.forms["editForm"].submit();		
     }
	
	function editMappingForCustomMsg(ruleId, spsId){
            $("#selectedRuleIdForEditSub").val(ruleId);	
            $("#selectedSpsIdForEditSub").val(spsId);	
            document.forms['editForm'].action="${context}/createoreditcustommessagemapping/editCustomMessageMapping.html";		           
            document.forms["editForm"].submit();		
     }
	
	function openUserCommentsApproveDialogForSps(spsId){	

		$("#approvestateflowspsId").val(spsId);
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
	function openUserCommentsApproveDialogForRule(ruleId){	

		$("#approvestateflowruleId").val(ruleId);
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
	function openUserCommentsApproveDialogForCustomMsg(ruleId, spsId){	

		$("#approvestateflowruleIdCus").val(ruleId);
		$("#approvestateflowspsIdCus").val(spsId);
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
	function openUserCommentsSend2TestDialogForSps(spsId){
	
		$("#send2teststateflowspsId").val(spsId);
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
	
	function openUserCommentsSend2TestDialogForRule(ruleId){
	
		$("#send2teststateflowruleId").val(ruleId);
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
	
	function openUserCommentsSend2TestDialogForCustomMsg(ruleId, spsId){
	
		$("#send2teststateflowruleIdCus").val(ruleId);
		$("#send2teststateflowspsIdCus").val(spsId);
		
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
	
	function saveUserCommentsForApproveForSps(){
		if(!imposeMaxLength('approveMappingSpsComments' ,250,'user comments')){						
			return false;
		} 
		document.forms['userCommentsApproveSpsForm'].submit();
	}
	
	function saveUserCommentsForApproveForRule(){
		 if(!imposeMaxLength('approvedMappingRuleComments' ,250,'user comments')){						
			return false;
		}
		document.forms['userCommentsApproveRuleForm'].submit();
	}
	
	function saveUserCommentsForApproveForCustomMsg(){
		 if(!imposeMaxLength('approvedMappingCustomMsgComments' ,250,'user comments')){						
			return false;
		}
		document.forms['userCommentsApproveCustomMsgForm'].submit();
	}
	

	function saveUserCommentsForSent2TestForSps(){	
	if(!imposeMaxLength('send2TestMappingSpsComments' ,250,'user comments')){						
			return false;
		}					
		document.forms['userCommentsSent2TestSpsForm'].submit();
	}
	
	function saveUserCommentsForSent2TestForRule(){	
	if(!imposeMaxLength('send2TestMappingRuleComments' ,250,'user comments')){						
			return false;
		}				
		document.forms['userCommentsSent2TestRuleForm'].submit();
	}
	
	function saveUserCommentsForSent2TestForCustomMsg(){	
	if(!imposeMaxLength('send2TestMappingCustomMsgComments' ,250,'user comments')){						
			return false;
		}				
		document.forms['userCommentsSent2TestCustomMsgForm'].submit();
	}
	
	function saveUserCommentsForNotApplicableForSps(){
		if(!imposeMaxLength('notApplicableMappingSpsComments' ,250,'user comments')){						
				return false;
		}
		document.forms['userCommentsNotApplicableSpsForm'].submit();
	}
	
	function saveUserCommentsForNotApplicableForRule(){
		if(!imposeMaxLength('notApplicableMappingRuleComments' ,250,'user comments')){						
				return false;
		}
		document.forms['userCommentsNotApplicableRuleForm'].submit();
	}
	
	function openUserCommentsNotApplicableDialogForSps(spsId){

		$("#notApplicablespsId").val(spsId);		
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
var ruleIdForViewPage ="";
var userCommentsFrom = "";
var spsIdForViewPage = "";
//open user comments dialog from view and locate for Sps id
function openUserCommentsDialogForSps(spsId,actionFrom){
		resetMessages();
		spsIdForViewPage = escape(spsId);
		userCommentsFrom = actionFrom;			
		$("#userMappingCommentsForSps").val('');

		if(userCommentsFrom=='Send2TestSpsFromLocate') {
			$("#userCommentsDialogForSps").dialog({
	                        height:'auto',
							resizable:false,
							width:'450px',	
	                        show:'slide',
							title: spsId +' - Send to test',
	                        modal: true
	                  });
		}else if(userCommentsFrom=='ApproveSpsFromLocate') {
			$("#userCommentsDialogForSps").dialog({
	                        height:'auto',
							resizable:false,
							width:'450px',	
	                        show:'slide',
							title: spsId +' - Approve',
	                        modal: true
	                  });
		}else if(userCommentsFrom=='Send2TestSpsFromView') {		
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
		resetMessages();
		ruleIdForViewPage = escape(ruleId);
		userCommentsFrom = actionFrom;			
		$("#userMappingCommentsForRule").val('');

		if(userCommentsFrom=='Send2TestRuleFromLocate') {
			$("#userCommentsDialogForRule").dialog({
	                        height:'auto',
							resizable:false,
							width:'450px',	
	                        show:'slide',
							title: ruleId +' - Send to test',
	                        modal: true
	                  });
		}else if(userCommentsFrom=='ApproveRuleFromLocate') {
			$("#userCommentsDialogForRule").dialog({
	                        height:'auto',
							resizable:false,
							width:'450px',	
	                        show:'slide',
							title: ruleId +' - Approve',
	                        modal: true
	                  });
		}else if(userCommentsFrom=='Send2TestRuleFromView') {		
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
		resetMessages();
		ruleIdForViewPage = escape(ruleId);
		spsIdForViewPage = escape(spsId);
		userCommentsFrom = actionFrom;			
		$("#userMappingCommentsForCustomMsg").val('');

		if(userCommentsFrom=='Send2TestCustomMsgFromLocate') {
			$("#userCommentsDialogForCustomMsg").dialog({
	                        height:'auto',
							resizable:false,
							width:'450px',	
	                        show:'slide',
							title: spsId + ' '+ ruleId + ' - Send to test',
	                        modal: true
	                  });
		}else if(userCommentsFrom=='ApproveCustomMsgFromLocate') {
			$("#userCommentsDialogForCustomMsg").dialog({
	                        height:'auto',
							resizable:false,
							width:'450px',	
	                        show:'slide',
							title: spsId + ' '+ ruleId +' - Approve',
	                        modal: true
	                  });
		}else if(userCommentsFrom=='Send2TestCustomMsgFromView') {		
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
		if(userCommentsFrom=='Send2TestSpsFromLocate') {
			$.ajax({
				url: "${context}/stateflowewpdbx/sendToTestSpsAjax.ajax",
				dataType: "json",
				type: "POST",			
				data: "spsId="+spsIdForViewPage+"&userComments="+userComments,
				success: function(data) {
				cache: false,
				handleSendToTestFromLocate(data);}
			});
		}else if(userCommentsFrom=='ApproveSpsFromLocate') {
			$.ajax({
				url: "${context}/stateflowewpdbx/approveSpsAjax.ajax",
				dataType: "json",
				type: "POST",
				data: "spsId="+spsIdForViewPage+"&userComments="+userComments,			
				success: function(data) {
				cache: false,
				handleApproveFromLocate(data);}
			});
		}else if(userCommentsFrom=='Send2TestSpsFromView') {
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
		if(userCommentsFrom=='Send2TestRuleFromLocate') {
			$.ajax({
				url: "${context}/stateflowewpdbx/sendToTestRuleAjax.ajax",
				dataType: "json",
				type: "POST",			
				data: "ruleId="+ruleIdForViewPage+"&userComments="+userComments,
				success: function(data) {
				cache: false,
				handleSendToTestFromLocate(data);}
			});
		}else if(userCommentsFrom=='ApproveRuleFromLocate') {
			$.ajax({
				url: "${context}/stateflowewpdbx/approveRuleAjax.ajax",
				dataType: "json",
				type: "POST",
				data: "ruleId="+ruleIdForViewPage+"&userComments="+userComments,			
				success: function(data) {
				cache: false,
				handleApproveFromLocate(data);}
			});
		}else if(userCommentsFrom=='Send2TestRuleFromView') {
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
		if(userCommentsFrom=='Send2TestCustomMsgFromLocate') {
			$.ajax({
				url: "${context}/stateflowewpdbx/sendToTestCustomMsgAjax.ajax",
				dataType: "json",
				type: "POST",			
				data: "ruleId="+ruleIdForViewPage+"&spsId="+spsIdForViewPage+"&userComments="+userComments,	
				success: function(data) {
				cache: false,
				handleSendToTestFromLocate(data);}
			});
		}else if(userCommentsFrom=='ApproveCustomMsgFromLocate') {
			$.ajax({
				url: "${context}/stateflowewpdbx/approveCustomMsgAjax.ajax",
				dataType: "json",
				type: "POST",
				data: "ruleId="+ruleIdForViewPage+"&spsId="+spsIdForViewPage+"&userComments="+userComments,			
				success: function(data) {
				cache: false,
				handleApproveFromLocate(data);}
			});
		}else if(userCommentsFrom=='Send2TestCustomMsgFromView') {
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
	function handleSendToTestFromLocate(data) {		
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
		if(data.ruleId != null && data.spsId == null){		
			handleIconsWhileApprove(data.ruleId);
			if ("SCHEDULED_TO_PRODUCTION" == data.variableMappingStatus) {
				$('#'+'status_'+data.ruleId).html("SCHEDULED_TO_PRODUCTION");	
			}
		}
		else if(data.spsId != null && data.ruleId == null){
			handleIconsWhileApprove(data.spsId);
			if ("SCHEDULED_TO_PRODUCTION" == data.variableMappingStatus) {
				$('#'+'status_'+data.spsId).html("SCHEDULED_TO_PRODUCTION");	
			}
		}
		else if(data.ruleId != null && data.spsId != null){
			var cusMsg = data.ruleId + data.spsId;
			handleIconsWhileApprove(cusMsg); 
			if ("SCHEDULED_TO_PRODUCTION" == data.variableMappingStatus) {
				$('#'+'status_'+cusMsg).html("SCHEDULED_TO_PRODUCTION");	
			}
		}
		addMessages(data);	
	}	
	
	function markVariableAsNotApplicableForSps(spsId){
		openCreateFromUnmappedSectionForSps(spsId);	
     }
     function markVariableAsNotApplicableForRule(ruleId){
		openCreateFromUnmappedSectionForRule(ruleId);	
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
	function toggleNumRecords(){
  			
		 var appendUser = ($('#toggled').attr('src') == "${imageDir}/scheduled_approval.gif") ? "false" :
	  	 "true"; 	 
	  	 
	  	 var src = ($('#numRecords').attr('src') == "${imageDir}/fullcopy.gif") ? "${imageDir}/copy_landing.gif" :
	  	 "${imageDir}/fullcopy.gif";
	  	 
	  	 $('#numRecords').attr('src', src);	
	  	 
	  	 var getAll = ($('#numRecords').attr('src') == "${imageDir}/fullcopy.gif") ? "true" :
	  	 "false";
	  	 
	  	 if (getAll == "true") {
	  	 	$('#numRecords').attr('title', 'Show First 50 Mappings');
	  	 	$('#numRecords').attr('alt', 'Show First 50 Mappings');
	  	}
	  	else {
	  		$('#numRecords').attr('title', 'Show All Mappings');
	  	 	$('#numRecords').attr('alt', 'Show All Mappings');
	  	}
	  	 
	  	 ajaxInprogress(appendUser, getAll);
  	 
  	 }
  	 function toggleUserIcon(){
	  	 var getAll = ($('#numRecords').attr('src') == "${imageDir}/fullcopy.gif") ? "true" :
	  	 "false";
	  	
	  	 var src = ($('#toggled').attr('src') == "${imageDir}/icon-by-customer.gif") ? "${imageDir}/scheduled_approval.gif" :
	  	 "${imageDir}/icon-by-customer.gif";
	  	 
	  	 $('#toggled').attr('src', src);

	  	
	  	  var appendUser = ($('#toggled').attr('src') == "${imageDir}/scheduled_approval.gif") ? "false" :
	  	 "true";
	  	if (appendUser == "true") {
	  	 	$('#toggled').attr('title', 'Show All Users');
	  	 	$('#toggled').attr('alt', 'Show All Users');
	  	}
	  	else {
	  		$('#toggled').attr('title', 'Show Current User');
	  	 	$('#toggled').attr('alt', 'Show Current User');
	  	}
	  	 ajaxInprogress(appendUser, getAll);
  	 
  	 }
  	 function ajaxInprogress(appendUser, getAll){	
  		var authorizedToapprove = $('#authorizedToapprove').val();
  	 	var authorizedTounlock = $('#authorizedTounlock').val();
  	 	$.ajax({
				url: "${context}/stateflowewpdbx/changeInProgress.ajax",
				dataType: "json",
				type: "POST",
				data: "appendUser="+appendUser+"&getAll="+getAll+"&authorizedToapprove="+authorizedToapprove+"&authorizedTounlock="+authorizedTounlock,
				cache: false,
				success: function(data) {
				
					oTable.fnClearTable(); 					
					oTable.fnAddData(data.aaData);	
					
					inProgressTableSubData.fnClearTable(); 					
					inProgressTableSubData.fnAddData(data.message);	
				}					
			});
	}
	function printInprogress() {
	 var getAll = ($('#numRecords').attr('src') == "${imageDir}/fullcopy.gif") ? "true" :
	  	 "false";
	  	
	  	 
	var appendUser = ($('#toggled').attr('src') == "${imageDir}/scheduled_approval.gif") ? "false" :
	  	 "true";
	 
	  $("#appendUserPrint").val(appendUser);	
	  $("#getAllPrint").val(getAll);	
	  	 document.forms['editForm'].action="${context}/stateflowewpdbx/generateExcel.html";
		document.forms['editForm'].submit();
	}

	// Function for unlocking mapping from locate
	function unlockMappingFromLocate(ruleId, spsId, action, lockUserId){
	resetMessages();
	var warningMsgForUnlocking = "The mapping is locked by the user " + lockUserId + " . Do you want to unlock?";
	$("#confirmationDivUnlock").html(warningMsgForUnlocking);
	$("#confirmationDivUnlock").addClass("UnmappedVariables");
	var authorizedToApprove = $('#authorizedToapprove').val();
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
					if(action=='unlockRuleFromLocate') {
						$.ajax({
							url: "${context}/stateflowewpdbx/unlockRuleMapping.ajax",
							dataType: "json",
							type: "POST",			
							data: "ruleId="+escape(ruleId)+"&authorizedToApprove="+authorizedToApprove,
							success: function(data) {
							cache: false,
							handleUnlock(data, 'unlockRuleFromLocate');}
						});
					}else if(action=='unlockSpsFromLocate') {
						$.ajax({
							url: "${context}/stateflowewpdbx/unlockSpsMapping.ajax",
							dataType: "json",
							type: "POST",
							data: "spsId="+escape(spsId)+"&authorizedToApprove="+authorizedToApprove,			
							success: function(data) {
							cache: false,
							handleUnlock(data, 'unlockSpsFromLocate');}
						});
					}else if(action=='unlockCustomMsgFromLocate') {
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
					if(action=='unlockRuleFromLanding') {
						$.ajax({
							url: "${context}/stateflowewpdbx/unlockRuleMapping.ajax",
							dataType: "json",
							type: "POST",			
							data: "ruleId="+escape(ruleId)+"&authorizedToApprove="+authorizedToApprove,
							success: function(data) {
							cache: false,
							handleUnlock(data, 'unlockRuleFromLanding');}
						});
					}else if(action=='unlockSpsFromLanding') {
						$.ajax({
							url: "${context}/stateflowewpdbx/unlockSpsMapping.ajax",
							dataType: "json",
							type: "POST",
							data: "spsId="+escape(spsId)+"&authorizedToApprove="+authorizedToApprove,			
							success: function(data) {
							cache: false,
							handleUnlock(data, 'unlockSpsFromLanding');}
						});
					}else if(action=='unlockCustomMsgFromLanding') {
						$.ajax({
							url: "${context}/stateflowewpdbx/unlockCustomMsgMapping.ajax",
							dataType: "json",
							type: "POST",			
							data: "spsId="+escape(spsId)+"&ruleId="+escape(ruleId)+"&authorizedToApprove="+authorizedToApprove,
							success: function(data) {
							cache: false,
							handleUnlock(data, 'unlockCustomMsgFromLanding');}
						});
					}
				}
			}
		});
		$("#confirmationDivUnlock").dialog('open');	
		
	}
	/* common method to unlock rule, sps and custom message mapping*/
function handleUnlock(data, fromAction) {
		addMessages(data);
					
		if(data.ruleId != null && data.spsId == null){	
			if(fromAction == 'unlockRuleFromLocate'){	
				handleIconsWhileUnlockFromLocate(data.ruleId,'',data.mapping.user.lastUpdatedUserName, data.authorizedToApprove, 'rule');
		   }
		   else{	
				handleIconsWhileUnlockFromLanding(data.ruleId,'',data.mapping.user.lastUpdatedUserName, data.authorizedToApprove, 'rule');
		   }
		}
		else if(data.spsId != null && data.ruleId == null){
			if(fromAction == 'unlockSpsFromLocate'){
				handleIconsWhileUnlockFromLocate('',data.spsId,data.mapping.user.lastUpdatedUserName, data.authorizedToApprove, 'sps');
			}
			else{
				handleIconsWhileUnlockFromLanding('',data.spsId,data.mapping.user.lastUpdatedUserName, data.authorizedToApprove, 'sps');
			}
		}
		else if(data.ruleId != null && data.spsId != null){
			//var cusMsg = data.ruleId + data.spsId;
			if(fromAction == 'unlockCustomMsgFromLocate'){
				handleIconsWhileUnlockFromLocate(data.ruleId,data.spsId, data.mapping.user.lastUpdatedUserName, data.authorizedToApprove, 'customMessage'); 
			}
			else{
				handleIconsWhileUnlockFromLanding(data.ruleId,data.spsId, data.mapping.user.lastUpdatedUserName, data.authorizedToApprove, 'customMessage'); 
			}	
		}
	
	}
	function handleIconsWhileUnlockFromLocate(ruleId, SpsId, userId, permissionToApprove, action) {		
			
		var sendToTest = "";
		var approve = "";
		var editFunction = "";
		var userCommentsFunction = "";
		var editComp = "";
		var sendtotestComp = "";
		var approveComp = "";
		if(action == 'rule'){
			sendToTest = "Send2TestRuleFromLocate";
			approve = "ApproveRuleFromLocate";
			editFunction = "editMappingForRule";
			userCommentsFunction = "openUserCommentsDialogForRule";
			
			editComp = '<a href = "#" id="locate_EditIcon_'+ruleId+ 
			'" onclick="'+editFunction+'(\''+ruleId+'\');"><img src="${imageDir}/edit_icon.gif" alt="Edit" title="Edit" /></a>';
		
			sendtotestComp = '<a href = "#" id="locate_SendToTestIcon_'+ruleId+ 
			'" onclick="'+userCommentsFunction+'(\''+ruleId+'\',\''+sendToTest+'\');"><img src="${imageDir}/test_icon.gif" alt="Send to Test" title="Send to Test"/></a>';
		
			approveComp = '<a href = "#" id="locate_approveIcon_'+ruleId+ 
			'" onclick="'+userCommentsFunction+'(\''+ruleId+'\',\''+approve+'\');"><img src="${imageDir}/approve_icon.gif" alt="Approve" title="Approve"/></a>';
		
			
			$('#locate_UnlockIcon_'+ruleId).replaceWith(editComp);
			$('#locate_EditIcon_'+ruleId).append("&#160;&#160;");
			
			$('#locate_EditIcon_'+ruleId).after(sendtotestComp);
			$('#locate_SendToTestIcon_'+ruleId).append("&#160;&#160;");
			
			if(permissionToApprove){				
				$('#locate_SendToTestIcon_'+ruleId).after(approveComp);
			}
		}	
		if(action == 'sps'){
			sendToTest = "Send2TestSpsFromLocate";
			approve = "ApproveSpsFromLocate";
			editFunction = "editMappingForSps";
			userCommentsFunction = "openUserCommentsDialogForSps";
			
			editComp = '<a href = "#" id="locate_EditIcon_'+SpsId+ 
			'" onclick="'+editFunction+'(\''+SpsId+'\');"><img src="${imageDir}/edit_icon.gif" alt="Edit" title="Edit" /></a>';
		
			sendtotestComp = '<a href = "#" id="locate_SendToTestIcon_'+SpsId+ 
			'" onclick="'+userCommentsFunction+'(\''+SpsId+'\',\''+sendToTest+'\');"><img src="${imageDir}/test_icon.gif" alt="Send to Test" title="Send to Test"/></a>';
		
			approveComp = '<a href = "#" id="locate_approveIcon_'+SpsId+ 
			'" onclick="'+userCommentsFunction+'(\''+SpsId+'\',\''+approve+'\');"><img src="${imageDir}/approve_icon.gif" alt="Approve" title="Approve"/></a>';
		
			
			$('#locate_UnlockIcon_'+SpsId).replaceWith(editComp);
			$('#locate_EditIcon_'+SpsId).append("&#160;&#160;");
			
			$('#locate_EditIcon_'+SpsId).after(sendtotestComp);
			$('#locate_SendToTestIcon_'+SpsId).append("&#160;&#160;");
			
			if(permissionToApprove){				
				$('#locate_SendToTestIcon_'+SpsId).after(approveComp);
			}
		}
		if(action == 'customMessage'){
			sendToTest = "Send2TestCustomMsgFromLocate";
			approve = "ApproveCustomMsgFromLocate";
			editFunction = "editMappingForCustomMsg";
			userCommentsFunction = "openUserCommentsDialogForCustomMsg";
			
			editComp = '<a href = "#" id="locate_EditIcon_'+ruleId+SpsId+ 
			'" onclick="'+editFunction+'(\''+ruleId+'\', \''+SpsId+'\');"><img src="${imageDir}/edit_icon.gif" alt="Edit" title="Edit" /></a>';
			
			sendtotestComp = '<a href = "#" id="locate_SendToTestIcon_'+ruleId+SpsId+ 
			'" onclick="'+userCommentsFunction+'(\''+ruleId+'\',\''+SpsId+'\',\''+sendToTest+'\');"><img src="${imageDir}/test_icon.gif" alt="Send to Test" title="Send to Test"/></a>';
		
			approveComp = '<a href = "#" id="locate_approveIcon_'+ruleId+SpsId+ 
			'" onclick="'+userCommentsFunction+'(\''+ruleId+'\', \''+SpsId+'\',\''+approve+'\');"><img src="${imageDir}/approve_icon.gif" alt="Approve" title="Approve"/></a>';
		
			$('#locate_UnlockIcon_'+ruleId+SpsId).replaceWith(editComp);
			$('#locate_EditIcon_'+ruleId+SpsId).append("&#160;&#160;");
			
			$('#locate_EditIcon_'+ruleId+SpsId).after(sendtotestComp);
			$('#locate_SendToTestIcon_'+ruleId+SpsId).append("&#160;&#160;");
			
			if(permissionToApprove){				
				$('#locate_SendToTestIcon_'+ruleId+SpsId).after(approveComp);
			}
		}
	
	}	
	
	function handleIconsWhileUnlockFromLanding(ruleId, SpsId, userId, permissionToApprove, action) {		
		
		var editFunction = "";
		var userCmntsTest = "";
		var userCmntsApprove = "";
		var editComp = "";
		var sendtotestComp = "";
		var approveComp = "";
		
		if(action == 'rule'){		
			editFunction = "editMappingForRule";
			userCmntsTest = "openUserCommentsSend2TestDialogForRule";
			userCmntsApprove = "openUserCommentsApproveDialogForRule";
			
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
	
	function generateReport(){		
		var selectedSpsId = $("#spsIdFrmLocate").val();
		var selectedRuleId = $("#ruleIdFrmLocate").val();

		if(document.locateResultsForm.isUnMapped.checked){
		var isUnmapped = document.locateResultsForm.isUnMapped.value;
		}
		if(document.locateResultsForm.isMapped.checked){
		var isMapped =document.locateResultsForm.isMapped.value;
		}
		if(document.locateResultsForm.isNotApplicable.checked){
		var isNotApplicable =document.locateResultsForm.isNotApplicable.value;
		}		
		if(document.locateResultsForm.customMessageLocateChkBox.checked){
		var isCustomMessage = document.locateResultsForm.customMessageLocateChkBox.value;
		}
		document.forms['locateResultsForm'].action="${context}/locateebxresults/generateExcelReport.html?isCustomMessage="+isCustomMessage+
		"&selectedRuleId="+escape(selectedRuleId)+
		"&selectedSpsId="+escape(selectedSpsId)+
		"&mappedStatus="+isMapped+
		"&unMappedStatus="+isUnmapped+
		"&notAppStatus="+isNotApplicable;
		document.forms['locateResultsForm'].submit();
	}
</script>
<%@ include file="/WEB-INF/jspf/pageTop.jspf"%>

<div class="innerContainer" style="height:96%;"><!-- innerContainer Starts-->
<div class="Level1"><!-- Level1 Starts-->
<div class="unmapped"><!--unmapped Starts-->
<div class="titleBar">
<div class="headerTitle">Unmapped</div>
</div>
<form name="unmappedVarForm">
<input type="hidden" id="contextPath" value= "<%=request.getContextPath()%>"/>
<input type="hidden" name="spsIdHidden" id="spsIdHidden" value="" />
<input type="hidden" name="ruleIdHidden" id="ruleIdHidden" value="" />
<input type="hidden" id="pageName" name="pageName" value="viewLanding"/>
<div class="ListTableDiv" style="height:252px;width:470px;" id="unmappedTableDiv"><!--Starts submitterContinue-->
<table border="0" cellpadding="0" cellspacing="0"	id="unMappedTable" class="mappedTable Pd3 shadedText">
	<THEAD>
		<tr class="UnmappedVariables unmappedTable">			
			<th id="spsIdUnmapped" width="122px" class="tableHeader">
			SPS ID/Header Rule
			<span id="spsheaderIconUnmapped" class="ui-widget-header" style="background:none; border:0px; position:right; top:-4px; left:-7px;height:10px;">
					<span class="ui-icon ui-icon-carat-1-s" style="position:right;height:10px;"/>
			</span>
			</th>
			<th id="descriptionUnmapped"  width="144px" class="tableHeader">
			Description
			<span id="descriptionIconUnmapped" class="ui-widget-header" style="padding-left:55px;display:inline-block;background:none; border:0px; position:right; top:-4px; left:-7px;height:10px;">
					<span class="ui-icon ui-icon-carat-1-s" style="position:right;height:10px;"/>
			</span>
			</th>
			<th id="createdUnmapped" width="99px" class="tableHeader">
			Created&nbsp;On
			<span id="createdIconUnmapped" class="ui-widget-header" style="padding-left:55px;display:inline-block;background:none; border:0px; position:right; top:-4px; left:-7px;height:10px;">
					<span class="ui-icon ui-icon-carat-1-s" style="position:right;height:10px;"/>
			</span>
			</th>
			<td width="105px" class="tableHeader" nowrap="nowrap">&nbsp;</td>
		</tr>
	</THEAD>
	<TBODY>
	<c:set var="rowCount" value="0" />
	
	<c:if test="${! empty unmappedSps}" >	
	<c:forEach items="${unmappedSps}" var="unmappedSpsVar">	
		<tr class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
			<input type="hidden" id="unmappedSpsId" value=""/>			
			<td>${unmappedSpsVar.spsId.spsId}</td>
			<td style="word-wrap:break-word;WORD-BREAK:BREAK-ALL;">${unmappedSpsVar.spsId.spsDesc}</td>
			<td><fmt:formatDate pattern="MM/dd/yyyy" value="${unmappedSpsVar.lastChangedTmStamp}" /></td>
			<td nowrap>
				<a href="#" onclick='openViewMappingDialogForSps("${unmappedSpsVar.spsId.spsId}");'>
					<img src="${imageDir}/search_icon.gif" alt="View" title="View" id="view"/>
				</a>&#160;&#160;
				<a href="#" onclick='openCreateFromUnmappedSectionForSps("${unmappedSpsVar.spsId.spsId}");'>
					<img src="${imageDir}/create_icon.gif"  alt="Create" title="Create"/>
				</a>	
					<c:if test="${userUIPermissions.authorizedToMarkAsNotApplicable}">			
					&#160;&#160;
					<a href="#" onclick='openUserCommentsNotApplicableDialogForSps("${unmappedSpsVar.spsId.spsId}");'>
					<img src="${imageDir}/markAsNotApp.gif"  alt="Mark as Not Applicable" title="Mark as Not Applicable"/>
					</a>					
					</c:if>
			</td>
		</tr>
	<c:set var="rowCount" value="${rowCount + 1}"/>
	</c:forEach>
	</c:if>
	
	<c:if test="${! empty unmappedRule}" >	
	<c:forEach items="${unmappedRule}" var="unmappedRuleVar">	
		<tr class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
			<input type="hidden" id="unmappedRuleId" value=""/>			
			<td>${unmappedRuleVar.rule.headerRuleId}</td>
			<td style="word-wrap:break-word;WORD-BREAK:BREAK-ALL;">${unmappedRuleVar.rule.ruleDesc}</td>
			<td><fmt:formatDate pattern="MM/dd/yyyy" value="${unmappedRuleVar.lastChangedTmStamp}" /></td>
			<td nowrap>
				<a href="#" onclick='openViewMappingDialogForRule("${unmappedRuleVar.rule.headerRuleId}");'>
					<img src="${imageDir}/search_icon.gif" alt="View" title="View" id="view"/>
				</a>&#160;&#160;
				<a href="#" onclick='openCreateFromUnmappedSectionForRule("${unmappedRuleVar.rule.headerRuleId}");'>
					<img src="${imageDir}/create_icon.gif"  alt="Create" title="Create" />
				</a>
					<c:if test="${userUIPermissions.authorizedToMarkAsNotApplicable}">				
					&#160;&#160;
					<a href="#" onclick='openUserCommentsNotApplicableDialogForRule("${unmappedRuleVar.rule.headerRuleId}");'>
					<img src="${imageDir}/markAsNotApp.gif"  alt="Mark as Not Applicable" title="Mark as Not Applicable" />
					</a>
					</c:if>
			</td>
		</tr>
	<c:set var="rowCount" value="${rowCount + 1}"/>
	</c:forEach>
	</c:if>	
	<c:if test="${empty unmappedRule && empty unmappedSps}" >
	<tr> <td colspan = "4" align="center" >No Data Found </td> </tr>
	</c:if>
	</TBODY>
</table>
</div>
</form>
<!--UnmappedVariables Ends--></div>
<!--unmapped Ends-->
<form name="editForm" method="POST">

<input type="hidden" id="getAllPrint" name="getAllPrint" value="" />
<input type="hidden" id="appendUserPrint" name="appendUserPrint" value="" />
<input type="hidden" id="selectedSpsIdForEditSub" name="selectedSpsIdCusForEdit" value="" />
<input type="hidden" id="selectedRuleIdForEditSub" name="selectedRuleIdCusForEdit" value="" />
<input type="hidden" id="selectedSpsIdForEdit" name="selectedSpsIdForEdit" value="" />
<input type="hidden" id="selectedruleForEditId" name="selectedruleForEdit" value="" />
<input type="hidden" name="authorizedToapprove" id="authorizedToapprove" value="${userUIPermissions.authorizedToapprove}"/>
<input type="hidden" name="authorizedTounlock" id="authorizedTounlock" value="${userUIPermissions.authorizedToUnlock}"/>
<input type="hidden" id="pageName" name="pageName" value="viewlandingewpdbx"/>
<div class="inprogress"><!--inprogress Starts-->
<div class="titleBar">
<div class="headerTitle">In Progress 
<a href="#" onclick='toggleUserIcon();' class="toggleUserIcon" ><img src="${imageDir}/icon-by-customer.gif" id="toggled" style="height:15px;" alt="Show All Users" title="Show All Users"></a>
<a href="#" onclick='toggleNumRecords();'><img src="${imageDir}/copy_landing.gif" id="numRecords" style="height:15px;" alt="Show All Mappings" title="Show All Mappings"></a>
<a href="#" onclick='printInprogress();'><img src="${imageDir}/print.gif" id="printIcon" style="height:15px;" alt="Print" title="Print"></a>

</div>
</div>
<div id="AccordionContainer" class="AccordionContainer"   >
<div> 

	 <div class="ListTableDivSub" id="inProgressDivHeader1">
 <table id="inProgressTable1" border="0" cellpadding="0" cellspacing="0" style="width: 470px;">
			<tr class="UnmappedVariables unmappedTable">
				<td width="50px" class="tableHeader">SPS/Rule</td>
				<td width="114px" class="tableHeader">Description</td>
				<td width="69px" class="tableHeader">Updated&nbsp;On</td>
				<td width="89px" class="tableHeader">User&nbsp;Id</td>
				<td width="148px" class="tableHeader" nowrap="nowrap">
				<div class="AccordionTitle" onClick="runAccordion(1);" style="text-align : right;padding-right : 23px;" onselectstart="return false;">
				
				<span class="ui-widget-header" style="display:inline-block;background:none; border:0px; position:relative; top:-4px; left:-7px;height:10px;">
						<span class="ui-icon ui-icon-triangle-1-e" style="position:relative;height:15px;"/>
				</span>
				
				</div>
				</td>
			</tr>
	</table>
	</div>

</div>

<div id="Accordion1Content" class="AccordionContent">
	<div class="ListTableDiv" style="width:470px;height:223px;" id="inProgressDiv"">
	<!--  <div class="ListTableDiv" style="width:470px;height:223px;" id="inProgressDiv" style="display:none;"> --><!--Starts submitterContinue-->
<table id="inProgressTable2" border="0" cellpadding="0" cellspacing="0" class="mappedTable Pd3 shadedText">
	<thead >
	  <tr class="UnmappedVariables unmappedTable" >
	  			<th  id="spsheaderId" width="50px" class="tableHeader" style="width: 50px;">
	  			SPS/Rule
	  			 <span id="spsheaderIcon" class="ui-widget-header" style="padding-left:100px;display:inline-block;background:none; border:0px; position:right; top:-4px; left:-7px;height:10px;">
						<span class="ui-icon ui-icon-carat-1-s" style="position:right;height:10px;"/>
				</span>
				
	  			</th>
				<th  id="descriptionId"  width="114px" class="tableHeader">Description
				<span id="descriptionIcon" class="ui-widget-header" style="padding-left:70px;display:inline-block;background:none; border:0px; position:relative; top:-4px; left:-7px;height:10px;">
						<span class="ui-icon ui-icon-carat-1-s" style="position:relative;height:10px;"/>
				</span>
				</th>
				
				<th  id="updatedId1"  width="69px" class="tableHeader">Updated&nbsp;On
				<span id="updatedIcon1" class="ui-widget-header" style="padding-left:70px;display:inline-block;background:none; border:0px; position:relative; top:-4px; left:-7px;height:10px;">
						<span class="ui-icon ui-icon-carat-1-s" style="position:relative;height:10px;"/>
				</span>
				</th>
				
				<th  id="userId"  width="89px" class="tableHeader">User&nbsp;Id 
				<span id="userIcon1" class="ui-widget-header" style="padding-left:70px;display:inline-block;background:none; border:0px; position:relative; top:-4px; left:-7px;height:10px;">
						<span class="ui-icon ui-icon-carat-1-s" style="position:relative;height:10px;"/>
				</span>
				</th>
				
				<td width="148px" class="tableHeader" nowrap="nowrap">
				<div class="AccordionTitle" onClick="runAccordion(1);" style="text-align : right;padding-right : 9px;" onselectstart="return false;">
				
				
				 <span class="ui-widget-header" style="display:inline-block;background:none; border:0px; position:relative; top:-4px; left:-7px;height:10px;">
						<span class="ui-icon ui-icon-triangle-1-s" style="position:relative;height:10px;"/>
				</span>
				
				</div>
				
				</td>
			</tr>
	</thead>
	<TBODY>

	<c:set var="rowCount" value="0" />
		<c:if test="${! empty inprogressSps}" >		
		<c:forEach items="${inprogressSps}" var="inProgressSpsVar">
		
			<c:if test="${!inProgressSpsVar.finalized}">
				<c:set var="rowClass" value="finalized" />
			</c:if>
			<c:if test="${inProgressSpsVar.finalized}">
				<c:set var="rowClass"  value="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}"/>		
			</c:if>	
			<tr class="${rowClass}">
				<td width="50px">${inProgressSpsVar.spsId.spsId}</td>
				<td width="114px" style="word-wrap:break-word;WORD-BREAK:BREAK-ALL;" >${inProgressSpsVar.spsId.spsDesc}</td>
				<td width="79px"><fmt:formatDate pattern="MM/dd/yyyy" value="${inProgressSpsVar.lastChangedTmStamp}" /></td>
				<td width="99px">${inProgressSpsVar.user.lastUpdatedUserName}</td>
				<td width="105px">
					<a href="#" onclick ='openViewMappingInProgressDialogForSps("${inProgressSpsVar.spsId.spsId}");'><img src="${imageDir}/search_icon.gif" alt="View" title="View"/></a>&#160;&#160;
					<c:set var="lock" value="1"/>
					<c:if test="${empty inProgressSpsVar.lock}">
						<c:set var="lock" value="0"/>
					</c:if>
					<c:if test ="${inProgressSpsVar.lock.lockUserId  == userName}">
						<c:set var="lock" value="0"/>
					</c:if>
					<c:if test="${lock == 0}">
						<c:set var="idEdit" value="${inProgressSpsVar.spsId.spsId}_Edit" />
						<a href="#" onclick='editMappingForSps("${inProgressSpsVar.spsId.spsId}");'><img src="${imageDir}/edit_icon.gif" alt="Edit" title="Edit"/></a>&#160;&#160;
					</c:if>
					<c:if test="${lock == 1}">
						<c:if test="${userUIPermissions.authorizedToUnlock}">
							<c:set var="idLock" value="${inProgressSpsVar.spsId.spsId}_Lock" />
							<a href="#" id="${idLock}" onclick='unlockMappingFromLocate("","${inProgressSpsVar.spsId.spsId}","unlockSpsFromLanding","${inProgressSpsVar.lock.lockUserId}");'><img src="${imageDir}/lock_icon.jpg" alt="Lock" title="Lock"  style="height:15px;"/></a>&#160;&#160;
						</c:if>
				
						<c:if test="${!userUIPermissions.authorizedToUnlock}"> 
							<img src="${imageDir}/lock_icon.jpg" alt="Lock" title="Lock"  style="height:15px;"/>&#160;&#160;
						</c:if>
					</c:if>
					<c:set var="sentToTest" value="0"/>
					<c:set var="approve" value="0"/>
					<c:set var="idTest" value="${inProgressSpsVar.spsId.spsId}_Test" />
					<c:set var="idApprove" value="${inProgressSpsVar.spsId.spsId}_Approve" />		
					
					<c:if test="${inProgressSpsVar.variableMappingStatus == 'SCHEDULED_TO_TEST'}">
						<c:set var="sentToTest" value="1"/>
						<c:set var="approve" value="1"/>
					</c:if>
					<c:if test="${inProgressSpsVar.variableMappingStatus == 'OBJECT_TRANSFERRED'}">
						<c:set var="sentToTest" value="1"/>
					</c:if>
					<c:if test="${inProgressSpsVar.variableMappingStatus == 'SCHEDULED_TO_PRODUCTION' || inProgressSpsVar.variableMappingStatus == 'PUBLISHED'}">
						<c:set var="sentToTest" value="1"/>
						<c:set var="approve" value="1"/>
					</c:if>
					<c:if test="${sentToTest != 1 && lock != 1}">
						<a href="#" id ="${idTest}" onclick='openUserCommentsSend2TestDialogForSps("${inProgressSpsVar.spsId.spsId}");'><img src="${imageDir}/test_icon.gif" alt="Send to Test" title="Send to Test" /></a>&#160;&#160;
					</c:if>	
					 <!-- Only for the approver //To Do: Access check -->	
					<c:if test="${approve != 1 && lock != 1}">
							<c:if test="${userUIPermissions.authorizedToapprove || inProgressSpsVar.variableMappingStatus == 'OBJECT_TRANSFERRED' }">						
								<a href="#" id ="${idApprove}" onclick='openUserCommentsApproveDialogForSps("${inProgressSpsVar.spsId.spsId}");'><img src="${imageDir}/approve_icon.gif" alt="Approve" title="Approve"/></a>
							</c:if>	
					</c:if>			
				</td>			
			</tr>
		<c:set var="rowCount" value="${rowCount + 1}"/>
		</c:forEach>
		</c:if>
		
		<c:if test="${! empty inprogressRule}" >		
		<c:forEach items="${inprogressRule}" var="inprogressRuleVar">			 
			<tr class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">				
				<td width="50px">${inprogressRuleVar.rule.headerRuleId}</td>
				<td width="114px"style="word-wrap:break-word;WORD-BREAK:BREAK-ALL;" width="154px">${inprogressRuleVar.rule.ruleDesc}</td>
				<td width="79px"><fmt:formatDate pattern="MM/dd/yyyy" value="${inprogressRuleVar.lastChangedTmStamp}" /></td>
				<td width="99px">${inprogressRuleVar.user.lastUpdatedUserName}</td>
				<td width="105px">
					<a href="#" onclick ='openViewMappingInProgressDialogForRule("${inprogressRuleVar.rule.headerRuleId}");'><img src="${imageDir}/search_icon.gif" alt="View" title="View" /></a>&#160;&#160;
					<c:set var="lock" value="1"/>
					<c:if test="${empty inprogressRuleVar.lock}">
						<c:set var="lock" value="0"/>
					</c:if>
					<c:if test ="${inprogressRuleVar.lock.lockUserId  == userName}">
						<c:set var="lock" value="0"/>
					</c:if>
					<c:if test="${lock == 0}">
						<c:set var="idEdit" value="${inprogressRuleVar.rule.headerRuleId}_Edit" />
						<a href="#" onclick='editMappingForRule("${inprogressRuleVar.rule.headerRuleId}");'><img src="${imageDir}/edit_icon.gif" alt="Edit" title="Edit" /></a>&#160;&#160;
					</c:if>
					<c:if test="${lock == 1}">
						<c:if test="${userUIPermissions.authorizedToUnlock}">
							<c:set var="idLock" value="${inprogressRuleVar.rule.headerRuleId}_Lock" />
							<a href="#" id="${idLock}" onclick='unlockMappingFromLocate("${inprogressRuleVar.rule.headerRuleId}","","unlockRuleFromLanding","${inProgressSpsVar.lock.lockUserId}");'><img src="${imageDir}/lock_icon.jpg" alt="Lock" title="Lock"  style="height:15px;"/></a>&#160;&#160;
						</c:if>
				
						<c:if test="${!userUIPermissions.authorizedToUnlock}"> 
							<img src="${imageDir}/lock_icon.jpg" alt="Lock" title="Lock"  style="height:15px;"/>&#160;&#160;
						</c:if>
					</c:if>
					<c:set var="sentToTest" value="0"/>
					<c:set var="approve" value="0"/>
					<c:set var="idTest" value="${inprogressRuleVar.rule.headerRuleId}_Test" />
					<c:set var="idApprove" value="${inprogressRuleVar.rule.headerRuleId}_Approve" />		
					
					<c:if test="${inprogressRuleVar.variableMappingStatus == 'SCHEDULED_TO_TEST'}">
						<c:set var="sentToTest" value="1"/>
						<c:set var="approve" value="1"/>
					</c:if>
					<c:if test="${inprogressRuleVar.variableMappingStatus == 'OBJECT_TRANSFERRED'}">
						<c:set var="sentToTest" value="1"/>
					</c:if>
					<c:if test="${inprogressRuleVar.variableMappingStatus == 'SCHEDULED_TO_PRODUCTION' || inprogressRuleVar.variableMappingStatus == 'PUBLISHED'}">
						<c:set var="sentToTest" value="1"/>
						<c:set var="approve" value="1"/>
					</c:if>
					<c:if test="${sentToTest != 1 && lock != 1}">
						<a href="#" id ="${idTest}" onclick='openUserCommentsSend2TestDialogForRule("${inprogressRuleVar.rule.headerRuleId}");'><img src="${imageDir}/test_icon.gif" alt="Send to Test" title="Send to Test"/></a>&#160;&#160;
					</c:if>	
					<!--  Only for the approver //To Do: Access check -->	
					<c:if test="${approve != 1 && lock != 1}">	
							<c:if test="${userUIPermissions.authorizedToapprove || inprogressRuleVar.variableMappingStatus == 'OBJECT_TRANSFERRED' }">					
								<a href="#" id ="${idApprove}" onclick='openUserCommentsApproveDialogForRule("${inprogressRuleVar.rule.headerRuleId}");'><img src="${imageDir}/approve_icon.gif" alt="Approve" title="Approve"/></a>
							</c:if>	
					</c:if>			
				</td>			
			</tr>
		<c:set var="rowCount" value="${rowCount + 1}"/>
		</c:forEach>
		</c:if>


	</TBODY>
</table>
</div>
</div>

<div> 

<div class="ListTableDivSub" id="inProgressDivHeader2">
<table id="inProgressTableCustomMessage1" border="0" cellpadding="0" cellspacing="0" style="width: 470px;" >
		<tr class="UnmappedVariables unmappedTable">
			<td width="50px" class="tableHeader">Rule</td>
			<td width="30px" class="tableHeader">SPS</td>
			<td width="104px" class="tableHeader">Message</td>
			<td width="69px" class="tableHeader" nowrap="nowrap">Updated&nbsp;On</td>
			<td width="89px" class="tableHeader">User&nbsp;Id</td> 
			<td width="128px" class="tableHeader" nowrap="nowrap">
				<div class="AccordionTitle" onClick="runAccordion(2);" style="text-align : right;padding-right : 23px;" onselectstart="return false;">
				
				<span class="ui-widget-header" style="display:inline-block;background:none; border:0px; position:relative; top:-4px; left:-7px;height:10px;">
						<span class="ui-icon ui-icon-triangle-1-e" style="position:relative;height:15px;"/>
				</span>
				
				</div>
				</td>
		</tr>
</table>
</div>
</div>

<div id="Accordion2Content" class="AccordionContent">
	<div class="ListTableDiv" id="inProgressDivSub" style="width:470px; height:223px;"><!--Starts submitterContinue-->
<table id="inProgressTableSub" border="0" cellpadding="0" cellspacing="0" class="mappedTable Pd3 shadedText">
	<thead>
	    <tr class="UnmappedVariables unmappedTable" >
			<th id="ruleEwpdId" width="50px" class="tableHeader">
				Rule
				<span id="ruleIcon" class="ui-widget-header" style="padding-left:45px;display:inline-block;background:none; border:0px; position:right; top:-4px; left:-7px;height:10px;">
						<span class="ui-icon ui-icon-carat-1-s" style="position:right;height:10px;"/>
				</span>
			</th>
			<th id="spsEwpdId" width="30px" class="tableHeader">
				SPS
				<span id="spsIcon" class="ui-widget-header" style="padding-left:30px;display:inline-block;background:none; border:0px; position:right; top:-4px; left:-7px;height:10px;">
						<span class="ui-icon ui-icon-carat-1-s" style="position:right;height:10px;"/>
				</span>
			</th>
			<th id="messageId" width="104px" class="tableHeader">
				Message
				<span id="messageIcon" class="ui-widget-header" style="padding-left:70px;display:inline-block;background:none; border:0px; position:right; top:-4px; left:-7px;height:10px;">
						<span class="ui-icon ui-icon-carat-1-s" style="position:right;height:10px;"/>
				</span>
			</th>
			<th id="updatedId2" width="69px" class="tableHeader">
				Updated&nbsp;On
				<span id="updatedIcon2" class="ui-widget-header" style="padding-left:62px;display:inline-block;background:none; border:0px; position:right; top:-4px; left:-7px;height:10px;">
						<span class="ui-icon ui-icon-carat-1-s" style="position:right;height:10px;"/>
				</span>
			</th>
			<th id="userId2" width="89px" class="tableHeader">
				User&nbsp;Id
				<span id="userIcon2" class="ui-widget-header" style="padding-left:62px;display:inline-block;background:none; border:0px; position:right; top:-4px; left:-7px;height:10px;">
						<span class="ui-icon ui-icon-carat-1-s" style="position:right;height:10px;"/>
				</span>
			</th>
		        <td width="128px" class="tableHeader" nowrap="nowrap">
		        
		      	<div class="AccordionTitle" onClick="runAccordion(2);" style="text-align : right;padding-right : 9px;" onselectstart="return false;">
		      	
				
				<span class="ui-widget-header" style="display:inline-block;background:none; border:0px; position:relative; top:-4px; left:-7px;height:10px;">
						<span class="ui-icon ui-icon-triangle-1-s" style="position:relative;height:10px;"/>
				</span>
				
				</div>
				</td>
				
		</tr>
	</thead>
	<TBODY>	 
	
		<c:if test="${! empty inprogressCustomMsg}" >		
		<c:set var="rowCount" value="0" />
		<c:forEach items="${inprogressCustomMsg}" var="inProgressCusVar">

			<tr class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">	
				<td width="50px">${inProgressCusVar.rule.headerRuleId}</td>
				<td width="30px">${inProgressCusVar.spsId.spsId}</td>
				<td width="104px" style="word-wrap:break-word;WORD-BREAK:BREAK-ALL;">${inProgressCusVar.messageForDisplay}</td>
				<td width="69px"><fmt:formatDate pattern="MM/dd/yyyy" value="${inProgressCusVar.lastChangedTmStamp}" /></td>
				<td width="89px">${inProgressCusVar.user.lastUpdatedUserName}</td>
				<td width="128px">
					<a href="#" onclick ='openViewMappingInProgressDialogForCustomMsg("${inProgressCusVar.rule.headerRuleId}","${inProgressCusVar.spsId.spsId}");'><img src="${imageDir}/search_icon.gif" alt="View" title="View" /></a>&#160;&#160;
					<c:set var="lock" value="1"/>
					<c:if test="${empty inProgressCusVar.lock}">
						<c:set var="lock" value="0"/>
					</c:if>
					<c:if test ="${inProgressCusVar.lock.lockUserId  == userName}">
						<c:set var="lock" value="0"/>
					</c:if>
					<c:if test="${lock == 0}">
						<c:set var="idEdit" value="${inProgressCusVar.rule.headerRuleId}${inProgressCusVar.spsId.spsId}_Edit" />
						<a href="#" onclick='editMappingForCustomMsg("${inProgressCusVar.rule.headerRuleId}","${inProgressCusVar.spsId.spsId}");'><img src="${imageDir}/edit_icon.gif" alt="Edit" title="Edit"/></a>&#160;&#160;
					</c:if>
					<c:if test="${lock == 1}">
						<c:if test="${userUIPermissions.authorizedToUnlock}">
							<c:set var="idLock" value="${inProgressCusVar.rule.headerRuleId}${inProgressCusVar.spsId.spsId}_Lock" />
							<a href="#" id="${idLock}" onclick='unlockMappingFromLocate("${inProgressCusVar.rule.headerRuleId}","${inProgressCusVar.spsId.spsId}","unlockCustomMsgFromLanding","${inProgressCusVar.lock.lockUserId}");'><img src="${imageDir}/lock_icon.jpg" alt="Lock" title="Lock"  style="height:15px;"/></a>&#160;&#160;
						</c:if>
				
						<c:if test="${!userUIPermissions.authorizedToUnlock}"> 
							<img src="${imageDir}/lock_icon.jpg" alt="Lock" title="Lock"  style="height:15px;"/>&#160;&#160;
						</c:if>
					</c:if>
					<c:set var="sentToTest" value="0"/>
					<c:set var="approve" value="0"/>
					<c:set var="idTest" value="${inProgressCusVar.rule.headerRuleId }${inProgressCusVar.spsId.spsId}_Test" />
					<c:set var="idApprove" value="${inProgressCusVar.rule.headerRuleId}${inProgressCusVar.spsId.spsId}_Approve" />		
					
					<c:if test="${inProgressCusVar.variableMappingStatus == 'SCHEDULED_TO_TEST'}">
						<c:set var="sentToTest" value="1"/>
						<c:set var="approve" value="1"/>
					</c:if>
					<c:if test="${inProgressCusVar.variableMappingStatus == 'OBJECT_TRANSFERRED'}">
						<c:set var="sentToTest" value="1"/>
					</c:if>
					<c:if test="${inProgressCusVar.variableMappingStatus == 'SCHEDULED_TO_PRODUCTION' || inProgressCusVar.variableMappingStatus == 'PUBLISHED'}">
						<c:set var="sentToTest" value="1"/>
						<c:set var="approve" value="1"/>
					</c:if>
					<c:if test="${sentToTest != 1 && lock != 1}">
						<a href="#" id ="${idTest}" onclick='openUserCommentsSend2TestDialogForCustomMsg("${inProgressCusVar.rule.headerRuleId}","${inProgressCusVar.spsId.spsId}");'><img src="${imageDir}/test_icon.gif" alt="Send to Test" title="Send to Test" /></a>&#160;&#160;
					</c:if>	
					<!-- Only for the approver //To Do: Access check -->	
					<c:if test="${approve != 1 && lock != 1}">
							<c:if test="${userUIPermissions.authorizedToapprove || inProgressSpsVar.variableMappingStatus == 'OBJECT_TRANSFERRED' }">						
								<a href="#" id ="${idApprove}" onclick='openUserCommentsApproveDialogForCustomMsg("${inProgressCusVar.rule.headerRuleId}","${inProgressCusVar.spsId.spsId}");'><img src="${imageDir}/approve_icon.gif" alt="Approve" title="Approve"/></a>
							</c:if>
					</c:if>			
				</td>	
		
			</tr>
		<c:set var="rowCount" value="${rowCount + 1}"/>
		</c:forEach>
		</c:if>		

	</TBODY>
</table>
</div>
</div>
</div>
</div>
</form>
<!--In Progress Ends--></div>
<!-- Level1 Ends-->
<div class="clear"></div>
<div class="Level2 mt10"><!-- Level2 Starts-->

<div class="mapping">
<div class="titleBar">
<div class="headerTitle">Create</div>
</div>
<form name="createform">
<div class="createMapping">
<table border="0" cellpadding="0" cellspacing="0"
	class="mt10 mL10 Pd3 shadedText">
	<tr>
		<td width="45px">SPS ID <span class="star">*</span></td>
		<td width="140px"><input type="textbox" class="inputbox65" name = "selectedSpsId" id="spsId" onfocus="clearCreateOrLocateFields('spsId','Create')"
		onkeypress="enterKeyPress('create',event);"/></td>
		 <td width="160px"><label id="spsIdLabel" for="selectedSpsId" style="font-size:11px"></label></td> 
		  <td class="verdana" nowrap>
			<input type="checkbox" name="customMessageChkBox" id="customMessageChkBox" onclick="clearCreateOrLocateFields('customMessageChkBox','Create')"/>&#160;Message Text&#160;&#160;&#160;							
		 </td>   
	</tr>
	<tr>
		<td width="45px">Rule ID <span class="star">*</span></td>
		<td width="140px"><input type="textbox" class="inputbox65" name = "selectedruleId" id="ruleId" onfocus="clearCreateOrLocateFields('ruleId','Create')"
		onkeypress="enterKeyPress('create',event);"/></td>
		 <td><label id="ruleIdLabel" for="selectedruleId" style="font-size:11px"></label></td> 
	</tr>
	<tr>
		<td>&#160;</td>
		<td><a href="#" id="createButton" onclick="openCreatePage()"><img src="${imageDir}/create_but.gif" width="78"
			height="23" /></a></td>
	</tr>
</table>
</div>
</form>
<!--createMapping Ends--></div>
<!--mapping Ends-->


<div class="Locate">
<div class="titleBar">
<div class="headerTitle">Locate <span style="padding-left:390px;position:right;"><a style="color: #000000; font-size: 10;" href="${pageContext.request.contextPath}/advancesearchebx/viewSearch.html" id="link2"><img src="${imageDir}/Search-icon.png" width="16"

height="16" alt="Advanced Search" title="Advanced Search"/></a></span>

</div>
</div>
<form name="locateResultsForm" action="${context}/locateresults.html" method="POST" onkeypress="('locate',event);">
<div class="createTable">
<table border="0" cellpadding="0" cellspacing="0"
	class="mt10 mL10 Pd3 shadedText">
<input type="hidden" name="currentPage" id="currentPage" value="0"/>
	<tr>
		<td width="45px">SPS ID<span class="star">*</span></td>
		<td width="160px"><input type="textbox" class="inputbox65" id="spsIdFrmLocate" name="spsIDLocate" onfocus="clearCreateOrLocateFields('spsIdFrmLocate','Locate')"
		onkeypress="enterKeyPress('locate',event);"/></td>
		<td width="30px">Show</td>
		<td width="100px" class="verdana">
			<input type="checkbox" checked name="isUnMapped" id="isUnMapped" value="unMapped" onclick="setFocus('locate');"/>Unmapped
		</td>
		  <td class="verdana" nowrap>
			<input type="checkbox" name="customMessageLocateChkBox" id="customMessageLocateChkBox"  value="isCustomMessage" onclick="clearCreateOrLocateFields('customMessageLocateChkBox','Locate')"
			/>&#160;Message Text&#160;&#160;&#160;					
		 </td>   
	</tr>
	<tr>
		<td>Rule ID<span class="star">*</span></td>
		<td><input type="textbox" class="inputbox65" id="ruleIdFrmLocate" name="ruleIDLocate" onfocus="clearCreateOrLocateFields('ruleIdFrmLocate','Locate')"
		onkeypress="enterKeyPress('locate',event);"/></td>
		<td>&#160;</td>
		<td class="verdana"><input type="checkbox" checked name="isMapped" id="isMapped" value="isMapped" onclick="setFocus('locate');"/>Mapped</td>
	</tr>	
	<tr>
		<td>&#160;</td>		
		<td><a href="#" id="locateButton" onclick="openLocateResultsDialog('0','fromLanding');"><img src="${imageDir}/locate_but.gif" width="78"
			height="23" /></a></td>
		<td>&#160;</td>
		<td class="verdana" valign="top"><input type="checkbox" checked name="isNotApplicable" id="isNotApplicable" value="isNotApplicable" onclick="setFocus('locate');"/>Not Applicable</td>
	</tr>
</table>
</div>

</form>
<!--createMapping Ends--></div>

</div>
<!-- Level2 Ends--></div>

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
<div id="contractBxMappingDialog" title="Contract Mapping">
</div>
<div id="lockAuditReportDialog" title="Lock Audit Report">
</div>
<!-- innerContainer Ends-->
<%@ include file="/WEB-INF/jspf/pageFooterEWPD.jspf"%> 

<div id="userCommentsApproveDialogForSps"  style="display: none;">
<form name="userCommentsApproveSpsForm" action="${context}/stateflowewpdbx/approveSps.html" method="POST">
<input type="hidden" name="approvestateflowspsId" id="approvestateflowspsId" value="" />
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

<div id="userCommentsApproveDialogForRule"  style="display: none;">
<form name="userCommentsApproveRuleForm" action="${context}/stateflowewpdbx/approveRule.html" method="POST">
<input type="hidden" name="approvestateflowruleId" id="approvestateflowruleId" value="" />
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

<div id="userCommentsApproveDialogForCustomMsg"  style="display: none;">
<form name="userCommentsApproveCustomMsgForm" action="${context}/stateflowewpdbx/approveCustomMsg.html" method="POST">
<input type="hidden" name="approvestateflowruleIdCus" id="approvestateflowruleIdCus" value="" />
<input type="hidden" name="approvestateflowspsIdCus" id="approvestateflowspsIdCus" value="" />
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

<div id="userCommentsSent2TestDialogForSps" style="display: none;">
<form name="userCommentsSent2TestSpsForm" action="${context}/stateflowewpdbx/sendToTestSps.html" method="POST">
 <input type="hidden" name="send2teststateflowspsId" id="send2teststateflowspsId" value="" /> 
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

<div id="userCommentsSent2TestDialogForRule" style="display: none;">
<form name="userCommentsSent2TestRuleForm" action="${context}/stateflowewpdbx/sendToTestRule.html" method="POST">
 <input type="hidden" name="send2teststateflowruleId" id="send2teststateflowruleId" value="" /> 
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

<div id="userCommentsSent2TestDialogForCustomMsg" style="display: none;">
<form name="userCommentsSent2TestCustomMsgForm" action="${context}/stateflowewpdbx/sendToTestCustomMsg.html" method="POST">
 <input type="hidden" name="send2teststateflowruleIdCus" id="send2teststateflowruleIdCus" value="" /> 
 <input type="hidden" name="send2teststateflowspsIdCus" id="send2teststateflowspsIdCus" value="" />
  <input type="hidden" id="pageName" name="pageName" value="landingEbx"/>
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

<div id="userCommentsNotApplicableSpsDialog" style="display: none;">
<form name="userCommentsNotApplicableSpsForm" action="${context}/stateflowewpdbx/markSpsAsNotApplicable.html" method="POST">
 <input type="hidden" name="notApplicablespsId" id="notApplicablespsId" value="" />
  <input type="hidden" id="pageName" name="pageName" value="landingEbx"/>
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

<div id="userCommentsNotApplicableRuleDialog" style="display: none;">
<form name="userCommentsNotApplicableRuleForm" action="${context}/stateflowewpdbx/markRuleAsNotApplicable.html" method="POST">
 <input type="hidden" name="notApplicableruleId" id="notApplicableruleId" value="" />  
 <input type="hidden" id="pageName" name="pageName" value="landingEbx"/>
	<table id="NARuleCommentsTable" border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5 bT">          
	  <tr class="">
        <td ><textarea name="notApplicableMappingRuleComments"  id="notApplicableMappingRuleComments" 
		rows="5" cols="77"></textarea></td>
	  </tr> 			
	</table>
	<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
      <tr>       
        <td >
			<a
			href="#" onclick="saveUserCommentsForNotApplicableForRule();"><img  id="notApplicableSaveImg" src="${imageDir}/save_but.gif" 
			alt="Save"  title="Save"/></a>
		</td>		
      </tr>
	</table>	
</form>
</div>

	<div id="userCommentsDialogForSps" style="display: none;">	
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
	
	<div id="userCommentsDialogForRule" style="display: none;">	
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
	
	<div id="userCommentsDialogForCustomMsg" style="display: none;">	
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
		
<div id="viewLoacteDialog" title="Locate Results">
	
</div>
<div id="confirmationDivUnlock"></div>
<div id="referenceDataDialog" title="Reference Data">
</div>
<div id="ooaMessageReportDialog" title="OOA Message Search Criteria">
</div>
<%@include file="/WEB-INF/jspf/messageTray.jspf"%>
</body>
</html>