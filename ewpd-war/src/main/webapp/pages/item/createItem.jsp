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

<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/prototype.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript" src="../../js/rico.js"></script>
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
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript">
	 var cal1 = new CalendarPopup();
	 cal1.showYearNavigation();
	 function noenter(){
  	 return !(window.event && window.event.keyCode == 13); 
	 }
</script>
<TITLE>Create Item</TITLE>
<!-- <script language="JavaScript" src="../../js/wpd.js"></script> -->
</HEAD>
<f:view>
	<BODY onkeypress="return submitOnEnterKey('itemForm:createItem');">
	<hx:scriptCollector id="scriptCollector1">

		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<h:inputHidden id="dummy" value="#{itemBackingBean.catalogId}" />

				<TD><jsp:include page="../navigation/top.jsp"></jsp:include></TD>
			</TR>

			<TR>
				<TD><h:form styleClass="form" id="itemForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD width="273" valign="top" class="leftPanel">


							<DIV class="treeDiv" style="height:380px"><!-- Space for Tree  Data	--></DIV>

							</TD>

							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message value="#{itemBackingBean.validationMessages}"></w:message>
										</TD>
									</TR>
								</TBODY>
							</TABLE>

							<!-- Table containing Tabs -->
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<TR>
								<tr >
								      
                                                     <td align="left">
                                                     
                                                    <h:selectOneRadio onclick="showStar();" id = "radioBtn" value = "#{itemBackingBean.srdaFlag}" > 
                                                                  
                                                         <f:selectItem itemValue = "eWPD" itemLabel = "eWPD" />  
                                                         <f:selectItem itemValue = "SRDA" itemLabel = "SRDA" />
                                                                           
                                                   </h:selectOneRadio> 
                                                     
                                                   </td>
                                      </tr>
									<TD width="200">
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
									<TD width="100%"></TD>
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
										<TD width="168">&nbsp;<h:outputText value="Catalog Name*"
											styleClass="#{itemBackingBean.requiredCatalogID ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD width="177">
										<DIV id="catalogDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="catalogString"
											value="#{itemBackingBean.catalogId}"></h:inputHidden></TD>
										<TD valign="top" width="22"><h:commandButton alt="catalogName"
											id="catalogNameButton" image="../../images/select.gif"
											onclick="showSrdaDataPopup();
															qualifierCheck();return false;"
											tabindex="1">
										</h:commandButton></TD>
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
										<TD width="168">&nbsp;<h:outputText value="Primary Code*"
											styleClass="#{itemBackingBean.requiredPrimaryCode ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD width="177"><h:inputText styleClass="formInputField"
											id="txtPrimary" maxlength="30" tabindex="2"
											value="#{itemBackingBean.primaryCode}" /></TD>
									</TR>
									<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher1"
										textComponentName="itemForm:txtPrimary"
										rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Item Primary Code"
										modalHelperPage="../spellcheck/RapidSpellModalHelper.html"
										showNoErrorsMessage="False" showFinishedMessage="False"
										includeUserDictionaryInSuggestions="True" allowAnyCase="True"
										allowMixedCase="True" windowWidth="570" windowHeight="300"
										modal="False" showButton="False" windowX="-1"
										warnDuplicates="False" textComponentInterface="Custom">
									</RapidSpellWeb:rapidSpellWebLauncher>


									<TR>
										<TD height="25" width="168" style="display:none"  id="secondaryCodeSrda">&nbsp;<h:outputText
											 value="Secondary Code*" styleClass="#{itemBackingBean.requiredSecondaryCode ? 'mandatoryError': 'mandatoryNormal'}" /></TD>
										<TD height="25" width="168" id="secondaryCodeEwpd">&nbsp;<h:outputText
											id="secondaryCode" value="Secondary Code" /></TD>
										<TD height="25" width="177"><h:inputText id="txtSecondary"
											maxlength="30" styleClass="formInputField" tabindex="3"
											value="#{itemBackingBean.secondaryCode}" /></TD>
									</TR>

									<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher2"
										textComponentName="itemForm:txtSecondary"
										rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Item Secondary Code"
										modalHelperPage="../spellcheck/RapidSpellModalHelper.html"
										showNoErrorsMessage="False" showFinishedMessage="False"
										includeUserDictionaryInSuggestions="True" allowAnyCase="True"
										allowMixedCase="True" windowWidth="570" windowHeight="300"
										modal="False" showButton="False" windowX="-1"
										warnDuplicates="False" textComponentInterface="Custom">
									</RapidSpellWeb:rapidSpellWebLauncher>
									<TR>
										<TD height="25" width="168" valign="top">&nbsp;<h:outputText
											value="Description*"
											styleClass="#{itemBackingBean.requiredDescription ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD width="177"><h:inputTextarea
											styleClass="formTxtAreaField_GeneralDesc"
											id="itemDescription_txt" tabindex="4"
											value="#{itemBackingBean.description}"></h:inputTextarea></TD>

									</TR>
									<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher3"
										textComponentName="itemForm:itemDescription_txt"
										rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Item Description"
										modalHelperPage="../spellcheck/RapidSpellModalHelper.html"
										showNoErrorsMessage="False" showFinishedMessage="False"
										includeUserDictionaryInSuggestions="True" allowAnyCase="True"
										allowMixedCase="True" finishedListener="spellFin"
										windowWidth="570" windowHeight="300" modal="False"
										showButton="False" windowX="-1" warnDuplicates="False"
										textComponentInterface="Custom">
									</RapidSpellWeb:rapidSpellWebLauncher>
									<TR id="frequencyFlagTR" style="display:none;">
										<TD height="25" width="168" valign="top">&nbsp;<h:outputText
											value="Frequency Required*"
											styleClass="#{itemBackingBean.requiredFrequencyFlag ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD width="177"><h:selectOneMenu id="frequencyFlagId"
											styleClass="formInputField" tabindex="5"
											value="#{itemBackingBean.frequencyFlag}">
											<f:selectItems id="frequencyFlagList"
												value="#{itemBackingBean.frequencyFlagListForCombo}" />
										</h:selectOneMenu></TD>

									</TR>
									<TR>
										<TD width="168"><h:commandButton value="Create"
											id="createItem" styleClass="wpdButton"
											onclick="return validatePrimaryCode() && validateSecondaryCode() && validateDesc() && runSpellCheck();" tabindex="17">
										</h:commandButton> <h:commandLink id="create" style="hidden"
											action="#{itemBackingBean.createItem}">
										</h:commandLink></TD>
										<TD width="177">&nbsp;</TD>
									</TR>

								</TBODY>
							</TABLE>
							<!--	End of Page data	--></FIELDSET>
							</TD>
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
<script>

if(radioBtn1 = document.getElementById('itemForm:radioBtn:1').checked == true){
	radioBtn1.checked=true;
	document.getElementById('secondaryCodeSrda').style.display = 'inline';
	document.getElementById('secondaryCodeEwpd').style.display = 'none';
}else {
	document.getElementById('itemForm:radioBtn:0').checked =true
	
} 

function showSrdaDataPopup(){
	if(document.getElementById('itemForm:radioBtn:0').checked ===true){
		ewpdModalWindow_ewpd('../popups/selectCatalogPopupForItemCreate.jsp'+getUrl()+'?'+ 'date='+currentTime + '&title=' + 'Catalog Name Popup','catalogDiv','itemForm:catalogString',4,2);
	}else{
		ewpdModalWindow_ewpd('../popups/selectSrdaCatalogPopupForItemCreate.jsp'+getUrl()+'?'+ 'date='+currentTime + '&title=' + 'Catalog Name Popup','catalogDiv','itemForm:catalogString',4,2);
	}
	
}

function showStar(){
	if( document.getElementById('itemForm:radioBtn:0').checked == true){
		document.getElementById('secondaryCodeEwpd').style.display = 'inline';
		document.getElementById('secondaryCodeSrda').style.display = 'none';
	}else{
		document.getElementById('secondaryCodeSrda').style.display = 'inline';
		document.getElementById('secondaryCodeEwpd').style.display = 'none';
	}
	
} 

function runSpellCheck(){
	var rswlCntrls = ["rapidSpellWebLauncher1","rapidSpellWebLauncher2","rapidSpellWebLauncher3"];
	var i=0;
	for(var i=0; i<rswlCntrls.length; i++){
		eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
	}
	return false;
}
function spellFin(cancelled){

		document.getElementById('itemForm:create').click();	
}
document.getElementById('itemForm:txtPrimary').focus(); // for on load default selection

	var currentDate = new Date();
	var currentTime = currentDate.getTime();
copyHiddenToDiv_ewpd('itemForm:catalogString','catalogDiv',4,2 );
function qualifierCheck(){
	var catalog = document.getElementById('catalogDiv').innerText.toUpperCase();
	if(catalog == "QUALIFIER"){
		frequencyFlagTR.style.display = '';
	}else{
		frequencyFlagTR.style.display = 'none';
	}
}
var catalogName = document.getElementById('catalogDiv').innerText.toUpperCase();
if(catalogName == "QUALIFIER"){
		frequencyFlagTR.style.display = '';
}else{
		frequencyFlagTR.style.display = 'none';
}
var validationMessagePc ="Primary code allows only ASCII characters and should not have linefeeds or non-ASCII characters. Please correct and try again.";
	function validatePrimaryCode(){
	if(validateChars('itemForm:txtPrimary',validationMessagePc)){
			return true;
		}else{
			document.getElementById('itemForm:txtPrimary').focus();
			return false;
		}
} 
var validationMessageSc ="Secondary code allows only ASCII characters and should not have linefeeds or non-ASCII characters. Please correct and try again.";
	function validateSecondaryCode(){
	if(validateChars('itemForm:txtSecondary',validationMessageSc)){
			return true;
		}else{
			document.getElementById('itemForm:txtSecondary').focus();
			return false;
		}
} 
 var validationMessage ="Description only allows only ASCII characters and should not have linefeeds or non-ASCII characters. Please correct and try again.";
	function validateDesc(){
	if(validateChars('itemForm:itemDescription_txt',validationMessage)){
			return true;
		}else{
			document.getElementById('itemForm:itemDescription_txt').focus();
			return false;
		}
} 


</script>

</HTML>
