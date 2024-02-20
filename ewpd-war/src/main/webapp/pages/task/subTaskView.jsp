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

<TITLE>Sub-Task View</TITLE>
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
	<h:form styleClass="form" id="subTaskViewForm">
		<h:inputHidden id="viewSelectedTaskId"
			value="#{taskBackingBean.viewTaskId}" />
		<table width="100%" cellpadding="0" cellspacing="0">

			<TR>
				<TD><jsp:include page="../navigation/top_view_print_menu.jsp"></jsp:include>
				</TD>
			</TR>
			<tr>
				<td>

				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="150" valign="top" class="leftPanel">


						<DIV class="treeDiv"><!-- Space for Tree  Data	--></DIV>

						</TD>


						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message value="#{taskBackingBean.validationMessages}"></w:message>
									</TD>
								</tr>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="25%">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:outputText
											value=" General Information" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="3" height="21" /></td>
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
							class="outputText" width="100%">
							<TBODY>
								<TR>
									<TD width="28%">&nbsp;<h:outputText id="SubTaskNameStar"
										value="Name " /></TD>
									<TD align="left" width="33%"><h:outputText
										value="#{taskBackingBean.taskName}" /></TD>
									<TD width="55%"></TD>
								</TR>
								<TR>
									<TD width="28%" valign="top">&nbsp;<h:outputText id="descriptionStar"
										value="Description " /></TD>
									<TD align="left" width="33%"><h:outputText
										value="#{taskBackingBean.description}" /></TD>
									<TD width="55%"></TD>
								</TR>
								<TR>
									<TD width="28%">&nbsp;<h:outputText id="SubTaskTypeStar"
										value="Type " /></TD>
									<TD align="left" width="33%"><h:outputText
										value="#{taskBackingBean.taskType}" /></TD>
									<TD width="55%"></TD>
								</TR>

								<TR>
									<TD width="28%">&nbsp;<h:outputText id="ModuleStar"
										value="Module " /></TD>
									<TD align="left" width="33%"><h:outputText
										value="#{taskBackingBean.moduleName}" /></TD>
									<TD width="55%"></TD>
								</TR>

								<TR>
									<TD width="28%">&nbsp;<h:outputText id="parentTaskStar"
										value="Parent Task " /></TD>
									<TD align="left" width="33%"><h:outputText
										value="#{taskBackingBean.subTaskParent}" /></TD>
									<TD width="55%"></TD>
								</TR>



								<TR>
									<TD width="28%">&nbsp;<h:outputText value="Created By" /></TD>
									<TD align="left" width="33%"><h:outputText
										value="#{taskBackingBean.createdUser}" /></TD>
									<TD width="55%"></TD>
								</TR>

								<TR>
									<TD width="28%">&nbsp;<h:outputText value="Created Date" /></TD>
									<TD align="left" width="33%"><h:outputText
										value="#{taskBackingBean.createdTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText></TD>
									<TD width="55%"></TD>
								</TR>


								<TR>
									<TD width="28%">&nbsp;<h:outputText value="Last Updated By" />
									</TD>
									<TD align="left" width="33%"><h:outputText
										value="#{taskBackingBean.lastUpdatedUser}" /></TD>
									<TD width="55%"></TD>
								</TR>


								<TR>
									<TD width="28%">&nbsp;<h:outputText value="Last Updated Date" />
									</TD>
									<TD align="left" width="33%"><h:outputText
										value="#{taskBackingBean.lastUpdatedTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText></TD>
									<TD width="55%"></TD>
								</TR>





							</TBODY>
						</TABLE>
						<!--	End of Page data	--></fieldset>
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields --> <!-- End of hidden fields  --> </h:form>
				</TD>
			</tr>
			<TR>
				<TD><%@include file="../navigation/bottom_view.jsp"%></TD>
			</TR>
		</table>
	</BODY>
</f:view>
<!-- space for script -->
<script>
	//copyHiddenToDiv_ewpd('catalogViewForm:txtLob','lobDiv',2,1); 
	//copyHiddenToDiv_ewpd('catalogViewForm:txtBusinessEntity','BusinessEntityDiv',2,1); 
	//copyHiddenToDiv_ewpd('catalogViewForm:txtBusinessGroup','BusinessGroupDiv',2,1); 
    
	</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="subTaskGenInfo" /></form>
</HTML>
