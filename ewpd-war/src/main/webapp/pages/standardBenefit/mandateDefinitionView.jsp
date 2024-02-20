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

<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css"
	title="Style">

<TITLE>Mandate Definition View</TITLE>

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

<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>

</head>



<f:view>
	<BODY>
	<TABLE width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD><jsp:include page="../navigation/top_view.jsp"></jsp:include></TD>
		</TR>
		<TR>
			<TD>
			<TABLE width="100%" id="breadCumbTable" bgcolor="#7670B3">
				<TR>

					<TD height="16" valign="middle" bgcolor="#7670B3"
						class="breadcrumb">Product Configuration&gt;&gt; Benefit (MM
					deductible)&gt;&gt; Mandate Definition</TD>

				</TR>
			</TABLE>
			</TD>
		</TR>

		<TR>
			<TD><h:form styleClass="form" id="MandateDefinitionViewForm">
				<TABLE width="97%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD valign="top" class="leftPanel" width="273"><DIV class="treeDiv"><!-- Space for Tree  Data	-->
						<jsp:include page="../standardBenefit/standardBenefitTree.jsp"></jsp:include></DIV>
						</TD>
						<TD colspan="1" valign="top" class="ContentArea" width="963">
						<TABLE>
							<TBODY>
								<TR>
									<TD><!-- Insert WPD Message Tag --></TD>
								</TR>
							</TBODY>
						</TABLE>




						<!-- Table containing Tabs -->



						<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">


							<TR>
								<TD width="25%">
								<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
									<TR>
										<TD width="2" align="left"><IMG
											src="../../images/tabNormalLeft.gif" width="3" height="21"></TD>
										<TD width="186" class="tabNormal"><h:outputText
											value=" Standard Benefit" /></TD>
										<TD width="2" align="right"><IMG
											src="../../images/tabNormalRight.gif" width="2" height="21"></TD>
									</TR>
								</TABLE>
								</TD>
								<TD width="25%">
								<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
									<TR>
										<TD width="3" align="left"><IMG
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21"></TD>
										<TD class="tabNormal"><h:commandLink>
											<h:outputText value="Standard Definition" />
										</h:commandLink></TD>
										<TD width="2" align="right"><IMG
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21"></TD>
									</TR>
								</TABLE>
								</TD>
								<TD width="25%">
								<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
									<TR>
										<TD width="2" align="left"><IMG
											src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
										<TD width="186" class="tabActive"><h:outputText
											value="Adj Benefit Mandates" /></TD>
										<TD width="2" align="right"><IMG
											src="../../images/activeTabRight.gif" width="2" height="21"></TD>
									</TR>
								</TABLE>
								</TD>
								<TD width="25%">
								<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
									<TR>
										<TD width="2" align="left"><IMG
											src="../../images/tabNormalLeft.gif" width="3" height="21"></TD>
										<TD width="186" class="tabNormal"><h:outputText
											value="Non Adj Benefit Mandates" /></TD>
										<TD width="2" align="right"><IMG
											src="../../images/tabNormalRight.gif" width="2" height="21"></TD>
									</TR>
								</TABLE>
								</TD>
								<TD width="100%"></TD>
							</TR>
						</TABLE>
						<!-- End of Tab table -->

						<FIELDSET
							style="margin-left:6px;margin-right:-65px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	--> <br>
						<br>
						<br>
						<br>

						<TABLE width="100%" cellpadding="0" cellspacing="0" border="0">

							<tbody>
								<tr>
									<td>
									<DIV id="panel2Header" class="tabTitleBar"
										style="position:relative;width:100% "><STRONG><h:outputText
										value="Associated Mandate Definitions"></h:outputText></STRONG></DIV>
									</td>
								</tr>

								<TR>
									<TD>
									<div id="resultHeaderDiv">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="0"
										id="resultHeaderTable" border="0" width="100%">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<td align="left" width="25%"><h:outputText
													value="Description"></h:outputText></td>
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
									</TD>
								</TR>
								<TR>
									<TD>
									<DIV id="searchResultdataTableDiv"
										style="height:100px; overflow: auto;"><!-- Search Result Data Table -->
									<h:dataTable
										rendered="#{MandateDefinitionBackingbean.mandateListView != null}"
										rowClasses="dataTableEvenRow,dataTableOddRow"
										bgcolor="#cccccc" styleClass="outputText"
										headerClass="dataTableHeader" id="mandateDefinitionsTable"
										var="singleValue" cellpadding="3" width="100%" cellspacing="1"
										value="#{MandateDefinitionBackingbean.mandateListView}">

										<h:column>
											<h:outputText id="desc" value="#{singleValue.description}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="effDate"
												value="#{singleValue.effectiveDate}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="expDate" value="#{singleValue.expiryDate}"></h:outputText>
										</h:column>

										<h:column>
											<h:outputText id="mandate" value="#{singleValue.mandate}"></h:outputText>
										</h:column>


									</h:dataTable></DIV>
									</TD>
								</TR>
							</tbody>

						</TABLE>

						</FIELDSET>
						<BR>
						<br>



						<FIELDSET
							style="margin-left:6px;margin-right:-65px;padding-bottom:1px;padding-top:6px;width:100%">

						<TABLE align="right" border="0" cellspacing="0" cellpadding="0"
							width="100%">

							<TR>
								<TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<h:outputText value="Check In"></h:outputText></TD>

								<TD align="left" width="127">
								<TABLE width="100%">
									<TR>
										<TD>State</TD>
										<TD>: New</TD>
									</TR>
									<TR>
										<TD>Status</TD>
										<TD>: Building</TD>
									</TR>
									<TR>
										<TD>Version</TD>
										<TD>: 1.0</TD>
									</TR>
								</TABLE>
								</TD>
							</TR>
						</TABLE>
						</FIELDSET>




						</TD>
					</TR>
				</TABLE>






				<!--	End of Page data	-->


				<!-- Space for hidden fields -->

				<!-- End of hidden fields  -->






			</h:form></TD>
		</TR>
		<TR>
			<td colspan="2" width="100%"><%@ include
				file="../navigation/bottom_view.jsp"%></td>
		</TR>

	</TABLE>
	</BODY>
</f:view>
<script language="javascript">

		setColumnWidth('MandateDefinitionViewForm:mandateDefinitionsTable','25%:25%:25%:25%');
		</script>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>










