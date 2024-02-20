<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

</head>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/simulationInputPage.js"></script>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="context" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">

 $(document).ready(function() {   
$("#startDatePicker").datepicker({ showOn: 'button', buttonText: "Select", buttonImage: '${imageDir}/cal.gif', buttonImageOnly: true, changeYear: true, yearRange: '1900:2900' });
  });
  
 $(document).ready(function() {   
$("#endDatePicker").datepicker({ showOn: 'button', buttonText: "Select", buttonImage: '${imageDir}/cal.gif', buttonImageOnly: true, changeYear: true, yearRange: '1900:2900' });
  });
 
  
  
function reportValidation(){
		var systemTypeValue =$("#systemType").val();
		var systemIndicatorValue = "";
		if($("#systemIndicator1").is(':checked')){
			systemIndicatorValue=systemIndicatorValue+"P";
		}
		if($("#systemIndicator2").is(':checked')){
			systemIndicatorValue=systemIndicatorValue+"T";
		}
		var startDateValue = $("#startDatePicker").val();
		var endDateValue = $("#endDatePicker").val();
		var startDate = new Date($('#startDatePicker').val()); 
		var endDate = new Date($('#endDatePicker').val()); 
		if((systemTypeValue == null || systemTypeValue == "")&&(systemIndicatorValue == null || systemIndicatorValue == "")){ 
           
			addErrorToNotificationTray('System and System Indicator is(are) mandatory to view report');
			openTrayNotification();
		}
		else if(systemTypeValue == null || systemTypeValue == ""){ 
		
			addErrorToNotificationTray('System is mandatory to view report');
			openTrayNotification();
		}
		else if (!(($("#systemIndicator1").is(':checked')) || ($("#systemIndicator2").is(':checked'))) && systemTypeValue !="EBX") {
		
		addErrorToNotificationTray('At least one among test or production should be selected for  viewing the report');
			openTrayNotification();
		}
		else if(!isEmpty(startDateValue)&& !isDate(startDateValue)){
			addErrorToNotificationTray('Invalid Start Date');
            openTrayNotification();
		}
		else if(!isEmpty(endDateValue)&& !isDate(endDateValue)){
			addErrorToNotificationTray('Invalid End Date');
            openTrayNotification();
		}
		else if (startDate > endDate){ 
			addErrorToNotificationTray('Start Date should not be greated than End Date');
            openTrayNotification();
		} 		
		else {
		
		 $.ajax({
				url: "${context}/lockedvariableauditreport/invalidReportInputs.ajax",
				dataType: "json",
				type: "POST",			
				data: "systemType="+systemTypeValue+"&systemIndicator="+systemIndicatorValue+"&startDatePicker="+startDateValue+"&endDatePicker="+endDateValue,
				success: function(data) {
				cache: false,
				viewReport(data,systemTypeValue,systemIndicatorValue,startDateValue,endDateValue);
				}
			});	
		}	
} 
  
function viewReport(data,systemTypeValue,systemIndicatorValue,startDateValue,endDateValue){
	var errorMessages = data.error_messages;
	if(typeof(errorMessages) != 'undefined' && errorMessages.length >0) {
	
			for(i=0; i< errorMessages.length; i++) {
				addErrorToNotificationTray(errorMessages[i]);
				
			}
			openTrayNotification();
		}
	else {
	
			$("#systemType").val(systemTypeValue);
			$("#systemIndicator").val(systemIndicatorValue);
			$("#startDatePicker").val(startDateValue);
			$("#endDatePicker").val(endDateValue);
			
			document.forms['reportLandingPage'].action="${context}/lockedvariableauditreport/generateLockedVarialbAuditReport.html";
			document.forms['reportLandingPage'].submit();
						
	}
	
}
function changeSystemIndicator1(){
	if (!(($("#systemIndicator1").is(':checked')) || ($("#systemIndicator2").is(':checked')))) {	
		alert("Please check at least one system indicator");
		$("#systemIndicator1").attr('checked',true);	 
	}
			
	}
function changeSystemIndicator2(){
	if (!(($("#systemIndicator1").is(':checked')) || ($("#systemIndicator2").is(':checked')))) {
		alert("Please check at least one system indicator"); 
		$("#systemIndicator2").attr('checked',true);	
	}		
	}	
function changeSystem(){
	var systemTypeValue =$("#systemType").val();
	if(systemTypeValue == "EBX"){
		$("#systemIndicator1").attr('checked',false);
		$("#systemIndicator2").attr('checked',false);
		$("#systemIndicator1").attr('disabled',true);
		$("#systemIndicator2").attr('disabled',true);
	}else{
		$("#systemIndicator1").attr('checked',true);
		$("#systemIndicator2").attr('checked',true);
		$("#systemIndicator1").attr('disabled',false);
		$("#systemIndicator2").attr('disabled',false);
	
	}
}	
</script>


<form name = "reportLandingPage" method="POST">
<div >
<table border="0" cellspacing="0" width="100%">
		
	<tr height="40px" >
		<td width="100px" >System </td>
		<td width ="80px">
			<select name="systemType" id="systemType" onchange="changeSystem();">
				<option value="LG">LG</option>
				<option value="ISG">ISG</option>
				<option value="EBX">EBX</option>
			</select>
		</td>		
		<td width="70px" align="center"></td>
		<td width ="80px"></td>
		<td width="100px"></td>
		<td width="200px"></td>
		
		</tr>
		
		<tr height="40px" >
		<td width="100px" >Start Date</td>
		<td width ="200px">
				<input type="text" class="inputbox65" id="startDatePicker"
					name="startDatePicker" style="margin-right:6px">
				<br>
				<font face="Verdana" color="black"><span style="font-size: xx-small">(mm/dd/yyyy)</span></font>
		</td>		
		<td width="70px" align="center"></td>
		<td width ="100px"><input type="checkbox" name="systemIndicator" id="systemIndicator1" value="P" > Production</td>
		<td width="80px"></td>
		<td width="100px"></td>		 
	</tr>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	
	<tr height="40px" >
		<td width="100px" >End Date</td>
		<td width ="200px">
				<input type="text" class="inputbox65" id="endDatePicker"
					name="endDatePicker" style="margin-right:6px">
				<br>
				<font face="Verdana" color="black"><span style="font-size: xx-small">(mm/dd/yyyy)</span></font>
		</td>		
		<td width="70px" align="center"></td>
		<td width ="80px"><input type="checkbox" name="systemIndicator" id="systemIndicator2" value="T"> Test</td>
		<td width="80px"></td>
		<td width="100px"></td>		 
	</tr>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	
	<tr height="40px" >
	<td colspan="7" align="center"></td></tr>
	
	<tr>
	<td colspan="7" align="center">
		<img src="${imageDir}/ViewAuditReport.jpg" width="100" height="20" onClick="reportValidation();"/>
	</td>
		       
	</tr>
	
</table>
</div>

</form>
</body>
</html>