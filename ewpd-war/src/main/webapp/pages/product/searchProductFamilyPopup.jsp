<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"	title="Style">

<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->	
<style type="text/css">

.selectOrOptionColumn {

}.shortDescriptionColumn {
	width: 92%;
}

</style>
	
<TITLE>Product Family Popup </TITLE>
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
</HEAD>
<f:view>
	<BODY>
		<h:form id="prodFamilyForm">
<h:inputHidden value="#{ReferenceDataBackingBeanCommon.refDataLookupRecords}"></h:inputHidden>
			<table  border="0" cellpadding="5" cellspacing="0" width="100%">
				
				<tr>
					<td align="left">&nbsp;
						<h:commandButton id="selectButton" value="Select" styleClass="wpdbutton" onclick="getCheckedItems_ewpd('prodFamilyForm:prodFamilyDataTable',2);return false;"></h:commandButton>
					</td>
				</tr>
			</table>
			<table width="97%"  align="right" cellpadding="0" cellspacing="0" >
			<TBODY>
				<TR>
					<TD>
						<table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#cccccc">
							<tr>
							<TD width="6%" align="left">
								<h:selectBooleanCheckbox onclick="checkAll_ewpd(this,'prodFamilyForm:prodFamilyDataTable'); ">
								</h:selectBooleanCheckbox>
							</TD>
								<TD width="94%" align="center"> <strong> <h:outputText value="Product Family"> </h:outputText> </strong> </td>
							</tr>
						</table>
					</TD>
				</TR>
				<tr>
					<td>
					<DIV class="popupDataTableDiv" id="popupDataTableDiv">
						<h:dataTable  cellspacing="1" width="100%" columnClasses="selectOrOptionColumn,shortDescriptionColumn"
						 id="prodFamilyDataTable" value="#{ReferenceDataBackingBeanCommon.refDataLookUpList}" 
							var="eachRow" cellpadding="0" bgcolor="#cccccc">
						 	<h:column>
						 	    <f:verbatim>
								<h:selectBooleanCheckbox id="productFamilyChkBox">
								</h:selectBooleanCheckbox>
							</f:verbatim>
							</h:column>
						 	<h:column>
                                          <f:verbatim>&nbsp;</f:verbatim>
                                          <h:inputHidden value="#{eachRow.description}"></h:inputHidden>
                                          <h:inputHidden value="#{eachRow.primaryCode}"></h:inputHidden>
                                          <h:inputHidden value="#{eachRow.parentCatalogId}"></h:inputHidden>
                                          <h:outputText value="#{eachRow. description }"></h:outputText>
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

<script language="javascript">

	tigra_tables('prodFamilyForm:prodFamilyDataTable',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
	setColumnWidth('prodFamilyForm:prodFamilyDataTable','6%');
	window.opener = window.dialogArguments.parentWindow;
	var hiddenObj = window.opener.document.getElementById(window.dialogArguments.hiddenId);
	if(hiddenObj == null || hiddenObj == undefined) {
		alert("Hidden field not available");
	}
	if(hiddenObj.value) {
		matchCheckboxItems_ewpd('prodFamilyForm:prodFamilyDataTable',window.dialogArguments.selectedValues, 2, 2, 2);
	}

</script>
</HTML>


