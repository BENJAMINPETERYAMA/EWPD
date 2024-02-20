<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> 

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/jspf/pageHead.jspf"%>

</head>
<body>
	<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
	<c:set var="context" value="${pageContext.request.contextPath}" />
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/umRuleUtil.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery.dataTables.nightly.js"></script>
	<script type="text/javascript">
		 // OOAMessage Scripts start here 30296... 
		var diaCloseIcon = false;
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


		// OOAMessage Scripts End here...

		function setFocus(elementId) {
			document.getElementById(elementId).focus();

		}
		function enterKeyPress(e) {
			// look for window.event in case event isn't passed in
			if (window.event) {
				e = window.event;
			}
			if (e.keyCode == 13) {
				document.getElementById('locateButton').click();
			}
		}
		function referenceData() {
			$.ajax({
				url : "${context}/referencedata/showReferenceDataPopup.ajax",
				dataType : "html",
				type : "POST",
				data : "key=/viewlandingpage.html",
				success : function(data) {
					$("#referenceDataDialog").html(data);
					$("#referenceDataDialog").dialog({
						height : '300',
						width : '490px',
						show : 'slide',
						modal : true,
						resizable : false,
						title : 'Referance Data'

					});
					$("#referenceDataDialog").dialog();
				}
			});
		}
		function contractBx() {
			$.ajax({
				url : "${context}/vieworexportreport/contractBxMapping.ajax",
				dataType : "html",
				type : "POST",
				success : function(data) {
					$("#contractBxMappingDialog").html(data);
					$("#contractBxMappingDialog").dialog({
						height : '500',
						width : '690px',
						show : 'slide',
						modal : true,
						resizable : false,
						title : 'Reports Mapping'

					});
					$("#contractBxMappingDialog").dialog();
				}
			});
		}

		function auditReport() {
			$
					.ajax({
						url : "${context}/lockedvariableauditreport/auditReportView.ajax",
						dataType : "html",
						type : "POST",
						success : function(data) {
							$("#lockAuditReportDialog").html(data);
							$("#lockAuditReportDialog").dialog({
								height : '500',
								width : '690px',
								show : 'slide',
								modal : true,
								resizable : false,
								title : 'Lock Audit Report'

							});
							$("#lockAuditReportDialog").dialog();
						}
					});
		}

		function auditReportTest() {
			$
					.ajax({
						url : "${context}/lockedvariableauditreport/auditReportViewTest.ajax",
						dataType : "html",
						type : "POST",
						success : function(data) {
							$("#lockAuditReportDialog").html(data);
							$("#lockAuditReportDialog").dialog({
								height : '500',
								width : '690px',
								show : 'slide',
								modal : true,
								resizable : false,
								title : 'Lock Audit Report'

							});
							$("#lockAuditReportDialog").dialog();
						}
					});
		}

		var oTable;
		$(document).ready(
				function() {

					$("#systemId").click(
							function(event) {
								$('#systemIcon').attr('style',
										'display : block, background : none');
								$('#variableIcon').attr('style',
										'display : none');
								$('#descIcon').attr('style', 'display : none');
								$('#createdIcon').attr('style',
										'display : none');
							});
					$("#variableWpdId").click(
							function(event) {
								$('#systemIcon')
										.attr('style', 'display : none');
								$('#variableIcon').attr('style',
										'display : block, background : none');
								$('#descIcon').attr('style', 'display : none');
								$('#createdIcon').attr('style',
										'display : none');
							});
					$("#descId").click(
							function(event) {
								$('#systemIcon')
										.attr('style', 'display : none');
								$('#variableIcon').attr('style',
										'display : none');
								$('#descIcon').attr('style',
										'display : block, background : none');
								$('#createdIcon').attr('style',
										'display : none');
							});
					$("#createdId").click(
							function(event) {
								$('#systemIcon')
										.attr('style', 'display : none');
								$('#variableIcon').attr('style',
										'display : none');
								$('#descIcon').attr('style', 'display : none');
								$('#createdIcon').attr('style',
										'display : block, background : none');
							});

					$("#systemIdProg").click(
							function(event) {
								$('#systemIconProg').attr('style',
										'display : block, background : none');
								$('#variableIconProg').attr('style',
										'display : none');
								$('#descIconProg').attr('style',
										'display : none');
								$('#updatedIconProg').attr('style',
										'display : none');
								$('#userIdIconProg').attr('style',
										'display : none');
							});
					$("#varIdProg").click(
							function(event) {
								$('#systemIconProg').attr('style',
										'display : none');
								$('#variableIconProg').attr('style',
										'display : block, background : none');
								$('#descIconProg').attr('style',
										'display : none');
								$('#updatedIconProg').attr('style',
										'display : none');
								$('#userIdIconProg').attr('style',
										'display : none');
							});
					$("#descIdProg").click(
							function(event) {
								$('#systemIconProg').attr('style',
										'display : none');
								$('#variableIconProg').attr('style',
										'display : none');
								$('#descIconProg').attr('style',
										'display : block, background : none');
								$('#updatedIconProg').attr('style',
										'display : none');
								$('#userIdIconProg').attr('style',
										'display : none');
							});
					$("#updatedIdProg").click(
							function(event) {
								$('#systemIconProg').attr('style',
										'display : none');
								$('#variableIconProg').attr('style',
										'display : none');
								$('#descIconProg').attr('style',
										'display : none');
								$('#updatedIconProg').attr('style',
										'display : block, background : none');
								$('#userIdIconProg').attr('style',
										'display : none');
							});
					$("#userIdProg").click(
							function(event) {
								$('#systemIconProg').attr('style',
										'display : none');
								$('#variableIconProg').attr('style',
										'display : none');
								$('#descIconProg').attr('style',
										'display : none');
								$('#updatedIconProg').attr('style',
										'display : none');
								$('#userIdIconProg').attr('style',
										'display : block, background : none');
							});

				});
		function sniffer() {
			if (screen.height == 1024) {
				document.getElementById('unmappedTableDiv').style.height = "380px";
				document.getElementById("inProgressDiv").style.height = "380px";
			} else if (screen.height == 864) {
				document.getElementById("unmappedTableDiv").style.height = "277px";
				document.getElementById("inProgressDiv").style.height = "277px";
			}
		}

		$(document).ready(function() {
			document.getElementById('systemIcon').style.display = 'none';
			document.getElementById('variableIcon').style.display = 'none';
			document.getElementById('descIcon').style.display = 'none';
			document.getElementById('createdIcon').style.display = 'none';
			document.getElementById('systemIconProg').style.display = 'none';
			document.getElementById('variableIconProg').style.display = 'none';
			document.getElementById('descIconProg').style.display = 'none';
			document.getElementById('updatedIconProg').style.display = 'none';
			document.getElementById('userIdIconProg').style.display = 'none';

			var strUnMapped = $("#unMappedTable tbody tr td").html();
			var strInProgTable = $("#inProgressTable tbody tr td").html();

			if ($.trim(strUnMapped) != 'No Data Found') {
				$('#unMappedTable').dataTable({
					"bPaginate" : false,
					"bFilter" : false,
					"bSearchable" : false,
					"bInfo" : false,
					"bSort" : true,
					"bStateSave" : false,
					"bJQueryUI" : false,
					"bProcessing" : false,
					"aaSorting" : [],
					"fnRowCallback" : function(nRow, aData, iDisplayIndex) {

						if (iDisplayIndex % 2 == 0) {
							$(nRow).removeClass('alternate');
							$(nRow).addClass('white-bg');
						} else {
							$(nRow).removeClass('white-bg');
							$(nRow).addClass('alternate');
						}
						return nRow;
					},
					"bAutoWidth" : false
				});

			}

			oTable = $('#inProgressTable').dataTable({
				"bPaginate" : false,
				"bFilter" : false,
				"bSearchable" : false,
				"bInfo" : false,
				"bSort" : true,
				"bStateSave" : false,
				"bJQueryUI" : false,
				"bProcessing" : false,
				"aaSorting" : [],
				"asStripClasses" : [ 'white-bg', 'alternate' ],
				"oLanguage" : {
					sEmptyTable : 'No Data Found'
				},
				"fnRowCallback" : function(nRow, aData, iDisplayIndex) {
					var i = aData[0];
					var finalized = i.substring(0, 9);

					if (finalized == "Finalized") {
						aData[0] = i.substring(9);
						$('td:eq(0)', nRow).html(i.substring(9));
						$(nRow).addClass('finalized');
					}
					return nRow;
				},
				"bAutoWidth" : false,
				"aoColumns" : [ {
					sWidth : '10px'
				}, {
					sWidth : '60px'
				}, {
					sWidth : '37px'
				}, {
					sWidth : '35px'
				}, {
					sWidth : '10px'
				}, {
					sWidth : '70px'
				} ]
			});

		});

		$(document)
				.ready(
						function() {
							//$("#variableId").autocomplete({ source: [{"id":"1","label":"India","value":"India"},{"id":"2","label":"China","value":"China"},{"id":"3","label":"USA","value":"USA"},{"id":"4","label":"United Kingdom","value":"United Kingdom"}]});
							//Scrollbar not implemented
							$('#selectedVariableIdFrmLocate')
									.blur(
											function() {
												$(
														'#selectedVariableIdFrmLocate')
														.val(
																$(
																		'#selectedVariableIdFrmLocate')
																		.val()
																		.toUpperCase());
											});
							$("#selectedVariableIdFrmLocate")
									.autocomplete(
											{
												source : function(request,
														response) {

													$
															.ajax({
																url : "${context}/ajaxvariablelist.ajax",
																dataType : "json",
																type : "POST",
																data : "key="
																		+ encodeURIComponent(request.term)
																		+ "&name=all",
																success : function(
																		data) {
																	cache:
																			false,
																			response(data.rows);
																}
															});
												}
											})
						});

		var list = new Array();
		$(document)
				.ready(
						function() {
							//$("#variableId").autocomplete({ source: [{"id":"1","label":"India","value":"India"},{"id":"2","label":"China","value":"China"},{"id":"3","label":"USA","value":"USA"},{"id":"4","label":"United Kingdom","value":"United Kingdom"}]});
							//Scrollbar not implemented
							$('#variableId')
									.blur(
											function() {
												if ($(this).val() == null
														|| $
																.trim($(this)
																		.val()).length < 1) {
													$("#variableIdLabel").text(
															'');
												}
												$('#variableId').val(
														$('#variableId').val()
																.toUpperCase());
											});
							$("#variableId")
									.autocomplete(
											{
												select : function(event, ui) {
													$("#variableIdLabel")
															.text(ui.item.id)
															.removeClass(
																	'invalid_hippacode_value');
												},

												source : function(request,
														response) {
													$
															.ajax({
																url : "${context}/ajaxvariablelist.ajax",
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
																"variableIdLabel",
																inValidVariable);
													}
												}

											})
							sniffer();
						});

		// Method to open create page on click of 'Create Button'
		function openCreatePage() {
			var variableID = $("#variableId").val();
			if (variableID == null || variableID == "") {
				addErrorToNotificationTray('Variable ID is mandatory to create mapping');
				openTrayNotification();
			} else {
              	 document.forms['createform'].submit();
			}
		}
		// Method to open create page on click of 'Create (+) icon'
		function openCreateFromUnmappedSection(variableId) {
			$('#variableIdHidden').val(variableId);
            document.forms['unmappedVarForm'].submit();
		}
		// Method to open create page on click of 'Locate'
		function openLocateResultsDialog(currentPageNo, page) {

			var variableId = $("#selectedVariableIdFrmLocate").val();
			var variableDesc = $("#selectedVariableDescFrmLocate").val();
			var currentPage = currentPageNo;
			var fromWhichSection = page;
			if (document.locateResultsForm.isUnMapped.checked) {
				var isUnmapped = document.locateResultsForm.isUnMapped.value;
			}
			if (document.locateResultsForm.isMapped.checked) {
				var isMapped = document.locateResultsForm.isMapped.value;
			}
			if (document.locateResultsForm.isNotApplicable.checked) {
				var isNotApplicable = document.locateResultsForm.isNotApplicable.value;
			}

			if ((variableId == null || variableId == "")
					&& (variableDesc == null || variableDesc == "")) {
				addErrorToNotificationTray('Either Variable ID or description is required to locate');
				openTrayNotification();
			} else {
				$
						.ajax({
							url : "${context}/locateresults/locateRequest.ajax",
							dataType : "html",
							type : "POST",
							data : "variableId="
									+ encodeURIComponent(variableId)
									+ "&variableDesc=" + escape(variableDesc)
									+ "&mappedStatus=" + isMapped
									+ "&unMappedStatus=" + isUnmapped
									+ "&notAppStatus=" + isNotApplicable
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
														$('.ui-dialog-titlebar')
																.append(
																		"<a href='#' onclick='generateReport();'><img src='${imageDir}/print.gif' class='printIt' id='printIcon' style='height:15px; position:relative; left:75%;' alt='Print' title='Print'></a>");
													},
													close : function() {
														$('.printIt').remove();
													}
												});
								$("#viewLoacteDialog").dialog();
							}
						});
			}
		}

		// Method to open pagination results
		function openPagntnResultsDialog(currentPageNo, page) {

			var variableId = $("#selectedVariableIdFrmLocate").val();
			var variableDesc = $("#selectedVariableDescFrmLocate").val();
			var currentPage = currentPageNo;
			var fromWhichSection = page;
			if (document.locateResultsForm.isUnMapped.checked) {
				var isUnmapped = document.locateResultsForm.isUnMapped.value;
			}
			if (document.locateResultsForm.isMapped.checked) {
				var isMapped = document.locateResultsForm.isMapped.value;
			}
			if (document.locateResultsForm.isNotApplicable.checked) {
				var isNotApplicable = document.locateResultsForm.isNotApplicable.value;
			}

			if ((variableId == null || variableId == "")
					&& (variableDesc == null || variableDesc == "")) {
				addErrorToNotificationTray('Either Variable ID or description is required to perform a search');
				openTrayNotification();
			} else {
				$.ajax({
					url : "${context}/locateresults/locateRequest.ajax",
					dataType : "html",
					type : "POST",
					data : "variableId=" + encodeURIComponent(variableId)
							+ "&variableDesc=" + escape(variableDesc)
							+ "&mappedStatus=" + isMapped + "&unMappedStatus="
							+ isUnmapped + "&notAppStatus=" + isNotApplicable
							+ "&currentPage=" + currentPage + "&section="
							+ fromWhichSection,
					success : function(data) {
						$("#viewLoacteDialog").html(data);
					}
				});
			}
		}

		function openViewMappingDialog(variableId) {

			$.ajax({
				url : "${context}/viewmappingpage/viewUnmappedVariable.ajax",
				dataType : "html",
				type : "POST",
				data : "variableId=" + encodeURIComponent(variableId),
				success : function(data) {
					$("#viewMappingDialog").html(data);
					$("#viewMappingDialog").dialog({
						height : 'auto',
						width : '890px',
						show : 'slide',
						modal : true,
						resizable : false,
						title : 'View Mapping'

					});
					$("#viewMappingDialog").dialog();
				}
			});
		}

		function openViewMappingInProgressDialog(variableId) {

			$.ajax({
				url : "${context}/viewmappingpage/viewMapping.ajax",
				dataType : "html",
				type : "POST",
				data : "variableId=" + encodeURIComponent(variableId),
				success : function(data) {
					$("#viewMappingDialog").html(data);
					$("#viewMappingDialog").dialog({
						height : 'auto',
						width : '910px',
						show : 'slide',
						modal : true,
						resizable : false,
						title : 'View Mapping'

					});
					$("#viewMappingDialog").dialog();
				}
			});
		}
		function editMapping(variableId) {
			$("#selectedvariableForEditId").val(variableId);
			document.forms['editForm'].action = "${context}/editmapping/editMapping.html";
			document.forms["editForm"].submit();
		}
		function unlockMapping(variableId, lockedUserId) {
			$("#selectedvariableForEditId").val(variableId);
			var action = "${context}/editmapping/unlockMapping.html";
			confirmationDialogUnlockMapping(action, 'editForm', lockedUserId,
					variableId);
		}
		function openUserCommentsApproveDialog(variableId) {
			if(isEBSegValIcon(variableId)){
			$("#approvestateflowvariableId").val(variableId);
			$("#userCommentsApproveDialog table#approveUserCommentsTable1")
					.css("border-top", "1px solid black");
			$("#userCommentsApproveDialog").dialog({
				height : 'auto',
				width : '450px',
				resizable : false,
				show : 'slide',
				title : variableId + ' - Approve',
				modal : true
			});
			$("#userCommentsApproveDialog").dialog();
			}
			if(diaCloseIcon == true){
			$("#userCommentsApproveDialog").dialog("close");
			}
		}

		

		function openUserCommentsSend2TestDialog(variableId) {
			if(isEBSegValIcon(variableId)){
			$("#send2teststateflowvariableId").val(variableId);
			$("#userCommentsSent2TestDialog table#sendToTestUserCommentsTable1")
					.css("border-top", "1px solid black");
			$("#userCommentsSent2TestDialog").dialog({
				height : 'auto',
				width : '450px',
				resizable : false,
				show : 'slide',
				title : variableId + ' - Send to test',
				modal : true
			});
			$("#userCommentsSent2TestDialog").dialog();
			}
			if(diaCloseIcon == true){
			$("#userCommentsSent2TestDialog").dialog("close");
			}
		}
		
		function isEBSegValIcon(variableId){
		var viewEBSegValues = viewEBSegValIcon(variableId);
		if(!viewEBSegValues)
		var flag = ebMapAssocIcon(variableId);
		return (chkEBSegHSD02LimValIcon(variableId) && flag);
	}
	
	function viewEBSegValIcon(variableId){
	    var ebflag = false;
		$.ajax({
		url: "${context}/ajaxautocompletelistcreatepage.ajax",
		async: false,//sorry couldn't see any option. i would have a function call in success but u guess...
		dataType: "json",
		type:"POST",
		data: "key="+variableId+ "&name=VARIABLEICON",
		success: function(resp){
			if(resp.isEBSegmentPresent){
				ebflag = false;
				return false;
			}else{
				return true;
				ebflag = true;
			}
		}
	});
	return ebflag;
		/* return  ($.trim("${currentMapping.eb01.hippaCodeSelectedValues[0].value}") == "") && 
				($.trim("${currentMapping.eb02.hippaCodeSelectedValues[0].value}") == "") &&
				($.trim("${currentMapping.eb06.hippaCodeSelectedValues[0].value}") == "") && 
				($.trim("${currentMapping.eb09.hippaCodeSelectedValues[0].value}") == "") ; */
	
	}
	function ebMapAssocIcon(variableId){
	var varId = variableId;
	var ebmapFlag = true;
	$.ajax({
		url: "${context}/ajaxautocompletelistcreatepage.ajax",
		async: false,//sorry couldn't see any option. i would have a function call in success but u guess...
		dataType: "json",
		type:"POST",
		data: "key="+varId+ "&name=VARIABLEID",
		success: function(resp){
			if(resp.ebMappingAssocDetails != "[]"){
				var errMsg = "EB Mapping already Associated with "+ resp.ebMappingAssocDetails;
					addErrorToNotificationTray(errMsg);
					openTrayNotification();
					$('#userCommentsSent2TestDialog').dialog("destroy");
					$('#userCommentsApproveDialog').dialog("destroy");
					diaCloseIcon = true;
					ebmapFlag = false;
					return ebmapFlag;
			}
		}
	});
	return ebmapFlag;
	}
	function chkEBSegHSD02LimValIcon(variableId){
	var list =new Array();
	var issueVar = new Array();
	/* for(var i =1;i<7;i++){ 
		if($.trim($('#hsd02-0'+i).text()) != "")
		list.push($.trim($('#hsd02-0'+i).text()));
	} */
	
	$.ajax({
		url: "${context}/ajaxautocompletelistcreatepage.ajax",
		async: false,//sorry couldn't see any option. i would have a function call in success but u guess...
		dataType: "json",
		type:"POST",
		data: "key="+variableId+ "&name=VARIABLEIDICON",
		success: function(resp){
			if(resp.ebMappingAssocDetails != "[]"){
				 list = resp.ebMappingAssocDetails.split(',');
			}
		}
	});
	
	issueVar = callAjaxDataIcon(list);
	if(issueVar!=""){
		var errMsg = "EB Segment present in ["+issueVar.toString()+"]";
		addErrorToNotificationTray(errMsg);
		openTrayNotification();
		exit();//I know this is going to abruptly terminate the script and there is no function defined exit() in java script. I'm not an idiot.
		return false;
	}
	
	return true;
	}
	
	function callAjaxDataIcon(list){
	var issueVar = new Array();
	for(var i =0;i<list.length;i++){
		var vatT1 = list[i].replace("[", "");
		var vatT2 = vatT1.replace("]", "");
		var varId = vatT2;
		$.ajax({
			url: "${context}/ajaxautocompletelistcreatepage.ajax",
			async: false,//sorry couldn't see any option. i would have a function call in success but u guess...
			dataType: "json",
			type:"POST",
			data: "key="+varId+ "&name=VARIABLE",
			success: function(resp){
				if(resp.isEBSegmentPresent){
					issueVar.push(resp.variableId);
				}
			}
		});
	}
	
	return issueVar;
	
	}
		
		function openUserCommentsNotApplicableDialog(variableId, variableDesc) {

			$("#notApplicablestateflowvariableId").val(variableId);
			$("#notApplicablestateflowvariableDesc").val($.trim(variableDesc));
			$("#notApplicableMappingComments").val('');
			$("#userCommentsNotApplicableDialog table#NACommentsTable1").css(
					"border-top", "1px solid black");
			$("#userCommentsNotApplicableDialog").dialog({
				height : 'auto',
				width : '450px',
				resizable : false,
				show : 'slide',
				title : variableId + ' - Not Applicable',
				modal : true
			});
			$("#userCommentsNotApplicableDialog").dialog();
		}
		function saveUserCommentsForApprove() {
			if (!imposeMaxLength('approvedMappingComments', 250,
					'user comments')) {
				return false;
			}
			document.forms['userCommentsApproveForm'].submit();
		}

		function saveUserCommentsForSent2Test() {
			if (!imposeMaxLength('send2TestMappingComments', 250,
					'user comments')) {
				return false;
			}
			document.forms['userCommentsSent2TestForm'].submit();
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
								+ search+"&userComment="+userComment,
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

		function saveUserCommentsForNotApplicable() {
			if (!imposeMaxLength('notApplicableMappingComments', 250,
					'user comments')) {
				return false;
			}
			// LO changes to avoid multiple insert of variables in db. September 2014 Release
			$("a").removeAttr("onclick");
			document.forms['userCommentsNotApplicableForm'].submit();
		}
		var variableIdForViewPage = "";
		var userCommentsFrom = "";

		function openUserCommentsDialog(variableId, actionFrom) {
			resetMessages();
			if(isEBSegVal()){
			variableIdForViewPage = encodeURIComponent(variableId);
			userCommentsFrom = actionFrom;
			$("#userMappingComments").val('');
			$("#userCommentsDialog table#userCommentsTable1").css("border-top",
					"1px solid black");
			if (userCommentsFrom == 'Send2TestFromLocate') {
				$("#userCommentsDialog").dialog({
					height : 'auto',
					resizable : false,
					width : '450px',
					show : 'slide',
					title : variableId + ' - Send to test',
					modal : true
				});
			} else if (userCommentsFrom == 'ApproveFromLocate') {
				$("#userCommentsDialog").dialog({
					height : 'auto',
					resizable : false,
					width : '450px',
					show : 'slide',
					title : variableId + ' - Approve',
					modal : true
				});
			} else if (userCommentsFrom == 'Send2TestFromView') {
				$("#userCommentsDialog").dialog({
					height : 'auto',
					resizable : false,
					width : '450px',
					show : 'slide',
					title : 'User comments - Send to test',
					modal : true
				});
			} else if (userCommentsFrom == 'ApproveFromView') {
				$("#userCommentsDialog").dialog({
					height : 'auto',
					resizable : false,
					width : '450px',
					show : 'slide',
					title : 'User comments - Approve',
					modal : true
				});
			}
			}
			if(diaClose == true){
			$("#userCommentsDialog").dialog("close");
			}
		}
		//Method to invoke the save the user comments n id from comments dialog box
		function saveUserComments() {
			$('#userCommentsDialog').dialog("close");
			var userComments = $("#userMappingComments").val();
			if (userCommentsFrom == 'Send2TestFromLocate') {
				$.ajax({
					url : "${context}/locatepagestateflow/sendToTest.ajax",
					dataType : "json",
					type : "POST",
					data : "variableId=" + variableIdForViewPage
							+ "&userComments=" + userComments,
					success : function(data) {
						cache: false, handleSendToTestFromLocate(data);
					}
				});
			} else if (userCommentsFrom == 'ApproveFromLocate') {
				$.ajax({
					url : "${context}/locatepagestateflow/approve.ajax",
					dataType : "json",
					type : "POST",
					data : "variableId=" + variableIdForViewPage
							+ "&userComments=" + userComments,
					success : function(data) {
						cache: false, handleApproveFromLocate(data);
					}
				});
			} else if (userCommentsFrom == 'Send2TestFromView') {
				$.ajax({
					url : "${context}/stateflow/sendToTest.ajax",
					dataType : "json",
					type : "POST",
					data : "variableId=" + variableIdForViewPage
							+ "&userComments=" + userComments,
					success : function(data) {
						cache: false, handleSendToTestFromView(data);
					}
				});
			} else if (userCommentsFrom == 'ApproveFromView') {
				$.ajax({
					url : "${context}/stateflow/approve.ajax",
					dataType : "json",
					type : "POST",
					data : "variableId=" + variableIdForViewPage
							+ "&userComments=" + userComments,
					success : function(data) {
						cache: false, handleApproveFromView(data);
					}
				});
			}
		}
		function addMessages(data) {

			//alert("coming inside AddMessages");
			var infoMessages = data.info_messages;
			var errorMessages = data.error_messages;
			var warningMessages = data.warning_messages;
			//alert(infoMessages);
			//alert("coming here");

			if (typeof (infoMessages) != 'undefined' && infoMessages.length > 0) {
				for (i = 0; i < infoMessages.length; i++) {
					//alert(infoMessages[i]);
					addInfoToNotificationTray(infoMessages[i]);
				}
			}
			if (typeof (errorMessages) != 'undefined'
					&& errorMessages.length > 0) {
				for (i = 0; i < errorMessages.length; i++) {
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
		function handleSendToTestFromView(data) {
			addMessages(data);
			$('#statusImageContainer').html(
					'<img src="${imageDir}/totest.gif" />');
			$('#sendToTestButton').html("");
			$('#sendToTestButtonSep').html("");
			if (isElementDefined($('#approveButton'))) {
				$('#approveButton').html("");
			}
			if (isElementDefined($('#copyToSep'))) {
				$('#copyToSep').html("");
			}
			var variableId = data.variableId;
			handleIconsWhileSendToTest(variableId);
			var auditTrail = data.auditTrail;
			var auditRow = "<tr id=''>" + "<td width='90px'>"
					+ data.auditTrail.createdAuditDate + "</td>"
					+ "<td width='93px'>" + data.auditTrail.createdUser
					+ "</td>" + "<td width='180px'>"
					+ data.auditTrail.systemComments + "</td>"
					+ "<td width='250px'>" + data.auditTrail.userComments
					+ "</td>" + "</tr>";
			$('#auditTrailsTable tr:nth-child(1)').append(auditRow);
			$('#auditTrailsTable tr:nth-child(even)').removeClass('white-bg');
			$('#auditTrailsTable tr:nth-child(odd)').removeClass('alternate');
			$('#auditTrailsTable tr:nth-child(even)').addClass('alternate');
			$('#auditTrailsTable tr:nth-child(odd)').addClass('white-bg');
			$('#auditTrailsTable tr:first-child').removeClass('white-bg')
					.addClass('createEditTableShade');

		}
		function handleApproveFromView(data) {
			addMessages(data);
			$('#statusImageContainer').html(
					'<img src="${imageDir}/toproduction.gif" />');
			$('#approveButton').html("");
			if (isElementDefined($('#copyToSep'))) {
				$('#copyToSep').html("");
			}
			if (isElementDefined($('#sendToTestButton'))) {
				$('#sendToTestButton').html("");
				$('#sendToTestButtonSep').html("");
			}
			var variableId = data.variableId;
			handleIconsWhileSendToTest(variableId);
			handleIconsWhileApprove(variableId);
			var auditRow = "<tr id=''>" + "<td width='90px'>"
					+ data.auditTrail.createdAuditDate + "</td>"
					+ "<td width='93px'>" + data.auditTrail.createdUser
					+ "</td>" + "<td width='180px'>"
					+ data.auditTrail.systemComments + "</td>"
					+ "<td width='250px'>" + data.auditTrail.userComments
					+ "</td>" + "</tr>";
			$('#auditTrailsTable tr:nth-child(1)').append(auditRow);
			$('#auditTrailsTable tr:nth-child(even)').removeClass('white-bg');
			$('#auditTrailsTable tr:nth-child(odd)').removeClass('alternate');
			$('#auditTrailsTable tr:nth-child(even)').addClass('alternate');
			$('#auditTrailsTable tr:nth-child(odd)').addClass('white-bg');
			$('#auditTrailsTable tr:first-child').removeClass('white-bg')
					.addClass('createEditTableShade');
		}
		function handleSendToTestFromLocate(data) {
			var variableId = data.variableId;
			handleIconsWhileSendToTest(variableId);
			addMessages(data);
		}
		function handleIconsWhileSendToTest(variableId) {
			if (isElementDefined($('#' + variableId + '_Test'))) {
				$('#' + variableId + '_Test').remove();
			}
			if (isElementDefined($('#' + 'locate_SendToTestIcon_' + variableId))) {
				$('#' + 'locate_SendToTestIcon_' + variableId).remove();
			}
			if (isElementDefined($('#' + 'status_' + variableId))) {
				$('#' + 'status_' + variableId).html("SCHEDULED_TO_TEST");
			}
			// Remove approve as approve will be available only after object transfered
			if (isElementDefined($('#' + 'locate_approveIcon_' + variableId))) {
				$('#' + 'locate_approveIcon_' + variableId).remove();
			}
			if (isElementDefined($('#' + variableId + '_Approve'))) {
				$('#' + variableId + '_Approve').remove();
			}

		}

		function handleIconsWhileApprove(variableId) {
			if (isElementDefined($('#' + variableId + '_Approve'))) {
				$('#' + variableId + '_Approve').remove();
			}
			$('#' + 'locate_approveIcon_' + variableId).remove();
			$('#' + 'status_' + variableId).html("SCHEDULED_TO_TEST");
			if (isElementDefined($('#' + variableId + '_Test'))) {
				$('#' + variableId + '_Test').remove();
			}
			if (isElementDefined($('#' + 'locate_SendToTestIcon_' + variableId))) {
				$('#' + 'locate_SendToTestIcon_' + variableId).remove();
			}
		}

		function handleApproveFromLocate(data) {
			var variableId = data.variableId;
			handleIconsWhileApprove(variableId);
			if ("SCHEDULED_TO_PRODUCTION" == data.variableMappingStatus) {
				$('#' + 'status_' + variableId).html("SCHEDULED_TO_PRODUCTION");
			}
			addMessages(data);
		}

		function imposeMaxLength(elementId, MaxLen, element) {
			var valueOfTextArea = $.trim($('#' + elementId).val());
			$('#' + elementId).val(valueOfTextArea);
			if (valueOfTextArea != null && valueOfTextArea.length > MaxLen) {
				var message = "Text length cannot be greater than " + MaxLen
						+ " for " + element;
				addErrorToNotificationTray(message);
				openTrayNotification();
				return false;
			}
			return true;
		}

		function toggleNumRecords() {

			var appendUser = ($('#toggled').attr('src') == "${imageDir}/scheduled_approval.gif") ? "false"
					: "true";

			var src = ($('#numRecords').attr('src') == "${imageDir}/fullcopy.gif") ? "${imageDir}/copy_landing.gif"
					: "${imageDir}/fullcopy.gif";

			$('#numRecords').attr('src', src);

			var getAll = ($('#numRecords').attr('src') == "${imageDir}/fullcopy.gif") ? "true"
					: "false";

			if (getAll == "true") {
				$('#numRecords').attr('title', 'Show First 50 Mappings');
				$('#numRecords').attr('alt', 'Show First 50 Mappings');
			} else {
				$('#numRecords').attr('title', 'Show All Mappings');
				$('#numRecords').attr('alt', 'Show All Mappings');
			}

			ajaxInprogress(appendUser, getAll);

		}
		function toggleUserIcon() {
			var getAll = ($('#numRecords').attr('src') == "${imageDir}/fullcopy.gif") ? "true"
					: "false";

			var src = ($('#toggled').attr('src') == "${imageDir}/icon-by-customer.gif") ? "${imageDir}/scheduled_approval.gif"
					: "${imageDir}/icon-by-customer.gif";

			$('#toggled').attr('src', src);

			var appendUser = ($('#toggled').attr('src') == "${imageDir}/scheduled_approval.gif") ? "false"
					: "true";
			if (appendUser == "true") {
				$('#toggled').attr('title', 'Show All Users');
				$('#toggled').attr('alt', 'Show All Users');
			} else {
				$('#toggled').attr('title', 'Show Current User');
				$('#toggled').attr('alt', 'Show Current User');
			}
			ajaxInprogress(appendUser, getAll);

		}
		function ajaxInprogress(appendUser, getAll) {
			var authorizedToapprove = $('#authorizedToapprove').val();
			var authorizedTounlock = $('#authorizedTounlock').val();
			var authorizedToAuditUnlock = $('#authorizedToAuditUnlock').val();
			var authorizedToAuditLock = $('#authorizedToAuditLock').val();
			var auditLockEditStatus = $('#auditLockEditStatus').val();
			$.ajax({
				url : "${context}/stateflow/multipleViewInProgress.ajax",
				dataType : "json",
				type : "POST",
				data : "appendUser=" + appendUser + "&getAll=" + getAll
						+ "&authorizedToapprove=" + authorizedToapprove
						+ "&authorizedTounlock=" + authorizedTounlock
						+ "&authorizedToAuditLock=" + authorizedToAuditLock
						+ "&authorizedToAuditUnlock=" + authorizedToAuditUnlock
						+ "&auditLockEditStatus=" + auditLockEditStatus,
				cache : false,
				success : function(data) {
					oTable.fnClearTable();
					oTable.fnAddData(data.aaData);
				}
			});
		}
		function printInprogress() {
			var getAll = ($('#numRecords').attr('src') == "${imageDir}/fullcopy.gif") ? "true"
					: "false";

			var appendUser = ($('#toggled').attr('src') == "${imageDir}/scheduled_approval.gif") ? "false"
					: "true";

			$("#appendUserPrint").val(appendUser);
			$("#getAllPrint").val(getAll);
			document.forms['editForm'].action = "${context}/stateflow/generateExcel.html";
			document.forms['editForm'].submit();
		}
		function unlockMappingFromLocate(variableId, action, lockUserId,
				auditLockStatus, systemName, variableDesc) {
			resetMessages();
			var warningMsgForUnlocking = "The mapping is locked by the user "
					+ lockUserId + " . Do you want to unlock?";
			$("#confirmationDivUnlock").html(warningMsgForUnlocking);
			$("#confirmationDivUnlock").addClass("UnmappedVariables");
			var authorizedToApprove = $('#authorizedToapprove').val();
			var authorizedToAuditUnlock = $('#authorizedToAuditUnlock').val();
			var authorizedToAuditLock = $('#authorizedToAuditLock').val();
			$("#confirmationDivUnlock")
					.dialog(
							{
								autoOpen : false,
								title : 'Confirm',
								resizable : false,
								height : 130,
								modal : true,
								buttons : {
									Cancel : function() {
										$(this).dialog('close');
									},
									Ok : function() {
										$(this).dialog('close');
										if (action == 'unlockVariableFromLocate') {
											$
													.ajax({
														url : "${context}/stateflow/unlockVariableMapping.ajax",
														dataType : "json",
														type : "POST",
														data : "variableId="
																+ encodeURIComponent(variableId)
																+ "&authorizedToApprove="
																+ authorizedToApprove
																+ "&auditLockStatus="
																+ auditLockStatus
																+ "&systemName="
																+ systemName
																+ "&variableDesc="
																+ variableDesc
																+ "&authorizedToAuditLock="
																+ authorizedToAuditLock
																+ "&authorizedToAuditUnlock="
																+ authorizedToAuditUnlock,
														success : function(data) {
															cache:
																	false,
																	handleUnlockFromLocate(data);
														}
													});
										}
										if (action == 'unlockVariableFromLanding') {
											$
													.ajax({
														url : "${context}/stateflow/unlockVariableMapping.ajax",
														dataType : "json",
														type : "POST",
														data : "variableId="
																+ escape(variableId)
																+ "&authorizedToApprove="
																+ authorizedToApprove
																+ "&auditLockStatus="
																+ auditLockStatus
																+ "&systemName="
																+ systemName
																+ "&variableDesc="
																+ variableDesc
																+ "&authorizedToAuditLock="
																+ authorizedToAuditLock
																+ "&authorizedToAuditUnlock="
																+ authorizedToAuditUnlock,
														success : function(data) {
															cache:
																	false,
																	handleIconsWhileUnlockFromLanding(data);
														}
													});
										}
									}
								}
							});
			$("#confirmationDivUnlock").dialog('open');
		}
		function handleIconsWhileUnlockFromLanding(data) {
			addMessages(data);
			if ($("[id=auditLockEditStatus]").val() == 'false') {
				if (data.auditLockStatus == 'N') {
					var editComp = '<a href = "#" id="'
							+ data.variableId
							+ '_Edit" onclick="editMapping(\''
							+ data.variableId
							+ '\');"><img src="${imageDir}/edit_icon.gif" alt="Edit" title="Edit" style="height:15px;"/></a><span id="emptySpace">&#160;</span>';
					$('#' + data.variableId + '_Lock').replaceWith(editComp);
				} else {
					var hideEditIcon = '<span id="'+data.variableId+'_Space" style="display:none;"></span>';
					$('#' + data.variableId + '_Lock')
							.replaceWith(hideEditIcon);
				}
			} else {
				var editComp = '<a href = "#" id="'
						+ data.variableId
						+ '_Edit" onclick="editMapping(\''
						+ data.variableId
						+ '\');"><img src="${imageDir}/edit_icon.gif" alt="Edit" title="Edit" style="height:15px;"/></a><span id="emptySpace">&#160;</span>';
				$('#' + data.variableId + '_Lock').replaceWith(editComp);
			}
			var sendtotestComp = '<a href = "#" id="'
					+ data.variableId
					+ '_Test" onclick="openUserCommentsSend2TestDialog(\''
					+ data.variableId
					+ '\');"><img src="${imageDir}/test_icon.gif" alt="Send to Test" title="Send to Test" style="height:15px;"/></a>';
			$('#' + data.variableId + '_Edit').after(sendtotestComp);
			$('#' + data.variableId + '_Test').append("&#160;&#160;");

			if (data.authorizedToApprove) {
				var approveComp = '<a href = "#" id="'
						+ data.variableId
						+ '_Approve" onclick="openUserCommentsApproveDialog(\''
						+ data.variableId
						+ '\');"><img src="${imageDir}/approve_icon.gif" alt="Approve" title="Approve" style="height:15px;"/></a>';
				$('#' + data.variableId + '_Test').after(approveComp);
			}
			if (data.auditLockStatus == 'N' && data.authorizedToAuditUnlock) {
				var unLockIcon = '<a href = "#" id="AuditUnLock_'
						+ data.variableId
						+ '" onclick="openUserCommentsLockDialog(\''
						+ data.variableId
						+ '\',\''
						+ data.systemName
						+ '\',\''
						+ data.userName
						+ '\',\''
						+ data.variableDesc
						+ '\',\'Lock\');"><img src="${imageDir}/AuditLockIndicatorNew.jpg" alt="Lock" title="Lock" style="height:15px;width=12px;"/></a>';
				$("[id=AuditUnLock_" + data.variableId + "]").replaceWith(
						unLockIcon);
			}
			if (data.auditLockStatus == 'Y' && data.authorizedToAuditLock) {
				var lockIcon = '<a href = "#" id="AuditLock_'
						+ data.variableId
						+ '" onclick="openUserCommentsLockDialog(\''
						+ data.variableId
						+ '\',\''
						+ data.systemName
						+ '\',\''
						+ data.userName
						+ '\',\''
						+ data.variableDesc
						+ '\',\'Unlock\');"><img src="${imageDir}/auditUnLockIndicator.GIF" alt="Unlock" title="Unlock" style="height:15px;width=12px;"/></a>';
				$("[id=AuditLock_" + data.variableId + "]").replaceWith(
						lockIcon);
			}
		}
		function handleUnlockFromLocate(data) {
			addMessages(data);
			if (data.variableId != null) {
				handleIconsWhileUnlock(data.variableId,
						data.mapping.user.lastUpdatedUserName,
						data.authorizedToApprove, 'variable',
						data.auditLockStatus, data.userName, data.variableDesc,
						data.systemName, data.authorizedToAuditUnlock,
						data.authorizedToAuditLock);
			}
		}
		function handleIconsWhileUnlock(variableId, userId,
				permissionToApprove, action, auditStatus, userName,
				variableDesc, systemName, authorizedToAuditUnlock,
				authorizedToAuditLock) {

			var sendToTest = "Send2TestFromLocate";
			var approve = "ApproveFromLocate";

			if ($("[id=auditLockEditStatus]").val() == 'false') {
				if (auditStatus == 'N') {
					var editComp = '<a href = "#" id="locate_EditIcon_'
							+ variableId
							+ '" onclick="editMapping(\''
							+ variableId
							+ '\');"><img src="${imageDir}/edit_icon.gif" alt="Edit" title="Edit" style="height:15px;" /></a><span id="emptySpace">&#160;</span>';
					$('#locate_UnlockIcon_' + variableId).replaceWith(editComp);
				} else {
					var hideEditIcon = '<span id="'+variableId+'_Space" style="display:none;"></span>';
					$('#' + variableId + '_Lock').replaceWith(hideEditIcon);
				}
			} else {
				var editComp = '<a href = "#" id="locate_EditIcon_'
						+ variableId
						+ '" onclick="editMapping(\''
						+ variableId
						+ '\');"><img src="${imageDir}/edit_icon.gif" alt="Edit" title="Edit" style="height:15px;" /></a><span id="emptySpace">&#160;</span>';
				$('#locate_UnlockIcon_' + variableId).replaceWith(editComp);
			}

			var sendtotestComp = '<a href = "#" id="locate_SendToTestIcon_'
					+ variableId
					+ '" onclick="openUserCommentsDialog(\''
					+ variableId
					+ '\',\''
					+ sendToTest
					+ '\');"><img src="${imageDir}/test_icon.gif" alt="Send to Test" title="Send to Test" style="height:15px;"/></a>';
			$('#locate_EditIcon_' + variableId).after(sendtotestComp);
			$('#locate_SendToTestIcon_' + variableId).append("&#160;&#160;");

			if (permissionToApprove) {
				var approveComp = '<a href = "#" id="locate_approveIcon_'
						+ variableId
						+ '" onclick="openUserCommentsDialog(\''
						+ variableId
						+ '\',\''
						+ approve
						+ '\');"><img src="${imageDir}/approve_icon.gif" alt="Approve" title="Approve" style="height:15px;"/></a>';
				$('#locate_SendToTestIcon_' + variableId).after(approveComp);
			}

			if (auditStatus == 'N' && authorizedToAuditUnlock) {
				var unLockIcon = '<a href = "#" id="AuditUnLockLocate_'
						+ variableId
						+ '" onclick="openUserCommentsLockDialog(\''
						+ variableId
						+ '\',\''
						+ systemName
						+ '\',\''
						+ userName
						+ '\',\''
						+ variableDesc
						+ '\');"><img src="${imageDir}/AuditLockIndicatorNew.jpg" alt="Lock" title="Lock" style="height:15px;width=12px;"/></a>';
				$("[id=AuditUnLockLocate_" + variableId + "]").replaceWith(
						unLockIcon);
			}
			if (auditStatus == 'Y' && authorizedToAuditLock) {
				var lockIcon = '<a href="#" id="AuditLockLocate_'
						+ variableId
						+ '" onclick ="openUserCommentsLockDialog(\''
						+ variableId
						+ '\',\''
						+ systemName
						+ '\',\''
						+ userName
						+ '\',\''
						+ variableDesc
						+ '\');"><img src="${imageDir}/auditUnLockIndicator.GIF" alt="Unlock" title="Unlock" style="height:15px;width:12px;"/></a>';
				$("[id=AuditLockLocate_" + variableId + "]").replaceWith(
						lockIcon);
			}
		}
		function generateReport() {
			var variableId = $("#selectedVariableIdFrmLocate").val();
			var variableDesc = $("#selectedVariableDescFrmLocate").val();
			if(document.locateResultsForm.isUnMapped.checked){
				var isUnmapped = document.locateResultsForm.isUnMapped.value;
			}
			if(document.locateResultsForm.isMapped.checked){
				var isMapped =document.locateResultsForm.isMapped.value;
			}
			if(document.locateResultsForm.isNotApplicable.checked){
				var isNotApplicable =document.locateResultsForm.isNotApplicable.value;
			}
			//var isUnmapped = $("#isUnMapped").val();
			//var isMapped = $("#isMapped").val();
			//var isNotApplicable = $("#isNotApplicable").val();
			document.forms['locateResultsForm'].action = "${context}/locateresults/generateExcelReport.ajax?variableId="
					+ escape(variableId)
					+ "&variableDesc="
					+ escape(variableDesc)
					+ "&mappedStatus="
					+ isMapped
					+ "&unMappedStatus="
					+ isUnmapped
					+ "&notAppStatus="
					+ isNotApplicable;
			document.forms['locateResultsForm'].submit();
		}
		function lockAuditLock(variableId, systemName, lockUserId,
				variableDesc, usercomment) {
			$.ajax({
				url : "${context}/stateflow/auditLockVariable.ajax",
				dataType : "json",
				type : "POST",
				data : "variableId=" + escape(variableId) + "&systemName="
						+ escape(systemName) + "&userName="
						+ escape(lockUserId) + "&variableDesc="
						+ escape(variableDesc) + "&userComment="
						+ escape(usercomment),
				success : function(data) {
					cache: false, displayUnLockIconForLanding(data);
				}
			});
		}
		function lockAuditLockFromLocate(variableId, systemName, lockUserId,
				variableDesc, usercomment) {
			$.ajax({
				url : "${context}/stateflow/auditLockVariable.ajax",
				dataType : "json",
				type : "POST",
				data : "variableId=" + escape(variableId) + "&systemName="
						+ escape(systemName) + "&userName="
						+ escape(lockUserId) + "&variableDesc="
						+ escape(variableDesc) + "&userComment="
						+ escape(usercomment),
				success : function(data) {
					cache: false, displayUnLockIconForLocate(data);
				}
			});
		}
		function displayUnLockIconForLanding(data) {
			var errorMessages = data.error_messages;
			if (typeof (errorMessages) != 'undefined'
					&& errorMessages.length > 0) {
				for (i = 0; i < errorMessages.length; i++) {
					addErrorToNotificationTray(errorMessages[i]);
				}
				openTrayNotification();
			} else {
				var unLockIcon = '<a href = "#" id="AuditUnLock_'
						+ data.variableId
						+ '" onclick="openUserCommentsLockDialog(\''
						+ data.variableId
						+ '\',\''
						+ data.systemName
						+ '\',\''
						+ data.userName
						+ '\',\''
						+ escape(data.variableDesc)
						+ '\',\'Lock\');"><img src="${imageDir}/AuditLockIndicatorNew.jpg" alt="Lock" title="Lock" style="height:15px;"/></a>';
				$("[id=AuditLock_" + data.variableId + "]").replaceWith(
						unLockIcon);
				$("[id=AuditLockImg_" + data.variableId + "]").hide();
				if ($("[id=auditLockEditStatus]").val() == 'false') {
					var editIcon = '<a href = "#" id="'
							+ data.variableId
							+ '_Edit" onclick="editMapping(\''
							+ data.variableId
							+ '\')";\'><img src="${imageDir}/edit_icon.gif" alt="Edit" title="Edit"  style="height:15px;"/></a><span id="emptySpace">&#160;</span>';
					$("[id=" + data.variableId + "_Space]").replaceWith(
							editIcon);
				}
				if (data.isLockedOrUnlocked == 'unlocked') {
					addErrorToNotificationTray('Mapping is not locked');
					openTrayNotification();
				}
			}
		}
		function displayUnLockIconForLocate(data) {
			var errorMessages = data.error_messages;
			if (typeof (errorMessages) != 'undefined'
					&& errorMessages.length > 0) {
				for (i = 0; i < errorMessages.length; i++) {
					addErrorToNotificationTray(errorMessages[i]);
				}
				openTrayNotification();
			} else {
				var unLockIcon = '<a href = "#" id="AuditUnLockLocate_'
						+ data.variableId
						+ '" onclick="openUserCommentsLockDialog(\''
						+ data.variableId
						+ '\',\''
						+ data.systemName
						+ '\',\''
						+ data.userName
						+ '\',\''
						+ data.variableDesc
						+ '\',\'LockLocate\');"><img src="${imageDir}/AuditLockIndicatorNew.jpg" alt="Lock" title="Lock" style="height:15px;width:12px;"/></a>';
				$("[id=AuditLockLocate_" + data.variableId + "]").replaceWith(
						unLockIcon);
				$("[id=AuditUnLockLocateImg_" + data.variableId + "]").hide();
				if ($("[id=auditLockEditStatus]").val() == 'false') {
					var editIconLocate = '<a href="#" id="locate_EditIcon_'
							+ data.variableId
							+ '" onclick=\'editMapping("'
							+ data.variableId
							+ '");\'><img src="${imageDir}/edit_icon.gif"  alt="Edit" title="Edit" /></a>';
					$("[id=" + data.variableId + "_LocateSpace]").replaceWith(
							editIconLocate);
					var markNAIconLocate = '<a href="#" id="locate_NA_'
							+ data.variableId
							+ '" onclick=\'editMapping("'
							+ data.variableId
							+ '");\'><img src="${imageDir}/Applicable.gif" alt="Mark as Applicable"  title="Mark as Applicable"/></a>';
					$("[id=" + data.variableId + "_NASpace]").replaceWith(
							markNAIconLocate);
				}

				if (isElementDefined($("[id=AuditUnLock_" + data.variableId
						+ "]"))) {
					var unLockIconForLanding = '<a href = "#" id="AuditUnLock_'
							+ data.variableId
							+ '" onclick="openUserCommentsLockDialog(\''
							+ data.variableId
							+ '\',\''
							+ data.systemName
							+ '\',\''
							+ data.userName
							+ '\',\''
							+ data.variableDesc
							+ '\',\'Lock\');"><img src="${imageDir}/AuditLockIndicatorNew.jpg" alt="Lock" title="Lock" style="height:15px;"/></a>';
					$("[id=AuditLock_" + data.variableId + "]").replaceWith(
							unLockIconForLanding);
					if ($("[id=auditLockEditStatus]").val() == 'false') {
						var editIcon = '<a href = "#" id="'
								+ data.variableId
								+ '_Edit" onclick="editMapping(\''
								+ data.variableId
								+ '\')";\'><img src="${imageDir}/edit_icon.gif" alt="Edit" title="Edit"  style="height:15px;"/></a><span id="emptySpace">&#160;</span>';
						$("[id=" + data.variableId + "_Space]").replaceWith(
								editIcon);
					}
				}

				if (isElementDefined($("[id=AuditLockImg_" + data.variableId
						+ "]"))) {
					$("[id=AuditLockImg_" + data.variableId + "]").hide();
				}

				if (data.isLockedOrUnlocked == 'unlocked') {
					addErrorToNotificationTray('Mapping is not locked');
					openTrayNotification();
				}
			}
		}

		function unLockAuditLock(variableId, systemName, lockUserId,
				variableDesc, usercomment) {
			$.ajax({
				url : "${context}/stateflow/auditUnLockVariable.ajax",
				dataType : "json",
				type : "POST",
				data : "variableId=" + escape(variableId) + "&systemName="
						+ escape(systemName) + "&userName="
						+ escape(lockUserId) + "&variableDesc="
						+ escape(variableDesc) + "&userComment="
						+ escape(usercomment),
				success : function(data) {
					cache: false, displayLockIconFromLanding(data);
				}
			});
		}
		function unLockAuditLockFromLocate(variableId, systemName, lockUserId,
				variableDesc, usercomment) {
			$.ajax({
				url : "${context}/stateflow/auditUnLockVariable.ajax",
				dataType : "json",
				type : "POST",
				data : "variableId=" + escape(variableId) + "&systemName="
						+ escape(systemName) + "&userName="
						+ escape(lockUserId) + "&variableDesc="
						+ escape(variableDesc) + "&userComment="
						+ escape(usercomment),
				success : function(data) {
					cache: false, displayLockIconFromLocate(data);
				}
			});
		}
		function displayLockIconFromLanding(data) {
			var errorMessages = data.error_messages;
			if (typeof (errorMessages) != 'undefined'
					&& errorMessages.length > 0) {
				for (i = 0; i < errorMessages.length; i++) {
					addErrorToNotificationTray(errorMessages[i]);
				}
				openTrayNotification();
			} else {
				var lockIcon = '<a href = "#" id="AuditLock_'
						+ data.variableId
						+ '" onclick="openUserCommentsLockDialog(\''
						+ data.variableId
						+ '\',\''
						+ data.systemName
						+ '\',\''
						+ data.userName
						+ '\',\''
						+ escape(data.variableDesc)
						+ '\',\'Unlock\');"><img src="${imageDir}/auditUnLockIndicator.GIF" alt="Unlock" title="Unlock" style="height:15px;"/></a>';
				$("[id=AuditUnLock_" + data.variableId + "]").replaceWith(
						lockIcon);
				var lockImgIcon = '<img src="${imageDir}/AuditLockIndicatorNew.jpg" id="AuditLockImg_'+data.variableId+'" alt="Lock" title="Lock" style="height:9px;width:9px"/>';
				$("[id=AuditLockImg_" + data.variableId + "]").replaceWith(
						lockImgIcon);
				$("[id=AuditLockImg_" + data.variableId + "]").show();
				if ($("[id=auditLockEditStatus]").val() == 'false') {
					var hideEditIcon = '<span id="'+data.variableId+'_Space" style="display:none;"></span>';
					$("[id=" + data.variableId + "_Edit]").replaceWith(
							hideEditIcon);
					$("[id=emptySpace]").remove();
				}
				$("[id=auditLockStatus]").val("Y");
				if (data.isLockedOrUnlocked == 'locked') {
					addErrorToNotificationTray('Mapping is already locked');
					openTrayNotification();
				}
			}
		}
		function displayLockIconFromLocate(data) {
			var errorMessages = data.error_messages;
			if (typeof (errorMessages) != 'undefined'
					&& errorMessages.length > 0) {
				for (i = 0; i < errorMessages.length; i++) {
					addErrorToNotificationTray(errorMessages[i]);
				}
				openTrayNotification();
			} else {
				var lockIcon = '<a href = "#" id="AuditLockLocate_'
						+ data.variableId
						+ '" onclick="openUserCommentsLockDialog (\''
						+ data.variableId
						+ '\',\''
						+ data.systemName
						+ '\',\''
						+ data.userName
						+ '\',\''
						+ escape(data.variableDesc)
						+ '\',\'UnlockLocate\');"><img src="${imageDir}/auditUnLockIndicator.GIF" alt="Unlock" title="Unlock" style="height:15px;width:12px;"/></a>';
				$("[id=AuditUnLockLocate_" + data.variableId + "]")
						.replaceWith(lockIcon);
				var lockImgIcon = '<img src="${imageDir}/AuditLockIndicatorNew.jpg" id="AuditUnLockLocateImg_'+data.variableId+'" alt="Audit Lock" title="Audit Lock" style="height:9px;width:9px"/>';
				$("[id=AuditUnLockLocateImg_" + data.variableId + "]")
						.replaceWith(lockImgIcon);
				$("[id=AuditUnLockLocateImg_" + data.variableId + "]").show();
				if ($("[id=auditLockEditStatus]").val() == 'false') {
					var hideEditIconLocate = '<span id="'+data.variableId+'_LocateSpace" style="display:none;"></span>';
					$("[id=locate_EditIcon_" + data.variableId + "]")
							.replaceWith(hideEditIconLocate);
					var hidemarkNAIconLocate = '<span id="'+data.variableId+'_NASpace" style="display:none;"></span>';
					$("[id=locate_NA_" + data.variableId + "]").replaceWith(
							hidemarkNAIconLocate);
				}
				if (isElementDefined($("[id=AuditUnLock_" + data.variableId
						+ "]"))) {
					var lockIcon = '<a href = "#" id="AuditLock_'
							+ data.variableId
							+ '" onclick="openUserCommentsLockDialog(\''
							+ data.variableId
							+ '\',\''
							+ data.systemName
							+ '\',\''
							+ data.userName
							+ '\',\''
							+ escape(data.variableDesc)
							+ '\',\'Unlock\');"><img src="${imageDir}/auditUnLockIndicator.GIF" alt="Unlock" title="Unlock" style="height:15px;"/></a>';
					$("[id=AuditUnLock_" + data.variableId + "]").replaceWith(
							lockIcon);
					if ($("[id=auditLockEditStatus]").val() == 'false') {
						var hideEditIcon = '<span id="'+data.variableId+'_Space" style="display:none;"></span>';
						$("[id=" + data.variableId + "_Edit]").replaceWith(
								hideEditIcon);
						$("[id=emptySpace]").remove();
					}
				}

				if (isElementDefined($("[id=AuditLockImg_" + data.variableId
						+ "]"))) {
					var lockImgIcon = '<img src="${imageDir}/AuditLockIndicatorNew.jpg" id="AuditLockImg_'+data.variableId+'" alt="Lock" title="Lock" style="height:9px;width:9px"/>';
					$("[id=AuditLockImg_" + data.variableId + "]").replaceWith(
							lockImgIcon);
					$("[id=AuditLockImg_" + data.variableId + "]").show();

				}
				if (data.isLockedOrUnlocked == 'locked') {
					addErrorToNotificationTray('Mapping is already locked');
					openTrayNotification();
				}
			}
		}
		function openUserCommentsLockDialog(variableId, systemName, lockUserId,
				variableDesc, action) {
			$("#variableIdForLockFlow").val(variableId);
			$("#systemForLockFlow").val(systemName);
			$("#variableDescForLockFlow").val(variableDesc);
			$("#actionForLockFlow").val(action);
			$("#userForLockFlow").val(lockUserId);
			$("#lockComments").val('');
			if (action == 'LockView' || action == 'LockLocate'
					|| action == 'Lock') {
				action = 'Lock';
				if (isElementDefined($("[id=buttonUnlock]"))) {
					var lockImage = '<img id="buttonLock" src="${imageDir}/Lock_but.gif" alt="Lock"  title="Lock"/>';
					$("#buttonUnlock").replaceWith(lockImage);
				}
			} else if (action == 'UnlockView' || action == 'UnlockLocate'
					|| action == 'Unlock') {
				action = 'Unlock';
				if (isElementDefined($("[id=buttonLock]"))) {
					var unlockImage = '<img id="buttonUnlock" src="${imageDir}/Unlock_but.gif" alt="Unlock"  title="Unlock"/>';
					$("#buttonLock").replaceWith(unlockImage);
				}
			}
			$("#lockusercomment").dialog({
				height : 'auto',
				width : '450px',
				title : action + ' - ' + variableId,
				show : 'slide',
				modal : true
			});
			$("#lockusercomment").dialog('open');
		}

		function lockAuditLockView(variableId, systemName, lockUserId,
				variableDesc, usercomment) {
			$.ajax({
				url : "${context}/stateflow/auditLockVariable.ajax",
				dataType : "json",
				type : "POST",
				data : "variableId=" + escape(variableId) + "&systemName="
						+ escape(systemName) + "&userName="
						+ escape(lockUserId) + "&variableDesc="
						+ escape(variableDesc) + "&userComment="
						+ escape(usercomment),
				success : function(data) {
					cache: false, displayUnLockIconForView(data);
				}
			});
		}
		function displayUnLockIconForView(data) {
			var errorMessages = data.error_messages;
			if (typeof (errorMessages) != 'undefined'
					&& errorMessages.length > 0) {
				for (i = 0; i < errorMessages.length; i++) {
					addErrorToNotificationTray(errorMessages[i]);
				}
				openTrayNotification();
			} else {
				var unLockIcon = '<a href = "#" id="AuditUnLockView_'
						+ data.variableId
						+ '" onclick="openUserCommentsLockDialog(\''
						+ data.variableId + '\',\'' + data.systemName + '\',\''
						+ data.userName + '\',\'' + escape(data.variableDesc)
						+ '\',\'LockView\');">Lock</a>';
				$("[id=AuditLockView_" + data.variableId + "]").replaceWith(
						unLockIcon);
				$("[id=AuditLockViewImg_" + data.variableId + "]").hide();

				if (isElementDefined($("[id=AuditLock_" + data.variableId + "]"))) {
					var unLockIconforLanding = '<a href = "#" id="AuditUnLock_'
							+ data.variableId
							+ '" onclick="openUserCommentsLockDialog(\''
							+ data.variableId
							+ '\',\''
							+ data.systemName
							+ '\',\''
							+ data.userName
							+ '\',\''
							+ escape(data.variableDesc)
							+ '\',\'Lock\');"><img src="${imageDir}/AuditLockIndicatorNew.jpg" alt="Lock" title="Lock" style="height:15px;"/></a>';
					$("[id=AuditLock_" + data.variableId + "]").replaceWith(
							unLockIconforLanding);
					if ($("[id=auditLockEditStatus]").val() == 'false') {
						var editIcon = '<a href = "#" id="'
								+ data.variableId
								+ '_Edit" onclick="editMapping(\''
								+ data.variableId
								+ '\')";\'><img src="${imageDir}/edit_icon.gif" alt="Edit" title="Edit"  style="height:15px;"/></a><span id="emptySpace">&#160;</span>';
						$("[id=" + data.variableId + "_Space]").replaceWith(
								editIcon);
					}
				}

				if (isElementDefined($("[id=AuditLockImg_" + data.variableId
						+ "]"))) {
					$("[id=AuditLockImg_" + data.variableId + "]").hide();
				}

				if (isElementDefined($("[id=AuditLockLocate_" + data.variableId
						+ "]"))) {
					var unLockIconForLocate = '<a href = "#" id="AuditUnLockLocate_'
							+ data.variableId
							+ '" onclick="openUserCommentsLockDialog (\''
							+ data.variableId
							+ '\',\''
							+ data.systemName
							+ '\',\''
							+ data.userName
							+ '\',\''
							+ escape(data.variableDesc)
							+ '\',\'LockLocate\');"><img src="${imageDir}/AuditLockIndicatorNew.jpg" alt="Lock" title="Lock" style="height:15px;width:12px;"/></a>';
					$("[id=AuditLockLocate_" + data.variableId + "]")
							.replaceWith(unLockIconForLocate);
					if ($("[id=auditLockEditStatus]").val() == 'false') {
						var editIconLocate = '<a href="#" id="locate_EditIcon_'
								+ data.variableId
								+ '" onclick=\'editMappingVariable("'
								+ data.variableId
								+ '");\'><img src="${imageDir}/edit_icon.gif"  alt="Edit" title="Edit" /></a><span id="emptySpace">&#160;</span>';
						$("[id=" + data.variableId + "_LocateSpace]")
								.replaceWith(editIconLocate);
						var markNAIconLocate = '<a href="#" id="locate_NA_'
								+ data.variableId
								+ '" onclick=\'editMapping("'
								+ data.variableId
								+ '");\'><img src="${imageDir}/Applicable.gif" alt="Mark as Applicable"  title="Mark as Applicable"/></a>';
						$("[id=" + data.variableId + "_NASpace]").replaceWith(
								markNAIconLocate);
					}
				}

				if (isElementDefined($("[id=AuditLockLocate_" + data.variableId
						+ "]"))) {
					$("[id=AuditUnLockLocateImg_" + data.variableId + "]")
							.hide();
				}

				if (data.isLockedOrUnlocked == 'unlocked') {
					addErrorToNotificationTray('Mapping is not locked');
					openTrayNotification();
				} else {
					$("#viewAuditTrailId").html(data.auditTrailList);
				}
			}
		}
		function unLockAuditLockView(variableId, systemName, lockUserId,
				variableDesc, usercomment) {
			$.ajax({
				url : "${context}/stateflow/auditUnLockVariable.ajax",
				dataType : "json",
				type : "POST",
				data : "variableId=" + escape(variableId) + "&systemName="
						+ escape(systemName) + "&userName="
						+ escape(lockUserId) + "&variableDesc="
						+ escape(variableDesc) + "&userComment="
						+ escape(usercomment),
				success : function(data) {
					cache: false, displayLockIconFromView(data);
				}
			});
		}
		function displayLockIconFromView(data) {
			var errorMessages = data.error_messages;
			if (typeof (errorMessages) != 'undefined'
					&& errorMessages.length > 0) {
				for (i = 0; i < errorMessages.length; i++) {
					addErrorToNotificationTray(errorMessages[i]);
				}
				openTrayNotification();
			} else {
				var lockIcon = '<a href = "#" id="AuditLockView_'
						+ data.variableId
						+ '" onclick="openUserCommentsLockDialog(\''
						+ data.variableId + '\',\'' + data.systemName + '\',\''
						+ data.userName + '\',\'' + escape(data.variableDesc)
						+ '\',\'UnlockView\');">Unlock</a>';
				$("[id=AuditUnLockView_" + data.variableId + "]").replaceWith(
						lockIcon);
				var lockImgIcon = '<img src="${imageDir}/AuditLockIndicatorNew.jpg" id="AuditLockViewImg_'+data.variableId+'" alt="Lock" title="Lock" style="height:9px;width:9px"/>';
				$("[id=AuditLockViewImg_" + data.variableId + "]").replaceWith(
						lockImgIcon);
				$("[id=AuditLockViewImg_" + data.variableId + "]").show();

				if (isElementDefined($("[id=AuditUnLock_" + data.variableId
						+ "]"))) {
					var lockIconForLanding = '<a href = "#" id="AuditLock_'
							+ data.variableId
							+ '" onclick="openUserCommentsLockDialog(\''
							+ data.variableId
							+ '\',\''
							+ data.systemName
							+ '\',\''
							+ data.userName
							+ '\',\''
							+ escape(data.variableDesc)
							+ '\',\'Unlock\');"><img src="${imageDir}/auditUnLockIndicator.GIF" alt="Unlock" title="Unlock" style="height:15px;"/></a>';
					$("[id=AuditUnLock_" + data.variableId + "]").replaceWith(
							lockIconForLanding);
					if ($("[id=auditLockEditStatus]").val() == 'false') {
						var hideEditIcon = '<span id="'+data.variableId+'_Space" style="display:none;"></span>';
						$("[id=" + data.variableId + "_Edit]").replaceWith(
								hideEditIcon);
						$("[id=emptySpace]").remove();
					}
				}

				if (isElementDefined($("[id=AuditLockImg_" + data.variableId
						+ "]"))) {
					var lockImgIconForLanding = '<img src="${imageDir}/AuditLockIndicatorNew.jpg" id="AuditLockImg_'+data.variableId+'" alt="Lock" title="Lock" style="height:9px;width:9px"/>';
					$("[id=AuditLockImg_" + data.variableId + "]").replaceWith(
							lockImgIconForLanding);
					$("[id=AuditLockImg_" + data.variableId + "]").show();

				}
				if (isElementDefined($("[id=AuditUnLockLocate_"
						+ data.variableId + "]"))) {
					var lockIconForLocate = '<a href = "#" id="AuditLockLocate_'
							+ data.variableId
							+ '" onclick="openUserCommentsLockDialog (\''
							+ data.variableId
							+ '\',\''
							+ data.systemName
							+ '\',\''
							+ data.userName
							+ '\',\''
							+ escape(data.variableDesc)
							+ '\',\'UnlockLocate\');"><img src="${imageDir}/auditUnLockIndicator.GIF" alt="Unlock" title="Unlock" style="height:15px;width:12px;"/></a>';
					$("[id=AuditUnLockLocate_" + data.variableId + "]")
							.replaceWith(lockIconForLocate);
					if ($("[id=auditLockEditStatus]").val() == 'false') {
						var hideEditIconLocate = '<span id="'+data.variableId+'_LocateSpace" style="display:none;"></span>';
						$("[id=locate_EditIcon_" + data.variableId + "]")
								.replaceWith(hideEditIconLocate);
						var hidemarkNAIconLocate = '<span id="'+data.variableId+'_NASpace" style="display:none;"></span>';
						$("[id=locate_NA_" + data.variableId + "]")
								.replaceWith(hidemarkNAIconLocate);
					}
				}
				if (isElementDefined($("[id=AuditUnLockLocateImg_"
						+ data.variableId + "]"))) {
					var lockImgIconForLocate = '<img src="${imageDir}/AuditLockIndicatorNew.jpg" id="AuditUnLockLocateImg_'+data.variableId+'" alt="Audit Lock" title="Audit Lock" style="height:9px;width:9px"/>';
					$("[id=AuditUnLockLocateImg_" + data.variableId + "]")
							.replaceWith(lockImgIconForLocate);
					$("[id=AuditUnLockLocateImg_" + data.variableId + "]")
							.show();

				}

				if (data.isLockedOrUnlocked == 'locked') {
					addErrorToNotificationTray('Mapping is already locked');
					openTrayNotification();
				} else {
					$("#viewAuditTrailId").html(data.auditTrailList);
				}
			}
		}
		

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
	</script>
	<%@ include file="/WEB-INF/jspf/pageTop.jspf"%>

	<div class="innerContainer" style="height: 96%;">
		<!-- innerContainer Starts-->
		<div class="Level1">
			<!-- Level1 Starts-->
			<div class="unmapped">
				<!--unmapped Starts-->
				<div class="titleBar">
					<div class="headerTitle">Unmapped Variables</div>
				</div>
				<form name="unmappedVarForm" method="POST" action="${context}/viewcreatemappingpage/viewFromUnMapped.html">
					<div class="ListTableDiv" id="unmappedTableDiv">
						<!--Starts submitterContinue-->
						<table border="0" cellpadding="0" cellspacing="0"
							id="unMappedTable" class="mappedTable Pd3 shadedText">
							<THEAD>
								<tr class="UnmappedVariables unmappedTable">
									<th id="systemId" width="4%" class="tableHeader">System <span
										id="systemIcon" class="ui-widget-header"
										style="padding-left: 30px; display: inline-block; background: none; border: 0px; position: right; top: -4px; left: -7px; height: 10px;">
											<span class="ui-icon ui-icon-carat-1-s"
											style="position: right; height: 10px;" />
									</span>
									</th>
									<th id="variableWpdId" width="20%" class="tableHeader">
										Variable ID <span id="variableIcon" class="ui-widget-header"
										style="padding-left: 45px; display: inline-block; background: none; border: 0px; position: right; top: -4px; left: -7px; height: 10px;">
											<span class="ui-icon ui-icon-carat-1-s"
											style="position: right; height: 10px;" />
									</span>
									</th>
									<th id="descId" width="33%" class="tableHeader">
										Description <span id="descIcon" class="ui-widget-header"
										style="padding-left: 50px; display: inline-block; background: none; border: 0px; position: right; top: -4px; left: -7px; height: 10px;">
											<span class="ui-icon ui-icon-carat-1-s"
											style="position: right; height: 10px;" />
									</span>
									</th>
									<th id="createdId" width="20%" class="tableHeader"
										nowrap="nowrap">Created&nbsp;On <span id="createdIcon"
										class="ui-widget-header"
										style="padding-left: 50px; display: inline-block; background: none; border: 0px; position: right; top: -4px; left: -7px; height: 10px;">
											<span class="ui-icon ui-icon-carat-1-s"
											style="position: right; height: 10px;" />
									</span>
									</th>
									<td width="23%" class="tableHeader" nowrap="nowrap">&nbsp;</td>
								</tr>
							</THEAD>
							<TBODY>
								<c:set var="rowCount" value="0" />

								<c:if test="${! empty unmappedVariables}">

									<c:forEach items="${unmappedVariables}" var="unmappedVar">

										<tr class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
											<input type="hidden" id="unmappedVariableId" value="" />
											<input type="hidden" name="variableIdHidden"
												id="variableIdHidden" value="" />
											<td>${unmappedVar.variable.variableSystem}</td>
											<td>${unmappedVar.variable.variableId}</td>
											<td style="word-wrap: break-word">${unmappedVar.variable.description}</td>
											<td><fmt:formatDate pattern="MM/dd/yyyy"
													value="${unmappedVar.variable.createdDate}" /></td>
											<td nowrap><a href="#"
												onclick='openViewMappingDialog("${unmappedVar.variable.variableId}");'>
													<img src="${imageDir}/search_icon.gif" alt="View" id="view"
													title="View" /> <!--alt will not work in IE8 , instead use title -->
											</a>&#160;&#160; <a href="#"
												onclick='openCreateFromUnmappedSection("${unmappedVar.variable.variableId}");'>
													<img src="${imageDir}/create_icon.gif" alt="Create"
													title="Create" />
											</a> <c:if
													test="${userUIPermissions.authorizedToMarkAsNotApplicable}">
					&#160;&#160;
					<a href="#"
														onclick='openUserCommentsNotApplicableDialog("${unmappedVar.variable.variableId}","${unmappedVar.variable.variableDescForDisplay}");'>
														<img src="${imageDir}/markAsNotApp.gif"
														alt="Mark as Not Applicable"
														title="Mark as Not Applicable" />
													</a>
												</c:if></td>
										</tr>
										<c:set var="rowCount" value="${rowCount + 1}" />
									</c:forEach>
								</c:if>
								<c:if test="${empty unmappedVariables}">
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
			<!--unmapped Ends-->
			<div class="inprogress">
				<!--inprogress Starts-->
				<input type="hidden" name="authorizedToapprove"
					id="authorizedToapprove"
					value="${userUIPermissions.authorizedToapprove}" /> <input
					type="hidden" name="authorizedTounlock" id="authorizedTounlock"
					value="${userUIPermissions.authorizedToUnlock}" /> <input
					type="hidden" name="authorizedToAuditLock"
					id="authorizedToAuditLock"
					value="${userUIPermissions.authorizedToAuditLock}" /> <input
					type="hidden" name="authorizedToAuditUnlock"
					id="authorizedToAuditUnlock"
					value="${userUIPermissions.authorizedToAuditUnlock}" />

				<div class="titleBar">
					<div class="headerTitle">
						In Progress <a href="#" onclick='toggleUserIcon();'
							class="toggleUserIcon"><img
							src="${imageDir}/icon-by-customer.gif" id="toggled"
							style="height: 15px;" alt="Show All Users" title="Show All Users"></a>
						<a href="#" onclick='toggleNumRecords();'><img
							src="${imageDir}/copy_landing.gif" id="numRecords"
							style="height: 15px;" alt="Show All Mappings"
							title="Show All Mappings"></a> <a href="#"
							onclick='printInprogress();'><img src="${imageDir}/print.gif"
							id="printIcon" style="height: 15px;" alt="Print" title="Print"></a>
					</div>
				</div>

				<div class="ListTableDiv" id="inProgressDiv">
					<!--Starts submitterContinue-->
					<table id="inProgressTable" border="0" cellpadding="0"
						cellspacing="0" class="mappedTable Pd3 shadedText">
						<THEAD>
							<tr class="UnmappedVariables unmappedTable">
								<th id="systemIdProg" width="1%" class="tableHeader">
									System <span id="systemIconProg" class="ui-widget-header"
									style="padding-left: 10px; display: inline-block; background: none; border: 0px; position: right; top: -4px; left: -7px; height: 10px;">
										<span class="ui-icon ui-icon-carat-1-s"
										style="position: right; height: 10px;" />
								</span>
								</th>
								<th id="varIdProg" width="21%" class="tableHeader">
									Variable ID <span id="variableIconProg"
									class="ui-widget-header"
									style="padding-left: 45px; display: inline-block; background: none; border: 0px; position: right; top: -4px; left: -7px; height: 10px;">
										<span class="ui-icon ui-icon-carat-1-s"
										style="position: right; height: 10px;" />
								</span>
								</th>
								<th id="descIdProg" width="14%" class="tableHeader">
									Description <span id="descIconProg" class="ui-widget-header"
									style="padding-left: 37px; display: inline-block; background: none; border: 0px; position: right; top: -4px; left: -7px; height: 10px;">
										<span class="ui-icon ui-icon-carat-1-s"
										style="position: right; height: 10px;" />
								</span>
								</th>
								<th id="updatedIdProg" width="11%" class="tableHeader">
									Updated&nbsp;On <span id="updatedIconProg"
									class="ui-widget-header"
									style="padding-left: 35px; display: inline-block; background: none; border: 0px; position: right; top: -4px; left: -7px; height: 10px;">
										<span class="ui-icon ui-icon-carat-1-s"
										style="position: right; height: 10px;" />
								</span>
								</th>
								<th id="userIdProg" width="10%" class="tableHeader">
									User&nbsp;Id <span id="userIdIconProg" class="ui-widget-header"
									style="padding-left: 10px; display: inline-block; background: none; border: 0px; position: right; top: -4px; left: -7px; height: 10px;">
										<span class="ui-icon ui-icon-carat-1-s"
										style="position: right; height: 10px;" />
								</span>
								</th>
								<td width="45%" class="tableHeader">&nbsp;</td>
							</tr>
						</THEAD>
						<TBODY>
							<c:set var="lock" value="1" />
							<c:if test="${! empty inProgressVariables}">
								<c:set var="rowCount" value="0" />
								<c:forEach items="${inProgressVariables}" var="inProgressVar">
									<c:if test="${!inProgressVar.finalized}">
										<c:set var="rowClass" value="finalized" />
									</c:if>
									<c:if test="${inProgressVar.finalized}">
										<c:set var="rowClass"
											value="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}" />
									</c:if>

									<tr class="${rowClass}">
										<td>${inProgressVar.variable.variableSystem}</td>
										<td>${inProgressVar.variable.variableId} <input
											type="hidden" name="auditLockStatus" id="auditLockStatus"
											value="${inProgressVar.auditLock}" /> <input type="hidden"
											name="auditLockEditStatus" id="auditLockEditStatus"
											value="${userUIPermissions.authorizedEditLockVar}" /> <c:if
												test="${inProgressVar.auditLock == 'Y' && inProgressVar.variableMappingStatus != 'UNMAPPED' }">
												<img src="${imageDir}/AuditLockIndicatorNew.jpg" alt="Lock"
													title="Lock"
													id="AuditLockImg_${inProgressVar.variable.variableId}"
													style="height: 9px; width: 9px" />
											</c:if> <c:if
												test="${inProgressVar.auditLock == 'N' && inProgressVar.variableMappingStatus != 'UNMAPPED' }">
												<div id="AuditLockImg_${inProgressVar.variable.variableId}"></div>
											</c:if>
										</td>
										<td style="word-wrap: break-word; WORD-BREAK: BREAK-ALL;">${inProgressVar.variable.description}</td>
										<td><fmt:formatDate pattern="MM/dd/yyyy"
												value="${inProgressVar.variable.createdDate}" /></td>
										<td>${inProgressVar.user.lastUpdatedUserName}</td>
										<td><a href="#"
											onclick='openViewMappingInProgressDialog("${inProgressVar.variable.variableId}");'><img
												src="${imageDir}/search_icon.gif" alt="View" title="View"
												style="height: 15px;" /></a>&#160;&#160; <c:set var="lock"
												value="1" /> <c:if test="${empty inProgressVar.lock}">
												<c:set var="lock" value="0" />
											</c:if> <c:if test="${inProgressVar.lock.lockUserId  == userName}">
												<c:set var="lock" value="0" />
											</c:if> <c:if test="${lock == 0}">
												<c:if
													test="${(inProgressVar.auditLock == 'N') || (userUIPermissions.authorizedEditLockVar && inProgressVar.auditLock == 'Y')}">
													<c:set var="idEdit"
														value="${inProgressVar.variable.variableId}_Edit" />
													<a href="#" id="${idEdit}"
														onclick='editMapping("${inProgressVar.variable.variableId}");'><img
														src="${imageDir}/edit_icon.gif" alt="Edit" title="Edit"
														style="height: 15px;" /></a>
													<span id="emptySpace">&#160;</span>
												</c:if>
												<c:if
													test="${!userUIPermissions.authorizedEditLockVar && inProgressVar.auditLock == 'Y'}">
													<span id="${inProgressVar.variable.variableId}_Space"
														style="display: none;"></span>
												</c:if>
											</c:if> <c:if test="${lock == 1}">
												<c:if test="${userUIPermissions.authorizedToUnlock}">
													<c:set var="idLock"
														value="${inProgressVar.variable.variableId}_Lock" />
													<a href="#" id="${idLock}"
														onclick='unlockMappingFromLocate("${inProgressVar.variable.variableId}","unlockVariableFromLanding","${inProgressVar.lock.lockUserId}","${inProgressVar.auditLock}","${inProgressVar.variable.variableSystem}","${inProgressVar.variable.description}");'><img
														src="${imageDir}/lock_icon.jpg" alt="Lock" title="Lock"
														style="height: 15px;" /></a>&#160;&#160;
						</c:if>

												<c:if test="${!userUIPermissions.authorizedToUnlock}">
													<img src="${imageDir}/lock_icon.jpg" alt="Lock"
														title="Lock" style="height: 15px;" />&#160;&#160;
						</c:if>
											</c:if> <c:set var="sentToTest" value="0" /> <c:set var="approve"
												value="0" /> <c:set var="idTest"
												value="${inProgressVar.variable.variableId}_Test" /> <c:set
												var="idApprove"
												value="${inProgressVar.variable.variableId}_Approve" /> <c:if
												test="${inProgressVar.variableMappingStatus == 'SCHEDULED_TO_TEST'}">
												<c:set var="sentToTest" value="1" />
												<c:set var="approve" value="1" />
											</c:if> <c:if
												test="${inProgressVar.variableMappingStatus == 'OBJECT_TRANSFERRED'}">
												<c:set var="sentToTest" value="1" />
											</c:if> <c:if
												test="${inProgressVar.variableMappingStatus == 'SCHEDULED_TO_PRODUCTION' || inProgressVar.variableMappingStatus == 'PUBLISHED'}">
												<c:set var="sentToTest" value="1" />
												<c:set var="approve" value="1" />
											</c:if> <c:if test="${sentToTest != 1 && lock != 1}">
												<a href="#" id="${idTest}"
													onclick='openUserCommentsSend2TestDialog("${inProgressVar.variable.variableId}");'><img
													src="${imageDir}/test_icon.gif" alt="Send to Test"
													title="Send to Test" style="height: 15px;" /></a>&#160;&#160;
					</c:if> <!-- Only for the approver //To Do: Access check --> <c:if
												test="${approve != 1 && lock != 1}">
												<c:if
													test="${userUIPermissions.authorizedToapprove || inProgressVar.variableMappingStatus == 'OBJECT_TRANSFERRED' }">
													<a href="#" id="${idApprove}"
														onclick='openUserCommentsApproveDialog("${inProgressVar.variable.variableId}");'><img
														src="${imageDir}/approve_icon.gif" alt="Approve"
														title="Approve" style="height: 15px;" /></a>&#160;&#160;
						</c:if>
											</c:if> <c:if
												test="${userUIPermissions.authorizedToAuditLock && inProgressVar.auditLock == 'Y' && lock != 1}">
												<c:set var="auditLock"
													value="AuditLock_${inProgressVar.variable.variableId}" />
												<a href="#" id="${auditLock}"
													onclick='openUserCommentsLockDialog("${inProgressVar.variable.variableId}","${inProgressVar.variable.variableSystem}","${inProgressVar.lock.lockUserId}","${inProgressVar.variable.description}","Unlock");'><img
													src="${imageDir}/auditUnLockIndicator.GIF" alt="Unlock"
													title="Unlock" style="height: 15px; width: 12px;" /></a>
											</c:if> <c:if
												test="${userUIPermissions.authorizedToAuditUnlock && inProgressVar.auditLock == 'N' && lock != 1}">
												<c:set var="auditUnLock"
													value="AuditUnLock_${inProgressVar.variable.variableId}" />
												<a href="#" id="${auditUnLock}"
													onclick='openUserCommentsLockDialog("${inProgressVar.variable.variableId}","${inProgressVar.variable.variableSystem}","${inProgressVar.lock.lockUserId}","${inProgressVar.variable.description}","Lock");'><img
													src="${imageDir}/AuditLockIndicatorNew.jpg" alt="Lock"
													title="Lock" style="height: 15px; width: 12px;" /></a>
											</c:if> <c:if
												test="${userUIPermissions.authorizedToAuditUnlock && inProgressVar.auditLock == 'N' && lock == 1}">
												<div id="AuditUnLock_${inProgressVar.variable.variableId}"></div>
											</c:if> <c:if
												test="${userUIPermissions.authorizedToAuditLock && inProgressVar.auditLock == 'Y' && lock == 1}">
												<div id="AuditLock_${inProgressVar.variable.variableId}"></div>
											</c:if></td>
									</tr>
									<c:set var="rowCount" value="${rowCount + 1}" />
								</c:forEach>
							</c:if>
						</TBODY>
					</table>
				</div>
			</div>
			<!--In Progress Ends-->
		</div>

		<!-- Level1 Ends-->
		<div class="clear"></div>

		<div class="Level2 mt10">
			<!-- Level2 Starts-->

			<div class="mapping" style="position: relative; left: -1px;">
				<div class="titleBar">
					<div class="headerTitle">Create</div>
				</div>
				<form name="createform" method="POST" action="${context}/viewcreatemappingpage/viewFromCreate.html">
					<div class="createMapping">
						<table border="0" cellpadding="0" cellspacing="0"
							class="mt10 mL10 Pd3 shadedText">
							<tr>
								<td width="80px">Variable ID <span class="star">*</span></td>
								<td width="150px"><input type="textbox" class="inputbox65"
									name="selectedVariableId" id="variableId" /></td>
								<td><label id="variableIdLabel" for="selectedVariableId"
									style="font-size: 11px"></label></td>
							</tr>
							<tr>
								<td>&#160;</td>
								<td><a href="#" onclick="openCreatePage()"><img
										src="${imageDir}/create_but.gif" width="78" height="23" /></a></td>
							</tr>
						</table>
					</div>
				</form>
				<!--createMapping Ends-->
			</div>
			<!--mapping Ends-->


			<div class="Locate" style="position: relative; right: -1px;">
				<div class="titleBar">
					<div class="headerTitle">
						Locate <span style="padding-left: 390px; position: right;"><a
							style="color: #000000; font-size: 10;"
							href="${pageContext.request.contextPath}/advancesearch/viewSearch.html"
							id="link2"><img src="${imageDir}/Search-icon.png" width="16"
								height="16" alt="Advanced Search" title="Advanced Search" /></a></span>
					</div>
				</div>

				<form name="locateResultsForm"
					action="${context}/locateresults/locateRequest.ajax" method="POST">
					<div class="createTable">
						<table border="0" cellpadding="0" cellspacing="0"
							class="mt10 mL10 Pd3 shadedText">
							<input type="hidden" name="currentPage" id="currentPage"
								value="0" />
							<tr>
								<td width="80px">Variable ID</td>
								<td width="180px"><input type="textbox" class="inputbox65"
									id="selectedVariableIdFrmLocate" name="variableIDLocate"
									onkeypress="enterKeyPress(event);" /></td>
								<td width="30px">Show</td>
								<td width="100px" class="verdana topVerticalAlign"><input
									type="checkbox"
									<c:if test="${variable.unmappedVariable == true}">checked </c:if>
									name="isUnMapped" id="isUnMapped" value="unMapped"
									onclick="setFocus('locateButton');" />Unmapped</td>
							</tr>
							<tr>
								<td>Description</td>
								<td><input type="textbox" class="inputbox65"
									id="selectedVariableDescFrmLocate" name="variableDescLocate"
									onkeypress="enterKeyPress(event);" /></td>
								<td>&#160;</td>
								<td class="verdana  topVerticalAlign"><input
									type="checkbox"
									<c:if test="${variable.mappedVariable == true}">checked</c:if>
									name="isMapped" id="isMapped" value="isMapped"
									onclick="setFocus('locateButton');" />Mapped</td>
							</tr>
							<tr>
								<td>&#160;</td>

								<td><a href="#" id="locateButton"
									onclick="openLocateResultsDialog('0','fromLanding');"><img
										src="${imageDir}/locate_but.gif" width="78" height="23" /></a></td>
								<td>&#160;</td>
								<td class="verdana  topVerticalAlign"><input
									type="checkbox"
									<c:if test="${variable.notApplicable == true}">checked</c:if>
									name="isNotApplicable" id="isNotApplicable"
									value="isNotApplicable" onclick="setFocus('locateButton');" />Not
									Applicable</td>
							</tr>
						</table>
					</div>
				</form>
				<!--createMapping Ends-->
			</div>

		</div>
		<!-- Level2 Ends-->
	</div>
	<form name="editForm" action="${context}/editmapping/editMapping.html"
		method="POST">
		<input type="hidden" id="pageName" name="pageName"
			value="viewlandingpage" /> <input type="hidden"
			id="selectedvariableForEditId" name="selectedvariableForEditId"
			value="" /> <input type="hidden" id="getAllPrint" name="getAllPrint"
			value="" /> <input type="hidden" id="appendUserPrint"
			name="appendUserPrint" value="" />
	</form>
	<div id="viewMappingDialog" title="View Mapping"></div>
	<!-- innerContainer Ends-->
	<%@ include file="/WEB-INF/jspf/pageFoot.jspf"%>

	<div id="userCommentsApproveDialog" style="display: none;">
		<form name="userCommentsApproveForm"
			action="${context}/landingpagestateflow/approve.html" method="POST">
			<input type="hidden" name="approvestateflowvariableId"
				id="approvestateflowvariableId" value="" />
			<table id="approveUserCommentsTable1" border="0" cellpadding="0"
				cellspacing="0" class="Pd3 mT5 bT">
				<tr class="">
					<td><textarea name="approvedMappingComments"
							id="approvedMappingComments" rows="5" cols="77"></textarea></td>
				</tr>
			</table>
			<table border="0" align="center" cellpadding="0" cellspacing="0"
				class="footerTable">
				<tr>
					<td><a href="#" onclick="saveUserCommentsForApprove()"><img
							id="approveSaveImg" src="${imageDir}/save_but.gif" alt="Save"
							title="Save" /></a></td>

				</tr>
			</table>
		</form>
	</div>

	<div id="userCommentsSent2TestDialog" style="display: none;">
		<form name="userCommentsSent2TestForm"
			action="${context}/landingpagestateflow/sendToTest.html"
			method="POST">
			<input type="hidden" name="send2teststateflowvariableId"
				id="send2teststateflowvariableId" value="" />
			<table id="sendToTestUserCommentsTable1" border="0" cellpadding="0"
				cellspacing="0" class="Pd3 mT5 bT">
				<tr class="">
					<td><textarea name="send2TestMappingComments"
							id="send2TestMappingComments" rows="5" cols="77"></textarea></td>
				</tr>
			</table>
			<table border="0" align="center" cellpadding="0" cellspacing="0"
				class="footerTable">
				<tr>
					<td><a href="#" onclick="saveUserCommentsForSent2Test();"><img
							id="sendToTestSaveImg" src="${imageDir}/save_but.gif" alt="Save"
							title="Save" /></a></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="userCommentsNotApplicableDialog" style="display: none;">
		<form name="userCommentsNotApplicableForm"
			action="${context}/landingpagestateflow/markVariableAsNotApplicable.html"
			method="POST">
			<input type="hidden" name="notApplicablestateflowvariableId"
				id="notApplicablestateflowvariableId" value="" /> <input
				type="hidden" name="notApplicablestateflowvariableDesc"
				id="notApplicablestateflowvariableDesc" value="" />
			<table id="NACommentsTable1" border="0" cellpadding="0"
				cellspacing="0" class="Pd3 mT5 bT">
				<tr class="">
					<td><textarea name="notApplicableMappingComments"
							id="notApplicableMappingComments" rows="5" cols="77"></textarea></td>
				</tr>
			</table>
			<table border="0" align="center" cellpadding="0" cellspacing="0"
				class="footerTable">
				<tr>
					<td><a href="#" onclick="saveUserCommentsForNotApplicable();"><img
							id="notApplicableSaveImg" src="${imageDir}/save_but.gif"
							alt="Save" title="Save" /></a></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="userCommentsDialog" style="display: none;">
		<table id="userCommentsTable1" border="0" cellpadding="0"
			cellspacing="0" class="Pd3 mT5 bT">
			<tr class="">
				<td><textarea name="userMappingComments"
						id="userMappingComments" rows="5" cols="77"></textarea></td>
			</tr>
		</table>
		<table border="0" align="center" cellpadding="0" cellspacing="0"
			class="footerTable">
			<tr>
				<td><a href="#" onclick="saveUserComments();"><img
						src="${imageDir}/save_but.gif" alt="Save" title="Save" /></a></td>
			</tr>
		</table>
	</div>

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
	
	<!-- Start 30296 EBX OOAMESSAGE  -->

	<div id="ooaMessageDeleteDialog" style="display: none;">
		<form name="ooaMessageDeleteDialogForm"
			action="${context}/lockedvariableauditreport/deleteOOAMessage.html"
			method="POST">
			<input type="hidden" name="delNetworkOrContractCode"
				id="delNetworkOrContractCode" value="" /> <input type="hidden"
				name="messageId" id="messageId" value="" /> <input type="hidden"
				name="search" id="search" value="" />
			<  <table id="deleteCommentsTable1" border="0" cellpadding="0"
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
	<div id="contractBxMappingDialog" title="Contract Mapping"></div>
	<div id="lockAuditReportDialog" title="Lock Audit Report"></div>
	<div id="lockusercomment" style="display: none;">

		<div id="ooaMessageReportDialog" title="OOA Message Search Criteria">
		</div>


		<%@include file="/WEB-INF/jsp/lockUserCommentPopup.jsp"%>
	</div>
	<%@include file="/WEB-INF/jspf/messageTray.jspf"%>
</body>
</html>
