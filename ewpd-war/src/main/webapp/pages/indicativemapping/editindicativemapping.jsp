<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
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

<TITLE>Edit Indicative mapping</TITLE>
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
	<BODY onkeypress="return submitOnEnterKey('editForm:saveButton');">
	<hx:scriptCollector id="scriptCollector1">
		<table width="100%" cellpadding="0" cellspacing="0">

			<TR>
				<TD><h:inputHidden
					value="#{indicativeMappingBackingBean.loadIndicativeMapping}" /><jsp:include
					page="../navigation/top_Print.jsp"></jsp:include></TD>
			</TR>
			<tr>
				<td><h:form styleClass="form" id="editForm">
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
							<DIV id="ind"><!--	Start of Table for actual Data	-->
							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText" width="100%">
								<TBODY>
									<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher1" textComponentName="editForm:msg"
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
										<td width="25%">&nbsp;<h:outputText id="RuleStar"
											value="Indicative*" /></td>
										<td width="25%">

										<DIV id="indDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="indhid"
											value="#{indicativeMappingBackingBean.indicativeCriteria}"></h:inputHidden>
										</TD>
										<TD width="50%">&nbsp;</TD>
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
                                                         'SPS Parameter Popup','spsParameterIdDiv','editForm:spsParameterHidden'); return false;"
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
                                                         'Benefit Name Popup','bnDiv','editForm:bnHidden'); return false;"
											tabindex="5">
										</h:commandButton></TD>
									</TR>

									<TR>
										<TD width="25%" valign="top">&nbsp;<h:outputText
											id="descriptionStar" value="Description" /></TD>

										<TD width="25%">
										<DIV id="mapdesc"><h:inputTextarea
											styleClass="formTxtAreaField_GeneralDesc" id="msg"
											value="#{indicativeMappingBackingBean.indMapDesc}"></h:inputTextarea></DIV>
										</TD>
										<td width="50%"><f:verbatim></f:verbatim></td>

									</tr>
									<TR>

										<TD width="25%"><h:outputText id="createdBy"
											value="Created By" /></TD>
										<TD width="25%">
										<DIV id="mapcu"><h:outputText
											value="#{indicativeMappingBackingBean.createdUser}" /></DIV>
										</TD>
										<td width="50%"><f:verbatim></f:verbatim></td>
									</TR>
									<TR>
										<TD width="25%"><h:outputText id="Time" value="Created Date" />
										</TD>
										<TD width="25%">
										<DIV id="mapct"><h:outputText
											value="#{indicativeMappingBackingBean.createdDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText></DIV>
										</TD>
										<td width="50%"><f:verbatim></f:verbatim></td>

									</TR>
									<TR>
										<TD width="25%"><h:outputText id="updatedBy"
											value="Last Updated By" /></TD>
										<TD width="25%">
										<DIV id="mapclu"><h:outputText
											value="#{indicativeMappingBackingBean.lastUpdatedUser}" /></DIV>
										</TD>
										<td width="50%"><f:verbatim></f:verbatim></td>
									</TR>
									<TR>

										<TD width="25%"><h:outputText id="updatedTime"
											value="Last Updated Date" /></TD>
										<TD width="25%">
										<DIV id="maplt"><h:outputText
											value="#{indicativeMappingBackingBean.lastUpdatedDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText></DIV>
										</TD>
										<td width="50%"><f:verbatim></f:verbatim></td>
									</TR>


								</TBODY>
							</TABLE>
							</DIV>
							<table>
								<TR>
									<TD width="23%">&nbsp;<h:commandButton value="Save"
										id="saveButton" styleClass="wpdButton"
										onclick="runSpellCheck(); return false;" tabindex="15"
										onmousedown="javascript:savePageAction(this.id);">
									</h:commandButton></TD>
								</TR>
							</table>
							<!--	End of Page data	--></fieldset>
							</TD>
						</TR>

					</table>
					<!-- Space for hidden fields -->
					
					<h:inputHidden id="createdUser1"
											value="#{indicativeMappingBackingBean.createdUser}"></h:inputHidden>

					<h:inputHidden id="lastUpdatedUser1"
											value="#{indicativeMappingBackingBean.lastUpdatedUser}"></h:inputHidden>
					<h:inputHidden id="createdDate1"
											value="#{indicativeMappingBackingBean.createdDate}"></h:inputHidden>

					<h:inputHidden id="lastUpdatedDate1"
											value="#{indicativeMappingBackingBean.lastUpdatedDate}"></h:inputHidden>
					<h:inputHidden id="msgHid"
						></h:inputHidden>
					<h:inputHidden id="preInd"
						value="#{indicativeMappingBackingBean.prevInd}"></h:inputHidden>
					<h:inputHidden id="preSps"
						value="#{indicativeMappingBackingBean.prevSps}"></h:inputHidden>
					<h:inputHidden id="preBen"
						value="#{indicativeMappingBackingBean.prevBen}"></h:inputHidden>
					<h:commandLink id="editInd" style="hidden"
						action="#{indicativeMappingBackingBean.editIndicativeMapping}">
					</h:commandLink>
					<!-- End of hidden fields  -->
				</h:form></td>
			</tr>
			<TR>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</table>

		<DIV id="sn"></DIV>
		<DIV id="sps"></DIV>
		<DIV id="bn"></DIV>
		<DIV id="desc"></DIV>
		<DIV id="cu"></DIV>
		<DIV id="ct"></DIV>
		<DIV id="lu"></DIV>
		<DIV id="lt"></DIV>

	</hx:scriptCollector>
	</BODY>
</f:view>


<script>

	copyHiddenToDiv_ewpd('editForm:indhid','indDiv',3,2);
	copyHiddenToDiv_ewpd('editForm:spsParameterHidden','spsParameterIdDiv',2,2);
	copyHiddenToDiv_ewpd('editForm:bnHidden','bnDiv',2,2);
    if(document.getElementById('editForm:indhid').value !='')
    showIndNr(encodeURI(document.getElementById('editForm:indhid').value));

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
var indr = val.split('~');
document.getElementById('snDiv').innerHTML=indr[2];
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
		document.getElementById('editForm:editInd').click();	
}

function  divValue(){
alert('insdiv')
document.getElementById('editForm:msgHid').value=document.getElementById('editForm:msg').value;

}




</script>

<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="printEditIndicativeMappingPage" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
</HTML>
