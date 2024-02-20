<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="../../css/wpd.css" rel="stylesheet" type="text/css">
<TITLE>WellPoint Product Database System</TITLE>
</HEAD>
<f:view>
	<BODY>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
		    <td width="22%"><img src="../../images/WPLogo.jpg" alt="WellPoint Logo" width="179" height="75" /></td>
		    <td width="78%" align="right" valign="top" class="topRightgraphic">
		<span id="ApplicationName"> <img src="../../images/applicationName.jpg" alt="WellPoint Product Database System" width="314" height="31" border="0" /></span>
		</td>	
		  </tr>
		</table>
		<br/>
		<br/>
	    	<div class="infoDiv" style="margin:5 5 5 5">
			    <li id="infoItem">You have successfully logged out of WPD.  Please close this browser window.</li>
			</div>
	</BODY>
	
</f:view>
</HTML>
<%if(request.getParameter("logout") != null){
	request.getSession().invalidate();
  }%>