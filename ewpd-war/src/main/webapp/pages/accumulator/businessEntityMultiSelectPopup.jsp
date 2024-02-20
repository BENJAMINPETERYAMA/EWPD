<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css" title="Style">
	
<TITLE>Business Entity Popup</TITLE>
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
		<h:form id="businessEntityForm">
			<table  border="0" cellpadding="5" cellspacing="0" width="100%">
				<tr>
					<td align="left"><h:commandButton id="selectButton" value="Select"
					styleClass="wpdbutton"
					onclick="getCheckedItems_SAccum('businessEntityForm:businessEntityDataTable');return false;"></h:commandButton>
				</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					
				</tr>
	
		 	</table>
			<table width="96%"  align="center" cellpadding="0" cellspacing="0" >
			<tbody>
				<tr>
					<td>
						<table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#cccccc">
							<tr>
							<td > <h:selectBooleanCheckbox onclick="checkAllid(this,'businessEntityForm:businessEntityDataTable','businessEntityChkBox'); "></h:selectBooleanCheckbox></TD>
								<td width="94%" align="center"> <strong> <h:outputText value="Business Entity"> </h:outputText> </strong> </td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
					
					<div id="popupDataTableDiv" class=popupDataTableDiv>
							<h:dataTable cellspacing="1" width="100%" id="businessEntityDataTable"
							value="#{lgContractRefDataBean.businessEntity}" var="businessEntityValue"
							cellpadding="0" style="border:1px solid #cccccc;">
							<h:column>
								<f:verbatim> <h:selectBooleanCheckbox id="businessEntityChkBox"> </h:selectBooleanCheckbox></f:verbatim>
							</h:column>
							<h:column>
								<h:outputText value="#{businessEntityValue.code}"></h:outputText>
								<h:inputHidden value="#{businessEntityValue.code}@@"></h:inputHidden>
							</h:column>
							<h:column>
								<h:outputText value="#{businessEntityValue.description}"></h:outputText>
							</h:column>
						</h:dataTable>
						</div>
					</td>
				</tr>
			</TBODY>
			</table>
		</h:form>
	</BODY>
</f:view>

<script language="javascript">
tigra_tables('businessEntityForm:businessEntityDataTable',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
	window.opener = window.dialogArguments.parentWindow;
	var hiddenObj = window.opener.document.getElementById(window.dialogArguments.hiddenId);
	if(hiddenObj == null || hiddenObj == undefined) {
		alert("Hidden field not available");
	}
	if(hiddenObj.value) {
		matchCheckboxItems_accum('businessEntityForm:businessEntityDataTable',hiddenObj);
	}
</script>
</HTML>
