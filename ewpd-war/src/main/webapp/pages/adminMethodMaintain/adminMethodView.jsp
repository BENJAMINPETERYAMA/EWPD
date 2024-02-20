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
<style type="text/css">
.viewFormTxtAreaReadOnly {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 11px;
	width: 250px;
	height: 30px;	
	word-wrap: break-word;
    word-break:break-all; 
	background-color: white;	
	color: #000000;
}
</style>
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/prototype.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript" src="../../js/rico.js"></script>
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
<script language="JavaScript" src="../../js/tigra_tables.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript">
	 var cal1 = new CalendarPopup();
	 cal1.showYearNavigation();
	 function noenter(){
  	 return !(window.event && window.event.keyCode == 13); 
	 }
</script>

<TITLE>View Admin Method </TITLE>
<!-- <script language="JavaScript" src="../../js/wpd.js"></script> -->
</HEAD>
<f:view>
	<BODY>
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><jsp:include page="../navigation/top_view_print_menu.jsp">
					<h:inputHidden
						value="#{adminMethodMaintainBackingBean.loadAdminMethod}" />
				</jsp:include></TD>
			</TR>

			<TR>
				<TD><h:form styleClass="form" id="AdminMethodViewForm">

					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD width="273" valign="top" class="leftPanel">


							<DIV class="treeDiv"></DIV>

							</TD>

							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message></w:message></TD>
									</TR>
								</TBODY>
							</TABLE>


							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<TR>
									<TD width="200">
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
									<TD width="100%"></TD>
								</TR>
							</TABLE>


							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
							<div id="info">
							<TABLE border="0" cellspacing="3" cellpadding="2"
								class="outputText">
								<TBODY>
									<TR>
										<TD valign="top"><h:outputText value="Admin Method Number" /></TD>
										<TD><h:outputText id="adminMethodNo"
											value="#{adminMethodMaintainBackingBean.adminMethodNo}" /></TD>

									</TR>

									<TR>
										<TD valign="top"><h:outputText
											value="Admin Method Description" /></TD>
										<TD><div id="id1" class="viewFormTxtAreaReadOnly" ><h:outputText id="description"
											value="#{adminMethodMaintainBackingBean.description}" /></div></TD>

									</TR>

									<TR>
										<TD valign="top"><h:outputText value="Processing Method" /></TD>
										<TD><h:outputText id="processMethodDesc"
											value="#{adminMethodMaintainBackingBean.processMethodDesc}" />
										</TD>
									</TR>
										<TR>
										<TD valign="top" ><h:outputText
											value="Configuration"  />
											</TD>
										<TD >
										<h:dataTable styleClass="outputText"
											headerClass="dataTableHeader" id="viewConfigTable"
											var="AdminMethod" cellpadding="3" cellspacing="1" border="0"
											rendered="#{adminMethodMaintainBackingBean.configurationList != null}"
											value="#{adminMethodMaintainBackingBean.configurationList}"
											style="border: solid #7f9db9 1px; border-bottom-color: #7f9db9; border-bottom-style: solid; border-bottom-width: 1px; border-color: #7f9db9; border-left-color: #7f9db9; border-left-style: solid; border-left-width: 1px; border-right-color: #7f9db9; border-right-style: solid; border-right-width: 1px; border-style: solid; border-top-color: #7f9db9; border-top-style: solid; border-top-width: 1px; border-width: 1px; text-align: left"
											width="100%">
											<h:column>
												<h:outputText id="configDesc"
													value="#{AdminMethod.configDesc}"></h:outputText>
											</h:column>
										</h:dataTable>
									</TR>

									<TR>
										<TD valign="top" ><h:outputText value="Comments" /></TD>
										<TD><div id="commentsId" class="viewFormTxtAreaReadOnly" > <h:outputText id="comments" 
											value="#{adminMethodMaintainBackingBean.comments}" /></div></TD>

									</TR>
									
									<TR>
										<TD  valign="top" ><h:outputText
											value="Required Parameter" /></TD>
										<TD ><h:dataTable styleClass="requiredParam"
											headerClass="dataTableHeader" id="viewReqTable"
											var="AdminMethod" cellpadding="3" cellspacing="3" border="0"
											rendered="#{adminMethodMaintainBackingBean.reqParamGroups != null}"
											value="#{adminMethodMaintainBackingBean.reqParamGroups}" columnClasses="param"
											width="100%">
											<h:column>

												<h:outputText id="refDesc"
													value="#{AdminMethod.referenceDesc}"></h:outputText>
											</h:column>
										</h:dataTable>
									</TR>
										<tr>
									<td valign="top" width="27%"><span class="mandatoryNormal"
										id="creationDateId"></span><h:outputText
										value="Created By" /></td>
									<td width="29%"><h:outputText
										value="#{adminMethodMaintainBackingBean.createdUser}" /> <h:inputHidden
										id="createdUserHidden"
										value="#{adminMethodMaintainBackingBean.createdUser}" /></td>
									<td width="44%"><f:verbatim></f:verbatim></td>
								</tr>


								<tr >
									<td valign="top" width="27%"><span class="mandatoryNormal"
										id="createdBy"></span><h:outputText value="Created Date" /></td>
									<td width="29%"><h:outputText
										value="#{adminMethodMaintainBackingBean.createdDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="createdTimestampHidden"
										value="#{adminMethodMaintainBackingBean.createdDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden> <h:inputHidden id="time"
										value="#{requestScope.timezone}">
									</h:inputHidden></td>
									<td width="44%"><f:verbatim></f:verbatim></td>
								</tr>

								<tr>
									<td valign="top" width="27%"><span class="mandatoryNormal"
										id="updationDate"></span><h:outputText
										value="Last Updated By" /></td>
									<td width="29%"><h:outputText
										value="#{adminMethodMaintainBackingBean.lastUpdatedUser}" /> <h:inputHidden
										id="lastUpdatedUserHidden"
										value="#{adminMethodMaintainBackingBean.lastUpdatedUser}" /></td>
									<td width="44%"><f:verbatim></f:verbatim></td>
								</tr>


								<tr>
									<td valign="top" width="27%"><span class="mandatoryNormal"
										id="updateBy"></span><h:outputText
										value="Last Updated Date" /></td>
									<td width="29%"><h:outputText
										value="#{adminMethodMaintainBackingBean.lastUpdatedDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="lastUpdatedTimestampHidden"
										value="#{adminMethodMaintainBackingBean.lastUpdatedDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden></td>
									<td width="44%"><f:verbatim></f:verbatim></td>
								
									


								</TBODY>
							</TABLE>
							</div>
							</FIELDSET>
							</TD>
						</TR>
					</TABLE>


				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>
<script>
</script>
<script>
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="printAdminMethodViewPage" /></form>

</HTML>
