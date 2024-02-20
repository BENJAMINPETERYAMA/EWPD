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
$("#effectiveDatePicker").datepicker({ showOn: 'button', buttonText: "Select", buttonImage: '${imageDir}/cal.gif', buttonImageOnly: true, changeYear: true, yearRange: '1900:2900' });
  });
  
// validation for version number field in contract mapping
 	function checkVersionForContractBxReport() {
 		$("#systemTypeForEbx").val($.trim($("#systemTypeForEbx").val()));
		$('#systemTypeForEbx').val($('#systemTypeForEbx').val().toUpperCase())
		var system = $("#systemTypeForEbx").val();
		if (system == 'EWPD') {
			document.getElementById('versionNumber').disabled = false;
			document.getElementById('versionSymbolBX').style.display = "inline";
			$('#versionTr').show();
			$('#enviornmentTr').hide();
		} else {
			document.getElementById('versionNumber').value = "";
			document.getElementById('versionNumber').disabled = true;
			document.getElementById('versionSymbolBX').style.display = "none";
			$('#versionTr').hide();
			$('#enviornmentTr').show();
	}
}
  
function getStartDatesAndVersionForBXReport(){
var system = $("#systemTypeForEbx").val();
$("#contractId").val($.trim($("#contractId").val()));
$('#contractId').val($('#contractId').val().toUpperCase());		
var environmentId = $("input[name='environmentIdForBXReport']:checked").val();
var contractId = $("#contractId").val();
		$.ajax({
			url: "${context}/vieworexportreport/populateStartDateAndVersionOfContract.ajax",
			dataType: "json",
			type: "POST",
			data: "systemName="+escape(system)+"&contractIDMapping="+escape(contractId)+"&environment="+escape(environmentId),
			success: function(data) {
			cache: false;
				if (data.systemForContract == 'EWPD') {
					$('#versionNumber').val(data.version);
				}
				$('#effectiveDatePicker').html(data.rows);
			}
		});
 }	  
  
function reportValidation(){
		var errorFlag = false;
		var contractIdvalue =$("#contractId").val(); 
		var systemTypeValue =$("#systemTypeForEbx").val();
		var effectiveDateValue = $("#effectiveDatePicker").val();
		var versionValue = $("#versionNumber").val();
		var environmentId = $("input[name='environmentIdForBXReport']:checked").val();
		if((contractIdvalue == null || contractIdvalue == "")&&(effectiveDateValue == null || effectiveDateValue == "")){ 
           
			addErrorToNotificationTray('Contract Id and Effective Date is(are) mandatory to view mapping');
			errorFlag = true;
		}
		else if(contractIdvalue == null || contractIdvalue == ""){ 
		
			addErrorToNotificationTray('Contract Id is mandatory to view mapping');
			errorFlag = true;
		}
		else if(effectiveDateValue == null || effectiveDateValue == ""){
		
		addErrorToNotificationTray('Effective Date is mandatory to view mapping');
			errorFlag = true;
		}
		else if(!isEmpty(effectiveDateValue)&& !isDate(effectiveDateValue)){
			addErrorToNotificationTray('Invalid Effective Date');
            errorFlag = true;
		}else if (systemTypeValue.toUpperCase() == 'EWPD') { 
		if (versionValue == null || versionValue == "" ) {
			addErrorToNotificationTray('Please enter the mandatory fields');
			errorFlag = true;	
		} else {
			if (!isNumeric(versionValue)) {
			addErrorToNotificationTray('Invalid  Version Number');
			errorFlag = true;
			}
		 }
		}
		if (errorFlag) {
		openTrayNotification();
		} else {
		 $.ajax({
				url: "${context}/vieworexportreport/invalidReportInputs.ajax",
				dataType: "json",
				type: "POST",			
				data: "contractId="+contractIdvalue+"&systemType="+systemTypeValue+"&effectiveDatePicker="+effectiveDateValue
				+"&environmentId="+environmentId+"&versionValue="+versionValue,
				success: function(data) {
				cache: false,
				viewMapping(data,contractIdvalue,systemTypeValue,effectiveDateValue,versionValue);
				}
			});	
		}	
} 
  
function viewMapping(data,contractIdvalue,systemTypeValue,effectiveDateValue,versionValue){
	var errorMessages = data.error_messages;
	if(typeof(errorMessages) != 'undefined' && errorMessages.length >0) {
	
			for(i=0; i< errorMessages.length; i++) {
				addErrorToNotificationTray(errorMessages[i]);
				
			}
			openTrayNotification();
		}
	else {
			$("#contractId").val(contractIdvalue);
			$("#systemTypeForEbx").val(systemTypeValue);
			$("#effectiveDatePicker").val(effectiveDateValue);
			$("#versionValue").val(versionValue);
			for (var i=0; i < document.reportLandingPage.reports.length; i++)
   			{
  				 if (document.reportLandingPage.reports[i].checked)
      				{
     				 var reportType = document.reportLandingPage.reports[i].value;
      				}
   			}
			if(reportType == "excel"){
				document.forms['reportLandingPage'].action="${context}/vieworexportreport/generateExcel.html";
				document.forms['reportLandingPage'].submit();
			}
			if(reportType == 'pdf'){
				document.forms['reportLandingPage'].action="${context}/vieworexportreport/generatePdf.html";
				document.forms['reportLandingPage'].submit();
			}
			if(reportType == 'text'){
				document.forms['reportLandingPage'].action="${context}/vieworexportreport/generateText.html";
				document.forms['reportLandingPage'].submit();
			}
	}
}

</script>


<div class="titleBar">
<div class="headerTitle">Reports</div>
</div>

<form name = "reportLandingPage" method="POST">
<div >
<!--<table border="0" cellspacing="0" width="100%">
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	
	<tr height="80px" >
		<td width="80px" >Contract ID<SPAN class="star">*</SPAN></td>
		<td width ="100px"><input type="textbox" class="inputbox60" name = "contractId" id="contractId" /></td>
		
		<td width="70px" align="center">System <SPAN class="star">*</SPAN></td>
		<td width ="80px">
			<select name="systemType" id="systemType" >
				<option value="LG">LG</option>
				<option value="ISG">ISG</option>
				<option value="EWPD">EWPD</option>
			</select>
		</td>
		<td width="100px">Effective Date<SPAN class="star">*</SPAN></td>
		<td width="200px">
				<input type="text" class="inputbox65" id="effectiveDatePicker"
					name="effectiveDatePicker" style="margin-right:6px">
				<br>
				<font face="Verdana" color="black"><span style="font-size: xx-small">(mm/dd/yyyy)</span></font>
		</td>
		 
	</tr>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<tr>
	<td colspan="2" align="center">
		<input type="radio" name = "reports" value="excel" width="30px" checked="checked">Excel
		<input type="radio" name="reports" value="pdf" width="30px">PDF
		<input type="radio" name="reports" value="text" width="30px">Text
	</td>
	<td></td>
	<td height="20" colspan="3" align="left" >
		<img src="${imageDir}/view_mappings.GIF" width="100" height="20" onClick="reportValidation();"/>
	</td>
	<td></td>
	<td></td>
	<td></td>	       
	</tr>
	
</table>
-->

<table id="" border="0" cellpadding="0"
	cellspacing="0" width="665">
		<tr class="createEditTable1-Listdata" align="left" >
			<td align="left" nowrap height="25" width="40%"><font size="+1">System *
			</font>
			<a href="#" onclick="openPopUp('System','The system where the contract is residing in.',event);"> what is this?</a></td>
			<td width ="20%">
			<select class="dropdown135" style=" font: 12px Arial, sans-serif;" name="systemTypeForEbx" id="systemTypeForEbx" onchange="checkVersionForContractBxReport();getStartDatesAndVersionForBXReport()"> 
				<option value=""></option>
				<option value="EWPD">EWPD</option>
				<option value="ISG">ISG</option>
				<option value="LG">LG</option>
				
				
			</select>
		</td>
		<td width="40%"></td>
		</tr>
		<tr class="createEditTable1-Listdata" align="left">
			<td align="left" nowrap  width="40%"><font size="+2">Contract
			ID *</font><a href="#" onclick="openPopUp('Contract ID','The contract ID',event);"> what is this?</a></td>
			<td width ="20%"><input type="text" class="inputbox65" style=" font: 12px Arial, sans-serif;" name = "contractId" id="contractId" onchange="getStartDatesAndVersionForBXReport()"/></td>
			<td width="40%"></td>
		</tr>
		<tr class="createEditTable1-Listdata" align="left">
			<td align="left"  width="40%"><font color="black">Effective
			Date *<a href="#" onclick="openPopUp('Start Date','The date segment start date.',event);"> what is this?</a></font></td>
			<td nowrap  align="left" colspan="1" width="20%">
				<select class="dropdown135" style=" font: 12px Arial, sans-serif;" id="effectiveDatePicker" name="effectiveDatePicker">
					<option value = ""> &#160</option>
				</select>
			</td>
			<td width="40%"></td>
		</tr>
		<tr class="createEditTable1-Listdata" align="left" id=versionTr>
			<td align="left" nowrap height="25" width="40%"><font size="+1">Version
			Number <span id="versionSymbolBX" style="display: none; width: 20px;">*</span></font><a href="#" onclick="openPopUp('Version Number','The version number of the contract if it is eWPD contract.',event);"><font size="+1"></font> what is this?</a>&#160;<a
				href="#"></a></td>
			<td nowrap height="25" colspan="1" align="left" width="20%"><input type="text"
				class="inputbox65" style=" font: 12px Arial, sans-serif;" id="versionNumber"
				name="versionNumber" disabled="disabled"></td>
				<td width="40%"></td>
		</tr>
		<tr class="createEditTable1-Listdata" id = enviornmentTr>
			<td align="left" height="27" width="40%">
				<font size="+1">Environment</font><a href="#" onclick="openPopUp('Environment','Specify the region to run the Contract BX report.',event);"> what is this?</a><br>
			</td>
			<td align="left" width="20%">
				<input type="radio" name="environmentIdForBXReport" id="environmentIdForBXReport" value="Production" onclick="getStartDatesAndVersionForBXReport()" checked>
				<font size="+1">Production</font>
			</td>
			<td align="left" width="40%">
				<input type="radio" name="environmentIdForBXReport" id="environmentIdForBXReport"  value="Test" onclick="getStartDatesAndVersionForBXReport()">
				<font size="+1">Test</font>
			</td>
		</tr>
		<tr> 
		<td height="20px">
		</td>
		</tr>
		<tr>
	<td colspan="3" align="center">
		<input type="radio" name = "reports" value="excel" width="30px" checked="checked">Excel
		<input type="radio" name="reports" value="pdf" width="30px">PDF
		<input type="radio" name="reports" value="text" width="30px">Text
	</td>
	</tr>
	
	<br>
	<tr> 
		<td height="20px">
		</td>
	</tr>
	<tr class="createEditTable1-Listdata" align="left">
	<td height="20" colspan="3" align="center" >
		<img src="${imageDir}/view_mappings.GIF" width="100" height="20" onClick="reportValidation();"/>
	</td>
	</tr>
</table>
</div>

</form>
</body>
</html>