<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css" 			title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css" 			title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css" 			title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"		title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" 	title="Style">

<TITLE>WellPoint Product Database: Rules Information</TITLE>
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
function processDone(){
	document.getElementById('rulesInfoForm:invokeWebService').value = '';
	document.getElementById('rulesInfoForm:button2').click();
} 
function onPageLoadPopup(){
 	var ebxFlag = document.getElementById('rulesInfoForm:hidd_webServiceFlag').value;
 	var vendorFlag = document.getElementById('rulesInfoForm:hidd_VendorFlag').value;
   	if(vendorFlag == "true" && ebxFlag == "true"){ 
   		var url = '../contract/contractErrorValidationsPopup.jsp' + getUrl() + '?temp=' + Math.random();		
		document.getElementById('rulesInfoForm:hidd_webServiceFlag').value = "false";
		document.getElementById('rulesInfoForm:hidd_VendorFlag').value = "false";
		newWinForView = window.open(url,'Validations','height=600,width=1000,left=100,top=100,resizable=no,scrollbars=no,toolbar=no,menubar=no,location=no,directories=no, status=no');
     }else if(vendorFlag == "true" && ebxFlag == "false"){
     	var url = '../contract/contractErrorValidationsPopup.jsp' + getUrl() + '?temp=' + Math.random();
     	document.getElementById('rulesInfoForm:hidd_webServiceFlag').value = "false";
		document.getElementById('rulesInfoForm:hidd_VendorFlag').value = "false";
		newWinForView = window.open(url,'Validations','height=600,width=1000,left=100,top=100,resizable=no,scrollbars=no,toolbar=no,menubar=no,location=no,directories=no, status=no');
     }else if(vendorFlag == "false" && ebxFlag == "true"){
     	var url = '../popups/ebxValidationsPopup.jsp'+ getUrl() + '?temp=' + Math.random();
     	document.getElementById('rulesInfoForm:hidd_webServiceFlag').value = "false";
		document.getElementById('rulesInfoForm:hidd_VendorFlag').value = "false";
     	newWinForView = window.open(url,'Validations','height=600,width=1000,left=100,top=100,resizable=no,scrollbars=no,toolbar=no,menubar=no,location=no,directories=no, status=no');
     }
}  

</script>
</HEAD>
<f:view>
	<BODY onkeypress="return submitOnEnterKey('rulesInfoForm:addRuleInfoButton');">
	<table width="100%" cellpadding="0" cellspacing="0">
	
		<TR>
			<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="rulesInfoForm">
			   <h:inputHidden id="hidd_webServiceFlag" value="#{contractRuleBackingBean.webServiceFlag}"/>
			   <h:inputHidden id="hidd_VendorFlag" value="#{contractRuleBackingBean.vendorFlag}"/>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<DIV class="treeDiv"><!-- Space for Tree  Data	--> <jsp:include
							page="contractTree.jsp"></jsp:include></DIV>

						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr id ="ErrorRow">
									<TD><w:message value="#{contractRuleBackingBean.validationMessages}"></w:message></TD>
								</tr>
								
									<TR  id="NotesErrorRow" style="display:none;">
										<TD><div class='errorDiv' /><li id=errorItem>There are notes attached to un-coded benefitlines/unanswered questions.&nbsp;&nbsp;<img id='link_Notes' src='../../images/select.gif' alt='Select' 
											onclick= "callUncodedNotesNotesPopUp();return false;" style='cursor: hand'  /></li></div></TD>
									</TR>	
							</TBODY>
						</TABLE>
						<!-- WAS 7.0 Changes - Hidden id pageInit value loaded using binding instead of value -->
						<h:inputHidden id="pageInit" 	binding="#{contractRuleBackingBean.loadContractRules}"></h:inputHidden>
						<!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<tr>
									<td width="18%">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" alt="Tab LeftNormal"
												width="3" height="21" /></td>
											<td class="tabNormal"><h:commandLink
												action="#{contractBasicInfoBackingBean.getBasicInfo}"
												id="linkToComment" onmousedown="javascript:navigatePageAction(this.id);">
												<h:outputText id="GeneralInformation"
													value=" General Information"></h:outputText>
											</h:commandLink></td>
											<td width="2" align="right"><img
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21" /></td>
										</tr>
									</table>
									</td>
									<TD width="18%">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21" /></td>
											<TD width="100%" class="tabNormal"><h:commandLink
												action="#{contractPricingInfoBackingBean.dispalyContractPricingInfoTab}" id="priceId" onmousedown="javascript:navigatePageAction(this.id);">
												<h:outputText id="pricingInfoTabTable"
													value="Pricing Information" />
											</h:commandLink></TD>
											<td width="2" align="right"><img
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21" /></td>
										</TR>
									</TABLE>
									</TD>
									<td width="14%" id="tabForStandard1">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" alt="Tab LeftNormal"
												width="3" height="21" /></td>
											<td class="tabNormal"><h:commandLink
												action="#{ContractNotesBackingBean.load}"
												id="linkToNotes" onmousedown="javascript:navigatePageAction(this.id);">
												<h:outputText id="labelNotes" value="Notes"></h:outputText>
											</h:commandLink></td>
											<td width="2" align="right"><img
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21" /></td>
										</tr>
									</table>
									</td>
									<td width="16%">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" alt="Tab LeftNormal"
												width="3" height="21" /></td>
											<td class="tabNormal"><h:commandLink
												action="#{contractCommentBackingBean.loadComment}" id="link" onmousedown="javascript:navigatePageAction(this.id);">
												<h:outputText id="LabelComments" value="Comments"></h:outputText>
											</h:commandLink></td>
											<td width="2" align="right"><img
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21" /></td>
										</tr>
									</table>
									</td>
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
												src="../../images/activeTabLeft.gif" alt="Tab LeftNormal"
												width="3" height="21" /></td>
											<td class="tabActive">Rule</td>
											<td width="2" align="right"><img
												src="../../images/activeTabRight.gif" alt="Tab Right Normal"
												width="4" height="21" /></td>
										</tr>
									</table>
									</td>
								</tr>
							</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	-->
						<table width="100%" border="0" cellspacing="0" cellpadding="2">
									<tr><td>&nbsp;</td></tr>
									<TR>
									<TD align="left" width="160"><h:outputText
										id="ruleType_tx" value="Rule Type*"
										styleClass="#{contractRuleBackingBean.requiredRuleType ? 'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<TD align="left" width="183"><h:selectOneMenu
										id="ruleType_txt" styleClass="formInputField" 
										value="#{contractRuleBackingBean.ruleType}"
										onchange="resetRuleID();">
										<f:selectItems id="contractTypeList"
											value="#{contractRuleBackingBean.ruleTypeListForCombo}" />
									</h:selectOneMenu></TD>
								</TR>
									<tr>
										<td width="150" align="left"><h:outputText
											styleClass="#{contractRuleBackingBean.ruleInvalid ? 'mandatoryError': 'mandatoryNormal'}"
											id="pricingOutText" value="Rule Id*"></h:outputText></td>
										<td align="left" width="180">
										<DIV id="RuleDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="ruleHidden"
											value="#{contractRuleBackingBean.ruleHidden}"></h:inputHidden>
										</td>
										<td align="left"><img src="../../../images/select.gif" alt="Select" width="15" height="15" style="cursor:hand;" onclick="getRuleID(); return false;"/></td>
									</tr>
	
									<tr></tr>
									<tr></tr>
									<tr><td>&nbsp;</td></tr>
									<tr>
										<td colspan="3" valign="top"><h:commandButton
											value="Save" styleClass="wpdbutton" 
											id="addRuleInfoButton"
											action="#{contractRuleBackingBean.addAndStoreRuleInfo}"
											disabled=""></h:commandButton></td>
									</tr>
									<tr><td>&nbsp;</td>
									</tr>
								</table>
									<TABLE width="100%" cellspacing="0" cellpadding="0">
										<TR>
											<TD>
<!-- 
											<DIV id="resultHeaderDiv">
 -->	
											<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
												id="headerTable" border="0" width="100%">
												<TR>
													<TD><b> <h:outputText value="Associated Rule Id"/></b></TD>
												</TR>
											</TABLE>

		<table style="margin-top: 6px;">
					<tr>
						<td align="left">
							<h:commandLink action="#{contractRuleBackingBean.loadExclusionRule}" id="linkToExclusions" onmousedown="javascript:navigatePageAction(this.id);"
							styleClass="#{contractRuleBackingBean.exclusionRuleSelected ? 'sectionheading': ''}">
							<h:outputText styleClass="#{contractRuleBackingBean.exclusionRuleSelected ? 'sectionheading': ''}" value="Exclusion Rule"/></h:commandLink>  
						</td>
						<td>
							 &nbsp;|&nbsp;
						</td>
						<td align="left" >
							<h:commandLink action="#{contractRuleBackingBean.loadUMRule}" id="linkToUMRule" onmousedown="javascript:navigatePageAction(this.id);"
							styleClass="#{contractRuleBackingBean.umRuleSelected ? 'sectionheading': ''}">
							<h:outputText value="UM Rule" styleClass="#{contractRuleBackingBean.umRuleSelected ? 'sectionheading': ''}"/></h:commandLink>  
						</td>
						<td>
							 &nbsp;|&nbsp;
						</td>
						<td align="left" >
							<h:commandLink action="#{contractRuleBackingBean.loadDenialRule}" id="linkToDenialRule" onmousedown="javascript:navigatePageAction(this.id);"
							styleClass="#{contractRuleBackingBean.denialRuleSelected ? 'sectionheading': ''}">
							<h:outputText value="Denial Rule" styleClass="#{contractRuleBackingBean.denialRuleSelected ? 'sectionheading': ''}"/></h:commandLink>  
						</td>
						<td>
							 &nbsp;|&nbsp;
						</td>
						<td align="left" >
							<h:commandLink action="#{contractRuleBackingBean.loadPNRRule}" id="linkToPNRRule" onmousedown="javascript:navigatePageAction(this.id);"
							styleClass="#{contractRuleBackingBean.pnrRuleSelected ? 'sectionheading': ''}">
							<h:outputText value="PNR Rule" styleClass="#{contractRuleBackingBean.pnrRuleSelected ? 'sectionheading': ''}"/></h:commandLink>  
						</td>
					</tr>
		</table>								
<BR/>
						<div id="resultInfo">
						<div id="resultInfoExclusion" class="dataTableColumnHeader">
						<CENTER>No associated Exclusion Rule.	</CENTER></div>
						<div id="resultInfoDenial" 	class="dataTableColumnHeader">
						<CENTER>No associated Denial Rule.	</CENTER></div>
						<div id="resultInfoUM" 		class="dataTableColumnHeader">
						<CENTER>No associated UM Rule.	</CENTER></div>
						<div id="resultInfoPNR" 	class="dataTableColumnHeader">
						<CENTER>No associated PNR Rule.	</CENTER></div>
						</div>
											<DIV id="resultHeaderDiv">
											<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
												id="resultHeaderTable" border="0" width="100%">
												<TBODY>
													<TR class="dataTableColumnHeader">
														<TD align="center"><h:outputText value="EWPD Rule Id" /></TD>
														<TD align="center"><h:outputText value="Rule Id"/></TD>
														<TD align="center"><h:outputText value="Description"/></TD>
														<TD align="center"><h:outputText value="PVA"/></TD>
														<TD align="center"><h:outputText value="Group Indicator"/></TD>
														<TD align="center"><h:outputText value="Value"/></TD>
														<TD align="center"><h:outputText value="Delete"/></TD>
														<TD>&nbsp;</TD>
														</TR>
												</TBODY>
											</TABLE>
											</DIV>
											</TD>
										</TR>

										<tr>
											<td>
												<div id="resultsTableDiv"	style="height:104px;overflow:auto;overflow-x:hidden;">
												<h:panelGrid
												id="panelTable"
												binding="#{contractRuleBackingBean.panel}"
												rowClasses="dataTableEvenRow,dataTableOddRow"/>
												</div>
											</td>
										</tr>
										<tr><td>&nbsp;</td></tr>
										<tr><td>&nbsp;<DIV id='deleteAdminOption'><h:commandButton	styleClass="wpdButton" type="submit" value="Save" id="updateButton" onmousedown="javascript:savePageAction(this.id);"
						action="#{contractRuleBackingBean.updateRules}">  <f:verbatim> &nbsp;</f:verbatim>
						</h:commandButton>
											<h:commandButton id="delete" value="Delete"  
												styleClass="wpdButton" onclick = "confirmDeletion(); 
												return false;"> 
											</h:commandButton>
										</DIV></td></tr>
									</TABLE>
</BR>



						<!--	End of Page data	--></fieldset>

						<table width="100%">
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>
								<table border="0" width="100%" class="tableBorder">
									<tr>
										<td width="1%">&nbsp;</td>
										<td width="70%" align="left"><table Width=100%><tr><td></td><td></td></tr><tr><td><h:selectBooleanCheckbox id="checkall"
											value="#{contractRuleBackingBean.checkIn}">
										</h:selectBooleanCheckbox> &nbsp;<h:outputText value="Check In"></h:outputText>
										</td><td></td></tr><tr><td><a href="#" onclick=" showPopupForContractTransferLog();return false;">
										&nbsp;<h:outputText value="Transfer Log" styleClass="variableLink" /></a></td><td></td></table></td>
										
										<td align ="left" width="18%">
										<table Width=100%>
											<tr>
												<td width="35%">State</td>
												<td width="197">:&nbsp;<h:outputText id ="txtState"
													value="#{contractRuleBackingBean.state}"
													styleClass="outputText" /></td>
											</tr>
											<tr>
												<td width="105">Status</td>
												<td width="197">:&nbsp;<h:outputText  id ="txtStatus"
													value="#{contractRuleBackingBean.status}"
													styleClass="outputText" /></td>
											</tr>
											<tr>
												<td width="105">Version</td>
												<td width="197">:&nbsp;<h:outputText  id ="txtVersion"
													value="#{contractRuleBackingBean.version}"
													styleClass="outputText" /></td>
											</tr>
										</table>
										</td>
									</tr>
									<!-- Transfer Log -->
								
								</table>
								</td>
							</tr>
							<TR><TD>&nbsp;</TD>
							</TR>
							<tr>
								<td>&nbsp;&nbsp;
								<h:commandButton value="Done" styleClass="wpdButton" id="button2" action="#{contractRuleBackingBean.done}">
								</h:commandButton>
								<!-- <input type="button" value="Done" class="wpdButton" id="doneButton" onclick="processDone();"/> -->
								</td>
							</tr>
						</table>
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->

				
				<h:inputHidden id="hiddenDateSegmentSysId" value="#{contractRuleBackingBean.hiddenDateSegmentSysId}"></h:inputHidden>
				<h:commandLink id="deleteButton1"
										action="#{contractRuleBackingBean.deleteRuleInfo}"
										style="display:none; visibility: hidden;">
										<f:verbatim> &nbsp;&nbsp;</f:verbatim>
									</h:commandLink>
									<h:inputHidden id="selectedDateSegmentSysID" value="#{contractRuleBackingBean.selectedDateSysId}"></h:inputHidden>
									<h:inputHidden id="selectedRuleSysID" value="#{contractRuleBackingBean.selectedRuleSysID}"></h:inputHidden>
									<h:inputHidden id="ewpdGenID"			value="#{contractRuleBackingBean.ewpdGenRuleID}"/>
									<h:inputHidden id="renderFlagExclusion"	value="#{contractRuleBackingBean.exclusionRuleSelected}"/>
									<h:inputHidden id="renderFlagDenial"	value="#{contractRuleBackingBean.denialRuleSelected}"/>
									<h:inputHidden id="renderFlagUM"		value="#{contractRuleBackingBean.umRuleSelected}"/>
									<h:inputHidden id="renderFlagPNR"		value="#{contractRuleBackingBean.pnrRuleSelected}"/>
									<h:inputHidden id="renderFlag" value= "#{contractRuleBackingBean.renderFlagForPanel}"/> 
									<h:inputHidden id="uncodedLines" value="#{contractRuleBackingBean.ifUncodedLineNotesExist}"></h:inputHidden>	
									<h:commandLink id="deleteLink"
											style="display:none; visibility: hidden;"
											action="#{contractRuleBackingBean.deleteRuleInfo}">
											<f:verbatim />
									</h:commandLink>
									<h:commandLink id="checkinLink" 
									action="#{contractBasicInfoBackingBean.directCheckin}" style="display:none; visibility: hidden;"><f:verbatim> &nbsp;&nbsp;</f:verbatim>
									</h:commandLink> 
<h:inputHidden id="hiddenProductType"
					value="#{contractBenefitGeneralInfoBackingBean.pageLoadBasedOnContractType}"></h:inputHidden>
<h:inputHidden id="hasValErrors"
					value="#{contractRuleBackingBean.hasValidationErrors}"></h:inputHidden>
<h:inputHidden id="duplicateData" value="#{contractRuleBackingBean.duplicateData}"></h:inputHidden>

	<DIV id="dummyDiv" style="visibility:hidden"></DIV>

				<!-- End of hidden fields  -->
				<h:inputHidden id="invokeWebService" value="#{contractRuleBackingBean.invokeWebService}"></h:inputHidden>
				<h:commandLink id="cmdLnkIdForCancelButton" style="display:none; visibility: hidden;" action="#{contractRuleBackingBean.doContractCancelAction}"></h:commandLink>
			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="contractRulesInformation" /></form>
<script>
	IGNORED_FIELD1='rulesInfoForm:duplicateData'; 

// for uncoded notes validation
	var ifUncodedLineNotesExist = document.getElementById('rulesInfoForm:uncodedLines').value;
	if(ifUncodedLineNotesExist == 'Yes'){
		var message = "There are notes attached to un-coded benefitlines/ unanswered questions and these notes will be removed while check in. Do you want to continue?"
		if(window.confirm(message)){
			document.getElementById('rulesInfoForm:uncodedLines').value ='';
			submitLink('rulesInfoForm:checkinLink');
		}else{
			  NotesErrorRow.style.display='';
			  ErrorRow.style.display ='none';	
		}
		document.getElementById('rulesInfoForm:uncodedLines').value ='';
	}
	function getRuleID(){
		var entityId = document.getElementById('rulesInfoForm:hiddenDateSegmentSysId').value;
		var ruleType = document.getElementById('rulesInfoForm:ruleType_txt').value;
		var queryName = 'contractRuleTypeSearch';
		var titleName;
		var url = '../popups/contractRulesSearchFilter.jsp'+getUrl();
		if(ruleType.length <=1){
			alert('Atleast one Rule type need to be selected.');
		}
		else{
			
			if(ruleType == 'UM Rule'){
				ruleType = 'ContractUmRule';
				titleName = 'Contract UM Rule Popup';
			}else if(ruleType == 'Denial Rule'){
				ruleType = 'ContractDenialRule';
				titleName = 'Contract Denial Rule Popup';
			}else if(ruleType == 'Exclusion Rule'){
				ruleType = 'ContractExclusionRule';
				titleName = 'Contract Exclusion Rule Popup';
			}else if(ruleType == 'PNR Rule'){
				ruleType = 'ContractPnrRule';
				titleName = 'Contract PNR Rule Popup';
			}
			url = url+getUrl()+'?entityId='+entityId+'&headerName='+ruleType+'&queryName='+queryName+'&titleName='+titleName+'&temp='+Math.random();
			ewpdModalWindow_ewpd(url,'RuleDiv','rulesInfoForm:ruleHidden',3,3);

		}
						
	}
	
	function resetRuleID()
	{
		document.getElementById('rulesInfoForm:ruleHidden').value ='';
		copyHiddenToDiv_ewpd('rulesInfoForm:ruleHidden','RuleDiv',2,2);
	}


function validateDelete(){
		// var declarations
		var tableObject = null;
		var isChecked = null;
		var checkBoxObject = null;

		// get the table object
		tableObject = document.getElementById('rulesInfoForm:panelTable');
		if(tableObject){
			isChecked = false;
			// iterate the rows
			for(var i = 0; i < tableObject.rows.length; i++){
				// check whether the checkbox is checked
				checkBoxObject = tableObject.rows[i].cells[6].children[0];
				if(checkBoxObject && checkBoxObject.checked){
					isChecked = true;
					break;
				}
			}
			document.getElementById('rulesInfoForm:delete').disabled = !isChecked;			
		}
	}

function confirmDeletion(){
		var message = "Are you sure you want to delete?"
		var ruleIds = getSelectedRuleIds();
		if(ruleIds == ''){
			alert("Please select atleast one Rule to delete.");	
			return false;
		}
		else{
			if(window.confirm(message)){
				//submitDataTableButton('benefitDefinitionCreateForm:searchResultTable', 'benefitDefinitionMasterId', 'benefitDefinitionCreateForm:targetHiddenToStoreMasterKey', 'benefitDefinitionCreateForm:deleteBenefitDefinition');
			document.getElementById('rulesInfoForm:selectedRuleSysID').value = ruleIds[0];
			document.getElementById('rulesInfoForm:ewpdGenID').value = ruleIds[1];
			document.getElementById('rulesInfoForm:deleteLink').click();
			}
			else{
				return false;
			}
		}
	}

function getSelectedRuleIds(){
		// variable declarations
		var tableObject = null;
		var checkBoxObject = null;
		var ruleIds = null;
		var ewpdRuleIds = null;
		var status = null;

		// get the table object
		tableObject = document.getElementById('rulesInfoForm:panelTable');
		if(tableObject){
			ruleIds = '';
			ewpdRuleIds='';
			status = false;
			// iterate the rows
			for(var i = 0; i < tableObject.rows.length; i++){
				// check whether the checkbox is checked
				checkBoxObject = tableObject.rows[i].cells[6].children[0];
				if(checkBoxObject && checkBoxObject.checked){
					if(status){
						ruleIds = ruleIds + '~';
						ewpdRuleIds = ewpdRuleIds +'~';
					}
					status = true;
					// get the defn id and append the ids
					ruleIds = ruleIds + tableObject.rows[i].cells[0].children[0].children[0].value;
					ewpdRuleIds = ewpdRuleIds + tableObject.rows[i].cells[0].children[0].children[1].value;
				}
			}
		}
		// return the selected ids
		return [ruleIds,ewpdRuleIds];
	}

i = document.getElementById("rulesInfoForm:hiddenProductType").value;

	if(i=='MANDATE')
	{
	tabForStandard.style.display='none';
	tabForStandard1.style.display='none';
	}else{
	tabForStandard.style.display='';
	tabForStandard1.style.display='';
	}



	copyHiddenToDiv_ewpd('rulesInfoForm:ruleHidden','RuleDiv',3,3); 
	hideIfNoValue('resultHeaderDiv');
	function hideIfNoValue(divId){
		if(document.getElementById('rulesInfoForm:renderFlag').value=='true'){
			document.getElementById(divId).style.visibility = 'visible';
			
			document.getElementById('resultHeaderTable').width = "100%";
			var relTblWidth = document.getElementById('resultHeaderTable').offsetWidth;
			if(document.getElementById('rulesInfoForm:panelTable').offsetHeight <= 104) {
				document.getElementById('rulesInfoForm:panelTable').width = relTblWidth+"px";
				setColumnWidth('rulesInfoForm:panelTable','18%:13%:33%:6%:10%:10%:6%:4%');
				setColumnWidth('resultHeaderTable','18%:13%:33%:6%:10%:10%:6%:4%');
			}else{
			var relTblWidth = document.getElementById('resultHeaderTable').offsetWidth;
			document.getElementById('resultHeaderTable').width = relTblWidth-17+"px";

//				document.getElementById('rulesInfoForm:panelTable').height = 106;
	//			document.getElementById('rulesInfoForm:panelTable').width = (relTblWidth-17)+"px";
				
				setColumnWidth('rulesInfoForm:panelTable','18%:13%:33%:6%:10%:10%:6%:4%');
				setColumnWidth('resultHeaderTable','18%:13%:33%:6%:10%:10%:6%:4%');
			}
				document.getElementById('resultInfo').style.visibility = 'hidden';				
				document.getElementById('resultInfo').style.height = "0px";				
				document.getElementById('resultInfo').style.position = 'absolute';	
				document.getElementById('rulesInfoForm:delete').disabled = true;				
		}else{
			document.getElementById('deleteAdminOption').style.visibility = 'hidden';
			document.getElementById('resultsTableDiv').style.visibility = 'hidden';
			document.getElementById('resultsTableDiv').style.height = "0px";
			document.getElementById(divId).style.visibility = 'hidden';
			if(document.getElementById('rulesInfoForm:renderFlagExclusion').value=='false')
			{	
				document.getElementById('resultInfoExclusion').style.visibility = 'hidden';		
				document.getElementById('resultInfoExclusion').style.height = "0px";			
				document.getElementById('resultInfoExclusion').position = 'absolute';		
			}			
			if(document.getElementById('rulesInfoForm:renderFlagDenial').value=='false')
			{	
				document.getElementById('resultInfoDenial').style.visibility = 'hidden';		
				document.getElementById('resultInfoDenial').style.height = "0px";			
				document.getElementById('resultInfoDenial').position = 'absolute';		
			}			
			if(document.getElementById('rulesInfoForm:renderFlagUM').value=='false')
			{	
				document.getElementById('resultInfoUM').style.visibility = 'hidden';		
				document.getElementById('resultInfoUM').style.height = "0px";			
				document.getElementById('resultInfoUM').position = 'absolute';		
			}			
			if(document.getElementById('rulesInfoForm:renderFlagPNR').value=='false')
			{	
				document.getElementById('resultInfoPNR').style.visibility = 'hidden';		
				document.getElementById('resultInfoPNR').style.height = "0px";			
				document.getElementById('resultInfoPNR').position = 'absolute';		
			}			
		}
	}	
var initial='yes'; 
if(document.getElementById('rulesInfoForm:hasValErrors').value == 'true'){
	var url='../adminMethods/adminMethodContractValidation.jsp'+getUrl()+'?temp='+Math.random()+'&initial='+initial;
	var returnvalue=window.showModalDialog(url,"","dialogHeight:800px;dialogWidth:1200px;resizable=true;status=no;");
	document.getElementById('rulesInfoForm:hasValErrors').value = 'false';
}

function viewAction(ruleIdObj){
	
	var ruleId = document.getElementById('rulesInfoForm:'+ruleIdObj).value;
	var date = new Date();
	window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&temp=' + Math.random()+ "&date=" + date,'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	
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
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
    if(document.getElementById('rulesInfoForm:duplicateData').value == '')
		document.getElementById('rulesInfoForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
    else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('rulesInfoForm:duplicateData').value;
</script>
<script>
onPageLoadPopup();
</script>
</HTML>