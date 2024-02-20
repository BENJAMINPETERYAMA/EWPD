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

<TITLE>Contract Adapted Information</TITLE>
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
<script language="JavaScript" src="../../js/CalendarPopup.js" ></script>
<script language="JavaScript" src="../../js/PopupWindow.js" ></script>
<script language="JavaScript" src="../../js/date.js" ></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript">
	 var cal1 = new CalendarPopup();
	 cal1.showYearNavigation();

</script>
<BASE target="_self" />
</HEAD>
<f:view>
	<BODY
		onkeypress="return submitOnEnterKey('AdaptedInfoForm:saveButton');">
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><jsp:include page="../navigation/top_view.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="AdaptedInfoForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>
							<TD valign="top" class="leftPanel" width="273"><!-- Space for Tree  Data	-->
							<DIV class="treeDiv"><jsp:include page="contractTree.jsp"></jsp:include>
							</DIV>



							</TD>

							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message></w:message></TD>
									</TR>

								</TBODY>
							</TABLE>

							<!-- Table containing Tabs -->
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<TR>

									<TD width="18%">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
											<TD width="100%" class="tabActive" nowrap="nowrap"><h:outputText
												value=" General Information" /></TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>

									<TD width="18%">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD width="100%" class="tabNormal"><h:commandLink
												action="#{contractPricingInfoBackingBean.dispalyContractPricingInfoTab}"
												id ="priceId" onmousedown="javascript:navigatePageAction(this.id);">
												<h:outputText value="Pricing Information" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>

									<TD width="14%"  id="tabForStandard1">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD width="100%" class="tabNormal"><h:commandLink
												action="#{ContractNotesBackingBean.load}"
												id ="noteId" onmousedown="javascript:navigatePageAction(this.id);">
												<h:outputText value="Notes" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>

									<TD width="16%">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD width="100%" class="tabNormal"><h:commandLink
												action="#{contractCommentBackingBean.loadComment}"
												id="linkToComment" onmousedown="javascript:navigatePageAction(this.id);">
												<h:outputText value="Comments" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<td width="16%"  id="tabForStandard">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" alt="Tab LeftNormal"
												width="3" height="21" /></td>
											<td class="tabNormal"><h:commandLink
												action="#{contractProductAdminOptionBackingBean.displayContractAdminOption}"
												id="linkToAdminOption" onmousedown="javascript:navigatePageAction(this.id);">
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
												id="linkToRules" onmousedown="javascript:navigatePageAction(this.id);">
												<h:outputText id="Rule" value="Rules"></h:outputText>
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
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:20px;width:100%">
							<table style="margin-top: 6px;">
								<tr>
									<td align="left"><h:commandLink
										action="#{contractBasicInfoBackingBean.getBasicInfo}"
										id="genId" onmousedown="javascript:navigatePageAction(this.id);">
										<h:outputText value="Basic Information"></h:outputText>
									</h:commandLink></td>
									<td>&nbsp;|&nbsp;</td>
									<td><h:commandLink
										action="#{contractSpecificInfoBackingBean.loadContractSpecificInfo}"
										id="specId" >
										<h:outputText value="Specific Information"></h:outputText>
									</h:commandLink></td>
									<td>&nbsp;|&nbsp;</td>
									<td><h:commandLink
										action="#{contractBasicInfoBackingBean.getMembershipInfo}"
										id="membId" onmousedown="javascript:navigatePageAction(this.id);">
										<h:outputText value="Membership Information">
											</h::outputText>
									</h:commandLink></td>
									<td>&nbsp;|&nbsp;</td>
									<td align="left" class="sectionheading"><h:outputText
										value="Adapted Information"></h:outputText>
									</td>
								</tr>
							</table>

							<BR />
							<!--	Start of Table for actual Data	-->
							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText">

								<tr></tr>

								<TR>
									<TD valign="top" width="227"><h:outputText id="regulatoryAgency"
										value="Regulatory Agency"></h:outputText>
									</TD>
									<TD width="26%">
									<h:outputText id="regulatoryAgency_optxt" value=""></h:outputText>
									<h:inputHidden id="txtRegulatoryAgency"
										value="#{contractSpecificInfoBackingBean.regulatoryAgency}"></h:inputHidden>
									</TD>
									<TD width="48%">
									</TD>
								</TR>

								<TR>
									<TD valign="top" width="227"><h:outputText id="complianceStatus"
										value="Compliance Status"></h:outputText>
									</TD>
									<TD width="26%">
									<h:outputText id="complianceStatus_optxt" value=""></h:outputText>
									<h:inputHidden id="txtComplianceStatus"
										value="#{contractSpecificInfoBackingBean.complianceStatusDesc}"></h:inputHidden>
									</TD>
								</TR>

								<TR>
									<TD valign="top" width="227"><h:outputText
										id="nameCode" value="Product/Project Name Code">
									</h:outputText></TD>
									<TD width="26%">
									<h:outputText id="nameCode_optxt" value=""></h:outputText>
									<h:inputHidden id="txtNameCode"
										value="#{contractSpecificInfoBackingBean.prodProjNameCode}"></h:inputHidden>
									</TD>
									<TD width="48%">
									</TD>
								</TR>

								<TR>																		  		
									<TD valign="top" width="227">
										<h:outputText value="Contract Term Date"  styleClass="#{contractBasicInfoBackingBean.startDateInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<TD width="26%">
									<h:outputText id="contractStartDate_optxt" value=""></h:outputText>
									<h:inputHidden id="txtContractStartDate" 
										value="#{contractSpecificInfoBackingBean.contractTermDate}"/> 
									</TD>
								</TR>

								<TR>
									<TD width="227"><h:outputText id="multiPlanIndicator"
										value="Multi Plan Indicator"></h:outputText>
									</TD>
									<TD width="26%">
									<h:outputText id="multiPlan_optxt" value=""></h:outputText>
									<h:inputHidden id="txtMultiPlan" 
										value="#{contractSpecificInfoBackingBean.multiplanIndicator}"/> 
									</TD>
								</TR>

								<TR>
									<TD width="227"><h:outputText id="perfGuarantee"
										value="Performance Guarantee"></h:outputText>
									</TD>
									<TD width="26%">
									<h:outputText id="perfGuarantee_optxt" value=""></h:outputText>
									<h:inputHidden id="txtPerfGuarantee" 
										value="#{contractSpecificInfoBackingBean.performanceGuarantee}"/> 
									</TD>
								</TR>

								<TR>																		  		
									<TD valign="top" width="227">
										<h:outputText value="Sales Market Date"  styleClass="#{contractBasicInfoBackingBean.startDateInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<TD width="26%">
									<h:outputText id="salesMarketDate_optxt" value=""></h:outputText>
									<h:inputHidden id="txtsalesMarketDate"
										value="#{contractSpecificInfoBackingBean.salesMarketDate}"/> 
									</TD>
								</TR>

						

								<tr>
								</tr>

								<tr>
								</tr>

							</TABLE>
							</FIELDSET>
							<br>

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
							<table border="0" cellspacing="0" cellpadding="3" width="100%">
								<tr>
									<td align="left"><a href="#" onclick=" showPopupForContractTransferLog();return false;"><h:outputText value="Transfer Log" styleClass="variableLink" /></a></td>
									<td align="left" width="127">
									<table Width=100%>
										<tr>
											<td><h:outputText value="State" /></td>
											<td>:<h:outputText
												value="#{contractSpecificInfoBackingBean.state}" /></td>
										</tr>
										<tr>
											<td><h:outputText value="Status" /></td>
											<td>:<h:outputText
												value="#{contractSpecificInfoBackingBean.status}" /></td>
										</tr>
										<tr>
											<td><h:outputText value="Version" /></td>
											<td>:<h:outputText
												value="#{contractSpecificInfoBackingBean.version}" /></td>
										</tr>
									</table>
									</td>
								</tr>
								<!-- Transfer Log -->
										
							</table>
							</FIELDSET>
							<br>
							

							<br>
							<br>
							<!--	End of Page data	--> <!-- Space for hidden fields --> <!-- End of hidden fields  -->
							</TD>
						</TR>
					</TABLE>

					<h:inputHidden id="contractIDForRefData"
						value="#{contractSpecificInfoBackingBean.contractIdForRefData}"></h:inputHidden>
					<h:inputHidden id="groupSize"
						value="#{contractSpecificInfoBackingBean.groupSize}"></h:inputHidden>
					<h:inputHidden id="lineOfBusiness"
						value="#{contractSpecificInfoBackingBean.lineOfBusiness}"></h:inputHidden>
					<h:inputHidden id="businessGroup"
						value="#{contractSpecificInfoBackingBean.businessGroup}"></h:inputHidden>
					<h:inputHidden id="businessEntity"
						value="#{contractSpecificInfoBackingBean.businessEntity}"></h:inputHidden>
					<h:inputHidden id="effectiveDate"
						value="#{contractSpecificInfoBackingBean.effectiveDate}"></h:inputHidden>
					<h:inputHidden id="expiryDate"
						value="#{contractSpecificInfoBackingBean.expiryDate}"></h:inputHidden>
					<h:inputHidden id="hiddenProduct"
						value="#{contractSpecificInfoBackingBean.hiddenProduct}"></h:inputHidden>
					<h:inputHidden id="dateSegmentType"
						value="#{contractSpecificInfoBackingBean.dateSegmentType}"></h:inputHidden>
					
					<h:inputHidden id="custom"
						value="#{contractSpecificInfoBackingBean.custom}"></h:inputHidden>
					<h:inputHidden id="hiddenProductType"
						value="#{contractBenefitGeneralInfoBackingBean.pageLoadBasedOnContractType}"></h:inputHidden>
					
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
	copyHiddenToDiv_ewpd('AdaptedInfoForm:txtRegulatoryAgency','AdaptedInfoForm:regulatoryAgency_optxt',2,1); 
	copyHiddenToDiv_ewpd('AdaptedInfoForm:txtComplianceStatus','AdaptedInfoForm:complianceStatus_optxt',2,1); 
	copyHiddenToDiv_ewpd('AdaptedInfoForm:txtNameCode','AdaptedInfoForm:nameCode_optxt',2,2); 
	copyHiddenToDiv_ewpd('AdaptedInfoForm:txtContractStartDate','AdaptedInfoForm:contractStartDate_optxt',2,1); 
	copyHiddenToDiv_ewpd('AdaptedInfoForm:txtMultiPlan','AdaptedInfoForm:multiPlan_optxt',2,1); 
	copyHiddenToDiv_ewpd('AdaptedInfoForm:txtPerfGuarantee','AdaptedInfoForm:perfGuarantee_optxt',2,1); 
	copyHiddenToDiv_ewpd('AdaptedInfoForm:txtsalesMarketDate','AdaptedInfoForm:salesMarketDate_optxt',2,1); 

i = document.getElementById("AdaptedInfoForm:hiddenProductType").value;
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
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="contractAdaptedInfo" /></form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>

