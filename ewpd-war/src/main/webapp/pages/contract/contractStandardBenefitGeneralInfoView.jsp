
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
<base target=_self>
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
		<tr>
			<td><jsp:include page="../navigation/top_view.jsp"></jsp:include></td>
		</tr>

		<tr>
			<td><h:form styleClass="form" id="contractBenefitGeneralInfo">
			<h:inputHidden id="benefitCompntId" value="#{contractBenefitGeneralInfoBackingBean.benefitCompntId}"></h:inputHidden>


				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<DIV class="treeDiv"><!-- Space for Tree  Data	--> <jsp:include
							page="contractTree.jsp"></jsp:include></DIV>

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
										<td width="3" align="left"><img
											src="../../images/activeTabLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabActive"><h:outputText
											value="General Information" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>

								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{contractCoverageBackingBean.getCoverage}">
											<h:outputText value="#{contractCoverageBackingBean.benefitTypeTab}" />
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
										<td width="186" class="tabNormal">
											<h:commandLink action="#{contractBenefitNotesBackingBean.loadNotes}"
											 id="noteId"> <h:outputText value="Notes" /> </h:commandLink> 
										</td>
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
								<td width="200"></td>
								<td width="200"></td>
								<td width="200%"></td>
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
									<h:inputHidden id="dumlineOfBusinessHidden" value="#{contractBenefitGeneralInfoBackingBean.lob}"></h:inputHidden> 
									<h:inputHidden id="dumbusinessEntityHidden" value="#{contractBenefitGeneralInfoBackingBean.businessEntity}"></h:inputHidden>
									<h:inputHidden id="dumbusinessGroupHidden" value="#{contractBenefitGeneralInfoBackingBean.businessGroup}"></h:inputHidden>
									<h:inputHidden id="dumMarketBusinessUnitHidden" value="#{contractBenefitGeneralInfoBackingBean.marketBusinessUnit}"></h:inputHidden>
									<TD colspan="2">
									<FIELDSET style="width:100%"><LEGEND><FONT color="black">Business
									Domain</FONT></LEGEND>
									<TABLE>
										<TR>
											<TD width="111"><h:outputText value="Line of Business" /></TD>
											<TD width="131">
											<div id="lineOfBusiness" class="selectDivReadOnlyView"></div>
											<h:inputHidden id="lineOfBusinessHidden"
												value="#{contractBenefitGeneralInfoBackingBean.lob}"></h:inputHidden>
											</TD>
										</TR>
										<TR>
											<TD width="111"><h:outputText value="Business Entity" /></TD>
											<TD width="131">
											<div id="businessEntity" class="selectDivReadOnlyView"></div>
											<h:inputHidden id="businessEntityHidden"
												value="#{contractBenefitGeneralInfoBackingBean.businessEntity}"></h:inputHidden>
											</TD>
										</TR>
										<TR>
											<TD width="111"><h:outputText value="Business Group" /></TD>
											<TD width="131">
											<div id="businessGroup" class="selectDivReadOnlyView"></div>
											<h:inputHidden id="businessGroupHidden"
												value="#{contractBenefitGeneralInfoBackingBean.businessGroup}"></h:inputHidden>
											</TD>
										</TR>
										<TR>
											<TD width="111"><h:outputText value="Market Business Unit" /></TD>
											<TD width="131">
											<div id="marketBusinessUnit" class="selectDivReadOnlyView"></div>
											<h:inputHidden id="marketBusinessUnitHidden"
												value="#{contractBenefitGeneralInfoBackingBean.marketBusinessUnit}"></h:inputHidden>
											</TD>
										</TR>
									</TABLE>
									</FIELDSET>
									</TD>
								</TR>

								<TR>
									<TD width="117"><h:outputText value="Name" /></TD>
									<TD width="205"><h:outputText
										value="#{contractBenefitGeneralInfoBackingBean.minorHeading}" />
									<h:inputHidden id="benefitName"
										value="#{contractBenefitGeneralInfoBackingBean.minorHeading}"></h:inputHidden>
									</TD>
								</TR>
								<TR>
									<TD width="117"><h:outputText value="Benefit Meaning" /></TD>
									<TD width="205"><h:outputText
										value="#{contractBenefitGeneralInfoBackingBean.benefitMeaning}" />
									<h:inputHidden id="benMeaning"
										value="#{contractBenefitGeneralInfoBackingBean.benefitMeaning}"></h:inputHidden>
									</TD>
								</TR>
							
									<tr>
									<TD width="117"><h:outputText value="Benefit Type" /></TD>
									<td width="205"><h:outputText
										value="#{contractBenefitGeneralInfoBackingBean.benefitType}" />
									<h:inputHidden id="txtBenefitId"
										value="#{contractBenefitGeneralInfoBackingBean.benefitType}"></h:inputHidden>
									
									<h:inputHidden id="txtBenefitIdHidden"
										value="#{contractBenefitGeneralInfoBackingBean.benefitId}"></h:inputHidden></td>

								</tr>
                                <TR>
									<TD width="117"><h:outputText value="Benefit Category" /></TD>
									<TD width="205"><h:outputText
										value="#{contractBenefitGeneralInfoBackingBean.benefitCategory}" />
									<h:inputHidden id="benCategory"
										value="#{contractBenefitGeneralInfoBackingBean.benefitCategory}"></h:inputHidden>
									</TD>
								</TR>
							
									<TR>
									<TD width="117" valign="top"><h:outputText value="Description " /></TD>
									<TD width="205"><h:outputText
										value="#{contractBenefitGeneralInfoBackingBean.description}" styleClass="formTxtAreaReadOnly"/>
									<h:inputHidden id="benefitDesc"
										value="#{contractBenefitGeneralInfoBackingBean.description}"></h:inputHidden>
									</TD>
								</TR>
								
							
									<TR>
									<TD width="117" valign="top"><h:outputText value="Tier Definition " /></TD>
									<TD width="205"><DIV id="tierDefDiv" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="tierDefHidden"
												value="#{contractBenefitGeneralInfoBackingBean.tierDefinition}"></h:inputHidden></TD>									
								</TR>
									<TR>
									<TD width="117"><h:outputText value="Benefit Rule ID" /></TD>
									<TD width="205"><h:outputText id="txtRule"
										value="#{contractBenefitGeneralInfoBackingBean.ruleType}" />
									<h:inputHidden id="ruleId"
										value="#{contractBenefitGeneralInfoBackingBean.ruleType}"></h:inputHidden>
									<h:inputHidden id="txhRuleType" value="#{contractBenefitGeneralInfoBackingBean.blzeRuleType}"></h:inputHidden>
									
									</TD>
									<TD width="8%">
										<h:commandButton alt="View" id="viewButton"
												image="../../images/view.gif"
												onclick="viewAction();return false;" />
									</TD>
								</TR>
								<TR>
									<TD colspan="3">
									<FIELDSET style="width:100%"><LEGEND><FONT color="black">Benefit Level Scope</FONT></LEGEND>
									<TABLE>
										<TR>
											<TD width="110"><h:outputText id="TermStar" value="Term" /></TD>
											<TD width="142">
											<DIV id="TermDiv" class="selectDivReadOnlyView"></DIV>
											<h:inputHidden id="txtTerm"
												value="#{contractBenefitGeneralInfoBackingBean.term}"></h:inputHidden>
											</TD>

										</TR>
										<TR>
											<TD width="110"><h:outputText id="QualifierStar"
												value="Qualifier" /></TD>
											<TD width="142">
											<DIV id="QualifierDiv" class="selectDivReadOnlyView"></DIV>
											<h:inputHidden id="txtQualifier"
												value="#{contractBenefitGeneralInfoBackingBean.qualifier}"></h:inputHidden>
											</TD>
										</TR>
										<TR>
											<TD width="110"><h:outputText id="ProviderArrangementStar"
												value="Provider Arrangement" /></TD>
											<TD width="142">
											<DIV id="ProviderArrangementDiv"
												class="selectDivReadOnlyView"></DIV>
											<h:inputHidden id="txtProviderArrangement"
												value="#{contractBenefitGeneralInfoBackingBean.providerArrangement}"></h:inputHidden>
											</TD>
										</TR>
										<TR>
											<TD width="110"><h:outputText id="DataTypeStar"
												value="Data Type" /></TD>
											<TD width="142">
											<DIV id="DataTypeDiv" class="selectDivReadOnlyView"></DIV>
											<h:inputHidden id="txtDataType"
												value="#{contractBenefitGeneralInfoBackingBean.dataType}"></h:inputHidden>
											</TD>
										</TR>
									</TABLE>
									</FIELDSET>
									</TD>
								</TR>
					
								<TR>
							
								<TR>
								<tr>
									<TD width="117"><h:outputText
										value="Version" /></td>
									<TD width="205"><h:outputText
										value="#{contractBenefitGeneralInfoBackingBean.benefitVersion}" />
									<h:inputHidden id="versionHidden"
										value="#{contractBenefitGeneralInfoBackingBean.benefitVersion}"></h:inputHidden></td>
									
								</tr>
								<TR>
									<TD width="117"><h:outputText value="Created By " /></TD>
									<TD width="205"><h:outputText
										value="#{contractBenefitGeneralInfoBackingBean.createdUser}" />
									<h:inputHidden id="createBy"
										value="#{contractBenefitGeneralInfoBackingBean.createdUser}"></h:inputHidden>
									</TD>
								</TR>
								<TR>
									<TD width="117"><h:outputText value="Created Date " /></TD>
									<TD width="205"><h:outputText
										value="#{contractBenefitGeneralInfoBackingBean.createdTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="createDate"
										value="#{contractBenefitGeneralInfoBackingBean.createdTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden> <h:inputHidden id="time"
										value="#{requestScope.timezone}">
									</h:inputHidden></TD>
								</TR>
								<TR>
									<TD width="117"><h:outputText value="Last Updated By " /></TD>
									<TD width="205"><h:outputText
										value="#{contractBenefitGeneralInfoBackingBean.lastUpdatedUser}" />
									<h:inputHidden id="updateBy"
										value="#{contractBenefitGeneralInfoBackingBean.lastUpdatedUser}"></h:inputHidden>
									</TD>
								</TR>
								<TR>
									<TD width="117"><h:outputText value="Last Updated Date " /></TD>
									<TD width="205"><h:outputText
										value="#{contractBenefitGeneralInfoBackingBean.lastUpdatedTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="updateDate"
										value="#{contractBenefitGeneralInfoBackingBean.lastUpdatedTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden></TD>
								</TR>


							</TBODY>
						</TABLE>
						<!--	End of Page data	--></fieldset>
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
				<h:inputHidden id="hidden1" value="value1"></h:inputHidden>
			<h:inputHidden id="hiddenProductType"
					value="#{contractBenefitGeneralInfoBackingBean.pageLoadBasedOnContractType}"></h:inputHidden>
				<h:commandLink id="hiddenLnk1"
					style="display:none; visibility: hidden;">
					<f:verbatim />
				</h:commandLink>
				<!-- End of hidden fields  -->

			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom_view.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="contractStandardBenefitGeneralPrint" />
</form>
<script>

copyHiddenToDiv_ewpd('contractBenefitGeneralInfo:lineOfBusinessHidden','lineOfBusiness',2,2); 
copyHiddenToDiv_ewpd('contractBenefitGeneralInfo:businessEntityHidden','businessEntity',2,2); 
copyHiddenToDiv_ewpd('contractBenefitGeneralInfo:businessGroupHidden','businessGroup',2,2); 
copyHiddenToDiv_ewpd('contractBenefitGeneralInfo:marketBusinessUnitHidden','marketBusinessUnit',2,2);  
copyHiddenToDiv_ewpd('contractBenefitGeneralInfo:txtTerm','TermDiv',2,2); 
copyHiddenToDiv_ewpd('contractBenefitGeneralInfo:txtQualifier','QualifierDiv',2,2); 
copyHiddenToDiv_ewpd('contractBenefitGeneralInfo:txtProviderArrangement','ProviderArrangementDiv',2,2); 
copyHiddenToDiv_ewpd('contractBenefitGeneralInfo:txtDataType','DataTypeDiv',2,1); 
copyHiddenToDiv_ewpd('contractBenefitGeneralInfo:tierDefHidden','tierDefDiv',1,1);
formatTildaToComma1('contractBenefitGeneralInfo:txtRule');
	i = document.getElementById("contractBenefitGeneralInfo:hiddenProductType").value;
	if(i=='MANDATE')
	{
	tab2.style.display='none';
	tab3.style.display='';
	}else{
	tab2.style.display='';
	tab3.style.display='none';
	}
	
function viewAction(){
	
	var ruleIdStr = document.getElementById('contractBenefitGeneralInfo:ruleId').value;
	var ruleType = document.getElementById('contractBenefitGeneralInfo:txhRuleType').value;
	var ruleArray = ruleIdStr.split('~');
	var ruleId = ruleArray[1];
	//ICD10 Enhancement -- Individual Rule Extract
	var checkMode = 'view'; 
	var headerRuleBenefitSelected = 'true';
	var benefitComponentId = document.getElementById('contractBenefitGeneralInfo:benefitCompntId').value;
	var benefitName = document.getElementById('contractBenefitGeneralInfo:benefitName').value;
	//ICD10 End
if(ruleType=="BLZWPDAB")
	{
	window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&ruleType='+ruleType+'&temp='
	+ Math.random()+'&checkMode='+checkMode+'&headerRuleBenefitSelected='+headerRuleBenefitSelected+'&benefitComponentId='+escape(benefitComponentId)+'&benefitName='+escape(benefitName),
	'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	}
	else if(ruleType!="BLZWPDAB") {

		window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&ruleType='+ruleType+'&temp=' 
		+ Math.random()+'&checkMode='+checkMode+'&headerRuleBenefitSelected='+headerRuleBenefitSelected+'&benefitComponentId='+escape(benefitComponentId)+'&benefitName='+escape(benefitName),
		'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	}

		
}	
</script>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
