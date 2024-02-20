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
<title>Migration Wizard</title>
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
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>

</HEAD>
<f:view>
	<body>
	<hx:scriptCollector id="scriptCollector1">
		<h:inputHidden id="hidden1"
			value="#{confirmMigrationContractBackingBean.updateLastAccessed}"></h:inputHidden>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<TR>
				<TD><%
		javax.servlet.http.HttpServletRequest httpReq = (javax.servlet.http.HttpServletRequest) javax.faces.context.FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		httpReq
				.setAttribute("breadCrumbTextLeft",
						"Administration >> Contract Migration Wizard >> Confirm Migration (Step 10)");
	%> <jsp:include page="../navigation/top_migration.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="wizardForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>
							<TD width="273" valign="top" class="leftPanel">
							<DIV class="treeDiv"><!-- Space for Tree  Data	--> <jsp:include
								page="../migration/migrationWizardTree.jsp">
							</jsp:include></DIV>
							</TD>
							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message
											value="#{confirmMigrationContractBackingBean.validationMessages}"></w:message>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<tr>
									<td width="100%"><b>Step10 : Confirm Migration </b></td>
								</tr>
								<tr>
									<td>Click Confirm to migrate contract to ET-Auto Bagging system
									if the mapping is complete and the application can perform the
									migration.</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
							</TABLE>
							<!--
							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
							-->
							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText">
								<TBODY>
									<tr>
										<td>
										<center><b> Press confirm to create ET autobagging date
										segments</b></center>
										</td>
									</tr>
									<tr>
										<td>
										<table width="20%" align="center">
											<tr>
												<td width="193"><span
													style="margin-left:6px;margin-right:6px;"> <h:commandButton
													value="Back" styleClass="wpdButton"
													action="#{confirmMigrationContractBackingBean.getPreviousPage}">
												</h:commandButton> </span></td>
												<td><span style="margin-left:6px;margin-right:6px;"> <h:commandButton
													value="Confirm" styleClass="wpdButton"
													onclick="return confirmMigration();"
													action="#{confirmMigrationContractBackingBean.confirmMapping}">
												</h:commandButton></span></td>
												<td><span style="margin-left:6px;margin-right:6px;"> <h:commandButton
													value="Cancel" styleClass="wpdButton"
													onclick="deleteContract();return false;">
												</h:commandButton></span></td>
												<h:commandLink id="cancelMigrationLink"
													style="display:none; visibility: hidden;"
													action="#{migrationGeneralInfoBackingBean.cancelMigration}">
													<f:verbatim />
												</h:commandLink>
												<h:inputHidden id="benefitYrIndWarningMessage"
													value="#{confirmMigrationContractBackingBean.benefitYrIndWarningMessage}"></h:inputHidden>
											</tr>
										</table>
										</td>
									</tr>
								</TBODY>
							</TABLE>
							<!--	End of Page data	--> <!--					</FIELDSET>--></td>
						</tr>
					</table>
				</h:form></TD>
			</TR>
			<TR>
				<td><%@ include file="../navigation/bottom_view.jsp"%></td>
			</TR>
		</TABLE>

	</hx:scriptCollector>
	</BODY>
</f:view>
<script language="JavaScript">
function confirmMigration() {
	msg = getObj('wizardForm:benefitYrIndWarningMessage').value;
	if(msg == null || trim(msg) == '')
		return true;

	if(window.confirm(msg)) 
		return true;
	else
		return false;

}
function deleteContract(){
		var message = "Are you sure you want to cancel? All data saved during this migration will be lost"
		if(window.confirm(message)){
			submitLink('wizardForm:cancelMigrationLink');
		}else{
				return false;
		}
}
</script>


</html>
