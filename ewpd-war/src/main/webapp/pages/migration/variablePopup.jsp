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
<TITLE>Variable Popup</TITLE>
<BASE target="_self" />
</HEAD>
<f:view>
	<BODY onunload="getCheckedItems()">
	<script language="JavaScript" src="../../js/tigra_tables.js"></script>
	<h:form id="variableForm">
	<h:inputHidden id="pageLoad" value="#{ContrMigratProductMappingBackingBean.variablePopup}"/>
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
			<tr width="100%">
			
				<td align="left" width="15%" class ="dataTableHeader"><h:outputText
								value="Description :"></h:outputText></td>
				<td align="left" width="15%"><h:outputText
								value="#{ContrMigratProductMappingBackingBean.benefitLineDescription}"></h:outputText></td>
				<td align="left" width="10%" class ="dataTableHeader"><h:outputText
								value="PVA :"></h:outputText></td>
				<td align="left" width="15%"><h:outputText
								value="#{ContrMigratProductMappingBackingBean.benefitLinePva}"></h:outputText></td>
				<td align="left" width="15%" class ="dataTableHeader"><h:outputText
								value="Reference :"></h:outputText></td>
				<td align="left" width="30%"><h:outputText
								value="#{ContrMigratProductMappingBackingBean.benefitLineReference}"></h:outputText></td>
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
						<TR>
							<TD width="6%" align="left"></TD>
							<TD width="94%" align="center" colspan = "6"><strong><h:outputText
								value="Variables"></h:outputText></strong></TD>
						</TR>
						<tr bgcolor="#ffffff">
							<td width="8%"></td>
							<td width="19%"><strong><h:outputText
								value="Variable"></h:outputText></strong></td>
							<td width="21%"><strong><h:outputText
								value="Description"></h:outputText></strong></td>
							<td width="14%"><strong><h:outputText
								value="PVA"></h:outputText></strong></td>
							<td width="14%"><strong><h:outputText
								value="Format"></h:outputText></strong></td>
							<td width="14%"><strong><h:outputText
								value="Value"></h:outputText></strong></td>
							<td width="10%">&nbsp;</td>
			
						</tr>
					</table>
					</TD>
				</TR>
				<tr>
					<td>
					<DIV id="popupDataTableDiv" class=popupDataTableDiv><h:dataTable
						rowClasses="dataTableEvenRow,dataTableOddRow" cellspacing="1"
						width="100%" id="varTable"
						value="#{ContrMigratProductMappingBackingBean.variableList}"
						var="eachRow" cellpadding="0" bgcolor="#cccccc">
						<h:column>
								<wpd:singleRowSelect groupName="baseContract" id="minor1" rendered="true"></wpd:singleRowSelect>					

						</h:column>
						<h:column>
							
							<h:outputText value="#{eachRow.variableId}"></h:outputText>
							<h:inputHidden value="#{eachRow.variableId}"></h:inputHidden>
						</h:column>
						<h:column>
							
							<h:outputText value="#{eachRow.variableText}"></h:outputText>
							<h:inputHidden value="#{eachRow.variableText}"></h:inputHidden>
						</h:column>
						<h:column>							
							<h:outputText value="#{eachRow.providerArrangement}"></h:outputText>
							<h:inputHidden value="#{eachRow.providerArrangement}"></h:inputHidden>
						</h:column>
											
						<h:column>
							
							<h:outputText value="#{eachRow.format}"></h:outputText>
							<h:inputHidden value="#{eachRow.format}"></h:inputHidden>
						</h:column>	
						<h:column>							
							<h:outputText value="#{eachRow.codedValue}"></h:outputText>
							<h:inputHidden value="#{eachRow.codedValue}"></h:inputHidden>
						</h:column>
						<h:column>
						<h:inputHidden value="#{eachRow.varNoteFlag}"></h:inputHidden>
						<h:graphicImage value="../../images/notes_exist.gif" rendered="#{eachRow.varNoteFlag == 'Y'}" />
																									
						</h:column>
					</h:dataTable></DIV>
					</td>
				</tr>
				<h:inputHidden id="variableId" value="#{ContrMigratProductMappingBackingBean.variableId}"></h:inputHidden>
				<h:inputHidden id="variableDesc" value="#{ContrMigratProductMappingBackingBean.variableDesc}"></h:inputHidden>
			</TBODY>
		</table>
	</h:form>


	</BODY>

</f:view>
<script language="JavaScript">
selectCheckBox();
function selectCheckBox(){

	var tableObject;
	var valueToCompare1= document.getElementById('variableForm:variableId').value;
	if (document.getElementById('variableForm:varTable')!= null ) {
		
		tableObject = document.getElementById('variableForm:varTable');
		var checkboxObject;
		for(var i = 0; i<tableObject.rows.length; i++)
		{
			checkboxObject = tableObject.rows[i].cells[0].children[0];
			valueToCompare2 = tableObject.rows[i].cells[1].children[0].value;
			if(valueToCompare1 == valueToCompare2) {
				checkboxObject.checked = true;
				break;
			}
		}
	}
}

if(null != document.getElementById('variableForm:varTable')){
setColumnWidth('variableForm:varTable','8%:15%:22%:15%:15%:15%:10%');
//matchCheckboxItems_ewpd('variableForm:varTable', window.dialogArguments.selectedValues,2, 1, 1)
}
function getCheckedItems_variable(id)
{
	var tableObject;
	var noteFlag ='N';	
	if (document.getElementById(id))
	{
		tableObject = document.getElementById(id);
		var selectedValues = '';
		var cnt = 0;
		var currentCell;
		for (var i=0;i<tableObject.rows.length;i++)
		{
			var checkboxObject = tableObject.rows[i].cells[0].children[0];
			currentCell = tableObject.rows[i].cells[1];
			if (null!= tableObject.rows[i].cells[6].children[0]){
				noteFlag =tableObject.rows[i].cells[6].children[0].value;
				
			}
			if (checkboxObject && checkboxObject.checked) {
				if(cnt > 0)
					selectedValues += '@';
				 selectedValues += tableObject.rows[i].cells[1].children[0].value + '@' +
						tableObject.rows[i].cells[2].children[0].value + '@' + 
						tableObject.rows[i].cells[3].children[0].value + '@'+ 
						tableObject.rows[i].cells[4].children[0].value + '@' + 
						tableObject.rows[i].cells[5].children[0].value + '@' + 
						noteFlag;
				cnt++;
			}
		}	
	}
	window.returnValue = selectedValues;
	window.close();	
	if(cnt > 0)
		return true;
	return false;
}
function getCheckedItems(){

	var varDesc = document.getElementById('variableForm:variableDesc').value;
	var variable = varDesc.split('/');	
	if(variable[0]== undefined){		
		variable[0] = "";	
	}
	if(variable[1]== undefined){		
		variable[1] = "";	
	}
	if(variable[2]== undefined){		
		variable[2] = "";	
	}
	if(variable[3]== undefined){		
		variable[3] = "";	
	}

	var retVal = variable[0]+'@'+variable[1]+'@'+variable[2]+'@'+variable[3];
	if(retVal=='@@@'){
			retVal ="";
	}
	window.returnValue = retVal;
	return false;
}
function selectFn(){
	document.body.onunload = "";
	getCheckedItems_variable('variableForm:varTable');
	return false;
}
</script>

</HTML>