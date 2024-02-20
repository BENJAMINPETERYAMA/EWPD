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
<script language="JavaScript" src="../../js/wpd.js"></script>
</HEAD>
<f:view>
	<BODY>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td><jsp:include page="../navigation/top_Print.jsp"></jsp:include></td>
		</tr>

		<tr>
			<td><h:form styleClass="form" id="itemEditForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD valign="top" class="leftPanel" width="270">


						<DIV class="treeDiv"><!-- Space for Tree  Data	--></DIV>

						</TD>

						<TD colspan="2" valign="top" class="ContentArea" width="90%">
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
								<TR>
									<TD height="29" width="179">&nbsp;<h:outputText
										value="Catalog Name*" /></TD>
									<TD height="29" width="516"><h:outputText id="txtId"
										value="#{itemBackingBean.catalogName}" /> <h:inputHidden
										id="catalogNme" value="#{itemBackingBean.catalogName}"></h:inputHidden>
									<h:inputHidden id="catalogString"
										value="#{itemBackingBean.catalogId}"></h:inputHidden></TD>
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
									<TD width="179">&nbsp;<h:outputText value="Primary Code*" /></TD>
									<TD width="516"><h:outputText id="txtPrimary"
										value="#{itemBackingBean.primaryCode}" /> <h:inputHidden
										id="prmryCd" value="#{itemBackingBean.primaryCode}"></h:inputHidden>
									</TD>
								</TR>
								<TR>
									<TD height="25" width="179">&nbsp;<h:outputText
										id="secondaryCode" value="Secondary Code" /></TD>
									<TD height="25" width="516"><h:inputText id="txtSecondary"
										maxlength="30" styleClass="formInputField" tabindex="7"
										value="#{itemBackingBean.secondaryCode}" /></TD>
								</TR>
								<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher1"
									textComponentName="itemEditForm:txtSecondary"
									rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Item Secondary Code"
									modalHelperPage="../spellcheck/RapidSpellModalHelper.html"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" windowWidth="570" windowHeight="300"
									modal="False" showButton="False" windowX="-1"
									warnDuplicates="False" textComponentInterface="Custom">
								</RapidSpellWeb:rapidSpellWebLauncher>

								<TR>
									<TD height="25" width="179" valign="top">&nbsp;<h:outputText
										value="Description*"
										styleClass="#{itemBackingBean.requiredDescription ? 'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<TD width="516"><h:inputTextarea
										styleClass="formTxtAreaField_GeneralDesc" tabindex="12"
										id="itemDescription_txt"
										value="#{itemBackingBean.description}"></h:inputTextarea></TD>
								</TR>
								<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher2"
									textComponentName="itemEditForm:itemDescription_txt"
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
									<TD height="30" width="179" valign="top">&nbsp;<h:outputText
										value="Frequency Required*"
										styleClass="#{itemBackingBean.requiredFrequencyFlag ? 'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<TD width="516"><h:selectOneMenu id="frequencyFlagId"
										styleClass="formInputField" tabindex="5"
										value="#{itemBackingBean.frequencyFlag}">
										<f:selectItems id="frequencyFlagList"
											value="#{itemBackingBean.frequencyFlagListForCombo}" />
									</h:selectOneMenu></TD>

								</TR>
								<TR>
									<TD height="30%" width="179">&nbsp;<h:outputText id="createdBy"
										value="Created By" /></TD>
									<TD width="516"><h:outputText
										value="#{itemBackingBean.createdUser}" /> <h:inputHidden
										id="createdUser" value="#{itemBackingBean.createdUser}"></h:inputHidden>
									</TD>
								</TR>
								<TR>
									<TD height="30%" width="179">&nbsp;<h:outputText id="Time"
										value="Created Date" /></TD>
									<TD width="516"><h:outputText
										value="#{itemBackingBean.createdTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="createdTime"
										value="#{itemBackingBean.createdTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden> <h:inputHidden id="time"
										value="#{requestScope.timezone}">
									</h:inputHidden></TD>
								</TR>
								<TR>
									<TD height="30%" width="179">&nbsp;<h:outputText id="updatedBy"
										value="Last Updated By" /></TD>
									<TD width="74%"><h:outputText
										value="#{itemBackingBean.lastUpdatedUser}" /> <h:inputHidden
										id="updatedUser" value="#{itemBackingBean.lastUpdatedUser}"></h:inputHidden>
									</TD>
								</TR>
								<TR>
									<TD height="30%" width="179">&nbsp;<h:outputText
										id="updatedTime" value="Last Updated Date" /></TD>
									<TD width="74%"><h:outputText
										value="#{itemBackingBean.lastUpdatedTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="lastUpdated"
										value="#{itemBackingBean.lastUpdatedTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden></TD>
								</TR>
								<TR>
									<TD width="179"><h:commandButton value="Save" id="saveitem"
										onmousedown="javascript:savePageAction(this.id);"
										styleClass="wpdButton" onclick="return validateSecondaryCode() && validateDesc()  &&  runSpellCheck();"
										tabindex="17">
									</h:commandButton> <h:commandLink id="save" style="hidden"
										action="#{itemBackingBean.updateItem}">
									</h:commandLink></TD>
									<TD width="516">&nbsp;</TD>
								</TR>

							</TBODY>
						</TABLE>
						<!--	End of Page data	--></fieldset>
						</TD>
					</TR>
				</table>


			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="itemGenInfo" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	//copyHiddenToDiv_ewpd('itemEditForm:catalogString','catalogDiv',2,2); 
function runSpellCheck(){
	var rswlCntrls = ["rapidSpellWebLauncher1","rapidSpellWebLauncher2"];
	var i=0;
	for(var i=0; i<rswlCntrls.length; i++){
		eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
	}
	return false;
}
function spellFin(cancelled){
	
		document.getElementById('itemEditForm:save').click();	
}
var catalogName = document.getElementById('itemEditForm:catalogNme').value.toUpperCase();
if(catalogName == "QUALIFIER"){
		frequencyFlagTR.style.display = '';
}else{
		frequencyFlagTR.style.display = 'none';
}
var validationMessageSc ="Secondary code allows only ASCII characters and should not have linefeeds or non-ASCII characters. Please correct and try again.";
	function validateSecondaryCode(){
	if(validateChars('itemEditForm:txtSecondary',validationMessageSc)){
			return true;
		}else{
			document.getElementById('itemEditForm:txtSecondary').focus();
			return false;
		}
} 

 var validationMessage = "Description allows only ASCII characters and should not have linefeeds or non-ASCII characters. Please correct and try again.";
	function validateDesc(){
	if(validateChars('itemEditForm:itemDescription_txt',validationMessage))
			return true;
		else{
			document.getElementById('itemEditForm:itemDescription_txt').focus();
			return false;
		}
} 

</script>
</HTML>
