<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

</head>
<body>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/simulationInputPage.js"></script>
	<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
	<c:set var="context" value="${pageContext.request.contextPath}" />
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
												
							
							document
									.getElementById("oOAMessageEffectiveDatePicker").value = '${addSegmentEffectiveDate}';
							document
									.getElementById("oOAMessageExpiryDatePicker").value = '${addSegmentExpiryDate}';
							
							document.getElementById("oOAMessageExpiryDatePicker").disabled  = true;

							$("#oOAMessageEffectiveDatePicker").datepicker({
								showOn : 'button',
								buttonText : "Select",
								buttonImage : '${imageDir}/cal.gif',
								buttonImageOnly : true,
								changeYear : true,
								yearRange : '1900:9999'
							});							
							
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
		
		function cmtDailogClose(){
		 $("div[id='userCommentsAddDateSegmentDialog']").parent('.ui-dialog').remove();
		}
		
		function textAreaAdjust(o) {
 		 	o.style.height = "1px";
  			o.style.height = (50+o.scrollHeight)+"px";
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
		function onPreviewTextAjax() {
			$
					.ajax({
						url : "${context}/lockedvariableauditreport/oOAMessagePreviewMethod.ajax",
						data : "completeMessage="
								+ encodeURIComponent($("#oOAMessageTextAreaId").val())
								+ "&splitVal=260",
						success : function(result) {
							document
									.getElementById("oOAMessageTextPreviewAreaId").value = "";
							if (null != result.previewTextOne) {
								document
										.getElementById("oOAMessageTextPreviewAreaId").value = result.previewTextOne;
							}
							if (null != result.previewTextTwo) {
								document
										.getElementById("oOAMessageTextPreviewAreaId").value = document
										.getElementById("oOAMessageTextPreviewAreaId").value
										+ '\n \n' + result.previewTextTwo;
							}
							if (null != result.previewTextThree) {
								document
										.getElementById("oOAMessageTextPreviewAreaId").value = document
										.getElementById("oOAMessageTextPreviewAreaId").value
										+ '\n \n' + result.previewTextThree;
							}

						}
					});

		}

		
		function openUserCommentsAddDateSegment(networkOrContractVar,
				netWorkOrContractCode, exchangeVal, messageId,
				oOAMessageTextAreaVar,oOAMessageEffectiveDatePickerVar, 
				oOAMessageExpiryDatePickerVar) {
				
				$(".ui-dialog>div[id='userCommentsAddDateSegmentDialog']").show();

				$("#addDateSegNetWorkOrContractCodeName").val(networkOrContractVar);
				$("#addDateSegNetWorkOrContractCode").val(netWorkOrContractCode);
				$("#addDateSegExchangeVal").val(exchangeVal);
				$("#addDateSegMessageId").val(messageId);
				$("#addDateSegOOAMessageTextAreaVar").val(oOAMessageTextAreaVar);
				$("#addDateSegOOAMessageEffectiveDatePickerVar").val(oOAMessageEffectiveDatePickerVar);
				$("#addDateSegOOAMessageExpiryDatePickerVar").val(oOAMessageExpiryDatePickerVar);
				
				$("#userCommentsAddDateSegmentDialog table#userCommentsTable1")
						.css("border-top", "1px solid black");
				$("#userCommentsAddDateSegmentDialog textarea").text('');
				
	        $("div[id='userCommentsAddDateSegmentDialog']").parent('.ui-dialog').remove();
				$("#userCommentsAddDateSegmentDialog").dialog({
					 height : 'auto',
					 width : '455px', 
					 title : 'User Comment Dialog',
					 modal : true
				});
				//$("#userCommentsAddDateSegmentDialog").dialog();
				
			}

		function onDateCheck(dateIN, monthIN, yearIN) {

			var month = parseInt(dateIN, 10) + 1;
			var date = parseInt(monthIN, 10);
			var year = parseInt(yearIN, 10) + 1900;
			if (date < 10) {
				date = "0" + date;
			}
			if (month < 10) {
				month = "0" + month;
			}

			return month + '/' + date + "/" + year;

		}
		
		function saveUserCommentsForAddDateSegment(){
		
		 	var networkOrContractName= $(".ui-dialog>div[id='userCommentsAddDateSegmentDialog'] #addDateSegNetWorkOrContractCodeName").val();
			var networkOrContractCode = $(".ui-dialog>div[id='userCommentsAddDateSegmentDialog'] #addDateSegNetWorkOrContractCode").val();
			var exchangeVal = $(".ui-dialog>div[id='userCommentsAddDateSegmentDialog'] #addDateSegExchangeVal").val();
			var messageId = $(".ui-dialog>div[id='userCommentsAddDateSegmentDialog'] #addDateSegMessageId").val();
			var oOAMessageTextAreaVar = $(".ui-dialog>div[id='userCommentsAddDateSegmentDialog'] #addDateSegOOAMessageTextAreaVar").val();
			var oOAMessageEffectiveDatePickerVar = $(".ui-dialog>div[id='userCommentsAddDateSegmentDialog'] #addDateSegOOAMessageEffectiveDatePickerVar").val();
			var oOAMessageExpiryDatePickerVar = $(".ui-dialog>div[id='userCommentsAddDateSegmentDialog'] #addDateSegOOAMessageExpiryDatePickerVar").val();
			var userComment =  $(".ui-dialog>div[id='userCommentsAddDateSegmentDialog'] #addDateSegmentComments").val(); 
			
			if(userComment==null){
			userComment="";
			}
			$
					.ajax({
						url : "${context}/lockedvariableauditreport/oOAMessageADDDateSegmentSave.ajax",
						dataType : "html",
						type : "POST",
						data : "oOAMessageTextAreaName="
								+ encodeURIComponent(oOAMessageTextAreaVar)
								+ "&netWorkOrContractName="
								+ networkOrContractName
								+ "&netWorkOrContractCodeName="
								+ networkOrContractCode
								+ "&ExcahngeOrExplValIdName="
								+ exchangeVal
								+ "&oOAMessageEffectiveDatePickerName="
								+ oOAMessageEffectiveDatePickerVar
								+ "&oOAMessageExpiryDatePickerName="
								+ oOAMessageExpiryDatePickerVar
								+ "&oldMessageId="
								+ messageId 
								+"&userComment="
								+encodeURIComponent(userComment),

						success : function(data) {
						
							var newData = JSON.parse(data);
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
										$("div[id='userCommentsAddDateSegmentDialog']").parent('.ui-dialog').remove();
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
										addInfoToNotificationTray(networkOrContractName+" "+networkOrContractCode+" Messages Saved Successfully");
										openTrayNotification();							
										$("messageDialog").dialog('close');
							
						}
					});
			
			}

		function oOAAddDateSegment(netWorkOrContractVar,
				netWorkOrContractCodeVar, exchangeVal, messageId) {
			
			var noErrors = true;
			var validDate = true;
			
			var oOAMessageTextAreaVar = $("#oOAMessageTextAreaId").val();
			var ExcahngeOrExplValIdVar = exchangeVal;
			
			var oOAMessageEffectiveDatePickerVar = $(
					"#oOAMessageEffectiveDatePicker").val();
			var oOAMessageExpiryDatePickerVar = $("#oOAMessageExpiryDatePicker")
					.val();
			
			if (null != document.getElementById("exemptCheckBox")
					&& document.getElementById("exemptCheckBox").checked) {
				ExcahngeOrExplValIdVar = "Y";
			} else if (null != document.getElementById("exemptCheckBox")
					&& !document.getElementById("exemptCheckBox").checked) {
				ExcahngeOrExplValIdVar = "N";
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
					
			}else{
			if(oOAMessageTextAreaVar==''){
				noErrors = false;
				validDate = false;
				addErrorToNotificationTray("Please Enter MessageText");
			}
			}
			var datePriorEqualCheck = datePriorAndEqualCheck(oOAMessageEffectiveDatePickerVar, oOAMessageExpiryDatePickerVar, validDate);
			if(!datePriorEqualCheck){
				noErrors = false;				
				addErrorToNotificationTray("Effective Date cannot be same or lesser than Expiry date");
			}
			
			var isdatePrior = datePriorCheck('${addSegmentEffectiveDate}',oOAMessageEffectiveDatePickerVar,validDate);
			if(!isdatePrior){
				noErrors = false;				
				addErrorToNotificationTray("New Effective date Should GREATER than after Old Effective date = ${addSegmentEffectiveDate}");
			}
			if(noErrors){ 
	        openUserCommentsAddDateSegment(netWorkOrContractVar, netWorkOrContractCodeVar,
				ExcahngeOrExplValIdVar, messageId,oOAMessageTextAreaVar,
				oOAMessageEffectiveDatePickerVar, oOAMessageExpiryDatePickerVar);
	        
				} else {
					 openTrayNotification();
				}
		}
		
function datePriorCheck(oOAMessageOldEffDatePickerVar, oOAMessageNewEffDatePickerVar, validDate){
			
			if(validDate){
			var oldEffDateSplit = oOAMessageOldEffDatePickerVar.split("/");
			var newEffDateSplit = oOAMessageNewEffDatePickerVar.split("/");
			
			
			if(parseInt(oldEffDateSplit[2]) >= parseInt(newEffDateSplit[2])){
				if (parseInt(oldEffDateSplit[2]) == parseInt(newEffDateSplit[2])){
					
				      if(parseInt(oldEffDateSplit[0]) > parseInt(newEffDateSplit[0])){
				    	  return false;
				      } else if (parseInt(oldEffDateSplit[0]) == parseInt(newEffDateSplit[0])) {
				    	  
				    	  if((parseInt(oldEffDateSplit[1])) >= parseInt(newEffDateSplit[1])){
				    		  
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
		
	</script>


	<form name="oOAMessageCreate" method="POST">
		<div>
			<table border="0" cellspacing="0" width="100%">

				<tr height="40px">
					<td width="300px" align="right" id="netWorkOrContractID">${netWorkOrContract}</td>
					<td width="20px"></td>
					<td id="networkOrContractCodeID">${netWorkOrContractCode}</td>


				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr height="40px">


					<td align="right" id="ExcahngeOrExplId">${ExcahngeOrExpl}</td>
					<td width="20px"></td>

					<td align="left" id="ExcahngeOrExplValId">${ExcahngeOrExplVal}</td>


				</tr>
				<tr height="40px">
					<td align="right">Message Effective Date</td>
					<td></td>
					<td align="left"><input type="text" class="inputbox65"
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
							id="oOAMessageTextAreaId" rows="5" cols="50" maxlength="780"  onkeyup="textAreaAdjust(this)" style="overflow:hidden"></textarea></td>

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
						name="oOAMessageTextPreviewAreaId" disabled="disabled"
						id="oOAMessageTextPreviewAreaId" rows="5" cols="50"></textarea></td>

				<tr>

				</tr>
				<tr height="10px">
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr height="20px">
					<td colspan="3"></td>
				</tr>
				<tr height="40px">



					<td align="center"><a href="#" onclick="onPreviewTextAjax();"><img
							name="previewTexxt" id="previewTexxt"
							src="${imageDir}/btn_Preview.JPG" width="100" height="20"
							/></a></td>
					<td align="center"><a href="#"
						onclick="oOAAddDateSegment('${networkOrContract}','${netWorkOrContractCode}','${ExcahngeVal}','${MessageId}');">
							<img src="${imageDir}/btn_Save.JPG" name="saveOOAid"
							id="saveOOAid" width="100" height="20" />
					</a></td>
					<td align="center" ><a  href="#" name="forCancel" id="forCancel" onclick="onCancelClick('${networkOrContract}','${netWorkOrContractCode}','${ExcahngeVal}' );" >
							<img src="${imageDir}/btn_Cancel.JPG" 
							 width="100" height="20" />
					</a></td>

				</tr>
				
			</table>
		</div>
		<div>
		
		</div>
		
		
		<div id="userCommentsAddDateSegmentDialog" style="display:none">
			<input type="hidden" name="networkOrContractVar"
				id="addDateSegNetWorkOrContractCodeName" value="" /> 
				<input type="hidden"
				name="addDateSegNetWorkOrContractCodeName" id="addDateSegNetWorkOrContractCodeName" value="" />
				<input type="hidden"
				name="addDateSegNetWorkOrContractCode" id="addDateSegNetWorkOrContractCode" value="" />
				<input type="hidden"
				name="addDateSegExchangeVal" id="addDateSegExchangeVal" value="" /> 
				<input type="hidden"
				name="addDateSegMessageId" id="addDateSegMessageId" value="" />
				<input type="hidden"
				name="addDateSegOOAMessageTextAreaVar" id="addDateSegOOAMessageTextAreaVar" value="" />
				<input type="hidden"
				name="addDateSegOOAMessageEffectiveDatePickerVar" id="addDateSegOOAMessageEffectiveDatePickerVar" value="" />
				<input type="hidden"
				name="addDateSegOOAMessageExpiryDatePickerVar" id="addDateSegOOAMessageExpiryDatePickerVar" value="" />
				<table id="sendToTestUserCommentsTable1" border="0" cellpadding="0"
				cellspacing="0" class="Pd3 mT5 bT">
				<tr class="">
					<td><textarea name="addDateSegmentComments"
							id="addDateSegmentComments" rows="5" maxlength="100" cols="77"></textarea></td>
				</tr>
			</table>
			<table border="0" align="center" cellpadding="2" cellspacing="0">
				<tr>
					<td align="center"><a href="#" onclick="saveUserCommentsForAddDateSegment();"><img
							id="sendToTestSaveImg" src="${imageDir}/btn_Save.JPG" alt="Save"
							title="Save" /></a></td><td></td>
							<td align="center"><a  href="#" name="addDateCancel" id="addDateCancel" onclick="cmtDailogClose();" >
							<img src="${imageDir}/btn_Cancel.JPG" alt="Cancel" title="Cancel" />
					</a></td>
							
				</tr>
			</table>
			</div>
		
	</div>
		

	</form>
</body>
</html>