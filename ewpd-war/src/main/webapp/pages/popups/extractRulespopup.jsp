<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import = "org.apache.commons.lang.StringEscapeUtils" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"
	title="Style">
<BASE target="_self" />
<%
String browser = request.getHeader("user-agent");
if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
%>
<script language="JavaScript" src="../../js/wpd.js"></script>
<%
}
else {
%>
<script language="JavaScript" src="../../js/browserCompatible.js"></script>
<script language="JavaScript" src="../../js/showModalDialog.js"></script>
<%
}
%>
<script language="JavaScript" src="../../js/tigra_tables.js"></script>
<!-- This tag gets the title name from the request -->
<title><c_rt:out value="StringEscapeUtils.escapeXml(${param.titleName})" /></title>
</HEAD>

<f:view>
<BODY>
<form id="submitContractForm" name="submitContractForm"  action="extractRulespopup.xlsx" method="post" target="targetIframe">
	<h:inputHidden id="entitySysId" value="#{param['entityId']}"></h:inputHidden>
	<h:inputHidden id="entityName" value="#{param['entityName']}"></h:inputHidden>
	<h:inputHidden id="pageFrom" value="#{param['pageFrom']}"></h:inputHidden>	
	<h:inputHidden id="extractAllRules" value = 'true'></h:inputHidden>
	<h:inputHidden id="extractExclusionRules" value = 'true'></h:inputHidden>
	<h:inputHidden id="extractDenialRules" value = 'true'></h:inputHidden>
	<h:inputHidden id="extractUMRules" value = 'true'></h:inputHidden>
	<h:inputHidden id="extractHeaderRules" value = 'true'></h:inputHidden>
</form>
		<div id="fullInfo" onkeypress="return submitOnEnterKey('selectButton');" onclick="closeSearchDivOnBodyClick();" >
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
			<tr>
				<td align="left">&nbsp;<h:commandButton id="selectButton"
					value="Extract Excel" styleClass="wpdbutton" onclick="invokeServlet();"
					></h:commandButton>
				</td>
			</tr>
		</table>
		</div>
		<BR>
<h:form styleClass="form" id="ruleExtractPopUpForm">


		<div>
		<table width="98%" align="right" cellpadding="0" cellspacing="0">
			<TBODY>				
				<tr>
					<TD>
					<div id="headerTable" style="height:0px; overflow:auto;">
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						bgcolor="#cccccc">
						<tr>
							<td width="5%" align="center" >
							<h:selectBooleanCheckbox id="checkId1" value='true'
								onclick=  "selectAll(this);">
								</h:selectBooleanCheckbox>
								</TD>								
							<TD width="65%" align="left" ><strong> <h:outputText value="Extract All Rules" styleClass="outputText"></h:outputText></strong></td>
						</tr>
						
					</table>
					</div>
					</TD>
				</tr>
				
				<tr>
					<TD> 
						<div id="searchResultdataTableDiv" class="popupDataTableDiv" style="height:300px;">
						<table width="100%" cellspacing="0" cellpadding="0" bgcolor="#cccccc">
						<tr class="dataTableEvenRow">
							<td width="5%" align="center" height="27" style="border-bottom:1px solid #cccccc; border-right:1px solid #cccccc;border-top:1px solid #cccccc;border-left:1px solid #cccccc;"><h:selectBooleanCheckbox id="Check1" 
								 value='true' onclick=  "isChecked(this,'exclusion');"></h:selectBooleanCheckbox></TD>
							<TD width="65%" align="left" height="27" style="border-bottom:1px solid #cccccc; border-right:1px solid #cccccc;border-top:1px solid #cccccc;"><h:outputText value="EXCLUSION RULES" styleClass="outputText"></h:outputText></td>
						</tr>
						<tr class="dataTableOddRow">
							<td width="5%" align="center" height="27" style="border-bottom:1px solid #cccccc; border-right:1px solid #cccccc;border-left:1px solid #cccccc;"><h:selectBooleanCheckbox id="Check2"
								value='true' onclick=  "isChecked(this,'denial');"></h:selectBooleanCheckbox></TD>
							<TD width="65%" align="left" height="27" style="border-bottom:1px solid #cccccc; border-right:1px solid #cccccc;"><h:outputText value="DENIAL RULES" styleClass="outputText"></h:outputText></td>
						</tr>
						<tr class="dataTableEvenRow">
							<td width="5%" align="center" height="27" style="border-bottom:1px solid #cccccc; border-right:1px solid #cccccc;border-left:1px solid #cccccc;"><h:selectBooleanCheckbox id="Check3"
								value='true' onclick=  "isChecked(this,'um');"></h:selectBooleanCheckbox></TD>
							<TD width="65%" align="left" height="27" style="border-bottom:1px solid #cccccc; border-right:1px solid #cccccc;"><h:outputText value="UM RULES" styleClass="outputText"></h:outputText></td>
						</tr>
						<tr class="dataTableOddRow">
							<td width="5%" align="center" height="27" style="border-bottom:1px solid #cccccc; border-right:1px solid #cccccc;border-left:1px solid #cccccc;"><h:selectBooleanCheckbox id="Check4"
								value='true' onclick=  "isChecked(this,'header');"></h:selectBooleanCheckbox></TD>
							<TD width="65%" align="left" height="27" style="border-bottom:1px solid #cccccc; border-right:1px solid #cccccc;"><h:outputText value="HEADER RULES" styleClass="outputText"></h:outputText></td>
						</tr>
						
						<!-- <tr>
							<td><h:outputText value="#{param['entityId']}"></h:outputText></TD>
							<td><h:outputText value="#{param['pageFrom']}"></h:outputText></TD>
						</tr> -->
					</table>
		</div>
		</TABLE>	
	</div>		
</h:form>

 <iframe width="100%" height="300" style="display:none;visibility:hidden" name="targetIframe" id="targetIframe" onreadystatechange ="checkIframStatus();">
	</iframe> 
	<div class="transparent" id="transparentDiv">
</div>  
<div class="transparent" id="transparentDiv1">
</div> 
<div class ="picDiv" id="loadingImageDiv">
	<table  width="100%" height="100%">
		<tr>
			<td align="center">
				<img src="../../images/loading.gif">  
			</td>
		
		</tr>
	</table>
</div>	
</BODY>
</f:view>

<script type="text/javascript">
var reportSubmitted = false;
enablePage();


// Check the status of the Iframe and enable screen by removing blocking div once
// the response is received.
function checkIframStatus() {
		iframe = document.getElementById('targetIframe');
		if(reportSubmitted) {
			if( iframe && iframe.document && (iframe.document.readyState == 'interactive' || iframe.document.readyState == 'complete')) {
				reportSubmitted = false;
				//alert("checkIframStatus");
				enablePage();
			}
		}
	}	
	


function invokeServlet() {
//var dateSegmentSysIdd = document.getElementById('contractReportForm:hiddenDateSegmentSysId').value;
//var pageFrom = document.getElementById('contractReportForm:pageFrom').value;			
disablePage();			
document.forms['submitContractForm'].submit();
reportSubmitted = true;	
}

//function checks all the check boxes when Extract All is checked
function selectAll(chk){
if (chk.checked) { 
	document.getElementById('extractAllRules').value='true';
    document.getElementById('ruleExtractPopUpForm:Check1').checked=true;
	document.getElementById('ruleExtractPopUpForm:Check2').checked=true;
	document.getElementById('ruleExtractPopUpForm:Check3').checked=true;
	document.getElementById('ruleExtractPopUpForm:Check4').checked=true;

	document.getElementById('extractExclusionRules').value='true';
	document.getElementById('extractDenialRules').value='true';
	document.getElementById('extractUMRules').value='true';
	document.getElementById('extractHeaderRules').value='true';
	
}else {
	document.getElementById('extractAllRules').value='false';
 	document.getElementById('ruleExtractPopUpForm:Check1').checked=false;
 	document.getElementById('extractExclusionRules').value='false';
	document.getElementById('ruleExtractPopUpForm:Check2').checked=false;
	document.getElementById('extractDenialRules').value='false';
	document.getElementById('ruleExtractPopUpForm:Check3').checked=false;
	document.getElementById('extractUMRules').value='false';
	document.getElementById('ruleExtractPopUpForm:Check4').checked=false;
	document.getElementById('extractHeaderRules').value='false';
}
}

function isChecked(chk,flag){

if(flag=='exclusion'){
	if (chk.checked){
		document.getElementById('ruleExtractPopUpForm:Check1').checked=true;
		document.getElementById('extractExclusionRules').value='true';
	}else{
		document.getElementById('ruleExtractPopUpForm:checkId1').checked=false;
		document.getElementById('extractAllRules').value='false';
		document.getElementById('ruleExtractPopUpForm:Check1').checked=false;
		document.getElementById('extractExclusionRules').value='false';
	}
}
if(flag=='denial'){
	if (chk.checked){
		document.getElementById('ruleExtractPopUpForm:Check2').checked=true;
		document.getElementById('extractDenialRules').value='true';
	}else{
		document.getElementById('ruleExtractPopUpForm:checkId1').checked=false;
		document.getElementById('extractAllRules').value='false';
		document.getElementById('ruleExtractPopUpForm:Check2').checked=false;
		document.getElementById('extractDenialRules').value='false';
	}
}
if(flag=='um'){
	if (chk.checked){
		document.getElementById('ruleExtractPopUpForm:Check3').checked=true;
		document.getElementById('extractUMRules').value='true';
	}else{
		document.getElementById('ruleExtractPopUpForm:checkId1').checked=false;
		document.getElementById('extractAllRules').value='false';
		document.getElementById('ruleExtractPopUpForm:Check3').checked=false;
		document.getElementById('extractUMRules').value='false';
	}
}
if(flag=='header'){
	if (chk.checked){
		document.getElementById('ruleExtractPopUpForm:Check4').checked=true;
		document.getElementById('extractHeaderRules').value='true';
	}else{
		document.getElementById('ruleExtractPopUpForm:checkId1').checked=false;
		document.getElementById('extractAllRules').value='false';
		document.getElementById('ruleExtractPopUpForm:Check4').checked=false;
		document.getElementById('extractHeaderRules').value='false';
	}
}

if(document.getElementById('extractExclusionRules').value =='true'&& 
	document.getElementById('extractDenialRules').value =='true' &&
	document.getElementById('extractUMRules').value == 'true' &&
	document.getElementById('extractHeaderRules').value =='true'){
	
	document.getElementById('ruleExtractPopUpForm:checkId1').checked=true;
	document.getElementById('extractAllRules').value='true';
	}



}

var searchOpen = 'F';

function closeSearchDivOnBodyClick() {
	var e = window.event;
	if(!e || e==undefined) {
		return;
}
var button_id = e.srcElement.name;
if(	button_id != 'divSearchButton') {
	if(searchOpen == 'T') {
		closeSearchDiv();
		}
	}
}

function closeSearchDiv() {
	document.getElementById('searchDiv').style.display = 'none';
	searchOpen = 'F';
}
</script>
</HTML>

	