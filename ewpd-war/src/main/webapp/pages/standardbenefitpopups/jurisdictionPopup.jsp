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
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.selectOrOptionColumn {
	width: 10%;
}
.shortDescriptionColumn {
	width: 92%;
}
</style>
	
<TITLE>State Code Popup</TITLE>
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
		<h:form id="stateCodeForm">
			<table  border="0" cellpadding="5" cellspacing="0" width="100%">
            <tr>
				<td>
				<TABLE>
					<TBODY>
						<TR>
							<TD>&nbsp;</TD>
						</TR>
					</TBODY>
				</TABLE>
				</td>
			</tr>
				<tr>
					<td align="left">
						<h:commandButton id="selectButton" value="Select" styleClass="wpdbutton" onclick="getCheckedItems_ewpd('stateCodeForm:stateCodeDataTable',2);return false;"></h:commandButton>
					</td>
				</tr>
	
		 	</table>
			<table width="96%"  align="center" cellpadding="0" cellspacing="0" >
			<TBODY>
				<TR>
					<TD>
						<table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#cccccc">
							<tr>
								<td width="6%" align="left"> <h:selectBooleanCheckbox onclick="checkAll_ewpd(this,'stateCodeForm:stateCodeDataTable'); "></h:selectBooleanCheckbox></TD>
								<TD width="94%" align="center"> <strong> <h:outputText value="Jurisdiction"> </h:outputText> </strong> </td>
							</tr>
						</table>
					</TD>
				</TR>
				<tr>
					<td>
					<DIV id="popupDataTableDiv" class="popupDataTableDiv">
						<h:dataTable  cellspacing="1" width="100%" id="stateCodeDataTable" value="#{ReferenceDataBackingBeanCommon.refDataLookUpList}" 
						var="singleValue" cellpadding="0" bgcolor="#cccccc" columnClasses="selectOrOptionColumn,shortDescriptionColumn" >
						 	<h:column >

								<f:verbatim> <h:selectBooleanCheckbox id="stateCodeChkBox"> </h:selectBooleanCheckbox>  </f:verbatim>
						 	</h:column>
						 	<h:column>
						 				 
                                          <h:inputHidden value="#{singleValue.description}"></h:inputHidden>
                                          <h:inputHidden value="#{singleValue.primaryCode}"></h:inputHidden>
                                          <h:inputHidden value="#{singleValue.parentCatalogId}"></h:inputHidden>
                                          <h:outputText value="#{singleValue.description }"></h:outputText>
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

	tigra_tables('stateCodeForm:stateCodeDataTable',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
	
	window.opener = window.dialogArguments.parentWindow;
	var hiddenObj = window.opener.document.getElementById(window.dialogArguments.hiddenId);
	if(hiddenObj == null || hiddenObj == undefined) {
		alert("Hidden field not available");
	}
	if(hiddenObj.value) {
		var disabledValues = window.opener.document.getElementById('genInfoForm:disabledStateCodes');
		matchCheckboxItems('stateCodeForm:stateCodeDataTable',hiddenObj,disabledValues);

	}

	matchCheckboxItems_ewpd('stateCodeForm:stateCodeDataTable',window.dialogArguments.selectedValues,2,2,2);


</script>
</HTML>
