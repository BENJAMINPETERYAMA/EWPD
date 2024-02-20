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

<TITLE>Mandate Information</TITLE>
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
</HEAD>
<f:view>
	<h:inputHidden id="loadMandateInfo" rendered="#{benefitMandateBackingBean.flagForSaveandCheckin}"
								value="#{benefitMandateBackingBean.loadMandateInfo}"></h:inputHidden> 
	<BODY
		onkeypress="return submitOnEnterKey('MandateInfoForm:saveButton');">
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="MandateInfoForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>
							<TD width="273" valign="top" class="leftPanel">
							<div class="treeDiv">
							<jsp:include
								page="../standardBenefit/standardBenefitTree.jsp"></jsp:include>
							</div>
							</TD>

							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message
											value="#{benefitMandateBackingBean.validationMessages}"></w:message>

										</TD>
									</TR>
								</TBODY>
							</TABLE>

							<!-- Table containing Tabs -->
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<TR>

									<TD width="25%">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD width="100%" class="tabNormal"><h:commandLink
												action="#{standardBenefitBackingBean.loadStandardBenefit}"
												onmousedown="javascript:navigatePageAction(this.id);return false;" id="genId">
												<h:outputText value="General Information" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>

									<TD width="25%">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD class="tabNormal"><h:commandLink
												action="#{benefitDefinitionBackingBean.loadBenefitDefinition}"
												onmousedown="javascript:navigatePageAction(this.id);return false;" id="benId">
												<h:outputText
													value="#{standardBenefitBackingBean.benefitTypeTab}" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<%--
											<TD width="25%">
											<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
												<TR>
													<TD width="3" align="left"><IMG src="../../images/tabNormalLeft.gif" alt="Tab Left Normal" width="3" height="21"></TD>
													<TD class="tabNormal"> 
														<h:commandLink action="#{MandateDefinitionBackingBean.loadMandateDefinition}"> <h:outputText value="Adj Benefit Mandates " /> </h:commandLink> 
													</TD>
													<TD width="2" align="right"><IMG src="../../images/tabNormalRight.gif" alt="Tab Right Normal" width="4" height="21"></TD>
												</TR>
											</TABLE>
										</TD>											 
										--%>
									<TD width="25%" id="manTab">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
											<TD width="186" class="tabActive"><h:outputText
												value="Mandate Information " /></TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<TD width="25%" id="notTab">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" width="3" height="21" /></td>
											<TD width="100%" class="tabNormal" align="center"><h:commandLink
												action="#{standardBenefitNotesBackingBean.loadStandardBenefitNotes}">
												<h:outputText value=" Notes " />
											</h:commandLink></TD>
											<td width="2" align="right"><img
												src="../../images/tabNormalRight.gif" width="4" height="21" /></td>
										</tr>
									</table>
									</TD>

									<TD width="100%"></TD>
								</TR>
							</TABLE>
							<!-- End of Tab table -->

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:20px;width:100%">

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
								<tr>
									<td width="30%">
									<table width="100%">
										<tr>
											<td width="70%">
											<div align="left" id="stateCodeTextDiv"><h:outputText
												styleClass="#{benefitMandateBackingBean.requiredStateCode ? 'mandatoryError': 'mandatoryNormal'}"
												id="StateCodeStar" value="Jurisdiction*" />
											</td>
										</tr>
									</table>
									</td>
									<td width="70%">
									<h:inputHidden id="txtState"
										value="#{benefitMandateBackingBean.stateCode}"></h:inputHidden>
									<div align="left" id="stateCodeDiv">
									<%--<TABLE width="100%"  border="0"> 
										<TBODY>
											<tr>

												<td width="100%">--%>
												<table cellspacing="0" cellpadding="0" border="0"
													width="100%">
													<tr>
														<td width="39%">
														<DIV id="StateDiv" class="selectDataDisplayDiv"></DIV>
														</TD>
														<TD width="61%"><h:commandButton alt="Select" 
															id="StateButton" image="../../images/select.gif"
															style="cursor: hand" tabindex="1"
															onclick="stateInfo(); return false;"></h:commandButton></TD>
													</tr>
												</table>
												<%--</td>
											</tr>
										</TBODY>
									</TABLE>--%> 
									</div>

									</td>
								</tr>






								<tr>
									<td width="30%">&nbsp;<h:outputText
										styleClass="#{benefitMandateBackingBean.requiredMandateCategory ? 'mandatoryError': 'mandatoryNormal'}"
										id="MandateCategoryStar" value="Mandate Category*" /></td>
									<td width="70%">
									<table cellspacing="0" cellpadding="0" border="0">
										<tr>
											<td><h:selectOneMenu  id="Mandate_Category_list1"
												styleClass="formInputField" tabindex="2"
												value="#{benefitMandateBackingBean.mandateCategory}">
												<f:selectItems id="mandateTypeCategory"
													value="#{ReferenceDataBackingBeanCommon.mandateCategoryList}" />

											</h:selectOneMenu></td>
											<td width="63%" align="right"></td>
										</tr>
									</table>
									</td>
								</tr>
								<TR>
									<TD width="30%">&nbsp;<h:outputText id="CitationNumberStar"
										value="Citation Number" /></TD>
									<TD width="70%">
									<TABLE cellspacing="0" cellpadding="0" border="0" width="100%">
										<TR>
											<TD width="39%">
											<DIV id="CitationNumberDiv" class="selectDataDisplayDiv"></DIV>
											<h:inputHidden id="txtCitationNumber"
												value="#{benefitMandateBackingBean.citationNumber}"></h:inputHidden>
											</TD>

											<TD width="66%"><h:commandButton alt="Select"
												id="CitationNumberButton" image="../../images/select.gif"
												style="cursor: hand" tabindex="2"
												onclick="modalWindow11('../standardbenefitpopups/citationNumberPopup.jsp'+getUrl(),'CitationNumberDiv','MandateInfoForm:txtCitationNumber'); return false;"></h:commandButton></TD>
										</TR>
									</TABLE>
									</TD>
								</TR>


								<TR>
									<TD width="30%">&nbsp;<h:outputText
										styleClass="#{benefitMandateBackingBean.requiredFundingArrangement ? 'mandatoryError': 'mandatoryNormal'}"
										id="FundingArrangementStar" value="Funding Arrangement*" /></TD>
									<TD width="70%">
									<TABLE cellspacing="0" cellpadding="0" border="0" width="100%">
										<TR>
											<TD width="39%">
											<DIV id="FundingArrangementDiv" class="selectDataDisplayDiv"></DIV>
											<h:inputHidden id="txtFundingArrangement"
												value="#{benefitMandateBackingBean.fundingArrangement}"></h:inputHidden>
											</TD>
											<TD width="66%"><h:commandButton alt="Select"
												id="FundingArrangementButton"
												image="../../images/select.gif" style="cursor: hand"
												tabindex="3"
												onclick="fundingArrangementInfo(); return false;"></h:commandButton></TD>

										</TR>
									</TABLE>
									</TD>
								</TR>

								<TR>
									<TD width="30%">&nbsp;<h:outputText
										styleClass="#{benefitMandateBackingBean.requiredEffectiveness ? 'mandatoryError': 'mandatoryNormal'}"
										id="EffectivenessStar" value="Effectiveness*" /></TD>
									<td width="70%">
									<table cellspacing="0" cellpadding="0" border="0">
										<tr>
											<td><h:selectOneMenu id="Effectiveness_type_list1"
												styleClass="formInputField" tabindex="4"
												value="#{benefitMandateBackingBean.effectiveness}">
												<f:selectItems id="effectivenessTypeList"
													value="#{ReferenceDataBackingBeanCommon.effectivenessList}" />

											</h:selectOneMenu></td>
											<td width="63%" align="right"></td>
										</tr>
									</table>
									</td>
								</TR>


								<TR>
									<TD valign="top" width="30%">&nbsp;<h:outputText
										styleClass="#{benefitMandateBackingBean.requiredDesription ? 'mandatoryError': 'mandatoryNormal'}"
										id="descriptionStar"  value="Text*" /></TD>
									<TD colspan="2" valign="top"><!-- *********************************************************************** -->
									<div id="viewNotesDiv" style="z-index:1;">
									<table cellpadding="0" cellspacing="0" border="0">
										<tr>
											<td>
											<div id="spacingdiv" style="z-index:1;">
											<table cellpadding="0" cellspacing="0" border="0">
												<tr>
													<td>&nbsp;<h:inputHidden id="tempText" value="#{benefitMandateBackingBean.text}"/></td>
												</tr>
											</table>
											</div>
											<div id="viewNoteBox" class="noteViewDiv">
											<div id="viewNoteBoxNormal" class="noteViewNormalTextDiv"></div>
											<div id="viewNoteBoxRed" class="noteViewTruncatedTextDiv"></div>
											</div>
											</td>
											<td align="left" valign="bottom">&nbsp;
												<h:commandButton
												id="editButton" tabindex="4" value="Edit" styleClass="wpdbutton"
												onclick="showHide()" />
											&nbsp;&nbsp;
										</tr>
									</table>
									</div>
									<div id="editNotesDiv" style="z-index:3;">
									<table cellpadding="0" cellspacing="0" border="0">
										<tr>
											<td><h:inputTextarea
												styleClass="formTxtAreaField_MandateText" id="txtText"
												tabindex="5" value="#{benefitMandateBackingBean.text}"
												onkeypress="return validateText();">
											</h:inputTextarea>
											<div id="spacingdiv" style="z-index:1;">
											<table cellpadding="0" cellspacing="0" border="0">
												<tr>
													<td>&nbsp;</td>
												</tr>
											</table>
											</div>
											</td>
										</tr>
									</table>
									</div>
									<!-- *********************************************************************** -->
									</TD>

								</TR>
									<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher1"
										textComponentName="MandateInfoForm:txtText"
										rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Mandate Text"
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

									<TD width="251">
										<h:commandButton value="Save"
											styleClass="wpdButton" onclick="return runSpellCheck();" tabindex="6">
										</h:commandButton>
										<h:commandLink id="saveButton"
											style="hidden" action="#{benefitMandateBackingBean.saveBenefitMandate}">
										</h:commandLink>
									</TD>
									<TD width="589">&nbsp;</TD>
								</TR>
								<TR>
									<TD width="251">&nbsp;</TD>
								</TR>
							</TABLE>

							</FIELDSET>
							<BR>
							<FIELDSET
								style="margin-left:6px;margin-right:-6px;margin-top:6px;padding-bottom:1px;padding-top:20px;width:100%">
							<TABLE border="0" cellspacing="0" cellpadding="3" width="100%">
								<TR>
									<TD width="15"><h:selectBooleanCheckbox id="checkall" tabindex="9"
										tabindex="7" value="#{standardBenefitBackingBean.checkin}">
									</h:selectBooleanCheckbox></TD>
									<TD align="left"><h:outputText value="Check In" /></TD>
									<TD align="right" width="104">
									<TABLE width="100%">
										<TR>
											<td><h:outputText value="State" /></td>
											<TD><h:outputText value=":" /></TD>
											<TD><h:outputText id="state"
												value="#{benefitMandateBackingBean.state}"></h:outputText></TD>
										</TR>
										<TR>
											<td><h:outputText value="Status" /></td>
											<TD><h:outputText value=":" /></TD>
											<TD><h:outputText id="status"
												value="#{benefitMandateBackingBean.status}"></h:outputText>
											</TD>
										</TR>
										<TR>
											<td><h:outputText value="Version" /></td>
											<TD><h:outputText value=":" /></TD>
											<TD><h:outputText id="version"
												value="#{benefitMandateBackingBean.version}"></h:outputText>
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

										<TD width="8">&nbsp;</TD>
									</TR>
									<TR>

										<!-- <TD width="8">&nbsp;</TD> -->

										<TD>&nbsp;
											<h:commandButton value="Done"
												styleClass="wpdButton" onclick="return checkIn();" tabindex="8">
											</h:commandButton>
											<h:commandLink id="checkinBenefit"
												style="hidden" action="#{benefitMandateBackingBean.doneForCheckin}">
											</h:commandLink>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
							<!--	End of Page data	--> <!-- Space for hidden fields --> <h:inputHidden
								id="targetHiddenToStoreMasterKey"
								value="#{benefitMandateBackingBean.benefitMandateMasterKey}"></h:inputHidden>
							<h:commandLink id="deleteBenefitMandate"
								style="display:none; visibility: hidden;"
								action="#{benefitMandateBackingBean.deleteBenefitMandate}">
								<f:verbatim />
							</h:commandLink> <h:inputHidden id="masterKeyUsedForUpdate"
								value="#{benefitMandateBackingBean.masterKeyUsedForUpdate}"></h:inputHidden>
							<!-- start: for editing the selected details --> <h:commandLink
								id="editCommandLink"
								action="#{benefitMandateBackingBean.editMandateDetails}">
								<f:verbatim />
							</h:commandLink> <h:inputHidden id="mandateIdHidden"
								value="#{benefitMandateBackingBean.masterKeyUsedForUpdate}">
							</h:inputHidden> <h:inputHidden id="tabHidden"
								value="#{standardBenefitBackingBean.benefitTypeTab}"></h:inputHidden>

							<h:inputHidden id="formattedTextHidden"
								value="#{benefitMandateBackingBean.formatedText}"></h:inputHidden>
							<h:inputHidden id="viewDivDataHidden" /> <h:inputHidden
								id="editDivDataHidden" /> <!-- end: for editing the selected details -->
							<!-- End of hidden fields  --></TD>
						</TR>
					</TABLE>
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
	
	
	copyHiddenToDiv_ewpd('MandateInfoForm:txtFundingArrangement','FundingArrangementDiv',2,1); 
	copyHiddenToDiv_ewpd('MandateInfoForm:txtState','StateDiv',3,1); 
	parseForDiv(document.getElementById('CitationNumberDiv'), document.getElementById('MandateInfoForm:txtCitationNumber')); 

hideTab();
hideIfNoValue('stateCodeDiv','txtState','stateCodeTextDiv');


var edited=false;
var checkin =false;
function runSpellCheck(){
	if(edited){
		var rswlCntrls = ["rapidSpellWebLauncher1"];
		var i=0;
		for(var i=0; i<rswlCntrls.length; i++){
			eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
		}
	}else if(checkin){
		document.getElementById('MandateInfoForm:checkinBenefit').click();
		checkin=false;
	}else{
		document.getElementById('MandateInfoForm:saveButton').click();
	}
	return false;
}
function spellFin(cancelled){
		if(checkin==true){
			document.getElementById('MandateInfoForm:checkinBenefit').click();
		}
		else if(edited==true)
			document.getElementById('MandateInfoForm:saveButton').click();	

	checkin=false;
	edited =false;
}
function showHide(){
	showHideDivs('editNotesDiv','viewNotesDiv','MandateInfoForm:editDivDataHidden');
	edited= true;
}
function checkIn(){
	checkin=true;
	return runSpellCheck();
}

	
function hideTab(){
	var tab;
	tab = document.getElementById("MandateInfoForm:tabHidden").value ;
	if(tab=="Standard Definition"){
		manTab.style.display='none';
		notTab.style.display='';
	}
	else{
		manTab.style.display='';
		notTab.style.display='none';
	}
}
function hideIfNoValue(divId, txtName,divTextId){
		hiddenStateObj = document.getElementById('MandateInfoForm:'+txtName);
		hideStateDiv = document.getElementById(divId);
		hideStateTextDiv = document.getElementById(divTextId);
		if(hiddenStateObj.value == 'ALL~ALL~E'){
			document.getElementById('MandateInfoForm:txtState').value = 'ALL~ALL~E';
			document.getElementById('stateCodeDiv').innerHTML = 'ALL';
		}else{
			document.getElementById(divId).style.visibility = 'visible';
			document.getElementById(divTextId).style.visibility = 'visible';
		}
	}

	InitializeNoteScreen('viewNotesDiv','MandateInfoForm:viewDivDataHidden','editNotesDiv','MandateInfoForm:editDivDataHidden','viewNoteBoxNormal','viewNoteBoxRed','MandateInfoForm:formattedTextHidden');
	function InitializeNoteScreen(viewDivId,viewDivHiddenId,editDivId,editDivHiddenId,normalNoteDivId,truncatedNoteDivId,formattedNoteHidden){
		copyFormattedNotesToViewDiv(normalNoteDivId,truncatedNoteDivId,formattedNoteHidden);
		document.getElementById(viewDivHiddenId).value = escape(document.getElementById(viewDivId).innerHTML);
		document.getElementById(editDivHiddenId).value = escape(document.getElementById(editDivId).innerHTML);
		showHideDivs(viewDivId,editDivId,viewDivHiddenId);
	}
	function copyFormattedNotesToViewDiv(normalNoteDivId,truncatedNoteDivId,formatedNotedHiddenId){
		var formattedNoteText = document.getElementById(formatedNotedHiddenId).value;		
		if(trim(formattedNoteText) == '')
			return;
		var divObjNormal = document.getElementById(normalNoteDivId);
		var divObjRed = document.getElementById(truncatedNoteDivId);
		var textLines = formattedNoteText.split('\n');
		var divValue1 = '';
		var divValue2 = '';
		for(i=0; i<textLines.length; i++) {
			if(i < 40) 
				divValue1 += (textLines[i]+"\n");
			else 
				divValue2 += (textLines[i]+"\n");
			
		}
		divObjNormal.innerText = divValue1;
		divObjRed.innerText = divValue2;
	}
	function showHideDivs(divIdToShow,divIdToHide,hiddenId_ShowDiv) {	
		hideDivObj = document.getElementById(divIdToHide);
		showDivObj = document.getElementById(divIdToShow);		
		hideDivObj.innerHTML = '';
		hideDivObj.style.visibility= 'hidden';
		showDivObj.innerHTML = unescape(document.getElementById(hiddenId_ShowDiv).value);
		showDivObj.style.visibility= 'visible';
	}

function validateText(){
	if(document.getElementById('MandateInfoForm:txtText').value.length > 3000){
		return false;
	}else{
		return true;
	}
}

function stateInfo()
{
 	var entityId=document.getElementById('MandateInfoForm:targetHiddenToStoreMasterKey').value;
	ewpdModalWindow_ewpd('../standardbenefitpopups/stateMultipleSelect.jsp'+getUrl()+'?lookUpAction='+'3'+'&parentCatalog='+'State Code'+'&entityId='+ entityId + '&entityType=' + 'stdbenefit','StateDiv','MandateInfoForm:txtState',3,1); 
	
}

function fundingArrangementInfo()
{
	var entityId=document.getElementById('MandateInfoForm:targetHiddenToStoreMasterKey').value;
	ewpdModalWindow_ewpd('../contractpopups/fundingArrangementPopup.jsp'+getUrl()+'?lookUpAction='+'3'+'&parentCatalog='+'Funding Arrangement'+'&entityId='+ entityId + '&entityType=' + 'stdbenefit','FundingArrangementDiv','MandateInfoForm:txtFundingArrangement',3,1); 
}


</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="benefitMandate" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
</HTML>

