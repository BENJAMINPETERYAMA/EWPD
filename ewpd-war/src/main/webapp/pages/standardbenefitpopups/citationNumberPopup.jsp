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

<TITLE>Citation Number Popup</TITLE>
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
	<h:form id="citationNumberForm">
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
            <tr>
				<td>
				<TABLE>
					<TBODY>
						<TR>
							<TD>&nbsp;</TD>
						</TR>
					</TBODY>
				</TABLE>
				</td>
			</tr>
			<tr>
				<td align="left"><h:commandButton id="selectButton" value="Select"
					styleClass="wpdbutton"
					onclick="getTextFieldItems('citationNumTable');return false;"></h:commandButton>
				</td>
			</tr>

		</table>
		<table width="96%" align="center" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>
					<table width="100%" border="0" cellspacing="1" cellpadding="3"
						bgcolor="#cccccc">
						<tr>
							<TD width="94%" align="center"><strong> <h:outputText
								value="Citation Number">
							</h:outputText> </strong></td>
						</tr>
					</table>
					</TD>
				</TR>
				<tr>
					<td>
					<DIV id="citationDiv" style="overflow:auto;">
					<table id="citationNumTable" width="100%" cellspacing="1"
						bgcolor="#cccccc">
						<tr>
							<td align="center"><input id="textfieldid" name="textfield"
								type="text" class="formInputField" maxlength="50"></td>
						</tr>
						<tr>
							<td align="center"><input id="textfieldId" name="textfield"
								type="text" class="formInputField" maxlength="50"></td>
						</tr>
						<tr>
							<td align="center"><input id="textfieldId" name="textfield"
								type="text" class="formInputField" maxlength="50"></td>
						</tr>
						<tr>
							<td align="center"><input id="textfieldId" name="textfield"
								type="text" class="formInputField" maxlength="50"></td>
						</tr>
					</table>
					</DIV>
					</td>
				</tr>
				<TR>
					<TD>
					<table border="0" cellpadding="5" cellspacing="0" width="100%"
						bgcolor="WHITE">
						<tr>
							<td align="left"><input type="button" class="wpdbutton"
								name="action2" value="Add More"
								onClick="insertRows('citationNumTable',true);" /></td>
						</tr>
					</table>
					</TD>
				</TR>
			</TBODY>
		</table>
	</h:form>
	</BODY>
</f:view>

<script language="javascript">
	window.opener = window.dialogArguments.parentWindow;
	var hiddenObj = window.opener.document.getElementById(window.dialogArguments.hiddenId);
	if(hiddenObj == null || hiddenObj == undefined) {
		alert("Hidden field not available");
	}
	if(hiddenObj.value && hiddenObj.value != '') {
		var disabledValues = window.opener.document.getElementById('genInfoForm:protectedCitations');
		setCitation(hiddenObj,'citationNumTable',disabledValues);

	}
	tigra_tables('citationNumTable',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
	

function isNumberKey(thisObj, thisEvent) {
//use 'thisObj' to refer directly to this component instead of keyword 'this'
//use 'thisEvent' to refer to the event generated instead of keyword 'event'
onkeypress="return isNumberKey(event);"
}
function isNumberKey(thisObj, thisEvent) {
//use 'thisObj' to refer directly to this component instead of keyword 'this'
//use 'thisEvent' to refer to the event generated instead of keyword 'event'
//use 'thisObj' to refer directly to this component instead of keyword 'this'
//use 'thisEvent' to refer to the event generated instead of keyword 'event'
onkeypress="return isNumberKey(event);"
}
function isNumberKey(thisObj, thisEvent) {
//use 'thisObj' to refer directly to this component instead of keyword 'this'
//use 'thisEvent' to refer to the event generated instead of keyword 'event'
//use 'thisObj' to refer directly to this component instead of keyword 'this'
//use 'thisEvent' to refer to the event generated instead of keyword 'event'
//use 'thisObj' to refer directly to this component instead of keyword 'this'
//use 'thisEvent' to refer to the event generated instead of keyword 'event'
onkeypress="return isNumberKey(event);"
}</script>
</HTML>
