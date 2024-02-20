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
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">
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
<TITLE>VariableNotePopup</TITLE>
<BASE target="_self" />
</HEAD>
<f:view>
	<BODY onunload="getCheckedItems()">
	
	<h:form id="variableNotesForm">
	<h:inputHidden id="pageLoad" value="#{ContrMigratProductMappingBackingBean.variableNotePopup}"/>
		 <table border="0" cellpadding="5" cellspacing="0" width="96%">
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
				<td colspan = "6" align="left"><h:commandButton id="txtSelect"
					styleClass="wpdbutton" value="Select"
					onclick="selectFn()">
				</h:commandButton></td>
			</tr>

		</table>

		<table width="96%" align="center" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						bgcolor="#cccccc">
						<TR bgcolor="#cccccc">
							<TD width="6%" align="left"></TD>
							<TD width="94%" align="center" colspan = "5"><strong><h:outputText
								value="#{ContrMigratProductMappingBackingBean.variableDescForNotes}"></h:outputText></strong></TD>
						</TR>
						<TR class="dataTableEvenRow">
							<TD width="6%" align="left"><h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
										id="notesCheckBox" value="#" rendered="#{ContrMigratProductMappingBackingBean.variableNotes != null}"></h:selectBooleanCheckbox></TD>
							<TD width="94%" align="center" colspan = "5"><h:outputText
								value="#{ContrMigratProductMappingBackingBean.variableNotes}" rendered="#{ContrMigratProductMappingBackingBean.variableNotes != null}"></h:outputText></TD>
						</TR>
						
					</table>
					</TD>
				</TR>
				<h:inputHidden id="checkboxFlag" value="#{ContrMigratProductMappingBackingBean.notesCheckboxFlag}"></h:inputHidden>
			</TBODY>
		</table>
	</h:form>


	</BODY>

</f:view>
<script language="JavaScript">



if(document.getElementById('variableNotesForm:checkboxFlag').value=='Y'){
	var notesObject = document.getElementById('variableNotesForm:notesCheckBox');
	notesObject.checked = true;
}

function getCheckedFlag(id)
{
	var tableObject;	
	var selectedValues = '';
	if (document.getElementById(id))
	{
		tableObject = document.getElementById(id);
		if(tableObject){
			if (tableObject && tableObject.checked) {
				selectedValues = 'Y';
			}
		}		
	}	
	window.returnValue = selectedValues;
	window.close();	
	
	return false;
}
function getCheckedItems(){
	window.returnValue = document.getElementById('variableNotesForm:checkboxFlag').value
	return false;
}
function selectFn(){
	document.body.onunload = "";
	getCheckedFlag('variableNotesForm:notesCheckBox');
	return false;
}
</script>
</HTML>
