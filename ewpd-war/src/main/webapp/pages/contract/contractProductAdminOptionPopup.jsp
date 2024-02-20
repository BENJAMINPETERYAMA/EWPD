<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"
	title="Style">
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.selectOrOptionColumn {
	width: 8%;
	text-align: center;
	vertical-align: middle;
	
}
.shortDescriptionColumn {
}
</style>
	
<TITLE>Admin Option Popup</TITLE>
<<%
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
	<h:form id="adminForm">
	<h:inputHidden value = "#{contractProductAdminPopupBackingBean.retrieveAllAdminRecords}"></h:inputHidden>
		<div id="fullInfo">
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
            
			<tr>
				<td align="left">&nbsp;<h:commandButton id="selectButton" value="Select"
					styleClass="wpdbutton"
					onclick="getCheckedItems_ewpd('adminForm:adminDataTable',2);return false;"></h:commandButton>
				</td>
			</tr>

		</table>

		<table width="98%" align="right" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						bgcolor="#cccccc">
						<tr>
							<td width="6%" align="center"><h:selectBooleanCheckbox
								onclick="checkAllid(this,'adminForm:adminDataTable','adminChkBox'); "></h:selectBooleanCheckbox></TD>
							<TD width="94%" align="center"><strong> <h:outputText
								value="Admin Option" styleClass="outputText"></h:outputText></strong></td>
						</tr>
					</table>
					</TD>
				</TR>

				<tr>
					<TD>
					<div id="searchResultdataTableDiv" class="popupDataTableDiv"
						style="height:340px;"><h:dataTable id="adminDataTable"
						value="#{contractProductAdminPopupBackingBean.productAdminList}"
						var="eachRow" cellpadding="2" cellspacing="1" bgcolor="#cccccc" width="100%"
						rendered="#{contractProductAdminPopupBackingBean.productAdminList != null}"
						border="0" rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selectOrOptionColumn,shortDescriptionColumn">

						<h:column>
							
								<h:selectBooleanCheckbox id="adminChkBox">
								</h:selectBooleanCheckbox>
							
							
						</h:column>				
						<h:column>
                            <f:verbatim>&nbsp;</f:verbatim>
							<h:outputText value="#{eachRow.adminDesc}"></h:outputText>
							<h:inputHidden value="#{eachRow.adminKey}"></h:inputHidden>
							<h:inputHidden value="#{eachRow.adminDesc}"></h:inputHidden>
						</h:column>
					</h:dataTable></div>
					</TD>
				</tr>
		</TABLE>
		</div>
		<table>
			<tr>
				<td><w:message></w:message></td>
			</tr>

		</table>
	</h:form>
	</BODY>
</f:view>
<script>

	window.opener = window.dialogArguments.parentWindow;
	var hiddenObj = window.opener.document.getElementById(window.dialogArguments.hiddenId);
	if(hiddenObj == null || hiddenObj == undefined) {
		alert("Hidden field not available");
	}

	hide();
	function hide(){
		obj = getObj('adminForm:adminDataTable');
		if(obj == null || obj.rows.length == 0) {
			obj2 = getObj('fullInfo');
			obj2.innerHTML = '';
		}
	}
</script>

</HTML>

