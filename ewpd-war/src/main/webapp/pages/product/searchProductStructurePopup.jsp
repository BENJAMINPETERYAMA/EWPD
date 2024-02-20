<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="../../theme/Master.css" rel="stylesheet"
	type="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"
	title="Style">
	
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->	
<style type="text/css">

.selectproductStructureColumn {
  width: 5%;
}.shortDescriptionColumn {
	
}

</style>
<TITLE>Product structure Popup</TITLE>
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
	<h:form id="productStructureForm">
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
		
			<tr>
				<td align="left">&nbsp;<h:commandButton id="selectButton" value="Select"
					styleClass="wpdbutton" 		
					onclick="getCheckedItems_ewpd('productStructureForm:productStructureDataTable',2);return false;"></h:commandButton>
				</td>
			</tr>
		</table>
		<table width="98%" align="right" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						bgcolor="#cccccc">
						<tr>
							<td width="6%" align="left"><h:selectBooleanCheckbox
								onclick="checkAll_ewpd(this,'productStructureForm:productStructureDataTable'); "></h:selectBooleanCheckbox></TD>
<TD width="94%" align="center"><strong> <h:outputText value="Product Structure" styleClass="outputText"></h:outputText></strong></td>
						</tr>
					</table>
					</TD>
				</TR>
				<tr>
					<td>
					<DIV id="popupDataTableDiv" class=popupDataTableDiv>
					<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selectproductStructureColumn,shortDescriptionColumn"
						cellspacing="1" width="100%" id="productStructureDataTable"
						value="#{productGeneralInformationBackingBean.productStructureList}" var="eachRow"
						cellpadding="0" bgcolor="#cccccc">
						<h:column>
							<f:verbatim>
								<h:selectBooleanCheckbox id="productStructureChkBox">
								</h:selectBooleanCheckbox>
							</f:verbatim>
							
						</h:column>
								<h:column >
                            <f:verbatim>&nbsp;</f:verbatim>
							<h:inputHidden value="#{eachRow.productStructureName}"></h:inputHidden>
							<h:inputHidden value="#{eachRow.productStructureId}"></h:inputHidden>
							<h:outputText value="#{eachRow.productStructureName}"></h:outputText>
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
		matchCheckboxItems_ewpd('productStructureForm:productStructureDataTable', window.dialogArguments.selectedValues, 2, 2, 2);
	}
</script>	
</HTML>
