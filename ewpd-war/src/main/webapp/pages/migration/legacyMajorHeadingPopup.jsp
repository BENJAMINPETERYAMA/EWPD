<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">
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
<TITLE>Major Heading Id Popup</TITLE>
<BASE target="_self" />
</HEAD>
<f:view>
	<BODY onunload="getCheckedItems()">
	<script language="JavaScript" src="../../js/tigra_tables.js"></script>
	<h:form id="legacyMajorHeadingForm">
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
            <tr>
				<td>
				<TABLE>
					<TBODY>
						<TR>
							<TD>&nbsp;</TD>
						</TR>
					</TBODY>
				</TABLE>
				</td>
			</tr>
			<tr>
				<td align="left"><h:commandButton id="selectBtn"
					styleClass="wpdbutton" value="Select"
					onclick="selectFn()">
				</h:commandButton></td>
			</tr>
		</table>
		<br/>
		<table>
			<tr><td><span>Please select a major heading so that its note will be attached to the benefit component.</span></td></tr>
		</table>
		<table width="96%" align="center" id="HeadingTable" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						bgcolor="#cccccc">
						<TR>
							<TD width="6%" align="left"></TD>
							<TD width="94%" align="center"><strong><h:outputText
								value="Major Headings"></h:outputText></strong></TD>
						</TR>
					</table>
					</TD>
				</TR>
				<tr>
					<td>
					<DIV id="popupDataTableDiv" class=popupDataTableDiv><h:dataTable
						rowClasses="dataTableEvenRow,dataTableOddRow" cellspacing="1"
						width="100%" id="majorHeadingTable"
						value="#{legacyContractBackingBean.majorHeadingList}"
						rendered="#{legacyContractBackingBean.majorHeadingList != null}"
						var="eachRow" cellpadding="0" bgcolor="#cccccc">
						<h:column>
							<wpd:singleRowSelect groupName="majorHeading" id="majorHdg" rendered="true"></wpd:singleRowSelect>
						</h:column>
						<h:column>
							<h:outputText value="#{eachRow.majorHeadingId}"></h:outputText>
							<h:inputHidden value="#{eachRow.majorHeadingId}"></h:inputHidden>
							<h:inputHidden value="#{eachRow.majorHeadingDesc}"></h:inputHidden> 
						</h:column>
						<h:column>
							<h:outputText value="#{eachRow.majorHeadingDesc}"></h:outputText>
						</h:column>
					</h:dataTable></DIV>
					</td>
				</tr>
			</TBODY>
		</table>
	</h:form>
</BODY>
</f:view>
<script language="JavaScript">
hideDiv();
function hideDiv(){
	if(null != document.getElementById('legacyMajorHeadingForm:majorHeadingTable')){
		setColumnWidth('legacyMajorHeadingForm:majorHeadingTable','8%:18%:84%');
		var temp =  window.dialogArguments.selectedValues;
		window.opener = window.dialogArguments.parentWindow;
		var source = window.opener.document.getElementById('migrateNotesForm:hiddenMajorHeadingId').value;
		matchCheckboxItems_ewpd('legacyMajorHeadingForm:majorHeadingTable', source,1, 1, 1)
	}
}

function getCheckedItems_majorHeading(id)
{
	var tableObject;	
	if (document.getElementById(id))
	{
		tableObject = document.getElementById(id);
		var selectedValues = '';
		var cnt = 0;
		var radioButtonObj;
		for (var i=0;i<tableObject.rows.length;i++)
		{
			radioButtonObj = tableObject.rows[i].cells[0].children[0];
			if(radioButtonObj && radioButtonObj.checked) {
				selectedValues += tableObject.rows[i].cells[1].innerText;
				
				}
	  }
	}
	if(selectedValues != ''){
		window.returnValue = selectedValues;
	}else{
		window.returnValue = '';
	}
	window.close();	
	if(cnt > 0)
		return true;
	return false;
}

function getCheckedItems(){
	window.returnValue = window.opener.document.getElementById('migrateNotesForm:hiddenMajorHeadingId').value;
	return false;
}
function selectFn(){
	document.body.onunload = "";
	getCheckedItems_majorHeading('legacyMajorHeadingForm:majorHeadingTable',2);
	return false;
}
</script>
</html>