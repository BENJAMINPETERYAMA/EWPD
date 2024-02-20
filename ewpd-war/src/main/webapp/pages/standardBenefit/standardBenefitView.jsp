<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
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

<TITLE>View Benefit</TITLE>
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
<BASE target="_self" />
</HEAD>
<f:view>
	<BODY>
	<h:form id="StandardBenefitsViewForm">
		<h:inputHidden id="viewStandardBenefitKey"
			value="#{standardBenefitBackingBean.viewStandardBenefitKey}" />
		<table width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><jsp:include page="../navigation/top_view.jsp"></jsp:include></TD>
			</TR>
			<tr>
				<td>
				<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD valign="top" class="leftPanel" width="273"><DIV class="treeDiv"><jsp:include
							page="../standardBenefit/standardBenefitTree.jsp"></jsp:include></DIV>



						</TD>
						<TD colspan="1" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<TR>
									<TD></TD>
								</TR>
							</TBODY>
						</TABLE>





						<!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="34%">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td align="left" width="0%"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td class="tabActive" align="center" width="99%"><h:outputText
											value=" General Information" /></td>
										<td width="2%" align="right"><img
											src="../../images/activeTabRight.gif" width="3" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="33%">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2%" align="left"><img
											src="../../images/tabNormalLeft.gif" width="3" height="21" /></td>
										<td align="center" width="99%" class="tabNormal"><h:commandLink
											action="#{benefitDefinitionBackingBean.loadBenefitDefinitionView}">
											<h:outputText
												value="#{standardBenefitBackingBean.benefitTypeTab}" />
										</h:commandLink></td>
										<td width="2%" align="right"><img
											src="../../images/tabNormalRight.gif" width="3" height="21" /></td>
									</tr>
								</table>
								</td>
								
								<td width="33%" id="manInfo">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2%" align="left"><img
											src="../../images/tabNormalLeft.gif" width="3" height="21" /></td>
										<td class="tabNormal" align="center" width="99%"><h:commandLink
											action="#{benefitMandateBackingBean.loadBenefitMandateView}">
											<h:outputText value="Mandate Information" />
										</h:commandLink></td>
										<td width="2%" align="right"><img
											src="../../images/tabNormalRight.gif" width="3" height="21" /></td>
									</tr>
								</table>
								</td>
								<TD width="33%" id="noteTab">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2%" align="left"><img
											src="../../images/tabNormalLeft.gif" width="3" height="21" /></td>
										<td class="tabNormal" align="center" width="99%"><h:commandLink
											action="#{standardBenefitNotesBackingBean.loadBenefitNotesView}">
											<h:outputText value="Notes" />
										</h:commandLink></td>
										<td width="2%" align="right"><img
											src="../../images/tabNormalRight.gif" width="3" height="21" /></td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
						<!-- End of Tab table -->

						<FIELDSET
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%;height:430">

						<!--	Start of Table for actual Data	-->
						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText">
							<TBODY>
								<TR>
									<TD colspan=3>
									<FIELDSET style=""><LEGEND><FONT color="black">Business Domain</FONT></LEGEND>
									<TABLE>
										<tr>
											<TD width="139"></TD>
										</tr>
										<TR>
											<TD width="29%"><h:outputText value="Line of Business" /></TD>
											<TD width="71%">
											<DIV id="lobDiv" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="txtLob"
												value="#{standardBenefitBackingBean.lob}"></h:inputHidden></TD>
											<TD width="0%"></TD>
										</TR>
										<TR>
											<TD width="29%"><h:outputText value="Business Entity" /></TD>
											<TD width="71%">
											<DIV id="BusinessEntityDiv" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="txtBusinessEntity"
												value="#{standardBenefitBackingBean.businessEntity}"></h:inputHidden>

											</TD>
											<TD width="0%"></TD>
										</TR>
										<TR>
											<TD width="29%"><h:outputText value="Business Group" /></TD>
											<TD width="71%">
											<DIV id="BusinessGroupDiv" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="txtBusinessGroup"
												value="#{standardBenefitBackingBean.businessGroup}"></h:inputHidden>

											</TD>
											<TD width="0%"></TD>
										</TR>
<!-- ----------------------------------------------------------------------------------- -->
										<TR>
											<TD width="29%"><h:outputText value="Market Business Unit" /></TD>
											<TD width="71%">
											<DIV id="MarketBusinessUnitDiv" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="txtMarketBusinessUnit"
												value="#{standardBenefitBackingBean.marketBusinessUnit}"></h:inputHidden>

											</TD>
											<TD width="0%"></TD>
										</TR>
<!-- ----------------------------------------------------------------------------------- -->
									</TABLE>
									</FIELDSET>
									</TD>
								</TR>
								<TR>
									<TD width="29%"><h:outputText value="Name" /></TD>
									<TD width="64%"><%--<DIV id="MinorHeadingDiv" class="selectDivReadOnly"></DIV>
									<h:inputHidden id="txtMinorHeading"
										value="#{standardBenefitBackingBean.minorHeading}"></h:inputHidden> --%>
									<h:outputText id="MinorHeading"
										value="#{standardBenefitBackingBean.minorHeading}"></h:outputText>
									<h:inputHidden id="hiddenMinHead"
										value="#{standardBenefitBackingBean.minorHeading}"></h:inputHidden>
									</TD>
									<TD width="8%"></TD>
								</TR>
								<TR>
									<TD width="29%"><h:outputText value="Benefit Meaning" /></TD>
									<TD width="64%"><%-- <DIV id="MinorHeadingDiv1" class="selectDivReadOnly"></DIV>
									<h:inputHidden id="txtMinorHeading1"
										value="#{standardBenefitBackingBean.minorHeading}"></h:inputHidden>--%>
									<h:outputText id="MinorHeading1"
										value="#{standardBenefitBackingBean.minorHeading}"></h:outputText></TD>
									<TD width="8%"></TD>
								</TR>
								<TR>
									<TD width="29%"><h:outputText value="Benefit Type" /></TD>
									<TD width="64%"><h:inputTextarea styleClass="selectDivReadOnly"
										id="CorpPlanCd_list1"
										value="#{standardBenefitBackingBean.benefitType}"
										readonly="true" style="border:0"></h:inputTextarea></TD>
									<TD width="8%"></TD>
								</TR>

								<TR id="sub1" style="display:none;">
									<TD width="29%"><h:outputText value="Mandate Type" /></TD>
									<TD width="64%"><h:inputTextarea styleClass="selectDivReadOnly"
										id="Mandate_type_list1"
										value="#{standardBenefitBackingBean.mandateType}"
										readonly="true" style="border:0"></h:inputTextarea></TD>
									<TD width="8%"></TD>
								</TR>
								<TR>
									<TD width="29%"><h:outputText value="Benefit Category" /></TD>
									<TD width="64%"><h:inputTextarea styleClass="selectDivReadOnly"
										id="Benefit_CategoryList"
										value="#{standardBenefitBackingBean.benefitCategoryHidden}"
										readonly="true" style="border:0"></h:inputTextarea></TD>
									<TD width="8%"></TD>
								</TR>
								<TR>
									<TD width="29%" valign="top"><h:outputText value="Description" /></TD>
									<TD width="64%">
										<h:inputTextarea id="txtDescription" readonly="true" styleClass="formTxtAreaField_ViewDesc" 
										value="#{standardBenefitBackingBean.description}"></h:inputTextarea>
									</TD>
									<TD width="8%"></TD>
								</TR>
								<TR>
									<TD width="29%"><h:outputText value="Benefit Rule ID" /></TD>
									<TD width="64%"><h:inputTextarea id="txtRule" readonly="true" styleClass="formTxtAreaField_ViewDesc" 
										style="height:17px;" value="#{standardBenefitBackingBean.rule}"></h:inputTextarea>
										<h:inputHidden id="txtRuleHidden" value="#{standardBenefitBackingBean.rule}"></h:inputHidden>
										<h:inputHidden id="txtStrRuleType" value="#{standardBenefitBackingBean.strRuleType}"></h:inputHidden>
									</TD>
									<TD width="8%"><h:commandButton alt="View" id="viewButton"
												image="../../images/view.gif"
												onclick="viewAction();return false;" /></TD>
								</TR>
								
								<TR>
									<TD colspan="3">
									<FIELDSET style=""><LEGEND><FONT color="black">Benefit Level Scope</FONT></LEGEND>
									<TABLE>
										<TR>
											<TD width="29%"><h:outputText value="Term" /></TD>
											<TD width="65%">
											<DIV id="TermDiv" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="txtTerm"
												value="#{standardBenefitBackingBean.term}"></h:inputHidden>


											</TD>
											<TD width="89"></TD>
										</TR>
										<TR>
											<TD width="29%"><h:outputText value="Qualifier" /></TD>
											<TD width="65%">
											<DIV id="QualifierDiv" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="txtQualifier"
												value="#{standardBenefitBackingBean.qualifier}"></h:inputHidden>

											</TD>
											<TD width="89"></TD>
										</TR>
										<TR>
											<TD width="29%"><h:outputText value="Provider Arrangement" />
											</TD>
											<TD width="65%">
											<DIV id="ProviderArrangementDiv" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="txtProviderArrangement"
												value="#{standardBenefitBackingBean.providerArrangement}"></h:inputHidden>

											</TD>
											<TD width="89"></TD>
										</TR>
										<TR>
											<TD width="29%"><h:outputText value="Data Type" /></TD>
											<TD width="65%">
											<DIV id="DataTypeDiv" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="txtDataType"
												value="#{standardBenefitBackingBean.dataType}"></h:inputHidden>

											</TD>
											<TD width="89"></TD>
										</TR>
									</TABLE>
									</FIELDSET>
									</TD>
								</TR>
								<tr>
									<td valign="top" width="29%"><span class="mandatoryNormal"
										id="creationDateId">&nbsp;</span><h:outputText
										value="Created By" /></td>
									<td width="64%"><h:outputText id="createdUserView"
										styleClass=""
										value="#{standardBenefitBackingBean.createdUser}" /> <h:inputHidden
										id="createdUserHidden"
										value="#{standardBenefitBackingBean.createdUser}">
									</h:inputHidden></td>
								</tr>
								<tr>
									<td valign="top" width="29%"><span class="mandatoryNormal"
										id="createdBy">&nbsp;</span><h:outputText value="Created Date" /></td>
									<td width="64%"><h:outputText id="createdDateView"
										styleClass=""
										value="#{standardBenefitBackingBean.createdTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="createdTimestampHidden"
										value="#{standardBenefitBackingBean.createdTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden> <h:inputHidden id="time"
										value="#{requestScope.timezone}">
									</h:inputHidden></td>
								</tr>
								<tr>
									<td valign="top" width="29%"><span class="mandatoryNormal"
										id="updationDate">&nbsp;</span><h:outputText
										value="Last Updated By" /></td>
									<td width="64%"><h:outputText id="updatedUserView"
										styleClass=""
										value="#{standardBenefitBackingBean.lastUpdatedUser}" /> <h:inputHidden
										id="lastUpdatedUserHidden"
										value="#{standardBenefitBackingBean.lastUpdatedUser}">
									</h:inputHidden></td>
								</tr>
								<tr>
									<td valign="top" width="29%"><span class="mandatoryNormal"
										id="updateBy">&nbsp;</span><h:outputText
										value="Last Updated Date" /></td>
									<td width="64%"><h:outputText id="updatedTimeView"
										styleClass=""
										value="#{standardBenefitBackingBean.lastUpdatedTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}" />
									<h:inputHidden id="lastUpdatedTimestampHidden"
										value="#{standardBenefitBackingBean.lastUpdatedTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden></td>
								</tr>

							</TBODY>
						</TABLE>
						<!--	End of Page data	--></fieldset>
						<FIELDSET
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<table border="0" cellspacing="0" cellpadding="3" width="100%">
							<tr>
								<td width="15"></td>
								<td align="left"></td>
								<td align="left" width="127">
								<table Width=100%>
									<tr>
										<td><h:outputText value="State" /></td>
										<td><h:outputText value=":" /></td>
										<td><h:outputText value="#{standardBenefitBackingBean.state}" /></td>

									</tr>
									<tr>
										<td><h:outputText value="Status" /></td>
										<td><h:outputText value=":" /></td>
										<td><h:outputText value="#{standardBenefitBackingBean.status}" /></td>

									</tr>
									<tr>
										<td><h:outputText value="Version" /></td>
										<td><h:outputText value=":" /></td>
										<td><h:outputText
											value="#{standardBenefitBackingBean.version}" /></td>

									</tr>
								</table>
								</td>
							</tr>
						</table>
						</FIELDSET>

						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields --> <h:inputHidden id="hidden1"
					value="value1"></h:inputHidden> <h:inputHidden id="benefitkey"
					value="#{standardBenefitBackingBean.selectedStandardBenefitKey}" />
				<h:inputHidden id="benfitNm"
					value="#{standardBenefitBackingBean.selectedStandardBenefitName}" />
				<h:inputHidden id="tabHidden"
					value="#{standardBenefitBackingBean.benefitTypeTab}"></h:inputHidden>

				<h:commandLink id="hiddenLnk1"
					style="display:none; visibility: hidden;">
					<f:verbatim />
				</h:commandLink> <!-- End of hidden fields  --></td>
			</tr>
			<TR>
				<TD><%@include file="../navigation/bottom_view.jsp"%></TD>
			</TR>
		</table>
	</h:form>
	</BODY>
</f:view>

<script>

	copyHiddenToDiv_ewpd('StandardBenefitsViewForm:txtLob','lobDiv',2,2); 
    copyHiddenToDiv_ewpd('StandardBenefitsViewForm:txtBusinessEntity','BusinessEntityDiv',2,2); 
    copyHiddenToDiv_ewpd('StandardBenefitsViewForm:txtBusinessGroup','BusinessGroupDiv',2,2); 
    copyHiddenToDiv_ewpd('StandardBenefitsViewForm:txtMarketBusinessUnit','MarketBusinessUnitDiv',2,2); 
    copyHiddenToDiv_ewpd('StandardBenefitsViewForm:txtTerm','TermDiv',2,1); 
  
    copyHiddenToDiv_ewpd('StandardBenefitsViewForm:txtQualifier','QualifierDiv',2,2); 
    copyHiddenToDiv_ewpd('StandardBenefitsViewForm:txtProviderArrangement','ProviderArrangementDiv',2,2); 
    copyHiddenToDiv_ewpd('StandardBenefitsViewForm:txtDataType','DataTypeDiv',2,2);
	//copyHiddenToDiv('StandardBenefitsViewForm:txtMinorHeading','MinorHeadingDiv');
    //copyHiddenToDiv('StandardBenefitsViewForm:txtMinorHeading1','MinorHeadingDiv1');
	formatTildaToComma1("StandardBenefitsViewForm:txtRule");

var i;
var obj;
obj = document.getElementById("StandardBenefitsViewForm:CorpPlanCd_list1");
i= obj.value;

	if(i=="MANDATE")
	{
	sub1.style.display='';
	}
	else 
	{

sub1.style.display='none';

	
	}
var i;
var obj;
obj = document.getElementById("StandardBenefitsViewForm:Mandate_type_list1");
i= obj.value;

		if(i == "State" || i == "ET")
		{
		
		}
		else
		{
	
		
		
		}

hideTab();
function hideTab(){
	var tab;
	tab = document.getElementById("StandardBenefitsViewForm:tabHidden").value ;
	if(tab=="Standard Definition"){
		manInfo.style.display='none';
		noteTab.style.display='';
	}
	else{
		manInfo.style.display='';
		noteTab.style.display='none';
	}
}
function viewAction(){
	
	var ruleIdStr = document.getElementById('StandardBenefitsViewForm:txtRuleHidden').value;
	var ruleType = document.getElementById('StandardBenefitsViewForm:txtStrRuleType').value;
	var ruleArray = ruleIdStr.split('~');
	var ruleId = ruleArray[1];
	//ICD10 Enhancement -- Individual Rule Extract
	var checkMode = 'view'; 
	var headerRuleBenefitSelected = 'true';
	var benefitName = document.getElementById('StandardBenefitsViewForm:hiddenMinHead').value;
	//ICD10 End

	if(ruleIdStr.length <=1){
			alert('Benefit Rule ID need to be selected.');
			return false;
		}
	if(ruleType=="BLZWPDAB")
	{
	
		window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&ruleType='+ruleType+'&temp='+ 
		Math.random()+'&checkMode='+checkMode+'&headerRuleBenefitSelected='+headerRuleBenefitSelected+'&benefitName='+escape(benefitName),
		'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	}
	else if(ruleType!="BLZWPDAB") {

		window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&ruleType='+ruleType+'&temp=' + 
		Math.random()+'&checkMode='+checkMode+'&headerRuleBenefitSelected='+headerRuleBenefitSelected+'&benefitName='+escape(benefitName),
		'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	}
}

</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="standardBenefit" /></form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
