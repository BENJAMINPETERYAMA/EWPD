<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
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

<TITLE>WellPoint Product Database: Rule View</TITLE>
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
	<hx:scriptCollector id="scriptCollector1">

	
<%-- 
		<w:message></w:message>
--%>	
			

		<TABLE width="100%" cellpadding="0" cellspacing="0">

			<TR>
				
				<TD>
				<jsp:include page="../navigation/top_view_rule.jsp"></jsp:include>
				</TD>
				
			</TR>
			<tr>
				<h:form styleClass="form" id="contractForm">
			<td>
								&nbsp;&nbsp;
			</td>
			</tr>
			<TR>
				<TD>
				<FIELDSET
							style="margin-left:6px;margin-right:-10x;margin-top:20px;padding-bottom:1px;padding-top:20px;width:100%">
					

					
						<h:inputHidden id ="RuleId"  value ="#{contractRuleBackingBean.ruleIdDetails}"></h:inputHidden>
						<!-- WAS 7.0 Changes - Hidden id pageInit value loaded using binding instead of value -->
							<h:inputHidden id ="hidId" value="#{contractRuleBackingBean.sequenceHidden}"></h:inputHidden>	
							<div id ="noresultdiv">	
								<TABLE>
									<TBODY>
										<tr>
											<TD>
												<strong><h:outputText value="Given RuleID is not valid" style="color:blue;"></h:outputText></strong>
											</TD>

										</tr>		
									</TBODY>
								</TABLE>
						</div>
									
								<DIV id = viewtableDiv>
								<TABLE  width="50%" cellpadding="0" cellspacing="0">
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
											value="#{contractRuleBackingBean.ruleDescription}" rendered="#{null != contractRuleBackingBean.ruleDescription}" /> <h:inputHidden
											id="contractTypeHidden"
											value="#{contractRuleBackingBean.ruleDescription}">
										</h:inputHidden>
									</TR>
									<TR>

										<TD  width="150">&nbsp;&nbsp;<h:outputText id="baseContractId"
											value="Rule Version" style="font-weight:bold;" /></TD>

										<TD  width="285">
										<h:outputText id="base_contractId_txt" value="#{contractRuleBackingBean.ruleVersion}"  rendered="#{null != contractRuleBackingBean.ruleVersion}" />
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

						<TABLE  border="0" cellspacing="0" width="100%" style="margin-left:6px;margin-right:-20px;margin-top:20px;margin-bottom:20px;padding-bottom:20px;padding-top:20px;width:98%">

									<TR>
										<TD>
											
											<div id="displayRulesDetails"
													style="position:relative;overflow:auto;height:300px;width:100%">
												<h:panelGrid id="panelTable" width="100%"
													binding="#{contractRuleBackingBean.panelRuleSequences}">
												</h:panelGrid>
												</div>
										</TD>
									</TR>
						</TABLE>
			</DIV>
					</FIELDSET>
					
				</h:form></TD>
			</TR>
			<TR>
				<td>&nbsp;&nbsp;
				</td>
			</tr>

				<TR>
				<TD><%@include file="../navigation/bottom_view.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	<iframe width="100%" height="300" style="display:none;visibility:hidden" name="targetIframe" id="targetIframe" onreadystatechange ="checkIframStatus();">
	</iframe> 
	<div class="transparent" id="transparentDiv"></div>
	<div class ="picDiv" id="loadingImageDiv">
	<table  width="100%" height="100%">
		<tr>
			<td align="center">
				<img src="../../images/loading.gif">  
			</td>
		
		</tr>
	</table>
</div>
   <form id="submitRuleForm" name="submitRuleForm" action="viewRule.xlsx" method="post" target="targetIframe" >
		<h:inputHidden id="pageFrom" value="rule"></h:inputHidden>	
		<h:inputHidden id="entityId" value="#{contractRuleBackingBean.ruleIdDetails}"></h:inputHidden>	
		<h:inputHidden id="exclusionRuleSelected" value="#{contractRuleBackingBean.exclusionRuleSelected}"></h:inputHidden>	
		<h:inputHidden id="denialRuleSelected" value="#{contractRuleBackingBean.denialRuleSelected}"></h:inputHidden>	
		<h:inputHidden id="umRuleSelected" value="#{contractRuleBackingBean.umRuleSelected}"></h:inputHidden>
		
		<h:inputHidden id="headerRuleBCselected" value="#{contractRuleBackingBean.headerRuleBCselected}"></h:inputHidden>
		<h:inputHidden id="headerRuleBenefitSelected" value="#{contractRuleBackingBean.headerRuleBenefitSelected}"></h:inputHidden>
		<h:inputHidden id="benefitComponentName" value="#{contractRuleBackingBean.benefitComponentName}"></h:inputHidden>
		<h:inputHidden id="benefitComponentId" value="#{contractRuleBackingBean.benefitComponentId}"></h:inputHidden>
		<h:inputHidden id="benefitName" value="#{contractRuleBackingBean.benefitName}"></h:inputHidden>
		<h:inputHidden id="checkMode" value="#{contractRuleBackingBean.checkMode}"></h:inputHidden>
	</form> 
	</BODY>
</f:view>

<script>
 var ruleId = encodeURI(document.getElementById('contractForm:RuleId').value);
document.getElementById('breadCrumb1').innerHTML ='Rule ('+ ruleId + ') >> View';
	realruleid = document.getElementById('contractForm:contractIdHidden').value;
	if(realruleid == null || realruleid ==''){
	viewtableDiv.style.display='none';
	noresultdiv.style.display='';

	}else{
	viewtableDiv.style.display='';
	noresultdiv.style.display='none';
	
	}
var reportSubmitted = false;
enablePage();
function checkIframStatus() {
		iframe = document.getElementById('targetIframe');
		if(reportSubmitted) {
			if( iframe && iframe.document && (iframe.document.readyState == 'interactive' || iframe.document.readyState == 'complete')) {
				reportSubmitted = false;
				//alert("checkIframStatus");
				enablePage();
			}
		}
	}
function invokeServlet() {
disablePage();
document.forms['submitRuleForm'].submit();
reportSubmitted = true;	
}	

// Check the status of the Iframe and enable screen by removing blocking div once
// the response is received.
	
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="contractRuleSequenceView" /></form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>