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

<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->	
<style type="text/css">

.selectMajorNtsColumn {
	
}
.longDescriptionColumn {
	width: 92%;
}
</style>
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
<TITLE>Minor Heading Notes Popup</TITLE>
<BASE target="_self" />
</HEAD>
<f:view>
	<BODY onunload="getCheckedItems()">
	<script language="JavaScript" src="../../js/tigra_tables.js"></script>
	<h:form id="legacyMinorNotesForm">
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
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
				<td align="left"><DIV id="selectDiv"><h:commandButton id="selectBtn"
					styleClass="wpdbutton" value="Select"
					onclick="selectFn()">
				</h:commandButton></DIV></td>
			</tr>
		</table>
		<br/><div id="resultHeaderDiv">
		<table width="96%" align="center" id="HeadingTable" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						bgcolor="#cccccc">
						<TR>
							<TD width="6%" align="left"></TD>
							<TD width="94%" align="center"><strong><h:outputText
								value="P00-MM"></h:outputText></strong></TD>
						</TR>
					</table>
					</TD>
				</TR>
				<tr>
					<td>
					<DIV id="popupDataTableDiv" class=popupDataTableDiv><h:dataTable
						rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selectMajorNtsColumn,longDescriptionColumn"  cellspacing="1"
						width="100%" id="minorNotesTable"
						value="#{legacyContractBackingBean.minorNotesList}"
						rendered="#{legacyContractBackingBean.minorNotesList != null}"
						var="eachRow" cellpadding="0" bgcolor="#cccccc">
						<h:column>
								<h:selectBooleanCheckbox title=""
 					 			value="" >
							</h:selectBooleanCheckbox>
						</h:column>
						<h:column>
							
							<h:outputText value="#{eachRow.minorNotes}"></h:outputText>
						</h:column>
					</h:dataTable></DIV>
					</td>
					<h:inputHidden id="checkboxFlags" value="#{legacyContractBackingBean.minorNotesFlag}"></h:inputHidden>
				</tr>
			</TBODY>
		</table></div><TABLE>
							<TBODY>
								<tr>
									<TD><!-- Insert WPD Message Tag --> <w:message
										value="#{legacyContractBackingBean.messageList}"></w:message>
									</TD>
								</tr>
							</TBODY>
						</TABLE>
	</h:form>
</BODY>
</f:view>
<script>

if(document.getElementById('legacyMinorNotesForm:checkboxFlags').value=='Y'){
	var notesObject = document.getElementById('legacyMinorNotesForm:minorNotesTable');
	if(notesObject!=null){
	var notesCheckboxObject = notesObject.rows[0].cells[0].children[0];
	notesCheckboxObject.checked = true;
	}
}
function getCheckedFlag(id)
{
	var tableObject;	
	if (document.getElementById(id))
	{
		tableObject = document.getElementById(id);
		var selectedValues = '';
	
			var checkboxObject = tableObject.rows[0].cells[0].children[0];
		
			if (checkboxObject && checkboxObject.checked) {
				selectedValues = 'Y';
				
			}		
	}	
	window.returnValue = selectedValues;
	window.close();	
	
	return false;
}

function getCheckedItems(){
	window.returnValue = document.getElementById('legacyMinorNotesForm:checkboxFlags').value;
	return false;
}
function selectFn(){
	document.body.onunload = "";
	getCheckedFlag('legacyMinorNotesForm:minorNotesTable');
	return false;
}
hideIfNoValue('resultHeaderDiv','selectDiv');
	function hideIfNoValue(divId1,divId2){
		var notesObject = document.getElementById('legacyMinorNotesForm:minorNotesTable');
		if(notesObject!=null){
			document.getElementById(divId1).style.visibility = 'visible';
			document.getElementById(divId2).style.visibility = 'visible';

		}else{
			document.getElementById(divId1).style.display = 'none';
			document.getElementById(divId2).style.display = 'none';
		}
	}
</script>
</html>