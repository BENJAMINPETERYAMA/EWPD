<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
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
	
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->	
<style type="text/css">

.selectOrOptionColumn {
	width: 10%;
	text-align:center;
	vertical-align: middle;
}.shortDescriptionColumn {
	width: 90%;
}
</style>

<TITLE>Components Popup</TITLE>
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
	<BODY><hx:scriptCollector id="scriptCollector1">
	<h:form id="benefitcompForm">
		<h:inputHidden value="#{AssociatedBenefitComponentBackingBean.retrieveAllBenefitComponentRecords}"></h:inputHidden>
		<h:outputText value="No Benefit Components Available." rendered="#{AssociatedBenefitComponentBackingBean.selectBenefitComponents == null}" styleClass="dataTableColumnHeader" />
		<DIV id="benefitComponentDiv">
		<TABLE border="0" cellpadding="5" cellspacing="0">
			<TR>
				<TD align="left" width="78">&nbsp;<h:commandButton
						id="select" value="Select" styleClass="wpdbutton"
						onclick="getCheckedItems_ewpd('benefitcompForm:benefitComponentDataTable',2);return false;"></h:commandButton>
				</TD>
			</TR>
		</TABLE>
		<TABLE width="98%" align="right" cellpadding="0" cellspacing="0" border="0">
			<TBODY>
				<TR>
					<TD>
					<TABLE id="headerTable" border="0" cellspacing="1" cellpadding="0" width="100%" bgcolor="#cccccc">
						<TR class="dataTableColumnHeader" style="background-color:#cccccc;">
							<TD width="7%" align="center" valign="middle"><h:selectBooleanCheckbox onclick="checkAll_ewpd(this,'benefitcompForm:benefitComponentDataTable'); "></h:selectBooleanCheckbox></TD>
							<TD width="90%" align="center" valign="middle"><STRONG><h:outputText value="Benefit Component">
							</h:outputText> </STRONG></TD>
						</TR>
					</TABLE>
					</TD>
				</TR>
				<TR>
					<TD>
					<DIV id="popupDataTableDiv" style="height: 316px; overflow:auto;">
					<h:dataTable cellspacing="1" id="benefitComponentDataTable" columnClasses="selectOrOptionColumn,shortDescriptionColumn"
					 rendered="#{AssociatedBenefitComponentBackingBean.selectBenefitComponents != null}" value="#{AssociatedBenefitComponentBackingBean.selectBenefitComponents}" var="singleValue" cellpadding="0" bgcolor="#cccccc" rowClasses="dataTableEvenRow,dataTableOddRow">
						<h:column>
						
								<h:selectBooleanCheckbox id="componentChkBox">
								</h:selectBooleanCheckbox>
						</h:column>
						<h:column>
							<h:inputHidden value="#{singleValue.name}"></h:inputHidden>
							<h:inputHidden value="#{singleValue.benefitComponentId}"></h:inputHidden>
							<f:verbatim>&nbsp;</f:verbatim>
							<h:outputText value="#{singleValue.name}"></h:outputText>

						</h:column>
					</h:dataTable></DIV>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		</DIV>
	</h:form>
	</hx:scriptCollector></BODY>
</f:view>

<script language="javascript">
	initialize();
	window.opener = window.dialogArguments.parentWindow;
	function initialize(){
		if(document.getElementById('benefitcompForm:benefitComponentDataTable') != null) {
			setColumnWidth('benefitcompForm:benefitComponentDataTable', '40:450');
			setColumnWidth('headerTable', '40:450');
		}else {
			document.getElementById("benefitComponentDiv").style.visibility = 'hidden';
		}
	}
	

	window.opener = window.dialogArguments.parentWindow;
	var hiddenObj = window.opener.document.getElementById(window.dialogArguments.hiddenId);
	if(hiddenObj == null || hiddenObj == undefined) {
		alert("Hidden field not available");
	}
	if(hiddenObj.value) {
		matchCheckboxItems_ewpd('benefitcompForm:benefitComponentDataTable', window.dialogArguments.selectedValues, 2, 2, 2)
	}
</script>
</HTML>
