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
	
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->	
<style type="text/css">

.selectOrOptionColumn {
	width: 10%;
}.shortDescriptionColumn {
	width: 15%;
}
.longDescriptionColumn {
	width: 20%;
}
.longDescriptionColumn1 {
	width: 25%;
}
</style>

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
</HEAD>
<TITLE >Print Product Rules</TITLE>
<f:view>
	<BODY>
	<h:form styleClass="form" id="productGenInfoDetailedForm">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD width="5"></TD>
				<TD width="5"></TD>
				<TD width="1"></TD>
			</TR>
		</TABLE>
		<!--	Start of Table for actual Data	-->
		<table width="95%" border="0" cellspacing="0" cellpadding="0">
			<TR>
				<TD>
					&nbsp;<h:inputHidden id="temp" value="#{productGeneralInformationBackingBean.initViewForPrint}"></h:inputHidden>
					<h:inputHidden id="hidden1"
						value="#{productDenialAndExclusionBackingBean.hiddenList}"></h:inputHidden>
				</TD>
			</TR>
			<TR>
				<TD>
					<FIELDSET style="margin-left:16px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:93.5%">
						<h:outputText id="breadcrumb" 
							value="#{productGeneralInformationBackingBean.printBreadCrumbText}">
						</h:outputText>
					</FIELDSET>
				</TD>
			</TR>
			<TR>
				<TD>
					&nbsp;
				</TD>
			</TR>
			
			<!-- Denial and Exclusion block starts-->
			<TR id="productDenialExclusion">
				<td>
				<TABLE width="95%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td colspan="1" valign="top" class="ContentArea">
						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<div id="panel2Header" class="tabTitleBar"
							style="position:relative;width:100% ">Exclusion Rules</div>
						<table class="smallfont" id="resultsTable" width="100%"
							cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td>
								<div id="resultHeaderDivDe">
								<TABLE id="headerTable" width="100%" border="0"
									bgcolor="#cccccc" cellpadding="2" cellspacing="1">
									<tr class="dataTableOddRow">
										<td width="20%"><B><h:outputText value="EWPD Rule Id" /></B></TD>
										<td width="15%"><B><h:outputText value="Rule Id" /></B></TD>
										<td width="25%"><B><h:outputText value="Description" /></B></TD>
										<td width="15%"><B><h:outputText value="PVA" /></B></TD>
										<td width="15%"><B><h:outputText value="Group Indicator" /></B>
										</TD>
										<td width="10%"><B><h:outputText value="Value" /></B>
									</tr>
								</TABLE>
								</div>
								<div id="InformationDivDenial"></div>
								</td>
							</tr>
							<tr>
								<td bordercolor="#cccccc">
								<div id="searchResultdataTableDivDe"><h:dataTable
									cellspacing="1" id="denialDataTable" columnClasses="longDescriptionColumn,shortDescriptionColumn,longDescriptionColumn1,shortDescriptionColumn,shortDescriptionColumn,selectOrOptionColumn"
									rendered="#{productDenialAndExclusionBackingBean.exclusionRuleList != null}"
									value="#{productDenialAndExclusionBackingBean.exclusionRuleList}"
									var="singleValue" cellpadding="3" width="100%">
									<h:column>
										
										<h:inputHidden id="ruleKey1"
											value="#{singleValue.productRuleSysID}" />
										<h:outputText id="genRuleID" value="#{singleValue.genRuleID}" />
									</h:column>
									<h:column>
										
										<h:outputText id="ruleID" value="#{singleValue.ruleID}" />
									</h:column>
									<h:column>
										
										<h:outputText id="ruleDescription1"
											value="#{singleValue.ruleDescription}" />
									</h:column>
									<h:column>
										
										<h:outputText id="pva"
											value="#{singleValue.providerArrangement}" />
									</h:column>
									<h:column>
										
										<h:outputText id="groupIndicator" value="#{singleValue.flag}" />
									</h:column>
									<h:column>
										
										<h:outputText id="ruleComment"
											value="#{singleValue.ruleComment}" />
									</h:column>
								</h:dataTable></div>
								</td>
							</tr>
							<tr>
								<TD></TD>
							</tr>
							<tr>
								<TD></TD>
							</tr>
				
						</table>
						</fieldset>
<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<div id="panel2Header" class="tabTitleBar"
							style="position:relative;width:100% ">UM Rules</div>
						<table class="smallfont" id="resultsTableUmRule" width="100%"
							cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td>
								<div id="resultHeaderDivDeUmRule">
								<TABLE id="headerTableUmRule" width="100%" border="0"
									bgcolor="#cccccc" cellpadding="2" cellspacing="1">
									<tr class="dataTableOddRow">
										<td width="20%"><B><h:outputText value="EWPD Rule Id" /></B></TD>
										<td width="15%"><B><h:outputText value="Rule Id" /></B></TD>
										<td width="25%"><B><h:outputText value="Description" /></B></TD>
										<td width="15%"><B><h:outputText value="PVA" /></B></TD>
										<td width="15%"><B><h:outputText value="Group Indicator" /></B>
										</TD>
										<td width="10%"><B><h:outputText value="Value" /></B>
									</tr>
								</TABLE>
								</div>
								<div id="InformationDivDenialUmRule"></div>
								</td>
							</tr>
							<tr>
								<td bordercolor="#cccccc">
								<div id="searchResultdataTableDivDeUmRule"><h:dataTable
									cellspacing="1" id="denialDataTableUmRule" columnClasses="longDescriptionColumn,shortDescriptionColumn,longDescriptionColumn1,shortDescriptionColumn,shortDescriptionColumn,selectOrOptionColumn"
									rendered="#{productDenialAndExclusionBackingBean.umRuleList != null}"
									value="#{productDenialAndExclusionBackingBean.umRuleList}"
									var="singleValue" cellpadding="3" width="100%">
									<h:column>
									
										<h:inputHidden id="ruleKey1UmRule"
											value="#{singleValue.productRuleSysID}" />
										<h:outputText id="genRuleIDUmRule" value="#{singleValue.genRuleID}" />
									</h:column>
									<h:column>
										
										<h:outputText id="ruleIDUmRule" value="#{singleValue.ruleID}" />
									</h:column>
									<h:column>
										
										<h:outputText id="ruleDescription1UmRule"
											value="#{singleValue.ruleDescription}" />
									</h:column>
									<h:column>
										
										<h:outputText id="pvaUmRule"
											value="#{singleValue.providerArrangement}" />
									</h:column>
									<h:column>
										
										<h:outputText id="groupIndicatorUmRule" value="#{singleValue.flag}" />
									</h:column>
									<h:column>
										
										<h:outputText id="ruleCommentUmRule"
											value="#{singleValue.ruleComment}" />
									</h:column>
								</h:dataTable></div>
								</td>
							</tr>
							<tr>
								<TD></TD>
							</tr>
							<tr>
								<TD></TD>
							</tr>
				
						</table>
						</fieldset>
<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<div id="panel2Header" class="tabTitleBar"
							style="position:relative;width:100% ">Denial Rules</div>
						<table class="smallfont" id="resultsTableDenial" width="100%"
							cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td>
								<div id="resultHeaderDivDeDenial">
								<TABLE id="headerTableDenial" width="100%" border="0"
									bgcolor="#cccccc" cellpadding="2" cellspacing="1">
									<tr class="dataTableOddRow">
										<td width="20%"><B><h:outputText value="EWPD Rule Id" /></B></TD>
										<td width="15%"><B><h:outputText value="Rule Id" /></B></TD>
										<td width="25%"><B><h:outputText value="Description" /></B></TD>
										<td width="15%"><B><h:outputText value="PVA" /></B></TD>
										<td width="15%"><B><h:outputText value="Group Indicator" /></B>
										</TD>
										<td width="10%"><B><h:outputText value="Value" /></B>
									</tr>
								</TABLE>
								</div>
								<div id="InformationDivDenialDenial"></div>
								</td>
							</tr>
							<tr>
								<td bordercolor="#cccccc">
								<div id="searchResultdataTableDivDeDenial"><h:dataTable
									cellspacing="1" id="denialDataTableDenial" columnClasses="longDescriptionColumn,shortDescriptionColumn,longDescriptionColumn1,shortDescriptionColumn,shortDescriptionColumn,selectOrOptionColumn"
									rendered="#{productDenialAndExclusionBackingBean.denialRuleList != null}"
									value="#{productDenialAndExclusionBackingBean.denialRuleList}"
									var="singleValue" cellpadding="3" width="100%">
									<h:column>
										
										<h:inputHidden id="ruleKey1Denial"
											value="#{singleValue.productRuleSysID}" />
										<h:outputText id="genRuleIDDenial" value="#{singleValue.genRuleID}" />
									</h:column>
									<h:column>
									
										<h:outputText id="ruleIDDenial" value="#{singleValue.ruleID}" />
									</h:column>
									<h:column>
									
										<h:outputText id="ruleDescription1Denial"
											value="#{singleValue.ruleDescription}" />
									</h:column>
									<h:column>
									
										<h:outputText id="pvaDenial"
											value="#{singleValue.providerArrangement}" />
									</h:column>
									<h:column>
										
										<h:outputText id="groupIndicatorDenial" value="#{singleValue.flag}" />
									</h:column>
									<h:column>
										
										<h:outputText id="ruleCommentDenial"
											value="#{singleValue.ruleComment}" />
									</h:column>
								</h:dataTable></div>
								</td>
							</tr>
							<tr>
								<TD></TD>
							</tr>
							<tr>
								<TD></TD>
							</tr>
				
						</table>
						</fieldset>
<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<div id="panel2Header" class="tabTitleBar"
							style="position:relative;width:100% ">PNR Rules</div>
						<table class="smallfont" id="resultsTableDenialPnr" width="100%"
							cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td>
								<div id="resultHeaderDivDePnr">
								<TABLE id="headerTableDenialPnr" width="100%" border="0"
									bgcolor="#cccccc" cellpadding="2" cellspacing="1">
									<tr class="dataTableOddRow">
										<td width="20%"><B><h:outputText value="EWPD Rule Id" /></B></TD>
										<td width="15%"><B><h:outputText value="Rule Id" /></B></TD>
										<td width="25%"><B><h:outputText value="Description" /></B></TD>
										<td width="15%"><B><h:outputText value="PVA" /></B></TD>
										<td width="15%"><B><h:outputText value="Group Indicator" /></B>
										</TD>
										<td width="10%"><B><h:outputText value="Value" /></B>
									</tr>
								</TABLE>
								</div>
								<div id="InformationDivDenialPnr"></div>
								</td>
							</tr>
							<tr>
								<td bordercolor="#cccccc">
								<div id="searchResultdataTableDivDePnr"><h:dataTable
									cellspacing="1" id="denialDataTablePnr" columnClasses="longDescriptionColumn,shortDescriptionColumn,longDescriptionColumn1,shortDescriptionColumn,shortDescriptionColumn,selectOrOptionColumn"
									rendered="#{productDenialAndExclusionBackingBean.pnrlRuleList != null}"
									value="#{productDenialAndExclusionBackingBean.pnrlRuleList}"
									var="singleValue" cellpadding="3" width="100%">
									<h:column>
										
										<h:inputHidden id="ruleKey1DenialPnr"
											value="#{singleValue.productRuleSysID}" />
										<h:outputText id="genRuleIDDenialPnr" value="#{singleValue.genRuleID}" />
									</h:column>
									<h:column>
										
										<h:outputText id="ruleIDDenialPnr" value="#{singleValue.ruleID}" />
									</h:column>
									<h:column>
										
										<h:outputText id="ruleDescription1DenialPnr"
											value="#{singleValue.ruleDescription}" />
									</h:column>
									<h:column>
										
										<h:outputText id="pvaDenialPnr"
											value="#{singleValue.providerArrangement}" />
									</h:column>
									<h:column>
										
										<h:outputText id="groupIndicatorDenialPnr" value="#{singleValue.flag}" />
									</h:column>
									<h:column>
										
										<h:outputText id="ruleCommentDenialPnr"
											value="#{singleValue.ruleComment}" />
									</h:column>
								</h:dataTable></div>
								</td>
							</tr>
							<tr>
								<TD></TD>
							</tr>
							<tr>
								<TD></TD>
							</tr>
				
						</table>
						</fieldset>
						<br />
						<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText" width="100%">
								<TBODY>
									<tr>
										<td width="4%"></td>
										<td align="left"></td>
										<td align="left" width="127">
										<table Width=100%>
											<tr>
												<td><h:outputText value="State" /></td>
												<TD><h:outputText value=":" /></TD>
												<td><h:outputText
													value="#{productDenialAndExclusionBackingBean.state}" /> 
												</td>
											</tr>
											<tr>
												<td><h:outputText value="Status" /></td>
												<TD><h:outputText value=":" /></TD>
												<td><h:outputText
													value="#{productDenialAndExclusionBackingBean.status}" />
												</td>
											</tr>
											<tr>
												<td><h:outputText value="Version" /></td>
												<TD><h:outputText value=":" /></TD>
												<td><h:outputText
													value="#{productDenialAndExclusionBackingBean.version}" />
												</td>
											</tr>
										</table>
										</td>
									</tr>
								</TBODY>
							</TABLE>
							</FIELDSET>
							<BR>
						</TD>
					</TR>
				</TABLE>
				</TD>
			</TR>
			<!-- Denial and Exclusion block ends-->
		</TABLE>

		<h:inputHidden id="printproductGeneralInformation"
			value="#{productGeneralInformationBackingBean.printValue}"></h:inputHidden>
		<h:inputHidden id="printproductComponentAssociation"
			value="#{productComponentAssociationBackingBean.printValue}"></h:inputHidden>
		<h:inputHidden id="printproductNotes"
			value="#{productNoteAssociationBackingBean.printValue}"></h:inputHidden>
		<h:inputHidden id="printAdminOption"
			value="#{productAdminAssociationBackingBean.printValue}"></h:inputHidden>
		<h:inputHidden id="printDenialExclusion"
			value="#{productDenialAndExclusionBackingBean.printValue}"></h:inputHidden>
	</h:form>
	</BODY>
</f:view>

<script>	
	if(null != document.getElementById('productGenInfoDetailedForm:denialDataTable')){
		if(document.getElementById('productGenInfoDetailedForm:denialDataTable').rows.length == 0){
				document.getElementById('resultHeaderDivDe').style.visibility = 'hidden';
				document.getElementById('searchResultdataTableDivDe').style.height = '1px';
				document.getElementById('searchResultdataTableDivDe').style.visibility = 'hidden';
				document.getElementById('InformationDivDenial').innerHTML = "No Exclusion rule Associated";
			}
		}
if(null != document.getElementById('productGenInfoDetailedForm:denialDataTableUmRule')){
		if(document.getElementById('productGenInfoDetailedForm:denialDataTableUmRule').rows.length == 0){
				document.getElementById('resultHeaderDivDeUmRule').style.visibility = 'hidden';
				document.getElementById('searchResultdataTableDivDeUmRule').style.height = '1px';
				document.getElementById('searchResultdataTableDivDeUmRule').style.visibility = 'hidden';
				document.getElementById('InformationDivDenialUmRule').innerHTML = "No Um rule Associated";
			}
		}
if(null != document.getElementById('productGenInfoDetailedForm:denialDataTableDenial')){
		if(document.getElementById('productGenInfoDetailedForm:denialDataTableDenial').rows.length == 0){
				document.getElementById('resultHeaderDivDeDenial').style.visibility = 'hidden';
				document.getElementById('searchResultdataTableDivDeDenial').style.height = '1px';
				document.getElementById('searchResultdataTableDivDeDenial').style.visibility = 'hidden';
				document.getElementById('InformationDivDenialDenial').innerHTML = "No Denial rule Associated";
			}
		}
if(null != document.getElementById('productGenInfoDetailedForm:denialDataTablePnr')){
		if(document.getElementById('productGenInfoDetailedForm:denialDataTablePnr').rows.length == 0){
				document.getElementById('resultHeaderDivDePnr').style.visibility = 'hidden';
				document.getElementById('searchResultdataTableDivDePnr').style.height = '1px';
				document.getElementById('searchResultdataTableDivDePnr').style.visibility = 'hidden';
				document.getElementById('InformationDivDenialPnr').innerHTML = "No PNR rule Associated";
			}
		}
	
</script>
</HTML>

