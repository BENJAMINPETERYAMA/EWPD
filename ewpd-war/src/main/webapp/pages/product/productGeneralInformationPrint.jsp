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

<TITLE>Print Product General Information</TITLE>
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
			<td><h:form styleClass="form" id="benifitCreateForm">

				<FIELDSET
					style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
				<h:inputHidden id="temp"
					value="#{productGeneralInformationBackingBean.initViewForPrint}"></h:inputHidden>
				<!--	Start of Table for actual Data	-->
				<TABLE border="0" cellspacing="0" cellpadding="3" class="outputText">
					<TBODY>
						<TR>
							<TD colspan="4">
							<FIELDSET
								style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:350">
							<LEGEND> <FONT color="black">Business Domain</FONT></LEGEND>
							<TABLE border="0" cellspacing="5" cellpadding="3">
								<TR>
									<TD width="5"></TD>
									<TD width="122"><h:outputText id="lineOfBusiness"
										value="Line Of Business" styleClass="" /></TD>
									<h:inputHidden id="lineOfBusinessHidden"
										value="#{productGeneralInformationBackingBean.lineOfBusinessDiv}"></h:inputHidden>
									<TD width="184"><h:outputText id="lineOfBusinessDiv"
										value="#{productGeneralInformationBackingBean.lineOfBusinessDiv}"
										styleClass="" /></TD>
									<TD width="24"></TD>
								</TR>
								<TR>
									<TD width="5"></TD>
									<TD width="122"><h:outputText id="businessEntity"
										value="Business Entity" styleClass="" /></TD>
									<h:inputHidden id="businessEntityHidden"
										value="#{productGeneralInformationBackingBean.businessEntityDiv}"></h:inputHidden>
									<TD width="184"><h:outputText id="businessEntityDiv"
										value="#{productGeneralInformationBackingBean.businessEntityDiv}"
										styleClass="" /></TD>
									<TD width="24"></TD>
								</TR>
								<TR>
									<TD width="5"></TD>
									<TD width="122"><h:outputText id="businessGroup"
										value="Business Group" styleClass="" /></TD>
									<h:inputHidden id="businessGroupHidden"
										value="#{productGeneralInformationBackingBean.businessGroupDiv}"></h:inputHidden>
									<TD width="184"><h:outputText id="businessGroupDiv"
										value="#{productGeneralInformationBackingBean.businessGroupDiv}"
										styleClass="" /></TD>
									<TD width="24"></TD>
								</TR>

							</TABLE>
							</FIELDSET>
							</TD>
						</TR>
						<TR valign="top">
							<TD width="5"></TD>
							<TD width="122"><h:outputText id="productFamily"
								value="Product Family" styleClass="" /></TD>
							<h:inputHidden id="productFamHidden"
								value="#{productGeneralInformationBackingBean.productFamily}"></h:inputHidden>
							<TD width="184"><h:outputText id="productFamilyTxt"
								value="#{productGeneralInformationBackingBean.productFamily}" />
							</TD>
							<TD width="24"></TD>
						</TR>
						<TR valign="top">
							<TD width="5"></TD>
							<TD width="122"><h:outputText value="Name" styleClass="" /></TD>
							<TD width="184"><h:outputText id="productName_txt"
								value="#{productGeneralInformationBackingBean.productName}" />
							<h:inputHidden id="productNameHidden"
								value="#{productGeneralInformationBackingBean.productName}"></h:inputHidden>
							</TD>
							<TD width="40"><f:verbatim></f:verbatim></TD>
						</TR>
						<TR valign="top">
							<TD width="5"></TD>
							<TD width="122"><h:outputText id="productStructure"
								value="Product Structure" styleClass="" /></TD>
							<h:inputHidden id="productStructHidden"
								value="#{productGeneralInformationBackingBean.productStructDiv}"></h:inputHidden>
							<TD width="184"><h:outputText id="productStructDiv"
								value="#{productGeneralInformationBackingBean.productStructDiv}" />
							</TD>
							<TD width="24"></TD>
						</TR>
						<TR valign="top">
							<TD width="5"></TD>
							<TD width="122"><h:outputText value="Effective Date"
								styleClass="" /></TD>
							<TD width="184"><h:outputText id="effectiveDate_txt"
								value="#{productGeneralInformationBackingBean.effectiveDate}" />

							</TD>
							<TD valign="top" width="24"></TD>
						</TR>
						<TR valign="top">
							<TD width="5"></TD>
							<TD width="122"><h:outputText value="Expiry Date" styleClass="" />
							</TD>
							<TD width="184"><h:outputText id="expiryDate_txt"
								value="#{productGeneralInformationBackingBean.expiryDate}" /> <h:inputHidden
								id="expiryDate_txtHidden"
								value="#{productGeneralInformationBackingBean.expiryDate}"></h:inputHidden>
							</TD>
							<TD valign="top" width="24"></TD>
						</TR>
						<TR valign="top">
							<TD width="5"></TD>
							<TD width="122"><h:outputText value="Created By" /></TD>
							<TD width="184"><h:outputText id="createdBy_optxt"
								value="#{productGeneralInformationBackingBean.createdBy}" /> <h:inputHidden
								id="createdBy_optxtHidden"
								value="#{productGeneralInformationBackingBean.createdBy}"></h:inputHidden>
							</TD>
							<TD width="24"><f:verbatim /></TD>
						</TR>
						<TR valign="top">
							<TD width="5"></TD>
							<TD width="122"><h:outputText value="Created Date" /></TD>
							<TD width="184"><h:outputText id="creationDate_optxt"
								value="#{productGeneralInformationBackingBean.creationDate}">
								<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
							</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
							<h:inputHidden id="creationDate_optxtHidden"
								value="#{productGeneralInformationBackingBean.creationDate}">
								<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
							</h:inputHidden> <h:inputHidden id="time"
								value="#{requestScope.timezone}">
							</h:inputHidden></TD>
							<TD width="24"><f:verbatim /></TD>
						</TR>
						<TR valign="top">
							<TD width="5"></TD>
							<TD width="122"><h:outputText value="Last Updated By" /></TD>
							<TD width="184"><h:outputText id="updatedBy_optxt"
								value="#{productGeneralInformationBackingBean.updatedBy}" /> <h:inputHidden
								id="updatedBy_optxtHidden"
								value="#{productGeneralInformationBackingBean.updatedBy}"></h:inputHidden>
							</TD>
							<TD width="24"><f:verbatim /></TD>
						</TR>
						<TR valign="top">
							<TD width="5"></TD>
							<TD width="122"><h:outputText value="Last Updated Date" /></TD>
							<TD width="184"><h:outputText id="updationDate_optxt"
								value="#{productGeneralInformationBackingBean.updationDate}">
								<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
							</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
							<h:inputHidden id="updationDate_optxtHidden"
								value="#{productGeneralInformationBackingBean.updationDate}">
								<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
							</h:inputHidden></TD>
							<TD width="24"><f:verbatim /></TD>
						</TR>
						
						<TR valign="top">
							<TD width="5"></TD>
							<TD width="122"><h:outputText value="Description" /></TD>
							<TD width="184"><h:inputTextarea id="productDescription_txt"
								value="#{productGeneralInformationBackingBean.productDescription}"
								readonly="true"></h:inputTextarea> <h:inputHidden
								id="productDescription_txtHidden"
								value="#{productGeneralInformationBackingBean.productDescription}"></h:inputHidden>
							</TD>
							<TD width="40"><f:verbatim></f:verbatim></TD>
						</TR>
						<TR valign="top">
							<TD width="5"></TD>
							<TD width="122"></TD>
							<TD width="184"><f:verbatim /></TD>
							<TD width="24"><f:verbatim /></TD>
						</TR>
					</TBODY>
				</TABLE>

				</FIELDSET>
		
				<FIELDSET
					style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
				<TABLE border="0" cellspacing="0" cellpadding="3" class="outputText"
					width="100%">
					<TBODY>
						<tr>
							<td width="4%"><input type="checkbox" id="checkall"
								disabled="disabled"></td>
							<td align="left" width="71%">Check In</td>
							<td align="left" width="25%">
							<table Width=100%>
								<tr>
									<td>State</td>
									<td>: <h:outputText
										value="#{productGeneralInformationBackingBean.state}" /> <h:inputHidden
										id="stateHidden"
										value="#{productGeneralInformationBackingBean.state}"></h:inputHidden>
									</td>
								</tr>
								<tr>
									<td>Status</td>
									<td>: <h:outputText
										value="#{productGeneralInformationBackingBean.status}" /> <h:inputHidden
										id="statusHidden"
										value="#{productGeneralInformationBackingBean.status}"></h:inputHidden>
									</td>
								</tr>
								<tr>
									<td>Version</td>
									<td>: <h:outputText
										value="#{productGeneralInformationBackingBean.version}" /> <h:inputHidden
										id="versionHidden"
										value="#{productGeneralInformationBackingBean.version}"></h:inputHidden>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</TBODY>
				</TABLE>
				</FIELDSET>
			</h:form></td>
		</tr>
	</table>
	</BODY>
</f:view>

<script>
window.print();</script>
</HTML>


