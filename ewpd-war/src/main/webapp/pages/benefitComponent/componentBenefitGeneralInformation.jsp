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

<TITLE>Benefit General Information</TITLE>
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
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="benefitGeneralInformationForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD width="273" valign="top" class="leftPanel"><!-- Space for Tree  Data	-->
							<jsp:include page="../benefitComponent/benefitComponentTree.jsp"></jsp:include>



							</TD>

							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD></TD>
									</TR>
								</TBODY>
							</TABLE>

							<!-- Table containing Tabs -->
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<TR>
									<TD width="25%">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
											<TD width="186" class="tabActive">
												<h:outputText value=" General Information" />
											</TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<h:inputHidden id="hiddenTabValue"
										value="#{benefitComponentCreateBackingBean.componentTypeTab}"></h:inputHidden>
									<TD width="25%">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD class="tabNormal"><h:commandLink
												action="#{ComponentBenefitDefinitionsBackingBean.loadStandardDefinition}">
												<h:outputText
													value="#{benefitComponentCreateBackingBean.componentTypeTab}" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>


									<td width="25%" id="sbMandateInfoTab">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21" /></td>
											<td class="tabNormal"><h:commandLink
												action="#{benefitMandateBackingBean.loadBenefitMandateForView}">
												<h:outputText value="Mandate Information" />
											</h:commandLink></td>
											<td width="2" align="right"><img
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21" /></td>
										</tr>
									</table>
									</td>
									<td width="25%" id="sbOverrideNotesTab">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21" /></td>
											<td class="tabNormal"><h:commandLink
												action="#{BenefitComponentNotesBackingBean.loadStandardBenefitOverrideNotes}">
												<h:outputText value="Notes" />
											</h:commandLink></td>
											<td width="2" align="right"><img
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21" /></td>
										</tr>
									</table>
									</td>
									<td width="25%"></td>
								</TR>
							</TABLE>
							<!-- End of Tab table -->

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

							<!--	Start of Table for actual Data	--> <br>
							<TABLE border="0" cellspacing="1" cellpadding="3"
								class="outputText">
								<TBODY>
									<TR>
										<td colspan="2">
										<fieldset style=""><legend><font color="black">Business Domain</font></legend>
										<table border="0" cellspacing="0" cellpadding="3">
											<tr>
												<td width="154"><h:outputText value="Line Of Business" /></td>
												<td width="227">
												<DIV id="lobDiv" class="selectDataDisplayDiv"></DIV>
												<h:inputHidden
													value="#{benefitComponentCreateBackingBean.lineOfBusiness}"
													id="lineOfBusiness" /></td>
											</tr>
											<TR>
												<TD width="154"><h:outputText value="Business Entity" /></TD>
												<TD width="227">
												<DIV id="businessEntityDiv" class="selectDataDisplayDiv"></DIV>
												<h:inputHidden
													value="#{benefitComponentCreateBackingBean.businessEntity}"
													id="businessEntity" /></TD>
											</TR>
											<TR>
												<TD width="154"><h:outputText value="Business Group" /></TD>
												<TD width="227">
												<DIV id="businessGrpDiv" class="selectDataDisplayDiv"></DIV>
												<h:inputHidden
													value="#{benefitComponentCreateBackingBean.businessGroup}"
													id="businessGroup" /></TD>
											</TR>
<!-- ----------------------------------------------------------------------------------- -->
											<TR>
												<TD width="154"><h:outputText value="Market Business Unit" /></TD>
												<TD width="227">
												<DIV id="MarketBusinessUnitDiv" class="selectDataDisplayDiv"></DIV>
												<h:inputHidden
													value="#{benefitComponentCreateBackingBean.marketBusinessUnit}"
													id="marketBusinessUnit" /></TD>
											</TR>
<!-- ------------------------------------------------------------------------------------ -->
										</table>
										</fieldset>
										</td>
									</TR>
									<TR>
										<TD width="160"><h:outputText value="Name" /></TD>
										<TD width="232"><h:inputText styleClass="formInputField"
											id="minorHeading"
											value="#{benefitComponentCreateBackingBean.minorHeading}"
											readonly="true" /><h:inputHidden id="hiddenName"
											value="#{benefitComponentCreateBackingBean.minorHeading}"></h:inputHidden></TD>
									</TR>
									<TR>
										<TD width="160"><h:outputText value="Benefit Meaning" /></TD>
										<TD width="232"><h:inputText styleClass="formInputField"
											id="benMeaning"
											value="#{benefitComponentCreateBackingBean.minorHeading}"
											readonly="true" /><h:inputHidden id="hiddenBenMean"
											value="#{benefitComponentCreateBackingBean.minorHeading}"></h:inputHidden></TD>
									</TR>
									<TR>
										<TD width="25%"><h:outputText value="Benefit Type" /></TD>
										<TD width="68%"><h:inputText styleClass="formInputField"
											id="CorpPlanCd_list1"
											value="#{benefitComponentCreateBackingBean.sbBenType}"
											readonly="true"></h:inputText><h:inputHidden
											id="hideenBenType"
											value="#{benefitComponentCreateBackingBean.sbBenType}"></h:inputHidden></TD>
										<TD width="8%"></TD>
									</TR>
									<TR>
										<TD width="25%"><h:outputText value="Benefit Category" /></TD>
										<TD width="68%"><h:inputText styleClass="formInputField"
											id="benCategory"
											value="#{benefitComponentCreateBackingBean.benefitCategory}"
											readonly="true"></h:inputText><h:inputHidden
											id="hiddenBenCategory"
											value="#{benefitComponentCreateBackingBean.benefitCategory}"></h:inputHidden></TD>
										<TD width="8%"></TD>
									</TR>
									<TR>
										<TD width="160" valign="top"><h:outputText value="Description" /></TD>
										<TD width="232"><h:inputTextarea styleClass="formTxtAreaField"
											value="#{benefitComponentCreateBackingBean.stdBenefitDescription}"
											id="description" readonly="true" /><h:inputHidden
											id="hiddenDesc"
											value="#{benefitComponentCreateBackingBean.stdBenefitDescription}"></h:inputHidden></TD>
									</TR>
									<TR id="sub1" style="display:none;">
										<TD width="25%"><h:outputText value="Mandate Type" /></TD>
										<TD width="68%"><h:inputText styleClass="formInputField"
											id="Mandate_type_list1"
											value="#{benefitComponentCreateBackingBean.sbMandateType}"
											readonly="true"></h:inputText><h:inputHidden
											id="hiddenMndtType"
											value="#{benefitComponentCreateBackingBean.sbMandateType}"></h:inputHidden></TD>
										<TD width="8%"></TD>
									</TR>

									<TR id="sub2" style="display:none;">
										<TD width="25%"><h:outputText value="Jurisdiction" /></TD>
										<TD width="68%">
										<div id="StateDiv" class="selectDataDisplayDiv"></div>
										<h:inputHidden id="txtState"
											value="#{benefitComponentCreateBackingBean.sbSelState}"></h:inputHidden></TD>
										<TD width="8%"></TD>
									</TR>
									<TR>
										<TD width="25%"><h:outputText value="Benefit Rule ID" /></TD>
										<TD width="68%"><h:inputTextarea styleClass="formTxtAreaField"
											value="#{benefitComponentCreateBackingBean.sbRule}"
											id="txtRule" readonly="true" />
											&nbsp;&nbsp;&nbsp;&nbsp;
											<h:commandButton alt="View" id="viewButton"
												image="../../images/view.gif"
												rendered = "#{benefitComponentCreateBackingBean.sbRule != null}"
												onclick="viewAction();return false;" />
											<h:inputHidden
											id="hiddenRule"
											value="#{benefitComponentCreateBackingBean.sbRule}"></h:inputHidden>
											<h:inputHidden
											id="strRuleType"
											value="#{benefitComponentCreateBackingBean.strRuleType}"></h:inputHidden></TD>
										<TD width="8%">
											
										</TD>
									</TR>
									<TR>
										<TD width="25%"><h:outputText
											styleClass="mandatoryNormal"
											id="TierStar" value="Tier Definition" /></TD>
										<TD width="68%">
										<DIV id="TierDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="txtTier"
											value="#{benefitComponentCreateBackingBean.tierBenefitComp}"></h:inputHidden>
										</TD>									
									</TR>
									<TR>
										<td colspan="2">
										<fieldset style=""><legend><font color="black">Benefit Level Scope</font></legend>
										<table border="0" cellspacing="0" cellpadding="3">
											<tr>
												<td width="154"><h:outputText value="Term" /></td>
												<td width="227">
												<DIV id="termDiv" class="selectDataDisplayDiv"></DIV>
												<h:inputHidden
													value="#{benefitComponentCreateBackingBean.term}" id="term" />
												</td>
											</tr>
											<TR>
												<TD width="154"><h:outputText value="Qualifier" /></TD>
												<TD width="227">
												<DIV id="qualifierDiv" class="selectDataDisplayDiv"></DIV>
												<h:inputHidden
													value="#{benefitComponentCreateBackingBean.qualifier}"
													id="qualifier" /></TD>
											</TR>
											<TR>
												<TD width="154"><h:outputText value="Provider Arrangement" />
												</TD>
												<TD width="227">
												<DIV id="pvaDiv" class="selectDataDisplayDiv"></DIV>
												<h:inputHidden
													value="#{benefitComponentCreateBackingBean.providerArrangement}"
													id="providerArrangement" /></TD>
											</TR>
											<TR>
												<TD width="154"><h:outputText value="Data Type" /></TD>
												<TD width="227">
												<DIV id="datatypeDiv" class="selectDataDisplayDiv"></DIV>
												<h:inputHidden
													value="#{benefitComponentCreateBackingBean.dataType}"
													id="dataType" /></TD>
											</TR>
										</table>
										</fieldset>
										</td>
									</TR>
								</TBODY>
							</TABLE>
							<br />
							<TABLE border="0" cellspacing="1" cellpadding="3">
								<TR>
									<TD width="160"><h:outputText value="Version" /></TD>
									<TD width="232"><h:outputText
										value="#{benefitComponentCreateBackingBean.benefitVersion} " />
									<h:inputHidden id="hiddenbenefitVersion"
										value="#{benefitComponentCreateBackingBean.benefitVersion}"></h:inputHidden>
									</TD>
								</TR>
								<TR>
									<TD width="160"><h:outputText value="Created By" /></TD>
									<TD width="232"><h:outputText
										value="#{benefitComponentCreateBackingBean.createdUser} " />
									<h:inputHidden id="hiddenCreateUser"
										value="#{benefitComponentCreateBackingBean.createdUser}"></h:inputHidden>
									</TD>
								</TR>
								<TR>
									<TD width="160"><h:outputText value="Created Date" /></TD>
									<TD width="232"><h:outputText
										value="#{benefitComponentCreateBackingBean.createdTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="hiddenCreatedDate"
										value="#{benefitComponentCreateBackingBean.createdTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden> <h:inputHidden id="time"
										value="#{requestScope.timezone}">
									</h:inputHidden></td>
								</TR>
								<TR>
									<TD width="160"><h:outputText value="Last Updated By" />
									</TD>
									<TD width="232"><h:outputText
										value="#{benefitComponentCreateBackingBean.lastUpdatedUser}" />
									<h:inputHidden id="hiddenUpdateUser"
										value="#{benefitComponentCreateBackingBean.lastUpdatedUser}"></h:inputHidden>
									</TD>
								</TR>
								<TR>
									<TD width="160"><h:outputText value="Last Updated Date" /></TD>
									<TD width="232"><h:outputText
										value="#{benefitComponentCreateBackingBean.lastUpdatedTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="hiddenUpdateDate"
										value="#{benefitComponentCreateBackingBean.lastUpdatedTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden></TD>
								</TR>
							</TABLE>
							<!--	End of Page data	--></FIELDSET>
							</TD>
						</TR>
					</TABLE>

					<!-- Space for hidden fields -->



					<h:commandLink id="hiddenLnk1"
						style="display:none; visibility: hidden;">
						<f:verbatim />
					</h:commandLink>
					<!-- End of hidden fields  -->

				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>

<script>
	
	//For Business Domain Values
	copyHiddenToDiv_ewpd('benefitGeneralInformationForm:lineOfBusiness','lobDiv',2,2); 
	copyHiddenToDiv_ewpd('benefitGeneralInformationForm:businessEntity','businessEntityDiv',2,2); 
	copyHiddenToDiv_ewpd('benefitGeneralInformationForm:businessGroup','businessGrpDiv',2,2); 
	copyHiddenToDiv_ewpd('benefitGeneralInformationForm:marketBusinessUnit','MarketBusinessUnitDiv',2,2);

	//For Universe Values
	copyHiddenToDiv_ewpd('benefitGeneralInformationForm:term','termDiv',2,1); 
	copyHiddenToDiv_ewpd('benefitGeneralInformationForm:qualifier','qualifierDiv',2,2); 
	copyHiddenToDiv_ewpd('benefitGeneralInformationForm:providerArrangement','pvaDiv',2,2); 
	copyHiddenToDiv_ewpd('benefitGeneralInformationForm:dataType','datatypeDiv',2,2); 
	

	formatTildaForRule("benefitGeneralInformationForm:txtRule");
    copyHiddenToDiv_ewpd('benefitGeneralInformationForm:txtState','StateDiv',2,2); 

	copyHiddenToDiv_ewpd('benefitGeneralInformationForm:txtTier','TierDiv',2,1); 

var i;
var obj;
obj = document.getElementById("benefitGeneralInformationForm:CorpPlanCd_list1");
i= obj.value;
	
	if(i=="STANDARD")
	{	
	sub1.style.display='none';
	sub2.style.display='none';
	}
	else 
	{	
	sub1.style.display='';
	
	}


hideTab();
function hideTab(){
	var tab;
	tab = document.getElementById("benefitGeneralInformationForm:hiddenTabValue").value ;	
	if(tab=="Standard Definition"){		
		sbOverrideNotesTab.style.display = '';		
		sbMandateInfoTab.style.display = 'none';
	}
	else{			
		sbOverrideNotesTab.style.display = 'none';	
		sbMandateInfoTab.style.display = '';	
	}
}
function formatTildaForRule(objName)
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
			formattedString += values[i+1]+"-"+values[i] ;
			if(i < n-2)
			formattedString += " " ;
		}
		obj.innerHTML = formattedString;
	}
	return ;
	
	
}
//CARS START
//DESCRIPTION : This method would calll the rule view popup
function viewAction(){	
	var ruleIdStr = document.getElementById('benefitGeneralInformationForm:hiddenRule').value;
	var ruleType = document.getElementById('benefitGeneralInformationForm:strRuleType').value;

	var ruleArray = ruleIdStr.split('~');
	var ruleId = ruleArray[1];

	if(ruleType=="BLZWPDAB"){	
		window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&ruleType='+ruleType+'&temp='+ Math.random(),'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	}
	else if(ruleType!="BLZWPDAB"){
		window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&ruleType='+ruleType+'&temp=' + Math.random(),'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	}
}
//CARS END
document.getElementById("benefitGeneralInformationForm:txtRule").style.height="30px";
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="componentBenefitGeneralInfo" /></form>
</HTML>




