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
<script language="JavaScript" src="../../js/menu.js"></script>
<script language="JavaScript" src="../../js/menu_items.js"></script>
<script language="JavaScript" src="../../js/menu_tpl.js"></script>
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
<script language="JavaScript" src="../../js/prototype.js"></script>
<script language="JavaScript" src="../../js/rico.js"></script>
<script language="JavaScript" src="../../js/generalInfoFieldValidation.js"></script>

<script language="JavaScript">
	var timer;
	window.onload = function(){
				startTimer();
	}
  window.history.forward();
</script>
<script language="JavaScript">
			<!--
			new menu (PRINT_MENU_ITEMS, PRINT_MENU_POS);
			// -->
</script>

<f:verbatim>
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
  <tr>
    <td width="22%" ><img src="../../images/WPLogo.jpg" alt="WellPoint Logo"  width="179" height="75" /></td>
     <td width="78%" align="right" valign="top" class="topRightgraphic"   >

<a href="../../pages/navigation/logout.jsp?logout=true">  &nbsp;  <span id="ApplicationName"> <img src="../../images/applicationName.jpg" alt="WellPoint Product Database System" width="314" height="31" border="0" /></span>

</td>	
  </tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
            <TD tabindex="10">
</f:verbatim>

<f:verbatim></TD></tr></table>
	<table width="100%" border="0" cellpadding="5" cellspacing="0" class ="breadcrumb" >
      <tr>
        <td >&nbsp;</f:verbatim><h:outputText id="breadCrumb1" value="#{requestScope.breadCrumbText}"> </h:outputText><f:verbatim></td>
<td align="left" width="20%">
</f:verbatim>
<h:commandButton rendered = "#{contractRuleBackingBean.checkMode == 'view' && contractRuleBackingBean.pnrRuleSelected == 'false'}" alt="Extract to Excel" id="extractRuleButton"
										 image="../../images/excel_icon.gif" style="cursor: hand"
										 onclick="invokeServlet()"></h:commandButton>
<f:verbatim>
</td>
      </tr>
    </table>	
</f:verbatim>
