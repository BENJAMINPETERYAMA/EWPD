<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%! private String changedRules; %>

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
<TITLE>WellPoint Product Database: Associated Rules</TITLE>
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
	<BODY
		onkeypress="return submitOnEnterKey('denialAndExclusionForm:saveButton');">
	<table width="100%" cellpadding="0" cellspacing="0">
		<h:inputHidden id="pageInit"
			binding="#{productDenialAndExclusionBackingBean.loadProductRules}"></h:inputHidden>
		<TR>
			<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="denialAndExclusionForm">
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
									<TD><w:message
										value="#{productDenialAndExclusionBackingBean.validationMessages}"></w:message></TD>
								</tr>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs -->
						<table width="141" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="200" id="tab1">
								<table width="141" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{productGeneralInformationBackingBean.loadGeneralInfo}"
											onmousedown="javascript:navigatePageAction(this.id);"
											id="genId">
											<h:outputText value="General Information" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200" id="tab2">
								<table width="141" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{productComponentAssociationBackingBean.loadComponent}"
											onmousedown="javascript:navigatePageAction(this.id);"
											id="compId">
											<h:outputText value="Component Association" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200" id="tab3">
								<table width="141" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{productAdminAssociationBackingBean.loadComponent}"
											onmousedown="javascript:navigatePageAction(this.id);"
											id="adminId">
											<h:outputText value="Admin Options" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200" id="tab4">
								<table width="141" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{productNoteAssociationBackingBean.loadNotes}"
											onmousedown="javascript:navigatePageAction(this.id);"
											id="noteId">
											<h:outputText value="Notes" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200" id="tab5">
								<table width="141" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:outputText value="Rules" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	-->
						<TABLE width="100%" border="0" cellspacing="0" cellpadding="2"
							class="smallfont" >
							<TBODY>
								<TR>
									<td>&nbsp;</td>
								</TR>
								<TR>
									<TD align="left" width="160">&nbsp;<h:outputText
										id="ruleType_tx" value="Rule Type*"
										styleClass="#{productDenialAndExclusionBackingBean.requiredRuleType ? 'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<TD align="left" width="183"><h:selectOneMenu
										id="contractType_txt" styleClass="formInputField" 
										value="#{productDenialAndExclusionBackingBean.ruleType}"
										onchange="resetRuleID();">
										<f:selectItems id="contractTypeList"
											value="#{productDenialAndExclusionBackingBean.ruleTypeListForCombo}" />
									</h:selectOneMenu></TD>
								</TR>
								<tr>
									<td align="left" width="160">&nbsp;<h:outputText
										styleClass="#{productDenialAndExclusionBackingBean.requiredRuleID ? 'mandatoryError': 'mandatoryNormal'}"
										id="RuleStar" value="Rule ID*" /></td>
									<td align="left" width="183">
									<DIV id="RuleDiv" class="selectDataDisplayDiv"></DIV>
									<h:inputHidden id="txtRule"
										value="#{productDenialAndExclusionBackingBean.ruleID}" /></td>
									<td align="left" width="403"><h:commandButton alt="Select" id="RuleButton"
										image="../../images/select.gif" style="cursor: hand"
										 onclick="getRuleID(); return false;"></h:commandButton></TD>
								</tr>
								<TR>
									<TD align="left" width="160">&nbsp;<h:outputText
										styleClass="#{productDenialAndExclusionBackingBean.requiredProviderArrangement ? 'mandatoryError': 'mandatoryNormal'}"
										id="ProviderArrangementStar" value="Provider Arrangement*" />
									</TD>
									<TD align="left" width="183">
									<DIV id="ProviderArrangementDiv" class="selectDataDisplayDiv"></DIV>
									<h:inputHidden id="txtProviderArrangement"
										value="#{productDenialAndExclusionBackingBean.providerArrangement}" />
									</TD>
									<TD align="left" width="403"><h:commandButton alt="Select"
										id="ProviderArrangementButton" image="../../images/select.gif"
										style="cursor: hand" onclick="productInfo(); return false;"
										></h:commandButton></TD>
								</TR>

								<TR>
									<td height="2" width="160"></td>
								</TR>
								
								<TR>
									<td align="left" width="160"><h:commandButton
										styleClass="wpdButton" type="submit" value="Add"
										id="saveButton" 
										action="#{productDenialAndExclusionBackingBean.addAndStoreRule}"
										></h:commandButton></td>
									<td align="left" width="183">&nbsp;</td>
									<td align="left" style="cursor:hand" width="403">&nbsp;</td>
								</TR>
								<TR>
									<td width="160">&nbsp;</td>
								</TR>
							</TBODY>
						</TABLE>

						<TABLE width="100%" cellspacing="0" cellpadding="0">
							<TR>
								<TD>

								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="headerTable" border="0" width="100%">
									<TR>
										<TD><b> <h:outputText value="Associated Rule Id" /></b></TD>
									</TR>
								</TABLE>

								<table style="margin-top: 6px;">
									<tr>
										<td align="left" width="13%"><h:commandLink
											action="#{productDenialAndExclusionBackingBean.loadExclusionRule}"
                                            onmousedown="javascript:navigatePageAction(this.id);" id="exclusionId"
											styleClass="#{productDenialAndExclusionBackingBean.exclusionRuleSelected ? 'sectionheading': ''}">
											<h:outputText value="Exclusion Rule" styleClass="#{productDenialAndExclusionBackingBean.exclusionRuleSelected ? 'sectionheading': ''}"/>
										</h:commandLink></td>
										<td>&nbsp;|&nbsp;</td>
										<td align="left" width="7%"><h:commandLink
											action="#{productDenialAndExclusionBackingBean.loadUMRule}"
                                            onmousedown="javascript:navigatePageAction(this.id);" id="umId"
											styleClass="#{productDenialAndExclusionBackingBean.umRuleSelected ? 'sectionheading': ''}">
											<h:outputText value="UM Rule" styleClass="#{productDenialAndExclusionBackingBean.umRuleSelected ? 'sectionheading': ''}"/>
										</h:commandLink></td>
										<td>&nbsp;|&nbsp;</td>
										<td align="left" width="10%"><h:commandLink
											action="#{productDenialAndExclusionBackingBean.loadDenialRule}"
                                            onmousedown="javascript:navigatePageAction(this.id);" id="denialId"
											styleClass="#{productDenialAndExclusionBackingBean.denialRuleSelected ? 'sectionheading': ''}">
											<h:outputText value="Denial Rule" styleClass="#{productDenialAndExclusionBackingBean.denialRuleSelected ? 'sectionheading': ''}"/>
										</h:commandLink></td>
										<td>&nbsp;|&nbsp;</td>
										<td align="left" width="10%"><h:commandLink
											action="#{productDenialAndExclusionBackingBean.loadPNRRule}"
                                            onmousedown="javascript:navigatePageAction(this.id);" id="pnrId"
											styleClass="#{productDenialAndExclusionBackingBean.pnrRuleSelected ? 'sectionheading': ''}">
											<h:outputText value="PNR Rule" styleClass="#{productDenialAndExclusionBackingBean.pnrRuleSelected ? 'sectionheading': ''}"/>
										</h:commandLink></td>
										<td align="right" width="62%"><h:outputText value="#{productDenialAndExclusionBackingBean.pageStatus}" />
										</td>
									</tr>
								</table>
								<BR />
								<div id="InformationDiv"></div>
								<div id="resultInfo">
								<div id="resultInfoExclusion" class="dataTableColumnHeader">
								<CENTER></CENTER>
								</div>
								<div id="resultInfoDenial" class="dataTableColumnHeader">
								<CENTER></CENTER>
								</div>
								<div id="resultInfoUM" class="dataTableColumnHeader">
								<CENTER></CENTER>
								</div>
								<div id="resultInfoPNR" class="dataTableColumnHeader">
								<CENTER></CENTER>
								</div>
								</div>
								<DIV id="resultHeaderDiv">
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="resultHeaderTable" border="0" width="100%">
									<TBODY>
										<TR class="dataTableColumnHeader">
											<TD align="center"><h:outputText value="EWPD Rule Id" /></TD>
											<TD align="center"><h:outputText value="Rule Id" /></TD>
											<TD align="center"><h:outputText value="Description" /></TD>
											<TD align="center"><h:outputText value="PVA" /></TD>
											<TD align="center"><h:outputText value="Group Indicator" /></TD>
											<TD align="center"><h:outputText value="Value" /></TD>
											<TD align="center"><h:outputText value="View" /></TD>
											<TD align="center"><h:outputText value="Delete" /></TD>
											
										</TR>
									</TBODY>
								</TABLE>
										<h:inputHidden id="changedRuleIds" value="#{productDenialAndExclusionBackingBean.changedRuleIds}"></h:inputHidden>
								</DIV>
								</TD>
							</TR>

							<tr>
								<td>
								<div id="resultsTableDiv" style="height:265px; overflow: auto;">
								<h:panelGrid id="panelTable" style="margin-right:-5px"
									binding="#{productDenialAndExclusionBackingBean.panel}"
									rowClasses="dataTableEvenRow,dataTableOddRow" /></div>
								</td>
							</tr>
							<TR>
								<TD>
								<TABLE width="100%">
									<TR>
										<TD>
										<h:commandButton styleClass="wpdButton" type="submit" value="Prev" id="backButton"
										 disabled="#{productDenialAndExclusionBackingBean.disabledForPrevButton}" 
										 onclick="loadPrevRuleRecords();return false;" />
										</TD>
										<TD align="right">
										<h:commandButton styleClass="wpdButton" type="submit"
										disabled="#{productDenialAndExclusionBackingBean.disabledForNextButton}"
										value="Next" id="nextButton"
										onclick="loadNextRuleRecords();return false;" />
										</TD>
									</TR>
								</TABLE>
								</TD>
								
							</TR>
						</TABLE>
						<BR><BR>
						&nbsp;<h:commandButton styleClass="wpdButton" type="submit"
							value="Save" id="updateButton" onmousedown="javascript:savePageAction(this.id);"
							onclick="unsavedDataFinder(this.id);return false;"
							
							 /> 

						&nbsp;<h:commandButton styleClass="wpdButton" type="submit"
							value="Delete" id="delButton" onclick="unsavedDataFinder(this.id);return false;"
							
							 />


						<BR><BR>
						<!--	End of Page data	--></fieldset>
						<BR>
						<FIELDSET
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText" width="100%">
							<TBODY>
								<tr>
									<td width="4%">&nbsp;&nbsp;<h:selectBooleanCheckbox
										id="checkall" 
										value="#{productDenialAndExclusionBackingBean.checkin}"
										>
									</h:selectBooleanCheckbox></td>
									<td align="left"><h:outputText id = "check_Id" value="Check In" /></td>
									<td align="left" width="127">
									<table Width=100%>
										<tr>
											<td><h:outputText id="state" value="State" /></td>
											<TD>:</TD>
											<td><h:outputText id = "stateId"
												value="#{productDenialAndExclusionBackingBean.state}" /></td>
											<h:inputHidden id="stateHidden"
												value="#{productDenialAndExclusionBackingBean.state}"></h:inputHidden>

										</tr>
										<tr>
											<td><h:outputText id="status" value="Status" /></td>
											<TD>:</TD>
											<td><h:outputText id="statusId"
												value="#{productDenialAndExclusionBackingBean.status}" /> <h:inputHidden
												id="statusHidden"
												value="#{productDenialAndExclusionBackingBean.status}"></h:inputHidden>
											</td>
										</tr>
										<tr>
											<td><h:outputText id="versionId" value="Version" /></td>
											<TD>:</TD>
											<td><h:outputText id="versiontype"
												value="#{productDenialAndExclusionBackingBean.version}" />
											<h:inputHidden id="versionHidden"
												value="#{productDenialAndExclusionBackingBean.version}"></h:inputHidden>
											<h:inputHidden id="hasValErrors"
												value="#{productDenialAndExclusionBackingBean.hasValidationErrors}"></h:inputHidden>
											</td>
										</tr>
									</table>
									</td>
								</tr>
							</TBODY>
						</TABLE>
						</FIELDSET>
						<BR>
						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText" width="100%">
							<TBODY>
								<tr>
									<td width="1%"></td>
									<td align="left" width="6%"><h:commandButton value="Done"
										styleClass="wpdButton" id="doneButton" 
										action="#{productDenialAndExclusionBackingBean.done}">
									</h:commandButton></td>
									<td width="90%"></td>
								</tr>
							</TBODY>
						</TABLE>
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->

				<h:inputHidden id="genIds"
					value="#{productDenialAndExclusionBackingBean.genIds}" />
				<h:inputHidden id="ruleIds"
					value="#{productDenialAndExclusionBackingBean.ruleIds}" />
				<h:inputHidden id="renderFlagExclusion"
					value="#{productDenialAndExclusionBackingBean.exclusionRuleSelected}" />
				<h:inputHidden id="renderFlagDenial"
					value="#{productDenialAndExclusionBackingBean.denialRuleSelected}" />
				<h:inputHidden id="renderFlagUM"
					value="#{productDenialAndExclusionBackingBean.umRuleSelected}" />
				<h:inputHidden id="renderFlagPNR"
					value="#{productDenialAndExclusionBackingBean.pnrRuleSelected}" />
				<h:inputHidden id="renderFlag"
					value="#{productDenialAndExclusionBackingBean.renderFlag}" />
				<h:inputHidden id="hiddenProductType"
					value="#{productDenialAndExclusionBackingBean.productType}" />
				<h:inputHidden id="rowId"
					value="#{productDenialAndExclusionBackingBean.productRuleSysID}" />
				<h:inputHidden id="ewpdGenID"
					value="#{productDenialAndExclusionBackingBean.ewpdGenRuleID}" />
				<h:inputHidden id="prodId"
					value="#{productDenialAndExclusionBackingBean.prodId}" />
				<h:inputHidden id="duplicateData"
					value="#{productDenialAndExclusionBackingBean.duplicateData}"></h:inputHidden>
				<h:inputHidden id="deleteData" ></h:inputHidden>
				<h:inputHidden id="valueData" ></h:inputHidden>
				<h:commandLink id="deleteLink"
					style="display:none; visibility: hidden;"
					action="#{productDenialAndExclusionBackingBean.deleteRule}">
				</h:commandLink>
				<h:commandLink id="saveLink"
					style="display:none; visibility: hidden;"
					action="#{productDenialAndExclusionBackingBean.updateRulesComments}">
				</h:commandLink>
				<h:commandLink id="deleteRuleLink"
					style="display:none; visibility: hidden;"
					action="#{productDenialAndExclusionBackingBean.deleteRules}">
				</h:commandLink>
				<h:inputHidden id="pageno" value="#{productDenialAndExclusionBackingBean.pageno}" />
				<!-- End of hidden fields  -->
				<h:commandLink id="nextRecordLink" style="display:none; visibility: hidden;" action="#{productDenialAndExclusionBackingBean.nextReuleRecords}">
				</h:commandLink>
				<h:commandLink id="prevRecordLink" style="display:none; visibility: hidden;" action="#{productDenialAndExclusionBackingBean.prevReuleRecords}">
				</h:commandLink>
				<h:inputHidden id="ruleIdsForDelete" value="#{productDenialAndExclusionBackingBean.ruleIdsForDelete}"></h:inputHidden>
				<h:inputHidden id="genruleIdsForDelete" value="#{productDenialAndExclusionBackingBean.genruleIdsForDelete}"></h:inputHidden>

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
	name="currentPrintPage" value="productDenialExclusion" /></form>

<script>

IGNORED_FIELD1='denialAndExclusionForm:duplicateData';
IGNORED_FIELD2='denialAndExclusionForm:ruleIds';
IGNORED_FIELD3='denialAndExclusionForm:genIds';
IGNORED_FIELD4='denialAndExclusionForm:renderFlagExclusion';
IGNORED_FIELD5='denialAndExclusionForm:renderFlagDenial';
IGNORED_FIELD6='denialAndExclusionForm:renderFlagUM';
IGNORED_FIELD7='denialAndExclusionForm:renderFlagPNR';
IGNORED_FIELD8='denialAndExclusionForm:renderFlag';
IGNORED_FIELD9='denialAndExclusionForm:ewpdGenID';


enableForPanel('denialAndExclusionForm:panelTable',7,0,'denialAndExclusionForm:delButton');
	copyHiddenToDiv_ewpd('denialAndExclusionForm:txtProviderArrangement','ProviderArrangementDiv',2,2); 
	copyHiddenToDiv_ewpd('denialAndExclusionForm:txtRule','RuleDiv',2,2);

function loadNextRuleRecords(){

submitLink('denialAndExclusionForm:nextRecordLink');

}
function loadPrevRuleRecords(){

submitLink('denialAndExclusionForm:prevRecordLink');

}

function getMultipleSelectedItemsFromPanelTable(taleId,targetColNo,targetChildNo, ColumnNo, childNum, hiddenId) {
		
		var list='';

		if(document.getElementById(taleId) != null &&
			document.getElementById(taleId).rows.length > 0) {

			var rows = document.getElementById(taleId).rows;
			for(var i=0; i<rows.length; i++) {
				if(rows[i].cells[ColumnNo].children[0].checked) {
					if(list != '')
					list+='~';
					list+=rows[i].cells[targetColNo].children[0].children[targetChildNo].value;
					
				}
			}
		}

		if(document.getElementById(hiddenId) != null ){
			document.getElementById(hiddenId).value = list;
		}
	}


	function enableForPanel(taleId, ColumnNo, childNum, deleteButtonId) {
		var chk = false;

		if(document.getElementById(taleId) != null &&
			document.getElementById(taleId).rows.length > 0) {
			var rows = document.getElementById(taleId).rows;
			for(var i=0; i<rows.length; i++) {
				if(rows[i].cells[ColumnNo].children[0].checked == true) {
					chk = true;
					break;
				}
			}
		}

		if(chk) {
			document.getElementById(deleteButtonId).disabled = false;
		} else {
			document.getElementById(deleteButtonId).disabled = true;
		}

	}

	function getRuleID(){
		var ruleType = document.getElementById('denialAndExclusionForm:contractType_txt').value;
		var titleName;
		if(ruleType.length <=1){
			alert('Atleast one Rule type need to be selected.');
		}
		else{				
			if(ruleType == 'UM Rule'){
				ruleType = 'UMRULE';
				titleName = 'Product UM Rule Popup'
			}else if(ruleType == 'PNR Rule'){
				ruleType = 'UMRULE';
				titleName = 'Product PNR Rule Popup'
			}else if(ruleType == 'Denial Rule'){
				ruleType = 'BNFTDENY';
				titleName = 'Product Denial Rule Popup'
			}else if(ruleType == 'Exclusion Rule'){
				ruleType = 'ADJUD';
				titleName = 'Product Exclusion Rule Popup'
			}
					
			var url = 	'../popups/rulesSearchFilter.jsp'+getUrl()+'?entityId='+0+'&queryName=getRuleId&headerName='+ruleType+'&titleName='+titleName+'&temp='+Math.random();
			ewpdModalWindow_ewpd(url,'RuleDiv','denialAndExclusionForm:txtRule',2,2);return false;
			}
	}
	function resetRuleID()
	{
		document.getElementById('denialAndExclusionForm:txtRule').value ='';
		copyHiddenToDiv_ewpd('denialAndExclusionForm:txtRule','RuleDiv',2,2);
	}

hideIfNoValue('resultHeaderDiv');
	function hideIfNoValue(divId){

		if(null!= document.getElementById('denialAndExclusionForm:panelTable')){
			document.getElementById(divId).style.visibility = 'visible';
			document.getElementById('resultHeaderTable').width = "100%";
			var relTblWidth = document.getElementById('resultHeaderTable').offsetWidth;
			if(document.getElementById('denialAndExclusionForm:panelTable').offsetHeight <= 106) {
				document.getElementById('denialAndExclusionForm:panelTable').width = relTblWidth+"px";
				setColumnWidth('denialAndExclusionForm:panelTable','14%:12%:34%:6%:10%:9%:6%:8%');
				setColumnWidth('resultHeaderTable','14%:12%:34%:6%:10%:9%:6%:8%');
			}else{
				document.getElementById('denialAndExclusionForm:panelTable').height = 106;
				document.getElementById('denialAndExclusionForm:panelTable').width = (relTblWidth-17)+"px";
				document.getElementById('resultHeaderTable').width = (relTblWidth-17)+"px";
				setColumnWidth('denialAndExclusionForm:panelTable','14%:12%:34%:6%:10%:9%:6%:8%');
				setColumnWidth('resultHeaderTable','14%:12%:34%:6%:10%:9%:6%:8%');
			}
				document.getElementById('resultInfo').style.visibility = 'hidden';				
				document.getElementById('resultInfo').style.height = "0px";				
				document.getElementById('resultInfo').style.position = 'absolute';					
		}else{
			document.getElementById('denialAndExclusionForm:updateButton').style.visibility = 'hidden';
			document.getElementById('denialAndExclusionForm:delButton').style.visibility = 'hidden';
			document.getElementById('resultsTableDiv').style.visibility = 'hidden';
			document.getElementById('resultsTableDiv').style.height = "0px";
			document.getElementById('resultHeaderDiv').style.visibility = 'hidden';
			document.getElementById('resultHeaderDiv').style.height = "0px";
			document.getElementById(divId).style.visibility = 'hidden';
			if(document.getElementById('denialAndExclusionForm:renderFlagExclusion').value=='false')
			{	
				document.getElementById('resultInfoExclusion').style.visibility = 'hidden';		
				document.getElementById('resultInfoExclusion').style.height = "0px";			
				document.getElementById('resultInfoExclusion').position = 'absolute';	
			}			
			if(document.getElementById('denialAndExclusionForm:renderFlagDenial').value=='false')
			{	
				document.getElementById('resultInfoDenial').style.visibility = 'hidden';		
				document.getElementById('resultInfoDenial').style.height = "0px";			
				document.getElementById('resultInfoDenial').position = 'absolute';
			}			
			if(document.getElementById('denialAndExclusionForm:renderFlagUM').value=='false')
			{	
				document.getElementById('resultInfoUM').style.visibility = 'hidden';		
				document.getElementById('resultInfoUM').style.height = "0px";			
				document.getElementById('resultInfoUM').position = 'absolute';	
			}			
			if(document.getElementById('denialAndExclusionForm:renderFlagPNR').value=='false')
			{	
				document.getElementById('resultInfoPNR').style.visibility = 'hidden';		
				document.getElementById('resultInfoPNR').style.height = "0px";			
				document.getElementById('resultInfoPNR').position = 'absolute';	
			}
			if(document.getElementById('denialAndExclusionForm:renderFlagExclusion').value!='false')
			{	
				document.getElementById('InformationDiv').innerHTML = "<center><B>No associated Exclusion Rule.</B></center>";	
			}			
			if(document.getElementById('denialAndExclusionForm:renderFlagDenial').value!='false')
			{	
				document.getElementById('InformationDiv').innerHTML = "<center><B>No associated Denial Rule.</B></center>";		
			}			
			if(document.getElementById('denialAndExclusionForm:renderFlagUM').value!='false')
			{	
				document.getElementById('InformationDiv').innerHTML = "<center><B>No associated UM Rule.</B></center>";	
			}			
			if(document.getElementById('denialAndExclusionForm:renderFlagPNR').value!='false')
			{	
				document.getElementById('InformationDiv').innerHTML = "<center><B>No associated PNR Rule.</B></center>";	
			}			
		}
	}	

function confirmDeletion(key1, key2){				
		var message = "Are you sure you want to delete?"
		if(window.confirm(message)){
		document.getElementById('denialAndExclusionForm:rowId').value = document.getElementById('denialAndExclusionForm:'+key1).value;
		document.getElementById('denialAndExclusionForm:ewpdGenID').value = document.getElementById('denialAndExclusionForm:'+key2).value;
		document.getElementById('denialAndExclusionForm:deleteLink').click();
//			submitDataTableButton('denialAndExclusionForm:resultsTable', 'denialAndExclusionForm:'+key, 'denialAndExclusionForm:rowId', 'denialAndExclusionForm:deleteLink');
		}
		else{			
				return false;
		}
	}

function conDel(){				
		var message = "Are you sure you want to delete?"
		if(window.confirm(message)){
			getMultipleSelectedItemsFromPanelTable('denialAndExclusionForm:panelTable',0,0,7,0,'denialAndExclusionForm:ruleIds');getMultipleSelectedItemsFromPanelTable('denialAndExclusionForm:panelTable',0,1,7,0,'denialAndExclusionForm:genIds');
			return true;
		}
		else{			
				return false;
		}
	}


var i = document.getElementById("denialAndExclusionForm:hiddenProductType").value;
if(i=='MANDATE')
{
tab4.style.display='none';
tab3.style.display='none';
}else{
tab3.style.display='';
tab4.style.display='';
}
function productInfo(){
	var prodID = document.getElementById('denialAndExclusionForm:prodId').value;
	ewpdModalWindow_ewpd('../standardbenefitpopups/providerArrangementPopUp.jsp'+getUrl()+'?lookUpAction='+3+'&parentCatalog='+'provider arrangement'+'&entityId='+ prodID+ '&entityType=' + 'product','ProviderArrangementDiv','denialAndExclusionForm:txtProviderArrangement',2,2); 

}


if(document.getElementById('denialAndExclusionForm:hasValErrors').value == 'true'){
	var url='../adminMethods/adminMethodValidation.jsp'+getUrl()+'?temp='+Math.random();
	var returnvalue=window.showModalDialog(url,"","dialogHeight:800px;dialogWidth:1200px;resizable=true;status=no;");
	document.getElementById('denialAndExclusionForm:hasValErrors').value = 'false';
}

function viewAction(ruleIdObj){
	
	var ruleId = document.getElementById('denialAndExclusionForm:'+ruleIdObj).value;
	window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&temp=' + Math.random(),'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	
}

checkPanelForSave();
checkPanelForDelete();

function checkPanelForSave(){
	var tableObject = document.getElementById('denialAndExclusionForm:panelTable');
	if(null!= tableObject){
		if(tableObject.rows.length >0){
			deleteData = getDeleteData('denialAndExclusionForm:panelTable');
			document.getElementById('denialAndExclusionForm:deleteData').value = deleteData;
			
		}
	}
}
function getDeleteData(list){
	var tagNames = list.split(',');
	var deleteData = "";
	var tableObject = document.getElementById('denialAndExclusionForm:panelTable');
	if(null!=tableObject){
		var rows = tableObject.rows.length;
		if(rows >0){
			var columns = tableObject.rows[0].cells.length;
			for(var i=0;i<rows;i++){
				for(var j=7;j<=columns-1;j++){
					if(tableObject.rows[i].cells[j].children[0].type == 'checkbox'){
						deleteData += (tableObject.rows[i].cells[j].children[0].checked);
					}
				}
			}
			return deleteData;
		}else
			return deleteData;		
	}
}

function checkPanelForDelete(){
	var tableObject = document.getElementById('denialAndExclusionForm:panelTable');
	if(null!=tableObject){
		if(tableObject.rows.length >0){
			valueData = getValueData('denialAndExclusionForm:panelTable');
			document.getElementById('denialAndExclusionForm:valueData').value = valueData;
		}
	}
}
function getValueData(list){
	var tagNames = list.split(',');
	var valueData = "";
	var tableObject = document.getElementById('denialAndExclusionForm:panelTable');
	if(null!=tableObject){
		var rows = tableObject.rows.length;
		if(rows >0){
			var columns = tableObject.rows[0].cells.length;
			for(var i=0;i<rows;i++){
				for(var j=5;j<columns-2;j++){
					if(null != tableObject.rows[i].cells[j].children[0]){
						valueData += (tableObject.rows[i].cells[j].children[0].innerHTML); 	
					}
				}
			}
			return valueData;
		}else
			return valueData;		
	}
}

function unsavedDataFinder(objectId){
	var buttonId = objectId;
	var tableObject = document.getElementById('denialAndExclusionForm:panelTable');
	if(buttonId == 'denialAndExclusionForm:updateButton'){
		if(null!=tableObject){
			deletedata = getDeleteData('denialAndExclusionForm:panelTable');
			if(document.getElementById('denialAndExclusionForm:deleteData').value != deletedata){
				var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
				if (retValue){
					submitLink('denialAndExclusionForm:saveLink');
				}
			}else{
				submitLink('denialAndExclusionForm:saveLink');
			}
		}
	}else if(buttonId == 'denialAndExclusionForm:delButton'){
		if(null!=tableObject){
			valueData = getValueData('denialAndExclusionForm:panelTable');
			if(document.getElementById('denialAndExclusionForm:valueData').value != valueData){
				var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
				if (retValue){
					var confirmDel = conDel();
					if(confirmDel){
						submitLink('denialAndExclusionForm:deleteRuleLink');
					}
				}
			}else{
				var confirmDel = conDel();
				if(confirmDel){
					submitLink('denialAndExclusionForm:deleteRuleLink');
				}
			}
		}
	}
}


function markChange(obj,id){
	var value = document.getElementById('denialAndExclusionForm:'+id).value
	document.getElementById('denialAndExclusionForm:changedRuleIds').value += "~" + obj+'-'+value;
}
function deleteForPagination(genobj,obj,id){
//document.getElementById('denialAndExclusionForm:changedRuleIds').value += "~" + obj+'-'+value;
if(document.getElementById('denialAndExclusionForm:'+id).checked== true){
	document.getElementById('denialAndExclusionForm:ruleIdsForDelete').value += "~" + obj+'-'+'Y';
	document.getElementById('denialAndExclusionForm:genruleIdsForDelete').value += "~" + genobj+'-'+'Y';

//alert(document.getElementById('denialAndExclusionForm:ruleIdsForDelete').value);
//alert(document.getElementById('denialAndExclusionForm:genruleIdsForDelete').value);
}else{
document.getElementById('denialAndExclusionForm:ruleIdsForDelete').value += "~" + obj+'-'+'N';
document.getElementById('denialAndExclusionForm:genruleIdsForDelete').value += "~" + genobj+'-'+'N';
//alert(document.getElementById('denialAndExclusionForm:ruleIdsForDelete').value);
//alert(document.getElementById('denialAndExclusionForm:genruleIdsForDelete').value);
}
}

</script>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('denialAndExclusionForm:duplicateData').value == ''){
		document.getElementById('denialAndExclusionForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
}

	else{
	document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('denialAndExclusionForm:duplicateData').value;
}

function loadSearchPopupWithName(popupName,queryName,headerName,titleName,displayDiv,outComp,pageFrom){
		
		var url = popupName+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random()+'&pageFrom='+pageFrom;
      ewpdModalWindow_ewpd( url, displayDiv,outComp,2,2);
}

</script>
</HTML>




