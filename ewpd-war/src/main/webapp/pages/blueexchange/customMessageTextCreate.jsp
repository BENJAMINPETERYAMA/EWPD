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

<TITLE>Create Custom Message Text</TITLE>
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
		onkeypress="return submitOnEnterKey('customMessageCreateForm:createButton');">
	<table width="100%" cellpadding="0" cellspacing="0">

		<TR>
			<TD><jsp:include page="../navigation/top.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="customMessageCreateForm">
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
									textComponentName="customMessageCreateForm:messageTxt"
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
									<td width="25%">&nbsp;<h:outputText
										styleClass="#{customMessageBackingBean.requiredHeaderRule ? 'mandatoryError': 'mandatoryNormal'}"
										id="RuleStar" value="Header Rule ID*" /></td>
									<td width="25%">

									<DIV id="RuleDiv" class="selectDataDisplayDiv"></DIV>
									<h:inputHidden id="txtRule"
										value="#{customMessageBackingBean.headerRule}"></h:inputHidden>
									</TD>
									<TD width="50%">&nbsp;<h:commandButton alt="Select"
										id="RuleButton" image="../../images/select.gif"
										style="cursor: hand" tabindex="10"
										onclick="ewpdModalWindow_ewpd1('../blueexchange/HeaderRulePopup.jsp'+getUrl(),'RuleDiv','customMessageCreateForm:txtRule',2,1); splitIDAndDescriptionForHeadderRule(); return false;"></h:commandButton>&nbsp;
									<h:commandButton alt="View" id="viewButton"
										image="../../images/view.gif"
										onclick="viewAction();return false;" /></TD>
								</tr>
								<TR>
									<TD width="25%">&nbsp;<h:outputText id="headerRuleDescText"
										value="Header Rule Description " /></TD>
									<TD width="25%">
									<div id="ruleDescDiv" style="overflow-x:hidden" class="selectDataDisplayDiv"></div>
									</TD>
									<TD width="50%">&nbsp;</TD>
								</TR>
								<TR>
									<TD width="25%">&nbsp;<h:outputText id="spsParametertext"
										value="SPS Parameter ID* "
										styleClass="#{customMessageBackingBean.requiredSPSParameter? 'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<h:inputHidden id="spsParameterHidden"
										value="#{customMessageBackingBean.SPSParameter}"></h:inputHidden>
									<TD width="25%">
									<div id="spsParameterIdDiv" class="selectDataDisplayDiv"></div>
									</TD>
									<TD width="50%">&nbsp;<h:commandButton alt="SPS Parameter"
										id="SPS_Parameter_Button" image="../../images/select.gif"
										onclick="ewpdModalWindow_ewpd1('../blueexchange/spsParameterFilterSearchPopup.jsp'+getUrl()+'?lookUpAction='+'9'+'&parentCatalog='+'reference'+'&title='+'SPS Parameter'+'&temp=' + Math.random(), 'spsParameterIdDiv', 'customMessageCreateForm:spsParameterHidden', 2, 1);splitIDAndDescriptionForSPSParameter();return false;"
										tabindex="5">
									</h:commandButton></TD>
								</TR>
								<TR>
									<TD width="25%">&nbsp;<h:outputText id="spsParamDescText"
										value="SPS Description " /></TD>
									<TD width="25%">
									<div id="spsParameterDescDiv" class="selectDataDisplayDiv"></div>
									</TD>
									<TD width="50%">&nbsp;</TD>
								</TR>
								<TR>
									<TD width="25%" valign="top">&nbsp;<h:outputText
										styleClass="#{customMessageBackingBean.requiredCustomMessage ? 'mandatoryError': 'mandatoryNormal'}"
										id="descriptionStar" value="Message Text*" /></TD>

									<TD width="25%"><h:inputTextarea
										styleClass="formTxtAreaField_GeneralDesc" id="messageTxt"
										value="#{customMessageBackingBean.customMessage}" tabindex="9"></h:inputTextarea></TD>
									<td width="50%"><f:verbatim></f:verbatim></td>
								</tr>
								<TR>
									<TD width="25%"><h:outputText id="messageIndicator"
										value="Message Required Indicator*">
									</h:outputText></TD>
									<TD width="25%"><h:selectOneRadio id="MsgInd"
										value="#{customMessageBackingBean.messageRequired}">
										<f:selectItem id="MsgIndYes" itemLabel="Yes" itemValue="Y" />
										<f:selectItem id="MsgIndNo" itemLabel="No" itemValue="N" />
									</h:selectOneRadio></TD>
									<td width="50%"><f:verbatim></f:verbatim></td>
								</TR>
								<TR>
									<TD width="25%">&nbsp;<h:outputText id="noteTypeText"
										value="Note Type Code " />
									</TD>
									<h:inputHidden id="noteTypeHidden"
										value="#{customMessageBackingBean.noteTypeCode}"></h:inputHidden>
									<TD width="25%">
									<div id="noteTypeDiv" class="selectDataDisplayDiv"></div>
									</TD>
									<TD width="50%">&nbsp;<h:commandButton alt="Note Type Hidden"
										id="note_type_Button" image="../../images/select.gif"
										onclick="ewpdModalWindow_ewpd('../blueexchange/noteTypeCodePopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'NOTE TYPE CODE'+'&title='+'Note Type Code'+'&temp=' + Math.random(), 'noteTypeDiv', 'customMessageCreateForm:noteTypeHidden', 2, 1);return false;"
										tabindex="5">
									</h:commandButton></TD>
								</TR>
							</TBODY>
						</TABLE>
						<table>
							<TR>
								<TD width="23%">&nbsp;<h:commandButton value="Create"
									id="createButton" styleClass="wpdButton"
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
					action="#{customMessageBackingBean.createCustomMessage}">
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

copyHiddenToDiv_ewpd('customMessageCreateForm:noteTypeHidden','noteTypeDiv',2,1);  

function runSpellCheck(){
	var rswlCntrls = ["rapidSpellWebLauncher1"];
	var i=0;
	for(var i=0; i<rswlCntrls.length; i++){
		eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
	}
	return false;
}
function spellFin(cancelled){
		document.getElementById('customMessageCreateForm:createCustomMsg').click();	
}

function viewAction(){	
	var ruleIdStr = document.getElementById('customMessageCreateForm:txtRule').value;
	if(ruleIdStr.length <=1){
			alert('Please select Header Rule ID');
		}
	else{
		var ruleArray = ruleIdStr.split('~');
		var ruleId = ruleArray[1];
		window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&temp=' + Math.random(),'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	}
}

if(document.getElementById('customMessageCreateForm:txtRule').value != null &&document.getElementById('customMessageCreateForm:txtRule').value != ''){
	var hy_str=document.getElementById('customMessageCreateForm:txtRule').value;
	var val1=hy_str.split('~');
	document.getElementById('RuleDiv').innerText=val1[1];
    document.getElementById('ruleDescDiv').innerText=val1[0];
}

if(document.getElementById('customMessageCreateForm:spsParameterHidden').value != null &&document.getElementById('customMessageCreateForm:spsParameterHidden').value != ''){
	var hy_str=document.getElementById('customMessageCreateForm:spsParameterHidden').value;
	var val1=hy_str.split('~');
	document.getElementById('spsParameterIdDiv').innerText=val1[1];
    document.getElementById('spsParameterDescDiv').innerText=val1[0];
}

function splitIDAndDescriptionForHeadderRule(){

	var hy_str=document.getElementById('customMessageCreateForm:txtRule').value;
	var val1=hy_str.split('~');
    if(val1.length==2){
	   document.getElementById('RuleDiv').innerText=val1[1];
	   document.getElementById('ruleDescDiv').innerText=val1[0];
    }else{
		document.getElementById('RuleDiv').innerText="";
   		document.getElementById('ruleDescDiv').innerText="";
	}
}

function splitIDAndDescriptionForSPSParameter(){
	var hy_str=document.getElementById('customMessageCreateForm:spsParameterHidden').value;
	var val1=hy_str.split('~');
    if(val1.length==2){
	   document.getElementById('spsParameterIdDiv').innerText=val1[1];
	   document.getElementById('spsParameterDescDiv').innerText=val1[0];
    }else{
		document.getElementById('spsParameterIdDiv').innerText="";
   		document.getElementById('spsParameterDescDiv').innerText="";
	}
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
</HTML>
