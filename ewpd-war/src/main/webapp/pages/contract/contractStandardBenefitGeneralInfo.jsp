<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

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

<TITLE>Contract Benefit General Info</TITLE>
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
	<BODY>
	<table width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="contractBenefitGeneralInfo">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>
						
						<TD width="273" valign="top" class="leftPanel"><!-- Space for Tree  Data	-->
						<DIV class="treeDiv"><jsp:include page="contractTree.jsp"></jsp:include>
						</DIV>



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
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:outputText
											value="General Information" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td width="186" class="tabNormal"><h:commandLink
											action="#{contractCoverageBackingBean.getCoverage}"
											onmousedown="javascript:navigatePageAction(this.id);"
											id="thisId">
											<h:outputText value="#{contractCoverageBackingBean.benefitTypeTab}"  />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200" id="tab2">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td width="186" class="tabNormal"><h:commandLink
											action="#{contractBenefitNotesBackingBean.loadNotes}"
											onmousedown="javascript:navigatePageAction(this.id);"
											id="noteId">
											<h:outputText value="Notes" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200" id="tab3">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{contractBenefitMndateInfoBacingBean.retrieveMandates}">
											<h:outputText value="Mandate Information" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
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
							class="outputText" width="100%">
							<TBODY>
								<TR>
									<TD colspan=3>
									<FIELDSET style="width:50%"><LEGEND><FONT color="black">Business
									Domain</FONT></LEGEND>
									<TABLE width="117%">
										<TR>
											<TD width="41%"><h:outputText id="LobStar"
												value="Line of Business " /></TD>
											<TD width="58%">
											<DIV id="lobDiv" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="lob"
												value="#{contractBenefitGeneralInfoBackingBean.lob}"></h:inputHidden></TD>
											<TD width="1%"></TD>
										</TR>
										<TR>
											<TD width="41%"><h:outputText id="BusinessEntityStar"
												value="Business Entity " /></TD>
											<TD width="58%">
											<DIV id="BusinessEntityDiv" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="businessEntity"
												value="#{contractBenefitGeneralInfoBackingBean.businessEntity}"></h:inputHidden>
											</TD>
											<TD width="1%"></TD>
										</TR>
										<TR>
											<TD width="41%"><h:outputText id="BusinessGroupStar"
												value="Business Group " /></TD>
											<TD width="58%">
											<DIV id="BusinessGroupDiv" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="businessGroup"
												value="#{contractBenefitGeneralInfoBackingBean.businessGroup}"></h:inputHidden>
											</TD>
											<TD width="1%"></TD>
										</TR>
										<TR>
											<TD width="41%"><h:outputText id="marketBusinessUnitStar"
												value="Market Business Unit " /></TD>
											<TD width="58%">
											<DIV id="marketBusinessUnitDiv" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="marketBusinessUnit"
												value="#{contractBenefitGeneralInfoBackingBean.marketBusinessUnit}"></h:inputHidden>
											</TD>
											<TD width="1%"></TD>
										</TR>

									</TABLE>
									</FIELDSET>
									</TD>
								</TR>
								<TR>
									<TD width="23%">&nbsp;<h:outputText id="MinorHeadingStar"
										value="Name " /></TD>
									<TD width="27%"><h:outputText
										value="#{contractBenefitGeneralInfoBackingBean.minorHeading}">
									</h:outputText> <h:inputHidden id="name_Hidden"
										value="#{contractBenefitGeneralInfoBackingBean.minorHeading}"></h:inputHidden></TD>
									<TD width="49%"></TD>
								</TR>
									<tr>
									<td valign="top" width="24%"><span class="mandatoryNormal"
										id="ruleType">&nbsp;</span><h:outputText
										value="Benefit Meaning*"
										styleClass="#{contractBenefitGeneralInfoBackingBean.requiredBenefitMeaning ? 'mandatoryError': 'mandatoryNormal'}" /></td>
									<td width="27%"><h:inputText id="benefitMeaning"
										styleClass="formInputField"
										value="#{contractBenefitGeneralInfoBackingBean.benefitMeaning}"
										maxlength="34" tabindex="4" /> <h:inputHidden
										id="benMeaningHidden"
										value="#{contractBenefitGeneralInfoBackingBean.benefitMeaningFetched}"></h:inputHidden></td>
									<TD width="395"></TD>

								</tr>
								<tr>
									<td valign="top" width="24%"><span class="mandatoryNormal"
										id="creationDateId">&nbsp;</span><h:outputText
										value="Benefit Type" /></td>
									<td width="27%"><h:outputText
										value="#{contractBenefitGeneralInfoBackingBean.benefitType}" />
									<h:inputHidden id="txtBenefitId"
										value="#{contractBenefitGeneralInfoBackingBean.benefitType}"></h:inputHidden>
									
									<h:inputHidden id="txtBenefitIdHidden"
										value="#{contractBenefitGeneralInfoBackingBean.benefitId}"></h:inputHidden></td>
									<TD width="395"></TD>

								</tr>
								
								<tr>
									<td valign="top" width="24%"><span class="mandatoryNormal"
										id="benefitCategoryId">&nbsp;</span><h:outputText
										value="Benefit Category" /></td>
									<td width="27%"><h:outputText
										value="#{contractBenefitGeneralInfoBackingBean.benefitCategory}" />
									<h:inputHidden id="txtBenefitCategory"
										value="#{contractBenefitGeneralInfoBackingBean.benefitCategory}"></h:inputHidden>
									
									<h:inputHidden id="txtBenefitCategoryHidden"
										value="#{contractBenefitGeneralInfoBackingBean.benefitCategory}"></h:inputHidden></td>
									<TD width="395"></TD>

								</tr>

								<TR>
									<TD width="23%" valign="top">&nbsp;<h:outputText id="descriptionStar"
										value="Description " /></TD>
									<TD width="27%"><h:inputTextarea readonly="true"
										styleClass="formTxtAreaReadOnly" id="txtDescription1"
										value="#{contractBenefitGeneralInfoBackingBean.description}"
										tabindex="5"></h:inputTextarea><h:inputHidden
										id="txtDescription"
										value="#{contractBenefitGeneralInfoBackingBean.description}"></h:inputHidden></TD>
									<TD width="49%"></TD>
								</TR>
								<TR>
											<TD width="23%">&nbsp;<h:outputText id="tierDef"
												value="Tier Definition " /></TD>
											<TD width="27%">
											<DIV id="tierDefDiv" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="tierDefHidden"
												value="#{contractBenefitGeneralInfoBackingBean.tierDefinition}"></h:inputHidden></TD>
											<TD width="49%"></TD>
										</TR>
								<tr>
									<td valign="top" width="24%"><span class="mandatoryNormal"
										id="ruleType">&nbsp;</span><h:outputText value="Benefit Rule ID*"
										styleClass="#{contractBenefitGeneralInfoBackingBean.requiredBenefitRule ? 'mandatoryError': 'mandatoryNormal'}" /></td>
									<td width="27%">
										<DIV id="RuleTypeDiv" class="selectDataDisplayDiv"></DIV>

										<h:inputHidden id="txtRuleType" value="#{contractBenefitGeneralInfoBackingBean.ruleType}"></h:inputHidden>
										<h:inputHidden id="txhRuleType" value="#{contractBenefitGeneralInfoBackingBean.blzeRuleType}"></h:inputHidden>
										<!--  Added for New Changes -->
								
										<!--  End -->
										<h:inputHidden id="ruleTypeHidden"
											value="#{contractBenefitGeneralInfoBackingBean.ruleIDFetched}"></h:inputHidden>
									</td>
									<TD width="395"><h:commandButton alt="Benefit Rule ID" 
										id="contractIdButton" image="../../images/select.gif"
										onclick="loadpopup();return false;"
										tabindex="9">
									</h:commandButton>
									<h:commandButton alt="View" id="viewButton"
												image="../../images/view.gif"
												onclick="viewAction();return false;" />
									</TD>
								</tr>
								<TR>
									<TD colspan="3">
									<FIELDSET style="width:50%"><LEGEND><FONT color="black">Benefit Level Scope</FONT></LEGEND>
									<TABLE width="117%">
										<TR>
											<TD width="41%"><h:outputText id="termEditStar" value="Term " /></TD>
											<TD width="58%">
											<DIV id="TermDiv" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="txtTerm"
												value="#{contractBenefitGeneralInfoBackingBean.term}"></h:inputHidden>
											</TD>
											<TD width="1%"></TD>
										</TR>
										<TR>
											<TD width="41%"><h:outputText id="QualifierEditStar"
												value="Qualifier" /></TD>
											<TD width="58%">
											<DIV id="QualifierDiv" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="txtQualifier"
												value="#{contractBenefitGeneralInfoBackingBean.qualifier}"></h:inputHidden>
											</TD>
											<TD width="1%"></TD>
										</TR>
										<TR>
											<TD width="41%"><h:outputText
												id="ProviderArrangementEditStar"
												value="Provider Arrangement " /></TD>
											<TD width="58%">
											<DIV id="ProviderArrangementDiv" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="txtProviderArrangement"
												value="#{contractBenefitGeneralInfoBackingBean.providerArrangement}"></h:inputHidden>
											</TD>
											<TD width="1%"></TD>
										</TR>
										<TR>
											<TD width="41%"><h:outputText id="DataTypeEditStar"
												value="Data Type " /></TD>
											<TD width="58%">
											<DIV id="DataTypeDiv" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="txtDataType"
												value="#{contractBenefitGeneralInfoBackingBean.dataType}"></h:inputHidden>
											</TD>
											<TD width="1%"></TD>
										</TR>
									</TABLE>
									</FIELDSET>
									</TD>
								</TR>
							
					
							<tr>
									<td valign="top" width="24%"><span class="mandatoryNormal"
										id="versionId">&nbsp;</span><h:outputText
										value="Version" /></td>
									<td width="27%"><h:outputText
										value="#{contractBenefitGeneralInfoBackingBean.benefitVersion}" />
									<h:inputHidden id="versionHidden"
										value="#{contractBenefitGeneralInfoBackingBean.benefitVersion}"></h:inputHidden></td>
									<TD width="395"></TD>

								</tr>
								
								<tr>
									<td valign="top" width="24%"><span class="mandatoryNormal"
										id="creationDateId">&nbsp;</span><h:outputText
										value="Created By" /></td>
									<td width="27%"><h:outputText
										value="#{contractBenefitGeneralInfoBackingBean.createdUser}" />
									<h:inputHidden id="txtCreatedUser"
										value="#{contractBenefitGeneralInfoBackingBean.createdUser}"></h:inputHidden></td>
									<TD width="395"></TD>

								</tr>
								<tr>
									<td valign="top" width="24%"><span class="mandatoryNormal"
										id="createdBy">&nbsp;</span><h:outputText value="Created Date" /></td>
									<td width="27%"><h:outputText
										value="#{contractBenefitGeneralInfoBackingBean.createdTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="txtCreatedDate"
										value="#{contractBenefitGeneralInfoBackingBean.createdTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden> <h:inputHidden id="time"
										value="#{requestScope.timezone}">
									</h:inputHidden></td>
									<TD width="395"></TD>
								</tr>
								<tr>
									<td valign="top" width="24%"><span class="mandatoryNormal"
										id="updationDate">&nbsp;</span><h:outputText
										value="Last Updated By" /></td>
									<td width="27%"><h:outputText
										value="#{contractBenefitGeneralInfoBackingBean.lastUpdatedUser}" />
									<h:inputHidden id="txtUpdatedUser"
										value="#{contractBenefitGeneralInfoBackingBean.lastUpdatedUser}"></h:inputHidden></td>
									<TD width="395"></TD>
								</tr>
								<tr>
									<td valign="top" width="24%"><span class="mandatoryNormal"
										id="updateBy">&nbsp;</span><h:outputText
										value="Last Updated Date" /></td>
									<td width="27%"><h:outputText
										value="#{contractBenefitGeneralInfoBackingBean.lastUpdatedTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="txtUpdatedDate"
										value="#{contractBenefitGeneralInfoBackingBean.lastUpdatedTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden></td>
									<TD width="395"></TD>
								</tr>
								<tr>
									<td valign="top" width="24%">&nbsp;<h:commandButton
										value="Save" styleClass="wpdButton" tabindex="12" 
										onmousedown="javascript:savePageAction(this.id);"
										action="#{contractBenefitGeneralInfoBackingBean.saveRuleInfo}">
									</h:commandButton></td>
									<td width="27%"></td>
									<TD width="395"></TD>
								</tr>
							</TBODY>
						</TABLE>
						</fieldset>
						<!--	End of Page data	--> <BR>
						</TD>
					</TR>

				</table>

				<!-- Space for hidden fields -->
				<h:inputHidden id="defaultRuleID"
					value="#{contractBenefitGeneralInfoBackingBean.defaultRuleID}" />
				<h:inputHidden id="defaultRuleDesc"
					value="#{contractBenefitGeneralInfoBackingBean.defaultRuleDesc}" />
				<h:inputHidden id="hiddenProductType"
					value="#{contractBenefitGeneralInfoBackingBean.pageLoadBasedOnContractType}"></h:inputHidden>
				<h:inputHidden id="duplicateData" value="#{contractBenefitGeneralInfoBackingBean.duplicateData}"></h:inputHidden>
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
	name="currentPrintPage" value="contractStandardBenefitGeneralPrint" />
</form>
<script>
	IGNORED_FIELD1='contractBenefitGeneralInfo:duplicateData';
	copyHiddenToDiv_ewpd('contractBenefitGeneralInfo:lob','lobDiv',2,2); 
    copyHiddenToDiv_ewpd('contractBenefitGeneralInfo:businessEntity','BusinessEntityDiv',2,2); 
    copyHiddenToDiv_ewpd('contractBenefitGeneralInfo:businessGroup','BusinessGroupDiv',2,2); 
    copyHiddenToDiv_ewpd('contractBenefitGeneralInfo:marketBusinessUnit','marketBusinessUnitDiv',2,2); 
    copyHiddenToDiv_ewpd('contractBenefitGeneralInfo:txtTerm','TermDiv',2,2); 
    copyHiddenToDiv_ewpd('contractBenefitGeneralInfo:txtQualifier','QualifierDiv',2,2); 
    copyHiddenToDiv_ewpd('contractBenefitGeneralInfo:txtProviderArrangement','ProviderArrangementDiv',2,2); 
    copyHiddenToDiv_ewpd('contractBenefitGeneralInfo:txtDataType','DataTypeDiv',2,1);
    copyHiddenToDiv_ewpd('contractBenefitGeneralInfo:tierDefHidden','tierDefDiv',1,1);
	//copyHiddenToDiv_ewpd('contractBenefitGeneralInfo:txtRuleType','RuleTypeDiv',2,1);
	copyHiddenToDiv_ewpd1('contractBenefitGeneralInfo:txtRuleType','RuleTypeDiv',2,1); 
	//copyHiddenToDiv('contractBenefitGeneralInfo:benMeaningHidden','BenMeanDiv'); 
	adjustHieght('RuleTypeDiv');
	//adjustHieght('BenMeanDiv');
	function adjustHieght(divId){
		document.getElementById(divId).style.height="17px";
	}
	i = document.getElementById("contractBenefitGeneralInfo:hiddenProductType").value;
	if(i=='MANDATE')
	{
	tab2.style.display='none';
	tab3.style.display='';
	}else{
	tab2.style.display='';
	tab3.style.display='none';
	}
function loadpopup()
{
var emptyString='';
var ruleId=0;
var titleName = 'Benefit Rule Popup';
	ewpdModalWindowWithRuleType('../popups/benefitRuleTypePopup.jsp'+getUrl()+'?queryName=locateRuleId&ruleId='+ruleId+'&titleName='+titleName+'&temp='+Math.random(),'RuleTypeDiv','contractBenefitGeneralInfo:txtRuleType','contractBenefitGeneralInfo:txhRuleType',2,1); return false;

}
function viewAction(){

		var ruleIdStr = document.getElementById('contractBenefitGeneralInfo:txtRuleType').value;
		var ruleType = document.getElementById('contractBenefitGeneralInfo:txhRuleType').value;

		if(ruleIdStr.length <=1){
		alert('Benefit Rule ID need to be selected.');
		return false;
		}
		var ruleArray = ruleIdStr.split('~');
		var ruleId = ruleArray[1];

if(ruleType=="BLZWPDAB")
	{
	window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&ruleType='+ruleType+'&temp='+ Math.random(),'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	}
	else if(ruleType!="BLZWPDAB") {

		window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&ruleType='+ruleType+'&temp=' + Math.random(),'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	}


	
}

</script>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
    if(document.getElementById('contractBenefitGeneralInfo:duplicateData').value == '')
		document.getElementById('contractBenefitGeneralInfo:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
    else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('contractBenefitGeneralInfo:duplicateData').value;
</script>
</HTML>

