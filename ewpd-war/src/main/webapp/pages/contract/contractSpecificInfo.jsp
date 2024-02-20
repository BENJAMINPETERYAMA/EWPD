<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ taglib uri="/WEB-INF/RapidSpellWeb.tld" prefix="RapidSpellWeb"%>
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

<TITLE>Contract Specific Information</TITLE>
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
<script type="text/javascript">
function onPageLoadPopup(){
 	var ebxFlag = document.getElementById('SpecificInfoForm:hidd_webServiceFlag').value;
 	var vendorFlag = document.getElementById('SpecificInfoForm:hidd_VendorFlag').value;
   	if(vendorFlag == "true" && ebxFlag == "true"){ 
   		var url = '../contract/contractErrorValidationsPopup.jsp' + getUrl() + '?temp=' + Math.random();		
		document.getElementById('SpecificInfoForm:hidd_webServiceFlag').value = "false";
		document.getElementById('SpecificInfoForm:hidd_VendorFlag').value = "false";
		newWinForView = window.open(url,'Validations','height=600,width=1000,left=100,top=100,resizable=no,scrollbars=no,toolbar=no,menubar=no,location=no,directories=no, status=no');
     }else if(vendorFlag == "true" && ebxFlag == "false"){
     	var url = '../contract/contractErrorValidationsPopup.jsp' + getUrl() + '?temp=' + Math.random();
     	document.getElementById('SpecificInfoForm:hidd_webServiceFlag').value = "false";
		document.getElementById('SpecificInfoForm:hidd_VendorFlag').value = "false";
		newWinForView = window.open(url,'Validations','height=600,width=1000,left=100,top=100,resizable=no,scrollbars=no,toolbar=no,menubar=no,location=no,directories=no, status=no');
     }else if(vendorFlag == "false" && ebxFlag == "true"){
     	var url = '../popups/ebxValidationsPopup.jsp'+ getUrl() + '?temp=' + Math.random();
     	document.getElementById('SpecificInfoForm:hidd_webServiceFlag').value = "false";
		document.getElementById('SpecificInfoForm:hidd_VendorFlag').value = "false";
     	newWinForView = window.open(url,'Validations','height=600,width=1000,left=100,top=100,resizable=no,scrollbars=no,toolbar=no,menubar=no,location=no,directories=no, status=no');
     }

}  

</script>
</HEAD>
<f:view>
	<BODY
		onkeypress="return submitOnEnterKey('SpecificInfoForm:saveButton');">
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="SpecificInfoForm">
					<h:inputHidden id="hidd_webServiceFlag" value="#{contractSpecificInfoBackingBean.webServiceFlag}"/>
					<h:inputHidden id="hidd_VendorFlag" value="#{contractSpecificInfoBackingBean.vendorFlag}"/>
				
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>
							<TD valign="top" class="leftPanel" width="273"><!-- Space for Tree  Data	-->
							<DIV class="treeDiv"><jsp:include page="contractTree.jsp"></jsp:include>
							</DIV>



							</TD>

							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR id="ErrorRow">
										<TD><w:message></w:message></TD>
									</TR>
									<TR id="NotesErrorRow" style="display:none;">
										<TD>
										<div class='errorDiv' />
										<li id=errorItem>There are notes attached to un-coded
										benefitlines/unanswered questions.&nbsp;&nbsp;<img
											id='link_Notes' src='../../images/select.gif' alt='Select'
											onclick="callUncodedNotesNotesPopUp();return false;"
											style='cursor: hand' /></li>
										</div>
										</TD>
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
												id="priceId"
												onmousedown="javascript:navigatePageAction(this.id);">
												<h:outputText value="Pricing Information" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>

									<TD width="14%" id="tabForStandard1">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD width="100%" class="tabNormal"><h:commandLink
												action="#{ContractNotesBackingBean.load}" id="noteId"
												onmousedown="javascript:navigatePageAction(this.id);">
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
												id="linkToComment"
												onmousedown="javascript:navigatePageAction(this.id);">
												<h:outputText value="Comments" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<td width="16%" id="tabForStandard">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" alt="Tab LeftNormal"
												width="3" height="21" /></td>
											<td class="tabNormal"><h:commandLink
												action="#{contractProductAdminOptionBackingBean.displayContractAdminOption}"
												id="linkToAdminOption"
												onmousedown="javascript:navigatePageAction(this.id);">
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
												id="linkToRules"
												onmousedown="javascript:navigatePageAction(this.id);">
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
										id="genId"
										onmousedown="javascript:navigatePageAction(this.id);">
										<h:outputText value="Basic Information"></h:outputText>
									</h:commandLink></td>
									<td>&nbsp;|&nbsp;</td>
									<td align="left" class="sectionheading"><h:outputText
										value="Specific Information"></h:outputText></td>
									<td>&nbsp;|&nbsp;</td>
									<td><h:commandLink
										action="#{contractBasicInfoBackingBean.getMembershipInfo}"
										id="membId"
										onmousedown="javascript:navigatePageAction(this.id);">
										<h:outputText value="Membership Information">
											</h:outputText>
									</h:commandLink></td>
									<td>&nbsp;|&nbsp;</td>
									<td><h:commandLink
										action="#{contractSpecificInfoBackingBean.loadContractAdaptedInfo}"
										id="adaptId"
										onmousedown="javascript:navigatePageAction(this.id);">
										<h:outputText value="Adapted Information">
											</h:outputText>
									</h:commandLink></td>
								</tr>
							</table>

							<BR />
							<!--	Start of Table for actual Data	-->
							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText">

								<tr></tr>
								<script type="text/javascript">
										function RSCustomInterface(tbElementName){
											this.tbName = tbElementName;
											this.getText = getText;
											this.setText = setText;
									
										function getText(){
											if(!document.getElementById(this.tbName)) {
												alert('Error: element '+this.tbName+' does not exist, check TextComponentName.');
												return '';
											} else return document.getElementById(this.tbName).value;
										}
										function setText(text){
											if(document.getElementById(this.tbName)) document.getElementById(this.tbName).value = text;
										}
									}
									</script>
								<TR>
									<TD valign="top" width="227"><h:outputText id="productCode"
										value="Product Code*"
										styleClass="#{contractSpecificInfoBackingBean.requiredProductCode ? 'mandatoryError': 'mandatoryNormal'}">
									</h:outputText></TD>
									<TD width="26%">
									<DIV id="productCodeDiv" class="selectDataDisplayDiv"></DIV>
									<h:inputHidden id="txtProductCode"
										value="#{contractSpecificInfoBackingBean.productCode}"></h:inputHidden>
									</TD>
									<TD width="48%"><h:commandButton alt="Select"
										id="ProductCodeButton" image="../../images/select.gif"
										style="cursor: hand"
										onclick="productCodeInfo();setDesc(); return false;"></h:commandButton></TD>


								</TR>

								<TR>
									<TD valign="top" width="227"><h:outputText id="productCodeDesc"
										value="Product Code Description*"
										styleClass="#{contractSpecificInfoBackingBean.requiredProductCodeDesc ? 'mandatoryError': 'mandatoryNormal'}">
									</h:outputText></TD>

									<TD width="26%"><h:inputTextarea id="productCodeAreaDesc"
										styleClass="selectDataDisplayDiv1"
										value="#{contractSpecificInfoBackingBean.productCodeDesc}"></h:inputTextarea>
									</TD>

								</TR>





								<TR>
									<TD valign="top" width="227"><h:outputText
										id="standardPlanCode" value="Standard Plan Code">
									</h:outputText></TD>
									<TD width="26%">
									<DIV id="stdPlanCodeDiv" class="selectDataDisplayDiv"></DIV>
									<h:inputHidden id="txtStdPlanCode"
										value="#{contractSpecificInfoBackingBean.standardPlanCode}"></h:inputHidden>
									</TD>
									<TD width="48%"><h:commandButton alt="Select"
										id="StdPlanCodeButton" image="../../images/select.gif"
										style="cursor: hand"
										onclick="standardPlanCodeInfo(); return false;"></h:commandButton>
									</TD>


								</TR>

								<TR>
									<TD valign="top" width="227"><h:outputText id="benefitPlan"
										value="Benefit Plan*"
										styleClass="#{contractSpecificInfoBackingBean.requiredBenefitPlan ? 'mandatoryError': 'mandatoryNormal'}">
									</h:outputText></TD>
									<TD width="26%"><h:inputText id="benefitPlanTxt"
										styleClass="formInputField"
										value="#{contractSpecificInfoBackingBean.benefitPlan}"
										maxlength="15" /></TD>
									<TD width="48%"></TD>

								</TR>
								<TR>
									<TD valign="top" width="227"><h:outputText id="product"
										value="Product*"
										styleClass="#{contractSpecificInfoBackingBean.requiredProduct ? 'mandatoryError': 'mandatoryNormal'}">
									</h:outputText></TD>
									<TD width="26%">
									<DIV id="productDiv" class="selectDataDisplayDiv"></DIV>
									<h:inputHidden id="txtProduct"
										value="#{contractSpecificInfoBackingBean.product}"></h:inputHidden>
									</TD>
									<TD width="48%"><h:commandButton alt="Select"
										id="productButton" image="../../images/select.gif"
										style="cursor: hand" onclick="productPopup();return false;"
										rendered="#{contractSpecificInfoBackingBean.custom}"></h:commandButton>
									</TD>


								</TR>


								<TR>
									<TD valign="top" width="227"><h:outputText id="productFamily"
										value="Product Family">
									</h:outputText></TD>
									<TD width="26%"><h:outputText id="txtProductFamily"
										value="#{contractSpecificInfoBackingBean.productFamily}"></h:outputText>

									<h:inputHidden id="txtProductFamilyHidden"
										value="#{contractSpecificInfoBackingBean.productFamily}"></h:inputHidden>
									</TD>


								</TR>

								<TR>
									<TD valign="top" width="227"><h:outputText
										id="corporatePlanCode" value="Corporate Plan Code"
										styleClass="#{contractSpecificInfoBackingBean.requiredCorporateCode ? 'mandatoryError': 'mandatoryNormal'}">
									</h:outputText></TD>
									<TD width="26%">
									<DIV id="corporatePlanCodeDiv" class="selectDataDisplayDiv"></DIV>
									<h:inputHidden id="txtCorporatePlanCode"
										value="#{contractSpecificInfoBackingBean.corporatePlanCode}"></h:inputHidden>
									</TD>
									<TD width="48%"><h:commandButton alt="Select"
										id="corporatePlanCodeButton" image="../../images/select.gif"
										style="cursor: hand"
										onclick="corporatePlanCodeInfo(); return false;"></h:commandButton>
									</TD>


								</TR>



								<TR>
									<TD valign="top" width="227"><h:outputText id="brandName"
										value="Brand Name">
									</h:outputText></TD>
									<TD width="26%"><h:inputText id="brandNameTxt"
										styleClass="formInputField"
										value="#{contractSpecificInfoBackingBean.brandName}"
										maxlength="30" /></TD>
									<TD width="48%"></TD>

								</TR>

								<TR>
									<TD valign="top" width="227"><h:outputText id="provSpecCode"
										value="Related Provider Specialty Code">
									</h:outputText></TD>
									<TD width="26%">
									<DIV id="provSpecCodeDiv" class="selectDataDisplayDiv"></DIV>
									<h:inputHidden id="txtprovSpecCode"
										value="#{contractSpecificInfoBackingBean.provSpecCode}"></h:inputHidden>
									</TD>
									<TD width="48%"><h:commandButton alt="Select"
										id="provSpecCodeButton" image="../../images/select.gif"
										style="cursor: hand"
										onclick="ProvSpecCodeInfo(); return false;"></h:commandButton>
									</TD>

								</TR>




								<TR>
									<TD width="227"><h:outputText id="cobIndicator"
										value="COB Adjudication Indicator*"
										styleClass="#{contractSpecificInfoBackingBean.requiredCobIndicator ? 'mandatoryError': 'mandatoryNormal'}">
									</h:outputText></TD>
									<TD width="26%"><h:selectOneRadio id="COB"
										value="#{contractSpecificInfoBackingBean.cobAdjudicationIndicator}">
										<f:selectItem id="COBYes" itemLabel="Yes" itemValue="Y" />
										<f:selectItem id="COBNo" itemLabel="No" itemValue="N" />
									</h:selectOneRadio></TD>
								</TR>

								<TR>
									<TD width="227"><h:outputText id="medIndicator"
										value="Medicare Adjudication Indicator*"
										styleClass="#{contractSpecificInfoBackingBean.requiredMedIndicator ? 'mandatoryError': 'mandatoryNormal'}">
									</h:outputText></TD>
									<TD width="26%"><h:selectOneRadio id="MED"
										value="#{contractSpecificInfoBackingBean.medicareAdjudicationIndicator}">
										<f:selectItem id="MEDYes" itemLabel="Yes" itemValue="Y" />
										<f:selectItem id="MEDNo" itemLabel="No" itemValue="N" />
									</h:selectOneRadio></TD>
								</TR>

								<TR>
									<TD width="227"><h:outputText id="itsIndicator"
										value="ITS Adjudication Indicator*"
										styleClass="#{contractSpecificInfoBackingBean.requiredItsIndicator ? 'mandatoryError': 'mandatoryNormal'}">
									</h:outputText></TD>
									<TD width="26%"><h:selectOneRadio id="ITS"
										value="#{contractSpecificInfoBackingBean.itsAdjudicationIndicator}">
										<f:selectItem id="ITSYes" itemLabel="Yes" itemValue="Y" />
										<f:selectItem id="ITSNo" itemLabel="No" itemValue="N" />
									</h:selectOneRadio></TD>
								</TR>
						
									<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher3"
									textComponentName="SpecificInfoForm:productCodeAreaDesc"
									rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Product Code  Description"
									modalHelperPage="../spellcheck/RapidSpellModalHelper.html"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" windowWidth="570" windowHeight="300"
									modal="False" showButton="False" windowX="-1"
									warnDuplicates="False" textComponentInterface="Custom">
								</RapidSpellWeb:rapidSpellWebLauncher>



								<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher1"
									textComponentName="SpecificInfoForm:benefitPlanTxt"
									rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Benefit Plan"
									modalHelperPage="../spellcheck/RapidSpellModalHelper.html"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" windowWidth="570" windowHeight="300"
									modal="False" showButton="False" windowX="-1"
									warnDuplicates="False" textComponentInterface="Custom">
								</RapidSpellWeb:rapidSpellWebLauncher>
								<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher2"
									textComponentName="SpecificInfoForm:brandNameTxt"
									rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Brand Name"
									modalHelperPage="../spellcheck/RapidSpellModalHelper.html"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" finishedListener="spellFin"
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
								</RapidSpellWeb:rapidSpellWebLauncher>

								<TR>
									<TD><h:commandButton styleClass="wpdbutton" value="Save"
										id="saveButton"
										onmousedown="javascript:savePageAction(this.id);"
										onclick="return runSpellCheck();">
									</h:commandButton></TD>
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
									<td align="left">
									<table Width=100%>
										<tr>
											<td></td>
											<td></td>
										</tr>
										<tr>
											<td><h:selectBooleanCheckbox id="checkall"
												value="#{contractSpecificInfoBackingBean.checkin}">
											</h:selectBooleanCheckbox> <h:outputText value="Check In" /></td>
											<td></td>
										</tr>
										<tr>
											<td><a href="#"
												onclick=" showPopupForContractTransferLog();return false;">
											&nbsp;<h:outputText value="Transfer Log"
												styleClass="variableLink" /></a></td>
											<td></td>
									</table>
									</td>
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

							<TABLE>
								<TBODY>
									<TR>
										<TD>&nbsp;&nbsp;
										<h:commandButton styleClass="wpdbutton" id="button2" value="Done" onclick="return done();"></h:commandButton>
										<!-- <input type="button" value="Done" class="wpdButton" id="doneButton" onclick="processDone();"/> -->
										</TD>

									</TR>
								</TBODY>
							</TABLE>
							<br>
							<br>
							<!--	End of Page data	--> <!-- Space for hidden fields --> <!-- End of hidden fields  -->
							</TD>
						</TR>
					</TABLE>
					<h:inputHidden id="txtLob" value="#{contractSpecificInfoBackingBean.lob}"></h:inputHidden>
					<h:inputHidden id="txtBusinessEntity" value="#{contractSpecificInfoBackingBean.be}"></h:inputHidden>
					<h:inputHidden id="txtBusinessGroup" value="#{contractSpecificInfoBackingBean.bg}"></h:inputHidden>
					<h:inputHidden id="txtMarketBusinessUnit" value="#{contractSpecificInfoBackingBean.mbu}"></h:inputHidden>
					<h:inputHidden id="contractIDForRefData" value="#{contractSpecificInfoBackingBean.contractIdForRefData}"></h:inputHidden>
					<h:inputHidden id="groupSize" value="#{contractSpecificInfoBackingBean.groupSize}"></h:inputHidden>
					<h:inputHidden id="lineOfBusiness" value="#{contractSpecificInfoBackingBean.lineOfBusiness}"></h:inputHidden>
					<h:inputHidden id="businessGroup" value="#{contractSpecificInfoBackingBean.businessGroup}"></h:inputHidden>
					<h:inputHidden id="businessEntity"	value="#{contractSpecificInfoBackingBean.businessEntity}"></h:inputHidden>
					<h:inputHidden id="marketBusinessUnit"	value="#{contractSpecificInfoBackingBean.marketBusinessUnit}"></h:inputHidden>
					<h:inputHidden id="effectiveDate"	value="#{contractSpecificInfoBackingBean.effectiveDate}"></h:inputHidden>
					<h:inputHidden id="expiryDate"	value="#{contractSpecificInfoBackingBean.expiryDate}"></h:inputHidden>
					<h:inputHidden id="hiddenProduct"	value="#{contractSpecificInfoBackingBean.hiddenProduct}"></h:inputHidden>
					<h:inputHidden id="dateSegmentType"	value="#{contractSpecificInfoBackingBean.dateSegmentType}"></h:inputHidden>
					<h:inputHidden id="hasValErrors"	value="#{contractSpecificInfoBackingBean.hasValidationErrors}"></h:inputHidden>
					<h:inputHidden id="custom" value="#{contractSpecificInfoBackingBean.custom}"></h:inputHidden>
					<h:inputHidden id="hiddenProductType" value="#{contractBenefitGeneralInfoBackingBean.pageLoadBasedOnContractType}"></h:inputHidden>
					<h:inputHidden id="duplicateData" value="#{contractSpecificInfoBackingBean.duplicateData}"></h:inputHidden>
					<h:inputHidden id="uncodedLines" value="#{contractSpecificInfoBackingBean.ifUncodedLineNotesExist}"></h:inputHidden>

					<h:commandLink id="saveLink" style="hidden"
						action="#{contractSpecificInfoBackingBean.save}">
					</h:commandLink>
					<h:commandLink id="doneLink" style="hidden"
						action="#{contractSpecificInfoBackingBean.done}">
					</h:commandLink>
					<h:commandLink id="checkinLink"
						action="#{contractBasicInfoBackingBean.directCheckin}"
						style="display:none; visibility: hidden;">
						<f:verbatim> &nbsp;&nbsp;</f:verbatim>
					</h:commandLink>
				<h:inputHidden id="invokeWebService" value="#{contractSpecificInfoBackingBean.invokeWebService}"></h:inputHidden>
				<h:commandLink id="cmdLnkIdForCancelButton" style="display:none; visibility: hidden;" action="#{contractSpecificInfoBackingBean.doContractCancelAction}"></h:commandLink>	
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
IGNORED_FIELD1='SpecificInfoForm:duplicateData';

// for uncoded notes validation
	var ifUncodedLineNotesExist = document.getElementById('SpecificInfoForm:uncodedLines').value;
	if(ifUncodedLineNotesExist == 'Yes'){
		var message = "There are notes attached to un-coded benefitlines/ unanswered questions and these notes will be removed while check in. Do you want to continue?"
		if(window.confirm(message)){
			document.getElementById('SpecificInfoForm:uncodedLines').value ='';
			submitLink('SpecificInfoForm:checkinLink');
		}else{
			  NotesErrorRow.style.display='';
			  ErrorRow.style.display ='none';	
		}
		document.getElementById('SpecificInfoForm:uncodedLines').value ='';
	}

function productPopup(){
	ewpdModalWindow_ewpd(getProdPopupUrl('SpecificInfoForm:lineOfBusiness','SpecificInfoForm:businessEntity','SpecificInfoForm:businessGroup','SpecificInfoForm:marketBusinessUnit',
									 		'SpecificInfoForm:effectiveDate','SpecificInfoForm:expiryDate','../contractpopups/productPopup.jsp'+getUrl()+'?'),
						'productDiv','SpecificInfoForm:txtProduct',2,2);
	
}

 function getProdPopupUrl(lobId,beId,bgId,mbuId,effDateId,expDateId,url){
	var lob = getObj(lobId).value;
	var be = getObj(beId).value;
	var bg = getObj(bgId).value;
	var mbu = getObj(mbuId).value;
	var effDate = getObj(effDateId).value;
	var expDate = getObj(expDateId).value;

	var popupUrl = url;
	popupUrl += 'lob=' + escape(lob) + '&be=' + escape(be) + '&bg=' + escape(bg) + '&mbu=' + escape(mbu) + '&effDate=' + escape(effDate) + '&expDate=' + escape(expDate) + '&temp=' + Math.random();
	return popupUrl;
 }


	copyHiddenToDiv_ewpd('SpecificInfoForm:txtProductCode','productCodeDiv',2,2); 
	copyHiddenToDiv_ewpd('SpecificInfoForm:txtStdPlanCode','stdPlanCodeDiv',2,2); 
	copyHiddenToDiv_ewpd('SpecificInfoForm:txtProduct','productDiv',2,2); 
	copyHiddenToDiv_ewpd('SpecificInfoForm:txtCorporatePlanCode','corporatePlanCodeDiv',2,2); 

	copyHiddenToDiv_ewpd_show_code_desc('SpecificInfoForm:txtprovSpecCode','provSpecCodeDiv',2,2,'edit'); 


	
function productCodeInfo(){

	var contractID = document.getElementById('SpecificInfoForm:contractIDForRefData').value;
	var entityType = 'contract';
	var titleName = 'Product Code Popup';	
	getSelectedContractPricingData('../popups/SearchPopupSingleSelect.jsp'+getUrl()+'?parentCatalog='+'Product Codes'+'&entityId='+ contractID +'&titleName='+titleName+ '&entityType=' + entityType,'SpecificInfoForm','txtLob','txtBusinessEntity','txtBusinessGroup','txtMarketBusinessUnit','productCodeDiv','SpecificInfoForm:txtProductCode',2,2,'Product Codes');
	setRefDataUseFlag('SpecificInfoForm', 'productCode', 'productCodeDiv');

 	//return false;
}

// separates the description from the tilda separated description~code string.
// and sets the description to the text area. 
function setDesc() {
var val = document.getElementById('SpecificInfoForm:txtProductCode').value;
 if((null!=val) && !('' == val)){
		var prdct = val.split('~');	
		document.getElementById('SpecificInfoForm:productCodeAreaDesc').innerHTML=prdct[0];
  } else {
document.getElementById('SpecificInfoForm:productCodeAreaDesc').innerHTML='';
	}
}

function standardPlanCodeInfo(){
	var contractID = document.getElementById('SpecificInfoForm:contractIDForRefData').value;
	var entityType = 'contract';
	var titleName = 'Standard Plan Code Popup';		
	getSelectedContractPricingData('../popups/SearchPopupSingleSelect.jsp'+getUrl()+'?parentCatalog='+'Standard Plan Code'+'&entityId='+ contractID + '&titleName='+titleName+'&entityType=' + entityType,'SpecificInfoForm','txtLob','txtBusinessEntity','txtBusinessGroup','txtMarketBusinessUnit','stdPlanCodeDiv','SpecificInfoForm:txtStdPlanCode',2,2,'Standard Plan Code');
	setRefDataUseFlag('SpecificInfoForm', 'standardPlanCode', 'stdPlanCodeDiv');	
 	return false;
}
function corporatePlanCodeInfo(){
	var contractID = document.getElementById('SpecificInfoForm:contractIDForRefData').value;
	
	var entityType = 'contract';
	var titleName = 'Corporate Plan Code Popup';			
	getSelectedContractPricingData('../popups/SearchPopupSingleSelect.jsp'+getUrl()+'?parentCatalog='+'Corporate Plan Codes'+'&entityId='+ contractID + '&titleName='+titleName+'&entityType=' + entityType,'SpecificInfoForm','txtLob','txtBusinessEntity','txtBusinessGroup','txtMarketBusinessUnit','corporatePlanCodeDiv','SpecificInfoForm:txtCorporatePlanCode',2,2,'Corporate Plan Codes');
	setRefDataUseFlag('SpecificInfoForm', 'corporatePlanCode', 'corporatePlanCodeDiv');	
 	return false;
}


function  ProvSpecCodeInfo(){

var contractID = document.getElementById('SpecificInfoForm:contractIDForRefData').value;
	var entityType = 'contract';
	var titleName = 'Related Provider Speciality Popup';
	getSelectedContractPricingData_ewpd_show_code_desc('../contractpopups/ProvSpecPopupMultiSelect.jsp'+getUrl()+'?parentCatalog='+'REL PROVIDER SPECIALITY CODE'+'&entityId='+
 	contractID + '&titleName='+titleName+'&entityType=' + entityType,'SpecificInfoForm','txtLob','txtBusinessEntity','txtBusinessGroup','provSpecCodeDiv','SpecificInfoForm:txtprovSpecCode',2,2,'REL PROVIDER SPECIALITY CODE','edit');			
	setRefDataUseFlag('SpecificInfoForm', 'corporatePlanCode', 'corporatePlanCodeDiv');	
 	return false;

}


/*function fundingArrangementInfo(){
	var contractID = document.getElementById('SpecificInfoForm:contractIDForRefData').value;
	ewpdModalWindow_ewpd('../contractpopups/fundingArrangementPopup.jsp?lookUpAction='+3+'&parentCatalog='+'Funding Arrangement'+'&entityId='+ contractID + '&entityType=' + 'contract','fundingArrangementDiv','SpecificInfoForm:fundingArrangement',2,2); 

}*/
function stateInfo(){
	var contractID = document.getElementById('SpecificInfoForm:contractIDForRefData').value;
	ewpdModalWindow_ewpd('../contractpopups/HeadQuarterstatePopup.jsp'+getUrl()+'?lookUpAction='+3+'&parentCatalog='+'State Code'+'&entityId='+ contractID + '&entityType=' + 'contract','headQuartersStateDiv','SpecificInfoForm:headQuarters',2,1); 

}
i = document.getElementById("SpecificInfoForm:hiddenProductType").value;
	if(i=='MANDATE')
	{
	tabForStandard.style.display='none';
	tabForStandard1.style.display='none';
	}else{
	tabForStandard.style.display='';
	tabForStandard1.style.display='';
	}
var initial='yes';     
if(document.getElementById('SpecificInfoForm:hasValErrors').value == 'true'){
	var url='../adminMethods/adminMethodContractValidation.jsp'+getUrl()+'?temp='+Math.random()+'&initial='+initial;
	var returnvalue=window.showModalDialog(url,"","dialogHeight:800px;dialogWidth:1200px;resizable=true;status=no;");
	document.getElementById('SpecificInfoForm:hasValErrors').value = 'false';
}
var doneFlag = false;

function done(){
	doneFlag = true;
	return runSpellCheck();   
}
function runSpellCheck(){
	var rswlCntrls = ["rapidSpellWebLauncher3","rapidSpellWebLauncher1", "rapidSpellWebLauncher2"];
	var i=0;
	for(var i=0; i<rswlCntrls.length; i++){
		eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
	}
	return false;
}
function spellFin(cancelled){

		if(doneFlag)
			document.getElementById('SpecificInfoForm:doneLink').click();	
		else{ 
			document.getElementById('SpecificInfoForm:saveLink').click();
		}			

	doneFlag = false;
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
	name="currentPrintPage" value="contractSpecificInfo" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('SpecificInfoForm:duplicateData').value == '')
		document.getElementById('SpecificInfoForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
	else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('SpecificInfoForm:duplicateData').value;
</script>
<script>
onPageLoadPopup();
</script>
</HTML>
