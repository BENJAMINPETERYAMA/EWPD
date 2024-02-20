<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<HTML>
<HEAD>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%>

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
<script language="JavaScript" src="../../js/tigra_tables.js"></script>
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.selectOrOptionColumn {
	width: 10%;
	text-align: center;
	vertical-align: middle;
}
.shortDescriptionColumn {
	width: 30%;
}
.longDescriptionColumn {
	width: 60%;
}
</style>
<!-- This tag gets the title name from the request -->
<title>Provider Arrangement Popup</title>
</HEAD>
<BASE target="_self" />
<f:view>
	<BODY>
	<hx:scriptCollector id="scriptCollector1">
		<h:form id="benefitTermSelectPopupForm">
			<h:inputHidden id="search"
				value="#{ReferenceDataBackingBeanCommon.refDataLookupRecords}"></h:inputHidden>

			<DIV id="fullInfo"></DIV>
			<TABLE border="0" cellpadding="5" cellspacing="0" width="100%">

				<TR align="center">

				</TR>

			</TABLE>

			<TABLE>
				<TBODY>
					<TR>
						<TD><w:messageForPopup></w:messageForPopup></TD>
					</TR>
				</TBODY>
			</TABLE>

			<TABLE border="0" cellpadding="5" cellspacing="0" width="100%">
				<TR>
					<TD align="left" height="19">&nbsp;<INPUT type="button"
						class="wpdbutton" name="action" value="Select"
						onclick="getSelected();return false;"></TD>
				</TR>

			</TABLE>

			<TABLE width="98%" border="0" align="right" cellpadding="0"
				cellspacing="0">

				<TR>
					<TD></TD>
				</TR>
				<TR id="tr1">
					<TD colspan="2" width="100%">
					<table id="businessEntityTable2" width="100%" cellpadding="0"
						cellspacing="0" bgcolor="#cccccc">
						<tr id="tr4">
							<td width="8%" align="center" valign="middle"></td>

							<td width="70%" align="center"><strong><h:outputText
								value="Provider Arrangement" styleClass="outputText" ></h:outputText></strong></td>
						</tr>

					</table>
					<DIV id="popupTableDiv1" style="overflow:auto;"
						class="popupDataTableDiv"><h:dataTable
						headerClass="dataTableHeader" id="searchResultTable1test"
						var="providerArrangement" cellpadding="2" width="100%"
						cellspacing="1" bgcolor="#cccccc"
						rendered="#{ReferenceDataBackingBeanCommon.renderFirstList}"
						value="#{ReferenceDataBackingBeanCommon.refDataLookUpList}"
						border="0" rowClasses="dataTableEvenRow,dataTableOddRow"
						columnClasses="selectOrOptionColumn,shortDescriptionColumn,longDescriptionColumn"  >
						<h:column>
							
							<wpd:singleRowSelect groupName="coverage" id="coverageId"
								rendered="true"></wpd:singleRowSelect>
						</h:column>
						<h:column>
							
							<h:inputHidden id="hiddenProviderArrgmnId"
								value="#{providerArrangement.primaryCode}" />
							<h:outputText id="providerArrgmnId"
								value="#{providerArrangement.primaryCode}"
								style="padding-left: 5px"></h:outputText>
						</h:column>
						<h:column>
							
							<h:inputHidden id="hiddenProviderArrgmntDesc"
								value="#{providerArrangement.description}" />
							<h:outputText id="providerArrgmntDesc"
								value="#{providerArrangement.description}"
								style="padding-left: 5px"></h:outputText>

						</h:column>

					</h:dataTable></DIV>

					</TD>
				</TR>
			</TABLE>
			<h:inputHidden id="hiddensortorder"
				value="#{ReferenceDataBackingBeanCommon.sortOrder}"></h:inputHidden>

		</h:form>
	</hx:scriptCollector>
	</BODY>
</f:view>
<script language="JavaScript">
matchCheckboxItems_ewpd('benefitTermSelectPopupForm:searchResultTable1test',window.dialogArguments.selectedValues,2,1,1);
var sort = document.getElementById('benefitTermSelectPopupForm:hiddensortorder');  
function getSelected(){
      if(sort != null && sort.value == 'code'){
            getCheckedItems_ewpd('benefitTermSelectPopupForm:searchResultTable1test',1);return false;
      }
}

</script>
</HTML>

