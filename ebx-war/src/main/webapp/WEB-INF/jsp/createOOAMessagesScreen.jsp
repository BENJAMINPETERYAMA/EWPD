<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

</head>
<body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/simulationInputPage.js"></script>
	
	<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
	<c:set var="context" value="${pageContext.request.contextPath}" />
	<script type="text/javascript">
		
	
		$(document).ready(function() {
			
			var modify = true;
			
			/* $("#auditCommentDialog").hide(); */
			
			if('${currentPageMode}'=="EDIT"){
			if('${maintainMessageObject.workFlowAssosciationStatus}'=='BUILDING'){
			
			$("#oOAMessageEffectiveDatePicker").datepicker({
					showOn : 'button',
					buttonText : "Select",
					buttonImage : '${imageDir}/cal.gif',
					buttonImageOnly : true,
					changeYear : modify,
					yearRange : '1900:9999',
					defaultDate: new Date(1900,00,01)
				});
				
				document.getElementById("oOAMessageEffectiveDatePicker").disabled  = false;
			
			}else{
			document.getElementById("oOAMessageEffectiveDatePicker").disabled  = true;
			}	
				//modify = false;
				
				/* $("#oOAMessageEffectiveDatePicker").datepicker({
					showOn : 'button',
					buttonText : "Select",
					buttonImage : '${imageDir}/cal.gif',
					buttonImageOnly : true,
					changeYear : modify,
					yearRange : '1900:9999',
					defaultDate: new Date(1900,00,01)
				}); */
				$("#oOAMessageExpiryDatePicker").datepicker({
					showOn : 'button',
					buttonText : "Select",
					buttonImage : '${imageDir}/cal.gif',
					buttonImageOnly : true,
					changeYear : true,
					yearRange : '1900:9999',
					defaultDate: new Date(9999,11,31)
				});
				
				
			} else {
				
				$("#oOAMessageEffectiveDatePicker").datepicker({
					showOn : 'button',
					buttonText : "Select",
					buttonImage : '${imageDir}/cal.gif',
					buttonImageOnly : true,
					changeYear : modify,
					yearRange : '1900:9999',
					defaultDate: new Date(1900,00,01)
				});
				
				$("#oOAMessageExpiryDatePicker").datepicker({
					showOn : 'button',
					buttonText : "Select",
					buttonImage : '${imageDir}/cal.gif',
					buttonImageOnly : true,
					changeYear : true,
					yearRange : '1900:9999',
					defaultDate: new Date(9999,11,31)
				});
				document.getElementById('forCancel').disabled  = true;
				document.getElementById('forCancel').style.visibility = 'hidden';
				
			}
					
			
		});
		$(document).ready(function() {
			var maintainOOMessage = '${maintainMessageObject.messageTextOne}'+'${maintainMessageObject.messageTextTwo}'+'${maintainMessageObject.messageTextThree}';
			document.getElementById("oOAMessageTextAreaId").value = maintainOOMessage;
			//document.getElementById("oOAMessageEffectiveDatePicker").disabled  = true;
			//document.getElementById("oOAMessageExpiryDatePicker").disabled  = true;
			
			
			
			if('${currentPageMode}'=="EDIT"){
				
			
			document.getElementById("oOAMessageEffectiveDatePicker").value =onDateCheck('${maintainMessageObject.messageEffectiveDate.month}','${maintainMessageObject.messageEffectiveDate.date}','${maintainMessageObject.messageEffectiveDate.year}');
			
			
			document.getElementById("oOAMessageExpiryDatePicker").value =onDateCheck('${maintainMessageObject.messageExpiryDate.month}','${maintainMessageObject.messageExpiryDate.date}','${maintainMessageObject.messageExpiryDate.year}');
			
			
			if('${maintainMessageObject.search}'== 'Network'){
			
				if('${maintainMessageObject.exchangeIndicator}'=='BT'){
			
					document.getElementById("exchangeIndicatorDrpDwnID").value  = 'BOTH';
				}else if('${maintainMessageObject.exchangeIndicator}'=='OF') {
					document.getElementById("exchangeIndicatorDrpDwnID").value  = 'OFF';
				}
				else{
					document.getElementById("exchangeIndicatorDrpDwnID").value  = 'ON';
				}
			} else {
				if('${maintainMessageObject.exchangeIndicator}'=='Y'){
					if(document.getElementById("exemptCheckBox") != null ){
					document.getElementById("exemptCheckBox").checked = true;
					}
					document.getElementById("oOAMessageTextAreaId").disabled = true;
					
				} 
			}
			
			
			} else {
				document.getElementById("oOAMessageEffectiveDatePicker").value ='01/01/1900';
				document.getElementById("oOAMessageExpiryDatePicker").value ='12/31/9999';
			}
			
			
		});
		
		function disableTextArea() {

			if (document.getElementById("exemptCheckBox").checked) {
				document.getElementById("oOAMessageTextAreaId").disabled = true;
				document.getElementById("oOAMessageTextAreaId").innerHTML = "";
				document.getElementById("oOAMessageTextPreviewAreaId").innerHTML = "";
			} else {
				document.getElementById("oOAMessageTextAreaId").disabled = false;
			}

		}
		function textAreaAdjust(o) {
 		 	o.style.height = "1px";
  			o.style.height = (50+o.scrollHeight)+"px";
		}
		
		function ooaMessageAuditCommentDialog(networkOrContractVar,
				netWorkOrContractCode, exchangeVal, currentPageMode, messageId,
				oOAMessageTextAreaVar,oOAMessageEffectiveDatePickerVar, 
				oOAMessageExpiryDatePickerVar, hasExchOrExplChanged) {
				
				//$("#auditCommentDialog").show();
				
				$(".ui-dialog>div[id='auditCommentDialog']").show();
				
				$("#netWorkOrContractCodeName").val(networkOrContractVar);
				$("#netWorkOrContractCode").val(netWorkOrContractCode);
				$("#exchangeVal").val(exchangeVal);
				$("#currentPageMode").val(currentPageMode);
				$("#messageId").val(messageId);
				$("#oOAMessageTextAreaVar").val(oOAMessageTextAreaVar);
				$("#oOAMessageEffectiveDatePickerVar").val(oOAMessageEffectiveDatePickerVar);
				$("#oOAMessageExpiryDatePickerVar").val(oOAMessageExpiryDatePickerVar);
				$("#hasExchOrExplChanged").val(hasExchOrExplChanged);
				
				$("#auditCommentDialog table#userCommentsTable1")
						.css("border-top", "1px solid black"); 
						
				$("#auditCommentDialog textarea").text('');
	        $("div[id='auditCommentDialog']").parent('.ui-dialog').remove();
				$("#auditCommentDialog").dialog({
					height : 'auto',
					width : '455px',
					title : 'Audit Comment Dialog',
					modal : true
				});
				//$("#auditCommentDialog").dialog();
				
			}
	function cmtDailogClose(){
		 $("div[id='auditCommentDialog']").parent('.ui-dialog').remove();
		}
	
		function saveAuditComments() {
			
			 var networkOrContractName= $(".ui-dialog>div[id='auditCommentDialog'] #netWorkOrContractCodeName").val();
			var networkOrContractCode = $(".ui-dialog>div[id='auditCommentDialog'] #netWorkOrContractCode").val();
			var exchangeVal = $(".ui-dialog>div[id='auditCommentDialog'] #exchangeVal").val();
			var currentPageMode = $(".ui-dialog>div[id='auditCommentDialog'] #currentPageMode").val();
			var messageId = $("#messageId").val();
			var hasExchOrExplChanged = $(".ui-dialog>div[id='auditCommentDialog'] #hasExchOrExplChanged").val();
			var oOAMessageTextAreaVar = $(".ui-dialog>div[id='auditCommentDialog'] #oOAMessageTextAreaVar").val();
			var oOAMessageEffectiveDatePickerVar = $(".ui-dialog>div[id='auditCommentDialog'] #oOAMessageEffectiveDatePickerVar").val();
			var oOAMessageExpiryDatePickerVar = $(".ui-dialog>div[id='auditCommentDialog'] #oOAMessageExpiryDatePickerVar").val();
			var auditComments =  $(".ui-dialog>div[id='auditCommentDialog'] #auditComments").val(); 
						
			$
					.ajax({
						url : "${context}/lockedvariableauditreport/oOAMessageSave.ajax",
						dataType : "html",
						type : "POST",
						data : "netWorkOrContractCode=" + networkOrContractCode
																
								+ "&netWorkOrContractCodeName=" + networkOrContractCode
								+ "&netWorkOrContractName=" + networkOrContractName
								+ "&ExcahngeOrExplValIdName=" + exchangeVal
								+ "&currentPageMode=" + currentPageMode
								+ "&oldMessageId=" + messageId
								+ "&oOAMessageTextAreaName=" + encodeURIComponent(oOAMessageTextAreaVar)
								+ "&oOAMessageEffectiveDatePickerName=" + oOAMessageEffectiveDatePickerVar
								+ "&oOAMessageExpiryDatePickerName=" + oOAMessageExpiryDatePickerVar
								+ "&hasExchOrExplChangedVar=" + hasExchOrExplChanged
								+ "&auditComments=" + encodeURIComponent(auditComments),
						success :function(data) {
							var newData = null;	
						
							if(data.substring(0,1)=='{'){
								newData = JSON.parse(data);
							}
							
							if(null != newData){
								 if(null != newData.duplicateNotFound
										 && !newData.duplicateNotFound){
								addErrorToNotificationTray("For "+networkOrContractName+" "+networkOrContractCode+" Duplicate data exist within same date segment");
								 } else if (null != newData.invalidLength
										 && !newData.invalidLength){
									 addErrorToNotificationTray("After smart split the third split is exceeding 260 Characters ");
								 }else if (null != newData.duplicateDatesFound
										 && newData.duplicateDatesFound){
									 addErrorToNotificationTray(newData.duplicateDatesMsg);
								 }
							 else if(currentPageMode=='EDIT'){
						newData = JSON.parse(data);
							 $
								.ajax({
									url : "${context}/lockedvariableauditreport/searchOOAMessage.ajax",
									dataType : "html",
									type : "POST",
									data : "searchCriteria=" + networkOrContractCode
											+ "&search=" + networkOrContractName
											+ "&viewOrSearchFunction=searchFunction"
											+ "&excInd=",
									success : function(data) {
										$("#ooaMessageReportDialog").dialog('close');
										$("div[id='auditCommentDialog']").parent('.ui-dialog').remove();
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
										addInfoToNotificationTray(networkOrContractName+" "+networkOrContractCode+" Message Saved Successfully");			
						
						}
										openTrayNotification();							
										$("messageDialog").dialog('close');
						}else{
						addInfoToNotificationTray("The "+networkOrContractName+" "+networkOrContractCode+" message has been saved successfully");
							openTrayNotification();
						 $("div[id='auditCommentDialog']").parent('.ui-dialog').remove();
							$("#ooaMessageReportDialog").dialog('close');
							$("#ooaMessageReportDialog").html(data);
							$("#ooaMessageReportDialog").dialog({
								height : '500',
								width : '690px',
								show : 'slide',
								modal : true,
								resizable : false,
								title : 'OOA Message Search Criteria'

							});
							$("#ooaMessageReportDialog").dialog();
						
						}
							
						}
					});  
						
		}
		
		function validateChars(oOAMessageTextAreaVar){
					var iChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789`~!@#$%^&*()-_+={}[]|\\:;\" \'<,>.?/"; 
					var vNewLine = '\n';
					var rNewLine = '\r';
					var newLineCheck;
					var splCharCheck;
					for (var i = 0; i < oOAMessageTextAreaVar.length; i++) {
						newLineCheck = oOAMessageTextAreaVar.charAt(i);
						splCharCheck = iChars.indexOf(oOAMessageTextAreaVar.charAt(i));
						if((newLineCheck == vNewLine || newLineCheck == rNewLine) && splCharCheck == -1)
						{
						}
						else if(splCharCheck == -1)
						{
							//alert(validationMessage);
							return false;
						}

					}
					return true;
		}
		 
		
		function oOAMessageSave(netWorkOrContractVar, netWorkOrContractCodeVar,
				ExcahngeOrExplValIdVar, currentPageMode, messageId) {
			var noErrors = true;
			
			var validDate = true;
			
			
			if (null != document.getElementById("exemptCheckBox")
					&& document.getElementById("exemptCheckBox").checked) {
				ExcahngeOrExplValIdVar = "Y";
			} else if (null != document.getElementById("exemptCheckBox")
					&& !document.getElementById("exemptCheckBox").checked) {
				ExcahngeOrExplValIdVar = "N";
			}
			

			if (null != document.getElementById("exchangeIndicatorDrpDwnID")) {
				if (document.getElementById("exchangeIndicatorDrpDwnID").value == 'BOTH') {

					ExcahngeOrExplValIdVar = 'BT';
				} else if (document.getElementById("exchangeIndicatorDrpDwnID").value == 'OFF') {
					ExcahngeOrExplValIdVar = 'OF';
				} else {
					ExcahngeOrExplValIdVar = 'ON';
				}

			}
			
			
			//var oOAMessageTextAreaVar = $("#oOAMessageTextAreaId").val();
			var oOAMessageTextAreaVar = document.getElementById("oOAMessageTextAreaId").value
			var oOAMessageEffectiveDatePickerVar = $("#oOAMessageEffectiveDatePicker").val();
			var oOAMessageExpiryDatePickerVar = $("#oOAMessageExpiryDatePicker").val();
			var hasExchOrExplChanged = false;
			//added condition for edit in ScheduleToTest scenario
			if('${currentPageMode}'=="EDIT" && '${maintainMessageObject.workFlowAssosciationStatus}'!='BUILDING'){
				ExcahngeOrExplValIdVar = '${maintainMessageObject.exchangeIndicator}';
			}
			if('${currentPageMode}'=="EDIT"
					&& '${maintainMessageObject.exchangeIndicator}' != ExcahngeOrExplValIdVar){
			
					hasExchOrExplChanged =true;
				
				} 
			if('${currentPageMode}'=="EDIT"
				&& !hasExchOrExplChanged 
				&& oOAMessageTextAreaVar == ('${maintainMessageObject.messageTextOne}'+'${maintainMessageObject.messageTextTwo}'+'${maintainMessageObject.messageTextThree}') 
				&& oOAMessageEffectiveDatePickerVar == onDateCheck('${maintainMessageObject.messageEffectiveDate.month}','${maintainMessageObject.messageEffectiveDate.date}','${maintainMessageObject.messageEffectiveDate.year}')
				&& oOAMessageExpiryDatePickerVar == onDateCheck('${maintainMessageObject.messageExpiryDate.month}','${maintainMessageObject.messageExpiryDate.date}','${maintainMessageObject.messageExpiryDate.year}')){
				noErrors = false;
				addErrorToNotificationTray("No modifications to SAVE");
				
			}
			
			
			if(!isValidDate(oOAMessageEffectiveDatePickerVar)){
				noErrors = false;
				validDate = false;
				addErrorToNotificationTray("Invalid Message Effective Date ");
			}
			
			if(!isValidDate(oOAMessageExpiryDatePickerVar)){
				noErrors = false;
				validDate = false;
				addErrorToNotificationTray("Invalid Message Expiry Date ");
			}
			if (null != document.getElementById("exemptCheckBox")
					&& document.getElementById("exemptCheckBox").checked) {
					
			}else if(null == document.getElementById("exemptCheckBox") && '${maintainMessageObject.exchangeIndicator}'=='Y'){
			
			}else{
		
				//var valid = new RegExp(/^[A-Za-z0-9\s\r\n]*$/);
				var valid=false;
				var validationMessage = "Comment text has extended special characters, which are copied directly from Microsoft Word document.\nExtended special characters are not supported by EBX application. Please correct and try again.";
				valid= validateChars(oOAMessageTextAreaVar);
				
				if(oOAMessageTextAreaVar==''){
					noErrors = false;
					validDate = false;
					addErrorToNotificationTray("Please Enter MessageText properly");
				}
				
				if(!valid){
                           noErrors = false;
                           validDate = false;
                           addErrorToNotificationTray(validationMessage);
                 }		
				
			} 
			
			var datePriorEqualCheck = datePriorAndEqualCheck(oOAMessageEffectiveDatePickerVar, oOAMessageExpiryDatePickerVar, validDate);
			if(!datePriorEqualCheck){
				noErrors = false;				
				addErrorToNotificationTray("Expiry Date cannot be same or lesser than Effective date");
			}
			
			
			if(noErrors){
			ooaMessageAuditCommentDialog(netWorkOrContractVar, netWorkOrContractCodeVar,
				ExcahngeOrExplValIdVar, currentPageMode, messageId, oOAMessageTextAreaVar,
				oOAMessageEffectiveDatePickerVar, oOAMessageExpiryDatePickerVar, hasExchOrExplChanged);
			 } else {
				 openTrayNotification();
			 }


		}
	
	function onCancelClick(search,searchCriteria,exchangeIndicator) {
	
			if(null==exchangeIndicator||(exchangeIndicator != 'ON' 
					&& exchangeIndicator != 'OF' 
					&& exchangeIndicator != 'BT') ){
				exchangeIndicator = "";
			}
			//Overriding as of now will be changing based on the requirement.
			exchangeIndicator = "";
			 $
				.ajax({
					url : "${context}/lockedvariableauditreport/searchOOAMessage.ajax",
					dataType : "html",
					type : "POST",
					data : "searchCriteria=" + searchCriteria
							+ "&search=" + search
							+ "&viewOrSearchFunction=searchFunction"
							+ "&excInd="+exchangeIndicator,
					success : function(data) {
						$("#ooaMessageReportDialog").dialog('close');
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

		}
		function returnMessageID() {
			if(null != '${maintainMessageObject}' 
					&& null != '${maintainMessageObject.messageId}'){
				
				return '${maintainMessageObject.messageId}';
				
			} else {return "";}
			
			
			
		}
		
		 
		function changeSystemIndicator2() {
			if (!(($("#systemIndicator1").is(':checked')) || ($("#systemIndicator2")
					.is(':checked')))) {
				alert("Please check at least one system indicator");
				$("#systemIndicator2").attr('checked', true);
			}
		}
		
		
		
		function onPreviewTextAjax(){	
			$.ajax({url: "${context}/lockedvariableauditreport/oOAMessagePreviewMethod.ajax",
				data: "completeMessage="+encodeURIComponent($("#oOAMessageTextAreaId").val())+"&splitVal=260",
				success: function(result){
					document.getElementById("oOAMessageTextPreviewAreaId").value = "";
					if(null != result.previewTextOne){
						document.getElementById("oOAMessageTextPreviewAreaId").value = result.previewTextOne;
					} 
					if(null != result.previewTextTwo){
						document.getElementById("oOAMessageTextPreviewAreaId").value = document.getElementById("oOAMessageTextPreviewAreaId").value +'\n \n'+ result.previewTextTwo;
					}
					if(null != result.previewTextThree){
						document.getElementById("oOAMessageTextPreviewAreaId").value = document.getElementById("oOAMessageTextPreviewAreaId").value +'\n \n'+ result.previewTextThree;
					}
					 
	        }});
			 
			}

		
		
			
		function onDateCheck(monthIN, dateIN, yearIN){
			
			
			var month = parseInt(monthIN, 10)+1;
			var date =  parseInt(dateIN, 10);
			var year =  parseInt(yearIN, 10)+1900;
			if(date<10){
				date = "0"+date;
			}
			if(month<10){
				month = "0"+month;
			}
			
			
			 return month+'/'+date+"/"+year;
			
			}
		
		
	</script>


	<form name="oOAMessageCreate" method="POST" );" >
		<div>
			<table border="0" cellspacing="0" width="100%">

				<tr height="40px">
					<td width ="300px"align="right" id="netWorkOrContractID" >${networkOrContractVar}</td>
					<td width="20px"></td>
					<td id="networkOrContractCodeID">${netWorkOrContractCode}</td>


				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
				</tr><tr height="40px">
				
				
					<td align="right" id="ExcahngeOrExplId">${exchangeOrExemptVariable}</td>
					<td width="20px"></td>
					<td align="left" id="ExcahngeOrExplValId" >${exchangeOrExemptValue}</td>

				
				</tr>
				<tr height="40px">
					<td align="right">Message Effective Date</td>
					<td></td>
					<td align="left" ><input type="text" class="inputbox65"
						id="oOAMessageEffectiveDatePicker"
						name="oOAMessageEffectiveDatePicker" style="margin-right: 6px">
						<br> <font face="Verdana" color="black"><span
							style="font-size: xx-small">(mm/dd/yyyy)</span></font></td>

				</tr>
				<tr>
					<td height="10px"></td>
					<td></td>
					<td width="20px"></td>
				</tr>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

				<tr height="40px">
					<td align="right">Message Expiry Date</td>
					<td width="20px"></td>
					<td align="left"><input type="text" class="inputbox65"
						id="oOAMessageExpiryDatePicker" name="oOAMessageExpiryDatePicker"
						style="margin-right: 6px"> <br> <font face="Verdana"
						color="black"><span style="font-size: xx-small">(mm/dd/yyyy)</span></font>
					</td>

				</tr>
				<tr>
					<td></td>
					<td width="20px"></td>
					<td></td>
				</tr>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<tr height="80px">
					<td align="right">Message Text</td>
					<td width="20px"></td>

					<td align="left"><textarea name="oOAMessageTextAreaId"
							id="oOAMessageTextAreaId" rows="5" cols="50" maxlength="780" onkeyup="textAreaAdjust(this)" style="overflow:hidden"></textarea></td>

				</tr>
				<tr height="10px">
					<td></td>
					<td width="20px"></td>
					<td></td>
				</tr>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

				<td align="right">Preview Message Text</td>
				<td></td>

				<td width="100px" align="left"><textarea
						name="oOAMessageTextPreviewAreaId" disabled = "disabled"
						id="oOAMessageTextPreviewAreaId" rows="5" cols="50"></textarea></td>

				<tr>

				</tr>
				<tr height="10px">
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr height="20px">
					<td colspan="3">
					
					</td>
				</tr>
				<tr height="40px">



					<td align="center"><a href="#" onclick="onPreviewTextAjax();"><img
							name="previewTexxt" id="previewTexxt" 
							src="${imageDir}/btn_Preview.JPG" width="100" height="20"/> </a></td>
					<td align="center"><a href="#" onclick="oOAMessageSave('${networkOrContractVar}','${netWorkOrContractCode}','${exchangeVal}', '${currentPageMode}', '${messageId}');" >
							<img src="${imageDir}/btn_Save.JPG" name="saveOOAid"
							id="saveOOAid" width="100" height="20" />
					</a></td>
					<td align="center" ><a  href="#" name="forCancel" id="forCancel" onclick="onCancelClick('${networkOrContractVar}','${netWorkOrContractCode}','${maintainMessageObject.exchangeIndicator}' );" >
							<img src="${imageDir}/btn_Cancel.JPG" 
							 width="100" height="20" />
					</a></td>

				</tr>
				
				<!--  tr height="40px">


	Newly Modified		<td align="center"><a href="#" onclick="generatePreview();"><img
							src="${imageDir}/PreviewNew.png" name="previewTexxtTemp"
							id="previewTexxtTemp" width="100" height="20" /></a></td>
					<td></td>
					<td align="center"><a href="#" onclick="oOAMessageSaveNew();"><img
							src="${imageDir}/save_but.gif" name="saveOOAidTemp"
							id="saveOOAidTemp" width="100" height="20" /></a></td>

				</tr-->

			</table>
		</div>

	</form>
	
	<div id="auditCommentDialog"  style="display:none">
				<input type="hidden" 
				name="networkOrContractVar" id="netWorkOrContractCode" value="" /> 
				<input type="hidden"
				name="netWorkOrContractCodeName" id="netWorkOrContractCodeName" value="" />
				<input type="hidden"
				name="netWorkOrContractCode" id="netWorkOrContractCode" value="" />
				<input type="hidden"
				name="exchangeVal" id="exchangeVal" value="" /> 
				<input type="hidden"
				name="currentPageMode" id="currentPageMode" value="" /> 
				<input type="hidden"
				name="messageId" id="messageId" value="" />
				<input type="hidden"
				name="oOAMessageTextAreaVar" id="oOAMessageTextAreaVar" value="" />
				<input type="hidden"
				name="oOAMessageEffectiveDatePickerVar" id="oOAMessageEffectiveDatePickerVar" value="" />
				<input type="hidden"
				name="oOAMessageExpiryDatePickerVar" id="oOAMessageExpiryDatePickerVar" value="" />
				<input type="hidden"
				name="hasExchOrExplChanged" id="hasExchOrExplChanged" value="" />
			<table id="userCommentsTable1" border="0" cellpadding="0"
				cellspacing="0" class="Pd3 mT5 bT">
				<tr class="">
					<td><textarea name="auditComments"
							id="auditComments" rows="5" cols="77" maxlength="100"></textarea></td>
				</tr>
			</table>
			<table border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td><a href="#" onclick="saveAuditComments();"><img
							id="oOAMessageSaveImg" src="${imageDir}/btn_Save.JPG" alt="Save"
							title="Save" /></a></td><td></td>
							<td><a  href="#" name="forCancel" id="auditCancel" onclick="cmtDailogClose();" >
							<img src="${imageDir}/btn_Cancel.JPG" alt="Cancel" title="Cancel" />
					</a></td>
				</tr>
			</table>
	</div>
</body>
</html>