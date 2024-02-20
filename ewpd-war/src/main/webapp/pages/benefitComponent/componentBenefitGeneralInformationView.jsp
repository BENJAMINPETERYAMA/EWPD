<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/test/ComponentBenefitGeneralInformation.java" --%><%-- /jsf:pagecode --%>

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

<TITLE>Benefit Component General Information</TITLE>
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
	<hx:scriptCollector id="scriptCollector1">
		<h:form styleClass="form" id="benefitComponentGeneralInformationForm">
		<h:inputHidden id="benefitCompntId" value="#{benefitComponentCreateBackingBean.benefitCompntId}"></h:inputHidden>
			<TABLE width="100%" cellpadding="0" cellspacing="0">
				<TR>
					<TD><jsp:include page="../navigation/top_view.jsp"></jsp:include></TD>
				</TR>

				<TR>
					<TD>
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD width="273" valign="top" class="leftPanel"><DIV class="treeDiv"><!-- Space for Tree  Data	-->
							<jsp:include page="../benefitComponent/benefitComponentTree.jsp"></jsp:include></DIV>



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
									<TD width="200">
									<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
											<TD width="186" class="tabActive"><h:commandLink>
												<h:outputText value=" General Information" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<h:inputHidden id="hiddenTabValue"
										value="#{benefitComponentBackingBean.componentTypeTab}"></h:inputHidden>
									<TD width="200">
									<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD class="tabNormal"><h:commandLink
												action="#{ComponentBenefitDefinitionsBackingBean.loadStandardDefinitionView}">
												<h:outputText
													value="#{benefitComponentBackingBean.componentTypeTab}" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>


									<TD width="200" id="sbMandateInfoTab">
									<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD class="tabNormal"><h:commandLink
												action="#{benefitMandateBackingBean.loadMandateInformationForView}">
												<h:outputText value="Mandate Information" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<TD width="200" id="sbOverrideNotesTab">
									<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD class="tabNormal"><h:commandLink
												action="#{BenefitComponentNotesBackingBean.loadStandardBenefitNotesView}">
												<h:outputText value="Notes" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<TD width="100"></TD>
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
												<DIV id="lobDiv" class="selectDivReadOnly"></DIV>
												<h:inputHidden
													value="#{benefitComponentCreateBackingBean.lineOfBusiness}"
													id="lineOfBusiness" /></td>
											</tr>
											<TR>
												<TD width="154"><h:outputText value="Business Entity" /></TD>
												<TD width="227">
												<DIV id="businessEntityDiv" class="selectDivReadOnly"></DIV>
												<h:inputHidden
													value="#{benefitComponentCreateBackingBean.businessEntity}"
													id="businessEntity" /></TD>
											</TR>
											<TR>
												<TD width="154"><h:outputText value="Business Group" /></TD>
												<TD width="227">
												<DIV id="businessGrpDiv" class="selectDivReadOnly"></DIV>
												<h:inputHidden
													value="#{benefitComponentCreateBackingBean.businessGroup}"
													id="businessGroup" /></TD>
											</TR>
<!-- --------------------------------------------------------------------- -->
											<TR>
												<TD width="154"><h:outputText value="Market Business Unit" /></TD>
												<TD width="227">
												<DIV id="marketBusinessUnitDiv" class="selectDivReadOnly"></DIV>
												<h:inputHidden
													value="#{benefitComponentCreateBackingBean.marketBusinessUnit}"
													id="marketBusinessUnit" /></TD>
											</TR>
<!-- --------------------------------------------------------------------- -->
										</table>
										</fieldset>
										</td>
									</TR>
									<TR>
										<TD width="160"><h:outputText value="Name" /></TD>
										<TD width="232"><h:outputText id="minorHeading"
											value="#{benefitComponentCreateBackingBean.minorHeading}" />
										<h:inputHidden id="hiddenMinHead"
											value="#{benefitComponentCreateBackingBean.minorHeading}"></h:inputHidden>
										</TD>
									</TR>
									<TR>
										<TD width="160"><h:outputText value="Benefit Meaning" /></TD>
										<TD width="232"><h:outputText id="benMeaning"
											value="#{benefitComponentCreateBackingBean.minorHeading}" />
										<h:inputHidden id="hiddenBenMean"
											value="#{benefitComponentCreateBackingBean.minorHeading}"></h:inputHidden>
										</TD>
									</TR>
									<TR>
										<TD width="160"><h:outputText value="Benefit Type" /></TD>
										<TD width="232"><h:outputText id="benType"
											value="#{benefitComponentCreateBackingBean.sbBenType}" /> <h:inputHidden
											id="hiddenBenType"
											value="#{benefitComponentCreateBackingBean.sbBenType}"></h:inputHidden>
										</TD>
									</TR>
									<TR id="sub1" ; style="display:none;">
										<TD width="160"><h:outputText value="Mandate Type" /></TD>
										<TD width="232"><h:outputText id="manType"
											value="#{benefitComponentCreateBackingBean.sbMandateType}" />
										<h:inputHidden id="hiddenMndtType"
											value="#{benefitComponentCreateBackingBean.sbMandateType}"></h:inputHidden>
										</TD>
									</TR>
									<TR id="sub2" ; style="display:none;">
										<TD width="160"><h:outputText value="State" /></TD>
										<TD width="232"><h:outputText id="stateCde"
											value="#{benefitComponentCreateBackingBean.sbSelState}" /> <h:inputHidden
											id="hiddenState"
											value="#{benefitComponentCreateBackingBean.sbSelState}"></h:inputHidden>
										</TD>
									</TR>
									<TR>
										<TD width="160"><h:outputText value="Benefit Category" /></TD>
										<TD width="232"><h:outputText id="benefitCategoryId"
											value="#{benefitComponentCreateBackingBean.benefitCategory}" /> <h:inputHidden
											id="hiddenBenefitCategory"
											value="#{benefitComponentCreateBackingBean.benefitCategory}"></h:inputHidden>
										</TD>
									</TR>
									<TR>
										<TD width="160" valign="top"><h:outputText value="Description" /></TD>
										<TD width="232"><h:outputText
											value="#{benefitComponentCreateBackingBean.stdBenefitDescription}"
											id="description" styleClass="formTxtAreaReadOnly"/> <h:inputHidden
											id="hiddenDesc"
											value="#{benefitComponentCreateBackingBean.stdBenefitDescription}"></h:inputHidden>
										</TD>
									</TR>
									<TR>
										<TD width="160"><h:outputText value="Benefit Rule ID " /></TD>
										<TD width="232">
											<TABLE>
												<TR>
													<TD>
														<h:outputText id="txtRule" value="#{benefitComponentCreateBackingBean.sbRule}"></h:outputText>
													</TD>
													<TD>
														<h:inputHidden id="hiddenRule" value="#{benefitComponentCreateBackingBean.sbRule}"></h:inputHidden>
														<h:inputHidden id="strRuleType"	value="#{benefitComponentCreateBackingBean.strRuleType}"></h:inputHidden>
														<SCRIPT>copyHiddenToInputText1('benefitComponentGeneralInformationForm:hiddenRule','benefitComponentGeneralInformationForm:txtRule',1); </SCRIPT>	
														<h:commandButton alt="View" id="viewButton"
																image="../../images/view.gif"
																rendered = "#{benefitComponentCreateBackingBean.sbRule != null}"
																onclick="viewAction();return false;" />
													</TD>
												</TR>
											</TABLE>
										</TD>
									</TR>

									<TR>
										<TD width="160"><h:outputText
											styleClass="mandatoryNormal"
											id="TierStar" value="Tier Definition" /></TD>
										<TD width="232">
										<DIV id="TierDiv" class="selectDivReadOnly"></DIV>
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
												<DIV id="termDiv" class="selectDivReadOnly"></DIV>
												<h:inputHidden
													value="#{benefitComponentCreateBackingBean.term}" id="term" />
												</td>
											</tr>
											<TR>
												<TD width="154"><h:outputText value="Qualifier" /></TD>
												<TD width="227">
												<DIV id="qualifierDiv" class="selectDivReadOnly"></DIV>
												<h:inputHidden
													value="#{benefitComponentCreateBackingBean.qualifier}"
													id="qualifier" /></TD>
											</TR>
											<TR>
												<TD width="154"><h:outputText value="Provider Arrangement" />
												</TD>
												<TD width="227">
												<DIV id="pvaDiv" class="selectDivReadOnly"></DIV>
												<h:inputHidden
													value="#{benefitComponentCreateBackingBean.providerArrangement}"
													id="providerArrangement" /></TD>
											</TR>
											<TR>
												<TD width="154"><h:outputText value="Data Type" /></TD>
												<TD width="227">
												<DIV id="datatypeDiv" class="selectDivReadOnly"></DIV>
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
									<h:inputHidden id="createdUser"
										value="#{benefitComponentCreateBackingBean.createdUser}">
									</h:inputHidden></TD>
								</TR>
								<TR>
									<TD width="160"><h:outputText value="Created Date" /></TD>
									<TD width="232"><h:outputText
										value="#{benefitComponentCreateBackingBean.createdTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="hiddenCreateDate"
										value="#{benefitComponentCreateBackingBean.createdTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden> <h:inputHidden id="time"
										value="#{requestScope.timezone}">
									</h:inputHidden></TD>
								</TR>
								<TR>
									<TD width="160"><h:outputText value="Last Updated By" />
									</TD>
									<TD width="232"><h:outputText
										value="#{benefitComponentCreateBackingBean.lastUpdatedUser}" />
									<h:inputHidden id="updatedUser"
										value="#{benefitComponentCreateBackingBean.lastUpdatedUser}">
									</h:inputHidden></TD>
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

					<!-- Space for hidden fields --> <h:commandLink id="hiddenLnk1"
						style="display:none; visibility: hidden;">
						<f:verbatim />
					</h:commandLink> <!-- End of hidden fields  --> </TD>
				</TR>
				<TR>
					<TD><%@ include file="../navigation/bottom_view.jsp"%></TD>
				</TR>
			</TABLE>
			</h:form>
	</hx:scriptCollector>
	</BODY>
</f:view>

<script>
	
	//For Business Domain Values
	copyHiddenToDiv_ewpd('benefitComponentGeneralInformationForm:lineOfBusiness','lobDiv',2,2); 
	copyHiddenToDiv_ewpd('benefitComponentGeneralInformationForm:businessEntity','businessEntityDiv',2,2); 
	copyHiddenToDiv_ewpd('benefitComponentGeneralInformationForm:businessGroup','businessGrpDiv',2,2);
	copyHiddenToDiv_ewpd('benefitComponentGeneralInformationForm:marketBusinessUnit','marketBusinessUnitDiv',2,2);

	//For Universe Values
	copyHiddenToDiv_ewpd('benefitComponentGeneralInformationForm:term','termDiv',2,1); 
	copyHiddenToDiv_ewpd('benefitComponentGeneralInformationForm:qualifier','qualifierDiv',2,2); 
	copyHiddenToDiv_ewpd('benefitComponentGeneralInformationForm:providerArrangement','pvaDiv',2,2); 
	copyHiddenToDiv_ewpd('benefitComponentGeneralInformationForm:dataType','datatypeDiv',2,2);

	// For Rule Id
	formatTildaToComma1("benefitComponentGeneralInformationForm:txtRule");
	
	//for tier definitions
	copyHiddenToDiv_ewpd('benefitComponentGeneralInformationForm:txtTier','TierDiv',2,1); 

getBenefitType();

function getBenefitType()
	{
	var benType;	
	benType = document.getElementById("benefitComponentGeneralInformationForm:benType");
	if(benType.innerHTML =="MANDATE")
	{
		sub1.style.display = '';
	}
	else
	{
		sub1.style.display = 'none';
		sub2.style.display = 'none';
	}
} 



hideTab();
function hideTab(){
	var tab;
	tab = document.getElementById("benefitComponentGeneralInformationForm:hiddenTabValue").value ;
	// alert('tab:'+tab);
	if(tab=="Standard Definition"){		
		sbOverrideNotesTab.style.display = '';		
		sbMandateInfoTab.style.display = 'none';
	}
	else{				
		sbOverrideNotesTab.style.display = 'none';	
		sbMandateInfoTab.style.display = '';	
	}
}

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
//DESCRIPTION : This method calls the benefit rule view popup
function viewAction(){	
	var ruleIdStr = document.getElementById('benefitComponentGeneralInformationForm:hiddenRule').value;
	var ruleType = document.getElementById('benefitComponentGeneralInformationForm:strRuleType').value;
	var ruleArray = ruleIdStr.split('~');
	var ruleId = ruleArray[1];
	//ICD10 Enhancement -- Individual Rule Extract
	var checkMode = 'view'; 
	var headerRuleBenefitSelected = 'true';
	var benefitComponentId = document.getElementById('benefitComponentGeneralInformationForm:benefitCompntId').value;
	var benefitName = document.getElementById('benefitComponentGeneralInformationForm:hiddenMinHead').value;
	//ICD10 End
	if(ruleType=="BLZWPDAB"){	
		window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&ruleType='+ruleType+'&temp='+ 
		Math.random()+'&checkMode='+checkMode+'&headerRuleBenefitSelected='+headerRuleBenefitSelected+'&benefitComponentId='+benefitComponentId+'&benefitName='+escape(benefitName),
		'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	}
	else if(ruleType!="BLZWPDAB"){
		window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&ruleType='+ruleType+'&temp=' + 
		Math.random()+'&checkMode='+checkMode+'&headerRuleBenefitSelected='+headerRuleBenefitSelected+'&benefitComponentId='+benefitComponentId+'&benefitName='+escape(benefitName),
		'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	}
}
//CARS END
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="componentBenefitGeneralInfo" /></form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>




