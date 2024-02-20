<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">

<TITLE>Admin Look Up Popup</TITLE>
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
	<h:form id="adminLookUpForm">
		<h:outputText value="No Notes Available." 
					rendered="#{adminAttachmentBackingBean.notes == null}" 
					styleClass="dataTableColumnHeader"/>
		<DIV id="notesDiv">
			<table border="0" cellpadding="5" cellspacing="0">
				<tr>
					<td align="left" width="25">&nbsp;<h:commandButton id="select"
						value="Select" styleClass="wpdbutton"
						onclick="getCheckedItems_ewpd('adminLookUpForm:adminDataTable',2);return false;"></h:commandButton>
					</td>
				</tr>
	
			</table>
			<table width="98%" align="right" cellpadding="0" cellspacing="0"
				border="0">
				<TBODY>
					<TR>
						<TD>
						<table id="headerTable" border="0" cellspacing="1" cellpadding="0"
							bgcolor="#cccccc">
							<tr class="dataTableColumnHeader"
								style="background-color:#cccccc;">
								<td align="left"><h:selectBooleanCheckbox onclick="checkAll_ewpd(this,'adminLookUpForm:adminDataTable'); "></h:selectBooleanCheckbox></TD>
								
								<TD align="left"><strong> <h:outputText
									value="Admin Name">
								</h:outputText> </strong></td>
							</tr>
						</table>
						</TD>
					</TR>
					<tr>
						<td>
						<DIV id="popupDataTableDiv" style="height: 337px; overflow:auto;">
						<h:dataTable cellspacing="1" id="adminDataTable" 
							rendered="#{adminAttachmentBackingBean.notes != null}"
							value="#{adminAttachmentBackingBean.notes}"
							var="singleValue" cellpadding="0" bgcolor="#cccccc"
							rowClasses="dataTableEvenRow,dataTableOddRow">
							<h:column>
								<f:verbatim>
									<h:selectBooleanCheckbox id="componentChkBox">
									</h:selectBooleanCheckbox>
								</f:verbatim>
							</h:column>
							<h:column>
								<h:inputHidden value="#{singleValue.noteName}"></h:inputHidden>
								<f:verbatim>&nbsp;</f:verbatim>
								<h:outputText value="#{singleValue.noteName}"/>
							</h:column>
							
						</h:dataTable></DIV>
						</td>
					</tr>
				</TBODY>
			</table>
	</DIV>
	</h:form>
	</BODY>
</f:view>

<script language="javascript">
	initialize();
	window.opener = window.dialogArguments.parentWindow;
	function initialize(){
		if(document.getElementById('adminLookUpForm:adminDataTable') != null) {
			setColumnWidth('adminLookUpForm:adminDataTable', '40:150:300');
			setColumnWidth('headerTable', '40:150:300');
		}else {
			document.getElementById("notesDiv").style.visibility = 'hidden';
		}
	}
	

	window.opener = window.dialogArguments.parentWindow;
	var hiddenObj = window.opener.document.getElementById(window.dialogArguments.hiddenId);
	if(hiddenObj == null || hiddenObj == undefined) {
		alert("Hidden field not available");
	}
	if(hiddenObj.value) {
		matchCheckboxItems_ewpd('adminLookUpForm:adminDataTable', window.dialogArguments.selectedValues, 2, 2, 2)
	}
</script>
</HTML>
