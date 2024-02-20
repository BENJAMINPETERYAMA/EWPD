<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" title="Style">

<TITLE>Print Product Component Association</TITLE>
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
<table width="100%" cellpadding="0" cellspacing="0">
<tr>
	<td>
				<h:form styleClass="form" id="benifitCreateForm">
<!-- End of Tab table -->
								
<!--	Start of Table for actual Data	-->		
														<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	-->
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr><td>
						<table>
						
					</table></td></tr>
							<tr>

								<td colspan="2" valign="top" class="ContentArea">

								<fieldset>

								<div id="panel2Header" class="tabTitleBar"
									style="position:relative;width:100% ">Associated Benefit
								Components</div>
							
								<table class="smallfont" id="resultsTable" width="100%"
									cellpadding="0" cellspacing="0" border="0">
									<%-- tr bgcolor="#cccccc">
	
						<td colspan="3" class= "tabTitleBar" bgcolor="#cccccc"><span id="stateCodeStar"><strong>
						<h:outputText value="Associated Benefit Components"/></strong></span></td>
					</tr>
					<h:outputText value="No Major Heading Information is Available." rendered="#{productStructureBenefitComponentBackingBean.benefitComponentList == null}" styleClass="dataTableColumnHeader"/--%>
									<tr>
										<td>
										<div id="resultHeaderDiv">
										<TABLE id="headerTable" width="100%" border="0"
											bgcolor="#cccccc" cellpadding="2" cellspacing="1">
											<tr class="dataTableOddRow">
												<td>&nbsp;</td>

												<td><strong><h:outputText value="Benefit Component" /></strong></td>
												<td>&nbsp;</td>
											</tr>
										</TABLE>
										</div>
										</td>
									</tr>
									<tr>
										<td bordercolor="#cccccc">
										<div id="searchResultdataTableDiv"
											style="height:165px; overflow: auto;"><h:dataTable
											cellspacing="1" id="bComponentDataTable"
											rendered="#{productComponentAssociationBackingBean.benefitComponentList != null}"
											value="#{productComponentAssociationBackingBean.benefitComponentList}"
											var="singleValue" cellpadding="3" width="100%"
											>
											<h:column>
												<h:inputText id="id" value="#{singleValue.sequence}"
													maxlength="3" onkeypress="return isNumberKey(event);"
													styleClass="sequenceNumberReadOnly"
													readonly="true" />
											</h:column>
											<h:column>
												<h:outputText id="name" value="#{singleValue.componentDesc}"></h:outputText>
												<h:inputHidden id="benefitComponentName"
													value="#{singleValue.componentDesc}"></h:inputHidden>
											</h:column>
											<h:column>
												
											</h:column>
										</h:dataTable></div>

										</td>
									</tr>
									<tr>
										<TD></TD>
									</tr>
									<tr>
										<TD></TD>
									</tr>

								</table>



								</fieldset>
								<br />
								<fieldset>
								<table border="0" cellspacing="0" cellpadding="0" width="100%">
									<tr>
										<td width="15"><h:selectBooleanCheckbox disabled="true">
										</h:selectBooleanCheckbox></td>
										<td align="left"><h:outputText value="Check In" /></td>
										<td align="left" width="144">
										<table width=100%>
											<tr>
												<td><h:outputText value="State " /></td>
												<td><h:outputText value=":#{productComponentAssociationBackingBean.state}" /></td>
											</tr>
											<tr>
												<td><h:outputText value="Status " /></td>
												<td><h:outputText value=":#{productComponentAssociationBackingBean.status}" /></td>
											</tr>
											<tr>
												<td><h:outputText value="Version " /></td>
												<td><h:outputText value=":#{productComponentAssociationBackingBean.version}" /></td>
											</tr>
										</table>
										</td>
									</tr>

								</table>
								</fieldset>
								</td>
							</tr>
						</table>
						<!--	End of Page data	--></fieldset>

				</h:form>
	</td>
</tr>
</table>
</BODY></f:view>
<script>window.print();</script>
</HTML>



