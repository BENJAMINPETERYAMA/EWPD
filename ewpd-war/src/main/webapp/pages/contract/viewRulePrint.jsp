<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/contract/ViewRulePrint.java" --%><%-- /jsf:pagecode --%>
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
<META http-equiv="PRAGMA" content="NO-CACHE">
<META HTTP-EQUIV="Expires" CONTENT="-1">
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
</script>

</script>

<BASE target="_self" />
</HEAD>
<f:view>
	<BODY>
	<hx:scriptCollector id="scriptCollector1">
	
<%-- 
		<w:message></w:message>
--%>
		<TABLE width="100%" cellpadding="0" cellspacing="0" border="0">
			<TR>
				<td>&nbsp;&nbsp;
				</td>
				<td>&nbsp;&nbsp;
				</td>
			</TR>


		</TABLE>
		&nbsp;&nbsp;
		<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
				<!-- General information block starts -->
				<tr><td>&nbsp; </td></tr>
				<TR>
					<TD>  <FIELDSET style="margin-left:6px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:97.80%">

                                   
								<%
			        javax.servlet.http.HttpServletRequest httpReq = (javax.servlet.http.HttpServletRequest) javax.faces.context.FacesContext
			                .getCurrentInstance().getExternalContext().getRequest();
			        javax.servlet.http.HttpSession httpSession = (javax.servlet.http.HttpSession) javax.faces.context.FacesContext.
			                getCurrentInstance().getExternalContext().getSession(true);
			        httpReq.setAttribute("breadCrumbText",
			                "Rule(" + httpSession.getAttribute("RULE_SEQUENCE_PRINT") + ") >> Print ");
					
			    %> 
							<%=httpReq.getAttribute("breadCrumbText") %>
                              </FIELDSET>

						
					</TD>
				</TR>
			<tr><td>&nbsp; </td></tr>
		</TABLE>

		<FIELDSET
			style="margin-left:6px;margin-right:-6px;margin-top:20px;padding-bottom:1px;padding-top:20px;width:98%">
		<TABLE width="100%" cellpadding="0" cellspacing="0" border="0">
			<TR>
				<td>&nbsp;&nbsp;
				</td>
				<td>&nbsp;&nbsp;
				</td>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="contractForm">


							<h:inputHidden id ="RuleId"  value ="#{contractRuleBackingBean.ruleIdDetails}"></h:inputHidden>
							
<!-- WAS 7.0 Changes - Hidden id pageInit value loaded using binding instead of value -->
							<h:inputHidden id ="hidId" binding="#{contractRuleBackingBean.sequenceHidden}"></h:inputHidden>			
								<TABLE width="50%" cellpadding="0" cellspacing="0">
									<TR>
										<TD width="150">&nbsp;&nbsp;<h:outputText id="contractId"
											value="Rule ID" style="font-weight:bold;" /></TD>
										<h:inputHidden id="contractIdHidden"
											value="#{contractRuleBackingBean.ruleId}"></h:inputHidden>
										<TD width="285"><h:outputText id="contractId_txt"
											value="#{contractRuleBackingBean.ruleId}" /></TD>
									</TR>
									<TR>
										<TD  width="150">&nbsp;&nbsp;<h:outputText
											id="contractType" value="Rule Description "
											style="font-weight:bold;" /></TD>
										<TD  width="285"><h:outputText
											id="contractType_txt"
											value="#{contractRuleBackingBean.ruleDescription}" rendered="#{null != contractRuleBackingBean.ruleDescription}"/> <h:inputHidden
											id="contractTypeHidden"
											value="#{contractRuleBackingBean.ruleDescription}">
										</h:inputHidden>
									</TR>
									<TR>

										<TD  width="150">&nbsp;&nbsp;<h:outputText id="baseContractId"
											value="Rule Version" style="font-weight:bold;" /></TD>

										<TD  width="285"><h:outputText id="base_contractId_txt"
											value="#{contractRuleBackingBean.ruleVersion}" rendered="#{null != contractRuleBackingBean.ruleVersion}"/>
										<h:inputHidden id="baseContractIdHidden"
											value="#{contractRuleBackingBean.ruleVersion}"></h:inputHidden>
										</TD>
									</TR>
									<TR>

										<TD  width="150">&nbsp;&nbsp;<h:outputText id="baseContractDt"
											value="Tag"
											style="font-weight:bold;" /></TD>

										<TD  width="285"><h:outputText id="base_contractDt_txt"
											value="#{contractRuleBackingBean.tag}" rendered="#{null != contractRuleBackingBean.tag}"/>
										<h:inputHidden id="baseContractDtHidden"
											value="#{contractRuleBackingBean.tag}"></h:inputHidden>
										</TD>
									</TR>
									
								</table>


						<TABLE border="0" cellspacing="0" width="100%">
							<TR>
								<TD>
									<div id="displayRulesDetails">
										<h:panelGrid id="panelTable" width="100%"
											binding="#{contractRuleBackingBean.panelRuleSequences}">
										</h:panelGrid>
										</div>
								</TD>
							</TR>
						</TABLE>
				</h:form></TD>
			</TR>
			<TR>
				
			</TR>
		</TABLE>
</FIELDSET>
	</hx:scriptCollector>
	</BODY>
</f:view>

<script>
window.print();

 
</script>

</HTML>
