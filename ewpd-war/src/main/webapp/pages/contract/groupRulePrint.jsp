<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/contract/GroupRulePrint.java" --%><%-- /jsf:pagecode --%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
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
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.selectOrOptionColumn {
	width: 25%;
}
.shortDescriptionColumn {
	width: 75%;
}
</style>

<TITLE>WellPoint Product Database: Rule Print</TITLE>
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
		<h:form styleClass="form" id="GroupRulePrintForm">
		<TABLE width="100%" cellpadding="0" cellspacing="0">			
			<TR>
				<TD>
						<TABLE border="0" cellspacing="0" width="100%" cellpadding="0">
							<TR>
								<TD>
									<h:inputHidden id ="grouRuleID" value="#{contractRuleBackingBean.groupRuleHidden}"></h:inputHidden>
									<TABLE width="100%">
										<TR>
											<TD width="20%"><B> Rule ID</B></TD>
											<TD width="80%"><h:outputText value="#{contractRuleBackingBean.groupRule.ruleId}"/>
											</TD>
										</TR>
										<TR>
											<TD><B>Rule Description<B></TD>
											<TD><h:outputText value="#{contractRuleBackingBean.groupRule.ruleDesc}"/>
											</TD>
										</TR>
										<TR>
											<TD><B>Rule Version</B></TD>
											<TD><h:outputText value="#{contractRuleBackingBean.groupRule.ruleVersion}"/>
											</TD>
										</TR>
										<TR>
											<TD><B>Tag</B></TD>
											<TD><h:outputText value="#{contractRuleBackingBean.groupRule.tag}"/>
											</TD>
										</TR>
										
									</TABLE>
								</TD>
							</TR>
							<TR><TD>&nbsp;</TD> </TR>
							<TR>
								<TD>
									<table border="0" cellspacing="1" cellpadding="0" width="100%"
										bgcolor="#cccccc" >
										<tr>
										<TD width="100%">
											<B>Associated Rules</B></TD>
										</tr>
									</table>
								</TD>
							</TR>
							<TR>
								<TD>
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									bgcolor="#cccccc">
									<TR>
										<TD	width="25%"><B>Rule ID</B></td>
										<TD width="75%"><B>Rule Description</B></TD>
										
									</TR>
								</table>
								</TD>
							</TR>
							<TR>
								<TD>
										<h:dataTable headerClass="dataTableHeader"
											id="previousVersionsTable" var="rule" cellpadding="3"
											cellspacing="1"
											rendered="#{contractRuleBackingBean.groupRule.rules != null}"
											value="#{contractRuleBackingBean.groupRule.rules}"
											 border="0"
											width="100%" columnClasses="selectOrOptionColumn,shortDescriptionColumn">
										<h:column>
											
											<h:outputText value="#{rule.ruleId}"/>
										</h:column>
										<h:column>
											
											<h:outputText value="#{rule.ruleDesc}"/>
										</h:column>
										
										</h:dataTable>
								</TD>
							</TR>
						</TABLE>
				</TD>
			</TR>
			<TR><TD>&nbsp;</TD></TR>
			

		</TABLE>
		</h:form>
	</hx:scriptCollector>
	</BODY>
</f:view>

<script>
window.print();
</script>

</HTML>
