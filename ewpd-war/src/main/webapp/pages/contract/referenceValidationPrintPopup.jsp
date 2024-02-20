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
					value="Benefit Lines with duplicate Reference"></h:outputText></strong></TD>
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
			style="position:relative;z-index:1;font-size:10px;"></DIV>

		<br>
		<br>
		<br>

		<DIV id="resultHeaderQuestDiv">
		<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
			id="headerTable" border="0" width="100%">
			<TR>
				<TD><strong><h:outputText value="Questions with duplicate Reference"></h:outputText></strong></TD>
			</TR>
		</TABLE>

		<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
			id="resultQuestHeaderTable" border="0" width="100%">
			<TBODY>
				<TR class="dataTableColumnHeader">
					<TD align="left"><h:outputText value="Date Segment "></h:outputText></TD>
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
		</div>

		<DIV id="searchResultdataTableQuestDiv"
			style="position:relative;z-index:1;font-size:10px;"></DIV>
		<br />
	</h:form>
	</BODY>
</f:view>

<script>
	var parentDoc = window.dialogArguments; 
	document.getElementById('timeID').innerHTML =  parentDoc.getElementById('timeID').innerHTML;
	document.getElementById('contractName').innerHTML = 	parentDoc.getElementById('formName:contractName').innerHTML
	document.getElementById('versionNumber').innerHTML = 	parentDoc.getElementById('formName:versionNumber').innerHTML
	//document.getElementById('productName').innerHTML = 	parentDoc.getElementById('formName:productName').innerHTML
	document.getElementById('searchResultdataTableDiv').innerHTML = parentDoc.getElementById('searchResultdataTableDiv').innerHTML;
	document.getElementById('searchResultdataTableQuestDiv').innerHTML = parentDoc.getElementById('searchResultdataTableQuestDiv').innerHTML;

	if(getObj('formName:searchResultTable') != null && getObj('formName:searchResultTable').rows.length > 0 ) {
		setColumnWidth('formName:searchResultTable','10%:11%:11%:10%:18%:17%:8%:15%');
		setColumnWidth('resultHeaderTable','10%:11%:11%:10%:18%:17%:8%:15%');
		syncTables('resultHeaderTable','formName:searchResultTable');
	} else {
		document.getElementById('resultHeaderDiv').style.display = 'none';
		document.getElementById('searchResultdataTableDiv').style.display = 'none';
	}

	if(getObj('formName:searchResultQuestTable') != null && getObj('formName:searchResultQuestTable').rows.length > 0 ) {
		setColumnWidth('formName:searchResultQuestTable','10%:11%:11%:10%:18%:17%:8%:15%');
		setColumnWidth('resultQuestHeaderTable','10%:11%:11%:10%:18%:17%:8%:15%');
		syncTables('resultQuestHeaderTable','formName:searchResultQuestTable');
	} else {
		document.getElementById('resultHeaderQuestDiv').style.display = 'none';
		document.getElementById('searchResultdataTableQuestDiv').style.display = 'none';
	}

	window.print();
</script>

</HTML>
