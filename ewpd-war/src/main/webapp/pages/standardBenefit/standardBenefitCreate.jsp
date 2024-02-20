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

<TITLE>Create Benefit</TITLE>
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
	<BODY
		onkeypress="return submitOnEnterKey('benifitCreateForm:createButton');">
	<table width="100%" cellpadding="0" cellspacing="0">

		<TR>
			<TD><h:inputHidden id="dummy"
				value="#{standardBenefitBackingBean.dummyVar}"></h:inputHidden> <jsp:include
				page="../navigation/top.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="benifitCreateForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<DIV class="treeDiv" style="height:500px"><!-- Space for Tree  Data	--></DIV>

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
										<td width="100%" class="tabActive"><h:outputText
											value=" General Information" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="3" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="100%"></td>
							</tr>
						</table>
						<!-- End of Tab table -->
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

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	-->
						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText" width="100%">
							<TBODY>
								<TR>
									<TD colspan=3>
									<FIELDSET style="width:60%"><LEGEND><FONT color="black">Business
									Domain</FONT></LEGEND>
									<TABLE width="98%">
										<TR>
											<TD width="46%"><h:outputText
												styleClass="#{standardBenefitBackingBean.requiredLob ? 'mandatoryError': 'mandatoryNormal'}"
												id="LobStar" value="Line of Business*" /></TD>
											<TD width="48%">
											<DIV id="lobDiv" class="selectDataDisplayDiv"></DIV>
											<h:inputHidden id="txtLob"
												value="#{standardBenefitBackingBean.lob}"></h:inputHidden></TD>
											<TD width="8%"><h:commandButton
												alt="Select" id="lobButton" image="../../images/select.gif"
												style="cursor: hand"
												onclick="ewpdModalWindow_ewpd('../popups/lineOfBusinessPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Line of Business','lobDiv','benifitCreateForm:txtLob',2,2); return false;"
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
											<TD width="8%"><h:commandButton
												alt="Select" id="BusinessEntityButton"
												image="../../images/select.gif" style="cursor: hand"
												onclick="ewpdModalWindow_ewpd('../popups/businessEntityPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Business Entity','BusinessEntityDiv','benifitCreateForm:txtBusinessEntity',2,2);return false;"
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
											<TD width="8%"><h:commandButton
												alt="Select" id="BusinessGroupButton"
												image="../../images/select.gif" style="cursor: hand"
												onclick="ewpdModalWindow_ewpd('../popups/businessGroupPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'business group','BusinessGroupDiv','benifitCreateForm:txtBusinessGroup',2,2);return false;"
												tabindex="3"></h:commandButton></TD>
										</TR>
<!-- ----------------------------------------------------------------------------------------------------------------- -->
										<TR>
											<TD width="46%"><h:outputText
												styleClass="#{standardBenefitBackingBean.requiredMarketBusinessUnit ? 'mandatoryError': 'mandatoryNormal'}"
												id="MarketBusinessUnit" value="Market Business Unit*" /></TD>
											<TD width="48%">
											<DIV id="MarketBusinessUnitDiv" class="selectDataDisplayDiv"></DIV>
											<h:inputHidden id="txtMarketBusinessUnit"
												value="#{standardBenefitBackingBean.marketBusinessUnit}"></h:inputHidden>
											</TD>
											<TD width="8%"><h:commandButton
												alt="Select" id="MarketBusinessUnitButton"
												image="../../images/select.gif" style="cursor: hand"
												onclick="ewpdModalWindow_ewpd('../popups/marketBusinessUnit.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Market Business Unit','MarketBusinessUnitDiv','benifitCreateForm:txtMarketBusinessUnit',2,2);return false;"
												tabindex="3"></h:commandButton></TD>
										</TR>
<!-- ------------------------------------------------------------------------------------------------------------------ -->
									</TABLE>
									</FIELDSET>
									</TD>
								</TR>

								<TR>
									<TD width="27%">&nbsp;<h:outputText
										styleClass="#{standardBenefitBackingBean.requiredMinorHeading ? 'mandatoryError': 'mandatoryNormal'}"
										id="MinorHeadingStar" value="Name*" /></TD>
									<TD width="29%"><h:inputText styleClass="formInputField"
										maxlength="34" tabindex="4" id="MinorHeadingTxt"
										value="#{standardBenefitBackingBean.minorHeading}" /></TD>
									<TD width="44%"></TD>
								</TR>
								<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher1"
										textComponentName="benifitCreateForm:MinorHeadingTxt"
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




								<%--<tr>
									<td width="24%">&nbsp;<h:outputText
										styleClass="#{standardBenefitBackingBean.requiredBenefitType ? 'mandatoryError': 'mandatoryNormal'}"
										id="BenefitTypeStar" value="Benefit Type*" /></td>
									<td width="42%">
									<table cellspacing="0" cellpadding="0" border="0">
										<tr>
											<td><h:selectOneMenu id="CorpPlanCd_list1" rendered="#{!standardBenefitBackingBean.checkForCopy}"
												styleClass="formInputField" tabindex="5"
												value="#{standardBenefitBackingBean.benefitType}"
												onchange="getContractType();">
												<f:selectItems id="benefitTypeList"
													value="#{standardBenefitBackingBean.benefitTypeListForCombo}" />

											</h:selectOneMenu>
											<h:inputText id="benefitType_copy" rendered="#{standardBenefitBackingBean.checkForCopy}"
												value="#{standardBenefitBackingBean.benefitType}" readonly="true"/> <h:inputHidden
												id="benefitType_copyhidden"
												value="#{standardBenefitBackingBean.benefitTypeHidden}" /></td>
											<td width="63%" align="right"></td>
										</tr>
									</table>
									</td>
								</tr> --%>


								<tr>
									<td width="27%">&nbsp;<h:outputText
										styleClass="#{standardBenefitBackingBean.requiredBenefitType ? 'mandatoryError': 'mandatoryNormal'}"
										id="BenefitTypeStar" value="Benefit Type*" /></td>
									<td width="29%">
									<table cellspacing="0" cellpadding="0" border="0">
										<tr>
											<td><h:inputText id="benefitType_copy"
												rendered="#{standardBenefitBackingBean.checkForCopy}"
												styleClass="formInputField" tabindex="5" readonly="true"
												value="#{standardBenefitBackingBean.benefitType}"></h:inputText>
											<h:inputHidden id="benefitType_copyhidden"
												value="#{standardBenefitBackingBean.benefitTypeHidden}"></h:inputHidden>
											<h:selectOneMenu id="CorpPlanCd_list1"
												rendered="#{!standardBenefitBackingBean.checkForCopy}"
												styleClass="formInputField" tabindex="6"
												value="#{standardBenefitBackingBean.benefitType}"
												onchange="getContractType();return false;">
												<f:selectItems id="benefitTypeList"
													value="#{standardBenefitBackingBean.benefitTypeListForCombo}" />
												<%-- This code will direct the control to Standardbenefit BB from where it calls ReferencedataBB.
													 This modifiction was done for maintaining the value in benefittype dropdown to prevent the 
													 validation error. Please dont modify the code  --%>

											</h:selectOneMenu></td>
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
												styleClass="formInputField" tabindex="7" readonly="true"
												value="#{standardBenefitBackingBean.mandateType}"></h:inputText>
											<h:inputHidden id="mandateTypeHidden"
												value="#{standardBenefitBackingBean.mandateTypeHidden}"></h:inputHidden>
											<h:selectOneMenu id="Mandate_type_list1"
												rendered="#{standardBenefitBackingBean.version < 1}"
												styleClass="formInputField" tabindex="8"
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
										id="BenefitCategoryTxt" value="Benefit Category*" /></TD>
									<td width="43%">
									<table cellspacing="0" cellpadding="0" border="0">
										<tr>
											<td>
											
											<h:selectOneMenu id="benefitCategoryList"
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
								

								<TR>
									<TD width="27%" valign="top">&nbsp;<h:outputText
										styleClass="#{standardBenefitBackingBean.requiredDesription ? 'mandatoryError': 'mandatoryNormal'}"
										id="descriptionStar" value="Description*" /></TD>

									<TD width="29%"><h:inputTextarea
										styleClass="formTxtAreaField_GeneralDesc" id="txtDescription"
										value="#{standardBenefitBackingBean.description}" tabindex="9"></h:inputTextarea></TD>
									<td width="44%"><f:verbatim></f:verbatim></td>
								</tr>

								<tr>
									<td width="27%" >&nbsp;<h:outputText
										styleClass="#{standardBenefitBackingBean.requiredRule ? 'mandatoryError': 'mandatoryNormal'}"
										id="RuleStar" value="Benefit Rule ID*" /></td>
									<td width="29%">
									<table cellspacing="0" cellpadding="0" border="0" width="110%">
										<tr>
											<td width="59%">
											<DIV id="RuleDiv" class="selectDataDisplayDiv"></DIV>
											<h:inputHidden id="txtRule"
												value="#{standardBenefitBackingBean.rule}"></h:inputHidden>
											<h:inputHidden id="txtStrRuleType" value="#{standardBenefitBackingBean.strRuleType}"></h:inputHidden>
											</TD>
											<TD width="41%"><h:commandButton alt="Select"
												id="RuleButton" image="../../images/select.gif"
												style="cursor: hand" tabindex="10"
												onclick="loadpopup();return false;"></h:commandButton>&nbsp;
											<h:commandButton alt="View" id="viewButton"
												image="../../images/view.gif"
												onclick="viewAction();return false;" tabindex="11" /></TD>
										</tr>
									</table>
									</td>
								</tr>

								<TR>
									<TD colspan="3">
									<FIELDSET style="width:60%"><LEGEND><FONT color="black">Benefit
									Level Scope</FONT></LEGEND>
									<TABLE width="98%">
										<TR>
											<TD width="46%"><h:outputText
												styleClass="#{standardBenefitBackingBean.requiredTerm ? 'mandatoryError': 'mandatoryNormal'}"
												id="TermStar" value="Term*" /></TD>
											<TD width="48%">
											<DIV id="TermDiv" class="selectDataDisplayDiv"></DIV>
											<h:inputHidden id="txtTerm"
												value="#{standardBenefitBackingBean.term}"></h:inputHidden>
											</TD>
											<TD width="8%"><h:commandButton
												alt="Select" id="TermButton" image="../../images/select.gif"
												style="cursor: hand"
												onclick="getSelectedDomainReferenceData('../standardbenefitpopups/benefitTermPopUp.jsp',
												'benifitCreateForm', 'txtMarketBusinessUnit','txtLob','txtBusinessEntity','txtBusinessGroup','TermDiv','benifitCreateForm:txtTerm',2,2,'term');setRefDataUseFlag('benifitCreateForm', 'txtTerm', 'TermDiv'); return false;"
												tabindex="12"></h:commandButton></TD>
										</TR>
										<TR>
											<TD width="46%"><h:outputText
												styleClass="#{standardBenefitBackingBean.requiredQualifier ? 'mandatoryError': 'mandatoryNormal'}"
												id="QualifierStar" value="Qualifier" /></TD>
											<TD width="48%">
											<DIV id="QualifierDiv" class="selectDataDisplayDiv"></DIV>
											<h:inputHidden id="txtQualifier"
												value="#{standardBenefitBackingBean.qualifier}"></h:inputHidden>
											</TD>
											<TD width="8%"><h:commandButton
												alt="Select" id="QualifierButton"
												image="../../images/select.gif" style="cursor: hand"
												onclick="getSelectedBenefitQualifierDomainData('../popups/searchPopUpMultiSelect.jsp','benifitCreateForm','txtLob','txtBusinessEntity','txtBusinessGroup','QualifierDiv','benifitCreateForm:txtQualifier',2,2,'qualifier');setRefDataUseFlag('benifitCreateForm', 'txtQualifier', 'QualifierDiv'); return false;"
												tabindex="13"></h:commandButton></TD>
										</TR>
										<TR>
											<TD width="46%"><h:outputText
												styleClass="#{standardBenefitBackingBean.requiredProviderArrangement ? 'mandatoryError': 'mandatoryNormal'}"
												id="ProviderArrangementStar" value="Provider Arrangement*" />
											</TD>
											<TD width="48%">
											<DIV id="ProviderArrangementDiv" class="selectDataDisplayDiv"></DIV>
											<h:inputHidden id="txtProviderArrangement"
												value="#{standardBenefitBackingBean.providerArrangement}"></h:inputHidden>
											</TD>
											<TD width="8%"><h:commandButton
												alt="Select" id="ProviderArrangementButton"
												image="../../images/select.gif" style="cursor: hand"
												onclick="getSelectedDomainReferenceData('../standardbenefitpopups/providerArrangementPopUp.jsp','benifitCreateForm','txtMarketBusinessUnit', 'txtLob','txtBusinessEntity','txtBusinessGroup','ProviderArrangementDiv','benifitCreateForm:txtProviderArrangement',2,2,'provider arrangement');setRefDataUseFlag('benifitCreateForm', 'txtProviderArrangement', 'ProviderArrangementDiv'); return false;"
												tabindex="14"></h:commandButton></TD>
										</TR>
										<TR>
											<TD width="46%"><h:outputText
												styleClass="#{standardBenefitBackingBean.requiredDataType ? 'mandatoryError': 'mandatoryNormal'}"
												id="DataTypeStar" value="Data Type*" /></TD>
											<TD width="48%">
											<DIV id="DataTypeDiv" class="selectDataDisplayDiv"></DIV>
											<h:inputHidden id="txtDataType"
												value="#{standardBenefitBackingBean.dataType}"></h:inputHidden>
											</TD>
											<TD width="8%"><h:commandButton
												alt="Select" id="DataTypeButton"
												image="../../images/select.gif" style="cursor: hand"
												onclick="ewpdModalWindow_ewpd('../standardbenefitpopups/dataTypeSelectPopup.jsp'+getUrl(),'DataTypeDiv','benifitCreateForm:txtDataType',2,2); return false;"
												tabindex="15"></h:commandButton></TD>
										</TR>
									</TABLE>
									</FIELDSET>
									</TD>
								</TR>
									<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher2"
										textComponentName="benifitCreateForm:txtDescription"
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

								<TR>
									<TD width="23%">
										<h:commandButton value="Create" id="createButton"
											styleClass="wpdButton" onclick="return runSpellCheck();" tabindex="16">
										</h:commandButton>
										<h:commandLink id="createBenefit"
											style="hidden" action="#{standardBenefitBackingBean.createBenefit}">
										</h:commandLink>
									</TD>

								</TR>
							</TBODY>
						</TABLE>
						<!--	End of Page data	--></fieldset>
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
				<h:inputHidden id="version"
					value="#{standardBenefitBackingBean.version}" />
				<h:inputHidden id="selectedStdBenKey"
					value="#{standardBenefitBackingBean.selectedStdBenKey}" />
				<h:inputHidden id="selectedDataType"
					value="#{standardBenefitBackingBean.selectedDataType}" />
				<h:inputHidden id="selectedParentSystemId"
					value="#{standardBenefitBackingBean.selectedParentSystemId}" />
				<h:inputHidden id="copyFlag"
					value="#{standardBenefitBackingBean.copyFlag}" />
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

document.getElementById('benifitCreateForm:MinorHeadingTxt').focus(); // for on load default selection

/*var copyFlagStatus = document.getElementById('benifitCreateForm:copyFlag'); 
if(copyFlagStatus){
	var benefitTypeList = document.getElementById('benifitCreateForm:CorpPlanCd_list1'); 
alert(benefitTypeList.length);
	for(var i = 0; i < benefitTypeList.length; i++){
		benefitTypeList.options[i].value = "";
	}
}*/

	function runSpellCheck(){
		copyToHidden('benifitCreateForm:txtDataType','benifitCreateForm:selectedDataType');
		var rswlCntrls = ["rapidSpellWebLauncher1","rapidSpellWebLauncher2"];
		var i=0;
		for(var i=0; i<rswlCntrls.length; i++){
			eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
		}
		return false;
	}
	function spellFin(cancelled){

			document.getElementById('benifitCreateForm:createBenefit').click();	
	}

	function copyToHidden(sourceField,hiddenField){
		var sourceObj = document.getElementById(sourceField);
		var targetObj = document.getElementById(hiddenField);
		targetObj.value = sourceObj.value;
	}

	copyHiddenToDiv_ewpd('benifitCreateForm:txtLob','lobDiv',2,2); 
	copyHiddenToDiv_ewpd('benifitCreateForm:txtBusinessEntity','BusinessEntityDiv',2,2); 
	copyHiddenToDiv_ewpd('benifitCreateForm:txtBusinessGroup','BusinessGroupDiv',2,2);
	copyHiddenToDiv_ewpd('benifitCreateForm:txtMarketBusinessUnit','MarketBusinessUnitDiv',2,2); 
	copyHiddenToDiv_ewpd('benifitCreateForm:txtTerm','TermDiv',2,2); 
	copyHiddenToDiv_ewpd('benifitCreateForm:txtQualifier','QualifierDiv',2,2); 
	copyHiddenToDiv_ewpd('benifitCreateForm:txtProviderArrangement','ProviderArrangementDiv',2,2); 
	copyHiddenToDiv_ewpd('benifitCreateForm:txtDataType','DataTypeDiv',2,2);
	//copyHiddenToDiv_ewpd('benifitCreateForm:txtRule','RuleDiv',2,1);
	copyHiddenToDiv_ewpd1('benifitCreateForm:txtRule','RuleDiv',2,1);

//newly added

var a;
var i;
var obj;
obj = document.getElementById("benifitCreateForm:Mandate_type_list1");
obj1 = document.getElementById("benifitCreateForm:Mandate_type");
objBen = document.getElementById("benifitCreateForm:CorpPlanCd_list1");
objBen1 = document.getElementById("benifitCreateForm:benefitType_copy");
if(obj != null && obj.value != ''){

	i= obj.selectedIndex;
	a=obj.options[i].value;
	document.getElementById("benifitCreateForm:mandateTypeHidden").value = a;

}else if(obj1 != null){

	document.getElementById("benifitCreateForm:mandateTypeHidden").value = obj1.value;

}
if(objBen != null){
	i= objBen.selectedIndex;
	a=objBen.options[i].value;
	document.getElementById("benifitCreateForm:benefitType_copyhidden").value = a;

}else if(objBen1 != null){

	document.getElementById("benifitCreateForm:benefitType_copyhidden").value = objBen1.value;

}
// end
function setMandateTypeValue(){
var a;
var i;
var obj;
obj = document.getElementById("benifitCreateForm:Mandate_type_list1");
i= obj.selectedIndex;
a=obj.options[i].value;
document.getElementById("benifitCreateForm:mandateTypeHidden").value = a;
}
//For combo boxes on load
var i;
var obj;
obj = document.getElementById("benifitCreateForm:CorpPlanCd_list1");
objBen1 = document.getElementById("benifitCreateForm:benefitType_copy");
if(obj != null){
	i= obj.selectedIndex;
	if(i== 1)
	{
	sub1.style.display='';
	}
	else 
	{
	sub1.style.display='none';
	}
}else if(objBen1 != null){
	sub1.style.display='';
}

	
var i;
var obj;
obj = document.getElementById("benifitCreateForm:Mandate_type_list1");
if(obj != null && obj.value != ''){
	i= obj.selectedIndex;
}
function getContractType()
{
var a;
var i;
var obj;

obj = document.getElementById("benifitCreateForm:CorpPlanCd_list1");
objBen1 = document.getElementById("benifitCreateForm:benefitType_copy");
	if(obj != null){
		i= obj.selectedIndex;
		a=obj.options[i].value;
		if(i== 1)
			{
		
		sub1.style.display='';
			}
			
		else 
			{
		
		sub1.style.display='none';
	
	//	return "Base Contract Code";
		
		}
	}
	else if(objBen1 != null){
		sub1.style.display='';
	}
}
if(objBen1 != null && objBen1.value == 'STANDARD'){
	sub1.style.display='none';
}

function viewAction(){
	
	var ruleIdStr = document.getElementById('benifitCreateForm:txtRule').value;
	var ruleType = document.getElementById('benifitCreateForm:txtStrRuleType').value;
	var ruleArray = ruleIdStr.split('~');
	var ruleId = ruleArray[1];
	
	if(ruleIdStr.length <=1)
	{
			alert('Benefit Rule ID need to be selected.');
			return false;
	}
	var ruleArray = ruleIdStr.split('~');
	var ruleId = ruleArray[1];
	if(ruleType=="BLZWPDAB")
	{	
		window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&ruleType='+ruleType+'&temp='+ Math.random(),'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	}
	else if(ruleType!="BLZWPDAB") 
	{

		window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&ruleType='+ruleType+'&temp=' + Math.random(),'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	}









}
function loadpopup()
{
var emptyString='';
var ruleId=0;
var titleName = 'Benefit Rule Popup';

	ewpdModalWindowWithRuleType('../popups/benefitRuleTypePopup.jsp'+getUrl()+'?queryName=locateRuleId&ruleId='+ruleId+'&titleName='+titleName+'&temp='+Math.random(),'RuleDiv','benifitCreateForm:txtRule','benifitCreateForm:txtStrRuleType',2,1); return false;
}

</script>

</HTML>





