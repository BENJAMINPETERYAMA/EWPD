<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="PRAGMA" content="NO-CACHE">
<META HTTP-EQUIV="Expires" CONTENT="-1">
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

<TITLE>WellPoint Product Database: Basic Information</TITLE>
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
		<h:inputHidden id="temp"
			value="#{contractBasicInfoBackingBean.initView}"></h:inputHidden>
		<%-- 
		<w:message></w:message>
--%>
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><jsp:include page="../navigation/top_view.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="contractForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD width="273" valign="top" class="leftPanel">


							<DIV class="treeDiv"><!-- Space for Tree  Data	--> <jsp:include
								page="contractTree.jsp"></jsp:include></DIV>

							</TD>

							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message /></TD>
									</TR>
								</TBODY>
							</TABLE>

							<!-- Table containing Tabs -->
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<TR>
									<TD width="16%">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
											<TD width="100%" class="tabActive"><h:outputText
												value=" General Information" /></TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>

									<!-- TD width="18%">
											<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
												<TR>
													<TD width="3" align="left"><img	src="../../images/tabNormalLeft.gif" alt="Tab Left Normal" width="3" height="21" /></td>
													<TD width= "100%" class="tabNormal"> 
														<h:commandLink action="#{contractSpecificInfoBackingBean.loadContractSpecificInfo}">
														<h:outputText id="specificInfoTabTable" value="Specific Information"/> 
														</h:commandLink> 
													</TD>
													<td width="2" align="right"><img src="../../images/tabNormalRight.gif" alt="Tab Right Normal" width="4" height="21" /></td>
												</TR>
											</TABLE>
										</TD-->
									<TD width="18%">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21" /></td>
											<TD width="100%" class="tabNormal"><h:commandLink
												action="#{contractPricingInfoBackingBean.dispalyContractPricingInfoTab}">
												<h:outputText id="pricingInfoTabTable"
													value="Pricing Information" />
											</h:commandLink></TD>
											<td width="2" align="right"><img
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21" /></td>
										</TR>
									</TABLE>
									</TD>
									<TD width="10%" id="tabForStandard1">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21" /></td>
											<td class="tabNormal"><h:commandLink
												action="#{ContractNotesBackingBean.load}"
												id="contractNotesID">
												<h:outputText id="labelNotes" value="Notes"></h:outputText>
											</h:commandLink></td>
											<td width="2" align="right"><img
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21" /></td>
										</TR>
									</TABLE>
									</TD>
									<TD width="14%">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21" /></td>
											<TD width="100%" class="tabNormal"><h:commandLink
												action="#{contractCommentBackingBean.loadComment}"
												id="linkToComment">
												<h:outputText id="commentsTabTable" value="Comments" />
											</h:commandLink></TD>
											<td width="2" align="right"><img
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21" /></td>
										</TR>
									</TABLE>
									</TD>
									<td width="14%" id="tabForStandard">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" alt="Tab LeftNormal"
												width="3" height="21" /></td>
											<td class="tabNormal"><h:commandLink
												action="#{contractProductAdminOptionBackingBean.displayContractAdminOption}"
												id="linkToAdminOption">
												<h:outputText id="LabelAdminOption" value="Admin Option"></h:outputText>
											</h:commandLink></td>
											<td width="2" align="right"><img
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21" /></td>
										</tr>
									</table>
									</td>
									<td width="14%">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" alt="Tab LeftNormal"
												width="3" height="21" /></td>
											<td class="tabNormal"><h:commandLink
												action="#{contractRuleBackingBean.displayRules}"
												id="linkToRules">
												<h:outputText id="rules" value="Rules"></h:outputText>
											</h:commandLink></td>
											<td width="2" align="right"><img
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21" /></td>
										</tr>
									</table>
									</td>
								</TR>
							</TABLE>
							<!-- End of Tab table -->

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
							<!-- ---------------------------------------- -->
							<table style="margin-top: 6px;">
								<tr>
									<td align="left" class="sectionheading"><h:outputText
										value="Basic Information"></h:outputText></td>
									<td>&nbsp;|&nbsp;</td>
									<td align="left"><h:commandLink
										action="#{contractSpecificInfoBackingBean.loadContractSpecificInfo}">
										<h:outputText value="Specific Information"></h:outputText>
									</h:commandLink></td>
									<td>&nbsp;|&nbsp;</td>
									<td align="left"><h:commandLink
										action="#{contractBasicInfoBackingBean.getMembershipInfo}">
										<h:outputText value="Membership Information"></h:outputText>
									</h:commandLink></td>
									<td>&nbsp;|&nbsp;</td>
									<td align="left"><h:commandLink
										action="#{contractSpecificInfoBackingBean.loadContractAdaptedInfo}">
										<h:outputText value="Adapted Information"></h:outputText>
									</h:commandLink></td>
								</tr>
							</table>
							<!-- ---------------------------------------- --> <!--	Start of Table for actual Data	-->
							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText">
								<TBODY>
									<TR>
										<TD colspan="4">
										<FIELDSET
											style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:450">
										<LEGEND> <FONT color="black">Business Domain</FONT></LEGEND>
										<TABLE border="0" cellspacing="5" cellpadding="3">
											<TR>
												<TD width="308"><h:outputText id="lineOfBusiness"
													value="Line Of Business" styleClass="mandatoryNormal" /></TD>
												<h:inputHidden id="lineOfBusinessHidden"
													value="#{contractBasicInfoBackingBean.lineOfBusinessDiv}"></h:inputHidden>
												<TD width="282">
												<div id="lineOfBusinessDiv" class="selectDivReadOnly"></div>

												</TD>
											</TR>
											<TR>
												<TD width="308"><h:outputText id="businessEntity"
													value="Business Entity" styleClass="mandatoryNormal" /></TD>
												<h:inputHidden id="businessEntityHidden"
													value="#{contractBasicInfoBackingBean.businessEntityDiv}"></h:inputHidden>
												<TD width="282">
												<div id="businessEntityDiv" class="selectDivReadOnly"></div>

												</TD>

											</TR>
											<TR>
												<TD width="308"><h:outputText id="businessGroup"
													value="Business Group" styleClass="mandatoryNormal" /></TD>
												<h:inputHidden id="businessGroupHidden"
													value="#{contractBasicInfoBackingBean.businessGroupDiv}"></h:inputHidden>
												<TD width="282">
												<div id="businessGroupDiv" class="selectDivReadOnly"></div>

												</TD>
											</TR>
<!-- ----------------------------------------------------------------------------------------------------------------- -->
											<TR>
												<TD width="308"><h:outputText id="marketBusinessUnit"
													value="Market Business Unit" styleClass="mandatoryNormal" /></TD>
												<h:inputHidden id="marketBusinessUnitHidden"
													value="#{contractBasicInfoBackingBean.marketBusinessUnit}"></h:inputHidden>
												<TD width="282">
												<div id="marketBusinessUnit" class="selectDivReadOnly"></div>

												</TD>
											</TR>
<!-- ------------------------------------------------------------------------------------------------------------------ -->
										</TABLE>
										</FIELDSET>
										</TD>
									</TR>

									<TR>

										<TD width="210">&nbsp;&nbsp;<h:outputText id="contractId"
											value="Contract ID" styleClass="mandatoryNormal" /></TD>
										<h:inputHidden id="contractIdHidden"
											value="#{contractBasicInfoBackingBean.contractIdDiv}"></h:inputHidden>
										<TD width="185"><h:outputText id="contractId_txt"
											value="#{contractBasicInfoBackingBean.contractIdDiv}" /></TD>

									</TR>







									<TR>
										<TD height="25" width="210">&nbsp;&nbsp;<h:outputText
											id="contractType" value="Contract Type "
											styleClass="mandatoryNormal" /></TD>
										<TD height="25" width="185"><h:outputText
											id="contractType_txt"
											value="#{contractBasicInfoBackingBean.contractType}" /> <h:inputHidden
											id="contractTypeHidden"
											value="#{contractBasicInfoBackingBean.contractType}">
										</h:inputHidden>
									</TR>
									<!-- Changes made for STANDARD Contract -->
									<TR id="baseContRowStandard" style="display:none;">

										<TD width="210">&nbsp;&nbsp;<h:outputText
											id="baseContractIdStandard" value="Base Contract Id"
											styleClass="mandatoryNormal" /></TD>

										<TD width="185"><h:outputText
											id="base_contractId_txt_Standard"
											value="#{contractBasicInfoBackingBean.baseContractIdDivNonMaditory}" />
										<h:inputHidden id="baseContractIdStandardHidden"
											value="#{contractBasicInfoBackingBean.baseContractIdDivNonMaditory}"></h:inputHidden>
										</TD>

									</TR>

									<TR id="baseContDtRowStandard" style="display:none;">

										<TD width="210">&nbsp;&nbsp;<h:outputText
											id="baseContractDtStandard"
											value="Base Contract Effective Date"
											styleClass="mandatoryNormal" /></TD>

										<TD width="185"><h:outputText
											id="base_contractDt_txt_Standard"
											value="#{contractBasicInfoBackingBean.baseContractDtDivNonMaditory}" />
										<h:inputHidden id="baseContractDtStandardHidden"
											value="#{contractBasicInfoBackingBean.baseContractDtDivNonMaditory}"></h:inputHidden>
										</TD>

									</TR>
									<!-- Changes made for STANDARD Contract -->
									<TR id="baseContRow" style="display:none;">

										<TD width="210">&nbsp;&nbsp;<h:outputText id="baseContractId"
											value="Base Contract Id" styleClass="mandatoryNormal" /></TD>

										<TD width="185"><h:outputText id="base_contractId_txt"
											value="#{contractBasicInfoBackingBean.baseContractIdDiv}" />
										<h:inputHidden id="baseContractIdHidden"
											value="#{contractBasicInfoBackingBean.baseContractIdDiv}"></h:inputHidden>
										</TD>

									</TR>

									<TR id="baseContDtRow" style="display:none;">

										<TD width="210">&nbsp;&nbsp;<h:outputText id="baseContractDt"
											value="Base Contract Effective Date"
											styleClass="mandatoryNormal" /></TD>

										<TD width="185"><h:outputText id="base_contractDt_txt"
											value="#{contractBasicInfoBackingBean.baseContractDtDiv}" />
										<h:inputHidden id="baseContractDtHidden"
											value="#{contractBasicInfoBackingBean.baseContractDtDiv}"></h:inputHidden>
										</TD>

									</TR>
									<TR id="headQuaterStateRow" style="display:none;">

										<TD width="210">&nbsp;&nbsp;<h:outputText id="head"
											value="Head Quarters State " /></TD>

										<TD width="185"><h:outputText id="head_txt"
											value="#{contractBasicInfoBackingBean.headQuartersState}" />
										<h:inputHidden id="headHidden"
											value="#{contractBasicInfoBackingBean.headQuartersState}"></h:inputHidden>
										</TD>

									</TR>
									<TR>

										<TD height="25" width="210">&nbsp;&nbsp;<h:outputText
											id="groupSizeTxt" value="Group Size "
											styleClass="mandatoryNormal" /></TD>
										<h:inputHidden id="groupSizeHidden"
											value="#{contractBasicInfoBackingBean.groupSizeDiv}"></h:inputHidden>
										<TD width="185"><h:outputText id="groupSize_txt" value="" /></TD>

									</TR>
											<tr>
												<TD height="25" width="210">&nbsp;&nbsp;<h:outputText value="Termed Contract ID"/></td>
												<td>
													<h:outputText value="#{contractBasicInfoBackingBean.termedContractId}"/>
												</td>
												<td></td>
											</tr>
											<tr>
												<td height="25" width="210">&nbsp;&nbsp;<h:outputText value="Archival Status"/></td>
												<td>
													<h:outputText value="#{contractBasicInfoBackingBean.contractStatus}"/>
													<h:inputHidden id="statusCodeHidden"
											value="#{contractBasicInfoBackingBean.contractStatus}"/>
												</td>
												<td></td>
											</tr>
											<tr id="contractStatusDateRow">
												<td height="25" width="210">&nbsp;&nbsp;<h:outputText value="Archival Status Date"/></td>
												<td><h:outputText value="#{contractBasicInfoBackingBean.contractStatusDate}"/> 
											</td>
												<td valign="top"></td>
											</tr>
											<tr id="contractStatusReasonRow">
												<td height="25" width="210">&nbsp;&nbsp;<h:outputText value="Reason Code"/></td>
												<TD width="185">
													<h:outputText value="#{contractBasicInfoBackingBean.contractStatusReasonCodeDesc}"/>
												</TD>
												<TD width="288"></TD>
											</tr>

									<TR valign="top">

										<TD width="210">&nbsp;&nbsp;<h:outputText
											value="Effective Date" styleClass="mandatoryNormal" /></TD>
										<TD width="185"><h:outputText id="effectiveDate_txt"
											value="#{contractBasicInfoBackingBean.startDate}" /> <h:inputHidden
											id="effectiveDateHidden"
											value="#{contractBasicInfoBackingBean.startDate}">
										</h:inputHidden>
									</TR>
									<TR valign="top">

										<TD width="210">&nbsp;&nbsp;<h:outputText value="Expiry Date"
											styleClass="mandatoryNormal" /></TD>
										<TD width="185"><h:outputText id="expiryDate_txt"
											value="#{contractBasicInfoBackingBean.endDate}" /> <h:inputHidden
											id="expiryDateHidden"
											value="#{contractBasicInfoBackingBean.endDate}">
										</h:inputHidden>
									</TR>
									<tr>
										<td valign="top" width="210"><span class="mandatoryNormal"
											id="createdBy">&nbsp;</span> <h:outputText value="Created By" /></td>
										<td width="185"><h:outputText
											value="#{contractBasicInfoBackingBean.createdUser}" /> <h:inputHidden
											id="createdUserHidden"
											value="#{contractBasicInfoBackingBean.createdUser}">
										</h:inputHidden></td>
										<TD width="288"></TD>
									</tr>
									<tr>
										<td valign="top" width="210"><span class="mandatoryNormal"
											id="creationDate"></span> &nbsp;&nbsp;<h:outputText
											value="Created Date" /></td>
										<td width="185"><h:outputText
											value="#{contractBasicInfoBackingBean.creationDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
										<h:inputHidden id="creationDateHidden"
											value="#{contractBasicInfoBackingBean.creationDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:inputHidden> <h:inputHidden id="time"
											value="#{requestScope.timezone}">
										</h:inputHidden></td>
										<TD width="288"></TD>

									</tr>
									<tr>
										<td valign="top" width="210"><span class="mandatoryNormal"
											id="createdBy"></span>&nbsp;&nbsp;<h:outputText
											value="Last Updated By" /></td>
										<td width="185"><h:outputText
											value="#{contractBasicInfoBackingBean.updatedUser}" /> <h:inputHidden
											id="updatedUserHidden"
											value="#{contractBasicInfoBackingBean.updatedUser}">
										</h:inputHidden></td>
										<TD width="288"></TD>
									</tr>
									<tr>
										<td valign="top" width="210"><span class="mandatoryNormal"
											id="updationDate"></span> &nbsp;&nbsp;<h:outputText
											value="Last Updated Date" /></td>
										<td width="185"><h:outputText
											value="#{contractBasicInfoBackingBean.updationDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
										<h:inputHidden id="updationDateHidden"
											value="#{contractBasicInfoBackingBean.updationDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:inputHidden></td>
										<TD width="288"></TD>

									</tr>


								</TBODY>
							</TABLE>
							<!--	End of Page data	--></FIELDSET>

							<BR>

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
							<table border="0" cellspacing="0" cellpadding="3" width="100%">
								<tr>
									<td align="left" colspan="1"><a href="#"
										onclick=" showPopupForContractTransferLog();return false;"><h:outputText
										value="Transfer Log" styleClass="variableLink" /></a>
									</td>	
								<!-- Transfer Log -->						
									<td align="right" width="127" rowspan="2">
										<table Width=100%>
											<tr>
												<td><h:outputText value="State" /></td>
												<td>:<h:outputText
													value="#{contractBasicInfoBackingBean.state}" /></td>
												<h:inputHidden id="stateHidden"
													value="#{contractBasicInfoBackingBean.state}" />
											</tr>
											<tr>
												<td><h:outputText value="Status" /></td>
												<td>:<h:outputText
													value="#{contractBasicInfoBackingBean.status}" /></td>
												<h:inputHidden id="statusHidden"
													value="#{contractBasicInfoBackingBean.status}" />
											</tr>
											<tr>
												<td><h:outputText value="Version" /></td>
												<td>:<h:outputText
													value="#{contractBasicInfoBackingBean.version}" /></td>
												<h:inputHidden id="versionHidden"
													value="#{contractBasicInfoBackingBean.version}" />
	
											</tr>
										</table>
									</td>
								</tr>							

							</table>
							</FIELDSET>

							</TD>
						</TR>
					</TABLE>
					<h:inputHidden id="hiddenProductType"
						value="#{contractBenefitGeneralInfoBackingBean.pageLoadBasedOnContractType}"></h:inputHidden>

				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom_view.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>

<script>
var baseContractIdStandard = document.getElementById('contractForm:baseContractIdStandardHidden').value;
	// Space for page realated scripts
 copyHiddenToDiv_ewpd('contractForm:lineOfBusinessHidden','lineOfBusinessDiv',2,2); 
 copyHiddenToDiv_ewpd('contractForm:businessEntityHidden','businessEntityDiv',2,2); 
 copyHiddenToDiv_ewpd('contractForm:businessGroupHidden','businessGroupDiv',2,2); 
 copyHiddenToDiv_ewpd('contractForm:groupSizeHidden','contractForm:groupSize_txt',2,2);  
 copyHiddenToDiv_ewpd('contractForm:marketBusinessUnitHidden','marketBusinessUnit',2,2); 


var selIndex = document.getElementById('contractForm:contractTypeHidden');
   if(selIndex.value != 'STANDARD') {                           // unhide if the selected value is 'STANDARD'
            baseContRowStandard.style.display='none';
			baseContDtRowStandard.style.display='none';
    }  else{  
			if(baseContractIdStandard != ''){
				baseContRowStandard.style.display='';
				baseContDtRowStandard.style.display='';
			}
    }
  if(selIndex.value != 'CUSTOM'){                           
            baseContRow.style.display='none';
			baseContDtRow.style.display ='none';
	}
      else {
            baseContRow.style.display='';
			baseContDtRow.style.display='';
	}
	 if(selIndex.value != 'MANDATE'){  
            headQuaterStateRow.style.display='none';
	}
      else {
            headQuaterStateRow.style.display='';
	}
    
i = document.getElementById("contractForm:hiddenProductType").value;
	if(i=='MANDATE')
	{
	tabForStandard.style.display='none';
	tabForStandard1.style.display='none';
	}else{
	tabForStandard.style.display='';
	tabForStandard1.style.display='';
	}

function showPopupForContractTransferLog(){		
	var url='../contract/contractViewTransferLog.jsp'+getUrl()+'?temp='+Math.random();
	var returnvalue=window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;");
	if(returnvalue == undefined)
	{
//		getObj('ContractBasicSearchForm:searchButton').click();
	}
}
 function hideReasonCode()
{
  	if(document.getElementById('contractForm:statusCodeHidden').value == 'ACTIVE' || document.getElementById('contractForm:statusCodeHidden').value == '') {                           
            document.getElementById("contractStatusDateRow").style.display='none';
			 document.getElementById("contractStatusReasonRow").style.display='none';
    }  else{ 
			document.getElementById("contractStatusDateRow").style.display='block';
			 document.getElementById("contractStatusReasonRow").style.display='block';
    }
}

hideReasonCode();
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="contractBasicInfo" /></form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>

