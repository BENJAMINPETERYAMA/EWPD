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

<TITLE>Edit Custom Message Text</TITLE>
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
		onkeypress="return submitOnEnterKey('customMessageEditForm:createButton');">
	<table width="100%" cellpadding="0" cellspacing="0">

		<TR>
			<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="customMessageEditForm">
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
										value="#{customMessageBackingBean.validationMessages}"></w:message>
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
						<!-- End of Tab table --> <script type="text/javascript">
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
								<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher1"
									textComponentName="customMessageEditForm:messageTxt"
									rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="../spellcheck/RapidSpellModalHelper.html"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" finishedListener="spellFin"
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
								</RapidSpellWeb:rapidSpellWebLauncher>
								<tr>
									<td width="35%">&nbsp;<h:outputText
										styleClass="#{customMessageBackingBean.requiredHeaderRule ? 'mandatoryError': 'mandatoryNormal'}"
										id="RuleStar" value="Header Rule ID*" /></td>
									<td width="25%">

									<DIV id="RuleDiv"></DIV>
									<h:inputHidden id="txtRule"
										value="#{customMessageBackingBean.headerRule}"></h:inputHidden>
									</TD>
									<TD width="40%">&nbsp; <h:commandButton alt="View"
										id="viewButton" image="../../images/view.gif"
										onclick="viewAction();return false;" /></TD>
								</tr>
								<TR>
									<TD width="35%">&nbsp;<h:outputText id="headerRuleDescText"
										value="Header Rule Description " /></TD>
									<TD width="25%">
									<div id="ruleDescDiv"></div>
									</TD>
									<TD width="40%">&nbsp;</TD>
								</TR>
								<TR>
									<TD width="35%">&nbsp;<h:outputText id="spsParametertext"
										value="SPS Parameter ID* "
										styleClass="#{customMessageBackingBean.requiredSPSParameter? 'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<h:inputHidden id="spsParameterHidden"
										value="#{customMessageBackingBean.SPSParameter}"></h:inputHidden>
									<TD width="25%">
									<div id="spsParameterIdDiv"></div>
									</TD>
									<TD width="40%">&nbsp;</TD>
								</TR>
								<TR>
									<TD width="35%">&nbsp;<h:outputText id="spsParamDescText"
										value="SPS Description " /></TD>
									<TD width="25%">
									<div id="spsParameterDescDiv"></div>
									</TD>
									<TD width="40%">&nbsp;</TD>
								</TR>
								<TR>
									<TD width="35%" valign="top">&nbsp;<h:outputText
										styleClass="#{customMessageBackingBean.requiredCustomMessage ? 'mandatoryError': 'mandatoryNormal'}"
										id="descriptionStar" value="Message Text*" /></TD>
									<TD width="25%"><h:inputTextarea
										styleClass="formTxtAreaField_GeneralDesc" id="messageTxt"
										value="#{customMessageBackingBean.customMessage}" tabindex="9"></h:inputTextarea></TD>
									<td width="40%">&nbsp;</td>
								</tr>
								<TR>
									<TD width="35%">&nbsp;<h:outputText id="messageIndicator"
										value="Message Required Indicator*">
									</h:outputText></TD>
									<TD width="25%"><h:selectOneRadio id="MsgInd"
										value="#{customMessageBackingBean.messageRequired}">
										<f:selectItem id="MsgIndYes" itemLabel="Yes" itemValue="Y" />
										<f:selectItem id="MsgIndNo" itemLabel="No" itemValue="N" />
									</h:selectOneRadio></TD>
									<td width="40%">&nbsp;</td>
								</TR>
								<TR>
									<TD width="35%">&nbsp;<h:outputText id="noteTypeText"
										value="Note Type Code " />
									</TD>
									<h:inputHidden id="noteTypeHidden"
										value="#{customMessageBackingBean.noteTypeCode}"></h:inputHidden>
									<TD width="25%">
									<div id="noteTypeDiv" class="selectDataDisplayDiv"></div>
									</TD>
									<TD width="40%">&nbsp;<h:commandButton alt="Note Type Hidden"
										id="note_type_Button" image="../../images/select.gif"
										onclick="ewpdModalWindow_ewpd('../blueexchange/noteTypeCodePopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'NOTE TYPE CODE'+'&title='+'Note Type Code'+'&temp=' + Math.random(), 'noteTypeDiv', 'customMessageEditForm:noteTypeHidden', 2, 1);return false;"
										tabindex="5">
									</h:commandButton></TD>
								</TR>
								<TR>
									<TD width="35%">&nbsp;<h:outputText value="Created By" /></TD>
									<TD width="25%" colspan="2"><h:inputHidden
										id="createdUserHidden"
										value="#{customMessageBackingBean.createdUser}"></h:inputHidden><h:outputText
										id="createdUser"
										value="#{customMessageBackingBean.createdUser}" /></TD>
									<td width="40%">&nbsp;</td>
								</TR>
								<TR>
									<TD width="35%">&nbsp;<h:outputText value="Created Date" /></TD>
									<TD width="25%" colspan="2"><h:inputHidden
										id="createdDateHidden"
										value="#{customMessageBackingBean.createdDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden><h:outputText id="createdDate"
										value="#{customMessageBackingBean.createdDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>

									</TD>
									<td width="40%">&nbsp;</td>
								</TR>
								<TR>
									<TD width="35%">&nbsp;<h:outputText value="Last Updated By" /></TD>
									<TD width="25%" colspan="2"><h:inputHidden
										id="lastChangedUserHidden"
										value="#{customMessageBackingBean.lastChangedUser}"></h:inputHidden><h:outputText
										id="lastChangedUser"
										value="#{customMessageBackingBean.lastChangedUser}" /></TD>
									<td width="40%">&nbsp;</td>
								</TR>
								<TR valign="top" height="20">
									<TD width="35%">&nbsp;<h:outputText value="Last Updated Date" /></TD>
									<TD width="25%" colspan="2"><h:inputHidden
										id="lastChangedDateHidden"
										value="#{customMessageBackingBean.lastChangedDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden><h:outputText id="lastChangedDate"
										value="#{customMessageBackingBean.lastChangedDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>

									<h:inputHidden id="time" value="#{requestScope.timezone}">
									</h:inputHidden></TD>
									<td width="40%">&nbsp;</td>
								</TR>

							</TBODY>
						</TABLE>
						<table>
							<TR>
								<TD width="23%">&nbsp;&nbsp;<h:commandButton value="Save"
									id="createButton" styleClass="wpdButton"
									onmousedown="javascript:savePageAction(this.id);"
									onclick="runSpellCheck(); return false;" tabindex="15">
								</h:commandButton></TD>
							</TR>
						</table>
						<!--	End of Page data	--></fieldset>
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
				<h:commandLink id="createCustomMsg" style="hidden"
					action="#{customMessageBackingBean.updateCustomMessage}">
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
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="customMessageTextViewPrint" /></form>
<script>
copyHiddenToDiv_ewpd('customMessageEditForm:noteTypeHidden','noteTypeDiv',2,1);  


function runSpellCheck(){
	var rswlCntrls = ["rapidSpellWebLauncher1"];
	var i=0;
	for(var i=0; i<rswlCntrls.length; i++){
		eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
	}
	return false;
}
function spellFin(cancelled){
		document.getElementById('customMessageEditForm:createCustomMsg').click();	
}	

function viewAction(){	
	var ruleIdStr = document.getElementById('customMessageEditForm:txtRule').value;
	if(ruleIdStr.length <=1){
			alert('Please select Header Rule ID');
		}
	else{
		var ruleArray = ruleIdStr.split('~');
		var ruleId = ruleArray[1];
		window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&temp=' + Math.random(),'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	}
}

if(document.getElementById('customMessageEditForm:txtRule').value != null &&document.getElementById('customMessageEditForm:txtRule').value != ''){
	var hy_str=document.getElementById('customMessageEditForm:txtRule').value;
	var val1=hy_str.split('~');
	document.getElementById('RuleDiv').innerText=val1[1];
    document.getElementById('ruleDescDiv').innerText=val1[0];
}

if(document.getElementById('customMessageEditForm:spsParameterHidden').value != null &&document.getElementById('customMessageEditForm:spsParameterHidden').value != ''){
	var hy_str=document.getElementById('customMessageEditForm:spsParameterHidden').value;
	var val1=hy_str.split('~');
	document.getElementById('spsParameterIdDiv').innerText=val1[1];
    document.getElementById('spsParameterDescDiv').innerText=val1[0];
}

function replaceTildaWithHyphen(hiddenId){
    if(document.getElementById(hiddenId).value != null && document.getElementById(hiddenId).value != ''){
	var hy_str=document.getElementById(hiddenId).value;
	var val1=hy_str.split('~');
	return val1[1]+'-'+val1[0]
    }
    return null;
}

</script>
<%@ include file="../navigation/unsavedDataFinder.html"%>
</HTML>
