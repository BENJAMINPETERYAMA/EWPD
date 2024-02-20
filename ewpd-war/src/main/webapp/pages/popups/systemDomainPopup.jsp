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
	width: 8%;
	text-align:center;
	vertical-align: middle;
}.shortDescriptionColumn {
	width: 92%;
	vertical-align: middle;
	 style: text-indent: 2px;
}
</style>
<TITLE>Target Systems Popup</TITLE>
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
	<h:form id="systemDomainForm">
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
			
			<tr>
				<td align="left">&nbsp;<h:commandButton id="selectButton" value="Select"
					styleClass="wpdbutton"
					onclick="getCheckedItems_ewpd('systemDomainForm:systemDomainTable',2);return false;"></h:commandButton>
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
							<TD width="8%" align="center" valign="middle">
								<h:selectBooleanCheckbox onclick="checkAll_ewpd(this,'systemDomainForm:systemDomainTable'); ">
								</h:selectBooleanCheckbox>
							</TD>
							<TD width="92%" align="center">
								<strong><h:outputText value="System Domain "></h:outputText></strong>
							</TD>
						</TR>
					</table>
					</TD>
				</TR>
				<tr>
					<td>
					<DIV id="popupDataTableDiv" class=popupDataTableDiv>
					<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow"
						columnClasses="selectOrOptionColumn,shortDescriptionColumn"
						cellspacing="1" width="100%" id="systemDomainTable"
						value="#{ReferenceDataBackingBeanCommon.systemDomainResultList}" var="eachRow"
						cellpadding="0" bgcolor="#cccccc" >
						<h:column>
						
								<h:selectBooleanCheckbox id="systemDomainChkBox">
								</h:selectBooleanCheckbox>
						
							
						</h:column>
						<h:column>
						
							<h:inputHidden value="#{eachRow.description}"></h:inputHidden>
							<h:inputHidden value="#{eachRow.primaryCode}"></h:inputHidden>
							<f:verbatim>&nbsp;</f:verbatim>
							<h:outputText value="#{eachRow.description}"></h:outputText>
						

						</h:column>
					</h:dataTable>
					</DIV>
					</td>
				</tr>
			</TBODY>
		</table>
		<h:inputHidden id="systemDomainTxtHidden"		value="#{notesBackingBean.systemDomainCode}"/>
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

		matchCheckboxItems_ewpd('systemDomainForm:systemDomainTable', window.dialogArguments.selectedValues, 2, 2, 2);
	}

/*
	Function checks the checkboxes depending on the value of the source.
	
	Parameters
	table_id		- DataTable id
	source			- The data depending on which the check boxes will be checked.
					  This should be window.dialogArguments.selectedValues
	attrCount(of source)	- The number of attributes of the tilda String that constitues  a single data.
							  eg: id1~desc1~name1~id2~desc2~name2 	- here 3 attributes represents a single data. 
					  									  		 	- so the count is 3.
	attrPos(of source)		- Position of the attribute within the tilda String that needs to be shown in the div/inputText.
					  		  eg: id1~desc1~name1~id2~desc2~name2 - if 1 is given, id1, id2 will be used to match the data with DataTable.
																  - if 2 is given, desc1, desc2 will be used to match the data with DataTable.
	tableChildPos			- detail about the data to which the source is to be checked. the position of the hidden field
							  in the second column of data table.
								eg : if given 1 -> the source will be matched against the first hidden field of second column of data table.
									 if given 2 -> the source will be matched against the second hidden field of second column of data table.
*/	


function matchCheckboxItems_ewpd(table_id, source, attrCount, attrPos, tableChildPos) {
	var tableObject;
	var valueToCompare1;
	var splittedVal;
	attrPos -= 1;
	
	if (document.getElementById(table_id) && source != null && source !='' && source != undefined) {
		var splittedVal = source.split("~");
		tableObject = document.getElementById(table_id);
		var checkboxObject;
		var systemDomainCode = document.getElementById('systemDomainForm:systemDomainTxtHidden').value.split("~");

		for(var i = 0; i<tableObject.rows.length; i++)
		{
			checkboxObject = tableObject.rows[i].cells[0].children[0];
			valueToCompare1 = tableObject.rows[i].cells[1].children[tableChildPos-1].value;
			for(var j = 0; j<splittedVal.length; j+=attrCount)
			 {
				valueToCompare2 = splittedVal[j+attrPos];
				if(valueToCompare1 == valueToCompare2) {
					checkboxObject.checked = true;
					for(var m = 0; m<systemDomainCode.length; m+=attrCount)
					 {
						if(valueToCompare1 == systemDomainCode[m+attrPos]) {
							checkboxObject.disabled = true;
						}
					}
					break;
				}
			}
		}
	}
	
}


</script>
</HTML>


