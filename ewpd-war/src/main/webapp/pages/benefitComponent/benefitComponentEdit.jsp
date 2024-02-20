<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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

<TITLE>Benefit Component Edit</TITLE>
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
<script language="JavaScript" src="../../js/prototype.js"></script>
<script language="JavaScript" src="../../js/rico.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/tree.js"></script>
<script language="JavaScript" src="../../js/menu.js"></script>
<script language="JavaScript" src="../../js/menu_items.js"></script>
<script language="JavaScript" src="../../js/menu_tpl.js"></script>
<script language="JavaScript" src="../../js/tree_items.js"></script>
<script language="JavaScript" src="../../js/tree_tpl.js"></script>

<script language="JavaScript">
	 	var cal1 = new CalendarPopup();
		 cal1.showYearNavigation();
</script>

</HEAD>
<f:view>
	<BODY>
	<table width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<td><jsp:include page="../navigation/top_Print.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td><h:form styleClass="form" id="benefitComponentEditForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<DIV class="treeDiv"><!-- Space for Tree  Data	--> <jsp:include
							page="../benefitComponent/benefitComponentTree.jsp"></jsp:include>
						</DIV>

						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message
										value="#{benefitComponentCreateBackingBean.validationMessages}"></w:message>
									</TD>
								</tr>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:outputText
											id="generalInformationTabTable" value=" General Information" />
										</td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<h:inputHidden id="hiddenTabValue"
									value="#{benefitComponentCreateBackingBean.componentTypeTab}"></h:inputHidden>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/tabNormalLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabNormal"><h:commandLink id="benHierarchy"
											action="#{BenefitComponentHierarchiesBackingBean.loadBenefitHierarchyTab}"
											onmousedown="javascript:navigatePageAction(this.id);">
											<h:outputText id="benefitHierarchyTabTable"
												value=" Benefit Hierarchies" />
										</h:commandLink></td>

										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>


								<td width="200" id="bcNotesTab">
								<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
									<TR>
										<TD width="2" align="left"><IMG
											src="../../images/tabNormalLeft.gif" width="3" height="21"></TD>
										<TD width="186" class="tabNormal"><h:commandLink id="noteId" 
											action="#{BenefitComponentNotesBackingBean.loadBenefitComponentNotes}"
											onmousedown="javascript:navigatePageAction(this.id);">
											<h:outputText value="Notes " />
										</h:commandLink></TD>
										<TD width="2" align="right"><IMG
											src="../../images/tabNormalRight.gif" width="2" height="21"></TD>
									</TR>
								</TABLE>
								</td>
								<td width="100%"></td>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

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

						<!--	Start of Table for actual Data	-->
						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText">
							<TBODY>
								<br />
								<TR>
									<TD colspan=3>
									<FIELDSET style="width:90%"><LEGEND><FONT color="black">Business Domain</FONT></LEGEND>
									<TABLE>
										<TR>
											<TD width="144"><h:outputText id="lobLabel"
												value="Line of Business*"
												styleClass="#{benefitComponentCreateBackingBean.lobValdn?'mandatoryError': 'mandatoryNormal'}" />
											</TD>
											<TD width="188">
											<DIV id="lobDiv" class="selectDataDisplayDiv"></DIV>
											<h:inputHidden id="lobTxtHidden"
												value="#{benefitComponentCreateBackingBean.lineOfBusiness}"></h:inputHidden>
											</TD>
											<TD width="26"><h:commandButton id="lobButton" alt="Select"
												image="../../images/select.gif" style="cursor: hand"
												rendered="#{benefitComponentCreateBackingBean.status != 'CHECKED_OUT'}"
												onclick="ewpdModalWindow_ewpd('../popups/lineOfBusinessPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Line of Business','lobDiv','benefitComponentEditForm:lobTxtHidden',2,2); return false;"
												tabindex="1" /></TD>
										</TR>
										<TR>
											<TD width="144"><h:outputText id="businessEntityLabel"
												value="Business Entity*"
												styleClass="#{benefitComponentCreateBackingBean.businessentityValdn?'mandatoryError': 'mandatoryNormal'}" />
											</TD>
											<TD width="188">
											<DIV id="businessEntityDiv" class="selectDataDisplayDiv"></DIV>
											<h:inputHidden id="businessEntityTxtHidden"
												value="#{benefitComponentCreateBackingBean.businessEntity}"></h:inputHidden>
											</TD>
											<TD width="26"><h:commandButton id="businessEntityButton"
												alt="Select" image="../../images/select.gif"
												style="cursor: hand"
												rendered="#{benefitComponentCreateBackingBean.status != 'CHECKED_OUT'}"
												onclick="ewpdModalWindow_ewpd('../popups/businessEntityPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Business Entity','businessEntityDiv','benefitComponentEditForm:businessEntityTxtHidden',2,2); return false;"
												tabindex="2" /></TD>
										</TR>
										<TR>
											<TD width="144"><h:outputText id="businessGroupLabel"
												value="Business Group*"
												styleClass="#{benefitComponentCreateBackingBean.businessgroupValdn?'mandatoryError': 'mandatoryNormal'}" /></TD>
											<TD width="188">
											<DIV id="businessGroupDiv" class="selectDataDisplayDiv"></DIV>
											<h:inputHidden id="businessGroupTxtHidden"
												value="#{benefitComponentCreateBackingBean.businessGroup}"></h:inputHidden>
											</TD>
											<TD width="26"><h:commandButton id="businessGroupButton"
												alt="Select" image="../../images/select.gif"
												style="cursor: hand"
												rendered="#{benefitComponentCreateBackingBean.status != 'CHECKED_OUT'}"
												onclick="ewpdModalWindow_ewpd('../popups/businessGroupPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'business group','businessGroupDiv','benefitComponentEditForm:businessGroupTxtHidden',2,2); return false;"
												tabindex="3" /></TD>
										</TR>
<!-- ------------------------------------------------------------------------------------------ -->
										<TR>
											<TD width="144"><h:outputText
												value="Market Business Unit*"
												styleClass="#{benefitComponentCreateBackingBean.requiredMarketBusinessUnit?'mandatoryError': 'mandatoryNormal'}" /></TD>
											<TD width="188">
											<DIV id="MarketBusinessUnitDiv" class="selectDataDisplayDiv"></DIV>
											<h:inputHidden id="marketBusinessUnitTxtHidden"
												value="#{benefitComponentCreateBackingBean.marketBusinessUnit}"></h:inputHidden>
											</TD>
											<TD width="26"><h:commandButton id="marketBusinessUnitButton"
												alt="Select" image="../../images/select.gif"
												style="cursor: hand"
												rendered="#{benefitComponentCreateBackingBean.status != 'CHECKED_OUT'}"
												onclick="ewpdModalWindow_ewpd('../popups/marketBusinessUnit.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Market Business Unit','MarketBusinessUnitDiv','benefitComponentEditForm:marketBusinessUnitTxtHidden',2,2);return false;"
												tabindex="3" /></TD>
										</TR>
<!-- ------------------------------------------------------------------------------------------ -->
									</TABLE>
									</FIELDSET>
									</TD>
								</TR>


								<TR>
									<TD width="149">&nbsp;<h:outputText id="nameLabel"
										value="Name*"
										styleClass="#{benefitComponentCreateBackingBean.nameValdn?'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<TD width="186"><h:inputText id="nameTxt"
										styleClass="formInputField"
										value="#{benefitComponentCreateBackingBean.benefitComponentName}"
										readonly="#{benefitComponentCreateBackingBean.status == 'CHECKED_OUT'}"
										maxlength="34" tabindex="4" /></TD>
									<!--<h:inputHidden id="hiddenName"	value="#{benefitComponentCreateBackingBean.benefitComponentName}"></h:inputHidden> -->
								</TR>
									<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher1"
										textComponentName="benefitComponentEditForm:nameTxt"
										rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp'+getUrl()+'?title=Spell Checking - Benefit Component Name"
										modalHelperPage="../spellcheck/RapidSpellModalHelper.html"
										showNoErrorsMessage="False" showFinishedMessage="False"
										includeUserDictionaryInSuggestions="True"
										allowAnyCase="True" allowMixedCase="True"
										windowWidth="570" windowHeight="300"
										modal="False" showButton="False"
										windowX="-1" warnDuplicates="False"
										textComponentInterface="Custom">
									</RapidSpellWeb:rapidSpellWebLauncher>

								<!-- Enhancements -->


								<TR>
									<TD width="136">&nbsp;<h:outputText id="componentType"
										value="Component Type " /></TD>
									<TD height="9" width="177"><h:outputText id="compType"
										value="#{benefitComponentCreateBackingBean.componentType}" />
									<h:inputHidden id="cmpt_Hidden"
										value="#{benefitComponentCreateBackingBean.componentType}" />
									</TD>
								</TR>
                                <TR>
									<TD width="149" valign="top">&nbsp;<h:outputText id="descriptionLabel"
										value="Description*"
										styleClass="#{benefitComponentCreateBackingBean.descValdn?'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<TD width="186"><h:inputTextarea id="descriptionTxtarea"
										styleClass="formTxtAreaField_GeneralDesc"
										value="#{benefitComponentCreateBackingBean.description}"
										tabindex="4" /></TD>
								</TR>
								<tr id="sub1" style="display:none;">
									<TD width="136">&nbsp;<h:outputText id="mandType"
										value="Mandate Type*"
										styleClass="#{benefitComponentCreateBackingBean.mandateTypeValdn ? 'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<TD height="9" width="177"><h:selectOneMenu id="mandateType"
										styleClass="formInputField" tabindex="5"
										value="#{benefitComponentCreateBackingBean.mandateType}"
										onchange="getMandateType()">
										<f:selectItems id="mandateTypeList"
											value="#{ReferenceDataBackingBeanCommon.mandateTypeListForCombo}" />
									</h:selectOneMenu></TD>
								</TR>
								<tr id="sub2" style="display:none;">
									<TD width="136">&nbsp;<h:outputText id="stateCde"
										value="Jurisdiction*"
										styleClass="#{benefitComponentCreateBackingBean.stateIdValdn ? 'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<h:inputHidden id="hid_state"
										value="#{benefitComponentCreateBackingBean.selectedStateId}"></h:inputHidden>
									<TD width="177">
									<div id="stateDiv" class="selectDataDisplayDiv"></div>

									</TD>
									<TD width="40"><h:commandButton alt="State" id="stateButton"
										image="../../images/select.gif"
										onclick="getSelectedDomainReferenceData('../contractpopups/HeadQuarterstatePopup.jsp'+getUrl(), 'benefitComponentEditForm',
										'lobTxtHidden', 'businessEntityTxtHidden', 'businessGroupTxtHidden','stateDiv','benefitComponentEditForm:hid_state', 2, 2, 'State Code');setRefDataUseFlag('benefitComponentEditForm', 'hid_state', 'stateDiv');
																		return false;"
										tabindex="6">
									</h:commandButton></TD>
								</TR>
								<tr id="sub3" style="display:none;">
									<TD width="136">&nbsp;<h:outputText value="Mandate Type" /></TD>
									<TD width="177"><h:inputTextarea styleClass="selectDivReadOnly"
										id="mandType1"
										value="#{standardBenefitBackingBean.mandateType}"
										readonly="true" style="border:0"></h:inputTextarea> <h:inputHidden
										id="mandTypeHidden"
										value="#{benefitComponentCreateBackingBean.mandateType}">
									</h:inputHidden><SCRIPT>copyHiddenToInputText('benefitComponentEditForm:mandTypeHidden','benefitComponentEditForm:mandType1',2);</SCRIPT>
									</TD>
								</TR>
								<tr id="sub4" style="display:none;">
									<TD width="136">&nbsp;<h:outputText value="State" /></TD>
									<TD width="177"><h:inputTextarea id="stateCde1"
										styleClass="selectDivReadOnly" readonly="true"
										style="border:0"
										value="#{benefitComponentCreateBackingBean.selectedStateId}" />
									<h:inputHidden id="stateCdeHidden"
										value="#{benefitComponentCreateBackingBean.selectedStateId}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitComponentEditForm:stateCdeHidden','benefitComponentEditForm:stateCde1',2); </SCRIPT>
									</TD>
								</TR>
								<TR id="sub5" style="display:none;">
									<TD width="136">&nbsp;<h:outputText id="stateCde2"
										value="Jurisdiction*" /></TD>
									<TD width="177"><h:outputText id="FederalLabel" value="ALL"></h:outputText>
									</TD>
								</TR>
								<TR>
									<TD width="136">&nbsp;<h:outputText id="ruleIdLabel"
										value="Benefit Rule ID*" /></TD>
										<%-- styleClass="#{benefitComponentCreateBackingBean.ruleIdValdn?'mandatoryError': 'mandatoryNormal'}" --%> 
									<TD width="177">
									<DIV id="ruleIdDiv" class="selectDataDisplayDiv"></DIV>
									<h:inputHidden id="ruleIdTxtHidden"
										value="#{benefitComponentCreateBackingBean.ruleId}"></h:inputHidden>
									<h:inputHidden id="txtStrRuleType" value="#{benefitComponentCreateBackingBean.strRuleType}"></h:inputHidden>
									</TD>
									<TD width="43"><h:commandButton id="ruleIdButton" alt="Select"
										image="../../images/select.gif" style="cursor: hand"
										onclick="loadpopup();return false;"
										tabindex="7" />&nbsp;
											<h:commandButton alt="View" id="viewButton"
												image="../../images/view.gif"
												onclick="viewAction();return false;" tabindex="8"/></TD>
								</TR>
								<!-- End Enhancements -->
								
                                <TR>
									<TD width="149">&nbsp;<h:outputText id="effectiveDateLabel"
										value="Effective Date*"
										styleClass="#{benefitComponentCreateBackingBean.effdateValdn?'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<TD width="186"><h:inputText id="effectiveDateTxt"
										styleClass="formInputField"
										value="#{benefitComponentCreateBackingBean.effectiveDate}"
										tabindex="9" /><BR Class="brclass">
									<SPAN Class="dateformat">(mm/dd/yyyy)</SPAN></TD>


									<TD valign="top" width="95"><A href="#"
										onclick="cal1.select('benefitComponentEditForm:effectiveDateTxt','effectiveDate_cal','MM/dd/yyyy'); return false;"
										title="cal1.select(document.forms[0].benefitComponentEditForm:effectiveDateTxt,'effectiveDate_cal','MM/dd/yyyy'); return false;"
										name="effectiveDate_cal" id="effectiveDate_cal" tabindex="10"><IMG
										src="../../images/cal.gif" alt="Cal" border="0"></A></TD>
								</TR>
								<TR>
									<TD width="149">&nbsp;<h:outputText id="expiryDateLabel"
										value="Expiry Date*"
										styleClass="#{benefitComponentCreateBackingBean.expdateValdn?'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<TD width="186"><h:inputText id="expiryDateTxt"
										styleClass="formInputField"
										value="#{benefitComponentCreateBackingBean.expiryDate}"
										tabindex="11" /><BR Class="brclass">
									<SPAN Class="dateformat">(mm/dd/yyyy)</SPAN></TD>
									<TD valign="top" width="95"><A href="#"
										onclick="cal1.select('benefitComponentEditForm:expiryDateTxt','expiryDate_cal','MM/dd/yyyy'); return false;"
										title="cal1.select(document.forms[0].benefitComponentEditForm:expiryDateTxt,'expiryDate_cal','MM/dd/yyyy'); return false;"
										name="expiryDate_cal" id="expiryDate_cal" tabindex="12"><IMG
										src="../../images/cal.gif" alt="Cal" border="0"></A></TD>
								</TR>
								<TR>
									&nbsp;
								</TR>

								<tr>
									<td valign="top" width="149"><span class="mandatoryNormal"
										id="creationDateId">&nbsp;</span><h:outputText
										value="Created By" /></td>
									<td width="186"><h:outputText
										value="#{benefitComponentCreateBackingBean.createdUser}" /> <h:inputHidden
										id="createdUserHidden"
										value="#{benefitComponentCreateBackingBean.createdUser}" />
									</td>
								</tr>
								<tr>
									<td valign="top" width="149"><span class="mandatoryNormal"
										id="createdBy">&nbsp;</span><h:outputText value="Created Date" /></td>
									<td width="186"><h:outputText
										value="#{benefitComponentCreateBackingBean.createdTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText>
										<h:outputText value="#{requestScope.timezone}"></h:outputText>
										<h:inputHidden id="createdTimestampHidden"
											value="#{benefitComponentCreateBackingBean.createdTimestamp}" >
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss"/>
										</h:inputHidden>
										<h:inputHidden id="time"
											value="#{requestScope.timezone}">											
										</h:inputHidden>
									</td>
								</tr>
								<tr>
									<td valign="top" width="149"><span class="mandatoryNormal"
										id="updationDate">&nbsp;</span><h:outputText
										value="Last Updated By" /></td>
									<td width="186"><h:outputText
										value="#{benefitComponentCreateBackingBean.lastUpdatedUser}"/>
										<h:inputHidden id="lastUpdatedUserHidden"
										value="#{benefitComponentCreateBackingBean.lastUpdatedUser}" >							
										</h:inputHidden>
									</td>
								</tr>
								<tr>
									<td valign="top" width="149"><span class="mandatoryNormal"
										id="updateBy">&nbsp;</span><h:outputText
										value="Last Updated Date" /></td>
									<td width="186"><h:outputText
										value="#{benefitComponentCreateBackingBean.lastUpdatedTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText>
										<h:outputText value="#{requestScope.timezone}"></h:outputText>
										<h:inputHidden id="lastUpdatedTimestampHidden"
											value="#{benefitComponentCreateBackingBean.lastUpdatedTimestamp}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss"/>
										</h:inputHidden>
									</td>
								</tr>
									<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher2"
										textComponentName="benefitComponentEditForm:descriptionTxtarea"
										rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp'+getUrl()+'?title=Spell Checking - Benefit Component Description"
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

								<TR>
									<TD width="149">&nbsp;
										<h:commandButton value="Save" id="createButton"
											styleClass="wpdButton" onclick="showWarningMessage(); return false;" tabindex="13"
											onmousedown="javascript:savePageAction(this.id);">
										</h:commandButton>
										<h:commandLink id="editBenefitComp"
											style="hidden" action="#{benefitComponentCreateBackingBean.editBenefitComponent}">
										</h:commandLink>
									</TD>	
								</TR>

							</TBODY>
						</TABLE>
						</fieldset>
						<!--	End of Page data	--> <BR>
						<FIELDSET
							style="margin-left:6px;margin-right:-6px;margin-top:6px;padding-bottom:1px;padding-top:20px;width:100%">
						<table border="0" cellspacing="0" cellpadding="3" width="100%">
							<tr>
								<td width="20%"><h:selectBooleanCheckbox id="checkall"
									
									value="#{benefitComponentCreateBackingBean.checkInOpted}"
									tabindex="14"></h:selectBooleanCheckbox>
								<h:outputText value="Check In" /></td>
								<td align="right" >
								<table >
									<tr>
										<td ><h:outputText value="State" /></td>
										<td>: <h:outputText
											value="#{benefitComponentCreateBackingBean.state}" /></td>
										<h:inputHidden id="stateHidden"
											value="#{benefitComponentCreateBackingBean.state}" />
									</tr>
									<tr>
										<td ><h:outputText value="Status" /></td>
										<td>: <h:outputText
											value="#{benefitComponentCreateBackingBean.status}" /></td>
										<h:inputHidden id="statusHidden"
											value="#{benefitComponentCreateBackingBean.status}" />
									</tr>
									<tr>
										<td ><h:outputText value="Version" /></td>
										<td>: <h:outputText id="version"
											value="#{benefitComponentCreateBackingBean.version}" /></td>
										<h:inputHidden id="versionHidden"
											value="#{benefitComponentCreateBackingBean.version}" />
									</tr>
								</table>
								</td>
							</tr>
						</table>
						</FIELDSET>
						<BR>
						&nbsp;&nbsp;&nbsp;
							<h:commandButton value="Done"
								styleClass="wpdButton" onclick="done(); return false;" tabindex="15">
							</h:commandButton>
							<h:commandLink id="checkinBenefitComp"
								style="hidden" action="#{benefitComponentCreateBackingBean.done}">
							</h:commandLink>
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
				<h:inputHidden id="hiddenBenefitComponentId"
					value="#{benefitComponentCreateBackingBean.benefitComponentId}"></h:inputHidden>
				<h:inputHidden id="hiddenBenefitComponentParentId"
					value="#{benefitComponentCreateBackingBean.benefitComponentParentId}"></h:inputHidden>
				<h:inputHidden id="oldEffectiveDate"
					value="#{benefitComponentCreateBackingBean.oldEffectiveDate}"></h:inputHidden>
				<h:inputHidden id="oldExpiryDate"
					value="#{benefitComponentCreateBackingBean.oldExpiryDate}"></h:inputHidden>
				<h:inputHidden id="dateChanged"
					value="#{benefitComponentCreateBackingBean.dateChanged}"></h:inputHidden>
				<h:commandLink id="saveLink"
					style="display:none; visibility: hidden;"
					action="#{benefitComponentCreateBackingBean.editBenefitComponent}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="doneLink"
					style="display:none; visibility: hidden;"
					action="#{benefitComponentCreateBackingBean.done}">
					<f:verbatim />
				</h:commandLink>
				<h:inputHidden id="selectedMandate"
					value="#{benefitComponentCreateBackingBean.mandateType}" />
				<h:inputHidden id="selectedState"
					value="#{benefitComponentCreateBackingBean.selectedStateId}" />
				<h:inputHidden id="oldMandate"
					value="#{benefitComponentCreateBackingBean.oldMandateType}"></h:inputHidden>
				<h:inputHidden id="oldState"
					value="#{benefitComponentCreateBackingBean.oldSelectedStateId}"></h:inputHidden>
				<h:inputHidden id="oldLineOfBusinessCode"
					value="#{benefitComponentCreateBackingBean.oldLineOfBusinessCode}"></h:inputHidden>
				<h:inputHidden id="oldBusinessEntityCode"
					value="#{benefitComponentCreateBackingBean.oldBusinessEntityCode}"></h:inputHidden>
				<h:inputHidden id="oldBusinessGroupCode"
					value="#{benefitComponentCreateBackingBean.oldBusinessGroupCode}"></h:inputHidden>	
				<h:inputHidden id="oldMarketBusinessUnitCode"
					value="#{benefitComponentCreateBackingBean.oldmarketBusinessUnit}"></h:inputHidden>			
				<h:inputHidden id="domainChanged"
					value="#{benefitComponentCreateBackingBean.domainChange}"></h:inputHidden>
				<h:inputHidden id="duplicateData"
					value="#{benefitComponentCreateBackingBean.duplicateData}"></h:inputHidden>
				<!-- End of hidden fields  -->

			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>

<script language="JavaScript">
		 IGNORED_FIELD1='benefitComponentEditForm:duplicateData';
	// alert('STATE:'+ document.getElementById('benefitComponentEditForm:hid_state').value);
	// alert('aftersubmit:'+document.getElementById('benefitComponentEditForm:lobTxtHidden').value);
	copyHiddenToDiv_ewpd('benefitComponentEditForm:lobTxtHidden','lobDiv',2,2);
	copyHiddenToDiv_ewpd('benefitComponentEditForm:businessEntityTxtHidden','businessEntityDiv',2,2);
	//alert('domain:'+ document.getElementById('benefitComponentEditForm:businessEntityTxtHidden').value);
	copyHiddenToDiv_ewpd('benefitComponentEditForm:businessGroupTxtHidden','businessGroupDiv',2,2);	
	copyHiddenToDiv_ewpd('benefitComponentEditForm:marketBusinessUnitTxtHidden', 'MarketBusinessUnitDiv', 2,2);
	copyHiddenToDiv('benefitComponentEditForm:ruleIdTxtHidden','ruleIdDiv');		

	copyHiddenToDiv_ewpd1('benefitComponentEditForm:ruleIdTxtHidden','ruleIdDiv',2,1);
	copyHiddenToDiv_ewpd('benefitComponentEditForm:hid_state', 'stateDiv' , 2, 2) ; 

	appendToRefDataVariablesSelectedRefDataName('hid_state', 'stateDiv');
	parseForStateDiv(document.getElementById('stateDiv'), document.getElementById('benefitComponentEditForm:oldState'));

	var checkin = false;
	function done(){
		checkin=true;
		showWarningMessage();
	}
	function runSpellCheck(){
		var version = document.getElementById('benefitComponentEditForm:versionHidden').value;
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

			if(checkin)
				document.getElementById('benefitComponentEditForm:checkinBenefitComp').click();	
			else
				document.getElementById('benefitComponentEditForm:editBenefitComp').click();	
		checkin=false;
	}
	
	

// For the  combo boxes
var version;
version = document.getElementById("benefitComponentEditForm:version");
 // alert('versionval:'+version);
if(version.innerHTML==0)
{
	 // alert('version 0');
	getComponentType();
	getMandateType();
}
else{
	//  alert('version !0');
	getComponentType1();
	getMandateType1();
}


function getComponentType()
{
var i;
var obj;
obj = document.getElementById("benefitComponentEditForm:compType");
i= obj.innerHTML;	
	// alert(i);
	if(i== "MANDATE")
	{
	sub1.style.display='';
    //sub5.style.display='none';	
	}
	
	else 
	{
	sub1.style.display='none';
	sub2.style.display='none';
	//sub5.style.display='none';	
	}

}
function getMandateType()
	{
	var i;
	var obj;
	obj = document.getElementById("benefitComponentEditForm:mandateType");
// **Change
// alert("combo selected value:"+obj.value);
document.getElementById("benefitComponentEditForm:mandType1").value = obj.value;
document.getElementById("benefitComponentEditForm:mandTypeHidden").value = obj.value;
// alert("mantype1val:"+document.getElementById("benefitComponentEditForm:mandType1").value);
// **End**
	i= obj.selectedIndex;
//alert(i);
		// State or Extra Territorial
		if(i== 1 || i==3)
		{
		if(document.getElementById('stateDiv').innerHTML == 'undefined<BR>'){
			// alert('no');
			document.getElementById('stateDiv').innerHTML = '';
		}	
		sub2.style.display='';	
		sub5.style.display='none';	
		}
		// Federal 
		else if(i == 2)
		{
		sub2.style.display='none';
		sub4.style.display='none';
	//	document.getElementById('benefitComponentEditForm:oldState').value = "ALL";
	//	document.getElementById('benefitComponentEditForm:hid_state').value = "ALL";

		sub5.style.display='';	
//alert("After");
		}
		else 
		{
		sub2.style.display='none';
		sub5.style.display='none';	
		}
	}
function getComponentType1()
{
var i;
var obj;
obj = document.getElementById("benefitComponentEditForm:compType");
i= obj.innerHTML;
	//alert(i);
	if(i=="MANDATE")
	{
	// alert('mandate');
	sub3.style.display='';
	//sub5.style.display='none';	
	}
	
	else 
	{
	//alert('ccdf');
	sub3.style.display='none';
	sub4.style.display='none';
    //sub5.style.display='none';	
	}

}
function getMandateType1()
{
var i;
var obj;
 // alert("mantype1val:"+document.getElementById("benefitComponentEditForm:mandateType").value);
obj = document.getElementById("benefitComponentEditForm:mandateType");
i= obj.value;
	  //alert('i'+ i + obj);
	if(i=="ET" || i == "ST")
	{
	if(i=="ST"){
					//  alert('State');	
					document.getElementById("benefitComponentEditForm:mandType1").value = "State";
				}else{
					document.getElementById("benefitComponentEditForm:mandType1").value = "ExtraTerritorial";
				}
	sub4.style.display='';
	sub5.style.display='none';
	}
	
	else if(i=="FED")
	{
	  //alert('Federal');
		document.getElementById("benefitComponentEditForm:mandType1").value = "Federal";
		document.getElementById('benefitComponentEditForm:oldState').value = "ALL";
		document.getElementById('benefitComponentEditForm:hid_state').value = "ALL";
		sub4.style.display='none';
		sub5.style.display='';
	}else{
		sub4.style.display='none';
		sub5.style.display='none';
	}

}
// **Change**
function copyToSub4(){
	var stateVal = document.getElementById("benefitComponentEditForm:hid_state").value;
	// alert("statVal:"+stateVal);
	document.getElementById("benefitComponentEditForm:stateCdeHidden").value = stateVal;
	document.getElementById("benefitComponentEditForm:stateCde1").value = stateVal;
}
// **End**
// Enhancement
	hideNotesTab();
	function hideNotesTab(){
	var tab = document.getElementById('benefitComponentEditForm:hiddenTabValue').value;	
	// alert('tab:'+ tab);
	if(tab == "Standard Definition")
		bcNotesTab.style.display = '';
	else
		bcNotesTab.style.display = 'none';
	}
	
	// Method to show the display message  before changing the mandateType and state
	function showWarningMessage(){
	
	confirmFlagForAllValdn = false;
	confirmFlagForBDAndStateValdn = false;
	confirmFlagForBDAndDateValdn = false;
	confirmFlagForDateAndStateValdn = false;
	confirmFlagForBDValdn = false;
	confirmFlagForStateValdn = false;
	confirmFlagForDateValdn = false;
	commonLinkFlag = true;
	confirmFlagForDomainChange = false;

	// For Date Validation
	var oldEffectiveDate = document.getElementById('benefitComponentEditForm:oldEffectiveDate').value;
	var oldExpiryDate = document.getElementById('benefitComponentEditForm:oldExpiryDate').value;
	var currentEffectiveDate = document.getElementById('benefitComponentEditForm:effectiveDateTxt').value;
	var currentExpiryDate = document.getElementById('benefitComponentEditForm:expiryDateTxt').value;

	// Business Domian Validation 
	var oldLineOfBusinessCode = document.getElementById('benefitComponentEditForm:oldLineOfBusinessCode').value;
	var oldBusinessEntityCode = document.getElementById('benefitComponentEditForm:oldBusinessEntityCode').value;
	var oldBusinessGroupCode = document.getElementById('benefitComponentEditForm:oldBusinessGroupCode').value;
	var oldMarketBusinessUnitCode = document.getElementById('benefitComponentEditForm:oldMarketBusinessUnitCode').value;
	var newLineOfBusinessCode = document.getElementById('benefitComponentEditForm:lobTxtHidden').value;
	var newBusinessEntityCode = document.getElementById('benefitComponentEditForm:businessEntityTxtHidden').value;
	var newBusinessGroupCode = document.getElementById('benefitComponentEditForm:businessGroupTxtHidden').value;
	var newMarketBusinessUnitCode = document.getElementById('benefitComponentEditForm:marketBusinessUnitTxtHidden').value;
	
	// State And MandateType Validation
	obj1 = document.getElementById("benefitComponentEditForm:selectedState").value;
	document.getElementById("benefitComponentEditForm:selectedState").value = 
					document.getElementById("benefitComponentEditForm:hid_state").value;

	var temp = document.getElementById("benefitComponentEditForm:hid_state");		
	copyHiddenToDiv_ewpd('benefitComponentEditForm:hid_state','stateDiv',2,2); 

	var oldMandate = document.getElementById('benefitComponentEditForm:oldMandate').value;		
	var oldState = document.getElementById('benefitComponentEditForm:oldState').value;		
	var newMandate = document.getElementById('benefitComponentEditForm:mandateType').value;		
	var newState = document.getElementById('benefitComponentEditForm:hid_state').value;
	var compTypeObj = document.getElementById("benefitComponentEditForm:compType");
	var compType = compTypeObj.innerHTML;
	if((((oldMandate != newMandate) || (oldState != newState)) && (compType != 'STANDARD'))
		&& ((oldEffectiveDate != currentEffectiveDate) || (oldExpiryDate != currentExpiryDate)) 
		){
		 confirmFlagForAllValdn = true;	
		 confirmFlagForDomainChange = true;		

	// Case 4: Validation For Date and State
	}else if(((oldEffectiveDate != currentEffectiveDate) || (oldExpiryDate != currentExpiryDate))
			 && ((oldMandate != newMandate) || (oldState != newState)&& (compType != 'STANDARD')) 
			 &&	((oldLineOfBusinessCode == newLineOfBusinessCode) || (oldBusinessEntityCode == newBusinessEntityCode) 
					|| (oldBusinessGroupCode == newBusinessGroupCode) || (oldMarketBusinessUnitCode == newMarketBusinessUnitCode) )){
		confirmFlagForDateAndStateValdn = true;

	// Case 6: Validation For State Only
	}else if(((oldMandate != newMandate) || (oldState != newState) && (compType != 'STANDARD'))
		&& ((oldEffectiveDate == currentEffectiveDate) || (oldExpiryDate == currentExpiryDate)) 
		&& ((oldLineOfBusinessCode == newLineOfBusinessCode)  || (oldBusinessEntityCode == newBusinessEntityCode) 
					|| (oldBusinessGroupCode == newBusinessGroupCode) || (oldMarketBusinessUnitCode == newMarketBusinessUnitCode) )){

		confirmFlagForStateValdn = true;			

	}else if( ((oldEffectiveDate != currentEffectiveDate) || (oldExpiryDate != currentExpiryDate))
		 && ((oldMandate == newMandate) || (oldState == newState) )	
		 && ((oldLineOfBusinessCode == newLineOfBusinessCode)|| (oldBusinessEntityCode == newBusinessEntityCode) 
				|| (oldBusinessGroupCode == newBusinessGroupCode) || (oldMarketBusinessUnitCode == newMarketBusinessUnitCode) )){
		confirmFlagForDateValdn = true;
	}
	
	if(confirmFlagForAllValdn){
		setNewValuesForMandateTypeAndState(newMandate,newState);
		//setNewValuesForBD(newLineOfBusinessCode,newBusinessEntityCode,newBusinessGroupCode);	
		setNewValuesForDate();
		var flag = confirm("Change in date,MandateType or state will cause all the benefits associated(if any) to be deleted . Do you wish to continue?");
		if(flag){
			return runSpellCheck();
		}

	}
	
	else if(confirmFlagForBDAndStateValdn){
		//setNewValuesForBD(newLineOfBusinessCode,newBusinessEntityCode,newBusinessGroupCode);
		setNewValuesForMandateTypeAndState(newMandate,newState);
		var flag = confirm("Change in Business Domains or state will cause all the benefits associated(if any) to be deleted . Do you wish to continue?");
		if(flag){
			return runSpellCheck();
		}

	}
	
	else if(confirmFlagForBDAndDateValdn){
		//setNewValuesForBD(newLineOfBusinessCode,newBusinessEntityCode,newBusinessGroupCode);	
		setNewValuesForDate();
		var flag = confirm("Change in date,Business Domains  will cause all the benefits associated(if any) to be deleted . Do you wish to continue?");					
		if(flag){
			return runSpellCheck();
		}

	}
	else if(confirmFlagForDateAndStateValdn){		
		setNewValuesForDate();
		setNewValuesForMandateTypeAndState(newMandate,newState);
		var flag = confirm("Change in date or state will cause all the benefits associated(if any) to be deleted . Do you wish to continue?");				
		if(flag){
			return runSpellCheck();
		}
	}
	else if(confirmFlagForBDValdn){
		//setNewValuesForBD(newLineOfBusinessCode,newBusinessEntityCode,newBusinessGroupCode);	
		var flag = confirm("Change in Business Domains  will cause all the benefits associated(if any) to be deleted . Do you wish to continue?");				
		if(flag){
			return runSpellCheck();
		}
	}
	else if(confirmFlagForStateValdn){
		setNewValuesForMandateTypeAndState(newMandate,newState);
		var flag = confirm("Change in MandateType or state will cause all the benefits associated(if any) to be deleted . Do you wish to continue?");	
		if(flag){
			return runSpellCheck();
		}
	}
	else if(confirmFlagForDateValdn){			
		setNewValuesForDate();
		var flag = confirm("Change in date will cause all the benefits associated(if any) to be deleted . Do you wish to continue?");				
		if(flag){
			return runSpellCheck();
		}
	}
	else{
		//alert(10);
		document.getElementById('benefitComponentEditForm:dateChanged').value = false;
		return runSpellCheck();
	}
//}
}
	// Set New Values For MandateType And State
	function setNewValuesForMandateTypeAndState(newMandate,newState){		
		document.getElementById('benefitComponentEditForm:selectedMandate').value = newMandate;			
		document.getElementById('benefitComponentEditForm:hid_state').value = newState;
		
	}

	// Set Old Values For MandateType And State
/*	function setOldValuesForMandateTypeAndState(oldMandate,oldState){
		document.getElementById('benefitComponentEditForm:mandateType').value = oldMandate;			
		document.getElementById('benefitComponentEditForm:hid_state').value = oldState;
		parseForStateDiv(document.getElementById('stateDiv'), document.getElementById('benefitComponentEditForm:oldState'));
		copyHiddenToInputText('benefitComponentEditForm:mandateType','benefitComponentEditForm:selectedMandate',1);			
		if(oldMandate == 2 || oldMandate == 3){
			sub2.style.display='';		
		}else{
			sub2.style.display='none';
		}
	}*/

// ****************************** Set New Values For BD - commented on 26thDec2007
//	function setNewValuesForBD(newLineOfBusinessCode,newBusinessEntityCode,newBusinessGroupCode){		
//		document.getElementById('benefitComponentEditForm:lobTxtHidden').value = newLineOfBusinessCode;
//		document.getElementById('benefitComponentEditForm:businessEntityTxtHidden').value = newBusinessEntityCode;
//		document.getElementById('benefitComponentEditForm:businessGroupTxtHidden').value = newBusinessGroupCode;
//		document.getElementById('benefitComponentEditForm:domainChanged').value = true;		
//	}

	// Set Old Values For BD
/*	function setOldValuesForBD(oldLineOfBusinessCode,oldBusinessEntityCode,oldBusinessGroupCode){
		document.getElementById('benefitComponentEditForm:lobTxtHidden').value = oldLineOfBusinessCode;
		document.getElementById('benefitComponentEditForm:businessEntityTxtHidden').value = oldBusinessEntityCode;
		document.getElementById('benefitComponentEditForm:businessGroupTxtHidden').value = oldBusinessGroupCode;
	}*/
	
	// Set  New Values For Date
	function setNewValuesForDate(){
		document.getElementById('benefitComponentEditForm:dateChanged').value = true;
	}


	function callClick(linkName){
		document.getElementById(linkName).click();
		return true;
	}

function viewAction(){
	
	var ruleIdStr = document.getElementById('benefitComponentEditForm:ruleIdTxtHidden').value;
	var ruleType  =	document.getElementById('benefitComponentEditForm:txtStrRuleType').value;
	if(ruleIdStr.length <=1){
			alert('Benefit Rule ID need to be selected.');
			return false;
		}
	
	var ruleArray = ruleIdStr.split('~');
	var ruleId = ruleArray[1];
	if(ruleType=="BLZWPDAB")
	{
		window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&ruleType='+ruleType+'&temp='+ Math.random(),'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	}
	else if(ruleType!="BLZWPDAB") {

		window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&ruleType='+ruleType+'&temp=' + Math.random(),'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	}

}



// End - Enhancement
document.getElementById('lobDiv').style.height= "17px";
document.getElementById('businessEntityDiv').style.height= "17px";
document.getElementById('businessGroupDiv').style.height= "17px";
document.getElementById('MarketBusinessUnitDiv').style.height= "17px";
function loadpopup()
{
var emptyString='';
var ruleId=0;
var titleName = 'Benefit Rule Popup';
ewpdModalWindowWithRuleType('../popups/benefitRuleTypePopup.jsp'+getUrl()+'?queryName=locateRuleId&ruleId='+ruleId+'&titleName='+titleName+'&temp='+Math.random(),'ruleIdDiv','benefitComponentEditForm:ruleIdTxtHidden','benefitComponentEditForm:txtStrRuleType',2,1); return false;

}
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="benefitComponent" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('benefitComponentEditForm:duplicateData').value == '')
		document.getElementById('benefitComponentEditForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
	else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('benefitComponentEditForm:duplicateData').value;
</script>

</HTML>
