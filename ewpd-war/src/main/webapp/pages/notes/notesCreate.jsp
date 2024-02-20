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
<TITLE>Create Note</TITLE>
<!-- <script language="JavaScript" src="../../js/wpd.js"></script> -->
</HEAD>
<f:view>
	<BODY>
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<h:inputHidden id="dummy" value="#{notesBackingBean.name}"></h:inputHidden>
				<h:inputHidden id="load" value="#{notesBackingBean.loadNotes}"></h:inputHidden>
				<TD><jsp:include page="../navigation/top.jsp"></jsp:include></TD>
			</TR>

			<TR>
				<TD><h:form styleClass="form" id="notesCreateForm">
					<TABLE width="99%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD width="273" valign="top" class="leftPanel" height="434">


							<DIV class="treeDiv" style="height:380px"><!-- Space for Tree  Data	--></DIV>

							</TD>

							<TD colspan="2" valign="top" class="ContentArea" height="434">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message></w:message></TD>
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
										<TD width="168">&nbsp;<h:outputText value="Name*"
											styleClass="#{notesBackingBean.nameValdn ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD width="221"><h:inputText
											styleClass="formInputFieldForNotes" id="txtNoteNm"
											tabindex="1" maxlength="30" value="#{notesBackingBean.name}"
											onkeypress="return noenter();" /></TD>
									</TR>
								<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher2"
										textComponentName="notesCreateForm:txtNoteNm"
										rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Note Name"
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
										<TD width="168">&nbsp;<h:outputText id="noteType"
											value="Type*"
											styleClass="#{notesBackingBean.typeValdn ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD width="221"><h:selectOneMenu id="mandateTypeName"
											styleClass="formInputFieldForNotes" tabindex="2"
											value="#{notesBackingBean.type}">
											<f:selectItems id="noteTypeList"
												value="#{ReferenceDataBackingBeanCommon.noteTypeListForCombo}" />
										</h:selectOneMenu></TD>
									</TR>
									
									<TR>
										<TD width="168">&nbsp;<h:outputText id="systemDomainLabel"
											value="Target Systems*"
											styleClass="#{notesBackingBean.systemDomainValdn?'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD width="221">
										<DIV id="systemDomainDiv" class="formInputFieldForNotes"></DIV>
										</TD>
										<TD align="left" width="378"><h:commandButton
											id="systemDomainButton" alt="Select"
											image="../../images/select.gif" style="cursor: hand"
											onclick="ewpdModalWindow_ewpd('../popups/systemDomainPopup.jsp','systemDomainDiv','notesCreateForm:systemDomainTxtHidden',2,1); return false;"
											tabindex="3" /> <h:inputHidden id="systemDomainTxtHidden"
											value="#{notesBackingBean.systemDomain}"></h:inputHidden></TD>
									</TR>
									<TR>
										<TD width="168" valign="top">&nbsp;<h:outputText value="Text*"
											styleClass="#{notesBackingBean.textValdn? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD colspan="2" valign="top"><h:inputTextarea
											styleClass="formTxtAreaField_NoteText" id="txtText"
											tabindex="4" value="#{notesBackingBean.text}"
											onkeypress="return validateText();" onblur="return validateNoteText();"/> 
										</TD>
									</TR>
									<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher1"
										textComponentName="notesCreateForm:txtText"
										rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Note Text"
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
										<TD width="139">&nbsp;
											<h:commandButton value="Create" id="createNote"
												styleClass="wpdButton" onclick="return runSpellCheck();" tabindex="5">
											</h:commandButton>
											<h:commandLink id="create"
												style="hidden" action="#{notesBackingBean.createNote}">
											</h:commandLink>
										</TD>
										<TD width="221">&nbsp;</TD>
									</TR>
								</TBODY>
							</TABLE>
							<!--	End of Page data	--></FIELDSET>
							</TD>
						</TR>
					</TABLE>
					<h:inputHidden id="copyFlag" value="#{notesBackingBean.copyFlag}"></h:inputHidden>
					<h:inputHidden id="noteId" value="#{notesBackingBean.noteId}"></h:inputHidden>
					<h:inputHidden id="version" value="#{notesBackingBean.version}"></h:inputHidden>


				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>

<script language="JavaScript"><!--
// for on load default selection

document.getElementById('notesCreateForm:txtNoteNm').focus(); 
copyHiddenToDiv('notesCreateForm:systemDomainTxtHidden','systemDomainDiv');	
var validationMessage = "Notes text has extended special characters, which are copied directly from Microsoft Word document.\nExtended special characters are not supported by WPD application. Please correct and try again.";

function runSpellCheck(){
	var rswlCntrls = ["rapidSpellWebLauncher2","rapidSpellWebLauncher1"];
	var i=0;
	for(var i=0; i<rswlCntrls.length; i++){
		eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
	}
return false;
}
function spellFin(cancelled){
		document.getElementById('notesCreateForm:create').click();	
}
function validateText(){
	if(document.getElementById('notesCreateForm:txtText').value.length > 3000){
		return false;
	}else{
		return true;
	}
}
function validateNoteText(){
	if(validateChars('notesCreateForm:txtText',validationMessage))
			return true;
		else{
			document.getElementById('notesCreateForm:txtText').focus();
			return false;
		}
}
--></script>
</HTML>
