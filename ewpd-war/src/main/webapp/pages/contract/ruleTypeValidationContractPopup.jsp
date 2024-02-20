<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"
	title="Style">
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
<base target=_self>
<TITLE>Rule Type Validation</TITLE>
</HEAD>
<f:view>
	<BODY>
	<h:form styleClass="form" id="formName">

		<h:inputHidden id ="loadRuleTypes" value="#{ruleValidationBackingBean.loadRuleTypes}"></h:inputHidden>
		<table width='100%'>
			<tr>
				<td align="right"><a href="#"> <img src="../../images/print.gif"
					alt="Print" width="19" height="14" border="0"
					onclick="printSelection();return false;" /> </a></td>
			</tr>
		</table>
		<TABLE>
			<TBODY>
				<tr>
					<TD><w:message></w:message></TD>
				</tr>
			</TBODY>
		</TABLE>
		<table id="questionHeaderTable" width="98%" cellpadding="1"
			cellspacing="1" border="0" align="center">
			<tr>
				<td width="15%"><h:outputText value="Contract Name " /></td>
				<td width="27" align="right"><h:outputText value=":" /></td>
				<td align="left" width="616"><h:outputText
					value="#{ruleValidationBackingBean.entityName}"
					id="contractName" /></td>
				<td width="18%"><SPAN id="timeID">time</SPAN></td>
			</tr>
			<tr>
				<td width="15%"><h:outputText value="Version" /></td>
				<td width="27" align="right"><h:outputText value=":" /></td>
				<td width="616"><h:outputText
					value="#{ruleValidationBackingBean.version}" id="versionNumber" />
				</td>
				<td width="177"></td>
			</tr>
			
		</table>

		<br>
	
	


		<DIV id="resultHeaderDiv" >
		<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
			id="headerTable" border="0" width="100%">
			<TR>
				<TD><strong><h:outputText value="Benefit and Benefit Component Rules"></h:outputText></strong></TD>
			</TR>
		</TABLE>
		<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
			id="resultHeaderTable" border="0" width="100%">
			<TBODY>
				<TR class="dataTableColumnHeader">
					<TD align="left"><h:outputText value="Date Segment"></h:outputText></TD>
					<TD align="left"><h:outputText value="Product"></h:outputText></TD>
					<TD align="left"><h:outputText value="Benefit Component"></h:outputText></TD>
					<TD align="left"><h:outputText value="Benefit"></h:outputText></TD>
					<TD align="left"><h:outputText value="Rule Id"></h:outputText></TD>
					<TD align="left"><h:outputText value="Description"></h:outputText></TD>
					<TD align="left"><h:outputText value="Rule Type"></h:outputText></TD>
				</TR>
			</TBODY>
		</TABLE>
		</DIV>



		<DIV id="searchResultdataTableDiv"
			style="height:650px;position:relative;z-index:1;font-size:10px;overflow:auto;">
		<!-- Search Result Data Table --> <h:dataTable styleClass="outputText"
			headerClass="dataTableHeader" id="searchResultTable"
			var="singleValue" cellpadding="3" width="100%" cellspacing="1"
			bgcolor="#cccccc"
			rendered="#{ruleValidationBackingBean.ruleListForDisplay != null}"
			value="#{ruleValidationBackingBean.ruleListForDisplay}"
			rowClasses="dataTableEvenRow" border="0">

			<h:column>
				<h:outputText id="dateSegment" value="#{singleValue.dateSegment}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="product" value="#{singleValue.productName}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="benefitComponent" value="#{singleValue.benefitComponentName}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="benefit" value="#{singleValue.benefitName}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="ruleId"
					value="#{singleValue.ruleId}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="ruleDesc"
					value="#{singleValue.ruleDesc}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="ruleType"
					value="#{singleValue.ruleTypeName}"></h:outputText>
			</h:column>
		</h:dataTable></DIV>
		<DIV id="dummyDiv" style="visibility:hidden"></DIV>
	<h:inputHidden id="dummyHidden" ></h:inputHidden>
	<h:inputHidden id ="timezone" value="#{requestScope.timezone}"></h:inputHidden>

		<!-- Space for hidden fields -->
	</h:form>
	</BODY>
</f:view>
<script>
var tableObject = document.getElementById('formName:searchResultTable');
var tablesize = 0;
if(null != tableObject){
tablesize = tableObject.rows.length;
}
for(var i=0;i<tablesize;i++)
{
	var rowobj = tableObject.rows[i];
	var cellobj = tableObject.rows[i].cells[0];
	var text = cellobj.children[0].innerHTML;

if(text ==''|| text == null){
	rowobj.className = 'dataTableOddRow';
}
}
if(tablesize == 0)
{
	document.getElementById('resultHeaderDiv').style.display = 'none';
	document.getElementById('searchResultdataTableDiv').style.display = 'none';
}
else{
	//var relTblWidth = document.getElementById('resultHeaderTable').offsetWidth;

	setColumnWidth('formName:searchResultTable','12%:16%:16%:19%:8%:13%:13%');
	setColumnWidth('resultHeaderTable','12%:16%:16%:19%:8%:13%:13%');
	syncTables('resultHeaderTable','formName:searchResultTable');
	syncTables('headerTable','formName:searchResultTable');
}

var Stamp = new Date();
var year = Stamp.getYear();
var month = (Stamp.getMonth() + 1);
if(month < 10){
month = "0"+ month;
}
var day = Stamp.getDate();
if(day < 10){
day = "0"+day;
}
var Hours;
var Mins;
var Time;
Hours = Stamp.getHours();
if(Hours <10){
Hours = "0"+ Hours;
}
if (Hours >= 12) {
Time = " P.M.";
}
else {
Time = " A.M.";
}

Mins = Stamp.getMinutes();
if (Mins < 10) {
Mins = "0" + Mins;
}	
seconds =Stamp.getSeconds()
if (seconds < 10) {
seconds = "0" + seconds;
}
//var totaltime= ""+day+'/'+month+'/'+year+"        "+Hours+':'+Mins+Time;
var totaltime=""+month+'/'+day+'/'+year+"        "+Hours+':'+Mins+':'+seconds;
document.getElementById('timeID').innerHTML=totaltime;

	function printPage(){
		window.print();
	}

</script>

<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="ruleTypeContractValidation" /></form>
</HTML>