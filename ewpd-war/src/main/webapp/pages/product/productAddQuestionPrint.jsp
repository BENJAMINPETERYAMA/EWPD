<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
<script>window.print();</script>
<TITLE>Print For Admin Option Question</TITLE>
</HEAD>

<f:view>
	<BODY>
	<h:form styleClass="form" id="productQuestionDetailedForm">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD width="5"></TD>
				<TD width="5"></TD>
				<TD width="1"></TD>
			</TR>
		</TABLE>
		<!--	Start of Table for actual Data	-->
		<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
			<TR>
				<TD>
					&nbsp;
				</TD>
			</TR>
			<TR>
				<TD>
					<FIELDSET style="margin-left:2px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:99.5%">
						<h:outputText id="breadcrumb" 
							value="#{productAdminOptionBackingBean.printBreadCrumbText}">
						</h:outputText>
					</FIELDSET>
				</TD>
			</TR>
			<TR>
				<TD>
					&nbsp;
				</TD>
			</TR>
			<!-- Administration tab starts-->
			<TR id="adminDiv"><h:outputText
				value="No Administration Questions Available."
				styleClass="dataTableColumnHeader" />
			</TR>
			<TR  id="productAdministration">
				<td>
				<TABLE border="0" cellspacing="0" cellpadding="0" width="100%"
							class="outputText">
					<TBODY>
						<TR>
							<td></td>
						</TR>
						<TR>
							<td valign="middle">
							<div id="LabelHeaderDiv"
								style="background-color:#cccccc;z-index:1;height:20px;width:100%">
							<B>&nbsp;<h:outputText value="Selected Questions"></h:outputText> </B>
							</div>
							<div id="displayHeaderDiv"
								style="background-color:#FFFFFF;z-index:1;overflow:auto;"><h:panelGrid
								id="displayHeaderTable"
								binding="#{productAdminOptionBackingBean.headerPanel}"
								rowClasses="dataTableOddRow">
							</h:panelGrid></div>
							<div id="displayPanelContent12"
								style="position:relative;height:200px;"><h:panelGrid
								id="panelTable"
								binding="#{productAdminOptionBackingBean.viewPanel}"
								rowClasses="dataTableOddRow">
							</h:panelGrid></div>

							</td>
						</TR>
					</TBODY>
				</TABLE>
				</TD>
			</TR>
			<!-- Administration tab ends-->
		</TABLE>

		<h:inputHidden id="printAdministration"
			value="#{productAdminOptionBackingBean.printValue}"></h:inputHidden>
	</h:form>
	</BODY>
</f:view>

<script>
	var printForAdminQuestion = document.getElementById("productQuestionDetailedForm:printAdministration").value;
	if( null == printForAdminQuestion || "" == printForAdminQuestion ){
		var proDenialDivObj = document.getElementById('productAdministration');
		proDenialDivObj.style.visibility = "hidden";
		proDenialDivObj.style.height = "0px";
		proDenialDivObj.innerText = null;
	}

	initialize();
	function initialize(){
		if(document.getElementById('productQuestionDetailedForm:panelTable') != null) {
			setColumnWidth('productQuestionDetailedForm:panelTable','25%:25%:25%:25%');
			setColumnWidth('productQuestionDetailedForm:displayHeaderTable','25%:25%:25%:25%');
			document.getElementById('adminDiv').style.visibility = 'hidden';
		}else {
			document.getElementById('productAdministration').style.visibility = 'hidden';
		}
	}
</script>
</HTML>

