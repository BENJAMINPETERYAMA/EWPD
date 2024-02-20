<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/contractpopups/SelectOneContractDateSegmentsPopup.java" --%><%-- /jsf:pagecode --%>
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
<TITLE>WellPoint Product Database: Contract Date Segments</TITLE>
</HEAD>
<f:view>
<BODY>
<script language="JavaScript" src="../../js/tigra_tables.js"></script>

	<h:inputHidden id="temp" value="#{contractSearchBackingBean.initPopup}"></h:inputHidden>

	<h:form id="dateSegmentPopupForm">
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
			<tr>
				<td align="left">			
					<h:commandButton id="txtSelect" styleClass="wpdbutton" value="Select" 
							onclick="getCheckedItems_ewpd('dateSegmentPopupForm:groupSizePopupTable',1);return false;">
					</h:commandButton>
				</td>
			</tr>
			
		</table>

		<table width="97%" align="right" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						bgcolor="#cccccc">
						<TR>
							<TD align="center"><strong><h:outputText
								value="Date Segments Option"></h:outputText></strong></TD>
						</TR>
					</table>
					</TD>
				</TR>
				<tr>
					<td><h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow"
						cellspacing="1" width="100%" id="groupSizePopupTable"
						value="#{contractSearchBackingBean.dateSegDelOptionList}"
						var="eachRow" cellpadding="0" bgcolor="#cccccc">
						<h:column>
							<f:verbatim>
								<wpd:singleRowSelect groupName="GroupSIze" id="minor1"
									rendered="true"></wpd:singleRowSelect>
							</f:verbatim>
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
	</h:form>
		
			
</BODY>
		
</f:view>
<script language="JavaScript">
setColumnWidth('dateSegmentPopupForm:groupSizePopupTable','8%:92%');

matchCheckboxItems_ewpd('dateSegmentPopupForm:groupSizePopupTable', window.dialogArguments.selectedValues, 2,2,2)
</script>

</HTML>