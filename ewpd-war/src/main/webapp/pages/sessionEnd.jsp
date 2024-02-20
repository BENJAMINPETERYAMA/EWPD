<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>
<html>
	<head>
		<title>Wellpoint Product Database</title>
		<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
			title="Style">
	</head>
	<f:view>
	<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
  		<tr>
    		<td width="22%"><img src="../../images/WPLogo.jpg" alt="WellPoint Logo" width="179" height="75" /></td>
     		<td width="78%" align="right" valign="top" class="topRightgraphic">
				&nbsp;  <span id="ApplicationName"> <img src="../../images/applicationName.jpg" alt="WellPoint Product Database System" width="314" height="31" border="0" /></span>
			</td>	
  		</tr>
	</table>
	</body>
	<table height="80%" align="center">
		<tr height="20%">
			<td>&nbsp;</td>
		</tr>
		<tr height="20%">
			<td><span class="pagetitle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Your session is no longer valid.  Please close this window and open a new browser window.</span></td>
		</tr>
		<tr height="60%">
			<td>&nbsp;</td>
		</tr>
	</table>
	<%@ include file="navigation/bottom.jsp" %>
	</f:view>
</html>
<% request.getSession().invalidate(); %>
