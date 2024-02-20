<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<link href="../../css/wpd.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
<link rel="stylesheet" href="../../css/wpd.css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"
	title="Style">

<script language="JavaScript" src="../../js/timeout.js"></script>
<script language="JavaScript">
	var timer;
	window.onload = function(){
		startTimer();
	}
    window.history.forward();
</script>

<f:verbatim>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="22%"><img src="../../images/WPLogo.jpg" alt="WellPoint Logo" width="179" height="75" /></td>
     <td width="78%" align="right" valign="top" class="topRightgraphic">

<a href="../../pages/navigation/logout.jsp?logout=true"> &nbsp;  <span id="ApplicationName"> <img src="../../images/applicationName.jpg" alt="WellPoint Product Database System" width="314" height="31" border="0" /></span>Logout</a>

</td>	
  </tr>
</table>
<!--   WAS 6.0 Migration Changes  style="margin-top: -13px; added for fixing the white space issue--> 
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: -13px;">
            <tr>
            <TD>
</f:verbatim>
<h:form id="menuForm">	

<h:inputHidden id="jscook_action"></h:inputHidden>
<x:jscookMenu layout="hbr" theme="ThemeOffice" javascriptLocation="/MenuResources" styleLocation="/MenuResources" imageLocation="/MenuResources">
    <x:navigationMenuItems value="#{menuBean.menu}"  />
</x:jscookMenu>
</h:form>
<f:verbatim></TD></tr></table>
	<table width="100%" border="0" cellpadding="5" cellspacing="0" class ="breadcrumb" >
     
    </table>	
</f:verbatim>


