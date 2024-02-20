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
     width: 80%; 
}
</style>
<TITLE>Type Of Bill Popup</TITLE>
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
</HEAD>
<f:view>
	<BODY>
	<h:form id="benefitListForm">
	<h:inputHidden value="#{ReferenceDataBackingBeanCommon.typeOfBillListForCombo}"></h:inputHidden>
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
			<tr>
				<td align="left">&nbsp;
					<h:commandButton id="selectButton" value="Select" styleClass="wpdbutton"
						onclick="getCheckedItems_ewpd('benefitListForm:benefitTable',2);return false;">
					</h:commandButton>
				</td>
			</tr>	
		</table>

		<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">


		<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">

		<tr>
		<td>

		  <table width="100%" cellpadding="0" cellspacing="1" bgcolor="#cccccc">
			<tr>
			
			  <td width="10%" align="left"><div id="popHeading"></div></td>
			  <td width="10%" align="left"><strong>Code</strong></td>
			  <td width="80%"><strong>Description</strong></td>
			 </tr>
		  </table>
		   <DIV id="popupDataTableDiv"  style="overflow:auto;" class="popupDataTableDiv">
		  <h:dataTable id="benefitTable" width="100%" cellpadding="0" cellspacing="1" bgcolor="#cccccc" 
					   rowClasses="dataTableEvenRow,dataTableOddRow"
					   value="#{ReferenceDataBackingBeanCommon.typeOfBillListForCombo}" var="tob" columnClasses="singleRowSelectColumn,singleRowSelectColumn,descriptionColumn">
			<h:column>
				
				<wpd:singleRowSelect groupName="majorHeading" id="major" rendered="true"></wpd:singleRowSelect>
			</h:column>
			<h:column>
				
				<h:inputHidden value="#{tob.primaryCode}"></h:inputHidden>
				<h:inputHidden value="#{tob.description}"></h:inputHidden>
				<h:outputText value="#{tob.primaryCode}"></h:outputText>
			</h:column>
			<h:column>
				
				<h:outputText value="#{tob.description}"></h:outputText>
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