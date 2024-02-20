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

<TITLE>View Task General Information</TITLE>
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
<script language="JavaScript">
	 var cal1 = new CalendarPopup();
	 cal1.showYearNavigation();
</script>
<BASE target="_self" />
</HEAD>
<f:view>
	<BODY>
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<h:inputHidden value="#{taskBackingBean.viewTask}" />
				<TD><jsp:include page="../navigation/top_view.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="productStructureGeneralInfoForm">

					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD width="273" valign="top" class="leftPanel"></TD>

							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD></TD>
									</TR>
								</TBODY>
							</TABLE>
							<TABLE width="200" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<TR>
									<TD width="100%">
									<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
											<TD width="186" class="tabActive"><h:outputText
												value=" General Information" /></TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<TD width="100%">
									<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
										<TBODY>
											<TR>
												<TD width="3" align="left"><IMG
													src="../../images/tabNormalLeft.gif" alt="Tab LeftNormal"
													width="3" height="21"></TD>
												<TD class="tabNormal"><h:commandLink
													action="#{taskBackingBean.loadSubTaskConfiguration}"
													id="linkToSubTaskConfiguration">
													<h:outputText id="labelBC" value="Sub-Task Configuration"></h:outputText>
												</h:commandLink></TD>
												<TD width="2" align="right"><IMG
													src="../../images/tabNormalRight.gif"
													alt="Tab Right Normal" width="4" height="21"></TD>
											</TR>
										</TBODY>
									</TABLE>
									</TD>
									<TD width="100%"></TD>
								</TR>
							</TABLE>

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText" width="100%">
								<TBODY>

									<TR>
										<TD width="168"><h:outputText value="Name " /></TD>
										<TD width="177"><h:outputText
											value="#{taskBackingBean.taskName}" /></TD>

									</TR>
									<TR>
										<TD width="168"><h:outputText value="Type " /></TD>
										<TD width="177"><h:outputText
											value="#{taskBackingBean.taskType}" /></TD>
									</TR>

									<TR>
										<TD height="25" width="168" valign="top"><h:outputText value="Description " />
										</TD>
										<TD width="177"><h:outputText
											value="#{taskBackingBean.description}" /></TD>
									</TR>
									<TR>
										<TD height="25" width="168"><h:outputText id="createdBy"
											value="Created By" /></TD>
										<TD width="176"><h:outputText
											value="#{taskBackingBean.createdUser}" /></TD>
									</TR>
									<TR>
										<TD height="25" width="168"><h:outputText id="Time"
											value="Created Date" /></TD>
										<TD width="176"><h:outputText
											value="#{taskBackingBean.createdTimestamp}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
										</TD>
									</TR>
									<TR>
										<TD height="25" width="168"><h:outputText id="updatedBy"
											value="Last Updated By" /></TD>
										<TD width="176"><h:outputText
											value="#{taskBackingBean.lastUpdatedUser}" /></TD>
									</TR>
									<TR>
										<TD height="25" width="168"><h:outputText id="updatedTime"
											value="Last Updated Date" /></TD>
										<TD width="176"><h:outputText
											value="#{taskBackingBean.lastUpdatedTimestamp}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
										</TD>
									</TR>

								</TBODY>
								</br>
							</TABLE>
							</FIELDSET>
							</br>


							</TD>
						</TR>
					</TABLE>
				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom_view.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="generalInfo" /></form>

</HTML>
