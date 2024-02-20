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
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css"
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

<TITLE>Print For Notes All Versions</TITLE>
</HEAD>
<f:view>
	<BODY>

	<h:form styleClass="form" id="notesLocateResultsPrintForm">

		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD>
					&nbsp;
				</TD>
			</TR>
			<TR>
				<TD>
					<FIELDSET style="margin-left:45px;margin-right:-1px;padding-bottom:1px;padding-top:1px;width:95%">
						<h:outputText id="breadcrumb" 
							value="#{notesBackingBean.printBreadCrumbText}">
						</h:outputText>
					</FIELDSET>
				</TD>
			</TR>
			<TR>
				<TD>
					&nbsp;
				</TD>
			</TR>

		</TABLE>
		<h:inputHidden id="noteVersionList"
			value="#{notesBackingBean.viewAllNotes}"></h:inputHidden>
		<table align="left" cellpadding="0" cellspacing="0" border="0"
			width="100%">
			<tr>
				<td>
				<div id="resultHeaderDiv">
				<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
					id="resultHeaderTable" border="0" width="90%" align="center">
					<TBODY>
						<tr bgcolor="#ffffff">
							<td width="19%" align="left"><strong><h:outputText value="Name"></h:outputText></strong></td>
							<td width="20%" align="left"><strong><h:outputText value="Text"></h:outputText></strong></td>
							<td width="12%" align="left"><strong><h:outputText value="Type"></h:outputText></strong></td>
							<td width="7%" align="left"><strong><h:outputText value="Version"></h:outputText></strong></td>
							<td width="18%" align="left"><strong><h:outputText value="Status"></h:outputText></strong></td>
							<td width="12%" align="left"><strong><h:outputText
								value="Last Updated User"></h:outputText></strong></td>
							<td width="12%" align="left"><strong><h:outputText
								value="Last Updated Date"></h:outputText></strong></td>
						</TR>
					</TBODY>
				</TABLE>
				</div>
				</td>
			</tr>

			<tr align="center">
				<TD><!-- Search Result Data Table -->
				<div id="searchResultdataTableDiv"
					style="height:292px; overflow:auto;width:90%;"><h:dataTable
					headerClass="dataTableHeader" id="previousVersionsTable"
					var="singleValue" cellpadding="3" cellspacing="1" 
					rendered="#{notesBackingBean.allVersionList != null}"
					value="#{notesBackingBean.allVersionList}"
					border="0"
					width="100%">
					<h:column>
						<h:outputText id="noteName" value="#{singleValue.noteName}"></h:outputText>
						<h:inputHidden id="noteName_hidden"
							value="#{singleValue.noteName}"></h:inputHidden>
						<h:inputHidden id="noteId_hidden" value="#{singleValue.noteId}"></h:inputHidden>
					</h:column>
					<h:column>
						<h:outputText id="noteText" value="#{singleValue.noteText}"></h:outputText>
						<h:inputHidden id="hidden_noteText"
							value="#{singleValue.noteText}"></h:inputHidden>
					</h:column>
					<h:column>
						<h:outputText id="noteType" value="#{singleValue.noteType}">
						</h:outputText>
						<h:inputHidden id="hidden_noteType"
							value="#{singleValue.noteType}"></h:inputHidden>
					</h:column>
					<h:column>
						<h:outputText id="noteVersion" value="#{singleValue.version}">
						</h:outputText>
						<h:inputHidden id="hidden_noteversion"
							value="#{singleValue.version}"></h:inputHidden>
					</h:column>
					<h:column>
						<h:outputText id="noteStatus" value="#{singleValue.status}" wrap>
						</h:outputText>
					</h:column>
					<h:column>
						<h:outputText id="lastUser" value="#{singleValue.lastUpdatedUser}">
						</h:outputText>
						<h:inputHidden ></h:inputHidden>
					</h:column>
					<h:column>
						<h:outputText id="lastDate"
							value="#{singleValue.lastUpdatedTimestamp}">
							<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
						</h:outputText>
						<h:outputText value="#{requestScope.timezone}"></h:outputText>
						<h:inputHidden value="#{singleValue.lastUpdatedTimestamp}">
							<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
						</h:inputHidden>
					</h:column>
				</h:dataTable></div>
				</TD>
			</tr>
			<TR>
				<TD></TD>
			</TR>
		</table>

	</h:form>
	</BODY>
</f:view>
<SCRIPT>
	if(null != document.getElementById('notesLocateResultsPrintForm:previousVersionsTable')){
		setColumnWidth('notesLocateResultsPrintForm:previousVersionsTable','19%:20%:12%:7%:18%:12%:12%');
	}
window.print();
</SCRIPT>
</HTML>
