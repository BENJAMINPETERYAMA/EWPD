<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/contractpopups/SearchPlancodePopUp.java" --%><%-- /jsf:pagecode --%>
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
}

.shortDescriptionColumn {
	width: 10%;
}

.longDescriptionColumn {
	width: 80%;
}
</style>

<TITLE>Corporate Plan Code Select</TITLE>
</HEAD>
<f:view>
	<BODY>
	<script language="JavaScript" src="../../js/tigra_tables.js"></script>

	<h:form id="corporatePlanSelectPopupForm">
		<table border="0" cellpadding="5" cellspacing="0" width="100%">

			<tr>
				<td align="left">&nbsp;<h:commandButton id="txtSelect"
					styleClass="wpdbutton" value="Select"
					onclick="getCheckedItems_ewpd('corporatePlanSelectPopupForm:corporatePlanFormTable',2);return false;"></h:commandButton>
				</td>
			</tr>
		</table>


		<table width="98%" border="0" align="right" cellpadding="0"
			cellspacing="0" bgcolor="#cccccc">
			<tr>
				<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0"
					bgcolor="#cccccc">
					<tr>
						<TD width="6%" align="left"><h:selectBooleanCheckbox
							onclick="checkAll_ewpd(this,'corporatePlanSelectPopupForm:corporatePlanFormTable'); ">
						</h:selectBooleanCheckbox></TD>
						<TD width="94%" align="center"><strong> <h:outputText
							value="Corporate Plan Code">
						</h:outputText> </strong></td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td><h:dataTable id="corporatePlanFormTable" cellspacing="1"
					width="100%" var="eachRow"
					value="#{ReferenceDataBackingBeanCommon.refDataLookUpList}"
					cellpadding="0" bgcolor="#cccccc" var="eachRow"
					columnClasses="selectOrOptionColumn,shortDescriptionColumn,longDescriptionColumn">
					<h:column>

						<f:verbatim>
							<h:selectBooleanCheckbox id="minor1" rendered="true"></h:selectBooleanCheckbox>
						</f:verbatim>
					</h:column>
					<h:column>

						<h:inputHidden id="hiddenCorporatePlanCode"
							value="#{eachRow.primaryCode}"></h:inputHidden>
						<h:inputHidden id="hiddenCorporatePlan"
							value="#{eachRow.description}"></h:inputHidden>
						<f:verbatim>&nbsp;</f:verbatim>
						<h:outputText value="#{eachRow.primaryCode}"></h:outputText>
					</h:column>
					<h:column>

						<f:verbatim>&nbsp;</f:verbatim>

						<h:outputText value="#{eachRow.description}"></h:outputText>
					</h:column>
				</h:dataTable></td>
			</tr>
		</table>
	</h:form>
	</BODY>

</f:view>
<script language="javascript">

	tigra_tables('corporatePlanSelectPopupForm:corporatePlanFormTable',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
 	
	matchCheckboxItems_ewpd('corporatePlanSelectPopupForm:corporatePlanFormTable', window.dialogArguments.selectedValues, 1, 1, 1);
	// setColumnWidth('qualifierSelectPopupForm:qualifierSelectPopupTable', '5:250');
	

</script>
</HTML>