
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

<TITLE>BenefitGeneralInformation</TITLE>
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
	<h:inputHidden id="Hidden"
		value="#{productBenefitGeneralInfoBackingBean.dummyVar}"></h:inputHidden>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td><jsp:include page="../navigation/top_Print.jsp"></jsp:include></td>
		</tr>

		<tr>
			<td><h:form styleClass="form" id="formName">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<DIV class="treeDiv"><!-- Space for Tree  Data	--> <jsp:include
							page="productTree.jsp"></jsp:include></DIV>

						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><!-- Insert WPD Message Tag --><w:message></w:message></TD>
								</tr>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="200" id="tab1">
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
								<td width="200" id="tab2">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{productBenefitDetailBackingBean.getBenefitDefinitionsPage}"
											onmousedown="javascript:navigatePageAction(this.id);">
											<h:outputText value="Coverage" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200" id="tabmandate">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{productBenefitDetailBackingBean.getBenefitDefinitionsPage}">
											<h:outputText value="Mandate Definition" />
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
											action="#{productBenefitMandateBackingBean.loadMandateInfo}">
											<h:outputText value="Mandate Information" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200" id=tab4>
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td width="186" class="tabNormal"><h:commandLink
											action="#{productBenefitNoteBackingBean.loadNotes}"
											onmousedown="javascript:navigatePageAction(this.id);">
											<h:outputText value="Notes" />
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
							class="outputText">
							<TBODY>
								<TR>
									<TD colspan="2">
									<FIELDSET style="width:100%"><LEGEND><FONT color="black">Business
									Domain</FONT></LEGEND>
									<TABLE>
										<TR>
											<TD width="140"><h:outputText value="Line of Business" /></TD>
											<TD width="120">
											<div id="lineOfBusiness" class="selectDivReadOnly"></div>
											<h:inputHidden id="lineOfBusinessHidden"
												value="#{productBenefitGeneralInfoBackingBean.lineOfBusiness}"></h:inputHidden>
											</TD>
										</TR>
										<TR>
											<TD width="140"><h:outputText value="Business Entity" /></TD>
											<TD width="120">
											<div id="businessEntity" class="selectDivReadOnly"></div>
											<h:inputHidden id="businessEntityHidden"
												value="#{productBenefitGeneralInfoBackingBean.businessEntity}"></h:inputHidden>
											</TD>
										</TR>
										<TR>
											<TD width="140"><h:outputText value="Business Group" /></TD>
											<TD width="120">
											<div id="businessGroup" class="selectDivReadOnly"></div>
											<h:inputHidden id="businessGroupHidden"
												value="#{productBenefitGeneralInfoBackingBean.businessGroup}"></h:inputHidden>
											</TD>
										</TR>
<!-- CARS START -->						
										<TR>
											<TD width="140"><h:outputText value="Market Business Unit" /></TD>
											<TD width="120">
											<div id="marketBusinessUnit" class="selectDivReadOnly" ></div><h:inputHidden
												id="marketBusinessUnitHidden"
												value="#{productBenefitGeneralInfoBackingBean.marketBusinessUnit}"></h:inputHidden>
											</TD>
										</TR>
<!-- CARS END -->
									</TABLE>
									</FIELDSET>
									</TD>
								</TR>

								<TR>
									<TD width="140">&nbsp;<h:outputText value="Name" /></TD>
									<TD width="120"><h:outputText styleClass=""
										value="#{productBenefitGeneralInfoBackingBean.name}" /> <h:inputHidden
										id="benefitName"
										value="#{productBenefitGeneralInfoBackingBean.name}"></h:inputHidden>
									</TD>
								</TR>
								<!-- **Enhancements** -->
								<TR>
									<TD width="140">&nbsp;<h:outputText value="Benefit Meaning " /></TD>
									<TD width="120"><h:outputText styleClass=""
										value="#{productBenefitGeneralInfoBackingBean.name}" /> <h:inputHidden
										id="benefitMeaning"
										value="#{productBenefitGeneralInfoBackingBean.name}"></h:inputHidden>
									</TD>
								</TR>
								<TR>
									<TD width="140">&nbsp;<h:outputText value="Benefit Type" /></TD>
									<TD width="120"><h:outputText id="benType"
										value="#{productBenefitGeneralInfoBackingBean.benefitType}"
										styleClass="" /> <h:inputHidden id="benTypeHidden"
										value="#{productBenefitGeneralInfoBackingBean.benefitType}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('formName:benTypeHidden','formName:benType',1); </SCRIPT>
									</TD>
								</TR>
								<TR>
									<TD width="140">&nbsp;<h:outputText value="Benefit Category" /></TD>
									<TD width="120"><h:outputText id="benCategory"
										value="#{productBenefitGeneralInfoBackingBean.benefitCategory}"
										styleClass="" /> <h:inputHidden id="benCategoryHidden"
										value="#{productBenefitGeneralInfoBackingBean.benefitCategory}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('formName:benCategoryHidden','formName:benCategory',1); </SCRIPT>
									</TD>
								</TR>
								<TR id="sub1" style="display:none;">
									<TD width="140">&nbsp;<h:outputText value="Mandate Type" /></TD>
									<TD width="120"><h:inputTextarea styleClass="selectDivReadOnly"
										id="mandateType"
										value="#{productBenefitGeneralInfoBackingBean.mandateType}"
										readonly="true" style="border:0"></h:inputTextarea><h:inputHidden
										id="mandateTypeHidden"
										value="#{productBenefitGeneralInfoBackingBean.mandateType}">
									</h:inputHidden></TD>
								</TR>

								<TR>

									<TD width="140">&nbsp;<h:outputText
										styleClass="#{productBenefitGeneralInfoBackingBean.requiredRule ? 'mandatoryError': 'mandatoryNormal'}"
										id="RuleStar" value="Benefit Rule ID*" /></TD>

									<TD width="120">
									<DIV id="RuleDiv" class="selectDataDisplayDiv"></DIV>
									<h:inputHidden id="txtRule"
										value="#{productBenefitGeneralInfoBackingBean.rule}"></h:inputHidden>
									<h:inputHidden id="txtStrRuleType"
										value="#{productBenefitGeneralInfoBackingBean.strRuleType}">
									</h:inputHidden></TD>
									<TD width="12%"><h:commandButton alt="Select" id="RuleButton"
										image="../../images/select.gif" style="cursor: hand"
										onclick="loadpopup();return false;"></h:commandButton>&nbsp; <h:commandButton
										alt="View" id="viewButton" image="../../images/view.gif"
										onclick="viewAction();return false;" /></TD>
								</TR>

								<!-- **End **-->
								<TR>
									<TD width="140">&nbsp;<h:outputText value="Description " /></TD>
									<TD width="120"><h:inputTextarea readonly="true" 
										styleClass="formTxtAreaField_ViewDesc"
										value="#{productBenefitGeneralInfoBackingBean.description}" />
									<h:inputHidden id="benefitDesc"
										value="#{productBenefitGeneralInfoBackingBean.description}"></h:inputHidden>
									</TD>
								</TR>
								<TR>
									<TD width="140">&nbsp;<h:outputText
										styleClass="mandatoryNormal" id="TierStar"
										value="Tier Definition" /></TD>

									<TD width="120">
									<DIV id="TierDiv" class="selectDataDisplayDiv"></DIV>
									<h:inputHidden id="txtTier"
										value="#{productBenefitGeneralInfoBackingBean.tier}"></h:inputHidden>
									</TD>
									<TD width="8%"><h:commandButton alt="Select" id="TierButton"
										image="../../images/select.gif" style="cursor: hand"
										onclick="getTierDefinitionPopUp('../popups/productTierDefinitionPopup.jsp',
										'TierDiv','formName:txtTier',2,1);return false;"
										tabindex="9"></h:commandButton></TD>
								</TR>
								<TR>
									<TD colspan="3">
									<FIELDSET style="width:100%"><LEGEND><FONT color="black">Benefit
									Level Scope</FONT></LEGEND>
									<TABLE>
										<TR>
											<TD width="140"><h:outputText id="TermStar" value="Term" /></TD>
											<TD width="120">
											<DIV id="term" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="txtTerm"
												value="#{productBenefitGeneralInfoBackingBean.term}"></h:inputHidden>
											</TD>

										</TR>
										<TR>
											<TD width="140"><h:outputText id="QualifierStar"
												value="Qualifier" /></TD>
											<TD width="120">
											<DIV id="qualifier" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="txtQualifier"
												value="#{productBenefitGeneralInfoBackingBean.qualifier}"></h:inputHidden>
											</TD>
										</TR>
										<TR>
											<TD width="140"><h:outputText id="ProviderArrangementStar"
												value="Provider Arrangement" /></TD>
											<TD width="120">
											<DIV id="providerArrangement" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="txtProviderArrangement"
												value="#{productBenefitGeneralInfoBackingBean.providerArrangement}"></h:inputHidden>
											</TD>
										</TR>
										<TR>
											<TD width="140"><h:outputText id="DataTypeStar"
												value="Data Type" /></TD>
											<TD width="120">
											<DIV id="dataType" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="txtDataType"
												value="#{productBenefitGeneralInfoBackingBean.dataType}"></h:inputHidden>
											</TD>
										</TR>
									</TABLE>
									</FIELDSET>
									</TD>
								</TR>
								<TR>
									<TD width="140"><h:outputText value="Version" /></TD>
									<TD width="120"><h:outputText styleClass=""
										value="#{productBenefitGeneralInfoBackingBean.bnftVersion}" />
									<h:inputHidden id="bnftVersion"
										value="#{productBenefitGeneralInfoBackingBean.bnftVersion}"></h:inputHidden>
									</TD>
								</TR>
								<TR>
									<TD width="140"><h:outputText value="Created By " /></TD>
									<TD width="120"><h:outputText styleClass=""
										value="#{productBenefitGeneralInfoBackingBean.createdBy}" />
									<h:inputHidden id="createBy"
										value="#{productBenefitGeneralInfoBackingBean.createdBy}"></h:inputHidden>
									</TD>
								</TR>
								<TR>
									<TD width="140"><h:outputText value="Created Date " /></TD>
									<TD width="150"><h:outputText styleClass=""
										value="#{productBenefitGeneralInfoBackingBean.creationDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="createDate"
										value="#{productBenefitGeneralInfoBackingBean.creationDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden> <h:inputHidden id="time"
										value="#{requestScope.timezone}">
									</h:inputHidden></TD>
								</TR>
								<TR>
									<TD width="140"><h:outputText value="Last Updated By " /></TD>
									<TD width="120"><h:outputText styleClass=""
										value="#{productBenefitGeneralInfoBackingBean.updatedBy}" />
									<h:inputHidden id="updateBy"
										value="#{productBenefitGeneralInfoBackingBean.updatedBy}"></h:inputHidden>
									</TD>
								</TR>

								<TR>
									<TD width="140"><h:outputText value="Last Updated Date " /></TD>
									<TD width="150"><h:outputText styleClass=""
										value="#{productBenefitGeneralInfoBackingBean.updationDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="updateDate"
										value="#{productBenefitGeneralInfoBackingBean.updationDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden></TD>
								</TR>
								<TR>
									<TD width="110"><h:commandButton value="Save" id="saveButton"
										styleClass="wpdButton" onclick="saveBenefit();return false"
										tabindex="6" onmousedown="javascript:savePageAction(this.id);">
									</h:commandButton> <h:commandLink id="saveLink"
										style="display:none; visibility: hidden;"
										action="#{productBenefitGeneralInfoBackingBean.saveBenefitGeneralInfo}">
									</h:commandLink></TD>
								</TR>

							</TBODY>
						</TABLE>
						<!--	End of Page data	--></fieldset>
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
				<h:inputHidden id="hidden1" value="value1"></h:inputHidden>
				<h:commandLink id="hiddenLnk1"
					style="display:none; visibility: hidden;">
					<f:verbatim />
				</h:commandLink>
				<h:inputHidden id="hiddenProductType"
					value="#{productBenefitGeneralInfoBackingBean.productType}"></h:inputHidden>
				<!-- End of hidden fields  -->

			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="productBenefitGenInfoPrint" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>


copyHiddenToDiv_ewpd('formName:lineOfBusinessHidden','lineOfBusiness',2,2); 
copyHiddenToDiv_ewpd('formName:businessEntityHidden','businessEntity',2,2); 
copyHiddenToDiv_ewpd('formName:businessGroupHidden','businessGroup',2,2);
copyHiddenToDiv_ewpd('formName:marketBusinessUnitHidden','marketBusinessUnit',2,2);  
copyHiddenToDiv_ewpd('formName:txtTerm','term',2,1); 
copyHiddenToDiv_ewpd('formName:txtQualifier','qualifier',2,2); 
copyHiddenToDiv_ewpd('formName:txtProviderArrangement','providerArrangement',2,2); 
copyHiddenToDiv_ewpd('formName:txtDataType','dataType',2,2); 

copyHiddenToDiv_ewpd('formName:txtTier','TierDiv',2,1); 
copyHiddenToDiv_ewpd1('formName:txtRule','RuleDiv',2,1);

	var i;
var obj;
obj = document.getElementById("formName:benType");
i= obj.value;
var j;

	if(i=="MANDATE")
	{
	sub1.style.display='';
	j=document.getElementById("formName:mandateType").innerHTML;
			if(j == "ST"){
				document.getElementById("formName:mandateType").value="State";
				document.getElementById("formName:mandateTypeHidden").value="State";
				}else if( j == "ET"){
				document.getElementById("formName:mandateType").value="ET";
				}else{
				document.getElementById("formName:mandateType").value="Federal";
				document.getElementById("formName:mandateTypeHidden").value="Federal";
				}
	
	}
	else 
	{
	sub1.style.display='none';

	}
i = document.getElementById("formName:hiddenProductType").value;
if(i=='MANDATE')
{
tab4.style.display='none';
tab2.style.display='none';
tab3.style.display='';
tabmandate.style.display='';
}else{
tab4.style.display='';
tab2.style.display='';
tab3.style.display='none';
tabmandate.style.display='none';
}

function getTierDefinitionPopUp(url, targetDiv, targetHidden, attrCount, attrPosn){
	url = url +getUrl()+ '?temp='+Math.random();
	ewpdModalWindow_ewpd(url, targetDiv, targetHidden, attrCount, attrPosn);
}

function saveBenefit(){
	document.getElementById('formName:saveLink').click();
}


function loadpopup(ruleId){
var ruleId = document.getElementById('formName:txtRule').value;
if(null == ruleId || "" == ruleId){
ruleId = 0;
}
var emptyString='';
var titleName = 'Benefit Rule Popup';
ewpdModalWindowWithRuleType('../popups/benefitRuleTypePopup.jsp'+getUrl()+'?queryName=locateRuleId&ruleId='+ruleId+'&titleName='+titleName+'&temp='+Math.random(),'RuleDiv','formName:txtRule','formName:txtStrRuleType',2,1); return false;
}

function viewAction(){	
	var ruleIdStr = document.getElementById('formName:txtRule').value;
	var ruleType = document.getElementById('formName:txtStrRuleType').value;
	if(ruleIdStr.length <=1){
		alert('Benefit Rule ID need to be selected.');
		return false;
	}
	var ruleArray = ruleIdStr.split('~');
	var ruleId = ruleArray[1];	
	if(ruleType=="BLZWPDAB"){	
		window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&ruleType='+ruleType+'&temp='+ Math.random(),'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	}
	else if(ruleType!="BLZWPDAB"){
		window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&ruleType='+ruleType+'&temp=' + Math.random(),'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	}
}

</script>



</HTML>
