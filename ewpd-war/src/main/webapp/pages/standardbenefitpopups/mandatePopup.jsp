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
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"	title="Style">
<LINK href="../../theme/Master.css" rel="stylesheet"
	type="text/css">
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
<TITLE>Mandate Popup</TITLE>
</HEAD>
<f:view>
<BODY>
<script language="JavaScript" src="../../js/tigra_tables.js"></script>
	<h:form id="MandatePopupForm">
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
				<td align="left">			
					<h:commandButton id="txtSelect" styleClass="wpdbutton" value="Select" 
							onclick="getCheckedItems_ewpd('MandatePopupForm:MandatePopupTable',2);return false;">
					</h:commandButton>
				</td>
			</tr>
		</table>

		<table width="96%" align="center" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						bgcolor="#cccccc">
						<TR>
							<TD width="6%" align="left">
								<f:verbatim></f:verbatim>
							</TD>
							<TD width="94%" align="center">
								<strong><h:outputText value="Mandates"></h:outputText></strong>
							</TD>
						</TR>
					</table>
					</TD>
				</TR>
				<tr>
					<td>
					<DIV id="popupDataTableDiv" class=popupDataTableDiv>
					<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow"
						cellspacing="1" width="100%" id="MandatePopupTable"
						value="#{benefitMandateBackingBean.mandateListPopUp}" var="eachRow"
						cellpadding="0" bgcolor="#cccccc">
						<h:column>
							<f:verbatim>
								<wpd:singleRowSelect groupName="Mandate" id="minor1" rendered="true"></wpd:singleRowSelect>								
							</f:verbatim>
						</h:column>
						<h:column>
							<h:inputHidden id="hiddenMandateId" value="#{eachRow.mandateId}"></h:inputHidden>
							<h:inputHidden id="hiddenMandateDesc" value="#{eachRow.mandateDescription}"></h:inputHidden>
							<h:outputText value="#{eachRow.mandateDescription}"></h:outputText>
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
<script language="JavaScript">
setColumnWidth('MandatePopupForm:MandatePopupTable','12%:88%');

matchCheckboxItems_ewpd('MandatePopupForm:MandatePopupTable', window.dialogArguments.selectedValues, 2, 2, 2)
</script>

	
<!-- Test 
<script language="JavaScript">

	alert("hhhh");
	var obj = window.dialogArguments;
	var check1 = obj.check;
	alert("saf"+check1);
	var tableObject = document.getElementById("MandatePopupForm:MandatePopupTable");

	for(var i = 0; i < tableObject.rows.length; i++)
	{
		var checkBoxObj = tableObject.rows[i].cells[0].children[0];
		var dataObj = tableObject.rows[i].cells[1].children[0];
		alert("val:"+dataObj.value+"=="+check1);
		if( check1 == dataObj.value )
		{
			checkBoxObj.checked = true;
		}
	}
</script>
<!-- End Test -->



</HTML>