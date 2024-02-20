<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/contract/ContractViewAllVersionsPrint.java" --%><%-- /jsf:pagecode --%>
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

<TITLE>Print For Contract All  DS </TITLE>
</HEAD>
<f:view>
	<BODY>
	<h:form  id="viewAllVersionForm">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			</br>
			<tr>
				<td><FIELDSET style="margin-left:8px;margin-right:-30px;padding-bottom:1px;padding-top:1px;width:98%">
                        <h:outputText id="breadcrumb" 
                              value="#{dateSegmentBackingBean.breadCrumbPrint}">
                        </h:outputText>
                   </FIELDSET>
				</td>
			</tr>
			<tr>
		<h:inputHidden id="selectedContractSysIdForPrint"
							value="#{dateSegmentBackingBean.selectedContractSysId}"/>
				<td colspan="2" valign="top" align="left" class="ContentArea"
					width="100%">
				<w:message></w:message>
				<div id="searchPanelHeader" class="tabTitleBar"><b>Available DateSegments</b></div>
				<br />
				<table align="left" cellpadding="0" cellspacing="0" width="100%"
					border="0">
					<tr>
						<td>
						<div id="resultHeaderDiv">
						<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
							id="resultHeaderTable" border="0" width="100%">
							<TBODY>
								<tr bgcolor="#ffffff">
									<td align="left" width="18%"><strong><h:outputText value="ContractId"></h:outputText></strong></td>
									<td align="left" width="13%"><strong><h:outputText value="Version"></h:outputText></strong></td>
									<td align="left" width="18%"><strong><h:outputText value="Effective Date"></h:outputText></strong></td>
									<td align="left" width="18%"><strong><h:outputText value="Expiry Date"></h:outputText></strong></td>
									<td align="left" width="18%"><strong><h:outputText value="Contract Type"></h:outputText></strong></td>
									<td align="left" width="15%"><strong><h:outputText value="Contract Status"></h:outputText></strong></td>
									<td align="left" width="15%"><strong><h:outputText value="DateSegment Type"></h:outputText></strong></td>
								</TR>
							</TBODY>
						</TABLE>
						</div>
						</td>
					</tr>
					<tr>
						<TD><!-- Search Result Data Table -->
						<div id="searchResultdataTableDiv"
							style=" overflow:auto; width:100%;"><h:dataTable
							headerClass="dataTableHeader" id="previousVersionsTable"
							var="singleValue" cellpadding="3" cellspacing="1"
							rendered="true"
							value="#{dateSegmentBackingBean.resultList}"
							border="0"
							width="100%">
							<h:column>
								<h:outputText id="ContractId"
									value="#{singleValue.contractId}"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText id="Version"
									value="#{singleValue.version}"></h:outputText>
								<h:inputHidden id="VersionIn"
								value="#{singleValue.version}"></h:inputHidden>
							</h:column>
							<h:column>
								<h:outputText id="StartDate"
									value="#{singleValue.startDate}"><f:convertDateTime pattern="MM/dd/yyyy" /></h:outputText>
								
							</h:column>
							<h:column>
								<h:outputText id="EndDate"
									value="#{singleValue.endDate}"> <f:convertDateTime pattern="MM/dd/yyyy" /></h:outputText>
							<h:inputHidden id="contractKey"
								value="#{singleValue.contractKey}"></h:inputHidden>
							<h:inputHidden id="contractIdHid"
								value="#{singleValue.contractId}"></h:inputHidden>	
							<h:inputHidden id="contractDateSegmentSysHId"	value="#{singleValue.dateSegmentId}"/>
							</h:column>
							<h:column>
								<h:outputText id="ContractType"
									value="#{singleValue.contractType}"></h:outputText>
								
							</h:column>
							<h:column>
								<h:outputText id="Status"
									value="#{singleValue.status}"></h:outputText>
								<h:inputHidden id="StatusIn"
								value="#{singleValue.status}"></h:inputHidden>					
							</h:column>
							<h:column>
								<h:outputText value="#{singleValue.description}"></h:outputText>
							</h:column>
						</h:dataTable></div>

						</TD>
					</tr>
					<TR>
					<TD>

						

					</TD>
					</TR>
				</table>

				</td>
			</tr>
			
		</table>
		
	</h:form>
	</BODY>
</f:view>
<SCRIPT>
	if(null != document.getElementById('viewAllVersionForm:previousVersionsTable')){
		setColumnWidth('resultHeaderTable','12%:8%:14%:14%:12%:25%:15%');
		setColumnWidth('viewAllVersionForm:previousVersionsTable','12%:8%:14%:14%:12%:25%:15%');
	}
window.print();
</SCRIPT>
</HTML>
