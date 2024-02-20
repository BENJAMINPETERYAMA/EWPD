<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/contractpopups/ReservedIdPopup.java" --%><%-- /jsf:pagecode --%>
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
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.selectOrOptionColumn {
	}
.shortDescriptionColumn {
	width: 92%;
}
</style>

<TITLE>Contract Id Popup</TITLE>
</HEAD>
<f:view>
	<BODY>
	<script language="JavaScript" src="../../js/tigra_tables.js"></script>
	<h:form id="contractCodeForm">
		<div id="noContractsDiv">
		<h:outputText value="No Contracts Available Or all contracts locked by other user." 
					styleClass="dataTableColumnHeader"/></div>
		<div id="fullInfo">
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
			<tr>
				<td align="left">&nbsp;
				<h:commandButton id="txtSelect"
					styleClass="wpdbutton" value="Select"
					onclick="getCheckedItems_ewpd('contractCodeForm:contractCodeTable',2);return false;">
				</h:commandButton></td>
			</tr>

		</table>

		<table width="98%" align="right" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						bgcolor="#cccccc">
						<TR>
							<TD width="6%" align="left"></TD>
							<TD width="94%" align="center"><strong><h:outputText
								value="Contract Id"></h:outputText></strong></TD>
						</TR>
					</table>
					</TD>
				</TR>
				<tr>
					<td>
					<DIV id="popupDataTableDiv" class=popupDataTableDiv><h:dataTable
						rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selectOrOptionColumn,shortDescriptionColumn"
						cellspacing="1" width="100%" id="contractCodeTable" 
						value="#{contractPopupBackingBean.contractIdExistingList}" 
						var="eachRow" cellpadding="0" bgcolor="#cccccc">
						<h:column>
							<f:verbatim>
								<wpd:singleRowSelect groupName="baseContract" id="minor1" rendered="true"></wpd:singleRowSelect>								
							</f:verbatim>

						</h:column>
						<h:column>
							
                            <f:verbatim>&nbsp;</f:verbatim>
							<h:outputText value="#{eachRow.contractId}"></h:outputText>
							<h:inputHidden value="#{eachRow.contractId}"></h:inputHidden>
							<h:inputHidden value="#{eachRow.contractSysId}"></h:inputHidden>
						</h:column>
					</h:dataTable></DIV>
					</td>
				</tr>
			</TBODY>
		</table>
	</div>
		<table>
			<tr>
				<td><w:messageForPopup></w:messageForPopup></td>
			</tr>

		</table>

	</h:form>


	</BODY>

</f:view>
<script language="JavaScript">
setColumnWidth('contractCodeForm:contractCodeTable','8%:92%');
matchCheckboxItems_ewpd('contractCodeForm:contractCodeTable', window.dialogArguments.selectedValues,2, 1, 1)
	var tableObject=document.getElementById('contractCodeForm:contractCodeTable');
		if(tableObject.rows.length > 0){


			document.getElementById("noContractsDiv").style.visibility = 'hidden';
			document.getElementById("noContractsDiv").style.height = "0px";;
		}else {

			document.getElementById("fullInfo").style.visibility = 'hidden';
			document.getElementById("noContractsDiv").style.visibility = 'visible';
		}

</script>

</HTML>