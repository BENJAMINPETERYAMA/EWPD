<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/contractpopups/CheckoutProductPopup.java" --%><%-- /jsf:pagecode --%>
<HTML>
<HEAD>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="PRAGMA" content="NO-CACHE">

<META HTTP-EQUIV="Expires" CONTENT="-1">

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
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.selectOrOptionColumn {
	width: 8%;
	
}
.shortDescriptionColumn {

}
</style>

<TITLE>WellPoint Product Database: Contract Check Out</TITLE>
</HEAD>
<f:view>
	<BODY>
	<script language="JavaScript" src="../../js/tigra_tables.js"></script>
	<h:inputHidden id="printRule"
			value="#{contractBasicInfoBackingBean.checkoutValues}"></h:inputHidden>
	<table>
		<tr>
			<h:form id="dateSegmentPopupForm">
				<table border="0" cellpadding="5" cellspacing="0" width="100%">
					<tr>
						<td align="left"><h:commandButton id="txtSelect"
							styleClass="wpdbutton" value="Select"
							onclick="getChecked();return false;">
						</h:commandButton></td>
					</tr>

				</table>
		</tr>
		<tr>
			<table width="97%" align="right" cellpadding="0" cellspacing="0">
				<TBODY>
					<TR>
						<TD>
						<DIV id="ProductHead">
						<table width="100%" border="0" cellspacing="1" cellpadding="0"
							bgcolor="#cccccc">
							<TR>
								<TD align="center"><strong><h:outputText value="Product Option"></h:outputText></strong></TD>
							</TR>
						</table>
						</DIV>
						</TD>
					</TR>
					<tr>
						<td><h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow"
							columnClasses="shortDescriptionColumn,shortDescriptionColumn"
							cellspacing="1" width="100%" id="productTable"
							value="#{contractBasicInfoBackingBean.productCheckoutList}"
							var="eachRow2" cellpadding="0" bgcolor="#cccccc"
							rendered="#{contractBasicInfoBackingBean.productCheckoutList != null}">
							<h:column>
								<f:verbatim>
									<wpd:singleRowSelect groupName="GroupSIze1" id="minor1"
										rendered="true"></wpd:singleRowSelect>
								</f:verbatim>
							</h:column>
							<h:column>

								<h:inputHidden id="hiddenHQStateId1" value="#{eachRow2.code}"></h:inputHidden>
								<h:inputHidden id="hiddenHQStateDesc1"
									value="#{eachRow2.description}"></h:inputHidden>

								<f:verbatim>
									<h:outputText value="#{eachRow2.description}"></h:outputText>
								</f:verbatim>
							</h:column>
						</h:dataTable></td>
					</tr>

				</TBODY>
			</table>
		
		<br />
		<br />
		<br />
			<br />
		<br />
		<br />
			<br />
		<br />
		<br />
	
			<table width="97%" align="right" cellpadding="0" cellspacing="0">
				<TBODY>
					<TR>
						<TD>
						<DIV id="NotesHead" >
						<table width="100%" border="0" cellspacing="1" cellpadding="0"
							bgcolor="#cccccc" rendered="#{contractBasicInfoBackingBean.noteCheckoutList != null}">
							<TR>
								<TD align="left" width="6%">
								<h:selectBooleanCheckbox onclick="checkAll_ewpd(this,'dateSegmentPopupForm:noteTable'); ">
								</h:selectBooleanCheckbox>
							</TD>
							<TD align="center" width="94%">
								<strong><h:outputText value="Select the datesegments which you want to upgrade with the latest version of the notes"></h:outputText></strong>
							</TD>
							</TR>
						</table>
						</DIV>
						</TD>
					</TR>
					<tr>
						<td><h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow"
						    columnClasses="selectOrOptionColumn,shortDescriptionColumn"
							cellspacing="1" width="100%" id="noteTable"
							value="#{contractBasicInfoBackingBean.noteCheckoutList}"
							var="eachRow" cellpadding="0" bgcolor="#cccccc"
							rendered="#{contractBasicInfoBackingBean.noteCheckoutList != null}">
							<h:column>
								
								<h:selectBooleanCheckbox id="minor1">
								</h:selectBooleanCheckbox>
							</h:column>
							<h:column>

								<h:inputHidden id="hiddenHQStateId" value="#{eachRow.code}"></h:inputHidden>
								<h:inputHidden id="hiddenHQStateDesc"
									value="#{eachRow.description}"></h:inputHidden>

								<f:verbatim>
									<h:outputText value="#{eachRow.description}"></h:outputText>
								</f:verbatim>
							</h:column>
						</h:dataTable></td>
					</tr>

				</TBODY>
			</table>
		
		</tr>
	</table>
	</h:form>


	</BODY>

</f:view>
<script language="JavaScript">

function getChecked(){
var tbl = document.getElementById('dateSegmentPopupForm:productTable');
var count =0;
if(tbl != null){	
for (var i=0;i<tbl.rows.length;i++)
{
	var checkboxObject = tbl.rows[i].cells[0].children[0];
			if (checkboxObject && checkboxObject.checked)
			{	
			count++;
			}		
}
if(count ==0){
	return false;
}
}
	getCheckedItems('dateSegmentPopupForm:productTable','dateSegmentPopupForm:noteTable',1);return false;

}

var tblobj1 = document.getElementById('dateSegmentPopupForm:productTable');
var tblobj2 = document.getElementById('dateSegmentPopupForm:noteTable');


if(tblobj1 != null){	
tblobj1.rows[0].cells[0].children[0].checked= 'true';
setColumnWidth('dateSegmentPopupForm:productTable','8%:92%');
}
if(tblobj2.rows.length > 0){

setColumnWidth('dateSegmentPopupForm:noteTable','8%:92%');
}
else{
document.getElementById('NotesHead').style.display='none';
}

if(tblobj1 == null){
	 document.getElementById('ProductHead').style.display='none';
	 
}

if(tblobj1 == null && tblobj2.rows.length == 0){
	window.returnValue = "" +'~'+ "";
<%
if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
%>
window.close();
<%
}
else {
%>
parent.document.getElementsByTagName('dialog')[0].close();
<%
}
%>
}

function getCheckedItems(id1,id2,attrCount)
{
	var tableObject;	
	if (document.getElementById(id1))
	{
		tableObject = document.getElementById(id1);
		var selectedValues = '';
		var cnt = 0;
		var currentCell;
		for (var i=0;i<tableObject.rows.length;i++)
		{
			var checkboxObject = tableObject.rows[i].cells[0].children[0];
			currentCell = tableObject.rows[i].cells[1];
			
			if (checkboxObject && checkboxObject.checked) {
				if(cnt > 0)
					selectedValues += '~';

				switch(attrCount){
					case 1: selectedValues += currentCell.children[0].value; 
							break;
					case 2: selectedValues += currentCell.children[0].value + '~' + currentCell.children[1].value;	
							break;
					case 3: selectedValues += currentCell.children[0].value + '~' + currentCell.children[1].value + '~' + currentCell.children[2].value;
							break;
					default: alert('invalid attrCount parameter for function getCheckedItems');
				}
				cnt++;
			}
		}	
	}

	if (document.getElementById(id2))
	{
		tableObject2 = document.getElementById(id2);
		var selectedValues1 = '';
		var cnt = 0;
		var currentCell;
		for (var i=0;i<tableObject2.rows.length;i++)
		{
			var checkboxObject = tableObject2.rows[i].cells[0].children[0];
			currentCell = tableObject2.rows[i].cells[1];
			
			if (checkboxObject && checkboxObject.checked) {
				if(cnt > 0)
					selectedValues1 += '~';

				switch(attrCount){
					case 1: selectedValues1 += currentCell.children[0].value; 
							break;
					case 2: selectedValues1 += currentCell.children[0].value + '~' + currentCell.children[1].value;	
							break;
					case 3: selectedValues1 += currentCell.children[0].value + '~' + currentCell.children[1].value + '~' + currentCell.children[2].value;
							break;
					default: alert('invalid attrCount parameter for function getCheckedItems');
				}
				cnt++;
			}
		}	
	}
	window.returnValue = selectedValues +'~'+ selectedValues1;
<%
if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
%>
window.close();
<%
}
else {
%>
parent.document.getElementsByTagName('dialog')[0].close();
<%
}
%>
	if(cnt > 0)
		return true;
	return false;
}
</script>

</HTML>
