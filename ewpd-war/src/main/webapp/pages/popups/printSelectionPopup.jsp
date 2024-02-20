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

<TITLE>Print Selection Popup</TITLE>
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
<BASE>
</HEAD>
<BODY>
<form name="printOptionform">
<TABLE border="0">
	<TBODY>
		<TR>
			<TD width="31"><INPUT type="radio" name="printSelection" value="1"
				checked></TD>
			<TD width="266">Print current page</TD>
		</TR>
		<TR>
			<TD width="31"><INPUT type="radio" name="printSelection" value="2"></TD>
			<TD width="266">Print details</TD>
		</TR>
		<TR>
			<TD width="31"><INPUT type="button" name="print" Class="wpdButton"
				value="Print" onclick="printPage();return false;"></TD>
			<TD width="266"><INPUT type="button" value="Cancel"
				onclick="window.close();" Class="wpdButton"></TD>
		</TR>
	</TBODY>
</TABLE>
</form>
</BODY>
</HTML>
