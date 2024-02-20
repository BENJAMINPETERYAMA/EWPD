<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/admin/ViewLog.java" --%><%-- /jsf:pagecode --%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<TITLE>Application Status Admin Page</TITLE>
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
                <td><jsp:include page="../navigation/top.jsp"></jsp:include></td>
        </tr>
        <tr>
            <td>
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <TR>
                        <TD width="273" valign="top" class="leftPanel">
                            <DIV class="treeDiv"></DIV>
                        </TD>
                        <TD colspan="2" valign="top" class="ContentArea">
                            <TABLE>
                                <TBODY>
                                    <tr>
                                            <TD><w:message /></TD>
                                    </tr>
                                </TBODY>
                            </TABLE>
							<h:form id="applicationStatusMaintainanceForm">
							<span class="pagetitle">Application Access Control</span>
							<p/>
							<span class="content" style="margin-left:2px">Current Status:</span><h:outputText value="Access Disabled" rendered="#{applicationStatus.accessDisabled}" style="background-color:#FF0000;font-weight:bold"></h:outputText>
											<h:outputText value="Access Enabled" rendered="#{!applicationStatus.accessDisabled}" style="background-color:#00FF00;font-weight:bold"></h:outputText>
							<p/>
							<h:panelGroup rendered="#{applicationStatus.accessDisabled}">
								<h:panelGrid columns="2">
									<h:outputText value="Disabled by: "></h:outputText>
									<h:outputText value="#{applicationStatus.adminUser}"></h:outputText>
									<h:outputText value="Disabled at: "></h:outputText>
									<h:outputText value="#{applicationStatus.statusChangedTime}"><f:convertDateTime pattern="MM/dd/yyyy hh:mm:ss z" /></h:outputText>
									<h:outputText value="Acess disable timeout at: "></h:outputText>
									<h:outputText value="#{applicationStatus.expiryTime}"><f:convertDateTime pattern="MM/dd/yyyy hh:mm:ss z" /></h:outputText>	
									<h:commandButton action="#{applicationStatus.enable}" styleClass="wpdbutton" value="Enable" style="margin-top:5px"></h:commandButton>	
								</h:panelGrid>
							</h:panelGroup>
							<h:panelGroup rendered="#{!applicationStatus.accessDisabled}">
								<h:panelGrid columns="2">
									<h:outputText value="Disable duration (in minutes):"></h:outputText>
									<h:inputText value="#{applicationStatus.lockDuration}" styleClass="formInputField" style="width:40px" maxlength="4" onkeypress="return isNumberKey(event);"></h:inputText>
									<h:commandButton action="#{applicationStatus.disable}" styleClass="wpdbutton" value="Disable" style="margin-top:5px"></h:commandButton>
								</h:panelGrid>
							</h:panelGroup>
							</h:form>
                        </TD>
                    </TR>
                    </table>
            </td>
        </tr>
        <tr>
                <td><%@ include file="../navigation/bottom.jsp"%></td>
        </tr>
    </table>
</f:view>
</HTML>