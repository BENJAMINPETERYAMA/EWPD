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
<TITLE>Minor Heading Id Popup</TITLE>
<BASE target="_self" />
</HEAD>
<f:view>
	<BODY onunload="getCheckedItems()">
	<script language="JavaScript" src="../../js/tigra_tables.js"></script>
	<h:form id="legacyMinorHeadingForm">
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
				<td align="left"><h:commandButton id="selectBtn"
					styleClass="wpdbutton" value="Select"
					onclick="selectFn()">
				</h:commandButton></td>
			</tr>
		</table>
		<br/>

		<table>
			<tr>
				<td width="80">
				</td>
				<td width="145" valign="top" height="26">
				<strong><h:outputText styleClass="" id="majorHeadingType" value="Select Major Heading">
				</h:outputText></strong></td>
				<td colspan="2" valign="top" width="180">
					<h:selectOneMenu id="majorHeadingList" immediate="true"  value="#{legacyContractBackingBean.majorHeadingId}"
						onchange="submitFn()">
						<f:selectItems value="#{legacyContractBackingBean.majorHeadingDescList}" />
					</h:selectOneMenu>
				</td>
				<td valign="top">&nbsp;</td>
		</tr>
		</table>
		<table>
			<tr><td><span>Please select a minor heading so that its note will be attached to the standard benefit.</span></td></tr>
		</table>
		<table width="96%" align="center" id="HeadingTable" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>
					<div id ="headerDiv">
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						bgcolor="#cccccc">
						<TR>
							<TD width="6%" align="left"></TD>
							<TD width="94%" align="center"><strong><h:outputText
								value="Minor Headings"></h:outputText></strong></TD>
						</TR>
					</table>
					</div>
					</TD>
				</TR>
				<tr>
					<td>
					<DIV id="popupDataTableDiv" class=popupDataTableDiv><h:dataTable
						rendered="#{legacyContractBackingBean.minorHeadingList != null}"
						rowClasses="dataTableEvenRow,dataTableOddRow" cellspacing="1"
						width="100%" id="minorHeadingTable"
						value="#{legacyContractBackingBean.minorHeadingList}"
						var="eachRow" cellpadding="0" bgcolor="#cccccc">
						<h:column>
							<wpd:singleRowSelect groupName="minorHeading" id="minorHdg" rendered="true"></wpd:singleRowSelect>
						</h:column>
						<h:column>
							<h:inputHidden value="#{eachRow.minorHeadingId}"></h:inputHidden>
							<h:inputHidden value="#{eachRow.minorHeadingDesc}"></h:inputHidden> 
							<h:outputText value="#{eachRow.minorHeadingId}"></h:outputText>
						</h:column>
						<h:column>
							<h:outputText value="#{eachRow.minorHeadingDesc}"></h:outputText>
						</h:column>
					</h:dataTable></DIV>
					</td>
					<h:commandLink id="majorHeadingSelectLink" action="#{legacyContractBackingBean.majorHeadingSelectAction}" 
						style="display:none; visibility: hidden;"><f:verbatim></f:verbatim></h:commandLink>
	<h:inputHidden id="minorHeadingId" value="#{legacyContractBackingBean.minorHeadingFromScreen}"></h:inputHidden>
				</tr>
			</TBODY>
		</table>
	</h:form>
</BODY>
</f:view>
<script>
hideHeadingTable();
selectCheckBox();
function hideHeadingTable() {
		var tableObj = document.getElementById('legacyMinorHeadingForm:minorHeadingTable');

		if(tableObj != null) {
			setColumnWidth('legacyMinorHeadingForm:minorHeadingTable','8%:18%:84%');
		}
		//else{
		//	alert("hi");
		 //   var tableObj = document.getElementById('legacyMinorHeadingForm:headerDiv');
		//	alert(tableObj);
		//	alert(tableObj.style.visibility);
		//	tableObj.style.visibility = 'hidden';
			//tableObj.style.height = "0px";
			//tableObj.style.position = 'absolute';
		//}
	}

function selectFn(){
	document.body.onunload = "";
	getCheckedItems_minorHeading('legacyMinorHeadingForm:minorHeadingTable',2);return false;
}
function submitFn(){
	document.body.onunload = "";
	submitLink('legacyMinorHeadingForm:majorHeadingSelectLink'); 
	document.body.onunload = "getCheckedItems()";
	return false;
}
function selectCheckBox(){

	var tableObject;
	var valueToCompare1= document.getElementById('legacyMinorHeadingForm:minorHeadingId').value;
	if (document.getElementById('legacyMinorHeadingForm:minorHeadingTable')!= null ) {
		
		tableObject = document.getElementById('legacyMinorHeadingForm:minorHeadingTable');
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
function getCheckedItems(){
	window.returnValue = document.getElementById('legacyMinorHeadingForm:minorHeadingId').value;
	document.getElementById('legacyMinorHeadingForm:majorHeadingList').value = null;
	document.forms[0].submit();
	return false;
}
function getMinorHeadings(){
	document.getElementById('legacyMinorHeadingForm:getMinorHeadingsLink').click();
}
function getCheckedItems_minorHeading(id)
{
	var tableObject;	
	if (document.getElementById(id))
	{
		tableObject = document.getElementById(id);
		var selectedValues = '';
		var cnt = 0;
		var radioButtonObj;
		for (var i=0;i<tableObject.rows.length;i++)
		{
			radioButtonObj = tableObject.rows[i].cells[0].children[0];
			if(radioButtonObj && radioButtonObj.checked) {
				selectedValues += tableObject.rows[i].cells[1].innerText;
				//alert(selectedValues);
				}
	  }
	}
	if(selectedValues != ''){
		window.returnValue = selectedValues;
	}else{
		window.returnValue = '';
	}
	document.getElementById('legacyMinorHeadingForm:majorHeadingList').value = null;
	window.close();	
	if(cnt > 0)
		return true;
	return false;
}
</script>
</html>