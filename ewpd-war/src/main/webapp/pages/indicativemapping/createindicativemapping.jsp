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

<TITLE>Create Indicative mapping</TITLE>
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
			<td><h:form styleClass="form" id="createForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>
						<TD width="273" valign="top" class="leftPanel">
						<DIV class="treeDiv" style="height:500px"><!-- Space for Tree  Data	--></DIV>
						</TD>
						<TD colspan="2" valign="top" class="ContentArea">
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
									textComponentName="createForm:msg"
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
										styleClass="#{indicativeMappingBackingBean.indicativereq? 'mandatoryError': 'mandatoryNormal'}"
										id="RuleStar" value="Indicative*" /></td>
									<td width="25%">

									<DIV id="indDiv" class="selectDataDisplayDiv"></DIV>
									<h:inputHidden id="indhid"
										value="#{indicativeMappingBackingBean.indicativeCriteria}"></h:inputHidden>
									</TD>
									<TD width="50%">&nbsp;<h:commandButton alt="Select"
										id="RuleButton" image="../../images/select.gif"
										style="cursor: hand" tabindex="10"
										onclick="loadIndicativeSearchPopup('searchIndicative','Indicative',
                                                         'Indicative Popup','indDiv','createForm:indhid');
                                                         showIndNr(document.getElementById('createForm:indhid').value);
                                                          return false;"></h:commandButton>
									&nbsp;</TD>
								</tr>
								<TR>
									<TD width="25%">&nbsp;<h:outputText id="headerRuleDescText"
										value="Segment Number " /></TD>
									<TD width="25%">
									<div id="snDiv" style="overflow-x:hidden"
										class="selectDataDisplayDiv"></div>
									</TD>
									<TD width="50%">&nbsp;</TD>
								</TR>
								<TR>
									<TD width="25%">&nbsp;<h:outputText id="spsParametertext"
										value="SPS Parameter* "
										styleClass="#{indicativeMappingBackingBean.spsreq? 'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<h:inputHidden id="spsParameterHidden"
										value="#{indicativeMappingBackingBean.spsParamCriteria}"></h:inputHidden>
									<TD width="25%">
									<div id="spsParameterIdDiv" class="selectDataDisplayDiv"></div>
									</TD>
									<TD width="50%">&nbsp;<h:commandButton alt="SPS Parameter"
										id="SPS_Parameter_Button" image="../../images/select.gif"
										onclick="loadMultiSearchPopup('searchSPS','SPS Parameters',
                                                         'SPS Parameter Popup','spsParameterIdDiv','createForm:spsParameterHidden'); return false;"
										tabindex="5">
									</h:commandButton></TD>
								</TR>
								<TR>
									<TD width="25%">&nbsp;<h:outputText id="bntext"
										value="Benefit Name* "
										styleClass="#{indicativeMappingBackingBean.benefitreq? 'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<h:inputHidden id="bnHidden"
										value="#{indicativeMappingBackingBean.benefitNameCriteria}"></h:inputHidden>
									<TD width="25%">
									<div id="bnDiv" class="selectDataDisplayDiv"></div>
									</TD>
									<TD width="50%">&nbsp;<h:commandButton alt="Benefit Name"
										id="bn_Button" image="../../images/select.gif"
										onclick="loadSearchPopup('searchBenefitName','Benefit',
                                                         'Benefit Name Popup','bnDiv','createForm:bnHidden'); return false;"
										tabindex="5">
									</h:commandButton></TD>
								</TR>

								<TR>
									<TD width="25%" valign="top">&nbsp;<h:outputText
										id="descriptionStar" value="Description" /></TD>

									<TD width="25%"><h:inputTextarea
										styleClass="formTxtAreaField_GeneralDesc" id="msg"
										value="#{indicativeMappingBackingBean.indMapDesc}"
										tabindex="9"></h:inputTextarea></TD>
									<td width="50%"><f:verbatim></f:verbatim></td>
								</tr>
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
				<h:commandLink id="createIndMap" style="hidden"
					action="#{indicativeMappingBackingBean.createIndicativeMapping}">
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

	copyHiddenToDiv_ewpd('createForm:indhid','indDiv',3,2);
	copyHiddenToDiv_ewpd('createForm:spsParameterHidden','spsParameterIdDiv',2,2);
	copyHiddenToDiv_ewpd('createForm:bnHidden','bnDiv',2,2);
    if(document.getElementById('createForm:indhid').value !='')
    showIndNr(encodeURI(document.getElementById('createForm:indhid').value));

function loadIndicativeSearchPopup(queryName,headerName,titleName,displayDiv,outComp){
	ewpdModalWindow_ewpd( '../popups/searchIndicativeSPSpopup.jsp'+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,3,2);
}

function loadMultiSearchPopup(queryName,headerName,titleName,displayDiv,outComp){
	ewpdModalWindow_ewpd( '../popups/searchSPSSingleSelectPopup.jsp'+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,2,2);
}

function loadSearchPopup(queryName,headerName,titleName,displayDiv,outComp){
	ewpdModalWindow_ewpd( '../popups/benefitindicativepopup.jsp'+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,2,2);
}

function showIndNr(val){
	if((null!=val) && !('' == val)){
		var indr = val.split('~');
		document.getElementById('snDiv').innerHTML=indr[2];
	}else{
		document.getElementById('snDiv').innerHTML='';
	}
}




function runSpellCheck(){
	var rswlCntrls = ["rapidSpellWebLauncher1"];
	var i=0;
	for(var i=0; i<rswlCntrls.length; i++){
		eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
	}
	return false;
}
function spellFin(cancelled){
		document.getElementById('createForm:createIndMap').click();	
}




</script>
</HTML>
