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
<h:form id="helperFormForRoot">
<h:inputHidden id="queryVariables" value="#{rootQuestionsBackingBean.queryVariables}"></h:inputHidden>
<h:inputHidden id="adminId" value="#{rootQuestionsBackingBean.adminId}"></h:inputHidden>
<h:inputHidden id="adminName" value="#{rootQuestionsBackingBean.adminName}"></h:inputHidden>
<h:inputHidden id="adminVersion" value="#{rootQuestionsBackingBean.adminVersion}"></h:inputHidden>
<h:inputHidden id="submitPage" value="#{rootQuestionsBackingBean.submitPage}"></h:inputHidden>
<P><h:commandLink id="submitLink" action="#{rootQuestionsBackingBean.submitAction}"><f:verbatim/></h:commandLink>
<h:commandLink id="submitAddLink" action="#{rootQuestionsBackingBean.submitAddAction}"><f:verbatim/></h:commandLink></P>
</h:form>
</BODY>
</f:view>
<script language="javascript">
submitPage();
function submitPage(){
	var submitPage = document.getElementById('helperFormForRoot:submitPage');
	if(submitPage.value == "editRoot")
		document.getElementById('helperFormForRoot:submitLink').click();
	else if(submitPage.value == "addRoot")
		document.getElementById('helperFormForRoot:submitAddLink').click();
}
</script>
</HTML>
