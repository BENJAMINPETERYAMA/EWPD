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
<TITLE>Contract Notes Validation Print</TITLE>
</HEAD>
<f:view>
	<BODY>
	<h:form styleClass="form" id="formName">
		<br />
		<table id="questionHeaderTable" width="98%" cellpadding="1"
			cellspacing="1" border="0" align="center">
			<tr>
				<td width="15%"><h:outputText value="Contract Name " /></td>
				<td>:&nbsp;<SPAN id="contractName"></SPAN></td>
				<td width="25%"><SPAN id="timeID">time</SPAN></td>
			</tr>
			<tr>
				<td><h:outputText value="Version" /></td>
				<td>:&nbsp;<SPAN id="versionNumber"></SPAN></td>
				<td></td>
			</tr>
		</table>
		<br />
		<DIV id="resultHeaderDiv">
		<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
			id="headerTable" border="0" width="100%">
			<TR>
				<TD><strong><h:outputText
					value="Benefit and Benefit Component Rules"></h:outputText></strong></TD>
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
			style="position:relative;z-index:1;font-size:10px;"></DIV>

		<br>
		</h:form>
	</BODY>
</f:view>

<script>
	

	var parentDoc = window.dialogArguments; 
	document.getElementById('timeID').innerHTML =  parentDoc.getElementById('timeID').innerHTML;
	document.getElementById('contractName').innerHTML = 	parentDoc.getElementById('formName:contractName').innerHTML;
	document.getElementById('versionNumber').innerHTML = 	parentDoc.getElementById('formName:versionNumber').innerHTML;
	
	document.getElementById('searchResultdataTableDiv').innerHTML = parentDoc.getElementById('searchResultdataTableDiv').innerHTML;
		if(getObj('formName:searchResultTable') != null && getObj('formName:searchResultTable').rows.length > 0 ) {
		setColumnWidth('formName:searchResultTable','12%:16%:16%:19%:8%:13%:13%');
		setColumnWidth('resultHeaderTable','12%:16%:16%:19%:8%:13%:13%');
		syncTables('resultHeaderTable','formName:searchResultTable');
	} else {
		document.getElementById('resultHeaderDiv').style.display = 'none';
		document.getElementById('searchResultdataTableDiv').style.display = 'none';
	}

	var rowObject = getObj('formName:searchResultTable');
	for (var i=0;i<rowObject.rows.length;i++){
		rowObject.rows[i].className = 'dataTableOddRow';
	}
	
	window.print();
</script>

</HTML>

