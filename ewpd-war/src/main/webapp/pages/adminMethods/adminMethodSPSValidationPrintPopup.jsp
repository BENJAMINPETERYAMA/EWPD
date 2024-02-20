

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

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
<BASE target="_self" />
<TITLE>Admin Method SPS Validation Print popup</TITLE>
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
<br>
<TABLE width="100%" cellpadding="1" cellspacing="1" border="0">

					<TR><td>
<FIELDSET
						style="margin-left:1px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:100%">
					<h:outputText id="breadcrumb" value= "#{adminMethodSPSValidationBackingBean.breadCrumbText}">

					</h:outputText>
</FIELDSET>
</td></TR>
</TABLE>
<BR>
		<div id ="state">
		</div>
		<div id ="tierHead"> 
		</div>		
		<div id ="stateTier"> 
		</div>
	</BODY>
</f:view>

<script language="JavaScript">
	var parentDoc = window.dialogArguments; 
    var tierHeaderData = "#{adminMethodSPSValidationBackingBean.breadCrumbText}";
	//document.getElementById('breadcrumb').value =  parentDoc.getElementById('setBreadCrumb').value;
	document.getElementById('state').innerHTML =  parentDoc.getElementById('resultHeaderDiv').innerHTML;	
	document.getElementById('stateTier').innerHTML =  parentDoc.getElementById('resultTierDiv').innerHTML;
	document.getElementById('tierHead').innerHTML =  parentDoc.getElementById('tierHeaderDiv').innerHTML;
    //if(parentDoc.getElementById('resultTierDiv').innerHTML !=null )
	//{
     //  document.getElementById('tierHeader').innerHTML =  '<b>&nbsp;&nbsp;&nbsp;&nbsp; Tiered Processing Methods </b>';
	//}

</script>
</html>

