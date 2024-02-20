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
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"
	title="Style">
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->	
<style type="text/css">

.selectOrOptionColumn {
	width: 10%;
	text-align:center;
	vertical-align: middle;
}.shortDescriptionColumn {
	width: 20%;
}
.longDescriptionColumn {
	width: 70%;
}
</style>
<TITLE>Term Popup</TITLE>
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
	<h:form id="termForm">
		<h:inputHidden value="#{ReferenceDataBackingBeanCommon.refDataLookupRecords}"></h:inputHidden>
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
			
			<tr>
				<td align="left">&nbsp;<h:commandButton id="selectButton"
					value="Select" styleClass="wpdbutton"
					onclick="getCheckedItems_ewpd('termForm:termTable',2);return false;"></h:commandButton>
				</td>
			</tr>

		</table>

		<table width="98%" align="right" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						bgcolor="#cccccc">
						<TR>
							<TD width="10%" align="center" valign="middle"><h:selectBooleanCheckbox
								onclick="checkAll_ewpd(this,'termForm:termTable'); ">
							</h:selectBooleanCheckbox></TD>
							<TD width="20%"></TD>
							<TD width="70%" align="left"><strong><h:outputText
								value="Term "></h:outputText></strong></TD>
						</TR>
					</table>
					</TD>
				</TR>
				<tr>
					<td colspan="2" width="100%">
					<DIV id="popupDataTableDiv" class=popupDataTableDiv>
					<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow"
					columnClasses="selectOrOptionColumn,shortDescriptionColumn,longDescriptionColumn"
						cellspacing="1" width="100%" id="termTable"
						value="#{ReferenceDataBackingBeanCommon.refDataLookUpList}" var="eachRow"
						cellpadding="1" bgcolor="#cccccc">
						<h:column>
						
							<h:selectBooleanCheckbox id="termChkBox">
							</h:selectBooleanCheckbox>
						
						</h:column>
						<h:column>
						
   										  <h:inputHidden value="#{eachRow.description}"></h:inputHidden>
                                          <h:inputHidden value="#{eachRow.primaryCode}"></h:inputHidden>
                                          <h:outputText value="#{eachRow.primaryCode}" style="padding-left: 5px"></h:outputText>
						</h:column>
						<h:column>
						
							 <h:outputText value="#{eachRow. description }" style="padding-left: 5px"></h:outputText>
						</h:column>
					</h:dataTable></DIV>
					</td>
				</tr>
			</TBODY>
		</table>
	</h:form>
	</BODY>
</f:view>

<script language="javascript">
	window.opener = window.dialogArguments.parentWindow;
	var hiddenObj = window.opener.document.getElementById(window.dialogArguments.hiddenId);
	if(hiddenObj == null || hiddenObj == undefined) {
		alert("Hidden field not available");
	}
	if(hiddenObj.value) {

		matchCheckboxItems_ewpd('termForm:termTable', window.dialogArguments.selectedValues, 2, 2, 2);
	}
</script>
</HTML>


