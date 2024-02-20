<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/mandate/MandatePrint.java" --%><%-- /jsf:pagecode --%>
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

<script language="JavaScript" src="../../js/wpd.js"></script>
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript">
	 var cal1 = new CalendarPopup();
	 cal1.showYearNavigation();

</script>
<TITLE>Edit Mandate</TITLE>
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
			<h:inputHidden id="viewMandateKey"
				value="#{mandateBackingBean.viewMandateKey}">
			</h:inputHidden>
			<td><h:form styleClass="form" id="mandateForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>



						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message value="#{mandateBackingBean.validationMessages}"></w:message>
									</TD>
								</tr>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs --> <!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	-->
						<TABLE border="0" cellspacing="0" cellpadding="0"
							class="outputText">
							<TBODY>
								<TR>
									<TD height="25" width="168"><h:outputText value="Mandate Name " /></TD>
									<TD width="183"><h:outputText id="txtMandateNm"
										value="#{mandateBackingBean.mandateName}" /></TD>
								</TR>

								<TR>
									<TD height="25" width="168"><h:outputText
										value="Citation Number " /></TD>
									<TD width="183"><h:outputText id="txtCitation"
										value="#{mandateBackingBean.citationNumber}" /></TD>
								</TR>

								<TR>
									<TD height="25" width="168"><h:outputText
										id="effectiveDateOutText" value="Effective Date " /></TD>
									<TD width="183"><h:outputText id="effectiveDate_txt"
										value="#{mandateBackingBean.effectiveDate}" /> <span
										class="dateFormat">(mm/dd/yyyy)</span></TD>

								</TR>


								<TR>
									<TD height="25" width="168"><h:outputText
										id="expiryDateOutText" value="Expiry Date " /></TD>
									<TD width="183"><h:outputText id="expiryDate_txt"
										value="#{mandateBackingBean.expiryDate}" /> <span
										class="dateFormat">(mm/dd/yyyy)</span></TD>
								</TR>
								<TR>
									<TD height="25" width="168"><h:outputText id="mandateType"
										value="Mandate Type " /></TD>
									<TD height="25" width="183"><h:outputText
										id="mandateTypeName_txt"
										value="#{mandateBackingBean.mandateTypeDesc}" /></TD>
								</TR>
								<TR>
									<TD height="25" width="168"><h:outputText id="jurisdiction"
										value="Jurisdiction " /></TD>
									<TD width="183"><h:outputText id="jurisdiction_txt"
										value="#{mandateBackingBean.jurisdictionDesc}" /></TD>
								</TR>
								<TR>
									<TD height="25" width="168"><h:outputText id="groupSize"
										value="Group Size " /></TD>
									<TD height="25" width="183"><h:outputText id="groupSizeName"
										value="#{mandateBackingBean.groupSizeDesc}" /></TD>
								</TR>
								<TR>
									<TD height="25" width="168"><h:outputText
										id="fundingArrangement" value="Funding Arrangement " /></TD>
									<TD height="25" width="183"><h:outputText id="fundingName"
										value="#{mandateBackingBean.fundingArrangementDesc}" /></TD>
								</TR>
								<TR>
									<TD height="25" width="168"><h:outputText value="Description" />
									</TD>
									<TD width="183"><h:outputText id="productDescription_txt"
										value="#{mandateBackingBean.description}"></h:outputText></TD>


								</TR>
								<TR>
								</TR>
								<TR></TR>
								<TR>
									<TD height="25" width="168"><h:outputText
										id="outTxtCreatedUserId" value="Created By" /></TD>
									<TD><h:outputText id="createdUserId"
										value="#{mandateBackingBean.createdUser}" /></TD>
								</TR>
								<TR>
									<TD height="10" width="168"><h:outputText
										id="outTxtCreatedDate" value="Created Date" /></TD>
									<TD><h:outputText id="createdDate"
										value="#{mandateBackingBean.createdTimestamp}" ><f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText>
									<h:outputText value="#{requestScope.timezone}"></h:outputText></TD>
								</TR>
								<TR>
									<TD height="10" width="168"><h:outputText
										id="outTxtUpdatedUserId" value="Last Updated By" /></TD>
									<TD><h:outputText id="updatedUserId"
										value="#{mandateBackingBean.lastUpdatedUser}" /></TD>
								</TR>
								<TR>
									<TD height="10" width="168"><h:outputText
										id="outTxtUpdatedDate" value="Last Updated Date" /></TD>
									<TD><h:outputText id="updatedDate"
										value="#{mandateBackingBean.lastUpdatedTimestamp}" ><f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText>
									<h:outputText value="#{requestScope.timezone}"></h:outputText></TD>
								</TR>

								<TR>
									<TD width="168"></TD>
									<TD width="183">&nbsp;</TD>
								</TR>
							</TBODY>
						</TABLE>
						<!--	End of Page data	--></fieldset>
						<br>
						<FIELDSET
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<table border="0" cellspacing="0" cellpadding="0" width="100%">
							<tr>

								<td>
								<table>
									<tr>
										<td width=168><h:outputText id="outTxtState" value="State" /></td>
										<td>:&nbsp;<h:outputText id="state"
											value="#{mandateBackingBean.state}" /></td>
									</tr>
									<tr>
										<td width=168><h:outputText value="Status" /></td>
										<td>:&nbsp;<h:outputText value="#{mandateBackingBean.status}" />

										</td>
									</tr>
									<tr>
										<td width=168><h:outputText value="Version" /></td>
										<td>:&nbsp;<h:outputText value="#{mandateBackingBean.version}" /></td>
										<h:inputHidden id="versionHidden"
											value="#{mandateBackingBean.version}" />
									</tr>
									<tr>
										<td><h:inputHidden id="statusHidden"
											value="#{mandateBackingBean.status}" /></td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
						</FIELDSET>
						</TD>
					</TR>





				</table>


			</h:form></td>
		</tr>

	</table>
	</BODY>
</f:view>

<script>
window.print();
</script>
</HTML>



