<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css"
	title="Style">

<TITLE>Reference mapping Print</TITLE>
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


	<TABLE cellpadding="0" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td width="1000">
			<FIELDSET
				style="margin-left:30px;margin-right:-6px;padding-bottom:1px;padding-top:1px;width:99%">
			<h:outputText id="breadcrumb">
			</h:outputText></FIELDSET>
			</td>
		</tr>
		<TR>
			<TD width="1000"><br />

			<FIELDSET
				style="margin-left:30px;margin-right:-6px;padding-bottom:1px;padding-top:1px;width:99%">
			<div id="printInfo"></div>

			</FIELDSET>
			</TD>
		</TR>

	</TABLE>

	</BODY>
</f:view>
<script>
window.opener = window.dialogArguments.parentWindow;
var doc = window.opener.document;
document.getElementById('printInfo').innerHTML =doc.getElementById('info').innerHTML;
document.getElementById('breadcrumb').innerHTML = 'Administration >> Reference Mapping ('+doc.getElementById('indViewForm:indDesc').innerHTML+') >> Print';
window.print();
</script>
</HTML>

