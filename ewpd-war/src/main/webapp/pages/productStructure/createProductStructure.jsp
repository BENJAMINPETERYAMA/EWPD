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

<TITLE>Create Product Structure</TITLE>
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
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript">
	 var cal1 = new CalendarPopup();
	 cal1.showYearNavigation();

</script>
</HEAD>
<f:view>
	<BODY
		onkeypress="return submitOnEnterKey('productStructureForm:createButton')">
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><h:inputHidden id="dummy"
					value="#{productStructureGeneralInfoBackingBean.name}">
				</h:inputHidden> <jsp:include page="../navigation/top.jsp"></jsp:include>
				</TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="productStructureForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD width="273" valign="top" class="leftPanel">


							<DIV class="treeDiv" style="height:500px""><!-- Space for Tree  Data	--></DIV>

							</TD>

							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message
											value="#{productStructureGeneralInfoBackingBean.validationMessages}"></w:message>
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
												value="General Information" /></TD>
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

							<!--	Start of Table for actual Data	-->
							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText" width="100%">
								<TBODY>
									<TR>
										<TD colspan=3>
											<FIELDSET style="width:54%">
											<LEGEND> <FONT color="black">Business Domain</FONT></LEGEND>
										<TABLE border="0" cellspacing="0" cellpadding="3" width="102%">
											<TR>
												<TD width="41%"><h:outputText value="Line Of Business*"
													styleClass="#{productStructureGeneralInfoBackingBean.requiredLob ? 'mandatoryError': 'mandatoryNormal'}" /></TD>
												<h:inputHidden id="hiddenLob"
													value="#{productStructureGeneralInfoBackingBean.lob}"></h:inputHidden>
												<TD width="47%">
												<DIV id="divGroupSizeForLob" class="selectDataDisplayDiv"></DIV>
												<SCRIPT> //parseForDiv(document.getElementById('divGroupSizeForLob'), document.getElementById('productStructureForm:hiddenLob')); </SCRIPT>
												</TD>
												<TD width="13%">&nbsp;&nbsp;&nbsp;<h:commandButton
													alt="Line Of Business" id="lobButton"
													image="../../images/select.gif"
													onclick="ewpdModalWindow_ewpd('../popups/lineOfBusinessPopup.jsp'+getUrl()+'?lookUpAction=' + '1' + '&parentCatalog='+'Line of Business', 'divGroupSizeForLob', 'productStructureForm:hiddenLob',2,2);return false;"
													tabindex="1"></h:commandButton></TD>
											</TR>
											<TR>
												<TD width="41%"><h:outputText value="Business Entity*"
													styleClass="#{productStructureGeneralInfoBackingBean.requiredEntity ? 'mandatoryError': 'mandatoryNormal'}" /></TD>
												<h:inputHidden id="hiddenEntity"
													value="#{productStructureGeneralInfoBackingBean.entity}"></h:inputHidden>
												<TD width="47%">
												<DIV id="divGroupSizeForEntity" class="selectDataDisplayDiv"></DIV>
												<SCRIPT>// parseForDiv(document.getElementById('divGroupSizeForEntity'), document.getElementById('productStructureForm:hiddenEntity')); 
</SCRIPT>
												</TD>
												<TD width="13%">&nbsp;&nbsp;&nbsp;<h:commandButton
													alt="Business Entity" id="entityButton"
													image="../../images/select.gif"
													onclick="ewpdModalWindow_ewpd('../popups/businessEntityPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Business Entity','divGroupSizeForEntity','productStructureForm:hiddenEntity',2,2);return false;"
													tabindex="2"></h:commandButton></TD>
											</TR>
											<TR>
												<TD width="41%"><h:outputText value="Business Group*"
													styleClass="#{productStructureGeneralInfoBackingBean.requiredGroup ? 'mandatoryError': 'mandatoryNormal'}" /></TD>
												<h:inputHidden id="hiddenGroup"
													value="#{productStructureGeneralInfoBackingBean.group}"></h:inputHidden>
												<TD width="47%">
												<DIV id="divGroupSizeForGroup" class="selectDataDisplayDiv"></DIV>
												<SCRIPT> //parseForDiv(document.getElementById('divGroupSizeForGroup'), document.getElementById('productStructureForm:hiddenGroup')); 
												</SCRIPT>
												</TD>
												<TD width="13%">&nbsp;&nbsp;&nbsp;<h:commandButton
													alt="Business Group" id="groupButton"
													image="../../images/select.gif"
													onclick="ewpdModalWindow_ewpd('../popups/businessGroupPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'business group','divGroupSizeForGroup','productStructureForm:hiddenGroup',2,2);return false;"
													tabindex="3"></h:commandButton></TD>
											</TR>
											<!-- CARS START -->
											<TR>
												<TD width="41%"><h:outputText value="Market Business Unit*"
													styleClass="#{productStructureGeneralInfoBackingBean.requiredBusinessUnit ? 'mandatoryError': 'mandatoryNormal'}" /></TD>
												<h:inputHidden id="hiddenMarketBusinessUnit"
													value="#{productStructureGeneralInfoBackingBean.marketBusinessUnit}"></h:inputHidden>
												<TD width="47%">
													<DIV id="divGroupSizeForBusinessUnit" class="selectDataDisplayDiv"></DIV>
												</TD>
												<TD width="13%">&nbsp;&nbsp;&nbsp;<h:commandButton
													alt="Business Group" id="marketBusinessUnitButton"
													image="../../images/select.gif"
													onclick="ewpdModalWindow_ewpd('../popups/marketBusinessUnit.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Market Business Unit','divGroupSizeForBusinessUnit','productStructureForm:hiddenMarketBusinessUnit',2,2);return false;"
													tabindex="3"></h:commandButton></TD>
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
										<!-- <TD width="6" height="30"></TD> -->
										<TD height="9" width="23%">&nbsp;<h:outputText value="Name*"
											id="nameOutText"
											styleClass="#{productStructureGeneralInfoBackingBean.requiredName ? 'mandatoryError': 'mandatoryNormal'}" /></TD>
										<TD height="9" width="27%"><h:inputText
											styleClass="formInputField" id="nameOutTextView"
											value="#{productStructureGeneralInfoBackingBean.name}"
											maxlength="30" tabindex="4">
										</h:inputText></TD>
									</TR>
<!--Start product family september release -->

									<TR valign="top">
										<!-- <TD width="6" height="30"></TD> -->
										<TD height="9" width="23%">&nbsp;<h:outputText id="productFamily"
											value="Product Family*"
											styleClass="#{productStructureGeneralInfoBackingBean.familyInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
											<h:inputHidden id="productFamHidden"
											value="#{productStructureGeneralInfoBackingBean.productFamily}"></h:inputHidden>
										<TD height="9" width="27%"><div id="productFamilyDiv" class="selectDataDisplayDiv"></div></TD>
										<TD width="50%"><h:commandButton alt="productFamily"
											id="productFamilyButton" image="../../images/select.gif"
											onclick="getSelectedDomainReferenceData('../product/productFamilyPopup.jsp','productStructureForm','','hiddenLob','hiddenEntity','hiddenGroup','productFamilyDiv','productStructureForm:productFamHidden',2,2, 'Product Family');
																				return false;"
											tabindex="5">
										</h:commandButton></TD>
                                        <TD width="63%"></TD>
									</TR>
<!--End product family september release -->
									<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher1"
										textComponentName="productStructureForm:nameOutTextView"
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

									<TR id="strType1" style="display:none;">
										<!-- <TD width="6" height="30"></TD> -->
										<TD height="9" width="23%"">&nbsp;<h:outputText id="structureType"
											value="Structure Type*"
											styleClass="#{productStructureGeneralInfoBackingBean.requiredStructureType ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD width="27%">
										<table cellspacing="0" cellpadding="0" border="0">
										<tr>
										<TD height="9" width="43%">
											<h:selectOneMenu id="structType"
											styleClass="formInputField" tabindex="5"
											value="#{productStructureGeneralInfoBackingBean.structureType}"
											onchange="getStructureType()">
											<f:selectItems id="structTypeList"
												value="#{ReferenceDataBackingBeanCommon.entityTypeListForCombo}" />
										</h:selectOneMenu></TD>
										</tr>
										</table>
										</TD>
									</TR>
									<TR id="strType2" style="display:none;">
										<!-- <TD width="6" height="30"></TD> -->
										<TD height="9" width="23%">&nbsp;<h:outputText id="structureType1"
											value="Structure Type *"
											styleClass="#{productStructureGeneralInfoBackingBean.requiredStructureType ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD width="27%">
										<table cellspacing="0" cellpadding="0" border="0">
										<tr>
										<TD height="9" width="43%"><h:inputText id="structureType_copy"
												rendered="#{productStructureGeneralInfoBackingBean.copyFlag}"
												styleClass="formInputField" tabindex="5" readonly="true"
												value="#{productStructureGeneralInfoBackingBean.structureType}"></h:inputText> 
											
											</TD>
										</tr>
										</table>
										</TD>
									</TR>
									<tr id="sub1" style="display:none;">
										<!-- <TD width="6" height="30"></TD> -->
										<TD height="9" width="23%">&nbsp;<h:outputText id="mandType"
											value="Mandate Type *"
											styleClass="#{productStructureGeneralInfoBackingBean.requiredMandateType ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD height="9" width="27%"><h:selectOneMenu id="mandateType"
											styleClass="formInputField" tabindex="6"
											value="#{productStructureGeneralInfoBackingBean.mandateType}"
											onchange="getMandateType('true')">
											<f:selectItems id="mandateTypeList"
												value="#{ReferenceDataBackingBeanCommon.mandateTypeListForCombo}" />
										</h:selectOneMenu></TD>
									</TR>
									<tr id="sub2" style="display:none;">
										<!-- <TD width="6" height="30"></TD> -->
										<TD height="9" width="23%">&nbsp;<h:outputText id="state"
											value="Jurisdiction *"
											styleClass="#{productStructureGeneralInfoBackingBean.requiredState ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<h:inputHidden id="hid_state" value="#{productStructureGeneralInfoBackingBean.stateCode}"></h:inputHidden>
											<TD width="27%">
														<div id="stateDiv" class="selectDataDisplayDiv"></div>
														<SCRIPT> parseForDiv(document.getElementById('stateDiv'), document.getElementById('productStructureForm:hid_state')); </SCRIPT>		
											</TD>
											<TD width="345">
												<h:commandButton alt="State" id="stateButton" image="../../images/select.gif" onclick="getSelectedDomainReferenceData('../contractpopups/HeadQuarterstatePopup.jsp', 'productStructureForm',
													'hiddenLob', 'hiddenEntity', 'hiddenGroup','stateDiv','productStructureForm:hid_state', 2, 2, 'State Code');setRefDataUseFlag('productStructureForm', 'hid_state', 'stateDiv');return false;" tabindex = "7">
												</h:commandButton>
											</TD>
									</TR>
									<tr id="sub3" style="display:none;">
										<!-- <TD width="6" height="30"></TD> -->
										<TD height="9" width="23%">&nbsp;<h:outputText value="Jurisdiction" /></TD>
										<TD height="9" width="27%"><h:inputTextarea
											styleClass="selectDivReadOnly" id="stateCde" value="ALL"
											readonly="true" style="border:0"></h:inputTextarea></TD>
									</TR>
									
									
									<TR valign="top">
										<!-- <TD width="6" height="30"></TD> -->
										<TD height="8" width="112">&nbsp;<h:outputText value="Description*"
											styleClass="#{productStructureGeneralInfoBackingBean.requiredDesc ? 'mandatoryError': 'mandatoryNormal'}" /></TD>
										<TD height="8" width="27%"><h:inputTextarea
											styleClass="formTxtAreaField_GeneralDesc"
											id="descriptionOutTextView"
											value="#{productStructureGeneralInfoBackingBean.description}"
											tabindex="8">
										</h:inputTextarea></TD>

									</TR>

									<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher2"
										textComponentName="productStructureForm:descriptionOutTextView"
										rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Product Structure Description"
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
										<!-- <TD width="6"></TD> -->
										<TD width="112">&nbsp;<h:outputText value="Effective Date*"
											styleClass="#{productStructureGeneralInfoBackingBean.requiredEffectiveDate ? 'mandatoryError': 'mandatoryNormal'}" /></TD>
										<TD width="27%"><h:inputText styleClass="formInputField"
											id="effectiveDate_txt"
											value="#{productStructureGeneralInfoBackingBean.effectiveDate}"
											maxlength="10" tabindex="9" /><BR class="brclass"><SPAN class="dateFormat">(mm/dd/yyyy)</SPAN></TD>
										<TD valign="top" align="left" width="345"><A href="#"
											onclick="cal1.select('productStructureForm:effectiveDate_txt','anchor2','MM/dd/yyyy','anchor1','MM/dd/yyyy'); return false;"
											name="anchor1" id="anchor1"
											title="cal1.select('productStructureForm:effectiveDate_txt','anchor1','MM/dd/yyyy'); return false;">
										<h:commandButton image="../../images/cal.gif"
											style="cursor: hand" alt="Cal" tabindex="10" /> </A></TD>

									</TR>
									<TR valign="top">
										<!-- <TD width="6" height="30"></TD> -->
										<TD width="112" height="30">&nbsp;<h:outputText value="Expiry Date*"
											styleClass="#{productStructureGeneralInfoBackingBean.requiredExpiryDate ? 'mandatoryError': 'mandatoryNormal'}" /></TD>
										<TD height="30" width="27%"><h:inputText
											styleClass="formInputField" id="expiryDate_txt"
											value="#{productStructureGeneralInfoBackingBean.expiryDate}"
											maxlength="10" tabindex="11" /> 
                                        <BR class="brclass">
                                        <SPAN class="dateFormat">(mm/dd/yyyy)</SPAN></TD>
										<TD valign="top" height="30" width="345"><A href="#"
											onclick="cal1.select('productStructureForm:expiryDate_txt','anchor2','MM/dd/yyyy'); return false;"
											name="anchor2" id="anchor2"
											title="cal1.select(document.forms[0].productStructureForm:expiryDate_txt,'anchor2','MM/dd/yyyy'); return false;">
										<h:commandButton image="../../images/cal.gif"
											style="cursor: hand" alt="Cal" tabindex="12" /> </A></TD>
									</TR>
									<TR>
									<TD width="23%"><h:commandButton value="Create"
											styleClass="wpdButton" id="createButton"
											onclick="callCreateMethod(); return false;"
											tabindex="13">
										</h:commandButton></TD>
										<!-- <TD width="184">&nbsp;</TD> -->
									</TR>
								</TBODY>
							</TABLE>
							<!--	End of Page data	--></FIELDSET>
							</TD>
						</TR>
					</TABLE>
					<h:inputHidden id="copyHidden"
						value="#{productStructureGeneralInfoBackingBean.copyFlag}" />
					<h:inputHidden id="domainChanged"
						value="#{productStructureGeneralInfoBackingBean.domainChange}"></h:inputHidden>
					<h:inputHidden id="dateChanged"
						value="#{productStructureGeneralInfoBackingBean.dateChange}"></h:inputHidden>
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
					<h:inputHidden id="oldMarketBusinessUnitHid"
						value="#{productStructureGeneralInfoBackingBean.oldMarketBusinessUnit}" />	
						

					<h:commandLink id="createLink"
						style="display:none; visibility: hidden;"
						action="#{productStructureGeneralInfoBackingBean.saveGeneralInfo}">
						<f:verbatim />
					</h:commandLink>			
					<!-- Space for hidden fields -->
					<h:commandLink id="hiddenLnk1"
						style="display:none; visibility: hidden;">
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
<script language="javascript">


    copyHiddenToDiv_ewpd('productStructureForm:hiddenLob','divGroupSizeForLob',2,2);
    copyHiddenToDiv_ewpd('productStructureForm:hiddenEntity','divGroupSizeForEntity',2,2);
	copyHiddenToDiv_ewpd('productStructureForm:hiddenGroup','divGroupSizeForGroup',2,2);
//CARS START
	copyHiddenToDiv_ewpd('productStructureForm:hiddenMarketBusinessUnit','divGroupSizeForBusinessUnit',2,2);
//CARS END
	copyHiddenToDiv_ewpd('productStructureForm:productFamHidden','productFamilyDiv',2,2);

	
	document.getElementById('productStructureForm:nameOutTextView').focus(); // for on load default selection
var actionOne = '1';
var beCatalog = 'Business Entity';
var bgCatalog = 'business group';
var lobCatalog = 'Line of Business';
var stateCode = 'State Code';
var copyFlag = document.getElementById("productStructureForm:copyHidden").value;
copyHiddenToDiv_ewpd('productStructureForm:hid_state','stateDiv',2,2); 
getStructureType();
getMandateType('false');
function getStructureType()
	{
	var i;
	var obj;
	obj = document.getElementById("productStructureForm:structType");
	copyObj = document.getElementById("productStructureForm:structureType_copy");
if(obj!=null && copyFlag=="false"){
	i= obj.selectedIndex;
var type = obj.value;
if(type == 'MANDATE')
		// if(i== 1)
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
	else if(copyObj != null && copyFlag =="true"){
		if(copyObj.value == 'MANDATE'||copyObj.value == 'Mandate')
		// if(i== 1)
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
}
if(copyObj != null && copyObj.value == 'Standard'){
	sub1.style.display='none';
	sub2.style.display='none';
}
function getMandateType(change)
	{
	// To reset the state value, while copying (if the mandate type is changed)
	if(change=='true'){
		document.getElementById('productStructureForm:hid_state').value="";
		copyHiddenToDiv_ewpd('productStructureForm:hid_state','stateDiv',2,2); 
	}

	var i;
	var obj;

	obj = document.getElementById("productStructureForm:mandateType");
	i= obj.selectedIndex;
		if(i== 1 || i==3)
		{
		sub2.style.display='';	
		sub3.style.display='none';	
		}
		else if(i== 2){ 
		sub2.style.display='none';
		sub3.style.display='';
		}else
		{
		sub2.style.display='none';
		sub3.style.display='none';
		}
	}

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
	var oldEffectiveDate = document.getElementById('productStructureForm:oldEffectiveDateHid').value;
	var oldExpiryDate = document.getElementById('productStructureForm:oldExpiryDateHid').value;
	var currentEffectiveDate = document.getElementById('productStructureForm:effectiveDate_txt').value;
	var currentExpiryDate = document.getElementById('productStructureForm:expiryDate_txt').value;
	var oldLineOfBusinessCode = document.getElementById('productStructureForm:oldLobHid').value;
	var oldBusinessEntityCode = document.getElementById('productStructureForm:oldEntityHid').value;
	var oldBusinessGroupCode = document.getElementById('productStructureForm:oldGroupHid').value;
//CARS START
	var oldMarketBusinessUnitCode = document.getElementById('productStructureForm:oldMarketBusinessUnitHid').value;
	var newMarketBusinessUnitCode = document.getElementById('productStructureForm:hiddenMarketBusinessUnit').value;
//CARS END
	var newLineOfBusinessCode = document.getElementById('productStructureForm:hiddenLob').value;
	var newBusinessEntityCode = document.getElementById('productStructureForm:hiddenEntity').value;
	var newBusinessGroupCode = document.getElementById('productStructureForm:hiddenGroup').value;
		// Business Domian Validation 
	if((oldLineOfBusinessCode != newLineOfBusinessCode) 
		 || (oldBusinessEntityCode != newBusinessEntityCode) || (oldBusinessGroupCode != newBusinessGroupCode) || (oldMarketBusinessUnitCode != newMarketBusinessUnitCode)){
		 document.getElementById('productStructureForm:domainChanged').value = true;	
	}
	if((oldEffectiveDate != currentEffectiveDate) || (oldExpiryDate != currentExpiryDate)){
		document.getElementById('productStructureForm:dateChanged').value = true;
	}
if(document.getElementById("productStructureForm:structureType_copy").value=="Standard" || document.getElementById("productStructureForm:structureType_copy").value=="STANDARD" )
	document.getElementById("productStructureForm:structType").value="STANDARD";
else
	document.getElementById("productStructureForm:structType").value="MANDATE";

 // document.getElementById("productStructureForm:structType").value = document.getElementById("productStructureForm:structureType_copy").value;
}
return runSpellCheck();

}

document.getElementById('divGroupSizeForLob').style.height= "17px";
document.getElementById('divGroupSizeForEntity').style.height= "17px";
document.getElementById('divGroupSizeForGroup').style.height= "17px";
document.getElementById('divGroupSizeForBusinessUnit').style.height= "17px";

function runSpellCheck(){
	var rswlCntrls = ["rapidSpellWebLauncher1","rapidSpellWebLauncher2"];
	var i=0;
	for(var i=0; i<rswlCntrls.length; i++){
		eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
	}
	return false;
}
function spellFin(cancelled){

		document.getElementById('productStructureForm:createLink').click();
}

</script>
</HTML>
