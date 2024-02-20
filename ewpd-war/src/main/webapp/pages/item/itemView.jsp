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

<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/prototype.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript" src="../../js/rico.js"></script>
<script language="JavaScript" src="../../js/wpd.js"></script>
<script language="JavaScript" src="../../js/tigra_tables.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript">
	 var cal1 = new CalendarPopup();
	 cal1.showYearNavigation();
	 function noenter(){
  	 return !(window.event && window.event.keyCode == 13); 
	 }
</script>
<TITLE>View Item</TITLE>
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
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><h:inputHidden value="#{itemBackingBean.itemInfo}" /> <jsp:include
					page="../navigation/top_view_print_menu.jsp"></jsp:include></TD>
			</TR>

			<TR>
				<TD><h:form styleClass="form" id="itemForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD width="273" valign="top" class="leftPanel">


							<DIV class="treeDiv"><!-- Space for Tree  Data	--></DIV>

							</TD>

							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message value="#{itemBackingBean.validationMessages}"></w:message>
										</TD>
									</TR>
								</TBODY>
							</TABLE>

							<!-- Table containing Tabs -->
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
							<!-- End of Tab table -->

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

							<!--	Start of Table for actual Data	-->
							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText">
								<TBODY>
									<TR>
										<TD width="168"><h:outputText value="Catalog Name " /></TD>
										<TD width="177"><h:outputText
											value="#{itemBackingBean.catalogName}" /> <h:inputHidden
											id="catalogNme" value="#{itemBackingBean.catalogName}" /></TD>

									</TR>
									<TR>
										<TD width="168"><h:outputText value="Primary Code " /></TD>
										<TD width="177"><h:outputText
											value="#{itemBackingBean.primaryCode}" /></TD>
									</TR>



									<TR>
										<TD height="25" width="168"><h:outputText id="secondaryCode"
											value="Secondary Code" /></TD>
										<TD height="25" width="177"><h:outputText
											value="#{itemBackingBean.secondaryCode}" /></TD>
									</TR>


									<TR>
										<TD height="25" width="168" valign="top"><h:outputText
											value="Description " /></TD>
										<TD width="177"><h:outputText
											value="#{itemBackingBean.description}" /></TD>
									</TR>
									<!-- CARS START -->
									<TR id="frequencyFlagTR" style="display:none;">
										<TD height="25" width="168" valign="top"><h:outputText
											value="Frequency Required " /></TD>
										<TD width="177"><h:outputText
											value="#{itemBackingBean.frequencyFlag}" /></TD>
									</TR>
									<!-- CARS END -->
									<TR>
										<TD height="25" width="168"><h:outputText id="createdBy"
											value="Created By" /></TD>
										<TD width="176"><h:outputText
											value="#{itemBackingBean.createdUser}" /></TD>
									</TR>
									<TR>
										<TD height="25" width="168"><h:outputText id="Time"
											value="Created Date" /></TD>
										<TD width="176"><h:outputText
											value="#{itemBackingBean.createdTimestamp}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
										</TD>
									</TR>
									<TR>
										<TD height="25" width="168"><h:outputText id="updatedBy"
											value="Last Updated By" /></TD>
										<TD width="176"><h:outputText
											value="#{itemBackingBean.lastUpdatedUser}" /></TD>
									</TR>
									<TR>
										<TD height="25" width="168"><h:outputText id="updatedTime"
											value="Last Updated Date" /></TD>
										<TD width="176"><h:outputText
											value="#{itemBackingBean.lastUpdatedTimestamp}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
										</TD>
									</TR>

								</TBODY>
							</TABLE>
							<!--	End of Page data	--></FIELDSET>
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
<script>
var catalogName = document.getElementById('itemForm:catalogNme').value.toUpperCase();
if(catalogName == "QUALIFIER"){
		frequencyFlagTR.style.display = '';
}else{
		frequencyFlagTR.style.display = 'none';
}
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="itemGenInfo" /></form>

</HTML>
