<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
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

<TITLE>View Product Structure Benefit Component</TITLE>
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
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript">
	 var cal1 = new CalendarPopup();
	 cal1.showYearNavigation();
</script>
<BASE target="_self" />
</HEAD>
<f:view>
	<BODY>
			<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td><jsp:include page="../navigation/top_view.jsp"></jsp:include></td>
			</tr>

			<tr>
				<td><h:form styleClass="benefitForm" id="benefitGeneralInfoForm">
				<h:inputHidden id="benefitCompntId" value="#{productStructureGeneralInfoBackingBean.benefitCompntId}"></h:inputHidden>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>
							<TD width="200" valign="top" class="leftPanel">
							<div class="treeDiv"><jsp:include
								page="../productStructure/productStructureTree.jsp" /></div>
							</TD>

							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<tr>
										<TD></TD>
									</tr>
								</TBODY>
							</TABLE>
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
											<td width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21" /></td>
											<td class="tabNormal"><h:commandLink
												action="#{productStructureBenefitDefenitionBackingBean.displayStandardBenefit}"
												id="linkToStandardDef">
												<h:outputText
													value="#{productStructureGeneralInfoBackingBean.benefitTypeTab}" />
											</h:commandLink></td>
											<td width="2" align="right"><img
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21" /></td>
										</tr>
									</table>
									</td>
								<td id = "notesTab" width="200">
									<table width="200" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21" /></td>
											<td class="tabNormal"><h:commandLink
												action="#{productStructureBenefitDefenitionBackingBean.loadStandardBenefitNotes}"
												id="linkToNotes">
												<h:outputText
													value="Notes" />
											</h:commandLink></td>
											<td width="2" align="right"><img
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21" /></td>
										</tr>
									</table>
									</td>
									<%--<td width="200">
									<table width="200" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21" /></td>
											<td class="tabNormal"><h:outputText
												value="Adj Benefit Mandates" /></td>
											<td width="2" align="right"><img
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21" /></td>
										</tr>
									</table>
									</td> --%>
									<td id="mandTab" width="200">
									<table width="200" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21" /></td>
											<td class="tabNormal"><h:commandLink
												action="#{productStructureBenefitMandatesBackingBean.retrieveMandates}"
												id="linkToNonAdjMan">
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
							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

							<TABLE border="0" cellspacing="0" cellpadding="2"
								class="outputText">
								<TBODY>
									<TR>
										<TD colspan="5">
										<FIELDSET
											style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:90%">
										<LEGEND> <FONT color="black">Business Domain</FONT></LEGEND>
										<TABLE border="0" cellspacing="0" cellpadding="3">
											<TR>
												<TD width="130"><h:outputText value="Line of Business" /></TD>
												<TD width="200"><h:inputHidden id="lineofBusinessHidden"
													value="#{productStructureBenefitDefenitionBackingBean.lob}"></h:inputHidden>
												<DIV id="divGroupSizeForLob" class="selectDivReadOnly"></DIV>
												<SCRIPT> //parseForDiv(document.getElementById('divGroupSizeForLob'), document.getElementById('benefitGeneralInfoForm:lineofBusinessHidden')); </SCRIPT>
												</TD>
											</TR>
											<TR>
												<TD width="130"><h:outputText value="Business Entity" /></TD>
												<TD width="200"><h:inputHidden id="businessEntityHidden"
													value="#{productStructureBenefitDefenitionBackingBean.entity}"></h:inputHidden>
												<DIV id="divGroupSizeForEntity" class="selectDivReadOnly"></DIV>
												<SCRIPT> //parseForDiv(document.getElementById('divGroupSizeForEntity'), document.getElementById('benefitGeneralInfoForm:businessEntityHidden')); </SCRIPT>
												</TD>
											</TR>
											<TR>
												<TD width="130"><h:outputText value="Business Group" /></TD>
												<TD width="200"><h:inputHidden id="businessGroupHidden"
													value="#{productStructureBenefitDefenitionBackingBean.group}"></h:inputHidden>
												<DIV id="divGroupSizeForGroup" class="selectDivReadOnly"></DIV>
												<SCRIPT> //parseForDiv(document.getElementById('divGroupSizeForGroup'), document.getElementById('benefitGeneralInfoForm:businessGroupHidden')); </SCRIPT>
												</TD>
											</TR>
											<!-- CARS START -->
											<TR>
												<TD width="130"><h:outputText value="Market Business Unit" /></TD>
												<TD width="200"><h:inputHidden id="marketBusinessUnitHidden"
													value="#{productStructureBenefitDefenitionBackingBean.marketBusinessUnit}"></h:inputHidden>
												<DIV id="divGroupSizeForMarketBusinessUnit" class="selectDivReadOnly"></DIV>
												</TD>
											</TR>
											<!-- CARS END -->
										</TABLE>



										</FIELDSET>
										</TD>
									</TR>
									<TR>
										<TD></TD>
									</TR>
									<TR>
										<TD width="3"></TD>
										<TD width="135"><h:outputText value="Name" /></TD>
										<TD width="262"><h:outputText id="structureName"
											value="#{productStructureBenefitDefenitionBackingBean.minorHeading}" />
										<h:inputHidden id="structureNameHidden"
											value="#{productStructureBenefitDefenitionBackingBean.minorHeading}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitGeneralInfoForm:structureNameHidden','benefitGeneralInfoForm:structureName',1); </SCRIPT>
										</TD>

									</TR>
									<TR>
										<TD width="3"></TD>
										<TD width="135"><h:outputText value="Benefit Meaning" /></TD>
										<TD width="262"><h:outputText id="benMean"
											value="#{productStructureBenefitDefenitionBackingBean.minorHeading}" />
										<h:inputHidden id="benMeanHidden"
											value="#{productStructureBenefitDefenitionBackingBean.minorHeading}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitGeneralInfoForm:benMeanHidden','benefitGeneralInfoForm:benMean',1); </SCRIPT>
										</TD>

									</TR>
									<TR>
										<TD width="3"></TD>
										<TD width="135"><h:outputText value="Benefit Type" /></TD>
										<TD width="262"><h:outputText id="benType"
											value="#{productStructureBenefitDefenitionBackingBean.benefitType}" />
										<h:inputHidden id="benTypeHidden"
											value="#{productStructureBenefitDefenitionBackingBean.benefitType}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitGeneralInfoForm:benTypeHidden','benefitGeneralInfoForm:benType',1); </SCRIPT>
										</TD>

									</TR>
									<TR>
										<TD width="3"></TD>
										<TD width="135"><h:outputText value="Benefit Category" /></TD>
										<TD width="262"><h:outputText id="benCategory"
											value="#{productStructureBenefitDefenitionBackingBean.benefitCategory}" />
										<h:inputHidden id="benCategoryHidden"
											value="#{productStructureBenefitDefenitionBackingBean.benefitCategory}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitGeneralInfoForm:benCategoryHidden','benefitGeneralInfoForm:benCategory',1); </SCRIPT>
										</TD>

									</TR>
									<TR id="sub1" ; style="display:none;">
										<TD width="3"></TD>
										<TD width="135"><h:outputText value="Mandate Type" /></TD>
										<TD width="262"><h:outputText id="mandType"
											value="#{productStructureBenefitDefenitionBackingBean.mandateType}" />
										<h:inputHidden id="mandTypeHidden"
											value="#{productStructureBenefitDefenitionBackingBean.mandateType}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitGeneralInfoForm:mandTypeHidden','benefitGeneralInfoForm:mandType',2); </SCRIPT>
										</TD>

									</TR>
                                    <TR>
										<TD width="3"></TD>
										<TD width="135" valign="top"><h:outputText value="Description" /></TD>
										<TD width="262"><h:outputText id="structureDesc"
											value="#{productStructureBenefitDefenitionBackingBean.description}" styleClass="formTxtAreaReadOnly">
										</h:outputText> <h:inputHidden id="structureDescHidden"
											value="#{productStructureBenefitDefenitionBackingBean.description}">
										</h:inputHidden> 
										</TD>
									</TR>
									<TR>
										<TD width="3"></TD>
										<TD width="135" valign="top"><h:outputText value="Benefit Rule ID" /></TD>
										<TD width="262">
											<TABLE>
												<TR>
													<TD width="196">		
														<h:outputText id="ruleId"
														value="#{productStructureBenefitDefenitionBackingBean.ruleId}"
														styleClass="" />
														<h:inputHidden id="ruleIdHidden"
														value="#{productStructureBenefitDefenitionBackingBean.ruleId}">
														</h:inputHidden>
														<h:inputHidden id="txtStrRuleType" value="#{productStructureBenefitDefenitionBackingBean.ruleType}"></h:inputHidden>
														<SCRIPT>copyHiddenToInputText1('benefitGeneralInfoForm:ruleIdHidden','benefitGeneralInfoForm:ruleId',1); </SCRIPT>				
													</TD>
													<TD align="left" width="34">												
														<h:commandButton alt="View" id="viewButton"
														image="../../images/view.gif"
														rendered = "#{productStructureBenefitDefenitionBackingBean.ruleId != null}"
														onclick="viewAction();return false;" />													
													</TD>
												</TR>
											</TABLE>
										</TD>
									</TR>
									<TR>
										<TD width="3"></TD>
										<TD width="135"><h:outputText
											styleClass="mandatoryNormal"
											id="TierStar" value="Tier Definition" /></TD>
										<TD width="262">	
										<DIV id="TierDiv" class="selectDivReadOnly"></DIV>	
										<h:inputHidden id="txtTier"
											value="#{productStructureBenefitDefenitionBackingBean.tierProductStructure}"></h:inputHidden>
										</TD>									
									</TR> 									

									<TR>
										<TD colspan="5">
										<FIELDSET
											style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:90%">
										<LEGEND> <FONT color="black">Benefit Level Scope</FONT></LEGEND>
										<TABLE border="0" cellspacing="0" cellpadding="3">
											<TR>
												<TD width="130"><h:outputText value="Term" /></TD>
												<TD width="194"><h:inputHidden id="txtTerm"
													value="#{productStructureBenefitDefenitionBackingBean.term}"></h:inputHidden>
												<DIV id="TermDiv" class="selectDivReadOnly"></DIV>
												<SCRIPT> parseForDiv(document.getElementById('TermDiv'), document.getElementById('benefitGeneralInfoForm:txtTerm')); </SCRIPT>
											</TR>
											<TR>
												<TD width="130"><h:outputText value="Qualifier" /></TD>
												<TD width="194"><h:inputHidden id="txtQualifier"
													value="#{productStructureBenefitDefenitionBackingBean.qualifier}"></h:inputHidden>
												<DIV id="QualifierDiv" class="selectDivReadOnly"></DIV>
												<SCRIPT> //parseForDiv(document.getElementById('QualifierDiv'), document.getElementById('benefitGeneralInfoForm:txtQualifier')); </SCRIPT>
											</TR>
											<TR>
												<TD width="130"><h:outputText value="Provider Arrangement" />
												</TD>
												<TD width="194"><h:inputHidden id="txtProviderArrangement"
													value="#{productStructureBenefitDefenitionBackingBean.providerArrangement}"></h:inputHidden>
												<DIV id="ProviderArrangementDiv" class="selectDivReadOnly"></DIV>
												<SCRIPT> //parseForDiv(document.getElementById('ProviderArrangementDiv'), document.getElementById('benefitGeneralInfoForm:txtProviderArrangement')); </SCRIPT>
											</TR>
											<TR>
												<TD width="130"><h:outputText value="Data Type" /></TD>
												<TD width="194"><h:inputHidden id="txtDataType"
													value="#{productStructureBenefitDefenitionBackingBean.dataType}"></h:inputHidden>
												<DIV id="DataTypeDiv" class="selectDivReadOnly"></DIV>
												<SCRIPT> parseForDiv(document.getElementById('DataTypeDiv'), document.getElementById('benefitGeneralInfoForm:txtDataType')); </SCRIPT>
											</TR>
										</TABLE>
										</FIELDSET>
										</TD>
									</TR>
									<tr>
										<TD></TD>
									</tr>
									<TR>
										<TD width="3"></TD>
										<TD width="135"><h:outputText value="Version" /></TD>
										<TD width="239"><h:outputText id="versionId"
											value="#{productStructureBenefitDefenitionBackingBean.benefitVersion}" />
										<h:inputHidden id="benefitVersionHidden"
											value="#{productStructureBenefitDefenitionBackingBean.benefitVersion}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitGeneralInfoForm:benefitVersionHidden','benefitGeneralInfoForm:versionId',1); </SCRIPT>
										</TD>
									</TR>
									<TR>
										<TD width="3"></TD>
										<TD width="135"><h:outputText value="Created By" /></TD>
										<TD width="262"><h:outputText id="createdBy"
											value="#{productStructureBenefitDefenitionBackingBean.createdBy}" />
										<h:inputHidden id="createdByHidden"
											value="#{productStructureBenefitDefenitionBackingBean.createdBy}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitGeneralInfoForm:createdByHidden','benefitGeneralInfoForm:createdBy',1); </SCRIPT>
										</TD>
									</TR>
									<TR>
										<TD width="3"></TD>
										<TD width="135"><h:outputText value="Created Date" /></TD>
										<TD width="262"><h:outputText id="creationDate"
											value="#{productStructureBenefitDefenitionBackingBean.creationDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
										<h:inputHidden id="creationDateHidden"
											value="#{productStructureBenefitDefenitionBackingBean.creationDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitGeneralInfoForm:creationDateHidden','benefitGeneralInfoForm:creationDate',1); </SCRIPT>
										<h:inputHidden id="time" value="#{requestScope.timezone}">
										</h:inputHidden></TD>
									</TR>
									<TR>
										<TD width="3"></TD>
										<TD width="135"><h:outputText value="Last Updated By" /></TD>
										<TD width="262"><h:outputText id="updatedBy"
											value="#{productStructureBenefitDefenitionBackingBean.updatedBy}" />
										<h:inputHidden id="updatedByHidden"
											value="#{productStructureBenefitDefenitionBackingBean.updatedBy}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitGeneralInfoForm:updatedByHidden','benefitGeneralInfoForm:updatedBy',1); </SCRIPT>
										</TD>

									</TR>
									<TR>
										<TD width="3"></TD>
										<TD width="135"><h:outputText value="Last Updated Date" /></TD>
										<TD width="262"><h:outputText id="updatedDate"
											value="#{productStructureBenefitDefenitionBackingBean.updatedDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
										<h:inputHidden id="updatedDateHidden"
											value="#{productStructureBenefitDefenitionBackingBean.updatedDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitGeneralInfoForm:updatedDateHidden','benefitGeneralInfoForm:updatedDate',1); </SCRIPT>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
							</FIELDSET>
							</TD>
						</TR>
					</TABLE>
					<h:inputHidden id="benTypHidden"
						value="#{productStructureGeneralInfoBackingBean.benefitTypeTab}" />
				</h:form></TD>
			</TR>
			<TR>
				<TD><%@ include file="../navigation/bottom_view.jsp"%></TD>
			</TR>
		</TABLE>
	
	</BODY>
</f:view>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="benefitGeneralInfo" />
</form> <script
	language="javascript">

  copyHiddenToDiv_ewpd('benefitGeneralInfoForm:lineofBusinessHidden','divGroupSizeForLob',2,2); 
  copyHiddenToDiv_ewpd('benefitGeneralInfoForm:businessEntityHidden','divGroupSizeForEntity',2,2); 
  copyHiddenToDiv_ewpd('benefitGeneralInfoForm:businessGroupHidden','divGroupSizeForGroup',2,2); 
  copyHiddenToDiv_ewpd('benefitGeneralInfoForm:marketBusinessUnitHidden','divGroupSizeForMarketBusinessUnit',2,2); 
  copyHiddenToDiv_ewpd('benefitGeneralInfoForm:txtQualifier','QualifierDiv',2,2);
  copyHiddenToDiv_ewpd('benefitGeneralInfoForm:txtProviderArrangement','ProviderArrangementDiv',2,2);
  copyHiddenToDiv_ewpd('benefitGeneralInfoForm:txtDataType', 'DataTypeDiv',2,2); 
	
  //for tier definitions
  copyHiddenToDiv_ewpd('benefitGeneralInfoForm:txtTier','TierDiv',2,1); 

getStructureType();
function getStructureType()
	{
	var obj;
	obj = document.getElementById("benefitGeneralInfoForm:benType");

		if(obj.value== "Mandate" || obj.value == "MANDATE")
		{
		sub1.style.display='';		
		}
		else 
		{
		sub1.style.display='none';
		}
	}

displayMandateTab();
	function displayMandateTab(){
	var benType = document.getElementById('benefitGeneralInfoForm:benTypHidden').value;
	if(benType=="Mandate Definition"){
		mandTab.style.display='';
		notesTab.style.display='none';
	}
	else{
		mandTab.style.display='none';
	}
}
document.getElementById('divGroupSizeForLob').style.height= "17px";
document.getElementById('divGroupSizeForEntity').style.height= "17px";
document.getElementById('divGroupSizeForGroup').style.height= "17px";
document.getElementById('divGroupSizeForMarketBusinessUnit').style.height= "17px";

//input will be Tier4~4~Tier2~2~Tier3~3
//output will be Tier4,Tier2,Tier3
function formatTilda(objName)
{
    var formattedString = "";
	var obj = document.getElementById(objName);

	var val = obj.innerHTML;
	if(val == null || val == '')
		return;
	
	var values = val.split('~');
	if(values != null)
	{
		for(i=0, n = values.length; i < n; i+=2)
		{
		formattedString += values[i] ;			
			if(i < n-2)
			formattedString += ", " ;
		}
		obj.innerHTML = formattedString;		
	}
	return ;
}
//CARS START
//DESCRIPTION : This method calls the rule view popup	
function viewAction(){
	var ruleIdStr = document.getElementById('benefitGeneralInfoForm:ruleIdHidden').value;
	var ruleType = document.getElementById('benefitGeneralInfoForm:txtStrRuleType').value;

	var ruleArray = ruleIdStr.split('-');
	var ruleId = ruleArray[0];
	//ICD10 Enhancement -- Individual Rule Extract
	var checkMode = 'view'; 
	var headerRuleBenefitSelected = 'true';
	var benefitComponentId = document.getElementById('benefitGeneralInfoForm:benefitCompntId').value;
	var benefitName = document.getElementById('benefitGeneralInfoForm:structureName').value;
	//ICD10 End
	
	if(ruleType=="BLZWPDAB")
	{
		window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&ruleType='+ruleType+'&temp='
		+ Math.random()+'&checkMode='+checkMode+'&headerRuleBenefitSelected='+headerRuleBenefitSelected+'&benefitComponentId='+benefitComponentId+'&benefitName='+escape(benefitName),
		'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	}
	else if(ruleType!="BLZWPDAB") {
		window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&ruleType='+ruleType+'&temp=' 
		+ Math.random()+'&checkMode='+checkMode+'&headerRuleBenefitSelected='+headerRuleBenefitSelected+'&benefitComponentId='+benefitComponentId+'&benefitName='+escape(benefitName),
		'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	}
}
//CARS END
</script>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
