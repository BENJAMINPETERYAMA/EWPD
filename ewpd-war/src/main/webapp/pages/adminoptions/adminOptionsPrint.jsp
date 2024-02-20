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

<TITLE>Print Admin Options</TITLE>
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
	<TABLE width="100%" cellpadding="0" cellspacing="0">
		<tr><td>&nbsp; </td></tr>
		<TR>
					<TD>  <FIELDSET style="margin-left:15px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:98.5%">
									<h:inputHidden id="viewAdminOptions"
				value="#{createAdminOptionBackingBean.viewAdminOptions}"></h:inputHidden>
                                    <h:outputText id="breadcrumb" 

                                          value="#{createAdminOptionBackingBean.breadCrumpText}">

                                    </h:outputText>

                              </FIELDSET>


					</TD>
				</TR>
		<TR>
			
			
			<TD><h:form styleClass="form" id="adminOptionForm">

				<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>
						<TD colspan="2" valign="top" class="ContentArea"><!-- Table containing Tabs -->
						<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<TR>
								<TD width="200">
							
								</TD>
								<TD width="200"></TD>
								<TD width="100%"></TD>
							</TR>
						</TABLE>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<BR />
						<!--	Start of Table for actual Data	-->
						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText">
							<TBODY>
								<TR>
									<TD width="180"><h:outputText id="outTxtAdminName" value="Name"
										styleClass="#{createAdminOptionBackingBean.requiredAdminName ?
										 'mandatoryError' : 'mandatoryNormal'}" /></TD>
									<TD width="200"><h:outputText id="txtAdminName"
										value="#{createAdminOptionBackingBean.adminName}" /> <h:inputHidden
										id="hiddenAdminName"
										value="#{createAdminOptionBackingBean.adminName}"></h:inputHidden>
									</TD>
								</TR>
								<TR>
									<TD><h:outputText id="outTxtTerm" value="Term"
										styleClass="#{createAdminOptionBackingBean.requiredTerm ?
										 'mandatoryError' : 'mandatoryNormal'}" /></TD>
									<TD><h:outputText id="txtTerm"
										value="#{createAdminOptionBackingBean.term}"></h:outputText> <h:inputHidden
										id="hiddenTerm" value="#{createAdminOptionBackingBean.term}"></h:inputHidden>
									</TD>
									<TD width="25" valign="top"></TD>
								</TR>
								<TR>
									<TD><h:outputText id="outTxtQualifier" value="Qualifier" /></TD>
									<TD><h:outputText id="txtQualifier"
										value="#{createAdminOptionBackingBean.qualifier}"></h:outputText>
									<h:inputHidden id="hiddenQualifier"
										value="#{createAdminOptionBackingBean.qualifier}"></h:inputHidden>
									</TD>
									<TD valign="top"></TD>
								</TR>

								<TR>
									<TD><h:outputText id="outTxtCreatedUserId"
										value="Created By" /></TD>
									<TD><h:outputText id="createdUserId"
										value="#{createAdminOptionBackingBean.createdUserId}" /></TD>
								</TR>
								<TR>
									<TD><h:outputText id="outTxtCreatedDate" value="Created Date" />
									</TD>
									<TD><h:outputText id="createdDate"
										value="#{createAdminOptionBackingBean.createdDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText></TD>
								</TR>
								<TR>
									<TD><h:outputText id="outTxtUpdatedUserId"
										value="Last Updated By" /></TD>
									<TD><h:outputText id="updatedUserId"
										value="#{createAdminOptionBackingBean.updatedUserId}" /></TD>
								</TR>
								<TR>
									<TD><h:outputText id="outTxtUpdatedDate"
										value="Last Updated Date" /></TD>
									<TD><h:outputText id="updatedDate"
										value="#{createAdminOptionBackingBean.updatedDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText></TD>
								</TR>

								<TR>
									<TD>&nbsp;</TD>
								</TR>

							</TBODY>
						</TABLE>
						</fieldset>
						<br>

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<table border="0" cellspacing="0" cellpadding="3" width="100%">
							<tr>

								<td>
								<table Width=100%>
									<tr>
										<td width="180"><h:outputText id="outTxtState" value="State" /></td>
										<td>:&nbsp;<h:outputText id="state"
											value="#{createAdminOptionBackingBean.state}" /></td>
									</tr>
									<tr>
										<td><h:outputText id="outTxtStatus" value="Status" /></td>
										<td>:&nbsp;<h:outputText id="status"
											value="#{createAdminOptionBackingBean.status}" /></td>
									</tr>
									<tr>
										<td><h:outputText id="outTxtVersion" value="Version" /></td>
										<td>:&nbsp;<h:outputText id="version"
											value="#{createAdminOptionBackingBean.version}" /></td>
									</tr>
								</table>
								</td>
							</tr>

						</table>

						</fieldset>
						<br />

						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->

				<h:inputHidden id="hiddenCreatedUser"
					value="#{createAdminOptionBackingBean.createdUserId}"></h:inputHidden>
				<h:inputHidden id="hiddenCreatedDate"
					value="#{createAdminOptionBackingBean.createdDate}">
					<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
				</h:inputHidden>
				<h:inputHidden id="hiddenUpdatedUser"
					value="#{createAdminOptionBackingBean.updatedUserId}"></h:inputHidden>
				<h:inputHidden id="hiddenUpdatedDate"
					value="#{createAdminOptionBackingBean.updatedDate}">
					<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
				</h:inputHidden>
				<h:inputHidden id="time" value="#{requestScope.timezone}">
				</h:inputHidden>


				<h:inputHidden id="hiddenStatus"
					value="#{createAdminOptionBackingBean.status}"></h:inputHidden>
				<h:inputHidden id="hiddenVersion"
					value="#{createAdminOptionBackingBean.version}"></h:inputHidden>
				<h:inputHidden id="adminId"
					value="#{createAdminOptionBackingBean.adminOptionId}"></h:inputHidden>



				<!-- End of hidden fields  -->

			</h:form></TD>
		</TR>

	</TABLE>
	</BODY>
</f:view>

<script>
formatTildaToComma("adminOptionForm:txtTerm");
formatTildaToComma("adminOptionForm:txtQualifier");
// formatTildaToComma("adminOptionForm:txtReference");


</script>
<script>window.print();</script>
</HTML>

