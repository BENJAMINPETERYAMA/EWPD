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

<TITLE>Edit Benefit</TITLE>
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

</HEAD>
<f:view>
	<BODY>

	<table width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="benefitEditForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>
						<TD width="273" valign="top" class="leftPanel"><!-- Space for Tree  Data	-->
							<DIV class="treeDiv">	
						<jsp:include page="../standardBenefit/standardBenefitTree.jsp"></jsp:include>
						</DIV>
						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message
										value="#{standardBenefitBackingBean.validationMessages}"></w:message>
									</TD>
								</tr>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="25%">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="100%" class="tabActive" align="center"><h:outputText
											value=" General Information" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="25%">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" width="3" height="21" /></td>
										<td width="100%" class="tabNormal" align="center"><h:commandLink
											action="#{benefitDefinitionBackingBean.loadBenefitDefinition}"
											onmousedown="javascript:navigatePageAction(this.id);"
											id="linkToStandardDefinition">
											<h:outputText
												value="#{standardBenefitBackingBean.benefitTypeTab}" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<h:inputHidden id="tabHidden"
									value="#{standardBenefitBackingBean.benefitTypeTab}"></h:inputHidden>
								<td width="25%" id="manInfo">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" width="3" height="21" /></td>
										<td width="100%" class="tabNormal" align="center"><h:commandLink
											action="#{benefitMandateBackingBean.redirectToBenefitMandate}"
											id="linkToMandateInfo"
											onmousedown="javascript:navigatePageAction(this.id);">
											<h:outputText value="Mandate Information" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" width="4" height="21" /></td>
									</tr>
								</table>
								</td>

								<TD width="25%" id="noteTab">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" width="3" height="21" /></td>
										<TD width="100%" class="tabNormal" align="center"><h:commandLink
											action="#{standardBenefitNotesBackingBean.loadStandardBenefitNotes}" id="linkToBenefitNotes"
											onmousedown="javascript:navigatePageAction(this.id);">
											<h:outputText value=" Notes " />
										</h:commandLink></TD>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" width="4" height="21" /></td>
									</tr>
								</table>
								</TD>
								<td width="25%">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								</table>
								</td>
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
							class="outputText" width="100%">
							<TBODY>
								<TR>
									<TD colspan=3>
									<FIELDSET style="width:60%"><LEGEND><FONT color="black">Business
									Domain</FONT></LEGEND>
									<TABLE width="98%" border="0">
										<TR>
											<TD width="46%"><h:outputText
												styleClass="#{standardBenefitBackingBean.requiredLob ? 'mandatoryError': 'mandatoryNormal'}"
												id="LobStar" value="Line of Business*" /></TD>
											<TD width="48%"><!--**Change -->
											<DIV id="lobDiv" class="selectDataDisplayDiv"></DIV>
											<%-- <DIV id="DataTypeDiv" class="selectDivReadOnly" ></DIV>--%>
											<!-- **End --> <h:inputHidden id="txtLob"
												value="#{standardBenefitBackingBean.lob}"></h:inputHidden></TD>
											<TD width="8%"><h:commandButton alt="Select" id="lobButton"
												image="../../images/select.gif" style="cursor: hand"
												rendered="#{standardBenefitBackingBean.status != 'CHECKED_OUT'}"
												onclick="ewpdModalWindow_ewpd('../popups/lineOfBusinessPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Line of Business','lobDiv','benefitEditForm:txtLob',2,2); return false;"
												tabindex="1"></h:commandButton></TD>
										</TR>
										<TR>
											<TD width="46%"><h:outputText
												styleClass="#{standardBenefitBackingBean.requiredBusinessEntity ? 'mandatoryError': 'mandatoryNormal'}"
												id="BusinessEntityStar" value="Business Entity*" /></TD>
											<TD width="48%">
											<DIV id="BusinessEntityDiv" class="selectDataDisplayDiv"></DIV>
											<h:inputHidden id="txtBusinessEntity"
												value="#{standardBenefitBackingBean.businessEntity}"></h:inputHidden>
											</TD>
											<TD width="8%"><h:commandButton alt="Select"
												id="BusinessEntityButton" image="../../images/select.gif"
												style="cursor: hand"
												rendered="#{standardBenefitBackingBean.status != 'CHECKED_OUT'}"
												onclick="ewpdModalWindow_ewpd('../popups/businessEntityPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Business Entity','BusinessEntityDiv','benefitEditForm:txtBusinessEntity',2,2);return false;"
												tabindex="2"></h:commandButton></TD>
										</TR>
										<TR>
											<TD width="46%"><h:outputText
												styleClass="#{standardBenefitBackingBean.requiredBusinessGroup ? 'mandatoryError': 'mandatoryNormal'}"
												id="BusinessGroupStar" value="Business Group*" /></TD>
											<TD width="48%">
											<DIV id="BusinessGroupDiv" class="selectDataDisplayDiv"></DIV>
											<h:inputHidden id="txtBusinessGroup"
												value="#{standardBenefitBackingBean.businessGroup}"></h:inputHidden>
											</TD>
											<TD width="8%"><h:commandButton alt="Select"
												id="BusinessGroupButton" image="../../images/select.gif"
												style="cursor: hand"
												rendered="#{standardBenefitBackingBean.status != 'CHECKED_OUT'}"
												onclick="ewpdModalWindow_ewpd('../popups/businessGroupPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'business group','BusinessGroupDiv','benefitEditForm:txtBusinessGroup',2,2); return false;"
												tabindex="3"></h:commandButton></TD>
										</TR>
<!-- ---------------------------------------------------------------------------------- -->
										<TR>
											<TD width="46%"><h:outputText
												styleClass="#{standardBenefitBackingBean.requiredMarketBusinessUnit ? 'mandatoryError': 'mandatoryNormal'}"
												id="MarketBusinessUnit" value="Market Business Unit*" /></TD>
											<TD width="48%">
											<DIV id="MarketBusinessUnitDiv" class="selectDataDisplayDiv"></DIV>
											<h:inputHidden id="txtMarketBusinessUnit"
												value="#{standardBenefitBackingBean.marketBusinessUnit}"></h:inputHidden>
											</TD>
											<TD width="8%"><h:commandButton alt="Select"
												id="MarketBusinessUnitButton" image="../../images/select.gif"
												style="cursor: hand"
												rendered="#{standardBenefitBackingBean.status != 'CHECKED_OUT'}"
												onclick="ewpdModalWindow_ewpd('../popups/marketBusinessUnit.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Market Business Unit','MarketBusinessUnitDiv','benefitEditForm:txtMarketBusinessUnit',2,2);return false;"
												tabindex="3"></h:commandButton></TD>
										</TR>
<!-- ---------------------------------------------------------------------------------- -->
									</TABLE>
									</FIELDSET>
									</TD>
								</TR>
								<TR>

									<TD width="27%">&nbsp;<h:outputText
										styleClass="#{standardBenefitBackingBean.requiredMinorHeading ? 'mandatoryError': 'mandatoryNormal'}"
										id="MinorHeadingStar" value="Name*" /></TD>
									<TD width="29%"><!--**Change --> <h:inputText
										styleClass="formInputField" maxlength="34" id="minorHeading"
										value="#{standardBenefitBackingBean.minorHeading}"
										tabindex="4"
										rendered="#{standardBenefitBackingBean.status != 'CHECKED_OUT'}" />
									<h:outputText id="name"
										value="#{standardBenefitBackingBean.minorHeading}"
										rendered="#{standardBenefitBackingBean.status == 'CHECKED_OUT'}" />
									<h:inputHidden id="nameHidden"
										value="#{standardBenefitBackingBean.minorHeading}"> 
									</h:inputHidden>
									
									<h:inputHidden id="benefitIdentifier"
										value="#{standardBenefitBackingBean.benefitIdentifier}"> </h:inputHidden>			
									<!-- **End --></TD>
									<td width="44%"><f:verbatim></f:verbatim></td>

								</TR>
								<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher1"
										textComponentName="benefitEditForm:minorHeading"
										rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Benefit Name"
										modalHelperPage="../spellcheck/RapidSpellModalHelper.html"
										showNoErrorsMessage="False" showFinishedMessage="False"
										includeUserDictionaryInSuggestions="True"
										allowAnyCase="True" allowMixedCase="True"
										windowWidth="570" windowHeight="300"
										modal="False" showButton="False"
										windowX="-1" warnDuplicates="False"
										textComponentInterface="Custom">
									</RapidSpellWeb:rapidSpellWebLauncher>

								

								<tr>
									<TD width="27%">&nbsp;<h:outputText
										id="MinorHeadingStarMeaning" value="Benefit Meaning " /></TD>
									<TD width="29%"><h:outputText id="BenefitMeaning"
										value="#{standardBenefitBackingBean.benefitMeaning}" /> <h:inputHidden
										id="benefitMeaningHidden"
										value="#{standardBenefitBackingBean.benefitMeaning}" /></TD>
									<td width="44%"><f:verbatim></f:verbatim></td>
								</tr>


								<tr>
									<td width="27%">&nbsp;<h:outputText id="BenefitTypeStar"
										value="Benefit Type*" /></td>
									<td width="29%">
									<table cellspacing="0" cellpadding="0" border="0">
										<tr>
											<td><h:outputText id="CorpPlanCd_list1"
												value="#{standardBenefitBackingBean.benefitType}" /> <h:inputHidden
												id="benefitTypeList"
												value="#{standardBenefitBackingBean.benefitType}" /></td>
											<td width="63%" align="right"></td>
										</tr>
									</table>
									</td>
								</tr>
								<tr id="sub1" style="display:none;">
									<td width="27%">&nbsp;<h:outputText
										styleClass="#{standardBenefitBackingBean.requiredMandateType ? 'mandatoryError': 'mandatoryNormal'}"
										id="MandateTypeStar" value="MandateType*" /></td>
									<td width="29%">
									<table cellspacing="0" cellpadding="0" border="0">
										<tr>
											<td><h:inputText id="Mandate_type"
												rendered="#{standardBenefitBackingBean.version > 0}"
												styleClass="formInputField" tabindex="5" readonly="true"
												value="#{standardBenefitBackingBean.mandateType}"></h:inputText>
											<h:inputHidden id="mandateTypeHidden"
												value="#{standardBenefitBackingBean.mandateTypeHidden}"></h:inputHidden>
											<h:selectOneMenu id="Mandate_type_list1"
												rendered="#{standardBenefitBackingBean.version < 1}"
												styleClass="formInputField" tabindex="5"
												value="#{standardBenefitBackingBean.mandateType}"
												onchange="setMandateTypeValue();return false;">
												<f:selectItems id="mandateTypeList"
													value="#{ReferenceDataBackingBeanCommon.mandateTypeListForCombo}" />

											</h:selectOneMenu></td>
											<td width="63%" align="right"></td>
										</tr>
									</table>
									</td>
								</tr>
							<tr >
									<TD width="23%">&nbsp;<h:outputText
										styleClass="#{standardBenefitBackingBean.requiredBenefitCategory ? 'mandatoryError': 'mandatoryNormal'}"
										id="BenefitCategory" value="Benefit Category*" /></TD>
									<td width="43%">
									<table cellspacing="0" cellpadding="0" border="0">
										<tr>
											<td><h:outputText id="benefitCategoryHidden"
												rendered="#{standardBenefitBackingBean.status == 'CHECKED_OUT'}"
												value="#{standardBenefitBackingBean.benefitCategoryHidden}"></h:outputText>
 											<h:inputHidden id="benefitCategory"
												value="#{standardBenefitBackingBean.benefitCategoryHidden}"></h:inputHidden>

											<h:selectOneMenu id="benefitCategoryList"
												rendered="#{standardBenefitBackingBean.status != 'CHECKED_OUT'}"
												styleClass="formInputField" tabindex="6"
												value="#{standardBenefitBackingBean.benefitCategory}">
												<f:selectItems id="benefitCategoryList1"
													value="#{standardBenefitBackingBean.benefitCategoryListForCombo}" /> 
												<%-- This code will direct the control to Standardbenefit BB from where it calls ReferencedataBB.
													 This modifiction was done for maintaining the value in benefittype dropdown to prevent the 
													 validation error. Please dont modify the code  --%>

											</h:selectOneMenu></td>
											<td width="63%" align="right"></td>
										</tr>
									</table>
									</td>
							</tr>


                                     <tr><TD valign="top" width="27%">&nbsp;<h:outputText
										
										id="descriptionStar" value="Description*" /></TD>
									<TD width="29%"><h:inputTextarea
										styleClass="formTxtAreaField_GeneralDesc" id="txtDescription"
										value="#{standardBenefitBackingBean.description}" tabindex="8"></h:inputTextarea></TD>
									<td width="44%"><f:verbatim></f:verbatim></td></tr>

								<!--  	<tr id="sub3" style="display:none;">

									<TD width="24%">&nbsp;<h:outputText value="Mandate Type" /></TD>
									<td width="42%">
									<table cellspacing="0" cellpadding="0" border="0">
										<tr>
											<TD width="179"><%--<h:inputText id="mandType1"
												value="#{standardBenefitBackingBean.mandateType}"
												styleClass="formInputFieldReadOnly" readonly="true" />--%>
										<%--<h:outputText id="mandType1"
												value="#{standardBenefitBackingBean.mandateType}" />--%>
											<h:inputTextarea styleClass="selectDivReadOnly"
										id="mandType1"
										value="#{standardBenefitBackingBean.mandateType}"  
										readonly="true" style="border:0"></h:inputTextarea>
											 <h:inputHidden
												id="mandTypeHidden"
												value="#{standardBenefitBackingBean.mandateType}">
											</h:inputHidden><SCRIPT>copyHiddenToInputText('benefitEditForm:mandTypeHidden','benefitEditForm:mandType1',2);</SCRIPT></TD>
										</tr>
									</table>
									</td>
								</tr>
								<tr id="sub4" style="display:none;">

									<TD width="24%">&nbsp;<h:outputText value="State" /></TD>
									<td width="42%">
									<table cellspacing="0" cellpadding="0" border="0">
										<tr>
											<TD><%--<h:inputText id="stateCde1"
												value="#{standardBenefitBackingBean.selectedStateId}"
												styleClass="formInputFieldReadOnly" readonly="true" />--%>
										<h:inputTextarea styleClass="selectDivReadOnly"
										id="stateCde1"
										value="#{standardBenefitBackingBean.selectedStateId}"  
										readonly="true" style="border:0"></h:inputTextarea>									
										<%--<h:outputText id="stateCde1"
												value="#{standardBenefitBackingBean.selectedStateId}" />--%>  
									<h:inputHidden
												id="stateCdeHidden"
												value="#{standardBenefitBackingBean.selectedStateId}">
											</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitEditForm:stateCdeHidden','benefitEditForm:stateCde1',2); </SCRIPT>
											</TD>
										</tr>
									</table>
									</td>
								</tr> -->


								<tr>
									<td width="27%"><BR>
									&nbsp;<h:outputText
										styleClass="#{standardBenefitBackingBean.requiredRule ? 'mandatoryError': 'mandatoryNormal'}"
										id="RuleStar" value="Benefit Rule ID*" /></td>
									<td width="29%">
									<table cellspacing="0" cellpadding="0" border="0" width="110%">
										<tr>
											<td width="56%">&nbsp;
											<DIV id="RuleDiv" class="selectDataDisplayDiv"></DIV>
											<h:inputHidden id="txtRule"
												value="#{standardBenefitBackingBean.rule}"></h:inputHidden>
											<h:inputHidden id="txtStrRuleType" value="#{standardBenefitBackingBean.strRuleType}"></h:inputHidden>
									
											</TD>
											<TD width="38%"><BR>
											<h:commandButton alt="Select" id="RuleButton"
												image="../../images/select.gif" style="cursor: hand"
												tabindex="9"
												onclick="loadpopup();return false;"
												></h:commandButton>&nbsp;
											<h:commandButton alt="View" id="viewButton"
												image="../../images/view.gif"
												onclick="viewAction();return false;" tabindex="10"/></TD>
										</tr>
									



									</table>
									</td>
								</tr>

								<tr>
									
								</tr>

								<TR>
									<TD colspan="3">
									<FIELDSET style="width:60%"><LEGEND><FONT color="black">Benefit Level Scope</FONT></LEGEND>
									<TABLE width="98%">
										<TR>
											<TD width="46%"><h:outputText
												styleClass="#{standardBenefitBackingBean.requiredTerm ? 'mandatoryError': 'mandatoryNormal'}"
												id="termEditStar" value="Term*" /></TD>
											<TD width="48%">
											<DIV id="TermDiv" class="selectDataDisplayDiv"></DIV>
											<h:inputHidden id="txtTerm"
												value="#{standardBenefitBackingBean.term}"></h:inputHidden>
											</TD>
											<TD width="8%"><h:commandButton alt="Select" id="TermButton"
												image="../../images/select.gif" style="cursor: hand"
												onclick="getSelectedDomainReferenceData('../standardbenefitpopups/benefitTermPopUp.jsp','benefitEditForm','txtMarketBusinessUnit','txtLob','txtBusinessEntity','txtBusinessGroup','TermDiv','benefitEditForm:txtTerm',2,2,'term');setRefDataUseFlag('benefitEditForm', 'txtTerm', 'TermDiv'); return false;"
												tabindex="11"></h:commandButton></TD>
										</TR>
										<TR>
											<TD width="46%"><h:outputText
												styleClass="#{standardBenefitBackingBean.requiredQualifier ? 'mandatoryError': 'mandatoryNormal'}"
												id="QualifierEditStar" value="Qualifier" /></TD>
											<TD width="48%">
											<DIV id="QualifierDiv" class="selectDataDisplayDiv"></DIV>
											<h:inputHidden id="txtQualifier"
												value="#{standardBenefitBackingBean.qualifier}"></h:inputHidden>
											</TD>
											<TD width="8%"><h:commandButton alt="Select"
												id="QualifierButton" image="../../images/select.gif"
												style="cursor: hand"
												onclick="getSelectedBenefitQualifierDomainData('../popups/searchPopUpMultiSelect.jsp','benefitEditForm','txtLob','txtBusinessEntity','txtBusinessGroup','QualifierDiv','benefitEditForm:txtQualifier',2,2,'qualifier');setRefDataUseFlag('benefitEditForm', 'txtQualifier', 'QualifierDiv'); return false;"
												tabindex="12"></h:commandButton></TD>
										</TR>
										<TR>
											<TD width="46%"><h:outputText
												styleClass="#{standardBenefitBackingBean.requiredProviderArrangement ? 'mandatoryError': 'mandatoryNormal'}"
												id="ProviderArrangementEditStar"
												value="Provider Arrangement*" /></TD>
											<TD width="48%">
											<DIV id="ProviderArrangementDiv" class="selectDataDisplayDiv"></DIV>
											<h:inputHidden id="txtProviderArrangement"
												value="#{standardBenefitBackingBean.providerArrangement}"></h:inputHidden>
											</TD>
											<TD width="8%"><h:commandButton alt="Select"
												id="ProviderArrangementButton"
												image="../../images/select.gif" style="cursor: hand"
												onclick="getSelectedDomainReferenceData('../standardbenefitpopups/providerArrangementPopUp.jsp','benefitEditForm','txtMarketBusinessUnit','txtLob','txtBusinessEntity','txtBusinessGroup','ProviderArrangementDiv','benefitEditForm:txtProviderArrangement',2,2,'provider arrangement'); setRefDataUseFlag('benefitEditForm', 'txtProviderArrangement', 'ProviderArrangementDiv');return false;"
												tabindex="13"></h:commandButton></TD>
										</TR>
										<TR>
											<TD width="46%"><h:outputText
												styleClass="#{standardBenefitBackingBean.requiredDataType ? 'mandatoryError': 'mandatoryNormal'}"
												id="DataTypeEditStar" value="Data Type*" /></TD>
											<TD width="48%">
											<DIV id="DataTypeDiv" class="selectDataDisplayDiv"></DIV>
											<h:inputHidden id="txtDataType"
												value="#{standardBenefitBackingBean.dataType}"></h:inputHidden>
											</TD>
											<TD width="8%"><h:commandButton alt="Select"
												id="DataTypeButton" image="../../images/select.gif"
												style="cursor: hand"
												onclick="ewpdModalWindow_ewpd('../standardbenefitpopups/dataTypeSelectPopup.jsp'+getUrl(),'DataTypeDiv','benefitEditForm:txtDataType',2,2); return false;"
												tabindex="14"></h:commandButton></TD>
										</TR>
													
								
									</TABLE>
									</FIELDSET>
									</TD>
								</TR>

								<tr>
									<td valign="top" width="27%"><span class="mandatoryNormal"
										id="creationDateId">&nbsp;</span><h:outputText
										value="Created By" /></td>
									<td width="29%"><h:outputText
										value="#{standardBenefitBackingBean.createdUser}" /> <h:inputHidden
										id="createdUserHidden"
										value="#{standardBenefitBackingBean.createdUser}" /></td>
									<td width="44%"><f:verbatim></f:verbatim></td>
								</tr>


								<tr>
									<td valign="top" width="27%"><span class="mandatoryNormal"
										id="createdBy">&nbsp;</span><h:outputText value="Created Date" /></td>
									<td width="29%"><h:outputText
										value="#{standardBenefitBackingBean.createdTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="createdTimestampHidden"
										value="#{standardBenefitBackingBean.createdTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden> <h:inputHidden id="time"
										value="#{requestScope.timezone}">
									</h:inputHidden></td>
									<td width="44%"><f:verbatim></f:verbatim></td>
								</tr>

								<tr>
									<td valign="top" width="27%"><span class="mandatoryNormal"
										id="updationDate">&nbsp;</span><h:outputText
										value="Last Updated By" /></td>
									<td width="29%"><h:outputText
										value="#{standardBenefitBackingBean.lastUpdatedUser}" /> <h:inputHidden
										id="lastUpdatedUserHidden"
										value="#{standardBenefitBackingBean.lastUpdatedUser}" /></td>
									<td width="44%"><f:verbatim></f:verbatim></td>
								</tr>


								<tr>
									<td valign="top" width="27%"><span class="mandatoryNormal"
										id="updateBy">&nbsp;</span><h:outputText
										value="Last Updated Date" /></td>
									<td width="29%"><h:outputText
										value="#{standardBenefitBackingBean.lastUpdatedTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="lastUpdatedTimestampHidden"
										value="#{standardBenefitBackingBean.lastUpdatedTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden></td>
									<td width="44%"><f:verbatim></f:verbatim></td>
								</tr>
									<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher2"
										textComponentName="benefitEditForm:txtDescription"
										rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Benefit Description"
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

								<tr>
									<TD width="27%">
										<h:commandButton value="Save" id="editBenefit"

											styleClass="wpdButton" onclick="return runSpellCheck();" tabindex="15"
											onmousedown="javascript:savePageAction(this.id);javascript:setBenefitIdentifier();">

										</h:commandButton>
										<h:commandLink id="saveBenefit"
											style="hidden" action="#{standardBenefitBackingBean.editBenefit}">
										</h:commandLink>
									</TD>
									<td width="29%"><f:verbatim></f:verbatim></td>
									<td width="44%"><f:verbatim></f:verbatim></td>
								</tr>

							</TBODY>
						</TABLE>
						</fieldset>
						<!--	End of Page data	--> <BR>
						<FIELDSET
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<table border="0" cellspacing="0" cellpadding="3" width="100%">
							<tr>
								<td width="15"><h:selectBooleanCheckbox id="checkall"
									value="#{standardBenefitBackingBean.checkin}" tabindex="16">
								</h:selectBooleanCheckbox></td>
								<td align="left"><h:outputText value="Check In" /></td>
								<td align="left" width="127">
								<table Width=100%>
									<tr>
										<td><h:outputText value="State" /></td>
										<TD><h:outputText value=":" /></TD>
										<td><h:outputText value="#{standardBenefitBackingBean.state}" /></td>
										<h:inputHidden id="stateHidden"
											value="#{standardBenefitBackingBean.state}" />
									</tr>
									<tr>
										<td><h:outputText value="Status" /></td>
										<TD><h:outputText value=":" /></TD>
										<td><h:outputText value="#{standardBenefitBackingBean.status}" /></td>
										<h:inputHidden id="statusHidden"
											value="#{standardBenefitBackingBean.status}" />
									</tr>
									<tr>
										<td><h:outputText value="Version" /></td>
										<TD><h:outputText value=":" /></TD>
										<td><h:outputText id="version"
											value="#{standardBenefitBackingBean.version}" /></td>
										<h:inputHidden id="versionHidden"
											value="#{standardBenefitBackingBean.version}" />
									</tr>
								</table>
								</td>
							</tr>
						</table>
						</FIELDSET>
						<BR>
						&nbsp;&nbsp;
							<h:commandButton value="Done"
								styleClass="wpdButton" onclick="return done();" tabindex="17">
							</h:commandButton>
							<h:commandLink id="checkinBenefit"
								style="hidden" action="#{standardBenefitBackingBean.done}">
							</h:commandLink>
						</TD>
					</TR>
				</table>
				<!-- Space for hidden fields -->
				<h:inputHidden id="hiddenStandardBenefitKey"
					value="#{standardBenefitBackingBean.standardBenefitKey}"></h:inputHidden>
				<h:inputHidden id="duplicateData"
					value="#{standardBenefitBackingBean.duplicateData}"></h:inputHidden>
				<h:commandLink id="hiddenLnk1"
					style="display:none; visibility: hidden;">
					<f:verbatim />
				</h:commandLink>
				<!-- End of hidden fields  -->
			</h:form></td>
		</tr>
		<TR>
			<TD><%@include file="../navigation/bottom.jsp"%></TD>
		</TR>
	</table>
	</BODY>
</f:view>

<script>


function setBenefitIdentifier(){

var minorHeadingVal = document.getElementById('benefitEditForm:minorHeading');
if(null==minorHeadingVal || minorHeadingVal == ''){
	document.getElementById('benefitEditForm:nameHidden').value = '';
}else{
	document.getElementById('benefitEditForm:nameHidden').value = document.getElementById('benefitEditForm:minorHeading').value;
}

	var minorHeadingObj = document.getElementById('benefitEditForm:minorHeading');
	if(null != minorHeadingObj){
		var hiddenBenefitIdentifierObj = document.getElementById('benefitEditForm:benefitIdentifier');
		if(null != hiddenBenefitIdentifierObj)
 			hiddenBenefitIdentifierObj.value = minorHeadingObj.value;
	}
}



 IGNORED_FIELD1='benefitEditForm:duplicateData';
  IGNORED_FIELD2='benefitEditForm:nameHidden';
  IGNORED_FIELD3='benefitEditForm:benefitIdentifier';
if(null != document.getElementById('benefitEditForm:name')){
             document.getElementById('benefitEditForm:name').innerHTML = document.getElementById('benefitEditForm:nameHidden').value;
}

	copyHiddenToDiv_ewpd1('benefitEditForm:txtRule','RuleDiv',2,1);
	copyHiddenToDiv_ewpd('benefitEditForm:txtLob','lobDiv',2,2); 
	copyHiddenToDiv_ewpd('benefitEditForm:txtBusinessEntity','BusinessEntityDiv',2,2); 
	copyHiddenToDiv_ewpd('benefitEditForm:txtBusinessGroup','BusinessGroupDiv',2,2);
	copyHiddenToDiv_ewpd('benefitEditForm:txtMarketBusinessUnit','MarketBusinessUnitDiv',2,2);
	copyHiddenToDiv_ewpd('benefitEditForm:txtTerm','TermDiv',2,2); 
	copyHiddenToDiv_ewpd('benefitEditForm:txtQualifier','QualifierDiv',2,2); 
	copyHiddenToDiv_ewpd('benefitEditForm:txtProviderArrangement','ProviderArrangementDiv',2,2); 
	copyHiddenToDiv_ewpd('benefitEditForm:txtDataType','DataTypeDiv',2,2);
//	copyHiddenToDiv_ewpd('benefitEditForm:txtRule','RuleDiv',2,1);

appendToRefDataVariablesSelectedRefDataName('txtTerm', 'TermDiv');
appendToRefDataVariablesSelectedRefDataName('txtQualifier', 'QualifierDiv');
appendToRefDataVariablesSelectedRefDataName('txtProviderArrangement', 'ProviderArrangementDiv');
var version;
version = document.getElementById("benefitEditForm:version");

// alert("val:"+version);
//alert(version)
if(version.innerHTML==0)
{
//alert('inside version');
	getContractType();
	//getMandateType();
}
else{
//alert("method calls");
	// alert("version !0");
	getContractType1()
	//getMandateType1();
}

	var checkin = false;
	function done(){
		var minorHeadingVal = document.getElementById('benefitEditForm:minorHeading');
		if(null==minorHeadingVal || minorHeadingVal == ''){
			document.getElementById('benefitEditForm:nameHidden').value = '';
		}else{
			document.getElementById('benefitEditForm:nameHidden').value = document.getElementById('benefitEditForm:minorHeading').value;
		}
		checkin=true;
		return runSpellCheck();
	}

	function runSpellCheck(){
		var version = document.getElementById('benefitEditForm:versionHidden').value;
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
				document.getElementById('benefitEditForm:checkinBenefit').click();	
			else
				document.getElementById('benefitEditForm:saveBenefit').click();	
		checkin=false;
	}
function getContractType()
{
//alert('inseide Contract type')
var a;
var i;
var obj;
obj = document.getElementById("benefitEditForm:CorpPlanCd_list1");
//alert('object value is'+ obj);
i= obj.innerHTML;

if(i== "MANDATE")
	{
	sub1.style.display='';
	}
	
	else 
	{
//alert('inside else')
	sub1.style.display='none';
//alert(sub2.style.display);
//alert(sub1.style.display);
	}

}


function getContractType1()
{
var i;
var obj;
obj = document.getElementById("benefitEditForm:CorpPlanCd_list1");
//alert('inside getContractType1()'+ obj);
i= obj.innerHTML;
//alert('inside value of i '+ i);
	if(i=="MANDATE")
	{
sub1.style.display='';
//alert(document.getElementById("benefitEditForm:mandType1").value);

//alert('inside')
	//sub3.style.display='';
	}
	
	else 
	{
sub1.style.display='none';
	//sub3.style.display='none';
	//sub4.style.display='none';
	}
}


function getMandateType()
{
//alert('getMandateType()')
var a;
var i;
var obj;
obj = document.getElementById("benefitEditForm:Mandate_type_list1");

//document.getElementById("benefitEditForm:mandType1").value = obj.value;
//document.getElementById("benefitEditForm:mandTypeHidden").value = obj.value;

i= obj.selectedIndex;
a=obj.options[i].value;
alert(a);
//alert("inside getMandateType() i = "+ i)
}

function setMandateTypeValue(){
var a;
var i;
var obj;
obj = document.getElementById("benefitEditForm:Mandate_type_list1");
i= obj.selectedIndex;
a=obj.options[i].value;
document.getElementById("benefitEditForm:mandateTypeHidden").value = a;
}

function getMandateType1()
{
var i;
var obj;
obj = document.getElementById("benefitEditForm:Mandate_type_list1");
i = obj.value



	//if(i==2 || i == 3)
	//{
				//alert("2 or 3");
				//if(i==2)
				//	document.getElementById("benefitEditForm:mandType1").value = "State";
				//else
					//document.getElementById("benefitEditForm:mandType1").value = "ET";

//	sub4.style.display='';
	//}
	
//	else 
//	{

	//document.getElementById("benefitEditForm:mandType1").value = "Federal";
	//sub3.style.display='none';
//	sub4.style.display='none';
	//}

}


//newly added

var a;
var i;
var obj;
obj = document.getElementById("benefitEditForm:Mandate_type_list1");
obj1 = document.getElementById("benefitEditForm:Mandate_type");
if(obj != null){
	i= obj.selectedIndex;
	a=obj.options[i].value;
	document.getElementById("benefitEditForm:mandateTypeHidden").value = a;

}else if(obj1 != null){

	document.getElementById("benefitEditForm:mandateTypeHidden").value = obj1.value;

}

// end

hideTab();
function hideTab(){
	var tab;
	tab = document.getElementById("benefitEditForm:tabHidden").value ;
	if(tab=="Standard Definition"){

		manInfo.style.display='none';
		noteTab.style.display='';
	}
	else{
		manInfo.style.display='';
		noteTab.style.display='none';
	}
}
function copyToHidden(){
if(null != document.getElementById("benefitEditForm:minorHeading")){
	var name = document.getElementById("benefitEditForm:minorHeading").value;
	document.getElementById("benefitEditForm:nameHidden").value = name;
}
}
function viewAction(){
	
	var ruleIdStr = document.getElementById('benefitEditForm:txtRule').value;
	var ruleType = document.getElementById('benefitEditForm:txtStrRuleType').value;
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
function loadpopup()
{
var emptyString='';
var ruleId=0;
var titleName = 'Benefit Rule Popup';
ewpdModalWindowWithRuleType('../popups/benefitRuleTypePopup.jsp'+getUrl()+'?queryName=locateRuleId&ruleId='+ruleId+'&titleName='+titleName+'&temp='+Math.random(),'RuleDiv','benefitEditForm:txtRule','benefitEditForm:txtStrRuleType',2,1); return false;
}


//alert("outer"+ document.getElementById("benefitEditForm:benefitCategoryHidden").value);
//document.getElementById("benefitEditForm:benefitCategory").value = document.getElementById("benefitEditForm:benefitCategoryHidden").value;
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="standardBenefit" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>

<script>

	if(document.getElementById('benefitEditForm:duplicateData').value == ''){
		document.getElementById('benefitEditForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
//	alert("!");
}
	else{
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('benefitEditForm:duplicateData').value;
	//alert("2");
}
</script>
</HTML>

