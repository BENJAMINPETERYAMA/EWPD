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
<TITLE>Major Heading Popup</TITLE>
</HEAD>
<f:view>
	<BODY>
	<script language="JavaScript" src="../../js/tigra_tables.js"></script>
	<h:form id="majorHeadingForm">
	<h:inputHidden id="pageLoad" value="#{ContrMigratProductMappingBackingBean.pageLoad}"/>
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
				<td align="left"><h:commandButton id="txtSelect"
					styleClass="wpdbutton" value="Select"
					onclick="getCheckedItems_ewpd('majorHeadingForm:majorHeadingTable',2);return false;">
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
							<TD width="94%" align="center"><strong><h:outputText
								value="Major Headings"></h:outputText></strong></TD>
						</TR>
					</table>
					</TD>
				</TR>
				<tr>
					<td>
					<DIV id="popupDataTableDiv" class=popupDataTableDiv><h:dataTable
						rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selectMajorNtsColumn,longDescriptionColumn"  cellspacing="1"
						width="100%" id="majorHeadingTable"
						value="#{ContrMigratProductMappingBackingBean.majorHeadingsList}"
						var="eachRow" cellpadding="0" bgcolor="#cccccc">
						<h:column>
							<f:verbatim>
								<wpd:singleRowSelect groupName="baseContract" id="minor1" rendered="true"></wpd:singleRowSelect>								
							</f:verbatim>

						</h:column>
						<h:column>
							
							<h:outputText value="#{eachRow.majorHeadingText}"></h:outputText>
							<h:inputHidden value="#{eachRow.majorHeadingText}"></h:inputHidden>
							<h:inputHidden value="#{eachRow.majorHeading}"></h:inputHidden> 
						</h:column>



					</h:dataTable></DIV>
					</td>
				</tr>
			</TBODY>
		</table>
	</h:form>


	</BODY>

</f:view>
<script language="JavaScript">
if(null != document.getElementById('majorHeadingForm:majorHeadingTable')){
setColumnWidth('majorHeadingForm:majorHeadingTable','8%:92%');
var temp =  window.dialogArguments.selectedValues;
window.opener = window.dialogArguments.parentWindow;
var source = window.opener.document.getElementById('wizardForm:majorHeadingIdHidden').value;
matchCheckboxItems_ewpd('majorHeadingForm:majorHeadingTable', source,1, 1, 2)
//matchCheckboxItems_ewpd('majorHeadingForm:majorHeadingTable', window.dialogArguments.selectedValues,2, 1, 1)
}

</script>

</HTML>