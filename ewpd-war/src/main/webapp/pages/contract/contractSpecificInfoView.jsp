<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" title="Style">

<TITLE>WellPoint Product Database: Specific Information</TITLE>
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
<BODY onkeypress="return submitOnEnterKey('SpecificInfoForm:saveButton');">
<hx:scriptCollector id="scriptCollector1">
	<TABLE width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD>
				<jsp:include page="../navigation/top_view.jsp"></jsp:include>
			</TD>
		</TR>
		<TR>
			<TD>
				<h:form styleClass="form" id="SpecificInfoForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>
	                		<TD valign="top" class="leftPanel" width="273">
								
<!-- Space for Tree  Data	-->		<DIV class="treeDiv">	
<jsp:include page="contractTree.jsp"></jsp:include>	
						 		</DIV>		
	
									

							</TD>

	   						<TD colspan="2" valign="top" class="ContentArea" >
								<TABLE>
									<TBODY>
										<TR>
											<TD>
											</TD>
										</TR>	
										
									</TBODY>
								</TABLE>

<!-- Table containing Tabs -->
								<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" id="TabTable">
									<TR>

										<TD width="16%">
		          							<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
		              							<TR>
				                					<TD width="2" align="left"><IMG src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
													<TD width= "100%" class="tabActive"> <h:outputText value=" General Information" /> </TD> 
				                					<TD width="2" align="right"><IMG src="../../images/activeTabRight.gif" width="2" height="21"></TD>
		              							</TR>
		          							</TABLE>
		          						</TD>
					          			<!-- TD width="18%">
		          							<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
		              							<TR>
				                					<TD width="2" align="left"><IMG src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
													<TD width="100%" class="tabActive"> <h:outputText value="Specific Information" /> </TD> 
				                					<TD width="2" align="right"><IMG src="../../images/activeTabRight.gif" width="2" height="21"></TD>
		              							</TR>
		          							</TABLE>
		          						</TD-->
										<TD width="18%">
											<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
												<TR>
													<TD width="3" align="left"><IMG src="../../images/tabNormalLeft.gif" alt="Tab Left Normal" width="3" height="21"></TD>
													<TD width="100%" class="tabNormal"> 
														<h:commandLink action="#{contractPricingInfoBackingBean.dispalyContractPricingInfoTab}"> <h:outputText value="Pricing Information" /> </h:commandLink> 
													</TD>
													<TD width="2" align="right"><IMG src="../../images/tabNormalRight.gif" alt="Tab Right Normal" width="4" height="21"></TD>
												</TR>
											</TABLE>
										</TD>

										<TD width="10%"  id="tabForStandard1">
											<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
												<TR>
													<TD width="3" align="left"><IMG src="../../images/tabNormalLeft.gif" alt="Tab Left Normal" width="3" height="21"></TD>
													<td class="tabNormal"><h:commandLink
														action="#{ContractNotesBackingBean.load}"
														id="contractNotesID">
														<h:outputText id="labelNotes" value="Notes"></h:outputText>
													</h:commandLink></td>
													<TD width="2" align="right"><IMG src="../../images/tabNormalRight.gif" alt="Tab Right Normal" width="4" height="21"></TD>
												</TR>
											</TABLE>
										</TD>

										<TD width="14%">
											<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
												<TR>
													<TD width="3" align="left"><IMG src="../../images/tabNormalLeft.gif" alt="Tab Left Normal" width="3" height="21"></TD>
													<TD width="100%" class="tabNormal"> 
														<h:commandLink action="#{contractCommentBackingBean.loadComment}" id="linkToComment"><h:outputText value="Comments" /> </h:commandLink> 
													</TD>
													<TD width="2" align="right"><IMG src="../../images/tabNormalRight.gif" alt="Tab Right Normal" width="4" height="21"></TD>
												</TR>
											</TABLE>
										</TD>
							<td width="14%"  id="tabForStandard">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="3" align="left"><img
										src="../../images/tabNormalLeft.gif" alt="Tab LeftNormal"
										width="3" height="21" /></td>
									<td class="tabNormal"><h:commandLink action="#{contractProductAdminOptionBackingBean.displayContractAdminOption}" id="linkToAdminOption">
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
									<td class="tabNormal"><h:commandLink action="#{contractRuleBackingBean.displayRules}" id="linkToRules">
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
								
								<FIELDSET style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:20px;width:100%">
							<!-- ---------------------------------------- -->		
							<table style="margin-top: 6px;">
										<tr>
											<td align="left" >
												<h:commandLink action="#{contractBasicInfoBackingBean.getBasicInfo}">
													<h:outputText value="Basic Information"></h:outputText>
												</h:commandLink>  
											</td>
											<td>
												 &nbsp;|&nbsp;
											</td>
											<td align="left" class="sectionheading">														
												<h:outputText value="Specific Information"></h:outputText>
											</td>
											<td>
												 &nbsp;|&nbsp;
											</td>
											<td align="left">
												<h:commandLink action="#{contractBasicInfoBackingBean.getMembershipInfo}">														
												<h:outputText value="Membership Information"></h:outputText>
												</h:commandLink>  
											</td>
											<td>&nbsp;|&nbsp;</td>
											<td align="left"><h:commandLink
												action="#{contractSpecificInfoBackingBean.loadContractAdaptedInfo}">
											<h:outputText value="Adapted Information"></h:outputText>
											</h:commandLink></td>

										</tr>
							</table><br>
							<!-- ---------------------------------------- -->									
<!--	Start of Table for actual Data	-->		
								<TABLE border="0" cellspacing="0" cellpadding="3" class="outputText">

										<tr></tr>
										
										<TR>
											<TD valign="top" width="30%">
												<h:outputText id="productCode" value="Product Code " styleClass="#{contractSpecificInfoBackingBean.requiredProductCode ? 'mandatoryError': 'mandatoryNormal'}">
												</h:outputText>
											</TD>
											<TD width="35%">
												<h:outputText id="productCode_optxt" value="" />
												<h:inputHidden id="txtProductCode" value="#{contractSpecificInfoBackingBean.productCode}"></h:inputHidden>
											</TD>		
										</TR>
									
										<TR>
											<TD valign="top" width="30%">
												<h:outputText id="productCodeDesc" value="Product Code Description" >
												</h:outputText>
											</TD>
											<TD width="35%">
												<h:outputText id="productCodeDesc_optxtDesc" value="#{contractSpecificInfoBackingBean.productCodeDesc}" />
											
											</TD>		
										</TR>
										<TR>
											<TD valign="top" width="30%">
												<h:outputText id="standardPlanCode" value="Standard Plan Code" >
												</h:outputText>
											</TD>
											<TD width="35%">
												<h:outputText id="standardPlanCode_optxt" value="" />
												<h:inputHidden id="txtStdPlanCode" value="#{contractSpecificInfoBackingBean.standardPlanCode}"></h:inputHidden>
											</TD>
										</TR>
											
										<TR>
											<TD valign="top" width="30%">
												<h:outputText id="benefitPlan" value="Benefit Plan " styleClass="#{contractSpecificInfoBackingBean.requiredBenefitPlan ? 'mandatoryError': 'mandatoryNormal'}" >
												</h:outputText>
											</TD>
											<TD width="35%">
												<h:outputText id="benefitPlan_optxt" value="" />
												<h:inputHidden id="txtBenefitPlan"  value="#{contractSpecificInfoBackingBean.benefitPlan}"/> 
											</TD>
											<TD width="48%">
					
											</TD>
		
										</TR>
											<TR>
											<TD valign="top" width="30%">
												<h:outputText id="product" value="Product " styleClass="#{contractSpecificInfoBackingBean.requiredProduct ? 'mandatoryError': 'mandatoryNormal'}">
												</h:outputText>
											</TD>
											<TD width="35%">
												<h:outputText id="product_optxt" value="" />
												<h:inputHidden id="txtProduct"	value="#{contractSpecificInfoBackingBean.product}"></h:inputHidden>
											</TD>		
										</TR>
									
									
										<TR>
											<TD valign="top" width="30%">
												<h:outputText id="productFamily" value="Product Family" >
												</h:outputText>
											</TD>
											<TD width="35%">
												<h:outputText id="txtProductFamily" value=""></h:outputText>
												<h:inputHidden id="txtProductFamilyHidden" value="#{contractSpecificInfoBackingBean.productFamily}"></h:inputHidden>
											</TD>
										</TR>
										<TR>
											<TD valign="top" width="30%">
												<h:outputText id="corporatePlanCode" value="Corporate Plan Code">
												</h:outputText>
											</TD>
											<TD width="35%">
												<h:outputText id="corporatePlanCode_optxt" value="" />
												<h:inputHidden id="txtCorporatePlanCode" value="#{contractSpecificInfoBackingBean.corporatePlanCode}"></h:inputHidden>
											</TD>			
		
										</TR>

										
										<TR>
											<TD valign="top" width="30%">
												<h:outputText id="brandName" value="Brand Name">
												</h:outputText>
											</TD>
											<TD width="35%">	
												<h:outputText id="brandName_optxt" value="" />
												<h:inputHidden id="txtBrandName" value="#{contractSpecificInfoBackingBean.brandName}" /> 
											</TD>
							
										</TR>

									<TR>
											<TD valign="top" width="30%">
												<h:outputText id="specCode" value="Related Provider Specialty Code">
												</h:outputText>
											</TD>
											<TD width="35%">	
												<DIV id="provSpecCodeDiv" class="selectDataDisplayDiv"></DIV>
												<h:inputHidden id="txtspecCode" value="#{contractSpecificInfoBackingBean.provSpecCode}" /> 
											</TD>
											
										</TR>
										<TR>
											<TD valign="top" width="30%">
												<h:outputText id="cobIndicator" value="COB Adjudication Indicator">
												</h:outputText>
											</TD>
											<TD width="35%">	
												<h:outputText id="cobIndicator_optxt" value="" />
												<h:inputHidden id="txtCobIndicator" value="#{contractSpecificInfoBackingBean.cobAdjudicationIndicator}" /> 
											</TD>
													
										</TR>
										<TR>
											<TD valign="top" width="30%">
												<h:outputText id="medIndicator" value="Medicare Adjudication Indicator">
												</h:outputText>
											</TD>
											<TD width="35%">	
												<h:outputText id="medIndicator_optxt" value="" />
												<h:inputHidden id="txtMedIndicator" value="#{contractSpecificInfoBackingBean.medicareAdjudicationIndicator}" /> 
											</TD>
												
										</TR>
										<TR>
											<TD valign="top" width="30%">
												<h:outputText id="itsIndicator" value="ITS Adjudication Indicator">
												</h:outputText>
											</TD>
											<TD width="35%">	
												<h:outputText id="itsIndicator_optxt" value="" />
												<h:inputHidden id="txtItsIndicator" value="#{contractSpecificInfoBackingBean.itsAdjudicationIndicator}" /> 
											</TD>
													
										</TR>
										

									<tr>
									</tr>
									
									<tr>
									</tr>

            					</TABLE>
							</FIELDSET>	
						<br>

								<FIELDSET style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
									<table  border="0" cellspacing="0" cellpadding="3" width ="100%">
										<tr>
											<td align="left"><a href="#" onclick=" showPopupForContractTransferLog();return false;"><h:outputText value="Transfer Log" styleClass="variableLink" /></a></td>
											<td  align="left" width="127">
												<table Width = 100%>
													<tr>
														<td><h:outputText value="State"/></td>
														<td>:<h:outputText value="#{contractSpecificInfoBackingBean.state}"/></td>
													</tr>
													<tr>
														<td><h:outputText value="Status" /></td>
														<td>:<h:outputText value="#{contractSpecificInfoBackingBean.status}" /></td>
													</tr>
													<tr>
														<td><h:outputText value="Version" /></td>
														<td>:<h:outputText value="#{contractSpecificInfoBackingBean.version}" /></td>
													</tr>
												</table>
											</td>
										</tr>
										
											<!-- Transfer Log -->
										
										
									</table>
								</FIELDSET>	
<br>

<br><br>
<!--	End of Page data	-->							

					
<!-- Space for hidden fields -->
				
<!-- End of hidden fields  -->
							</TD>
						</TR>
					</TABLE>
			
			
			<h:inputHidden id="groupSize" value="#{contractSpecificInfoBackingBean.groupSize}"></h:inputHidden>
			<h:inputHidden id="lineOfBusiness" value="#{contractSpecificInfoBackingBean.lineOfBusiness}"></h:inputHidden>
			<h:inputHidden id="businessGroup" value="#{contractSpecificInfoBackingBean.businessGroup}"></h:inputHidden>
			<h:inputHidden id="businessEntity" value="#{contractSpecificInfoBackingBean.businessEntity}"></h:inputHidden>	
			<h:inputHidden id="effectiveDate" value="#{contractSpecificInfoBackingBean.effectiveDate}"></h:inputHidden>
			<h:inputHidden id="expiryDate" value="#{contractSpecificInfoBackingBean.expiryDate}"></h:inputHidden>
<h:inputHidden id="hiddenProductType"
					value="#{contractBenefitGeneralInfoBackingBean.pageLoadBasedOnContractType}"></h:inputHidden>

			</h:form>
			</TD>
		</TR>
		<TR>
			<TD>
				<%@include file="../navigation/bottom_view.jsp"%>
			</TD>
		</TR>
	</TABLE>
</hx:scriptCollector></BODY>
</f:view>
<script>
	copyHiddenToDiv_ewpd('SpecificInfoForm:txtProductCode','SpecificInfoForm:productCode_optxt',2,2); 

	copyHiddenToDiv_ewpd('SpecificInfoForm:txtStdPlanCode','SpecificInfoForm:standardPlanCode_optxt',2,2); 
	copyHiddenToDiv_ewpd('SpecificInfoForm:txtBenefitPlan','SpecificInfoForm:benefitPlan_optxt',2,1); 
	copyHiddenToDiv_ewpd('SpecificInfoForm:txtProduct','SpecificInfoForm:product_optxt',2,2); 
	copyHiddenToDiv_ewpd('SpecificInfoForm:txtProductFamilyHidden','SpecificInfoForm:txtProductFamily',2,1); 
	copyHiddenToDiv_ewpd('SpecificInfoForm:txtCorporatePlanCode','SpecificInfoForm:corporatePlanCode_optxt',2,2); 
	copyHiddenToDiv_ewpd('SpecificInfoForm:txtBrandName','SpecificInfoForm:brandName_optxt',2,1);
///	copyHiddenToDiv_ewpd_show_code_desc('SpecificInfoForm:txtspecCode','SpecificInfoForm:specCode_id',2,2,",",'view');
copyHiddenToDiv_ewpd_show_code_desc('SpecificInfoForm:txtspecCode','provSpecCodeDiv',2,2,'view'); 
	copyHiddenToDiv_ewpd('SpecificInfoForm:txtCobIndicator','SpecificInfoForm:cobIndicator_optxt',2,1);
	copyHiddenToDiv_ewpd('SpecificInfoForm:txtMedIndicator','SpecificInfoForm:medIndicator_optxt',2,1);
	copyHiddenToDiv_ewpd('SpecificInfoForm:txtItsIndicator','SpecificInfoForm:itsIndicator_optxt',2,1);
	i = document.getElementById("SpecificInfoForm:hiddenProductType").value;
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
<form name="printForm">
<input id="currentPrintPage" type="hidden" name="currentPrintPage" value="contractSpecificInfo" />
</form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>

