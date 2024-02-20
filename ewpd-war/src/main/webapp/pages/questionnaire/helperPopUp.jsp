<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">
<base target=_self>
<TITLE>helperPopUp.jsp</TITLE>
</HEAD>
<f:view>
<BODY>
<h:form id="helperForm">
<h:inputHidden id="getQueryVariables" value="#{editQuestionnaireBackingBean.queryVariables}"></h:inputHidden>
<h:inputHidden id="adminOptionName" value="#{editQuestionnaireBackingBean.adminOptionName}"></h:inputHidden>
<h:inputHidden id="adminOptionVersion" value="#{editQuestionnaireBackingBean.adminOptionVersion}"></h:inputHidden>
<h:inputHidden id="parentQuestionnaireHierarchyId" value="#{editQuestionnaireBackingBean.rootQuestionnaireHierarchySystemId}"></h:inputHidden>
<P><h:commandLink id="submitLink" action="#{editQuestionnaireBackingBean.submitAction}"><f:verbatim/></h:commandLink></P>
</h:form>
</BODY>
</f:view>
<script language="javascript">
submitPage();
function submitPage(){
	document.getElementById('helperForm:submitLink').click();
}
</script>
</HTML>
