<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="PRAGMA" content="NO-CACHE">

<META HTTP-EQUIV="Expires" CONTENT="-1">

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
<TITLE>WellPoint Product Database: Contract Copy Validation Popup</TITLE>
</HEAD>
<f:view>
	<BODY>
	<script language="JavaScript" src="../../js/tigra_tables.js"></script>
	<h:inputHidden id="loadList" value="#{contractBasicInfoBackingBean.loadList}"></h:inputHidden>
	<table>
		<tr>
			<h:form id="dateSegmentPopupForm">
				<table border="0" cellpadding="5" cellspacing="0" width="100%">
					<tr>
						<td align="left"><h:commandButton id="txtSelect"
							styleClass="wpdbutton" value="Select"
							onclick="getCheckedItems('dateSegmentPopupForm:productTable','dateSegmentPopupForm:noteTable',1);return false;">
						</h:commandButton></td>
					</tr>

				</table>
		</tr>
		<tr>
			<table width="97%" align="right" cellpadding="0" cellspacing="0">
				<TBODY>
					<TR>
						<TD>
						<DIV id="ProductHead">
						<table width="100%" border="0" cellspacing="1" cellpadding="0"
							bgcolor="#cccccc">
							<TR>
								<TD align="center"><strong><h:outputText value="Product Option"></h:outputText></strong></TD>
							</TR>
						</table>
						</DIV>
						</TD>
					</TR>
					<tr>
						<td><h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow"
							cellspacing="1" width="100%" id="productTable"
							value="#{contractBasicInfoBackingBean.productCopyList}"
							var="eachRow2" cellpadding="0" bgcolor="#cccccc"
							rendered="#{contractBasicInfoBackingBean.productCopyList != null}">
							<h:column>
								<f:verbatim>
									<wpd:singleRowSelect groupName="GroupSIze1" id="minor1"
										rendered="true"></wpd:singleRowSelect>
								</f:verbatim>
							</h:column>
							<h:column>

								<h:inputHidden id="hiddenHQStateId1" value="#{eachRow2.code}"></h:inputHidden>
								<h:inputHidden id="hiddenHQStateDesc1"
									value="#{eachRow2.description}"></h:inputHidden>

								<f:verbatim>
									<h:outputText value="#{eachRow2.description}"></h:outputText>
								</f:verbatim>
							</h:column>
						</h:dataTable></td>
					</tr>

				</TBODY>
			</table>
		
		<br />
		<br />
		<br />
			<br />
		<br />
		<br />
			<br />
		<br />
		<br />
	
			<table width="97%" align="right" cellpadding="0" cellspacing="0">
				<TBODY>
					<TR>
						<TD>
						<DIV id="NotesHead">
						<table width="100%" border="0" cellspacing="1" cellpadding="0"
							bgcolor="#cccccc">
							<TR>
								<TD align="center"><strong><h:outputText value="Notes Option"></h:outputText></strong></TD>
							</TR>
						</table>
						</DIV>
						</TD>
					</TR>
					<tr>
						<td><h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow"
							cellspacing="1" width="100%" id="noteTable"
							value="#{contractBasicInfoBackingBean.noteCopyList}"
							var="eachRow" cellpadding="0" bgcolor="#cccccc"
							rendered="#{contractBasicInfoBackingBean.noteCopyList != null}">
							<h:column>
								<f:verbatim>
									<wpd:singleRowSelect groupName="GroupSIze" id="minor1"
										rendered="true"></wpd:singleRowSelect>
								</f:verbatim>
							</h:column>
							<h:column>

								<h:inputHidden id="hiddenHQStateId" value="#{eachRow.code}"></h:inputHidden>
								<h:inputHidden id="hiddenHQStateDesc"
									value="#{eachRow.description}"></h:inputHidden>

								<f:verbatim>
									<h:outputText value="#{eachRow.description}"></h:outputText>
								</f:verbatim>
							</h:column>
						</h:dataTable></td>
					</tr>

				</TBODY>
			</table>
		</tr>
	</table>
	</h:form>


	</BODY>

</f:view>
<script language="JavaScript">

var tblobj1 = document.getElementById('dateSegmentPopupForm:productTable');
var tblobj2 = document.getElementById('dateSegmentPopupForm:noteTable');

if(tblobj1 != null){	

setColumnWidth('dateSegmentPopupForm:productTable','8%:92%');
}
if(tblobj2 != null){

setColumnWidth('dateSegmentPopupForm:noteTable','8%:92%');
}
if(tblobj1 == null){
	 document.getElementById('ProductHead').style.display='none';
}
if(tblobj2 == null){
	document.getElementById('NotesHead').style.display='none';
}
if(tblobj1 == null && tblobj2 == null){
	window.returnValue = "" +'~'+ "";
	
<%
if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
%>
window.close();
<%
}
else {
%>
parent.document.getElementsByTagName('dialog')[0].close();
<%
}
%>
}

function getCheckedItems(id1,id2,attrCount)
{
	var tableObject;	
	if (document.getElementById(id1))
	{
		tableObject = document.getElementById(id1);
		var selectedValues = '';
		var cnt = 0;
		var currentCell;
		for (var i=0;i<tableObject.rows.length;i++)
		{
			var checkboxObject = tableObject.rows[i].cells[0].children[0];
			currentCell = tableObject.rows[i].cells[1];
			
			if (checkboxObject && checkboxObject.checked) {
				if(cnt > 0)
					selectedValues += '~';

				switch(attrCount){
					case 1: selectedValues += currentCell.children[0].value; 
							break;
					case 2: selectedValues += currentCell.children[0].value + '~' + currentCell.children[1].value;	
							break;
					case 3: selectedValues += currentCell.children[0].value + '~' + currentCell.children[1].value + '~' + currentCell.children[2].value;
							break;
					default: alert('invalid attrCount parameter for function getCheckedItems');
				}
				cnt++;
			}
		}	
	}

	if (document.getElementById(id2))
	{
		tableObject2 = document.getElementById(id2);
		var selectedValues1 = '';
		var cnt = 0;
		var currentCell;
		for (var i=0;i<tableObject2.rows.length;i++)
		{
			var checkboxObject = tableObject2.rows[i].cells[0].children[0];
			currentCell = tableObject2.rows[i].cells[1];
			
			if (checkboxObject && checkboxObject.checked) {
				if(cnt > 0)
					selectedValues1 += '~';

				switch(attrCount){
					case 1: selectedValues1 += currentCell.children[0].value; 
							break;
					case 2: selectedValues1 += currentCell.children[0].value + '~' + currentCell.children[1].value;	
							break;
					case 3: selectedValues1 += currentCell.children[0].value + '~' + currentCell.children[1].value + '~' + currentCell.children[2].value;
							break;
					default: alert('invalid attrCount parameter for function getCheckedItems');
				}
				cnt++;
			}
		}	
	}
	window.returnValue = selectedValues +'~'+ selectedValues1;
<%
if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
%>
window.close();
<%
}
else {
%>
parent.document.getElementsByTagName('dialog')[0].close();
<%
}
%>
	if(cnt > 0)
		return true;
	return false;
}
</script>

</HTML>
