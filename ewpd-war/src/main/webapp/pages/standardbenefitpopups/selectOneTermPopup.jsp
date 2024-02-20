<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">
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
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.selectOrOptionColumn {
	width: 10%;
}

.shortDescriptionColumn {
	width: 90%;
}
</style>
<TITLE>Term Popup</TITLE>
</HEAD>
<f:view>
	<BODY>
	<script language="JavaScript" src="../../js/tigra_tables.js"></script>

	<h:form id="termSelectPopupForm">

		<table border="0" cellpadding="5" cellspacing="0" width="100%">
			<tr>
				<td align="left"><h:commandButton id="txtSelect"
					styleClass="wpdbutton" value="Select"
					onclick="getCheckedItems_ewpd('termSelectPopupForm:termSelectPopupTable',1);return false;"></h:commandButton>
				</td>
			</tr>
		</table>


		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" bgcolor="#cccccc">
			<tr>
				<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0"
					bgcolor="#cccccc">
					<tr>
						<td width="6%" align="left">&nbsp;</TD>
						<TD width="94%" align="center"><strong> <h:outputText
							value="Benefit Term">
						</h:outputText> </strong></td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td><h:dataTable cellspacing="1" width="100%"
					id="termSelectPopupTable" var="eachRow"
					value="#{referenceBackingBean.termResultList}"
					cellpadding="0" bgcolor="#cccccc" columnClasses="selectOrOptionColumn,shortDescriptionColumn">
					<h:column>

						<f:verbatim>
							<wpd:singleRowSelect groupName="term" id="minor" rendered="true"></wpd:singleRowSelect>

						</f:verbatim>
					</h:column>
					<h:column>

						<h:outputText value="#{eachRow.description}"></h:outputText>
						<h:inputHidden id="hiddenTerm" value="#{eachRow.description}"></h:inputHidden>
						<h:inputHidden id="hiddenTermCode" value="#{eachRow.code}"></h:inputHidden>
					</h:column>
				</h:dataTable></td>
			</tr>
		</table>

	</h:form>

	</BODY>

</f:view>
<script language="javascript">
		
	tigra_tables('termSelectPopupForm:termSelectPopupTable',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
	matchCheckboxItems_ewpd('termSelectPopupForm:termSelectPopupTable', window.dialogArguments.selectedValues, 1, 1, 1);
	
	// setColumnWidth('termSelectPopupForm:termSelectPopupTable', '5:250');
	

</script>
</HTML>

