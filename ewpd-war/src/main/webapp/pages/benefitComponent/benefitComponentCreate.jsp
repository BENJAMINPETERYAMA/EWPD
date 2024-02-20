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

<TITLE>Benefit Component Create</TITLE>
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
	<BODY
		onkeypress="return submitOnEnterKey('benefitComponentCreateForm:createButton');">
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><h:inputHidden id="dummy"
					value="#{benefitComponentCreateBackingBean.benefitComponentName}"></h:inputHidden>
				<jsp:include page="../navigation/top.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="benefitComponentCreateForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD width="273" valign="top" class="leftPanel">


							<DIV class="treeDiv" style="height:500px"><!-- Space for Tree  Data	--></DIV>

							</TD>

							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message
											value="#{benefitComponentCreateBackingBean.validationMessages}"></w:message>
										</TD>
									</TR>
								</TBODY>
							</TABLE>

							<!-- Table containing Tabs -->
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">

								<TR>
									<TD width="200">
									<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
											<TD width="186" class="tabActive"><h:outputText
												id="generalInformationTabTable" value=" General Information" />
											</TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>

									<TD width="100%"></TD>
								</TR>
							</TABLE>
							<!-- End of Tab table -->

							<FIELDSET
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
										<FIELDSET style="width:54%"><LEGEND><FONT color="black">Business
										Domain</FONT></LEGEND>
										<TABLE width="103%">
											<TR>
												<TD width="38%"><h:outputText id="lobLabel"
													value="Line of Business*"
													styleClass="#{benefitComponentCreateBackingBean.lobValdn?'mandatoryError': 'mandatoryNormal'}" />
												</TD>
												<TD width="46%">
												<DIV id="lobDiv" class="selectDataDisplayDiv"></DIV>
												<h:inputHidden id="lobTxtHidden"
													value="#{benefitComponentCreateBackingBean.lineOfBusiness}"></h:inputHidden>
												</TD>
												<TD width="16%">&nbsp;&nbsp;&nbsp;<h:commandButton
													id="lobButton" alt="Select" image="../../images/select.gif"
													style="cursor: hand"
													onclick="ewpdModalWindow_ewpd('../popups/lineOfBusinessPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Line of Business','lobDiv','benefitComponentCreateForm:lobTxtHidden',2,2); return false;"
													tabindex="1" /></TD>
											</TR>
											<TR>
												<TD width="38%"><h:outputText id="businessEntityLabel"
													value="Business Entity*"
													styleClass="#{benefitComponentCreateBackingBean.businessentityValdn?'mandatoryError': 'mandatoryNormal'}" />
												</TD>
												<TD width="46%">
												<DIV id="businessEntityDiv" class="selectDataDisplayDiv"></DIV>
												<h:inputHidden id="businessEntityTxtHidden"
													value="#{benefitComponentCreateBackingBean.businessEntity}"></h:inputHidden>
												</TD>
												<TD width="16%">&nbsp;&nbsp;&nbsp;<h:commandButton
													id="businessEntityButton" alt="Select"
													image="../../images/select.gif" style="cursor: hand"
													onclick="ewpdModalWindow_ewpd('../popups/businessEntityPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Business Entity','businessEntityDiv','benefitComponentCreateForm:businessEntityTxtHidden',2,2); return false;"
													tabindex="2" /></TD>
											</TR>
											<TR>
												<TD width="38%"><h:outputText id="businessGroupLabel"
													value="Business Group*"
													styleClass="#{benefitComponentCreateBackingBean.businessgroupValdn?'mandatoryError': 'mandatoryNormal'}" /></TD>
												<TD width="46%">
												<DIV id="businessGroupDiv" class="selectDataDisplayDiv"></DIV>
												<h:inputHidden id="businessGroupTxtHidden"
													value="#{benefitComponentCreateBackingBean.businessGroup}"></h:inputHidden>
												</TD>
												<TD width="16%">&nbsp;&nbsp;&nbsp;<h:commandButton
													id="businessGroupButton" alt="Select"
													image="../../images/select.gif" style="cursor: hand"
													onclick="ewpdModalWindow_ewpd('../popups/businessGroupPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'business group','businessGroupDiv','benefitComponentCreateForm:businessGroupTxtHidden',2,2); return false;"
													tabindex="3" /></TD>
											</TR>
<!-- ------------------------------------------------------------------- -->
											<TR>
												<TD width="38%">
													<h:outputText
														value="Market Business Unit*"
														styleClass="#{benefitComponentCreateBackingBean.requiredMarketBusinessUnit?'mandatoryError': 'mandatoryNormal'}" /></TD>
												<TD width="46%">
												<DIV id="MarketBusinessUnitDiv" class="selectDataDisplayDiv"></DIV>
												<h:inputHidden id="marketBusinessUnitTxtHidden"
													value="#{benefitComponentCreateBackingBean.marketBusinessUnit}"></h:inputHidden>
												</TD>
												<TD width="16%">&nbsp;&nbsp;&nbsp;<h:commandButton
													id="marketBusinessUnitButton" alt="Select"
													image="../../images/select.gif" style="cursor: hand"
												onclick="ewpdModalWindow_ewpd('../popups/marketBusinessUnit.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Market Business Unit','MarketBusinessUnitDiv','benefitComponentCreateForm:marketBusinessUnitTxtHidden',2,2);return false;"
													tabindex="3" /></TD>
											</TR>
<!-- ------------------------------------------------------------------- -->
										</TABLE>
										</FIELDSET>
										</TD>
									</TR>

									<TR>
										<TD width="21%">&nbsp;<h:outputText id="nameLabel"
											value="Name*"
											styleClass="#{benefitComponentCreateBackingBean.nameValdn?'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD width="27%"><h:inputText tabindex="4" id="nameTxt"
											styleClass="formInputField"
											value="#{benefitComponentCreateBackingBean.benefitComponentName}"
											maxlength="34" /></TD>

									</TR>
										<RapidSpellWeb:rapidSpellWebLauncher
											id="rapidSpellWebLauncher1"
											textComponentName="benefitComponentCreateForm:nameTxt"
											rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Benefit Component Name"
											modalHelperPage="../spellcheck/RapidSpellModalHelper.html"
											showNoErrorsMessage="False" showFinishedMessage="False"
											includeUserDictionaryInSuggestions="True"
											allowAnyCase="True" allowMixedCase="True"
											windowWidth="570" windowHeight="300"
											modal="False" showButton="False"
											windowX="-1" warnDuplicates="False"
											textComponentInterface="Custom">
										</RapidSpellWeb:rapidSpellWebLauncher>

									<!--  Enhancements -  to be modified-->

									<TR id="strType1" style="display:none;">
										<TD height="30" width="148">&nbsp;<h:outputText
											id="componentType" value="Component Type*"
											styleClass="#{benefitComponentCreateBackingBean.compTypeValdn ? 'mandatoryError': 'mandatoryNormal'}" /></TD>
										<TD height="9" width="186"><h:selectOneMenu id="compType"
											styleClass="formInputField" tabindex="5"
											value="#{benefitComponentCreateBackingBean.componentType}"
											onchange="getComponentType()">
											<f:selectItems id="componentTypeList"
												value="#{ReferenceDataBackingBeanCommon.entityTypeListForCombo}" />
										</h:selectOneMenu></TD>
										<TD height="9" width="356"></TD>
									</TR>
                                    <TR>
										<TD valign="top" width="148">&nbsp;<h:outputText
											id="descriptionLabel" value="Description*"
											styleClass="#{benefitComponentCreateBackingBean.descValdn?'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD width="186"><h:inputTextarea id="descriptionTxtarea"
											styleClass="formTxtAreaField_GeneralDesc"
											value="#{benefitComponentCreateBackingBean.description}"
											tabindex="6" /></TD>
									</TR>
									<TR id="strType2" style="display:none;">
										<TD height="30" width="148">&nbsp;<h:outputText
											id="componentType1" value="Component Type*"
											styleClass="#{benefitComponentCreateBackingBean.compTypeValdn ? 'mandatoryError': 'mandatoryNormal'}" /></TD>
										<TD height="9" width="186"><h:inputText
											id="componentType_copy"
											rendered="#{benefitComponentCreateBackingBean.copyFlag}"
											styleClass="formInputField" tabindex="7" readonly="true"
											value="#{benefitComponentCreateBackingBean.componentType}"></h:inputText></TD>
										<TD width="356">
										<TABLE cellspacing="0" cellpadding="0" border="0">
											<TR>
												<TD height="9" width="184"></TD>
											</TR>
										</TABLE>
										</TD>
									</TR>
									<TR id="sub1" style="display:none;">
										<TD width="148">&nbsp;<h:outputText id="mandType"
											value="Mandate Type*"
											styleClass="#{benefitComponentCreateBackingBean.mandateTypeValdn ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD height="9" width="186"><h:selectOneMenu id="mandateType"
											styleClass="formInputField" tabindex="8"
											value="#{benefitComponentCreateBackingBean.mandateType}"
											onchange="getMandateType()">
											<f:selectItems id="mandateTypeList"
												value="#{ReferenceDataBackingBeanCommon.mandateTypeListForCombo}" />
										</h:selectOneMenu></TD>
									</TR>
									<TR id="sub2" style="display:none;">
										<TD width="148">&nbsp;<h:outputText id="stateCde"
											value="Jurisdiction*"
											styleClass="#{benefitComponentCreateBackingBean.stateIdValdn ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<h:inputHidden id="hid_state"
											value="#{benefitComponentCreateBackingBean.selectedStateId}"></h:inputHidden>
										<TD width="186">
										<DIV id="stateDiv" class="selectDataDisplayDiv"></DIV>
										<SCRIPT> parseForDiv(document.getElementById('stateDiv'), document.getElementById('benefitComponentCreateForm:hid_state')),copyHiddenToDiv_ewpd('benefitComponentCreateForm:hid_state','stateDiv',2,2);; </SCRIPT>
										</TD>
										<TD width="356"><h:commandButton alt="State" id="stateButton"
											image="../../images/select.gif"
											onclick="getSelectedDomainReferenceData('../contractpopups/HeadQuarterstatePopup.jsp', 'benefitComponentCreateForm',
										'lobTxtHidden', 'businessEntityTxtHidden', 'businessGroupTxtHidden','stateDiv','benefitComponentCreateForm:hid_state', 2, 2, 'State Code');setRefDataUseFlag('benefitComponentCreateForm', 'hid_state', 'stateDiv');
																		return false;"
											tabindex="9">
										</h:commandButton></TD>
									</TR>
									<TR id="sub3" style="display:none;">
										<TD width="148">&nbsp;<h:outputText id="stateCde1"
											value="Jurisdiction*" /></TD>
										<TD width="186"><h:outputText id="FederalLabel" value="ALL"></h:outputText>
										</TD>
									</TR>
									<TR>
										<TD width="148">&nbsp;<h:outputText id="ruleIdLabel"
											value="Benefit Rule ID*" styleClass="#{benefitComponentCreateBackingBean.ruleIdValdn?'mandatoryError': 'mandatoryNormal'}" /></TD>
											
										<TD width="186">
										<DIV id="ruleIdDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="ruleIdTxtHidden"
											value="#{benefitComponentCreateBackingBean.ruleId}"></h:inputHidden>
										<h:inputHidden id="txtStrRuleType" value="#{benefitComponentCreateBackingBean.strRuleType}"></h:inputHidden>
										 
										</TD>
										<TD width="356"><h:commandButton id="ruleIdButton"
											alt="Select" image="../../images/select.gif"
											style="cursor: hand"
											onclick="loadpopup(); return false;"
											tabindex="10" />&nbsp;
											<h:commandButton alt="View" id="viewButton"
												image="../../images/view.gif"
												onclick="viewAction();return false;" tabindex="11"/></TD>
									</TR>
									<!--  End Enhancements -->
                                    <TR>
										<TD width="148">&nbsp;<h:outputText id="effectiveDateLabel"
											value="Effective Date*"
											styleClass="#{benefitComponentCreateBackingBean.effdateValdn?'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD width="186"><h:inputText tabindex="12"
											id="effectiveDateTxt" styleClass="formInputField"
											value="#{benefitComponentCreateBackingBean.effectiveDate}" />
										<BR class="brclass">
										<SPAN class="dateformat">(mm/dd/yyyy)</SPAN></TD>


										<TD valign="top" width="356"><A href="#"
											onclick="cal1.select('benefitComponentCreateForm:effectiveDateTxt','effectiveDate_cal','MM/dd/yyyy'); return false;"
											title="cal1.select(document.forms[0].benefitComponentCreateForm:effectiveDateTxt,'effectiveDate_cal','MM/dd/yyyy'); return false;"
											name="effectiveDate_cal" id="effectiveDate_cal"><h:commandButton
											image="../../images/cal.gif" style="cursor: hand" alt="Cal"
											tabindex="13" /></A></TD>
									</TR>
									<TR>
										<TD width="148">&nbsp;<h:outputText id="expiryDateLabel"
											value="Expiry Date*"
											styleClass="#{benefitComponentCreateBackingBean.expdateValdn?'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD width="186"><h:inputText tabindex="14" id="expiryDateTxt"
											styleClass="formInputField"
											value="#{benefitComponentCreateBackingBean.expiryDate}" /><BR
											class="brclass">
										<SPAN class="dateformat">(mm/dd/yyyy)</SPAN></TD>
										<TD valign="top" width="356"><A href="#"
											onclick="cal1.select('benefitComponentCreateForm:expiryDateTxt','expiryDate_cal','MM/dd/yyyy'); return false;"
											title="cal1.select(document.forms[0].benefitComponentCreateForm:expiryDateTxt,'expiryDate_cal','MM/dd/yyyy'); return false;"
											name="expiryDate_cal" id="expiryDate_cal"><h:commandButton
											image="../../images/cal.gif" style="cursor: hand" alt="Cal"
											tabindex="15" /></A></TD>
									</TR>
										<RapidSpellWeb:rapidSpellWebLauncher
											id="rapidSpellWebLauncher2"
											textComponentName="benefitComponentCreateForm:descriptionTxtarea"
											rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Benefit Component Description"
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
										<TD width="148">
											<h:commandButton value="Create" id="createButton"
												styleClass="wpdButton" onclick="return runSpellCheck();" tabindex="16">
											</h:commandButton>
											<h:commandLink id="createBenefitComp"
												style="hidden" action="#{benefitComponentCreateBackingBean.createBenefitComponent}">
											</h:commandLink>
										</TD>
										<TD width="186">&nbsp;</TD>
									</TR>
								</TBODY>
							</TABLE>
							<!--	End of Page data	--></FIELDSET>
							</TD>
						</TR>
					</TABLE>

					<!-- Space for hidden fields -->
					<h:inputHidden id="createFlag"
						value="#{benefitComponentCreateBackingBean.createFlag}"></h:inputHidden>
					<h:inputHidden id="benefitComponentId"
						value="#{benefitComponentCreateBackingBean.benefitComponentId}"></h:inputHidden>
					<h:inputHidden id="benefitComponentParentId"
						value="#{benefitComponentCreateBackingBean.benefitComponentParentId}"></h:inputHidden>
					<h:commandLink id="hiddenLnk1"
						style="display:none; visibility: hidden;">
						<f:verbatim />
					</h:commandLink>
					<h:inputHidden id="copyHidden"
						value="#{benefitComponentCreateBackingBean.copyFlag}" />

					<h:inputHidden id="oldEffectiveDateHid"
						value="#{benefitComponentCreateBackingBean.oldEffectiveDate}"></h:inputHidden>
					<h:inputHidden id="oldExpiryDateHid"
						value="#{benefitComponentCreateBackingBean.oldExpiryDate}"></h:inputHidden>
					<h:inputHidden id="oldLobHid"
						value="#{benefitComponentCreateBackingBean.oldLineOfBusinessCode}"></h:inputHidden>
					<h:inputHidden id="oldEntityHid"
						value="#{benefitComponentCreateBackingBean.oldBusinessEntityCode}"></h:inputHidden>
					<h:inputHidden id="oldGroupHid"
						value="#{benefitComponentCreateBackingBean.oldBusinessGroupCode}"></h:inputHidden>
					<h:inputHidden id="oldMarktUnitHid"
						value="#{benefitComponentCreateBackingBean.oldmarketBusinessUnit}"></h:inputHidden>
					<h:inputHidden id="domainChanged"
						value="#{benefitComponentCreateBackingBean.domainChange}"></h:inputHidden>
					<!-- End of hidden fields  -->
					<h:commandLink id="createLink"
						style="display:none; visibility: hidden;"
						action="#{benefitComponentCreateBackingBean.createBenefitComponent}">
						<f:verbatim />
					</h:commandLink>
				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>

<script language="JavaScript">
	document.getElementById('benefitComponentCreateForm:nameTxt').focus(); // for on load default selection

	copyHiddenToDiv('benefitComponentCreateForm:lobTxtHidden','lobDiv');
	copyHiddenToDiv_ewpd('benefitComponentCreateForm:businessEntityTxtHidden','businessEntityDiv',2,2);
	copyHiddenToDiv_ewpd('benefitComponentCreateForm:businessGroupTxtHidden','businessGroupDiv',2,2);
	copyHiddenToDiv_ewpd('benefitComponentCreateForm:marketBusinessUnitTxtHidden','MarketBusinessUnitDiv',2,2);
	//copyHiddenToDiv('benefitComponentCreateForm:ruleIdTxtHidden','ruleIdDiv');	
	copyHiddenToDiv_ewpd1('benefitComponentCreateForm:ruleIdTxtHidden','ruleIdDiv',2,1);
	//copyHiddenToDiv_ewpd('benefitComponentCreateForm:hid_state','selectDataDisplayDiv',2,2);
	// For the  combo boxes
getComponentType();
getMandateType();
function getComponentType()
{
var i;
var obj;
obj = document.getElementById("benefitComponentCreateForm:compType");
i= obj.selectedIndex;
	//alert('i in bcCreate'+ i);
	// i = 1: STANDARD
	// i = 2 :MANDATE	
var type = obj.value;
	if(type == "MANDATE")
// 	if(i== 1)
	{
	sub1.style.display='';	
	}	
	else 
	{
	sub1.style.display='none';
	sub2.style.display='none';
	sub3.style.display='none';	
	}

}
function getMandateType()
	{
	var i;
	var obj;
	obj = document.getElementById("benefitComponentCreateForm:mandateType");
	i= obj.selectedIndex;
		//alert('i in bc2'+i);		

		// i = 1 or 3 :If Extra Territorial or State 
		if(i== 1 || i==3)
		{
		if(document.getElementById('stateDiv').innerHTML == 'undefined<BR>'){
			// alert('no');
			document.getElementById('stateDiv').innerHTML = '';
		}	
		sub2.style.display='';	
		sub3.style.display='none';
		}
		// i = 2 : Federal	
		else if(i == 2)
		{
		sub2.style.display='none';
		sub3.style.display='';
		}
		else 
		{
		sub2.style.display='none';
		sub3.style.display='none';
		}
	}
var copyFlag = document.getElementById("benefitComponentCreateForm:copyHidden").value;
if(copyFlag=='true'){
	strType2.style.display='';	
	strType1.style.display='none';	
}else{
	strType2.style.display='none';	
	strType1.style.display='';	
}

// For Domain & Date change validation while copying
function callCreateMethod(){
	if(copyFlag=='true'){
		var oldEffectiveDate = document.getElementById('benefitComponentCreateForm:oldEffectiveDateHid').value;
		var oldExpiryDate = document.getElementById('benefitComponentCreateForm:oldExpiryDateHid').value;
		var currentEffectiveDate = document.getElementById('benefitComponentCreateForm:effectiveDateTxt').value;
		var currentExpiryDate = document.getElementById('benefitComponentCreateForm:expiryDateTxt').value;
		var oldLineOfBusinessCode = document.getElementById('benefitComponentCreateForm:oldLobHid').value;
		var oldBusinessEntityCode = document.getElementById('benefitComponentCreateForm:oldEntityHid').value;
		var oldBusinessGroupCode = document.getElementById('benefitComponentCreateForm:oldGroupHid').value;
		var oldMarketBusinessUnitCode = document.getElementById('benefitComponentCreateForm:oldMarktUnitHid').value;
		var newLineOfBusinessCode = document.getElementById('benefitComponentCreateForm:lobTxtHidden').value;
		var newBusinessEntityCode = document.getElementById('benefitComponentCreateForm:businessEntityTxtHidden').value;
		var newBusinessGroupCode = document.getElementById('benefitComponentCreateForm:businessGroupTxtHidden').value;
	    var newMarketBusinessUnitCode = document.getElementById('benefitComponentCreateForm:marketBusinessUnitTxtHidden').value;
			// Business Domian Validation 
		if((oldLineOfBusinessCode != newLineOfBusinessCode) 
			 || (oldBusinessEntityCode != newBusinessEntityCode)
			 || (oldBusinessGroupCode != newBusinessGroupCode)
			 || (oldMarketBusinessUnitCode != newMarketBusinessUnitCode) ){
			// alert('inside bus dom valdn');
			 document.getElementById('benefitComponentCreateForm:domainChanged').value = true;	
		}
	}
}

function runSpellCheck(){
	callCreateMethod();
	var rswlCntrls = ["rapidSpellWebLauncher1","rapidSpellWebLauncher2"];
	var i=0;
	for(var i=0; i<rswlCntrls.length; i++){
		eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
	}
	return false;
}
function spellFin(cancelled){

		document.getElementById('benefitComponentCreateForm:createBenefitComp').click();	
}


document.getElementById('lobDiv').style.height= "17px";
document.getElementById('businessEntityDiv').style.height= "17px";
document.getElementById('businessGroupDiv').style.height= "17px";
document.getElementById('MarketBusinessUnitDiv').style.height= "17px";

function viewAction()
{
	
	var ruleIdStr = document.getElementById('benefitComponentCreateForm:ruleIdTxtHidden').value;
	var ruleType = document.getElementById('benefitComponentCreateForm:txtStrRuleType').value;
	

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
	ewpdModalWindowWithRuleType('../popups/benefitRuleTypePopup.jsp'+getUrl()+'?queryName=locateRuleId&ruleId='+ruleId+'&titleName='+titleName+'&temp='+Math.random(),'ruleIdDiv','benefitComponentCreateForm:ruleIdTxtHidden','benefitComponentCreateForm:txtStrRuleType',2,1); return false;
}
//document.getElementById('ruleIdDiv').style.height= "17px";
</script>
</HTML>
