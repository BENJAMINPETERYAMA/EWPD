<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
	<HEAD>
		<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
		<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
		<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
		<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
			pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM WebSphere Studio">
		<META http-equiv="Content-Style-Type" content="text/css">
		<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css" title="Style">
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
		<BASE target="_self" />
		
		<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.selectCheckboxColumn {
      width: 13%;
}

.testCaseNameColumn {
      width: 87%;
}
</style>
		<TITLE> Test Case Popup</TITLE>
	</HEAD>
<f:view>
<body style="margin:17px; text-align: center;" onkeydown="return submitOnEnterKey('TCSearchForm:locateButton');">
	<h:form id="TCSearchForm">
	<table  style="text-align:right;margin-top:7px;" border="0" cellpadding="5" cellspacing="0" width="100%">
		<tr>
			<td width="30%">Test Case Name</td>
			<td width="50%">
				<h:inputText  id="testCaseName" style="width: 190px;font-size: 11px;background-color: #F4F4F4;font-family: Verdana, Arial, Helvetica, sans-serif;border: 1px solid #7f9db9;color: #1762A5;"
					value="#{testCaseBackingBean.testCaseName}" />
			</td>
			<td width="20%" style="text-align:center;">
				<h:commandButton styleClass="wpdbutton" id="locateButton" value="Search" style="cursor: hand" action="#{testCaseBackingBean.testCasePopup}" tabindex="9"></h:commandButton>
			</td>
		</tr>
	</table>
	</h:form>
	<h:form id="TCForm">
	<table  border="0" cellpadding="0" cellspacing="0" width="100%" style="margin-bottom:7px; 0px;">
		<tr >
			<td align="left">
				<input type="button" class="wpdbutton" name="action" value="Select" onClick="getCheckedItems_ewpd('TCForm:testCaseTable',2);"/>
			</td>
		</tr>
	</table>
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table width="100%" cellpadding="0" cellspacing="1" bgcolor="#cccccc">
					<tr>
						<td width="13%">
							<h:selectBooleanCheckbox onclick="if ( document.getElementById('TCForm:testCaseTable') != null ) checkAll_ewpd(this, 'TCForm:testCaseTable'); ">
							</h:selectBooleanCheckbox>
						</td>	
						<td width="87%" align="left"><strong>Test Case</strong></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<div id="popupDataTableDiv"  style="overflow:auto;" class="popupDataTableDiv">
				<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow"
							styleClass="outputText" headerClass="dataTableHeader"
							id="testCaseTable" var="testCase" cellpadding="1"
							cellspacing="1" bgcolor="#cccccc"
							rendered="#{testCaseBackingBean.testCaseList != null}"
							value="#{testCaseBackingBean.testCaseList}"
							width="100%" columnClasses="selectCheckboxColumn,testCaseNameColumn">
					<h:column>
						
						<h:selectBooleanCheckbox></h:selectBooleanCheckbox>
					</h:column>
					<h:column>
						
						<h:inputHidden id="hiddenTestCaseId" value="#{testCase.testCaseId}"></h:inputHidden>
						<h:inputHidden id="hiddenTestCaseName" value="#{testCase.testCaseName}"></h:inputHidden>
						<h:outputText value="#{testCase.testCaseName}"></h:outputText>
					</h:column>
				</h:dataTable>
				</div>
			</td>
		</tr>
	  </table>
	</h:form>
</body>
</f:view>
</html>