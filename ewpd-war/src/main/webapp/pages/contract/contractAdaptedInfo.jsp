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
	
	function onPageLoadPopup(){
	 	var ebxFlag = document.getElementById('AdaptedInfoForm:hidd_webServiceFlag').value;
	 	var vendorFlag = document.getElementById('AdaptedInfoForm:hidd_VendorFlag').value;
	   	if(vendorFlag == "true" && ebxFlag == "true"){ 
	   		var url = '../contract/contractErrorValidationsPopup.jsp' + getUrl() + '?temp=' + Math.random();		
			document.getElementById('AdaptedInfoForm:hidd_webServiceFlag').value = "false";
			document.getElementById('AdaptedInfoForm:hidd_VendorFlag').value = "false";
			newWinForView = window.open(url,'Validations','height=600,width=1000,left=100,top=100,resizable=no,scrollbars=no,toolbar=no,menubar=no,location=no,directories=no, status=no');
	     }else if(vendorFlag == "true" && ebxFlag == "false"){
	     	var url = '../contract/contractErrorValidationsPopup.jsp' + getUrl() + '?temp=' + Math.random();
	     	document.getElementById('AdaptedInfoForm:hidd_webServiceFlag').value = "false";
			document.getElementById('AdaptedInfoForm:hidd_VendorFlag').value = "false";
			newWinForView = window.open(url,'Validations','height=600,width=1000,left=100,top=100,resizable=no,scrollbars=no,toolbar=no,menubar=no,location=no,directories=no, status=no');
	     }else if(vendorFlag == "false" && ebxFlag == "true"){
	     	var url = '../popups/ebxValidationsPopup.jsp'+ getUrl() + '?temp=' + Math.random();
	     	document.getElementById('AdaptedInfoForm:hidd_webServiceFlag').value = "false";
			document.getElementById('AdaptedInfoForm:hidd_VendorFlag').value = "false";
	     	newWinForView = window.open(url,'Validations','height=600,width=1000,left=100,top=100,resizable=no,scrollbars=no,toolbar=no,menubar=no,location=no,directories=no, status=no');
	     }

	}  

</script>
</HEAD>
<f:view>
	<BODY
		onkeypress="return submitOnEnterKey('AdaptedInfoForm:saveButton');">
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="AdaptedInfoForm">
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
									<DIV id="regulatoryAgencyDiv" class="selectDataDisplayDiv"></DIV>
									<h:inputHidden id="txtRegulatoryAgency"
										value="#{contractSpecificInfoBackingBean.regulatoryAgency}"></h:inputHidden>
									</TD>
									<TD width="48%"><h:commandButton alt="Select"
										id="RegulatoryAgencyButton" image="../../images/select.gif"
										style="cursor: hand" 
										onclick="ewpdModalWindow_ewpd('../contractpopups/regulatoryAgencyPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='
																		+'REGULATORY AGENCY'+'&titleName='+'Regulatory Agency Popup','regulatoryAgencyDiv',
																		'AdaptedInfoForm:txtRegulatoryAgency',2,1);return false;"></h:commandButton>
									</TD>
								</TR>

								<TR>
									<TD valign="top" width="227"><h:outputText id="complianceStatus"
										value="Compliance Status"></h:outputText>
									</TD>
									<TD width="26%">
										<h:selectOneMenu id="complStatus" styleClass="formInputField" 
											value="#{contractSpecificInfoBackingBean.complianceStatus}">
												<f:selectItems id="contractTypeList" value="#{ReferenceDataBackingBeanCommon.complianceStatusListForCombo}" />
										</h:selectOneMenu>
									</TD>
								</TR>

								<TR>
									<TD valign="top" width="227"><h:outputText
										id="nameCode" value="Product/Project Name Code">
									</h:outputText></TD>
									<TD width="26%">
									<DIV id="nameCodeDiv" class="selectDataDisplayDiv"></DIV>
									<h:inputHidden id="txtNameCode"
										value="#{contractSpecificInfoBackingBean.prodProjNameCode}"></h:inputHidden>
									</TD>
									<TD width="48%"><h:commandButton alt="Select"
										id="NameCodeButton" image="../../images/select.gif"
										style="cursor: hand"
										onclick="ewpdModalWindow_ewpd('../contractpopups/prodProjNameCode.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'PRODUCT/PROJECT NAME CODE'+'&titleName='+'Product/Project Name Code Popup','nameCodeDiv','AdaptedInfoForm:txtNameCode',2,2);return false;" ></h:commandButton>
									</TD>
								</TR>

								<TR>																		  		
									<TD valign="top" width="227">
										<h:outputText value="Contract Term Date"  styleClass="#{contractBasicInfoBackingBean.startDateInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<TD width="26%"><h:inputText styleClass="formInputField" id="contractStartDate_txt" maxlength="10"
										value="#{contractSpecificInfoBackingBean.contractTermDate}"/> 
									    <span class="dateFormat">(mm/dd/yyyy)</span>
									</TD>
									<TD valign="top" width="301">
										<A href="#"
											onclick="cal1.select('AdaptedInfoForm:contractStartDate_txt','anchor1','MM/dd/yyyy'); return false;"
											name="anchor1" id="anchor1" title="cal1.select('AdaptedInfoForm:contractStartDate_txt','anchor1','MM/dd/yyyy'); return false;">
											<h:commandButton  image="../../images/cal.gif" style="cursor: hand"  alt="Cal"/>
										</A>									
									</TD>
								</TR>

								<TR>
									<TD width="227"><h:outputText id="multiPlanIndicator"
										value="Multi Plan Indicator"></h:outputText>
									</TD>
									<TD width="26%">
										<h:selectOneRadio id="multiPlan" 
											value="#{contractSpecificInfoBackingBean.multiplanIndicator}">
											<f:selectItem id="PlanYes" itemLabel="Yes" itemValue="Y"/>
											<f:selectItem id="PlanNo" itemLabel="No" itemValue="N"/>
										</h:selectOneRadio>
									</TD>
								</TR>

								<TR>
									<TD width="227"><h:outputText id="perfGuarantee"
										value="Performance Guarantee"></h:outputText>
									</TD>
									<TD width="26%">
										<h:selectOneRadio id="performGuarantee"  
											value="#{contractSpecificInfoBackingBean.performanceGuarantee}">
											<f:selectItem id="perfYes" itemLabel="Yes" itemValue="Y"/>
											<f:selectItem id="perfNo" itemLabel="No" itemValue="N"/>
										</h:selectOneRadio>
									</TD>
								</TR>

								<TR>																		  		
									<TD valign="top" width="227">
										<h:outputText value="Sales Market Date"  styleClass="#{contractBasicInfoBackingBean.startDateInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<TD width="26%"><h:inputText styleClass="formInputField" id="salesMarketDate_txt" maxlength="10"
										value="#{contractSpecificInfoBackingBean.salesMarketDate}"/> 
									    <span class="dateFormat">(mm/dd/yyyy)</span>
									</TD>
									<TD valign="top" width="301">
										<A href="#"
											onclick="cal1.select('AdaptedInfoForm:salesMarketDate_txt','anchor2','MM/dd/yyyy'); return false;"
											name="anchor2" id="anchor2" title="cal1.select('AdaptedInfoForm:salesMarketDate_txt','anchor2','MM/dd/yyyy'); return false;">
											<h:commandButton  image="../../images/cal.gif" style="cursor: hand"  alt="Cal"/>
										</A>									
									</TD>
								</TR>

						
								<TR>
									<TD><h:commandButton styleClass="wpdbutton" value="Save" onmousedown="javascript:savePageAction(this.id);"
										id="saveButton" 
										action="#{contractSpecificInfoBackingBean.saveAdaptedInfo}">
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
									<td align="left"><table Width=100%><tr><td></td><td></td></tr><tr><td><h:selectBooleanCheckbox id="checkall"
										value="#{contractSpecificInfoBackingBean.checkin}">
									</h:selectBooleanCheckbox>&nbsp;
									<h:outputText value="Check In" />
									</td><td></td></tr><tr><td><a href="#" onclick=" showPopupForContractTransferLog();return false;">
										&nbsp;<h:outputText value="Transfer Log" styleClass="variableLink" /></a></td><td></td></table></td>
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
											<!-- <input type="button" value="Done" class="wpdButton" id="doneButton" onclick="processDone();"/> -->
											<h:commandButton styleClass="wpdbutton" id="button2"  value="Done" action="#{contractSpecificInfoBackingBean.checkIn}">
											</h:commandButton>
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
					<h:inputHidden id="hasValErrors"
						value="#{contractSpecificInfoBackingBean.hasValidationErrors}"></h:inputHidden>
					<h:inputHidden id="duplicateData"
						value="#{contractSpecificInfoBackingBean.duplicateData}"></h:inputHidden>
					<h:inputHidden id="uncodedLines" 
						value="#{contractSpecificInfoBackingBean.ifUncodedLineNotesExist}"></h:inputHidden>

					<h:commandLink id="checkinLink" 
						action="#{contractBasicInfoBackingBean.directCheckin}" style="display:none; visibility: hidden;"><f:verbatim> &nbsp;&nbsp;</f:verbatim>
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
	IGNORED_FIELD1='AdaptedInfoForm:duplicateData';
	copyHiddenToDiv_ewpd('AdaptedInfoForm:txtRegulatoryAgency','regulatoryAgencyDiv',2,1); 
	copyHiddenToDiv_ewpd('AdaptedInfoForm:txtNameCode','nameCodeDiv',2,2);

// for uncoded notes validation
	var ifUncodedLineNotesExist = document.getElementById('AdaptedInfoForm:uncodedLines').value;
	if(ifUncodedLineNotesExist == 'Yes'){
		var message = "There are notes attached to un-coded benefitlines/ unanswered questions and these notes will be removed while check in. Do you want to continue?"
		if(window.confirm(message)){
			document.getElementById('AdaptedInfoForm:uncodedLines').value ='';
			submitLink('AdaptedInfoForm:checkinLink');
		}else{
			  NotesErrorRow.style.display='';
			  ErrorRow.style.display ='none';	
		}
		document.getElementById('AdaptedInfoForm:uncodedLines').value ='';
	}
	
	 

i = document.getElementById("AdaptedInfoForm:hiddenProductType").value;
	if(i=='MANDATE')
	{
	tabForStandard.style.display='none';
	tabForStandard1.style.display='none';
	}else{
	tabForStandard.style.display='';
	tabForStandard1.style.display='';
	}
var initial='yes'; 
if(document.getElementById('AdaptedInfoForm:hasValErrors').value == 'true'){
	var url='../adminMethods/adminMethodContractValidation.jsp'+getUrl()+'?temp='+Math.random()+'&initial='+initial;
	var returnvalue=window.showModalDialog(url,"","dialogHeight:800px;dialogWidth:1200px;resizable=true;status=no;");
	document.getElementById('AdaptedInfoForm:hasValErrors').value = 'false';
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
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('AdaptedInfoForm:duplicateData').value == '')
		document.getElementById('AdaptedInfoForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
	else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('AdaptedInfoForm:duplicateData').value;
</script>
<script>

onPageLoadPopup();
</script>
</HTML>
