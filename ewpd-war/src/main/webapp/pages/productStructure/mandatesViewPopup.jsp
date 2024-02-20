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

<TITLE>Mandates View Popup</TITLE>
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
</HEAD>
<f:view>
	<BODY>
	<script language="JavaScript" src="../../js/tigra_tables.js"></script>
	<h:form id="MandatePopupForm">

		<TABLE width="400" border="0" cellspacing="0" cellpadding="0">
			<TR>
				<TD colspan="2" valign="top" align="left" class="ContentArea"
					width="100%">

				<DIV id="searchPanelHeader" class="tabTitleBar"><B>Associated
				Mandates</B></DIV>
				<BR>
				<TABLE align="left" cellpadding="0" cellspacing="0" width="100%"
					border="0">
					<h:outputText value="Non Adj Benefit Mandates not Available"
						rendered="#{productStructureGeneralInfoBackingBean.mandateList == null}"
						styleClass="dataTableColumnHeader" />
					<TR>
						<TD>
						<DIV id="resultHeaderDiv">
						<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
							id="resultHeaderTable" border="0" width="100%">
							<TBODY>
								<TR bgcolor="#ffffff">
									<TD align="left"><B><h:outputText value="Description"></h:outputText></TD>
									<TD align="left"><B><h:outputText value="Jurisdiction"></h:outputText></TD>
								</TR>
							</TBODY>
						</TABLE>
						</div>
						</td>
					</tr>
					<tr>
						<td>
						<div id="searchResultdataTableDiv"
							style="height:200px;width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;">
						<!-- Search Result Data Table --> <h:dataTable
							styleClass="outputText" headerClass="dataTableHeader"
							id="searchResultTable" var="singleValue" cellpadding="3"
							width="100%" cellspacing="1" bgcolor="#cccccc"
							rendered="#{productStructureGeneralInfoBackingBean.mandateList != null}"
							value="#{productStructureGeneralInfoBackingBean.mandateList}"
							rowClasses="dataTableEvenRow,dataTableOddRow" border="0">
							<h:column>
								<h:outputText id="description"
									value="#{singleValue.description}"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText id="jurisdiction"
									value="#{singleValue.jurisdiction}"></h:outputText>
							</h:column>
						</h:dataTable></div>
						</td>
					</tr>

					</h:form>
	</BODY>
</f:view>

<script>

	// Space for page realated scripts
		setColumnWidth('MandatePopupForm:searchResultTable', '50%:50%');
</script>
</HTML>
