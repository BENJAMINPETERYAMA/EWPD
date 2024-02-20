<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/mandate/MandateView.java" --%><%-- /jsf:pagecode --%>
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
<BASE target="_self">
</HEAD>
<f:view>
	<BODY>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<h:inputHidden id="viewMandateKey"
				value="#{mandateBackingBean.viewMandateKey}">
			</h:inputHidden>
			<td><jsp:include page="../navigation/top_view.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td><h:form styleClass="form" id="mandateForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<DIV class="treeDiv"><!-- Space for Tree  Data	--></DIV>

						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message value="#{mandateBackingBean.validationMessages}"></w:message>
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
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:outputText
											value=" General Information" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="100%"></td>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	-->
						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText">
							<TBODY>
								<TR>
									<TD width="168"><h:outputText value="Mandate Name " /></TD>
									<TD width="183"><h:inputText
										styleClass="formInputFieldReadOnly" id="txtMandateNm"
										size="12" value="#{mandateBackingBean.mandateName}"
										readonly="true" /></TD>
								</TR>
								<TR>
									<TD width="168"><h:outputText value="Citation Number " /></TD>
									<TD width="183"><h:inputText
										styleClass="formInputFieldReadOnly" id="txtCitation" size="12"
										value="#{mandateBackingBean.citationNumber}" readonly="true" />
									</TD>
								</TR>
								<TR>
									<TD width="168"><h:outputText id="effectiveDateOutText"
										value="Effective Date " /></TD>
									<TD width="183"><h:inputText
										styleClass="formInputFieldReadOnly" size="12"
										id="effectiveDate_txt" maxlength="10"
										value="#{mandateBackingBean.effectiveDate}" readonly="true" />
									<span class="dateFormat">(mm/dd/yyyy)</span></TD>

								</TR>

								<TR>
									<TD width="168"><h:outputText id="expiryDateOutText"
										value="Expiry Date " /></TD>
									<TD width="183"><h:inputText
										styleClass="formInputFieldReadOnly" size="12"
										id="expiryDate_txt" maxlength="10"
										value="#{mandateBackingBean.expiryDate}" readonly="true" /> <span
										class="dateFormat">(mm/dd/yyyy)</span></TD>
								</TR>
								<TR>
									<TD height="25" width="168"><h:outputText id="mandateType"
										value="Mandate Type " /></TD>
									<TD height="25" width="183"><h:inputText
										styleClass="formInputFieldReadOnly" size="12"
										id="mandateTypeName_txt" maxlength="10"
										value="#{mandateBackingBean.mandateTypeDesc}" readonly="true" /></TD>
								</TR>
								<TR>
									<TD width="168"><h:outputText id="jurisdiction"
										value="Jurisdiction " /></TD>
									<TD width="183"><h:inputText
										styleClass="formInputFieldReadOnly" size="12"
										id="jurisdiction_txt" maxlength="10"
										value="#{mandateBackingBean.jurisdictionDesc}" readonly="true" />
									</TD>
								</TR>
								<TR>
									<TD height="25" width="168"><h:outputText id="groupSize"
										value="Group Size " /></TD>
									<TD height="25" width="183"><h:inputText
										styleClass="formInputFieldReadOnly" size="12"
										id="groupSizeName" maxlength="10"
										value="#{mandateBackingBean.groupSizeDesc}" readonly="true" /></TD>
								</TR>
								<TR>
									<TD height="25" width="168"><h:outputText
										id="fundingArrangement" value="Funding Arrangement " /></TD>
									<TD height="25" width="183"><h:inputText
										styleClass="formInputFieldReadOnly" size="12" id="fundingName"
										maxlength="10"
										value="#{mandateBackingBean.fundingArrangementDesc}"
										readonly="true" /></TD>
								</TR>
								<TR>
									<TD height="25" width="168"><h:outputText value="Description" />
									</TD>
									<TD width="183"><h:inputTextarea
										styleClass="formTxtAreaReadOnly" id="productDescription_txt"
										value="#{mandateBackingBean.description}" readonly="true"></h:inputTextarea>
									</TD>

								</TR>
								<TR>
									<TD><h:outputText id="outTxtCreatedUserId"
										value="Created By" /></TD>
									<TD><h:outputText id="createdUserId"
										value="#{mandateBackingBean.createdUser}" /></TD>
								</TR>
								<TR>
									<TD><h:outputText id="outTxtCreatedDate" value="Created Date" />
									</TD>
									<TD><h:outputText id="createdDate"
										value="#{mandateBackingBean.createdTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText></TD>
								</TR>
								<TR>
									<TD><h:outputText id="outTxtUpdatedUserId"
										value="Last Updated By" /></TD>
									<TD><h:outputText id="updatedUserId"
										value="#{mandateBackingBean.lastUpdatedUser}" /></TD>
								</TR>
								<TR>
									<TD><h:outputText id="outTxtUpdatedDate"
										value="Last Updated Date" /></TD>
									<TD><h:outputText id="updatedDate"
										value="#{mandateBackingBean.lastUpdatedTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText></TD>
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
						<table border="0" cellspacing="0" cellpadding="3" width="100%">
							<tr>
								<td width="15"></td>
								<td align="left"></td>
								<td align="left" width="127">
								<table Width=100%>
									<tr>
										<td><h:outputText id="outTxtState" value="State" /></td>
										<td>:&nbsp;<h:outputText id="state"
											value="#{mandateBackingBean.state}" /></td>
									</tr>
									<tr>
										<td><h:outputText value="Status" /></td>
										<td>:&nbsp;<h:outputText value="#{mandateBackingBean.status}" />

										</td>
									</tr>
									<tr>
										<td><h:outputText value="Version" /></td>
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
		<tr>
			<td><%@ include file="../navigation/bottom_view.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>

<script>
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="mandatePrint" /></form>
</HTML>


