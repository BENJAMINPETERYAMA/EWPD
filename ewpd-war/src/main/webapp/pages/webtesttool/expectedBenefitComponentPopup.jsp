<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"
	title="Style">
	<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.singleRowSelectColumn {
      width: 10%;  
}

.descriptionColumn {
     width: 90%; 
}
</style>

<TITLE>Benefit Component Popup</TITLE>
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
<BASE target="_self" />
</HEAD>
<f:view>
	<BODY onkeydown="return submitOnEnterKey('TCSearchForm:locateButton');">
	<h:form id="TCSearchForm">
	 <table  style="text-align:right;margin-top:7px;" border="0" cellpadding="5" cellspacing="0" width="100%">
		<tr>
			<td width="30%">Benefit Component Name</td>
			<td width="50%">
				<h:inputText  id="benefitComponentName" style="width: 190px;font-size: 11px;background-color: #F4F4F4;font-family: Verdana, Arial, Helvetica, sans-serif;border: 1px solid #7f9db9;color: #1762A5;"
					value="#{testCaseBackingBean.expectedBenefitComponent}" />
			</td>
			<td width="20%" style="text-align:center;">
				<h:commandButton styleClass="wpdbutton" id="locateButton" value="Search" style="cursor: hand" action="#{testCaseBackingBean.benefitComponentPopup}" tabindex="9"></h:commandButton>
			</td>
		</tr>
	</table>
	</h:form>
	<h:form id="benefitListForm">
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
			<tr>
				<td align="left">&nbsp;
					<h:commandButton id="selectButton" value="Select" styleClass="wpdbutton"
						onclick="getCheckedItems_ewpd('benefitListForm:benefitTable',2);return false;">
					</h:commandButton>
				</td>
			</tr>	
		</table>
		<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#cccccc">
		<tr>
		<td>

		  <table width="100%" cellpadding="0" cellspacing="1" bgcolor="#cccccc">
			<tr>
			  <td width="10%" align="left">&nbsp;</td>
			  <td width="90%"><strong>Benefit Component</strong></td>
			 </tr>
		  </table>
		  <DIV id="popupDataTableDiv"  style="overflow:auto;" class="popupDataTableDiv">
		  <h:dataTable id="benefitTable" width="100%" cellpadding="0" cellspacing="1" bgcolor="#cccccc" 
					   rowClasses="dataTableEvenRow,dataTableOddRow"
					   value="#{testCaseBackingBean.expBenefitCompntList}" var="benefit" columnClasses="singleRowSelectColumn,descriptionColumn">
			<h:column>
				
				<wpd:singleRowSelect groupName="majorHeading" id="major" rendered="true"></wpd:singleRowSelect>
			</h:column>
			<h:column>
				
				<h:inputHidden value="#{benefit.benefitCmptSysId}"></h:inputHidden>
				<h:inputHidden value="#{benefit.benefitCmptName}"></h:inputHidden>
				<h:outputText value="#{benefit.benefitCmptName}"></h:outputText>
			</h:column>
		  </h:dataTable>
		  </DIV>
		</td>
		</tr>
	  </table>
	</h:form>
	</BODY>
</f:view>

<script language="javascript">
	window.opener = window.dialogArguments.parentWindow;
	var hiddenObj = window.opener.document.getElementById(window.dialogArguments.hiddenId);
	if(hiddenObj == null || hiddenObj == undefined) {
	}
	if(hiddenObj.value) {
		matchCheckboxItems_ewpd('benefitListForm:benefitTable', window.dialogArguments.selectedValues, 2, 2, 2);
	}
</script>
</HTML>