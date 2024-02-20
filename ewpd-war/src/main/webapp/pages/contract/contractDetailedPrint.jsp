<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
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
<TITLE>Contract Print</TITLE>
</HEAD>
<f:view>
	<BODY>
	<hx:scriptCollector id="scriptCollector1">

		<h:form styleClass="form" id="contractPrintDetailedForm">

			<h:inputHidden id="hiddenProductType"
				value="#{contractBenefitGeneralInfoBackingBean.pageLoadBasedOnContractType}"></h:inputHidden>

			<h:inputHidden id="printRule"
				value="#{contractRuleBackingBean.mapForPrint}"></h:inputHidden>
			<TABLE width="100%" cellpadding="0" cellspacing="0">
				<TR>
					<TD width="5"></TD>
					<TD width="5"></TD>
					<TD width="1"></TD>
				</TR>
			</TABLE>
			<!--	Start of Table for actual Data	-->
			<TABLE width="95%" border="0" cellspacing="0" cellpadding="0">
				<!-- General information block starts -->
				<tr>
					<td>&nbsp;</td>
				</tr>
				<TR>
					<TD>
					<FIELDSET
						style="margin-left:15px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:93.5%">

					<h:outputText id="breadcrumb"
						value="#{contractBasicInfoBackingBean.breadCrumpText}">

					</h:outputText></FIELDSET>


					</TD>
				</TR>
				<TR id="TRContractBasicInformation">
					<TD>
					<DIV id="contractBasicInformation">
					<TABLE border="0" cellspacing="0" cellpadding="0"
						class="outputText" width="95%">
						<TBODY>
							<TR>
								<TD colspan="1" valign="top" class="ContentArea">
								<FIELDSET
									style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
								<h:inputHidden id="temp"
									value="#{contractBasicInfoBackingBean.initViewForPrint}"></h:inputHidden>
								<DIV id="panel2Header" class="tabTitleBar"
									style="position:relative;width:100% ">&nbsp;General Information</DIV>

								<!--Start of Table for actual Data	-->



								<TABLE border="0" cellspacing="0" cellpadding="3"
									class="outputText">
									<TBODY>
										<TR>
											<TD colspan="4">
											<FIELDSET
												style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:450">
											<LEGEND> <FONT color="black">Business Domain</FONT></LEGEND>
											<TABLE border="0" cellspacing="5" cellpadding="3">
												<TR>
													<TD width="258"><h:outputText id="lineOfBusiness"
														value="Line Of Business" styleClass="mandatoryNormal" />
													</TD>

													<TD width="332"><h:outputText id="lineOfBusinessDiv"
														value="#{contractBasicInfoBackingBean.lineOfBusinessDiv}"></h:outputText>

													</TD>
												</TR>
												<TR>
													<TD width="258"><h:outputText id="businessEntity"
														value="Business Entity" styleClass="mandatoryNormal"></h:outputText>
													</TD>

													<TD width="332"><h:outputText id="businessEntityDiv"
														value="#{contractBasicInfoBackingBean.businessEntityDiv}"></h:outputText>

													</TD>

												</TR>
												<TR>
													<TD width="258"><h:outputText id="businessGroup"
														value="Business Group" styleClass="mandatoryNormal" /></TD>

													<TD width="332"><h:outputText id="businessGroupDiv"
														value="#{contractBasicInfoBackingBean.businessGroupDiv}" />

													</TD>
												</TR>
<!-- ----------------------------------------------------------------------------------------------------------------- -->
												<TR>
													<TD width="258"><h:outputText id="marketBusinessUnit"
														value="Market Business Unit" styleClass="mandatoryNormal" /></TD>

													<TD width="332"><h:outputText id="marketBusinessUnitDiv"
														value="#{contractBasicInfoBackingBean.marketBusinessUnit}" />

													</TD>
												</TR>
<!-- ------------------------------------------------------------------------------------------------------------------ -->
											</TABLE>
											</FIELDSET>
											</TD>
										</TR>

										<TR>

											<TD width="210">&nbsp;&nbsp;<h:outputText id="contractId"
												value="Contract ID" styleClass="mandatoryNormal" /></TD>
											<h:inputHidden id="contractIdHidden"
												value="#{contractBasicInfoBackingBean.contractIdDiv}"></h:inputHidden>
											<TD width="185"><h:outputText id="contractId_txt"
												value="#{contractBasicInfoBackingBean.contractIdDiv}" /></TD>

										</TR>


										<TR>
											<TD height="25" width="210">&nbsp;&nbsp;<h:outputText
												id="contractType" value="Contract Type "
												styleClass="mandatoryNormal" /></TD>
											<TD height="25" width="185"><h:outputText
												id="contractType_txt"
												value="#{contractBasicInfoBackingBean.contractType}" /> <h:inputHidden
												id="contractTypeHidden"
												value="#{contractBasicInfoBackingBean.contractType}">
											</h:inputHidden></TD>
										</TR>
										<!-- Changes made for STANDARD Contract -->
										<TR id="baseContRowStandard" style="display:none;">

											<TD width="210">&nbsp;&nbsp;<h:outputText
												id="baseContractIdStandard" value="Base Contract Id"
												styleClass="mandatoryNormal" /></TD>

											<TD width="185"><h:outputText
												id="base_contractId_txt_Standard"
												value="#{contractBasicInfoBackingBean.baseContractIdDiv}" />
											<h:inputHidden id="baseContractIdStandardHidden"
												value="#{contractBasicInfoBackingBean.baseContractIdDiv}"></h:inputHidden>
											</TD>

										</TR>

										<TR id="baseContDtRowStandard" style="display:none;">

											<TD width="210">&nbsp;&nbsp;<h:outputText
												id="baseContractDtStandard"
												value="Base Contract Effective Date"
												styleClass="mandatoryNormal" /></TD>

											<TD width="185"><h:outputText
												id="base_contractDt_txt_Standard"
												value="#{contractBasicInfoBackingBean.baseContractDtDiv}" />
											<h:inputHidden id="baseContractDtStandardHidden"
												value="#{contractBasicInfoBackingBean.baseContractDtDiv}"></h:inputHidden>
											</TD>

										</TR>

										<!-- Changes made for STANDARD Contract -->
										<TR id="baseContRow" style="display:none;">

											<TD width="210">&nbsp;&nbsp;<h:outputText id="baseContractId"
												value="Base Contract Id" styleClass="mandatoryNormal" /></TD>

											<TD width="185"><h:outputText id="base_contractId_txt"
												value="#{contractBasicInfoBackingBean.baseContractIdDiv}" />
											<h:inputHidden id="baseContractIdHidden"
												value="#{contractBasicInfoBackingBean.baseContractIdDiv}"></h:inputHidden>
											</TD>

										</TR>

										<TR id="baseContDtRow" style="display:none;">

											<TD width="210">&nbsp;&nbsp;<h:outputText id="baseContractDt"
												value="Base Contract Effective Date"
												styleClass="mandatoryNormal" /></TD>

											<TD width="185"><h:outputText id="base_contractDt_txt"
												value="#{contractBasicInfoBackingBean.baseContractDtDiv}" />
											<h:inputHidden id="baseContractDtHidden"
												value="#{contractBasicInfoBackingBean.baseContractDtDiv}"></h:inputHidden>
											</TD>

										</TR>
										<TR id="headQuaterStateRow" style="display:none;">

											<TD width="210">&nbsp;&nbsp;<h:outputText id="head"
												value="Head Quarters State " /></TD>

											<TD width="185"><h:outputText id="head_txt"
												value="#{contractBasicInfoBackingBean.headQuartersState}" />
											<h:inputHidden id="headHidden"
												value="#{contractBasicInfoBackingBean.headQuartersState}"></h:inputHidden>
											</TD>

										</TR>
										<TR>

											<TD height="25" width="210">&nbsp;&nbsp;<h:outputText
												id="groupSizeTxt" value="Group Size "
												styleClass="mandatoryNormal" /></TD>
											<h:inputHidden id="groupSizeHidden"
												value="#{contractBasicInfoBackingBean.groupSizeDiv}"></h:inputHidden>
											<TD width="185"><h:outputText id="groupSize_txt" value="" />
											</TD>

										</TR>
										<tr>
												<TD height="25" width="210">&nbsp;&nbsp;<h:outputText value="Termed Contract ID"/></td>
												<td>
													<h:outputText value="#{contractBasicInfoBackingBean.termedContractId}"/>
												</td>
												<td></td>
											</tr>
										<tr>
												<td height="25" width="210">&nbsp;&nbsp;<h:outputText value="Archival Status"/></td>
												<td>
													<h:outputText value="#{contractBasicInfoBackingBean.contractStatus}"/>
													<h:inputHidden id="statusCodeHidden"
											value="#{contractBasicInfoBackingBean.contractStatus}"/>
												</td>
												<td></td>
											</tr>
											<tr id="contractStatusDateRow">
												<td height="25" width="210">&nbsp;&nbsp;<h:outputText value="Archival Status Date"/></td>
												<td><h:outputText value="#{contractBasicInfoBackingBean.contractStatusDate}"/> 
											</td>
												<td valign="top"></td>
											</tr>
											<tr id="contractStatusReasonRow">
												<td height="25" width="210">&nbsp;&nbsp;<h:outputText value="Reason Code"/></td>
												<TD width="185">
													<h:outputText value="#{contractBasicInfoBackingBean.contractStatusReasonCodeDesc}"/>
												</TD>
												<TD width="288"></TD>
											</tr>

										<TR valign="top">

											<TD width="210">&nbsp;&nbsp;<h:outputText
												value="Effective Date" styleClass="mandatoryNormal" /></TD>
											<TD width="185"><h:outputText id="effectiveDate_txt"
												value="#{contractBasicInfoBackingBean.startDate}" /> <h:inputHidden
												id="effectiveDateHidden"
												value="#{contractBasicInfoBackingBean.startDate}">
											</h:inputHidden></TD>
										</TR>
										<TR valign="top">

											<TD width="210">&nbsp;&nbsp;<h:outputText value="Expiry Date"
												styleClass="mandatoryNormal" /></TD>
											<TD width="185"><h:outputText id="expiryDate_txt"
												value="#{contractBasicInfoBackingBean.endDate}" /> <h:inputHidden
												id="expiryDateHidden"
												value="#{contractBasicInfoBackingBean.endDate}">
											</h:inputHidden></TD>
										</TR>
										<TR>
											<TD valign="top" width="210"><SPAN class="mandatoryNormal"
												id="createdBy">&nbsp;</SPAN> <h:outputText
												value="Created By" /></TD>
											<TD width="185"><h:outputText
												value="#{contractBasicInfoBackingBean.createdUser}" /> <h:inputHidden
												id="createdUserHidden"
												value="#{contractBasicInfoBackingBean.createdUser}">
											</h:inputHidden></TD>
											<TD width="288"></TD>
										</TR>
										<TR>
											<TD valign="top" width="210"><SPAN class="mandatoryNormal"
												id="creationDate"></SPAN> &nbsp;&nbsp;<h:outputText
												value="Created Date" /></TD>
											<TD width="185"><h:outputText
												value="#{contractBasicInfoBackingBean.creationDate}">
												<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
											</h:outputText> <h:outputText
												value="#{requestScope.timezone}"></h:outputText> <h:inputHidden
												id="creationDateHidden"
												value="#{contractBasicInfoBackingBean.creationDate}">
												<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
											</h:inputHidden> <h:inputHidden id="time"
												value="#{requestScope.timezone}">
											</h:inputHidden></TD>
											<TD width="288"></TD>

										</TR>
										<TR>
											<TD valign="top" width="210"><SPAN class="mandatoryNormal"
												id="createdBy"></SPAN>&nbsp;&nbsp;<h:outputText
												value="Last Updated By" /></TD>
											<TD width="185"><h:outputText
												value="#{contractBasicInfoBackingBean.updatedUser}" /> <h:inputHidden
												id="updatedUserHidden"
												value="#{contractBasicInfoBackingBean.updatedUser}">
											</h:inputHidden></TD>
											<TD width="288"></TD>
										</TR>
										<TR>
											<TD valign="top" width="210"><SPAN class="mandatoryNormal"
												id="updationDate"></SPAN> &nbsp;&nbsp;<h:outputText
												value="Last Updated Date" /></TD>
											<TD width="185"><h:outputText
												value="#{contractBasicInfoBackingBean.updationDate}">
												<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
											</h:outputText> <h:outputText
												value="#{requestScope.timezone}"></h:outputText> <h:inputHidden
												id="updationDateHidden"
												value="#{contractBasicInfoBackingBean.updationDate}">
												<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
											</h:inputHidden></TD>
											<TD width="288"></TD>

										</TR>


									</TBODY>
								</TABLE>
								</FIELDSET>
								<BR>
								</TD>
							</TR>
						</TBODY>
					</TABLE>
					</DIV>
					</TD>
				</TR>
				<!-- General information block ends -->
				<!-- Specific information block starts -->
				<TR id="TRContractSpecificInformation">
					<TD>
					<DIV id="contractSpecificInformation">

					<TABLE border="0" cellspacing="0" cellpadding="0"
						class="outputText" width="95%">
						<TBODY>
							<TR>
								<TD colspan="1" valign="top" class="ContentArea">
								<FIELDSET
									style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
								<DIV id="panel2Header" class="tabTitleBar"
									style="position:relative;width:100% ">Specific Information</DIV>
								<!--Start of Table for actual Data	--> <h:inputHidden id="temp1"
									value="#{contractSpecificInfoBackingBean.initViewForPrint}"></h:inputHidden>
								<TABLE border="0" cellspacing="0" cellpadding="3"
									class="outputText">

									<TR></TR>

									<TR>
										<TD valign="top" width="227"><h:outputText id="productCode"
											value="Product Code "
											styleClass="#{contractSpecificInfoBackingBean.requiredProductCode ? 'mandatoryError': 'mandatoryNormal'}">
										</h:outputText></TD>
										<TD width="70%"><h:outputText id="productCode_optxt" value="" />
										<h:inputHidden id="txtProductCode"
											value="#{contractSpecificInfoBackingBean.productCode}"></h:inputHidden>
										</TD>
									</TR>

										<TR>
											<TD valign="top" width="227">
												<h:outputText id="productCodeDesc" value="Product Code Description" >
												</h:outputText>
											</TD>
											<TD width="26%">
												<h:outputText id="productCodeDesc_optxtDesc" value="#{contractSpecificInfoBackingBean.productCodeDesc}" />
											
											</TD>		
										</TR>
									


									<TR>
										<TD valign="top" width="227"><h:outputText
											id="standardPlanCode" value="Standard Plan Code">
										</h:outputText></TD>
										<TD width="70%"><h:outputText id="standardPlanCode_optxt"
											value="" /> <h:inputHidden id="txtStdPlanCode"
											value="#{contractSpecificInfoBackingBean.standardPlanCode}"></h:inputHidden>
										</TD>
									</TR>

									<TR>
										<TD valign="top" width="227"><h:outputText id="benefitPlan"
											value="Benefit Plan "
											styleClass="#{contractSpecificInfoBackingBean.requiredBenefitPlan ? 'mandatoryError': 'mandatoryNormal'}">
										</h:outputText></TD>
										<TD width="70%"><h:outputText id="benefitPlan_optxt" value="" />
										<h:inputHidden id="txtBenefitPlan"
											value="#{contractSpecificInfoBackingBean.benefitPlan}" /></TD>


									</TR>
									<TR>
										<TD valign="top" width="227"><h:outputText id="product"
											value="Product "
											styleClass="#{contractSpecificInfoBackingBean.requiredProduct ? 'mandatoryError': 'mandatoryNormal'}">
										</h:outputText></TD>
										<TD width="70%"><h:outputText id="product_optxt" value="" />
										<h:inputHidden id="txtProduct"
											value="#{contractSpecificInfoBackingBean.product}"></h:inputHidden>
										</TD>
									</TR>


									<TR>
										<TD valign="top" width="227"><h:outputText id="productFamily"
											value="Product Family">
										</h:outputText></TD>
										<TD width="70%"><h:outputText id="txtProductFamily" value=""></h:outputText>
										<h:inputHidden id="txtProductFamilyHidden"
											value="#{contractSpecificInfoBackingBean.productFamily}"></h:inputHidden>
										</TD>
									</TR>
									<TR>
										<TD valign="top" width="227"><h:outputText
											id="corporatePlanCode" value="Corporate Plan Code">
										</h:outputText></TD>
										<TD width="70%"><h:outputText id="corporatePlanCode_optxt"
											value="" /> <h:inputHidden id="txtCorporatePlanCode"
											value="#{contractSpecificInfoBackingBean.corporatePlanCode}"></h:inputHidden>
										</TD>

									</TR>

									<TR>
										<TD valign="top" width="227"><h:outputText id="brandName"
											value="Brand Name">
										</h:outputText></TD>
										<TD width="70%"><h:outputText id="brandName_optxt" value="" />
										<h:inputHidden id="txtBrandName"
											value="#{contractSpecificInfoBackingBean.brandName}" /></TD>
									</TR>

									<TR>
										<TD valign="top" width="227"><h:outputText
											id="providerSpecCode" value="Related Provider Specialty Code">
										</h:outputText></TD>
										<TD width="70%"><div id="providerSpecCode_optxt" style="border:1px"/> <h:inputHidden id="txtProviderSpecCode"
											value="#{contractSpecificInfoBackingBean.providerSpecialtyCodeAndDesc}" /></TD>
									</TR>

									<TR>
										<TD valign="top" width="227"><h:outputText id="cobIndicator"
											value="COB Adjudication Indicator">
										</h:outputText></TD>
										<TD width="70%"><h:outputText id="cobIndicator_optxt" value="" />
										<h:inputHidden id="txtCobIndicator"
											value="#{contractSpecificInfoBackingBean.cobAdjudicationIndicator}" /></TD>
									</TR>
									<TR>
										<TD valign="top" width="227"><h:outputText id="medIndicator"
											value="Medicare Adjudication Indicator">
										</h:outputText></TD>
										<TD width="70%"><h:outputText id="medIndicator_optxt" value="" />
										<h:inputHidden id="txtMedIndicator"
											value="#{contractSpecificInfoBackingBean.medicareAdjudicationIndicator}" /></TD>
									</TR>
									<TR>
										<TD valign="top" width="227"><h:outputText id="itsIndicator"
											value="ITS Adjudication Indicator">
										</h:outputText></TD>
										<TD width="70%"><h:outputText id="itsIndicator_optxt" value="" />
										<h:inputHidden id="txtItsIndicator"
											value="#{contractSpecificInfoBackingBean.itsAdjudicationIndicator}" /></TD>
									</TR>


									<TR>
									</TR>

									<TR>
									</TR>

								</TABLE>
								</FIELDSET>
								<BR>
								</TD>
							</TR>
						</TBODY>
					</TABLE>
					</DIV>
					</TD>
				</TR>
				<!-- Specific information block ends -->
				<!-- membership information block stats -->
				<TR>
					<TD>
					<DIV id="contractMembershipInformation">
					<TABLE border="0" cellspacing="0" cellpadding="0"
						class="outputText" width="100%">
						<TBODY>
							<TR>
								<TD>
								<FIELDSET style="margin-left:15px;margin-right:-6px;width:94%"><BR />
								<!--	Start of Table for actual Data	--> <h:inputHidden
									id="maxresultMember"
									value="#{contractBasicInfoBackingBean.valueToMembership}"></h:inputHidden>
								<div id="panel2Member">
								<div id="resultInfoMember" class="dataTableColumnHeader"><br />
								<CENTER>No Membership Information is Available.</CENTER>
								</div>

								<div id="resultHeaderDivMember">
								<div id="resultHeaderTableInfo"
									class="dataTableColumnHeaderInfo" style="width:100%;height: 15">&nbsp;MemberShip
								Information</div>
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="0"
									id="resultHeaderTableMember" border="0" width="795px">
									<TBODY>

										<TR class="dataTableColumnHeader">
											<td align="left"><h:outputText
												styleClass="formOutputColumnField"
												value="Case Number & Name"></h:outputText></td>
											<td align="left"><h:outputText
												styleClass="formOutputColumnField"
												value="Case Effective- Cancel Date"></h:outputText></td>
											<td align="left"><h:outputText
												styleClass="formOutputColumnField" value="Case HQ State"></h:outputText>
											</td>
											<td align="left"><h:outputText
												styleClass="formOutputColumnField"
												value="Group Number & Name"></h:outputText></td>
											<td align="left"><h:outputText
												styleClass="formOutputColumnField"
												value="Group Effective- Cancel Date"></h:outputText></td>
											<td align="left"><h:outputText
												styleClass="formOutputColumnField"
												value="Funding Arrangement"></h:outputText></td>
											<td align="left"><h:outputText
												styleClass="formOutputColumnField" value="MBU Code & Value"></h:outputText>
											</td>
											<td align="left"><h:outputText
												styleClass="formOutputColumnField"
												value="Re-rate Code & Value"></h:outputText></td>
										</TR>
									</TBODY>
								</TABLE>
								</div>
								<%--
<h:inputHidden id="loadPricingInfoList" value= "#{contractPricingInfoBackingBean.loadPrisingInfoList}"/> 
		--%>
								<div id="panel2ContentMember"
									style="width:100%;position:relative;z-index:1;font-size:10px;">
								<table cellpadding="0" cellspacing="0" border="0">
									<tr>
										<td align="left"><h:dataTable headerClass="tableHeaderMember"
											id="resultsTableMember" border="0"
											value="#{contractBasicInfoBackingBean.membershipList}"
											rendered="#{contractBasicInfoBackingBean.membershipList != null}"
											var="eachRow" cellpadding="0" cellspacing="1">

											<h:column>
												<h:outputText id="caseNumber"
													styleClass="formOutputColumnField"
													value="#{eachRow.caseNumber}"></h:outputText>
												<h:outputText styleClass="formOutputColumnField" value=" : "></h:outputText>
												<h:outputText id="caseName"
													styleClass="formOutputColumnField"
													value="#{eachRow.caseName}"></h:outputText>
											</h:column>
											<h:column>
												<h:outputText id="CaseEffective"
													styleClass="formOutputColumnField"
													value="#{eachRow.caseEffectiveDate}">
													<f:convertDateTime pattern="MM/dd/yyyy" />
												</h:outputText>
												<h:outputText styleClass="formOutputColumnField" value=" - "></h:outputText>
												<h:outputText id="CaseExpiry"
													styleClass="formOutputColumnField"
													value="#{eachRow.caseExpiryDate}">
													<f:convertDateTime pattern="MM/dd/yyyy" />
												</h:outputText>
											</h:column>
											<h:column>
												<h:outputText id="caseHqState"
													styleClass="formOutputColumnField"
													value="#{eachRow.caseHqState}"></h:outputText>
											</h:column>
											<h:column>
												<h:outputText id="GroupNumber"
													styleClass="formOutputColumnField"
													value="#{eachRow.groupNumber}"></h:outputText>
												<h:outputText styleClass="formOutputColumnField" value=" : "></h:outputText>
												<h:outputText id="GroupName"
													styleClass="formOutputColumnField"
													value="#{eachRow.groupName}"></h:outputText>
											</h:column>
											<h:column>
												<h:outputText id="GroupEffective"
													styleClass="formOutputColumnField"
													value="#{eachRow.groupEffectiveDate}">
													<f:convertDateTime pattern="MM/dd/yyyy" />
												</h:outputText>
												<h:outputText styleClass="formOutputColumnField" value=" - "></h:outputText>
												<h:outputText id="GroupExpiry"
													styleClass="formOutputColumnField"
													value="#{eachRow.groupExpiryDate}">
													<f:convertDateTime pattern="MM/dd/yyyy" />
												</h:outputText>
											</h:column>
											<h:column>
												<h:outputText id="fundingCode"
													styleClass="formOutputColumnField"
													value="#{eachRow.fundingArrangementCode}"></h:outputText>
												<h:outputText styleClass="formOutputColumnField" value=" : "></h:outputText>
												<h:outputText id="fundingDesc"
													styleClass="formOutputColumnField"
													value="#{eachRow.fundingArrangementValue}"></h:outputText>
											</h:column>
											<h:column>
												<h:outputText id="mbuCode"
													styleClass="formOutputColumnField"
													value="#{eachRow.mbuCode}"></h:outputText>
												<h:outputText styleClass="formOutputColumnField" value=" : "></h:outputText>
												<h:outputText id="mbuDesc"
													styleClass="formOutputColumnField"
													value="#{eachRow.mbuValue}"></h:outputText>
											</h:column>
											<h:column>
												<h:outputText id="rateCode"
													styleClass="formOutputColumnField"
													value="#{eachRow.rerateCode}"></h:outputText>
												<h:outputText styleClass="formOutputColumnField" value=" : "></h:outputText>
												<h:outputText id="rateDesc"
													styleClass="formOutputColumnField"
													value="#{eachRow.rerateValue}"></h:outputText>
											</h:column>
										</h:dataTable></td>
									</tr>
								</table>

								</div>
								</div>

								<br>
								</FIELDSET>

								</TD>
							</TR>
						</TBODY>
					</TABLE>
					</DIV>
					</TD>
				</TR>
				<!-- Membership information block ends -->
				<!-- Adapted information block starts -->
				<TR id="TRContractAdaptedInformation">
					<TD>
					<DIV id="contractAdaptedInformation">
					<TABLE border="0" cellspacing="0" cellpadding="0"
						class="outputText" width="95%">
						<TBODY>
							<TR>
								<TD colspan="1" valign="top" class="ContentArea">
								<FIELDSET
									style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
								<DIV id="panel2Header" class="tabTitleBar"
									style="position:relative;width:100% ">Adapted Information</DIV>
								<!--Start of Table for actual Data	--> <h:inputHidden id="temp8"
									value="#{contractSpecificInfoBackingBean.initViewForPrintForAdapted}"></h:inputHidden>
								<TABLE border="0" cellspacing="0" cellpadding="3"
									class="outputText">

									<tr></tr>

									<TR>
										<TD valign="top" width="40%"><h:outputText
											id="regulatoryAgency" value="Regulatory Agency"></h:outputText>
										</TD>
										<TD width="48%"><h:outputText id="regulatoryAgency_optxt"
											value=""></h:outputText> <h:inputHidden
											id="txtRegulatoryAgency"
											value="#{contractSpecificInfoBackingBean.regulatoryAgency}"></h:inputHidden>
										</TD>

									</TR>

									<TR>
										<TD valign="top" width="40%"><h:outputText
											id="complianceStatus" value="Compliance Status"></h:outputText>
										</TD>
										<TD width="48%"><h:outputText id="complianceStatus_optxt"
											value=""></h:outputText> <h:inputHidden
											id="txtComplianceStatus"
											value="#{contractSpecificInfoBackingBean.complianceStatusDesc}"></h:inputHidden>
										</TD>
									</TR>

									<TR>
										<TD valign="top" width="40%"><h:outputText id="nameCode"
											value="Product/Project Name Code">
										</h:outputText></TD>
										<TD width="48%"><h:outputText id="nameCode_optxt" value=""></h:outputText>
										<h:inputHidden id="txtNameCode"
											value="#{contractSpecificInfoBackingBean.prodProjNameCode}"></h:inputHidden>
										</TD>

									</TR>

									<TR>
										<TD valign="top" width="40%"><h:outputText
											value="Contract Term Date"
											styleClass="#{contractBasicInfoBackingBean.startDateInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD width="48%"><h:outputText id="contractStartDate_optxt"
											value=""></h:outputText> <h:inputHidden
											id="txtContractStartDate"
											value="#{contractSpecificInfoBackingBean.contractTermDate}" />
										</TD>
									</TR>

									<TR>
										<TD width="40%"><h:outputText id="multiPlanIndicator"
											value="Multi Plan Indicator"></h:outputText></TD>
										<TD width="48%"><h:outputText id="multiPlan_optxt" value=""></h:outputText>
										<h:inputHidden id="txtMultiPlan"
											value="#{contractSpecificInfoBackingBean.multiplanIndicator}" />
										</TD>
									</TR>

									<TR>
										<TD width="40%"><h:outputText id="perfGuarantee"
											value="Performance Guarantee"></h:outputText></TD>
										<TD width="48%"><h:outputText id="perfGuarantee_optxt"
											value=""></h:outputText> <h:inputHidden id="txtPerfGuarantee"
											value="#{contractSpecificInfoBackingBean.performanceGuarantee}" />
										</TD>
									</TR>

									<TR>
										<TD valign="top" width="40%"><h:outputText
											value="Sales Market Date"
											styleClass="#{contractBasicInfoBackingBean.startDateInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD width="48%"><h:outputText id="salesMarketDate_optxt"
											value=""></h:outputText> <h:inputHidden
											id="txtsalesMarketDate"
											value="#{contractSpecificInfoBackingBean.salesMarketDate}" />
										</TD>
									</TR>



									<tr>
									</tr>

									<tr>
									</tr>

								</TABLE>
								</FIELDSET>
								<BR>
								</TD>
							</TR>
						</TBODY>
					</TABLE>
					</DIV>
					</TD>
				</TR>
				<!-- Adapted information block ends -->
				<!-- Pricing information block starts -->
				<TR id="TRContractPricingInformation">
					<TD>
					<DIV id="contractPricingInformation">
					<TABLE border="0" cellspacing="0" cellpadding="0" width="95%">
						<TR>
							<TD colspan="1" valign="top" class="ContentArea1">
							<FIELDSET style="margin-left:6px;margin-right:-6px;width:100%"><BR>
							<DIV id="panel2">
							<DIV id="resultInfo" class="dataTableColumnHeader"><BR>
							<CENTER>No Pricing Information is Available.</CENTER>
							</DIV>

							<DIV id="resultHeaderDivPricing">
							<DIV id="resultHeaderTableInfo" class="dataTableColumnHeaderInfo"
								style="width:100%;height: 15">&nbsp;Associated Pricing Records</DIV>
							<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="0"
								id="resultHeaderTable" border="0" width="795px">
								<TBODY>
									<TR class="dataTableColumnHeader">
										<TD width="33%">&nbsp;<h:outputText
											styleClass="formOutputColumnField" value="Coverage"></h:outputText>
										</TD>
										<TD width="33%">&nbsp;<h:outputText
											styleClass="formOutputColumnField" value="Pricing"></h:outputText>
										</TD>
										<TD width="34%">&nbsp;<h:outputText
											styleClass="formOutputColumnField" value="Network"></h:outputText>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
							</DIV>
							<h:inputHidden id="renderHidden"
								value="#{contractPricingInfoBackingBean.renderFlag}" /> <h:inputHidden
								id="temp3"
								value="#{contractPricingInfoBackingBean.initViewForPrint}" />
							<DIV id="panel2Content"
								style="width:100%;position:relative;z-index:1;font-size:10px;">
							<TABLE cellpadding="0" cellspacing="0" border="0">
								<TR>
									<TD align="left"><h:dataTable headerClass="tableHeader"
										id="resultsTable" border="0"
										value="#{contractPricingInfoBackingBean.pricingInformationList}"
										rendered="#{contractPricingInfoBackingBean.renderFlag}"
										var="eachRow" width="100%" cellpadding="0" cellspacing="1">

										<h:column>

											<h:inputHidden id="contractID"
												value="#{eachRow.contractDateSegmentSysId}"></h:inputHidden>
											<h:outputText id="coverageInfoDesc"
												styleClass="formOutputColumnField"
												value="#{eachRow.coverage}"></h:outputText>
											<h:inputHidden id="coverageInfoID"
												value="#{eachRow.coverage}"></h:inputHidden>
										</h:column>
										<h:column>

											<h:outputText id="pricingInfoDesc"
												styleClass="formOutputColumnField"
												value="#{eachRow.pricing}"></h:outputText>
											<h:inputHidden id="pricingInfoID" value="#{eachRow.pricing}"></h:inputHidden>
										</h:column>
										<h:column>

											<h:outputText id="networkInfoDesc"
												styleClass="formOutputColumnField"
												value="#{eachRow.network}"></h:outputText>
											<h:inputHidden id="networkInfoID" value="#{eachRow.network}"></h:inputHidden>
										</h:column>
									</h:dataTable></TD>
								</TR>
							</TABLE>

							</DIV>
							</DIV>
							<BR>
							</FIELDSET>
							<BR>
							</TD>
						</TR>
					</TABLE>
					</DIV>
					</TD>
				</TR>
				<!-- Pricing information block ends -->
				<!--Contarct Notes block starts -->
				<TR id="TRContractNotesInformation">
					<TD>
					<DIV id="contractNotesInformation">
					<TABLE width="95%" border="0" cellspacing="0" cellpadding="0">
						<TR>
							<TD colspan="1" valign="top" class="ContentArea1">
							<FIELDSET style="margin-left:6px;margin-right:-6px;width:100%">
							<DIV id="panel2">
							<DIV id="noContractNote"
								style="font-family:Verdana, Arial, Helvetica, sans-serif;
														font-size:11px;font-weight:bold;text-align:center;color:#000000; height:20px;
														background-color:#FFFFFF;">
							<CENTER>No Notes Attached.</CENTER>
							</DIV>
							<!--	Start of Table for actual Data	-->
							<TABLE border="0" cellspacing="0" cellpadding="0"
								class="outputText" width="100%">
								<TBODY>
									<TR>
										<TD><!-- Attach Notes Data Table --> <h:inputHidden id="temp4"
											value="#{ContractNotesBackingBean.initViewForPrint}" />
										<DIV id="resultHeaderDivNotes">
											<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
												id="headerTable" border="0" width="100%" height="21">
												<TR>
													<TD><B> Notes Attached </B></TD>
												</TR>
											</TABLE>
											<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
												id="resultHeaderTable11" border="0" width="100%" height="21">
												<TBODY>
													<TR>
														<TD align="center" width="20%"
															class="dataTableColumnHeader">Note ID</TD>
														<TD align="left" width="80%" class="dataTableColumnHeader">Note
														Name</TD>
													</TR>
												</TBODY>
											</TABLE>
											</DIV>
											</td>
											</tr>
											<tr>
											<td>
										<DIV id="attachNotesDataTableDiv" style=" width:100%;"><h:dataTable
											headerClass="dataTableHeader" id="attachNotesTable"
											var="singleValue" cellpadding="3" cellspacing="1"
											rendered="#{ContractNotesBackingBean.attachNotesList != null}"
											value="#{ContractNotesBackingBean.attachNotesList}"
											border="0" width="100%">
											
											<h:column>
												<h:outputText id="notesName"
													value="#{'Y' eq singleValue.legacyIndicator?'LEGACY':singleValue.noteId}"></h:outputText>
												<h:inputHidden id="hiddenNotesName"
													value="#{singleValue.noteId}"></h:inputHidden>
												<h:inputHidden id="hiddenNotesId"
													value="#{singleValue.noteId}"></h:inputHidden>
												<h:inputHidden id="hiddenNotesVersion"
													value="#{singleValue.version}"></h:inputHidden>

											</h:column>
											<h:column>
												<h:outputText id="noteName" value="#{singleValue.noteName}"></h:outputText>

											</h:column>

										</h:dataTable></DIV>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
							</DIV>
							</FIELDSET>
							<BR>
							</TD>
						</TR>
					</TABLE>
					</DIV>
					</TD>
				</TR>
				<!--Contarct Notes block ends -->
				<!--Contarct Comments block starts -->
				<TR>
					<TD>
					<DIV id="contractCommentsInformation">
					<TABLE width="95%" border="0" cellspacing="0" cellpadding="0">
						<TR>
							<TD colspan="1" valign="top" class="ContentArea">
							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:20px;width:100%">
							<h:inputHidden id="maxresult"
								value="#{contractCommentBackingBean.valueToMaxResult}"></h:inputHidden>
							<h:inputHidden id="hidden1"
								value="#{contractCommentBackingBean.loadComments}"></h:inputHidden>
							<!--	Start of Table for actual Data	-->
							<div id="resultHeaderDivComm"
								style="position:relative;font-size:10px;">
							<TABLE id="resultHeaderTableComm" width="100%" cellpadding="0"
								cellspacing="1" bgcolor="#cccccc">
								<TR>
									<TD height="15"><b>&nbsp;<h:outputText value="Comments History"></h:outputText></TD>
								</TR>
							</TABLE>
							<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
								id="resultHeaderTableComm" border="0" width="100%">
								<TBODY>

									<TR class="dataTableColumnHeader">
										<td align="left" width="25%"><h:outputText
											styleClass="formOutputColumnField" value="Date"></h:outputText>
										</td>
										<td align="left" width="20%"><h:outputText
											styleClass="formOutputColumnField" value="User"></h:outputText>
										</td>
										<td align="left" width="65%"><h:outputText
											styleClass="formOutputColumnField" value="Comments"></h:outputText>
										</td>

									</TR>
								</TBODY>
							</TABLE>
							</div>
							<DIV id="searchResultdataTableDiv" style="width:100%;"><h:dataTable
								headerClass="dataTableHeader" id="resultsTableComm"
								styleClass="outputText"
								value="#{contractCommentBackingBean.commentHistroyList}"
								rendered="#{contractCommentBackingBean.commentHistroyList != null}"
								var="eachRow" width="100%" cellpadding="3" cellspacing="1">

								<h:column>
									<h:inputHidden id="commentId" value="#{eachRow.commentSysId}"></h:inputHidden>
									<h:inputHidden id="dateSegmentId"
										value="#{eachRow.dateSegmentSysId}"></h:inputHidden>
									<h:outputText id="date" styleClass="formOutputColumnField"
										value="#{eachRow.createdTimeStamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText>
									<h:outputText id="space" value=" ">
									</h:outputText>
									<h:outputText id="timezone" styleClass="formOutputColumnField"
										value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="timeZonee" value="#{requestScope.timezone}">
									</h:inputHidden>
								</h:column>
								<h:column>
									<h:outputText id="hiddenUser"
										styleClass="formOutputColumnField"
										value="#{eachRow.createdUser}"></h:outputText>

								</h:column>
								<h:column>
									<h:outputText id="hiddencomments"
										styleClass="formOutputColumnField"
										value="#{eachRow.commentText}"></h:outputText>

								</h:column>
								<h:column>

									<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
								</h:column>
							</h:dataTable></div>


							</FIELDSET>
							<BR>
							</TD>
						</TR>
					</TABLE>
					</DIV>
					</TD>
				</TR>
				<!--Contarct Comments block ends -->
				<!--Contarct Admin Option block starts -->
				<TR>
					<TD>
					<DIV id="contractAdminOptionInformation"><h:inputHidden
						id="hiddenData"
						value="#{contractProductAdminOptionBackingBean.adminOptionHidden}"></h:inputHidden>
					<TABLE width="95%" border="0" cellspacing="0" cellpadding="0">
						<TR>
							<TD valign="top" class="ContentArea1">
							<fieldset
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

							<DIV id="resultInfoAdmin" class="dataTableColumnHeader">
							<CENTER>No Admin Options Attached.</CENTER>
							</DIV>

							<!--	Start of Table for actual Data	-->
							<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
								class="smallfont" border="0">
								<TBODY>
									<TR>
										<td colspan="4">
										<TABLE width="100%" cellspacing="0" cellpadding="0">
											<TR>
												<TD>
												<DIV id="resultHeaderDivAdmin">
												<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
													id="headerTable" border="0" width="100%">
													<TR>
														<TD><b> <h:outputText value="Associated Admin"></h:outputText>
														</b></TD>
													</TR>
												</TABLE>
												<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
													id="resultHeaderTableAdmin" border="0" width="100%">
													<TBODY>
														<TR class="dataTableColumnHeader">
															<TD align="left"><h:outputText value="Admin Name "></h:outputText>
															</TD>
														</TR>
													</TBODY>
												</TABLE>
												</DIV>
												</TD>
											</TR>

											<tr>
												<td>
												<DIV id="searchResultdataTableDiv"
													style="position:relative;z-index:1;font-size:10px;"><!-- Search Result Data Table -->
												<h:dataTable styleClass="outputText"
													headerClass="dataTableHeader" id="searchResultTable"
													var="singleValue" cellpadding="3" width="100%"
													cellspacing="1"
													rendered="#{contractProductAdminOptionBackingBean.adminList != null}"
													value="#{contractProductAdminOptionBackingBean.adminList}"
													border="0">
													<h:column>
														<h:inputHidden id="adminId"
															value="#{singleValue.adminKey}"></h:inputHidden>
														<h:outputText id="adminDesc"
															value="#{singleValue.adminDesc}"></h:outputText>
													</h:column>
												</h:dataTable></DIV>
												</td>
											</tr>
										</TABLE>


										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
								</TBODY>
							</TABLE>

							<!--	End of Page data	--></fieldset>
							<BR>
							</TD>
						</TR>
					</TABLE>
					</DIV>
					</TD>
				</TR>
				<!--Contarct Admin Option block ends -->
				<!--Contarct Rules block starts -->
				<TR>
					<TD>
					<DIV id="contractRulesInformation">
					<TABLE border="0" cellspacing="0" cellpadding="0"
						class="outputText" width="95%">
						<TBODY>
							<TR>
								<td valign="top" class="ContentArea1">
								<fieldset style="margin-left:6px;margin-right:-6px;width:100%">
								<div id="panel2Rules">
								<div id="resultInfoRule" class="dataTableColumnHeader">
								<CENTER>No associated Exclusion Rule.</CENTER>
								</div>

								<div id="resultHeaderDiv">
								<div id="resultHeaderTableInfo"
									class="dataTableColumnHeaderInfo" style="width:100%;height: 15">&nbsp;Exclusion
								Rules</div>
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="0"
									id="resultHeaderTableRule" border="0" width="100%">
									<TBODY>

										<TR class="dataTableColumnHeader">
											<td align="left" width="18%">&nbsp;<h:outputText
												styleClass="formOutputColumnField" value="EWPD Rule Id"></h:outputText>
											</td>
											<td align="left" width="13%">&nbsp;<h:outputText
												styleClass="formOutputColumnField" value="Rule Id"></h:outputText>
											</td>
											<td align="left" width="39%">&nbsp;<h:outputText
												styleClass="formOutputColumnField" value="Description"></h:outputText>
											</td>

											<td align="left" width="6%">&nbsp;<h:outputText
												styleClass="formOutputColumnField" value="PVA"></h:outputText>
											</td>
											<td align="left" width="10%">&nbsp;<h:outputText
												styleClass="formOutputColumnField" value="Group Indicator"></h:outputText>
											</td>
											<td align="left" width="14%">&nbsp;<h:outputText
												styleClass="formOutputColumnField" value="Value"></h:outputText>
											</td>
										</TR>
									</TBODY>
								</TABLE>
								</div>

								<div id="panel2ContentRules" style="width:100%">
								<table cellpadding="0" cellspacing="0" border="0" width="100%">
									<tr>
										<td align="left"><h:dataTable headerClass="tableHeader"
											id="resultsTableRule" border="0"
											value="#{contractRuleBackingBean.exclusionRuleList}"
											rendered="#{contractRuleBackingBean.exclusionRuleList!= null}"
											var="eachRow" cellpadding="0" cellspacing="1" width="100%">


											<h:column>

												<h:outputText id="generatedId"
													styleClass="formOutputColumnField"
													value="#{eachRow.generatedRuleId}"></h:outputText>

											</h:column>
											<h:column>

												<h:outputText id="coverageInfoDesc1"
													styleClass="formOutputColumnField"
													value="#{eachRow.ruleId}"></h:outputText>


											</h:column>
											<h:column>
												<h:outputText id="pricingInfoDesc1"
													styleClass="formOutputColumnField"
													value="#{eachRow.ruleShortDescription}"></h:outputText>

											</h:column>

											<h:column>
												<h:outputText id="PVA" styleClass="formOutputColumnField"
													value="#{eachRow.rulePVA}"></h:outputText>

											</h:column>
											<h:column>
												<h:outputText id="GroupIndicator"
													styleClass="formOutputColumnField"
													value="#{eachRow.ruleGroupIndicator}"></h:outputText>

											</h:column>
											<h:column>
												<h:outputText id="commentDesc"
													styleClass="formOutputColumnField"
													value="#{eachRow.ruleOverRideValue}"></h:outputText>

											</h:column>
										</h:dataTable></td>
									</tr>
								</table>

								</div>
								</div>

								</fieldset>

								<BR>
								</TD>
							</TR>
						</TBODY>
					</TABLE>
					</DIV>
					</TD>
				</TR>

				<TR>
					<TD>
					<DIV id="contractRulesInformationUM">
					<TABLE border="0" cellspacing="0" cellpadding="0"
						class="outputText" width="95%">
						<TBODY>
							<TR>
								<td valign="top" class="ContentArea1">
								<fieldset style="margin-left:6px;margin-right:-6px;width:100%">
								<div id="panel2Rules">
								<div id="resultInfoRuleUm" class="dataTableColumnHeader">
								<CENTER>No associated UM Rule.</CENTER>
								</div>

								<div id="resultHeaderDivUm">
								<div id="resultHeaderTableInfoUm"
									class="dataTableColumnHeaderInfo" style="width:100%;height: 15">&nbsp;UM
								Rules</div>
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="0"
									id="resultHeaderTableRuleUm" border="0" width="100%">
									<TBODY>

										<TR class="dataTableColumnHeader">
											<td align="left" width="18%">&nbsp;<h:outputText
												styleClass="formOutputColumnField" value="EWPD Rule Id"></h:outputText>
											</td>
											<td align="left" width="13%">&nbsp;<h:outputText
												styleClass="formOutputColumnField" value="Rule Id"></h:outputText>
											</td>
											<td align="left" width="39%">&nbsp;<h:outputText
												styleClass="formOutputColumnField" value="Description"></h:outputText>
											</td>

											<td align="left" width="6%">&nbsp;<h:outputText
												styleClass="formOutputColumnField" value="PVA"></h:outputText>
											</td>
											<td align="left" width="10%">&nbsp;<h:outputText
												styleClass="formOutputColumnField" value="Group Indicator"></h:outputText>
											</td>
											<td align="left" width="14%">&nbsp;<h:outputText
												styleClass="formOutputColumnField" value="Value"></h:outputText>
											</td>
										</TR>
									</TBODY>
								</TABLE>
								</div>

								<div id="panel2ContentRules" style="width:100%">
								<table cellpadding="0" cellspacing="0" border="0" width="100%">
									<tr>
										<td align="left"><h:dataTable headerClass="tableHeader"
											id="resultsTableRuleUm" border="0"
											value="#{contractRuleBackingBean.umRuleList}"
											rendered="#{contractRuleBackingBean.umRuleList!= null}"
											var="eachRow" cellpadding="0" cellspacing="1" width="100%">


											<h:column>

												<h:outputText id="generatedIdum"
													styleClass="formOutputColumnField"
													value="#{eachRow.generatedRuleId}"></h:outputText>

											</h:column>
											<h:column>

												<h:outputText id="coverageInfoDescum1"
													styleClass="formOutputColumnField"
													value="#{eachRow.ruleId}"></h:outputText>


											</h:column>
											<h:column>
												<h:outputText id="pricingInfoDescum1"
													styleClass="formOutputColumnField"
													value="#{eachRow.ruleShortDescription}"></h:outputText>

											</h:column>

											<h:column>
												<h:outputText id="PVAum" styleClass="formOutputColumnField"
													value="#{eachRow.rulePVA}"></h:outputText>

											</h:column>
											<h:column>
												<h:outputText id="GroupIndicatorum"
													styleClass="formOutputColumnField"
													value="#{eachRow.ruleGroupIndicator}"></h:outputText>

											</h:column>
											<h:column>
												<h:outputText id="commentDescum"
													styleClass="formOutputColumnField"
													value="#{eachRow.ruleOverRideValue}"></h:outputText>

											</h:column>
										</h:dataTable></td>
									</tr>
								</table>

								</div>
								</div>

								</fieldset>

								<BR>
								</TD>
							</TR>
						</TBODY>
					</TABLE>
					</DIV>
					</TD>
				</TR>
				<TR>
					<TD>
					<DIV id="contractRulesInformationDenial">
					<TABLE border="0" cellspacing="0" cellpadding="0"
						class="outputText" width="95%">
						<TBODY>
							<TR>
								<td valign="top" class="ContentArea1">
								<fieldset style="margin-left:6px;margin-right:-6px;width:100%">
								<div id="panel2Rules">
								<div id="resultInfoRuleDeniel" class="dataTableColumnHeader">
								<CENTER>No associated Denial Rule.</CENTER>
								</div>

								<div id="resultHeaderDivDenial">
								<div id="resultHeaderTableInfoDenial"
									class="dataTableColumnHeaderInfo" style="width:100%;height: 15">&nbsp;Denial
								Rules</div>
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="0"
									id="resultHeaderTableRuleDenial" border="0" width="100%">
									<TBODY>

										<TR class="dataTableColumnHeader">
											<td align="left" width="18%">&nbsp;<h:outputText
												styleClass="formOutputColumnField" value="EWPD Rule Id"></h:outputText>
											</td>
											<td align="left" width="13%">&nbsp;<h:outputText
												styleClass="formOutputColumnField" value="Rule Id"></h:outputText>
											</td>
											<td align="left" width="39%">&nbsp;<h:outputText
												styleClass="formOutputColumnField" value="Description"></h:outputText>
											</td>

											<td align="left" width="6%">&nbsp;<h:outputText
												styleClass="formOutputColumnField" value="PVA"></h:outputText>
											</td>
											<td align="left" width="10%">&nbsp;<h:outputText
												styleClass="formOutputColumnField" value="Group Indicator"></h:outputText>
											</td>
											<td align="left" width="14%">&nbsp;<h:outputText
												styleClass="formOutputColumnField" value="Value"></h:outputText>
											</td>
										</TR>
									</TBODY>
								</TABLE>
								</div>

								<div id="panel2ContentRules" style="width:100%">
								<table cellpadding="0" cellspacing="0" border="0" width="100%">
									<tr>
										<td align="left"><h:dataTable headerClass="tableHeader"
											id="resultsTableRuleDenial" border="0"
											value="#{contractRuleBackingBean.denialRuleList}"
											rendered="#{contractRuleBackingBean.denialRuleList!= null}"
											var="eachRow" cellpadding="0" cellspacing="1" width="100%">


											<h:column>

												<h:outputText id="generatedIddenial"
													styleClass="formOutputColumnField"
													value="#{eachRow.generatedRuleId}"></h:outputText>

											</h:column>
											<h:column>

												<h:outputText id="coverageInfoDescdenial1"
													styleClass="formOutputColumnField"
													value="#{eachRow.ruleId}"></h:outputText>


											</h:column>
											<h:column>
												<h:outputText id="pricingInfoDescdenial1"
													styleClass="formOutputColumnField"
													value="#{eachRow.ruleShortDescription}"></h:outputText>

											</h:column>

											<h:column>
												<h:outputText id="PVAdenial"
													styleClass="formOutputColumnField"
													value="#{eachRow.rulePVA}"></h:outputText>

											</h:column>
											<h:column>
												<h:outputText id="GroupIndicatordenial"
													styleClass="formOutputColumnField"
													value="#{eachRow.ruleGroupIndicator}"></h:outputText>

											</h:column>
											<h:column>
												<h:outputText id="commentDescdenial"
													styleClass="formOutputColumnField"
													value="#{eachRow.ruleOverRideValue}"></h:outputText>

											</h:column>
										</h:dataTable></td>
									</tr>
								</table>

								</div>
								</div>

								</fieldset>

								<BR>
								</TD>
							</TR>
						</TBODY>
					</TABLE>
					</DIV>
					</TD>
				</TR>

				<TR>
					<TD>
					<DIV id="contractRulesInformationPNR">
					<TABLE border="0" cellspacing="0" cellpadding="0"
						class="outputText" width="95%">
						<TBODY>
							<TR>
								<td valign="top" class="ContentArea1">
								<fieldset style="margin-left:6px;margin-right:-6px;width:100%">
								<div id="panel2Rules">
								<div id="resultInfoRulePNR" class="dataTableColumnHeader">
								<CENTER>No associated PNR Rule.</CENTER>
								</div>

								<div id="resultHeaderDivPNR">
								<div id="resultHeaderTableInfoPNR"
									class="dataTableColumnHeaderInfo" style="width:100%;height: 15">&nbsp;PNR
								Rules</div>
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="0"
									id="resultHeaderTableRulePNR" border="0" width="100%">
									<TBODY>

										<TR class="dataTableColumnHeader">
											<td align="left" width="18%">&nbsp;<h:outputText
												styleClass="formOutputColumnField" value="EWPD Rule Id"></h:outputText>
											</td>
											<td align="left" width="13%">&nbsp;<h:outputText
												styleClass="formOutputColumnField" value="Rule Id"></h:outputText>
											</td>
											<td align="left" width="39%">&nbsp;<h:outputText
												styleClass="formOutputColumnField" value="Description"></h:outputText>
											</td>

											<td align="left" width="6%">&nbsp;<h:outputText
												styleClass="formOutputColumnField" value="PVA"></h:outputText>
											</td>
											<td align="left" width="10%">&nbsp;<h:outputText
												styleClass="formOutputColumnField" value="Group Indicator"></h:outputText>
											</td>
											<td align="left" width="14%">&nbsp;<h:outputText
												styleClass="formOutputColumnField" value="Value"></h:outputText>
											</td>
										</TR>
									</TBODY>
								</TABLE>
								</div>

								<div id="panel2ContentRules" style="width:100%">
								<table cellpadding="0" cellspacing="0" border="0" width="100%">
									<tr>
										<td align="left"><h:dataTable headerClass="tableHeader"
											id="resultsTableRulepnr" border="0"
											value="#{contractRuleBackingBean.pnrRuleList}"
											rendered="#{contractRuleBackingBean.pnrRuleList!= null}"
											var="eachRow" cellpadding="0" cellspacing="1" width="100%">


											<h:column>

												<h:outputText id="generatedIdpnr"
													styleClass="formOutputColumnField"
													value="#{eachRow.generatedRuleId}"></h:outputText>

											</h:column>
											<h:column>

												<h:outputText id="coverageInfoDescpnr1"
													styleClass="formOutputColumnField"
													value="#{eachRow.ruleId}"></h:outputText>


											</h:column>
											<h:column>
												<h:outputText id="pricingInfoDescpnr1"
													styleClass="formOutputColumnField"
													value="#{eachRow.ruleShortDescription}"></h:outputText>

											</h:column>

											<h:column>
												<h:outputText id="PVApnr" styleClass="formOutputColumnField"
													value="#{eachRow.rulePVA}"></h:outputText>

											</h:column>
											<h:column>
												<h:outputText id="GroupIndicatorpnr"
													styleClass="formOutputColumnField"
													value="#{eachRow.ruleGroupIndicator}"></h:outputText>

											</h:column>
											<h:column>
												<h:outputText id="commentDescpnr"
													styleClass="formOutputColumnField"
													value="#{eachRow.ruleOverRideValue}"></h:outputText>

											</h:column>
										</h:dataTable></td>
									</tr>
								</table>

								</div>
								</div>

								</fieldset>

								<BR>
								</TD>
							</TR>
						</TBODY>
					</TABLE>
					</DIV>
					</TD>
				</TR>

				<!--Contarct Rules block ends -->
				<tr>
					<td>
					<DIV id="state">
					<FIELDSET
						style="margin-left:18px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:94%">
					<table border="0" cellspacing="0" cellpadding="3" width="100%">
						<tr>
							<td colspan="2"></td>
						</tr>
						<tr>
							<td width="10"></td>
							<td align="left" width="473"></td>
							<td align="left" width="127">
							<table Width=100%>
								<tr>
									<td><h:outputText value="State" /></td>
									<td>:&nbsp;<h:outputText
										value="#{contractBasicInfoBackingBean.state}" /></td>
									<h:inputHidden id="stateHidden"
										value="#{contractBasicInfoBackingBean.state}" />
								</tr>
								<tr>
									<td><h:outputText value="Status" /></td>
									<td>:&nbsp;<h:outputText
										value="#{contractBasicInfoBackingBean.status}" /></td>
									<h:inputHidden id="statusHidden"
										value="#{contractBasicInfoBackingBean.status}" />
								</tr>
								<tr>
									<td><h:outputText value="Version" /></td>
									<td>:&nbsp;<h:outputText
										value="#{contractBasicInfoBackingBean.version}" /></td>
									<h:inputHidden id="versionHidden"
										value="#{contractBasicInfoBackingBean.version}" />
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</FIELDSET>
					</DIV>
					</td>
				</tr>

			</TABLE>
			<h:inputHidden id="printRulesGeneralInformation"
				value="#{contractRuleBackingBean.printValue}"></h:inputHidden>
			<h:inputHidden id="printContractBasicInfo"
				value="#{contractBasicInfoBackingBean.printValue}"></h:inputHidden>
			<h:inputHidden id="printContractSpecificInfo"
				value="#{contractSpecificInfoBackingBean.printValue}"></h:inputHidden>
			<h:inputHidden id="printContractPricingInfo"
				value="#{contractPricingInfoBackingBean.printValue}"></h:inputHidden>
			<h:inputHidden id="printContractNotes"
				value="#{ContractNotesBackingBean.printValue}"></h:inputHidden>
			<h:inputHidden id="printContractComment"
				value="#{contractCommentBackingBean.printValue}"></h:inputHidden>
			<h:inputHidden id="printContractAdminOption"
				value="#{contractProductAdminOptionBackingBean.printValueAdmin}"></h:inputHidden>
			<h:inputHidden id="printContractMember"
				value="#{contractBasicInfoBackingBean.printValueMembership}"></h:inputHidden>
			<h:inputHidden id="printContractAdapted"
				value="#{contractSpecificInfoBackingBean.printValueAdapted}"></h:inputHidden>
		</h:form>
	</hx:scriptCollector>
	</BODY>
</f:view>

<script>

	var printContractBasicInfo = document.getElementById("contractPrintDetailedForm:printContractBasicInfo").value;
	var printContractSpecificInfo = document.getElementById("contractPrintDetailedForm:printContractSpecificInfo").value;
	var printContractPricingInfo = document.getElementById("contractPrintDetailedForm:printContractPricingInfo").value;
	var printContractNotes = document.getElementById("contractPrintDetailedForm:printContractNotes").value;
	var printContractComment = document.getElementById("contractPrintDetailedForm:printContractComment").value;
	var printForrulesGenInfo = document.getElementById("contractPrintDetailedForm:printRulesGeneralInformation").value;
	var printContractAdminOption = document.getElementById("contractPrintDetailedForm:printContractAdminOption").value;
	var printContractMember = document.getElementById("contractPrintDetailedForm:printContractMember").value;
	var printContractAdapted = document.getElementById("contractPrintDetailedForm:printContractAdapted").value;
	hideReasonCode();
	if( null == printContractBasicInfo || "" == printContractBasicInfo ){
		
		var genInfoDivObj = document.getElementById('contractBasicInformation');
		var TrObj1 = document.getElementById('TRContractBasicInformation');
		//alert("genInfoDivObj - "+genInfoDivObj);
		genInfoDivObj.style.visibility = "hidden";
		genInfoDivObj.style.height = "0px";
		genInfoDivObj.innerText = null;
		TrObj1.style.visibility = "hidden";
		TrObj1.style.height = "0px";
		TrObj1.innerText = null;
	} 
	else{
	 	formatTildaToComma('contractPrintDetailedForm:lineOfBusinessDiv');
		formatTildaToComma('contractPrintDetailedForm:businessEntityDiv');
		formatTildaToComma('contractPrintDetailedForm:businessGroupDiv'); 
		formatTildaToComma('contractPrintDetailedForm:marketBusinessUnitDiv'); 
 		copyHiddenToDiv_ewpd('contractPrintDetailedForm:groupSizeHidden','contractPrintDetailedForm:groupSize_txt',2,2);
		var selIndex = document.getElementById('contractPrintDetailedForm:contractTypeHidden');
		var baseContractIdStandard = document.getElementById('contractPrintDetailedForm:baseContractIdStandardHidden').value;
	    if(selIndex.value != 'STANDARD') {                           // unhide if the selected value is 'STANDARD'
            baseContRowStandard.style.display='none';
	   		baseContDtRowStandard.style.display='none';
	    }  else{  
	    		if(baseContractIdStandard != ''){
		    		baseContRowStandard.style.display='';
			    	baseContDtRowStandard.style.display='';
		    	}
	   } 	
	  if(selIndex.value != 'CUSTOM'){                           
	            baseContRow.style.display='none';
				baseContDtRow.style.display ='none';
		}
	      else {
	            baseContRow.style.display='';
				baseContDtRow.style.display='';
		}
	 if(selIndex.value != 'MANDATE'){  
            headQuaterStateRow.style.display='none';
	}
      else {
            headQuaterStateRow.style.display='';
	}
    
	}

	if( null == printContractSpecificInfo || "" == printContractSpecificInfo ){
		var genInfoDivObj = document.getElementById('contractSpecificInformation');
		var TRobj2 = document.getElementById('TRContractSpecificInformation');
		//alert("genInfoDivObj - "+genInfoDivObj);
		genInfoDivObj.style.visibility = "hidden";
		genInfoDivObj.style.height = "0px";
		genInfoDivObj.innerText = null;
		TRobj2.style.visibility = "hidden";
		TRobj2.style.height = "0px";
		TRobj2.innerText = null;
	} 
	else{
	 	copyHiddenToDiv_ewpd('contractPrintDetailedForm:txtProductCode','contractPrintDetailedForm:productCode_optxt',2,2); 
		copyHiddenToDiv_ewpd('contractPrintDetailedForm:txtStdPlanCode','contractPrintDetailedForm:standardPlanCode_optxt',2,2); 
		copyHiddenToDiv_ewpd('contractPrintDetailedForm:txtBenefitPlan','contractPrintDetailedForm:benefitPlan_optxt',2,1); 
		copyHiddenToDiv_ewpd('contractPrintDetailedForm:txtProduct','contractPrintDetailedForm:product_optxt',2,2); 
		copyHiddenToDiv_ewpd('contractPrintDetailedForm:txtProductFamilyHidden','contractPrintDetailedForm:txtProductFamily',2,1); 
		copyHiddenToDiv_ewpd('contractPrintDetailedForm:txtCorporatePlanCode','contractPrintDetailedForm:corporatePlanCode_optxt',2,2); 
		copyHiddenToDiv_ewpd('contractPrintDetailedForm:txtBrandName','contractPrintDetailedForm:brandName_optxt',2,1);
		copyHiddenToDiv_ewpd('contractPrintDetailedForm:txtCobIndicator','contractPrintDetailedForm:cobIndicator_optxt',2,1);
		copyHiddenToDiv_ewpd('contractPrintDetailedForm:txtMedIndicator','contractPrintDetailedForm:medIndicator_optxt',2,1);
		copyHiddenToDiv_ewpd('contractPrintDetailedForm:txtItsIndicator','contractPrintDetailedForm:itsIndicator_optxt',2,1);	
		copyHiddenToDivPrint_specialtyCode('contractPrintDetailedForm:txtProviderSpecCode','providerSpecCode_optxt',2,2,", ");
    
	}

	if( null == printContractAdapted || "" == printContractAdapted ){
		var genInfoDivObj = document.getElementById('contractAdaptedInformation');
		var TRobj3 = document.getElementById('TRContractAdaptedInformation');
		//alert("genInfoDivObj - "+genInfoDivObj);
		genInfoDivObj.style.visibility = "hidden";
		genInfoDivObj.style.height = "0px";
		genInfoDivObj.innerText = null;
		TRobj3.style.visibility = "hidden";
		TRobj3.style.height = "0px";
		TRobj3.innerText = null;
	} 
	else{
	copyHiddenToDiv_ewpd('contractPrintDetailedForm:txtRegulatoryAgency','contractPrintDetailedForm:regulatoryAgency_optxt',2,2); 
	copyHiddenToDiv_ewpd('contractPrintDetailedForm:txtComplianceStatus','contractPrintDetailedForm:complianceStatus_optxt',2,1); 
	copyHiddenToDiv_ewpd('contractPrintDetailedForm:txtNameCode','contractPrintDetailedForm:nameCode_optxt',2,2); 
	copyHiddenToDiv_ewpd('contractPrintDetailedForm:txtContractStartDate','contractPrintDetailedForm:contractStartDate_optxt',2,1); 
	copyHiddenToDiv_ewpd('contractPrintDetailedForm:txtMultiPlan','contractPrintDetailedForm:multiPlan_optxt',2,1); 
	copyHiddenToDiv_ewpd('contractPrintDetailedForm:txtPerfGuarantee','contractPrintDetailedForm:perfGuarantee_optxt',2,1); 
	copyHiddenToDiv_ewpd('contractPrintDetailedForm:txtsalesMarketDate','contractPrintDetailedForm:salesMarketDate_optxt',2,1); 
    
	}
	if( null == printContractPricingInfo || "" == printContractPricingInfo ){
		var genInfoDivObj = document.getElementById('contractPricingInformation');
		var TRobj4 = document.getElementById('TRContractPricingInformation');
		//alert("genInfoDivObj - "+genInfoDivObj);
		genInfoDivObj.style.visibility = "hidden";
		genInfoDivObj.style.height = "0px";
		genInfoDivObj.innerText = null;
		TRobj4.style.visibility = "hidden";
		TRobj4.style.height = "0px";
		TRobj4.innerText = null;
	}
	else{
		var divObj = document.getElementById('resultInfo');
		var divHeaderObj = document.getElementById('panel2Content');
		var divResultHeaderObj = document.getElementById('resultHeaderDivPricing');
		var tableObj = document.getElementById('contractPrintDetailedForm:resultsTable');
		if (tableObj != null) {
			divObj.style.visibility = 'hidden';
			divObj.style.height = "0px";
			divObj.style.position = 'absolute';
			var relTblWidth = document.getElementById('resultHeaderTable').width;
				document.getElementById('contractPrintDetailedForm:resultsTable').width = relTblWidth+"px";
				setColumnWidth('contractPrintDetailedForm:resultsTable','33%:33%:34%');
				setColumnWidth('resultHeaderTable','33%:33%:34%');
		}
		else{
			divHeaderObj.style.visibility = 'hidden';
			divResultHeaderObj.style.visibility = 'hidden';
			divHeaderObj.style.height = "0px";
			divResultHeaderObj.style.height = "0px";
			divHeaderObj.style.position = 'absolute';
			divResultHeaderObj.style.position = 'absolute';
		}
		
	}
	if( null == printContractNotes || "" == printContractNotes ){
		var genInfoDivObj = document.getElementById('contractNotesInformation');
		var Trobj5 = document.getElementById('TRContractNotesInformation');
		//alert("genInfoDivObj - "+genInfoDivObj);
		genInfoDivObj.style.visibility = "hidden";
		genInfoDivObj.style.height = "0px";
		genInfoDivObj.innerText = null;
		Trobj5.style.visibility = "hidden";
		Trobj5.style.height = "0px";
		Trobj5.innerText = null;
	}
	else{
		
		hiddenIdObj = document.getElementById('contractPrintDetailedForm:attachNotesTable:0:notesName');
		if(hiddenIdObj == null){
			document.getElementById('attachNotesDataTableDiv').style.visibility = 'hidden';
			document.getElementById('noContractNote').style.visibility = 'visible';
			document.getElementById('resultHeaderDivNotes').style.visibility = 'hidden';

		}else{
			document.getElementById('attachNotesDataTableDiv').style.visibility = 'visible';
			setColumnWidth('contractPrintDetailedForm:attachNotesTable', '20%:80%');
			setColumnWidth('resultHeaderTable11', '20%:80%');
			setColumnWidth('headerTable', '20%:80%');
			document.getElementById('noContractNote').style.visibility = 'hidden';

		}
		
	}
	if( null == printContractComment || "" == printContractComment ){
		var genInfoDivObj = document.getElementById('contractCommentsInformation');
		//alert("genInfoDivObj - "+genInfoDivObj);
		genInfoDivObj.style.visibility = "hidden";
		genInfoDivObj.style.height = "0px";
		genInfoDivObj.innerText = null;
	}
	else{
			if(document.getElementById('contractPrintDetailedForm:resultsTableComm') != null) {
				setColumnWidth('contractPrintDetailedForm:resultsTableComm','25%:20%:50%:5%');
				setColumnWidth('resultHeaderTableComm','25%:20%:50%:5%');
			}else{
				var divObj = document.getElementById('resultHeaderDivComm');
				divObj.style.visibility = 'hidden';
				divObj.style.height = "0px";
				divObj.style.position = 'absolute';
			}

		}

	
	if( null == printContractAdminOption || "" == printContractAdminOption ){
		var genInfoDivObj = document.getElementById('contractAdminOptionInformation');
		//alert("genInfoDivObj - "+genInfoDivObj);
		genInfoDivObj.style.visibility = "hidden";
		genInfoDivObj.style.height = "0px";
		genInfoDivObj.innerText = null;
	}
	else{
			hideIfNoValue('resultHeaderDivAdmin');
			function hideIfNoValue(divId){
				hiddenIdObj = document.getElementById('contractPrintDetailedForm:searchResultTable:0:adminId');
				if(hiddenIdObj == null){
					document.getElementById(divId).style.visibility = 'hidden';
				}else{
					document.getElementById('resultInfoAdmin').style.visibility = 'hidden';
					document.getElementById(divId).style.visibility = 'visible';
					setColumnWidth('contractPrintDetailedForm:searchResultTable', '40%:60%');
	 				setColumnWidth('resultHeaderTableAdmin', '40%:60%');
				}
			}	

		}

	if( null == printForrulesGenInfo || "" == printForrulesGenInfo ){
		var genInfoDivObj = document.getElementById('contractRulesInformation');
		var genInfoDivObjPNR = document.getElementById('contractRulesInformationPNR');
		var genInfoDivObjUM = document.getElementById('contractRulesInformationUM');
		var genInfoDivObjDenial = document.getElementById('contractRulesInformationDenial');

		genInfoDivObj.style.visibility = "hidden";
		genInfoDivObj.style.height = "0px";
		genInfoDivObj.innerText = null;

		genInfoDivObjPNR.style.visibility = "hidden";
		genInfoDivObjPNR.style.height = "0px";
		genInfoDivObjPNR.innerText = null;

		genInfoDivObjUM.style.visibility = "hidden";
		genInfoDivObjUM.style.height = "0px";
		genInfoDivObjUM.innerText = null;

		genInfoDivObjDenial.style.visibility = "hidden";
		genInfoDivObjDenial.style.height = "0px";
		genInfoDivObjDenial.innerText = null;
	} 
	else{
		if(document.getElementById('contractPrintDetailedForm:resultsTableRule') != null) {
			var tableObj = document.getElementById('contractPrintDetailedForm:resultsTableRule');
			if(tableObj.rows.length == 0){
				var divObj = document.getElementById('resultInfoRule');
				divObj.style.visibility = 'visible';
				divObj.style.height = "10px";
				
				var tableObjResult = document.getElementById('resultHeaderDiv');
				tableObjResult.style.visibility = 'hidden';
				tableObjResult.style.height = "0px";
				//tableObjResult.style.position = 'absolute';
			}
			else{
				var divObj = document.getElementById('resultInfoRule');
				divObj.style.visibility = 'hidden';
				divObj.style.height = "0px";
				
					
				setColumnWidth('contractPrintDetailedForm:resultsTableRule','18%:13%:39%:6%:10%:14%');
				setColumnWidth('resultHeaderTableRule','18%:13%:39%:6%:10%:14%');
				
			}
		}
		else{
			var divObj = document.getElementById('resultInfoRule');
			divObj.style.visibility = 'visible';
			divObj.style.height = "10px";

			var tableObjResult = document.getElementById('resultHeaderDiv');
				tableObjResult.style.visibility = 'hidden';
				tableObjResult.style.height = "0px";
			
		}

//UMRule

if(document.getElementById('contractPrintDetailedForm:resultsTableRuleUm') != null) {
			var tableObjPNR = document.getElementById('contractPrintDetailedForm:resultsTableRuleUm');
			if(tableObjPNR.rows.length == 0){
				var divObj = document.getElementById('resultInfoRuleUm');
				divObj.style.visibility = 'visible';
				divObj.style.height = "10px";
				
				var tableObjResult = document.getElementById('resultHeaderDivUm');
				tableObjResult.style.visibility = 'hidden';
				tableObjResult.style.height = "0px";
				//tableObjResult.style.position = 'absolute';
			}
			else{
				var divObj = document.getElementById('resultInfoRuleUm');
				divObj.style.visibility = 'hidden';
				divObj.style.height = "0px";
				divObj.style.position = 'absolute';
					
				setColumnWidth('contractPrintDetailedForm:resultsTableRuleUm','18%:13%:39%:6%:10%:14%');
				setColumnWidth('resultHeaderTableRuleUm','18%:13%:39%:6%:10%:14%');
				
			}
		}
		else{
			var divObj = document.getElementById('resultInfoRuleUm');
			divObj.style.visibility = 'visible';
			divObj.style.height = "10px";
			

				var tableObjResult = document.getElementById('resultHeaderDivUm');
				tableObjResult.style.visibility = 'hidden';
				tableObjResult.style.height = "0px";
			
		}



//Deniel

if(document.getElementById('contractPrintDetailedForm:resultsTableRuleDenial') != null) {
			var tableObjPNR = document.getElementById('contractPrintDetailedForm:resultsTableRuleDenial');
			if(tableObjPNR.rows.length == 0){
				var divObj = document.getElementById('resultInfoRuleDeniel');
				divObj.style.visibility = 'visible';
				divObj.style.height = "10px";
				
				var tableObjResult = document.getElementById('resultHeaderDivDenial');
				tableObjResult.style.visibility = 'hidden';
				tableObjResult.style.height = "0px";
				//tableObjResult.style.position = 'absolute';
			}
			else{
				var divObj = document.getElementById('resultInfoRuleDeniel');
				divObj.style.visibility = 'hidden';
				divObj.style.height = "0px";
				divObj.style.position = 'absolute';
					
				setColumnWidth('contractPrintDetailedForm:resultsTableRuleDenial','18%:13%:39%:6%:10%:14%');
				setColumnWidth('resultHeaderTableRuleDenial','18%:13%:39%:6%:10%:14%');
				
			}
		}
		else{
			var divObj = document.getElementById('resultInfoRuleDeniel');
			divObj.style.visibility = 'visible';
			divObj.style.height = "10px";
			

				var tableObjResult = document.getElementById('resultHeaderDivDenial');
				tableObjResult.style.visibility = 'hidden';
				tableObjResult.style.height = "0px";
			//	tableObjResult.style.position = 'absolute';
		}




//pNR



if(document.getElementById('contractPrintDetailedForm:resultsTableRulepnr') != null) {
			var tableObjPNR = document.getElementById('contractPrintDetailedForm:resultsTableRulepnr');
			if(tableObjPNR.rows.length == 0){
				var divObj = document.getElementById('resultInfoRulePNR');
				divObj.style.visibility = 'visible';
				divObj.style.height = "10px";
				
				var tableObjResult = document.getElementById('resultHeaderDivPNR');
				tableObjResult.style.visibility = 'hidden';
				tableObjResult.style.height = "0px";
				//tableObjResult.style.position = 'absolute';
			}
			else{
				var divObj = document.getElementById('resultInfoRulePNR');
				divObj.style.visibility = 'hidden';
				divObj.style.height = "0px";
				divObj.style.position = 'absolute';
					
				setColumnWidth('contractPrintDetailedForm:resultsTableRulepnr','18%:13%:39%:6%:10%:14%');
				setColumnWidth('resultHeaderTableRulePNR','18%:13%:39%:6%:10%:14%');
				
			}
		}
		else{
			var divObj = document.getElementById('resultInfoRulePNR');
			divObj.style.visibility = 'visible';
			divObj.style.height = "10px";

				var tableObjResult = document.getElementById('resultHeaderDivPNR');
				tableObjResult.style.visibility = 'hidden';
				tableObjResult.style.height = "0px";
				//tableObjResult.style.position = 'absolute';
		}




	}



	if( null == printContractMember || "" == printContractMember ){
		var genInfoDivObj = document.getElementById('contractMembershipInformation');
		genInfoDivObj.style.visibility = "hidden";
		genInfoDivObj.style.height = "0px";
		genInfoDivObj.innerText = null;
	} 
	else{
		
		var divObj = document.getElementById('resultInfoMember');
		var divHeaderObj = document.getElementById('panel2ContentMember');
		var divResultHeaderObj = document.getElementById('resultHeaderDivMember');

		var tableObj = document.getElementById('contractPrintDetailedForm:resultsTableMember');
		if (tableObj.rows.length > 0) {
			divObj.style.visibility = 'hidden';
			divObj.style.height = "0px";
			divObj.style.position = 'absolute';
		}
		else{
			divHeaderObj.style.visibility = 'hidden';
			divResultHeaderObj.style.visibility = 'hidden';
			divHeaderObj.style.height = "0px";
			divResultHeaderObj.style.height = "0px";
			divHeaderObj.style.position = 'absolute';
			divResultHeaderObj.style.position = 'absolute';
		}
	
	
	if(document.getElementById('contractPrintDetailedForm:resultsTableMember') != null) {
			var relTblWidthMember = document.getElementById('resultHeaderTableMember').width;
			document.getElementById('contractPrintDetailedForm:resultsTableMember').width = relTblWidthMember+"px";
			setColumnWidth('contractPrintDetailedForm:resultsTableMember','12%:12%:12%:12%:12%:12%:12%:12%');
			setColumnWidth('resultHeaderTableMember','12%:12%:12%:12%:12%:12%:12%:12%');
			
		}

	}
i = document.getElementById("contractPrintDetailedForm:hiddenProductType").value;
	if(i=='MANDATE')
	{
	var genInfoDivObj0 = document.getElementById('contractNotesInformation');
		//alert("genInfoDivObj - "+genInfoDivObj);
		genInfoDivObj0.style.visibility = "hidden";
		genInfoDivObj0.style.height = "0px";
		genInfoDivObj0.innerText = null;

		var genInfoDivObj1 = document.getElementById('contractAdminOptionInformation');
		//alert("genInfoDivObj - "+genInfoDivObj);
		genInfoDivObj1.style.visibility = "hidden";
		genInfoDivObj1.style.height = "0px";
		genInfoDivObj1.innerText = null;

	}
	
	function hideReasonCode()
{
  	if(document.getElementById('contractPrintDetailedForm:statusCodeHidden').value == 'ACTIVE' || document.getElementById('contractPrintDetailedForm:statusCodeHidden').value == '') {                           
            document.getElementById("contractStatusDateRow").style.display='none';
			 document.getElementById("contractStatusReasonRow").style.display='none';
    }  else{ 
			document.getElementById("contractStatusDateRow").style.display='block';
			 document.getElementById("contractStatusReasonRow").style.display='block';
    }
}

</script>
</HTML>
