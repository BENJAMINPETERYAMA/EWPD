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
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->	
<style type="text/css">

.selectOrOptionColumn {
	width: 10%;
}.shortDescriptionColumn {
	width: 90%;
}
</style>
</HEAD>
<TITLE >Print Product Component Association </TITLE>
<f:view>
	<BODY>
	<h:form styleClass="form" id="productGenInfoDetailedForm">
		<h:inputHidden id="hidden1"
			value="#{productComponentAssociationBackingBean.hiddenInit}"></h:inputHidden>
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
			<!-- ComponentAssociation block starts -->
			<TR id="productComponentAssociation">
				<td>
				<TABLE width="95%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td colspan="1" valign="top" class="ContentArea">
						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<div id="panel2Header" class="tabTitleBar"
							style="position:relative;width:100% ">Associated Benefit
						Components</div>
						<table class="smallfont" id="resultsTable" width="100%"
							cellpadding="0" cellspacing="0" border="0">
							<%-- tr bgcolor="#cccccc">
							<td colspan="3" class= "tabTitleBar" bgcolor="#cccccc"><span id="stateCodeStar"><strong>
							<h:outputText value="Associated Benefit Components"/></strong></span></td>
							</tr>
							<h:outputText value="No Major Heading Information is Available." rendered="#{productStructureBenefitComponentBackingBean.benefitComponentList == null}" styleClass="dataTableColumnHeader"/--%>
							<tr>
								<td>
								<div id="resultHeaderDivCa">
								<TABLE id="headerTable" width="100%" border="0"
									bgcolor="#cccccc" cellpadding="2" cellspacing="1">
									<tr class="dataTableOddRow">
										<td width="10%"><strong><h:outputText
											value="Sequence" styleClass="outputText" /></strong></td>

										<td width="90%"><strong><h:outputText
											value="Benefit Component" styleClass="outputText" /></strong></td>
									</tr>
								</TABLE>
								</div>
								<div id="InformationDiv"></div>
								</td>
							</tr>
							<tr>
								<td bordercolor="#cccccc">
								<div id="searchResultdataTableDivCa"><h:dataTable
									cellspacing="1" id="bComponentDataTable"
									columnClasses="selectOrOptionColumn,shortDescriptionColumn"
									rendered="#{productComponentAssociationBackingBean.benefitComponentList != null}"
									value="#{productComponentAssociationBackingBean.benefitComponentList}"
									var="singleValue" cellpadding="3" width="100%">
									<h:column>
									
										<h:outputText id="id" value="#{singleValue.sequence}"
											styleClass="outputText"></h:outputText>
									</h:column>
									<h:column>
									
										<h:outputText id="name" value="#{singleValue.componentDesc}"
											styleClass="outputText"></h:outputText>
										<h:inputHidden id="benefitComponentName"
											value="#{singleValue.componentDesc}"></h:inputHidden>
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
													value="#{productComponentAssociationBackingBean.stateOfObject}" /> 
												</td>
											</tr>
											<tr>
												<td><h:outputText value="Status" /></td>
												<TD><h:outputText value=":" /></TD>
												<td><h:outputText
													value="#{productComponentAssociationBackingBean.statusOfObject}" />
												</td>
											</tr>
											<tr>
												<td><h:outputText value="Version" /></td>
												<TD><h:outputText value=":" /></TD>
												<td><h:outputText
													value="#{productComponentAssociationBackingBean.versionOfObject}" />
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
			<!-- Comp Assoc block ends-->
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
if(null != document.getElementById('productGenInfoDetailedForm:bComponentDataTable')){
		if(document.getElementById('productGenInfoDetailedForm:bComponentDataTable').rows.length == 0){
				document.getElementById('resultHeaderDivCa').style.visibility = 'hidden';
				document.getElementById('searchResultdataTableDivCa').style.height = '1px';
				document.getElementById('searchResultdataTableDivCa').style.visibility = 'hidden';
				document.getElementById('InformationDiv').innerHTML = "No Benefit Components Associated";
			}
		}
	
</script>
</HTML>

