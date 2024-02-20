<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/contract/ContractCopySubstitute.java" --%><%-- /jsf:pagecode --%>
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
<TITLE>contractCopySubstitute.jsp</TITLE>
<base target=_self>
</HEAD>
<f:view>
	<BODY>
	<h:form id="contractCopySubstitute">
		<h:inputHidden id="contractKey" value="#{contractBasicInfoBackingBean.hiddenContractKeyForCopy}"></h:inputHidden>
		<h:inputHidden id="contractId" value="#{contractBasicInfoBackingBean.hiddenContractIdForCopy}"></h:inputHidden>
		<h:inputHidden id="dateSegmentId" value="#{contractBasicInfoBackingBean.hiddenDateSegmentIdForCopy}"></h:inputHidden>
		<h:inputHidden id="version" value="#{contractBasicInfoBackingBean.hiddenVersionForCopy}" ></h:inputHidden>
		<h:inputHidden id="status" value="#{contractBasicInfoBackingBean.hiddenStatusForCopy}" ></h:inputHidden>
		<h:inputHidden id="productSysId" value="#{contractBasicInfoBackingBean.hiddenProductIdForCopy}" ></h:inputHidden>
		<h:inputHidden id="productStatus" value="#{contractBasicInfoBackingBean.hiddenProductStatusForCopy}"}></h:inputHidden>		
	    <h:inputHidden id="noteStatus" value="#{contractBasicInfoBackingBean.hiddenNoteStatusForCopy}"}></h:inputHidden>
		<h:commandLink id="urlCommandLink" action="#{contractBasicInfoBackingBean.copyPage}"><f:verbatim></f:verbatim></h:commandLink>
	</h:form>
	</BODY>
</f:view>
<script>
load();
function load(){
 
   document.getElementById('contractCopySubstitute:urlCommandLink').click();
}
</script>
</HTML>
