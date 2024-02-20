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
	width: 90%;
}
.longDescriptionColumn {
	width: 70%;
}
.selectOrOptionColumn1 {
	width: 15%;
	
}.shortDescriptionColumn1 {
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
<TITLE >Product Detailed Print</TITLE>
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
						value="#{productComponentAssociationBackingBean.hiddenInit}"></h:inputHidden>
					<h:inputHidden id="hidden2"
						value="#{productDenialAndExclusionBackingBean.hiddenList}"></h:inputHidden>
					<h:inputHidden id="hidden3"
						value="#{productAdminAssociationBackingBean.hiddenInit}"></h:inputHidden>
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
			<!-- General information block starts -->
			<TR id="productGeneralInformation">
				<TD>
				<TABLE border="0" cellspacing="0" cellpadding="0" class="outputText"
					width="95%">
					<TBODY>
						<TR>
							
							<td colspan="1" valign="top" class="ContentArea">
							
							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
							<div id="panel2Header" class="tabTitleBar"
								style="position:relative;width:100% ">General Information</div>
							<!--Start of Table for actual Data-->
							<!-- <table>
								 <tr>
									<td><h:outputText id="productName_txtLable"
										value="#{productGeneralInformationBackingBean.productName}" /></td>
								</tr>
							</table>-->
							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText">
								<TBODY>
									<TR>
										<TD colspan="4">
										<FIELDSET
											style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:350">
										<LEGEND> <FONT color="black">Business Domain</FONT></LEGEND><br>
										<TABLE border="0" cellspacing="0" cellpadding="0">
											<TR>
												<TD width="5"></TD>
												<TD width="220"><h:outputText id="lineOfBusiness"
													value="Line Of Business" styleClass="outputText" /></TD>
												<h:inputHidden id="lineOfBusinessHidden"
													value="#{productGeneralInformationBackingBean.lineOfBusinessDiv}"></h:inputHidden>
												<TD width="221"><h:outputText id="lineOfBusinessDiv"
													value="#{productGeneralInformationBackingBean.lineOfBusinessDiv}"
													styleClass="outputText" /></TD>
												<TD width="75"></TD>
											</TR>
                                            <TR><TD><br></TD></TR>
											<TR>
												<TD width="5"></TD>
												<TD width="220"><h:outputText id="businessEntity"
													value="Business Entity" styleClass="outputText" /></TD>
												<h:inputHidden id="businessEntityHidden"
													value="#{productGeneralInformationBackingBean.businessEntityDiv}"></h:inputHidden>
												<TD width="221"><h:outputText id="businessEntityDiv"
													value="#{productGeneralInformationBackingBean.businessEntityDiv}"
													styleClass="outputText" /></TD>
												<TD width="75"></TD>
											</TR>
                                            <TR><TD><br></TD></TR>
											<TR>
												<TD width="5"></TD>
												<TD width="200"><h:outputText id="businessGroup"
													value="Business Group" styleClass="outputText" /></TD>
												<h:inputHidden id="businessGroupHidden"
													value="#{productGeneralInformationBackingBean.businessGroupDiv}"></h:inputHidden>
												<TD width="220"><h:outputText id="businessGroupDiv"
													value="#{productGeneralInformationBackingBean.businessGroupDiv}"
													styleClass="outputText" /></TD>
												<TD width="75"></TD>
											</TR>
<!-- CARS START -->							
                                            <TR><TD><br></TD></TR>
											<TR>
												<TD width="5"></TD>
												<TD width="200"><h:outputText id="marketBusinessUnit"
													value="Market Business Unit" styleClass="outputText" /></TD>
												<h:inputHidden id="marketBusinessUnitHidden"
													value="#{productGeneralInformationBackingBean.marketBusinessUnitDiv}"></h:inputHidden>
												<TD width="220"><h:outputText id="marketBusinessUnitDiv"
													value="#{productGeneralInformationBackingBean.marketBusinessUnitDiv}"
													styleClass="outputText" /></TD>
												<TD width="75"></TD>
											</TR>
<!-- CARS END -->
                                        </TABLE>
										</FIELDSET>
										</TD>
									</TR>
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Name" /></TD>
										<TD width="184"><h:outputText id="productName_txt"
											value="#{productGeneralInformationBackingBean.productName}"
											styleClass="outputText" /> <h:inputHidden
											id="productNameHidden"
											value="#{productGeneralInformationBackingBean.productName}"></h:inputHidden>
										</TD>
										<TD width="24"><f:verbatim></f:verbatim></TD>
									</TR>
									
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText id="productFamily"
											value="Product Family" styleClass="outputText" /></TD>
										<TD width="184"><h:outputText id="productFamilyTxt"
											value="#{productGeneralInformationBackingBean.productFamily}"
											styleClass="outputText" /></TD>
										<TD width="24"></TD>
									</TR>
									<!--Adding product type starts-->
									<TR>
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Product Type" /></TD>
										<TD width="184"><h:outputText id="productTyp"
											value="#{productGeneralInformationBackingBean.productType}" /><h:inputHidden
											id="productTypeHidden"
											value="#{productGeneralInformationBackingBean.productType}"></h:inputHidden>
										</TD>
										<TD width="24"></TD>
									</TR>
									<TR id="sub1" style="display:none;">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Mandate Type" /></TD>
										<TD width="184"><h:inputTextarea
											styleClass="selectDivReadOnly" id="mandateType"
											value="#{productGeneralInformationBackingBean.mandateType}"
											readonly="true" style="border:0"></h:inputTextarea> <h:inputHidden
											id="mandateType1Hidden"
											value="#{productGeneralInformationBackingBean.mandateType}"></h:inputHidden>
										</TD>
										<TD width="24"></TD>
									</TR>
									<TR id="sub2" style="display:none;">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="State" /></TD>
										<TD width="184"><h:inputTextarea
											styleClass="selectDivReadOnly" id="stateCde"
											value="#{productGeneralInformationBackingBean.stateCode}"
											readonly="true" style="border:0"></h:inputTextarea> <h:inputHidden
											id="stateCdeHidden"
											value="#{productGeneralInformationBackingBean.stateCode}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('productGenInfoDetailedForm:stateCdeHidden','productGenInfoDetailedForm:stateCde',1); </SCRIPT>
										</TD>
										<TD width="24"></TD>
									</TR>
									<!--Adding product type ends-->
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Description" /></TD>
										<TD width="184"><h:outputText id="productDescription_txt"
											value="#{productGeneralInformationBackingBean.productDescription}"
											styleClass="formTxtAreaReadOnly" style="border:none;width:250px;"></h:outputText>
											<h:inputHidden id="productDescription_txtHidden"
											value="#{productGeneralInformationBackingBean.productDescription}"></h:inputHidden>
										</TD>
										<TD width="40"><f:verbatim></f:verbatim></TD>
									</TR>
                                    <TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Effective Date" /></TD>
										<TD width="184"><h:outputText id="effectiveDate_txt"
											value="#{productGeneralInformationBackingBean.effectiveDate}"
											styleClass="outputText" /> <h:inputHidden
											id="effectiveDate_txtHidden"
											value="#{productGeneralInformationBackingBean.effectiveDate}"></h:inputHidden>
										</TD>
										<TD valign="top" width="24"></TD>
									</TR>
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Expiry Date"
											styleClass="" /></TD>
										<TD width="184"><h:outputText id="expiryDate_txt"
											value="#{productGeneralInformationBackingBean.expiryDate}"
											styleClass="outputText" /> <h:inputHidden
											id="expiryDate_txtHidden"
											value="#{productGeneralInformationBackingBean.expiryDate}"></h:inputHidden>
										</TD>
										<TD valign="top" width="24"></TD>
									</TR>
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText id="productStructure"
											value="Product Structure" styleClass="outputText" /></TD>
										<TD width="184"><h:inputHidden id="productStructHidden"
											value="#{productGeneralInformationBackingBean.productStructDiv}"></h:inputHidden>
										<h:outputText id="productStructDiv"
											value="#{productGeneralInformationBackingBean.productStructDiv}"
											styleClass="formTxtAreaReadOnly" style="border:none;width:250px;" /></TD>

										<TD width="24"></TD>
									</TR>
									<TR valign="top" >
									<TD width="5"></TD>
									<TD width="150"><h:outputText id="productStructureVersion"
													value="Product Structure Version" styleClass="" /></TD>
									<TD width="253"><h:outputText id="productStructureVersionId"
													value="#{productGeneralInformationBackingBean.productStructureVersion}" />
									<TD width="21"></TD>
									</TR>
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Created By" /></TD>
										<TD width="184"><h:outputText id="createdBy_optxt"
											value="#{productGeneralInformationBackingBean.createdBy}"
											styleClass="outputText" /> <h:inputHidden
											id="createdBy_optxtHidden"
											value="#{productGeneralInformationBackingBean.createdBy}"></h:inputHidden>
										</TD>
										<TD width="24"><f:verbatim /></TD>
									</TR>
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Created Date" /></TD>
										<TD width="184"><h:outputText id="creationDate_optxt"
											value="#{productGeneralInformationBackingBean.creationDate}"
											styleClass="outputText">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
										<h:inputHidden id="creationDate_optxtHidden"
											value="#{productGeneralInformationBackingBean.creationDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:inputHidden> <h:inputHidden id="time"
											value="#{requestScope.timezone}">
										</h:inputHidden></TD>
										<TD width="24"><f:verbatim /></TD>
									</TR>
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Last Updated By" /></TD>
										<TD width="184"><h:outputText id="updatedBy_optxt"
											value="#{productGeneralInformationBackingBean.updatedBy}" />
										<h:inputHidden id="updatedBy_optxtHidden"
											value="#{productGeneralInformationBackingBean.updatedBy}"></h:inputHidden>
										</TD>
										<TD width="24"><f:verbatim /></TD>
									</TR>
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Last Updated Date" /></TD>
										<TD width="184"><h:outputText id="updationDate_optxt"
											value="#{productGeneralInformationBackingBean.updationDate}"
											styleClass="outputText">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
										<h:inputHidden id="updationDate_optxtHidden"
											value="#{productGeneralInformationBackingBean.updationDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:inputHidden></TD>
										<TD width="24"><f:verbatim /></TD>
									</TR>
									
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"></TD>
										<TD width="184"><f:verbatim /></TD>
										<TD width="24"><f:verbatim /></TD>
									</TR>
								</TBODY>
							</TABLE>
							</FIELDSET>
					
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
													value="#{productGeneralInformationBackingBean.state}" /> <h:inputHidden
													id="stateHidden"
													value="#{productGeneralInformationBackingBean.state}"></h:inputHidden>
												</td>
											</tr>
											<tr>
												<td><h:outputText value="Status" /></td>
												<TD><h:outputText value=":" /></TD>
												<td><h:outputText
													value="#{productGeneralInformationBackingBean.status}" />
												<h:inputHidden id="statusHidden"
													value="#{productGeneralInformationBackingBean.status}"></h:inputHidden>
												</td>
											</tr>
											<tr>
												<td><h:outputText value="Version" /></td>
												<TD><h:outputText value=":" /></TD>
												<td><h:outputText
													value="#{productGeneralInformationBackingBean.version}" />
												<h:inputHidden id="versionHidden"
													value="#{productGeneralInformationBackingBean.version}"></h:inputHidden>
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
					</TBODY>
				</TABLE>
				</TD>
			</TR>
			<!-- General information block ends -->
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
								<div id="searchResultdataTableDivCa"><h:dataTable columnClasses="selectOrOptionColumn,shortDescriptionColumn"
									cellspacing="1" id="bComponentDataTable"
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
						</TD>
					</TR>
				</TABLE>
				</TD>
			</TR>
			<!-- Comp Assoc block ends-->
			<!-- Admin Option block starts-->
			<TR id="productAdminOption">
				<td>
				<TABLE width="95%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td colspan="1" valign="top" class="ContentArea">
						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<div id="panel2Header" class="tabTitleBar"
							style="position:relative;width:100% ">Admin Option</div>
						<table class="smallfont" id="resultsTable" width="100%"
							cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td>
								<div id="resultHeaderDivAo">
								<TABLE id="headerTable" width="100%" border="0"
									bgcolor="#cccccc" cellpadding="2" cellspacing="1">
									<tr class="dataTableOddRow">
										<td width="10%"><strong><h:outputText value="Sequence"
											styleClass="outputText" /></strong></td>
										<td width="90%"><strong><h:outputText value="Admin Option"
											styleClass="outputText" /></strong></td>
									</tr>
								</TABLE>
								</div>
								<div id="InformationDivAdmin"></div>
								</td>
							</tr>
							<tr>
								<td bordercolor="#cccccc">
								<div id="searchResultdataTableDivAo"><h:dataTable columnClasses="selectOrOptionColumn,shortDescriptionColumn"
									cellspacing="1" id="adminDataTable"
									rendered="#{productAdminAssociationBackingBean.adminList != null}"
									value="#{productAdminAssociationBackingBean.adminList}"
									var="singleValue" cellpadding="3" width="100%">
									<h:column>
										
										<h:outputText id="seqid" value="#{singleValue.sequence}"
											styleClass="outputText"></h:outputText>
										<h:inputHidden id="seqidHidden"
											value="#{singleValue.sequence}"></h:inputHidden>
									</h:column>
									<h:column>
										
										<h:outputText id="adminName" value="#{singleValue.adminDesc}"
											styleClass="outputText"></h:outputText>
										<h:inputHidden id="adminNameHidden"
											value="#{singleValue.adminDesc}"></h:inputHidden>
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
						</TD>
					</TR>
				</TABLE>
				</TD>
			</TR>
			<!-- Admin Option block ends-->
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
								<div id="searchResultdataTableDivNo"><h:dataTable  columnClasses="shortDescriptionColumn"
									cellspacing="1" id="noteDataTable"
									rendered="#{productNoteAssociationBackingBean.noteListForPrint != null}"
									value="#{productNoteAssociationBackingBean.noteListForPrint}"
									var="singleValue" cellpadding="3" width="100%">
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
						</TD>
					</TR>
				</TABLE>
				</TD>
			</TR>
			<!-- Notes block ends-->
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
									cellspacing="1" id="denialDataTable" columnClasses="shortDescriptionColumn1,selectOrOptionColumn1,longDescriptionColumn1,selectOrOptionColumn1,selectOrOptionColumn1,selectOrOptionColumn"
								
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
						<br />
						</TD>
					</TR>
				</TABLE>
				</TD>
			</TR>
<!-- um rule -->

<TR id="productDenialExclusion">
				<td>
				<TABLE width="95%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td colspan="1" valign="top" class="ContentArea">
						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<div id="panel2Header" class="tabTitleBar"
							style="position:relative;width:100% ">UM Rules</div>
						<table class="smallfont" id="resultsTable" width="100%"
							cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td>
								<div id="resultHeaderDivDeUMRule">
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
								<div id="InformationDivDenialUMRule"></div>
								</td>
							</tr>
							<tr>
								<td bordercolor="#cccccc">
								<div id="searchResultdataTableDivDeUMRule"><h:dataTable
									cellspacing="1" id="denialDataTableUMRule" columnClasses="shortDescriptionColumn1,selectOrOptionColumn1,longDescriptionColumn1,selectOrOptionColumn1,selectOrOptionColumn1,selectOrOptionColumn"
									rendered="#{productDenialAndExclusionBackingBean.umRuleList != null}"
									value="#{productDenialAndExclusionBackingBean.umRuleList}"
									var="singleValue" cellpadding="3" width="100%">
									<h:column>
										
										<h:inputHidden id="ruleKey1UMRule"
											value="#{singleValue.productRuleSysID}" />
										<h:outputText id="genRuleIDUMRule" value="#{singleValue.genRuleID}" />
									</h:column>
									<h:column>
										
										<h:outputText id="ruleIDUMRule" value="#{singleValue.ruleID}" />
									</h:column>
									<h:column>
										
										<h:outputText id="ruleDescription1UMRule"
											value="#{singleValue.ruleDescription}" />
									</h:column>
									<h:column>
										
										<h:outputText id="pvaUMRule"
											value="#{singleValue.providerArrangement}" />
									</h:column>
									<h:column>
										
										<h:outputText id="groupIndicatorUMRule" value="#{singleValue.flag}" />
									</h:column>
									<h:column>
										
										<h:outputText id="ruleCommentUMRule"
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
						</TD>
					</TR>
				</TABLE>
				</TD>
			</TR>
<!-- Denial Rule -->
<TR id="productDenialExclusion">
				<td>
				<TABLE width="95%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td colspan="1" valign="top" class="ContentArea">
						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<div id="panel2Header" class="tabTitleBar"
							style="position:relative;width:100% ">Denial Rules</div>
						<table class="smallfont" id="resultsTable" width="100%"
							cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td>
								<div id="resultHeaderDivDeDenial">
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
								<div id="InformationDivDenialDenial"></div>
								</td>
							</tr>
							<tr>
								<td bordercolor="#cccccc">
								<div id="searchResultdataTableDivDeDenial"><h:dataTable 
									cellspacing="1" id="denialDataTableDenial" columnClasses="shortDescriptionColumn1,selectOrOptionColumn1,longDescriptionColumn1,selectOrOptionColumn1,selectOrOptionColumn1,selectOrOptionColumn"
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
						<br />
						</TD>
					</TR>
				</TABLE>
				</TD>
			</TR>

			<!-- Denial and Exclusion block ends-->
	<TR id="productDenialExclusion">
				<td>
				<TABLE width="95%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td colspan="1" valign="top" class="ContentArea">
						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<div id="panel2Header" class="tabTitleBar"
							style="position:relative;width:100% ">PNR Rules</div>
						<table class="smallfont" id="resultsTable" width="100%"
							cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td>
								<div id="resultHeaderDivDePnr">
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
								<div id="InformationDivDenialPnr"></div>
								</td>
							</tr>
							<tr>
								<td bordercolor="#cccccc">
								<div id="searchResultdataTableDivDePnr"><h:dataTable
									cellspacing="1" id="denialDataTablePnr" columnClasses="shortDescriptionColumn1,selectOrOptionColumn1,longDescriptionColumn1,selectOrOptionColumn1,selectOrOptionColumn1,selectOrOptionColumn"
									rendered="#{productDenialAndExclusionBackingBean.pnrlRuleList != null}"
									value="#{productDenialAndExclusionBackingBean.pnrlRuleList}"
									var="singleValue" cellpadding="3" width="100%">
									<h:column>
										
										<h:inputHidden id="ruleKey1Pnr"
											value="#{singleValue.productRuleSysID}" />
										<h:outputText id="genRuleIDPnr" value="#{singleValue.genRuleID}" />
									</h:column>
									<h:column>
									
										<h:outputText id="ruleIDPnr" value="#{singleValue.ruleID}" />
									</h:column>
									<h:column>
										
										<h:outputText id="ruleDescription1Pnr"
											value="#{singleValue.ruleDescription}" />
									</h:column>
									<h:column>
										
										<h:outputText id="pvaPnr"
											value="#{singleValue.providerArrangement}" />
									</h:column>
									<h:column>
										
										<h:outputText id="groupIndicatorPnr" value="#{singleValue.flag}" />
									</h:column>
									<h:column>
										
										<h:outputText id="ruleCommentPnr"
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
						</TD>
					</TR>
				</TABLE>
				</TD>
			</TR>
			
		</TABLE>
<!-- Denial Rule -->

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

	var printForGenInfo = document.getElementById("productGenInfoDetailedForm:printproductGeneralInformation").value;
	var printForCompAss = document.getElementById("productGenInfoDetailedForm:printproductComponentAssociation").value;
	var printForNotes = document.getElementById("productGenInfoDetailedForm:printproductNotes").value;
	var printForAdminOption = document.getElementById("productGenInfoDetailedForm:printAdminOption").value;
	var printForDenialExclusion = document.getElementById("productGenInfoDetailedForm:printDenialExclusion").value;

	
	
	if( null == printForGenInfo || "" == printForGenInfo ){
		var genInfoDivObj = document.getElementById('productGeneralInformation');
		genInfoDivObj.style.visibility = "hidden";
		genInfoDivObj.style.height = "0px";
		genInfoDivObj.innerText = null;
		
	} 
	if( null == printForCompAss || "" == printForCompAss ){
		var compAssDivObj = document.getElementById('productComponentAssociation');
		compAssDivObj.style.visibility = "hidden";
		compAssDivObj.style.height = "0px";
		compAssDivObj.innerText = null;
		
	} 
	if( null == printForNotes || "" == printForNotes ){
		var proNoteDivObj = document.getElementById('productNotes');
		proNoteDivObj.style.visibility = "hidden";
		proNoteDivObj.style.height = "0px";
		proNoteDivObj.innerText = null;
		
	} 
	if( null == printForAdminOption || "" == printForAdminOption ){
		var proAdminDivObj = document.getElementById('productAdminOption');
		proAdminDivObj.style.visibility = "hidden";
		proAdminDivObj.style.height = "0px";
		proAdminDivObj.innerText = null;
		
	}
	if( null == printForDenialExclusion || "" == printForDenialExclusion ){
		var proDenialDivObj = document.getElementById('productDenialExclusion');
		proDenialDivObj.style.visibility = "hidden";
		proDenialDivObj.style.height = "0px";
		proDenialDivObj.innerText = null;
		
	}
	initialize();
	function initialize(){
	if(null != document.getElementById('productGenInfoDetailedForm:bComponentDataTable')){
		if(document.getElementById('productGenInfoDetailedForm:bComponentDataTable').rows.length == 0){
				document.getElementById('resultHeaderDivCa').style.visibility = 'hidden';
				document.getElementById('searchResultdataTableDivCa').style.height = '1px';
				document.getElementById('searchResultdataTableDivCa').style.visibility = 'hidden';
				document.getElementById('InformationDiv').innerHTML = "No Benefit Components Associated";
			}
		}
	if(null != document.getElementById('productGenInfoDetailedForm:adminDataTable')){
		if(document.getElementById('productGenInfoDetailedForm:adminDataTable').rows.length == 0){
				document.getElementById('resultHeaderDivAo').style.visibility = 'hidden';
				document.getElementById('searchResultdataTableDivAo').style.height = '1px';
				document.getElementById('searchResultdataTableDivAo').style.visibility = 'hidden';
				document.getElementById('InformationDivAdmin').innerHTML = "No Admin Options Associated";
			}
		}
	if(null != document.getElementById('productGenInfoDetailedForm:noteDataTable')){
		if(document.getElementById('productGenInfoDetailedForm:noteDataTable').rows.length == 0){
				document.getElementById('resultHeaderDivNo').style.visibility = 'hidden';
				document.getElementById('searchResultdataTableDivNo').style.height = '1px';
				document.getElementById('searchResultdataTableDivNo').style.visibility = 'hidden';
				document.getElementById('InformationDivNotes').innerHTML = "No Notes Associated";
			}
		}
	if(null != document.getElementById('productGenInfoDetailedForm:denialDataTable')){
		if(document.getElementById('productGenInfoDetailedForm:denialDataTable').rows.length == 0){
				document.getElementById('resultHeaderDivDe').style.visibility = 'hidden';
				document.getElementById('searchResultdataTableDivDe').style.height = '1px';
				document.getElementById('searchResultdataTableDivDe').style.visibility = 'hidden';
				document.getElementById('InformationDivDenial').innerHTML = "No Exclusion rule Associated";
			}
		}
	if(null != document.getElementById('productGenInfoDetailedForm:denialDataTableUMRule')){
		if(document.getElementById('productGenInfoDetailedForm:denialDataTableUMRule').rows.length == 0){
				document.getElementById('resultHeaderDivDeUMRule').style.visibility = 'hidden';
				document.getElementById('searchResultdataTableDivDeUMRule').style.height = '1px';
				document.getElementById('searchResultdataTableDivDeUMRule').style.visibility = 'hidden';
				document.getElementById('InformationDivDenialUMRule').innerHTML = "No UM rule Associated";
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

	}
	if( "" != printForGenInfo){
		formatTildaToComma('productGenInfoDetailedForm:lineOfBusinessDiv');
		formatTildaToComma('productGenInfoDetailedForm:businessEntityDiv');
		formatTildaToComma('productGenInfoDetailedForm:businessGroupDiv');
		formatTildaToComma('productGenInfoDetailedForm:marketBusinessUnitDiv');
		//formatTildaToComma('productGenInfoDetailedForm:productFamilyDiv');
		//formatTildaToComma('productGenInfoDetailedForm:productStructDiv');
		copyHiddenToDiv_ewpd('productGenInfoDetailedForm:productStructHidden','productGenInfoDetailedForm:productStructDiv', 2, 1);
		copyHiddenToInputText('productGenInfoDetailedForm:productTypeHidden','productGenInfoDetailedForm:productTyp',1);
		copyHiddenToInputText('productGenInfoDetailedForm:mandateType1Hidden','productGenInfoDetailedForm:mandateType',1);

		var i;
		var obj;
		obj = document.getElementById("productGenInfoDetailedForm:productTypeHidden");
		i= obj.value;
		if(i== "MANDATE")
		{
			i=document.getElementById("productGenInfoDetailedForm:mandateType").innerHTML
			if(i == 'ST'){
			document.getElementById("productGenInfoDetailedForm:mandateType").value="State";
			sub2.style.display='';
			}else if( i == 'ET'){
			document.getElementById("productGenInfoDetailedForm:mandateType").value="ET";
			sub2.style.display='';
			}else if( i == 'FED'){
			document.getElementById("productGenInfoDetailedForm:mandateType").value="Federal";
			sub2.style.display='';
			}
			sub1.style.display='';
			
		}else{
			sub1.style.display='none';
			sub2.style.display='none';
		}

		// Script for Admin Option/Notes tab hide for mandate
		var j;
		var obj1;
		obj1 = document.getElementById("productGenInfoDetailedForm:productTypeHidden");
		j= obj1.value;
		if(j== "MANDATE")
		{
			productAdminOption.style.display='none';
			productNotes.style.display='none';	
		}else{
			productAdminOption.style.display='';
			productNotes.style.display='';
		}
	}
</script>
</HTML>

