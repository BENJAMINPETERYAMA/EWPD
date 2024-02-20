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
	width: 90%;
}
</style>
<script language="JavaScript" src="../../js/wpd.js"> </script>
<script>window.print();</script>
</HEAD>
<TITLE >Print Product Notes</TITLE>
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
					<h:inputHidden id="hidden4"
						value="#{productNoteAssociationBackingBean.hiddenInit}"></h:inputHidden>

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
			<!-- Notes block starts-->
			<TR id="productNotes">
				<td>
				<TABLE width="95%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td colspan="1" valign="top" class="ContentArea">
						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<div id="panel2Header" class="tabTitleBar"
							style="position:relative;width:100% ">Associated Notes</div>
						<table class="smallfont" id="resultsTable" width="100%"
							cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td>
								<div id="resultHeaderDivNo">
								<TABLE id="headerTable" width="100%" border="0"
									bgcolor="#cccccc" cellpadding="2" cellspacing="1">
									<tr class="dataTableOddRow">
										<td width="90%"><strong><h:outputText value="Notes"
											styleClass="outputText" /></strong></td>
									</tr>
								</TABLE>
								</div>
								<div id="InformationDivNotes"></div>
								</td>
							</tr>
							<tr>
								<td bordercolor="#cccccc">
								<div id="searchResultdataTableDivNo"><h:dataTable
									cellspacing="1" id="noteDataTable"
									rendered="#{productNoteAssociationBackingBean.noteListForPrint != null}"
									value="#{productNoteAssociationBackingBean.noteListForPrint}"
									var="singleValue" cellpadding="3" width="100%" columnClasses="selectOrOptionColumn">
									<h:column>
										<h:outputText id="notName" value="#{singleValue.noteName}"
											styleClass="outputText"></h:outputText>
										<h:inputHidden id="notNameHidden"
											value="#{singleValue.noteName}"></h:inputHidden>
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
													value="#{productNoteAssociationBackingBean.state}" /> 
												</td>
											</tr>
											<tr>
												<td><h:outputText value="Status" /></td>
												<TD><h:outputText value=":" /></TD>
												<td><h:outputText
													value="#{productNoteAssociationBackingBean.status}" />
												</td>
											</tr>
											<tr>
												<td><h:outputText value="Version" /></td>
												<TD><h:outputText value=":" /></TD>
												<td><h:outputText
													value="#{productNoteAssociationBackingBean.version}" />
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
			<!-- Notes block ends-->
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

	if(null != document.getElementById('productGenInfoDetailedForm:noteDataTable')){
		if(document.getElementById('productGenInfoDetailedForm:noteDataTable').rows.length == 0){
				document.getElementById('resultHeaderDivNo').style.visibility = 'hidden';
				document.getElementById('searchResultdataTableDivNo').style.height = '1px';
				document.getElementById('searchResultdataTableDivNo').style.visibility = 'hidden';
				document.getElementById('InformationDivNotes').innerHTML = "No Notes Associated";
			}
		}	
	
</script>
</HTML>


