

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>WellPoint Product Database</title>
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
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/tree.js"></script>
<script language="JavaScript" src="../../js/menu.js"></script>
<script language="JavaScript" src="../../js/menu_items.js"></script>
<script language="JavaScript" src="../../js/menu_tpl.js"></script>
<script language="JavaScript" src="../../js/tree_items.js"></script>
<script language="JavaScript" src="../../js/tree_tpl.js"></script>







<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="22%"><img src="../../images/WPLogo.jpg" alt="WellPoint Logo" width="179" height="75" /></td>
     <td width="78%" align="right" valign="top" class="topRightgraphic">

<a href="../../pages/navigation/logout.jsp?logout=true"> &nbsp;  <span id="ApplicationName"> <img src="../../images/applicationName.jpg" alt="WellPoint Product Database System" width="314" height="31" border="0" /></span>Logout</a>

</td>	
  </tr>
</table>






