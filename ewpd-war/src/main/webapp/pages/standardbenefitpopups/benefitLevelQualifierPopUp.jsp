<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">
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
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.selectOrOptionColumn {
	width: 10%;
	text-align: center;
	vertical-align: middle;
}
.shortDescriptionColumn {
	width: 20%;
}
.longDescriptionColumn {
	width: 70%;
}
</style>
<TITLE>Benefit Term Qualifier Popup</TITLE>
</HEAD>
<f:view>
	<BODY>
	<script language="JavaScript" src="../../js/tigra_tables.js"></script>

	<h:form id="benefitTermSelectPopupForm">
		<h:inputHidden value="#{benefitLevelBackingBean.retrieveTermQualifierRecords}"></h:inputHidden>
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
			<tr>
				<td align="left">&nbsp;<h:commandButton id="txtSelect" disabled="true" 
					styleClass="wpdbutton" value="Select"
					onclick="getCheckedItems_ewpd('benefitTermSelectPopupForm:benefitTermSelectPopupTable',2);return false;"></h:commandButton>
				</td>
			</tr>
		</table>


		<table width="96%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0"
					bgcolor="#cccccc">
					<tr height="20px">
						
							<TD align="center" valign="middle" width="10%"></TD>
							<TD width="20%"></TD>
							<TD align="left" width="70%" ><strong><h:outputText
								value="Benefit Term Qualifier "></h:outputText></strong></TD>
						
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td>
				<DIV id="popupDataTableDiv" class="popupDataTableDiv"
					style="width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;">
				<h:dataTable id="benefitTermSelectPopupTable" cellspacing="1"
					width="100%" var="termQualifier"
					value="#{benefitLevelBackingBean.termQualifiersList}"
					cellpadding="0" bgcolor="#cccccc" columnClasses="selectOrOptionColumn,shortDescriptionColumn,longDescriptionColumn">
					<h:column>
						
						<h:selectBooleanCheckbox id="qualifierCheckBox"
							value="#{termQualifier.termValueCheckBx}"></h:selectBooleanCheckbox>
					</h:column>
					<h:column>
						
                        
						<h:inputHidden id="hiddenTermQualifier"
							value="#{termQualifier.description}"></h:inputHidden>
						<h:inputHidden id="hiddenTermQualifierDesc"
							value="#{termQualifier.code}"></h:inputHidden>
                          <f:verbatim>&nbsp;</f:verbatim>
						<h:outputText id="termQualifierId" value="#{termQualifier.code}"></h:outputText>
					</h:column>
					<h:column>
						
                          <f:verbatim>&nbsp;</f:verbatim>
						<h:outputText id="termQualifier"
							value="#{termQualifier.description}"></h:outputText>
					</h:column>
				</h:dataTable></DIV>
				</td>
			</tr>
		</table>
	</h:form>
	</BODY>

</f:view>
<script language="javascript">
	
	document.getElementById('benefitTermSelectPopupForm:txtSelect').disabled  = false;

	tigra_tables('benefitTermSelectPopupForm:benefitTermSelectPopupTable',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
 	
	matchCheckboxItems_ewpd('benefitTermSelectPopupForm:benefitTermSelectPopupTable', window.dialogArguments.selectedValues, 2, 2, 2);
	// setColumnWidth('benefitTermSelectPopupForm:benefitTermSelectPopupTable', '5:250');
	

</script>
</HTML>


