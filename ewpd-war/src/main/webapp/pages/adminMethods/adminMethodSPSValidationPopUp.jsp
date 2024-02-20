

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
<BASE target="_self" />
<TITLE>Admin Method SPS Validation popup</TITLE>
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
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR><td><h:inputHidden id="setBreadCrumb"
													value="#{adminMethodSPSValidationBackingBean.breadCrumbText}"></h:inputHidden></td></TR>
			<TR>
				<TD><jsp:include page="../navigation/top_breadcrumb_print.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="adminMethodForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD width="273" valign="top" class="leftPanel">
							<DIV class="treeDiv"><jsp:include
								page="../adminMethods/adminMethodContractCodedSPSTree.jsp"></jsp:include>
								<h:inputHidden id="loadTree" binding="#{adminmethodContractCodedSPSTreeBackingBean.loadTree}" />
							</DIV>
							</TD>


							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message></w:message></TD>
									</TR>
								</TBODY>
							</TABLE>
							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

							<TABLE width="100%" cellspacing="0" cellpadding="0">
								<TR>
										<TD  width="800">&nbsp;&nbsp;<h:outputText
											id="Test" value="All the SPS parameters for any one of the available Groups need to be coded for an Super Process Step  "
											style="font-weight:bold;" /></TD>
								</TR>
							</TABLE>
							<TABLE width="100%" cellspacing="0" cellpadding="0">
								<TR>
										<TD  width="800">&nbsp;&nbsp;<h:outputText
											id="Test23" value="    "
											style="font-weight:bold;" /></TD>
								</TR>
							</TABLE>
							<TABLE width="40%" cellspacing="0" cellpadding="0">
								<TR>
										<TD><h:graphicImage id="gi" alt="Coded SPS Parameters"   
								  value="/images/action_check.gif" width="15" height="15"  ></h:graphicImage></TD>
										<TD><h:outputText
											id="Test3" value=" = Coded SPS Parameters "
											style="font-weight:bold;" /></TD>
										<TD><h:graphicImage id="gi1" alt="Not Coded SPS Parameters"   
								  value="/images/action_delete.gif" width="15" height="15"  ></h:graphicImage></TD>
										<TD> <h:outputText
											id="TEst4" value=" = Not Coded SPS Parameters "
											style="font-weight:bold;" /></TD>
								</TR>


							</TABLE>
							<!--<TABLE width="100%" cellspacing="0" cellpadding="0">
								<TR>
										<TD  width="800">&nbsp;&nbsp;<h:outputText
											id="Test231" value="    "
											style="font-weight:bold;" /></TD>
								</TR>
							</TABLE>
							--><TABLE width="100%" cellspacing="0" cellpadding="0">
								<TR class="dataTableColumnHeader" height="25px">
									<TD>&nbsp;&nbsp;<h:outputText id="tierHeader0" value=" Processing Methods "
											rendered="#{adminMethodSPSValidationBackingBean.generalMethodList != null}"/>
									</TD>
								</TR>
							</TABLE>
							<TABLE width="100%" cellspacing="0" cellpadding="0">
								<TR>
									<TD>
									<DIV id="resultHeaderDiv" style="height:250px;overflow:auto;">
										<h:panelGrid id="displayTable"
											binding="#{adminMethodSPSValidationBackingBean.panelForValidation}">
										</h:panelGrid>
										<!--<table cellspacing="1" cellpadding="0">
											<tr/><tr/>
											<tr>
												<td></td>
											</tr>
											<tr/><tr/> 
										</table>
									--></DIV>
									</TD>
								</TR>
							</TABLE>
							<!-- CARS START -->

							<TABLE width="100%" cellspacing="0" cellpadding="0">
								<TR class="dataTableColumnHeader" height="25px">
									<TD>&nbsp;&nbsp;&nbsp;&nbsp;<div id="tierHeaderDiv"><h:outputText id="tierHeader" value="#{adminMethodSPSValidationBackingBean.tierHeading}"
											rendered="#{adminMethodSPSValidationBackingBean.tierSPSMap != null}"/></div>
									</TD>
								</TR>
								<TR>
									<TD>
									<DIV id="resultTierDiv" style="height:250px;overflow:auto;">
										<h:panelGrid id="tierDisplayTable"
											binding="#{adminMethodSPSValidationBackingBean.tierDisplayPanel}">
										</h:panelGrid>
										<!--<table cellspacing="1" cellpadding="0">
											<tr/><tr/>
											<tr>
												<td></td>
											</tr>
											<tr/><tr/> 
										</table>
									--></DIV>
									</TD>
								</TR>
							</TABLE>
							<!-- CARS END -->
							</fieldset>
							</TD>
						</tr>
					</table>

				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
			</TR>
<tr>
<TD>
<form name="printForm"><input id="currentPrintPage" type="hidden"
			name="currentPrintPage" value="adminMethodSPSValidationPrint" />
</TD>
</tr>

		</TABLE>
	</BODY>
</f:view>
</html>

