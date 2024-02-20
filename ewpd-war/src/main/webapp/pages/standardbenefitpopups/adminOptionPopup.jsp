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
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.selectOrOptionColumn {
	width: 10%;
	text-align: center;
	vertical-align: middle;
}
.shortDescriptionColumn {
	width: 90%;
}
</style>

<TITLE>Admin Options Popup</TITLE>
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
	<h:form id="adminOptionPopupForm">
	<h:inputHidden value="#{adminOptionsBackingBean.dummyField}"></h:inputHidden>
		<h:outputText value="No Admin Options Available." 
					rendered="#{adminOptionsBackingBean.adminOptionsListForPopup == null}" 
					styleClass="dataTableColumnHeader"/>
	<DIV id="adminOptionsDiv">
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
			<tr>
				<td align="left">&nbsp;<h:commandButton id="selectButton" value="Select"
					styleClass="wpdbutton"
					onclick="getCheckedItems_ewpd('adminOptionPopupForm:adminOptionDataTable',2);return false;"></h:commandButton>
				</td>
			</tr>

		</table>
		<table width="98%" align="right" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						bgcolor="#cccccc">
						<tr height="20px">
							<TD width="2%" align="center" valign="middle"><h:selectBooleanCheckbox
								onclick="checkAll_ewpd(this,'adminOptionPopupForm:adminOptionDataTable'); ">
							</h:selectBooleanCheckbox></TD>
							<td width="8%" align="left">&nbsp;</TD>
							<TD width="90%" align="center"><strong> <h:outputText
								value="Admin Options">
							</h:outputText> </strong></td>
						</tr>
					</table>
					</TD>
				</TR>
				<tr>
					<td>
					<DIV id="popupDataTableDiv"  style="height:253px; overflow:auto;" class=popupDataTableDiv><h:dataTable
						cellspacing="1" width="100%" id="adminOptionDataTable"
						rendered="#{adminOptionsBackingBean.adminOptionsListForPopup != null}"
						value="#{adminOptionsBackingBean.adminOptionsListForPopup}"
						var="eachRow" cellpadding="0" bgcolor="#cccccc" 
						columnClasses="selectOrOptionColumn,shortDescriptionColumn" >
						<h:column>
							
							<h:selectBooleanCheckbox id="adminOptionsChkBox">
							</h:selectBooleanCheckbox>

						</h:column>
						<h:column>
						
                            <f:verbatim>&nbsp;</f:verbatim>
							<h:inputHidden id="hiddenAdminOptSysId"
								value="#{eachRow.adminOptionSystemId}"></h:inputHidden>
							<h:inputHidden id="hiddenAdminOptDesc"
								value="#{eachRow.adminOptDescText}"></h:inputHidden>
                            
							<h:outputText value="#{eachRow.adminOptDescText}" style="width: 400px"></h:outputText>

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
	function initialize(){
		if(document.getElementById('adminOptionPopupForm:adminOptionDataTable') == null){ 
				 document.getElementById("adminOptionsDiv").style.visibility = 'hidden';
		}else {
			tigra_tables('adminOptionPopupForm:adminOptionDataTable',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
			matchCheckboxItems_ewpd('adminOptionPopupForm:adminOptionDataTable', window.dialogArguments.selectedValues, 2, 2, 2);
			setColumnWidth('adminOptionPopupForm:adminOptionDataTable', '10:90');
		}
	}
	
 	
</script>
</HTML>
