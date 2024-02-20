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

<TITLE>Edit Product Structure</TITLE>
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
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
</HEAD>
<f:view>
	<BODY
		onkeypress="return submitOnEnterKey('productStructureGeneralInfoForm:saveButton')">
	<TABLE width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD><jsp:include page="../navigation/top_Product_Print.jsp"></jsp:include></TD>
		</tr>
		<TR>
			<TD><h:form styleClass="form" id="productStructureGeneralInfoForm">
				<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD valign="top" class="leftPanel" width="305">

						<div class="treeDiv" style="height:600"><jsp:include
							page="../productStructure/productStructureTree.jsp" /></div>
						</TD>

						<TD colspan="2" valign="top" class="ContentArea" width="700">
						<TABLE>
							<TBODY>
								<TR>
									<TD><w:message
										value="#{productStructureGeneralInfoBackingBean.validationMessages}"></w:message>
									</TD>
								</TR>
							</TBODY>
						</TABLE>

						<TABLE width="200" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<TR>
								<TD width="100%">
								<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
									<TR>
										<TD width="2" align="left"><IMG
											src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
										<TD width="186" class="tabActive"><h:outputText
											value=" General Information" /></TD>
										<TD width="2" align="right"><IMG
											src="../../images/activeTabRight.gif" width="2" height="21"></TD>
									</TR>
								</TABLE>
								</TD>
								<TD width="100%">
								<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
									<TR>
										<TD width="3" align="left"><IMG
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21"></TD>
										<TD class="tabNormal"><h:commandLink
											onmousedown="return false;"
											action="#{productStructureBenefitComponentBackingBean.load}"
											id="comId"
											onmousedown="javascript:navigatePageAction(this.id);"
											id="thisId">
											<h:outputText value="Benefit Component" />
										</h:commandLink></TD>
										<TD width="2" align="right"><IMG
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21"></TD>
									</TR>
								</TABLE>
								</TD>
								<TD width="200"></TD>
							</TR>
						</TABLE>
						<FIELDSET
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText">
							<TBODY>
								<TR>
									<TD colspan="5">
									<FIELDSET
										style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:65%">
									<LEGEND> <FONT color="black">Business Domain</FONT></LEGEND>
									<TABLE border="0" cellspacing="0" cellpadding="3">

										<TR>
											<TD width="125"><h:outputText
												styleClass="#{productStructureGeneralInfoBackingBean.requiredLob ? 'mandatoryError': 'mandatoryNormal'}"
												value="Line of Business*" /></TD>
											<h:inputHidden id="hiddenLob"
												value="#{productStructureGeneralInfoBackingBean.lob}"></h:inputHidden>
											<TD width="180">
											<DIV id="divForLob" class="selectDataDisplayDiv"></DIV>
											<SCRIPT> //parseForDiv(document.getElementById('divForLob'), document.getElementById('productStructureGeneralInfoForm:hiddenLob')); </SCRIPT>
											<TD width="5"><h:commandButton alt="Select"
												id="lineofBusinessSelect" image="../../images/select.gif"
												rendered="#{productStructureGeneralInfoBackingBean.status != 'CHECKED_OUT'}"
												onclick="ewpdModalWindow_ewpd('../popups/lineOfBusinessPopup.jsp?lookUpAction=' + actionOne + '&parentCatalog='+lobCatalog, 'divForLob', 'productStructureGeneralInfoForm:hiddenLob',2,2,'');return false;"
												tabindex="1">
											</h:commandButton></TD>
										</TR>
										<TR>
											<TD width="125"><h:outputText
												styleClass="#{productStructureGeneralInfoBackingBean.requiredEntity ? 'mandatoryError': 'mandatoryNormal'}"
												value="Business Entity*" /></TD>
											<h:inputHidden id="hiddenEntity"
												value="#{productStructureGeneralInfoBackingBean.entity}"></h:inputHidden>
											<TD width="180">
											<DIV id="divForEntity" class="selectDataDisplayDiv"></DIV>
											<SCRIPT> //parseForDiv(document.getElementById('divForEntity'), document.getElementById('productStructureGeneralInfoForm:hiddenEntity')); </SCRIPT>
											<TD width="5"><h:commandButton alt="Select" id="entitySelect"
												image="../../images/select.gif"
												rendered="#{productStructureGeneralInfoBackingBean.status != 'CHECKED_OUT'}"
												onclick="ewpdModalWindow_ewpd('../popups/businessEntityPopup.jsp?lookUpAction=' + actionOne + '&parentCatalog='+beCatalog, 'divForEntity', 'productStructureGeneralInfoForm:hiddenEntity',2,2,'');return false;"
												tabindex="2">
											</h:commandButton></TD>
										</TR>
										<TR>
											<TD width="125"><h:outputText
												styleClass="#{productStructureGeneralInfoBackingBean.requiredGroup? 'mandatoryError': 'mandatoryNormal'}"
												value="Business Group*" /></TD>
											<h:inputHidden id="hiddenGroup"
												value="#{productStructureGeneralInfoBackingBean.group}"></h:inputHidden>
											<TD width="180">
											<DIV id="divForGroup" class="selectDataDisplayDiv"></DIV>
											<SCRIPT> //parseForDiv(document.getElementById('divForGroup'), document.getElementById('productStructureGeneralInfoForm:hiddenGroup')); </SCRIPT>
											<TD width="5"><h:commandButton alt="Select" id="groupSelect"
												image="../../images/select.gif"
												rendered="#{productStructureGeneralInfoBackingBean.status != 'CHECKED_OUT'}"
												onclick="ewpdModalWindow_ewpd('../popups/businessGroupPopup.jsp?lookUpAction=' + actionOne + '&parentCatalog='+bgCatalog, 'divForGroup', 'productStructureGeneralInfoForm:hiddenGroup',2,2,'');return false;"
												tabindex="3">
											</h:commandButton></TD>
										</TR>
										<!-- CARS START -->
										<TR>
											<TD width="125"><h:outputText
												styleClass="#{productStructureGeneralInfoBackingBean.requiredBusinessUnit? 'mandatoryError': 'mandatoryNormal'}"
												value="Market Business Unit*" /></TD>
											<h:inputHidden id="hiddenMarketBusinessUnit"
												value="#{productStructureGeneralInfoBackingBean.marketBusinessUnit}"></h:inputHidden>
											<TD width="180">
											<DIV id="divForMarketBusinessUnit" class="selectDataDisplayDiv"></DIV>
											<TD width="5"><h:commandButton alt="Select" id="businessUnitSelect"
												image="../../images/select.gif"
												rendered="#{productStructureGeneralInfoBackingBean.status != 'CHECKED_OUT'}"
												onclick="ewpdModalWindow_ewpd('../popups/marketBusinessUnit.jsp?lookUpAction=' + actionOne + '&parentCatalog='+mbuCatalog, 'divForMarketBusinessUnit', 'productStructureGeneralInfoForm:hiddenMarketBusinessUnit',2,2,'');return false;"
												tabindex="3">
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
								<TR>
									<TD width="5"></TD>
									<TD width="125"><h:outputText
										styleClass="#{productStructureGeneralInfoBackingBean.requiredName? 'mandatoryError': 'mandatoryNormal'}"
										value="Name* " /></TD>
									<TD width="179"><h:inputHidden id="name_Hidden"
										value="#{productStructureGeneralInfoBackingBean.name}" />
										<h:inputText styleClass="formInputField"
										id="structureName"
										value="#{productStructureGeneralInfoBackingBean.name}"
										maxlength="30"
										readonly="#{productStructureGeneralInfoBackingBean.status == 'CHECKED_OUT'}"
										tabindex="4" /> 
								</TR>
								
									<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher1"
										textComponentName="productStructureGeneralInfoForm:structureName"
										rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Product Structure Name"
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

								<TD width="5"></TD>
									<TD width="125" valign="top"><h:outputText
										styleClass="#{productStructureGeneralInfoBackingBean.familyInvalid ? 'mandatoryError': 'mandatoryNormal'}" 
										value="Product Family*" /><h:inputHidden id="productFamHidden"
											value="#{productStructureGeneralInfoBackingBean.productFamily}"></h:inputHidden></TD>
									<TD width="179"><div id="productFamilyDiv" class="selectDataDisplayDiv"></TD>
									<TD valign="top" width="230"><h:commandButton alt="productFamily" rendered="#{productStructureGeneralInfoBackingBean.status != 'CHECKED_OUT'}"
											id="productFamilyButton"  image="../../images/select.gif" 
											onclick="getSelectedDomainReferenceData('../product/productFamilyPopup.jsp','productStructureGeneralInfoForm','','hiddenLob','hiddenEntity','hiddenGroup','productFamilyDiv','productStructureGeneralInfoForm:productFamHidden',2,2, 'Product Family');
																				return false;"
											tabindex="5">
										</h:commandButton></TD>

									</TR>
								<TR>
									<TD width="5"></TD>
									<TD width="125"><h:outputText value="Structure Type " /></TD>
									<TD width="179"><h:outputText id="strType"
										value="#{productStructureGeneralInfoBackingBean.structureType}" />
									<h:inputHidden id="str_Hidden"
										value="#{productStructureGeneralInfoBackingBean.structureType}" />
								</TR>

								<tr id="sub1" style="display:none;">
									<TD width="6" height="30"></TD>
									<TD height="9" width="112"><h:outputText id="mandType"
										value="Mandate Type*"
										styleClass="#{productStructureGeneralInfoBackingBean.requiredMandateType ? 'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<TD height="9" width="179"><h:selectOneMenu id="mandateType"
										styleClass="formInputField" tabindex="5"
										value="#{productStructureGeneralInfoBackingBean.mandateType}"
										onchange="getMandateType('true')">
										<f:selectItems id="mandateTypeList"
											value="#{ReferenceDataBackingBeanCommon.mandateTypeListForCombo}" />
									</h:selectOneMenu></TD>
								</TR>

								<tr id="sub2" style="display:none;">
									<TD width="6" height="30"></TD>
									<TD height="9" width="112"><h:outputText id="stateCde"
										value="Jurisdiction*"
										styleClass="#{productStructureGeneralInfoBackingBean.requiredState ? 'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<h:inputHidden id="hid_state"
										value="#{productStructureGeneralInfoBackingBean.stateCode}"></h:inputHidden>
									<TD width="179">
									<div id="stateDiv" class="selectDataDisplayDiv"></div>
									<SCRIPT> //parseForDiv(document.getElementById('stateDiv'), document.getElementById('productStructureGeneralInfoForm:hid_state')); </SCRIPT>
									</TD>
									<TD width="40"><h:commandButton alt="Jurisdiction" id="stateButton"
										image="../../images/select.gif"
										onclick="getSelectedDomainReferenceData('../contractpopups/HeadQuarterstatePopup.jsp', 'productStructureGeneralInfoForm',
													'hiddenLob', 'hiddenEntity', 'hiddenGroup','stateDiv','productStructureGeneralInfoForm:hid_state', 2, 1, 'State Code');setRefDataUseFlag('productStructureGeneralInfoForm', 'hid_state', 'stateDiv');return false;"
										tabindex="6">
									</h:commandButton></TD>
								</TR>
								<tr id="sub3" style="display:none;">
									<TD width="6"></TD>
									<TD width="112"><h:outputText value="Mandate Type" /></TD>
									<TD width="179"><h:inputTextarea styleClass="selectDivReadOnly"
										id="mandType1" readonly="true" style="border:0"
										value="#{productStructureGeneralInfoBackingBean.mandateType}"></h:inputTextarea><h:inputHidden
										id="mandTypeHidden"
										value="#{productStructureGeneralInfoBackingBean.mandateType}">
									</h:inputHidden><SCRIPT>copyHiddenToInputText('productStructureGeneralInfoForm:mandTypeHidden','productStructureGeneralInfoForm:mandType1',1);</SCRIPT>
									</TD>
								</TR>
								<tr id="sub4" style="display:none;">
									<TD width="6"></TD>
									<TD width="122"><h:outputText value="Jurisdiction" /></TD>
									<TD width="179"><h:inputTextarea styleClass="selectDivReadOnly"
										id="stateCde1"
										value="#{productStructureGeneralInfoBackingBean.stateCode}"
										readonly="true" style="border:0"></h:inputTextarea> <h:inputHidden
										id="stateCdeHidden"
										value="#{productStructureGeneralInfoBackingBean.stateCode}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureGeneralInfoForm:stateCdeHidden','productStructureGeneralInfoForm:stateCde1',2); </SCRIPT>
									</TD>
								</TR>
								<tr id="sub5" style="display:none;">
									<TD width="6"></TD>
									<TD width="112"><h:outputText value="Jurisdiction" /></TD>
									<TD width="179"><h:inputTextarea styleClass="selectDivReadOnly"
										id="stateCdeFed" value="ALL" readonly="true" style="border:0"></h:inputTextarea>
									</TD>
								</TR>

								

								<TR>
									<TD width="5"></TD>
									<TD width="125" valign="top"><h:outputText
										styleClass="#{productStructureGeneralInfoBackingBean.requiredDesc? 'mandatoryError': 'mandatoryNormal'}"
										value="Description*" /></TD>
									<TD width="179"><h:inputTextarea
										styleClass="formTxtAreaField_GeneralDesc" id="structureDesc"
										value="#{productStructureGeneralInfoBackingBean.description}"
										tabindex="7">
									</h:inputTextarea></TD>
								</TR>

								<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher2"
										textComponentName="productStructureGeneralInfoForm:structureDesc"
										rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Product Structure Description"
										modalHelperPage="../spellcheck/RapidSpellModalHelper.html"
										showNoErrorsMessage="False" showFinishedMessage="False"
										includeUserDictionaryInSuggestions="True"
										allowAnyCase="False" allowMixedCase="True"
										finishedListener="spellFin" 
										windowWidth="570" windowHeight="300"
										modal="False" showButton="False"
										windowX="-1" warnDuplicates="False"
										textComponentInterface="Custom">
									</RapidSpellWeb:rapidSpellWebLauncher>

                                <TR>
									<TD width="5"></TD>
									<TD width="125" valign="top"><h:outputText
										styleClass="#{productStructureGeneralInfoBackingBean.requiredEffectiveDate? 'mandatoryError': 'mandatoryNormal'}"
										value="Effective Date*" /></TD>
									<TD width="179"><h:inputText styleClass="formInputField"
										id="effectiveDate"
										value="#{productStructureGeneralInfoBackingBean.effectiveDate}"
										maxlength="10" tabindex="8" /> <SPAN class="dateFormat">(mm/dd/yyyy)</SPAN></TD>
									<TD valign="top" width="230"><A href="#"
										onclick="cal1.select('productStructureGeneralInfoForm:effectiveDate','anchor1','MM/dd/yyyy'); return false;"
										name="anchor1" id="anchor1"
										title="cal1.select('productStructureGeneralInfoForm:effectiveDate','anchor1','MM/dd/yyyy'); return false;">
									<h:commandButton image="../../images/cal.gif"
										style="cursor: hand" alt="Cal" tabindex="9" /> </A></TD>
								</TR>
								<TR>
									<TD width="5"></TD>
									<TD width="125" valign="top"><h:outputText
										styleClass="#{productStructureGeneralInfoBackingBean.requiredExpiryDate? 'mandatoryError': 'mandatoryNormal'}"
										value="Expiry Date*" /></TD>
									<TD width="179"><h:inputText styleClass="formInputField"
										id="expiryDate"
										value="#{productStructureGeneralInfoBackingBean.expiryDate}"
										maxlength="10" tabindex="10" /> <SPAN class="dateFormat">(mm/dd/yyyy)</SPAN></TD>
									<TD valign="top" width="230"><A href="#"
										onclick="cal1.select('productStructureGeneralInfoForm:expiryDate','anchor2','MM/dd/yyyy'); return false;"
										name="anchor2" id="anchor2"
										title="cal1.select('productStructureGeneralInfoForm:expiryDate,'anchor2','MM/dd/yyyy'); return false;">
									<h:commandButton image="../../images/cal.gif"
										style="cursor: hand" alt="Cal" tabindex="11" /> </A></TD>
								</TR>
								<TR>
									<TD width="5"></TD>
									<TD width="125"><h:outputText value="Created By"
										id="labelCreatedUser" /></TD>
									<TD width="179"><h:outputText id="createdUser"
										value="#{productStructureGeneralInfoBackingBean.createdUser}" />
									<h:inputHidden id="createdUserHidden"
										value="#{productStructureGeneralInfoBackingBean.createdUser}" />
									</TD>

								</TR>
								<TR>
									<TD width="5"></TD>
									<TD width="125"><h:outputText value="Created Date" /></TD>
									<TD width="179"><h:outputText id="createdDate"
										value="#{productStructureGeneralInfoBackingBean.creationDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="createdDateHidden"
										value="#{productStructureGeneralInfoBackingBean.creationDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden> <h:inputHidden id="time"
										value="#{requestScope.timezone}">
									</h:inputHidden></TD>

								</TR>
								<TR>
									<TD width="5"></TD>
									<TD width="125"><h:outputText value="Last Updated By" /></TD>
									<TD width="179"><h:outputText id="updatedUser"
										value="#{productStructureGeneralInfoBackingBean.lastUpdatedUser}" />
									<h:inputHidden id="updatedUserHidden"
										value="#{productStructureGeneralInfoBackingBean.lastUpdatedUser}" />
									</TD>
								</TR>
								<TR>
									<TD width="5"></TD>
									<TD height="20" width="125"><h:outputText
										value="Last Updated Date" /></TD>
									<TD width="179"><h:outputText id="updationDate"
										value="#{productStructureGeneralInfoBackingBean.lastUpdatedDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="updationDateHidden"
										value="#{productStructureGeneralInfoBackingBean.lastUpdatedDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden></TD>
								</TR>
								<TR>
									<TD width="5"></TD>
									<TD width="125"><h:commandButton value="Save"
										styleClass="wpdButton" id="saveButton" onmousedown="javascript:savePageAction(this.id);"
										onclick="setHiddenValues('productStructureGeneralInfoForm:saveLink'); return false;"
										tabindex="12">
									</h:commandButton></TD>
									<TD width="179">&nbsp;</TD>
								</TR>
							</TBODY>
						</TABLE>
						</FIELDSET>
						</br>
						<FIELDSET
							style="margin-left:6px;margin-right:-6px;margin-top:6px;padding-bottom:1px;padding-top:1px;width:100%">
						<TABLE border="0" cellspacing="0" cellpadding="3" width="100%">
							<TR>
								<TD width="20%"><h:selectBooleanCheckbox id="checkIn"
									
									value="#{productStructureGeneralInfoBackingBean.checkIn}"
									rendered="#{productStructureGeneralInfoBackingBean.checkout!= true}"
									readonly="#{productStructureGeneralInfoBackingBean.checkout}"
									tabindex="13">
								</h:selectBooleanCheckbox><h:outputText value="Check In"
									rendered="#{productStructureGeneralInfoBackingBean.checkout!= true}" /></TD>
								<TD align="right">
								<TABLE>
									<TR>
										<TD>State</TD>
										<TD>:&nbsp;<h:outputText id="state"
											value="#{productStructureGeneralInfoBackingBean.state}" /> <h:inputHidden
											id="stateHidden"
											value="#{productStructureGeneralInfoBackingBean.state}" /></td>
									</TR>
									<TR>
										<TD>Status</TD>
										<TD>:&nbsp;<h:outputText id="status"
											value="#{productStructureGeneralInfoBackingBean.status}" />
										<h:inputHidden id="statusHidden"
											value="#{productStructureGeneralInfoBackingBean.status}" />
										</TD>
									</TR>
									<TR>
										<TD>Version</TD>
										<TD>:&nbsp;<h:outputText id="version"
											value="#{productStructureGeneralInfoBackingBean.version}" />
										<h:inputHidden id="versionHidden"
											value="#{productStructureGeneralInfoBackingBean.version}" />
										</TD>
									</TR>
								</TABLE>
								</TD>
							</TR>
						</TABLE>
						</FIELDSET>
						<TABLE>
							<TBODY>
								<TR>
									<td width="8"></td>
									<TD>&nbsp;&nbsp;<h:commandButton styleClass="wpdbutton" value="Done"
										id="doneButton"
										onclick="setHiddenValues('productStructureGeneralInfoForm:doneLink'); return false;"
										rendered="#{productStructureGeneralInfoBackingBean.checkout!= true}"
										tabindex="14">
									</h:commandButton></TD>
								</TR>
							</TBODY>
						</TABLE>
						</TD>
					</TR>
				</TABLE>
				<h:inputHidden id="selectedMandate"
					value="#{productStructureGeneralInfoBackingBean.mandateType}" />
				<h:inputHidden id="selectedState"
					value="#{productStructureGeneralInfoBackingBean.stateCode}" />
				<h:inputHidden id="oldLobHid"
					value="#{productStructureGeneralInfoBackingBean.oldLob}" />
				<h:inputHidden id="oldEntityHid"
					value="#{productStructureGeneralInfoBackingBean.oldEntity}" />
				<h:inputHidden id="oldGroupHid"
					value="#{productStructureGeneralInfoBackingBean.oldGroup}" />
				<h:inputHidden id="oldEffectiveDateHid"
					value="#{productStructureGeneralInfoBackingBean.oldEffectiveDate}" />
				<h:inputHidden id="oldExpiryDateHid"
					value="#{productStructureGeneralInfoBackingBean.oldExpiryDate}" />
				<h:inputHidden id="errorFlagForNullState"
					value="#{productStructureGeneralInfoBackingBean.errorFlagForNullState}" />
				<h:inputHidden id="oldMandate"
					value="#{productStructureGeneralInfoBackingBean.oldMandateType}"></h:inputHidden>
				<h:inputHidden id="oldState"
					value="#{productStructureGeneralInfoBackingBean.oldStateCode}"></h:inputHidden>
				<h:inputHidden id="domainChanged"
					value="#{productStructureGeneralInfoBackingBean.domainChange}"></h:inputHidden>
				<h:inputHidden id="dateChanged"
					value="#{productStructureGeneralInfoBackingBean.dateChange}"></h:inputHidden>
				<h:inputHidden id="hasValErrors"
					value="#{productStructureGeneralInfoBackingBean.hasValidationErrors}"></h:inputHidden>
				<h:inputHidden id="oldMarketBusinessUnitHid"
						value="#{productStructureGeneralInfoBackingBean.oldMarketBusinessUnit}" />	
				<h:commandLink id="saveLink"
					style="display:none; visibility: hidden;"
					action="#{productStructureGeneralInfoBackingBean.saveGeneralInfo}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="doneLink"
					style="display:none; visibility: hidden;"
					action="#{productStructureGeneralInfoBackingBean.checkInGenralInfo}">
					<f:verbatim />
				</h:commandLink>
				<h:inputHidden id="duplicateData" value="#{productStructureGeneralInfoBackingBean.duplicateData}"/>
			</h:form></TD>
		</TR>
		<TR>
			<TD><%@ include file="../navigation/bottom.jsp"%></TD>
		</TR>
	</TABLE>
	</BODY>
</f:view>
<script language="JavaScript">
	IGNORED_FIELD1='productStructureGeneralInfoForm:duplicateData';
    copyHiddenToDiv_ewpd('productStructureGeneralInfoForm:hiddenLob','divForLob',2,2);
	copyHiddenToDiv_ewpd('productStructureGeneralInfoForm:hiddenEntity','divForEntity',2,2);
	copyHiddenToDiv_ewpd('productStructureGeneralInfoForm:hiddenGroup','divForGroup',2,2);
	copyHiddenToDiv_ewpd('productStructureGeneralInfoForm:hiddenMarketBusinessUnit','divForMarketBusinessUnit',2,2);
	copyHiddenToDiv_ewpd('productStructureGeneralInfoForm:productFamHidden','productFamilyDiv',2,2);
    
	 var cal1 = new CalendarPopup();
	 cal1.showYearNavigation();
	 copyHiddenToDiv_ewpd('productStructureGeneralInfoForm:hid_state','stateDiv',2,1); 

var version;
version = document.getElementById("productStructureGeneralInfoForm:version");
if(version.innerHTML==0)
{
getStructureType();
getMandateType('false');
}
else{
getStructureType1();
getMandateType1();
}
function getStructureType()
{
var i;
var obj;
obj = document.getElementById("productStructureGeneralInfoForm:strType");
i= obj.innerHTML;
	if(i=="Mandate")
	{
	sub1.style.display='';
	}
	
	else 
	{
	sub1.style.display='none';
	sub2.style.display='none';
	}

}
function getMandateType(change)
	{
	 copyHiddenToInputText('productStructureGeneralInfoForm:mandateType','productStructureGeneralInfoForm:selectedMandate',1);
	if(change=='true'){
		document.getElementById('productStructureGeneralInfoForm:hid_state').value = "";
		parseForDiv(document.getElementById('stateDiv'), document.getElementById('productStructureGeneralInfoForm:hid_state'));
		copyHiddenToDiv_ewpd('productStructureGeneralInfoForm:hid_state','stateDiv',2,1); 
		}
	var i;
	var obj;
	obj = document.getElementById("productStructureGeneralInfoForm:mandateType");
	i= obj.selectedIndex;
		if(i== 1 || i==3)
		{
		sub2.style.display='';	
		sub5.style.display='none';	
		}
		else if(i== 2)
		{
		sub5.style.display='';
		sub2.style.display='none';	
		} else{
		sub2.style.display='none';
		sub5.style.display='none';
		}
	}

function getStructureType1()
{
var i;
var obj;
obj = document.getElementById("productStructureGeneralInfoForm:strType");
i= obj.innerHTML;
	if(i=="Mandate")
	{
	sub3.style.display='';
	}
	
	else 
	{
	sub3.style.display='none';
	sub4.style.display='none';
	sub5.style.display='none';
	}

}
function getMandateType1()
{
var i;
var obj;
obj = document.getElementById("productStructureGeneralInfoForm:mandType1");
i= obj.value;
	if(i=="ET" || i == "ST")
	{
				if(i=="ST")

					document.getElementById("productStructureGeneralInfoForm:mandType1").value = "State";
				else
					document.getElementById("productStructureGeneralInfoForm:mandType1").value = "Extra-Territorial";
	sub4.style.display='';
	sub5.style.display='none';
	}
	
	else if(i == "FED") 
	{

	document.getElementById("productStructureGeneralInfoForm:mandType1").value = "Federal";
	sub5.style.display='';
	sub4.style.display='none';
	}
	else{
		sub4.style.display='none';
		sub5.style.display='none';
	}
	

}
var link;
function setHiddenValues(linkName){
link = linkName;
	if(version.innerHTML == 0){
		alertUser(linkName);		
		return true;
	}else{
		// copyHiddenToInputText('productStructureGeneralInfoForm:mandateType','productStructureGeneralInfoForm:hidMandate',1);
		copyHiddenToInputText('productStructureGeneralInfoForm:hid_state','productStructureGeneralInfoForm:selectedState',1);
		obj1 = document.getElementById("productStructureGeneralInfoForm:selectedState").value;
		var temp = document.getElementById("productStructureGeneralInfoForm:hid_state");
		copyHiddenToDiv_ewpd('productStructureGeneralInfoForm:hid_state','stateDiv',2,1); 
		alertUser(linkName);
		return true;
}
function alertUser(linkName){
			confirmFlagForAllValdn = false;
		confirmFlagForBDAndStateValdn = false;
		confirmFlagForBDAndDateValdn = false;
		confirmFlagForDateAndStateValdn = false;
		confirmFlagForBDValdn = false;
		confirmFlagForStateValdn = false;
		confirmFlagForDateValdn = false;
		confirmFlagForDomainChange = false;
		commonLinkFlag = true;
		// For Date Validation
		var oldEffectiveDate = document.getElementById('productStructureGeneralInfoForm:oldEffectiveDateHid').value;
		var oldExpiryDate = document.getElementById('productStructureGeneralInfoForm:oldExpiryDateHid').value;
		var currentEffectiveDate = document.getElementById('productStructureGeneralInfoForm:effectiveDate').value;
		var currentExpiryDate = document.getElementById('productStructureGeneralInfoForm:expiryDate').value;
	
		// Business Domian Validation 
		var oldLineOfBusinessCode = document.getElementById('productStructureGeneralInfoForm:oldLobHid').value;
		var oldBusinessEntityCode = document.getElementById('productStructureGeneralInfoForm:oldEntityHid').value;
		var oldBusinessGroupCode = document.getElementById('productStructureGeneralInfoForm:oldGroupHid').value;
		var newLineOfBusinessCode = document.getElementById('productStructureGeneralInfoForm:hiddenLob').value;
		var newBusinessEntityCode = document.getElementById('productStructureGeneralInfoForm:hiddenEntity').value;
		var newBusinessGroupCode = document.getElementById('productStructureGeneralInfoForm:hiddenGroup').value;
		var oldMarketBusinessUnitCode = document.getElementById('productStructureGeneralInfoForm:oldMarketBusinessUnitHid').value;
		var newMarketBusinessUnitCode = document.getElementById('productStructureGeneralInfoForm:hiddenMarketBusinessUnit').value;
		
		// State And MandateType Validation
		obj1 = document.getElementById("productStructureGeneralInfoForm:hid_state").innerHTML;
		document.getElementById("productStructureGeneralInfoForm:selectedState").value = 
						document.getElementById("productStructureGeneralInfoForm:hid_state").value;
	
		var temp = document.getElementById("productStructureGeneralInfoForm:hid_state");		
		copyHiddenToDiv_ewpd('productStructureGeneralInfoForm:hid_state','stateDiv',2,1); 
	
		var oldMandate = document.getElementById('productStructureGeneralInfoForm:oldMandate').value;		
		var oldState = document.getElementById('productStructureGeneralInfoForm:oldState').value;		
		var newMandate = document.getElementById('productStructureGeneralInfoForm:mandateType').value;		
		var newState = document.getElementById('productStructureGeneralInfoForm:hid_state').value;
		var strTypeObj = document.getElementById("productStructureGeneralInfoForm:strType");
		var strType = strTypeObj.innerHTML;
	 	
			if((newState == null || newState == '') &&  (newMandate == 'FED') && (strType != 'STANDARD')){
			document.getElementById('productStructureGeneralInfoForm:hid_state').value = "ALL";
			}
			var newState = document.getElementById('productStructureGeneralInfoForm:hid_state').value;
		// Case1: All Validations
		if((((oldMandate != newMandate) || (oldState != newState)) && (strType != 'STANDARD'))
			&& ((oldEffectiveDate != currentEffectiveDate) || (oldExpiryDate != currentExpiryDate)) 
			&& ((oldLineOfBusinessCode != newLineOfBusinessCode) || (oldBusinessEntityCode != newBusinessEntityCode) || (oldBusinessGroupCode != newBusinessGroupCode) || (oldMarketBusinessUnitCode != newMarketBusinessUnitCode))){
			 confirmFlagForAllValdn = true;	
			confirmFlagForDomainChange = true;		
	
		// Case 2: Validation For BD and State
		}else if(((oldLineOfBusinessCode != newLineOfBusinessCode) 
			 || (oldBusinessEntityCode != newBusinessEntityCode) || (oldBusinessGroupCode != newBusinessGroupCode) || (oldMarketBusinessUnitCode != newMarketBusinessUnitCode))
				&& ((oldMandate != newMandate) || (oldState != newState) && (strType != 'STANDARD')) 
				&&((oldEffectiveDate == currentEffectiveDate)|| (oldExpiryDate == currentExpiryDate)) ){
			 confirmFlagForBDAndStateValdn = true;	
			confirmFlagForDomainChange = true;			
	
		// Case 3: Validation For BD and Date
		}else if(((oldLineOfBusinessCode != newLineOfBusinessCode) 
			 || (oldBusinessEntityCode != newBusinessEntityCode) || (oldBusinessGroupCode != newBusinessGroupCode) || (oldMarketBusinessUnitCode != newMarketBusinessUnitCode))
			 && ((oldEffectiveDate != currentEffectiveDate)		 || (oldExpiryDate != currentExpiryDate)) 
			 && ((oldMandate == newMandate) || (oldState == newState) )){
			confirmFlagForBDAndDateValdn = true;
			confirmFlagForDomainChange = true;
	
		// Case 4: Validation For Date and State
		}else if(((oldEffectiveDate != currentEffectiveDate) || (oldExpiryDate != currentExpiryDate))
				 && ((oldMandate != newMandate) || (oldState != newState)&& (strType != 'STANDARD')) 
				 &&	((oldLineOfBusinessCode == newLineOfBusinessCode) || (oldBusinessEntityCode == newBusinessEntityCode) || (oldBusinessGroupCode == newBusinessGroupCode) || (oldMarketBusinessUnitCode != newMarketBusinessUnitCode))){
			confirmFlagForDateAndStateValdn = true;
			confirmFlagForDomainChange = true;
		// Case 5 : Validation for BD Only
		}else if(((oldLineOfBusinessCode != newLineOfBusinessCode) || (oldBusinessEntityCode != newBusinessEntityCode) || (oldBusinessGroupCode != newBusinessGroupCode) || (oldMarketBusinessUnitCode != newMarketBusinessUnitCode))
				&&((oldMandate == newMandate) || (oldState == newState) )
				&&  ((oldEffectiveDate == currentEffectiveDate) || (oldExpiryDate == currentExpiryDate))){
			confirmFlagForBDValdn = true;
			confirmFlagForDomainChange = true;
		// Case 6: Validation For Staate Only
		}else if(((oldMandate != newMandate) || (oldState != newState)) && (strType != 'Standard')){
	
			confirmFlagForStateValdn = true;			
	
		// Case6: Validation For Date Only
		}else if( ((oldEffectiveDate != currentEffectiveDate) || (oldExpiryDate != currentExpiryDate))
			 && ((oldMandate == newMandate) || (oldState == newState) )	
			 && ((oldLineOfBusinessCode == newLineOfBusinessCode)|| (oldBusinessEntityCode == newBusinessEntityCode) || (oldBusinessGroupCode == newBusinessGroupCode) || (oldMarketBusinessUnitCode != newMarketBusinessUnitCode))){
			confirmFlagForDateValdn = true;
			// confirmFlagForDomainChange = true;
		}
		
		if(confirmFlagForAllValdn){
			setNewValuesForMandateTypeAndState(newMandate,newState);
			setNewValuesForBD(newLineOfBusinessCode,newBusinessEntityCode,newBusinessGroupCode,newMarketBusinessUnitCode);		
			setNewValuesForDate();
			var flag = confirm("Change in date,MandateType,Business Domains or state will cause all the benefit components associated(if any) to be deleted . Do you wish to continue?");
			if(flag){
				callClick(linkName);
			}
	
		}
		
		else if(confirmFlagForBDAndStateValdn){
			setNewValuesForBD(newLineOfBusinessCode,newBusinessEntityCode,newBusinessGroupCode,newMarketBusinessUnitCode);
			setNewValuesForMandateTypeAndState(newMandate,newState);
			var flag = confirm("Change in Business Domains or state will cause all the benefit components associated(if any) to be deleted . Do you wish to continue?");
			if(flag){
				callClick(linkName);
			}
			
		}
		
		else if(confirmFlagForBDAndDateValdn){
			setNewValuesForBD(newLineOfBusinessCode,newBusinessEntityCode,newBusinessGroupCode, newMarketBusinessUnitCode);	
			setNewValuesForDate();
			var flag = confirm("Change in date,Business Domains  will cause all the benefit components associated(if any) to be deleted . Do you wish to continue?");					
			if(flag){
				callClick(linkName);
			}
	
		}
		else if(confirmFlagForDateAndStateValdn){	
			setNewValuesForDate();
			setNewValuesForMandateTypeAndState(newMandate,newState);
			var flag = confirm("Change in date or state will cause all the benefit components associated(if any) to be deleted . Do you wish to continue?");				
			if(flag){
				callClick(linkName);
			}
		}
		else if(confirmFlagForBDValdn){
			setNewValuesForBD(newLineOfBusinessCode,newBusinessEntityCode,newBusinessGroupCode, newMarketBusinessUnitCode);	
			var flag = confirm("Change in Business Domains  will cause all the benefit components and questionares associated(if any) to be deleted . Do you wish to continue?");				
			if(flag){
				callClick(linkName);
			}else{
				document.getElementById('productStructureGeneralInfoForm:hiddenLob').value =document.getElementById('productStructureGeneralInfoForm:oldLobHid').value;
				document.getElementById('productStructureGeneralInfoForm:hiddenEntity').value =document.getElementById('productStructureGeneralInfoForm:oldEntityHid').value;;
				document.getElementById('productStructureGeneralInfoForm:hiddenGroup').value =document.getElementById('productStructureGeneralInfoForm:oldGroupHid').value;
				parseForDiv(document.getElementById('divForLob'), document.getElementById('productStructureGeneralInfoForm:hiddenLob'));	
				parseForDiv(document.getElementById('divForEntity'), document.getElementById('productStructureGeneralInfoForm:hiddenEntity'));	
				parseForDiv(document.getElementById('divForGroup'), document.getElementById('productStructureGeneralInfoForm:hiddenGroup'));	
				parseForDiv(document.getElementById('divForMarketBusinessUnit'), document.getElementById('productStructureGeneralInfoForm:hiddenMarketBusinessUnit'));
				document.getElementById('divForLob').style.height= "14px";
				document.getElementById('divForEntity').style.height= "14px";
				document.getElementById('divForGroup').style.height= "14px";
				document.getElementById('divForMarketBusinessUnit').style.height= "14px";
				window.refresh();
			}
		}
		else if(confirmFlagForStateValdn){
			setNewValuesForMandateTypeAndState(newMandate,newState);
			var flag = confirm("Change in MandateType or state will cause all the benefit components associated(if any) to be deleted . Do you wish to continue?");	
			if(flag){
				callClick(linkName);
			}
		}
		else if(confirmFlagForDateValdn){
	
			setNewValuesForDate();
			var flag = confirm("Change in date will cause all the benefit components associated(if any) to be deleted . Do you wish to continue?");						
			if(flag){
				callClick(linkName);
			}
		}
		else{
			callClick(linkName);
		}
	}
	if(confirmFlagForDomainChange && (newState == null || newState == '')){
		// alert("There is a change in business domain. Please change the state.");
		document.getElementById('productStructureGeneralInfoForm:errorFlagForNullState').value = true;		
		document.getElementById('productStructureGeneralInfoForm:hid_state').value = " ";
	}
}
function callClick(linkName){
		//document.getElementById(linkName).click();
		return runSpellCheck();
		return true;
	}
// Set New Values For MandateType And State
	function setNewValuesForMandateTypeAndState(newMandate,newState){		
		document.getElementById('productStructureGeneralInfoForm:selectedMandate').value = newMandate;			
		document.getElementById('productStructureGeneralInfoForm:hid_state').value = newState;
		
	}

	// Set New Values For BD
	function setNewValuesForBD(newLineOfBusinessCode,newBusinessEntityCode,newBusinessGroupCode, newMarketBusinessUnitCode){		
		// document.getElementById('productStructureGeneralInfoForm:hiddenLob').value = newLineOfBusinessCode;
		document.getElementById('productStructureGeneralInfoForm:hiddenEntity').value = newBusinessEntityCode;
		document.getElementById('productStructureGeneralInfoForm:hiddenGroup').value = newBusinessGroupCode;
		document.getElementById('productStructureGeneralInfoForm:hiddenMarketBusinessUnit').value = newMarketBusinessUnitCode;
		document.getElementById('productStructureGeneralInfoForm:domainChanged').value = true;	
	}
// Set  New Values For Date
	function setNewValuesForDate(){
		document.getElementById('productStructureGeneralInfoForm:dateChanged').value = true;
	}
appendToRefDataVariablesSelectedRefDataName('hid_state', 'stateDiv');
parseForStateDiv(document.getElementById('stateDiv'), document.getElementById('productStructureGeneralInfoForm:oldState'));
 var actionOne = '1';
var beCatalog = 'Business Entity';
var bgCatalog = 'business group';
var lobCatalog = 'Line of Business';
var stateCode = 'State Code';
var mbuCatalog = 'Market Business Unit';
document.getElementById('divForLob').style.height= "17px";
document.getElementById('divForEntity').style.height= "17px";
document.getElementById('divForGroup').style.height= "17px";
document.getElementById('divForMarketBusinessUnit').style.height= "17px";

if(document.getElementById('productStructureGeneralInfoForm:hasValErrors').value == 'true'){
	var url='../adminMethods/adminMethodValidationIntermediatePage.jsp'+getUrl()+'?temp='+Math.random();
	var returnvalue=window.showModalDialog(url,"","dialogHeight:800px;dialogWidth:1200px;resizable=true;status=no;");
	document.getElementById('productStructureGeneralInfoForm:hasValErrors').value = 'false';
}

function runSpellCheck(){
	var version = document.getElementById('productStructureGeneralInfoForm:versionHidden').value; 
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

		document.getElementById(link).click();	
}
</script>

<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="generalInfo" /></form>

<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('productStructureGeneralInfoForm:duplicateData').value == '')
		document.getElementById('productStructureGeneralInfoForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
	else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('productStructureGeneralInfoForm:duplicateData').value;
</script>
</HTML>
