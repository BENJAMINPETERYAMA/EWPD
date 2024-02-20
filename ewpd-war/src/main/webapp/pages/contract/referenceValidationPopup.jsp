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
<TITLE>Reference Validation Popup</TITLE>
</HEAD>
<f:view>
	<BODY>
	<h:form styleClass="form" id="formName">
		<h:inputHidden id="printproductBenefitDefinition"
			value="#{contractBasicInfoBackingBean.loadContractDuplicateReference}"></h:inputHidden>

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
					value="#{contractBasicInfoBackingBean.contractId}"
					id="contractName" /></td>
				<td width="18%"><SPAN id="timeID">time</SPAN></td>
			</tr>
			<tr>
				<td width="15%"><h:outputText value="Version" /></td>
				<td width="27" align="right"><h:outputText value=":" /></td>
				<td width="616"><h:outputText
					value="#{contractBasicInfoBackingBean.version}" id="versionNumber" />
				</td>
				<td width="177"></td>
			</tr>
			
		</table>




		<DIV id="resultHeaderDiv">
		<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
			id="headerTable" border="0" width="100%">
			<TR>
				<TD><strong><h:outputText
					value="Benefit Lines with duplicate References"></h:outputText></strong></TD>
			</TR>
		</TABLE>
		<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
			id="resultHeaderTable" border="0" width="100%">
			<TBODY>
				<TR class="dataTableColumnHeader">
					<TD align="left"><h:outputText value="Date Segment "></h:outputText></TD>
					<TD align="left"><h:outputText value="Product "></h:outputText></TD>
					<TD align="left"><h:outputText value="Benefit Component "></h:outputText></TD>
					<TD align="left"><h:outputText value="Benefit "></h:outputText></TD>
					<TD align="left"><h:outputText value="Reference "></h:outputText></TD>
					<TD align="left"><h:outputText value="Line "></h:outputText></TD>
					<TD align="left"><h:outputText value="PVA "></h:outputText></TD>
					<TD align="left"><h:outputText value="Tier "></h:outputText></TD>
				</TR>
			</TBODY>
		</TABLE>
		</DIV>



		<DIV id="searchResultdataTableDiv"
			style="height:200px;position:relative;z-index:1;font-size:10px;overflow:auto;">
		<!-- Search Result Data Table --> <h:dataTable styleClass="outputText"
			headerClass="dataTableHeader" id="searchResultTable"
			var="singleValue" cellpadding="3" width="100%" cellspacing="1"
			bgcolor="#cccccc"
			rendered="#{contractBasicInfoBackingBean.duplicateBenefitLevelReferenceList != null}"
			value="#{contractBasicInfoBackingBean.duplicateBenefitLevelReferenceList}"
			rowClasses="dataTableEvenRow" border="0">

			<h:column>
				<h:outputText id="dateSegmentId" value="#{singleValue.dateRange}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="productName"
					value="#{singleValue.productName}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="benefitComponentName"
					value="#{singleValue.benefitComponentName}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="benefitSysName"
					value="#{singleValue.benefitSysName}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="referenceDesc"
					value="#{singleValue.referenceDesc}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="benefitLevelDesc"
					value="#{singleValue.benefitLevelDesc}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="benefitLinePva"
					value="#{singleValue.benefitLinePva}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="tierDesc"
					value="#{singleValue.tierDesc}"></h:outputText>
			</h:column>
		</h:dataTable></DIV>
		<DIV id="resultHeaderQuestDiv">
		

				<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
					id="headerQuestTable" border="0">
					<TR>
						<TD width="1251"><strong><h:outputText
							value="Questions with duplicate Reference"></h:outputText></strong></TD>
					</TR>
				</TABLE>
				<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
					id="resultQuestHeaderTable" border="0" width="100%">
					<TBODY>
						<TR class="dataTableColumnHeader">
							<TD align="left""><h:outputText value="Date Segment "></h:outputText></TD>
							<TD align="left"><h:outputText value="Product "></h:outputText></TD>
							<TD align="left"><h:outputText value="Benefit Component "></h:outputText></TD>
							<TD align="left"><h:outputText value="Benefit "></h:outputText></TD>
							<TD align="left"><h:outputText value="Reference "></h:outputText></TD>
							<TD align="left"><h:outputText value="Admin Option "></h:outputText></TD>
							<TD align="left"><h:outputText value="Question"></h:outputText></TD>
							<TD align="left"><h:outputText value="Tier"></h:outputText></TD>
						</TR>
					</TBODY>
				</TABLE>

				
			<TR>
				<td colspan="4">
				<DIV id="searchResultdataTableQuestDiv"
					style="height:200px;position:relative;z-index:1;font-size:10px;overflow:auto;">
				<!-- Search Result Data Table --> <h:dataTable
					styleClass="outputText" headerClass="dataTableHeader"
					id="searchResultQuestTable" var="singleValue" cellpadding="3"
					width="100%" cellspacing="1" bgcolor="#cccccc"
					rendered="#{contractBasicInfoBackingBean.duplicateQuestionReferenceList != null}"
					value="#{contractBasicInfoBackingBean.duplicateQuestionReferenceList}"
					rowClasses="dataTableEvenRow" border="0">

					<h:column>
						<h:outputText id="dateSegmentIdQuest"
							value="#{singleValue.dateRange}"></h:outputText>
					</h:column>
					<h:column>
					<h:outputText id="productNameQuest"
						value="#{singleValue.productName}"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText id="benefitComponentNameQuest"
							value="#{singleValue.benefitComponentName}"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText id="benefitSysNameQuest"
							value="#{singleValue.benefitSysName}"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText id="referenceDescQuest"
							value="#{singleValue.referenceDesc}"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText id="adminOptionDescQuest"
							value="#{singleValue.adminDesc}"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText id="questionDescQuest"
							value="#{singleValue.questionDesc}"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText id="tierDescQuest"
							value="#{singleValue.tierDesc}"></h:outputText>
					</h:column>
				</h:dataTable></DIV>
				
		</DIV>
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
	var cellobj = tableObject.rows[i].cells[3];
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
	var relTblWidth = document.getElementById('resultHeaderTable').offsetWidth;
	setColumnWidth('formName:searchResultTable','10%:11%:11%:10%:18%:17%:8%:15%');
	setColumnWidth('resultHeaderTable','10%:11%:11%:10%:18%:17%:8%:15%');
	syncTables('resultHeaderTable','formName:searchResultTable');
	
	
}

var tableObjectQuest = document.getElementById('formName:searchResultQuestTable');
var tablesizeQuest=0;
if(null != tableObjectQuest){
tablesizeQuest = tableObjectQuest.rows.length;
}
for(var i=0;i<tablesizeQuest;i++)
{
	var rowobjQuest = tableObjectQuest.rows[i];
	var cellobjQuest = tableObjectQuest.rows[i].cells[3];
	var textQuest = cellobjQuest.children[0].innerHTML;
if(textQuest ==''|| textQuest == null){
rowobjQuest.className = 'dataTableOddRow';
}
}
if(tablesizeQuest == 0)
{
document.getElementById('resultHeaderQuestDiv').style.display = 'none';
document.getElementById('searchResultdataTableQuestDiv').style.display = 'none';
}
else{
	var relTblWidth = document.getElementById('resultQuestHeaderTable').offsetWidth;
	setColumnWidth('formName:searchResultQuestTable','10%:11%:11%:10%:18%:17%:8%:15%');
	setColumnWidth('resultQuestHeaderTable','10%:11%:11%:10%:18%:17%:8%:15%');
	syncTables('resultQuestHeaderTable','formName:searchResultQuestTable');
	
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
	name="currentPrintPage" value="contractUniqueReferenceValidation" /></form>
</HTML>
