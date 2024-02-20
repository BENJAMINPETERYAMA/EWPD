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
<TITLE>Edit Note</TITLE>
<script language="JavaScript" src="../../js/wpd.js"></script>
</HEAD>
<f:view>
	<BODY>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td><jsp:include page="../navigation/top_Print.jsp"></jsp:include></td>
		</tr>

		<tr>
			<td><h:form styleClass="form" id="notesCreateForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel" height="850">


						<DIV class="treeDiv"><!-- Space for Tree  Data	--></DIV>

						</TD>

						<TD colspan="2" valign="top" class="ContentArea" height="850">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message></w:message></TD>
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
											value=" General Information" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="25%">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/tabNormalLeft.gif" width="3" height="21" /></td>
										<td width="100%" class="tabNormal"><h:commandLink
											action="#{notesBackingBean.loadNoteDomains}" 
											onmousedown="javascript:navigatePageAction(this.id);" id="dataDomainId">
											<h:outputText id="dataDomainTable" value="Data Domain" />
										</h:commandLink></td>

										<td width="2" align="right"><img
											src="../../images/tabNormalLeft.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="100%"></td>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	-->
						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText">
							<TBODY>
								<h:inputHidden id="noteIdHidden"
									value="#{notesBackingBean.noteId}"></h:inputHidden>
								<h:inputHidden id="versionHidden"
									value="#{notesBackingBean.version}" />
								<h:inputHidden id="statusHidden"
									value="#{notesBackingBean.status}" />
								<h:inputHidden id="stateHidden"
									value="#{notesBackingBean.state}" />
								<TR height="30">
									<TD width="25%">&nbsp;<h:outputText value="Id*" /></TD>
									<TD width="214"><h:outputText id="sysId" value="#{notesBackingBean.noteId}" /></TD>
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
								<TR height="30">
									<TD width="25%">&nbsp;<h:outputText value="Name*"
										styleClass="#{notesBackingBean.nameValdn ? 'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<TD width="214"><h:inputHidden id="noteNameHidden"
										value="#{notesBackingBean.name}"></h:inputHidden> <h:inputText
										id="txtNoteNm" styleClass="formInputFieldForNotes"
										value="#{notesBackingBean.name}" maxlength="30"
										rendered="#{notesBackingBean.checkForEdit}" /> <h:outputText
										id="txtNoteNmreadonly" styleClass="formInputFieldReadOnly"
										rendered="#{!notesBackingBean.checkForEdit}"
										value="#{notesBackingBean.name}"></h:outputText></TD>
								</TR>
								<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher1"
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
								<TR height="30">
									<TD valign="top" width="25%">&nbsp;<h:outputText id="noteType"
										value="Type*"
										styleClass="#{notesBackingBean.typeValdn ? 'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<TD width="214"><h:selectOneMenu id="mandateTypeName"
										styleClass="formInputFieldForNotes" 
										value="#{notesBackingBean.type}" onchange="valueModified();"
										rendered="#{notesBackingBean.checkForEdit}">
										<f:selectItems id="noteTypeList"
											value="#{ReferenceDataBackingBeanCommon.noteTypeListForCombo}" />
									</h:selectOneMenu> <h:outputText id="mandateTypeNameReadOnly"
										styleClass="formInputFieldReadOnly"
										rendered="#{!notesBackingBean.checkForEdit}"
										value="#{notesBackingBean.noteTypeDesc}"></h:outputText>
										<h:inputHidden id="typeHidden" value="#{notesBackingBean.type}"/>
									</TD>
								</TR>
								

								<TR height="30">
									<TD valign="top" width="25%">&nbsp;<h:outputText
										id="systemDomainLabel" value="Target Systems*"
										styleClass="#{notesBackingBean.systemDomainValdn?'mandatoryError': 'mandatoryNormal'}" /></TD>
									<TD width="214">
									<DIV id="systemDomainDiv" class="formInputFieldForNotes"></DIV>
									<h:inputHidden id="systemDomainTxtHiddenOld" value="#{notesBackingBean.systemDomain}"/>
									<h:inputHidden id="systemDomainTxtHidden"
										value="#{notesBackingBean.systemDomain}"></h:inputHidden></TD>
									<TD width="400"><h:commandButton id="systemDomainButton"
										alt="Select" image="../../images/select.gif"
										style="cursor: hand"
										onclick="systemDomain(); return false;"
										tabindex="1" /></TD>
								</TR>
								<TR>
									<TD valign="top" width="25%"><br>
									&nbsp;<h:outputText value="Text*"
										styleClass="#{notesBackingBean.textValdn? 'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<TD colspan="2" valign="top"><!-- *********************************************************************** -->
									<div id="viewNotesDiv" style="z-index:1;">
									<table cellpadding="0" cellspacing="0" border="0">
										<tr>
											<td>
											<div id="spacingdiv" style="z-index:3;">
											<table cellpadding="0" cellspacing="0" border="0">
												<tr>
													<td>&nbsp;</td>
												</tr>
											</table>
											</div>
											<div id="viewNoteBox" class="noteViewDiv">
												<div id="viewNoteBoxNormal" class="noteViewNormalTextDiv" style="width:510"></div>
												<div id="viewNoteBoxRed" class="noteViewTruncatedTextDiv"></div>
											</div>
											</td>
											<td align="left" valign="bottom">&nbsp;<h:commandButton
												id="editButton" value="Edit" tabindex = "2" styleClass="wpdbutton"
												onclick="showHide(); return false;" /></td>
											&nbsp;&nbsp;
										</tr>
									</table>
									</div>
									<div id="editNotesDiv" style="z-index:3;">
									<table cellpadding="0" cellspacing="0" border="0">
										<tr>
											<td><h:inputTextarea styleClass="formTxtAreaField_NoteText"
												id="txtText" value="#{notesBackingBean.text}"
												onkeypress="return validateText();" onblur="return validateNoteText();">
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
								<TR>
									<TD width="25%">&nbsp;<h:outputText value="Created By" /></TD>
									<TD colspan="2"><h:outputText
										value="#{notesBackingBean.createdUser}" /></TD>
								</TR>
								<TR>
									<TD width="25%">&nbsp;<h:outputText value="Created Date" /></TD>
									<TD colspan="2"><h:outputText
										value="#{notesBackingBean.createdTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText></TD>
								</TR>
								<TR>
									<TD width="25%">&nbsp;<h:outputText
										value="Last Updated By" /></TD>
									<TD colspan="2"><h:outputText
										value="#{notesBackingBean.lastUpdatedUser}" /></TD>
								</TR>
								<TR>
									<TD width="25%">&nbsp;<h:outputText value="Last Updated Date" /></TD>
									<TD colspan="2"><h:outputText
										value="#{notesBackingBean.lastUpdatedTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText></TD>
								</TR>
									<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher2"
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
									<TD width="171">&nbsp;
										<h:commandButton value="Save" id="createNote"
											styleClass="wpdButton" onclick="return runSpellCheck();" tabindex="3"
											onmousedown="javascript:checkModified();javascript:savePageActionForNotesPage(this.id, 'notesCreateForm');">

										</h:commandButton>
										<h:commandLink id="create"
											style="hidden" action="#{notesBackingBean.updateNotes}">
										</h:commandLink>
									</TD>
								</TR>

							</TBODY>
						</TABLE>
						<!--	End of Page data	--></fieldset>
						<br>

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<TABLE border="0" cellspacing="0" cellpadding="3" width="100%">
							<tr>
								<td width="15"><h:selectBooleanCheckbox id="checkIn"
									value="#{notesBackingBean.checkInFlag}" tabindex="4"></h:selectBooleanCheckbox></td>
								<td align="left"><h:outputText value="Check In" /></td>
								<td align="left" width="127">
								<table Width=100%>
									<tr>
										<td><h:outputText id="outTxtState" value="State" /></td>
										<td>:&nbsp;<h:outputText id="state"
											value="#{notesBackingBean.state}" /></td>
									</tr>
									<tr>
										<td><h:outputText value="Status" /></td>
										<td>:&nbsp;<h:outputText id="status"
											value="#{notesBackingBean.status}" /></td>
									</tr>
									<tr>
										<td><h:outputText value="Version" /></td>
										<td>:&nbsp;<h:outputText id="version"
											value="#{notesBackingBean.version}" /></td>
									</tr>
								</table>
								</td>
						</TABLE>
						</fieldset>

						<BR>
						&nbsp;&nbsp;
							<h:commandButton value="Done"
								styleClass="wpdButton" onclick="return checkIn();" tabindex="5">
							</h:commandButton>
							<h:commandLink id="checkinNote"
								style="hidden" action="#{notesBackingBean.checkIn}">
							</h:commandLink>
						</TD>
					</TR>
				</table>
				<!-- ************************************************* -->
				<h:inputHidden id="noteDescHidden"
					value="#{notesBackingBean.noteDescHidden}"></h:inputHidden>
				<h:inputHidden id="formattedNotesHidden"
					value="#{notesBackingBean.formattedNotes}"></h:inputHidden>
				<h:inputHidden id="originalNotesHidden"></h:inputHidden>
				<h:inputHidden id="viewDivDataHidden"></h:inputHidden>
				<h:inputHidden id="editDivDataHidden"></h:inputHidden>
				<h:inputHidden id="hiddenValueHolder"></h:inputHidden>
				<h:inputHidden id="hiddenValueChanged" value="1"></h:inputHidden>
				<h:inputHidden id="hiddenTypeValueChanged" value="0"></h:inputHidden>
				<h:inputHidden id="hiddenCreatedUser"
					value="#{notesBackingBean.createdUser}"></h:inputHidden>
				<h:inputHidden id="hiddenCreatedDate"
					value="#{notesBackingBean.createdTimestamp}">
					<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
				</h:inputHidden>
				<h:inputHidden id="hiddenLastUser"
					value="#{notesBackingBean.lastUpdatedUser}"></h:inputHidden>
				<h:inputHidden id="hiddenLastDate"
					value="#{notesBackingBean.lastUpdatedTimestamp}">
					<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
				</h:inputHidden>
				<h:inputHidden id="hiddenEditNote"
					value="#{notesBackingBean.hiddenEditNote}"></h:inputHidden>
				<h:inputHidden id="time" value="#{requestScope.timezone}">
				</h:inputHidden>
				<h:commandLink id="noteUpdate"
					style="display:none; visibility: hidden;"
					action="#{notesBackingBean.updateNotes}">
					<f:verbatim />
				</h:commandLink>

				<h:inputHidden id="notesInActivelyUsedStatus" value="#{notesBackingBean.notesInActivelyUsedStatus}"/>
				<h:inputHidden id="identifyTab" value="#{notesBackingBean.identifyTab}"/>
				<!-- ************************************************* -->
			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom.jsp"%></td>
		</tr>
	</table>
	<form name="printForm"><input id="currentPrintPage" type="hidden"
		name="currentPrintPage" value="notesGeneralInformationEdit"></form>
	</BODY>
</f:view>

<script language="JavaScript">

	
	document.getElementById('notesCreateForm:hiddenValueChanged').value = 0;
	document.getElementById('notesCreateForm:identifyTab').value = "NOTES";
	var validationMessage = "Notes text has extended special characters, which are copied directly from Microsoft Word document.\nExtended special characters are not supported by WPD application. Please correct and try again.";

	InitializeNoteScreen('viewNotesDiv','notesCreateForm:viewDivDataHidden','editNotesDiv','notesCreateForm:editDivDataHidden','viewNoteBoxNormal','viewNoteBoxRed','notesCreateForm:formattedNotesHidden');
	copyHiddenToDiv('notesCreateForm:systemDomainTxtHidden','systemDomainDiv');	
	function InitializeNoteScreen(viewDivId,viewDivHiddenId,editDivId,editDivHiddenId,normalNoteDivId,truncatedNoteDivId,formattedNoteHidden){
		copyFormattedNotesToViewDiv(normalNoteDivId,truncatedNoteDivId,formattedNoteHidden);
		document.getElementById(viewDivHiddenId).value = escape(document.getElementById(viewDivId).innerHTML);
		document.getElementById(editDivHiddenId).value = escape(document.getElementById(editDivId).innerHTML);
		showHideDivs(viewDivId,editDivId,viewDivHiddenId);
	}

    if(document.getElementById('notesCreateForm:hiddenEditNote').value == "true")
	{	
		showHideDivs('editNotesDiv','viewNotesDiv','notesCreateForm:editDivDataHidden');
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
function checkModified(){
var divValue = document.getElementById('editNotesDiv').innerText;
var hiddenValue = document.getElementById('notesCreateForm:formattedNotesHidden').value;
hiddenValue = hiddenValue.replace(/[\n\r]/g, '');
divValue = divValue.replace(/[\n\r]/g, '');
	if(document.getElementById('editNotesDiv').style.visibility == 'hidden'){
		document.getElementById('notesCreateForm:hiddenValueChanged').value = 0;
	}else{
		if(trim(hiddenValue) == trim(divValue)){
			document.getElementById('notesCreateForm:hiddenValueChanged').value = 0;
		}else{
			document.getElementById('notesCreateForm:hiddenValueChanged').value = 1;
		}
	}
}

function valueModified(){
	document.getElementById('notesCreateForm:hiddenTypeValueChanged').value = 1;
}

function validateText(){

	if(document.getElementById('notesCreateForm:txtText').value.length >= 3000){
		return false;
	}else{
		return true;
	}
}
	
var editedText = false;
var checkin = false;
var editedName = false;
function runSpellCheck(){
	
	if(null == document.getElementById('notesCreateForm:txtText'))	{
		document.getElementById('notesCreateForm:noteDescHidden').value = document.getElementById('viewNotesDiv').innerText;
	}
	
	var version = document.getElementById('notesCreateForm:versionHidden').value;
	var oldName = document.getElementById('notesCreateForm:noteNameHidden').value;
	if(version < 1){
		var newName = document.getElementById('notesCreateForm:txtNoteNm').value;
		if(oldName!=newName)
			editedName = true;
	}
	if(editedText){	
		if(version<1)
			var rswlCntrls = ["rapidSpellWebLauncher1","rapidSpellWebLauncher2"];
		else
			var rswlCntrls = ["rapidSpellWebLauncher2"];
		var i=0;
		for(var i=0; i<rswlCntrls.length; i++){
			eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
		}
	}
	else if(!editedText && editedName){
		var rswlCntrls = ["rapidSpellWebLauncher1"];
		var i=0;
		for(var i=0; i<rswlCntrls.length; i++){
			eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
		}		
		if(checkin){
			document.getElementById('notesCreateForm:checkinNote').click();
		}
		else{
			document.getElementById('notesCreateForm:create').click();	
		}
	}
	else if(!editedText && !editedName){
		if(null!=document.getElementById('notesCreateForm:mandateTypeName')){
			document.getElementById('notesCreateForm:typeHidden').value = document.getElementById('notesCreateForm:mandateTypeName').value;
		}
		if(checkin){
			document.getElementById('notesCreateForm:checkinNote').click();
		}
		else{
			document.getElementById('notesCreateForm:create').click();	
		}
	}

	return false;
}
function spellFin(cancelled){	

		if(checkin)
			document.getElementById('notesCreateForm:checkinNote').click();
		else{
			if(null!=document.getElementById('notesCreateForm:mandateTypeName')){
				document.getElementById('notesCreateForm:typeHidden').value = document.getElementById('notesCreateForm:mandateTypeName').value;
			}	
			document.getElementById('notesCreateForm:create').click();	
		}

	checkin=false;
	editedText =false;
	editedName =false;
}
function validateNoteText(){
	if(validateChars('notesCreateForm:txtText',validationMessage))
			return true;
		else{
			document.getElementById('notesCreateForm:txtText').focus();
			return false;
		}
}
function checkIn(){

	if(null == document.getElementById('notesCreateForm:txtText'))	{

		document.getElementById('notesCreateForm:noteDescHidden').value = document.getElementById('viewNotesDiv').innerText;
	}else
		document.getElementById('notesCreateForm:noteDescHidden').value = '';

	if(null!=document.getElementById('notesCreateForm:mandateTypeName')){
		document.getElementById('notesCreateForm:typeHidden').value = document.getElementById('notesCreateForm:mandateTypeName').value;
	}
	checkin=true;
	return runSpellCheck();
}
function showHide(){
	showHideDivs('editNotesDiv','viewNotesDiv','notesCreateForm:editDivDataHidden');
	editedText= true;
}	
function systemDomain(){
var url = '../popups/systemDomainPopup.jsp'+getUrl()+'?'
+ 'temp=' + Math.random()
+'&&'+'noteId='+document.getElementById('notesCreateForm:noteIdHidden').value 
+'&&'+'systemDomain='+document.getElementById('notesCreateForm:systemDomainTxtHiddenOld').value 
+'&&'+'notesInActivelyUsedStatus='+document.getElementById('notesCreateForm:notesInActivelyUsedStatus').value ;	
	
ewpdModalWindow_ewpd(url,'systemDomainDiv','notesCreateForm:systemDomainTxtHidden',2,1)
}
</script>
<%@ include file="../navigation/unsavedDataFinder.html"%>
</HTML>
