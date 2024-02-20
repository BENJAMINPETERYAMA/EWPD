<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css" title="Style">
	
<TITLE>Costshare Type Popup</TITLE>
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
	<script language="JavaScript" src="../../js/tigra_tables.js"></script>	
</HEAD>
<f:view>
	<BODY>
		<h:form id="csTypePopupForm">
			<table  border="0" cellpadding="5" cellspacing="0" width="100%">
				<tr>
					<td align="left">
						<h:commandButton id="selectButton" value="Select" styleClass="wpdbutton" onclick="getRadioSelectionContract('csTypePopupForm:cstDataTable');return false;"></h:commandButton>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
	
		 	</table>
			<table width="96%"  align="center" cellpadding="0" cellspacing="0" >
			<TBODY>
				<TR>
					<TD>
						<table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#cccccc">
							<tr>
								<td width="6%" align="left">&nbsp;</TD>
								<TD width="94%" align="center"> <strong> <h:outputText value="Costshare Type"> </h:outputText> </strong> </td>
							</tr>
						</table>
					</TD>
				</TR>
				<tr>
					<td>
					<DIV id="popupDataTableDiv" class=popupDataTableDiv>
						<h:dataTable  cellspacing="1" width="100%" id="cstDataTable" value="#{lgContractRefDataBean.cstTypForComboBox}" var="eachRow" cellpadding="0" bgcolor="#cccccc" >
						 	<h:column >
								<f:verbatim>
									<wpd:singleRowSelect groupName="cst" id="minor" rendered="true"></wpd:singleRowSelect>
								</f:verbatim>

						 	</h:column>
						 	<h:column>
								<h:outputText value="#{eachRow.code}"></h:outputText>
								<h:inputHidden id="hiddenCst" value="#{eachRow.code}"></h:inputHidden>
							</h:column>
						<%-- 
						 	<h:column>
								<h:outputText value="#{eachRow.description}"></h:outputText>
							</h:column>
						--%>
						</h:dataTable>
					</DIV>
					</td>
				</tr>
			</TBODY>
			</table>
		</h:form>
	</BODY>
</f:view>

<script language="javascript">
 	tigra_tables('csTypePopupForm:cstDataTable',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
 	setColumnWidth('csTypePopupForm:cstDataTable','6%');
	window.opener = window.dialogArguments.parentWindow;
	var hiddenObj = window.opener.document.getElementById(window.dialogArguments.hiddenId);
	if(hiddenObj == null || hiddenObj == undefined) {
		alert("Hidden field not available");
	}
	if(hiddenObj.value) {
		matchCheckboxItemsContract('csTypePopupForm:cstDataTable',hiddenObj);
	 }
</script>
</HTML>
