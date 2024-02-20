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
	width: 10%;
	text-align:center;
	vertical-align: middle;
}.shortDescriptionColumn {
}
</style>
	
<TITLE>Product Structure Popup </TITLE>
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
		<h:form id="prodStructForm">
			<table  border="0" cellpadding="5" cellspacing="0" width="100%">
		
				<tr>
					<td align="left">
						&nbsp;<h:commandButton id="selectButton" disabled="true" value="Select" styleClass="wpdbutton" onclick="getCheckedItems_ewpd('prodStructForm:prodStructDataTable',2);return false;"></h:commandButton>
					</td>
				</tr>
		 	</table>
			<table width="98%"  align="right" cellpadding="0" cellspacing="0" >
			<TBODY>
				<TR>
					<TD>
						<table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#cccccc">
							<tr>
								<td width="10%" align="center" valign="middle"></TD>
								<TD align="left" width="90%" height="20px"><strong><h:outputText value="Product Structure"> </h:outputText> </strong> </td>
							</tr>
						</table>
					</TD>
				</TR>
				<tr>
					<td>
					<DIV class="popupDataTableDiv" id="popupDataTableDiv">
						<h:dataTable  cellspacing="1" width="100%" columnClasses="selectOrOptionColumn,shortDescriptionColumn"
						 id="prodStructDataTable" value="#{productGeneralInformationBackingBean.productStructureList}" var="singleValue" cellpadding="0" bgcolor="#cccccc">
						 	<h:column >
								<f:verbatim> <wpd:singleRowSelect groupName="majorHeading" id="major" rendered="true"></wpd:singleRowSelect> </f:verbatim>
						 	</h:column>
						 	<h:column>
								<f:verbatim>&nbsp;</f:verbatim>
						 		<h:outputText value="#{singleValue.productStructureName}"></h:outputText>
                                <f:verbatim>&nbsp;</f:verbatim>
								<h:inputHidden value="#{singleValue.productStructureName}"></h:inputHidden>
								<h:inputHidden value="#{singleValue.productStructureId}"></h:inputHidden>
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
	document.getElementById('prodStructForm:selectButton').disabled  = false;
	tigra_tables('prodStructForm:prodStructDataTable',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
	setColumnWidth('prodStructForm:prodStructDataTable','6%');
	window.opener = window.dialogArguments.parentWindow;
	var hiddenObj = window.opener.document.getElementById(window.dialogArguments.hiddenId);
	if(hiddenObj == null || hiddenObj == undefined) {
		alert("Hidden field not available");
	}
	if(hiddenObj.value) {
		matchCheckboxItems_ewpd('prodStructForm:prodStructDataTable', window.dialogArguments.selectedValues, 2, 2, 2);
	}
</script>
</HTML>

