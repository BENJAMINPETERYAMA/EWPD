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
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"	title="Style">
<LINK href="../../theme/Master.css" rel="stylesheet"
	type="text/css">
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
<TITLE>BenefitLevel Popup</TITLE>
</HEAD>
<f:view>
<BODY>
<script language="JavaScript" src="../../js/tigra_tables.js"></script>
	<h:form id="benefitLevelPopUpForm">
		<h:inputHidden value="#{ReferenceDataBackingBeanCommon.benefitLevelRecords}"></h:inputHidden>
		
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
            
			<tr>
				<td align="left">&nbsp;&nbsp;&nbsp;			
					<h:commandButton id="txtSelect" styleClass="wpdbutton" value="Select" disabled="true" 
							onclick="getCheckedItems_ewpd('benefitLevelPopUpForm:BenefitLevelPopUpTable',2);return false;">
					</h:commandButton>
				</td>
			</tr>
		</table>

		<table width="96%" align="right" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						bgcolor="#cccccc">
						<TR height="20px">
							<TD width="4%" align="left">
								<f:verbatim></f:verbatim>
							</TD>
							<TD width="96%" align="center">
								<strong><h:outputText value="Benefit Level"></h:outputText></strong>
							</TD>
						</TR>
					</table>
					</TD>
				</TR>
				<tr>
					<td>
					<DIV id="popupDataTableDiv" class=popupDataTableDiv>
					<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow"
						cellspacing="1" width="100%" id="BenefitLevelPopUpTable"
						value="#{ReferenceDataBackingBeanCommon.benefitLevelListPopUp}" var="eachRow"
						cellpadding="0" bgcolor="#cccccc">
						<h:column>
							<f:verbatim>
								<wpd:singleRowSelect groupName="benefitLevel" id="minor1" rendered="true"></wpd:singleRowSelect>
							</f:verbatim>
						</h:column>
						<h:column>
							<h:inputHidden id="hiddenLevelId" value="#{eachRow.benefitLevelId}"></h:inputHidden>
                            <f:verbatim>&nbsp;</f:verbatim>
							<h:inputHidden id="hiddenDescription" value="#{eachRow.description}"></h:inputHidden>
                               <f:verbatim>&nbsp;</f:verbatim>
							<h:outputText value="#{eachRow.description}"></h:outputText>
						</h:column>
					</h:dataTable>
					</DIV>
					</td>
				</tr>
			</TBODY>
		</table>
	</h:form>
		
			
</BODY>
		
</f:view>
<script language="JavaScript">
document.getElementById('benefitLevelPopUpForm:txtSelect').disabled  = false;
setColumnWidth('benefitLevelPopUpForm:BenefitLevelPopUpTable','12%:88%');

matchCheckboxItems_ewpd('benefitLevelPopUpForm:BenefitLevelPopUpTable', window.dialogArguments.selectedValues, 2, 2, 2)
</script>

</HTML>