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

<TITLE>Edit Admin Options</TITLE>
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
		<TR>
			<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="adminOptionForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message
										value="#{createAdminOptionBackingBean.validationMessages}"></w:message></TD>
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
											value=" Admin Options" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink id="thisId"
											onmousedown="copyAdminOptionInfo();"
											action="#{adminOptionQuestionnaireBackingBean.displayAdminQuestionTab}">
											<h:outputText value="Questionnaire" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="100%"></td>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<BR />
						<!--	Start of Table for actual Data	-->
						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText">
							<TBODY>
								<TR>
									<TD width="180"><h:outputText id="outTxtAdminName"
										value="Name*"
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
									<TD><h:inputText id="txtTerm"
										styleClass="formInputFieldForAdminOption" readonly="true"
										value="#{createAdminOptionBackingBean.term}"></h:inputText> <h:inputHidden
										id="hiddenTerm" value="#{createAdminOptionBackingBean.term}"></h:inputHidden>
									</TD>
									<TD width="25" valign="top"></TD>
								</TR>
								<TR>
									<TD><h:outputText id="outTxtQualifier" value="Qualifier" /></TD>
									<TD><h:inputText id="txtQualifier"
										styleClass="formInputFieldForAdminOption"
										value="#{createAdminOptionBackingBean.qualifier}"
										readonly="true"></h:inputText> <h:inputHidden
										id="hiddenQualifier"
										value="#{createAdminOptionBackingBean.qualifierId}"></h:inputHidden>
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
								<TR>
									<TD colspan="2"><!--<h:commandButton value="Save" onmousedown="javascript:savePageAction(this.id);"
										styleClass="wpdButton" tabindex="5"
										action="#{createAdminOptionBackingBean.updateAdminOption}">
									</h:commandButton> -->&nbsp;&nbsp;
									<TD>&nbsp;</TD>
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
								<td width="4%"><h:selectBooleanCheckbox
									value="#{createAdminOptionBackingBean.checkInOpted}"
									id="checkIn" tabindex="6" /></td>
								<td align="left" width="66%"><h:outputText id="outTxtCheckIn"
									value="Check In" /></td>
								<td align="left" width="30%">
								<table Width=100%>
									<tr>
										<td width="4%"><h:outputText id="outTxtState" value="State" /></td>
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
						<TABLE>
							<TR>
								<TD>&nbsp;&nbsp;<h:commandButton value="Done"
									styleClass="wpdButton" tabindex="7"
									action="#{createAdminOptionBackingBean.checkInAdminOption}">
								</h:commandButton></TD>
							</TR>
						</TABLE>
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


				<h:inputHidden id="hiddenState"
					value="#{createAdminOptionBackingBean.state}"></h:inputHidden>
				<h:inputHidden id="hiddenStatus"
					value="#{createAdminOptionBackingBean.status}"></h:inputHidden>
				<h:inputHidden id="hiddenVersion"
					value="#{createAdminOptionBackingBean.version}"></h:inputHidden>
				<h:inputHidden id="adminId"
					value="#{createAdminOptionBackingBean.adminOptionId}"></h:inputHidden>

				<h:inputHidden id="hiddenAdminOptionId"
					value="#{adminOptionQuestionnaireBackingBean.adminId}"></h:inputHidden>
				<h:inputHidden id="hiddenAdminOptionName"
					value="#{adminOptionQuestionnaireBackingBean.adminName}"></h:inputHidden>
				<h:inputHidden id="versionToNextPage"
					value="#{adminOptionQuestionnaireBackingBean.version}"></h:inputHidden>
				<h:inputHidden id="stateToNextPage"
					value="#{adminOptionQuestionnaireBackingBean.state}"></h:inputHidden>
				<h:inputHidden id="statusToNextPage"
					value="#{adminOptionQuestionnaireBackingBean.status}"></h:inputHidden>


				<!-- End of hidden fields  -->

			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>

<script>
copyHiddenToInputText('adminOptionForm:hiddenTerm','adminOptionForm:txtTerm',1);
copyHiddenToInputText('adminOptionForm:hiddenQualifier','adminOptionForm:txtQualifier',1); 
copyHiddenToInputText('adminOptionForm:hiddenReference','adminOptionForm:txtReference',1);

function copyAdminOptionInfo(){
	document.getElementById('adminOptionForm:hiddenAdminOptionId').value = document.getElementById('adminOptionForm:adminId').value;
	document.getElementById('adminOptionForm:hiddenAdminOptionName').value = document.getElementById('adminOptionForm:hiddenAdminName').value;
	document.getElementById('adminOptionForm:versionToNextPage').value = document.getElementById('adminOptionForm:hiddenVersion').value;
	document.getElementById('adminOptionForm:stateToNextPage').value = document.getElementById('adminOptionForm:hiddenState').value;
	document.getElementById('adminOptionForm:statusToNextPage').value = document.getElementById('adminOptionForm:hiddenStatus').value;
}
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="adminOption" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
</HTML>

