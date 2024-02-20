<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ taglib uri="/WEB-INF/RapidSpellWeb.tld" prefix="RapidSpellWeb"%>
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

<TITLE>Create Contract</TITLE>
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
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript">
	 var cal1 = new CalendarPopup();
	 cal1.showYearNavigation();

</script>
</HEAD>
<f:view>
	<BODY
		onkeypress="return submitOnEnterKey('migrationGeneralInfoForm:createButton');">
	<hx:scriptCollector id="scriptCollector1">
		<h:inputHidden id="hidden1" ></h:inputHidden>
		<h:inputHidden id="txtProductId"
			value="#{migrationGeneralInfoBackingBean.modelContractURL}"></h:inputHidden>

		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><jsp:include page="../navigation/top_migration.jsp"></jsp:include>
				</TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="migrationGeneralInfoForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD width="273" valign="top" class="leftPanel">


							<DIV class="treeDiv"><!-- Space for Tree  Data	--> <jsp:include
								page="../migration/migrationWizardTree.jsp">
							</jsp:include></DIV>

							</TD>

							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message
											value="#{migrationGeneralInfoBackingBean.validationMessages}"></w:message>
										</TD>
									</TR>
								</TBODY>
							</TABLE>

							<!-- Table containing Tabs -->
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<tr>
									<td width="100%"><b>Step3 : General Information </b></td>
								</tr>
								<tr>
									<td>This Screen will have all the fields in the Basic info,
									Specific info and Adapted info currently present in the
									Contract for ET Auto bagging system.</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>

							</TABLE>
							<!-- End of Tab table --> <!--							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
--> <!--	Start of Table for actual Data	-->
							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText">
								<TBODY>

									<TR>

										<TD colspan="4">
										<FIELDSET
											style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:450">
										<LEGEND> <FONT color="black">Business Domain</FONT></LEGEND>
										<TABLE border="0" cellspacing="1" cellpadding="3">
											<TR>
												<TD width="172"><h:outputText id="lineOfBusiness"
													value="Line Of Business*"
													styleClass="#{migrationGeneralInfoBackingBean.lobInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
												</TD>
												<h:inputHidden id="lineOfBusinessHiddenOld"
													value="#{migrationGeneralInfoBackingBean.lineOfBusinessDiv}" />
												<h:inputHidden id="lineOfBusinessHidden"
													value="#{migrationGeneralInfoBackingBean.lineOfBusinessDiv}"></h:inputHidden>
												<TD>
												<div id="lineOfBusinessDiv" class="selectDataDisplayDiv"></div>

												</TD>
												<TD><h:commandButton alt="Select" id="lineOfBusinessButton"
													image="../../images/select.gif" tabindex="1"
													onclick="modalWindow('../popups/lineOfBusinessPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Line of Business','lineOfBusinessDiv','migrationGeneralInfoForm:lineOfBusinessHidden',2,2);return false;"
													rendered="#{migrationGeneralInfoBackingBean.disableField}">
												</h:commandButton></TD>
											</TR>
											<TR>
												<TD><h:outputText id="businessEntity"
													value="Business Entity*"
													styleClass="#{migrationGeneralInfoBackingBean.entityInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
												</TD>
												<h:inputHidden id="businessEntityHiddenOld"
													value="#{migrationGeneralInfoBackingBean.businessEntityDiv}" />
												<h:inputHidden id="businessEntityHidden"
													value="#{migrationGeneralInfoBackingBean.businessEntityDiv}"></h:inputHidden>
												<TD>
												<div id="businessEntityDiv" class="selectDataDisplayDiv"></div>

												</TD>
												<TD><h:commandButton alt="Select" tabindex="2"
													id="businessEntityButton" image="../../images/select.gif"
													onclick="modalWindow('../popups/businessEntityPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Business Entity','businessEntityDiv','migrationGeneralInfoForm:businessEntityHidden',2,2);
																				return false;"
													rendered="#{migrationGeneralInfoBackingBean.disableField}">
												</h:commandButton></TD>
											</TR>
											<TR>
												<TD><h:outputText id="businessGroup" value="Business Group*"
													styleClass="#{migrationGeneralInfoBackingBean.groupInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
												</TD>
												<h:inputHidden id="businessGroupHiddenOld"
													value="#{migrationGeneralInfoBackingBean.businessGroupDiv}" />
												<h:inputHidden id="businessGroupHidden"
													value="#{migrationGeneralInfoBackingBean.businessGroupDiv}"></h:inputHidden>
												<TD>
												<div id="businessGroupDiv" class="selectDataDisplayDiv"></div>

												</TD>
												<TD><h:commandButton alt="Select" tabindex="3"
													id="businessGroupButton" image="../../images/select.gif"
													onclick="modalWindow('../popups/businessGroupPopup.jsp?lookUpAction='+'1'+'&parentCatalog='+'business group','businessGroupDiv','migrationGeneralInfoForm:businessGroupHidden',2,2);
																				return false;"
													rendered="#{migrationGeneralInfoBackingBean.disableField}">
												</h:commandButton></TD>
											</TR>

										</TABLE>
										</FIELDSET>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText">
								<TBODY>
									<TR>
										<TD>&nbsp;</TD>
										<TD colspan="4">

										<TABLE border="0" cellspacing="0" cellpadding="3">
											<TR>

												<TD width="210"><h:outputText id="contractId"
													value="Contract ID" /></TD>
												<h:inputHidden id="contractIdHidden"
													value="#{migrationGeneralInfoBackingBean.contractId}"></h:inputHidden>
												<TD width="185"><h:outputText id="contractId_txt"
													value="#{migrationGeneralInfoBackingBean.contractId}" /></TD>

											</TR>




											<TR valign="top">

												<TD width="210"><h:outputText value="Effective Date" /></TD>
												<TD width="185"><h:outputText id="effectiveDate_txt"
													value="#{migrationGeneralInfoBackingBean.startDate}" /> <h:inputHidden
													id="effectiveDateHidden"
													value="#{migrationGeneralInfoBackingBean.startDate}">
												</h:inputHidden> <h:inputHidden id="legacyStartDateHidden"
													value="#{migrationGeneralInfoBackingBean.legacyStartDate}">
												</h:inputHidden>
											</TR>
											<TR valign="top">

												<TD width="210"><h:outputText value="Expiry Date" /></TD>
												<TD width="185"><h:outputText id="expiryDate_txt"
													value="#{migrationGeneralInfoBackingBean.endDate}" /> <h:inputHidden
													id="expiryDateHidden"
													value="#{migrationGeneralInfoBackingBean.endDate}">
												</h:inputHidden>
											</TR>


											<TR>
												<TD height="25" width="210"><h:outputText id="contractType"
													value="Contract Type *"
													styleClass="#{migrationGeneralInfoBackingBean.contractTypeInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
												</TD>
												<TD height="25" width="185"><h:inputHidden
													id="contractType_Hidden"
													value="#{migrationGeneralInfoBackingBean.contractType}"></h:inputHidden>
												<DIV id="contractTypeDiv" class="selectDataDisplayDiv"></DIV>
												<h:inputHidden id="contractTypeHiddenOld"
													value="#{migrationGeneralInfoBackingBean.contractType}" />

												<h:selectOneMenu id="contractType_txt"
													styleClass="formInputField" tabindex="4"
													value="#{migrationGeneralInfoBackingBean.contractType}"
													onchange="hideBaseContract();"
													rendered="#{migrationGeneralInfoBackingBean.disableField}">
													<f:selectItems id="contractTypeList"
														value="#{migrationGeneralInfoBackingBean.contractTypeList}" />
												</h:selectOneMenu></TD>
												<TD height="25" width="185">
											</TR>
											<!-- Changes made for STANDARD Contract -->
											<TR id="baseContRowStandard" style="display:none;">

												<TD width="210"><h:outputText id="baseContractIdStandard"
													value="Base Contract Id" /></TD>
												<h:inputHidden id="baseContractIdStandardHidden"
													value="#{migrationGeneralInfoBackingBean.baseContractIdStandardDiv}"></h:inputHidden>
												<TD width="185">
												<div id="baseContractIdStandardDiv"
													class="selectDataDisplayDiv"></div>

												</TD>
												<TD width="288"><h:commandButton alt="Select"
													id="baseContractIdStandardButton" tabindex="5"
													image="../../images/select.gif"
													onclick="baseContractPopupForStandard();
																				return false;">
												</h:commandButton></TD>


											</TR>
											<TR id="baseContEfftDateRowStandard" style="display:none;">

												<TD width="210"><h:outputText id="baseContractDtStandard"
													value="Base Contract Effective Date"
													styleClass="#{migrationGeneralInfoBackingBean.baseContractDtForStandardInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
												</TD>
												<h:inputHidden id="baseContractDtStandardHidden"
													value="#{migrationGeneralInfoBackingBean.baseContractDtStandardDiv}"></h:inputHidden>
												<TD width="185">
												<div id="baseContractDtStandardDiv"
													class="selectDataDisplayDiv"></div>

												</TD>
												<TD width="288"><h:commandButton alt="Select" tabindex="6"
													id="baseContractDtStandardButton"
													image="../../images/select.gif"
													onclick="popupActionForStandard();return false;">
												</h:commandButton></TD>
											</TR>
											<!-- Changes made for STANDARD Contract -->
											<TR id="baseContRow" style="display:none;">

												<TD width="210"><h:outputText id="baseContractId"
													value="Base Contract Id "
													styleClass="#{migrationGeneralInfoBackingBean.baseContractIdInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
												</TD>
												<h:inputHidden id="baseContractIdHidden"
													value="#{migrationGeneralInfoBackingBean.baseContractIdDiv}"></h:inputHidden>
												<TD width="185">
												<div id="baseContractIdDiv" class="selectDataDisplayDiv"></div>

												</TD>
												<TD width="288"><h:commandButton alt="Select"
													id="baseContractIdButton" tabindex="5"
													image="../../images/select.gif"
													onclick="baseContractPopup();
																				return false;">
												</h:commandButton></TD>


											</TR>
											<TR id="baseContEfftDateRow" style="display:none;">

												<TD width="210"><h:outputText id="baseContractDt"
													value="Base Contract Effective Date" /></TD>
												<h:inputHidden id="baseContractDtHidden"
													value="#{migrationGeneralInfoBackingBean.baseContractDtDiv}"></h:inputHidden>
												<TD width="185">
												<div id="baseContractDtDiv" class="selectDataDisplayDiv"></div>

												</TD>
												<TD width="288"><h:commandButton alt="Select" tabindex="6"
													id="baseContractDtButton" image="../../images/select.gif"
													onclick="popupaction();return false;">
												</h:commandButton></TD>
											</TR>
											<TR>

												<TD height="25" width="210"><h:outputText id="groupSizeTxt"
													value="Group Size *"
													styleClass="#{migrationGeneralInfoBackingBean.groupSizeInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
												</TD>
												<h:inputHidden id="groupSizeHidden"
													value="#{migrationGeneralInfoBackingBean.groupSize}"></h:inputHidden>
												<TD width="185">
												<div id="groupSizeDiv" class="selectDataDisplayDiv"></div>

												</TD>
												<TD width="288"><h:commandButton alt="Select" tabindex="7"
													id="groupSizeButton" image="../../images/select.gif"
													onclick="getSelectedDomainReferenceData('../contractpopups/groupSize.jsp','migrationGeneralInfoForm','lineOfBusinessHidden','businessEntityHidden','businessGroupHidden','groupSizeDiv','migrationGeneralInfoForm:groupSizeHidden',2,1,'Group Size');return false;">
												</h:commandButton></TD>
											</TR>

											<TR>
												<TD height="25" width="210"><h:outputText id="productCode"
													value="Product Code *"
													styleClass="#{migrationGeneralInfoBackingBean.requiredProductCode ? 'mandatoryError': 'mandatoryNormal'}">
												</h:outputText></TD>
												<TD width="26%">
												<DIV id="productCodeDiv" class="selectDataDisplayDiv"></DIV>
												<h:inputHidden id="txtProductCode"
													value="#{migrationGeneralInfoBackingBean.productCode}"></h:inputHidden>
												</TD>
												<TD width="48%"><h:commandButton alt="Select"
													id="ProductCodeButton" image="../../images/select.gif"
													style="cursor: hand" tabindex="8"
													onclick="getSelectedDomainReferenceData('../contractpopups/ProductCodePopup.jsp','migrationGeneralInfoForm','lineOfBusinessHidden','businessEntityHidden','businessGroupHidden','productCodeDiv','migrationGeneralInfoForm:txtProductCode',2,1,'Product Codes'); return false;"></h:commandButton>
												</TD>


											</TR>
											<TR>
												<TD height="25" width="210"><h:outputText
													id="standardPlanCode" value="Standard Plan Code">
												</h:outputText></TD>
												<TD width="26%">
												<DIV id="stdPlanCodeDiv" class="selectDataDisplayDiv"></DIV>
												<h:inputHidden id="txtStdPlanCode"
													value="#{migrationGeneralInfoBackingBean.standardPlanCode}"></h:inputHidden>
												</TD>
												<TD width="48%"><h:commandButton alt="Select"
													id="StdPlanCodeButton" image="../../images/select.gif"
													style="cursor: hand" tabindex="9"
													onclick="getSelectedDomainReferenceData('../contractpopups/standardPlanCodePopup.jsp','migrationGeneralInfoForm','lineOfBusinessHidden','businessEntityHidden','businessGroupHidden','stdPlanCodeDiv','migrationGeneralInfoForm:txtStdPlanCode',2,1,'Standard Plan Code'); return false;"></h:commandButton></TD>

											</TR>
											<script type="text/javascript">
										function RSCustomInterface(tbElementName){
											this.tbName = tbElementName;
											this.getText = getText;
											this.setText = setText;
									
										function getText(){
											if(!document.getElementById(this.tbName)) {
												alert('Error: element '+this.tbName+' does not exist, check TextComponentName.');
												return '';
											} else return document.getElementById(this.tbName).value;
										}
										function setText(text){
											if(document.getElementById(this.tbName)) document.getElementById(this.tbName).value = text;
										}
									}
									</script>
											<TR>
												<TD height="25" width="210"><h:outputText id="benefitPlan"
													value="Benefit Plan *"
													styleClass="#{migrationGeneralInfoBackingBean.requiredBenefitPlan ? 'mandatoryError': 'mandatoryNormal'}">
												</h:outputText></TD>
												<TD width="26%"><h:inputText id="benefitPlanTxt"
													styleClass="formInputField" tabindex="10"
													value="#{migrationGeneralInfoBackingBean.benefitPlan}"
													maxlength="15" /></TD>
												<TD width="48%"></TD>

											</TR>


											<TR>
												<TD height="25" width="210"><h:outputText
													id="corporatePlanCode" value="Corporate Plan Code"
													styleClass="#{migrationGeneralInfoBackingBean.requiredCorporateCode ? 'mandatoryError': 'mandatoryNormal'}">
												</h:outputText></TD>
												<TD width="26%">
												<DIV id="corporatePlanCodeDiv" class="selectDataDisplayDiv"></DIV>
												<h:inputHidden id="txtCorporatePlanCode"
													value="#{migrationGeneralInfoBackingBean.corporatePlanCode}"></h:inputHidden>
												</TD>
												<TD width="48%"><h:commandButton alt="Select"
													id="corporatePlanCodeButton"
													image="../../images/select.gif" style="cursor: hand"
													tabindex="11"
													onclick="getSelectedDomainReferenceData('../contractpopups/corporatePlanPopup.jsp','migrationGeneralInfoForm','lineOfBusinessHidden','businessEntityHidden','businessGroupHidden','corporatePlanCodeDiv','migrationGeneralInfoForm:txtCorporatePlanCode',2,1,'Corporate Plan Codes'); return false;"></h:commandButton></TD>


											</TR>

											<TR>
												<TD height="25" width="210"><h:outputText id="brandName"
													value="Brand Name">
												</h:outputText></TD>
												<TD width="26%"><h:inputText id="brandNameTxt"
													styleClass="formInputField" tabindex="13"
													value="#{migrationGeneralInfoBackingBean.brandName}"
													maxlength="30" /></TD>
												<TD width="48%"></TD>

											</TR>

											<TR>
												<TD width="227"><h:outputText id="cobIndicator"
													value="COB Adjudication Indicator *" styleClass="">
												</h:outputText></TD>
												<TD width="26%"><h:selectOneRadio id="COB" tabindex="15"
													value="#{migrationGeneralInfoBackingBean.cobIndicator}">
													<f:selectItem id="COBYes" itemLabel="Yes" itemValue="Y" />
													<f:selectItem id="COBNo" itemLabel="No" itemValue="N" />
												</h:selectOneRadio></TD>
											</TR>
											<TR>
												<TD width="227"><h:outputText id="medIndicator"
													value="Medicare Adjudication Indicator *" styleClass="">
												</h:outputText></TD>
												<TD width="26%"><h:selectOneRadio id="MED" tabindex="16"
													value="#{migrationGeneralInfoBackingBean.medIndicator}">
													<f:selectItem id="MEDYes" itemLabel="Yes" itemValue="Y" />
													<f:selectItem id="MEDNo" itemLabel="No" itemValue="N" />
												</h:selectOneRadio></TD>
											</TR>
											<TR>
												<TD width="227"><h:outputText id="itsIndicator"
													value="ITS Adjudication Indicator *">
												</h:outputText></TD>
												<TD width="26%"><h:selectOneRadio id="ITS" tabindex="17"
													value="#{migrationGeneralInfoBackingBean.itsIndicator}">
													<f:selectItem id="ITSYes" itemLabel="Yes" itemValue="Y" />
													<f:selectItem id="ITSNo" itemLabel="No" itemValue="N" />
												</h:selectOneRadio></TD>
											</TR>

											<TR>
												<TD valign="top" width="227"><h:outputText
													id="regulatoryAgency" value="Regulatory Agency"></h:outputText>
												</TD>
												<TD width="26%">
												<DIV id="regulatoryAgencyDiv" class="selectDataDisplayDiv"></DIV>
												<h:inputHidden id="txtRegulatoryAgency"
													value="#{migrationGeneralInfoBackingBean.regulatoryAgency}"></h:inputHidden>
												</TD>
												<TD width="48%"><h:commandButton alt="Select"
													id="RegulatoryAgencyButton" image="../../images/select.gif"
													style="cursor: hand" tabindex="18"
													onclick="ewpdModalWindow_ewpd('../contractpopups/regulatoryAgencyPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'REGULATORY AGENCY'+'&titleName='+'Regulatory Agency Popup','regulatoryAgencyDiv','migrationGeneralInfoForm:txtRegulatoryAgency',2,1);return false;"></h:commandButton>
												</TD>
											</TR>

											<TR>
												<TD valign="top" width="227"><h:outputText
													id="complianceStatus" value="Compliance Status"></h:outputText>
												</TD>
												<TD width="26%"><h:selectOneMenu id="complStatus"
													styleClass="formInputField" tabindex="19"
													value="#{migrationGeneralInfoBackingBean.complianceStatus}">
													<f:selectItems id="contractTypeList1"
														value="#{ReferenceDataBackingBeanCommon.complianceStatusListForCombo}" />
												</h:selectOneMenu></TD>
											</TR>

											<TR>
												<TD valign="top" width="227"><h:outputText id="nameCode"
													value="Product/Project Name Code">
												</h:outputText></TD>
												<TD width="26%">
												<DIV id="nameCodeDiv" class="selectDataDisplayDiv"></DIV>
												<h:inputHidden id="txtNameCode"
													value="#{migrationGeneralInfoBackingBean.prodProjNameCode}"></h:inputHidden>
												</TD>
												<TD width="48%"><h:commandButton alt="Select"
													id="NameCodeButton" image="../../images/select.gif"
													style="cursor: hand" tabindex="20"
													onclick="ewpdModalWindow_ewpd('../contractpopups/prodProjNameCode.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'PRODUCT/PROJECT NAME CODE'+'&titleName='+'Product/Project Name Code Popup','nameCodeDiv','migrationGeneralInfoForm:txtNameCode',2,1);return false;"></h:commandButton>
												</TD>
											</TR>

											<TR>
												<TD valign="top" width="227"><h:outputText
													value="Contract Term Date"
													styleClass="#{migrationGeneralInfoBackingBean.contractTermDateInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
												</TD>
												<TD width="26%"><h:inputText styleClass="formInputField"
													id="contractStartDate_txt" maxlength="10" tabindex="21"
													value="#{migrationGeneralInfoBackingBean.contractTermDate}" />
												<span class="dateFormat">(mm/dd/yyyy)</span></TD>
												<TD valign="top" width="301"><A href="#"
													onclick="cal1.select('migrationGeneralInfoForm:contractStartDate_txt','anchor1','MM/dd/yyyy'); return false;"
													tabindex="22" name="anchor1" id="anchor1"
													title="cal1.select('migrationGeneralInfoForm:contractStartDate_txt','anchor1','MM/dd/yyyy'); return false;">
												<h:commandButton image="../../images/cal.gif"
													style="cursor: hand" alt="Cal" /> </A></TD>
											</TR>

											<TR>
												<TD width="227"><h:outputText id="multiPlanIndicator"
													value="Multi Plan Indicator"></h:outputText></TD>
												<TD width="26%"><h:selectOneRadio id="multiPlan"
													tabindex="23"
													value="#{migrationGeneralInfoBackingBean.multiplanIndicator}">
													<f:selectItem id="PlanYes" itemLabel="Yes" itemValue="Y" />
													<f:selectItem id="PlanNo" itemLabel="No" itemValue="N" />
												</h:selectOneRadio></TD>
											</TR>

											<TR>
												<TD width="227"><h:outputText id="perfGuarantee"
													value="Performance Guarantee"></h:outputText></TD>
												<TD width="26%"><h:selectOneRadio id="performGuarantee"
													tabindex="24"
													value="#{migrationGeneralInfoBackingBean.performanceGuarantee}">
													<f:selectItem id="perfYes" itemLabel="Yes" itemValue="Y" />
													<f:selectItem id="perfNo" itemLabel="No" itemValue="N" />
												</h:selectOneRadio></TD>
											</TR>

											<TR>
												<TD valign="top" width="227"><h:outputText
													value="Sales Market Date"
													styleClass="#{migrationGeneralInfoBackingBean.salesMarketDateInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
												</TD>
												<TD width="26%"><h:inputText styleClass="formInputField"
													id="salesMarketDate_txt" maxlength="10" tabindex="25"
													value="#{migrationGeneralInfoBackingBean.salesMarketDate}" />
												<span class="dateFormat">(mm/dd/yyyy)</span></TD>
												<TD valign="top" width="301"><A href="#"
													onclick="cal1.select('migrationGeneralInfoForm:salesMarketDate_txt','anchor2','MM/dd/yyyy'); return false;"
													tabindex="26" name="anchor2" id="anchor2"
													title="cal1.select('migrationGeneralInfoForm:salesMarketDate_txt','anchor2','MM/dd/yyyy'); return false;">
												<h:commandButton image="../../images/cal.gif"
													style="cursor: hand" alt="Cal" /> </A></TD>
											</TR>

										</TABLE>
										</TD>
									</TR>

								</TBODY>

							</TABLE>
							<TABLE>
								<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher1"
									textComponentName="migrationGeneralInfoForm:benefitPlanTxt"
									rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Benefit Plan"
									modalHelperPage="../spellcheck/RapidSpellModalHelper.html"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" windowWidth="570" windowHeight="300"
									modal="False" showButton="False" windowX="-1"
									warnDuplicates="False" textComponentInterface="Custom">
								</RapidSpellWeb:rapidSpellWebLauncher>
								<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher2"
									textComponentName="migrationGeneralInfoForm:brandNameTxt"
									rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Brand Name"
									modalHelperPage="../spellcheck/RapidSpellModalHelper.html"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" finishedListener="spellFin"
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
								</RapidSpellWeb:rapidSpellWebLauncher>

								<TR>


									<TD width="20"><!-- After clicking on this action it will be moved to the ../product/save.jsp page -->

									<h:commandButton value="Back" styleClass="wpdButton"
										tabindex="27" onclick="clickBack();return false;">
									</h:commandButton> <h:commandLink id="genLink"
										style="display:none; visibility: hidden;"
										action="#{legacyContractBackingBean.goToStep2FromStep3}">
										<f:verbatim />
									</h:commandLink></TD>
									<TD width="20"><h:commandButton value="Next" tabindex="28"
										styleClass="wpdButton" id="createButton"
										onclick="goToNext();return false;">
									</h:commandButton></TD>
									<h:commandLink id="nextLink"
										style="display:none; visibility: hidden;"
										action="#{migrationGeneralInfoBackingBean.saveMigGeneralInfo}">
										<f:verbatim />
									</h:commandLink>
									<h:commandLink id="saveProductInfoLink"
										style="display:none; visibility: hidden;"
										action="#{migrationGeneralInfoBackingBean.saveProductInfo}">
										<f:verbatim />
									</h:commandLink>
									<h:commandLink id="removeProductInfoLink"
										style="display:none; visibility: hidden;"
										action="#{migrationGeneralInfoBackingBean.removeProductInfo}">
										<f:verbatim />
									</h:commandLink>
									<h:commandLink id="getProductSysIdLink"
										style="display:none; visibility: hidden;"
										action="#{migrationGeneralInfoBackingBean.getProductSysId}">
										<f:verbatim />
									</h:commandLink>

									<TD width="20"><h:commandButton value="Cancel" tabindex="29"
										styleClass="wpdButton"
										onclick="deleteContract();return false;">
									</h:commandButton></TD>
								<tr>
									<TD width="20"><h:commandButton value="Done" tabindex="30"
										styleClass="wpdButton" onclick="return done();">
									</h:commandButton></TD>
									<h:commandLink id="cancelMigrationLink"
										style="display:none; visibility: hidden;"
										action="#{migrationGeneralInfoBackingBean.cancelMigration}">
										<f:verbatim />
									</h:commandLink>
									<h:inputHidden id="duplicateData"
										value="#{migrationGeneralInfoBackingBean.duplicateData}"></h:inputHidden>
								</tr>

							</TABLE>
							<!--	End of Page data	--> <!--</FIELDSET>--></TD>
						</TR>
					</TABLE>

					<!-- Space for hidden fields -->
					<h:inputHidden id="isSecondTimeMigrationHid"
						value="#{migrationGeneralInfoBackingBean.disableField}" />
					<h:inputHidden id="hiddenProductParentSysId"
						value="#{migrationGeneralInfoBackingBean.productParentSysId}" />
					<h:inputHidden id="hiddenBaseContractId"
						value="#{migrationGeneralInfoBackingBean.baseContractId}" />
					<h:inputHidden id="dateSegment" value="value1"></h:inputHidden>
					<h:inputHidden id="selectBaseContractFlag" value="Y"></h:inputHidden>
					<h:inputHidden id="confirmProductFlag"
						value="#{migrationGeneralInfoBackingBean.confirmProductFlag}"></h:inputHidden>
					<h:inputHidden id="saveProductFlag"
						value="#{migrationGeneralInfoBackingBean.saveProductFlag}"></h:inputHidden>

					<h:inputHidden id="autoPopulateFlag"
						value="#{migrationGeneralInfoBackingBean.autoPopulate}"></h:inputHidden>

					<h:inputHidden id="benefitYrIndWarningMessage"
						value="#{migrationGeneralInfoBackingBean.benefitYrIndWarningMessage}"></h:inputHidden>
					<h:commandLink id="retrieveBenefitYearConflictMessage"
						style="display:none; visibility: hidden;"
						action="#{migrationGeneralInfoBackingBean.retrieveBenefitYearConflictMessage}">
						<f:verbatim />
					</h:commandLink>
					<h:commandLink id="retrieveBenefitYearConflictMessageForStandard"
						style="display:none; visibility: hidden;"
						action="#{migrationGeneralInfoBackingBean.retrieveBenefitYearConflictMessageForStandard}">
						<f:verbatim />
					</h:commandLink>
					<h:commandLink id="doneLink"
						style="display:none; visibility: hidden;"
						action="#{migrationGeneralInfoBackingBean.done}">
						<f:verbatim />
					</h:commandLink>
					<%--<h:commandLink id="hiddenLnk1" style="display:none; visibility: hidden;" > <f:verbatim/></h:commandLink>--%>
					<!-- End of hidden fields  -->

				</h:form></TD>
			</TR>
			<TR>
				<h:inputHidden id="disableField"
					value="#{migrationGeneralInfoBackingBean.disableField}"></h:inputHidden>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>

	<%@include file="../template/freeze.html"%>
	</BODY>
</f:view>

<script>
IGNORED_FIELD1='migrationGeneralInfoForm:duplicateData' ;
var isSecondTimeMigration =  document.getElementById('migrationGeneralInfoForm:isSecondTimeMigrationHid').value;
var intialBaseContractId = document.getElementById('migrationGeneralInfoForm:baseContractIdStandardHidden').value =='' ? false : true;
var baseContractRemoved = false;
	document.getElementById('migrationGeneralInfoForm:brandNameTxt').focus(); // for on load default selection

if(isSecondTimeMigration == 'true'){
	document.getElementById('contractTypeDiv').style.visibility = 'hidden';
	document.getElementById('contractTypeDiv').style.height = "0px";
}else{
	copyHiddenToDiv_ewpd('migrationGeneralInfoForm:contractType_Hidden','contractTypeDiv',2,1);

	var selectItem = document.getElementById('migrationGeneralInfoForm:contractType_Hidden');
    var baseContRow = getObj('baseContRow');   
	var baseContEfftDateRow = getObj('baseContEfftDateRow');
	var baseContRowStandard = getObj('baseContRowStandard');   
	var baseContEfftDateRowStandard = getObj('baseContEfftDateRowStandard');
  	if(selectItem.value != 'STANDARD') {                         // unhide if the selected value is 'STANDARD'
        baseContRowStandard.style.display='none';
		baseContEfftDateRowStandard.style.display='none';
    }else{			
		baseContRowStandard.style.display='';
        baseContEfftDateRowStandard.style.display = '';			
    }
  	if(selectItem.value != 'CUSTOM') { 
        // unhide if the selected value is 'CUSTOM'
        baseContRow.style.display='none';
		baseContEfftDateRow.style.display='none';
    }  else{ 
        baseContRow.style.display='';
		baseContEfftDateRow.style.display='';
    }
}

// for delete contract
function deleteContract(){
	var message = "Are you sure you want to cancel? All data saved during this migration will be lost"
	if(window.confirm(message)){
		submitLink('migrationGeneralInfoForm:cancelMigrationLink');
	}else{
		return false;
	}
}
	copyHiddenToDiv_ewpd('migrationGeneralInfoForm:txtRegulatoryAgency','regulatoryAgencyDiv',2,1); 
	copyHiddenToDiv_ewpd('migrationGeneralInfoForm:txtNameCode','nameCodeDiv',2,1); 

 	copyHiddenToDiv_ewpd('migrationGeneralInfoForm:lineOfBusinessHidden','lineOfBusinessDiv',2,2);
 	copyHiddenToDiv_ewpd('migrationGeneralInfoForm:businessEntityHidden','businessEntityDiv',2,2); 
 	copyHiddenToDiv_ewpd('migrationGeneralInfoForm:businessGroupHidden','businessGroupDiv',2,2); 

 	copyHiddenToDiv_ewpd('migrationGeneralInfoForm:baseContractIdHidden','baseContractIdDiv',2,1);  
 	copyHiddenToDiv_ewpd('migrationGeneralInfoForm:baseContractDtHidden','baseContractDtDiv',2,1);
	copyHiddenToDiv_ewpd('migrationGeneralInfoForm:baseContractIdStandardHidden','baseContractIdStandardDiv',2,1);  
 	copyHiddenToDiv_ewpd('migrationGeneralInfoForm:baseContractDtStandardHidden','baseContractDtStandardDiv',2,1);  
 	copyHiddenToDiv_ewpd('migrationGeneralInfoForm:groupSizeHidden','groupSizeDiv',2,1);
	copyHiddenToDiv_ewpd('migrationGeneralInfoForm:txtProductCode','productCodeDiv',2,1); 
	copyHiddenToDiv_ewpd('migrationGeneralInfoForm:txtStdPlanCode','stdPlanCodeDiv',2,1); 
	copyHiddenToDiv_ewpd('migrationGeneralInfoForm:txtCorporatePlanCode','corporatePlanCodeDiv',2,1); 

function baseContractPopup(){
	ewpdModalWindow_ewpd(getBaseContractPopupUrl('migrationGeneralInfoForm:lineOfBusinessHidden',
												'migrationGeneralInfoForm:businessEntityHidden',
												'migrationGeneralInfoForm:businessGroupHidden',
												'migrationGeneralInfoForm:contractIdHidden',
                                                'migrationGeneralInfoForm:hiddenProductParentSysId',
									 		'../migration/searchBaseContractCode.jsp'+getUrl()+'?'),
						'baseContractIdDiv','migrationGeneralInfoForm:baseContractIdHidden',2,1,'baseContractDtDiv','migrationGeneralInfoForm:baseContractDtHidden');


	var retValue = document.getElementById('migrationGeneralInfoForm:baseContractIdHidden').value;
	if(retValue!= ''){
		var splitValue = retValue.split('~');
		document.getElementById('migrationGeneralInfoForm:selectBaseContractFlag').value ="Y";
		document.getElementById('migrationGeneralInfoForm:hiddenBaseContractId').value = splitValue[1];			
		submitLink('migrationGeneralInfoForm:getProductSysIdLink');
		freezeScreen();
	}else if(retValue == '' || retValue == null || retValue == 'undefined'){
		document.getElementById('migrationGeneralInfoForm:selectBaseContractFlag').value ="N";
		if(intialBaseContractId){
			baseContractRemoved = true;
		}
	}
}
function baseContractPopupForStandard(){
	ewpdModalWindow_ewpd(getBaseContractPopupUrl('migrationGeneralInfoForm:lineOfBusinessHidden',
												'migrationGeneralInfoForm:businessEntityHidden',
												'migrationGeneralInfoForm:businessGroupHidden',
												'migrationGeneralInfoForm:contractIdHidden',
                                                'migrationGeneralInfoForm:hiddenProductParentSysId',
									 		'../migration/searchBaseContractCode.jsp'+getUrl()+'?'),
						'baseContractIdStandardDiv','migrationGeneralInfoForm:baseContractIdStandardHidden',2,1,'baseContractDtStandardDiv','migrationGeneralInfoForm:baseContractDtStandardHidden');

		
	var retValue = document.getElementById('migrationGeneralInfoForm:baseContractIdStandardHidden').value;
	if(retValue!= ''){
		var splitValue = retValue.split('~');
		document.getElementById('migrationGeneralInfoForm:selectBaseContractFlag').value ="Y";
		document.getElementById('migrationGeneralInfoForm:hiddenBaseContractId').value = splitValue[1];			
		submitLink('migrationGeneralInfoForm:getProductSysIdLink');
		freezeScreen();
	}else if(retValue == '' || retValue == null || retValue == 'undefined'){
		document.getElementById('migrationGeneralInfoForm:selectBaseContractFlag').value ="N";
		if(intialBaseContractId){
			baseContractRemoved = true;
		}
	}
	var divj = document.getElementById('baseContractIdStandardDiv').innerText;
	if((divj != '') || (null != divj)){
		baseContEfftDateRowStandard.style.display = '';
	}else{
		baseContEfftDateRowStandard.style.display = 'none';
	}			

}
function copyHiddenToDiv_ewpd(hiddenId, divId, attrCount, attrPos) {

	var hiddenObj = getObj(hiddenId);
	var divObj = getObj(divId);
	var divHtml = '';
	var textValue = hiddenObj.value;
	var values;
	var targetDiv = getObj(divId);
	var elementCount = 0;
	if(hiddenObj.value == null || hiddenObj.value == ''){
			divObj.innerText = '';
			targetDiv.style.height="17px";
	}
	
	if(textValue != null && textValue != undefined && textValue.length > 0){
		attrPos -= 1;			
		values = textValue.split("~");
		for(var i=0, n = values.length; i<n; i+=attrCount) {
			elementCount++;
			divHtml += values[i+attrPos] + "\n";
		}
		targetDiv.innerText = divHtml;
		if(elementCount ==0 || elementCount == 1)
			targetDiv.style.height="17px";
		else
			targetDiv.style.height="30px";

		targetDiv.style.overflowY='auto';
		targetDiv.style.overflowX = 'hidden';
	}
	targetDiv.style.visibility = 'visible';
	targetDiv.style.display = 'block';
}

 function getBaseContractPopupUrl(lobId,beId,bgId,legacyContractId,prodParSysId,url){
	var lob = getObj(lobId).value;
	var be = getObj(beId).value;
	var bg = getObj(bgId).value;
	var lcId = getObj(legacyContractId).value;
    var productParentSysId = getObj(prodParSysId).value;
	var popupUrl = url;
	popupUrl += 'lob=' + escape(lob) 
				+ '&be=' + escape(be) 
				+ '&bg=' + escape(bg) 
				+ '&legacyContractId='	+ escape(lcId) 
                + '&productParentSysId=' + escape(productParentSysId)
				+ '&temp=' + Math.random();
	return popupUrl;
 }
	



function hideBaseContract()
{
	var selectObj = getObj('migrationGeneralInfoForm:contractType_txt');    // Get the ComboBox for contract Type
	var selIndex = selectObj.selectedIndex; 
	                 // Get the Selected index in contract type
	var selectItem = selectObj[selIndex];                       // Get the item object which is selected in Combo.
	var baseContRow = getObj('baseContRow');   
	var baseContEfftDateRow = getObj('baseContEfftDateRow');
	var baseContRowStandard = getObj('baseContRowStandard');   
	var baseContEfftDateRowStandard = getObj('baseContEfftDateRowStandard');
                 // Get the Table row object which contain base contract.
  if(selectItem.value != 'STANDARD') {                         // unhide if the selected value is 'STANDARD'
            baseContRowStandard.style.display='none';
			baseContEfftDateRowStandard.style.display='none';
			document.getElementById('migrationGeneralInfoForm:selectBaseContractFlag').value = "N";
  }else{			
	baseContRowStandard.style.display='';
    baseContEfftDateRowStandard.style.display = '';
  }
  if(selectItem.value != 'CUSTOM') { 
	// unhide if the selected value is 'CUSTOM'
	baseContRow.style.display='none';
	baseContEfftDateRow.style.display='none';
	document.getElementById('migrationGeneralInfoForm:selectBaseContractFlag').value = "N";
  }else{
    baseContRow.style.display='';
	baseContEfftDateRow.style.display='';		
  }	

 	document.getElementById('migrationGeneralInfoForm:baseContractIdStandardHidden').value ='';
 	document.getElementById('baseContractIdStandardDiv').innerHTML ='';
	document.getElementById('migrationGeneralInfoForm:baseContractDtStandardHidden').value ='';
 	document.getElementById('baseContractDtStandardDiv').innerHTML ='';
 	
 	document.getElementById('migrationGeneralInfoForm:baseContractIdHidden').value ='';
 	document.getElementById('baseContractIdDiv').innerHTML ='';		
 	document.getElementById('migrationGeneralInfoForm:baseContractDtHidden').value ='';
 	document.getElementById('baseContractDtDiv').innerHTML ='';
 	
}


var selectObj = getObj('migrationGeneralInfoForm:contractType_txt');    // Get the ComboBox for contract Type
if(selectObj!=null)
{
	var selIndex = selectObj.selectedIndex; 
                 // Get the Selected index in contract type
   	var selectItem = selectObj[selIndex];                       // Get the item object which is selected in Combo.
    var baseContRow = getObj('baseContRow');                    // Get the Table row object which contain base contract.
	var baseContEfftDateRow = getObj('baseContEfftDateRow');
	var baseContRowStandard = getObj('baseContRowStandard');   
	var baseContEfftDateRowStandard = getObj('baseContEfftDateRowStandard');
  	if(selectItem.value != 'STANDARD') {                           // unhide if the selected value is 'STANDARD'
        baseContRowStandard.style.display='none';
		baseContEfftDateRowStandard.style.display='none';
    }  else{
		baseContRowStandard.style.display='';
        baseContEfftDateRowStandard.style.display = '';			
    }
  if(selectItem.value != 'CUSTOM') {                           // unhide if the selected value is 'CUSTOM'
        baseContRow.style.display='none';
		baseContEfftDateRow.style.display='none';
    }  else{ 
        baseContRow.style.display='';
		baseContEfftDateRow.style.display='';
    }
}

function popupaction()
{
	var baseContractid = document.getElementById('migrationGeneralInfoForm:baseContractIdHidden').value;
	var url ='../contractpopups/SelectBaseContractStartDate.jsp'+getUrl()+'?sysId='+ baseContractid;
	var retValue = ewpdModalWindow_ewpd(url,'baseContractDtDiv','migrationGeneralInfoForm:baseContractDtHidden',2,1);
	var SrtDateObj = getObj('baseContractDtDiv');
	var startDate = SrtDateObj.innerText;

	if(getObj('migrationGeneralInfoForm:baseContractDtHidden').value != '') {
		submitLink('migrationGeneralInfoForm:retrieveBenefitYearConflictMessage');
		freezeScreen();
	} else {
		getObj('migrationGeneralInfoForm:benefitYrIndWarningMessage').value = '';
	}
}
function popupActionForStandard()
{
	var baseContractid = document.getElementById('migrationGeneralInfoForm:baseContractIdStandardHidden').value;
	var url ='../contractpopups/SelectBaseContractStartDate.jsp'+getUrl()+'?sysId='+ baseContractid;
	var retValue = ewpdModalWindow_ewpd(url,'baseContractDtStandardDiv','migrationGeneralInfoForm:baseContractDtStandardHidden',2,1);
	var SrtDateObj = getObj('baseContractDtStandardDiv');
	var startDate = SrtDateObj.innerText;

	if(getObj('migrationGeneralInfoForm:baseContractDtStandardHidden').value != '') {
		submitLink('migrationGeneralInfoForm:retrieveBenefitYearConflictMessageForStandard');
		freezeScreen();
	} else {
		getObj('migrationGeneralInfoForm:benefitYrIndWarningMessage').value = '';
	}
}

function modalWindow(url, targetId, hiddenId, attrCount, attrPos, peer_divId, peer_hiddenId, hiddenIdToDisable){
	var val = getObj(hiddenId).value;
	ewpdModalWindow_ewpd(url, targetId, hiddenId, attrCount, attrPos, peer_divId, peer_hiddenId, hiddenIdToDisable);
}

function clickBack(){
	navigatePageActionSubmit('migrationGeneralInfoForm:genLink');
}

function goToNext(){
	if(isSecondTimeMigration == 'true'){
		var lineOfBusinessOld = document.getElementById('migrationGeneralInfoForm:lineOfBusinessHiddenOld').value;
		var lineOfBusiness = document.getElementById('migrationGeneralInfoForm:lineOfBusinessHidden').value;
		var businessEntityOld = document.getElementById('migrationGeneralInfoForm:businessEntityHiddenOld').value;
		var businessEntity = document.getElementById('migrationGeneralInfoForm:businessEntityHidden').value;
		var businessGroupOld = document.getElementById('migrationGeneralInfoForm:businessGroupHiddenOld').value;
		var businessGroup = document.getElementById('migrationGeneralInfoForm:businessGroupHidden').value;
		var contractTypeOld = document.getElementById('migrationGeneralInfoForm:contractTypeHiddenOld').value;
		var contractType = document.getElementById('migrationGeneralInfoForm:contractType_txt').value;
		var change = "";
		var confirmed = true;
		var domainChange = false;

		if(!(lineOfBusinessOld =="" || businessEntityOld =="" || businessGroupOld =="" )){
			if(lineOfBusinessOld != lineOfBusiness ||	businessEntityOld != businessEntity ||	businessGroupOld != businessGroup) {
				change ='domain';
				domainChange = true;
			}
		}
	
		if(contractTypeOld != '' && contractTypeOld != contractType) {
			if(change !='')
				change +=' and ';
			change +=  'contract type';
		}
	
		if(change != '') {
			if(!domainChange){
				var messageforchange = 'Are you sure, you want to change the '+change+ ' ?';
			}else{
				var messageforchange = 'Domain change might cause Product to be detached, if domains does not match. Are you sure, you want to change the '+change+ ' ?';
			}
			confirmed = window.confirm(messageforchange)
		} 
		if (confirmed)
			onNext();
	}else{
			onNext();
	}
}

var link;
function onNext(){
		// Contract has an associated Basecontract
		var hasBaseContract = document.getElementById('migrationGeneralInfoForm:selectBaseContractFlag').value =='Y' ? true : false;
		// newly selected base contract's product is conflicting with currently associated product
		var productConflict = document.getElementById('migrationGeneralInfoForm:confirmProductFlag').value == 'true' ? true : false;
		// Currently product is not associated in Migration.
		var newProduct = document.getElementById('migrationGeneralInfoForm:saveProductFlag').value == 'true' ? true : false;
		//autopopulate the model contract from previous 
		var autoPopulateFlag = document.getElementById('migrationGeneralInfoForm:autoPopulateFlag').value == 'true' ? true : false;

		// If legacy contract's PlanRenewalType (ie BY/CY) and Model Contract's Planrenewal type differs 'benefitYrIndWarningMessage'
		// field will have the corresponding message.
		var benefitYearIndConflicts = getObj('migrationGeneralInfoForm:benefitYrIndWarningMessage').value != '' ? true : false;
		var confirmed = true;
		if(hasBaseContract){
			if(productConflict){			
				var message = "The base contract's product does not match the already chosen product in migration. Continuing may lose the already mapped information in this mapping. Do you want to continue ?"
				confirmed = window.confirm(message);
			}else if (newProduct || autoPopulateFlag){
				var message = "The base contract's product will be mapped to the legacy structure in this migration. Do you want to continue ?"
				confirmed = window.confirm(message);
			}
			if(benefitYearIndConflicts) {
				confirmed = window.confirm(getObj('migrationGeneralInfoForm:benefitYrIndWarningMessage').value);
			}
		}else if(baseContractRemoved){
			var message = "The base contract has been removed. Continuing may lose the product information and the mapping. Do you want to continue ?"	
			var confirm = window.confirm(message);
			if(confirm) {
				link = 'migrationGeneralInfoForm:removeProductInfoLink';
				confirmed = false;
			}
		}

		if(confirmed) {
			if(hasBaseContract && (newProduct || autoPopulateFlag || productConflict) )
				link = 'migrationGeneralInfoForm:saveProductInfoLink';
				// submitLink('migrationGeneralInfoForm:saveProductInfoLink');
			else	
				link = 'migrationGeneralInfoForm:nextLink';
				// submitLink('migrationGeneralInfoForm:nextLink');
			 	freezeScreen();
		}else if(!baseContractRemoved){
			return false;
		}
		return runSpellCheck();
	
}
var doneFlag = false;

function done(){
	doneFlag = true;
	goToNext();
	return false;
	//return runSpellCheck();   
}
function runSpellCheck(){
	var rswlCntrls = ["rapidSpellWebLauncher1", "rapidSpellWebLauncher2"];
	var i=0;
	for(var i=0; i<rswlCntrls.length; i++){
		eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
	}
	return false;
}
function spellFin(cancelled){
	if(doneFlag)
		document.getElementById('migrationGeneralInfoForm:doneLink').click();	
	else{ 
		document.getElementById(link).click();			
	}			
	doneFlag = false;
}
</script>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('migrationGeneralInfoForm:duplicateData').value == '')
		document.getElementById('migrationGeneralInfoForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
	else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('migrationGeneralInfoForm:duplicateData').value;
</script>


</HTML>

