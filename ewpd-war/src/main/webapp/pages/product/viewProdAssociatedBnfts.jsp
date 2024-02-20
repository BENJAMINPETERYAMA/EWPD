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

<TITLE>Benefit Component Hierarchies</TITLE>
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
</HEAD>
<f:view>
	<BODY>
	<table width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD><jsp:include page="../navigation/top_view.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="BenefitComponentHierarchiesForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<DIV class="treeDiv"><!-- Space for Tree  Data	--> <jsp:include
							page="productTree.jsp"></jsp:include></DIV>


						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message
										value="#{productComponentGeneralInfoBackingBean.validationMessages}"></w:message>
									</TD>
								</tr>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{productComponentGeneralInfoBackingBean.retrieveBenefitComponent}">
											<h:outputText value="General Information" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" alt="Tab Left Active"
											width="3" height="21" /></td>
										<td class="tabActive" width="186"><h:outputText
											value="Benefit" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" alt="Tab Right Active"
											width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200" id="notestab">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td width="186" class="tabNormal"><h:commandLink
											action="#{productBenefitComponentNoteBackingBean.loadNotesView}">
											<h:outputText value="Notes" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<TD width="100%"></TD>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:20px;width:100%">

						<!--	Start of Table for actual Data	-->

						<table border="0" width="100%" id="tableNew" cellspacing="0"
							cellpadding="0">
						
							<tr>
								<td colspan="2" valign="top" class="ContentArea">
								<div id="panel2Header" class="tabTitleBar"
									style="position:relative;width:100% ">Associated Benefits</div>
								<table class="smallfont" id="resultsTable" width="100%"
									cellpadding="0" cellspacing="0" border="0">
									<tr>
										<td>
										<div id="resultHeaderDiv">
										<TABLE id="headerTable" width="100%" border="0"
											bgcolor="#cccccc" cellpadding="1" cellspacing="1">
											<tr class="dataTableOddRow">

												<td><strong><h:outputText value="Benefit Name" /></strong></td>
											</tr>
										</TABLE>
										</div>
										</td>
									</tr>
									<tr>
										<td bordercolor="#cccccc">
										<div id="searchResultdataTableDiv"
											style="height:301px; overflow: auto;"><h:dataTable
											cellspacing="1" id="bComponentDataTable"
											rendered="#{productComponentGeneralInfoBackingBean.benefitDetailsList != null}"
											value="#{productComponentGeneralInfoBackingBean.benefitDetailsList}"
											rowClasses="dataTableEvenRow,dataTableOddRow"
											var="singleValue" cellpadding="3" width="100%"
											bgcolor="#cccccc">
											<h:column>
												<h:outputText value="#{singleValue.standardBenefitDesc}"></h:outputText>
												<h:inputHidden value="#{singleValue.standardBenefitDesc}"></h:inputHidden>
											</h:column>
										</h:dataTable></div>
										</td>
									</tr>
								</table>
								<!--	End of Page data	--></TD>
							</TR>
						</table>
						</fieldset>
						</TD>
					</TR>

				</table>



				<h:inputHidden id="PageLoad1" binding="#{productComponentGeneralInfoBackingBean.loadAssociatedBenefits}"></h:inputHidden>
			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom_view.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>

<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="productBenefitInfo" /></form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
