<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../../css/wpd.css">
<script language="JavaScript" src="../../js/tigra_tables.js"></script>
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
	text-align: center;
	vertical-align: middle;
}
.shortDescriptionColumn {
	width: 20%;
}
.longDescriptionColumn {
	width: 70%;
}
</style>

<TITLE>Data Type Popup</TITLE>
</HEAD>
<f:view>
	<BODY>
	<h:form id="dataTypeSelectForm">

		<table border="0" cellpadding="5" cellspacing="0" width="100%">
			<tr>
				<td align="left">&nbsp;<input id="selectButtonId" disabled="disabled" type="button" class="wpdbutton"
					name="action" value="Select"
					onClick="getCheckedItems_ewpd('dataTypeSelectForm:dataTypeTable',2);" />
				</td>
			</tr>
		</table>


		<table width="98%" border="0" align="right" cellpadding="0"
			cellspacing="0" bgcolor="#cccccc">
			<tr>
				<td>
				<div id="popHeading"></div>
				<table id="dataTypeDisplayTable" width="100%" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="10%" align="center" valign="middle"><input name="checkbox" type="checkbox"
							id="checkall" 
							onClick="checkAll_ewpd(this,'dataTypeSelectForm:dataTypeTable',1,1);"></td>
						<td align="left" width="20%"></td>
						<td width="70%" align="left"><strong>&nbsp;&nbsp;<h:outputText
							value="Data Type" styleClass="outputText"></h:outputText></strong></td>

					</tr>
				</table>
			</td>
		</tr>	
					<tr>
						<td colspan="3" width="100%"><div id="dataTableHeaderDiv" style="overflow:auto;"><h:dataTable styleClass="outputText"
							headerClass="dataTableHeader" id="dataTypeTable" var="dataType"
							cellpadding="0" width="100%" cellspacing="1"
							value="#{ReferenceDataBackingBeanCommon.dataTypeList}"
							rowClasses="dataTableEvenRow,dataTableOddRow"
							columnClasses="selectOrOptionColumn,shortDescriptionColumn,longDescriptionColumn" >

							<h:column>
								
								<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
									id="dataTypeChkBx" value="#{dataType.dataTypeBx}"></h:selectBooleanCheckbox>
							</h:column>
							<h:column>
								
								<h:inputHidden id="dataTypeHidden"
									value="#{dataType.dataTypeId}" />
								<h:inputHidden id="dataTypeHiddenName"
									value="#{dataType.dataTypeLgnd}" />
								<h:outputText id="dataType" value="#{dataType.dataTypeLgnd}"
									style="padding-left: 5px"></h:outputText>
							</h:column>
							<h:column>
								
								<h:outputText id="dataTypeId" value="#{dataType.dataTypeName}"
									style="padding-left: 5px"></h:outputText>
								
							</h:column>
						</h:dataTable></div></td>
					</tr>
				</table>
				

		<script language="javascript">
			document.getElementById('selectButtonId').disabled  = false;
			tigra_tables('dataTypeSelectForm:dataTypeTable',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
			
			matchCheckboxItems_ewpd('dataTypeSelectForm:dataTypeTable', window.dialogArguments.selectedValues, 2, 2, 2);
		</script>
		<!--<input type="button" class="wpdbutton" name="something" value="somevalue" />-->
	</h:form>
	</BODY>
</f:view>
</HTML>
