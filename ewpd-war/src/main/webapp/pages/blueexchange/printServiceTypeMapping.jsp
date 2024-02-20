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

<TITLE>Print Service Type Mapping</TITLE>
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
</script>
</HEAD>
<f:view>
	<BODY>

	<h:inputHidden id="temp"
		value="#{serviceTypeMappingBackingBean.initPrint}"></h:inputHidden>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td><%
		javax.servlet.http.HttpServletRequest httpReq = (javax.servlet.http.HttpServletRequest) javax.faces.context.FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		javax.servlet.http.HttpSession httpSession = (javax.servlet.http.HttpSession) javax.faces.context.FacesContext
				.getCurrentInstance().getExternalContext().getSession(true);
		httpReq.setAttribute("breadCrumbText",
				"Administration >> Blue Exchange >> Service Type Mapping ("
						+ httpSession.getAttribute("ruleID") + ") >> Print");
	%>
			<FIELDSET
				style="margin-left:15px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:89%">

			<h:outputText id="breadcrumb"
				value="#{serviceTypeMappingBackingBean.breadCrumpText}">

			</h:outputText></FIELDSET>



			</td>
		</tr>
		<tr>
			<td><h:form styleClass="form" id="servTypMappingForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>
						<TD colspan="2" valign="top" class="ContentArea">
						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:90%">
						<!--	Start of Table for actual Data	-->
						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText">
							<TBODY>
								<TR>
									<TD>
									<TABLE>
								<!-- Header Rule -->
								<TR>
									<TD width="242"><h:outputText value="Header Rule" /></TD>
									<TD width="221"><h:inputHidden id="srvCodeHidden"
										value="#{serviceTypeMappingBackingBean.ruleId}" /> <h:outputText
										value="#{serviceTypeMappingBackingBean.ruleId}" /></TD>
								</TR>
								<!-- Applicable To Blue Exchange -->
								<TR>
									<TD width="242"><h:outputText
										value="Applicable To Blue Exchange" /></TD>
									<TD width="221"><h:inputHidden id="srvClassHighHidden"
										value="#{serviceTypeMappingBackingBean.validForBX}" /> <h:outputText
										value="#{serviceTypeMappingBackingBean.validForBX}" /></TD>

								</TR>
								</TABLE>
									</TD>
								</TR>
								
								<!-- EB03/Service Type Code  -->
								<TR>
									<TD colspan="3" width="100%">
								<div id="adminDiv"><h:outputText
									rendered="#{serviceTypeMappingBackingBean.eb03IdentifierList == null}"
									value="No Benefit Administration Option Available."
									styleClass="dataTableColumnHeader" /></div>

								<DIV id="searchResultdataTableDiv" style="width:100%;">

								<TABLE width="100%" cellspacing="0" cellpadding="0">
									<TR>
										<TD>
										<DIV id="serviceTypeCodeDiv">
										<fieldset
											style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:80%">
										<TABLE width="100%" cellspacing="0" cellpadding="0">
											<TR>
												<TD width="663">

												<DIV id="resultHeaderDiv">
												<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
													id="resultHeaderTable1" border="0" width="100%">
													<TR align="center">
														<TD align="left"><b><h:outputText
															value="EB03/Service Type Code"></h:outputText></b></TD>
													</TR>
												</TABLE>
												<TABLE cellspacing="1" cellpadding="3"
													id="resultHeaderTable" border="0" width="70%">
													<TBODY>
														<TR class="dataTableColumnHeader">
															<TD align="left" width="35%"><h:outputText value="Code"></h:outputText>
															</TD>
															<TD align="left" width="45"><h:outputText
																value="Description"></h:outputText></TD>
															<TD align="left" width="20%"><h:outputText
																value="Send Dynamic Information"></h:outputText></TD>
														</TR>
													</TBODY>
												</TABLE>
												</DIV>
												</TD>												
											</TR>

											<TR>
												<TD colspan="3">
												<div id="paneltable"
												style="width:100%"><!-- Search Result Data Table -->
												 <h:dataTable
													styleClass="outputText" headerClass="dataTableHeader"
													id="searchResultTable" var="singleValue" cellpadding="3"
													width="70%" cellspacing="1"
													value="#{serviceTypeMappingBackingBean.eb03IdentifierList}"
													rendered="#{serviceTypeMappingBackingBean.eb03IdentifierList != null}"
													border="0">
													<h:column>
														<h:inputHidden id="serviceTypeCode"
															value="#{singleValue.serviceTypeCode}"></h:inputHidden>
														<h:outputText id="Code"
															value="#{singleValue.serviceTypeCode}">
														</h:outputText>
													</h:column>
													<h:column>
														<h:inputHidden id="serviceTypeCodeDesc"
															value="#{singleValue.serviceTypeSecCode}"></h:inputHidden>
														<h:outputText id="Description"
															value="#{singleValue.serviceTypeSecCode}">
														</h:outputText>
													</h:column>
													<h:column >
														<h:inputHidden id="sendynamicinfo"
															value="#{singleValue.sendDynamicInfo}"></h:inputHidden>
														<h:outputText id="senddynamic" 
															value="#{singleValue.sendDynamicInfo}">
														</h:outputText>
													</h:column>
												</h:dataTable></DIV>
												</TD>
											</TR>
										</TABLE></fieldset></DIV>
									</TD>
								</TR>
								<TR>
									<TD>
								<TABLE>
									<!-- Created User -->
									<TR>
										<TD width="242"><h:outputText value="Created By" /></TD>
										<TD width="221"><h:outputText
											value="#{serviceTypeMappingBackingBean.createdUser}" /> <h:inputHidden
											value="#{serviceTypeMappingBackingBean.createdUser}" /></TD>
									</TR>
									<!-- Created Date -->
									<TR>
										<TD width="242"><h:outputText value="Created Date" /></TD>
										<TD width="221"><h:outputText
											value="#{serviceTypeMappingBackingBean.createdTimestamp}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> <h:outputText value="#{requestScope.timezone}" />
										<h:inputHidden
											value="#{serviceTypeMappingBackingBean.createdTimestamp}" />
										</TD>
									</TR>
									<!-- Last updated user -->
									<TR>
										<TD width="242"><h:outputText value="Last Updated By" /></TD>
										<TD width="221"><h:outputText
											value="#{serviceTypeMappingBackingBean.lastUpdatedUser}" /> <h:inputHidden
											value="#{serviceTypeMappingBackingBean.lastUpdatedUser}" /></TD>
									</TR>
									<!-- Last update time -->
									<TR>
										<TD width="242"><h:outputText value="Last Updated Date" /></TD>
										<TD width="221"><h:outputText
											value="#{serviceTypeMappingBackingBean.lastUpdatedTimestamp}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> <h:outputText value="#{requestScope.timezone}" />
										<h:inputHidden
											value="#{serviceTypeMappingBackingBean.lastUpdatedTimestamp}" />
										</TD>
									</TR>

									<TR>
										<TD width="242"></TD>
										<TD width="221">&nbsp;</TD>
									</TR>
								</TABLE>
									</TD>
								</TR>
						</TABLE>
						</TD>
					</TR>
				</table></fieldset>
			</h:form></td>
		</tr>
	</table>
	</BODY>
</f:view>
<script>
initialize();
		function initialize(){
			if(document.getElementById('servTypMappingForm:searchResultTable').rows.length != 0 && document.getElementById('servTypMappingForm:searchResultTable') != null) {
				setColumnWidth('servTypMappingForm:searchResultTable','35%:45%:20%');
			}else {
				document.getElementById('resultHeaderDiv').style.display = 'none';
				document.getElementById('paneltable').style.display = 'none';
				document.getElementById('serviceTypeCodeDiv').style.display = 'none';
			    //document.getElementById('paneltable').style.visibility = 'hidden';
			}
		}
window.print();
</script>
</HTML>
