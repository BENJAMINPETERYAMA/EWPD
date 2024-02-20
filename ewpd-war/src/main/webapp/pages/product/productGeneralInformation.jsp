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

<TITLE>Product General Information</TITLE>
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
%>3
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
<BODY onkeypress="return submitOnEnterKey('productForm:saveButton');">
	<hx:scriptCollector id="scriptCollector1">
		<h:inputHidden id="hidden2"
			value="#{productGeneralInformationBackingBean.hiddenInit}"></h:inputHidden>
		<TABLE width="100%" cellpadding="0" cellspacing="0">


			<TR>
				<TD><jsp:include page="../navigation/top_Product_Print.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="productForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD width="15%" valign="top" class="leftPanel">


							<DIV class="treeDiv"><!-- Space for Tree  Data	--> <jsp:include
								page="productTree.jsp"></jsp:include></DIV>

							</TD>

							<TD colspan="2" valign="top" width="85%" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message
											value="#{productGeneralInformationBackingBean.validationMessages}"></w:message>
										</TD>
									</TR>
								</TBODY>
							</TABLE>

							<!-- Table containing Tabs -->
							<TABLE width="241" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<TR>
									<TD width="200" id="tab1">
									<TABLE width="141" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
											<TD width="186" class="tabActive"><h:outputText
												value=" General Information" /></TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<TD width="200" id="tab2">
									<TABLE width="141" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD class="tabNormal"><h:commandLink
												action="#{productComponentAssociationBackingBean.loadComponent}"
												onmousedown="javascript:navigatePageAction(this.id);"
												id="compId">
												<h:outputText value="Component Association" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<TD width="200" id="tab3">
									<TABLE width="141" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD class="tabNormal"><h:commandLink
												action="#{productAdminAssociationBackingBean.loadComponent}"
												onmousedown="javascript:navigatePageAction(this.id);"
												id="thisId">
												<h:outputText value="Admin Options" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>

									<td width="200" id="tab4">
									<table width="141" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21" /></td>
											<td class="tabNormal"><h:commandLink
												action="#{productNoteAssociationBackingBean.loadNotes}"
												onmousedown="javascript:navigatePageAction(this.id);"
												id="noteId">
												<h:outputText value="Notes" />
											</h:commandLink></td>
											<td width="2" align="right"><img
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21" /></td>
										</tr>
									</table>
									</td>
									<td width="200" id="tab5">
									<table width="141" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21" /></td>
											<td class="tabNormal"><h:commandLink
												action="#{productDenialAndExclusionBackingBean.displayDenialAndExclusionTab}"
												onmousedown="javascript:navigatePageAction(this.id);"
												id="rulesId">
												<h:outputText value="Rules" />
											</h:commandLink></td>
											<td width="2" align="right"><img
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21" /></td>
										</tr>
									</table>
									</td>
								</TR>
							</TABLE>
							<!-- End of Tab table -->

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

							<!--	Start of Table for actual Data	-->
							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText">
								<TBODY>
									<TR>
										<TD colspan="4">
										<FIELDSET
											style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:360">
										<LEGEND> <FONT color="black">Business Domain</FONT></LEGEND>
										<TABLE border="0" cellspacing="5" cellpadding="3">
											<TR>
												<TD width="367"><h:outputText id="lineOfBusiness"
													value="Line Of Business*"
													styleClass="#{productGeneralInformationBackingBean.lobInvalid ? 'mandatoryError':'mandatoryNormal'}}" />
												</TD>
												<h:inputHidden id="lineOfBusinessHidden"
													value="#{productGeneralInformationBackingBean.lineOfBusinessDiv}"></h:inputHidden>
												<TD width="139">
												<div id="lineOfBusinessDiv" class="selectDataDisplayDiv"></div>

												</TD>
												<TD width="24"><h:commandButton alt="lineOfBusiness"
													id="lineOfBusinessButton" image="../../images/select.gif"
													onclick="ewpdModalWindow_ewpd('../popups/lineOfBusinessPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Line of Business','lineOfBusinessDiv','productForm:lineOfBusinessHidden',2,2);
																				return false;"
													tabindex="1"
													rendered="#{!productGeneralInformationBackingBean.higherVersion }">
												</h:commandButton></TD>
											</TR>
											<TR>
												<TD width="367"><h:outputText id="businessEntity"
													value="Business Entity*"
													styleClass="#{productGeneralInformationBackingBean.entityInvalid ? 'mandatoryError':'mandatoryNormal'}}" />
												</TD>
												<h:inputHidden id="businessEntityHidden"
													value="#{productGeneralInformationBackingBean.businessEntityDiv}"></h:inputHidden>
												<TD width="139">
												<div id="businessEntityDiv" class="selectDataDisplayDiv"></div>

												</TD>
												<TD width="24"><h:commandButton alt="businessEntity"
													id="businessEntitButton" image="../../images/select.gif"
													onclick="ewpdModalWindow_ewpd('../popups/businessEntityPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Business Entity','businessEntityDiv','productForm:businessEntityHidden',2,2);

																				return false;"
													rendered="#{!productGeneralInformationBackingBean.higherVersion }"
													tabindex="2">
												</h:commandButton></TD>
											</TR>
											<TR>
												<TD width="367"><h:outputText id="businessGroup"
													value="Business Group*"
													styleClass="#{productGeneralInformationBackingBean.groupInvalid ? 'mandatoryError':'mandatoryNormal'}}" />
												</TD>
												<h:inputHidden id="businessGroupHidden"
													value="#{productGeneralInformationBackingBean.businessGroupDiv}"></h:inputHidden>
												<TD width="139">
												<div id="businessGroupDiv" class="selectDataDisplayDiv"></div>

												</TD>
												<TD width="24"><h:commandButton alt="businessGroup"
													id="businessGroupButton" image="../../images/select.gif"
													onclick="ewpdModalWindow_ewpd('../popups/businessGroupPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'business group','businessGroupDiv','productForm:businessGroupHidden',2,2);

																				return false;"
													tabindex="3"
													rendered="#{!productGeneralInformationBackingBean.higherVersion }">
												</h:commandButton></TD>
											</TR>
<!-- CARS START -->							
											<TR>
												<TD width="367"><h:outputText id="marketBusinessUnit"
													value="Market Business Unit*"
													styleClass="#{productGeneralInformationBackingBean.marketBusinessUnitInvalid ? 'mandatoryError':'mandatoryNormal'}}" />
												</TD>
												<h:inputHidden id="marketBusinessUnitHidden"
													value="#{productGeneralInformationBackingBean.marketBusinessUnitDiv}"></h:inputHidden>
												<TD width="139">
												<div id="marketBusinessUnitDiv" class="selectDataDisplayDiv"></div>

												</TD>
												<TD width="24"><h:commandButton alt="businessGroup"
													id="marketBusinessUnitButton" image="../../images/select.gif"
													onclick="ewpdModalWindow_ewpd('../popups/marketBusinessUnit.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Market Business Unit','marketBusinessUnitDiv','productForm:marketBusinessUnitHidden',2,2);

																				return false;"
													tabindex="3"
													rendered="#{!productGeneralInformationBackingBean.higherVersion }">
												</h:commandButton></TD>
											</TR>
<!-- CARS END -->
										</TABLE>
										</FIELDSET>
										</TD>
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
									<TR valign="top">
										<TD width="10"></TD>
										<TD width="122"><h:outputText value="Name*"
											styleClass="#{productGeneralInformationBackingBean.nameInvalid ? 'mandatoryError' : 'mandatoryNormal'}}" />
										</TD>

										<TD width="184"><h:inputText id="productName_txt"
											maxlength="30" styleClass="formInputField"
											value="#{productGeneralInformationBackingBean.productName}"
											tabindex="4"
											rendered="#{!productGeneralInformationBackingBean.higherVersion}"></h:inputText>
										<h:inputHidden id="productNameHidden"
											value="#{productGeneralInformationBackingBean.productName}"
											rendered="#{productGeneralInformationBackingBean.higherVersion}"></h:inputHidden>
										<h:outputText id="productName_outpt"
											
											rendered="#{productGeneralInformationBackingBean.higherVersion}"
											value="#{productGeneralInformationBackingBean.productName}"></h:outputText>

										</TD>
										<TD width="40"><f:verbatim></f:verbatim></TD>
									</TR>
									<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher1"
										textComponentName="productForm:productName_txt"
										rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Product Name"
										modalHelperPage="../spellcheck/RapidSpellModalHelper.html"
										showNoErrorsMessage="False" showFinishedMessage="False"
										includeUserDictionaryInSuggestions="True"
										allowAnyCase="True" allowMixedCase="True"
										windowWidth="570" windowHeight="300"
										modal="False" showButton="False"
										windowX="-1" warnDuplicates="False"
										textComponentInterface="Custom">
									</RapidSpellWeb:rapidSpellWebLauncher>
									<TR>
										<TD width="6" height="30"></TD>
										<TD width="112"><h:outputText id="productTyp"
											value="Product Type*"
											styleClass="#{productGeneralInformationBackingBean.requiredProductType ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>

										<TD height="9" width="184"><h:outputText id="productType"
											value="#{productGeneralInformationBackingBean.productType}" />
										<h:inputHidden id="productTypeHidden"
											value="#{productGeneralInformationBackingBean.productType}"></h:inputHidden>
										</TD>
									</TR>
									<tr id="sub1" style="display:none;">
										<TD width="6" height="30"></TD>
										<TD height="9" width="112"><h:outputText id="mandType"
											value="Mandate Type*"
											styleClass="#{productGeneralInformationBackingBean.requiredMandateType ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD height="9" width="184"><h:selectOneMenu id="mandateType"
											styleClass="formInputField" tabindex="6"
											value="#{productGeneralInformationBackingBean.mandateType}"
											onchange="getMandateType()">
											<f:selectItems id="mandateTypeList"
												value="#{ReferenceDataBackingBeanCommon.mandateTypeListForCombo}" />
										</h:selectOneMenu></TD>
									</TR>

									<tr id="sub3" style="display:none;">
										<TD width="6" height="30"></TD>
										<TD height="9" width="112"><h:outputText id="mandType1"
											value="Mandate Type*"
											styleClass="#{productGeneralInformationBackingBean.requiredMandateType ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD height="9" width="184"><h:inputTextarea
											styleClass="selectDivReadOnly" id="mandateType1"
											value="#{productGeneralInformationBackingBean.mandateType}"
											readonly="true" style="border:0"></h:inputTextarea></TD>
									</TR>

									<tr id="sub2" style="display:none;">
										<TD width="6" height="30"></TD>
										<TD height="9" width="112"><h:outputText id="state"
											value="Jurisdiction*"
											styleClass="#{productGeneralInformationBackingBean.requiredState  ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<h:inputHidden id="hid_state"
											value="#{productGeneralInformationBackingBean.stateCode}"></h:inputHidden>
										<TD width="177">
										<div id="stateDiv" class="selectDataDisplayDiv"></div>
										<SCRIPT> parseForStateDiv(document.getElementById('stateDiv'), document.getElementById('productForm:hid_state')); </SCRIPT>
										</TD>
										<TD width="40"><h:commandButton alt="State" id="stateButton"
											image="../../images/select.gif"
											onclick="prodcutStructClean();
													getSelectedDomainReferenceData('../contractpopups/HeadQuarterstatePopup.jsp','productForm','lineOfBusinessHidden','businessEntityHidden','businessGroupHidden','stateDiv','productForm:hid_state',2,2,'State Code');
													setRefDataUseFlag('productForm', 'hid_state', 'stateDiv');copyToSub4();
																					return false;"
											tabindex="7">
										</h:commandButton></TD>
									</TR>
									<tr id="sub4" style="display:none;">
										<TD width="6" height="30"></TD>
										<TD height="9" width="112"><h:outputText id="state1"
											value="State*"
											styleClass="#{productGeneralInformationBackingBean.requiredState  ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<h:inputHidden id="hid_state1"
											value="#{productGeneralInformationBackingBean.stateCode}"></h:inputHidden>
										<TD height="9" width="184"><h:inputTextarea
											styleClass="selectDivReadOnly" id="stateCde1"
											value="#{productGeneralInformationBackingBean.stateCode}"
											readonly="true" style="border:0"></h:inputTextarea> <SCRIPT>copyHiddenToInputText('productForm:hid_state1','productForm:stateCde1',1); </SCRIPT>
										</TD>
									</TR>
									<TR id="sub5" style="display:none;">
										<TD width="6" height="30"></TD>
										<TD height="9" width="112"><h:outputText id="statecode2"
											value="State*"
											styleClass="#{productGeneralInformationBackingBean.requiredState  ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD height="9" width="184"><h:outputText id="stateLabel"
											value="ALL"></h:outputText></TD>
									</TR>
									
									<TR valign="top">
										<TD width="10"></TD>
										<TD width="122"><h:outputText value="Description*"
											styleClass="#{productGeneralInformationBackingBean.descInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD width="184"><h:inputTextarea
											styleClass="formTxtAreaField_GeneralDesc"
											id="productDescription_txt"
											value="#{productGeneralInformationBackingBean.productDescription}"
											tabindex="8"></h:inputTextarea></TD>
										<TD width="40"><f:verbatim></f:verbatim></TD>
									</TR>
                                    <RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher2"
										textComponentName="productForm:productDescription_txt"
										rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Product Description"
										modalHelperPage="../spellcheck/RapidSpellModalHelper.html"
										showNoErrorsMessage="False" showFinishedMessage="False"
										includeUserDictionaryInSuggestions="True"
										allowAnyCase="True" allowMixedCase="True"
										finishedListener="spellFin" 
										windowWidth="570" windowHeight="300"
										modal="False" showButton="False"
										windowX="-1" warnDuplicates="False"
										textComponentInterface="Custom">
									</RapidSpellWeb:rapidSpellWebLauncher>
                                    <TR valign="top">
										<TD width="10"></TD>
										<TD width="122"><h:outputText value="Effective Date*"
											styleClass="#{productGeneralInformationBackingBean.effectiveDateInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD width="184"><h:inputText styleClass="formInputField"
											id="effectiveDate_txt" maxlength="10"
											value="#{productGeneralInformationBackingBean.effectiveDate}"
											tabindex="9" ;/> <span class="dateFormat">(mm/dd/yyyy)</span></TD>
										<TD valign="top" width="24"><A href="#"
											onclick="cal1.select('productForm:effectiveDate_txt','anchor1','MM/dd/yyyy'); return false;"
											name="anchor1" id="anchor1"
											title="cal1.select('productForm:effectiveDate_txt','anchor1','MM/dd/yyyy'); return false;">
										<h:commandButton image="../../images/cal.gif"
											style="cursor: hand" alt="Cal" tabindex="10" /> </A></TD>
									</TR>
									<TR valign="top">
										<TD width="10"></TD>
										<TD width="122"><h:outputText value="Expiry Date*"
											styleClass="#{productGeneralInformationBackingBean.expiryDateInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD width="184"><h:inputText styleClass="formInputField"
											id="expiryDate_txt" maxlength="10" tabindex="11"
											value="#{productGeneralInformationBackingBean.expiryDate}" />
										<span class="dateFormat">(mm/dd/yyyy)</span></TD>
										<TD valign="top" width="24"><A href="#"
											onclick="cal1.select('productForm:expiryDate_txt','anchor2','MM/dd/yyyy'); return false;"
											name="anchor2" id="anchor2"
											title="cal1.select(document.forms[0].productForm:expiryDate_txt,'anchor2','MM/dd/yyyy'); return false;">
										<h:commandButton image="../../images/cal.gif"
											style="cursor: hand" alt="Cal" tabindex="12" /> </A></TD>
									</TR>
									<TR valign="top">
										<TD width="10"></TD>
										<TD width="122"><h:outputText id="productStructure"
											value="Product Structure*"
											styleClass="#{productGeneralInformationBackingBean.structureInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<h:inputHidden id="productStructHidden"
											value="#{productGeneralInformationBackingBean.productStructDiv}"></h:inputHidden>
										<TD width="184">
										<div id="productStructDiv" class="selectDataDisplayDiv"></div>

										</TD>
										<TD width="24"><h:commandButton alt="productStructure"
											id="productStructureButton" image="../../images/select.gif"
											onclick="productStructurePopup();
																return false;"
											tabindex="13">
										</h:commandButton></TD>
									</TR>
									<TR valign="top">
										<TD width="10"></TD>
										<TD width="150"><h:outputText value="Product Structure Version" /></TD>
										<TD width="184"><h:outputText id="prdstruct_versionId"
											value="#{productGeneralInformationBackingBean.productStructureVersion}" />
										<h:inputHidden id="productStructureVersionByHidden"
											value="#{productGeneralInformationBackingBean.productStructureVersion}">
										</h:inputHidden></TD>

										<TD width="24"><f:verbatim /></TD>
									</TR>
									<TR valign="top">
										<TD width="10"></TD>
										<TD width="122">
										<h:outputText id="product_Family"
											value="Product Family*"
											styleClass="#{productGeneralInformationBackingBean.familyInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD width="184"><h:outputText id="prdstruct_Family"
											value="#{productGeneralInformationBackingBean.productFamily}" />
										<h:inputHidden id="prdstruct_FamilyByHidden"
											value="#{productGeneralInformationBackingBean.productFamily}"></h:inputHidden>
										</TD>
										<TD width="40"></TD>
									</TR>
									<TR valign="top">
										<TD width="10"></TD>
										<TD width="122"><h:outputText value="Created By" /></TD>
										<TD width="184"><h:outputText id="createdBy_optxt"
											value="#{productGeneralInformationBackingBean.createdBy}" />
										<h:inputHidden id="createdByHidden"
											value="#{productGeneralInformationBackingBean.createdBy}">
										</h:inputHidden></TD>

										<TD width="24"><f:verbatim /></TD>
									</TR>
									<TR valign="top">
										<TD width="10"></TD>
										<TD width="122"><h:outputText value="Created Date" /></TD>
										<TD width="184"><h:outputText id="creationDate_optxt"
											value="#{productGeneralInformationBackingBean.creationDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
										<h:inputHidden id="creationDateHidden"
											value="#{productGeneralInformationBackingBean.creationDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:inputHidden> <h:inputHidden id="time"
											value="#{requestScope.timezone}">
										</h:inputHidden></TD>

										<TD width="24"><f:verbatim /></TD>
									</TR>
									<TR valign="top">
										<TD width="10"></TD>
										<TD width="122"><h:outputText value="Last Updated By" /></TD>
										<TD width="184"><h:outputText id="updatedBy_optxt"
											value="#{productGeneralInformationBackingBean.updatedBy}" />
										<h:inputHidden id="updatedByHidden"
											value="#{productGeneralInformationBackingBean.updatedBy}">
										</h:inputHidden></TD>
										<TD width="24"><f:verbatim /></TD>
									</TR>
									<TR valign="top">
										<TD width="10"></TD>
										<TD width="122"><h:outputText value="Last Updated Date" /></TD>
										<TD width="184"><h:outputText id="updationDate_optxt"
											value="#{productGeneralInformationBackingBean.updationDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
										<h:inputHidden id="updationDateHidden"
											value="#{productGeneralInformationBackingBean.updationDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:inputHidden></TD>
										<TD width="24"><f:verbatim /></TD>
									</TR>
									
									<TR valign="top">
										<TD width="10"></TD>
										<TD width="122"><h:commandButton value="Save" onmousedown="javascript:savePageAction(this.id);"
											styleClass="wpdButton" id="saveButton"
											onclick="return runSpellCheck();"
											tabindex="14">
										</h:commandButton></TD>
										<TD width="184"><f:verbatim /></TD>
										<TD width="24"><f:verbatim /></TD>
									</TR>
								</TBODY>
							</TABLE>
							</FIELDSET>
							<BR>
							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText" width="100%">
								<TBODY>
									<tr>
										<td width="4%">&nbsp;&nbsp;<h:selectBooleanCheckbox
											id="checkall" 
											value="#{productGeneralInformationBackingBean.checkin}" 
											tabindex="15">
										</h:selectBooleanCheckbox></td>
										<td align="left"><h:outputText value="Check In" /></td>
										<td align="left" width="127">
										<table Width=100%>
											<tr>
												<td><h:outputText value="State" /></td>
												<TD>:</TD>
												<td><h:outputText
													value="#{productGeneralInformationBackingBean.state}" /></td>
												<h:inputHidden id="stateHidden"
													value="#{productGeneralInformationBackingBean.state}"></h:inputHidden>

											</tr>
											<tr>
												<td><h:outputText value="Status" /></td>
												<TD>:</TD>
												<td><h:outputText
													value="#{productGeneralInformationBackingBean.status}" />
												<h:inputHidden id="statusHidden"
													value="#{productGeneralInformationBackingBean.status}"></h:inputHidden>
												</td>
											</tr>
											<tr>
												<td><h:outputText value="Version" /></td>
												<TD>:</TD>
												<td><h:outputText id="versiontype"
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
							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText" width="100%">
								<TBODY>
									<tr>
										<td width="4%"><f:verbatim /></td>
										<td width="6%"><h:commandButton value="Done"
											styleClass="wpdButton" id="doneButton" tabindex="16"
											onclick="return done();">
										</h:commandButton></td>
										<td width="90%"><f:verbatim /></td>
									</tr>
								</TBODY>
							</TABLE>

							<!--	End of Page data	--></TD>
						</TR>
					</TABLE>

					<!-- Space for hidden fields -->
					<h:inputHidden id="hidden1"
						value="#{productGeneralInformationBackingBean.productKey}"></h:inputHidden>
					<h:inputHidden id="hiddenProductType"
						value="#{productGeneralInformationBackingBean.hiddenProductType}"></h:inputHidden>
					<h:inputHidden id="hasValErrors"
													value="#{productGeneralInformationBackingBean.hasValidationErrors}"></h:inputHidden>
					<h:inputHidden id="duplicateData"
					value="#{productGeneralInformationBackingBean.duplicateData}"></h:inputHidden>
					<h:commandLink id="saveLink"
						style="display:none; visibility: hidden;"
						action="#{productGeneralInformationBackingBean.saveEdited}">
						<f:verbatim />
					</h:commandLink>
					<h:commandLink id="doneLink"
						style="display:none; visibility: hidden;"
						action="#{productGeneralInformationBackingBean.done}">
						<f:verbatim />
					</h:commandLink>
					<!-- End of hidden fields  -->

				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="productGeneralInformation" /></form>

<script language="javascript">
    IGNORED_FIELD1='productForm:duplicateData';
	var i;
	var obj;
	i = document.getElementById("productForm:productType").innerHTML;
	var version=document.getElementById("productForm:versiontype").innerHTML;
	// i= obj.value;
			if(i== "MANDATE")
			{
				if(version == 0){
					sub1.style.display='';	
				}else{
					i=document.getElementById("productForm:mandateType1").innerHTML
				if(i == "ST"){
				document.getElementById("productForm:mandateType1").value="State";
				}else if( i == "ET"){
				document.getElementById("productForm:mandateType1").value="ET";
				}else{
				document.getElementById("productForm:mandateType1").value="Federal";
				}
				sub3.style.display='';	
				}	
if(i== 'ET' || i=='FED'||i=='ST')
{

sub4.style.display='';
}else{
sub4.style.display='none';
}
		}else 
		{
			sub1.style.display='none';
			sub2.style.display='none';
			sub4.style.display='none';
			sub5.style.display='none';
		}
	var j;
	var obj;
	obj = document.getElementById("productForm:mandateType");
	j= obj.value;
	if(i == 'MANDATE'){
		if(j== 'ET' || j=='ST')
		{
			if(version == 0){
				sub2.style.display='';	
			}else{

			sub4.style.display='';
			}
		}else if(j== 'FED')
		{
			if(version == 0){
				sub5.style.display='';	
			}else{

			sub4.style.display='';
			}
		}else 
		{
			sub2.style.display='none';
			sub4.style.display='none';
		}
	}
function getProductType()
	{
	var i;
	var obj;
	obj = document.getElementById("productForm:productType");
	i= obj.selectedIndex;
		if(i== 1)
		{
		sub1.style.display='';		
		}
		else 
		{
		sub1.style.display='none';
		sub2.style.display='none';
		}
	}

function getMandateType()
	{
	var i;
	var obj;
	obj = document.getElementById("productForm:mandateType");
	i= obj.selectedIndex;
		if(i== '1' || i=='3')
		{
		sub2.style.display='';	
		sub5.style.display='none';	
		}
		else 
		{
		sub2.style.display='none';
		sub5.style.display='';
		}
document.getElementById("productForm:hid_state").value = "";
document.getElementById("productForm:productStructHidden").value = "";
copyHiddenToDiv_ewpd('productForm:productStructHidden','productStructDiv',2,1); 
copyHiddenToDiv_ewpd('productForm:hid_state','stateDiv',2,1); 
	}

 copyHiddenToDiv_ewpd('productForm:lineOfBusinessHidden','lineOfBusinessDiv',2,2); 
 copyHiddenToDiv_ewpd('productForm:businessEntityHidden','businessEntityDiv',2,2); 
 copyHiddenToDiv_ewpd('productForm:businessGroupHidden','businessGroupDiv',2,2); 
 copyHiddenToDiv_ewpd('productForm:marketBusinessUnitHidden','marketBusinessUnitDiv',2,2); 
 //copyHiddenToDiv_ewpd('productForm:productFamHidden','productFamilyDiv',2,2);
 copyHiddenToDiv_ewpd('productForm:productStructHidden','productStructDiv',2,1);  
 function productStructurePopup(){
	ewpdModalWindow_ewpd(getProdStrPopupUrl('productForm:lineOfBusinessHidden','productForm:businessEntityHidden','productForm:businessGroupHidden','productForm:marketBusinessUnitHidden',
									 		'productForm:effectiveDate_txt','productForm:expiryDate_txt','productForm:productTypeHidden','productForm:mandateType','productForm:hid_state','../product/productStructurePopup.jsp'+getUrl()+'?'),
						'productStructDiv','productForm:productStructHidden',2,1);
 }
function prodcutStructClean(){
document.getElementById("productForm:productStructHidden").value = "";
copyHiddenToDiv_ewpd('productForm:productStructHidden','productStructDiv',2,1); 
}
i = document.getElementById("productForm:productType").innerHTML;
if(i=='MANDATE')
{
tab4.style.display='none';
tab3.style.display='none';
}else{
tab3.style.display='';
tab4.style.display='';
}
function copyToSub4(){

	var stateVal = document.getElementById("productForm:hid_state").value;
	document.getElementById("productForm:hid_state1").value = stateVal;
	document.getElementById("productForm:stateCde1").value = stateVal;
}
appendToRefDataVariablesSelectedRefDataName('hid_state', 'stateDiv');
//appendToRefDataVariablesSelectedRefDataName('productFamHidden', 'productFamilyDiv');

if(document.getElementById('productForm:hasValErrors').value == 'true'){
	var url='../adminMethods/adminMethodValidationIntermediatePage.jsp'+getUrl()+'?temp='+Math.random();
	var returnvalue=window.showModalDialog(url,"","dialogHeight:800px;dialogWidth:1200px;resizable=true;status=no;");
	document.getElementById('productForm:hasValErrors').value = 'false';
}
var doneFlag = false;

function done(){
	doneFlag = true;
	return runSpellCheck();   
}

function runSpellCheck(){
	var version = document.getElementById('productForm:versionHidden').value;
	if(version<1)
			var rswlCntrls = ["rapidSpellWebLauncher1","rapidSpellWebLauncher2"];
		else
			var rswlCntrls = ["rapidSpellWebLauncher2"];	
	var i=0;
	for(var i=0; i<rswlCntrls.length; i++){
		eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
	}
	return false;
}
function spellFin(cancelled){

		if(doneFlag)
			document.getElementById('productForm:doneLink').click();	
		else{ 
			document.getElementById('productForm:saveLink').click();
		}			

	doneFlag = false;
}
</script>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('productForm:duplicateData').value == '')
		document.getElementById('productForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
	else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('productForm:duplicateData').value;
</script>
</HTML>
