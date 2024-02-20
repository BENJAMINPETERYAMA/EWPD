<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="org.owasp.esapi.ESAPI" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">
<TITLE>rule view substitute</TITLE>
<base target=_self>
</HEAD>
<f:view>
	<BODY>
	
	<h:form id="ruleViewSubstitute">
	<h:inputHidden id ="RuleType"  value ="#{contractRuleBackingBean.blzRuleType}"></h:inputHidden>
	<h:inputHidden id ="RuleId"  value ="#{contractRuleBackingBean.ruleIdDetails}"></h:inputHidden>
	<h:inputHidden id ="pnrRuleSelected"  value ="#{contractRuleBackingBean.pnrRuleSelected}"></h:inputHidden>
	<h:inputHidden id ="exclusionRuleSelected"  value ="#{contractRuleBackingBean.exclusionRuleSelected}"></h:inputHidden>
	<h:inputHidden id ="denialRuleSelected"  value ="#{contractRuleBackingBean.denialRuleSelected}"></h:inputHidden>
	<h:inputHidden id ="umRuleSelected"  value ="#{contractRuleBackingBean.umRuleSelected}"></h:inputHidden>
	<h:inputHidden id ="headerRuleBCselected" value= "#{contractRuleBackingBean.headerRuleBCselected}"></h:inputHidden>
	<h:inputHidden id ="headerRuleBenefitSelected" value= "#{contractRuleBackingBean.headerRuleBenefitSelected}"></h:inputHidden>
	<h:inputHidden id ="benefitComponentName" value= "#{contractRuleBackingBean.benefitComponentName}"></h:inputHidden>
	<h:inputHidden id ="benefitComponentId" value= "#{contractRuleBackingBean.benefitComponentId}"></h:inputHidden>
	<h:inputHidden id ="benefitName" value= "#{contractRuleBackingBean.benefitName}"></h:inputHidden>
	<h:inputHidden id ="checkMode"  value ="#{contractRuleBackingBean.checkMode}"></h:inputHidden>
	<h:commandLink id="urlCommandLink" action="#{contractRuleBackingBean.loadRuleViewPage}"><f:verbatim></f:verbatim></h:commandLink>
	</h:form>
	</BODY>
</f:view>
<script> 
load();
function load(){

	var ruleid = ('<%=ESAPI.encoder().encodeForHTML(request.getParameter("ruleId"))%>').toUpperCase();
	var ruleType = ('<%=ESAPI.encoder().encodeForHTML(request.getParameter("ruleType"))%>').toUpperCase();
	
	//ICD10 Enhancement -- Individual Rule Extract
	var pnrRuleSelected = '<%=ESAPI.encoder().encodeForHTML(request.getParameter("pnrRuleSelected"))%>';
	var exclusionRuleSelected = '<%=ESAPI.encoder().encodeForHTML(request.getParameter("exclusionRuleSelected"))%>';
	var denialRuleSelected = '<%=ESAPI.encoder().encodeForHTML(request.getParameter("denialRuleSelected"))%>';
	var umRuleSelected = '<%=ESAPI.encoder().encodeForHTML(request.getParameter("umRuleSelected"))%>';
	
	var headerRuleBCselected = '<%=ESAPI.encoder().encodeForHTML(request.getParameter("headerRuleBCselected"))%>';
	var headerRuleBenefitSelected = '<%=ESAPI.encoder().encodeForHTML(request.getParameter("headerRuleBenefitSelected"))%>';
	var benefitComponentName = '<%=ESAPI.encoder().encodeForHTML(request.getParameter("benefitComponentName"))%>';
	var benefitComponentId = '<%=ESAPI.encoder().encodeForHTML(request.getParameter("benefitComponentId"))%>';
	var benefitName = '<%=ESAPI.encoder().encodeForHTML(request.getParameter("benefitName"))%>';
	var checkMode = '<%=ESAPI.encoder().encodeForHTML(request.getParameter("checkMode"))%>';
	//ICD10 End
	
	document.getElementById('ruleViewSubstitute:RuleId').value = ruleid ;
	document.getElementById('ruleViewSubstitute:RuleType').value = ruleType ;
	
	//ICD10 Enhancement -- Individual Rule Extract
	document.getElementById('ruleViewSubstitute:pnrRuleSelected').value = pnrRuleSelected ;
	document.getElementById('ruleViewSubstitute:exclusionRuleSelected').value = exclusionRuleSelected ;
	document.getElementById('ruleViewSubstitute:denialRuleSelected').value = denialRuleSelected ;
	document.getElementById('ruleViewSubstitute:umRuleSelected').value = umRuleSelected ;
	
	document.getElementById('ruleViewSubstitute:headerRuleBCselected').value = headerRuleBCselected ;
	document.getElementById('ruleViewSubstitute:headerRuleBenefitSelected').value = headerRuleBenefitSelected ;
	document.getElementById('ruleViewSubstitute:benefitComponentName').value = benefitComponentName ;
	document.getElementById('ruleViewSubstitute:benefitComponentId').value = benefitComponentId ;
	document.getElementById('ruleViewSubstitute:benefitName').value = benefitName ;
	document.getElementById('ruleViewSubstitute:checkMode').value = checkMode ;
   	document.getElementById('ruleViewSubstitute:urlCommandLink').click();
   	//ICD10 End
}
</script>
</HTML>

