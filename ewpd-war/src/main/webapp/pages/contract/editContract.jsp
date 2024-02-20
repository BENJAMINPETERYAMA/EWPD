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

<TITLE>Edit Contract</TITLE>
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
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript">
	 var cal1 = new CalendarPopup();
	 cal1.showYearNavigation();

function onPageLoadPopup(){
	
 	var ebxFlag = document.getElementById('contractForm:hidd_webServiceFlag').value;
 	var vendorFlag = document.getElementById('contractForm:hidd_VendorFlag').value;
   	if(vendorFlag == "true" && ebxFlag == "true"){ 
   		var url = '../contract/contractErrorValidationsPopup.jsp' + getUrl() + '?temp=' + Math.random();		
		document.getElementById('contractForm:hidd_webServiceFlag').value = "false";
		document.getElementById('contractForm:hidd_VendorFlag').value = "false";
		newWinForView = window.open(url,'Validations','height=600,width=1000,left=100,top=100,resizable=no,scrollbars=no,toolbar=no,menubar=no,location=no,directories=no, status=no');
     }else if(vendorFlag == "true" && ebxFlag == "false"){
     	var url = '../contract/contractErrorValidationsPopup.jsp' + getUrl() + '?temp=' + Math.random();
     	document.getElementById('contractForm:hidd_webServiceFlag').value = "false";
		document.getElementById('contractForm:hidd_VendorFlag').value = "false";
		newWinForView = window.open(url,'Validations','height=600,width=1000,left=100,top=100,resizable=no,scrollbars=no,toolbar=no,menubar=no,location=no,directories=no, status=no');
     }else if(vendorFlag == "false" && ebxFlag == "true"){     	
     	var url = '../popups/ebxValidationsPopup.jsp'+ getUrl() + '?temp=' + Math.random();
     	document.getElementById('contractForm:hidd_webServiceFlag').value = "false";
		document.getElementById('contractForm:hidd_VendorFlag').value = "false";
     	newWinForView = window.open(url,'Validations','height=600,width=1000,left=100,top=100,resizable=no,scrollbars=no,toolbar=no,menubar=no,location=no,directories=no, status=no');
     }

}  

</script>
</HEAD>
<f:view>
	<BODY
		onkeypress="return submitOnEnterKey('contractForm:createButton');">
	<hx:scriptCollector id="scriptCollector1">
		<h:inputHidden id="hidden1" ></h:inputHidden>
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="contractForm">
				<h:inputHidden id="hidd_webServiceFlag" value="#{contractBasicInfoBackingBean.webServiceFlag}"/>
				<h:inputHidden id="hidd_VendorFlag" value="#{contractBasicInfoBackingBean.vendorFlag}"/>
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD width="273" valign="top" class="leftPanel">


							<DIV class="treeDiv"><!-- Space for Tree  Data	--> <jsp:include
								page="contractTree.jsp"></jsp:include></DIV>

							</TD>

							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR id ="ErrorRow">
										<TD><w:message></w:message></TD>
									</TR>
									<TR  id="NotesErrorRow" style="display:none;">
										<TD><div class='errorDiv' /><li id=errorItem>There are notes attached to un-coded benefitlines/unanswered questions.&nbsp;&nbsp;<img id='link_Notes' src='../../images/select.gif' alt='Select' 
											onclick= "callUncodedNotesNotesPopUp();return false;" style='cursor: hand'  /></li></div></TD>
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
											<TD width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21" /></td>
											<TD width="100%" class="tabNormal"><h:commandLink
												action="#{contractPricingInfoBackingBean.dispalyContractPricingInfoTab}"
												onmousedown="javascript:navigatePageAction(this.id);"
												id="priceId">
												<h:outputText id="pricingInfoTabTable"
													value="Pricing Information" />
											</h:commandLink></TD>
											<td width="2" align="right"><img
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21" /></td>
										</TR>
									</TABLE>
									</TD>
									<TD width="14%" id="tabForStandard1">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21" /></td>
											<TD width="100%" class="tabNormal"><h:commandLink
												action="#{ContractNotesBackingBean.load}"
												onmousedown="javascript:navigatePageAction(this.id);"
												id="noteId">
												<h:outputText id="notesTabTable" value="Notes" />
											</h:commandLink></TD>
											<td width="2" align="right"><img
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21" /></td>
										</TR>
									</TABLE>
									</TD>
									<TD width="16%">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21" /></td>
											<TD width="100%" class="tabNormal"><h:commandLink
												action="#{contractCommentBackingBean.loadComment}"
												onmousedown="javascript:navigatePageAction(this.id);"
												id="linkToComment">
												<h:outputText id="commentsTabTable" value="Comments" />
											</h:commandLink></TD>
											<td width="2" align="right"><img
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21" /></td>
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
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
							<br />
							<table style="margin-top: 6px;">
								<tr>
									<td align="left" class="sectionheading"><h:outputText
										value="Basic Information"></h:outputText></td>
									<td>&nbsp;|&nbsp;</td>
									<td align="left"><h:commandLink
										action="#{contractSpecificInfoBackingBean.loadContractSpecificInfo}"
										id="specId"
										onmousedown="javascript:navigatePageAction(this.id);">
										<h:outputText value="Specific Information"></h:outputText>
									</h:commandLink></td>
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
							<!--	Start of Table for actual Data	-->
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
												<TD width="537"><h:outputText id="lineOfBusiness"
													value="Line Of Business*"
													styleClass="#{contractBasicInfoBackingBean.lobInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
												</TD>
												<h:inputHidden id="lineOfBusinessHidden"
													value="#{contractBasicInfoBackingBean.lineOfBusinessDiv}"></h:inputHidden>
												<TD width="220">
												<div id="lineOfBusinessDiv" class="selectDivReadOnly"></div>

												</TD>
												<TD width="100">&nbsp;</TD>
											</TR>
											<TR>
												<TD width="537"><h:outputText id="businessEntity"
													value="Business Entity*"
													styleClass="#{contractBasicInfoBackingBean.entityInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
												</TD>
												<h:inputHidden id="businessEntityHidden"
													value="#{contractBasicInfoBackingBean.businessEntityDiv}"></h:inputHidden>
												<TD width="220">
												<div id="businessEntityDiv" class="selectDivReadOnly"></div>

												</TD>
												<TD width="100">&nbsp;</TD>
											</TR>
											<TR>
												<TD width="537"><h:outputText id="businessGroup"
													value="Business Group*"
													styleClass="#{contractBasicInfoBackingBean.groupInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
												</TD>
												<h:inputHidden id="businessGroupHidden"
													value="#{contractBasicInfoBackingBean.businessGroupDiv}"></h:inputHidden>
												<TD width="220">
												<div id="businessGroupDiv" class="selectDivReadOnly"></div>

												</TD>
												<TD width="100">&nbsp;</TD>
											</TR>
<!-- ----------------------------------------------------------------------------------------------------------------- -->
											<TR>
												<TD width="537"><h:outputText id="MarketBusinessUnit"
													value="Market Business Unit*"
													styleClass="#{contractBasicInfoBackingBean.requiredMarketBusinessUnit ? 'mandatoryError': 'mandatoryNormal'}" />
												</TD>
												<h:inputHidden id="marketBusinessUnitHidden"
													value="#{contractBasicInfoBackingBean.marketBusinessUnit}"></h:inputHidden>
												<TD width="220">
												<div id="marketBusinessUnit" class="selectDivReadOnly"></div>

												</TD>
												<TD width="100">&nbsp;</TD>
											</TR>
<!-- ------------------------------------------------------------------------------------------------------------------ -->
										</TABLE>
										</FIELDSET>
										</TD>
									</TR>

									<TR>

										<TD width="220">&nbsp;&nbsp;<h:outputText id="contractId"
											value="Contract ID"
											styleClass="#{contractBasicInfoBackingBean.contractIdInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<h:inputHidden id="contractIdHidden"
											value="#{contractBasicInfoBackingBean.contractIdDiv}"></h:inputHidden>
										<TD width="185"><h:outputText id="contractId_txt"
											value="#{contractBasicInfoBackingBean.contractIdDiv}" /></TD>

									</TR>

									<TR>
										<TD height="25" width="220">&nbsp;&nbsp;<h:outputText
											id="contractType" value="Contract Type "
											styleClass="#{contractBasicInfoBackingBean.contractTypeInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD height="25" width="185"><h:outputText
											id="contractType_txt"
											value="#{contractBasicInfoBackingBean.contractType}" /> <h:inputHidden
											id="contractTypeHidden"
											value="#{contractBasicInfoBackingBean.contractType}">
										</h:inputHidden>
									</TR>
									<!-- Changes made for STANDARD Contract -->
									<TR id="baseContRowStandard" style="display:none;">

										<TD width="220">&nbsp;&nbsp;<h:outputText
											id="baseContractIdStandard" value="Base Contract Id"
											styleClass="#{contractBasicInfoBackingBean.baseContractIdInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>

										<TD width="185"><h:outputText
											id="base_contractId_txt_Standard"
											value="#{contractBasicInfoBackingBean.baseContractIdDivNonMaditory}" />
										<h:inputHidden id="baseContractIdStandardHidden"
											value="#{contractBasicInfoBackingBean.baseContractIdDivNonMaditory}"></h:inputHidden>
										</TD>

									</TR>

									<TR id="baseContDtRowStandard" style="display:none;">

										<TD width="220">&nbsp;&nbsp;<h:outputText
											id="baseContractDtStandard"
											value="Base Contract Effective Date"
											styleClass="#{contractBasicInfoBackingBean.baseContractIdInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>

										<TD width="185"><h:outputText
											id="base_contractDt_txt_Standard"
											value="#{contractBasicInfoBackingBean.baseContractDtDivNonMaditory}" />
										<h:inputHidden id="baseContractDtStandardHidden"
											value="#{contractBasicInfoBackingBean.baseContractDtDivNonMaditory}"></h:inputHidden>
										</TD>

									</TR>
									<!-- Changes made for STANDARD Contract -->
									<TR id="baseContRow" style="display:none;">

										<TD width="220">&nbsp;&nbsp;<h:outputText id="baseContractId"
											value="Base Contract Id"
											styleClass="#{contractBasicInfoBackingBean.baseContractIdInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>

										<TD width="185"><h:outputText id="base_contractId_txt"
											value="#{contractBasicInfoBackingBean.baseContractIdDiv}" />
										<h:inputHidden id="baseContractIdHidden"
											value="#{contractBasicInfoBackingBean.baseContractIdDiv}"></h:inputHidden>
										</TD>

									</TR>

									<TR id="baseContDtRow" style="display:none;">

										<TD width="220">&nbsp;&nbsp;<h:outputText id="baseContractDt"
											value="Base Contract Effective Date"
											styleClass="#{contractBasicInfoBackingBean.baseContractIdInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>

										<TD width="185"><h:outputText id="base_contractDt_txt"
											value="#{contractBasicInfoBackingBean.baseContractDtDiv}" />
										<h:inputHidden id="baseContractDtHidden"
											value="#{contractBasicInfoBackingBean.baseContractDtDiv}"></h:inputHidden>
										</TD>

									</TR>
									<TR id="headQuaterStateRow" style="display:none;">

										<TD width="220">&nbsp;&nbsp;<h:outputText id="head"
											value="Head Quarters State " /></TD>

										<TD width="185"><h:outputText id="head_txt"
											value="#{contractBasicInfoBackingBean.headQuartersState}" />
										<h:inputHidden id="headHidden"
											value="#{contractBasicInfoBackingBean.headQuartersState}"></h:inputHidden>
										</TD>

									</TR>

									<TR>

										<TD height="25" width="220">&nbsp;&nbsp;<h:outputText
											id="groupSizeTxt" value="Group Size*"
											styleClass="#{contractBasicInfoBackingBean.groupSizeInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<h:inputHidden id="groupSizeHidden"
											value="#{contractBasicInfoBackingBean.groupSizeDiv}"></h:inputHidden>
										<TD width="185">
										<div id="groupSizeDiv" class="selectDataDisplayDiv"></div>

										</TD>
										<TD width="288"><h:commandButton alt="Group Size"
											id="groupSizeButton" image="../../images/select.gif"
											onclick="getSelectedDomainReferenceData('../contractpopups/groupSize.jsp','contractForm','marketBusinessUnitHidden','lineOfBusinessHidden',
												'businessEntityHidden','businessGroupHidden','groupSizeDiv','contractForm:groupSizeHidden',2,1,'Group Size');
																				return false;"
											rendered="#{contractBasicInfoBackingBean.notMandate}">
										</h:commandButton></TD>
									</TR>
									<TR>

										<TD width="220">&nbsp;
											<h:outputText id="termedContract" value="Termed Contract ID" />
										</TD>
										<h:inputHidden id="termedContractId"
													value="#{contractBasicInfoBackingBean.termedContractId}"/>
										<TD width="185">
										<div id="termedcntDiv" class="selectDataDisplayDiv"></div>

										</TD>
										<TD width="288"><h:commandButton alt="Termed Contract ID" image="../../images/select.gif" 
													onclick="loadContactIdSearchPopup('searchContractId','Contract Id',
                                                         'Contract Id Popup','termedcntDiv','contractForm:termedContractId','effectiveDateDiv','contractForm:tempDateHidden'); return false;" />
													<div id="effectiveDateDiv" style="display:none" ></div>
													<h:inputHidden id="tempDateHidden" />
										</TD>
									</TR>
									<TR>
										<TD width="220">&nbsp;&nbsp;<h:outputText value="Archival Status*" styleClass="#{contractBasicInfoBackingBean.contractStatusInvalid ? 'mandatoryError': 'mandatoryNormal'}"/></TD>

										<TD width="185"><h:selectOneMenu id="selectContractStatus" styleClass="formInputField" onchange="hideReasonCode();"
													value="#{contractBasicInfoBackingBean.contractStatus}">
														<f:selectItems 
															value="#{ReferenceDataBackingBeanCommon.contractStatuses}" />
													</h:selectOneMenu>
										</TD>

									</TR>
									<TR id="contractStatusDateRow">

										<TD width="220">&nbsp;&nbsp;<h:outputText value="Archival Status Date*" styleClass="#{contractBasicInfoBackingBean.contractStatusDateInvalid ? 'mandatoryError': 'mandatoryNormal'}"/>
										</TD>

										<TD width="185">
											<h:inputText styleClass="formInputField" 
													maxlength="10" id="statusDate"
													tabindex="11" value="#{contractBasicInfoBackingBean.contractStatusDate}"/> <span
													class="dateFormat"></span>
										</TD>
													
										<TD width="288">
											<A href="#"
													onclick="cal1.select('contractForm:statusDate','anchor1','MM/dd/yyyy'); return false;"
													name="anchor1" id="anchor1"
													title="cal1.select('contractForm:contractForm','anchor1','MM/dd/yyyy'); return false;"
													> <h:commandButton image="../../images/cal.gif"
													style="cursor: hand" alt="Cal" /> </A>
										</TD>

									</TR>
									<TR id="contractStatusReasonRow">

										<TD width="220">&nbsp;&nbsp;<h:outputText value="Reason Code*" styleClass="#{contractBasicInfoBackingBean.statusResonCodeInvalid ? 'mandatoryError': 'mandatoryNormal'}"/>
										</TD>

										<TD width="185">
											<div class="selectDataDisplayDiv" id="statusReasonCodeDiv"></div>
													<h:inputHidden id="statusReasonCodeHidden"
											value="#{contractBasicInfoBackingBean.contractStatusReasonCode}"></h:inputHidden>
										</TD>
										<TD width="288">			
											<h:commandButton alt="Reason Code"
														image="../../images/select.gif" id="reasonCodeButton"
														onclick="getSelectedReasoncode('../contractpopups/statusReasonCode.jsp','contractForm','statusReasonCodeDiv','contractForm:statusReasonCodeHidden',0,0,'Reason Codes');return false;"
														rendered="#{contractBasicInfoBackingBean.notMandate}">
													</h:commandButton>
										</TD>

									</TR>
									
									<TR valign="top">

										<TD width="220">&nbsp;&nbsp;<h:outputText
											value="Effective Date"
											styleClass="#{contractBasicInfoBackingBean.startDateInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD width="185"><h:outputText id="effectiveDate_txt"
											value="#{contractBasicInfoBackingBean.startDate}" /> <h:inputHidden
											id="effectiveDateHidden"
											value="#{contractBasicInfoBackingBean.startDate}">
										</h:inputHidden>
									</TR>
									<TR valign="top">

										<TD width="220">&nbsp;&nbsp;<h:outputText value="Expiry Date"
											styleClass="#{contractBasicInfoBackingBean.endDateInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD width="185"><h:outputText id="expiryDate_txt"
											value="#{contractBasicInfoBackingBean.endDate}" /> <h:inputHidden
											id="expiryDateHidden"
											value="#{contractBasicInfoBackingBean.endDate}">
										</h:inputHidden>
									</TR>
									<tr>
										<td valign="top" width="220"><span class="mandatoryNormal"
											id="createdBy">&nbsp;</span> <h:outputText value="Created By" /></td>
										<td width="185"><h:outputText
											value="#{contractBasicInfoBackingBean.createdUser}" /> <h:inputHidden
											id="createdUserHidden"
											value="#{contractBasicInfoBackingBean.createdUser}">
										</h:inputHidden></td>
										<TD width="288"></TD>
									</tr>
									<tr>
										<td valign="top" width="220"><span class="mandatoryNormal"
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
										<td valign="top" width="220"><span class="mandatoryNormal"
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
										<td valign="top" width="220"><span class="mandatoryNormal"
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


									<TR>
										<TD width="210">&nbsp;&nbsp;<h:commandButton value="Save"
											onmousedown="javascript:savePageAction(this.id);"
											styleClass="wpdButton" id="createButton"
											action="#{contractBasicInfoBackingBean.saveBasicInfo}">
										</h:commandButton></TD>
										<TD width="185">&nbsp;</TD>
										<TD width="288"></TD>
									</TR>

								</TBODY>
							</TABLE>
							<!--	End of Page data	--></FIELDSET>

							<BR>

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
							<table border="0" cellspacing="0" cellpadding="3" width="100%">
								<tr>
									<td align="left">
									<table Width=100%>
										<tr>
											<td><a href="#"
												onclick="window.showModalDialog('../contractpopups/testDate.jsp'+getUrl()+'?temp='+Math.random(),'','dialogHeight:250px;dialogWidth:450px;resizable=true;status=no;');return false;">
											&nbsp;<h:outputText value="Enter Test Data"
												styleClass="variableLink" /></a></td>
											<td></td>
										</tr>
										<tr>
											<td><h:selectBooleanCheckbox id="checkall"
												value="#{contractBasicInfoBackingBean.checkIn}">
											</h:selectBooleanCheckbox>&nbsp;<h:outputText
												value="Check In" /></td>
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
									<td align="right" width="127" rowspan="2">
									<table Width=100%>
										<tr>
											<td><h:outputText value="State" /></td>
											<td>:<f:verbatim>&nbsp;</f:verbatim><h:outputText
												value="#{contractBasicInfoBackingBean.state}" /></td>
											<h:inputHidden id="stateHidden"
												value="#{contractBasicInfoBackingBean.state}" />
										</tr>
										<tr>
											<td><h:outputText value="Status" /></td>
											<td>:<f:verbatim>&nbsp;</f:verbatim><h:outputText
												value="#{contractBasicInfoBackingBean.status}" /></td>
											<h:inputHidden id="statusHidden"
												value="#{contractBasicInfoBackingBean.status}" />
										</tr>
										<tr>
											<td><h:outputText value="Version" /></td>
											<td>:<f:verbatim>&nbsp;</f:verbatim><h:outputText
												value="#{contractBasicInfoBackingBean.version}" /></td>
											<h:inputHidden id="versionHidden"
												value="#{contractBasicInfoBackingBean.version}" />
										</tr>
									</table>
									</td>
								</tr>
								<!-- Transfer Log -->

							</table>
							</FIELDSET>
							<BR>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<h:commandButton value="Done" styleClass="wpdButton" id="button2" action="#{contractBasicInfoBackingBean.done}"></h:commandButton>
							</TD>
						</TR>
					</TABLE>

					<!-- Space for hidden fields -->

					<h:inputHidden id="mandateBoolean" value="#{contractBasicInfoBackingBean.notMandate}" />
					<h:inputHidden id="hasValErrors" value="#{contractBasicInfoBackingBean.hasValidationErrors}"></h:inputHidden>
					<h:inputHidden id="hiddenProductType" value="#{contractBenefitGeneralInfoBackingBean.pageLoadBasedOnContractType}"></h:inputHidden>
					<h:inputHidden id="duplicateData" value="#{contractBasicInfoBackingBean.duplicateData}"></h:inputHidden>
					<h:inputHidden id="lob" value="#{contractBasicInfoBackingBean.lob}"></h:inputHidden>
					<h:inputHidden id="be" value="#{contractBasicInfoBackingBean.be}"></h:inputHidden>
					<h:inputHidden id="bg" value="#{contractBasicInfoBackingBean.bg}"></h:inputHidden>
					<h:inputHidden id="mbu" value="#{contractBasicInfoBackingBean.mbu}"></h:inputHidden>
					<h:inputHidden id="uncodedLines" value="#{contractBasicInfoBackingBean.ifUncodedLineNotesExist}"></h:inputHidden>
					<%--<h:inputHidden id="hidden1" value="value1"></h:inputHidden>
					<h:commandLink id="hiddenLnk1" style="display:none; visibility: hidden;" > <f:verbatim/></h:commandLink>--%>
					<!-- End of hidden fields  -->

					<h:commandLink id="checkinLink" action="#{contractBasicInfoBackingBean.directCheckin}" style="display:none; visibility: hidden;"><f:verbatim> &nbsp;&nbsp;</f:verbatim>
					</h:commandLink> 				
				<h:inputHidden id="invokeWebService" value="#{contractBasicInfoBackingBean.invokeWebService}"></h:inputHidden>
				<h:commandLink id="cmdLnkIdForCancelButton" style="display:none; visibility: hidden;" action="#{contractBasicInfoBackingBean.doContractCancelAction}"></h:commandLink>	
				</h:form>
				</TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>

<script>
var baseContractIdStandard = document.getElementById('contractForm:baseContractIdStandardHidden').value;
	// Space for page realated scripts
 IGNORED_FIELD1='contractForm:duplicateData';
 copyHiddenToDiv_ewpd('contractForm:lineOfBusinessHidden','lineOfBusinessDiv',2,2); 
 copyHiddenToDiv_ewpd('contractForm:businessEntityHidden','businessEntityDiv',2,2); 
 copyHiddenToDiv_ewpd('contractForm:businessGroupHidden','businessGroupDiv',2,2); 
 copyHiddenToDiv_ewpd('contractForm:marketBusinessUnitHidden','marketBusinessUnit',2,2); 
 copyHiddenToDiv_ewpd('contractForm:groupSizeHidden','groupSizeDiv',2,1);  
 copyHiddenToDiv_ewpd('contractForm:statusReasonCodeHidden','statusReasonCodeDiv',2,1); 
 copyHiddenToDiv_ewpd('contractForm:termedContractId','termedcntDiv',2,1); 
 
// for uncoded notes validation
	var ifUncodedLineNotesExist = document.getElementById('contractForm:uncodedLines').value;
	if(ifUncodedLineNotesExist == 'Yes'){
		var message = "There are notes attached to un-coded benefitlines/ unanswered questions and these notes will be removed while check in. Do you want to continue?"
		if(window.confirm(message)){
			document.getElementById('contractForm:uncodedLines').value ='';
			submitLink('contractForm:checkinLink');
		}else{
			  
			  NotesErrorRow.style.display='';
			  ErrorRow.style.display ='none';	
		}
		document.getElementById('contractForm:uncodedLines').value ='';	
	}


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
var initial='yes';    
if(document.getElementById('contractForm:hasValErrors').value == 'true'){
	var url='../adminMethods/adminMethodContractValidationIntermediate.jsp'+getUrl()+'?temp='+Math.random()+'&initial='+initial;
	var returnvalue=window.showModalDialog(url,"","dialogHeight:800px;dialogWidth:1200px;resizable=true;status=no;");
	document.getElementById('contractForm:hasValErrors').value = 'false';
}
var lob = document.getElementById('contractForm:lineOfBusinessHidden').value; 
var be  = document.getElementById('contractForm:businessEntityHidden').value; 
var bg  = document.getElementById('contractForm:businessGroupHidden').value; 
var mbu  = document.getElementById('contractForm:marketBusinessUnitHidden').value; 
if(null != lob && null != be && null != bg && null != mbu){
document.getElementById('contractForm:lob').value = lob;
document.getElementById('contractForm:be').value = be;
document.getElementById('contractForm:bg').value = bg;
document.getElementById('contractForm:mbu').value = mbu;
}
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="contractBasicInfo" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('contractForm:duplicateData').value == '')
		document.getElementById('contractForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
	else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('contractForm:duplicateData').value;

function showPopupForContractTransferLog(){		
	var url='../contract/contractViewTransferLog.jsp'+getUrl()+'?temp='+Math.random();
	var returnvalue=window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;");
	if(returnvalue == undefined)
	{
//		getObj('ContractBasicSearchForm:searchButton').click();
	}
}

function loadContactIdSearchPopup(queryName,headerName,titleName,displayDiv,outComp,peerDiv,peerHiddenComp){
	ewpdModalWindow_termed_contract( '../popups/searchFilterPopupSingle.jsp'+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,2,2,peerDiv,peerHiddenComp);
}

function hideReasonCode()
{
	var selectObj = getObj('contractForm:selectContractStatus');    
	var selIndex = selectObj.selectedIndex; 
                // Get the Selected index in contract type
   	var selectItem = selectObj[selIndex];                       // Get the item object which is selected in Combo.
  	if(selectItem.value == 'ACTIVE' || selectItem.value == '') {                           
            document.getElementById("contractStatusDateRow").style.display='none';
			 document.getElementById("contractStatusReasonRow").style.display='none';
    }  else{ 
			document.getElementById("contractStatusDateRow").style.display='block';
			 document.getElementById("contractStatusReasonRow").style.display='block';
    }
}

hideReasonCode();
</script>
<script>
onPageLoadPopup();
</script>

</HTML>
