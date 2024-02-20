
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
<TITLE>BenefitGeneralInformationView</TITLE>
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
			<td><jsp:include page="../navigation/top_view.jsp"></jsp:include></td>
		</tr>

		<tr>
			<td><h:form styleClass="form" id="formName">
				<h:inputHidden id="benefitCompntId" value="#{productBenefitGeneralInfoBackingBean.benefitCompntId}"></h:inputHidden>


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
									<TD><w:message></w:message></TD>
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
											action="#{productBenefitDetailBackingBean.getBenefitDefinitionsPage}">
											<h:outputText value="Standard Definition" />
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
											action="#{productBenefitNoteBackingBean.loadNotes}">
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
									<TD width="140"><h:outputText value="Name" /></TD>
									<TD width="232"><h:outputText
										value="#{productBenefitGeneralInfoBackingBean.name}" /> <h:inputHidden
										id="benefitName"
										value="#{productBenefitGeneralInfoBackingBean.name}"></h:inputHidden>
									</TD>
								</TR>
								<!-- **Enhancements** -->
								<TR>
									<TD width="140"><h:outputText value="Benefit Meaning " /></TD>
									<TD width="232"><h:outputText styleClass=""
										value="#{productBenefitGeneralInfoBackingBean.name}" /> <h:inputHidden
										id="benefitMeaning"
										value="#{productBenefitGeneralInfoBackingBean.name}"></h:inputHidden>
									</TD>
								</TR>
								<TR>
									<TD width="140"><h:outputText value="Benefit Type" /></TD>
									<TD width="232"><h:outputText id="benType"
										value="#{productBenefitGeneralInfoBackingBean.benefitType}"
										styleClass="" /> <h:inputHidden id="benTypeHidden"
										value="#{productBenefitGeneralInfoBackingBean.benefitType}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('formName:benTypeHidden','formName:benType',1); </SCRIPT>
									</TD>
								</TR>
								<TR>
									<TD width="140"><h:outputText value="Benefit Category" /></TD>
									<TD width="232"><h:outputText id="benCategory"
										value="#{productBenefitGeneralInfoBackingBean.benefitCategory}"
										styleClass="" /> <h:inputHidden id="benCategoryHidden"
										value="#{productBenefitGeneralInfoBackingBean.benefitCategory}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('formName:benCategoryHidden','formName:benCategory',1); </SCRIPT>
									</TD>
								</TR>
								<TR id="sub1" style="display:none;">
									<TD width="140"><h:outputText value="Mandate Type" /></TD>
									<TD width="232"><h:inputTextarea styleClass="selectDivReadOnly"
										id="mandateType"
										value="#{productBenefitGeneralInfoBackingBean.mandateType}"
										readonly="true" style="border:0"></h:inputTextarea><h:inputHidden
										id="mandateTypeHidden"
										value="#{productBenefitGeneralInfoBackingBean.mandateType}">
									</h:inputHidden></TD>
								</TR>
								<TR>
									<TD width="140"><h:outputText value="Benefit Rule ID" /></TD>
									<TD width="232">
									<TABLE>
										<TR>
											<TD><h:outputText id="ruleId"
												value="#{productBenefitGeneralInfoBackingBean.ruleId}"
												styleClass="" /></TD>
											<TD><h:inputHidden id="ruleIdHidden"
												value="#{productBenefitGeneralInfoBackingBean.ruleId}">
											</h:inputHidden> 
											<h:inputHidden id="txtStrRuleType"
										value="#{productBenefitGeneralInfoBackingBean.strRuleType}"/>
											<SCRIPT>copyHiddenToInputText1('formName:ruleIdHidden','formName:ruleId',1); </SCRIPT>
											<h:commandButton alt="View" id="viewButton"
												image="../../images/view.gif"
												rendered="#{productBenefitGeneralInfoBackingBean.ruleId != null}"
												onclick="viewAction();return false;" /></TD>
										</TR>
									</TABLE>
									</TD>

								</TR>

								<!-- **End **-->
								<TR>
									<TD width="140" valign="top"><h:outputText value="Description " /></TD>
									<TD width="232"><h:outputText
										value="#{productBenefitGeneralInfoBackingBean.description}"
										styleClass="formTxtAreaReadOnly" /> <h:inputHidden
										id="benefitDesc"
										value="#{productBenefitGeneralInfoBackingBean.description}"></h:inputHidden>
									</TD>
								</TR>
								<TR>
									<TD width="140" valign="top"><h:outputText
										styleClass="mandatoryNormal" id="TierStar"
										value="Tier Definition" /></TD>
									<TD width="232"><h:outputText id="txtTier"
										value="#{productBenefitGeneralInfoBackingBean.tier}"></h:outputText>
									<h:inputHidden id="hiddenTier"
										value="#{productBenefitGeneralInfoBackingBean.tier}"></h:inputHidden>
									</TD>
								</TR>
								<TR>
									<TD colspan="3">
									<FIELDSET style="width:100%"><LEGEND><FONT color="black">Benefit
									Level Scope</FONT></LEGEND>
									<TABLE>
										<TR>
											<TD width="140"><h:outputText id="TermStar" value="Term" /></TD>
											<TD width="120">
											<DIV id="TermDiv" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="txtTerm"
												value="#{productBenefitGeneralInfoBackingBean.term}"></h:inputHidden>
											</TD>
										</TR>
										<TR>
											<TD width="140"><h:outputText id="QualifierStar"
												value="Qualifier" /></TD>
											<TD width="120">
											<DIV id="QualifierDiv" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="txtQualifier"
												value="#{productBenefitGeneralInfoBackingBean.qualifier}"></h:inputHidden>
											</TD>
										</TR>
										<TR>
											<TD width="140"><h:outputText id="ProviderArrangementStar"
												value="Provider Arrangement" /></TD>
											<TD width="120">
											<DIV id="ProviderArrangementDiv" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="txtProviderArrangement"
												value="#{productBenefitGeneralInfoBackingBean.providerArrangement}"></h:inputHidden>
											</TD>
										</TR>
										<TR>
											<TD width="140"><h:outputText id="DataTypeStar"
												value="Data Type" /></TD>
											<TD width="120">
											<DIV id="DataTypeDiv" class="selectDivReadOnly"></DIV>
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
									<TD width="232"><h:outputText styleClass=""
										value="#{productBenefitGeneralInfoBackingBean.createdBy}" />
									<h:inputHidden id="createBy"
										value="#{productBenefitGeneralInfoBackingBean.createdBy}"></h:inputHidden>
									</TD>
								</TR>
								<TR>
									<TD width="140"><h:outputText value="Created Date " /></TD>
									<TD width="232"><h:outputText styleClass=""
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
									<TD width="232"><h:outputText styleClass=""
										value="#{productBenefitGeneralInfoBackingBean.updatedBy}" />
									<h:inputHidden id="updateBy"
										value="#{productBenefitGeneralInfoBackingBean.updatedBy}"></h:inputHidden>
									</TD>
								</TR>

								<TR>
									<TD width="140"><h:outputText value="Last Updated Date " /></TD>
									<TD width="232"><h:outputText styleClass=""
										value="#{productBenefitGeneralInfoBackingBean.updationDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="updateDate"
										value="#{productBenefitGeneralInfoBackingBean.updationDate}">
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
			<td><%@ include file="../navigation/bottom_view.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="productBenefitGenInfoPrint" /></form>
<script>

copyHiddenToDiv_ewpd('formName:lineOfBusinessHidden','lineOfBusiness',2,2); 
copyHiddenToDiv_ewpd('formName:businessEntityHidden','businessEntity',2,2); 
copyHiddenToDiv_ewpd('formName:businessGroupHidden','businessGroup',2,2); 
copyHiddenToDiv_ewpd('formName:marketBusinessUnitHidden','marketBusinessUnit',2,2); 
copyHiddenToDiv_ewpd('formName:txtTerm','TermDiv',2,1); 
copyHiddenToDiv_ewpd('formName:txtQualifier','QualifierDiv',2,2); 
copyHiddenToDiv_ewpd('formName:txtProviderArrangement','ProviderArrangementDiv',2,2); 
copyHiddenToDiv_ewpd('formName:txtDataType','DataTypeDiv',2,2); 

//for tier definitions
formatTilda("formName:txtTier"); 

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
				}else if( j == "ET"){
				document.getElementById("formName:mandateType").value="ET";
				}else{
				document.getElementById("formName:mandateType").value="Federal";
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
//DESCRIPTION : This method calls the rule view popup.


function viewAction(){	

	var ruleIdStr = document.getElementById('formName:ruleIdHidden').value;
	var ruleType = document.getElementById('formName:txtStrRuleType').value;
	if(ruleIdStr.length <=1){
		alert('Benefit Rule ID need to be selected.');
		return false;
	}

	var ruleArray = ruleIdStr.split('-');
	var ruleId = ruleArray[0];	
	//ICD10 Enhancement -- Individual Rule Extract
	var checkMode = 'view'; 
	var headerRuleBenefitSelected = 'true';
	var benefitComponentId = document.getElementById('formName:benefitCompntId').value;
	var benefitName = document.getElementById('formName:benefitName').value;
	//ICD10 End
	if(ruleType=="BLZWPDAB"){	
		window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&ruleType='+ruleType+'&temp='
		+ Math.random()+'&checkMode='+checkMode+'&headerRuleBenefitSelected='+headerRuleBenefitSelected+'&benefitComponentId='+escape(benefitComponentId)+'&benefitName='+escape(benefitName),
		'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	}
	else if(ruleType!="BLZWPDAB"){
		window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&ruleType='+ruleType+'&temp=' 
		+ Math.random()+'&checkMode='+checkMode+'&headerRuleBenefitSelected='+headerRuleBenefitSelected+'&benefitComponentId='+escape(benefitComponentId)+'&benefitName='+escape(benefitName),
		'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	}
}

//CARS END
</script>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
