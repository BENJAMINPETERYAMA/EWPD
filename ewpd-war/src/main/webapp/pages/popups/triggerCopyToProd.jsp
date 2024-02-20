<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
	<HEAD>
		<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
		<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
		<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%>
		<%@ taglib uri="/wpd.tld" prefix="w"%>
		<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM WebSphere Studio">
		<META http-equiv="Content-Style-Type" content="text/css">
		<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">
		<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css" title="Style">
		<LINK rel="stylesheet" type="text/css" href="../../css/global.css" title="Style">
		<LINK rel="stylesheet" type="text/css" href="../../css/menu.css" title="Style">
		<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" title="Style">
		<BASE target="_self" />
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
<f:view>

	<TITLE><h:outputText />Confirmation</TITLE>
	<!-- <script language="JavaScript" src="../../js/wpd.js"></script> -->
</HEAD>
<BODY>
	<h:form id="copyToProdTriggerform">
	<h:inputHidden id = "hidCopyToProdTriggered" value="#{indicativeLayoutMappingBackingBean.copyToProdTriggered}"></h:inputHidden>
	<h:commandLink id="lnkTriggerCoyyToProd" style="display:none; visibility: hidden;"
		action="#{indicativeLayoutMappingBackingBean.triggerCopyToProd}">
		<f:verbatim />
	</h:commandLink>
		
	</h:form>	
</BODY>
</f:view>
<script>
 var bool = trim(document.getElementById('copyToProdTriggerform:hidCopyToProdTriggered').value);
 if(bool == 'false'){
	document.getElementById('copyToProdTriggerform:hidCopyToProdTriggered').value = 'true';
	document.getElementById('copyToProdTriggerform:lnkTriggerCoyyToProd').click();	 	
 }	
</script>
</HTML>

