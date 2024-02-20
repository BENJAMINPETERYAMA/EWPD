<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/contract/ContractCoveragePrintSubstitute.java" --%><%-- /jsf:pagecode --%>
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
<TITLE>contractCoveragePrintSubstitute.jsp</TITLE>
<base target=_self>
</HEAD>
<f:view>
	<BODY>
	<h:form id="contractCoveragePrintSubstitute">
		<h:commandLink id="urlCommandLink" action="#{contractCoverageBackingBean.benefitLevelForPrint}"><f:verbatim></f:verbatim></h:commandLink>
	</h:form>
	</BODY>
</f:view>
<script>
load();
function load(){
   document.getElementById('contractCoveragePrintSubstitute:urlCommandLink').click();
}
</script>
</HTML>
