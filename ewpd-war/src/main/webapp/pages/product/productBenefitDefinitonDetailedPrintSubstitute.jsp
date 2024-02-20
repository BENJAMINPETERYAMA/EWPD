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
<TITLE>productBenefitDefinitonDetailedPrintSubstitute.jsp</TITLE>
<base target=_self>
</HEAD>

<f:view>
	<BODY>
	<h:form id="detailedPrintSubstitute">
		<h:inputHidden id="genInfo"
			value="#{productPrintInfoBackingBean.printSubGenInf}"></h:inputHidden>
		<h:inputHidden id="benDef"
			value="#{productPrintInfoBackingBean.printSubBenDef}"></h:inputHidden>
		<h:inputHidden id="manInfo"
			value="#{productPrintInfoBackingBean.printSubManInfo}"></h:inputHidden>
		<h:inputHidden id="notes"
			value="#{productPrintInfoBackingBean.printSubNotes}"></h:inputHidden>
		<h:commandLink id="urlCommandLink"
			action="#{productPrintInfoBackingBean.forwardPrint}">
			<f:verbatim></f:verbatim>
		</h:commandLink>
	</h:form>
	</BODY>
</f:view>
<script>
load();
function load(){
 	document.getElementById('detailedPrintSubstitute:urlCommandLink').click();
}
</script>
</HTML>
