<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
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

<TITLE>Non Adjudication Benefit Mandate View</TITLE>
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
<BASE target="_self" />
</HEAD>
<f:view>
	<BODY>
	<h:form styleClass="form" id="nonAdjBenefitMandateViewForm">
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td><jsp:include page="../navigation/top_view.jsp"></jsp:include></td>
			</tr>
			<tr>
				<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel"><!-- Space for Tree  Data	-->
						<jsp:include page="../benefitComponent/benefitComponentTree.jsp"></jsp:include>



						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><!-- Insert WPD Message Tag --></TD>
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
											action="#{benefitComponentCreateBackingBean.loadComponentBenefitforView}">
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
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{ComponentBenefitDefinitionsBackingBean.loadStandardDefinitionView}">
											<h:outputText value="Benefit Definition" />
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
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:outputText
											value=" Non Adj Benefit mandates" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<TD width="200">
								<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
									<TR>
										<TD width="3" align="left"><IMG
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21"></TD>
										<TD class="tabNormal"><h:commandLink
											action="#{BenefitComponentNotesBackingBean.loadStandardBenefitNotesView}">
											<h:outputText value="Notes" />
										</h:commandLink></TD>
										<TD width="2" align="right"><IMG
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21"></TD>
									</TR>
								</TABLE>
								</TD>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<br>
						<!--	Start of Table for actual Data	-->
						<TABLE width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td>
								<div id="resultHeaderDiv">
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="resultHeaderTable" border="0" width="100%">
									<tr>
										<TD><h:outputText value="Associated Non Adj Benefit Mandates"></h:outputText>
										</TD>
									</tr>
								</TABLE>
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="resultHeaderTable" border="0" width="100%">
									<TBODY>
										<TR class="dataTableColumnHeader">
											<td align="left" width="25%"><h:outputText
												value="Optional Identifier"></h:outputText></td>
											<td align="left" width="25%"><h:outputText
												value="Effective Date"></h:outputText></td>
											<td align="left" width="25%"><h:outputText
												value="Expiry Date"></h:outputText></td>
											<td align="left" width="25%"><h:outputText value="Mandate"></h:outputText>
											</td>
										</TR>
									</TBODY>
								</TABLE>
								</div>
								</td>
							</tr>
							<tr>
								<TD><!-- Search Result Data Table -->
								<div id="searchResultdataTableDiv"
									style="height:100px; overflow:auto; width:100%;"><h:dataTable
									headerClass="dataTableHeader" id="searchResultTable"
									var="singleValue" cellpadding="3" cellspacing="1"
									bgcolor="#cccccc"
									rendered="#{benefitMandatesBackingBean.associatedbenefitMandatesList != null}"
									value="#{benefitMandatesBackingBean.associatedbenefitMandatesList}"
									rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
									width="100%">
									<h:column>
										<h:outputText id="identifier"
											value="#{singleValue.optionalIdentifier}"></h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="effectiveDate"
											value="#{singleValue.effectiveDate}">
											<f:convertDateTime pattern="MM/dd/yyyy" />
										</h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="expiryDate"
											value="#{singleValue.expiryDate}">
											<f:convertDateTime pattern="MM/dd/yyyy" />
										</h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="mandate" value="#{singleValue.mandate}"></h:outputText>
									</h:column>

								</h:dataTable></div>
								</TD>
							</tr>
						</table>
						<!--	End of Page data	--></fieldset>
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields --> <h:inputHidden id="hidden1"
					value="value1"></h:inputHidden> <h:commandLink id="hiddenLnk1"
					style="display:none; visibility: hidden;">
					<f:verbatim />
				</h:commandLink> <!-- End of hidden fields  --> </h:form></td>
			</tr>
			<tr>

				<td><%@ include file="../navigation/bottom_view.jsp"%></td>

			</tr>
		</table>
	</BODY>
</f:view>

<script language="javascript">
	initialize();

	function initialize(){
		if(document.getElementById('nonAdjBenefitMandateViewForm:searchResultTable') != null) {
			setColumnWidth('nonAdjBenefitMandateViewForm:searchResultTable', '25%:25%:25%:25%');
			setColumnWidth('resultHeaderTable', '25%:25%:25%:25%');
		}else {
			document.getElementById("resultHeaderDiv").style.visibility = 'hidden';
		}
	}
	
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="nonAdjBenefitMandates" /></form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
