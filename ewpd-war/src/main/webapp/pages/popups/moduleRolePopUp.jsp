<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"
	title="Style">
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->	
<style type="text/css">

.selectOrOptionColumn {
	width: 8%;
	text-align:center;
	vertical-align: middle;
}.shortDescriptionColumn {
	width: 92%;
	vertical-align: middle;
	 style: text-indent: 2px;
}
</style>
<TITLE>Module Popup</TITLE>
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
	<h:form id="moduleRolePopupForm">
	<h:inputHidden value="#{roleBackingBean.loadModulePopUpList}"></h:inputHidden>
		<h:outputText value="No Modules Available." 
					rendered="#{roleBackingBean.moduleList == null}" 
					styleClass="dataTableColumnHeader"/>
		<DIV id="moduleSelectDiv">
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
		
			<tr>
				<td align="left">&nbsp;<h:commandButton id="selectButton" value="Select" styleClass="wpdbutton"
					onclick="getCheckedItems_ewpd('moduleRolePopupForm:moduleSelectPopupTable',2);return false;"></h:commandButton>
				</td>
			</tr>
		</table>
		<table width="98%" align="right" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>
					<table width="100%" border="0" cellspacing="1" cellpadding="0" id="headerTable"
						bgcolor="#cccccc">
						<tr>
							<TD width="8%" align="center" valign="middle">
								<h:selectBooleanCheckbox onclick="checkAll_ewpd(this,'moduleRolePopupForm:moduleSelectPopupTable'); ">
								</h:selectBooleanCheckbox>
							</TD>
							<TD width="94%" align="center"><strong> <h:outputText
								value="Module Name">
							</h:outputText> </strong></td>
						</tr>
					</table>


				<tr>
					<td>
					<DIV id="popupDataTableDiv" class=popupDataTableDiv>
					<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow"
					columnClasses="selectOrOptionColumn,shortDescriptionColumn"
						cellspacing="1" width="100%"
						id="moduleSelectPopupTable" var="moduleName"
						rendered="#{roleBackingBean.moduleList != null}"
						value="#{roleBackingBean.moduleList}"
						cellpadding="0" bgcolor="#cccccc">
						<h:column>
							<%--f:attribute name="width" value="8%" />
							<f:verbatim>
								<wpd:singleRowSelect groupName="catalog" id="minor"
									rendered="true"></wpd:singleRowSelect>

							</f:verbatim--%>
							
							
								<h:selectBooleanCheckbox id="businessEntityChkBox">
								</h:selectBooleanCheckbox>
						
						</h:column>
						<h:column>
						
                                <f:verbatim>&nbsp;</f:verbatim>
								<h:inputHidden value="#{moduleName.moduleName}" />
								<h:inputHidden value="#{moduleName.moduleId}" />
							<h:outputText  value="#{moduleName.moduleName}"></h:outputText>
						
						</h:column>
					</h:dataTable></DIV></td>
				</tr>

			</TBODY>
		</table>
	</DIV>
	</h:form>
	</BODY>
</f:view>

<script language="javascript">
		initialize();
		function initialize(){
			if(document.getElementById('moduleRolePopupForm:moduleSelectPopupTable') != null) {
				setColumnWidth('moduleRolePopupForm:moduleSelectPopupTable', '40:450');
				setColumnWidth('headerTable', '40:450');
			}else {
				document.getElementById("moduleSelectDiv").style.visibility = 'hidden';
			}
		}
		window.opener = window.dialogArguments.parentWindow;
		var hiddenObj = window.opener.document.getElementById(window.dialogArguments.hiddenId);
		if(hiddenObj == null || hiddenObj == undefined) {
			alert("Hidden field not available");
		}
		if(hiddenObj.value) {
		
			matchCheckboxItems_ewpd('moduleRolePopupForm:moduleSelectPopupTable', window.dialogArguments.selectedValues, 2, 2, 2);
		
		}

</script>
</HTML>
