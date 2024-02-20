<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/contractpopups/SearchLineOfBusiness.java" --%><%-- /jsf:pagecode --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/contractpopups/SearchLineOfBusiness.java" --%><%-- /jsf:pagecode --%>
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

<TITLE>Line Of Business Popup</TITLE>
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
	<h:form id="lineOfBusinessForm">
<h:inputHidden value="#{ReferenceDataBackingBeanCommon.refDataLookupRecords}"></h:inputHidden>
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
			<tr>
				<td align="left">&nbsp;<h:commandButton id="selectButton" value="Select"
					styleClass="wpdbutton"
					onclick="getCheckedItems_ewpd('lineOfBusinessForm:lineOfBusinessDataTable',2);return false;"></h:commandButton>
				</td>
			</tr>			
		</table>

		<table width="98%" align="right" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						bgcolor="#cccccc">
						<TR>
							<TD align="center" valign="middle" width="10%">
								<h:selectBooleanCheckbox onclick="checkAll_ewpd(this,'lineOfBusinessForm:lineOfBusinessDataTable'); ">
								</h:selectBooleanCheckbox>
							</TD>
							<TD align="left" width="90%">
								<strong><h:outputText value="Line Of Business"></h:outputText></strong>
							</TD>
						</TR>
					</table>
					</TD>
				</TR>
				<tr>
					<td>
					<DIV id="popupDataTableDiv" class=popupDataTableDiv>
					<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selectOrOptionColumn,shortDescriptionColumn"
						cellspacing="1" width="100%" id="lineOfBusinessDataTable"
						value="#{ReferenceDataBackingBeanCommon.refDataLookUpList}" var="eachRow"
						cellpadding="0" bgcolor="#cccccc">
						<h:column>
							
								<h:selectBooleanCheckbox id="lineOfBusinessChkBox">
								</h:selectBooleanCheckbox>
							
						</h:column>
						<h:column>
							
							<h:inputHidden value="#{eachRow.description}"></h:inputHidden>
							<h:inputHidden value="#{eachRow.primaryCode}"></h:inputHidden>
							<f:verbatim>&nbsp;</f:verbatim>
							<h:outputText value="#{eachRow.description}"></h:outputText>
						</h:column>
					</h:dataTable>
					</DIV>
					</td>
				</tr>
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
	if(hiddenObj.value) {

	matchCheckboxItems_ewpd('lineOfBusinessForm:lineOfBusinessDataTable', window.dialogArguments.selectedValues, 2, 2, 2);
	}
</script>
</HTML>

